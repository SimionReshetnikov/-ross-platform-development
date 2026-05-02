public class Main {
    public static void main(String[] args) {
        DatabaseManager databaseManager = new DatabaseManager();

        try {
            System.out.println("Creating table...");
            databaseManager.createTable();

            System.out.println("Adding users...");
            databaseManager.createUser("Alice");
            databaseManager.createUser("Bob");

            System.out.println("\nUsers:");
            databaseManager.readUsers();

            System.out.println("\nUpdating user...");
            databaseManager.updateUser(1, "Alice Updater");

            System.out.println("\nUsers after update:");
            databaseManager.readUsers();

            System.out.println("\nDeleting user...");
            databaseManager.deleteUser(2);

            System.out.println("\nUsers after delete:");
            databaseManager.readUsers();

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}