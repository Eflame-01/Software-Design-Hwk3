import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CoffeeDecoratorTest {
	Coffee order;
	ArrayList<Coffee> list;
	
	@BeforeEach
	void initCustomer() {
		order = CoffeeShop.customerOrder;
		list = CoffeeShop.fullCustomerOrder;
	}
	
	@Test
	void testIfCustomerOrderWasMade() {
		order = new BasicCoffee();
		assertEquals("BasicCoffee", order.getClass().getName(), "The customer order was not coffee.");
	}
	@Test
	void testIfCustomerOrderListWasMade() {
		assertEquals("java.util.ArrayList", list.getClass().getName(), "The customer list type was not java.util.ArrayList.");
	}
	
	@Test
	void testIfCustomerOrderIsNull() {
		assertNull(order, "The variable 'customerOrder' is susposed to be null because they did not order anything yet.");
	}
	
	@Test
	void testCustomerOrderListSize1() {
		assertEquals(0, list.size(), "The list should have 0 orders because they did not confirm an order yet.");
	}
	@Test
	void testCustomerOrderListSize2() {
		list.add(new BasicCoffee());
		
		assertEquals(1, list.size(), "The list should have 1 order because they ordered one item.");
	}
	
	
	@Test
	void testIfExtraShotWasAdded() {
		order = new ExtraShot(new BasicCoffee());
		
		assertEquals("ExtraShot", order.getClass().getName(), "The name does not equal ExtraShot");
	}
	@Test
	void testIfSugarWasAdded() {
		order = new Sugar(new BasicCoffee());
		
		assertEquals("Sugar", order.getClass().getName(), "The name does not equal Sugar");
	}
	@Test
	void testIfCreamWasAdded() {
		order = new Cream(new BasicCoffee());
		
		assertEquals("Cream", order.getClass().getName(), "The name does not equal Cream");
	}
	
	@Test
	void testIfPriceIsRight1() {
		order = new BasicCoffee();
		double cost = order.makeCoffee();
		
		assertEquals(3.99, cost, "The cost should be 3.99");
	}
	
	@Test
	void testIfPriceIsRight2() {
		order = new Cream(new Sugar(new ExtraShot(new BasicCoffee())));
		double cost = order.makeCoffee();
		
		assertEquals(6.19, cost, "The cost should be 3.99");
	}


}
