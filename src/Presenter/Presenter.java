package Presenter;

import models.WrapperUser;
import network.PresenterImp;
import network.UserNetwork;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import persistence.Persistence;
import views.Center.PropertiesPanel;
import views.MainFrame;
import views.modelsTree.NodeTree;
import views.modelsTree.TypeFiles;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Date;

public class Presenter implements ActionListener, MouseListener, PresenterImp {
    private MainFrame mainFrame;
    private UserNetwork userNetwork;
    private WrapperUser wrapperUser;

    public Presenter() {
        try {
            mainFrame = new MainFrame(this, this);
            userNetwork = new UserNetwork(this);
            mainFrame.showLogin(true);
            repaint();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void repaint() {
        Timer timer = new Timer(10, e -> {
            mainFrame.repaint();
        });
        timer.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (Events.valueOf(e.getActionCommand())) {
            case LOGIN:
                String emailUser = mainFrame.getEmailUser();
                userNetwork.writeUTF("NEW_CLIENT");
                userNetwork.writeUTF(emailUser);
                break;
            case REGISTER:
                break;
            case EXIT:
                mainFrame.showLogin(false);
                break;
            case PROPERTIES:
                mainFrame.showPropertiesPanel();
                userNetwork.writeUTF("REFRESH_PROPERTIES_USER");
                userNetwork.writeUTF(wrapperUser.getNameUser());
                Document document = Persistence.convertXMLFileToXMLDocument("data/PropertiesUser.xml");
                Node root = document.getDocumentElement();
                mainFrame.loadDataUser(root);
                break;
            case ADD_SERVICE_WATER:
                String idSelectNodeUser = mainFrame.getIdSelectNodeUser();
                userNetwork.writeUTF("ADD_SERVICE_WATER");
                userNetwork.writeInt(Integer.parseInt(idSelectNodeUser));
                break;
            case ADD_SERVICE_ELECTRICITY:
                idSelectNodeUser = mainFrame.getIdSelectNodeUser();
                userNetwork.writeUTF("ADD_SERVICE_ELECTRICITY");
                userNetwork.writeInt(Integer.parseInt(idSelectNodeUser));

                break;
            case ADD_SERVICE_GAS:
                idSelectNodeUser = mainFrame.getIdSelectNodeUser();
                userNetwork.writeUTF("ADD_SERVICE_GAS");
                userNetwork.writeInt(Integer.parseInt(idSelectNodeUser));
                break;
            case ADD_SERVICE_INTERNET:
                idSelectNodeUser = mainFrame.getIdSelectNodeUser();
                userNetwork.writeUTF("ADD_SERVICE_INTERNET");
                userNetwork.writeInt(Integer.parseInt(idSelectNodeUser));
                break;
            case ADD_BILL:
                mainFrame.showDialogBill(true);
                break;
            case ADD_BILL_DIALOG:
                mainFrame.showDialogBill(false);
                LocalDate dateBill = mainFrame.getDateDialog();
                Double valueBill = mainFrame.getValueBill();
                idSelectNodeUser = mainFrame.getIdSelectNodeUser();
                mainFrame.clearFieldsBill();
                userNetwork.writeUTF("ADD_NEW_BILL");
                String convertAux = dateBill.toString() + "#" + valueBill + "#" + idSelectNodeUser;
                userNetwork.writeUTF(convertAux);
                break;
            case REPORTS:
                mainFrame.showDialogReport(true);
                break;
            case ACCEPT_REPORT:
                mainFrame.showDialogReport(false);
                userNetwork.writeUTF("REPORT1");
                userNetwork.writeUTF(wrapperUser.getNameUser());
                userNetwork.writeUTF(mainFrame.getDateDialogReport().toString());
                break;
            case DELETE:
                idSelectNodeUser = mainFrame.getIdSelectNodeUser();
                userNetwork.writeUTF("DELETE");
                userNetwork.writeInt(Integer.parseInt(idSelectNodeUser));
                break;
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON3) {
            PropertiesPanel panel = (PropertiesPanel) e.getComponent().getParent();
            String selectTypeNode = panel.getSelectTypeNode();
            if (selectTypeNode.equals(TypeFiles.APARTMENT.getType()) || selectTypeNode.equals(TypeFiles.HOUSE.getType())) {
                panel.showMenuOptionProperty(e.getComponent(), e.getX(), e.getY());
            } else if (selectTypeNode.equals(TypeFiles.BILL_SERVICE.getType())) {
               panel.showDeleteBill(e.getComponent(), e.getX(), e.getY());
            }else if(!selectTypeNode.equals(TypeFiles.HORIZONTAL_PROPERTY_USER.getType())){
                panel.showReceipt(e.getComponent(), e.getX(), e.getY());
            }

        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void showAlertUser(boolean option, String name) {
        if (!option) {
            JOptionPane.showMessageDialog(null, "Usuario no encontrado");
            mainFrame.clearFieldsLogin();
        } else {
            wrapperUser = new WrapperUser(name);
            mainFrame.showLogin(false);
            mainFrame.setVisible(true);
            mainFrame.clearFieldsLogin();
        }
    }

    @Override
    public void loadDataUser() {
        Document document = Persistence.convertXMLFileToXMLDocument("data/PropertiesUser.xml");
        Node root = document.getDocumentElement();
        mainFrame.loadDataUser(root);
    }

    @Override
    public void addNewWaterService(int waterServiceID) {
        mainFrame.addElementToNode(new NodeTree(TypeFiles.SERVICE_WATER, "Service Water", String.valueOf(waterServiceID)));

    }

    @Override
    public void addNewElectricityService(int electricityServiceID) {
        mainFrame.addElementToNode(new NodeTree(TypeFiles.SERVICE_ELECTRICITY, "Service Electricity", String.valueOf(electricityServiceID)));
    }

    @Override
    public void addNewGasService(int gasServiceID) {
        mainFrame.addElementToNode(new NodeTree(TypeFiles.SERVICE_GAS, "Service Gas", String.valueOf(gasServiceID)));
    }

    @Override
    public void addNewInternetService(int internetServiceID) {
        mainFrame.addElementToNode(new NodeTree(TypeFiles.SERVICE_INTERNET, "Service Internet", String.valueOf(internetServiceID)));
    }

    @Override
    public void addNewBillService(int idBillService) {
        mainFrame.addElementToNode(new NodeTree(TypeFiles.BILL_SERVICE, "Factura", String.valueOf(idBillService)));
    }
}
