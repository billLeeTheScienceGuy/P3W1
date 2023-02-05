//
//import java.awt.image.BufferedImage;
//import java.io.FileInputStream;
//import java.io.FileNotFoundException;
//import java.io.IOException;
//import java.io.InputStream;
//import java.net.MalformedURLException;
//import java.net.URL;
//import java.util.List;
//import java.util.Scanner;
//
//import javax.imageio.ImageIO;
//import javax.swing.ImageIcon;
//import javax.swing.JFrame;
//import javax.swing.JLabel;
//
//import javafx.application.Application;
//import javafx.application.Platform;
//import javafx.geometry.Pos;
//import javafx.scene.Group;
//import javafx.scene.Scene;
//import javafx.scene.control.Button;
//import javafx.scene.image.Image;
//import javafx.scene.image.ImageView;
//import javafx.scene.layout.BorderPane;
//import javafx.scene.layout.GridPane;
//import javafx.scene.layout.HBox;
//import javafx.scene.layout.Pane;
//import javafx.stage.Stage;

import javafx.application.*;

import javafx.collections.FXCollections;
import javafx.scene.Scene;

import javafx.scene.control.Button;

import javafx.scene.control.ComboBox;

import javafx.scene.control.Label;

import javafx.scene.image.Image;

import javafx.scene.image.ImageView;

import javafx.scene.input.MouseEvent;

import javafx.scene.layout.Background;

import javafx.scene.layout.BackgroundFill;

import javafx.scene.layout.BorderPane;

import javafx.scene.layout.GridPane;

import javafx.scene.layout.HBox;

import javafx.scene.layout.VBox;

import javafx.scene.paint.Color;

import javafx.scene.text.Font;

import javafx.scene.text.FontPosture;

import javafx.scene.text.FontWeight;

import javafx.scene.text.Text;

import javafx.scene.text.TextAlignment;

import javafx.geometry.*;

import javafx.stage.*;

import java.io.FileInputStream;

import java.io.FileNotFoundException;

import java.nio.file.Path;

import java.util.ArrayList;
import java.util.List;

import javafx.event.*;

public class FlightPathFrontend extends Application{
	private static String Locations [];
	
	public static ICity start;

	public ICity end;
	public CityLoader cLoader;
	private IFlightPathBackend backend;

	private ArrayList<String> route; 
	
	@Override

	public void start(Stage stage) throws FileNotFoundException {
		int state = 0;
		
		BorderPane root = new BorderPane();

		VBox box = new VBox();

		HBox subtext = new HBox();

		HBox interact = new HBox();

		Scene scene = new Scene(root, 800, 600, Color.RED);
		
		Image logo = new Image(new FileInputStream("C:/Users/byeon/Downloads/background2.jpg"));

		ImageView image = new ImageView(logo);

		image.setId("background");

		image.setFitHeight(715);

		image.setFitWidth(670);

		image.setOpacity(0.1);

		root.setCenter(image);

		root.setAlignment(image, Pos.CENTER);
		
		root.setTop(box);

		box.setAlignment(Pos.BASELINE_CENTER);

		Text title_text = new Text("Welcome to Bucky Flight Path Program\nPlease select a starting point.");
		
		title_text.setId("title");

		title_text.setFont(Font.font("Times New Roman", FontWeight.SEMI_BOLD, FontPosture.ITALIC, 50));
		
		Button cities[] = new Button[8];
		for(int i = 0; i < 9; i++) {
			Button city = new Button();

			double xPosition = backend.loadCity().getX();
			double yPosition = backend.loadCity().getY();
			String title = backend.getCities.getName();
			xPosition = (xPosition +92)*120;
			yPosition = (yPosition -46)*149;
			city.setLayoutX(xPosition);
			city.setLayoutY(yPosition);
			city.setText(title);
			
			city.setOnAction(e->{
			
				start = (ICity) city;
				city.setOnAction(o->{
					end = (ICity) city;
					Button opOne = new Button();
					Button opTwo = new Button();
					Button opThr = new Button();
					opOne.setText("Path 1");
					opTwo.setText("Path 2");
					opThr.setText("Path 3");
					opOne.setLayoutX(200);
					opTwo.setLayoutX(320);
					opThr.setLayoutX(440);
					opOne.setLayoutY(90);
					opTwo.setLayoutY(90);
					opThr.setLayoutY(90);
					
					opOne.setOnAction(w->{
						moveBucky(start, end, 0);
					});
					opTwo.setOnAction(w->{
						moveBucky(start, end, 1);
					});
					opThr.setOnAction(w->{
						moveBucky(start, end, 2);
					});
				});
				
		});
			
			cities[i] = city;
		}
		box.getChildren().addAll(cities);
		
			
		box.getChildren().add(title_text);
		
		box.getChildren().add(subtext);

		// sets spacing, alignment 

		box.getChildren().add(interact);

		interact.setAlignment(Pos.CENTER);

		interact.setSpacing(100);
		
		
		Text text_path = new Text();

		text_path.setFont(Font.font("Times New Roman", FontWeight.SEMI_BOLD, FontPosture.REGULAR, 24));

		text_path.setId("path");

		box.getChildren().add(text_path);
		
		
		// exit button

		Button exit = new Button("exit");

		exit.setId("exit");

		box.getChildren().add(exit);

		exit.setAlignment(Pos.BOTTOM_RIGHT);

			stage.setScene(scene);

			stage.setTitle("Bucky Flight Path");

			stage.show();

			}



			private ICity moveBucky(ICity start2, ICity end2, int path) {
		backend.setOrigin(start2);
		backend.setDestination(end2);
		List<ICity> pathNum[] = backend.getPaths();
		ICity chosenPath = (ICity) pathNum[path];
		
	}



			public static void main(String[] args) {


			Application.launch();
		
		
}

}