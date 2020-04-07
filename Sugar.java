
public class Sugar extends CoffeeDecorator{

	private double cost = .50;
	
	Sugar(Coffee specialCoffee){
		super(specialCoffee);
	}
	
	public double makeCoffee() {
		return specialCoffee.makeCoffee()+ addSugar();
	}
	
	private double addSugar() {
		
		CoffeeShop.price = CoffeeShop.price.concat(" + sugar: $.50\n");
		
		return cost;
	}
}
