package pointcards.io.input;

import java.util.Scanner;

public class LocalConsoleInput implements IInput {
    private Scanner scanner = new Scanner(System.in);

    public int queryInt(String message) {
        int result = 0;
        boolean valid = false;
        while (!valid) {
            System.out.print(message + ": ");
            if (scanner.hasNextInt()) {
                result = scanner.nextInt();
                valid = true;
            } else {
                System.out.println("Invalid input. Please enter an integer.");
                scanner.next(); // Clear the invalid input
            }
        }
        return result;
    }

    public int queryInt(String message, int min, int max) {
        var result = this.queryInt(message);

        if (result < min) {
            return this.queryInt("Invalid input. Please enter an integer greater than or equal to " + min + ".");
        }

        if (result > max) {
            return this.queryInt("Invalid input. Please enter an integer less than or equal to " + max + ".");
        }

        return result;
    }

    public String queryString(String message) {
        System.out.print(message + ": ");
        return scanner.next();
    }

    public char queryChar(String message) {
        char result = '\0';
        boolean valid = false;
        while (!valid) {
            System.out.print(message + ": ");
            String input = scanner.next();
            if (input.length() == 1) {
                result = input.charAt(0);
                valid = true;
            } else {
                System.out.println("Invalid input. Please enter a single character.");
            }
        }
        return result;
    }

    public String queryChoice(String message, String[] choices) {
        String result = "";
        boolean valid = false;
        while (!valid) {
            System.out.print(message + " (" + String.join(", ", choices) + "): ");
            String input = scanner.next();
            for (String choice : choices) {
                if (input.equals(choice)) {
                    result = choice;
                    valid = true;
                    break;
                }
            }
            if (!valid) {
                System.out.println("Invalid input. Please enter one of the choices.");
            }
        }
        return result;
    }
}
