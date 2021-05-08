package com.yonder.addtolist

/**
 * Yusuf Onder on 04,May,2021
 */

object Libs {

  object Gradle{

    const val androidGradlePlugin = "com.android.tools.build:gradle:4.1.3"

  }
  object Kotlin {
    const val org_jetbrains_kotlin: String =
      "org.jetbrains.kotlin:kotlin-stdlib:" + Versions.jetbrains_kotlin
    const val gradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:" + Versions.gradle_plugin
  }



  object UnitTest {
    const val junit_test: String = "androidx.test.ext:junit:" + Versions.junit_test
    const val junit: String = "junit:junit:" + Versions.junit
  }

  object AndroidTest {
    const val espresso_core: String = "androidx.test.espresso:espresso-core:" +
        Versions.espresso_core
  }

  object Network {
    const val gson: String = "com.google.code.gson:gson:" + Versions.gson

    const val retrofit = "com.squareup.retrofit2:retrofit:" + Versions.retrofit
    const val converter = "com.squareup.retrofit2:converter-gson:" + Versions.retrofit
    const val okhttp = "com.squareup.okhttp3:okhttp:" + Versions.ok_http
    const val interceptor = "com.squareup.okhttp3:logging-interceptor:" + Versions.ok_http
  }

  object AndroidX {
    const val androidx_appcompat: String =
      "androidx.appcompat:appcompat:" + Versions.androidx_appcompat
    const val core_ktx: String = "androidx.core:core-ktx:" + Versions.core_ktx
    const val constraint_layout: String =
      "androidx.constraintlayout:constraintlayout:" + Versions.constraint_layout
    const val navigation_fragment: String =
      "androidx.navigation:navigation-fragment-ktx:" + Versions.navigation
    const val navigation_ui_ktx: String =
      "androidx.navigation:navigation-ui-ktx:" + Versions.navigation
    const val legacy_support: String =
      "androidx.legacy:legacy-support-v4:" + Versions.legacy_support
    const val live_data_vm: String =
      "androidx.lifecycle:lifecycle-livedata-ktx:" + Versions.livedata
    const val live_data_ktx: String =
      "androidx.lifecycle:lifecycle-viewmodel-ktx:" + Versions.livedata
    const val dataStore : String = "androidx.datastore:datastore-preferences:" + Versions.dataStore
  }

  object Design {
    const val material: String = "com.google.android.material:material:" + Versions.material
  }

  object DaggerHilt {
    const val daggerHilt = "com.google.dagger:hilt-android:" + Versions.hilt_version
    const val daggerHiltCompiler = "com.google.dagger:hilt-compiler:" + Versions.hilt_version
    const val daggerHiltGradlePlugin =
      "com.google.dagger:hilt-android-gradle-plugin:" + Versions.hilt_version
  }


}