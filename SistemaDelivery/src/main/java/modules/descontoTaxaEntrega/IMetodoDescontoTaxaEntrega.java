package modules.descontoTaxaEntrega;

import model.Pedido;

/**
 *
 * @author lara_
 */

public interface IMetodoDescontoTaxaEntrega {
    public void calcularDesconto(Pedido pedido);
    public boolean seAplica(Pedido pedido);
}

