/**
 * This class represents a racecar as a record/object in the DMS. This allows it to store data about the
 * object and is used when creating new entries or performing modifications of a racecar's data.
 *
 * @author Ashbel Legros
 **/
public class RaceCar {
    private int vehicleID;
    private String make;
    private String model;
    private int year;
    private double topSpeed;
    private boolean hasLaunched;

    /**
     * Creates an empty race car object.
     */
    public RaceCar() {

    }

    /**
     * Creates an race car object with data.
     * @param vehicleID Id of the race car
     * @param make The make of the race car
     * @param model The model of the race car
     * @param year The year that the race car was made
     * @param topSpeed The top speed of race car
     * @param hasLaunched The boolean value of whether the car has launched or not
     */
    public RaceCar(int vehicleID, String make, String model, int year, double topSpeed, boolean hasLaunched) {
        this.vehicleID = vehicleID;
        this.make = make;
        this.model = model;
        this.year = year;
        this.topSpeed = topSpeed;
        this.hasLaunched = hasLaunched;
    }

    /**
     * This method gets the value VehicleID from a RaceCar object.
     * @return int the id of the car
     */
    public int getVehicleID() {
        return vehicleID;
    }

    /**
     * This method sets the value of vehicleID to the parameter.
     * @param vehicleID the id of the car
     */
    public void setVehicleID(int vehicleID) {
        this.vehicleID = vehicleID;
    }

    /**
     * This method gets the value of make.
     * @return String value of make
     */
    public String getMake() {
        return make;
    }

    /**
     * This method sets the value of make to the parameter.
     * @param make the make of the car
     */
    public void setMake(String make) {
        this.make = make;
    }

    /**
     * This method gets the value of model.
     * @return String value of model
     */
    public String getModel() {
        return model;
    }

    /**
     * This method sets the value of model to the parameter.
     * @param model the model of the car
     */
    public void setModel(String model) {
        this.model = model;
    }

    /**
     * This method gets the value of year.
     * @return int value of year
     */
    public int getYear() {
        return year;
    }

    /**
     * This method sets the value of year to the parameter.
     * @param year the year the car was made
     */
    public void setYear(int year) {
        this.year = year;
    }

    /**
     * This method gets the value of topSpeed.
     * @return double speed represented in decimal format
     */
    public double getTopSpeed() {
        return topSpeed;
    }

    /**
     * This method sets the value of topSpeed to the parameter.
     * @param topSpeed the top speed that the car can perform
     */
    public void setTopSpeed(double topSpeed) {
        this.topSpeed = topSpeed;
    }

    /**
     * This method represents boolean field, hasLaunched, as a String for clarity to the user
     * @return String launch status represented as string instead of boolean
     */
    public String isHasLaunched() {
        if (hasLaunched) {
            return "Launched";
        }
        else  {
            return "Not Launched";
        }
    }

    /**
     * This method gets the value of hasLaunched
     * @return boolean the boolean value of hasLaucnhed
     */
    public boolean boolHasLaunched() {
        return hasLaunched;
    }

    /**
     * This method sets the value of hasLaunched to the parameter.
     * @param hasLaunched the launch status of the car
     */
    public void setHasLaunched(boolean hasLaunched) {
        this.hasLaunched = hasLaunched;
    }

    /**
     * This method gives a custom string to display all race car information.
     * @return String concatenated string with all fields of the object
     */
    @Override
    public String toString() {
        return vehicleID + " | " + make  + " | " + model + " | " + year + " | " + topSpeed + " | " + isHasLaunched();
    }
}
