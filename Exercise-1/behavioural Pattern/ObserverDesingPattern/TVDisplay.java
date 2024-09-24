public class TVDisplay implements Observer {
    @Override
    public void update(float temperature) {
        System.out.println("TV Display: Current temperature is " + temperature + "°C");
    }
}
