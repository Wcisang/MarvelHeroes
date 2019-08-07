object Version {

    const val kotlin = "1.3.31"
    const val gradle = "3.4.2"
    const val appCompat = "1.1.0-beta01"
    const val coreKtx = "1.2.0-alpha02"
    const val constraintLayout = "1.1.3"
    const val retrofit = "2.6.0"
    const val okHttp = "4.0.0-RC1"
    const val coroutinesAdapter = "0.9.2"
    const val coroutines = "1.3.0-M2"
    const val koin = "2.0.1"
    const val lifecycle = "2.1.0-rc01"
    const val paging = "1.0.1"
    const val picasso = "2.71828"
    const val pallete = "28.0.0"
    const val anko = "0.10.8"

    //test
    const val jUnit = "4.12"
    const val mockitoKotlin = "2.1.0"
    const val mockwebserver = "2.7.5"

    //androidTest
    const val runner = "1.2.0"
    const val expresso = "3.2.0"
    const val barista = "3.1.0"
}

object Libs {

    const val kotlinStd = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Version.kotlin}"
    const val appCompat = "androidx.appcompat:appcompat:${Version.appCompat}"
    const val lifecycle = "androidx.lifecycle:lifecycle-extensions:${Version.lifecycle}"
    const val viewModel = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Version.lifecycle}"
    const val coreKtx =  "androidx.core:core-ktx:${Version.coreKtx}"

    //Layout
    const val constraintLayout =  "androidx.constraintlayout:constraintlayout:${Version.constraintLayout}"
    const val paging = "android.arch.paging:runtime:${Version.paging}"
    const val picasso =  "com.squareup.picasso:picasso:${Version.picasso}"
    const val pallete = "com.android.support:palette-v7:${Version.pallete}"

    //Networking
    const val retrofit = "com.squareup.retrofit2:retrofit:${Version.retrofit}"
    const val okhttp = "com.squareup.okhttp3:okhttp:${Version.okHttp}"
    const val moshiConverter = "com.squareup.retrofit2:converter-moshi:${Version.retrofit}"

    //Coroutines
    const val coroutinesAdapter = "com.jakewharton.retrofit:retrofit2-kotlin-coroutines-adapter:${Version.coroutinesAdapter}"
    const val coroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Version.coroutines}"
    const val coroutinesAndroid = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Version.coroutines}"
    const val anko = "org.jetbrains.anko:anko-common:${Version.anko}"

    //Koin
    const val koinViewModel = "org.koin:koin-android-viewmodel:${Version.koin}"
    const val koin  = "org.koin:koin-android:${Version.koin}"
}

object LibsTest {
    const val koin = "org.koin:koin-test:${Version.koin}"
    const val mockWebServer = "com.squareup.okhttp:mockwebserver:${Version.mockwebserver}"
    const val mockitoKotlin = "com.nhaarman.mockitokotlin2:mockito-kotlin:${Version.mockitoKotlin}"
    const val coroutinesTest = "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Version.coroutines}"
}

object LibsAndroidTest {
    const val barista = "com.schibsted.spain:barista:${Version.barista}"
}