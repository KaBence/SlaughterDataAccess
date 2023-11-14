package Domain;

public class AnimalPart {
    private int id;
    private String name;
    private double weight;
    private int animalId;
    private int trayId;
    private int HalfAnAnimalPackageId;
    private int OneKindPackageId;
    private boolean contaminated;



    public AnimalPart(int id, String name, double weight, int animalId, int trayId, int oneKindPackageId, int halfAnAnimalPackageId, boolean cont) {
        this.id = id;
        this.name = name;
        this.weight = weight;
        this.animalId = animalId;
        this.trayId=trayId;
        this.HalfAnAnimalPackageId= halfAnAnimalPackageId;
        this.OneKindPackageId= oneKindPackageId;
        contaminated=cont;
    }

    public AnimalPart(String name, double weight, int animalId, int trayId, int oneKindPackageId, int halfAnAnimalPackageId) {
        this.name = name;
        this.weight = weight;
        this.animalId = animalId;

        this.OneKindPackageId= oneKindPackageId;
        this.HalfAnAnimalPackageId= halfAnAnimalPackageId;
    }

    public int getId() {
        return id;
    }

    public double getWeight() {
        return weight;
    }


    public String getName() {
        return name;
    }
    public int getOneKindPackageId() {
        return OneKindPackageId;
    }

    public int getHalfAnAnimalPackageId() {
        return HalfAnAnimalPackageId;
    }


    public int getAnimalId() {
        return animalId;
    }

    public int getTrayId() {
        return trayId;
    }
}
