
build:
	mvn clean package -Dmaven.test.skip=true
run:
	java -jar target/goinfoBridgeService-1.0.0.jar
done:
	make build && make run