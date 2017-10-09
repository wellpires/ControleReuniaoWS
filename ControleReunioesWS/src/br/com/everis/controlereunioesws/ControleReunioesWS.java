package br.com.everis.controlereunioesws;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

import br.com.everis.controlereunioesws.exception.ErrorResponse;
import br.com.everis.controlereunioesws.exception.ResponseException;
import br.com.everis.controlereunioesws.model.Permissao;
import br.com.everis.controlereunioesws.services.IPermissaoService;

@RestController
public class ControleReunioesWS {

	@Autowired
	private IPermissaoService permissaoService = null;

	@RequestMapping(value = "/mostrarMensagem", produces = {
			MediaType.TEXT_PLAIN_VALUE + "; charset=utf-8" }, method = RequestMethod.GET)
	public String mostrarMensagem() {
		return "MENSAGEM TESTE";
	}

	@RequestMapping(value = "/gravarPermissao", produces = {
			MediaType.APPLICATION_JSON_VALUE + "; charset=utf-8" }, consumes = {
					MediaType.APPLICATION_JSON_VALUE + "; charset=utf-8" }, method = RequestMethod.POST)
	public ResponseEntity<?> gravarPermissao(@RequestBody String permissao) throws ResponseException {
		try {
			Permissao p = new Gson().fromJson(permissao, Permissao.class);
			permissaoService.gravar(p);
			return ResponseEntity.ok().build();
		} catch (NullPointerException npe) {
			throw new NullPointerException(npe.getMessage());
		} catch (Exception e) {
			throw new ResponseException(e.getMessage());
		}
	}

	@ExceptionHandler(ResponseException.class)
	public ResponseEntity<ErrorResponse> exceptionHandler(Exception ex) {
		ErrorResponse error = new ErrorResponse();
		error.setErrorCode(HttpStatus.PRECONDITION_FAILED.value());
		error.setMessage(ex.getMessage());
		return new ResponseEntity<ErrorResponse>(error, HttpStatus.OK);
	}

	@ExceptionHandler(NullPointerException.class)
	public ResponseEntity<ErrorResponse> nullPointerException(NullPointerException npe) {
		ErrorResponse error = new ErrorResponse();
		error.setErrorCode(HttpStatus.PRECONDITION_FAILED.value());
		error.setMessage(npe.getMessage());
		return new ResponseEntity<ErrorResponse>(error, HttpStatus.OK);
	}

}
