package DataBase.DAOs.Package;

import Domain.HalfAnimalPackage;
import Domain.OneKindAnimalPackage;

import java.util.ArrayList;

public interface OneKindAnimalPackageDao {
    String saveOneKindAnimalPackage(OneKindAnimalPackage oneKindAnimalPackage);
    ArrayList<OneKindAnimalPackage> getOneKindAnimalPackages();
    OneKindAnimalPackage getOneKindAnimalPackage(int id);
}
