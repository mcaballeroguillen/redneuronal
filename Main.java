package tarea1;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.chart.NumberAxis;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;


public class Main extends Application{
protected Red1 red1 = new Red1();
	
    @Override public void start(Stage stage) {
        stage.setTitle("JPlot");

        BorderPane pane = new BorderPane();

        HBox hbox = new HBox(10);
        hbox.setAlignment(Pos.CENTER_LEFT);

        FileChooser dataChooser = new FileChooser();
        Button openFileButton = new Button("Choose Data File");

        openFileButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				File file = dataChooser.showOpenDialog(stage);
				/**do things with file**/
			}
		});

        Button drawButton = new Button("Draw");

        drawButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				pane.setCenter(buildPlot());
			}
		});

        ObservableList<String> options =
        	    FXCollections.observableArrayList(
        	        "LinePlot Sample"
        	    );
        ComboBox<String> comboBox = new ComboBox<>(options);
        comboBox.setValue("LinePlot Sample");

        hbox.getChildren().addAll(new Label("Plot Type:"), comboBox, openFileButton, drawButton);
        pane.setTop(hbox);

        Scene scene  = new Scene(pane,800,600);

        stage.setScene(scene);
        stage.show();
    }

    protected Node buildPlot() {
    	LinePlot<Number, Number> lineplot = new LinePlot<>(new NumberAxis(), new NumberAxis());
    	red1.build();
    	red1.loadstatics(100);
    	
        List<Number> x = red1.getdatos();
        List<Number> y = red1.getHits();

        lineplot.setTitle("Line Plot Sample");
        lineplot.setXLabel("Number of month");
        lineplot.setYLabel("Millions of US$");
        lineplot.addSeries(x, y, "Portfolio #1");
		return lineplot.getPlot();
	}

	public static void main(String[] args) {

		launch(args);
    }
}
