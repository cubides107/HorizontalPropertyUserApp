package views.login;

import javax.security.auth.login.LoginContext;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class JPMainPanel extends JPanel implements ActionListener {

    private static final long serialVersionUID = 1L;

    private JLabel iconLogin;
    private JTextFieldModel userName;
    private JSeparator jSeparator;
    private JSeparator jSeparator1;
    private PasswordFieldModel passwordField;

    private ImagesPanel imagesPanel;
    private LoginAndRegisterPanel loginAndRegisterPanel;

    private RoundedJButton login;
    private JERoundTextField jeRoundTextField;

    public JPMainPanel(ActionListener actionListener) {

        setLayout(new GridLayout(0,2));
        setBackground(new Color(33, 44, 63));
        initComponents(actionListener);
    }

    private void initComponents(ActionListener actionListener) {

        imagesPanel = new ImagesPanel(actionListener);
        add(imagesPanel);

        loginAndRegisterPanel = new LoginAndRegisterPanel(this, actionListener);
        add(loginAndRegisterPanel);
    }

    private ImageIcon createIcon(String path, int weight, int height) {
        ImageIcon icon = new ImageIcon(getClass().getResource(path));
        ImageIcon icono = new ImageIcon(icon.getImage().getScaledInstance(weight, height, Image.SCALE_DEFAULT));
        return icono;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        switch (EventsView.valueOf(e.getActionCommand())){
            case REGISTER_PANEL:
                System.out.println("sdfsdf");
                loginAndRegisterPanel.showPanelRegister();
                break;
            case LOGIN_PANEL:
                loginAndRegisterPanel.showPanelLogin();
                break;
        }
    }

    public String getEmailUser() {
       return loginAndRegisterPanel.getEmailUser();
    }

    public void clearFields() {
        loginAndRegisterPanel.clearFields();
    }
}
