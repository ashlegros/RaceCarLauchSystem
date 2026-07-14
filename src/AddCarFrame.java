/*
 * Name : Ashbel Legros
 * Course : Software Development I
 * Date : 07/13/26
 * Class : AddCarFrame
 * Purpose : Provides a form for user to enter information for a new entry.
 * */

import javax.swing.*;

public class AddCarFrame extends JFrame {
    private JTextField idField;
    private JTextField makeField;
    private JTextField modelField;
    private JTextField yearField;
    private JTextField speedField;
    private JButton saveButton;
    private JButton cancelButton;
    private JPanel addPanel;
    private JPanel buttonPanel;
    private JPanel carInfoPanel;

    private RaceCarManager raceCarService;
    private MainFrame mainFrame;

    public AddCarFrame(RaceCarManager raceCarService, MainFrame mainFrame) {

        this.raceCarService = raceCarService;
        this.mainFrame = mainFrame;

        setContentPane(addPanel);
        setTitle("Add Car");
        setSize(246, 246);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        saveButton.addActionListener(e -> {
           try{
               int id = Integer.parseInt(idField.getText());
               String make = makeField.getText();
               String model = modelField.getText();
               int year = Integer.parseInt(yearField.getText());
               double speed = Double.parseDouble(speedField.getText());

               if(!raceCarService.idValid(id)) {
                   JOptionPane.showMessageDialog(this, "Please enter a valid ID (no existing id's or negatives)");
                   return;
               }
               if (!raceCarService.stringValid(make)) {
                   JOptionPane.showMessageDialog(this, "Please enter a valid make (no blanks or spaces)");
                   return;
               }
               if (!raceCarService.stringValid(model)) {
                   JOptionPane.showMessageDialog(this, "Please enter a valid model (no blanks or spaces)");
                   return;
               }
               if (!raceCarService.yearValid(year)) {
                   JOptionPane.showMessageDialog(this, "Please enter a valid year (2000-2026)");
                   return;
               }
               if (!raceCarService.topSpeedValid(speed)) {
                   JOptionPane.showMessageDialog(this, "Please enter a valid speed (one decimal format)");
                   return;
               }

               RaceCar raceCar = new RaceCar(id, make, model, year, speed, false);
               raceCarService.addRaceCar(raceCar);
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
