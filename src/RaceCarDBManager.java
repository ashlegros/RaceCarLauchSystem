import DBHelper.RaceCars;
import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;

/**
 * This child class contains business logic to perform CRUD operations to the RaceCar database. It also
 * performs a special function to simulate a race for cars. This drives the backend of the system as it performs
 * all necessary action to complete CRUD functionality.
 *
 * @author Ashbel Legros
 **/
public class RaceCarDBManager extends RaceCarManager{
    private Connection connection;
    private RaceCars raceCarTable;

    /**
     * Initializes the manager with no connection and a query helper. The query helper (raceCarTable) handles
     * database operations in SQL.
     */
    public RaceCarDBManager() {
        connection = null;
        raceCarTable = new RaceCars();
    }

    /**
     * This method gets the connection value.
     * @return Connection value of connection
     */
    public Connection getConnection() {
        return connection;
    }

    /**
     * This method sets the value of connection the parameter.
     * @param connection established connection of a database
     */
    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    /**
     * This method gets the value of raceCarTable.
     * @return RaceCars the service that allows database operations
     */
    public RaceCars getRaceCarTable() {
        return raceCarTable;
    }

    /**
     * This method sets the value of RaceCarTable to the parameter.
     * @param raceCarTable the service that allows database operations
     */
    public void setRaceCarTable(RaceCars raceCarTable) {
        this.raceCarTable = raceCarTable;
    }

    /**
     * This establishes connection to the database.
     * @param filePath the file path of the sample database
     * @return boolean true or false value to determine if operation was successful
     */
    public boolean loadRaceCarDB(String filePath){
        try {
            String url = "jdbc:sqlite:" + filePath;
            setConnection(DriverManager.getConnection(url));
            raceCarTable.setDATABASE_NAME(filePath);

        }
        catch (SQLException e) {
            System.out.println("Connection with DB failed!");
            System.out.println("Database path: " + filePath);
            e.printStackTrace();
            return false;
        }

        return  true;
    }

    /**
     * This method adds a race car object to the database.
     * @param raceCar object representing the race car
     * @return boolean true or false value to determine if operation was successful
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

        raceCarTable.insert(raceCar.getVehicleID(), raceCar.getMake(), raceCar.getModel(), raceCar.getYear(),raceCar.getTopSpeed(), 0);

        return true;
    }

    /**
     * This method removes a race car from the database.
     * @param vehicleID the id of the car the user wants to remove
     * @return boolean true or false value to determine if the operation was successful
     */
    public boolean removeRaceCar(int vehicleID) {
        System.out.println("Removing Race Car...");

        raceCarTable.delete(RaceCars.VehicleID, String.valueOf(vehicleID));

        return true;
    }

    /**
     * This method performs updates to the database, modifying existing information.
     * @param id the id of the car
     * @param make the make of the car
     * @param model the model of the car
     * @param year the year that the car was made
     * @param topSpeed the top speed that the car can perform
     * @param hasLaunched the boolean value that represents the launch status of the car
     * @return boolean true or false value that determines if the operation was successful
     */
    public boolean updateRaceCar(int id, String make, String model, int year, double topSpeed, boolean hasLaunched) {
        System.out.println("Updating Race Car...");

        RaceCar carToBeUpdated = new RaceCar(id, make, model, year, topSpeed, hasLaunched);

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

        raceCarTable.update(RaceCars.Make, make, RaceCars.VehicleID, String.valueOf(id));
        raceCarTable.update(RaceCars.Model, model, RaceCars.VehicleID, String.valueOf(id));
        raceCarTable.update(RaceCars.Year, String.valueOf(year), RaceCars.VehicleID, String.valueOf(id));
        raceCarTable.update(RaceCars.TopSpeed, String.valueOf(topSpeed), RaceCars.VehicleID, String.valueOf(id));

        return true;
    }

    /**
     * This method converts a SQL select query into an array list.
     * @return the result of the query as an array list
     */
    @Override
    public ArrayList<RaceCar> getRaceCars() {
        ArrayList<RaceCar> raceCars = new ArrayList<>();

        String query = "SELECT * FROM RaceCars";

        try {
            Statement statement = connection.createStatement();
            ResultSet sqlOutput = statement.executeQuery(query);

            while (sqlOutput.next()) {
                int id = sqlOutput.getInt("VehicleID");
                String make = sqlOutput.getString("Make");
                String model = sqlOutput.getString("Model");
                int year = sqlOutput.getInt("Year");
                double topSpeed = sqlOutput.getDouble("TopSpeed");
                boolean hasLaunched = sqlOutput.getBoolean("LaunchStatus");

                RaceCar car = new RaceCar(id, make, model, year, topSpeed, hasLaunched);
                raceCars.add(car);
            }
        }
        catch (SQLException e) {
            System.out.println("Could not perform SQL query!");
        }
        return raceCars;
    }

    /**
     * This method simulates a race between two cars.
     * @param raceCar1 the first car to be raced
     * @param raceCar2 the second car to be raced against
     * @return RaceCar the winner of the race
     */
    public RaceCar launchRaceCars(RaceCar raceCar1, RaceCar raceCar2) {
        System.out.println("Launching Race Car...");

        if (!raceValid(raceCar1, raceCar2)) {
            return null;
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

        raceCarTable.update(RaceCars.LaunchStatus, "1", RaceCars.VehicleID, String.valueOf(raceCar1.getVehicleID()));
        raceCarTable.update(RaceCars.LaunchStatus, "1", RaceCars.VehicleID, String.valueOf(raceCar2.getVehicleID()));

        return winner;
    }

    // Validation Methods

    /**
     * This method performs validation of the vehicle id. This checks if there is an existing id or if there
     * is a non-negative number.
     * @param id the id to be checked
     * @return boolean true or false value to determine if the id was valid
     */
    public boolean idValid(int id) {
        for (RaceCar raceCar : getRaceCars()) {
            if (raceCar.getVehicleID() == id) {
                return false;
            }
        }

        if (id < 0){
            System.out.println("Please enter a positive number.\n");
            return false;
        }

        return true;
    }

    /**
     * This method checks if the class has a connection.
     * @return boolean true or false value to determine if there is a connection
     */
    public boolean hasConnection() {
        if (getConnection() == null) {
            return false;
        }
        return true;
    }
}
