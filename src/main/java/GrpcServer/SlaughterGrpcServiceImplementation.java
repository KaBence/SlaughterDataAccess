package GrpcServer;

import DataBase.Implementations.Animal.AnimalDaoImplementation;
import DataBase.Implementations.AnimalPart.AnimalPartDaoImplementation;
import DataBase.Implementations.Tray.TrayImplementation;
import Domain.Animal;
import Domain.AnimalPart;
import Domain.Tray;
import io.grpc.stub.StreamObserver;
import slaughter.*;

import java.util.ArrayList;

import static GrpcServer.DtoFactory.*;

public class SlaughterGrpcServiceImplementation extends SlaughterServiceGrpc.SlaughterServiceImplBase {
    private ArrayList<Animal> allAnimals;
    private ArrayList<AnimalPart> allAnimalParts;
    private ArrayList<Tray> allTrays;

    private AnimalDaoImplementation animalDao;
    private AnimalPartDaoImplementation animalPartDao;
    private TrayImplementation trayDao;



    public SlaughterGrpcServiceImplementation() {
        this.allAnimals = new ArrayList<>();
        this.allAnimalParts = new ArrayList<>();
        this.animalDao = new AnimalDaoImplementation();
        this.animalPartDao = new AnimalPartDaoImplementation();
        this.allTrays= new ArrayList<>();
        this.trayDao= new TrayImplementation();
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
    public void getAnimal(GetAnimalReq request, StreamObserver<GetAnimalRes> responseObserver) {
        Animal x = animalDao.getAnimal(request.getId());
        GetAnimalRes res = GetAnimalRes.newBuilder()
                .addOminous(toDtoAnimal(x))
                .build();

        responseObserver.onNext(res);
        responseObserver.onCompleted();
    }


    @Override
    public void putAnimal(PutAnimalReq request, StreamObserver<PutAnimalRes> responseObserver) {
        Animal temp = toAnimal(request.getOminous());

        String database = animalDao.saveAnimal(temp);
        System.out.println(temp.getId());
        PutAnimalRes res = PutAnimalRes.newBuilder()
                .setResp(database)
                .build();

        responseObserver.onNext(res);
        responseObserver.onCompleted();
    }

    @Override
    public void getAnimalParts(GetAnimalPartsReq request, StreamObserver<GetAnimalPartsRes> responseObserver) {
        allAnimalParts = animalPartDao.getAllAnimalParts();
        GetAnimalPartsRes res = GetAnimalPartsRes.newBuilder()
                .addAllOminous(toDtoAnimalParts(allAnimalParts))
                .build();

        responseObserver.onNext(res);
        responseObserver.onCompleted();
    }

    @Override
    public void putAnimalPart(PutAnimalPartReq request, StreamObserver<PutAnimalPartRes> responseObserver) {
        AnimalPart temp = toAnimalPart(request.getOminous());

        String database = animalPartDao.saveAnimalPart(temp);
        System.out.println(temp.getName());
        PutAnimalPartRes res = PutAnimalPartRes.newBuilder()
                .setResp(database)
                .build();

        responseObserver.onNext(res);
        responseObserver.onCompleted();
    }
    //I need to add Tray methods in here I guess

    public void getTray(GetTrayReq request, StreamObserver<GetTrayRes> responseObserver) {
        Tray x = trayDao.getTray(request.getId());
        GetTrayRes res = GetTrayRes.newBuilder()
                .addOminous(toDtoTray(x))
                .build();

        responseObserver.onNext(res);
        responseObserver.onCompleted();}
    //HERE I NEED TO FINISH CHANGING THE METHOD
    public void putTray(PutTrayReq request, StreamObserver<PutTrayRes> responseObserver) {
        Tray temp = toTray(request.getOminous());

        String database = animalDao.saveAnimal(temp);
        System.out.println(temp.getId());
        PutAnimalRes res = PutAnimalRes.newBuilder()
                .setResp(database)
                .build();

        responseObserver.onNext(res);
        responseObserver.onCompleted();
    }
}

