package tarea1;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;


import java.util.List;


import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;

import javafx.scene.chart.NumberAxis;
import javafx.scene.control.Button;

import javafx.scene.control.Label;

import javafx.scene.layout.HBox;



public class Main extends Application{
protected Red1 red1;
	
    @Override public void start(Stage stage) {
        stage.setTitle("JPlot");

        BorderPane pane = new BorderPane();

        HBox hbox = new HBox(10);
        hbox.setAlignment(Pos.CENTER_LEFT);

        
        

     

        Button drawButton = new Button("Draw");

        drawButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				pane.setCenter(buildPlot());
			}
		});

            

        hbox.getChildren().addAll(new Label("Plot Type:"),   drawButton);
        pane.setTop(hbox);

        Scene scene  = new Scene(pane,800,600);

        stage.setScene(scene);
        stage.show();
    }

    protected Node buildPlot() {
    	LinePlot<Number, Number> lineplot = new LinePlot<>(new NumberAxis(), new NumberAxis());
    	red1 = new Red1();
    	red1.build();
    	red1.loadstatics(100);
    	
        List<Number> x = red1.getdatos();
        List<Number> y = red1.getHits();

        lineplot.setTitle("Hits");
        lineplot.setXLabel("Learnig Num");
        lineplot.setYLabel("Hits");
        lineplot.addSeries(x, y, "Red1");
		return lineplot.getPlot();
	}

	public static void main(String[] args) {

		launch(args);
    }
}
