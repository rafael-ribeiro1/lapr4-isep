package eapli.base.app.user.console.http.dashboard;

import javax.net.ssl.SSLSocket;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.SocketException;

public class HttpDashboardRequest extends Thread {

    String baseFolder;
    SSLSocket sock;
    DataInputStream inS;
    DataOutputStream outS;

    public HttpDashboardRequest(SSLSocket s, String f) {
        baseFolder = f;
        sock = s;
    }

    public void run() {
        try {
            outS = new DataOutputStream(sock.getOutputStream());
            inS = new DataInputStream(sock.getInputStream());
        } catch (IOException ex) {
            System.out.println("Thread error on data streams creation");
        }
        try {
            HTTPmessage request = new HTTPmessage(inS);
            HTTPmessage response = new HTTPmessage();

            if (request.getMethod().equals("GET")) {
                if (request.getURI().equals("/info")) {
                    String content = HttpServerDashboard.getContent();
                    if (content != null) {
                        response.setContentFromString(content, "text/html");
                        response.setResponseStatus("200 Ok");
                    } else {
                        response.setContentFromString(
                                "<span id=\"loading\">Error loading content, trying again...</span>", "text/html");
                        response.setResponseStatus("404 Not Found");
                    }
                } else {
                    String fullname = baseFolder/* + "/"*/;
                    if (request.getURI().equals("/")) fullname += "/index.html";
                    else fullname += request.getURI();
                    //System.out.println(fullname);

                    if (response.setContentFromFile(fullname)) {
                        response.setResponseStatus("200 Ok");
                    } else {
                        response.setContentFromString(
                                "<html><body><h1>404 File not found</h1></body></html>", "text/html");
                        response.setResponseStatus("404 Not Found");
                    }
                }
                response.send(outS);
            }
        } catch (IOException ex) {
            /*System.out.println("Thread error when reading request");
            ex.printStackTrace();*/
        }
        try {
            sock.close();
        } catch (IOException ex) {
            System.out.println("Thread error when closing socket");
        }
    }

}
