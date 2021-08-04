plugins {
    kotlin("multiplatform")
    id("tz.co.asoft.library")
    id("io.codearte.nexus-staging")
    signing
}

kotlin {
    jvm { library(); withJava() }
    js(IR) { library() }
    val nativeTargets = nativeTargets(true)

    sourceSets {
        val commonMain by getting {}

        val commonTest by getting {
            dependencies {
                implementation(asoft("expect-coroutines", vers.asoft.expect))
            }
        }

        val nativeMain by creating {
            dependsOn(commonMain)
            dependencies {
                api(asoft("kotlinx-atomic-collections", vers.asoft.collections))
            }
        }

        val nativeTest by creating {
            dependsOn(nativeMain)
            dependsOn(commonTest)
        }

        for (target in nativeTargets) {
            val main by target.compilations.getting {
                defaultSourceSet {
                    dependsOn(nativeMain)
                }
            }

            val test by target.compilations.getting {
                defaultSourceSet {
                    dependsOn(nativeMain)
                    dependsOn(main.defaultSourceSet)
                }
            }
        }
    }
}

aSoftOSSLibrary(
    version = vers.asoft.live,
    description = "An multiplatform representation of a Live<T> object"
)