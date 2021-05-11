package views.Center;

import com.sun.pdfview.PDFFile;
import com.sun.pdfview.PDFPage;
import com.sun.pdfview.PagePanel;
import views.MainFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.logging.Logger;

public class PdfPanel extends JPanel{


    private PDFPage page;
    private PagePanel pagePanel;

    private JButton button;

    public PdfPanel() throws IOException {
        setSize(500,500);
//        button = new JButton("Aceptar");
//        pagePanel = new PagePanel();
//        JFileChooser filecho = new JFileChooser();
//        filecho.showOpenDialog(null);
//        File selectedFile = filecho.getSelectedFile();
////        File file = new File("data/Report.pdf");
//        RandomAccessFile raf = new RandomAccessFile(selectedFile, "r");
//        FileChannel channel = raf.getChannel();
//        ByteBuffer buf = channel.map(FileChannel.MapMode.READ_ONLY, 0, channel.size());
//        PDFFile pdffile = new PDFFile(buf);
//        page = pdffile.getPage(0);
//        pagePanel.showPage(page);
//        add(pagePanel);
//        repaint();
//        setVisible(true);
//        button.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                repaint();
//            }
//        });
//        pagePanel.add(button);
        setup();
        setVisible(true);

    }

    public void showPDF() {
        pagePanel.showPage(page);
        repaint();
    }

    private void setup() {

        Thread thread = new Thread(() -> {
            try {
                PagePanel panel = new PagePanel();



                add(panel);
                panel.repaint();
                panel.validate();



                //load a pdf from a byte buffer
                File file = new File("data/Report.pdf");
//                FileReader fleAu8x = new FileReader(file);
                RandomAccessFile raf = new RandomAccessFile(file, "r");
                FileChannel channel = raf.getChannel();
                ByteBuffer buf = channel.map(FileChannel.MapMode.READ_ONLY, 0, channel.size());
                PDFFile pdffile = new PDFFile(buf);

                // show the first page
                PDFPage page = pdffile.getPage(0);

                Thread.sleep(6000);


                panel.showPage(page);
                file = null;

            } catch (FileNotFoundException ex) {
            } catch (IOException ex) {
            } catch (InterruptedException ex) {
            }

        });
        thread.start();

    }

    public static void main(String[] args) throws IOException {
        new PdfPanel();
    }

}
