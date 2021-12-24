public class SimpleBrain extends Brain {
    String[][] view;
    public SimpleBrain(Player player, int row, int column, Terrain[][] map, String[][] view) {
        super(player, row, column, map);
        this.view = view;
    }
    //moves the player one column east and counts how many columns they made it
    public void moveEast() {
        boolean cont;
        int turnCounter = 0;
        for (int i = column; i < map[0].length; i++) {
            cont = player.enter(map[row][i]);
            if(!cont){
                System.out.println("Player has died!");
                break;
            }
            view[row][i] = "*";
            turnCounter++;
            if(i == map[0].length - 1){
                System.out.println("Player has made it through the terrain!");
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
        System.out.println("Player made it " + turnCounter + " biome(s) deep through the terrain.");
    }
}


