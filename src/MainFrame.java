/*
 * Name : Ashbel Legros
 * Course : Software Development I
 * Date : 07/13/26
 * Class : MainFrame
 * Purpose : Provides main window for user interaction.
 * */

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class MainFrame extends JFrame{
    //From ui swing form
    private JTable raceCarTable;
    private JButton loadDataButton;
    private JButton exitButton;
    private JButton addCarButton;
    private JButton removeCarButton;
    private JButton updateEntryButton;
    private JButton launchCarsButton;
    private JPanel mainPanel;
    private JLabel titleLabel;
    private JScrollPane tableScrollPane;
    private JPanel buttonPanel;

    private RaceCarManager raceCarService;
    private DefaultTableModel tableModel;

    public MainFrame(RaceCarManager raceCarService) {
        this.raceCarService = raceCarService;

        setContentPane(mainPanel);
        setTitle("Race Car Launch System");
        setSize(738,448);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        tableModel = new DefaultTableModel();
        tableModel.setColumnIdentifiers(new String[]{
            "Vehicle ID", "Make", "Model", "Year", "Top Speed", "Launch Status"
        });
        raceCarTable.setModel(tableModel);

        tableScrollPane.getViewport().setBackground(new Color(105, 94, 94));

        raceCarTable.setForeground(new Color(39, 46, 82));
        raceCarTable.getTableHeader().setBackground(new Color(160, 159, 159));
        raceCarTable.getTableHeader().setFont(new Font("Times New Roman", Font.BOLD, 16));

        titleLabel.setBackground(new Color(160, 159, 159));


        raceCarTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); // can select entries on table

        loadDataButton.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            int userReturn = fileChooser.showOpenDialog(this);

            if (userReturn == JFileChooser.APPROVE_OPTION) {
                String filePath = fileChooser.getSelectedFile().getAbsolutePath();

                boolean loadStatus = this.raceCarService.loadRaceCarManager(filePath);

                if (loadStatus) {
                    refreshRaceCarTable();
                }

                System.out.println("File selected: " + fileChooser.getSelectedFile().getAbsolutePath());
            }
        });

        addCarButton.addActionListener(e -> {
            new AddCarFrame(raceCarService, this);
        });

        removeCarButton.addActionListener(e -> {
           int userSelectedRow = raceCarTable.getSelectedRow();

           if (userSelectedRow == -1) {
               JOptionPane.showMessageDialog(this, "Select the entry you would like to remove from table", "No Car Selection", JOptionPane.ERROR_MESSAGE);
               return;
           }

           RaceCar removedCar = raceCarService.getRaceCars().get(userSelectedRow);

           int userChoice = JOptionPane.showConfirmDialog(this, "Do you want to remove car: " + removedCar.toString(), "Remove Car", JOptionPane.YES_NO_OPTION);

           if (userChoice == JOptionPane.YES_OPTION) {
               raceCarService.removeRaceCar(removedCar.getVehicleID());
               refreshRaceCarTable();
           }
        });

        updateEntryButton.addActionListener(e -> {
           int userSelectedRow = raceCarTable.getSelectedRow();

           if (userSelectedRow == -1) {
               JOptionPane.showMessageDialog(this, "Select the entry you would like to update from table", "No Car Selection", JOptionPane.ERROR_MESSAGE);
               return;
           }

           RaceCar updatedCar = raceCarService.getRaceCars().get(userSelectedRow);

           new UpdateEntryFrame(raceCarService, updatedCar, this);
        });

        launchCarsButton.addActionListener(e -> {
            new LaunchCarsFrame(raceCarService, this);
        });

        exitButton.addActionListener(e -> System.exit(0));

        setVisible(true);
    }

    // Method: updateRaceCarTable
    // Purpose: refreshes the JTable in the GUI (used after changes have been performed)
    // Return: void
    public void refreshRaceCarTable() {
        tableModel.setRowCount(0);
        for (RaceCar raceCar : raceCarService.getRaceCars()) {

            Object[] row = {
                    raceCar.getVehicleID(),
                    raceCar.getMake(),
                    raceCar.getModel(),
                    raceCar.getYear(),
                    raceCar.getTopSpeed(),
                    raceCar.isHasLaunched()
            };
            tableModel.addRow(row);
        }
    }
}
