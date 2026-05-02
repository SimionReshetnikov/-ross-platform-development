FROM eclipse-temurin:17

WORKDIR /app

RUN apt-get update && apt-get install -y curl

COPY src/*.java ./

RUN curl -L -o mysql-connector-j.jar https://repo1.maven.org/maven2/com/mysql/mysql-connector-j/8.4.0/mysql-connector-j-8.4.0.jar

RUN javac -cp mysql-connector-j.jar Main.java DatabaseManager.java User.java

CMD sh -c "sleep 10 && java -cp .:mysql-connector-j.jar Main"