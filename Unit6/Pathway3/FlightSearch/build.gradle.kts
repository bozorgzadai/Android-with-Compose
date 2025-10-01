plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.kotlin.compose) apply false

    // Hilt
    alias(libs.plugins.ksp.project) apply false
    alias(libs.plugins.hilt.project) apply false
}