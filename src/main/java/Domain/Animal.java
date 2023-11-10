package Domain;

import java.util.Date;

public class Animal {
    private int id;
    private double weight;
    private String dod;
    private int farm;

    public Animal(int id, double weight, String dod, int farm) {
        this.id = id;
        this.weight = weight;
        this.dod = dod;
        this.farm = farm;
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
}
