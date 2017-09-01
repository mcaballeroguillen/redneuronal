package tarea1;

import java.util.List;

import javafx.scene.chart.Axis;
import javafx.scene.chart.Chart;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;

public class LinePlot<T1, T2> {
	private Axis<T1> xAxis;
	private Axis<T2> yAxis;
	private Chart plot;

	public LinePlot(Axis<T1> xAxis, Axis<T2> yAxis){
		this.xAxis = xAxis;
		this.yAxis = yAxis;
		plot = new LineChart<T1, T2>(xAxis, yAxis);
	}

	public void addSeries(List<T1> xData, List<T2> yData){
		addSeries(xData, yData, "");
	}

	@SuppressWarnings("unchecked")
	public void addSeries(List<T1> xData, List<T2> yData, String seriesName){
		if(xData.size() != yData.size())
			throw new IllegalArgumentException("X and Y data must have the same size");
		XYChart.Series<T1, T2> series = new XYChart.Series<>();
		for(int i=0; i<xData.size(); i++){
			series.getData().add(new XYChart.Data<>(xData.get(i), yData.get(i)));
		}
		series.setName(seriesName);
		((XYChart<T1, T2>) plot).getData().add(series);
	}

	public void setTitle(String title){
		plot.setTitle(title);
	}

	public void setXLabel(String label){
		xAxis.setLabel(label);
	}

	public void setYLabel(String label){
		yAxis.setLabel(label);
	}

	public Chart getPlot(){
		plot.maxHeight(1.0);
		return plot;
	}
}
