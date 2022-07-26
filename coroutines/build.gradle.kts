plugins {
    kotlin("multiplatform")
    id("tz.co.asoft.library")
    signing
}

kotlin {
    jvm { library() }
    js(IR) { library() }
    nativeTargets(true)
    sourceSets {
        val commonMain by getting {
            dependencies {
                api(projects.liveCore)
                api(kotlinx.coroutines.core)
                api(asoft.koncurrent.primitives.coroutines)
            }
        }

        val commonTest by getting {
            dependencies {
                implementation(asoft.expect.coroutines)
            }
        }
    }
}

aSoftOSSLibrary(
    version = asoft.versions.stdlib.get(),
    description = "Bindings for Live<S> object to be used with coroutines"
)