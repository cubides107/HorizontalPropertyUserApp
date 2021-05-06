package views;

import org.w3c.dom.Node;
import views.DialogAddBill.DialogAddBill;
import views.login.DialogLogin;
import views.modelsTree.NodeTree;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.time.LocalDate;
import java.util.Date;


public class MainFrame extends JFrame {

    public static final int WIDTH_FRAME = 1000;
    public static final int HEIGHT_FRAME = 900;

    private MainPanel mainPanel;
    private DialogLogin dialogLogin;
    private DialogAddBill dialogAddBill;

    public MainFrame(ActionListener actionListener, MouseListener mouseListener) {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setIconImage(new ImageIcon(getClass().getResource("/IconApp1.png")).getImage());
        setSize(WIDTH_FRAME, HEIGHT_FRAME);
        setLocationRelativeTo(null);

        mainPanel = new MainPanel(actionListener, mouseListener);
        add(mainPanel);

        dialogLogin = new DialogLogin(actionListener);
        dialogAddBill = new DialogAddBill(actionListener);
    }

    public void showLogin(boolean option) {
        dialogLogin.add(option);
    }

    public void showDialogBill(boolean option) {
        dialogAddBill.showDialog(option);
    }

    public String getEmailUser() {
        return dialogLogin.getEmailUser();
    }

    public void clearFieldsLogin() {
        dialogLogin.clearFields();
    }

    public void loadDataUser(Node root) {
        mainPanel.loadDataUser(root);
    }

    public String getIdSelectNodeUser() {
        return mainPanel.getIdSelectNodeUser();
    }

    public void addElementToNode(NodeTree node) {
        mainPanel.addElementToNode(node);
    }

    public LocalDate getDateDialog() {
        return dialogAddBill.getDate();
    }

    public Double getValueBill() {
        return dialogAddBill.getValueBill();
    }

    public void clearFieldsBill() {
        dialogAddBill.clearFields();
    }
}
