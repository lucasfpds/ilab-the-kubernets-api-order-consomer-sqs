mvn clean compile package

export CLASSPATH=target/api.order.consomer.sqs-1.0-SNAPSHOT.jar
export className=Application
echo "## Running $className..."
mvn exec:java -Dexec.mainClass="br.com.api.order.consomer.sqs.$className"