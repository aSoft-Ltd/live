import org.jetbrains.compose.compose
import org.jetbrains.kotlin.gradle.dsl.KotlinCompile
import org.jetbrains.kotlin.gradle.dsl.KotlinJsCompile

plugins {
    id("org.jetbrains.compose")
    kotlin("multiplatform")
    id("tz.co.asoft.library")
    signing
}

repositories {
    mavenCentral()
    maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
    google()
}

kotlin {
    jvm {
        library()
    }
    js(IR) { library() }
    // macosArm64()
    // macosX64()
    // iosArm64()
    // iosX64()

    sourceSets {
        val commonMain by getting {
            dependencies {
                api(projects.liveCore)
                api(compose.runtime)
            }
        }

        val commonTest by getting {
            dependencies {
                implementation(projects.expectCore)
            }
        }
    }
}

compose {
    kotlinCompilerPlugin.set(kotlinz.versions.compose.compiler)
    kotlinCompilerPluginArgs.add(kotlinz.versions.kotlin.map {
        "suppressKotlinVersionCompatibilityCheck=$it"
    })
}

tasks.withType(KotlinJsCompile::class).configureEach {
    kotlinOptions {
        val v = kotlinz.versions.kotlin.get()
        freeCompilerArgs += listOf(
            "-P", "plugin:androidx.compose.compiler.plugins.kotlin:suppressKotlinVersionCompatibilityCheck=$v"
        )
    }
}

aSoftOSSLibrary(
    version = asoft.versions.root.get(),
    description = "Bindings for Live<S> object to be used with compose"
)