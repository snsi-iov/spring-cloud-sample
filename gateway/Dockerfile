FROM java:8
VOLUME /tmp
VOLUME /app-logs
ARG JAR_NAME

WORKDIR /app

ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "/app/app.jar"]

EXPOSE 5555

ADD target/$JAR_NAME /app/app.jar
RUN touch /app/app.jar
