
build:
	mvn clean package -Dmaven.test.skip=true
run:
	java -server -Xmx512m -XX:MaxPermSize=512m -jar target/goinfoBridgeService-1.0.0.jar
done:
	make build && make run
run-app:
	mvn package -Dmaven.test.skip=true && make run
