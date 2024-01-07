buildscript {

    repositories {
        google()
        mavenCentral()
        maven(url = "https://plugins.gradle.org/m2/")
    }

    dependencies {
        classpath(libs.detekt.gradle.plugin)
        classpath(libs.gradle)
        classpath(libs.gradle.versions.plugin)
        classpath(libs.kotlin.gradle.plugin)
        classpath("com.google.android.libraries.mapsplatform.secrets-gradle-plugin:secrets-gradle-plugin:2.0.1")



    }

    configurations.classpath {
        resolutionStrategy {
            force(
                "com.pinterest.ktlint:ktlint-rule-engine:${libs.versions.ktlint.get()}",
                "com.pinterest.ktlint:ktlint-rule-engine-core:${libs.versions.ktlint.get()}",
                "com.pinterest.ktlint:ktlint-cli-reporter-core:${libs.versions.ktlint.get()}",
                "com.pinterest.ktlint:ktlint-cli-reporter-checkstyle:${libs.versions.ktlint.get()}",
                "com.pinterest.ktlint:ktlint-cli-reporter-json:${libs.versions.ktlint.get()}",
                "com.pinterest.ktlint:ktlint-cli-reporter-html:${libs.versions.ktlint.get()}",
                "com.pinterest.ktlint:ktlint-cli-reporter-plain:${libs.versions.ktlint.get()}",
                "com.pinterest.ktlint:ktlint-cli-reporter-sarif:${libs.versions.ktlint.get()}",
                "com.pinterest.ktlint:ktlint-ruleset-standard:${libs.versions.ktlint.get()}",
            )
        }
    }
}

plugins {
    id("com.google.dagger.hilt.android").version(libs.versions.hilt).apply(false)
    id("io.gitlab.arturbosch.detekt").version(libs.versions.detektGradlePlugin)
    id("org.jmailen.kotlinter").version(libs.versions.kotlinter).apply(false)
    id("com.google.devtools.ksp").version(libs.versions.ksp).apply(false)
    id("com.squareup.sort-dependencies").version(libs.versions.sortDependencies).apply(false)
}





