package Domain;

public class Tray {
    private int id;
    private double maxWeight;
    private int OneKindPackageId;
    private int HalfAnAnimalPackageId;

    public Tray(int id, double maxWeight, int oneKindPackageId, int halfAnAnimalPackageId){
        this.id=id;
        this.maxWeight= maxWeight;
        this.HalfAnAnimalPackageId= halfAnAnimalPackageId;
        this.OneKindPackageId= oneKindPackageId;
    }
    public Tray(double maxWeight, int halfAnAnimalPackageId, int oneKindPackageId){
        this.maxWeight=maxWeight;
        this.OneKindPackageId= oneKindPackageId;
        this.HalfAnAnimalPackageId= halfAnAnimalPackageId;
    }

    public double getMaxWeight() {
        return maxWeight;
    }

    public int getHalfAnAnimalPackageId() {
        return HalfAnAnimalPackageId;
    }

    public int getId() {
        return id;
    }

    public int getOneKindPackageId() {
        return OneKindPackageId;
    }
}
