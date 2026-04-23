package praktikum3;

import java.awt.MouseInfo;
import java.awt.Point;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 *
 * @author luqmanaffandi
 */
public class Client {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        try {
            // GANTI IP ini dengan IP VPN teman Anda
            InetAddress ia = InetAddress.getByName("10.8.0.12"); // Ganti ke IP teman
            int Port = 45678;
            for(;;){
                Point point = MouseInfo.getPointerInfo().getLocation();

                int x = point.x;
                int y = point.y;
                System.out.println(x+","+y);
                String s = x+","+y; 
                byte[] b = s.getBytes();
                DatagramPacket dp = new DatagramPacket(b, b.length, ia, Port);
                DatagramSocket sender = new DatagramSocket();
                sender.send(dp);
                sender.close();
                Thread.sleep(250); // biar tidak terlalu cepat
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }

}