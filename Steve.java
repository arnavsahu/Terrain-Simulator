public class Steve extends Player{
    public Steve(){
        super(15.0, 15.0, 18.0);
    }

    public double foodFactor(){
        return 0.5;
    }

    public double waterFactor(){
        return 0.5;
    }

    public double staminaFactor(){
        return 0.8;
    }
}
