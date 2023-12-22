import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Scanner;

class Contact {
    String name;
    String phoneNumber;
    String emailAddress;

    public Contact(String name, String phoneNumber, String emailAddress) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.emailAddress = emailAddress;
    }

    @Override
    public String toString() {
        return "Contact{" +
                "name='" + name + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", emailAddress='" + emailAddress + '\'' +
                '}';
    }
}

class AddressBook {
    private ArrayList<Contact> contacts;
    private Scanner scanner;

    public AddressBook() {
        this.contacts = new ArrayList<>();
        this.scanner = new Scanner(System.in);
        loadContactsFromFile();
    }

    public void addContact() {
        System.out.print("Enter contact name: ");
        String name = getInputWithValidation("name");
        System.out.print("Enter contact phone number: ");
        String phoneNumber = getInputWithValidation("phone number");
        System.out.print("Enter contact email address: ");
        String emailAddress = getInputWithValidation("email address");

        Contact contact = new Contact(name, phoneNumber, emailAddress);
        contacts.add(contact);
        System.out.println("Contact added successfully!\n");
    }

    public void removeContact() {
        System.out.print("Enter contact name to remove: ");
        String name = getInputWithValidation("name");

        contacts.removeIf(contact -> contact.name.equals(name));
        System.out.println("Contact removed successfully!\n");
    }

    public void editContact() {
        System.out.print("Enter contact name to edit: ");
        String name = getInputWithValidation("name");

        for (Contact contact : contacts) {
            if (contact.name.equals(name)) {
                System.out.print("Enter new phone number (press Enter to keep it unchanged): ");
                String newPhoneNumber = scanner.nextLine();
                if (!newPhoneNumber.isEmpty()) {
                    contact.phoneNumber = newPhoneNumber;
                }

                System.out.print("Enter new email address (press Enter to keep it unchanged): ");
                String newEmailAddress = scanner.nextLine();
                if (!newEmailAddress.isEmpty()) {
                    contact.emailAddress = newEmailAddress;
                }

                System.out.println("Contact information updated successfully!\n");
                return;
            }
        }

        System.out.println("Contact not found!\n");
    }

    public void searchContact() {
        System.out.print("Enter contact name to search: ");
        String name = getInputWithValidation("name");

        for (Contact contact : contacts) {
            if (contact.name.equals(name)) {
                System.out.println("Contact found:\n" + contact + "\n");
                return;
            }
        }

        System.out.println("Contact not found!\n");
    }

    public void displayAllContacts() {
        if (contacts.isEmpty()) {
            System.out.println("No contacts available.\n");
        } else {
            System.out.println("All Contacts:");
            for (Contact contact : contacts) {
                System.out.println(contact);
            }
            System.out.println();
        }
    }

    public void saveContactsToFile() {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("contacts.dat"))) {
            outputStream.writeObject(contacts);
            System.out.println("Contact data saved to file successfully!\n");
        } catch (IOException e) {
            System.out.println("Error saving contact data to file: " + e.getMessage() + "\n");
        }
    }

    @SuppressWarnings("unchecked")
    public void loadContactsFromFile() {
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("contacts.dat"))) {
            contacts = (ArrayList<Contact>) inputStream.readObject();
            System.out.println("Contact data loaded from file successfully!\n");
        } catch (FileNotFoundException e) {
            System.out.println("No existing contact data file found. Starting with an empty address book.\n");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading contact data from file: " + e.getMessage() + "\n");
        }
    }

    private String getInputWithValidation(String fieldName) {
        while (true) {
            String input = scanner.nextLine().trim();
            if (!input.isEmpty()) {
                return input;
            } else {
                System.out.print(fieldName + " cannot be empty. Please enter a valid " + fieldName + ": ");
            }
        }
    }
}

class AddressBookSystem {
    public static void main(String[] args) {
        AddressBook addressBook = new AddressBook();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Address Book System");
            System.out.println("1. Add Contact");
            System.out.println("2. Remove Contact");
            System.out.println("3. Edit Contact");
            System.out.println("4. Search Contact");
            System.out.println("5. Display All Contacts");
            System.out.println("6. Save Contacts to File");
            System.out.println("7. Load Contacts from File");
            System.out.println("8. Exit");
            System.out.print("Enter your choice: ");

            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    addressBook.addContact();
                    break;
                case 2:
                    addressBook.removeContact();
                    break;
                case 3:
                    addressBook.editContact();
                    break;
                case 4:
                    addressBook.searchContact();
                    break;
                case 5:
                    addressBook.displayAllContacts();
                    break;
                case 6:
                    addressBook.saveContactsToFile();
                    break;
                case 7:
                    addressBook.loadContactsFromFile();
                    break;
                case 8:
                    System.out.println("Exiting the application. Goodbye!");
                    addressBook.saveContactsToFile();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a valid option.\n");
            }
        }
    }
}
