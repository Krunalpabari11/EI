import java.util.Scanner;

interface Command {
    void execute();
}

class Satellite {
    private String orientation;
    private boolean solarPanelsActive;
    private int dataCollected;

    public Satellite() {
        this.orientation = "North";
        this.solarPanelsActive = false;
        this.dataCollected = 0;
    }

    public void setOrientation(String orientation) {
        this.orientation = orientation;
        System.out.println("Satellite orientation set to " + this.orientation);
    }

    public void activatePanels() {
        this.solarPanelsActive = true;
        System.out.println("Solar panels activated.");
    }

    public void deactivatePanels() {
        this.solarPanelsActive = false;
        System.out.println("Solar panels deactivated.");
    }

    public void collectData() {
        if (this.solarPanelsActive) {
            this.dataCollected += 10;
            System.out.println("Data collected: " + this.dataCollected + " units.");
        } else {
            System.out.println("Cannot collect data: Solar panels are inactive.");
        }
    }

    public void printStatus() {
        System.out.println("Current Satellite Status:");
        System.out.println("Orientation: " + this.orientation);
        System.out.println("Solar Panels Active: " + (this.solarPanelsActive ? "Yes" : "No"));
        System.out.println("Data Collected: " + this.dataCollected);
        System.out.println("--------------------------");
    }
}

class RotateCommand implements Command {
    private Satellite satellite;
    private String direction;

    public RotateCommand(Satellite satellite, String direction) {
        this.satellite = satellite;
        this.direction = direction;
    }

    @Override
    public void execute() {
        satellite.setOrientation(direction);
    }
}

class ActivatePanelsCommand implements Command {
    private Satellite satellite;

    public ActivatePanelsCommand(Satellite satellite) {
        this.satellite = satellite;
    }

    @Override
    public void execute() {
        satellite.activatePanels();
    }
}

class DeactivatePanelsCommand implements Command {
    private Satellite satellite;

    public DeactivatePanelsCommand(Satellite satellite) {
        this.satellite = satellite;
    }

    @Override
    public void execute() {
        satellite.deactivatePanels();
    }
}

class CollectDataCommand implements Command {
    private Satellite satellite;

    public CollectDataCommand(Satellite satellite) {
        this.satellite = satellite;
    }

    @Override
    public void execute() {
        satellite.collectData();
    }
}

class CommandInvoker {
    public void executeCommand(Command command) {
        command.execute();
    }
}


public class CommandPattern {
    public static void main(String[] args) {
        Satellite satellite = new Satellite();
        CommandInvoker invoker = new CommandInvoker();
        Scanner scanner = new Scanner(System.in);
        String command;

        System.out.println("Welcome to the Satellite Command System!");
        satellite.printStatus();

        while (true) {
            System.out.println("Enter a command (rotate, activatePanels, deactivatePanels, collectData, status, exit):");
            command = scanner.nextLine();

            switch (command.toLowerCase()) {
                case "rotate":
                    System.out.println("Enter direction (North, South, East, West):");
                    String direction = scanner.nextLine();
                    invoker.executeCommand(new RotateCommand(satellite, direction));
                    break;

                case "activatepanels":
                    invoker.executeCommand(new ActivatePanelsCommand(satellite));
                    break;

                case "deactivatepanels":
                    invoker.executeCommand(new DeactivatePanelsCommand(satellite));
                    break;

                case "collectdata":
                    invoker.executeCommand(new CollectDataCommand(satellite));
                    break;

                case "status":
                    satellite.printStatus();
                    break;

                case "exit":
                    System.out.println("Exiting Satellite Command System.");
                    scanner.close();
                    return;

                default:
                    System.out.println("Unknown command. Please try again.");
                    break;
            }

            satellite.printStatus();
        }
    }
}
