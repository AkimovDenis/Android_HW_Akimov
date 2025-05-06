plugins {
    id("com.android.application")
    kotlin("android")
}

android {
    namespace = "com.example.hwlesson24"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.hwlesson24"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        getByName("debug") {
            applicationIdSuffix = ".debug"
            versionNameSuffix = "-debug"
        }
        getByName("release") {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    buildFeatures {
        buildConfig = true
    }

    flavorDimensions += "version"

    productFlavors {
        create("free") {
            dimension = "version"
            applicationIdSuffix = ".free"
            versionNameSuffix = "-free"
            buildConfigField("String", "API_URL", "\"https://api.free.example.com\"")
        }
        create("paid") {
            dimension = "version"
            applicationIdSuffix = ".paid"
            versionNameSuffix = "-paid"
            buildConfigField("String", "API_URL", "\"https://api.paid.example.com\"")
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    kotlinOptions {
        jvmTarget = "11"
    }
    dependencies {
        implementation("com.google.android.material:material:1.8.0")
    }

}
