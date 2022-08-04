plugins {
    kotlin("multiplatform")
    id("tz.co.asoft.library")
    signing
}

kotlin {
    jvm { library(); withJava() }
    js(IR) { library() }
    nativeTargets(true)

    sourceSets {
        val commonMain by getting {
            dependencies {
                if (System.getenv("INCLUDE_BUILD") == "true") {
                    api(asoft.functions.core)
                    api(asoft.koncurrent.primitives.core)
                } else {
                    api(project(":functions-core"))
                    api(project(":koncurrent-primitives-core"))
                }
            }
        }

        val commonTest by getting {
            dependencies {
                if (System.getenv("INCLUDE_BUILD") == "true") {
                    implementation(asoft.expect.coroutines)
                    implementation(asoft.koncurrent.primitives.mock)
                } else {
                    implementation(project(":expect-coroutines"))
                    implementation(project(":koncurrent-primitives-mock"))
                }
            }
        }
    }
}

aSoftOSSLibrary(
    version = asoft.versions.root.get(),
    description = "An multiplatform representation of a Live<T> object"
)