FROM eclipse-temurin:17

WORKDIR /app

COPY src/*.java ./
COPY src/input.txt ./

RUN javac Main.java TextProcessor.java

CMD ["java", "Main", "input.txt"]