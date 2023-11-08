package Domain;

import java.util.Date;

public class Animal {
    private int id;
    private double weight;
    private Date dod;
    private int farm;

    public Animal(int id, double weight, Date dod, int farm) {
        this.id = id;
        this.weight = weight;
        this.dod = dod;
        this.farm = farm;
    }

    public Date getDod() {
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
