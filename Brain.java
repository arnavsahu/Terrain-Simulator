public class Brain {
    protected Player player;
    protected int column;
    protected int row;
    protected Terrain[][] map;

    public Brain(){
    }

    public Brain(Player player, int row, int column, Terrain[][] map){
        this.player = player;
        this.row = row;
        this.column = column;
        this.map = map;
    }
}
