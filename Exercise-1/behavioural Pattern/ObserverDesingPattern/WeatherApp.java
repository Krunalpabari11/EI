public class WeatherApp implements Observer {
    @Override
    public void update(float temperature) {
        System.out.println("Weather App: Current temperature is " + temperature + "°C");
    }
}
