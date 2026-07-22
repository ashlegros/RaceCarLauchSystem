import java.math.BigDecimal;
import java.util.ArrayList;

/**
 * This parent class provides validation logic for RaceCarDBManager class. Its primary role is to ensure proper
 * creation of data as well as modification to data.
 *
 * @author Ashbel Legros
 **/
public class RaceCarManager {
    private ArrayList<RaceCar> raceCars;

    /**
     * Initializes a new empty array list which holds race car objects.
     */
    public RaceCarManager() {
        raceCars = new ArrayList<>();
    }

    /**
     * This method gets the array list of cars.
     * @return the array list of car objects
     */
    public ArrayList<RaceCar> getRaceCars() {
        return raceCars;
    }

    /**
     * This method sets the value of raceCars to the parameter.
     * @param raceCars an array of race car objects
     */
    public void setRaceCars(ArrayList<RaceCar> raceCars) {
        this.raceCars = raceCars;
    }

    //Validation Methods

    /**
     * This method was used to check if an id was found in an array list of race car objects.
     * @param id the id to be found
     * @return RaceCar if the id is found, the raceCar object is returned
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

    /**
     * This method checks if string input is valid. It checks if there is empty input or full of spaces.
     * @param inputString the string to be checked
     * @return boolean true or false value to determine if the string input is valid
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

    /**
     * This method checks if year input is valid. It checks if the year is in the range of 2000-2026
     * @param year the year to be checked
     * @return boolean true or false value to determine if the year input is valid
     */
    public boolean yearValid(int year) {

        if (year < 2000 || year > 2026) {
            System.out.println("Please enter a year between 2000-2026.\n");
            return false;
        }

        return true;
    }

    /**
     * This method checks if the top speed input is valid. It checks if the user is using one decimal place or
     * if the speed is positive.
     * @param topSpeed the top speed input to be checked
     * @return boolean true or false value to determine if the top speed input is valid
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

    /**
     * This method checks if the launch status is valid. It checks if it has a value of 0 or 1.
     * @param launchStatus the launch status input to be checked
     * @return boolean true or false value to determine if the launch status input is valid
     */
    public boolean launchValid(int launchStatus) {

        if (launchStatus < 0 || launchStatus > 1) {
            System.out.println("Please enter a valid launch status (0 or 1).\n");
            return false;
        }

        return true;
    }

    /**
     * This method checks if the conditions of the race are valid. It checks if the car objects are not null,
     * if the cars racing are two separate cars, and if either car has been launched already.
     * @param raceCar1 the first race car to be checked
     * @param raceCar2 the second race car to be checked
     * @return boolean true or false to determine if the race conditions have been met
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
