FROM <Docker_Registry_Location_For_OpenJDK>

ENV APP_NAME="monthlysubs" \
    JAVA_OPTS="-Xms512m -Xmx1024m -Djava.net.preferIPv4Stack=true"

COPY ./target/*.jar ./app.jar

EXPOSE 8080

CMD ["sh", "-c", "java -Dspring.profiles.active=$SPRING_PROFILES_ACTIVE $JAVA_OPTS -jar app.jar"]