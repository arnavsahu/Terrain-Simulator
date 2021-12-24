public class Player {
    private double foodSupply;
    private double waterSupply;
    private double staminaSupply;

    public Player(double food, double water, double stamina) {
        foodSupply = food;
        waterSupply = water;
        staminaSupply = stamina;
    }

    public double getFoodSupply() {
        return foodSupply;
    }

    public double getStaminaSupply() {
        return staminaSupply;
    }

    public double getWaterSupply() {
        return waterSupply;
    }

    public void setFoodSupply(double foodSupply) {
        this.foodSupply = foodSupply;
    }

    public void setStaminaSupply(double staminaSupply) {
        this.staminaSupply = staminaSupply;
    }

    public void setWaterSupply(double waterSupply) {
        this.waterSupply = waterSupply;
    }

    public double foodFactor() {
        return 1.0;
    }

    public double waterFactor() {
        return 1.0;
    }

    public double staminaFactor() {
        return 1.0;
    }

    //cast method to override the static methods in the terrain class. requires the square and the certain stat (food, water, stamina) to use in the enter method
    public double cast(Terrain square, String val) {
        River river = new River();
        Lake lake = new Lake();
        Desert desert = new Desert();
        tundra tundra = new tundra();
        Mountain mountain = new Mountain();
        Grass grass = new Grass();

        double foodCost;
        double waterCost;
        double staminaCost;

        if (square.getClass() == river.getClass()) {
            foodCost = ((River) square).foodCost();
            waterCost = ((River) square).waterCost();
            staminaCost = ((River) square).staminaCost();
        } else if (square.getClass() == lake.getClass()) {
            foodCost = ((Lake) square).foodCost();
            waterCost = ((Lake) square).waterCost();
            staminaCost = ((Lake) square).staminaCost();
        } else if (square.getClass() == desert.getClass()) {
            foodCost = ((Desert) square).foodCost();
            waterCost = ((Desert) square).waterCost();
            staminaCost = ((Desert) square).staminaCost();
        } else if (square.getClass() == tundra.getClass()) {
            foodCost = ((tundra) square).foodCost();
            waterCost = ((tundra) square).waterCost();
            staminaCost = ((tundra) square).staminaCost();
        } else if (square.getClass() == mountain.getClass()) {
            foodCost = ((Mountain) square).foodCost();
            waterCost = ((Mountain) square).waterCost();
            staminaCost = ((Mountain) square).staminaCost();
        } else if (square.getClass() == grass.getClass()) {
            foodCost = ((Grass) square).foodCost();
            waterCost = ((Grass) square).waterCost();
            staminaCost = ((Grass) square).staminaCost();
        } else {
            foodCost = ((Jungle) square).foodCost();
            waterCost = ((Jungle) square).waterCost();
            staminaCost = ((Jungle) square).staminaCost();
        }

        if(val.equalsIgnoreCase("food")){
            return foodCost;
        } else if(val.equalsIgnoreCase("water")){
            return waterCost;
        } else {
            return staminaCost;
        }
    }

    public boolean enter(Terrain square){
        //used cast here and for the other stat doubles created
        double food = cast(square, "food");
        if (food > 0.0)
        {
            food *= foodFactor();
        }
        foodSupply -= food;
        if (foodSupply > 20.0)
        {
            foodSupply = 20.0;
        }
        if (foodSupply < 0.0)
        {
            return false; // The player has 'died'
        }
        //water
        double water = cast(square, "water");
        if (water > 0.0)
        {
            water *= waterFactor();
        }
        waterSupply -= water;
        if (waterSupply > 20.0)
        {
            waterSupply = 20.0;
        }
        if (waterSupply < 0.0)
        {
            return false; // The player has 'died'
        }
        //stamina
        double stamina = cast(square, "stamina");
        if (stamina > 0.0)
        {
            stamina *= staminaFactor();
        }
        staminaSupply -= stamina;
        if (staminaSupply > 20.0)
        {
            staminaSupply = 20.0;
        }
        if (staminaSupply < 0.0)
        {
            return false; // The player has 'died'
        }
        return true;
    }
}
