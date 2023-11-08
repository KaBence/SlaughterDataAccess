package GrpcServer;

import Domain.Animal;
import com.google.protobuf.InvalidProtocolBufferException;
import slaughter.DTOAnimal;

import java.util.ArrayList;

public class DtoFactory
{

    private static DTOAnimal toDtoAnimal(Animal x)
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
}
