package br.com.everis.controlereunioesws;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import br.com.everis.controlereunioesws.exception.ErrorResponse;
import br.com.everis.controlereunioesws.exception.ResponseException;
import br.com.everis.controlereunioesws.model.Reuniao;
import br.com.everis.controlereunioesws.services.IReuniaoService;
import br.com.everis.controlereunioesws.utils.Constants;
import br.com.everis.controlereunioesws.utils.ReuniaoUtils;

@RestController
public class ControleReunioesWS {

	@Autowired
	private IReuniaoService reuniaoService = null;

	@RequestMapping(value = "/gravarReuniao", method = RequestMethod.POST , consumes = MediaType.APPLICATION_JSON_VALUE + "; charset=utf-8" , produces = MediaType.APPLICATION_JSON_VALUE + "; charset=utf-8")
	public ResponseEntity<?> gravarReuniao(@RequestBody String reuniao) throws Exception{
		try{
			Gson gson = new GsonBuilder().setDateFormat(Constants.DATETIME_PATTERN).create();
			Reuniao r = gson.fromJson(reuniao, Reuniao.class);
			reuniaoService.salvar(r);
			return ResponseEntity.ok().build();
		} catch(ResponseException re){
			throw new ResponseException(re.getErrorMessage());
		} catch(NullPointerException npe){
			throw new NullPointerException(npe.getMessage());
		}
	}

	@RequestMapping(value = "/buscarReunioes", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE + "; charset=utf-8", produces = MediaType.APPLICATION_JSON_VALUE + "; charset=utf-8")
	public ResponseEntity<String> buscarReunioes(@RequestParam(value = "data") String data) throws Exception{
		try{
			Gson gson = new GsonBuilder().setDateFormat(Constants.DATETIME_PATTERN).create();
			
			Date dtHoje = ReuniaoUtils.stringToDateTime(data);
			List<Reuniao> lstReunioes = reuniaoService.buscarReunioes(dtHoje);
			String reuniaoJson = gson.toJson(lstReunioes);
			return ResponseEntity.ok().body(reuniaoJson);
		}catch(NullPointerException npe){
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
