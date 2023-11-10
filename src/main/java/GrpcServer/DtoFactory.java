package GrpcServer;

import Domain.Animal;
import Domain.AnimalPart;
import slaughter.DTOAnimal;
import slaughter.DTOAnimalPart;

import java.util.ArrayList;

public class DtoFactory {


    public static DTOAnimal toDtoAnimal(Animal x) {
        return DTOAnimal.newBuilder()
                .setId(x.getId())
                .setWeight(x.getWeight())
                .setDod(x.getDod())
                .setFarm(x.getFarm())
                .build();
    }

    public static ArrayList<DTOAnimal> toDtoAnimals(ArrayList<Animal> animals) {
        ArrayList<DTOAnimal> x = new ArrayList<>();
        for (int i = 0; i < animals.size(); i++) {
            x.add(toDtoAnimal(animals.get(i)));
        }
        return x;
    }

    public static Animal toAnimal(DTOAnimal x) {
        return new Animal (x.getWeight(), x.getDod(), x.getFarm());
    }

    public static DTOAnimalPart toDtoAnimalPart(AnimalPart x) {
        return DTOAnimalPart.newBuilder()
                .setId(x.getId())
                .setName(x.getName())
                .setWeight(x.getWeight())
                .setAnimalId(x.getAnimalId())
                .build();
    }


    public static ArrayList<DTOAnimalPart> toDtoAnimalParts(ArrayList<AnimalPart> parts) {
        ArrayList<DTOAnimalPart> x = new ArrayList<>();
        for (int i = 0; i < parts.size(); i++) {
            x.add(toDtoAnimalPart(parts.get(i)));
        }
        return x;
    }

    public static AnimalPart toAnimalPart(DTOAnimalPart x) {
        return new AnimalPart(x.getName(), x.getWeight(), x.getAnimalId());
    }
}
