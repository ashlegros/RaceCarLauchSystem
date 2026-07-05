/*
 * Name : Ashbel Legros
 * Course : Software Development I
 * Date : 07/03/26
 * Class : RaceCarManager
 * Purpose : Handles modifications/actions to car records within the DMS. Also performs special function to race cars.
 * */

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

    /* Method: addRaceCar()
       Purpose: adds a race car to the list
       Return Type: boolean
       Arguments: RaceCar
     */
    public boolean addRaceCar(RaceCar raceCar) {
        System.out.println("Adding Race Car...");
        return raceCars.add(raceCar);
    }

    /* Method: removeRaceCar()
       Purpose: removes a race car from the list using the id
       Return Type: boolean
       Arguments: int
     */
    public boolean removeRaceCar(int raceCarID) {
        for (int i = 0; i<raceCars.size(); i++) {
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
        }
        return true;
    }

    /* Method: updateRaceCar()
       Purpose: updates an existing entry from the list using the id
       Return Type: boolean
       Arguments: int
     */
    public boolean updateRaceCar(int raceCarID) {
        System.out.println("Initiating Update...");
        RaceCar carToUpdate = new RaceCar();
        carToUpdate.setVehicleID(raceCarID);

        return true;
    }

    /* Method: launchRaceCars()
       Purpose: simulates a race between two race cars by probability
       Return Type: boolean
       Arguments: RaceCar, RaceCar
     */
    public boolean launchRaceCars(RaceCar raceCar1, RaceCar raceCar2) {


        System.out.println("Race Car 1: " + raceCar1);
        System.out.println("Race Car 2: " + raceCar2);
        System.out.println();
        System.out.println("Launching Race Cars...");

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

        if (ran < winningChance) {
            System.out.println("Race Car 1 has won!\n");
        }
        else {
            System.out.println("Race Car 2 has won!\n");
        }

        raceCar1.setHasLaunched(true);
        raceCar2.setHasLaunched(true);

        return true;
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
       Purpose: updates an existing entry from the list using the id
       Return Type: boolean
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
