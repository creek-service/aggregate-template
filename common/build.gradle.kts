plugins {
    `java-library`
}

val kafkaVersion : String by extra
val creekVersion : String by extra

dependencies {
    api(project(":services"))

    api("org.creek:creek-service-context:$creekVersion")
    api("org.creek:creek-kafka-streams-extension:$creekVersion")
}