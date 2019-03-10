plugins {
    java
    application
    id( "org.springframework.boot") version "2.1.3.RELEASE"
}

apply(plugin = "io.spring.dependency-management")

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

application {
    group = "com.casumo.hometest"
    version = "0.0.1-SNAPSHOT"
    mainClassName = "com.casumo.hometest.videorentalstore.VideoRentalStoreApplication"
}


repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot", "spring-boot-starter-web")
    runtimeOnly("com.h2database", "h2")
    testImplementation("org.springframework.boot", "spring-boot-starter-test")
}
