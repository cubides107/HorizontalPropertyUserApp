package views;

import org.w3c.dom.Node;
import views.Center.MainCenterPanel;
import views.dashboard.Dashboard;
import views.modelsTree.NodeTree;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;

public class MainPanel extends JPanel {

    private Dashboard dashboard;
    private MainCenterPanel mainCenterPanel;

    public MainPanel(ActionListener actionListener, MouseListener mouseListener) {
        setLayout(new BorderLayout());
        initComponents(actionListener, mouseListener);
    }

    private void initComponents(ActionListener actionListener, MouseListener mouseListener) {
        dashboard = new Dashboard(actionListener);
        add(dashboard, BorderLayout.WEST);

        mainCenterPanel = new MainCenterPanel(mouseListener, actionListener);
        add(mainCenterPanel, BorderLayout.CENTER);
    }

    public void loadDataUser(Node root) {
        mainCenterPanel.loadDataUser(root);
    }

    public String getIdSelectNodeUser() {
        return mainCenterPanel.getIdSelectNode();
    }

    public void addElementToNode(NodeTree node) {
        mainCenterPanel.addElementToNode(node);
    }
}
