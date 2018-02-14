import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.*;
import java.net.BindException;
import java.net.InetSocketAddress;

public class Webserver {

    HttpServer server;

    Webserver(int port) {
        try {
            server = HttpServer.create(new InetSocketAddress(port), 0);
            server.createContext("/", new Handler());
            server.setExecutor(null);
            server.start();
        } catch (IOException e) {
            System.out.println("Could not bind to the port, make sure it's not bound already.");
            System.exit(0);
        }
    }

    static class Handler implements HttpHandler {

        @Override
        public void handle(HttpExchange exchange) throws IOException {
            File file = new File("index.html");
            exchange.sendResponseHeaders(200, 0);
            OutputStream os = exchange.getResponseBody();
            FileInputStream fs = new FileInputStream(file);
            final byte[] buffer = new byte[0x10000];
            int count = 0;
            while ((count = fs.read(buffer)) >= 0) {
                os.write(buffer,0,count);
            }
            fs.close();
            os.close();
        }
    }

}
