object Libs {

    object KotlinX {
        const val coroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutines}"
        const val coroutinesAndroid = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutines}"
    }

    object AndroidX {
        const val core = "androidx.core:core-ktx:${Versions.core}"
        const val appcompat = "androidx.appcompat:appcompat:${Versions.appcompat}"
        const val activityCompose = "androidx.activity:activity-compose:${Versions.appcompat}"
        const val dataStore = "androidx.datastore:datastore-preferences:${Versions.dataStore}"
        const val material = "com.google.android.material:material:${Versions.material}"

        object Lifecycle {
            const val common = "androidx.lifecycle:lifecycle-common-java8:${Versions.lifecycle}"
            const val viewmodelKtx = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycle}"
            const val runtimeKtx = "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.lifecycle}"
        }

        object Compose {
            const val compiler = "androidx.compose.compiler:compiler:${Versions.compose}"
            const val runtime = "androidx.compose.runtime:runtime:${Versions.compose}"
            const val foundation = "androidx.compose.foundation:foundation:${Versions.compose}"
            const val ui = "androidx.compose.ui:ui:${Versions.compose}"
            const val uiUtil = "androidx.compose.ui:ui-util:${Versions.compose}"
            const val uiTooling = "androidx.compose.ui:ui-tooling:${Versions.compose}"
            const val uiToolingPreview = "androidx.compose.ui:ui-tooling-preview:${Versions.compose}"
            const val material = "androidx.compose.material:material:${Versions.compose}"
            const val accompanistSystemUi = "com.google.accompanist:accompanist-systemuicontroller:${Versions.accompanist}"
        }

        object Navigation {
            const val compose = "androidx.navigation:navigation-compose:2.4.0-alpha08"
        }

        object Room {
            const val runtime = "androidx.room:room-runtime:${Versions.room}"
            const val compiler = "androidx.room:room-compiler:${Versions.room}"
            const val ktx = "androidx.room:room-ktx:${Versions.room}"
        }

        object Hilt {
            const val navigationCompose = "androidx.hilt:hilt-navigation-compose:1.0.0-alpha03"
        }
    }

    object Dagger {
        const val hiltAndroid = "com.google.dagger:hilt-android:${Versions.dagger}"
        const val hiltAndroidCompiler = "com.google.dagger:hilt-android-compiler:${Versions.dagger}"
    }

    object Coil {
        const val compose = "io.coil-kt:coil-compose:${Versions.coil}"
    }
}


object Versions {
    const val kotlin = "1.5.21"
    const val coroutines = "1.5.2"
    const val core = "1.3.2"
    const val appcompat = "1.3.1"
    const val material = "1.4.0"
    const val lifecycle = "2.3.1"
    const val compose = "1.0.2"
    const val room = "2.3.0"
    const val dataStore = "1.0.0"
    const val dagger = "2.38.1"
    const val coil = "1.3.2"
    const val accompanist = "0.18.0"
}
