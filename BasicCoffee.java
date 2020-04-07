
public class BasicCoffee implements Coffee {

	private double cost = 3.99;
	
	@Override
	public double makeCoffee() {

		CoffeeShop.price = CoffeeShop.price.concat("Black Coffee: $3.99\n");
		
		return cost;
	}

	

}
