plugins {
    kotlin("js")
    id("tz.co.asoft.library")
    signing
}

kotlin {
    js(IR) { browserLib() }
    sourceSets {
        val main by getting {
            dependencies {
                api(projects.liveCore)
                api(project.dependencies.platform(kotlinw.bom))
                api(kotlinw.react.core)
            }
        }
        val test by getting {
            dependencies {
                implementation(asoft.expect.core)
            }
        }
    }
}

aSoftOSSLibrary(
    version = asoft.versions.stdlib.get(), description = "An extension of the Live<T> targeted for react"
)