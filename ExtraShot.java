
public class ExtraShot extends CoffeeDecorator {

	private double cost = 1.20;
	
	ExtraShot(Coffee specialCoffee){
		super(specialCoffee);
	}
	
	public double makeCoffee() {
		return specialCoffee.makeCoffee() + addShot();
	}
	
	private double addShot() {
		
		CoffeeShop.price = CoffeeShop.price.concat(" + extra shot: $1.20\n");
		
		return cost;
	}
}
