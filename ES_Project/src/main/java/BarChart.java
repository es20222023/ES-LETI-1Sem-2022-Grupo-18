import javax.swing.JFrame;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

public class BarChart extends JFrame {

	private static final long serialVersionUID = 1L;
	private DefaultCategoryDataset dataset = new DefaultCategoryDataset();
	
	public BarChart(String appTitle) {
		super(appTitle);
		// Create chart
		JFreeChart chart = ChartFactory.createBarChart("Percentagem de Ocupação diária", // Chart Title
				"User", // Category axis
				"Percentagem de ocupação do dia", // Value axis
				dataset, PlotOrientation.VERTICAL, true, true, false);

		ChartPanel panel = new ChartPanel(chart);
		setContentPane(panel);
	}

	public void addToDataSet(double value, String user, String day) {
		dataset.addValue(value, user, day);
	}
}
