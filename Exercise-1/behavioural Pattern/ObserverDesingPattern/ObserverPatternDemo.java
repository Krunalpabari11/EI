import java.util.ArrayList;
import java.util.List;

class NewsAgency {
    private List<NewsChannel> observers = new ArrayList<>();
    private String news;
    public void attach(NewsChannel observer) {
        observers.add(observer);
    }
    public void detach(NewsChannel observer) {
        observers.remove(observer);
    }
    public void setNews(String news) {
        this.news = news;
        notifyObservers();
    }
    private void notifyObservers() {
        for (NewsChannel observer : observers) {
            observer.update(news);
        }
    }
}
interface NewsChannel {
    void update(String news);
}
class TVChannel implements NewsChannel {
    private String name;

    public TVChannel(String name) {
        this.name = name;
    }
    @Override
    public void update(String news) {
        System.out.println(name + " received news: " + news);
    }
}

public class ObserverPatternDemo {
    public static void main(String[] args) {
        NewsAgency agency = new NewsAgency();
        NewsChannel channel1 = new TVChannel("CNN");
        NewsChannel channel2 = new TVChannel("BBC");
        agency.attach(channel1);
        agency.attach(channel2);
        agency.setNews("Breaking: Major earthquake in California!");
        agency.detach(channel2);
        agency.setNews("Update: Rescue efforts underway.");
    }
}