import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import java.nio.charset.StandardCharsets

@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    alias(libs.plugins.jvm)
}

group = "org.oathwarden"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

subprojects {

    repositories {
        mavenCentral()
    }
    apply {
        plugin(rootProject.libs.plugins.jvm.get().pluginId)
    }
    dependencies {
        kotlin("stdlib")
        testImplementation(rootProject.libs.bundles.kotest)
    }
    tasks.test {
        useJUnitPlatform()
        testLogging.showExceptions = true
        testLogging {
            exceptionFormat = org.gradle.api.tasks.testing.logging.TestExceptionFormat.FULL
            showExceptions = true
            showCauses = true
            showStackTraces = true
        }
    }

    val jvmVersion: String = JavaVersion.VERSION_17.majorVersion

    kotlin {
        jvmToolchain(jvmVersion.toInt())
    }
    tasks.withType<KotlinCompile> {
        kotlinOptions {
            val version = org.jetbrains.kotlin.gradle.dsl.KotlinVersion.KOTLIN_2_0.version
            apiVersion = version
            languageVersion = version
            jvmTarget = jvmVersion
            javaParameters = true
        }
    }
    java {
        toolchain {
            languageVersion.set(JavaLanguageVersion.of(jvmVersion))
        }
    }
    tasks.withType<JavaCompile> {
        options.encoding = StandardCharsets.UTF_8.toString()
    }
}