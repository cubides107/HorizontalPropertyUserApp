package views.DialogAddBill;

import Presenter.Events;
import com.toedter.calendar.JDateChooser;
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
    private JDateChooser dateChooser;
    private JTextFieldModel valueBill;


    public DialogAddBill(ActionListener actionListener) {
        setSize(400, 400);
        setLocationRelativeTo(null);
        initComponents(actionListener);
    }

    private void initComponents(ActionListener actionListener) {
        container = new JPanel();
        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
        container.setBorder(new EmptyBorder(38, 30, 38, 30));
        dateChooser = new JDateChooser();

        container.add(dateChooser);
        valueBill = new JTextFieldModel("Valor de Factura: $$");
        container.add(valueBill);


        RoundedJButton addBill = new RoundedJButton("REGISTRAR", Events.ADD_BILL_DIALOG.name(), actionListener, Color.decode("#0A7EF5"), Color.WHITE);
        addBill.setAlignmentX(CENTER_ALIGNMENT);
        container.add(addBill);
        add(container);
    }

    public void showDialog(boolean option) {
        setVisible(option);
    }

    public LocalDate getDate() {
        return convertToLocalDateViaInstant(dateChooser.getDate());
    }

    public Double getValueBill() {
        return Double.valueOf(valueBill.getText());
    }

    public LocalDate convertToLocalDateViaInstant(Date dateToConvert) {
        return dateToConvert.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }

    public void clearFields() {
        valueBill.setText("");
        dateChooser.setDate(null);
    }
}
