package br.com.everis.controlereunioesws;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import br.com.everis.controlereunioesws.exception.ErrorResponse;
import br.com.everis.controlereunioesws.exception.ResponseException;
import br.com.everis.controlereunioesws.model.Reuniao;
import br.com.everis.controlereunioesws.model.ReuniaoUsuario;
import br.com.everis.controlereunioesws.model.Usuario;
import br.com.everis.controlereunioesws.services.IReuniaoUsuarioService;
import br.com.everis.controlereunioesws.services.IUsuarioService;

@RestController
@RequestMapping("usuario")
public class UsuarioWS {

	@Autowired
	private IUsuarioService usuarioService = null;
	
	@Autowired
	private IReuniaoUsuarioService reuniaoUsuarioService = null; 

	@RequestMapping(value = "/gravarUsuario/{idUsuario}/{idReuniao}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE + "; charset=utf-8", consumes = MediaType.APPLICATION_JSON_VALUE + "; charset=utf-8")
	public ResponseEntity<?> gravarUsuario(@PathVariable("idUsuario") long idUsuario, @PathVariable("idReuniao") long idReuniao, @RequestBody String dados) throws Exception {
		try {
			JsonObject dadosJsonObject = new JsonParser().parse(dados).getAsJsonObject();
			String confirmacao = dadosJsonObject.get("confirmacao").getAsString();
			Usuario usuario = (Usuario) new Gson().fromJson(dadosJsonObject.get("usuario"), Usuario.class);
			
			usuarioService.gravarUsuario(usuario);
			
			Reuniao reuniao = new Reuniao();
			reuniao.setIdReuniao(idReuniao);
			
			ReuniaoUsuario reuniaoUsuario = new ReuniaoUsuario();
			reuniaoUsuario.setConfirmado(confirmacao);
			reuniaoUsuario.getPk().setUsuario(usuario);
			reuniaoUsuario.getPk().setReuniao(reuniao);
			
			reuniaoUsuarioService.confirmarReuniao(reuniaoUsuario);
			
			return ResponseEntity.ok().build();
		} catch (NullPointerException npe) {
			throw new ResponseException(npe.getMessage());
		}
	}

	@RequestMapping(value = "/buscarDadosUsuario", method = RequestMethod.GET, consumes = "text/plain;charset=utf-8", produces = MediaType.APPLICATION_JSON_VALUE+ ";charset=utf-8")
	public ResponseEntity<String> buscarDadosUsuario(@RequestParam("idUsuario") long idUsuario) throws Exception {
		try {
			Gson gson = new GsonBuilder().addSerializationExclusionStrategy(new ExclusionStrategy() {
				@Override
				public boolean shouldSkipField(FieldAttributes pfa) {
					if("java.util.List<br.com.everis.controlereunioesws.model.ReuniaoUsuario>".equals(pfa.getDeclaredType().getTypeName())){
						return true;
					} else if("java.util.List<br.com.everis.controlereunioesws.model.UsuarioQualificacao>".equals(pfa.getDeclaredType().getTypeName())){
						return true;
					}
					return false;
				}
				
				@Override
				public boolean shouldSkipClass(Class<?> paramClass) {
					return false;
				}
			}).create();
			
			Usuario usuario = new Usuario();
			usuario.setIdUsuario(idUsuario);
			Object[] objectRetornado = usuarioService.buscarDadosUsuario(usuario);
			
			JsonArray jsonArray = new JsonArray();
			JsonElement usuarioElement = new JsonParser().parse(gson.toJson(objectRetornado[0]));
			JsonElement reuniaoElement = new JsonParser().parse(gson.toJson(objectRetornado[1]));
			jsonArray.add(usuarioElement);
			jsonArray.add(reuniaoElement);
			
			String usuarioJson = gson.toJson(objectRetornado);

			return ResponseEntity.ok().body(usuarioJson);
		} catch (NullPointerException npe) {
			throw new NullPointerException(npe.getMessage());
		} catch (Exception e) {
			throw new Exception(e.getMessage());
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
