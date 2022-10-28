package com.fpsoluctionstechs.hortfruitonline.controller.configuracao;

import com.fpsoluctionstechs.hortfruitonline.controller.categoria.response.CategoriaResponseGet;
import com.fpsoluctionstechs.hortfruitonline.model.RegraPedido;
import com.fpsoluctionstechs.hortfruitonline.model.Usuario;
import com.fpsoluctionstechs.hortfruitonline.respository.RegrasPedidoRepository;
import com.fpsoluctionstechs.hortfruitonline.respository.UsuarioRepository;
import com.fpsoluctionstechs.hortfruitonline.service.FilesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("configuracao")
public class ConfiguracaoController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private RegrasPedidoRepository regrasPedidoRepository;

    @Autowired
    private FilesService filesService;

    @GetMapping("")
    public String index() {


        filesService.init();

        Optional<Usuario> usuarioOpitional = usuarioRepository.findById((long) 1);
        if (usuarioOpitional.isEmpty()) {

            BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
            Usuario usuario1 = Usuario.builder().username("mauromyfruits@fpsolutions.com")
                    .password(bCryptPasswordEncoder.encode("mauromyfruitsadmin2022")).build();
            usuarioRepository.save(usuario1);
            Usuario usuario2 = Usuario.builder().username("damiaomyfruits@fpsolutions.com")
                    .password(bCryptPasswordEncoder.encode("damiaomyfruitsadmin2022")).build();
            usuarioRepository.save(usuario2);

        }


        List<RegraPedido> regraPedidos = regrasPedidoRepository.findAll();
        if(regraPedidos.isEmpty()){
            regrasPedidoRepository.save(RegraPedido.builder().valorMinimo(BigDecimal.valueOf(20)).build());
        }

        return "configuração executada com sucesso";

    }
}
