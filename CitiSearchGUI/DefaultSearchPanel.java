import javax.swing.*;
import javax.swing.event.*;
import java.awt.event.*;
import java.awt.*;

public class DefaultSearchPanel extends JPanel implements ICallback{
	final String name = "Default Search";

	RadioButton m_landcode;
	RadioButton m_latitude;
	RadioButton m_longitude;
	RadioButton m_population;
	RadioButton m_elevation;

	ComboBox m_landcode_sel;

	TextField m_latitude_min;
	TextField m_longitude_min;
	TextField m_population_min;
	TextField m_elevation_min;

	TextField m_latitude_max;
	TextField m_longitude_max;
	TextField m_population_max;
	TextField m_elevation_max;	


	DefaultSearchPanel(){
		super(new GridBagLayout());

		String[] landcodes = new String[3];
		landcodes[0] = "NL";
		landcodes[1] = "DE";
		landcodes[2] = "US";

		InitPane0(landcodes);
		InitPane1();
		InitPane2();

		GridBagConstraints constraint;
		constraint = new GridBagConstraints();
	    constraint.weightx = 0.5;
	    constraint.weighty = 1;
	    constraint.fill = GridBagConstraints.HORIZONTAL;
	    constraint.gridx = 0;
	    constraint.gridy = 8;
	    constraint.ipady = 20;
	    add(new JLabel(""), constraint);

		Button search = new Button("Search", this);
		constraint = new GridBagConstraints();
	    constraint.weightx = 0.5;
	    constraint.weighty = 0;
	    constraint.fill = GridBagConstraints.HORIZONTAL;
	    constraint.gridx = 0;
	    constraint.gridy = 9;		
		add(search, constraint);
	}

	private void InitPane0(String[] landcodes){
		m_landcode_sel = new ComboBox(landcodes);

		m_latitude_min = new TextField(10);
		m_longitude_min = new TextField(10);
		m_population_min = new TextField(10);
		m_elevation_min = new TextField(10);

		GridBagConstraints constraint;
		constraint = new GridBagConstraints();
		constraint.weightx = 0.5;
	    constraint.weighty = 0.5;
	    constraint.fill = GridBagConstraints.HORIZONTAL;
	    constraint.gridx = 0;
	    constraint.gridy = 0;
		add(new Label("Select Landcode:"), constraint);

		constraint = new GridBagConstraints();
		constraint.weightx = 0.5;
	    constraint.weighty = 0.5;
	    constraint.fill = GridBagConstraints.HORIZONTAL;
	    constraint.gridx = 0;
	    constraint.gridy = 1;
		add(m_landcode_sel, constraint);

		constraint = new GridBagConstraints();
		constraint.weightx = 0.5;
	    constraint.weighty = 0.5;
	    constraint.fill = GridBagConstraints.HORIZONTAL;
	    constraint.gridx = 0;
	    constraint.gridy = 2;
	    constraint.ipady = 20;
		add(new JLabel(""), constraint);

		constraint = new GridBagConstraints();
		constraint.weightx = 0.5;
	    constraint.weighty = 0.5;
	    constraint.fill = GridBagConstraints.HORIZONTAL;
	    constraint.gridx = 0;
	    constraint.gridy = 3;
		add(new JLabel("  Min values:"), constraint);

		constraint = new GridBagConstraints();
		constraint.weightx = 0.5;
	    constraint.weighty = 0.5;
	    constraint.fill = GridBagConstraints.HORIZONTAL;
	    constraint.gridx = 0;
	    constraint.gridy = 4;
		add(m_latitude_min, constraint);

		constraint = new GridBagConstraints();
		constraint.weightx = 0.5;
	    constraint.weighty = 0.5;
	    constraint.fill = GridBagConstraints.HORIZONTAL;
	    constraint.gridx = 0;
	    constraint.gridy = 5;
		add(m_longitude_min, constraint);

		constraint = new GridBagConstraints();
		constraint.weightx = 0.5;
	    constraint.weighty = 0.5;
	    constraint.fill = GridBagConstraints.HORIZONTAL;
	    constraint.gridx = 0;
	    constraint.gridy = 6;
		add(m_population_min, constraint);

		constraint = new GridBagConstraints();
		constraint.weightx = 0.5;
	    constraint.weighty = 0.5;
	    constraint.fill = GridBagConstraints.HORIZONTAL;
	    constraint.gridx = 0;
	    constraint.gridy = 7;
		add(m_elevation_min, constraint);
	}

