// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id("com.android.application") version "8.0.1" apply false
    id("com.android.library") version "8.0.1" apply false
    id("org.jetbrains.kotlin.android") version "1.7.20" apply false
    id("com.google.dagger.hilt.android") version DaggerHilt.version apply false
    id("org.jetbrains.kotlin.jvm") version "1.8.20" apply false
//    alias(libs.plugins.com.google.devtools.ksp) apply false
//    alias(libs.plugins.com.google.dagger.hilt.android) apply false
}

tasks.create<Delete>("clean") {
    delete(rootProject.buildDir)
}
