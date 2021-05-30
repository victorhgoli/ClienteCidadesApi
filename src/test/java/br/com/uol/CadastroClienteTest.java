package br.com.uol;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

import java.time.LocalDate;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;

import br.com.uol.domain.model.Cidade;
import br.com.uol.domain.model.Cliente;
import br.com.uol.domain.model.SexoCliente;
import br.com.uol.domain.repository.CidadeRepository;
import br.com.uol.domain.repository.ClienteRepository;
import br.com.uol.util.DatabaseCleaner;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class CadastroClienteTest {

	@LocalServerPort
	private int port;
	
	@Autowired
	private CidadeRepository cidadeRepository;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private DatabaseCleaner databaseCleaner;
	
	private Cidade cidadeSaoPaulo;
	private Cliente clienteJoao;
	
	@BeforeEach
	public void setUp() {
		RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
		RestAssured.port = port;
		RestAssured.basePath = "/clientes";

		databaseCleaner.clearTables();
		prepararDados();
	}

	@Test
	public void deveRetornarStatus201_QuandoCadastrarCliente() {
		String clienteJson = "{\"nome\":\"Tiao\",\"sexo\": \"MASCULINO\", \"dataNascimento\": \"1990-08-22\", \"cidade\":{\"id\": "+ cidadeSaoPaulo.getId()+"}}";
		
		given()
			.body(clienteJson)
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
		.when()
			.post()
		.then()
			.statusCode(HttpStatus.CREATED.value());
	}

	@Test
	public void deveRetornarUmCliente_QuandoConsultarPorNome() {
		given()
			.param("nome", "Joao")
			.accept(ContentType.JSON)
		.when()
			.get()
		.then()
			.statusCode(HttpStatus.OK.value()).body("", Matchers.hasSize(1));
	}

	@Test
	public void deveRetornarUmCliente_QuandoConsultarClientePorId() {
		given()
			.pathParam("clienteId", clienteJoao.getId())
			.accept(ContentType.JSON)
		.when()
			.get("/{clienteId}")
		.then()
			.statusCode(HttpStatus.OK.value())
			.body("nome", equalTo(clienteJoao.getNome()));
	}
	
	@Test
	public void deveRetornarStatus204_QuandoRemoverCliente() {
		given()
			.pathParam("clienteId", clienteJoao.getId())
		.when()
			.delete("/{clienteId}")
		.then()
			.statusCode(HttpStatus.NO_CONTENT.value());
	}
	
	@Test
	public void deveValidarNomeCliente_QuandoAlterarNome() {
		String body = "{\"nome\":\"Dino da Silva Sauro\",\"sexo\": \"MASCULINO\", \"dataNascimento\": \"1990-08-22\", \"cidade\":{\"id\": "+ cidadeSaoPaulo.getId()+"}}";
		
		given()
			.pathParam("clienteId", clienteJoao.getId())
			.body(body)
			.accept(ContentType.JSON)
			.contentType(ContentType.JSON)
		.when()
			.put("/{clienteId}")
		.then()
			.statusCode(HttpStatus.OK.value())
			.body("nome", Matchers.equalTo("Dino da Silva Sauro"));
	}

	private void prepararDados() {
		cidadeSaoPaulo = new Cidade();
		cidadeSaoPaulo.setEstado("SP");
		cidadeSaoPaulo.setNome("São Paulo");

		cidadeRepository.save(cidadeSaoPaulo);

		clienteJoao = new Cliente();
		clienteJoao.setNome("Joao");
		clienteJoao.setCidade(cidadeSaoPaulo);
		clienteJoao.setDataNascimento(LocalDate.of(2001, 05, 10));
		clienteJoao.setSexo(SexoCliente.MASCULINO);

		clienteRepository.save(clienteJoao);

	}

}
