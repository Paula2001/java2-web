package fastAmbulance.models;

import javax.persistence.*;
import lombok.*;

import java.sql.Date;
import java.util.List;

@Entity
@Table(name = "paramedics")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Paramedic {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String name;


	@Column(nullable = false, unique = true)
	@Getter
	@Setter
	private String email;

	@Column(nullable = false)
	@Getter
	@Setter
	private Date date_of_birth;

	@Column(nullable = false)
	private String password;

	@Column(nullable = false)
	private boolean is_accepting;

	@ManyToMany
	@JoinTable(name = "paramedics_tutorials",
			joinColumns = @JoinColumn(name = "paramedic_id"),
			inverseJoinColumns = @JoinColumn(name = "tutorial_id"))
	private List<Tutorial> tutorials;
}
