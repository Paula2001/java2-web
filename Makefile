build:
	./apache-maven-3.9.2/bin/mvn package -Dquarkus.package.type=uber-jar

run:
	./jdk-18.0.2.1/bin/java -jar ./target/rest-server-1.0.0-SNAPSHOT-runner.jar
