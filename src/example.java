import javafx.application.*;

import javafx.collections.FXCollections;

import javafx.event.EventHandler;

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



import javafx.event.ActionEvent;

import javafx.event.*;

public class example extends Application {




private static String locations []; 

public static String start;

public String end;

private IFlightPathBackend backend;

private ArrayList<String> route; 



@Override

public void start(Stage stage) throws FileNotFoundException {


// set locations for testing


String[] test_locations = {"Bascom Hall", "Nicholas Recreation Center", "Camp Randall", "College Library"};

locations = test_locations;


BorderPane root = new BorderPane();

VBox UI = new VBox();

HBox subtext = new HBox();

HBox interactives = new HBox();

Scene scene = new Scene(root, 800, 600, Color.RED);


// Background Image //


Image logo = new Image(new FileInputStream("/Users/georgemavroghenis/Documents/Wisconsin/CS400/Programs/P3FrontendDeveloper/images/Wisconsin_Badgers_knockout_logo_red_tm.svg.png"));

ImageView image = new ImageView(logo);

image.setId("background");

image.setFitHeight(215);

image.setFitWidth(205);

image.setOpacity(0.1);

root.setCenter(image);

root.setAlignment(image, Pos.CENTER);


//** Title and UI Set Up **// 


root.setTop(UI);

UI.setAlignment(Pos.CENTER);

Text title_text = new Text("Madison Campus Route Planner");

title_text.setId("title");

title_text.setFont(Font.font("Times New Roman", FontWeight.SEMI_BOLD, FontPosture.ITALIC, 50));

UI.getChildren().add(title_text);



//** Button and Combo Box Set Up **//


// sets titles for combo boxes

UI.getChildren().add(subtext);

Text start_text = new Text("Start Location");

start_text.setId("startlabel");

Text end_text = new Text("End Location");

end_text.setId("endlabel");

subtext.getChildren().add(start_text);

subtext.getChildren().add(end_text);

subtext.setAlignment(Pos.CENTER);

subtext.setSpacing(220);

// sets spacing, alignment 

UI.getChildren().add(interactives);

interactives.setAlignment(Pos.CENTER);

interactives.setSpacing(100);


// sets combo boxes, adds to interactives 

String options[] = this.locations;

ComboBox start_locations = new ComboBox(FXCollections.observableArrayList(options));

start_locations.setId("start");

ComboBox end_locations = new ComboBox(FXCollections.observableArrayList(options));

end_locations.setId("end");

interactives.getChildren().add(start_locations);

interactives.getChildren().add(end_locations);




// sets button, add to interactives

Button go_button = new Button("GO");

go_button.setId("go");

UI.getChildren().add(go_button);



// output path

Text text_path = new Text();

text_path.setFont(Font.font("Times New Roman", FontWeight.SEMI_BOLD, FontPosture.REGULAR, 24));

text_path.setId("path");

UI.getChildren().add(text_path);




// reset button only visible when path is visible 


Button reset = new Button("reset");

reset.setId("reset");

UI.getChildren().add(reset);

reset.setAlignment(Pos.CENTER);

reset.setVisible(false);



//** Event Handlers **// 

start_locations.setOnAction((Event e) -> {

FrontendDeveloper.start = (String) start_locations.getValue();

});

 

end_locations.setOnAction((Event e) -> {

this.end = (String) end_locations.getValue();

});

 

go_button.setOnAction((ActionEvent e) -> {

 

if(start != null && end != null) {

start_locations.getSelectionModel().clearSelection();

end_locations.getSelectionModel().clearSelection();

// ArrayList<String> best_route = backend.best_route(start, end);

ArrayList<String> best_route = new ArrayList<String>();

start = null;

end = null;

String string_path = ""; 

for (int i  = 0; i < best_route.size(); i++) {

if(i == best_route.size() - 1) {

string_path = string_path + best_route.get(i);

} else {

string_path = string_path + best_route.get(i) + " --> ";

}

text_path.setText(string_path);


}

reset.setVisible(true);

 

}

});

 

reset.setOnAction((ActionEvent e) -> {

if(reset.isVisible()) {

text_path.setText("");

start = null;

end = null;

reset.setVisible(false);

}

 

});

 

stage.setScene(scene);

stage.setTitle("Madison Campus Route Planner");

stage.show();

}



public static void main(String[] args) {


Application.launch();

}



/**

* Sets the available locations for the drop-down menu (set by backend developer) 

* @param locations String of locations for the route planner

*/

public void set_locations(String [] locations) {

this.locations = locations;

}


/**

* Sets the best route -> only used for testing!

* @param route

*/

public void set_route(ArrayList<String> route) {

this.route = route;

}


public String get_start() {

return this.start;

}


public String get_end() {

return this.end;

}
}


