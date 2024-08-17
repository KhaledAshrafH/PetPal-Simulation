import java.util.Scanner;

public class Main {
    private static final int MAX_HUNGER = 90;
    private static final int MAX_HAPPINESS = 90;
    private static final int MIN_HUNGER = 1;
    private static final int MIN_HAPPINESS = 1;
    private static final int RATE_OF_CHANGE = 10;

    static Scanner scanner = new Scanner(System.in);
    static String petName="";
    static int hungerLevel;
    static int happinessLevel;
    static int choice;

    public static void main(String[] args) {
        boolean isPlaying = true;
        boolean isGameOver = false;
        while (isPlaying) {
            if(!isGameOver) {
                printGameMenu();
                choice = getUserChoice();
            }
            switch (choice) {
                case 1:
                    if (!petName.isEmpty())
                        System.out.println("You already have a pet! Please end the game and start a new game to adopt a new pet.");
                    else
                        adoptPet();
                    break;
                case 2:
                    if (petName.isEmpty()) {
                        System.out.println("You don't have a pet yet. Please adopt one first!");
                    } else {
                        feedPet();
                        if(happinessLevel<MIN_HAPPINESS){
                            isGameOver=true;
                            choice=5;
                        }
                    }
                    break;
                case 3:{
                    if (petName.isEmpty())
                        System.out.println("You don't have a pet yet. Please adopt one first.");
                    else{
                        playWithPet();
                        if(hungerLevel<MIN_HUNGER){
                            isGameOver=true;
                            choice=5;
                        }
                    }
                    break;
                }
                case 4:
                    if (petName.isEmpty())
                        System.out.println("You don't have a pet yet. Please adopt one first.");
                    else
                        checkPetStatus();
                    break;
                case 5:
                    isPlaying = false;
                    checkPetStatus();
                    if(isGameOver)
                        System.out.println("Game Over!");
                    else {
                        System.out.println("Do you want to start a new game? (y/n)");
                        String answer = scanner.next();
                        if (answer.equalsIgnoreCase("y")) {
                            petName = "";
                            isPlaying = true;
                        }
                        else System.out.println("Good Bye!");
                    }
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }

        }
    }

    private static void playWithPet() {
        if (happinessLevel<MAX_HAPPINESS){
            happinessLevel+=10;
            System.out.println("Pet played and is happier now!");
        }
        else{
            happinessLevel-=10;
            System.out.println("pet is too happy");
        }
        hungerLevel-=10;
    }

    private static void feedPet() {
        if (hungerLevel<MAX_HUNGER){
            hungerLevel+=10;
            System.out.println("Pet has been feed!");
        }
        else{
            hungerLevel-=10;
            System.out.println("pet is full!");
        }
        happinessLevel-=10;
    }

    private static void checkPetStatus() {
        System.out.println(petName + "'s status:");
        System.out.println("  Hunger Level: " + hungerLevel);
        System.out.println("  Happiness Level: " + happinessLevel);
    }

    private static void adoptPet() {
        System.out.print("Enter your pet's name: ");
        petName = scanner.nextLine();
        hungerLevel=50;
        happinessLevel=50;
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
        System.out.println("-----------------------------------------------");
        System.out.println("1. Adopt a Pet");
        System.out.println("2. Feed Your Pet");
        System.out.println("3. Play with Your Pet");
        System.out.println("4. Check Pet Status");
        System.out.println("5. End Game");
        System.out.print("Enter your choice: ");
    }
}