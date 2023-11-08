package Domain;

public class AnimalPart {
    private String name;
    private int animalId;

    public AnimalPart(String name, int animalId) {
        this.name = name;
        this.animalId = animalId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAnimalId() {
        return animalId;
    }

    public void setAnimalId(int animalId) {
        this.animalId = animalId;
    }
}
