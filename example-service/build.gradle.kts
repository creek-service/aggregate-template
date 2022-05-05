plugins {
    `java-library`
}

val creekVersion : String by extra

dependencies {
    implementation(project(":common"))

    testImplementation("org.creekservice:creek-kafka-streams-test:$creekVersion")
}