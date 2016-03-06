import javax.swing.*;
import javax.swing.event.*;
import java.awt.event.*;
import java.awt.*;

public class GUI extends JFrame{

	JTabbedPane tabbedPane; 
	DefaultSearchPanel defSearch; 
	AdvancedSearchPanel advSearch; 
	ResultsPanel results;

	GUI(){
		tabbedPane = new JTabbedPane();
		defSearch = new DefaultSearchPanel();
		advSearch = new AdvancedSearchPanel();
		results = new ResultsPanel();

		tabbedPane.add(defSearch.name, defSearch);
		tabbedPane.add(advSearch.name, advSearch);
		tabbedPane.add(results.name, results);

		setContentPane(tabbedPane);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);	
        pack();

        //setResizable(false);
	}
}