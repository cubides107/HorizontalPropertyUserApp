package views.Center;

import org.w3c.dom.Node;
import views.modelsTree.NodeTree;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;

public class MainCenterPanel extends JPanel {

    private MainHeader mainHeader;
    private CenterPanel centerPanel;

    public MainCenterPanel(MouseListener mouseListener, ActionListener actionListener) {
        setLayout(new BorderLayout());
        initComponents(mouseListener, actionListener);
    }

    private void initComponents(MouseListener mouseListener, ActionListener actionListener) {
        mainHeader = new MainHeader();
        add(mainHeader, BorderLayout.NORTH);

        centerPanel = new CenterPanel(mouseListener, actionListener);
        add(centerPanel, BorderLayout.CENTER);
    }

    public void setNodeRootProperties(NodeTree nodeRoot) {
        centerPanel.setNodeRoot(nodeRoot);
    }


    public String getIdSelectNode() {
        return centerPanel.getIdSelectNode();
    }

    public DefaultMutableTreeNode getSelectNode() {
        return centerPanel.getSelectNode();
    }

    public void addElementToRoot(NodeTree node) {
        centerPanel.addElementToRoot(node);
    }

    public void addElementToNode(NodeTree node) {
        centerPanel.addElementToNode(node);
    }



    public void showUserPanel() {
        centerPanel.showUsersPanel();
    }

    public void showProperties() {
        centerPanel.showPropertiesPanel();
    }

    public void loadDataUser(Node root) {
        centerPanel.loadDataUser(root);
    }

    public void showPropertiesPanel() {
        centerPanel.showPropertiesPanel();
    }
}
