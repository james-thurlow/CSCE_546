plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsKotlinAndroid)
    
    id("com.android.application")

    // Add the Google services Gradle plugin

    id("com.google.gms.google-services")

}

android {
    namespace = "com.example.workoutapp"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.workoutapp"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    // Import the Firebase BoM

    implementation(platform("com.google.firebase:firebase-bom:32.8.1"))


    // TODO: Add the dependencies for Firebase products you want to use

    // When using the BoM, don't specify versions in Firebase dependencies

    implementation("com.google.firebase:firebase-analytics")


    // Add the dependencies for any other desired Firebase products

    // https://firebase.google.com/docs/android/setup#available-libraries
}