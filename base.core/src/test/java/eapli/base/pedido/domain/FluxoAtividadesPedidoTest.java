package eapli.base.pedido.domain;

import eapli.base.pedidoservico.domain.FluxoAtividadesPedido;
import org.junit.Test;

public class FluxoAtividadesPedidoTest {

    @Test(expected = IllegalArgumentException.class)
    public  void  ensureNullsIsNotAllowed(){ FluxoAtividadesPedido fx = new FluxoAtividadesPedido(null,null,null);}
}
