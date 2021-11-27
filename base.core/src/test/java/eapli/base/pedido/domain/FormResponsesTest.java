package eapli.base.pedido.domain;

import eapli.base.pedidoservico.domain.FluxoAtividadesPedido;
import eapli.base.pedidoservico.domain.FormResponses;
import org.junit.Test;

public class FormResponsesTest {
    @Test(expected = IllegalArgumentException.class)
    public  void  ensureNullsIsNotAllowed(){ FormResponses fx = new FormResponses(null);}
}
