public class Mountain extends Terrain{
    //static methods override terrain static methods through cast methods I created in the medium brain, complex brain, and player classes
    public static double foodCost(){
        return 1.5;
    }
    public static double waterCost(){
        return 1.5;
    }
    public static double staminaCost(){
        return 2.0;
    }
}
