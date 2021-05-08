package views.Center;

import Presenter.Events;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import views.ComponentsModel.TreeCellRenderer;
import views.modelsTree.NodeTree;
import views.modelsTree.TypeFiles;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeSelectionModel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;

public class PropertiesPanel extends JPanel {

    private DefaultTreeModel model;
    private DefaultMutableTreeNode nodeRoot;
    private JTree tree;
    private final JPopupMenu popOptionProperty;
    private JPopupMenu popAddReceipt;
    private JButton add;

    public PropertiesPanel(MouseListener mouseListener, ActionListener actionListener) {
        popOptionProperty = new JPopupMenu();
        popAddReceipt = new JPopupMenu();
        setLayout(new BorderLayout());
        setBackground(Color.decode("#1C2868"));
        nodeRoot = new DefaultMutableTreeNode();
        model = new DefaultTreeModel(nodeRoot);
        initComponents(mouseListener, actionListener);
    }

    public void setNodeRoot(NodeTree nodeRoot) {
        this.nodeRoot.setUserObject(nodeRoot);
        tree.updateUI();
    }

    private void initComponents(MouseListener mouseListener, ActionListener actionListener) {


        JMenu serviceMenu = new JMenu("Agregar Servicio");

        serviceMenu.add(new MenuItemModel("Luz",actionListener, Events.ADD_SERVICE_ELECTRICITY.name()));
        serviceMenu.add(new MenuItemModel("Agua",actionListener, Events.ADD_SERVICE_WATER.name()));
        serviceMenu.add(new MenuItemModel("Gas",actionListener, Events.ADD_SERVICE_GAS.name()));
        serviceMenu.add(new MenuItemModel("Internet",actionListener, Events.ADD_SERVICE_INTERNET.name()));

        popOptionProperty.add(serviceMenu);

        popAddReceipt.add(new MenuItemModel("Agregar Factura",actionListener,Events.ADD_BILL.name()));
//        MenuItemModel removeElement = new MenuItemModel("Eliminar", actionListener, Events.DELETE_PROPERTY.name());
//        menu.add(new MenuItemModel("Edificio", actionListener, Events.ADD_BUILDING.name()));
//        menu.add(new MenuItemModel("Casa", actionListener, Events.ADD_HOUSE.name()));
//        menu.add(new MenuItemModel("Salon comunal ", actionListener, Events.ADD_COMMON_ROOM.name()));
//        menu.add(new MenuItemModel("Piscina ", actionListener, Events.ADD_POOL.name()));
//        menu.add(new MenuItemModel("Cancha", actionListener, Events.FIELD.name()));
//        popMenuRoot.add(menu);
//        popMenuRoot.add(new MenuItemModel("Eliminar", actionListener, Events.DELETE_PROPERTY.name()));


//        menuBuilding.add(new MenuItemModel("Apartamento", actionListener, Events.ADD_APARTMENT.name()));
//        popMenuBuilding.add(new MenuItemModel("Eliminar", actionListener, Events.DELETE_PROPERTY.name()));


//        deletePropertyOnly.add(removeElement);

        tree = new JTree(model);
        tree.addMouseListener(mouseListener);
        tree.setCellRenderer(new TreeCellRenderer());
        tree.setShowsRootHandles(true);
        tree.setOpaque(false);
        add(tree, BorderLayout.CENTER);

        add = new JButton("Agregar");
        add.addActionListener(actionListener);
//        add.setActionCommand(Events.ADD_APARTMENT_TREE_PROPERTIES.name());
        add.setVisible(false);
        add(add, BorderLayout.SOUTH);

    }

    public void showButtonAdd(boolean b) {
        add.setVisible(b);
    }

    public void setActionCommandAddButton() {
        add.setVisible(true);
//        add.setActionCommand(Events.SELECT_PROPERTY.name() );
    }

    public void removeElementToTree() {
        model.removeNodeFromParent(getSelectNode());
    }


