/*
 * Name : Ashbel Legros
 * Course : Software Development I
 * Date : 07/03/26
 * Class : MainSystem
 * Program Objective : Provide operator with a DMS to manage RaceCars loaded by a text file. They should be able to
 * print all race cars to screen, add a new race car with details, remove a race car, update a race car entry, and launch/simulate a race.
 * User should be able to exit the DMS as well using the corresponding command action.
 * */

public class MainSystem {
    public static void main(String[] args) {
        RaceCarManager raceCarService = new RaceCarManager();

        CommandLineMenu system = new CommandLineMenu();

        system.displayMenu(raceCarService);
    }
}
