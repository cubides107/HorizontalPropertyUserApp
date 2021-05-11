package views.DialogAddBill;

import Presenter.Events;
import com.toedter.calendar.JMonthChooser;
import com.toedter.calendar.JYearChooser;
import views.login.RoundedJButton;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

public class DialogReport extends JDialog {
    private RoundedJButton acceptButton;
    private JMonthChooser monthChooser;
    private JYearChooser yearChooser;
    private JPanel container;
    private JLabel title;

    public DialogReport(ActionListener actionListener) {
        container = new JPanel();
        setSize(400, 250);
        setLocationRelativeTo(null);
        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
        container.setBorder(new EmptyBorder(38, 30, 38, 30));
        setIconImage(new ImageIcon(getClass().getResource("/IconApp1.png")).getImage());
        initComponents(actionListener);
    }

    private void initComponents(ActionListener actionListener) {
        title = new JLabel("Generar Reporte");
        title.setFont(new Font("Arial", Font.BOLD,24));
        title.setAlignmentX(CENTER_ALIGNMENT);
        container.add(title);


        container.add(Box.createRigidArea(new Dimension(20,10)));

        JPanel panelContainer = new JPanel();
        panelContainer.setBorder(BorderFactory.createTitledBorder("Reporte a partir de"));
        panelContainer.setLayout(new BoxLayout(panelContainer, BoxLayout.X_AXIS));

        monthChooser = new JMonthChooser();
        monthChooser.setAlignmentX(CENTER_ALIGNMENT);

        yearChooser = new JYearChooser();
        yearChooser.setAlignmentX(CENTER_ALIGNMENT);

        panelContainer.add(monthChooser);
        panelContainer.add(yearChooser);

        acceptButton = new RoundedJButton("Aceptar", Events.ACCEPT_REPORT.name(), actionListener, Color.decode("#0A7EF5"), Color.WHITE);
        acceptButton.setAlignmentX(CENTER_ALIGNMENT);
        container.add(Box.createRigidArea(new Dimension(20,20)));
        container.add(panelContainer);
        container.add(Box.createRigidArea(new Dimension(20,20)));
        container.add(acceptButton);

        add(container);
        Timer timer = new Timer(0, e -> {
           container.repaint();
        });
        timer.start();
    }



    public LocalDate getDate() {
        return LocalDate.of(yearChooser.getYear(), monthChooser.getMonth() + 1, 1);
    }

}
