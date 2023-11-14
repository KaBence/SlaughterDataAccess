package GrpcServer;

import DataBase.DAOs.Package.HalfAnimalPackageDAo;
import DataBase.DAOs.Package.OneKindAnimalPackageDao;
import DataBase.Implementations.Animal.AnimalDaoImplementation;
import DataBase.Implementations.AnimalPart.AnimalPartDaoImplementation;
import DataBase.Implementations.Package.HalfAnimalPackageDaoImplementation;
import DataBase.Implementations.Package.OneKindAnimalPackageDaoImplementation;
import Domain.Animal;
import Domain.AnimalPart;
import Domain.HalfAnimalPackage;
import Domain.OneKindAnimalPackage;
import io.grpc.stub.StreamObserver;
import slaughter.*;

import java.util.ArrayList;

import static GrpcServer.DtoFactory.*;

public class SlaughterGrpcServiceImplementation extends SlaughterServiceGrpc.SlaughterServiceImplBase {
    private ArrayList<Animal> allAnimals;
    private ArrayList<AnimalPart> allAnimalParts;

    private AnimalDaoImplementation animalDao;
    private AnimalPartDaoImplementation animalPartDao;

    private HalfAnimalPackageDAo halfAnimalPackageDAo;
    private OneKindAnimalPackageDao oneKindAnimalPackageDao;


    public SlaughterGrpcServiceImplementation() {
        this.allAnimals = new ArrayList<>();
        this.allAnimalParts = new ArrayList<>();
        this.animalDao = new AnimalDaoImplementation();
        this.animalPartDao = new AnimalPartDaoImplementation();
        this.halfAnimalPackageDAo = new HalfAnimalPackageDaoImplementation();
        this.oneKindAnimalPackageDao = new OneKindAnimalPackageDaoImplementation();
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
                .setOminous(toDtoAnimal(x))
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

    @Override
    public void getHalfAnimalPackage(GetHalfAnimalPackageReq request, StreamObserver<GetHalfAnimalPackageRes> responseObserver) {
        HalfAnimalPackage x = halfAnimalPackageDAo.getHalfAnimalPackage(request.getId());
        GetHalfAnimalPackageRes res = GetHalfAnimalPackageRes.newBuilder()
                .setOminous(toDtoHalfAnimalPackage(x))
                .build();
        responseObserver.onNext(res);
        responseObserver.onCompleted();
    }

    @Override
    public void getHalfAnimalPackages(GetHalfAnimalPackagesReq request, StreamObserver<GetHalfAnimalPackagesRes> responseObserver) {
       ArrayList<HalfAnimalPackage> x = halfAnimalPackageDAo.getHalfAnimalPackages();
       GetHalfAnimalPackagesRes res = GetHalfAnimalPackagesRes.newBuilder()
               .addAllOminous(toDtoHalfAnimalPackages(x))
               .build();
        responseObserver.onNext(res);
        responseObserver.onCompleted();
    }

    @Override
    public void putHalfAnimalPackage(PutHalfAnimalPackageReq request, StreamObserver<PutHalfAnimalPackageRes> responseObserver) {
        HalfAnimalPackage x = toHalfAnimalPackage(request.getOminous());

        String database = halfAnimalPackageDAo.saveHalfAnimalPackage(x);
        System.out.println(x.getHalf_package_id());
        PutHalfAnimalPackageRes res = PutHalfAnimalPackageRes.newBuilder()
                .setResp(database)
                .build();

        responseObserver.onNext(res);
        responseObserver.onCompleted();
    }

    @Override
    public void recall(RecallReq req,StreamObserver<RecallRes> responseObserver){
        String x=animalPartDao.recallAnimal(req.getId());
        RecallRes res=RecallRes.newBuilder().setRes(x).build();


        responseObserver.onNext(res);
        responseObserver.onCompleted();
    }

    @Override
    public void getContaminated(GetContaminatedReq req,StreamObserver<GetContaminatedRes> responseObserver){
        ArrayList<Animal> animals=animalDao.getContaminatedAnimals();

        GetContaminatedRes res=GetContaminatedRes.newBuilder().addAllOminous(toDtoAnimals(animals)).build();

        responseObserver.onNext(res);
        responseObserver.onCompleted();
    }

    @Override
    public void getOneKindAnimalPackage(GetOneKindAnimalPackageReq request, StreamObserver<GetOneKindAnimalPackageRes> responseObserver) {
        OneKindAnimalPackage x = oneKindAnimalPackageDao.getOneKindAnimalPackage(request.getId());
        GetOneKindAnimalPackageRes res = GetOneKindAnimalPackageRes.newBuilder()
                .setOminous(toDtoOneKindAnimalPackage(x))
                .build();
        responseObserver.onNext(res);
        responseObserver.onCompleted();
    }

    @Override
    public void getOneKindAnimalPackages(GetOneKindAnimalPackagesReq request, StreamObserver<GetOneKindAnimalPackagesRes> responseObserver) {
        ArrayList<OneKindAnimalPackage> x = oneKindAnimalPackageDao.getOneKindAnimalPackages();
        GetOneKindAnimalPackagesRes res = GetOneKindAnimalPackagesRes.newBuilder()
                .addAllOminous(toDtoOneKindAnimalPackages(x))
                .build();
        responseObserver.onNext(res);
        responseObserver.onCompleted();
    }

    @Override
    public void putOneKindAnimalPackage(PutOneKindAnimalPackageReq request, StreamObserver<PutOneKindAnimalPackageRes> responseObserver) {
        OneKindAnimalPackage x = toOneKindAnimalPackage(request.getOminous());

        String database = oneKindAnimalPackageDao.saveOneKindAnimalPackage(x);
        System.out.println(x.getOne_package_id());
        PutOneKindAnimalPackageRes res = PutOneKindAnimalPackageRes.newBuilder()
                .setResp(database)
                .build();

        responseObserver.onNext(res);
        responseObserver.onCompleted();
    }

}
