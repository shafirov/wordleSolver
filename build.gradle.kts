repositories {
    mavenCentral()
}

plugins {
    kotlin("jvm") version "1.6.0"
    application
}

application {
    mainClass.set("shafirov.WordleSolverKt")
}