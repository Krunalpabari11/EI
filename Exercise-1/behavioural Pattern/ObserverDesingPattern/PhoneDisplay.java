public class PhoneDisplay implements Observer {
    @Override
    public void update(float temperature) {
        System.out.println("Phone Display: Current temperature is " + temperature + "°C");
    }
}
