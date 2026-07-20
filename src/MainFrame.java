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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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

    private RaceCarDBManager raceCarDBService;
    private DefaultTableModel tableModel;

    public MainFrame(RaceCarDBManager raceCarService) {
        this.raceCarDBService = raceCarService;

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

                boolean loadStatus = this.raceCarDBService.loadRaceCarDB(filePath);

                if (loadStatus) {
                    refreshRaceCarTable();
                }

                System.out.println("File selected: " + fileChooser.getSelectedFile().getAbsolutePath());
            }
        });

        addCarButton.addActionListener(e -> {
            if (!raceCarService.hasConnection()) {
                JOptionPane.showMessageDialog(null, "Connection Failed! Please connect to a database.");
                return;
            }

            new AddCarFrame(raceCarDBService, this);
        });

        removeCarButton.addActionListener(e -> {
            if (!raceCarService.hasConnection()) {
                JOptionPane.showMessageDialog(null, "Connection Failed! Please connect to a database.");
                return;
            }

            int userSelectedRow = raceCarTable.getSelectedRow();

           if (userSelectedRow == -1) {
               JOptionPane.showMessageDialog(this, "Select the entry you would like to remove from table", "No Car Selection", JOptionPane.ERROR_MESSAGE);
               return;
           }

           int idToBeRemoved = (Integer) tableModel.getValueAt(userSelectedRow, 0);

           int userChoice = JOptionPane.showConfirmDialog(this, "Do you want to remove car ID: " + idToBeRemoved, "Remove Car", JOptionPane.YES_NO_OPTION);

           if (userChoice == JOptionPane.YES_OPTION) {
               raceCarDBService.removeRaceCar(idToBeRemoved);
               refreshRaceCarTable();
           }
        });

        updateEntryButton.addActionListener(e -> {
            if (!raceCarService.hasConnection()) {
                JOptionPane.showMessageDialog(null, "Connection Failed! Please connect to a database.");
                return;
            }

            int userSelectedRow = raceCarTable.getSelectedRow();

           if (userSelectedRow == -1) {
               JOptionPane.showMessageDialog(this, "Select the entry you would like to update from table", "No Car Selection", JOptionPane.ERROR_MESSAGE);
               return;
           }

           int id = (Integer)raceCarTable.getValueAt(userSelectedRow, 0);
           String make = (String)raceCarTable.getValueAt(userSelectedRow, 1);
           String model = (String)raceCarTable.getValueAt(userSelectedRow, 2);
           Integer year = (Integer)raceCarTable.getValueAt(userSelectedRow, 3);
           Double topSpeed = (Double)raceCarTable.getValueAt(userSelectedRow, 4);

           String status = (String)raceCarTable.getValueAt(userSelectedRow, 5);
           boolean launchStatus = status.equals("Launched");

           RaceCar updatedCar = new RaceCar(id, make, model, year, topSpeed, launchStatus);

           new UpdateEntryFrame(raceCarDBService, updatedCar, this);
        });

        launchCarsButton.addActionListener(e -> {
            if (!raceCarService.hasConnection()) {
                JOptionPane.showMessageDialog(null, "Connection Failed! Please connect to a database.");
                return;
            }

            new LaunchCarsFrame(raceCarDBService, this);
        });

        exitButton.addActionListener(e -> System.exit(0));

        setVisible(true);
    }

    // Method: updateRaceCarTable
    // Purpose: refreshes the JTable in the GUI (used after changes have been performed)
    // Return: void
    public void refreshRaceCarTable() {
        tableModel.setRowCount(0);

        String query = "SELECT * FROM RaceCars";
        try {
            Statement statement = raceCarDBService.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                tableModel.addRow(new Object[]{
                        resultSet.getInt("VehicleID"),
                        resultSet.getString("Make"),
                        resultSet.getString("Model"),
                        resultSet.getInt("Year"),
                        resultSet.getDouble("TopSpeed"),
                        resultSet.getBoolean("LaunchStatus") ? "Launched" : "Not Launched"
                });
            }

        } catch (SQLException e) {
            System.out.println("Error connecting to database");
        }
    }
}
