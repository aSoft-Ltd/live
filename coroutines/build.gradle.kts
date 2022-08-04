plugins {
    kotlin("multiplatform")
    id("tz.co.asoft.library")
    signing
}

kotlin {
    jvm { library() }
    js(IR) { library() }
//    val nativeTargets = nativeTargets(true)
    val nativeTargets = linuxTargets(true)
    sourceSets {
        val commonMain by getting {
            dependencies {
                if (System.getenv("INCLUDE_BUILD") == "true") {
                    api(asoft.koncurrent.primitives.coroutines)
                } else {
                    api(project(":koncurrent-primitives-coroutines"))
                }
                api(projects.liveCore)
                api(kotlinx.coroutines.core)
            }
        }

        val commonTest by getting {
            dependencies {
                if (System.getenv("INCLUDE_BUILD") == "true") {
                    implementation(asoft.expect.coroutines)
                } else {
                    implementation(project(":expect-coroutines"))
                }
            }
        }
    }
}

aSoftOSSLibrary(
    version = asoft.versions.root.get(), description = "Bindings for Live<S> object to be used with kotlinx-coroutines"
)