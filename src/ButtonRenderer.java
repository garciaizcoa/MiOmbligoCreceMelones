import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.TableCellRenderer;

public class ButtonRenderer extends JButton implements TableCellRenderer {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JButton myButton;
	private ButtonEditor btnEditor;
	
	public ButtonRenderer(){
		
		btnEditor = new ButtonEditor(new JTextField());	
	}
	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
			int row, int column) {
		// TODO Auto-generated method stub
		if(value instanceof JButton){
			setText(((JButton)value).getText());
		}
		myButton = (JButton)value;
		return this;
	}
	
	public ButtonEditor getButtonEditor(){
		return btnEditor;
	}
	
	public class ButtonEditor extends DefaultCellEditor{

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		
		protected JButton button;
		private boolean clicked;

		public ButtonEditor(JTextField textField) {
			super(textField);
			
			button = new JButton();
			
			button.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					//fireEditingStopped();
					myButton.doClick();
				}
			});
		}
		
		@Override
		public Component getTableCellEditorComponent(JTable table, Object value ,boolean isSelected, int row, int col){
			button.setText(((JButton)value).getText());
			clicked = true;
			return button;
		}
		
		@Override
		public Object getCellEditorValue(){
			return null;
		}
		
		@Override
		public boolean stopCellEditing(){
			clicked = false;
			return super.stopCellEditing();
		}
		
	}

}
