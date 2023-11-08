package DataBase.DAOs.AnimalPart;

import Domain.AnimalPart;

import java.util.ArrayList;

public interface AnimalPartDao {
    String saveAnimalPart(AnimalPart animalPart);
    ArrayList<AnimalPart> getAllAnimalParts();
}
