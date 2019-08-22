import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("org.springframework.boot") version "2.1.7.RELEASE"
	id("io.spring.dependency-management") version "1.0.8.RELEASE"
	id("com.palantir.docker") version "0.22.1"
	kotlin("jvm") version "1.2.71"
	kotlin("plugin.spring") version "1.2.71"
}

val appName = "ip-address-server"
group = "org.burbridge"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_1_8

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-actuator")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
}

springBoot {
	buildInfo()
}

tasks {
	withType<KotlinCompile> {
		kotlinOptions {
			freeCompilerArgs = listOf("-Xjsr305=strict")
			jvmTarget = "1.8"
		}
	}

	docker {
		val build = build.get()
		val bootJar = bootJar.get()
		val dockerImageName = "gcr.io/spring-sandbox-219017/$appName"

		dependsOn(build)

		name = "$dockerImageName:latest"
		tag("current", "$dockerImageName:$version")
		tag("latest", "$dockerImageName:latest")
		files(bootJar.archiveFile)
		setDockerfile(file("$projectDir/src/main/docker/Dockerfile"))
		buildArgs(
				mapOf(
						"JAR_FILE" to bootJar.archiveFileName.get(),
						"JAVA_OPTS" to "-XX:-TieredCompilation",
						"PORT" to "8080"
				)
		)
		pull(true)
	}
}

