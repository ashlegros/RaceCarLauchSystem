import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

/* Name: Ashbel Legros
* Course: Software Development I
* Date : 07/03/26
* Class : CommandLineMenu
* Purpose : displays a user interactive menu to perform CRUD operations and special functions. User enters a number to corresponding command to execute.
* Also, it handles file reading from a text and parsing it to convert to data for the DMS.
*/
public class CommandLineMenu {

    /* Method: loadRaceCarManager()
       Purpose: handles txt file reading to populate DMS with data
       Return Type: RaceCarManager
     */
    public RaceCarManager loadRaceCarManager() {

        while(true) {
            Scanner inputReader = new Scanner(System.in);

            System.out.println("Please enter the path of your txt file (0 to exit): ");
            String filePath = inputReader.nextLine();

            if (filePath.equals("0")) {
                System.exit(0);
            }

            RaceCarManager raceCarService = new RaceCarManager();
            try(BufferedReader br = new BufferedReader(new FileReader(filePath))) {
                System.out.println("Loading Race Car System...");

                String line;
                while ((line = br.readLine()) != null) {
                    String[] data = line.split("-");

                    int id = Integer.parseInt(data[0]);
                    String make = data[1];
                    String model = data[2];
                    int year = Integer.parseInt(data[3]);
                    double topSpeed = Double.parseDouble(data[4]);
                    boolean hasLaunched = Boolean.parseBoolean(data[5]);

                    RaceCar raceCar = new RaceCar(id, make, model, year, topSpeed, hasLaunched);
                    raceCarService.addRaceCar(raceCar);
                    System.out.println(raceCar);


                }
                System.out.println();
                System.out.println("File successfully loaded.\n");
                return raceCarService;

            } catch (FileNotFoundException e) {
                System.out.println("Could not locate the file.\n");
            } catch (IOException e) {
                System.out.println("Something went wrong!");
            }
        }
    }

