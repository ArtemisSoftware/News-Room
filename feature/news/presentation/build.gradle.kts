@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("dagger.hilt.android.plugin")
    alias(libs.plugins.com.google.devtools.ksp)
}

android {
    namespace = "com.news.presentation"
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
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = Compose.kotlinCompilerExtensionVersion
    }
}

dependencies {

    implementation("androidx.core:core-ktx:1.8.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.3.1")

    implementation(Compose.activityCompose)
    implementation(platform(Compose.bom))
    implementation(Compose.ui)
    implementation(Compose.uiGraphics)
    implementation(Compose.uiToolingPreview)
    implementation(Compose.material3)


    implementation(libs.hilt.android)
    ksp(libs.hilt.android.compiler)
    implementation(Compose.hiltNavigationCompose)
    implementation(Compose.navigation)


    implementation(project(Modules.coreDomain))
    implementation(project(Modules.newsDomain))
    implementation(project(Modules.coreUi))


    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation (platform("androidx.compose:compose-bom:2022.10.00"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")










    //

//    implementation("androidx.core:core-ktx:1.8.0")
//    implementation("androidx.appcompat:appcompat:1.6.1")

    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.2.0")

    implementation("com.google.accompanist:accompanist-pager:0.23.1")
    implementation("com.google.accompanist:accompanist-pager-indicators:0.23.1")

    implementation(project(Modules.newsDomain))







//
//    testImplementation("junit:junit:4.13.2")
//    androidTestImplementation("androidx.test.ext:junit:1.1.5")
//    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
}
