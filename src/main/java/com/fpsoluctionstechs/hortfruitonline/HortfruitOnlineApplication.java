package com.fpsoluctionstechs.hortfruitonline;

import com.fpsoluctionstechs.hortfruitonline.controller.endereco.request.EnderecoRequest;
import com.fpsoluctionstechs.hortfruitonline.controller.medida.request.MedidaIdRequest;
import com.fpsoluctionstechs.hortfruitonline.controller.pedido.request.PedidoRequest;
import com.fpsoluctionstechs.hortfruitonline.controller.produto.request.ProdutoIdRequest;
import com.fpsoluctionstechs.hortfruitonline.controller.produtoPedido.request.ProdutoPedidoRequest;
import com.fpsoluctionstechs.hortfruitonline.enums.EStatusProduto;
import com.fpsoluctionstechs.hortfruitonline.enums.StatusPedido;
import com.fpsoluctionstechs.hortfruitonline.model.*;
import com.fpsoluctionstechs.hortfruitonline.respository.*;
import com.fpsoluctionstechs.hortfruitonline.service.FilesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@SpringBootApplication
public class HortfruitOnlineApplication {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private  RegrasPedidoRepository regrasPedidoRepository;

	@Autowired
	private FilesService filesService;

	public static void main(String[] args) {
		SpringApplication.run(HortfruitOnlineApplication.class, args);

	}

	@Bean
	public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
		return args -> {

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
		};

	}

}
