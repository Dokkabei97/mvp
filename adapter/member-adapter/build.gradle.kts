plugins {
    kotlin("plugin.jpa") version "1.9.25"
}

allprojects {
    allOpen {
        annotation("javax.persistence.Entity")
        annotation("javax.persistence.MappedSuperclass")
        annotation("javax.persistence.Embeddable")
    }
}

dependencies {
    implementation(project(":application:member-application"))
    implementation(project(":shared:persistence"))

    implementation("org.springframework.boot:spring-boot-starter-web")
//    implementation("org.springframework.boot:spring-boot-starter-data-jpa")

    implementation("org.jetbrains.exposed:exposed-core:0.54.0")
    implementation("org.jetbrains.exposed:exposed-crypt:0.54.0")
    implementation("org.jetbrains.exposed:exposed-dao:0.54.0")
    implementation("org.jetbrains.exposed:exposed-jdbc:0.54.0")
    implementation("org.jetbrains.exposed:exposed-kotlin-datetime:0.54.0")
    implementation("org.jetbrains.exposed:exposed-json:0.54.0")
    implementation("org.jetbrains.exposed:exposed-money:0.54.0")
    implementation("org.jetbrains.exposed:exposed-spring-boot-starter:0.54.0")

    runtimeOnly("org.postgresql:postgresql")
    implementation("org.springframework.boot:spring-boot-starter-data-redis")
    implementation("org.springframework.boot:spring-boot-starter-security")
    testImplementation("org.springframework.security:spring-security-test")
}
