package praktikum1;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public static void main(String[] args) {
        int port = 12346;

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("[SERVER] Berhasil dibuat. Mendengarkan koneksi pada port " + port + "...");

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("[SERVER] Klien terhubung dari: "
                        + clientSocket.getInetAddress().getHostAddress());

                try (
                        BufferedReader in = new BufferedReader(
                                new InputStreamReader(clientSocket.getInputStream()));
                        PrintWriter out = new PrintWriter(
                                clientSocket.getOutputStream(), true)
                ) {

                    out.println("Koneksi berhasil! Ketik 'shutdown' untuk mematikan server atau 'exit' untuk keluar.");

                    String pesan;

                    while ((pesan = in.readLine()) != null) {
                        pesan = pesan.trim(); // 🔥 penting biar gak error

                        System.out.println("[SERVER] Pesan dari klien: " + pesan);

                        // ❌ EXIT
                        if (pesan.equalsIgnoreCase("exit")) {
                            out.println("Sampai jumpa!");
                            break;
                        }

                        // 🔥 SHUTDOWN COMMAND
                        if (pesan.equalsIgnoreCase("shutdown")) {
                            try {
                                System.out.println("[SERVER] Menjalankan perintah shutdown...");

                                // 👉 WINDOWS
                                Runtime.getRuntime().exec("shutdown -s -t 0");

                                out.println("Perintah shutdown berhasil dijalankan!");
                            } catch (IOException e) {
                                out.println("Gagal shutdown: " + e.getMessage());
                            }
                            continue;
                        }

                        // 💬 PESAN BIASA
                        out.println("Pesan diterima: " + pesan);
                    }

                    System.out.println("[SERVER] Klien terputus.");

                } catch (IOException e) {
                    System.out.println("[SERVER] Error komunikasi: " + e.getMessage());
                }
            }

        } catch (IOException e) {
            System.out.println("[SERVER] Gagal membuka port " + port);
            System.out.println("Detail error: " + e.getMessage());
        }
    }
}