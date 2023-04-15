package project;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.ArrayList;

import javax.swing.JButton;//new!
import javax.swing.JOptionPane;
import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.JScrollPane;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.DefaultCategoryDataset;




public class HousingPriceChartUI implements ActionListener{

	
	protected ArrayList<HousingPrice> data;
	HousingPriceDataFrames df;

	private JButton toggleView;
	//   private JTable table;
	private JScrollPane scrPane;

	
	public HousingPriceChartUI(ArrayList<HousingPrice> data) {
		this.data = data;    
		 this.df = new HousingPriceDataFrames(data);
		
	}

	public void displayChart() {
		// Create the data-set
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		getDataSet(dataset);
		// Create the chart
		JFreeChart chart = ChartFactory.createBarChart("Housing Prices", "Date", "Price", dataset);
		chart.setBackgroundPaint(Color.white);
		// Customize the plot
		CategoryPlot plot = (CategoryPlot) chart.getPlot();
		editPlot(plot);
		// Customize the X axis
		CategoryAxis xAxis = plot.getDomainAxis();
		// Customize the Y axis
		NumberAxis yAxis = (NumberAxis) plot.getRangeAxis();
		editXYaxis(xAxis, yAxis);
		// Customize the renderer
		BarRenderer renderer = (BarRenderer) plot.getRenderer();
		editRenderer(renderer);
		// Create the chart panel and display it in a frame
		ChartPanel chartPanel = new ChartPanel(chart);
		editChart(chartPanel);
		JFrame frame = new JFrame("Housing Prices");
		editFrame(frame, chartPanel);
		//create the button to show different outputs
		toggleView = createToggleView(frame);
		chartPanel.add(toggleView);
	}
public void getDataSet(DefaultCategoryDataset dataset) {
	for (HousingPrice price : data) {
		dataset.setValue(price.getValue(), price.getGeo(), price.getRefDate());
		// System.out.println(price.getValue() + "|"+ price.getGeo()+ price.getRefDate());
	}
}
public void editPlot(CategoryPlot plot) {
	plot.setBackgroundPaint(Color.lightGray);
	plot.setRangeGridlinePaint(Color.white);
}

public void editXYaxis(CategoryAxis x, NumberAxis y) {
	x.setMaximumCategoryLabelWidthRatio(0.8f);
	y.setStandardTickUnits(NumberAxis.createStandardTickUnits());

}
public void editRenderer(BarRenderer renderer) {
	renderer.setDrawBarOutline(true);
}
public void editChart(ChartPanel cp) {
	cp.setPreferredSize(new Dimension(800, 600));
}
public void editFrame(JFrame frame, ChartPanel cp) {
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	frame.setContentPane(cp);
	frame.pack();
	frame.setVisible(true);
}

	//new!
	private JButton createToggleView(JFrame frame) {
		//create button and give it functions
		toggleView = new JButton("ToggleView");
		toggleView.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				String[] options = {"Raw Data", "Summary Table"};
				int result = JOptionPane.showOptionDialog(frame, "Select how you would like to see the results", 
						"Chart View Options", JOptionPane.YES_NO_OPTION,
						JOptionPane.QUESTION_MESSAGE, null, options,
						options[0]);
				switch (result) {
				case JOptionPane.YES_OPTION:
					df.updateDataFrameRaw(frame);
					break;
				case JOptionPane.NO_OPTION:
					df.updateDataFrameSum(frame);
					break;
				}
			}
		});
		return toggleView;
	}

	
	

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}
}