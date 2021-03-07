plugins {
//    id("lt.petuska.npm.publish") version "1.1.2"
    kotlin("multiplatform")
    id("tz.co.asoft.library")
    id("io.codearte.nexus-staging")
    signing
}

kotlin {
    multiplatformLib(withJava = true)
    js(IR) {
        binaries.library()
    }
    val isMac = System.getenv("MACHINE") == "mac"
    if (isMac) {
        iosArm64()
        iosArm32()
        iosX64()
    }
    sourceSets {
        val commonMain by getting {}

        val commonTest by getting {
            dependencies {
                api(asoft("expect-core", vers.asoft.expect))
            }
        }

        if (isMac) {
            val nativeMain by creating {
                dependsOn(commonMain)
            }

            val nativeTest by creating {
                dependsOn(nativeMain)
                dependsOn(commonTest)
            }

            val iosArm64Main by getting {
                dependsOn(nativeMain)
            }

            val iosArm64Test by getting {
                dependsOn(iosArm64Main)
                dependsOn(nativeTest)
            }

            val iosArm32Main by getting {
                dependsOn(nativeMain)
            }
            val iosArm32Test by getting {
                dependsOn(iosArm32Main)
                dependsOn(nativeTest)
            }

            val iosX64Main by getting {
                dependsOn(nativeMain)
            }

            val iosX64Test by getting {
                dependsOn(iosX64Main)
                dependsOn(nativeTest)
            }
        }
    }
}

//npmPublishing {
//    organization = "asoft-ltd"
//    bundleKotlinDependencies = false
//    repositories {
//        repository("npm") {
//            registry = uri("https://registry.npmjs.org")
//            authToken = "5a1fa3b1-f226-4b06-a9bc-00fb463909a1"
//        }
//    }
//}

aSoftOSSLibrary(
    version = vers.asoft.live,
    description = "An multiplatform representation of a Live<T> object"
)