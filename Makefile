
build:
	mvn clean package
run:
	java -jar target/goinfoBridgeService-0.1.0.jar
done:
	make build && make run