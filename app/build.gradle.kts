plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    id("kotlin-android")
    id("kotlin-kapt")
}

android {
    namespace = "com.example.estagios_server"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.estagios_server"
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

    implementation(libs.androidx.activity)
    val kotlin_version = "1.6.0"
    val roomVersion = "2.6.0"
    val activityVersion = "1.4.0"
    val appCompatVersion = "1.4.0"
    val constraintLayoutVersion = "2.1.2"
    val coreTestingVersion = "2.1.0"
    val coroutines = "1.5.2"
    val lifecycleVersion = "2.4.0"
    val materialVersion = "1.4.0"
    val junitVersion = "4.13.2"
    val espressoVersion = "3.4.0"
    val androidxJunitVersion = "1.1.3"


    // Dependências básicas
    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.10.0")
    implementation("androidx.activity:activity-ktx:1.8.2")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("com.google.android.gms:play-services-maps:18.2.0")


    // Lifecycle
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.0")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.6.0")
    implementation("androidx.lifecycle:lifecycle-common-java8:2.6.0")

    // RecyclerView
    implementation("androidx.recyclerview:recyclerview:1.2.1")
    implementation(libs.androidx.room.common)
    implementation(libs.androidx.room.ktx)

    // Testes
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

    // Retrofit
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")

    // Kotlin Coroutines
    val coroutinesVersion = "1.6.4"
    api("org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutinesVersion")
    api("org.jetbrains.kotlinx:kotlinx-coroutines-android:$coroutinesVersion")


    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.10.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

    implementation("androidx.appcompat:appcompat:$appCompatVersion")
    implementation("androidx.activity:activity-ktx:$activityVersion")
    implementation("androidx.room:room-runtime:2.5.0")
    annotationProcessor("androidx.room:room-compiler:2.5.0")

// Para suporte a Kotlin coroutines e Flow
    implementation("androidx.room:room-ktx:2.5.0")

// Dependencies for working with Architecture components
// You'll probably have to update the version numbers in build.gradle (Project)

// Room components
    implementation("androidx.room:room-ktx:$roomVersion")
    kapt("androidx.room:room-compiler:$roomVersion")
    androidTestImplementation("androidx.room:room-testing:$roomVersion")

// Lifecycle components
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycleVersion")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:$lifecycleVersion")
    implementation("androidx.lifecycle:lifecycle-common-java8:$lifecycleVersion")

// Kotlin components
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk7:$kotlin_version")
    api("org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutines")
    api("org.jetbrains.kotlinx:kotlinx-coroutines-android:$coroutines")

// UI
    implementation("androidx.constraintlayout:constraintlayout:$constraintLayoutVersion")
    implementation("com.google.android.material:material:$materialVersion")

// Testing
    testImplementation("junit:junit:$junitVersion")
    androidTestImplementation("androidx.arch.core:core-testing:$coreTestingVersion")

    androidTestImplementation("androidx.test.espresso:espresso-core:$espressoVersion")
    {
        exclude(group = "com.android.support", module = "support-annotations")
    }
    androidTestImplementation("androidx.test.ext:junit:$androidxJunitVersion")

}