plugins {
    id("java-library")
    id("org.jetbrains.kotlin.jvm")
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

dependencies {
    implementation(KotlinX.coroutineCore)

    implementation(DaggerHilt.dagger)
    implementation(project(Modules.coreDomain))
}
