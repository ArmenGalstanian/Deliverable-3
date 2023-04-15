package project;

import java.awt.FlowLayout;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class HousingPriceDataFrames   {

	ArrayList<HousingPrice> data;
	
	public HousingPriceDataFrames(ArrayList<HousingPrice> data) {
			this.data = data;
		// TODO Auto-generated constructor stub
	}

	public void getDataRaw(String[][] rowData){
		for (int i = 0; i< data.size(); i++) {
			//assign data to string array
			rowData[i][0] = data.get(i).getRefDate();
			rowData[i][1] = data.get(i).getGeo();
			rowData[i][2] = data.get(i).getDguid();
			rowData[i][3] = data.get(i).getIndex();
			rowData[i][4] = data.get(i).getUom();
			rowData[i][5] = data.get(i).getUomId();
			rowData[i][6] = data.get(i).getScalarFactor();
			rowData[i][7] = data.get(i).getScalarId();
			rowData[i][8] = data.get(i).getVector();
			rowData[i][9] = data.get(i).getCoordinate();
			rowData[i][10] = data.get(i).getValue()+"";
			rowData[i][11] = data.get(i).getStatus();
			rowData[i][12] = data.get(i).getSymbol();
			rowData[i][13] = data.get(i).getTerminated();
			rowData[i][14] = data.get(i).getDecimals()+"";
		}
	}
	
	public void updateDataFrameRaw(JFrame frame) {
		String[] columnNames = {"refDate"
				,"geo","dguid","index"
				,"uom","uomId","scalarFactor"
				,"scalarId","vector","coordinate"
				,"value","status","symbol"
				,"terminated","decimals"};

		String[][] rowData = new String [data.size()][15];
		getDataRaw(rowData);

		//create new jframe for data
		JFrame frame2 = new JFrame("Raw Data");  
		makeTable(frame2, rowData,columnNames);
	}
	
	public void makeTable(JFrame frame, String[][] rowData, 
			String[]columnNames) {
		// Display the window.  
				frame.setSize(800, 600);  
				frame.setVisible(true);  
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  

				// set flow layout for the frame  
				frame.getContentPane().setLayout(new FlowLayout());  

				//create table with scrolling feature
				JTable table = new JTable(rowData, columnNames);  
				table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
				
				JScrollPane scroller = new JScrollPane(table);  
				
				for (int i = 0; i< columnNames.length; i++) {
					table.getColumnModel().getColumn(i).setPreferredWidth(100);
				}


				scroller.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);  
				scroller.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);  

				frame.getContentPane().add(scroller); 
				frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}

	
	public void doCalculations(String[][] tableData) {
		//calculations for value statistics
				double total;
				double average;
				double min;
				double max;
				String currRegion;

				int tableCounter = 0;
				Double regionNumCounter = 0.0;
				double currVal;
				String currGeo;

				currRegion = data.get(0).getGeo();
				total = data.get(0).getValue();  
				min = total;  
				max = total;  
				for (int i = 0; i< data.size(); i++) { 
			
					currVal =  data.get(i).getValue();
					currGeo = data.get(i).getGeo();
					if (!currRegion.equalsIgnoreCase(currGeo)) {
						average = Math.round((total/regionNumCounter)*100)/100.0;

						tableData[tableCounter][0] = currRegion;
						tableData[tableCounter][1] = average+"";
						tableData[tableCounter][2] = min+"";
						tableData[tableCounter][3] = max+"";


						currRegion = currGeo;
						total = currVal;
						min = total;
						max = total;
						regionNumCounter = 0.0;
						if (min > currVal) {
							min = currVal;
						}
						if (max < currVal) {
							max = currVal;
						}
						total +=currVal;
						regionNumCounter++;
						tableCounter++;
						continue;
					}
					if (min > currVal) {
						min = currVal;
					}
					if (max < currVal) {
						max = currVal;
					}
					total +=currVal;
					regionNumCounter++;
				}
		
	}
	public void updateDataFrameSum(JFrame frame) {
		String[][] tableData = new String [data.size()/3][5];
		doCalculations(tableData);
		//UI work
		//create new jframe for output data
		JFrame frame3 = new JFrame("Summary Table of Values"); 
		String [] columnNames = {"Current Region", "Average", "Min", "Max"};
		makeTable(frame3, tableData, columnNames);
	}


	
}

