plugins {
    kotlin("js")
    id("tz.co.asoft.library")
    id("io.codearte.nexus-staging")
    signing
}

kotlin {
    js(IR) { browserLib() }
    sourceSets {
        val main by getting {
            dependencies {
                api(project(":live-core"))
                api(asoft("reakt-core", vers.asoft.reakt))
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