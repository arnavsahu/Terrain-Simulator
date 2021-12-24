public class MediumBrain extends Brain {
    //fields
    private double foodSupply;
    private double waterSupply;
    private double staminaSupply;

    private int direction1zero;
    private int direction2zero;

    String[][] view;
    //constructor
    public MediumBrain(Player player, int row, int column, Terrain[][] map, String[][] view) {
        super(player, row, column, map);
        foodSupply = player.getFoodSupply();
        waterSupply = player.getWaterSupply();
        staminaSupply = player.getStaminaSupply();
        direction1zero = 0;
        direction2zero = 0;
        this.view = view;
    }
    //casts the chosen square in the map to it's true subclass and returns either the food, water, or stamina the player has left if they were to enter the square
    public double cast(int row, int col, String val){
        River river = new River();
        Lake lake = new Lake();
        Desert desert = new Desert();
        tundra tundra = new tundra();
        Mountain mountain = new Mountain();
        Grass grass = new Grass();

        double foodRemaining;
        double waterRemaining;
        double staminaRemaining;
        if (map[row][col].getClass() == river.getClass()) {
            foodRemaining = foodSupply -  ((River)map[row][col]).foodCost();
            waterRemaining = waterSupply -  ((River)map[row][col]).waterCost();
            staminaRemaining = staminaSupply -  ((River)map[row][col]).staminaCost();
        } else if (map[row][col].getClass() == lake.getClass()) {
            foodRemaining = foodSupply -  ((Lake)map[row][col]).foodCost();
            waterRemaining = waterSupply -  ((Lake)map[row][col]).waterCost();
            staminaRemaining = staminaSupply -  ((Lake)map[row][col]).staminaCost();
        } else if (map[row][col].getClass() == desert.getClass()) {
            foodRemaining = foodSupply -  ((Desert)map[row][col]).foodCost();
            waterRemaining = waterSupply -  ((Desert)map[row][col]).waterCost();
            staminaRemaining = staminaSupply -  ((Desert)map[row][col]).staminaCost();
        } else if (map[row][col].getClass() == tundra.getClass()) {
            foodRemaining = foodSupply -  ((tundra)map[row][col]).foodCost();
            waterRemaining = waterSupply -  ((tundra)map[row][col]).waterCost();
            staminaRemaining = staminaSupply -  ((tundra)map[row][col]).staminaCost();
        } else if (map[row][col].getClass() == mountain.getClass()) {
            foodRemaining = foodSupply -  ((Mountain)map[row][col]).foodCost();
            waterRemaining = waterSupply -  ((Mountain)map[row][col]).waterCost();
            staminaRemaining = staminaSupply -  ((Mountain)map[row][col]).staminaCost();
        } else if (map[row][col].getClass() == grass.getClass()) {
            foodRemaining = foodSupply -  ((Grass)map[row][col]).foodCost();
            waterRemaining = waterSupply -  ((Grass)map[row][col]).waterCost();
            staminaRemaining = staminaSupply -  ((Grass)map[row][col]).staminaCost();
        } else {
            foodRemaining = foodSupply -  ((Jungle)map[row][col]).foodCost();
            waterRemaining = waterSupply -  ((Jungle)map[row][col]).waterCost();
            staminaRemaining = staminaSupply -  ((Jungle)map[row][col]).staminaCost();
        }
        if(val.equalsIgnoreCase("food")){
            return foodRemaining;
        } else if(val.equalsIgnoreCase("water")){
            return waterRemaining;
        } else {
            return staminaRemaining;
        }
    }
    //compares any two given squares by using the row and col numbers of these two squares. returns true if the first square is a better option and returns false if the second square is a better option.
    public boolean compare(int row1, int col1, int row2, int col2){
        double foodRemaining1 = cast(row1, col1, "food");
        double waterRemaining1 = cast(row1, col1, "water");
        double staminaRemaining1 = cast(row1, col1, "stamina");
        double totalRemaining1 = foodRemaining1 + waterRemaining1 + staminaRemaining1;

        double foodRemaining2 = cast(row2, col2, "food");
        double waterRemaining2 = cast(row2, col2, "water");
        double staminaRemaining2 = cast(row2, col2, "stamina");
        double totalRemaining2 = foodRemaining2 + waterRemaining2 + staminaRemaining2;

        if (foodRemaining1 <= 0) {
            direction1zero++;
        }
        if (waterRemaining1 <= 0) {
            direction1zero++;
        }
        if (staminaRemaining1 <= 0) {
            direction1zero++;
        }
        if (foodRemaining2 <= 0) {
            direction2zero++;
        }
        if (waterRemaining2 <= 0) {
            direction2zero++;
        }
        if (staminaRemaining2 <= 0) {
            direction2zero++;
        }
        if (direction1zero > direction2zero) {
            return false;
        } else if (direction2zero > direction1zero) {
            return true;
        } else {
            return totalRemaining1 > totalRemaining2;
        }
    }
    //moves the player through the terrain until they die or until they have reached the end of the map using the compare functions and some comparison logic
    public void movePlayer() {
        view[row][column] = "*";
        boolean cont;
        int turnCounter = 0;
        int eastCounter = 0;
        while (column < map[0].length - 1) {
            //updates stat supplies of player
            foodSupply = player.getFoodSupply();
            waterSupply = player.getWaterSupply();
            staminaSupply = player.getStaminaSupply();

            //first move condition if player is currently at row 0 (move south or move east)
            if(row == 0) {
                //true if player should move south false if player should move east
                boolean southvseast = compare(row + 1, column, row, column + 1);
                direction1zero = 0;
                direction2zero = 0;
                if(southvseast){
                    cont = player.enter(map[row + 1][column]);
                    if(!cont){
                        System.out.println("Player has died!");
                        break;
                    }
                    row++;
                    view[row][column] = "*";
                } else {
                    cont = player.enter(map[row][column + 1]);
                    if(!cont){
                        System.out.println("Player has died!");
                        break;
                    }
                    column++;
                    view[row][column] = "*";
                    eastCounter++;
                }
                turnCounter++;
                continue;
            }
            //second move condition if player is currently at row 9 (move north or move east)
            if(row == 9){
                //true if player should move north false if player should move east
                boolean northvseast = compare(row - 1, column, row, column + 1);
                direction1zero = 0;
                direction2zero = 0;
                if(northvseast){
                    cont = player.enter(map[row - 1][column]);
                    if(!cont){
                        System.out.println("Player has died!");
                        break;
                    }
                    row--;
                    view[row][column] = "*";
                } else {
                    cont = player.enter(map[row][column + 1]);
                    if(!cont){
                        System.out.println("Player has died!");
                        break;
                    }
                    column++;
                    view[row][column] = "*";
                    eastCounter++;
                }
                turnCounter++;
                continue;
            }
            //third move condition if player is not in row 9 or row 0
            boolean northvseast = compare(row - 1, column, row, column + 1);
            boolean southvseast = compare(row + 1, column, row, column + 1);
            boolean northvssouth = compare(row - 1, column, row + 1, column);
            if(northvseast && northvssouth){
                cont = player.enter(map[row - 1][column]);
                if(!cont){
                    System.out.println("Player has died!");
                    break;
                }
                row--;
                view[row][column] = "*";
                turnCounter++;
            } else if(!northvssouth && southvseast){
                cont = player.enter(map[row + 1][column]);
                if(!cont){
                    System.out.println("Player has died!");
                    break;
                }
                row++;
                view[row][column] = "*";
                turnCounter++;
            } else {
                cont = player.enter(map[row][column + 1]);
                if(!cont){
                    System.out.println("Player has died!");
                    break;
                }
                column++;
                view[row][column] = "*";
                eastCounter++;
                turnCounter++;
            }
        }
        System.out.println("Path Taken: ");
        for (String[] strings : view) {
            for (int j = 0; j < view[0].length; j++) {
                System.out.print(strings[j]);
            }
            System.out.println();
        }
        System.out.println();
        System.out.println("Player made it " + eastCounter + " column(s) deep into the terrain and encountered " + turnCounter + " square(s).");
    }
}


