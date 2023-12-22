import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

class Student {
    String name;
    String rollNumber;
    String branch;
    int admissionYear;
    int passingYear;
    String grade;

    public Student(String name, String rollNumber, String branch, int admissionYear, int passingYear, String grade) {
        this.name = name;
        this.rollNumber = rollNumber;
        this.branch = branch;
        this.admissionYear = admissionYear;
        this.passingYear = passingYear;
        this.grade = grade;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", rollNumber='" + rollNumber + '\'' +
                ", branch='" + branch + '\'' +
                ", admissionYear=" + admissionYear +
                ", passingYear=" + passingYear +
                ", grade='" + grade + '\'' +
                '}';
    }
}

public class task3 {

    private static ArrayList<Student> students = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("STUDENT MANAGEMENT SYSTEM");
            System.out.println("1. Add Student");
            System.out.println("2. Remove Student");
            System.out.println("3. Search Student");
            System.out.println("4. Display All Students");
            System.out.println("5. Exit");

            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (choice) {
                case 1:
                    addStudent();
                    break;
                case 2:
                    removeStudent();
                    break;
                case 3:
                    searchStudent();
                    break;
                case 4:
                    displayAllStudents();
                    break;
                case 5:
                    saveStudentsToFile();
                    System.out.println("Exiting the application. Goodbye!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a valid option.\n");
            }
        }
    }

    private static void addStudent() {
        System.out.print("Enter student name: ");
        String name = getInputWithValidation("name");
        System.out.print("Enter student roll number: ");
        String rollNumber = getInputWithValidation("roll number");
        System.out.print("Enter student branch: ");
        String branch = getInputWithValidation("branch");
        System.out.print("Enter student admission year: ");
        int admissionYear = getIntegerInputWithValidation("admission year");
        System.out.print("Enter student passing year: ");
        int passingYear = getIntegerInputWithValidation("passing year");
        System.out.print("Enter student grade: ");
        String grade = getInputWithValidation("grade");

        Student student = new Student(name, rollNumber, branch, admissionYear, passingYear, grade);
        students.add(student);
        System.out.println("Student added successfully!\n");
    }

    private static void removeStudent() {
        System.out.print("Enter student roll number to remove: ");
        String rollNumber = getInputWithValidation("roll number");

        students.removeIf(student -> student.rollNumber.equals(rollNumber));
        System.out.println("Student removed successfully!\n");
    }

    private static void searchStudent() {
        System.out.print("Enter student roll number to search: ");
        String rollNumber = getInputWithValidation("roll number");

        for (Student student : students) {
            if (student.rollNumber.equals(rollNumber)) {
                System.out.println("Student found:\n" + student + "\n");
                return;
            }
        }

        System.out.println("Student not found!\n");
    }

    private static void displayAllStudents() {
        if (students.isEmpty()) {
            System.out.println("No students available.\n");
        } else {
            System.out.println("All Students:");
            for (Student student : students) {
                System.out.println(student);
            }
            System.out.println();
        }
    }

    private static void saveStudentsToFile() {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("students.dat"))) {
            outputStream.writeObject(students);
            System.out.println("Student data saved to file successfully!\n");
        } catch (IOException e) {
            System.out.println("Error saving student data to file: " + e.getMessage() + "\n");
        }
    }

    private static String getInputWithValidation(String fieldName) {
        while (true) {
            String input = scanner.nextLine().trim();
            if (!input.isEmpty()) {
                return input;
            } else {
                System.out.print(fieldName + " cannot be empty. Please enter a valid " + fieldName + ": ");
            }
        }
    }

    private static int getIntegerInputWithValidation(String fieldName) {
        while (true) {
            try {
                String input = scanner.nextLine().trim();
                if (!input.isEmpty()) {
                    return Integer.parseInt(input);
                } else {
                    System.out.print(fieldName + " cannot be empty. Please enter a valid " + fieldName + ": ");
                }
            } catch (NumberFormatException e) {
                System.out.print(fieldName + " must be a valid number. Please enter a valid " + fieldName + ": ");
            }
        }
    }
}
