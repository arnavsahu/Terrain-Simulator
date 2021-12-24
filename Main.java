import java.util.Random;
import java.util.Scanner;
public class Main {
    public static void main(String args[]) {
        final Random rand = new Random();
        final Scanner scan = new Scanner(System.in);

        int num = rand.nextInt(4);
        //creates random player each time main method is run
        Player player;
        String p1;
        if (num == 0) {
            p1 = "Bob";
            player = new Bob();
        } else if (num == 1) {
            p1 = "Alexa";
            player = new Alexa();
        } else if (num == 2) {
            p1 = "Olivia";
            player = new Olivia();
        } else {
            p1 = "Steve";
            player = new Steve();
        }

        System.out.println("Initial map: ");
        //creates map with random biomes each time the main method is run
        //creates a string array of the terrain represented by the first letter of the chosen terrain.
        Terrain[][] map = new Terrain[10][50];
        String[][] view = new String[10][50];
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                num = rand.nextInt(15);
                if (num == 0 || num == 7 || num == 8) {
                    map[i][j] = new tundra();
                    view[i][j] = "T";
                } else if (num == 1) {
                    map[i][j] = new Grass();
                    view[i][j] = "G";
                } else if (num == 2 || num == 9) {
                    map[i][j] = new River();
                    view[i][j] = "R";
                } else if (num == 3 || num == 10) {
                    map[i][j] = new Mountain();
                    view[i][j] = "M";
                } else if (num == 4) {
                    map[i][j] = new Desert();
                    view[i][j] = "D";
                } else if (num == 5 || num == 11 || num == 12) {
                    map[i][j] = new Jungle();
                    view[i][j] = "J";
                } else {
                    map[i][j] = new Lake();
                    view[i][j] = "L";
                }
            }
        }

        for(int i = 0; i < map.length; i++){
            for(int j = 0; j < map[0].length; j++){
                System.out.print(view[i][j]);
            }
            System.out.println();
        }

        System.out.println();

        //asks user to choose one of the brains. Then uses that brain to go through the simulation
        System.out.print("Which brain would you like to use? (Enter 'Simple' or 'Medium' or 'Complex'): ");
        String answer = scan.nextLine();
        if (answer.equalsIgnoreCase("simple")) {
            System.out.println("Running the simple brain. " + p1 + " has entered the terrain!");
            SimpleBrain brain = new SimpleBrain(player, 5, 0, map, view);
            brain.moveEast();
        } else if (answer.equalsIgnoreCase("medium")) {
            System.out.println("Running the medium brain. " + p1 + " has entered the terrain!");
            MediumBrain brain = new MediumBrain(player, 5, 0, map, view);
            brain.movePlayer();
        } else {
            System.out.println("Running the complex brain. " + p1 + " has entered the terrain!");
            ComplexBrain brain = new ComplexBrain(player, 5, 0, map, view);
            brain.movePlayer();
        }
    }
}
