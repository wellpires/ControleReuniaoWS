package br.com.everis.controlereunioesws;

import java.sql.BatchUpdateException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

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

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.reflect.TypeToken;

import br.com.everis.controlereunioesws.exception.ErrorResponse;
import br.com.everis.controlereunioesws.exception.ResponseException;
import br.com.everis.controlereunioesws.model.Arquivo;
import br.com.everis.controlereunioesws.model.Reuniao;
import br.com.everis.controlereunioesws.model.ReuniaoArquivoUsuario;
import br.com.everis.controlereunioesws.model.ReuniaoUsuario;
import br.com.everis.controlereunioesws.model.ReuniaoUsuarioPK;
import br.com.everis.controlereunioesws.model.Usuario;
import br.com.everis.controlereunioesws.services.IArquivoService;
import br.com.everis.controlereunioesws.services.IReuniaoService;
import br.com.everis.controlereunioesws.services.IReuniaoUsuarioService;
import br.com.everis.controlereunioesws.services.IUsuarioService;
import br.com.everis.controlereunioesws.utils.Constants;
import br.com.everis.controlereunioesws.utils.ReuniaoUtils;

@RestController
@RequestMapping(value = "/reuniao/")
public class ControleReunioesWS {

	@Autowired
	private IReuniaoService reuniaoService = null;
	
	@Autowired
	private IArquivoService arquivoService = null;
	
	@Autowired
	private IUsuarioService servicoServico = null;
	
	@Autowired
	private IReuniaoUsuarioService reunioesUsuarios = null;

	@RequestMapping(value = "/gravarReuniao", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE + "; charset=utf-8", produces = MediaType.APPLICATION_JSON_VALUE + "; charset=utf-8")
	public ResponseEntity<String> gravarReuniao(@RequestBody String valores) throws Exception {
		try {
			Gson gson = new GsonBuilder().setDateFormat(Constants.DATETIME_PATTERN).create();
			
			ReuniaoArquivoUsuario rau = gson.fromJson(valores, ReuniaoArquivoUsuario.class);
			List<Arquivo> listArquivos = rau.getListArquivos();
			List<Usuario> listUsuarios = rau.getListUsuarios();
			Reuniao reuniao = rau.getReuniao();

			Reuniao reuniaoGravada = reuniaoService.gravarReuniao(reuniao);
			for(Arquivo a : listArquivos){
				a.setReunioes(reuniaoGravada);
			}
			
			arquivoService.gravarArquivos(listArquivos);

			List<Usuario> usuariosGravados = servicoServico.gravarUsuarios(listUsuarios);
			
			List<ReuniaoUsuario> lstReunioesUsuarios = new ArrayList<>();
			for(Usuario u : usuariosGravados){
				ReuniaoUsuarioPK ruPK = new ReuniaoUsuarioPK();
				ruPK.setReuniao(reuniaoGravada);
				ruPK.setUsuario(u);
				
				ReuniaoUsuario ru = new ReuniaoUsuario();
				ru.setConfirmado("0");
				ru.setPk(ruPK);
				
				lstReunioesUsuarios.add(ru);
			}
			reunioesUsuarios.gravarReuniaoUsuario(lstReunioesUsuarios);
			
			
			ReuniaoUsuario ru = new ReuniaoUsuario();
			ru.getPk().setReuniao(reuniaoGravada);
			
			List<Usuario> listUsuariosReuniao = reunioesUsuarios.buscarUsuarios(ru);
			String usuariosJson = gson.toJson(new JsonArray());
			if(listUsuariosReuniao.size() == 0){
				usuariosJson =  gson.toJson(new JsonArray());
			}
			
			
			return ResponseEntity.ok().body(usuariosJson);
		} catch (ResponseException re) {
			throw new ResponseException(re.getErrorMessage());
		} catch (NullPointerException npe) {
			throw new NullPointerException(npe.getMessage());
		} catch (ParseException e) {
			throw new ParseException(e.getMessage(), 0);
		}
	}

