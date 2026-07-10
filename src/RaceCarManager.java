/*
 * Name : Ashbel Legros
 * Course : Software Development I
 * Date : 07/03/26
 * Class : RaceCarManager
 * Purpose : Handles modifications/actions to car records within the DMS. Also performs special function to race cars.
 * */

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;

public class RaceCarManager {
    private ArrayList<RaceCar> raceCars;

    public RaceCarManager() {
        raceCars = new ArrayList<>();
    }

    public ArrayList<RaceCar> getRaceCars() {
        return raceCars;
    }

    public void setRaceCars(ArrayList<RaceCar> raceCars) {
        this.raceCars = raceCars;
    }

    /* Method: loadRaceCarManager()
       Purpose: handles txt file reading to populate DMS with data
       Return Type: RaceCarManager
     */
    public boolean loadRaceCarManager(String filePath, RaceCarManager raceCarManager) {
        try(BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            System.out.println("\nLoading Race Car System...\n");

            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split("-");

                int id = Integer.parseInt(data[0]);
                String make = data[1];
                String model = data[2];
                int year = Integer.parseInt(data[3]);
                double topSpeed = Double.parseDouble(data[4]);
                boolean hasLaunched = Boolean.parseBoolean(data[5]);

                RaceCar raceCar = new RaceCar(id, make, model, year, topSpeed, hasLaunched);
                raceCarManager.addRaceCar(raceCar);
                System.out.println(raceCar);
            }
            System.out.println();
            System.out.println("File successfully loaded.\n");
            return true;

        }
        catch (FileNotFoundException e) {
            System.out.println("Could not locate the file.\n");
            return false;
        }

