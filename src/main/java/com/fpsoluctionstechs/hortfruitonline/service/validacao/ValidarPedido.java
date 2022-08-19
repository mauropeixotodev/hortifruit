package com.fpsoluctionstechs.hortfruitonline.service.validacao;

import com.fpsoluctionstechs.hortfruitonline.config.exceptions.NegocioException;
import com.fpsoluctionstechs.hortfruitonline.model.Pedido;

public interface ValidarPedido {
    void validar(Pedido pedido) throws NegocioException;
}
