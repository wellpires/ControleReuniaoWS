package br.com.everis.controlereunioesws;

import java.text.ParseException;
import java.util.List;

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
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import br.com.everis.controlereunioesws.exception.ErrorResponse;
import br.com.everis.controlereunioesws.exception.ResponseException;
import br.com.everis.controlereunioesws.model.Arquivo;
import br.com.everis.controlereunioesws.services.IArquivoService;
import br.com.everis.controlereunioesws.utils.Constants;

@RestController
@RequestMapping("/arquivo/")
public class ArquivoWS {

	@Autowired
	private IArquivoService arquivoService = null;

	@RequestMapping(value = "/gravarArquivos", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE + "; charset=utf-8", consumes = MediaType.APPLICATION_JSON_VALUE + "; charset=utf-8")
	public ResponseEntity<?> gravarArquivos(@RequestBody String arquivoJson) throws Exception {
		try {
			Gson gson = new GsonBuilder().setDateFormat(Constants.DATETIME_PATTERN).create();
			List<Arquivo> lstArquivos = gson.fromJson(arquivoJson, new TypeToken<List<Arquivo>>(){}.getType());
			arquivoService.gravarArquivos(lstArquivos);
			return ResponseEntity.ok().build();
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
