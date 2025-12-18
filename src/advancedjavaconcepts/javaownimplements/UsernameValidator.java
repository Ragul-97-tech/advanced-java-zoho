package advancedjavaconcepts.javaownimplements;

import advancedjavaconcepts.ColorCode;

import java.util.InputMismatchException;
import java.util.Scanner;

public class UsernameValidator {
    public static void main(String[] args) {
        Scanner userInputs = new Scanner(System.in);

        RedBlackTree<String> users = new RedBlackTree<>();

        int choice;
        outerLoop:
        while (true) {
            System.out.println(ColorCode.boxDouble("   Special Username Validator   "));
            System.out.println("\n1. Add Username");
            System.out.println("2. Check Username exists");
            System.out.println("3. Remove Username");
            System.out.println("4. Show All Username");
            System.out.println("5. Exit");
            try {
                System.out.print("Enter your choice: ");
                choice = userInputs.nextInt();
                userInputs.nextLine();
            } catch (InputMismatchException e) {
                userInputs.nextLine();
                System.out.println(ColorCode.error("Invalid choice! Try again"));
                continue;
            }
            switch (choice) {
                case 1:
                    System.out.print("Enter username: ");
                    String username = userInputs.nextLine();
                    if (users.insert(username))
                        System.out.println(ColorCode.right("Username add successfully"));
                    else
                        System.out.println(ColorCode.error("Username already exists"));
                    break;
                case 2:
                    System.out.print("Enter username to check: ");
                    String checkUser = userInputs.nextLine();
                    if (users.contains(checkUser))
                        System.out.println(ColorCode.warning("Username already exists"));
                    else
                        System.out.println(ColorCode.right("New username"));
                    break;
                case 3:
                    System.out.print("Enter username to remove: ");
                    String removeUser = userInputs.nextLine();
                    if (users.remove(removeUser))
                        System.out.println(ColorCode.right("Username removed successfully"));
                    else
                        System.out.println("Invalid username");
                    break;
                case 4:
                    System.out.println("User Names: ");
                    users.traverse();
                    break;
                case 5:
                    System.out.println(ColorCode.error("Invalid choice! Try again"));
                    break outerLoop;
            }
        }
    }
}
