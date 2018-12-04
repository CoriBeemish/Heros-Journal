# Hero's-Journal
CS 4322 - Mobile Application Development Fall 2018 Semester Project

**Hero’s Journal** is a to-do list Android application that implements gamification elements to make daily tasks and errands engaging and rewarding. A user can create their own tasks or habits, where tasks are a one-time activity and habits are a repeating daily activity. When the user completes these tasks or habits, they gain experience and health to heal from previously unfinished activities. When enough experience is earned, the user will level up and gain attribute points to put into various categories that will give their character new abilities or skills.

Group Members: **Cori Beemish, Augustina Horlava, Alex Helfrich, & Casey Loria**

## User Stories

**Required Functionality**
* [x] User can **add items** from the to-do list
* [X] User can **remove items** from the to-do list
* [X] User can **edit items** on the to-do list
  * [X] User can **edit the name** of the task
  * [X] User can **add comments** to the task in the editing window.
  * [X] User can **edit the attribute stat** of the task
* [x] User can **reorder items** on the to-do list
* [X] User can see **a sprite** of their Hero on the main page.
* [X] User can see **their health, experience, and mana bars** of their Hero on the main page.
* [X] User can see their **experience points** immediately increase when they click a task as complete.
* [X] User can **level up** when they gain enough experience points.
* [X] User can **make a new account** in the app with a custom user name. 
  * [X] User can make a new account **with their email** of the task.
* [X] User can receive **"damage" to their character's health when they have not completed a task.**
  * [ ] User will receive damage at midnight local time if they have incomplete tasks.
  * [ ] Tasks will reset at midnight local time. 

**Additional Functionality**
* [X] User can edit **the time the task's alarm/notification reminder**
  * [X] The phone will vibrate when an alarm goes off and a push notification will show on the screen.
  * [X] Tasks will have individual alarms.
* [X] User can **allocate additional points** into their attributes when they level up.
* [X] User can touch their sprite from the home page and a new page will load where they can view their expanded sprite and their current stat attributes.

**Bonus Functionality**
* [ ] User can "fight" mob bosses to gain more experience and gold.
  * This is where the leveled-up attributes would come into play.
* [ ] User can get armor or weapons from dropped items from tasks, bosses, or purchased from the market. 
* [X] User can **customize their sprite** of the task.
  * [ ] User can select different pieces of armor.
  * [ ] User can select different weapons.
  * [X] User can select different skin colors.
  * [ ] User can select different hair styles.
  * [ ] User can select different hair colors.
  * [X] User can select different eye colors.
  * [ ] User can select different races. [Needs expansion]
* [ ] User can select different classes. [Needs expansion.]

 
## Attributes
* Not sure how to make the attributes work for boss battles or to increase mana, but that will be worked on in later updates.
* **S**trength: More damage to boss.
  * **Taggable.** Relating to exercise and physical activity.
* **P**erception: More loot drops (Bonus functionality)
  * **Taggable.** Related to work or financial tasks.
* **E**ndurance: More mana (Bonus Functionality)
  * **Not taggable.** Will allow points to be additionally added in at each level up.
* **C**onstitution: Less uncompleted habit damage.
  * **Taggable.** Related to health, wellness, and social interaction.
* **I**ntelligence: Relating to academic or mentally challenging pursuits
  * **Taggable.** More experience points when completing a habit.
* **A**gility: Less boss damage.
  * **Not taggable.** Will allow points to be additionally added in at each level up.
* **L**uck: More rare drops (Bonus Functionality)
  * **Not taggable.** Will allow points to be additionally added in at each level up.
  
## Leveling Up Attribute Stat Math Mechanics
* All new users start at level 1.
* Health will start at 100 points.
* Mana will start at 100 points.
* Damage from a task is 0.4 points of what the user would have received in experience for completing the task.
* Amount of experience increases the more the user keeps up the streak. 
* **Amount of experience to level up** will the level multiplied by 1000: 
  * LEVEL * 1000
* The number of **health points** a user gains per level:
  * newHealthPoints = healthPoints + (healthPoints * 0.2)
* The amount of **free points** a user gets to put into any attribute they want:
  * 1 - 25: 2
  * 26 - 50: 4
  * 51 - 75: 6
  * 76 - 100: 8
* The **attribute stat increase** occurs when a user completes enough of one specific tagged task to gain a point in that attribute:
  * Easy: 0.1 +
  * Medium: 0.2 +
  * Hard: 0.4 +
  * Example: 10 easy intelligence tasks completed in a level will allow the Intelligence Attribute Stat to increase by 1. 5 medium Constitution tasks will allow the Constitution Attribute Stat to increase by 1. 5 hard Strength tasks will allow the Strength Attribute Stat to increase by 2.
  
## Experience & Damage
* The amount of **experience gained** from a task **not tagged**:

EXP Gained | Task Not Tagged
------------ | -------------
Easy Difficulty | ((streak modifier) * 5) / 10
Medium Difficulty |  ((streak modifier) * 10) / 10
Hard Difficulty | ((streak modifier) * 25) / 10

* The amount of **damage received** from a task **not tagged**:

Damage Recieved | Task Not Tagged
------------ | -------------
Easy Difficulty| NoTagEXP_Easy * 0.4
Medium Difficulty| NoTagEXP_Medium * 0.4
Hard Difficulty| NoTagEXP_Hard * 0.4

* The amount of **experience gained** from a task **tagged Strength, Perception, Constitution, or Intelligence.**:

EXP Gained | Tagged Tasks
------------ | -------------
Easy Difficulty |  [Attribute]EXP_Easy = ((streak_modifier) * 8) / 10
Medium Difficulty |  [Attribute]EXP_Medium= ((streak_modifier) * 15) / 10
Hard Difficulty |  [Attribute]EXP_Hard = ((streak_modifier) * 30) / 10

* The amount of **damage received** from a task **tagged Strength, Perception, Constitution, or Intelligence.**:

Damage Recieved | Tagged Tasks
------------ | -------------
Easy Difficulty |  [Attribute]EXP_Easy * 0.4
Medium Difficulty | [Attribute]EXP_Medium * 0.4
Hard Difficulty | [Attribute]EXP_Hard * 0.4

https://i.imgur.com/QHK5TM8.png
