plugins {
    kotlin("jvm") version "1.6.10"
    id("com.github.johnrengelman.shadow") version "7.1.0"

}

group = "me.Zeron.CensorPlugin"
version = "1.0.0"

java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(17))
}

repositories {
    mavenCentral()
    maven("https://papermc.io/repo/repository/maven-public/")
}

dependencies {
    implementation(kotlin("stdlib"))
    compileOnly("io.papermc.paper:paper-api:1.17.1-R0.1-SNAPSHOT")
    compileOnly("net.kyori:adventure-api:4.11.0")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.4")

}
val loc = File("C:\\Users\\USER\\Desktop\\MyPlayGround\\plugins")

tasks {
    processResources {
        filesMatching("*.yml") {
            expand(project.properties)
        }
    }
    shadowJar {
        archiveBaseName.set(project.name)
        archiveClassifier.set("")
        archiveVersion.set(project.version.toString())
        doLast {
            copy {
                from(archiveFile)
                into(if (File(loc, archiveFileName.get()).exists()) loc else loc)
            }
        }
    }
}
