import org.jetbrains.compose.compose

@Suppress("DSL_SCOPE_VIOLATION") plugins {
    kotlin("multiplatform")
    id("tz.co.asoft.library")
    signing
}

repositories {
    mavenCentral()
    maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
    google()
}

kotlin {
    jvm {
        library()
    }
    js(IR) {
        library()
    }
    linuxTargets(true)

    sourceSets {
        val commonMain by getting {
            dependencies {
                api(projects.liveCore)
                api(projects.kollectionsInteroperable)
            }
        }

        val commonTest by getting {
            dependencies {
                api(projects.liveTest)
            }
        }
    }
}

aSoftOSSLibrary(
    version = asoft.versions.root.get(),
    description = "Extensions for Live<S> object to be used with compose"
)