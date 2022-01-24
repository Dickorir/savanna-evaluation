FROM openjdk:17

# maintainer ifno
LABEL maintainer="dickrir@gmail.com"

# add a vloume pointing to /tmp
VOLUME /tmp

# make port 8080 available to the world outside container
EXPOSE 8080

# use local time
ENV TZ Africa/Nairobi

RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone

# add the applications jar file
ARG JAR_FILE=target/mpesa-0.0.1-SNAPSHOT.jar

# Add the applications jar to the container
ADD ${JAR_FILE} mpesa-0.0.1-SNAPSHOT.jar

# RUn thr jar file
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/mpesa-0.0.1-SNAPSHOT.jar"]