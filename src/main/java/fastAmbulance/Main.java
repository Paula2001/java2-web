package fastAmbulance;

import io.quarkus.runtime.Quarkus;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class Main {

	public static void main(String[] args) {
		log.info("The project is booting up...");
		Quarkus.run(args);
	}

}
