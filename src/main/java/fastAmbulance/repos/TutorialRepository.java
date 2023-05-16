package fastAmbulance.repos;

import fastAmbulance.models.Certification;
import fastAmbulance.models.Tutorial;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class TutorialRepository implements PanacheRepository<Tutorial> {
    public Tutorial findTutorialWithVideosById(Long id) {
        return getEntityManager().createQuery(
                        "SELECT t FROM Tutorial t LEFT JOIN FETCH t.videos WHERE t.id = :id",
                        Tutorial.class
                )
                .setParameter("id", id)
                .getSingleResult();
    }
}
