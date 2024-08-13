FROM openjdk:21
VOLUME /tmp
EXPOSE 8080
COPY target/student-management-0.0.1-SNAPSHOT.jar student_management.jar
ENTRYPOINT ["java","-jar","/student_management.jar"]
