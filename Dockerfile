FROM openjdk:latest
COPY out/artifacts/Bot_main_jar/ /tmp
WORKDIR /tmp
CMD ["java", "-jar", "Bot.main.jar"]
