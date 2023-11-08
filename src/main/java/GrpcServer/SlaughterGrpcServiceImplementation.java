package GrpcServer;

import DataBase.Implementations.Animal.AnimalDaoImplementation;
import Domain.Animal;
import Domain.AnimalPart;
import io.grpc.stub.StreamObserver;
import slaughter.*;

import java.util.ArrayList;

import static GrpcServer.DtoFactory.toAnimal;
import static GrpcServer.DtoFactory.toDtoAnimals;

public class SlaughterGrpcServiceImplementation extends SlaughterServiceGrpc.SlaughterServiceImplBase
{
    private ArrayList<Animal> allAnimals;
    private ArrayList<AnimalPart> allAnimalParts;

    private AnimalDaoImplementation animalDao;
    //private AnimalDaoImplementation animalPartDao;


    public SlaughterGrpcServiceImplementation() {
        this.allAnimals = new ArrayList<>();
        this.allAnimalParts = new ArrayList<>();
        this.animalDao = new AnimalDaoImplementation();
    }

    @Override
    public void getAnimals(GetAnimalsReq request, StreamObserver<GetAnimalsRes> responseObserver) {
        allAnimals = animalDao.getAllAnimals();
        GetAnimalsRes res = GetAnimalsRes.newBuilder()
                .addAllOminous(toDtoAnimals(allAnimals))
                .build();

        responseObserver.onNext(res);
        responseObserver.onCompleted();
    }

    @Override
    public void putAnimal(PutAnimalReq request, StreamObserver<PutAnimalRes> responseObserver) {
        Animal temp=toAnimal(request.getOminous());

        animalDao.saveAnimal(temp);
        System.out.println(temp.getId());
        PutAnimalRes res = PutAnimalRes.newBuilder()
                .setResp("Good Job")
                .build();

        responseObserver.onNext(res);
        responseObserver.onCompleted();
    }
}
