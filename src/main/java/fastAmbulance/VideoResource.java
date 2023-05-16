package fastAmbulance;

import fastAmbulance.models.Tutorial;
import fastAmbulance.models.Video;
import lombok.extern.log4j.Log4j2;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.Collection;

@Log4j2
@Path("/api/video")
public class VideoResource {

//	@Inject
//	VideoRepo videoResource;

//	@GET
//	@Path("tutorial/{id}")
//	@Produces(MediaType.APPLICATION_JSON)
//	public Tutorial getTutorial(@PathParam("id") Long id) {
//		return videoResource.findById(id);
//	}
//
//	@POST
//	@Path("/")
//	@Transactional
//	@Consumes(MediaType.APPLICATION_JSON)
//	public Long createParamedic(Tutorial paramedic) {
//		Tutorial temp = paramedic;
//		tutorialRepository.persist(temp);
//		return temp.getId();
//	}
//
//	@DELETE
//	@Transactional
//	@Path("tutorial/{id}")
//	@Produces(MediaType.TEXT_PLAIN)
//	public boolean deleteTutorial(@PathParam("id") Long id) {
//		// TODO : delete a tutorial
//		return tutorialRepository.deleteById(id);
//	}
}
