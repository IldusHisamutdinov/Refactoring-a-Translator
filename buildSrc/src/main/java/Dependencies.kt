import org.gradle.api.JavaVersion

object Config {
    const val application_id = "ru.ildus.translator"
    const val compile_sdk = 30
    const val min_sdk = 26
    const val target_sdk = 30
    val java_version = JavaVersion.VERSION_1_8
}

object Releases {
    const val version_code = 1
    const val version_name = "1.0"
}

object Modules {
    const val app = ":app"
    const val utils = ":utils"
    const val repository = ":repository"
    const val model = ":model"
    const val descriptionscreen = ":descriptionscreen"
}

object Versions {
    //Tools
    const val multidex = "1.0.3"

    //Design
    const val appcompat = "1.2.0"
    const val material = "1.3.0"

    //Kotlin
    const val core = "1.3.2"
    const val stdlib = "1.4.21"
    const val coroutinesCore = "1.2.1"
    const val coroutinesAndroid = "1.1.1"

    //Retrofit
    const val retrofit = "2.9.0"
    const val converterGson = "2.9.0"
    const val interceptor = "4.2.1"
    const val adapterCoroutines = "0.9.2"

    //Koin
    const val koinAndroid = "2.0.1"
    const val koinViewModel = "2.0.1"

    //Glide
    const val glide = "4.11.0"
    const val glideCompiler = "4.9.0"
    const val glideSwiper = "1.0.0"

    //Room
    const val roomKtx = "2.2.6"
    const val runtime = "2.2.6"
    const val roomCompiler = "2.2.6"

    //Google Play
    const val googlePlayCore = "1.10.0"

    //Test
    const val jUnit = "4.13.1"
    const val runner = "1.2.0"
    const val espressoCore = "3.3.0"
    const val constraint = "2.0.4"
}

object Tools {
    const val multidex = "com.android.support:multidex:${Versions.multidex}"
    const val appcompat = "androidx.appcompat:appcompat:${Versions.appcompat}"
}

object Design {
    const val material = "com.google.android.material:material:${Versions.material}"
}

object Kotlin {
    const val core = "androidx.core:core-ktx:${Versions.core}"
    const val stdlib = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.stdlib}"
    const val coroutines_core = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutinesCore}"
    const val coroutines_android = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutinesAndroid}"
}

object Retrofit {
    const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    const val converter_gson = "com.squareup.retrofit2:converter-gson:${Versions.converterGson}"
    const val logging_interceptor = "com.squareup.okhttp3:logging-interceptor:${Versions.interceptor}"
    const val adapter_coroutines =
        "com.jakewharton.retrofit:retrofit2-kotlin-coroutines-adapter:${Versions.adapterCoroutines}"
}

object Koin {
    const val koin_android = "org.koin:koin-android:${Versions.koinAndroid}"
    const val koin_view_model = "org.koin:koin-android-viewmodel:${Versions.koinViewModel}"
}

object Glide {
    const val glide = "com.github.bumptech.glide:glide:${Versions.glide}"
    const val glide_compiler = "com.github.bumptech.glide:compiler:${Versions.glideCompiler}"
    const val glide_swiper = "androidx.swiperefreshlayout:swiperefreshlayout:${Versions.glideSwiper}"
}

object Room {
    const val runtime = "androidx.room:room-runtime:${Versions.runtime}"
    const val compiler = "androidx.room:room-compiler:${Versions.roomCompiler}"
    const val room_ktx = "androidx.room:room-ktx:${Versions.roomKtx}"
}

object TestImpl {
    const val junit = "junit:junit:${Versions.jUnit}"
    const val runner = "androidx.test:runner:${Versions.runner}"
    const val espresso = "androidx.test.espresso:espresso-core:${Versions.espressoCore}"
    const val constraint = "androidx.constraintlayout:constraintlayout:${Versions.constraint}"
}

object GooglePlay {
    const val googlePlayCore = "com.google.android.play:core:${Versions.googlePlayCore}"
}