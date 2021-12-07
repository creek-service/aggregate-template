plugins {
    `java-library`
}

val kafkaVersion: String by extra

dependencies {
    api(project(":ids"))
    api("org.creek:creek-kafka-metadata:+")

    // To avoid dependency hell downstream, avoid adding any more dependencies except Creek metadata jars and test dependencies.

    testImplementation("org.apache.kafka:kafka-clients:$kafkaVersion")
}