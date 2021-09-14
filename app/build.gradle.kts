plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
}

android {
    compileSdk = BuildConfig.compileSdk

    defaultConfig {
        applicationId = "hr.vub.itepavac"
        minSdk = BuildConfig.minSdk
        targetSdk = BuildConfig.targetSdk

        versionCode = BuildConfig.versionCode
        versionName = BuildConfig.versionName

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        getByName("debug") {
            isDebuggable = true
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }

        getByName("release") {
            isMinifyEnabled = true
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = Versions.compose
    }
}

dependencies {
    implementation(Libs.AndroidX.core)
    implementation(Libs.AndroidX.appcompat)
    implementation(Libs.AndroidX.activityCompose)
    implementation(Libs.AndroidX.dataStore)
    implementation(Libs.AndroidX.material)

    implementation(Libs.AndroidX.Lifecycle.common)
    implementation(Libs.AndroidX.Lifecycle.viewmodelKtx)
    implementation(Libs.AndroidX.Lifecycle.runtimeKtx)

    implementation(Libs.AndroidX.Compose.compiler)
    implementation(Libs.AndroidX.Compose.runtime)
    implementation(Libs.AndroidX.Compose.foundation)
    implementation(Libs.AndroidX.Compose.ui)
    implementation(Libs.AndroidX.Compose.uiUtil)
    implementation(Libs.AndroidX.Compose.uiTooling)
    implementation(Libs.AndroidX.Compose.material)
    implementation(Libs.AndroidX.Compose.accompanistSystemUi)
    debugImplementation(Libs.AndroidX.Compose.uiToolingPreview)

    implementation(Libs.AndroidX.Navigation.compose)

    implementation(Libs.KotlinX.coroutines)
    implementation(Libs.KotlinX.coroutinesAndroid)

    kapt(Libs.AndroidX.Room.compiler)
    implementation(Libs.AndroidX.Room.runtime)
    implementation(Libs.AndroidX.Room.ktx)

    kapt(Libs.Dagger.hiltAndroidCompiler)
    implementation(Libs.Dagger.hiltAndroid)
    implementation(Libs.AndroidX.Hilt.navigationCompose)

    implementation(Libs.Coil.compose)
}
