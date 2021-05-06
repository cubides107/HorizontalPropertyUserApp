package views.login;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class DialogLogin extends JDialog{

	
	private static final long serialVersionUID = 1L;
	private JPMainPanel MainPanel;
	
	public DialogLogin(ActionListener actionListener) {
	setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	setSize(new Dimension(700,470));
	setModal(true);
	setUndecorated(true);
	setLocationRelativeTo(null);
	iniComponents(actionListener);
	}
	
	private void iniComponents(ActionListener actionListener) {
	MainPanel = new JPMainPanel(actionListener);
	add(MainPanel);
	}

	public void add(boolean option) {
		setVisible(option);
	}

	public String getEmailUser(){
		return MainPanel.getEmailUser();
	}

	public void clearFields() {
		MainPanel.clearFields();
	}
}
