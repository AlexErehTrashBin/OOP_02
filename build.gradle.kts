plugins {
    id("java")
}

group = "ru.vsu.cs.ereshkin_a_v.oop.task02"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    // GSON
    implementation("com.google.code.gson:gson:2.10.1")

    //Jetbrains Annotations
    implementation("org.jetbrains:annotations:24.0.0")

    compileOnly("org.projectlombok:lombok:1.18.30")
    annotationProcessor("org.projectlombok:lombok:1.18.30")

    testCompileOnly("org.projectlombok:lombok:1.18.30")
    testAnnotationProcessor("org.projectlombok:lombok:1.18.30")

    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.test {
    useJUnitPlatform()
}