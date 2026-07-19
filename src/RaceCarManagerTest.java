/*
 * Name: Ashbel Legros
 * Course: Software Development I
 * Date: 07/08/2026
 * Class: RaceCarManagerTest
 * Purpose: Run unit tests to verify functionality of CRUD operations and special function.
 */

import static org.junit.jupiter.api.Assertions.*;

/*class RaceCarManagerTest {
    private RaceCarManager testService;

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        testService = new RaceCarManager();
    }

    @org.junit.jupiter.api.Test
    void openFilePositive() {
        boolean testResult =  testService.loadRaceCarManager("/Users/ashvc/IdeaProjects/RaceCarLaunchSystem/src/RaceCarData.txt");

        assertTrue(testResult, "Opening file failed");
    }
    @org.junit.jupiter.api.Test
    void openFileNegative() {
        boolean testResult = testService.loadRaceCarManager("abc"); //wrong file path : will return false

        assertTrue(testResult, "Opening file failed");
    }

    @org.junit.jupiter.api.Test
    void addRaceCarPositive() {
        RaceCar testRaceCar = new RaceCar(1, "Toyota", "Supra", 2016, 185.7, false);

        boolean testResult = testService.addRaceCar(testRaceCar);

        assertTrue(testResult, "Adding race car failed");
    }
    @org.junit.jupiter.api.Test
    void addRaceCarNegative() {
        RaceCar testRaceCar = new RaceCar(1, "Toyota", "Supra", 1999, 185.7, false); //invalid year : will return false

        boolean testResult = testService.addRaceCar(testRaceCar);

        assertTrue(testResult, "Adding race car failed");
    }

    @org.junit.jupiter.api.Test
    void removeRaceCarPositive() {
        RaceCar testRaceCar = new RaceCar(1, "Toyota", "Supra", 2016, 185.7, false);
        testService.addRaceCar(testRaceCar);

        testService.removeRaceCar(1);

        assertEquals(0, testService.getRaceCars().size(), "Removing race car failed");
    }
    @org.junit.jupiter.api.Test
    void removeRaceCarNegative() {
        RaceCar testRaceCar = new RaceCar(1, "Toyota", "Supra", 2016, 185.7, false);
        testService.addRaceCar(testRaceCar);

        testService.removeRaceCar(-1); //No car exists at -1 // will not remove a car

        assertEquals(0, testService.getRaceCars().size(), "Removing race car failed");
    }

    @org.junit.jupiter.api.Test
    void updateRaceCarPositive() {
        RaceCar testRaceCar = new RaceCar(1, "Toyota", "Supra", 2016, 185.7, false);
        testService.addRaceCar(testRaceCar);

        testService.updateRaceCar(1, "Toyota", "Supra", 2018, 185.7, false); //changed year

        assertEquals(2018, testService.getRaceCars().get(0).getYear(), "Updating race car failed");
    }
    @org.junit.jupiter.api.Test
    void updateRaceCarNegative() {
        RaceCar testRaceCar = new RaceCar(1, "Toyota", "Supra", 2016, 185.7, false);
        testService.addRaceCar(testRaceCar);

        boolean testResult = testService.updateRaceCar(-1, "Toyota", "Supra", 2016, 185.7, false); // invalid id -1 : will return false

        assertTrue(testResult, "Updating race car failed");
    }

    @org.junit.jupiter.api.Test
    void launchRaceCarsPositive() {
        RaceCar testLaunchCar1 = new RaceCar(1, "Toyota", "Supra", 2016, 185.7, false);
        RaceCar testLaunchCar2 = new RaceCar(2, "Mazda", "MX-5 Miata", 2016, 145.2, false);

        testService.addRaceCar(testLaunchCar1);
        testService.addRaceCar(testLaunchCar2);

        RaceCar winner = testService.launchRaceCars(testLaunchCar1, testLaunchCar2);

        assertTrue(winner == testLaunchCar1 || winner == testLaunchCar2, "Launching race cars failed");
    }
    @org.junit.jupiter.api.Test
    void launchRaceCarsNegative() {
        RaceCar testLaunchCar1 = new RaceCar(1, "Toyota", "Supra", 2016, 185.7, true);
        RaceCar testLaunchCar2 = new RaceCar(2, "Mazda", "MX-5 Miata", 2016, 145.2, false);

        testService.addRaceCar(testLaunchCar1);
        testService.addRaceCar(testLaunchCar2);

        RaceCar winner = testService.launchRaceCars(testLaunchCar1, testLaunchCar2); // will throw null since car 1 has been launched

        assertNotNull(winner, "Launching race cars failed - null detected");
    }
}*/
