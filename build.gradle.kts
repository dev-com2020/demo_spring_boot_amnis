plugins {
	java
	id("org.springframework.boot") version "3.2.8"
	id("io.spring.dependency-management") version "1.1.6"
	id("groovy")
}

group = "com.example"
version = "0.0.1-SNAPSHOT"

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(17)
	}
}

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-web")
	runtimeOnly("com.h2database:h2")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")
	implementation("org.springframework.boot:spring-boot-starter-mustache")
	testImplementation("org.mockito:mockito-core:3.12.4")
	testImplementation ("org.codehaus.groovy:groovy-all:3.0.8")
	testImplementation ("org.spockframework:spock-core:2.0-groovy-3.0")


}

tasks.withType<Test> {
	useJUnitPlatform()
}
