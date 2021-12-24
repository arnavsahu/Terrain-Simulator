public class Lake extends Terrain{
    //static methods override terrain static methods through cast methods I created in the medium brain, complex brain, and player classes
    public static double foodCost(){
        return 1.0;
    }
    public static double waterCost(){
        return -1.0;
    }
    public static double staminaCost(){
        return 1.5;
    }
}
