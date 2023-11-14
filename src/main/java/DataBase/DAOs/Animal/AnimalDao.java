package DataBase.DAOs.Animal;

import Domain.Animal;

import java.util.ArrayList;

public interface AnimalDao
{
    String saveAnimal(Animal animal);
    Animal getAnimal(int id);
    ArrayList<Animal> getAllAnimals();

    ArrayList<Animal> getContaminatedAnimals();
}