        catch (IOException e) {
            System.out.println("Something went wrong!");
            return false;
        }
    }

    /* Method: addRaceCar()
       Purpose: adds a race car to the list
       Return Type: boolean
       Arguments: RaceCar
     */
    public boolean addRaceCar(RaceCar raceCar) {
        System.out.println("Adding Race Car...");

        if (!idValid(raceCar.getVehicleID())) {
            return false;
        }

        if (!stringValid(raceCar.getMake())) {
            return false;
        }

        if (!stringValid(raceCar.getModel())) {
            return false;
        }

        if (!yearValid(raceCar.getYear())) {
            return false;
        }

        if (!topSpeedValid(raceCar.getTopSpeed())) {
            return false;
        }

        raceCars.add(raceCar);

        return true;
    }

    /* Method: removeRaceCar()
       Purpose: removes a race car from the list using the id
       Return Type: boolean
       Arguments: int
     */
    public boolean removeRaceCar(int raceCarID) {
        RaceCar raceCar = idFound(raceCarID);

        if (raceCar == null) {
            return false;
        }

        raceCars.remove(raceCar);
        System.out.println("Car removed successfully!");

        /*for (int i = 0; i<raceCars.size(); i++) {
            RaceCar carToRemove = raceCars.get(i);
            if (carToRemove.getVehicleID() == raceCarID) {
                raceCars.remove(i);
                System.out.println("Car ID: " + carToRemove.getVehicleID() + " has been removed");
                break;
            }
            else {
                System.out.println("Looking for car in spot " + (i + 1));
                if (i == raceCars.size() - 1) {
                    System.out.println("Car does not exist");
                    return false;
                }
            }
        }*/
        return true;
    }

    /* Method: updateRaceCar()
       Purpose: updates an existing entry from the list using the id
       Return Type: boolean
       Arguments: int, String, String, int, double, boolean
     */
    public boolean updateRaceCar(int id, String make, String model, int year, double topSpeed, boolean hasLaunched) {
        System.out.println("Updating Race Car...");

        RaceCar carToBeUpdated = idFound(id);

        if (carToBeUpdated == null) {
            return false;
        }

        if (!stringValid(make)) {
            return false;
        }

        if (!stringValid(model)) {
            return false;
        }

        if (!yearValid(year)) {
            return false;
        }

        if (!topSpeedValid(topSpeed)) {
            return false;
        }

        carToBeUpdated.setMake(make);
        carToBeUpdated.setModel(model);
        carToBeUpdated.setYear(year);
        carToBeUpdated.setTopSpeed(topSpeed);
        carToBeUpdated.setHasLaunched(hasLaunched);

        return true;
    }

    /* Method: launchRaceCars()
       Purpose: simulates a race between two race cars by probability
       Return Type: RaceCar
       Arguments: RaceCar, RaceCar
     */
    public RaceCar launchRaceCars(RaceCar raceCar1, RaceCar raceCar2) {
        if (!raceValid(raceCar1, raceCar2)) {
            return null;
        }

        for (int i = 3; i >= 0 ; i--) {
            if (i == 0) {
                System.out.println("Gooooo!!!");
                break;
            }
            System.out.println(i);

        }

        double totalSpeedCombine = raceCar1.getTopSpeed() + raceCar2.getTopSpeed();
        double winningChance = raceCar1.getTopSpeed() / totalSpeedCombine;
        double ran = Math.random();

        RaceCar winner;

        if (ran < winningChance) {
            winner = raceCar1;
        }
        else {
            winner = raceCar2;
        }

        raceCar1.setHasLaunched(true);
        raceCar2.setHasLaunched(true);

        return winner;
    }

    /* Method: displayRaceCars()
       Purpose: prints all race cars to screen
       Return Type: boolean
     */
    public boolean displayRaceCars() {
        System.out.println("\nRace Car List\n" +
                "--------------");
        for (RaceCar raceCar : raceCars){
            System.out.println(raceCar.toString());
            System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++");
        }
        System.out.println("\n");
        return true;
    }

    //Validation Methods

    /* Method: idValid()
       Purpose: validates id for adding usage: checks if id is unique and non-negative
       Return Type: boolean
       Arguments: int
     */
    public boolean idValid(int id) {
        for (RaceCar raceCar : raceCars){
            if (raceCar.getVehicleID() == id){
                System.out.println("Race Car ID (" + id + ") already exists.\n");
                return false;
            }
        }

        if (id < 0){
            System.out.println("Please enter a positive number.\n");
            return false;
        }

        return true;
    }

    /* Method: idFound()
       Purpose: finds existing Car by id
       Return Type: RaceCar
       Arguments: int
     */
    public RaceCar idFound(int id) {
        for (int i = 0; i<raceCars.size(); i++) {
            if (raceCars.get(i).getVehicleID() == id) {
                System.out.println("Race Car ID (" + id + ") found.\n");
                System.out.println(raceCars.get(i).toString());
                return raceCars.get(i);
            } else {
                System.out.println("Race Car ID (" + id + ") not found at " + raceCars.get(i).getVehicleID() + ".\n");
                if (i == raceCars.size() - 1) {
                    System.out.println("Car does not exist\n");
                    return null;
                }
            }
        }
        return null;
    }


    /* Method: stringValid()
       Purpose: validates string input: checks if user left input empty or with spaces
       Return Type: boolean
       Arguments: String
     */
    public boolean stringValid(String inputString){

        if (inputString.isEmpty()){
            System.out.println("Don't leave field empty. You typed nothing.\n");
            return false;
        }

        if (inputString.isBlank()){
            System.out.println("Don't leave field blank. You only have spaces.\n");
            return false;
        }

        return true;
    }

    /* Method: yearValid()
       Purpose: validates year input: checks if the year is in certain range
       Return Type: boolean
       Arguments: int
     */
    public boolean yearValid(int year) {

        if (year < 2000 || year > 2026) {
            System.out.println("Please enter a year between 2000-2026.\n");
            return false;
        }

        return true;
    }

    /* Method: topSpeedValid()
       Purpose: validates topSpeedinput: checks if input uses one decimal place and checks if input is non-negative
       Return Type: boolean
       Arguments: double
     */
    public boolean topSpeedValid(double topSpeed) {
        if (BigDecimal.valueOf(topSpeed).scale() > 1 || BigDecimal.valueOf(topSpeed).scale() < 1) {
            System.out.println("Use only one decimal place.\n");
            return false;
        }

        if (topSpeed < 0) {
            System.out.println("Please enter a positive speed.\n");
            return false;
        }

        return true;
    }

    /* Method: launchValid()
       Purpose: validates launchStatus: check if user is entering either 0 or 1
       Return Type: boolean
       Arguments: int
     */
    public boolean launchValid(int launchStatus) {

        if (launchStatus < 0 || launchStatus > 1) {
            System.out.println("Please enter a valid launch status (0 or 1).\n");
            return false;
        }

        return true;
    }

    /* Method: raceValid()
       Purpose: validates race conditions: checks if either car has launched already, if vehicle id is the same, and if car exists
       Return Type: boolean
       Arguments: RaceCar, RaceCar
     */
    public boolean raceValid(RaceCar raceCar1,  RaceCar raceCar2) {
        if (raceCar1 == null || raceCar2 == null) {
            if (raceCar1 == null) {
                System.out.println("Car 1 does not exists.\n");
            }
            if (raceCar2 == null) {
                System.out.println("Car 2 does not exists.\n");
            }
            return false;
        }

        if (raceCar1.getVehicleID() == raceCar2.getVehicleID()) {
            System.out.println("Please use two separate cars");
            return false;
        }

        if(raceCar1.boolHasLaunched()) {
            System.out.println("Car 1 has been launched already.");
            return false;
        }

        if(raceCar2.boolHasLaunched()) {
            System.out.println("Car 2 has been launched already.");
            return false;
        }

        return true;
    }
}
