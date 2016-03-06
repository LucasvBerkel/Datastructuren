import javax.swing.*;
import javax.swing.event.*;
import java.awt.event.*;
import java.awt.*;
import javax.swing.text.*;

public class TextField extends JTextField{

	TextField(int characters){
		super(characters);
	}

	public String getValue(){
		return getText();
	}
}