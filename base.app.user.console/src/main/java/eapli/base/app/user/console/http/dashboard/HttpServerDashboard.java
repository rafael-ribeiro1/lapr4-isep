package eapli.base.app.user.console.http.dashboard;

import eapli.base.Application;
import eapli.base.colaboratormanagement.domain.Colaborador;
import eapli.base.colaboratormanagement.domain.EmailInstitucional;
import eapli.base.criticidademanagement.domain.Criticidade;
import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.protocolo.application.SDP2021Client;
import eapli.base.protocolo.domain.SDP2021Packet;
import eapli.base.protocolo.domain.SDP2021ProtocolTypeMessages;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;

import javax.net.ssl.SSLServerSocket;
import javax.net.ssl.SSLServerSocketFactory;
import javax.net.ssl.SSLSocket;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HttpServerDashboard extends Thread {

    private static final int PORT = Application.settings().getDashboardPort();

    private static final String BASE_FOLDER = "www/dashboard";
    private static SSLServerSocket sock;

    public void run() {
        SSLSocket cliSock;
        int port = PORT;

        System.setProperty("javax.net.ssl.trustStore", "dashboard.jks");
        System.setProperty("javax.net.ssl.trustStorePassword", "forgotten");

        System.setProperty("javax.net.ssl.keyStore", "dashboard.jks");
        System.setProperty("javax.net.ssl.keyStorePassword", "forgotten");

        try {
            SSLServerSocketFactory sslF = (SSLServerSocketFactory) SSLServerSocketFactory.getDefault();
            sock = (SSLServerSocket) sslF.createServerSocket(port);
        } catch (IOException ex) {
            System.out.println("Server failed to open local port " + port);
            return;
        }

        while (true) {
            try {
                cliSock = (SSLSocket) sock.accept();
            } catch (IOException e) {
                System.out.println("Server error - " + e.getMessage());
                return;
            }
            HttpDashboardRequest req = new HttpDashboardRequest(cliSock, BASE_FOLDER);
            req.start();
        }
    }

    public static synchronized String getContent() {
        Colaborador colab;
        try {
            AuthorizationService authz = AuthzRegistry.authorizationService();
            String email = (authz.session().orElse(null)).authenticatedUser().email().toString();
            colab = PersistenceContext.repositories().colaboradores().findColaboradorPorEmail(EmailInstitucional.valueOf(email));
            if (colab == null) return null;
        } catch (NullPointerException e) {
            return null;
        }

        List<Criticidade> criticidades = new ArrayList<>();
        for (Criticidade c : PersistenceContext.repositories().criticidades().findAll())
            criticidades.add(c);

        SDP2021Packet request = new SDP2021Packet(Application.settings().getSdpProtocolVersion(),
                SDP2021ProtocolTypeMessages.CODIGO_INFO_DASHBOARD, colab.mecanographicNumber().toString());

        SDP2021Packet response;
        try {
            response = SDP2021Client.simpleCommunication(request, Application.settings().getIpEngineServer(), Application.settings().getEngineServerPort(),
                    Application.settings().getCertClientName());
        } catch (IOException e) {
            return null;
        }

        if (response.code() == SDP2021ProtocolTypeMessages.CODIGO_ERRO)
            return null;

        String data = response.response();
        //String data = "1;2;3;3;2;1;1,84;2,85;3,86;"; // exemplo
        String[] arr = data.split(";");
        if (arr.length < 6) return null;
        int pending, outdated, oneHour, reduced, moderate, urgent;
        try {
            pending = Integer.parseInt(arr[0]);
            outdated = Integer.parseInt(arr[1]);
            oneHour = Integer.parseInt(arr[2]);
            reduced = Integer.parseInt(arr[3]);
            moderate = Integer.parseInt(arr[4]);
            urgent = Integer.parseInt(arr[5]);
        } catch (NumberFormatException e) {
            return null;
        }
        Map<String, Integer> crit = new HashMap<>();
        for (int i = 6; i < arr.length; i++) {
            String[] arr2 = arr[i].split(",");
            if (arr2.length != 2) continue;
            int num;
            String etiqueta;
            try {
                num = Integer.parseInt(arr2[0]);
                etiqueta = getEtiquetaCriticidade(criticidades, Long.parseLong(arr2[1]));
            } catch (NumberFormatException e) {
                continue;
            }
            crit.put(etiqueta, num);
        }

        StringBuilder htmlBuilder = new StringBuilder();
        htmlBuilder.append("<div class=\"main-info\"><h2>Tasks information</h2>" +
                "<span>Collaborator:<br><span class=\"bold\">");
        htmlBuilder.append(String.format("%s (%d)", colab.NomeCurto().toString(), colab.identity()));
        htmlBuilder.append("</span></span></div>");
        htmlBuilder.append("<div class=\"infos\">");
        htmlBuilder.append(String.format("<div class=\"info\"><span class=\"info-val\">%d</span><br><span class=\"info-desc\">pending tasks</span></div>", pending));
        htmlBuilder.append(String.format("<div class=\"info\"><span class=\"info-val\">%d</span><br><span class=\"info-desc\">out of time</span></div>", outdated));
        htmlBuilder.append(String.format("<div class=\"info\"><span class=\"info-val\">%d</span><br><span class=\"info-desc\">in 1 hour</span></div>", oneHour));
        htmlBuilder.append("</div><h3>Distribution by urgency</h3><div class=\"infos\">");
        htmlBuilder.append(String.format("<div class=\"info reduced\"><span class=\"info-val\">%d</span><br><span class=\"info-desc\">REDUCED</span></div>", reduced));
        htmlBuilder.append(String.format("<div class=\"info moderate\"><span class=\"info-val\">%d</span><br><span class=\"info-desc\">MODERATE</span></div>", moderate));
        htmlBuilder.append(String.format("<div class=\"info urgent\"><span class=\"info-val\">%d</span><br><span class=\"info-desc\">URGENT</span></div>", urgent));
        htmlBuilder.append("</div>");
        if (!crit.isEmpty()) {
            htmlBuilder.append("<h3>Distribution by criticality</h3>");
            int cinfo = 4;
            boolean opened = false;
            for (Map.Entry<String,Integer> entry : crit.entrySet()) {
                if (cinfo == 4) {
                    htmlBuilder.append("<div class=\"infos\">");
                    opened = true;
                }

                htmlBuilder.append(String.format("<div class=\"sinfo\"><span class=\"info-val\">%d</span><br><span class=\"info-desc\">%s</span></div>",
                        entry.getValue(), entry.getKey()));

                cinfo--;
                if (cinfo == 0) {
                    cinfo = 4;
                    htmlBuilder.append("</div>");
                    opened = false;
                }
            }
            if (opened) htmlBuilder.append("</div>");
        }
        return htmlBuilder.toString();
    }

    private static String getEtiquetaCriticidade(List<Criticidade> criticidades, Long id) {
        for (Criticidade c : criticidades) {
            if (c.identity().equals(id))
                return c.etiqueta();
        }
        return null;
    }

}
