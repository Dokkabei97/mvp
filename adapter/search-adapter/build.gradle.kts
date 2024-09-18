dependencies {
    implementation(project(":application:search-application"))

    implementation("org.springframework.boot:spring-boot-starter-webflux")
    implementation("org.springframework.boot:spring-boot-starter-data-elasticsearch")
}
