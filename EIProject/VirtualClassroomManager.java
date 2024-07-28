package EIProject;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class VirtualClassroomManager {
    private List<Classroom> classrooms;
    private List<Student> students;
    private boolean adminLoggedIn;
    private boolean exit=false;
    private Student loggedInStudent;
    private final String ADMIN_USERNAME = "admin"; // Change this to a secure username
    private final String ADMIN_PASSWORD = "admin123"; // Change this to a secure password

    public VirtualClassroomManager() {
        this.classrooms = new ArrayList<>();
        this.students = new ArrayList<>();
        this.adminLoggedIn = false;
        this.loggedInStudent = null;
    }

    public static void main(String[] args) {
        VirtualClassroomManager manager = new VirtualClassroomManager();
        manager.run();
    }

    private void run() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Virtual Classroom Manager");

        while (!exit) {
            System.out.println("\nMenu:");
            System.out.println("1. Admin Login");
            System.out.println("2. Student Login");
            System.out.println("3. Student Registration");
            System.out.println("4. Exit");
            System.out.print("Enter choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    adminLogin();
                    if (adminLoggedIn) {
                        adminMenu();
                    }
                    break;
                case 2:
                    studentLoginPrompt();
                    if (loggedInStudent != null) {
                        studentMenu();
                    }
                    break;
                case 3:
                    studentRegistration();
                    break;
                case 4:
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void adminMenu() {
        Scanner scanner = new Scanner(System.in);
        while (adminLoggedIn) {
            System.out.println("\nAdmin Menu:");
            System.out.println("1. Add Classroom");
            System.out.println("2. List Classrooms");
            System.out.println("3. Remove Classroom");
            System.out.println("4. Add Student to Classroom");
            System.out.println("5. List Students in Classroom");
            System.out.println("6. Schedule Assignment");
            System.out.println("7. Logout");
            System.out.print("Enter choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter class name: ");
                    addClassroom(scanner.nextLine().split(" "));
                    break;
                case 2:
                    listClassrooms();
                    break;
                case 3:
                    System.out.print("Enter class name: ");
                    removeClassroom(scanner.nextLine().split(" "));
                    break;
                case 4:
                    System.out.print("Enter student ID, password, and class name: ");
                    addStudent(scanner.nextLine().split(" "));
                    break;
                case 5:
                    System.out.print("Enter class name: ");
                    listStudents(scanner.nextLine().split(" "));
                    break;
                case 6:
                    System.out.print("Enter class name and assignment details: ");
                    scheduleAssignment(scanner.nextLine().split(" "));
                    break;
                case 7:
                    adminLoggedIn = false;
                    System.out.println("Admin logged out.");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void studentMenu() {
        Scanner scanner = new Scanner(System.in);
        while (loggedInStudent != null) {
            System.out.println("\nStudent Menu:");
            System.out.println("1. Submit Assignment");
            System.out.println("2. Logout");
            System.out.print("Enter choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter class name and assignment details: ");
                    submitAssignment(scanner.nextLine().split(" "));
                    break;
                case 2:
                    loggedInStudent = null;
                    System.out.println("Student logged out.");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void adminLogin() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter admin username: ");
        String username = scanner.nextLine().trim();
        System.out.print("Enter admin password: ");
        String password = scanner.nextLine().trim();

        if (username.equals(ADMIN_USERNAME) && password.equals(ADMIN_PASSWORD)) {
            adminLoggedIn = true;
            System.out.println("Admin logged in successfully.");
        } else {
            System.out.println("Incorrect admin username or password. Please try again.");
        }
    }

    private void studentLoginPrompt() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter student ID: ");
        String studentId = scanner.nextLine().trim();
        System.out.print("Enter student password: ");
        String password = scanner.nextLine().trim();
        studentLogin(new String[]{studentId, password});
    }

    private void studentRegistration() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter student ID: ");
        String studentId = scanner.nextLine().trim();
        System.out.print("Enter password: ");
        String password = scanner.nextLine().trim();

        studentRegister(new String[]{studentId, password});
    }

    private void studentRegister(String[] args) {
        if (args.length != 2) {
            System.out.println("Usage: student_register <student_id> <password>");
            return;
        }
        String studentId = args[0];
        String password = args[1];

        for (Student student : students) {
            if (student.getId().equals(studentId)) {
                System.out.println("Student ID already exists.");
                return;
            }
        }

        students.add(new Student(studentId, password));
        System.out.println("Student " + studentId + " registered successfully.");
    }

    private void studentLogin(String[] args) {
        if (args.length != 2) {
            System.out.println("Usage: student_login <student_id> <password>");
            return;
        }
        String studentId = args[0];
        String password = args[1];

        for (Student student : students) {
            if (student.getId().equals(studentId) && student.getPassword().equals(password)) {
                loggedInStudent = student;
                System.out.println("Student " + studentId + " logged in successfully.");
                return;
            }
        }

        System.out.println("Incorrect student ID or password. Please try again.");
    }

    private void addClassroom(String[] args) {
        if (args.length != 1) {
            System.out.println("Usage: add_classroom <class_name>");
            return;
        }
        String className = args[0];
        for (Classroom classroom : classrooms) {
            if (classroom.getName().equals(className)) {
                System.out.println("Classroom already exists.");
                return;
            }
        }
        classrooms.add(new Classroom(className));
        System.out.println("Classroom " + className + " has been created.");
    }

    private void listClassrooms() {
        if (classrooms.isEmpty()) {
            System.out.println("No classrooms found.");
            return;
        }
        for (Classroom classroom : classrooms) {
            System.out.println("Classroom: " + classroom.getName());
        }
    }

    private void removeClassroom(String[] args) {
        if (args.length != 1) {
            System.out.println("Usage: remove_classroom <class_name>");
            return;
        }
        String className = args[0];
        for (Classroom classroom : classrooms) {
            if (classroom.getName().equals(className)) {
                classrooms.remove(classroom);
                System.out.println("Classroom " + className + " has been removed.");
                return;
            }
        }
        System.out.println("Classroom not found.");
    }

    private void addStudent(String[] args) {
        if (args.length != 3) {
            System.out.println("Usage: add_student <student_id> <password> <class_name>");
            return;
        }
        String studentId = args[0];
        String password = args[1];
        String className = args[2];

        Classroom classroom = null;
        for (Classroom c : classrooms) {
            if (c.getName().equals(className)) {
                classroom = c;
                break;
            }
        }

        if (classroom == null) {
            System.out.println("Classroom not found.");
            return;
        }

        Student student = null;
        for (Student s : students) {
            if (s.getId().equals(studentId)) {
                student = s;
                break;
            }
        }

        if (student == null) {
            student = new Student(studentId, password);
            students.add(student);
        }

        classroom.addStudent(student);
        student.enrollClassroom(classroom);
        System.out.println("Student " + studentId + " has been enrolled in " + className + ".");
    }

    private void listStudents(String[] args) {
        if (args.length != 1) {
            System.out.println("Usage: list_students <class_name>");
            return;
        }
        String className = args[0];
        Classroom classroom = null;
        for (Classroom c : classrooms) {
            if (c.getName().equals(className)) {
                classroom = c;
                break;
            }
        }
        if (classroom == null) {
            System.out.println("Classroom not found.");
            return;
        }
        List<Student> students = classroom.getStudents();
        if (students.isEmpty()) {
            System.out.println("No students found in " + className + ".");
            return;
        }
        for (Student student : students) {
            System.out.println("Student ID: " + student.getId());
        }
    }

    private void scheduleAssignment(String[] args) {
        if (args.length < 2) {
            System.out.println("Usage: schedule_assignment <class_name> <assignment_details>");
            return;
        }
        String className = args[0];
        String assignmentDetails = String.join(" ", args).substring(className.length()).trim();

        Classroom classroom = null;
        for (Classroom c : classrooms) {
            if (c.getName().equals(className)) {
                classroom = c;
                break;
            }
        }

        if (classroom == null) {
            System.out.println("Classroom not found.");
            return;
        }

        classroom.addAssignment(new Assignment(assignmentDetails));
        System.out.println("Assignment for " + className + " has been scheduled.");
    }

    private void submitAssignment(String[] args) {
        if (args.length < 2) {
            System.out.println("Usage: submit_assignment <class_name> <assignment_details>");
            return;
        }
        String className = args[0];
        String assignmentDetails = String.join(" ", args).substring(className.length()).trim();

        Classroom classroom = null;
        for (Classroom c : classrooms) {
            if (c.getName().equals(className)) {
                classroom = c;
                break;
            }
        }

        if (classroom == null) {
            System.out.println("Classroom not found.");
            return;
        }

        Assignment assignment = null;
        for (Assignment a : classroom.getAssignments()) {
            if (a.getDetails().equals(assignmentDetails)) {
                assignment = a;
                break;
            }
        }

        if (assignment == null) {
            System.out.println("Assignment not found.");
            return;
        }

        assignment.submit(loggedInStudent.getId());
        System.out.println("Assignment submitted by Student " + loggedInStudent.getId() + " in " + className + ".");
    }
}
