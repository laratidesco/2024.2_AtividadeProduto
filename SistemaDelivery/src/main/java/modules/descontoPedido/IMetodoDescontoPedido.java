package modules.descontoPedido;

import model.Pedido;

/**
 *
 * @author lara_
 */

public interface IMetodoDescontoPedido {
    public void calcularDesconto(Pedido pedido);
    public boolean seAplica(Pedido pedido);
}
