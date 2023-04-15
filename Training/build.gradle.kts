plugins {
	java
	id("org.springframework.boot") version "2.7.9"
	id("io.spring.dependency-management") version "1.0.15.RELEASE"
}

group = "com.example"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_1_8

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springframework.boot:spring-boot-starter-web")
	// https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-security
	implementation("org.springframework.boot:spring-boot-starter-security:2.4.5")
	implementation("io.jsonwebtoken:jjwt:0.9.0")
	implementation("javax.xml.bind:jaxb-api:2.3.1")

	
	runtimeOnly("org.postgresql:postgresql")
	testImplementation("org.springframework.boot:spring-boot-starter-test")

	




	implementation("com.google.code.gson:gson:2.10.1")



	
}

tasks.withType<Test> {
	useJUnitPlatform()
}
