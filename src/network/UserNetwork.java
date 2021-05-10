package network;

import java.io.*;
import java.net.Socket;

public class UserNetwork {

    public static final int PORT = 8000;

    private PresenterImp presenterImp;
    private Socket socket;

    private DataOutputStream outputChanel;
    private DataInputStream inputChanel;


    public UserNetwork(PresenterImp presenterImp) throws IOException {
        this.presenterImp = presenterImp;
        socket = new Socket("localHost", PORT);
        outputChanel = new DataOutputStream(socket.getOutputStream());
        inputChanel = new DataInputStream(socket.getInputStream());
        readResponseToServer();
    }

    public void readResponseToServer() {
        new Thread(() -> {
            while (!socket.isClosed()) {
                try {
                    if (inputChanel.available() > 0) {
                        String message = inputChanel.readUTF();
                        manageRequest(message);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public void writeInt(int number) {
        try {
            outputChanel.writeInt(number);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void startToReadFile(String path) throws IOException {
//            String nameFile = inputChanel.readUTF();
        int sizeFile = inputChanel.readInt();

//            System.out.println("Tamaño:" + sizeFile + " Nombre: " + nameFile + "/n"
//                    + "Recibiendo Archivo....");

        BufferedOutputStream outputChannelFiles = new BufferedOutputStream(new FileOutputStream(path));
        BufferedInputStream bufferedInputStream = new BufferedInputStream(inputChanel);

        byte[] buffer = new byte[sizeFile];
        bufferedInputStream.read(buffer);

//        ByteArrayOutputStream out = new ByteArrayOutputStream(buffer.length);
//        out.write(buffer, 0, buffer.length);
        outputChannelFiles.write(buffer);
        outputChannelFiles.flush();
        outputChannelFiles.close();
        System.out.println("Archivo recibido");
//        return out;
    }

    public void writeUTF(String message) {
        try {
            outputChanel.writeUTF(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void manageRequest(String message) throws IOException {
        switch (message) {
            case "NEW_CLIENT":
                boolean isExistUser = inputChanel.readBoolean();
                presenterImp.showAlertUser(isExistUser,inputChanel.readUTF());
                if (isExistUser) {
                    startToReadFile("data/PropertiesUser.xml");
                }
                break;
            case "ADD_SERVICE_WATER":
                presenterImp.addNewWaterService(inputChanel.readInt());
                break;
            case "ADD_SERVICE_ELECTRICITY":
                presenterImp.addNewElectricityService(inputChanel.readInt());
                break;
            case "ADD_SERVICE_GAS":
                presenterImp.addNewGasService(inputChanel.readInt());
                break;
            case "ADD_SERVICE_INTERNET":
                presenterImp.addNewInternetService(inputChanel.readInt());
                break;
            case "ADD_NEW_BILL":
                presenterImp.addNewBillService(inputChanel.readInt());
                break;
            case "REFRESH_PROPERTIES_USER":
                startToReadFile("data/PropertiesUser.xml");
                presenterImp.loadDataUser();
                break;
            case "REPORT1":
                startToReadFile("data/Report.pdf");
                break;


        }
    }
}
