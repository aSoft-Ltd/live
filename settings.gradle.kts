pluginManagement {
    enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

    repositories {
        google()
        gradlePluginPortal()
        mavenCentral()
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
    }

    dependencyResolutionManagement {
        versionCatalogs {
            file("gradle/versions").listFiles().map {
                it.nameWithoutExtension to it.absolutePath
            }.forEach { (name, path) ->
                create(name) { from(files(path)) }
            }
        }
    }
}

fun includeRoot(name: String, path: String) {
    include(":$name")
    project(":$name").projectDir = File(path)
}

fun includeSubs(base: String, path: String = base, vararg subs: String) {
    subs.forEach {
        include(":$base-$it")
        project(":$base-$it").projectDir = File("$path/$it")
    }
}

val tmp = 1

rootProject.name = "asoft"

// dependencies
includeSubs("functions", "../functions", "core")
includeSubs("expect", "../expect", "core", "coroutines")
includeSubs("kollections", "../kollections", "interoperable")
includeSubs("koncurrent-primitives", "../koncurrent/primitives", "core", "coroutines", "mock")

// submodules
includeSubs("live", ".", "core", "kollections", "coroutines", "react", "test", "compose")