import javax.swing.*;

/**
 * This class provides a launch window for user to simulate a race. They can choose two cars that have not
 * been launched, and race them against each other.
 *
 * @author Ashbel Legros
 * */
public class LaunchCarsFrame extends JFrame {
    private JButton launchButton;
    private JButton cancelButton;
    private JComboBox carOneIntBox;
    private JComboBox carTwoIntBox;
    private JPanel launchPanel;
    private JLabel titleLabel;
    private JPanel buttonPanel;
    private JLabel carOneLabel;
    private JLabel carTwoLabel;

    private RaceCarDBManager raceCarDBService;
    private MainFrame mainFrame;

    /**
     * Initializes the launch window which allows users to simulate a race between two cars.
     * @param raceCarService This allows the window to perform SQL operations to the database
     * @param mainFrame This allows the window to refresh the table after changes have been made to the main window
     */
    public LaunchCarsFrame(RaceCarDBManager raceCarService, MainFrame mainFrame) {
        this.raceCarDBService = raceCarService;
        this.mainFrame = mainFrame;

        setContentPane(launchPanel);
        setTitle("Launch Cars");
        setSize(555, 165);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        for (RaceCar raceCar : raceCarDBService.getRaceCars()) {
            carOneIntBox.addItem(raceCar);
            carTwoIntBox.addItem(raceCar);
        }

        launchButton.addActionListener(e -> {
            RaceCar launchCar1 = (RaceCar) carOneIntBox.getSelectedItem();
            RaceCar launchCar2 = (RaceCar) carTwoIntBox.getSelectedItem();

            if (!raceCarDBService.raceValid(launchCar1, launchCar2)) {
                JOptionPane.showMessageDialog(this, "Invalid Race! (Use to separate cars or cars that have not launched!)");
                return;
            }

            RaceCar winner = raceCarDBService.launchRaceCars(launchCar1, launchCar2);

            if (winner != null) {
                JOptionPane.showMessageDialog(this, "Winner is: " + winner);
            }
            mainFrame.refreshRaceCarTable();

            dispose();
        });
        cancelButton.addActionListener(e -> {
            dispose();
        });

        setVisible(true);
    }
}
