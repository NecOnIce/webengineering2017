release: java -cp target/demo-1.0-SNAPSHOT.jar:target/dependency/* -jar target/demo-1.0-SNAPSHOT.jar:com.micromata.webengineering.demo.MigrationsRunner
web: java $JAVA_OPTS -Dspring.profiles.active=heroku -Dserver.port=$PORT -jar target/demo-1.0-SNAPSHOT.jar
