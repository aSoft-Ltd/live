plugins {
//    id("lt.petuska.npm.publish") version "1.1.2"
    kotlin("multiplatform")
    id("tz.co.asoft.library")
    id("io.codearte.nexus-staging")
    signing
}

kotlin {
    multiplatformLib(withJava = true)
    val isMac = System.getenv("machine") == "mac"
    js(IR) {
        binaries.library()
    }
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

            val iosArm64 by getting {
                dependsOn(nativeMain)
            }
            val iosArm32 by getting {
                dependsOn(nativeMain)
            }
            val iosX64 by getting {
                dependsOn(nativeMain)
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