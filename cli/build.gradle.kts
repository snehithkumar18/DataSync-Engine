plugins {
    application
}

application {
    mainClass.set("com.syncforge.cli.Main")
}

dependencies {
    implementation(project(":core"))
    implementation(project(":checksum"))
    implementation(project(":compression"))
    implementation(project(":path"))
    implementation(project(":metadata"))
    implementation(project(":validation"))
    implementation(project(":serialization"))
    implementation(project(":manifest"))
    implementation(project(":scanner"))
    implementation(project(":snapshot"))
    implementation(project(":diff"))
    implementation(project(":conflict"))
    implementation(project(":patch"))
    implementation(project(":planner"))
    implementation(project(":runtime"))
    implementation(project(":indexing"))
    implementation(project(":query"))
    implementation(project(":storage"))
}
