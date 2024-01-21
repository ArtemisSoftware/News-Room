@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    alias(libs.plugins.com.google.devtools.ksp)
}

android {
    namespace = "com.news.data"
    compileSdk = ProjectConfig.compileSdk

    defaultConfig {
        minSdk = 31
        targetSdk = 33

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro",
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_17.toString()
    }
}

dependencies {

    implementation("androidx.core:core-ktx:1.8.0")
    implementation("androidx.appcompat:appcompat:1.6.1")

    implementation(project(Modules.coreData))
    implementation(project(Modules.coreDomain))
    implementation(project(Modules.newsDomain))

    implementation(libs.hilt.android)
    ksp(libs.hilt.android.compiler)

//    implementation(Retrofit.retrofit)
//    implementation(Retrofit.moshiConverter)
//    implementation("com.squareup.okhttp3:okhttp:5.0.0-alpha.11")
//    implementation("com.squareup.okhttp3:logging-interceptor:5.0.0-alpha.11")
//
//    kapt(Room.roomCompiler)
//    implementation(Room.roomKtx)
//    implementation(Room.roomRuntime)

    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
}
