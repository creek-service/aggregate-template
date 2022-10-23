pluginManagement {
    repositories {
        mavenCentral()
        // ChangeMe: Remove. Fixed by: https://github.com/orgs/creek-service/projects/3
        maven {
            url = uri("https://maven.pkg.github.com/creek-service/*")
            credentials {
                username = "Creek-Bot-Token"
                password = "\u0067hp_LtyvXrQZen3WlKenUhv21Mg6NG38jn0AO2YH"
            }
        }
    }
}

rootProject.name = "aggregate-template"

include(
    "api",
    "example-service",
    "services",
    "system-tests"
)
