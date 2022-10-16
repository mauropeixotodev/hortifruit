package com.fpsoluctionstechs.hortfruitonline.service;

import com.fpsoluctionstechs.hortfruitonline.config.exceptions.NegocioException;
import com.fpsoluctionstechs.hortfruitonline.model.Pedido;
import com.fpsoluctionstechs.hortfruitonline.service.validacao.ValidarPedido;
import com.fpsoluctionstechs.hortfruitonline.service.validacao.imp.ValidarValorMinimoPedidoServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;

import static java.util.Arrays.asList;

@Service
public class ValidarPedidoService {

    @Autowired
    private ValidarValorMinimoPedidoServiceImp validarValorMinimoPedidoServiceImp;

    private List<ValidarPedido> validarPedidoServices;

    @PostConstruct
    void init(){
        validarPedidoServices = asList(validarValorMinimoPedidoServiceImp);
    }

    void validar(Pedido pedido) {
        for(ValidarPedido validarPedido : validarPedidoServices) {
            validarPedido.validar(pedido);
        }
    }

}
