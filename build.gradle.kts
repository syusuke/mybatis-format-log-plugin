fun properties(key: String) = project.findProperty(key).toString()

plugins {
    java
    id("org.jetbrains.intellij") version "1.2.1"
}

group = "com.coolxiaoyao.mybatis"
version = "1.0.0"

repositories {
    mavenCentral()
    mavenLocal()
}

dependencies {
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.8.1")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine")
    implementation("com.coolxiaoyao.mybatislog:mybatis-log-format-helper:1.0.0")
    runtimeOnly("com.coolxiaoyao.mybatislog:mybatis-log-format-helper:1.0.0")
    compileOnly("com.coolxiaoyao.mybatislog:mybatis-log-format-helper:1.0.0")

}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}


// Configure Gradle IntelliJ Plugin - read more: https://github.com/JetBrains/gradle-intellij-plugin
intellij {
    pluginName.set(properties("pluginName"))
    version.set(properties("platformVersion"))
    type.set(properties("platformType"))
    downloadSources.set(properties("platformDownloadSources").toBoolean())
    updateSinceUntilBuild.set(true)

    // Plugin Dependencies. Uses `platformPlugins` property from the gradle.properties file.
    plugins.set(properties("platformPlugins").split(',').map(String::trim).filter(String::isNotEmpty))
}