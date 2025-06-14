plugins {
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.compose.multiplatform)
}

kotlin {
    androidTarget()
    jvm("desktop")

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(compose.runtime)
                implementation(compose.foundation)
                implementation(compose.material3)
            }
        }
        val androidMain by getting
        val desktopMain by getting
    }
}

android {
    namespace = "com.natio21.nocoiner_control.shared"
    compileSdk = 35
    defaultConfig {
        minSdk = 26
        targetSdk = 34
    }
}
