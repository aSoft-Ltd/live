plugins {
    kotlin("multiplatform")
    id("tz.co.asoft.library")
    id("io.codearte.nexus-staging")
    signing
}

kotlin {
    multiplatformLib(withJava = true)
    val isMac = System.getenv("MACHINE") == "mac"
    if (isMac) {
        iosArm64()
        iosArm32()
        iosX64()
    }
//    linuxArm64, linuxArm32Hfp, linuxMips32, linuxMipsel32, linuxX64
    linuxArm64()
    linuxArm32Hfp()
    linuxX64()
    sourceSets {
        val commonMain by getting {}

        val commonTest by getting {
            dependencies {
                implementation(kotlin("test-common"))
                implementation(kotlin("test-annotations-common"))
            }
        }

        val jvmMain by getting {
            dependencies {
                implementation(kotlin("test"))
                implementation(kotlin("test-junit"))
            }
        }

        val jsMain by getting {
            dependencies {
                implementation(kotlin("test-js"))
            }
        }

        val nativeMain by creating {
            dependsOn(commonMain)
        }

        val nativeTest by creating {
            dependsOn(nativeMain)
            dependsOn(commonTest)
        }

        val linuxArm64Main by getting {
            dependsOn(nativeMain)
        }
        val linuxArm32HfpMain by getting {
            dependsOn(nativeMain)
        }
        val linuxX64Main by getting {
            dependsOn(nativeMain)
        }

        val linuxArm64Test by getting {
            dependsOn(nativeMain)
            dependsOn(nativeTest)
        }
        val linuxArm32HfpTest by getting {
            dependsOn(nativeMain)
            dependsOn(nativeTest)
        }
        val linuxX64Test by getting {
            dependsOn(nativeMain)
            dependsOn(nativeTest)
        }

        if (isMac) {
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

aSoftOSSLibrary(
    version = vers.asoft.live,
    description = "An multiplatform representation of a Live<T> object"
)