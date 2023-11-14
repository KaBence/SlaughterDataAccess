package Domain;

public class Tray {
    private int id;
    private double maxWeight;


    public Tray(int id, double maxWeight){
        this.id=id;
        this.maxWeight= maxWeight;

    }
    public Tray(double maxWeight){
        this.maxWeight=maxWeight;

    }

    public double getMaxWeight() {
        return maxWeight;
    }



    public int getId() {
        return id;
    }


}
