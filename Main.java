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
protected RedNeuronal red1;
protected RedNeuronal red2;	
protected RedNeuronal red3;	
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
     	red2 = new Red2();
    	red2.build();
    	red2.loadstatics(100);
    	red3 = new Red3();
    	red3.build();
    	red3.loadstatics(100);
    	
        List<Number> x1 = red1.getdatos();
        List<Number> y1 = red1.getHits();
        List<Number> x2 = red2.getdatos();
        List<Number> y2 = red2.getHits();
        List<Number> x3 = red3.getdatos();
        List<Number> y3 = red3.getHits();
        lineplot.setTitle("Hits");
        lineplot.setXLabel("Learnig Num");
        lineplot.setYLabel("Hits");
        lineplot.addSeries(x1, y1, "Red 3c1o");
        lineplot.addSeries(x2, y2, "Red 2c1o");
        lineplot.addSeries(x3, y3, "Red 3c2o");
		return lineplot.getPlot();
	}

	public static void main(String[] args) {

		launch(args);
    }
}