	private void InitPane1(){
		m_latitude_max = new TextField(10);
		m_longitude_max = new TextField(10);
		m_population_max = new TextField(10);
		m_elevation_max = new TextField(10);

		GridBagConstraints constraint;
		constraint = new GridBagConstraints();
		constraint.weightx = 0.5;
	    constraint.weighty = 0.5;
	    constraint.fill = GridBagConstraints.HORIZONTAL;
	    constraint.gridx = 1;
	    constraint.gridy = 2;
	    constraint.ipady = 20;
		add(new JLabel(""), constraint);

		constraint = new GridBagConstraints();
		constraint.weightx = 0.5;
	    constraint.weighty = 0.5;
	    constraint.fill = GridBagConstraints.HORIZONTAL;
	    constraint.gridx = 1;
	    constraint.gridy = 3;
		add(new Label("Max values:"), constraint);

		constraint = new GridBagConstraints();
		constraint.weightx = 0.5;
	    constraint.weighty = 0.5;
	    constraint.fill = GridBagConstraints.HORIZONTAL;
	    constraint.gridx = 1;
	    constraint.gridy = 4;
		add(m_latitude_max, constraint);

		constraint = new GridBagConstraints();
		constraint.weightx = 0.5;
	    constraint.weighty = 0.5;
	    constraint.fill = GridBagConstraints.HORIZONTAL;
	    constraint.gridx = 1;
	    constraint.gridy = 5;
		add(m_longitude_max, constraint);

		constraint = new GridBagConstraints();
		constraint.weightx = 0.5;
	    constraint.weighty = 0.5;
	    constraint.fill = GridBagConstraints.HORIZONTAL;
	    constraint.gridx = 1;
	    constraint.gridy = 6;
		add(m_population_max, constraint);

		constraint = new GridBagConstraints();
		constraint.weightx = 0.5;
	    constraint.weighty = 0.5;
	    constraint.fill = GridBagConstraints.HORIZONTAL;
	    constraint.gridx = 1;
	    constraint.gridy = 7;
		add(m_elevation_max, constraint);
	}

	private void InitPane2(){
		m_landcode = new RadioButton("Landcodes");
		m_latitude = new RadioButton("Latitude");
		m_longitude = new RadioButton("Longitude");
		m_population = new RadioButton("Population");
		m_elevation = new RadioButton("Elevation");

		GridBagConstraints constraint;
		constraint = new GridBagConstraints();
		constraint.weightx = 0.5;
	    constraint.weighty = 0.5;
	    constraint.fill = GridBagConstraints.HORIZONTAL;
	    constraint.gridx = 2;
	    constraint.gridy = 1;
		add(m_landcode, constraint);

		constraint = new GridBagConstraints();
		constraint.weightx = 0.5;
	    constraint.weighty = 0.5;
	    constraint.fill = GridBagConstraints.HORIZONTAL;
	    constraint.gridx = 1;
	    constraint.gridy = 2;
	    constraint.ipady = 20;
		add(new JLabel(""), constraint);

		constraint = new GridBagConstraints();
		constraint.weightx = 0.5;
	    constraint.weighty = 0.5;
	    constraint.fill = GridBagConstraints.HORIZONTAL;
	    constraint.gridx = 2;
	    constraint.gridy = 4;
		add(m_latitude, constraint);

		constraint = new GridBagConstraints();
		constraint.weightx = 0.5;
	    constraint.weighty = 0.5;
	    constraint.fill = GridBagConstraints.HORIZONTAL;
	    constraint.gridx = 2;
	    constraint.gridy = 5;
		add(m_longitude, constraint);

		constraint = new GridBagConstraints();
		constraint.weightx = 0.5;
	    constraint.weighty = 0.5;
	    constraint.fill = GridBagConstraints.HORIZONTAL;
	    constraint.gridx = 2;
	    constraint.gridy = 6;
		add(m_population, constraint);

		constraint = new GridBagConstraints();
		constraint.weightx = 0.5;
	    constraint.weighty = 0.5;
	    constraint.fill = GridBagConstraints.HORIZONTAL;
	    constraint.gridx = 2;
	    constraint.gridy = 7;
		add(m_elevation, constraint);
	}


	public void callback(String source){
		DefSearchParam parameters = new DefSearchParam();

		parameters.landcode = new SearchMatchParam(m_landcode.getValue(), 
												   m_landcode_sel.getValue());
		parameters.latitude = new SearchRangeParam(m_latitude.getValue(), 
												   m_latitude_min.getValue(), 
												   m_latitude_max.getValue());
		
	}
}