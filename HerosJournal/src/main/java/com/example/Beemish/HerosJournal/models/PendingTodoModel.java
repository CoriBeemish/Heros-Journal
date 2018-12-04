package com.example.Beemish.HerosJournal.models;

// Strings removed:
    // String todoDifficulty, String todoPriority, String todoRepeat
// Still working on adding todoDifficulty, todoPriority, and todoRepeat

public class PendingTodoModel {
    private int todoID;
    private String todoTitle, todoContent, todoDate, todoTime, todoPriority, todoDifficulty, todoRepeat, todoTag;

    public PendingTodoModel(){}

    public PendingTodoModel(String todoTitle, String todoContent, String todoTag, String todoDate, String todoTime) {
        this.todoTitle = todoTitle;
        this.todoContent = todoContent;
        this.todoDate = todoDate;
        this.todoTime = todoTime;
        this.todoTag = todoTag;
    }

    public PendingTodoModel(int todoID, String todoTitle, String todoContent, String todoTag, String todoDate, String todoTime) {
        this.todoID = todoID;
        this.todoTitle = todoTitle;
        this.todoContent = todoContent;
        this.todoDate = todoDate;
        this.todoTime = todoTime;
        this.todoTag = todoTag;
    }

    // Getters
    public int getTodoID() {
        return todoID;
    }
    public String getTodoTitle() {
        return todoTitle;
    }
    public String getTodoContent() {
        return todoContent;
    }
    public String getTodoPriority(){ return todoPriority; }
    public String getTodoDifficulty() { return todoDifficulty; }
    public String getTodoRepeat() { return todoRepeat; }
    public String getTodoDate() {
        return todoDate;
    }
    public String getTodoTime() {
        return todoTime;
    }
    public String getTodoTag() {
        return todoTag;
    }

    // Setters
    public void setTodoID(int todoID) {
        this.todoID = todoID;
    }
    public void setTodoTitle(String todoTitle) {
        this.todoTitle = todoTitle;
    }
    public void setTodoContent(String todoContent) { this.todoContent = todoContent; }
    public void setTodoPriority(String todoPriority){ this.todoPriority = todoPriority; }
    public void setTodoDifficulty(String todoDifficulty) { this.todoDifficulty = todoDifficulty; }
    public void setTodoRepeat(String todoRepeat) { this.todoRepeat = todoRepeat; }
    public void setTodoDate(String todoDate) {
        this.todoDate = todoDate;
    }
    public void setTodoTime(String todoTime) {
        this.todoTime = todoTime;
    }
    public void setTodoTag(String todoTag) {
        this.todoTag = todoTag;
    }
}
