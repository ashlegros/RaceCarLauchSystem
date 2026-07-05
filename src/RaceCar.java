/*
 * Name : Ashbel Legros
 * Course : Software Development I
 * Date : 07/03/26
 * Class : RaceCar
 * Purpose : Represent a racecar as a record/object in the DMS.
 * */

public class RaceCar {
    private int vehicleID;
    private String make;
    private String model;
    private int year;
    private double topSpeed;
    private boolean hasLaunched;

    public RaceCar() {

    }

    public RaceCar(int vehicleID, String make, String model, int year, double topSpeed, boolean hasLaunched) {
        this.vehicleID = vehicleID;
        this.make = make;
        this.model = model;
        this.year = year;
        this.topSpeed = topSpeed;
        this.hasLaunched = hasLaunched;
    }

    public int getVehicleID() {
        return vehicleID;
    }

    public void setVehicleID(int vehicleID) {
        this.vehicleID = vehicleID;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public double getTopSpeed() {
        return topSpeed;
    }

    public void setTopSpeed(double topSpeed) {
        this.topSpeed = topSpeed;
    }

    /* Method: isHasLaunched()
       Purpose: represents boolean field, hasLaunched, as a String for clarity of user
       Return Type: String
     */
    public String isHasLaunched() {
        if (hasLaunched) {
            return "Launched";
        }
        else  {
            return "Not Launched";
        }
    }

    public boolean boolHasLaunched() {
        return hasLaunched;
    }

    public void setHasLaunched(boolean hasLaunched) {
        this.hasLaunched = hasLaunched;
    }

    /* Method: toString
       Purpose: Gives custom string to display all race car information
       Return Type: String
     */
    @Override
    public String toString() {
        return vehicleID + " | " + make  + " | " + model + " | " + year + " | " + topSpeed + " | " + isHasLaunched();
    }
}
