package br.com.agendamento.api.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.agendamento.api.constantes.ConstanteController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * Controller inicial de boas vindas.
 * 
 * @author Salatiel Fiore
 */
@Api(value = "Home", tags = { "Home" })
@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class HomeController {

	/**
	 * Endpoint que retorna um Hello World.
	 * 
	 * @return String
	 */
	@ApiOperation(value = "Home")
	@ApiResponses(@ApiResponse(code = 200, message = ConstanteController.OPERACAO_REALIZADA_COM_SUCESSO))
	@GetMapping("/")
	public String home() {
		return "Hello World";
	}

}
