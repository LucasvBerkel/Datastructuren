import javax.swing.*;
import javax.swing.event.*;
import java.awt.event.*;
import java.awt.*;
import javax.swing.text.*;

public class Label extends JLabel{
	Label(String label){
		super("  " + label);

		setHorizontalAlignment(SwingConstants.LEFT);
		setVerticalAlignment(SwingConstants.BOTTOM);
	}
}