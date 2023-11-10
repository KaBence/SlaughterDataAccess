package Domain;

public class AnimalPart {
    private int id;
    private String name;
    private double weight;
    private int animalId;
    private int trayId;

    public AnimalPart(int id, String name, double weight, int animalId, int trayId) {
        this.id = id;
        this.name = name;
        this.weight = weight;
        this.animalId = animalId;
        this.trayId=trayId;
    }

    public AnimalPart(String name, double weight, int animalId, int trayId) {
        this.name = name;
        this.weight = weight;
        this.animalId = animalId;
        this.trayId=trayId;
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

    public int getAnimalId() {
        return animalId;
    }

    public int getTrayId() {
        return trayId;
    }
}
