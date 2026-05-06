import java.util.Scanner;

public class StudentInformationSystem {

    static Scanner sc = new Scanner(System.in);

    static final int MAX = 100;

    static String[] ids = new String[MAX];
    static String[] firstNames = new String[MAX];
    static String[] lastNames = new String[MAX];
    static String[] phones = new String[MAX];

    static int count = 0;

    public static void main(String[] args) {

        int choice;
        // Display Input
        do {
            System.out.println("\n STUDENT INFORMATION SYSTEM ");
            System.out.println("1. Add Student");
            System.out.println("2. View Students");
            System.out.println("3. Search Student");
            System.out.println("4. Update Student");
            System.out.println("5. Delete Student");
            System.out.println("6. Exit");
            choice = getChoice();

            switch (choice) {


            // Adding Student Info
                case 1:
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

            // check duplicate ID
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

            System.out.println("Student added successfully!");

            break;


            // View Student Info
                case 2:
                    if (count == 0) {
                        System.out.println("No records found.");
                        return;
                    }

                    System.out.println("\nID\tFirst Name\tLast Name\tPhone");

                    for (int i = 0; i < count; i++) {
                        System.out.println(ids[i] + "\t" + firstNames[i] + "\t\t" + lastNames[i] + "\t\t" + phones[i]);
                    }
                    break;
                    

            // Search Student Info
                case 3:
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

                    break;
                    
                    
            // Update Student Info
                case 4:
                    updateStudent();
                    break;

                    
            // Delete Student Info
                case 5:
                    deleteStudent();
                    break;

                    
            // Exit System       
                case 6:
                    System.out.println("Exiting program...");
                    break;

                    
                default:
                    System.out.println("Invalid choice!");
            }

        } while (choice != 6);
    }

    static int getChoice() {
        int choice;
        while (true) {
            System.out.print("Enter your choice: ");
            if (sc.hasNextInt()) {
                choice = sc.nextInt();
                sc.nextLine();
                return choice;
            } else {
                System.out.println("Invalid input! Enter a number.");
                sc.next();
            }
        }
    
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

                System.out.println("Record updated successfully!");
                return;
            }
        }

        System.out.println("Student not found.");
    }

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
                System.out.println("Record deleted successfully!");
                return;
            }
        }

        System.out.println("Student not found.");
    }
}
