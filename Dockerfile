FROM openjdk:8-jdk-alpine
COPY build/libs/ms-lista-placas-0.0.1-SNAPSHOT.jar listaplacas.jar
ENTRYPOINT ["java","-jar","listaplacas.jar"]
