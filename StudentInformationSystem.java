import java.util.Scanner;
import java.io.*;

public class StudentInformationSystem {

    static Scanner sc = new Scanner(System.in);

    static final int MAX = 100;

    static String[] ids = new String[MAX];
    static String[] firstNames = new String[MAX];
    static String[] lastNames = new String[MAX];
    static String[] phones = new String[MAX];

    static int count = 0;

    public static void main(String[] args) {

        loadFromFile();

        int choice;
        
        // Display Input
        do {
            System.out.println("\n=== STUDENT INFORMATION SYSTEM ===");
            System.out.println("1. Add Student");
            System.out.println("2. View Students");
            System.out.println("3. Search Student");
            System.out.println("4. Update Student");
            System.out.println("5. Delete Student");
            System.out.println("6. Exit");

            choice = getChoice();

            switch (choice) {
                case 1:
                    addStudent();
                    break;
                case 2:
                    viewStudents();
                    break;
                case 3:
                    searchStudent();
                    break;
                case 4:
                    updateStudent();
                    break;
                case 5:
                    deleteStudent();
                    break;
                case 6:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice!");
            }

        } while (choice != 6);
    }

    // Add Student Info
    static void addStudent() {
        if (count >= MAX) {
            System.out.println("Storage is full!");
            return;
        }

        System.out.print("Enter Student ID: ");
        String id = sc.nextLine();

        if (id.isEmpty()) {
            System.out.println("ID cannot be empty!");
            return;
        }

        for (int i = 0; i < count; i++) {
            if (ids[i].equals(id)) {
                System.out.println("ID already exists!");
                return;
            }
        }

        System.out.print("Enter First Name: ");
        String fn = sc.nextLine();

        System.out.print("Enter Last Name: ");
        String ln = sc.nextLine();

        System.out.print("Enter Phone: ");
        String ph = sc.nextLine();

        ids[count] = id;
        firstNames[count] = fn;
        lastNames[count] = ln;
        phones[count] = ph;

        count++;

        saveToFile();
        System.out.println("Student added successfully!");
    }

    // View Student Info
    static void viewStudents() {
        if (count == 0) {
            System.out.println("No records found.");
            return;
        }

        System.out.println("\nID\tFirst Name\tLast Name\tPhone");

        for (int i = 0; i < count; i++) {
            System.out.println(ids[i] + "\t" + firstNames[i] + "\t\t" + lastNames[i] + "\t\t" + phones[i]);
        }
    }

    // Search Student Info
    static void searchStudent() {
        System.out.print("Enter ID to search: ");
        String searchID = sc.nextLine();

        for (int i = 0; i < count; i++) {
            if (ids[i].equals(searchID)) {
                System.out.println("Student Found:");
                System.out.println("Name: " + firstNames[i] + " " + lastNames[i]);
                System.out.println("Phone: " + phones[i]);
                return;
            }
        }

        System.out.println("Student not found.");
    }

    // Update Student Info
    static void updateStudent() {
        System.out.print("Enter ID to update: ");
        String searchID = sc.nextLine();

        for (int i = 0; i < count; i++) {
            if (ids[i].equals(searchID)) {

                System.out.print("Enter new First Name: ");
                firstNames[i] = sc.nextLine();

                System.out.print("Enter new Last Name: ");
                lastNames[i] = sc.nextLine();

                System.out.print("Enter new Phone: ");
                phones[i] = sc.nextLine();

                saveToFile();
                System.out.println("Record updated successfully!");
                return;
            }
        }

        System.out.println("Student not found.");
    }

    // Delete Student Info
    static void deleteStudent() {
        System.out.print("Enter ID to delete: ");
        String searchID = sc.nextLine();

        for (int i = 0; i < count; i++) {
            if (ids[i].equals(searchID)) {

                for (int j = i; j < count - 1; j++) {
                    ids[j] = ids[j + 1];
                    firstNames[j] = firstNames[j + 1];
                    lastNames[j] = lastNames[j + 1];
                    phones[j] = phones[j + 1];
                }

                count--;

                saveToFile();
                System.out.println("Record deleted successfully!");
                return;
            }
        }

        System.out.println("Student not found.");
    }

    // Save to file
    static void saveToFile() {
        try (PrintWriter pw = new PrintWriter(new FileWriter("students.txt"))) {
            for (int i = 0; i < count; i++) {
                pw.println(ids[i] + "," + firstNames[i] + "," + lastNames[i] + "," + phones[i]);
            }
        } catch (IOException e) {
            System.out.println("Error saving file.");
        }
    }

    // Load from File
    static void loadFromFile() {
        try (BufferedReader br = new BufferedReader(new FileReader("students.txt"))) {
            String line;

            while ((line = br.readLine()) != null) {

                if (count >= MAX)
                    break;

                String[] data = line.split(",");

              
                if (data.length < 4) {
                    System.out.println("Skipping invalid line: " + line);
                    continue;
                }

                ids[count] = data[0];
                firstNames[count] = data[1];
                lastNames[count] = data[2];
                phones[count] = data[3];
                count++;
            }

        } catch (IOException e) {
            System.out.println("No saved data found.");
        }
    }
    
    static int getChoice() {
        while (true) {
            System.out.print("Enter your choice: ");
            if (sc.hasNextInt()) {
                int choice = sc.nextInt();
                sc.nextLine();
                return choice;
            } else {
                System.out.println("Invalid input!");
                sc.next();
            }
        }
    }
}
