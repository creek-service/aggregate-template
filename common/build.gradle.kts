plugins {
    `java-library`
}

val kafkaVersion : String by extra
val creekVersion : String by extra

dependencies {
    api(project(":services"))

    api("org.creekservice:creek-service-context:$creekVersion")
    api("org.creekservice:creek-kafka-streams-extension:$creekVersion")
}