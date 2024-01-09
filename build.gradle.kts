import org.jooq.meta.jaxb.Logging
plugins {
	java
	id("org.springframework.boot") version "3.2.1"
	id("io.spring.dependency-management") version "1.1.4"
	id("nu.studer.jooq") version "8.2.1"
}

group = "com.example"
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
	maven{
		url = uri ("https://plugins.gradle.org/m2/")
	}
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-thymeleaf:3.0.4")
	implementation("org.springframework.boot:spring-boot-starter-web:3.1.0")
	implementation("org.springframework.boot:spring-boot-starter-data-jdbc:3.0.4")
	runtimeOnly("org.postgresql:postgresql:42.5.4")
	compileOnly("org.projectlombok:lombok:1.18.26")
	annotationProcessor("org.projectlombok:lombok:1.18.26")
	testImplementation("org.springframework.boot:spring-boot-starter-test:3.1.0")
	jooqGenerator("org.postgresql:postgresql:42.5.4")
}

/*jooq{
	version.set("3.18.4")
	edition.set(nu.studer.gradle.jooq.JooqEdition.OSS)
	configurations{
		create("main"){
			generateSchemaSourceOnCompilation.set(true)
			jooqConfiguration.apply {
				logging = Logging.WARN
				jdbc.apply {
					driver="org.postgres.Driver"
					url = "jdbc:postgresql://localhost:5432/postgres"
					user = "postgres"
					password= "postgres"
				}
				generator.apply {
					name = "org.jooq.codegen.DefaultGenerator"
					database.apply {
						inputSchema = "postgres_schema"
					}
					generate.apply {
						isDeprecated = false
						isRecords = true
						isImmutablePojos = true
						isFluentSetters = true
					}
					target.apply {
						packageName = "com.example.jdbc_module.jooq.db"
						directory = "build/generated-src/jooq/main"
					}
					strategy.name = "org.jooq.codegen.DefaultGeneratorStrategy"
				}
			}
		}
	}
}

tasks.named<nu.studer.gradle.jooq.JooqGenerate>("generateJooq"){
	(launcher::set)
	(javaToolchains.launcherFor{
		languageVersion.set(JavaLanguageVersion.of(17))
	})
}*/

tasks.withType<Test> {
	useJUnitPlatform()
}
