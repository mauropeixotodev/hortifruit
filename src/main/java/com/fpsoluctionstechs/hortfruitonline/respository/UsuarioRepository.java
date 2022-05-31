package com.fpsoluctionstechs.hortfruitonline.respository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fpsoluctionstechs.hortfruitonline.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

	Optional<Usuario> findByUsername(String username);

}
