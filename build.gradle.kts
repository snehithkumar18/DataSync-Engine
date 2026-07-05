plugins {
    java
}

allprojects {
    repositories {
        mavenCentral()
    }
}

subprojects {
    apply(plugin = "java")
    apply(plugin = "java-library")

    group = "com.syncforge"
    version = "1.0.0"

    java {
        sourceCompatibility = JavaVersion.VERSION_21
        targetCompatibility = JavaVersion.VERSION_21
    }

    dependencies {
        testImplementation("org.junit.jupiter:junit-jupiter-api:5.10.1")
        testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.10.1")
    }

    tasks.test {
        useJUnitPlatform()
        testLogging {
            events("passed", "skipped", "failed")
        }
    }
}
