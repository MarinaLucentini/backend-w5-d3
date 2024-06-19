package marinalucentini.backend_w5_d3;

import marinalucentini.backend_w5_d3.entities.*;
import marinalucentini.backend_w5_d3.enums.OrderStatus;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.internal.matchers.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.time.LocalTime;
import java.util.Arrays;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BackendW5D3ApplicationTests {
@Autowired
	private Menu menu;
@Autowired
AnnotationConfigApplicationContext context;

	@Test
	void contextLoads() {
		assertNotNull(menu);
		assertNotNull(context);
	}
	@Test
void generateOrderAndVerificatedTotal(){
		Pizza pizza = context.getBean("pizzaMargherita", Pizza.class);
		double coperto = context.getBean("coperto", double.class);
		Drink water = context.getBean("Water", Drink.class);
		Topping topping = context.getBean(Topping.class);
		Pizza pizza2 = context.getBean("pizzaHawaiana", Pizza.class);
		Order order = new Order(1, Arrays.asList(pizza, pizza2, water, topping), OrderStatus.SERVED, 2, LocalTime.of(15,20));
		order.total(coperto);
		assertNotNull(order);
		assertNotSame(pizza, pizza2);
		assertEquals(16.97, order.total(coperto));
	}
	@ParameterizedTest
	@CsvSource({"2, 10.97", "4, 14.97"})
	void testTotalOrder(double coperto, double expectedResult){
		Pizza pizza = context.getBean("pizzaMargherita", Pizza.class);
		Drink water = context.getBean("Water", Drink.class);
		Topping topping = context.getBean(Topping.class);
		Order order = new Order(1, Arrays.asList(pizza, water, topping), OrderStatus.SERVED, 2, LocalTime.of(15,20));
		assertEquals(expectedResult, order.total(coperto));
	}
	@ParameterizedTest
	@CsvSource({"2, 10.97, pizzaMargherita", "2, 11.98, pizzaHawaiana"})
	void testTotalOrder2(double coperto, double expectedResult, String namePizza){
		Pizza pizza = context.getBean(namePizza, Pizza.class);
		Drink water = context.getBean("Water", Drink.class);
		Topping topping = context.getBean(Topping.class);
		Order order = new Order(1, Arrays.asList(pizza, water, topping), OrderStatus.SERVED, 2, LocalTime.of(15,20));
		assertEquals(expectedResult, order.total(coperto));
	}
}
