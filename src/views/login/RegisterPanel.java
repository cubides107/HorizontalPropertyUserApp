package views.login;

import Presenter.Events;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;

public class RegisterPanel extends JPanel {

    private JTextFieldModel name;
    private JTextFieldModel lastName;
    private JTextFieldModel email;
    private JTextFieldModel password;

    public RegisterPanel(ActionListener actionListener, ActionListener listenerPresenter) {
        setBackground(Color.blue);

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(new EmptyBorder(38, 30, 38, 30));
        setBackground(Color.WHITE);

        add(Box.createRigidArea(new Dimension(0, 50)));

        JLabel title = new JLabel("Crear Usuario");
        title.setAlignmentX(CENTER_ALIGNMENT);
        title.setForeground(Color.decode("#0A7EF5"));
        title.setFont(new Font("Arial", Font.BOLD, 19));
        add(title);

        add(Box.createRigidArea(new Dimension(0, 40)));
        name = new JTextFieldModel("Name");
        name.setIcon(createIcon("/nameUser.png", 16, 16));
        add(name);

        add(Box.createRigidArea(new Dimension(0, 10)));
        lastName = new JTextFieldModel("Last Name");
        lastName.setIcon(createIcon("/user.png", 16, 16));
        add(lastName);

        add(Box.createRigidArea(new Dimension(0, 10)));
        email = new JTextFieldModel("Email");
        email.setIcon(createIcon("/email.png", 16, 16));
        add(email);

        add(Box.createRigidArea(new Dimension(0, 10)));
        PasswordFieldModel passwordField = new PasswordFieldModel("Password", Color.white);
        passwordField.setIcon(createIcon("/key.png", 16, 16));
        add(passwordField);

        add(Box.createRigidArea(new Dimension(0, 40)));
        RoundedJButton register = new RoundedJButton("REGISTRAR USUARIO", Events.REGISTER.name(), listenerPresenter, Color.decode("#0A7EF5"), Color.WHITE);
        register.setAlignmentX(CENTER_ALIGNMENT);
        add(register);

        add(Box.createRigidArea(new Dimension(0, 3)));
        RoundedJButton loginUser = new RoundedJButton("Inicio de Sesión", EventsView.LOGIN_PANEL.name(), actionListener, Color.WHITE,Color.decode("#0A7EF5"));
        loginUser.setAlignmentX(CENTER_ALIGNMENT);
        add(loginUser);
        add(Box.createRigidArea(new Dimension(0, 40)));
    }

    private ImageIcon createIcon(String path, int weight, int height) {
        ImageIcon icon = new ImageIcon(getClass().getResource(path));
        ImageIcon icono = new ImageIcon(icon.getImage().getScaledInstance(weight, height, Image.SCALE_DEFAULT));
        return icono;
    }
}
