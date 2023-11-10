package DataBase.DAOs.Tray;

import Domain.AnimalPart;
import Domain.Tray;
import org.checkerframework.checker.units.qual.A;

import java.util.ArrayList;

public interface TrayDao {
Tray getTray(int id);
String putIntoTray(AnimalPart animalPart, int trayId);

String takeFromTray(int animalpartId, int packageId);
ArrayList<Tray> getAllTrays();



}
