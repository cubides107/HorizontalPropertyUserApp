package views.modelsTree;

import javax.swing.*;

public enum TypeFiles {
    HOUSE(new ImageIcon(TypeFiles.class.getResource("/ImagesTree/house.png")), "House"),
    APARTMENT(new ImageIcon(TypeFiles.class.getResource("/ImagesTree/apartment.png")), "Apartment"),
    SERVICE_WATER(new ImageIcon(TypeFiles.class.getResource("/ImagesTree/agua.png")), "ServiceWater"),
    HORIZONTAL_PROPERTY_USER(new ImageIcon(TypeFiles.class.getResource("/ImagesTree/user.png")), "horizontalPropertyUser"),
    SERVICE_ELECTRICITY(new ImageIcon(TypeFiles.class.getResource("/ImagesTree/luz.png")), "ServiceWater"),
    SERVICE_GAS(new ImageIcon(TypeFiles.class.getResource("/ImagesTree/gas.png")), "ServiceWater"),
    SERVICE_INTERNET(new ImageIcon(TypeFiles.class.getResource("/ImagesTree/internet.png")), "ServiceWater"),
    BILL_SERVICE(new ImageIcon(TypeFiles.class.getResource("/ImagesTree/bill.png")), "BillService");

    private Icon icon;
    private String type;

    TypeFiles(Icon icon, String type) {
        this.icon = icon;
        this.type = type;
    }

    public Icon getIcon() {
        return icon;
    }

    public String getType() {
        return type;
    }
}
