package fastAmbulance.repos;

import fastAmbulance.models.Certification;
import fastAmbulance.models.Paramedic;
import fastAmbulance.models.ParamedicTutorial;
import fastAmbulance.models.Tutorial;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.NoResultException;
import java.util.List;

@ApplicationScoped
public class ParamedicRepository implements PanacheRepository<Paramedic> {
    public Paramedic getParamedicWithPasswordAndEmail(String email, String password) {
        try {
            return getEntityManager()
                    .createQuery("FROM Paramedic WHERE password = :password AND email = :email", Paramedic.class)
                    .setParameter("password", password)
                    .setParameter("email", email)
                    .getSingleResult();
        } catch (NoResultException ex) {
            return null;
        }
    }

    public boolean assignTutorialToParamedic(Paramedic paramedic, Tutorial tutorial) {
        ParamedicTutorial paramedicTutorial = new ParamedicTutorial();
        paramedicTutorial.setParamedic(paramedic);
        paramedicTutorial.setTutorial(tutorial);
        paramedicTutorial.set_finished(false);
        paramedicTutorial.setFinished_percentage(0);


        getEntityManager().persist(paramedicTutorial);
        return true;
    }
}
