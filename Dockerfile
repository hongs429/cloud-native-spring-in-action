#FROM eclipse-temurin:17
#WORKDIR workspace
#ARG JAR_FILE=build/libs/*.jar
#COPY ${JAR_FILE} catalog-service.jar
#ENTRYPOINT ["java", "-jar", "catalog-service.jar"]

# 첫번째 단계를 위한 OpenJDK 베이스 이미지
FROM eclipse-temurin:17 AS builder
WORKDIR workspace
# 프로젝트 내에서 애플리케이션 JAR 파일의 위치를 지정하는 빌드 인수
ARG JAR_FILE=build/libs/*.jar
# 애플리케이션 JAR 파일을 로컬 머신에서 이미지의 workspace 폴더로 복사
COPY ${JAR_FILE} catalog-service.jar
# 계층 JAR 모드를 적용해 아카이브에서 계층을 추출한다
RUN java -Djarmode=layertools -jar catalog-service.jar extract

# 두 번째 단계를 위한 OpenJDK 베이스 이미지
FROM eclipse-temurin:17
WORKDIR workspace
COPY --from=builder workspace/dependencies/ ./
COPY --from=builder workspace/spring-boot-loader/ ./
COPY --from=builder workspace/snapshot-dependencies/ ./
COPY --from=builder workspace/application/ ./
ENTRYPOINT ["java", "org.springframework.boot.loader.JarLauncher"]