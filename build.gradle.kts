plugins {
    id("org.jetbrains.dokka") version "2.0.0"
}

subprojects {
    apply(plugin = "org.jetbrains.dokka")
}

dokkaHtml {
    outputDirectory.set(file("build/documentation/html"))
}


tasks.register("Delete", Delete::class) {
    delete(rootProject.layout.buildDirectory)
}
