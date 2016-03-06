import javax.swing.*;
import javax.swing.event.*;
import java.awt.event.*;
import java.awt.*;
import javax.swing.text.*;

public class Button extends JButton{
	String m_text;
	ICallback m_callback;

	Button(String text, ICallback callback){
		super(text);
		m_text = text;
		m_callback = callback;

		addActionListener(
			new ActionListener(){
				public void actionPerformed(ActionEvent event){
					m_callback.callback(m_text);
				}
			}
		);
	}
}