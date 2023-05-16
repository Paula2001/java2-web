package fastAmbulance;

import java.time.LocalDate;
import java.util.*;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import fastAmbulance.models.Paramedic;
import fastAmbulance.models.Tutorial;
import fastAmbulance.repos.ParamedicRepository;
import fastAmbulance.repos.TutorialRepository;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Path("/paramedics")
public class ParamedicResource {

	@Inject
	ParamedicRepository paramedicRepository;

	@Inject
	TutorialRepository tutorialRepository;

	@GET
	@Path("paramedics")
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<Paramedic> getParamedics(@HeaderParam("authToken") String authToken) throws Exception {
		if(!TokenGenerator.validateToken(authToken)) {
			throw new Exception("User not logged in");
		}
		return paramedicRepository.findAll().list();
	}

	@GET
	@Path("paramedic-tutorials")
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<Tutorial> getParamedicData(@HeaderParam("authToken") String authToken) {
		Long id = TokenGenerator.getId(authToken);
		return paramedicRepository.findById(id).getTutorials();
	}

	@POST
	@Path("login")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public Map<String, String> login(@FormParam("email") String email, @FormParam("password") String password){
		Paramedic p = paramedicRepository.getParamedicWithPasswordAndEmail(email, password);
		Map<String, String> map = new HashMap<>();

		if(p != null) {
			map.put("Token", TokenGenerator.generateToken(p.getId() ,p.getEmail()));
			map.put("Email", p.getEmail());
			map.put("Id", p.getId().toString());
		}else {
			map.put("Error", "Wrong Credentials");
		}
		return map;
	}

	@POST
	@Path("register")
	@Transactional
	@Consumes(MediaType.APPLICATION_JSON)
	public Map<String, String>  createParamedic(Paramedic paramedic) {
		Paramedic p = paramedic;
		paramedicRepository.persist(p);
		Map<String, String> map = new HashMap<>();
		map.put("Token", TokenGenerator.generateToken(p.getId() ,p.getEmail()));
		map.put("Email", p.getEmail());
		map.put("Id", p.getId().toString());
		return map;
	}

	@POST
	@Path("assign-tutorial")
	@Transactional
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public boolean assignTutorial(@HeaderParam("authToken") String authToken,@FormParam("tutorial_id") Long tutorialId ) throws Exception {
		if(TokenGenerator.validateToken(authToken)) {
			Long id = TokenGenerator.getId(authToken);
			Paramedic p = paramedicRepository.findById(id);
			if(p == null) throw new Exception("paramedic not found");
			Tutorial t = tutorialRepository.findById(tutorialId);
			if(t == null) throw new Exception("tutorial not found");
			paramedicRepository.assignTutorialToParamedic(p, t);
			return true;
		}else{
			throw new Exception("User not logged in");
		}
	}


	@GET
	@Path("say-hi-in-your-lang")
	public String multiLang(@QueryParam("lang") String lang) {
		// en - cz
		ResourceBundle messages = ResourceBundle.getBundle("message", new Locale(lang));

		return messages.getString("greeting");
	}
}
