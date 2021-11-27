package eapli.base.pedido.domain;

import eapli.base.criticidademanagement.domain.EtiquetaCriticidade;
import eapli.base.pedidoservico.domain.FicheiroPedido;
import org.junit.Test;

public class FicheiroPedidoTest {
    @Test(expected = IllegalArgumentException.class)
    public void ensureNullIsNotAllowed() {
        FicheiroPedido local = new FicheiroPedido(null);
    }
}
