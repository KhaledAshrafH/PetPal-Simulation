import java.util.Scanner;

public class Main {
    private static final int MAX_HUNGER = 90;
    private static final int MAX_HAPPINESS = 90;
    private static final int MIN_HUNGER = 1;
    private static final int MIN_HAPPINESS = 1;
    private static final int RATE_OF_CHANGE = 10;
    private static final int INITIAL_HUNGER_LEVEL = 50;
    private static final int INITIAL_HAPPINESS_LEVEL = 50;
    static Scanner scanner = new Scanner(System.in);
    static Pet pet = null;
    public static void main(String[] args) {
        boolean isPlaying = true;
        while (isPlaying) {
            printGameMenu();
            int choice = getUserChoice();
            switch (choice) {
                case 1:
                    if (pet != null)
                        System.out.println("You already have a pet! Please end the game and start a new game to adopt a new pet.");
                    else
                        adoptPet();
                    break;
                case 2:
                    if (pet == null) {
                        System.out.println("You don't have a pet yet. Please adopt one first!");
                    } else {
                        feedPet();
                    }
                    break;
                case 3:
                    if (pet == null)
                        System.out.println("You don't have a pet yet. Please adopt one first.");
                    else
                        playWithPet();
                    break;
                case 4:
                    if (pet == null)
                        System.out.println("You don't have a pet yet. Please adopt one first.");
                    else
                        checkPetStatus();
                    break;
                case 5:
                    isPlaying = false;
                    checkPetStatus();
                    System.out.println("Do you want to start a new game? (y/n)");
                    String answer = scanner.next();
                    if (answer.equalsIgnoreCase("y")) {
                        pet = null;
                        isPlaying = true;
                    }
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }

        }
    }

    private static void playWithPet() {

    }

    private static void feedPet() {

    }

    private static void checkPetStatus() {
        System.out.println(pet.getPetName() + "'s status:");
        System.out.println("  Hunger Level: " + pet.getHungerLevel());
        System.out.println("  Happiness Level: " + pet.getHappinessLevel());
    }

    private static void adoptPet() {
        System.out.print("Enter your pet's name: ");
        String petName = scanner.nextLine();
        pet = new Pet(petName,INITIAL_HUNGER_LEVEL, INITIAL_HAPPINESS_LEVEL);
        System.out.println("Congratulations! You adopted " + petName + ".");
    }

    private static int getUserChoice() {
        int choice;
        try {
            choice = scanner.nextInt();
            scanner.nextLine();
        } catch (NumberFormatException e) {
            System.out.println("Invalid input! Please enter a number.");
            choice=0;
        }
        return choice;
    }

    private static void printGameMenu() {
        System.out.println("\nHello, Welcome to the PetPal Simulation Game!");
        System.out.println("-----------------------------------");
        System.out.println("1. Adopt a Pet");
        System.out.println("2. Feed Your Pet");
        System.out.println("3. Play with Your Pet");
        System.out.println("4. Check Pet Status");
        System.out.println("5. End Game");
        System.out.print("Enter your choice: ");
    }
}