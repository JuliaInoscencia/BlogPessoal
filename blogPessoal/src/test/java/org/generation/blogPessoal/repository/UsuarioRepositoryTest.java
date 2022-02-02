package org.generation.blogPessoal.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

import org.generation.blogPessoal.model.Usuario;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UsuarioRepositoryTest {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@BeforeAll
	void start() {
		//Given
		usuarioRepository.save(new Usuario("Pedro Veloso","pv@email.com.br","765432"));
		usuarioRepository.save(new Usuario("Amanda Domingues","ad@email.com.br","765432"));
		usuarioRepository.save(new Usuario("Daiana Castanhares","dc@email.com.br","765432"));
		usuarioRepository.save(new Usuario("Caetano Veloso","cv@email.com.br","765432"));
	}
	
	@Test
	@DisplayName("Retorna um usuário")
	public void deveRetornarUmUsuario() {
		Optional<Usuario> usuario = usuarioRepository.findByUsuario("pv@email.com.br"); //When
		assertTrue(usuario.get().getUsuario().equals("pv@email.com.br")); //Then
	}
	@Test
	@DisplayName("Retorna verdadeiro para usuários não existentes")
	void deveRetornarTrueParaUsuarioNaoExistente() {
		Optional<Usuario> optional = usuarioRepository.findByUsuario("Douglas@email.com.br"); //When
		assertFalse(optional.isPresent()); //Then
	}
	@Test
	@DisplayName("Retorna 2 usuários com nome Veloso")
	void retornaDoisUsuariosComNomeVeloso() {
		//When
		List<Usuario> listaDeVelosos = usuarioRepository.findAllByNomeContainingIgnoreCase("veloso");
		//Then
		assertEquals(2, listaDeVelosos.size());
		assertTrue(listaDeVelosos.get(0).getNome().equals("Pedro Veloso"));
		assertTrue(listaDeVelosos.get(1).getNome().equals("Caetano Veloso"));
	}
	@Test
	@DisplayName("Validar o tamanho da lista")
	void validaSizeDaListaRetorna4Elementos() {
		List<Usuario> lista = usuarioRepository.findAll(); //When
		assertEquals(4, lista.size()); //Then
	}

}
