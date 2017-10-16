package br.com.everis.controlereunioesws;

import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import br.com.everis.controlereunioesws.exception.ErrorResponse;
import br.com.everis.controlereunioesws.exception.ResponseException;
import br.com.everis.controlereunioesws.model.Permissao;
import br.com.everis.controlereunioesws.services.IPermissaoService;
import br.com.everis.controlereunioesws.utils.Constants;

@RestController
@RequestMapping(value = "/permissao/")
public class PermissaoWS {
	
	@Autowired
	private IPermissaoService permissaoService = null;
	
	@RequestMapping(value = "/listarPermissoes", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE + "; charset=utf-8")
	public ResponseEntity<String> listarPermissoes() throws Exception{
		try {
			Gson gson = new GsonBuilder().setDateFormat(Constants.DATETIME_PATTERN).create();
			List<Permissao> lstPermissoes;
			lstPermissoes = permissaoService.listarPermissoes();
			String permissaoJson = gson.toJson(lstPermissoes);
			return ResponseEntity.ok().body(permissaoJson);
		} catch (ResponseException re) {
			throw new ResponseException(re.getErrorMessage());
		} catch (NullPointerException npe) {
			throw new NullPointerException(npe.getMessage());
		} catch (ParseException e) {
			throw new ParseException(e.getMessage(), 0);
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
