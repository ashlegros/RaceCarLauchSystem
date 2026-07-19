/*
 * Name : Ashbel Legros
 * Course : Software Development I
 * Date : 07/13/26
 * Class : LaunchCarsFrame
 * Purpose : Provides launch window for user to simulate a race.
 * */

import javax.swing.*;

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
