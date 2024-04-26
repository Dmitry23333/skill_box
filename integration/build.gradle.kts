plugins {
	java
	id("org.springframework.boot") version "3.1.10"
	id("io.spring.dependency-management") version "1.1.4"
}

group = "com.example.service"
version = "0.0.1-SNAPSHOT"

java {
	sourceCompatibility = JavaVersion.VERSION_17
}

configurations {
	compileOnly {
		extendsFrom(configurations.annotationProcessor.get())
	}
}

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.jetbrains:annotations:23.0.0")
    compileOnly("org.projectlombok:lombok")
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.mapstruct:mapstruct:1.5.3.Final")
	implementation("org.springframework.boot:spring-boot-starter-cache")
	implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.0.4")
	implementation("com.google.guava:guava:33.1.0-jre")
	implementation("io.lettuce:lettuce-core")
	implementation("org.springframework.boot:spring-boot-starter-data-redis")
	// https://mvnrepository.com/artifact/org.projectlombok/lombok-mapstruct-binding
	implementation("org.projectlombok:lombok-mapstruct-binding:0.2.0")

	annotationProcessor("org.mapstruct:mapstruct-processor:1.5.3.Final")
	runtimeOnly("org.postgresql:postgresql")
	annotationProcessor("org.projectlombok:lombok")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	runtimeOnly("org.springframework.boot:spring-boot-configuration-processor:3.2.4")
	annotationProcessor("org.springframework.boot:spring-boot-configuration-processor:3.2.4")
}

tasks.withType<Test> {
	useJUnitPlatform()
}

tasks.bootBuildImage {
	builder.set("paketobuildpacks/builder-jammy-base:latest")
}
