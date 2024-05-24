FROM openjdk:17
ARG JAR_FILE=target/sales-management-0.0.1.jar
COPY ${JAR_FILE} app_sales.jar
EXPOSE 8080
ENTRYPOINT [ "java", "-jar", "app_sales.jar" ]