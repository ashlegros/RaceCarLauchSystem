/**
 * The class contains main and starts the program. The purpose is to provide operator with a DMS to manage RaceCars connected to
 * a database. They should be able to print all race cars to screen, add a new race car with details,
 * remove a race car, update a race car entry, and launch/simulate a race. User should be able to exit the DMS as well using the corresponding exit button.
 *
 * @author Ashbel Legros
 **/
public class MainSystem {
    /**
     * The main method which starts up the GUI.
     */
    public static void main(String[] args) {
        RaceCarDBManager raceCarService = new RaceCarDBManager();

        MainFrame mainFrame = new MainFrame(raceCarService);
    }
}
