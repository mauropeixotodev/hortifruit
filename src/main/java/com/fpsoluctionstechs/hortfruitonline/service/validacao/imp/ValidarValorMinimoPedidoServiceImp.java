package com.fpsoluctionstechs.hortfruitonline.service.validacao.imp;

import com.fpsoluctionstechs.hortfruitonline.config.exceptions.NegocioException;
import com.fpsoluctionstechs.hortfruitonline.model.Pedido;
import com.fpsoluctionstechs.hortfruitonline.model.RegraPedido;
import com.fpsoluctionstechs.hortfruitonline.respository.RegrasPedidoRepository;
import com.fpsoluctionstechs.hortfruitonline.service.validacao.ValidarValorMinimoPedido;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ValidarValorMinimoPedidoServiceImp implements ValidarValorMinimoPedido {

    @Autowired
    private RegrasPedidoRepository regrasPedidoRepository;

    @Override
    public void validar(Pedido pedido) {
        RegraPedido regraPedido = buscarRegraPedido();

        if(regraPedido != null && pedido.getValorTotal().compareTo(regraPedido.getValorMinimo()) < 0)
            throw new NegocioException("Adicione mais produtos para conseguirmos o valor mínimo de R$ "+regraPedido.getValorMinimo().floatValue()+" para a entrega!");

    }

    RegraPedido buscarRegraPedido() {
        return regrasPedidoRepository.findAll().stream().findFirst().orElse(null);
    }
}
