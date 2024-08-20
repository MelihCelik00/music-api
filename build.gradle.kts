import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
	kotlin("jvm").version(libs.versions.kotlin)
	application
	idea
	alias(libs.plugins.springBoot)
	alias(libs.plugins.springDependencyManagement)
	alias(libs.plugins.kotlinJvm)
	alias(libs.plugins.kotlinSpring)
	alias(libs.plugins.swaggerGradlePlugin)
}

group = "com.melih"
version = "0.0.1-SNAPSHOT"

java {
	toolchain {
		languageVersion.set(JavaLanguageVersion.of(21))
	}
}

repositories {
	mavenCentral()
}

dependencies {
	implementation(libs.springBootStarterWeb)
	implementation(libs.kotlinReflect)
	implementation(libs.kotlinStdlib)
	implementation(libs.springdocOpenapiUi)
	compileOnly(libs.lombok)
	developmentOnly(libs.springBootDevtools)
	annotationProcessor(libs.springBootConfigurationProcessor)
	annotationProcessor(libs.lombok)
	testImplementation(libs.springBootStarterTest)
	testImplementation(libs.kotlinTestJunit5)
	testRuntimeOnly(libs.junitPlatformLauncher)
}

kotlin {
	jvmToolchain {
		languageVersion.set(JavaLanguageVersion.of(21))
	}
	freeCompilerArgs += listOf("-Xjsr305=strict")
}

tasks.named<Test>("test") {
	useJUnitPlatform()
}