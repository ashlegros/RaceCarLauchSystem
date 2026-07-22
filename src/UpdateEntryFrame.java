import javax.swing.*;

/**
 *  This class provides an update form for user to modify existing information. Once new information replaces existing data,
 *  changes are made to the database. This window appears after clicking the Update Entry button form the
 *  main window, MainFrame.
 *
 * @author Ashbel Legros
 **/
public class UpdateEntryFrame extends JFrame {
    private JTextField idField;
    private JTextField makeField;
    private JTextField modelField;
    private JTextField yearField;
    private JTextField speedField;
    private JButton updateButton;
    private JButton cancelButton;
    private JPanel updatePanel;
    private JPanel buttonPanel;
    private JPanel carInfoPanel;

    private RaceCarDBManager raceCarDBService;
    private MainFrame mainFrame;
    private RaceCar updateCar;

    /**
     * Initializes a window that allows the user to update select existing data.
     * @param raceCarService This allows the window to perform SQL operations to the database
     * @param updateCar a pre-existing car that is to be updated with user input
     * @param mainFrame This allows the window to refresh the table to the main window.
     */
    public UpdateEntryFrame(RaceCarDBManager raceCarService, RaceCar updateCar, MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        this.raceCarDBService =  raceCarService;
        this.updateCar = updateCar;

        setContentPane(updatePanel);
        setTitle("Update Entry");
        setSize(246, 286);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        //have pre-existing info in textfields
        makeField.setText(updateCar.getMake());
        modelField.setText(updateCar.getModel());
        yearField.setText(String.valueOf(updateCar.getYear()));
        speedField.setText(String.valueOf(updateCar.getTopSpeed()));

        updateButton.addActionListener(e -> {
            try{
                String make = makeField.getText();
                String model = modelField.getText();
                int year = Integer.parseInt(yearField.getText());
                double speed = Double.parseDouble(speedField.getText());

                if (!raceCarDBService.stringValid(make)) {
                    JOptionPane.showMessageDialog(this, "Please enter a valid make (no blanks or spaces)");
                    return;
                }
                if (!raceCarDBService.stringValid(model)) {
                    JOptionPane.showMessageDialog(this, "Please enter a valid model (no blanks or spaces)");
                    return;
                }
                if (!raceCarDBService.yearValid(year)) {
                    JOptionPane.showMessageDialog(this, "Please enter a valid year (2000-2026)");
                    return;
                }
                if (!raceCarDBService.topSpeedValid(speed)) {
                    JOptionPane.showMessageDialog(this, "Please enter a valid speed (one decimal format)");
                    return;
                }

                raceCarDBService.updateRaceCar(updateCar.getVehicleID(), make, model, year, speed, updateCar.boolHasLaunched());

                mainFrame.refreshRaceCarTable();

                dispose();
            }
            catch(NumberFormatException ex){
                JOptionPane.showMessageDialog(this, "Please enter a valid ID, make, model, year, and speed.", "Invalid input(s)", JOptionPane.WARNING_MESSAGE);
            }
        });
        cancelButton.addActionListener(e -> {
            dispose();
        });

        setVisible(true);
    }
}
