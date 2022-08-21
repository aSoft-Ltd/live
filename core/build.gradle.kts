plugins {
    kotlin("multiplatform")
    id("tz.co.asoft.library")
    signing
}

kotlin {
    jvm { library(); withJava() }
    js(IR) { library() }
//    val nativeTargets = nativeTargets(true)
    macosArm64()
    macosX64()
    iosArm64()
    iosX64()
    val nativeTargets = linuxTargets(true)

    sourceSets {
        val commonMain by getting {
            dependencies {
                api(project(":functions-core"))
                api(project(":koncurrent-primitives-core"))
            }
        }

        val commonTest by getting {
            dependencies {
                implementation(project(":expect-coroutines"))
                implementation(project(":koncurrent-primitives-mock"))
            }
        }
    }
}

aSoftOSSLibrary(
    version = asoft.versions.root.get(),
    description = "An multiplatform representation of a Live<T> object"
)