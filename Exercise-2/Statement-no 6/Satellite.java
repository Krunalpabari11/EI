public class Satellite {
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
