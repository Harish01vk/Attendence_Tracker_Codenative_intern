plugins {
    id("com.android.application")
}

android {
    namespace = "com.example.attendence_tracker"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.attendence_tracker"
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
}

dependencies {

    implementation("androidx.appcompat:appcompat:1.7.0")
    implementation("com.google.android.material:material:1.12.0")
    implementation("androidx.constraintlayout:constraintlayout:2.2.1")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.2.1")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.6.1")
    implementation ("com.airbnb.android:lottie:6.1.0")
// https://mvnrepository.com/artifact/com.squareup.retrofit2/retrofit
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
// https://mvnrepository.com/artifact/com.squareup.retrofit2/converter-gson
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
// https://mvnrepository.com/artifact/com.squareup.retrofit2/converter-scalars
    implementation("com.squareup.retrofit2:converter-scalars:2.9.0")
    implementation ("androidx.recyclerview:recyclerview:1.1.0")
    implementation ("androidx.recyclerview:recyclerview-selection:1.1.0")
    // HTTP logging interceptor for debugging
    implementation("com.squareup.okhttp3:logging-interceptor:4.9.0")
}