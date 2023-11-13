package DataBase.DAOs.Package;
import Domain.HalfAnimalPackage;

import java.util.ArrayList;

public interface HalfAnimalPackageDAo {
    String saveHalfAnimalPackage(HalfAnimalPackage halfAnimalPackage);
    ArrayList<HalfAnimalPackage> getHalfAnimalPackages();
    HalfAnimalPackage getHalfAnimalPackage(int id);

}
