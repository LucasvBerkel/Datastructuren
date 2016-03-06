import javax.swing.*;
import javax.swing.event.*;
import java.awt.event.*;
import java.awt.*;
import javax.swing.table.*;
import javax.swing.border.*;

public class ResultsPanel extends JPanel{
	final String name = "Results";

	JPanel panel;
	String[][] data;

	ResultsPanel(){
		super(new GridBagLayout());

		GridBagConstraints constraint;
		constraint = new GridBagConstraints();
	    constraint.weightx = 0.5;
	    constraint.fill = GridBagConstraints.HORIZONTAL;
	    constraint.gridx = 0;
	    constraint.gridy = 0;
	    add(new JLabel("Cities"), constraint);

		constraint = new GridBagConstraints();
	    constraint.weightx = 0.5;
	    constraint.weighty = 1;
	    constraint.fill = GridBagConstraints.BOTH;
	    constraint.gridx = 0;
	    constraint.gridy = 1;

	    String[] header = {"Cities"};
		DefaultTableModel tableModel = new DefaultTableModel(data, header);
		Table table = new Table(tableModel);
		add(table, constraint);
	}
}