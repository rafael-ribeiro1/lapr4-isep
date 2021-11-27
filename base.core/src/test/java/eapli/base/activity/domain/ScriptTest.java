package eapli.base.activity.domain;

import eapli.base.protocolo.application.SDP2021Client;
import eapli.base.protocolo.domain.SDP2021Packet;
import org.junit.Test;

import java.io.IOException;


import static eapli.base.protocolo.domain.SDP2021ProtocolTypeMessages.CODIGO_AVANCAR_FLUXO_PEDIDO;
import static eapli.base.protocolo.domain.SDP2021ProtocolTypeMessages.CODIGO_SCRIPT;

public class ScriptTest {

    @Test(expected=IllegalArgumentException.class)
    public void ensureNullIsNotAllowed() throws IOException {
        System.out.println("-----Test 1 (null)-----\n");
        Script s = new Script(null);
    }
}