/*
 * Name : Ashbel Legros
 * Course : Software Development I
 * Date : 07/16/26
 * Class : RaceCarDBManager
 * Purpose : Contains business logic to perform CRUD operations to the RaceCar database. It also performs a special function
 * to simulate a race for cars
 * */

import DBHelper.RaceCars;

import java.sql.*;
import java.util.ArrayList;

public class RaceCarDBManager extends RaceCarManager{
    private Connection connection;
    private RaceCars raceCarTable;

    public RaceCarDBManager() {
        connection = null;
        raceCarTable = new RaceCars();
    }

    public Connection getConnection() {
        return connection;
    }
    public void setConnection(Connection connection) {
        this.connection = connection;
    }
    public RaceCars getRaceCarTable() {
        return raceCarTable;
    }
    public void setRaceCarTable(RaceCars raceCarTable) {
        this.raceCarTable = raceCarTable;
    }

    /* Method: loadRaceCarDB()
       Purpose: connects to sqlite database of choosing
       Return Type: boolean
       Arguments: String filePath
     */
    public boolean loadRaceCarDB(String filePath){
        try {
            String url = "jdbc:sqlite:" + filePath;
            setConnection(DriverManager.getConnection(url));
            raceCarTable.setDATABASE_NAME(filePath);

        }
        catch (SQLException e) {
            System.out.println("Connection with DB failed!");
            return false;
        }

        return  true;
    }

    /* Method: addRaceCar()
       Purpose: adds a race car entry to the database
       Return Type: boolean
       Argument: RaceCar raceCar
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

    /* Method: removeRaceCar()
       Purpose: removes a race car entry from the database
       Return Type: boolean
       Argument: int vehicleID
     */
    public boolean removeRaceCar(int vehicleID) {
        System.out.println("Removing Race Car...");

        raceCarTable.delete(RaceCars.VehicleID, String.valueOf(vehicleID));

        return true;
    }

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

    /* Method: getRaceCar()
       Purpose: converts sql select all query into an array list
       Return Type: ArrayList
       Argument: RaceCar raceCar
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

    /* Method: launchRaceCars()
       Purpose: simulates a race between two race cars by probability
       Return Type: RaceCar
       Arguments: RaceCar raceCar1, RaceCar raceCar2
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
}