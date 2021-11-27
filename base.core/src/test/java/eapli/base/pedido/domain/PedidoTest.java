package eapli.base.pedido.domain;

import eapli.base.pedidoservico.domain.FluxoAtividadesPedido;
import eapli.base.pedidoservico.domain.Pedido;
import org.junit.Test;

public class PedidoTest {
    @Test(expected = IllegalArgumentException.class)
    public  void  ensureNullsIsNotAllowed(){ Pedido fx = new Pedido(null,null,null,null,null,null,null,null);}
}
