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
                    api(asoft.expect.core)
                } else {
                    api(project(":expect-core"))
                }
                api(projects.liveCore)
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
    version = asoft.versions.root.get(),
    description = "Tools for testing Live<S> objects"
)