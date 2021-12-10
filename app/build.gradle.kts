import com.pouyaheydari.android.buildsrc.Libs

plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
}

android {
    compileSdk = 31

    defaultConfig {
        applicationId = "com.pouyaheydari.android.evehiclerenting"
        minSdk = 21
        targetSdk = 31
        versionCode = 100
        versionName = "1.0.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        getByName("release") {
            isShrinkResources = true
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    buildFeatures {
        viewBinding = true
    }
}

dependencies {

    implementation(project(":core"))

    implementation(Libs.AndroidX.coreKtx)
    implementation(Libs.AndroidX.appcompat)
    implementation(Libs.AndroidX.constraintLayout)

    implementation(Libs.Material.material)

    implementation(Libs.AndroidX.Navigation.fragment)
    implementation(Libs.AndroidX.Navigation.ui)
    implementation(Libs.AndroidX.activity)
    implementation(Libs.AndroidX.liveData)
    implementation(Libs.AndroidX.viewModel)

    implementation(Libs.Map.googleMap)

    kapt(Libs.Network.Moshi.moshi)

    implementation(Libs.Hilt.android)
    kapt(Libs.Hilt.compiler)

    implementation(Libs.Network.Retrofit.retrofit)
    implementation(Libs.Network.Retrofit.moshiConverter)
    implementation(Libs.Network.Retrofit.logging)

    testImplementation(Libs.JUnit.junit)
    androidTestImplementation(Libs.Test.Ext.junit)
    androidTestImplementation(Libs.Test.espressoCore)
}

kapt {
    correctErrorTypes = true
}