	@RequestMapping(value = "/editarReuniao/{idReuniao}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE + "; charset=utf-8", produces = MediaType.APPLICATION_JSON_VALUE + "; charset=utf-8")
	public ResponseEntity<?> editarReuniao(@PathVariable("idReuniao") String idReuniao, @RequestBody String reuniao)
			throws Exception {
		try {
			Gson gson = new GsonBuilder().setDateFormat(Constants.DATETIME_PATTERN).create();
			Reuniao r = gson.fromJson(reuniao, Reuniao.class);
			r.setIdReuniao(ReuniaoUtils.stringToLong(idReuniao));
			reuniaoService.editarReuniao(r);
			return ResponseEntity.ok().build();
		} catch (ResponseException re) {
			throw new ResponseException(re.getErrorMessage());
		} catch (NullPointerException npe) {
			throw new NullPointerException(npe.getMessage());
		} catch (ParseException e) {
			throw new ParseException(e.getMessage(), 0);
		}
	}

	@RequestMapping(value = "/removerReuniao/{idReuniao}", method = RequestMethod.DELETE, consumes = "text/plain;charset=utf-8" , produces = MediaType.APPLICATION_JSON_VALUE + "; charset=utf-8")
	public ResponseEntity<?> removerReuniao(@PathVariable("idReuniao") String idReuniao) throws Exception {
		try {
			Reuniao r = new Reuniao();
			r.setIdReuniao(ReuniaoUtils.stringToLong(idReuniao));
			
			reuniaoService.removerReuniao(r);
			return ResponseEntity.ok().build();
		} catch (ResponseException re) {
			throw new ResponseException(re.getErrorMessage());
		} catch (NullPointerException npe) {
			throw new NullPointerException(npe.getMessage());
		} catch (ParseException e) {
			throw new ParseException(e.getMessage(), 0);
		}catch(BatchUpdateException bue){
			throw new BatchUpdateException(bue);
		}catch(Exception e){
			throw new Exception(e);
		}
	}

	@RequestMapping(value = "/buscarReuniao", method = RequestMethod.GET, consumes = {MediaType.APPLICATION_JSON_VALUE + "; charset=utf-8", "text/plain;charset=utf-8" }, produces = MediaType.APPLICATION_JSON_VALUE + "; charset=utf-8")
	public ResponseEntity<String> buscarReuniao(@RequestParam("idReuniao") String idReuniao) throws Exception {
		try {
			Gson gson = new GsonBuilder().setDateFormat(Constants.DATETIME_PATTERN).excludeFieldsWithoutExposeAnnotation().create();
			Reuniao r = new Reuniao();
			r.setIdReuniao(ReuniaoUtils.stringToLong(idReuniao));
			r = reuniaoService.buscarReuniao(r);
			return ResponseEntity.ok().body(gson.toJson(r));
		} catch (ResponseException re) {
			throw new ResponseException(re.getErrorMessage());
		} catch (NullPointerException npe) {
			throw new NullPointerException(npe.getMessage());
		} catch (ParseException e) {
			throw new ParseException(e.getMessage(), 0);
		}

	}

	@RequestMapping(value = "/buscarReunioes", method = RequestMethod.GET, consumes = { MediaType.APPLICATION_JSON_VALUE + "; charset=utf-8", "text/plain;charset=utf-8" }, produces = { MediaType.APPLICATION_JSON_VALUE + "; charset=utf-8", "text/plain;charset=utf-8" })
	public ResponseEntity<String> buscarReunioes(@RequestParam(value = "data") String data) throws Exception {
		try {
			Gson gson = new GsonBuilder().setDateFormat(Constants.DATETIME_PATTERN).excludeFieldsWithoutExposeAnnotation().create();
			Reuniao reuniao = new Reuniao();
			reuniao.setDtInicio(ReuniaoUtils.stringToDateTime(data));
			List<Reuniao> lstReunioes = reuniaoService.buscarReunioes(reuniao);
			String reuniaoJson = gson.toJson(lstReunioes, new TypeToken<List<Reuniao>>(){}.getType());
			return ResponseEntity.ok().body(reuniaoJson);
		} catch (ResponseException re) {
			throw new ResponseException(re.getErrorMessage());
		} catch (NullPointerException npe) {
			throw new NullPointerException(npe.getMessage());
		} catch (ParseException e) {
			throw new ParseException(e.getMessage(), 0);
		}
	}

	@RequestMapping(value = "/listarReunioes", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE + "; charset=utf-8")
	public ResponseEntity<String> listarReunioes() throws Exception {
		try {
			Gson gson = new GsonBuilder().setDateFormat(Constants.DATETIME_PATTERN).excludeFieldsWithoutExposeAnnotation().create();
			List<Reuniao> lstReunioes = reuniaoService.listarReunioes();
			String reunioesJson = gson.toJson(lstReunioes);
			return ResponseEntity.ok().body(reunioesJson);
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
		return responseError(ex);
	}

	@ExceptionHandler(NullPointerException.class)
	public ResponseEntity<ErrorResponse> nullPointerException(NullPointerException npe) {
		return responseError(npe);
	}
	
	@ExceptionHandler(ParseException.class)
	public ResponseEntity<ErrorResponse> parseException(ParseException e){
		return responseError(e);
	}

	private ResponseEntity<ErrorResponse> responseError(Exception e) {
		ErrorResponse error = new ErrorResponse();
		error.setErrorCode(HttpStatus.PRECONDITION_FAILED.value());
		error.setMessage(e.getMessage());
		return new ResponseEntity<ErrorResponse>(error, HttpStatus.OK);
	}
}
