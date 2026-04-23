package praktikum1;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        
        String host = "10.8.0.7"; // Localhost
        int port = 12346;

        try (Socket socket = new Socket(host, port);
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             Scanner scanner = new Scanner(System.in)) {

            System.out.println("[CLIENT] Terhubung ke server " + host + ":" + port);

            // Membuat thread terpisah agar klien bisa terus membaca balasan dari server
            // tanpa terblokir oleh input scanner (ketikan pengguna)
            Thread readThread = new Thread(() -> {
                try {
                    String serverResponse;
                    while ((serverResponse = in.readLine()) != null) {
                        System.out.println("\n[SERVER MEMBALAS] " + serverResponse);
                        System.out.print("Masukkan pesan (atau 'exit'): "); // Memberikan prompt ulang
                        if ("Sampai jumpa!".equals(serverResponse)) {
                            System.exit(0);
                        }
                    }
                } catch (IOException e) {
                    System.out.println("[CLIENT] Koneksi ke server ditutup.");
                    System.exit(0);
                }
            });
            readThread.start();

            // Loop utama untuk membaca ketikan pengguna dan mengirimkannya ke server
            while (true) {
                System.out.print("Masukkan pesan (atau 'exit'): ");
                String input = scanner.nextLine();
                out.println(input);
                if ("exit".equalsIgnoreCase(input)) {
                    break;
                }
            }

        } catch (IOException e) {
            System.out.println("[CLIENT] Tidak dapat terhubung ke server (Pastikan server sudah berjalan): " + e.getMessage());
        }
    }
}
