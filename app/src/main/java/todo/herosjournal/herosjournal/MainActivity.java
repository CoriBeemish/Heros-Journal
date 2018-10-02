package todo.herosjournal.herosjournal;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.widget.DrawerLayout;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity{


    //Create Objects.
    private ListView myList;
    private ListAdapter todoListAdapter;
    private TodoListSQLHelper todoListSQLHelper;

    /**
     * Used to store the last screen title. For use in {@link //#restoreActionBar()}.
     */
    private CharSequence mTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myList = (ListView) findViewById(R.id.list);
        ImageButton fabImageButton = (ImageButton) findViewById(R.id.fab_image_button);


        final ArrayList<String> list = new ArrayList<>();
        final MyCustomAdapter adapter = new MyCustomAdapter(this, list);
        SwipeDismissListViewTouchListener touchListener =
                new SwipeDismissListViewTouchListener(
                        (ListView) findViewById(R.id.list),
                        new SwipeDismissListViewTouchListener.DismissCallbacks() {
                            @Override
                            public boolean canDismiss(int position) {
                                return true;
                            }

                            @Override
                            public void onDismiss(ListView listView, int[] reverseSortedPositions) {
                                for (int position : reverseSortedPositions) {

                                    String deleteTodoItemSql = "DELETE FROM " + TodoListSQLHelper.TABLE_NAME +
                                            " WHERE " + TodoListSQLHelper._ID+ " = '" + todoListAdapter.getItemId(position) + "'";

                                    todoListSQLHelper = new TodoListSQLHelper(MainActivity.this);
                                    SQLiteDatabase sqlDB = todoListSQLHelper.getWritableDatabase();
                                    sqlDB.execSQL(deleteTodoItemSql);
                                    updateTodoList();

                                }
                            }

                        });
        fabImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                list.add("New Item");
                adapter.notifyDataSetChanged();
                AlertDialog.Builder todoTaskBuilder = new AlertDialog.Builder(MainActivity.this);
                todoTaskBuilder.setTitle("Add a List item.");
                todoTaskBuilder.setMessage("Describe the item.");
                final EditText todoET = new EditText(MainActivity.this);
                todoTaskBuilder.setView(todoET);
                todoTaskBuilder.setPositiveButton("Add Item", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String todoTaskInput = todoET.getText().toString();
                        todoListSQLHelper = new TodoListSQLHelper(MainActivity.this);
                        SQLiteDatabase sqLiteDatabase = todoListSQLHelper.getWritableDatabase();
                        ContentValues values = new ContentValues();
                        values.clear();

                        //write the Todo task input into database table
                        values.put(TodoListSQLHelper.COL1_TASK, todoTaskInput);
                        sqLiteDatabase.insertWithOnConflict(TodoListSQLHelper.TABLE_NAME, null, values, SQLiteDatabase.CONFLICT_IGNORE);

                        //update the Todo task list UI
                        updateTodoList();
                    }
                });

                todoTaskBuilder.setNegativeButton("Cancel", null);

                todoTaskBuilder.create().show();
            }
        });

        //show the ListView on the screen
        // The adapter MyCustomAdapter is responsible for maintaining the data backing this list and for producing
        // a view to represent an item in that data set.

        updateTodoList();
    }

   // @Override
    public void onNavigationDrawerItemSelected(int position) {
        // update the main content by replacing fragments
        FragmentManager fragmentManager = getSupportFragmentManager();
        switch (position){
            case 0:
                fragmentManager.beginTransaction()
                       // .replace(R.id.container, PlaceholderFragment.newInstance(position + 1))
                        .commit();
                break;
            case 1:
                Intent intent = new Intent(this, About.class);
                startActivity(intent);
                break;
        }
    }

    public void onSectionAttached(int number) {
        switch (number) {
            case 1:
                mTitle = getString(R.string.title_section1);
                break;
            case 2:
                mTitle = getString(R.string.title_section2);
                break;
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        //noinspection SimplifiableIfStatement
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            ArrayList<String> list = new ArrayList<>();

            MyCustomAdapter adapter = new MyCustomAdapter(this, list);

            list.add("New Item");
            adapter.notifyDataSetChanged();
            AlertDialog.Builder todoTaskBuilder = new AlertDialog.Builder(MainActivity.this);
            todoTaskBuilder.setTitle("Add List Item.");
            todoTaskBuilder.setMessage("Describe the item.");
            final EditText todoET = new EditText(MainActivity.this);
            todoTaskBuilder.setView(todoET);
            todoTaskBuilder.setPositiveButton("Add Task", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    String todoTaskInput = todoET.getText().toString();
                    todoListSQLHelper = new TodoListSQLHelper(MainActivity.this);
                    SQLiteDatabase sqLiteDatabase = todoListSQLHelper.getWritableDatabase();
                    ContentValues values = new ContentValues();
                    values.clear();

                    //write the Todo task input into database table
                    values.put(TodoListSQLHelper.COL1_TASK, todoTaskInput);
                    sqLiteDatabase.insertWithOnConflict(TodoListSQLHelper.TABLE_NAME, null, values, SQLiteDatabase.CONFLICT_IGNORE);

                    //update the Todo task list UI
                    updateTodoList();
                    sqLiteDatabase.close();

                }
            });

            todoTaskBuilder.setNegativeButton("Cancel", null);

            todoTaskBuilder.create().show();


            //show the ListView on the screen
            // The adapter MyCustomAdapter is responsible for maintaining the data backing this list and for producing
            // a view to represent an item in that data set.

            updateTodoList();
            return true;

        }
        return super.onOptionsItemSelected(item);

    }

    private void updateTodoList() {
        todoListSQLHelper = new TodoListSQLHelper(MainActivity.this);
        SQLiteDatabase sqLiteDatabase = todoListSQLHelper.getReadableDatabase();

        //cursor to read todo task list from database
        Cursor cursor = sqLiteDatabase.query(TodoListSQLHelper.TABLE_NAME,
                new String[]{TodoListSQLHelper._ID, TodoListSQLHelper.COL1_TASK},
                null, null, null, null, null);

        //binds the todo task list with the UI
        todoListAdapter = new SimpleCursorAdapter(
                this,
                R.layout.due,
                cursor,
                new String[]{TodoListSQLHelper.COL1_TASK},
                new int[]{R.id.due_text_view},
                0
        );

        myList.setAdapter(todoListAdapter);
    }

    //closing the todo task item
    public void onDoneButtonClick(View view) {
        View v = (View) view.getParent();
        TextView todoTV = (TextView) v.findViewById(R.id.due_text_view);
        String todoTaskItem = todoTV.getText().toString();

        String deleteTodoItemSql = "DELETE FROM " + TodoListSQLHelper.TABLE_NAME +
                " WHERE " + TodoListSQLHelper.COL1_TASK + " = '" + todoTaskItem + "'";

        todoListSQLHelper = new TodoListSQLHelper(MainActivity.this);
        SQLiteDatabase sqlDB = todoListSQLHelper.getWritableDatabase();
        sqlDB.execSQL(deleteTodoItemSql);
        updateTodoList();
        sqlDB.close();
    }
}

