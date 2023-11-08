package GrpcServer;

import Domain.Animal;
import Domain.AnimalPart;
import slaughter.DTOAnimal;
import slaughter.DTOAnimalPart;

import java.util.ArrayList;

public class DtoFactory
{

    public static DTOAnimal toDtoAnimal(Animal x)
    {
        return DTOAnimal.newBuilder()
                .setId(x.getId())
                .setWeight(x.getWeight())
                .build();
    }

    public static ArrayList<DTOAnimal> toDtoAnimals(ArrayList<Animal> animals)
    {
        ArrayList<DTOAnimal> x = new ArrayList<>();
        for (int i = 0; i < animals.size(); i++) {
            x.add(toDtoAnimal(animals.get(i)));
        }
        return x;
    }

    public static Animal toAnimal(DTOAnimal x)
    {
        return new Animal(x.getId(),x.getWeight());
    }

    public static DTOAnimalPart toDtoAnimalPart(AnimalPart x)
    {
        return DTOAnimalPart.newBuilder()
                .setAnimalId(x.getAnimalId())
                .setName(x.getName())
                .build();
    }


    public static ArrayList<DTOAnimalPart> toDtoAnimalParts(ArrayList<AnimalPart> parts)
    {
        ArrayList<DTOAnimalPart> x = new ArrayList<>();
        for (int i = 0; i < parts.size(); i++) {
            x.add(toDtoAnimalPart(parts.get(i)));
        }
        return x;
    }

    public static AnimalPart toAnimalPart(DTOAnimalPart x)
    {
        return new AnimalPart(x.getName(), x.getAnimalId());
    }
}
