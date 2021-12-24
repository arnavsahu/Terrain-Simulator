public class Bob extends Player{
    public Bob(){
        super(20.0, 20.0, 20.0);
    }

    public double foodFactor(){
        return 1.2;
    }

    public double waterFactor(){
        return 0.2;
    }

    public double staminaFactor(){
        return 1.2;
    }
}
