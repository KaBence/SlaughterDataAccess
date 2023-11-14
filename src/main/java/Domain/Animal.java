package Domain;


public class Animal {
    private int id;
    private double weight;
    private String dod;
    private int farm;
    private boolean contaminated;


    public Animal(int id, double weight, String dod, int farm,boolean cont) {
        this.id = id;
        this.weight = weight;
        this.dod = dod;
        this.farm = farm;
        contaminated=cont;
    }

    public Animal(double weight, String dod, int farm) {
        this.weight = weight;
        this.dod = dod;
        this.farm = farm;
    }

    public String getDod() {
        return dod;
    }

    public int getFarm() {
        return farm;
    }


    public int getId() {
        return id;
    }

    public double getWeight() {
        return weight;
    }

    public boolean isContaminated() {
        return contaminated;
    }
}
