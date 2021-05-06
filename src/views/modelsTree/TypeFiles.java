package views.modelsTree;

import javax.swing.*;

public enum TypeFiles {

//    BUILDING(new ImageIcon(TypeFiles.class.getResource("/ImagesTree/building.png")), "Building"),
    HOUSE(new ImageIcon(TypeFiles.class.getResource("/ImagesTree/house.png")), "House"),
    APARTMENT(new ImageIcon(TypeFiles.class.getResource("/ImagesTree/apartment.png")), "Apartment"),
    SERVICE_WATER(new ImageIcon(TypeFiles.class.getResource("/ImagesTree/agua.png")), "ServiceWater"),
    SERVICE_ELECTRICITY(new ImageIcon(TypeFiles.class.getResource("/ImagesTree/luz.png")), "ServiceWater"),
    SERVICE_GAS(new ImageIcon(TypeFiles.class.getResource("/ImagesTree/gas.png")), "ServiceWater"),
    SERVICE_INTERNET(new ImageIcon(TypeFiles.class.getResource("/ImagesTree/internet.png")), "ServiceWater"),
//    APARTMENT(new ImageIcon(TypeFiles.class.getResource("/ImagesTree/apartment.png")), "Apartment"),
//    HORIZONTAL_PROPERTY(new ImageIcon(TypeFiles.class.getResource("/ImagesTree/root.png")), "horizontalProperty"),
    HORIZONTAL_PROPERTY_USER(new ImageIcon(TypeFiles.class.getResource("/ImagesTree/user.png")), "horizontalPropertyUser");
//    POOL(new ImageIcon(TypeFiles.class.getResource("/ImagesTree/pool.png")), "pool"),
//    FIELD(new ImageIcon(TypeFiles.class.getResource("/ImagesTree/campo-de-futbol.png")), "field"),
//    COMMON_ROOM(new ImageIcon(TypeFiles.class.getResource("/ImagesTree/salon.png")), "commonRoom"),
//    USER(new ImageIcon(TypeFiles.class.getResource("/ImagesTree/user.png")),"User");

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
