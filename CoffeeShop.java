
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;

import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.LinearGradient;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.util.Duration;

/*THIS IS THE CLASS CREATED BY Eflame-01. 
 * IT USES THE FOLLOWING FILES CREATED BY HIS PROFESSOR:
 *	
 *	CoffeeDecorator()
 *	Coffee()
 *
 *	BasicCoffee()
 *	Cream()
 *	ExtraShot()
 *	Sugar()
*/

public class CoffeeShop extends Application {
	//This is for the fade in animations
	public FadeTransition fade = new FadeTransition();
	DropShadow shadow = new DropShadow();
	
	//These are for the layouts that will show the options and price
	public VBox storeLayout;
	public ScrollPane priceLayout;
	
	//These are for the pictures that will be displayed on the layout
	public final String COFFEE_BACKGROUND_IMAGE = "https://clipartart.com/images/coffee-icon-clipart-9.png";
	public final String JAVA_LOGO_URL = "https://cdn0.iconfinder.com/data/icons/flat-round-system/512/java-512.png";
	public final String JAVA_ICON_URL = "https://logos-download.com/wp-content/uploads/2016/10/Java_logo_icon.png";
	public final String CREAM_ICON_URL = "https://static.thenounproject.com/png/2164693-200.png";
	public final String SUGAR_ICON_URL = "https://static.thenounproject.com/png/637530-200.png";
	
	public final String CANCEL_ICON = "https://pluspng.com/img-png/png-wrong-cross-cancel-cross-exit-no-not-allowed-stop-wrong-icon-icon-512.png";
	public final String CONFIRM_ICON = "https://www.iconhot.com/icon/png/quiet/256/yes.png";
	
	public ImageView backgroundImageView = getImageView(new ImageView(COFFEE_BACKGROUND_IMAGE), 600, 600, true);
	
	//These are the different fonts that the layouts will use
	public Font titleFont = new Font("Gill Sans", 50);
	public Font textFont = new Font("Gill Sans", 20); 
	public Font smallerFont = new Font("Gill Sans", 15);
	
	//These are the variables that will store the customer's order and price
	public static ArrayList<Coffee> fullCustomerOrder = new ArrayList<Coffee>();
	public static Coffee customerOrder = null;
	public static String price = "Price:\n";

	//main
	public static void main(String[] args) {
		launch(args);
	}

	//start of the code. This has the logo and the options 
	//to make an order or exit.
	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setResizable(false);
		primaryStage.setTitle("Who want's coffee!!!");
		primaryStage.getIcons().add(new Image(JAVA_LOGO_URL));
		
		//Create a backgroundLayout to show the background 
		//image and color
		StackPane backGroundImageLayout = new StackPane();
		backGroundImageLayout.setStyle("-fx-background-color: burlywood");
		
		backgroundImageView.setOpacity(0.30);
		
		BorderPane mainLayout = new BorderPane();
		//Main Layout will include 2 layouts:
		//-Center Layout: Displays title
		//-Bottom Layout: Displays options
		
		VBox centerLayout = new VBox();
		centerLayout.setSpacing(10);
		//Center Layout will have:
		//- the Java icon
		//- the title
		
		//Creating Java Icon
		StackPane imageLayout = new StackPane();
		ImageView javaLogoImageView = getImageView(new ImageView(JAVA_ICON_URL), 400, 200, true);
		imageLayout.getChildren().add(javaLogoImageView);
		
		//Creating Title
		StackPane titleLayout = new StackPane();
		Text titleText = new Text("Coffee Shop");
		titleText.setFill(LinearGradient.valueOf("from 0% 0% to 200% 100%, repeat, dodgerblue 0%, red 50%"));
		titleText.setFont(titleFont);
		titleLayout.getChildren().add(titleText);
		
		//Adding java Icon and title to centerLayout
		centerLayout.getChildren().addAll(imageLayout, titleLayout);
		centerLayout.setAlignment(Pos.CENTER);
		
		StackPane bottomLayout = new StackPane();
		bottomLayout.setPrefSize(600, 200);
		//The bottomLayout will have 2 buttons:
		//- StartOrder | Exit 
		
		//Creating both buttons
		HBox optionLayout = new HBox();
		optionLayout.setSpacing(10);
		Button makeOrderButton = new Button("Start Order");
		makeOrderButton.setStyle("-fx-base: chocolate");
		makeOrderButton.setOnAction(e -> {
			initPriceLayout();
			initStoreLayout(primaryStage);
			displayLayout(primaryStage);
		});
		Button exitButton = new Button("Exit");
		exitButton.setStyle("-fx-base: firebrick");
		exitButton.setOnAction(e -> {
			primaryStage.close();
		});
		optionLayout.getChildren().addAll(makeOrderButton, exitButton);
		optionLayout.setAlignment(Pos.CENTER);
		
