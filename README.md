Coverage: 34%
# Inventory Management System Project
This project involved the design and creation of an Inventory Management System with which order, product and customer information can be aggregated and stored in a database.. This system employs CRUD functionality to interact with a MySql database (either local or remote). Hence it can be run on a Google Cloud Platform instance, aswell as cmd line and within the Eclipse IDE itself. 

## Getting Started
These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. See deployment for notes on how to deploy the project on a live system.

### Prerequisites
Maven

MySQL WORKBENCH

Maven

MySQL

Git 

#### Maven Dependencies:
Mockito Core

JUnit 

Aoache Commons IO

Appache Log4j

MySQL Connector

### Installing
1. Fork and then clone down this repo to your system.
2. Open your Eclipse IDE.
3. Then click on File and select Import.
4. In the Import window search or scroll for the Maven Folder.
5. Open the Folder and select Existing Maven Projects.
6. Click browse next to root directory and locate this repo in your system.
7. Then click Finish.
8. Finally the IP address of your own MySQL instance must be configured in the Ims class within the variable
IP_Address.
9. The application is now ready to deploy and test.

## Running the tests
To run the tests and view test coverage % for each class, right click on the ims project
and select coverage as, then select JUnit Test.

### Unit Tests 
Unit testing is a testing approach that targets the very fundamental building blocks of an application, the idea is to prove 
that each 'unit' of the application is functioning as expected. Moreover, Unit testing is an automated process, which is designed to be reusable and provide reliable results.
Lastly, the idea of unit testing is to ensure that our unit functions in isolation, this means we want to check that the performed actions of that unit/class meet our expectations.

* **Unit Testing Example:**
```
public class ItemTest {

	private Item item;
	private Item other;

	@Before
	public void setUp() {
		item = new Item(1L, "table", 50L, 20L);
		other = new Item(1L, "table", 50L, 20L);
	}

	@Test
	public void settersTest() {
		assertNotNull(item.getItem_id());
		assertNotNull(item.getItem_name());
		assertNotNull(item.getPrice());
		assertNotNull(item.getStock());

		item.setItem_id(null);
		assertNull(item.getItem_id());
		item.setItem_name(null);
		assertNull(item.getItem_name());
		item.setPrice(null);
		assertNull(item.getPrice());
		item.setStock(null);
		assertNull(item.getStock());

	}

	@Test
	public void equalsWithNull() {
		assertFalse(item.equals(null));
	}

	@Test
	public void equalsWithDifferentObject() {
		assertFalse(item.equals(new Object()));
	}

	@Test
	public void createItemWithId() {
		assertEquals(1, item.getItem_id(), 0);
		assertEquals("table", item.getItem_name());
		assertEquals(50, item.getPrice(), 0);
		assertEquals(20, item.getStock(), 0);
	}

```
### Integration Tests 
Integration testing is a testing approach that targets the very fundamental building blocks of an application,
the idea is to prove that each 'integration' of the application is functioning as expected.

* **Integration Test Example:**
```
@RunWith(MockitoJUnitRunner.class)
public class OrderControllerTest {

	@Mock
	private OrderServices orderServices;

	@Spy
	@InjectMocks
	private OrderController orderController;

	@Test
	public void readAllTest() {
		OrderController orderController = new OrderController(orderServices);
		List<Order> orders = new ArrayList<>();
		orders.add(new Order(1L, 1l, 20L, 10L));
		orders.add(new Order(2L, 2L, 30L, 20L));
		orders.add(new Order(3L, 3L, 40L, 20L));
		Mockito.when(orderServices.readAll()).thenReturn(orders);
		assertEquals(orders, orderController.readAll());
	}

	@Test
	public void deleteTest() {
		String Order_id = "1";
		Mockito.doReturn(Order_id).when(orderController).getInput();
		orderController.delete();
		Mockito.verify(orderServices, Mockito.times(1)).delete(1L);
	}

}

```

## Deployment
1. Firstly you must ensure that your local or remote MySQL instance IP has been correctly configured in the ims class.
2. Save the project, then open a command line instance in the project folder.
3. Then insert the maven clean package command into command line: *$ mvn clean package*
4. Then the directory must be set to targer via the command: *$ cd target*
5. Finally the application can be run using the command: *$ java -jar Andi-ims-0.0.1-jar-with-dependencies.jar*

## Built With

* [Maven](https://maven.apache.org/) - Dependency Management

## Versioning

We use [SemVer](http://semver.org/) for versioning.

## Authors

* **Chris Perrins** - *Initial work* - [christophperrins](https://github.com/christophperrins)
* **Andi Berisha** - *Everything after Initial Work*

## License

This project is licensed under the MIT license - see the [LICENSE.md](LICENSE.md) file for details 

*For help in [Choosing a license](https://choosealicense.com/)*

## Acknowledgments
* **Aswene Sivaraj**
* **Vinesh Ghela**
* **Nicholas Johnson**
* **Hat tip to anyone whose code was used**

