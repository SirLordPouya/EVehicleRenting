buildscript {
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath(com.pouyaheydari.android.buildsrc.Libs.Gradle.build)
        classpath(com.pouyaheydari.android.buildsrc.Libs.Kotlin.gradlePlugin)
        classpath(com.pouyaheydari.android.buildsrc.Libs.Hilt.plugin)
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}