		//Adding buttons to bottomLayout
		bottomLayout.getChildren().add(optionLayout);
		
		//Add centerLayout and bottomLayout to the mainLayout
		mainLayout.setCenter(centerLayout);
		mainLayout.setBottom(bottomLayout);
		mainLayout.autosize();
		
		fadeIn(3000, mainLayout);
		
		backGroundImageLayout.getChildren().addAll(backgroundImageView, mainLayout);
		backGroundImageLayout.autosize();
		
		//Set the scene and show it
		Scene scene = new Scene(backGroundImageLayout,600,600);
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	//This will display the layout. Every time a scene is updated,
	//the layout will change and will need to show the changes
	public void displayLayout(Stage window) {
		//Create a backgroundLayout to show the background image and color
		StackPane backgroundLayout = new StackPane();
		backgroundLayout.setStyle("-fx-background-color: burlywood");
		
		BorderPane mainLayout = new BorderPane();
		//mainLayout will have the following Layouts:
		//- storeLayout: store options
		//- priceLayout: receipt of order
		
		
		//add the store and price layout to the mainLayout
		mainLayout.setCenter(storeLayout);
		mainLayout.setRight(priceLayout);
		
		backgroundLayout.getChildren().addAll(backgroundImageView, mainLayout);
		
		//add mainLayout to the scene and display it
		Scene scene = new Scene(backgroundLayout, 600, 600);
		window.setScene(scene);
		window.show();
	}
	
