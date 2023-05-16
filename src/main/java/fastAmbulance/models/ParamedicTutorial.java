package fastAmbulance.models;

import lombok.*;

import javax.persistence.*;
import java.util.List;
@Entity
@Table(name = "Paramedics_Tutorials")
public class ParamedicTutorial {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne()
	@JoinColumn(name = "paramedic_id")
	@Getter
	@Setter
	private Paramedic paramedic;

	@ManyToOne()
	@JoinColumn(name = "tutorial_id")
	@Getter
	@Setter
	private Tutorial tutorial;

	@Getter
	@Setter
	private boolean is_finished;


	@Getter
	@Setter
	private int finished_percentage;
}