# This is the BUILD target, do not remove it, and do not modify it's name
.PHONY: java17-cli-build
java17-cli-build:
	mvn install -DtestFailureIgnore=true

# This is the RUN target, do not remove it, and do not modify it's name
.PHONY: java17-cli-run
java17-cli-run: ;

# This is the command run by the IDE's run button
.PHONY: lab-run
lab-run:
	mvn compile
	mvn spring-boot:stop
	mvn spring-boot:start -Dspring-boot.run.jvmArguments="-Djava.io.tmpdir=./tmp"
	
# This is the command run by the IDE's test button
.PHONY: lab-test
lab-test:
	mvn -Dtest=$(subst src/test/java/,,$(basename $(FILE))) test
