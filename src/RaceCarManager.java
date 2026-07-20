/*
 * Name : Ashbel Legros
 * Course : Software Development I
 * Date : 07/03/26
 * Class : RaceCarManager
 * Purpose : Contains validation for business logic.
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

    //Validation Methods



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
