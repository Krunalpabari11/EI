interface EuropeanSocket {
    void provideElectricity();
}
interface AmericanPlug {
    void supplyPower();
}
class EuropeanSocketImpl implements EuropeanSocket {
    @Override
    public void provideElectricity() {
        System.out.println("Providing 220V electricity from European socket.");
    }
}
class AmericanPlugImpl implements AmericanPlug {
    @Override
    public void supplyPower() {
        System.out.println("Supplying power from American plug.");
    }
}
class PlugAdapter implements EuropeanSocket {
    private AmericanPlug americanPlug;

    public PlugAdapter(AmericanPlug americanPlug) {
        this.americanPlug = americanPlug;
    }
    @Override
    public void provideElectricity() {
        System.out.println("Adapter is converting American plug to European socket...");
        americanPlug.supplyPower();
        System.out.println("Power has been adapted to 220V.");
    }
}
class ElectricalDevice {
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

public class AdapterPatternDemo {
    public static void main(String[] args) {
        System.out.println("Scenario 1: European Device with European Socket");
        EuropeanSocket europeanSocket = new EuropeanSocketImpl();
        ElectricalDevice europeanDevice = new ElectricalDevice(europeanSocket);
        europeanDevice.powerOn();
        System.out.println("\n------------------------\n");
        System.out.println("Scenario 2: European Device with American Plug (using Adapter)");
        AmericanPlug americanPlug = new AmericanPlugImpl();
        EuropeanSocket adapter = new PlugAdapter(americanPlug);
        ElectricalDevice adaptedDevice = new ElectricalDevice(adapter);
        adaptedDevice.powerOn();
    }
}