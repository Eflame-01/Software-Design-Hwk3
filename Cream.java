
public class Cream extends CoffeeDecorator{

	private double cost = .50;
	
	Cream(Coffee specialCoffee){
		super(specialCoffee);
	}
	
	public double makeCoffee() {
		return specialCoffee.makeCoffee() + addCream();
	}
	
	private double addCream() {
		
		CoffeeShop.price = CoffeeShop.price.concat(" + cream: $.50\n");
		
		return cost;
	}
}