    /* Method: displayMenu()
       Purpose: displays interactive user menu and handles simple validation like InputMismatch exception
       Return Type: void
       Arguments: RaceCarManager
     */
    public void displayMenu(RaceCarManager raceCarService) {

        while (true) {
            Scanner inputReader = new Scanner(System.in);
            System.out.println("Race Car Launch System\n" +
                    "------------------------------------");

            System.out.println("1 - Load Data");
            System.out.println("2 - Display Race Cars");
            System.out.println("3 - Add Race Car");
            System.out.println("4 - Remove Race Car");
            System.out.println("5 - Update Entry");
            System.out.println("6 - Launch Race Cars");
            System.out.println("0 - Exit");
            System.out.println("------------------------------------");


            int commandNumber;
            try {
                System.out.print("Enter your command number: ");
                commandNumber = inputReader.nextInt();
                inputReader.nextLine();
            } catch (InputMismatchException e) {
                System.out.println("Please enter a valid command number\n");
                inputReader.nextLine();
                continue;
            } catch (Exception e) {
                System.out.println("Something went wrong!");
                continue;
            }

            switch(commandNumber) {
                case 0:
                    System.exit(0);
                    break;
                case 1:
                    raceCarService = loadRaceCarManager();
                    break;
                case 2:
                    raceCarService.displayRaceCars();
                    break;
                case 3:
                    System.out.println("\nAdd Race Car\n------------");
                    System.out.println("*Race cars must have been made in years 2000-2026*\n");
                    int id = -1;
                    String make;
                    String model;
                    int year = -1;
                    double topSpeed = 0.0;
                    int launchStatus = -1; boolean hasLaunched = false;

                    do {
                        while (true) {
                            try {
                                System.out.print("Vehicle ID: ");
                                id = inputReader.nextInt();
                                System.out.println();
                                inputReader.nextLine();
                                break;
                            }
                            catch (InputMismatchException e) {
                                System.out.println("Please enter an integer.\n");
                                inputReader.nextLine();
                            }
                        }
                    } while(!raceCarService.idValid(id));

                    do {
                        System.out.print("Make: ");
                        make = inputReader.nextLine();
                        System.out.println();
                    } while(!raceCarService.stringValid(make));

                    do {
                        System.out.print("Model: ");
                        model = inputReader.nextLine();
                        System.out.println();
                    } while(!raceCarService.stringValid(model));

                    do {
                        while(true) {
                            try{
                                System.out.print("Year: ");
                                year = inputReader.nextInt();
                                System.out.println();
                                inputReader.nextLine();
                                break;
                            }
                            catch (InputMismatchException e) {
                                System.out.println("Please enter a integer.\n");
                                inputReader.nextLine();
                            }
                        }
                    } while(!raceCarService.yearValid(year));

                    do {
                        while (true) {
                            try {
                                System.out.print("Top Speed (one decimal place if needed): ");
                                topSpeed = inputReader.nextDouble();
                                System.out.println();
                                inputReader.nextLine();

                                break;
                            }
                            catch (InputMismatchException e) {
                                System.out.println("Please enter a decimal.\n");
                                inputReader.nextLine();
                            }
                        }
                    } while(!raceCarService.topSpeedValid(topSpeed));

                    do {
                        try{
                            System.out.print("Launch Status: (0 for Not Launched : 1 for Launched ): ");
                            launchStatus = inputReader.nextInt();
                            if (launchStatus == 1) {
                                hasLaunched = true;
                                System.out.println();
                                inputReader.nextLine();
                                break;
                            }
                            else if (launchStatus == 0) {
                                hasLaunched = false;
                                System.out.println();
                                inputReader.nextLine();
                                break;
                            }
                        }
                        catch(InputMismatchException e) {
                            System.out.println("Please enter an integer.\n");
                            inputReader.nextLine();
                        }
                    } while (!raceCarService.launchValid(launchStatus));

                    RaceCar newCar = new RaceCar(id, make, model, year, topSpeed, hasLaunched);

                    if(raceCarService.addRaceCar(newCar)){
                        System.out.println("Race Car added successfully\n");
                    }
                    else{
                        System.out.println("Race Car could not be added\n");
                    }

                    break;
                case 4:
                    while (true) {
                        try{
                            System.out.print("Vehicle ID of the car to be removed: ");
                            int removeID = inputReader.nextInt();
                            if (raceCarService.removeRaceCar(removeID)){
                                System.out.println("Race Car removed successfully\n");
                            }
                            else {
                                System.out.println("Race Car could not be removed\n");
                            }
                            inputReader.nextLine();
                            System.out.println();
                            break;
                        }
                        catch (InputMismatchException e) {
                            System.out.println("Please enter a integer.\n");
                            inputReader.nextLine();
                        }
                    }
                    break;
                case 5:
                    System.out.println("\nUpdate Entry\n------------");
                    int idUpdate = -1;
                    String makeUpdate;
                    String modelUpdate;
                    int yearUpdate = -1;
                    double topSpeedUpdate;
                    int launchStatusUpdate = -1; boolean hasLaunchedUpdate;

                    RaceCar carToBeUpdated = null;

                        while (carToBeUpdated == null) {
                            try {
                                System.out.print("Vehicle ID to be updated: ");
                                idUpdate = inputReader.nextInt();
                                inputReader.nextLine();
                                System.out.println();

                                carToBeUpdated = raceCarService.idFound(idUpdate);

                                if (carToBeUpdated == null) {
                                    System.out.println("Car was not found\n");
                                }
                            }
                            catch (InputMismatchException e) {
                                System.out.println("Please enter a integer.\n");
                                inputReader.nextLine();
                            }

                            if(!raceCarService.updateRaceCar(idUpdate)){
                                System.out.println("Race Car could not be found\n");
                            }
                        }


                    while(true) {
                        System.out.print("New Make (Press Enter to skip and keep original " + carToBeUpdated.getMake() + ") : ");
                        makeUpdate = inputReader.nextLine();

                        if (makeUpdate.isBlank()) {
                            break;
                        }

                        if (raceCarService.stringValid(makeUpdate)) {
                            carToBeUpdated.setMake(makeUpdate);
                            break;
                        }

                        System.out.println("Enter a valid make.\n");
                    }

                    while(true) {
                        System.out.print("New Model (Press Enter to skip and keep original " + carToBeUpdated.getModel() + ") : ");
                        modelUpdate = inputReader.nextLine();

                        if (modelUpdate.isBlank()) {
                            break;
                        }

                        if (raceCarService.stringValid(modelUpdate)) {
                            carToBeUpdated.setModel(modelUpdate);
                            break;
                        }

                        System.out.println("Enter a valid model.\n");
                    }


                    while(true) {
                        try{
                            System.out.print("New Year: (Enter -1 to keep original " + carToBeUpdated.getYear() + ") : ");
                            yearUpdate = inputReader.nextInt();
                            inputReader.nextLine();

                            if(yearUpdate == -1){
                                break;
                            }

                            if(raceCarService.yearValid(yearUpdate)){
                                carToBeUpdated.setYear(yearUpdate);
                                break;
                            }

                            System.out.println("Enter a valid year.\n");
                        }
                        catch (InputMismatchException e) {
                            System.out.println("Please enter a integer.\n");
                            inputReader.nextLine();
                        }
                    }


                    while (true) {
                        try {
                            System.out.print("New Top Speed (Enter -1 to keep original " + carToBeUpdated.getTopSpeed() + ") : ");
                            topSpeedUpdate = inputReader.nextDouble();
                            inputReader.nextLine();

                            if(topSpeedUpdate == -1){
                                break;
                            }

                            if(raceCarService.topSpeedValid(topSpeedUpdate)){
                                carToBeUpdated.setTopSpeed(topSpeedUpdate);
                                break;
                            }

                            System.out.println("Enter a valid top speed.\n");
                        }
                        catch (InputMismatchException e) {
                            System.out.println("Please enter a decimal.\n");
                            inputReader.nextLine();
                        }
                    }


                    while (true) {
                        try{
                            System.out.print("New Launch Status: (0 for Not Launched : 1 for Launched / Enter -1 keep original " + carToBeUpdated.isHasLaunched() + "): ");
                            launchStatusUpdate = inputReader.nextInt();


                            if(launchStatusUpdate == -1){
                                inputReader.nextLine();
                                break;
                            }

                            if(raceCarService.launchValid(launchStatusUpdate)){
                                if (launchStatusUpdate == 1) {
                                    hasLaunchedUpdate = true;
                                    carToBeUpdated.setHasLaunched(hasLaunchedUpdate);
                                    System.out.println();
                                    inputReader.nextLine();
                                    break;
                                }
                                if (launchStatusUpdate == 0) {
                                    hasLaunchedUpdate = false;
                                    carToBeUpdated.setHasLaunched(hasLaunchedUpdate);
                                    System.out.println();
                                    inputReader.nextLine();
                                    break;
                                }
                            }

                            System.out.println("Enter a valid launch status.\n");
                        }
                        catch(InputMismatchException e) {
                            System.out.println("Please enter an integer.\n");
                            inputReader.nextLine();
                        }
                    }


                    System.out.println("Update Entry: " + carToBeUpdated);
                    break;
                case 6:
                    System.out.println("\nLaunch Cars\n------------");
                    RaceCar launchCar1;
                    RaceCar launchCar2;
                    int idLaunch;

                    do {
                        launchCar1 = null;
                        launchCar2 = null;
                        while (launchCar1 == null) {
                            try {
                                System.out.print("Launch Car ID #1: ");
                                idLaunch = inputReader.nextInt();
                                inputReader.nextLine();
                                System.out.println();

                                launchCar1 = raceCarService.idFound(idLaunch);

                                if (launchCar1 == null) {
                                    System.out.println("Car was not found\n");
                                }
                            }
                            catch (InputMismatchException e) {
                                System.out.println("Please enter a integer.\n");
                                inputReader.nextLine();
                            }
                        }

                        while (launchCar2 == null) {
                            try {
                                System.out.print("Launch Car ID #2: ");
                                idLaunch = inputReader.nextInt();
                                inputReader.nextLine();
                                System.out.println();

                                launchCar2 = raceCarService.idFound(idLaunch);

                                if (launchCar2 == null) {
                                    System.out.println("Car was not found\n");
                                }
                            }
                            catch (InputMismatchException e) {
                                System.out.println("Please enter a integer.\n");
                                inputReader.nextLine();
                            }
                        }
                    } while(!raceCarService.raceValid(launchCar1, launchCar2));

                    if(raceCarService.launchRaceCars(launchCar1, launchCar2)){
                        System.out.println("Race completed successfully!\n");
                    }

                    break;
            }
        }
    }
}
