import javax.swing.*;
import javax.swing.event.*;
import java.awt.event.*;
import java.awt.*;

public class ComboBox extends JComboBox{

	ComboBox(String[] values){
		super(values);
	}

	public String getValue(){
		return (String)getSelectedItem();
	}
}