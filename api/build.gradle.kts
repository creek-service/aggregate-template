plugins {
    `java-library`
}

dependencies {
    api(project(":ids"))

    // To avoid dependency hell downstream, avoid adding any more dependencies
}