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

ext {
    // IMPORTANT: Override JUnit BOM from spring dependency management
    // See: https://stackoverflow.com/questions/54598484/gradle-5-junit-bom-and-spring-boot-incorrect-versions
    set("junit-jupiter.version", "5.4.0")
}

dependencies {
    implementation("org.springframework.boot", "spring-boot-starter-web")
    implementation("org.springframework.boot", "spring-boot-starter-jdbc")

    // metrics and monitoring
//    implementation("org.springframework.boot", "spring-boot-starter-parent", "2.0.1.RELEASE")
    implementation("org.springframework.boot", "spring-boot-devtools")

    // swagger
    // IMPORTANT: Use this version. Why?
    // See: https://github.com/springfox/springfox/issues/2528 & https://github.com/springfox/springfox/issues/2265
    implementation("io.springfox", "springfox-swagger2", "2.8.0")
    implementation("io.springfox", "springfox-swagger-ui", "2.8.0")

    // db
    runtimeOnly("com.h2database", "h2")

    // testing
    testImplementation("org.springframework.boot", "spring-boot-starter-test") {
        // Exclude JUnit 4 from starter-test, because it will be used JUnit 5
        exclude(group = "junit")
        exclude(group = "org.mockito")
        exclude(group = "org.hamcrest")
    }
    testImplementation("org.junit.jupiter", "junit-jupiter-api", "5.4.0")
    testRuntimeOnly("org.junit.jupiter", "junit-jupiter-engine", "5.4.0")
    // IMPORTANT: Use this version. Why? See https://github.com/mockito/mockito/issues/1604
    testImplementation("org.mockito", "mockito-junit-jupiter", "2.23.4")
    testImplementation("org.hamcrest","hamcrest-library","1.3")

    // used because JSONObject change the order of elements
    // see https://stackoverflow.com/questions/17229418/jsonobject-why-jsonobject-changing-the-order-of-attributes
    testImplementation("com.google.code.gson","gson","2.8.2")
}

tasks {
    // Use the built-in JUnit support of Gradle.
    "test"(Test::class) {
        useJUnitPlatform()
    }
}
