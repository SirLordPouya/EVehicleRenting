package com.pouyaheydari.android.buildsrc

object Libs {

    object Gradle {
        private const val version = "7.0.4"
        const val build = "com.android.tools.build:gradle:$version"
    }

    object Kotlin {
        private const val version = "1.6.0"
        const val stdlib = "org.jetbrains.kotlin:kotlin-stdlib:$version"
        const val gradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:$version"
    }

    object Glide {
        private const val version = "4.12.0"
        const val glide = "com.github.bumptech.glide:glide:$version"
        const val compiler = "com.github.bumptech.glide:compiler:$version"
    }

    object Coroutines {
        private const val version = "1.5.2"
        const val core = "org.jetbrains.kotlinx:kotlinx-coroutines-core:$version"
        const val android = "org.jetbrains.kotlinx:kotlinx-coroutines-android:$version"
        const val test = "org.jetbrains.kotlinx:kotlinx-coroutines-test:$version"
    }

    object Material {
        const val material = "com.google.android.material:material:1.4.0"
    }

    object Test {
        private const val version = "1.4.0"
        const val core = "androidx.test:core:$version"
        const val rules = "androidx.test:rules:$version"
        const val arch = "androidx.arch.core:core-testing:2.1.0"

        object Ext {
            private const val version = "1.1.2"
            const val junit = "androidx.test.ext:junit-ktx:$version"
        }

        object JUnit {
            private const val version = "4.13"
            const val junit = "junit:junit:$version"
        }

        object Mockk {
            const val mockk = "io.mockk:mockk:1.12.1"
        }

        const val espressoCore = "androidx.test.espresso:espresso-core:3.3.0"
    }

    object Map {
        private const val version = "18.0.0"
        const val googleMap = "com.google.android.gms:play-services-maps:$version"
        const val location = "com.google.android.gms:play-services-location:19.0.0"
    }

    object JavaX {
        const val inject = "javax.inject:javax.inject:1"
    }

    object AndroidX {
        private const val lifecycleVersion = "2.4.0"
        const val coreKtx = "androidx.core:core-ktx:1.7.0"
        const val appcompat = "androidx.appcompat:appcompat:1.4.0"
        const val constraintLayout = "androidx.constraintlayout:constraintlayout:2.1.2"
        const val liveData = "androidx.lifecycle:lifecycle-livedata-ktx:$lifecycleVersion"
        const val viewModel = "androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycleVersion"
        const val activity = "androidx.activity:activity-ktx:1.4.0"

        object Navigation {
            private const val version = "2.3.5"
            const val fragment = "androidx.navigation:navigation-fragment-ktx:$version"
            const val ui = "androidx.navigation:navigation-ui-ktx:$version"
        }
    }

    object Network {
        object Retrofit {
            private const val version = "2.9.0"
            const val retrofit = "com.squareup.retrofit2:retrofit:$version"
            const val moshiConverter = "com.squareup.retrofit2:converter-moshi:$version"
            const val logging = "com.squareup.okhttp3:logging-interceptor:5.0.0-alpha.3"
        }

        object Moshi {
            const val moshi = "com.squareup.moshi:moshi-kotlin-codegen:1.12.0"
        }
    }

    object Hilt {
        private const val version = "2.40.5"
        const val android = "com.google.dagger:hilt-android:$version"
        const val compiler = "com.google.dagger:hilt-compiler:$version"
        const val plugin = "com.google.dagger:hilt-android-gradle-plugin:$version"
    }
}