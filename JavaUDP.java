import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class JavaUDP {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        try {
            System.out.println("Server UDP mendengarkan pada port 45678...");
//            byte buffer = new byte[65536];
//            DatagramPacket incoming = new DatagramPacket(buffer,buffer.length);
            byte[] buffer= new byte[4019];
            DatagramPacket dp=new DatagramPacket(buffer,buffer.length);
            DatagramSocket ds=new DatagramSocket(45678);
            System.out.println("Menunggu pesan...");
            ds.receive(dp);
            byte[] data = dp.getData();
            String s = new String(data, 0, data.length);
            System.out.println("Port " + dp.getPort() + " on " +
            dp.getAddress() + " sent this message:");
            System.out.println(s.trim());
            ds.close();
            
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
}