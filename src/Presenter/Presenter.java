package Presenter;

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

public class Presenter implements ActionListener, MouseListener, PresenterImp {
    private MainFrame mainFrame;
    private UserNetwork userNetwork;

    public Presenter() {
        try {
            mainFrame = new MainFrame(this, this);
            userNetwork = new UserNetwork(this);
            mainFrame.showLogin(true);
        } catch (IOException e) {
            e.printStackTrace();
        }
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
                Document document = Persistence.convertXMLFileToXMLDocument("Test.xml");
                Node root = document.getDocumentElement();
                mainFrame.loadDataUser(root);
                break;
            case ADD_SERVICE_WATER:
                String idSelectNodeUser = mainFrame.getIdSelectNodeUser();
                mainFrame.addElementToNode(new NodeTree(TypeFiles.SERVICE_WATER,"Service Water","0"));
                break;
            case ADD_SERVICE_ELECTRICITY:
                idSelectNodeUser = mainFrame.getIdSelectNodeUser();
                mainFrame.addElementToNode(new NodeTree(TypeFiles.SERVICE_ELECTRICITY,"Service Electricity","0"));
                break;
            case ADD_SERVICE_GAS:
                idSelectNodeUser = mainFrame.getIdSelectNodeUser();
                mainFrame.addElementToNode(new NodeTree(TypeFiles.SERVICE_GAS,"Service Gas","0"));
                break;
            case ADD_SERVICE_INTERNET:
                idSelectNodeUser = mainFrame.getIdSelectNodeUser();
                mainFrame.addElementToNode(new NodeTree(TypeFiles.SERVICE_INTERNET,"Service Internet","0"));
                break;



        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON3) {
            PropertiesPanel panel = (PropertiesPanel) e.getComponent().getParent();
            panel.showDeletePopMenu(e.getComponent(),e.getX(),e.getY());
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
    public void showAlertUser(boolean option) {
        if (!option) {
            JOptionPane.showMessageDialog(null, "Usuario no encontrado");
            mainFrame.clearFieldsLogin();
        } else {
            mainFrame.showLogin(false);
            mainFrame.setVisible(true);
            mainFrame.clearFieldsLogin();
        }
    }

    @Override
    public void loadDataUser(Node root) {
        mainFrame.loadDataUser(root);
    }
}
