# Virtual Classroom Manager
## Overview
The Virtual Classroom Manager is a Java-based application designed to facilitate the management of virtual classrooms. It allows administrators to manage classrooms, students, and assignments, while students can submit assignments.
## Features
## Admin Functionality:

*Create and remove classrooms.

*Add students to classrooms.

*Schedule assignments for classrooms.

*List all classrooms and students in a classroom.
## Student Functionality:
*Register and log in to the system.

*Submit assignments for specific classrooms

## Prerequisites
Java Development Kit (JDK) 8 or higher

Git (for version control and repository management)

IDE or text editor of your choice (e.g., Visual Studio Code, IntelliJ IDEA, Eclipse)

## Usage
## Main Menu
Upon starting the application, you will be prompted with the main menu:
1. Admin Login
2. Student Login
3. Student Registration
4. Exit
## Admin Functionality
## Admin Login:

Enter the predefined admin username and password to access admin functionalities.
Admin Commands:

add_classroom <class_name>: Create a new classroom.

remove_classroom <class_name>: Remove an existing classroom.

list_classrooms: List all existing classrooms.

add_student <student_id> <class_name>: Add a student to a specific classroom.

list_students <class_name>: List all students in a specific classroom.

schedule_assignment <class_name> <assignment_details>: Schedule a new assignment for a classroom.

## Student Functionality
Student Registration:

Register a new student with a unique student ID and password.
Student Login:

Log in using your student ID and password.
Student Commands:

submit_assignment <class_name> <assignment_details>: Submit an assignment for a specific classroom.