    public String getSelectTypeNode() {
        TreeSelectionModel treeSelectionModel = tree.getSelectionModel();
        if (treeSelectionModel.getSelectionPath() != null) {
            DefaultMutableTreeNode lastPathComponent = (DefaultMutableTreeNode) treeSelectionModel.getSelectionPath().getLastPathComponent();
            NodeTree userObject = (NodeTree) lastPathComponent.getUserObject();
            return userObject.getTypeFile().getType();
        } else {
            return " ";
        }
    }

    public String getIdSelectNode() {
        TreeSelectionModel treeSelectionModel = tree.getSelectionModel();
        if (treeSelectionModel.getSelectionPath() != null) {
            DefaultMutableTreeNode lastPathComponent = (DefaultMutableTreeNode) treeSelectionModel.getSelectionPath().getLastPathComponent();
            NodeTree userObject = (NodeTree) lastPathComponent.getUserObject();
            return userObject.getID();
        } else {
            return " ";
        }
    }

    public DefaultMutableTreeNode getSelectNode() {
        TreeSelectionModel treeSelectionModel = tree.getSelectionModel();
        if (treeSelectionModel.getSelectionPath() != null) {
            return (DefaultMutableTreeNode) treeSelectionModel.getSelectionPath().getLastPathComponent();
        } else {
            return null;
        }
    }

    public void showMenuOptionProperty(Component component, int x, int y) {
        popOptionProperty.show(component, x, y);
    }

    public void showReceipt(Component component, int x, int y) {
        popAddReceipt.show(component, x, y);
    }

    public void addElementToRoot(NodeTree node) {
        this.nodeRoot.add(new DefaultMutableTreeNode(node));
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        tree.updateUI();
    }

    public void addElementToNode(NodeTree node) {
        if (getSelectNode() != null) {
            DefaultMutableTreeNode selectNode = getSelectNode();
            selectNode.add(new DefaultMutableTreeNode(node));
        }
        tree.updateUI();
    }

    public void loadData(Node root) {
        model.setRoot(builtTreeNode(root));
    }

    private DefaultMutableTreeNode builtTreeNode(Node root) {
        NodeTree node = null;
        node = caseNodeTree(root, node);
        DefaultMutableTreeNode dmtNode = new DefaultMutableTreeNode(node);
        NodeList nodeList = root.getChildNodes();
        for (int count = 0; count < nodeList.getLength(); count++) {
            Node tempNode = nodeList.item(count);
            // make sure it's element node.
            if (tempNode.getNodeType() == Node.ELEMENT_NODE) {
                if (tempNode.hasChildNodes()) {
                    if (tempNode.getNodeName().equals("ID")) {
                        String nodeValue = tempNode.getTextContent();
                        NodeTree userObject = (NodeTree) dmtNode.getUserObject();
                        userObject.setID(nodeValue);
                    }
                    if (!tempNode.getNodeName().equals("ID")) {
                        dmtNode.add(builtTreeNode(tempNode));
                    }
                }
            }
        }
        return dmtNode;
    }

    private NodeTree caseNodeTree(Node root, NodeTree node) {
        switch (root.getNodeName()) {
            case "House":
                node = new NodeTree(TypeFiles.HOUSE, root.getNodeName(), "0");
                break;
            case "Apartment":
                node = new NodeTree(TypeFiles.APARTMENT, root.getNodeName(), "0");
                break;
            case "ElectricityService":
                node = new NodeTree(TypeFiles.SERVICE_ELECTRICITY, root.getNodeName(), "0");
                break;
            case "WrapperService":
                node = new NodeTree(TypeFiles.BILL_SERVICE, root.getNodeName(), "0");
                break;
            case "GasService":
                node = new NodeTree(TypeFiles.SERVICE_GAS, root.getNodeName(), "0");
                break;
            case "WaterService":
                node = new NodeTree(TypeFiles.SERVICE_WATER, root.getNodeName(), "0");
                break;
            case "InternetService":
                node = new NodeTree(TypeFiles.SERVICE_INTERNET, root.getNodeName(), "0");
                break;
                default:
                node = new NodeTree(TypeFiles.HORIZONTAL_PROPERTY_USER, root.getNodeName(), "0");
                    break;
        }
        return node;
    }

}
