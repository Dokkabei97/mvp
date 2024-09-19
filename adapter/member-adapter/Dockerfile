FROM alpine:latest

RUN apk update && \
    apk add --no-cache openjdk21-jre

ENV JAVA_HOME=/usr/lib/jvm/java-21-openjdk
ENV PATH="/usr/lib/jvm/java-21-openjdk/bin:${PATH}"

WORKDIR /home/app

COPY build/libs/app-${VERSION}.jar app.jar

EXPOSE 8080

CMD ["java", "jar", "app.jar -Dspring.profiles.active=prod"]
