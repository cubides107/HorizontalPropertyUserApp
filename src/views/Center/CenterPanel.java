package views.Center;

import org.w3c.dom.Node;
import views.modelsTree.NodeTree;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.io.IOException;

public class CenterPanel extends JPanel {

    public static final String PROPERTIES_PANEL = "propertiesPanel";
    public static final String USERS_PANEL = "users_Panel";
    private CardLayout cardLayout;

    private PropertiesPanel propertiesPanel;
    private PdfPanel pdfpanel;

    public CenterPanel(MouseListener mouseListener, ActionListener actionListener) {
        cardLayout = new CardLayout();
        setLayout(cardLayout);
        initComponents(mouseListener, actionListener);
        cardLayout.show(this, PROPERTIES_PANEL);
    }

    private void initComponents(MouseListener mouseListener, ActionListener actionListener) {
        propertiesPanel = new PropertiesPanel(mouseListener, actionListener);
        add(propertiesPanel, PROPERTIES_PANEL);

//        try {
//            pdfpanel = new PdfPanel();
//            add(pdfpanel,"PDF");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

    }

    public void showPropertiesPanel() {
        cardLayout.show(this, PROPERTIES_PANEL);
    }

    public void showUsersPanel() {
        cardLayout.show(this, USERS_PANEL);
    }

    public void setNodeRoot(NodeTree nodeRoot) {
        propertiesPanel.setNodeRoot(nodeRoot);
    }


    public String getIdSelectNode() {
        return propertiesPanel.getIdSelectNode();
    }

    public DefaultMutableTreeNode getSelectNode() {
        return propertiesPanel.getSelectNode();
    }

    public void addElementToRoot(NodeTree node) {
        propertiesPanel.addElementToRoot(node);
    }

    public void addElementToNode(NodeTree node) {
        propertiesPanel.addElementToNode(node);
    }


    public void loadDataUser(Node root) {
        propertiesPanel.loadData(root);
    }

    public void showProperties() {
        
    }
}
