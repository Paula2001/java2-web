package fastAmbulance.models;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@XmlRootElement
@Entity
@ToString
@Table(name = "Tutorials")
public class Tutorial {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Getter
	@Setter
	private Long id;

	@Getter
	@Setter
	private String name;

	@Getter
	@Setter
	private String description;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "tutorial_id")
	@Getter
	@Setter
	private List<Video> videos;
}
