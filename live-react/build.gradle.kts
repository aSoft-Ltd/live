plugins {
    kotlin("js")
    id("tz.co.asoft.library")
    id("io.codearte.nexus-staging")
    signing
}

kotlin {
    js(IR) { library() }
    sourceSets {
        val main by getting {
            dependencies {
                api(project(":live-core"))
                api("org.jetbrains:kotlin-react:${vers.wrappers.react}")
            }
        }
        val test by getting {
            dependencies {
                api(asoft("expect-core", vers.asoft.expect))
            }
        }
    }
}

aSoftOSSLibrary(
    version = vers.asoft.live,
    description = "An multiplatform representation of a Live<T> object"
)