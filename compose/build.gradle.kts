import org.jetbrains.compose.compose

@Suppress("DSL_SCOPE_VIOLATION") plugins {
    id("org.jetbrains.compose")
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
    macosArm64()
    macosX64()
    iosArm64()
    iosX64()

    targets.all {
        compilations.all {
            kotlinOptions {
                freeCompilerArgs += listOf(
                    "-P", "plugin:androidx.compose.compiler.plugins.kotlin:suppressKotlinVersionCompatibilityCheck=true"
                )
            }
        }
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                api(projects.liveCore)
                api(compose.runtime)
            }
        }
    }
}

aSoftOSSLibrary(
    version = asoft.versions.root.get(), description = "Bindings for Live<S> object to be used with compose"
)