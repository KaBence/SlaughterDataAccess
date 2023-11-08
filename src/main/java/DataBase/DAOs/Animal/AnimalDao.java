package DataBase.DAOs.Animal;

import Domain.Animal;
import slaughter.DTOAnimal;

import java.util.ArrayList;

public interface AnimalDao
{
    String saveAnimal(Animal animal);
    ArrayList<Animal> getAllAnimals();
}
