plugins {
//    id("lt.petuska.npm.publish") version "1.1.2"
    kotlin("js")
    id("tz.co.asoft.library")
    id("io.codearte.nexus-staging")
    signing
}

kotlin {
    js(IR) {
        library()
        binaries.library()
    }
    sourceSets {
        val main by getting {
            dependencies {
                api(project(":live-core"))
                api("org.jetbrains:kotlin-react:${vers.wrappers.react}")
            }
        }
        val test by getting {
            dependencies {
                api(asoft("expect-core", vers.asoft.expect))
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