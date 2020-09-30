import com.android.build.gradle.LibraryExtension
import com.jfrog.bintray.gradle.BintrayExtension
import org.gradle.api.Project
import org.gradle.api.publish.PublishingExtension
import org.gradle.api.publish.maven.MavenPublication
import org.gradle.api.tasks.bundling.Jar
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.create
import org.gradle.kotlin.dsl.creating
import org.gradle.kotlin.dsl.get
import org.gradle.kotlin.dsl.getValue
import org.gradle.kotlin.dsl.provideDelegate

private fun Project.getPropertyOrSystemEnv(propertyName: String, systemEnvName: String): String? =
    properties.getOrDefault(propertyName, System.getenv(systemEnvName))?.toString()

fun Project.applyBintrayUpload(javadocJar: Jar) {
    val androidExtension = extensions.findByName("android") as? LibraryExtension
    if (androidExtension == null) {
        logger.warn("Not android library, skipping")
        return
    }

    apply(plugin = Plugins.androidLibrary)
    apply(plugin = "maven-publish")
    apply(plugin = Plugins.bintray)

    val POM_DESCRIPTION: String by rootProject
    val POM_URL: String by rootProject
    val POM_SCM_URL: String by rootProject
    val POM_SCM_CONNECTION: String by rootProject
    val POM_SCM_DEV_CONNECTION: String by rootProject
    val POM_LICENCE_NAME: String by rootProject
    val POM_LICENCE_URL: String by rootProject
    val POM_LICENCE_DIST: String by rootProject
    val POM_DEVELOPER_ID: String by rootProject
    val POM_DEVELOPER_NAME: String by rootProject

    val POM_NAME: String by project
    val POM_ARTIFACT_ID: String by project

    val bintrayUser = rootProject.getPropertyOrSystemEnv("bintray.user", "bintrayUser") ?: ""
    val bintrayApiKey = rootProject.getPropertyOrSystemEnv("bintray.apikey", "bintrayApiKey") ?: ""
    val sonatypeUser = rootProject.getPropertyOrSystemEnv("sonatype.user", "sonatypeUser") ?: ""
    val sonatypePassword = rootProject.getPropertyOrSystemEnv("sonatype.password", "sonatypePassword") ?: ""

    val sourceJar by tasks.creating(Jar::class) {
        archiveClassifier.set("sources")
        from(androidExtension.sourceSets["main"].java.srcDirs)
    }

    afterEvaluate {
        configure<PublishingExtension> {
            publications {
                create<MavenPublication>("release") {
                    from(components["release"])
                    groupId = project.group.toString()
                    artifactId = POM_ARTIFACT_ID
                    version = project.version.toString()

                    artifact(sourceJar)
                    artifact(javadocJar)

                    pom {
                        name.set(POM_NAME)
                        description.set(POM_DESCRIPTION)
                        url.set(POM_URL)
                        licenses {
                            license {
                                name.set(POM_LICENCE_NAME)
                                url.set(POM_LICENCE_URL)
                                distribution.set(POM_LICENCE_DIST)
                            }
                        }
                        developers {
                            developer {
                                id.set(POM_DEVELOPER_ID)
                                name.set(POM_DEVELOPER_NAME)
                            }
                        }
                        scm {
                            url.set(POM_SCM_URL)
                            connection.set(POM_SCM_CONNECTION)
                            developerConnection.set(POM_SCM_DEV_CONNECTION)
                        }
                    }
                }
            }
        }
        configure<BintrayExtension> {
                user = bintrayUser
                key = bintrayApiKey
                setPublications("release")

                isDryRun = false
                isPublish = true
                pkg.apply {
                    repo = "maven"
                    userOrg = "piano"
                    name = "${project.group}:${POM_ARTIFACT_ID}"
                    desc = POM_DESCRIPTION
                    websiteUrl = POM_URL
                    vcsUrl = POM_SCM_CONNECTION
                    setLicenses(POM_LICENCE_NAME)
                    version.apply {
                        name = project.version.toString()
                        gpg.apply {
                            sign = true
                        }
                        mavenCentralSync.apply {
                            user = sonatypeUser
                            password = sonatypePassword
                        }
                    }
                }
        }
    }
}