import java.util.Scanner;

public class Main {

    // Constants for pet's maximum and minimum hunger and happiness levels
    private static final int MAX_HUNGER = 90;
    private static final int MAX_HAPPINESS = 90;
    private static final int MIN_HUNGER = 1;
    private static final int MIN_HAPPINESS = 1;

    // Rate at which hunger and happiness change
    private static final int CHANGE_RATE = 10;

    private static final Scanner scanner = new Scanner(System.in);
    private static String petName = "";
    private static int hungerLevel=50;
    private static int happinessLevel=50;
    private static int choice;
    private static boolean isGameOver = false;
    private static boolean isPlaying = true;
    public static void main(String[] args) {

        // Main game loop
        while (isPlaying) {
            if (!isGameOver) {
                printGameMenu();
                getUserChoice();
            }
            switch (choice) {
                case 1:
                    adoptPet();
                    break;
                case 2:
                    feedPet();
                    checkGameOver(hungerLevel, happinessLevel);
                    break;
                case 3: {
                    playWithPet();
                    checkGameOver(hungerLevel, happinessLevel);
                    break;
                }
                case 4:
                    checkPetStatus();
                    break;
                case 5:
                    isPlaying = false;
                    if(!petName.isEmpty())
                        checkPetStatus();

                    if (isGameOver)
                        System.out.println("Game Over!");

                    System.out.println("Do you want to start a new game? (y/n)");
                    String answer = scanner.next().toLowerCase();
                    if (answer.equalsIgnoreCase("y")) {
                        resetGame();
                        isPlaying = true;
                    }
                    else
                        System.out.println("Good Bye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
        scanner.close(); // Close Scanner to avoid resource leaks
    }

    // Resets game state for a new game
    private static void resetGame() {
        petName="";
        hungerLevel=50;
        happinessLevel=50;
    }

    // Checks game over or not!
    private static void checkGameOver(int hungerLevel, int happinessLevel) {
        if (checkGameStatus(hungerLevel, happinessLevel)) {
            isGameOver = true;
            choice = 5;
        }
    }

    // Checks if the pet's condition is critical (hunger or happiness too low)
    private static boolean checkGameStatus(int hungerLevel, int happinessLevel) {
        return hungerLevel < MIN_HUNGER || happinessLevel < MIN_HAPPINESS;
    }

    // Checks if the player has adopted a pet
    private static boolean petExists(){
        if (petName.isEmpty()) {
            System.out.println("You don't have a pet yet. Please adopt one first!");
            return false;
        }
        return true;
    }

    // Displays the pet's current status
    private static void checkPetStatus() {
        if (!petExists())
            return;

        System.out.println(petName + "'s status:");
        System.out.println("  Hunger Level: " + hungerLevel);
        System.out.println("  Happiness Level: " + happinessLevel);
    }

    // Simulates playing with the pet
    private static void playWithPet() {
        if (!petExists())
            return;

        if (happinessLevel < MAX_HAPPINESS) {
            happinessLevel += CHANGE_RATE;
            System.out.println("Pet played and is happier now!");
        }
        else {
            happinessLevel -= CHANGE_RATE;
            System.out.println("pet is too happy");
        }
        hungerLevel -= CHANGE_RATE;
    }

    // Simulates feeding the pet
    private static void feedPet() {
        if (!petExists())
            return;

        if (hungerLevel < MAX_HUNGER) {
            hungerLevel += CHANGE_RATE;
            System.out.println("Pet has been feed!");
        }
        else {
            hungerLevel -= CHANGE_RATE;
            System.out.println("pet is full!");
        }
        happinessLevel -= CHANGE_RATE;
    }

    // Allows the player to adopt a pet
    private static void adoptPet() {
        if (!petName.isEmpty()) {
            System.out.println("You already have a pet! Please end the game and start a new game to adopt a new pet.");
            return;
        }
        System.out.print("Enter your pet's name: ");
        petName = scanner.nextLine();
        System.out.println("Congratulations! You adopted " + petName + ".");
    }

    // Gets the user's choice from the menu
    private static void getUserChoice() {
        try {
            choice = Integer.parseInt(scanner.nextLine());
        }
        catch (NumberFormatException e) {
            System.out.println("Invalid input! Please enter a number.");
            choice = 0;
        }
    }

    // Displays the game menu
    private static void printGameMenu() {
        System.out.println("\nHello, Welcome to the PetPal Simulation Game!");
        System.out.println("-----------------------------------------------");
        System.out.println("1. Adopt a Pet");
        System.out.println("2. Feed Your Pet");
        System.out.println("3. Play with Your Pet");
        System.out.println("4. Check Pet Status");
        System.out.println("5. End Game");
        System.out.print("Enter your choice: ");
    }
}