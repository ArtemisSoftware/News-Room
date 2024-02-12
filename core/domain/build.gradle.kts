plugins {
    kotlin("jvm")
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

dependencies {

    implementation(project(":core:model"))
    implementation(libs.dagger)
    implementation(libs.kotlinx.coroutines.core)
}
