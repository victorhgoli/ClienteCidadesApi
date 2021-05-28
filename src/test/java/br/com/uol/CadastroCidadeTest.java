package br.com.uol;

import static io.restassured.RestAssured.given;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;

import br.com.uol.domain.model.Cidade;
import br.com.uol.domain.repository.CidadeRepository;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class CadastroCidadeTest {

	@LocalServerPort
	private int port;

	@Autowired
	private CidadeRepository cidadeRepository;

	private Cidade cidadeSaoPaulo;

	@BeforeEach
	public void setUp() {
		RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
		RestAssured.port = port;
		RestAssured.basePath = "/cidades";

		prepararDados();
	}

	@Test
	public void deveRetornarStatus201_QuandoCadastrarCidade() {
		String cidadeJson = "{\"nome\":\"Araguari\",\"estado\": \"MG\"}";

		given()
			.body(cidadeJson)
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
		.when()
			.post()
		.then()
			.statusCode(HttpStatus.CREATED.value());
	}

	@Test
	public void deveRetornarUmaCidade_QuandoConsultarPorNome() {
		given()
			.param("nome", "São Paulo")
			.accept(ContentType.JSON)
		.when()
			.get()
		.then()
			.statusCode(HttpStatus.OK.value())
			.body("", Matchers.hasSize(1));
	}

	@Test
	public void deveRetornarUmaCidade_QuandoConsultarPorEstado() {
		given()
			.param("estado", "SP")
			.accept(ContentType.JSON)
		.when()
			.get()
		.then()
			.statusCode(HttpStatus.OK.value())
			.body("", Matchers.hasSize(1));
	}

	private void prepararDados() {
		cidadeRepository.deleteAll();

		cidadeSaoPaulo = new Cidade();
		cidadeSaoPaulo.setEstado("SP");
		cidadeSaoPaulo.setNome("São Paulo");

		cidadeRepository.save(cidadeSaoPaulo);
	}
}