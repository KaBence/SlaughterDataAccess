package GrpcServer;

import Domain.Animal;
import Domain.AnimalPart;
import Domain.HalfAnimalPackage;
import slaughter.DTOAnimal;
import slaughter.DTOAnimalPart;
import slaughter.DTOHalfAnimalPackage;

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

    public static HalfAnimalPackage toHalfAnimalPackage(DTOHalfAnimalPackage x){
        return new HalfAnimalPackage(x.getId());
    }

    public static DTOHalfAnimalPackage toDtoHalfAnimalPackage(HalfAnimalPackage x)
    {
        return DTOHalfAnimalPackage.newBuilder()
                .setId(x.getHalf_package_id())
                .build();
    }

    public  static ArrayList<DTOHalfAnimalPackage> toDtoHalfAnimalPackages(ArrayList<HalfAnimalPackage> packages)
    {
        ArrayList<DTOHalfAnimalPackage> x = new ArrayList<>();
        for (int i = 0; i < packages.size(); i++) {
            x.add(toDtoHalfAnimalPackage(packages.get(i)));
        }
        return x;
    }
}
