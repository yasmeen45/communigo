# Communigo 
### *Connecting Communities Through Movement*

Communigo is created for individuals looking to connect with their communities and become more active. It allows users
to create and join various group activities within a certain area. 

This application is of interest to me because I have been working on becoming more active. 
However, whenever I'm looking to go on a hike or run, I'm never able to find a group of people 
interested in joining me, so I end up losing the motivation to go. Therefore, I'd like to create Communigo
to solve this issue and foster communities that support active lifestyles.

# Instructions for Grader

- You can generate the first required action related to adding Xs to a Y by selecting the "View Upcoming Activities"
option in the main menu, selecting an activity, and selecting "Register Selected Activity".
- You can generate the second required action related to adding Xs to a Y by selecting the "View Registered Activities"
option in the main menu. This shows only registered activities, which is filtered from upcoming activities.
- You can locate my visual component on the home screen, at the very top.
- You can save the state of my application by selecting the "Save Data to File" option in the main menu.
- You can reload the state of my application by selecting the "Load Data from File" option in the main menu.

## Key Functionalities / User Stories
- As a user, I want to be able to view all upcoming activities
- As a user, I want to be able to search through upcoming activities by type, area, or date
- As a user, I want to be able to join an activity
- As a user, I want to be able to cancel an activity I have joined
- As a user, I want to be able to see the activities I have joined
- As a user, I want to be able to post an activity to the upcoming activities
- As a user, I want to be able to see the activities I have posted
- As a user, I want to be able to remove an activity I have posted
- As a user, I want to be able to save me activities to a file
- As a user, I want to be able to load my activities from a file

## Personal Goals
Not all will be completed, but some ideas for extra features:
- Display upcoming activities on a map, with more specific locations
- Mark activities as completed
- Calendar display with completed and upcoming registered activities
- Display completed activities in a log with stats
- For each activity, display number of users that have joined
- "Log-in" screen where multiple users can be chosen from, and new users can be created
    - When one user adds an event, the event is visible for all users
    - When one user joins an event, the number of users that have joined increases for all users
- User profiles (can be viewed by other users  in an event)
    - Name, interests, log of completed events
- Friend requests and friend invitations to activities
- Search for other users to friend. Can be searched by name or interests
- Chat function between friends
- Points/tier system. Points earned for each activity completed to level up through tiers from
  beginner to pro

## Phase 4: Task 2
Sample of events

## Phase 4: Task 3

One major refactoring that can be done to improve my application is splitting up my ui.Application
class into multiple classes.

This needs to be done because the Application class is currently hundreds of lines long and handles many different
tasks, so it has very low cohesion.

This can be done by:
- Creating a Printer class to handle all printing to screen
- Creating a Filer class to contain all methods related to user's
activity filtering options
- 



