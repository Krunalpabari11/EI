public class PlugAdapter implements EuropeanSocket {
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
