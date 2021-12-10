buildscript {
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        //TODO: USE LIBS
        classpath("com.android.tools.build:gradle:7.0.4")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.6.0")
        classpath("com.google.dagger:hilt-android-gradle-plugin:2.40.5")
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}