public class ElectricalDevice {
    private EuropeanSocket socket;

    public ElectricalDevice(EuropeanSocket socket) {
        this.socket = socket;
    }

    public void powerOn() {
        System.out.println("Electrical device is powering on...");
        socket.provideElectricity();
        System.out.println("Electrical device is now powered on.");
    }
}
