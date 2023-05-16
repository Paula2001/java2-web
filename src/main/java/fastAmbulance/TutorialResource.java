package fastAmbulance;

import fastAmbulance.models.Tutorial;
import fastAmbulance.repos.TutorialRepository;
import lombok.extern.log4j.Log4j2;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.Collection;

@Log4j2
@Path("/tutorials")
public class TutorialResource {

	@Inject
	TutorialRepository tutorialRepository;

	@GET
	@Path("tutorials")
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<Tutorial> getTutorials() {
		return tutorialRepository.findAll().list();
	}

	@GET
	@Path("tutorial/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Tutorial getTutorial(@PathParam("id") Long id) {
		return tutorialRepository.findTutorialWithVideosById(id);
	}

	@POST
	@Path("/")
	@Transactional
	@Consumes(MediaType.APPLICATION_JSON)
	public Tutorial createTutorial(Tutorial tutorial) {
		Tutorial t = tutorial;
		tutorialRepository.persist(t);
		return t;
	}
}
