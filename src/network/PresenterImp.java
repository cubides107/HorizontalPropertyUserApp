package network;

import org.w3c.dom.Node;

public interface PresenterImp {

    void showAlertUser(boolean option);

    void loadDataUser(Node root);
    void addNewWaterService(int waterServiceID);
    void addNewElectricityService(int electricityServiceID);
    void addNewGasService(int gasServiceID);
    void addNewInternetService(int internetServiceID);
    void addNewBillService(int idBillService);


}
