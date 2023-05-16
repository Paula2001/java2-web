package fastAmbulance.models;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.sql.Date;

@XmlRootElement
@Entity
@ToString
@Table(name = "Certifications")
public class Certification {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Getter
	@Setter
	private Long id;

	@Getter
	@Setter
	private Date issued_at;

	@ManyToOne
	@JoinColumn(name = "tutorial_id")
	private Tutorial tutorial;

	@ManyToOne
	@JoinColumn(name = "paramedic_id")
	private Paramedic paramedic;

}
