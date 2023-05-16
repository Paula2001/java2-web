package fastAmbulance.models;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@Entity
@ToString
@Table(name = "Videos")
public class Video {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Getter
	@Setter
	private Long id;

	@Getter
	@Setter
	private String url;

	@ManyToOne
	@JoinColumn(name = "tutorial_id")
	private Tutorial tutorial;
}