plugins {
    `java-library`
}

val creekVersion : String by extra

dependencies {
    implementation(project(":common"))

    testImplementation("org.creek:creek-kafka-streams-test:$creekVersion")
}