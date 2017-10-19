package br.com.everis.controlereunioesws;

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
import br.com.everis.controlereunioesws.model.Cargo;
import br.com.everis.controlereunioesws.model.Permissao;
import br.com.everis.controlereunioesws.model.Usuario;
import br.com.everis.controlereunioesws.services.ICargoService;
import br.com.everis.controlereunioesws.services.IPermissaoService;
import br.com.everis.controlereunioesws.services.IUsuarioService;
import br.com.everis.controlereunioesws.utils.Constants;

@RestController
@RequestMapping("/usuario/")
public class UsuarioWS {

	@Autowired
	private IUsuarioService usuarioService = null;
	
	@Autowired
	private ICargoService cargoService = null;
	
	@Autowired
	private IPermissaoService permissaoService = null;

	@RequestMapping(value = "/gravarUsuarios", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE + "; charset=utf-8", consumes = MediaType.APPLICATION_JSON_VALUE + "; charset=utf-8")
	public ResponseEntity<?> gravarUsuarios(@RequestBody String usuariosJson) throws Exception {
		try {
			Gson gson = new GsonBuilder().setDateFormat(Constants.DATETIME_PATTERN).create();
			List<Usuario> lstUsuarios = gson.fromJson(usuariosJson, new TypeToken<List<Usuario>>() {}.getType());
			
			for(Usuario u : lstUsuarios){
				Cargo cargo = cargoService.buscarCargo(u.getCargo().getIdCargo());
				u.setCargo(cargo);
				
				Permissao permissao = permissaoService.buscarPermissao(u.getPermissao().getIdPermissao());
				u.setPermissao(permissao);
			}
			
			lstUsuarios = usuarioService.gravarUsuarios(lstUsuarios);
			String json = gson.toJson(lstUsuarios, new TypeToken<List<Usuario>>() {}.getType());
			return ResponseEntity.ok().body(json);
		} catch (NullPointerException npe) {
			throw new NullPointerException(npe.getMessage());
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