	//Initialize layout methods
	public void initStoreLayout(Stage window) {
		storeLayout = new VBox();
		storeLayout.setSpacing(100);
		//in this function the storeLayout will have:
		//- Question: What would you like to order?
		//- Buttons: |Make Coffee| |Continue| |Cancel Entire Order|
		
		//Creating Question
		Text question = new Text("What would you like to order?");
		question.setFont(textFont);
		question.setWrappingWidth(200);
		question.setTextAlignment(TextAlignment.CENTER);
		
		//Creating Buttons
		HBox optionLayout = new HBox();
		//This HBox will contain the buttons:
		//- Make Coffee: start your order
		//- Continue: If you already have a order, it will
		//	show you how much you owe in total.
		//- Cancel Entire Order: This takes you back to the 
		//	start page whether or not you have any orders
		optionLayout.setSpacing(10);
		Button makeCoffeeButton = new Button("Make Coffee");
		makeCoffeeButton.setStyle("-fx-base: chocolate");
		makeCoffeeButton.setOnAction(e -> {
			customerOrder = new BasicCoffee();
			initPriceLayout();
			wouldYouLikeAnExtraShot(window);
		});
		Button continueButton = new Button("Check Out");
		continueButton.setStyle("-fx-base: green");
		continueButton.setOnAction(e -> {
			displayTotal(window);
		});
		Button cancelOrderButton = new Button("Cancel Entire Order");
		cancelOrderButton.setStyle("-fx-base: firebrick");
		cancelOrderButton.setOnAction(e -> {
			try {
				fullCustomerOrder.clear();
				start(window);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		});
		if(fullCustomerOrder.isEmpty() && customerOrder == null) {
			continueButton.setDisable(true);
		}
		optionLayout.getChildren().addAll(makeCoffeeButton, continueButton, cancelOrderButton);
		optionLayout.setAlignment(Pos.CENTER);
		
		//add the question and the option to the store layout
		storeLayout.getChildren().addAll(question, optionLayout);
		storeLayout.setAlignment(Pos.CENTER);
		
		fadeIn(500, storeLayout);
		
		//display the new layout
		displayLayout(window);
	}
	public void initPriceLayout() {
		priceLayout = new ScrollPane();
		
		StackPane backgroundText = new StackPane();
		backgroundText.setPrefSize(200, 600);
		backgroundText.autosize();
		backgroundText.setStyle("-fx-background-color: beige");
		
		Text text = new Text("CUSTOMER COPY");
		text.setRotate(45);
		text.setFont(new Font("Gills Sans", 25));
		text.setFill(Color.WHITE);
		text.setStroke(Color.BLACK);
		text.setStrokeWidth(1);
		text.setOpacity(0.5);
		
		
		VBox receiptLayout = new VBox();
		receiptLayout.setPrefSize(200, 600);
		//receiptLayout.setStyle("-fx-background-color: beige");
		//receiptLayout will have one thing:
		//- Text that displays the items they bought and the price.
		
		//reset price and get the cost
		price = "Price:\n";
		String cost = Double.toString(getCost());
		
		//make the text that displays the price
		Text price = new Text(CoffeeShop.price + "Total: " + cost);
		price.setWrappingWidth(190);
		price.setFont(textFont);
		
		//add the text to the layout
		//(it is adding the Text price and not the String price)
		receiptLayout.getChildren().add(price);
		receiptLayout.setAlignment(Pos.TOP_CENTER);
		
		backgroundText.getChildren().addAll(text, receiptLayout);
		
		//add the receipt to the priceLayout
		priceLayout.setContent(backgroundText);
		priceLayout.setHbarPolicy(ScrollBarPolicy.NEVER);
		priceLayout.setStyle("-fx-base: beige");
	}
	
	//These two functions will ask what they want on their coffee
	public void wouldYouLikeAnExtraShot(Stage window) {
		storeLayout = new VBox();
		storeLayout.setSpacing(100);
		//storeLayout will have 2 things:
		//- Question: Would you like an extra shot in your coffee?
		//- Buttons: Yes | No
		
		//Creating question
		Text question = new Text("Would you like an extra shot in your coffee?");
		question.setWrappingWidth(200);
		question.setFont(textFont);
		question.setTextAlignment(TextAlignment.CENTER);
		
		//Creating Buttons
		//The Buttons will do the following:
		//- Yes: add the extra shot price to the total and 
		//	move on to the next item choice
		//- No: move on to the next item choice
		HBox optionLayout = new HBox();
		optionLayout.setSpacing(10);
		Button yesButton = new Button("Yes");
		yesButton.setGraphic(getImageView(new ImageView(CONFIRM_ICON), 10, 10, true));
		yesButton.setOnAction(e -> {
			handleExtraShot(window);
			whatWouldYouLikeOnYourCoffee(window);
		});
		Button noButton = new Button("No");
		noButton.setGraphic(getImageView(new ImageView(CANCEL_ICON), 10, 10, true));
		noButton.setOnAction(e -> {
			whatWouldYouLikeOnYourCoffee(window);
		});
		optionLayout.getChildren().addAll(yesButton, noButton);
		optionLayout.setAlignment(Pos.CENTER);
		
		//adding the question and the buttons to the storeLayout
		storeLayout.getChildren().addAll(question, optionLayout);
		storeLayout.setAlignment(Pos.CENTER);
		
		fadeIn(500, storeLayout);
		
		//display the new layout
		displayLayout(window);
	}
	public void whatWouldYouLikeOnYourCoffee(Stage window) {
		storeLayout = new VBox();
		storeLayout.setSpacing(50);
		//The storeLayout will have the following:
		//- Question: What would you like on your coffee?
		//- Text: (Click on the text to add it to your coffee)
		//- Images: Sugar | Cream
		//- Buttons: | Cancel | Confirm | CancelOrder |
		
		//Create the question and text
		VBox textLayout = new VBox();
		textLayout.setSpacing(10);
		
		Text question = new Text("What would you like on your coffee?");
		question.setWrappingWidth(200);
		question.setFont(textFont);
		question.setTextAlignment(TextAlignment.CENTER);
		
		Text text = new Text("(Click on the text to add it to your coffee.)");
		text.setWrappingWidth(200);
		text.setFont(smallerFont);
		text.setTextAlignment(TextAlignment.CENTER);
		
		textLayout.getChildren().addAll(question, text);
		textLayout.setAlignment(Pos.TOP_CENTER);
		
		//Create the Images 
		//These images have different functions:
		//- Sugar: On clicking on the text, sugar will be added
		//	to their total.
		//- Cream: On clicking on the text, cream will be added 
		//	to their total.
		HBox addOnLayout = new HBox();
		addOnLayout.setSpacing(100);
		
		shadow.setOffsetY(1.0);
		shadow.setColor(Color.rgb(84, 42, 12));
		
		VBox sugarLayout = new VBox();
		ImageView sugarImageView = getImageView(new ImageView(SUGAR_ICON_URL), 100, 100, true);
		sugarImageView.setOnMouseClicked(e -> {
			handleSugar(window);
		});
		Text sugarText = new Text("Sugar");
		sugarText.setFont(textFont);
		sugarText.setOnMouseClicked(e -> {
			handleSugar(window);
		});
		sugarText.setOnMouseEntered(e -> {
			sugarText.setEffect(shadow);
		});
		sugarText.setOnMouseExited(e -> {
			sugarText.setEffect(null);
		});
		sugarLayout.getChildren().addAll(sugarImageView, sugarText);
		sugarLayout.setAlignment(Pos.CENTER);
		
		VBox creamLayout = new VBox();
		ImageView creamImageView = getImageView(new ImageView(CREAM_ICON_URL), 100, 100, true);
		creamImageView.setOnMouseClicked(e -> {
			handleCream(window);
		});
		Text creamText = new Text("Cream");
		creamText.setFont(textFont);
		creamText.setOnMouseClicked(e -> {
			handleCream(window);
		});
		creamText.setOnMouseEntered(e -> {
			creamText.setEffect(shadow);
		});
		creamText.setOnMouseExited(e -> {
			creamText.setEffect(null);
		});
		creamLayout.getChildren().addAll(creamImageView, creamText);
		creamLayout.setAlignment(Pos.CENTER);
		
		addOnLayout.getChildren().addAll(sugarLayout, creamLayout);
		addOnLayout.setAlignment(Pos.CENTER);
		
		//Creating the Buttons
		//These buttons will have the following features:
		//- Confirm: This will add this order to the ArrayList.
		//- Cancel Order: This will delete this order (and wont be added)
		HBox optionLayout = new HBox();
		optionLayout.setSpacing(10);
		Button confirmButton = new Button("Confirm");
		confirmButton.setGraphic(getImageView(new ImageView(CONFIRM_ICON), 10, 10, true));
		confirmButton.setOnAction(e -> {
			fullCustomerOrder.add(customerOrder);
			customerOrder = null;
			initPriceLayout();
			initStoreLayout(window);
		});
		Button cancelOrderButton = new Button("Cancel Order");
		cancelOrderButton.setGraphic(getImageView(new ImageView(CANCEL_ICON), 10, 10, true));
		cancelOrderButton.setOnAction(e -> {
			customerOrder = null;
			initPriceLayout();
			initStoreLayout(window);
		});
		optionLayout.getChildren().addAll(confirmButton, cancelOrderButton);
		optionLayout.setAlignment(Pos.CENTER);
		
		//add the question, pictures, and buttons to the storeLayout
		storeLayout.getChildren().addAll(textLayout, addOnLayout, optionLayout);
		storeLayout.setAlignment(Pos.CENTER);
		
		fadeIn(500, storeLayout);
		
		//display the new layout
		displayLayout(window);
	}
	
	//This method will display the total price
	public void displayTotal(Stage window) {
		storeLayout = new VBox();
		storeLayout.setSpacing(100);
		//This storeLayout will have the following
		//- Text: Your total price is (insert_price_here).
		//- Button: Start Over
		
		//creating text
		String price = Double.toString(getCost());
		Text totalText = new Text("Your total is: " + price + ".");
		totalText.setWrappingWidth(200);
		totalText.setFont(textFont);
		
		//creating button
		Button startOver = new Button("Start Over");
		startOver.setStyle("-fx-base: chocolate");
		startOver.setOnAction(e -> {
			fullCustomerOrder.clear();
			customerOrder = null;
			try {
				start(window);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		});
		
		//add the text and button to the storeLayout
		storeLayout.getChildren().addAll(totalText, startOver);
		storeLayout.setAlignment(Pos.CENTER);
		
		fadeIn(500, storeLayout);
		
		//display the new layout
		displayLayout(window);
	}
	
	//helper functions that deal with pictures and transitions
	public ImageView getImageView(ImageView iv, double width, double height, boolean ratio) {
		iv.setFitWidth(width);
		iv.setFitHeight(height);
		iv.setPreserveRatio(ratio);
		iv.setSmooth(true);
		
		return iv;
	}
	public void fadeIn(long duration, Pane layout) {
		//Create a fade transition when they reach this layout
		fade.setDuration(Duration.millis(duration));
		fade.setFromValue(0.1);
		fade.setToValue(1);
		fade.setNode(layout);
		fade.play();
	}
	
	//Helper functions to get/update the cost of the order
	public void handleExtraShot(Stage window) {
		//add extra shot to the order and display the layout
		customerOrder = new ExtraShot(customerOrder);
		initPriceLayout();
		displayLayout(window);
	}
	public void handleSugar(Stage window) {
		//add sugar to the order and display layout
		customerOrder = new Sugar(customerOrder);
		initPriceLayout();
		displayLayout(window);
	}
	public void handleCream(Stage window) {
		//add cream to the order and display layout
		customerOrder = new Cream(customerOrder);
		initPriceLayout();
		displayLayout(window);
	}
	public double getCost() {
		//This function gets the total of the order and updates 
		//the price variable(through use of the makeCoffee() method)
		DecimalFormat df = new DecimalFormat("###.##");
		double cost = 0;
		
		if(!fullCustomerOrder.isEmpty()) {
			//This means the customer has ordered something in
			//the past. Get this price first
			for(Coffee order : fullCustomerOrder) {
				cost += order.makeCoffee();
			}
		}
		
		if(customerOrder != null) {
			//This means the person still has an order pending.
			//Get this price after checking their existing order.
			cost += customerOrder.makeCoffee();
		}
		
		
		//This is to round the double if it goes more than 3 decimal
		//points off
		df.setRoundingMode(RoundingMode.HALF_UP);
		df.format(cost);
		cost = Double.parseDouble(df.format(cost));
		
		return cost;
	}
	
	//You've reached the end of the CoffeeShop class!
}
