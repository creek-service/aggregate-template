plugins {
    `java-library`
}

dependencies {
    api(project(":api"))

    // To avoid dependency hell downstream, avoid adding any more dependencies except Creek metadata jars and test dependencies.
}