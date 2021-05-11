package views.DialogAddBill;

import Presenter.Events;
import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JMonthChooser;
import com.toedter.calendar.JYearChooser;
import views.login.JTextFieldModel;
import views.login.RoundedJButton;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class DialogAddBill extends JDialog {

    private JPanel container;
    private JMonthChooser monthChooser;
    private JYearChooser yearChooser;
    private JTextFieldModel valueBill;


    public DialogAddBill(ActionListener actionListener) {
        setSize(400, 290);
        setIconImage(new ImageIcon(getClass().getResource("/IconApp1.png")).getImage());
        setLocationRelativeTo(null);
        initComponents(actionListener);
    }

    private void initComponents(ActionListener actionListener) {
        container = new JPanel();
        JLabel title = new JLabel("Agregar Factura");
        title.setFont(new Font("Arial", Font.BOLD,24));
        title.setAlignmentX(CENTER_ALIGNMENT);
        container.add(title);
        container.add(Box.createRigidArea(new Dimension(20,10)));
        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
        container.setBorder(new EmptyBorder(38, 30, 38, 30));


        JPanel panelContainer = new JPanel();
        panelContainer.setLayout(new BoxLayout(panelContainer,BoxLayout.X_AXIS));
        panelContainer.setBorder(BorderFactory.createTitledBorder("Seleccione La Fecha"));

        monthChooser = new JMonthChooser();
        yearChooser = new JYearChooser();

        panelContainer.add(monthChooser);
        panelContainer.add(yearChooser);

        valueBill = new JTextFieldModel("Valor de Factura: $$");
        container.add(valueBill);


        RoundedJButton addBill = new RoundedJButton("REGISTRAR", Events.ADD_BILL_DIALOG.name(), actionListener, Color.decode("#0A7EF5"), Color.WHITE);
        addBill.setAlignmentX(CENTER_ALIGNMENT);
        container.add(Box.createRigidArea(new Dimension(20,10)));
        container.add(panelContainer);
        container.add(Box.createRigidArea(new Dimension(20,10)));
        container.add(addBill);
        add(container);
    }

    public void showDialog(boolean option) {
        setVisible(option);
    }

    public LocalDate getDate() {
        return LocalDate.of(yearChooser.getYear(), monthChooser.getMonth()+1,1);
    }

    public Double getValueBill() {
        return Double.valueOf(valueBill.getText());
    }

    public LocalDate convertToLocalDateViaInstant(Date dateToConvert) {
        return dateToConvert.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }

    public void clearFields() {
        valueBill.setText("");
    }
}
