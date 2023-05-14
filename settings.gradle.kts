rootProject.name = "oathwarden"

pluginManagement {
    repositories {
        mavenCentral()
    }
    plugins {
        `version-catalog`
    }
}

include("processors")
include("main")
