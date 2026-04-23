package praktikum3;

import java.awt.Robot;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

/**
 *
 * @author luqmanaffandi
 */
public class JavaUDP {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        try {
            // Buat socket SEKALI saja
            DatagramSocket ds = new DatagramSocket(45678);
            System.out.println("Server UDP listening on port 45678...");

            while (true) {
                // Buffer lebih besar
                byte[] buffer = new byte[1024];

                DatagramPacket dp = new DatagramPacket(buffer, buffer.length);

                // Terima data
                ds.receive(dp);

                // Ambil panjang data yang benar
                String s = new String(dp.getData(), 0, dp.getLength());

                System.out.println("Dari " + dp.getAddress() + ":" + dp.getPort());
                System.out.println("Pesan: " + s.trim());
                System.out.println("----------------------");
                String[] pisah=s.split("\\,");
                int x=Integer.parseInt(pisah[0]);
                int y=Integer.parseInt(pisah[1]);
                Robot r=new Robot();
                r.mouseMove(x, y);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}