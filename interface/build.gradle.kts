plugins {
    id("com.android.library")
    id("maven-publish")
    id("signing")
}

android {
    namespace = "io.github.libxposed.service"
    compileSdk = 34
    buildToolsVersion = "34.0.0"

    defaultConfig {
        minSdk = 24
    }

    buildFeatures {
        buildConfig = false
        resValues = false
        aidl = true
    }

    compileOptions {
        targetCompatibility = JavaVersion.VERSION_21
        sourceCompatibility = JavaVersion.VERSION_21
    }

    publishing {
        singleVariant("release") {
            withSourcesJar()
            withSourcesJar()
        }
    }
}


publishing {
    publications {
        register<MavenPublication>("libxposed-interface") {
            artifactId = "libxposed-interface"
            group = "io.github.decryptable"
            version = "1.0.2"
            pom {
                name.set("libxposed-interface")
                description.set("Modern Xposed Service Interface")
                url.set("https://github.com/decryptable/libxposed-service")
                licenses {
                    license {
                        name.set("Apache License 2.0")
                        url.set("https://github.com/decryptable/libxposed-service/blob/master/LICENSE")
                    }
                }
                developers {
                    developer {
                        name.set("decryptable")
                        url.set("https://github.com/decryptable")
                    }
                }
                scm {
                    connection.set("scm:git:https://github.com/decryptable/libxposed-service.git")
                    url.set("https://github.com/decryptable/libxposed-service")
                }
            }
            afterEvaluate {
                from(components.getByName("release"))
            }
        }
    }
    repositories {
        mavenLocal()
        maven {
            name = "GitHubPackages"
            url = uri("https://maven.pkg.github.com/decryptable/libxposed-service")
            credentials {
                username = System.getenv("GITHUB_ACTOR")
                password = System.getenv("GITHUB_TOKEN")
            }
        }
    }
}
