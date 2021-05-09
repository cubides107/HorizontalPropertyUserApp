package views.DialogAddBill;

import Presenter.Events;
import com.toedter.calendar.JMonthChooser;
import com.toedter.calendar.JYearChooser;
import views.login.RoundedJButton;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;
import java.time.LocalDate;

public class DialogReport extends JDialog {
    private RoundedJButton acceptButton;
    private JMonthChooser monthChooser;
    private JYearChooser yearChooser;
    private JPanel container;

    public DialogReport(ActionListener actionListener) {
        container = new JPanel();
        setSize(400,400);
        setLocationRelativeTo(null);
        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
        container.setBorder(new EmptyBorder(38, 30, 38, 30));
        initComponents(actionListener);
    }

    private void initComponents(ActionListener actionListener) {

        monthChooser = new JMonthChooser();
        yearChooser = new JYearChooser();

        container.add(monthChooser);
        container.add(yearChooser);

        acceptButton = new RoundedJButton("Aceptar", Events.ACCEPT_REPORT.name(), actionListener, Color.decode("#0A7EF5"), Color.WHITE);
        container.add(acceptButton);
        add(container);
    }

    public LocalDate getDate(){
       return LocalDate.of(yearChooser.getYear(),monthChooser.getMonth()+1,1);
    }

}
