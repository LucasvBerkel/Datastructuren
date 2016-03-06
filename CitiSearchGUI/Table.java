import java.awt.*;
import javax.swing.*;
import javax.swing.table.*;

public class Table extends JTable{
	DefaultTableModel m_model;

	Table(DefaultTableModel model){
		super(model);
	}
}