FROM openjdk:17
ARG JAR_FILE=target/sales_management-1.0.0.jar
COPY ${JAR_FILE} app_sales.jar
EXPOSE 8080
ENTRYPOINT [ "executable" ]