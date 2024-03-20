FROM amazoncorretto:21

RUN mkdir workspace
COPY /target/modern-webdev-workshop-0.0.1-SNAPSHOT.jar /workspace/app.jar

ENTRYPOINT ["java","-jar","/workspace/app.jar"]
