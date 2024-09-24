interface Coffee {
    String getDescription();
    double cost();
}
class SimpleCoffee implements Coffee {
    @Override
    public String getDescription() {
        return "Simple Coffee";
    }
    @Override
    public double cost() {
        return 2.00; 
    }
}
abstract class CoffeeDecorator implements Coffee {
    protected Coffee decoratedCoffee;

    public CoffeeDecorator(Coffee coffee) {
        this.decoratedCoffee = coffee;
    }
    @Override
    public String getDescription() {
        return decoratedCoffee.getDescription();
    }
    @Override
    public double cost() {
        return decoratedCoffee.cost();
    }
}
class MilkDecorator extends CoffeeDecorator {
    public MilkDecorator(Coffee coffee) {
        super(coffee);
    }
    @Override
    public String getDescription() {
        return decoratedCoffee.getDescription() + ", Milk";
    }
    @Override
    public double cost() {
        return decoratedCoffee.cost() + 0.50; 
    }
}
class SugarDecorator extends CoffeeDecorator {
    public SugarDecorator(Coffee coffee) {
        super(coffee);
    }
    @Override
    public String getDescription() {
        return decoratedCoffee.getDescription() + ", Sugar";
    }
    @Override
    public double cost() {
        return decoratedCoffee.cost() + 0.25; 
    }
}
class WhippedCreamDecorator extends CoffeeDecorator {
    public WhippedCreamDecorator(Coffee coffee) {
        super(coffee);
    }
    @Override
    public String getDescription() {
        return decoratedCoffee.getDescription() + ", Whipped Cream";
    }
    @Override
    public double cost() {
        return decoratedCoffee.cost() + 0.75; 
    }
}
public class DecoratorPatternDemo {
    public static void main(String[] args) {
        Coffee coffee = new SimpleCoffee();
        System.out.println("Description: " + coffee.getDescription());
        System.out.println("Cost: $" + coffee.cost());
        coffee = new MilkDecorator(coffee);
        System.out.println("\nAfter adding milk:");
        System.out.println("Description: " + coffee.getDescription());
        System.out.println("Cost: $" + coffee.cost());
        coffee = new SugarDecorator(coffee);
        System.out.println("\nAfter adding sugar:");
        System.out.println("Description: " + coffee.getDescription());
        System.out.println("Cost: $" + coffee.cost());
        coffee = new WhippedCreamDecorator(coffee);
        System.out.println("\nAfter adding whipped cream:");
        System.out.println("Description: " + coffee.getDescription());
        System.out.println("Cost: $" + coffee.cost());
    }
}
