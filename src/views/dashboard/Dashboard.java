package views.dashboard;

import Presenter.Events;
import views.ComponentsModel.ButtonModelHover;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;

public class Dashboard extends JPanel {



    public Dashboard(ActionListener actionListener) {

        setBackground(Color.decode("#212F73"));
        setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
        setBorder(new EmptyBorder(20,15,10,15));
        initComponents(actionListener);

    }

    private void initComponents(ActionListener actionListener) {


        JLabel title = new JLabel();
        title.setAlignmentX(CENTER_ALIGNMENT);
        title.setIcon(new ImageIcon(getClass().getResource("/IconApp1.png")));
        add(title);


        add(Box.createRigidArea(new Dimension(0, 40)));
        ButtonModelHover reports = new ButtonModelHover("/statistics.png","REPORTES", actionListener,Events.REPORTS.name());
        add(reports);

        add(Box.createRigidArea(new Dimension(0, 40)));
        ButtonModelHover  property = new ButtonModelHover("/properties.png","PROPIEDADES", actionListener, Events.PROPERTIES.name());
        add(property);
    }
}
