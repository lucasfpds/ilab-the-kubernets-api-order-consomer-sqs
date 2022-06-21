mvn clean compile package -Dmaven.test.skip -DskipTests -Dmaven.javadoc.skip=true

export CLASSPATH=target/api.order.consomer.sqs-1.0-SNAPSHOT.jar
export className=Application
echo "## Running $className..."
mvn exec:java -Dexec.mainClass="br.com.api.order.consomer.sqs.$className"