package praktikum2;
import java.net.InetAddress;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class Client {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        try {
            // Ganti "192.168.x.x" dengan IP address VPN teman Anda
            InetAddress ia = InetAddress.getByName("10.8.0.7"); 
            int Port = 45678;
            String s = "Pesan ini dari UDP"; 
            byte[] b = s.getBytes();

            System.out.println("Mengirim pesan: " + s);
            DatagramPacket dp = new DatagramPacket(b, b.length, ia, Port);
            DatagramSocket sender = new DatagramSocket();
            sender.send(dp);
            System.out.println("Pesan terkirim ke port " + Port);
            sender.close();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}