void setBuildStatus(String message, String state) {
  step([
      $class: "GitHubCommitStatusSetter",
      reposSource: [$class: "ManuallyEnteredRepositorySource", url: "https://github.com/kacperkruger/bad-recipes"],
      contextSource: [$class: "ManuallyEnteredCommitContextSource", context: "ci/jenkins/build-status"],
      errorHandlers: [[$class: "ChangingBuildStatusErrorHandler", result: "UNSTABLE"]],
      statusResultSource: [ $class: "ConditionalStatusResultSource", results: [[$class: "AnyBuildResult", message: message, state: state]] ]
  ]);
}

pipeline {
    options {
        timeout(5)
    }

    agent {
        docker {
            image 'openjdk:17-slim'
            args  '-v /tmp:/tmp'
            reuseNode true
        }
    }

    environment {
        DATE = new Date().format('yy.M')
        TAG = "${DATE}.${BUILD_NUMBER}"
    }

    stages {
        stage("build") {
            stage("notification-email") {
                when {
                    changeset "**/notification-email/*.*"
                    beforeAgent true
                }
                steps {
                    dir("notification-email") {
                        setBuildStatus("Building notification-email", "PENDING");
                        sh "./gradlew clean build"
                    }
                }
            }
            stage("notification-sms") {
                when {
                    changeset "**/notification-sms/*.*"
                    beforeAgent true
                }
                steps {
                    dir("notification-sms") {
                        setBuildStatus("Building notification-sms", "PENDING");
                        sh "./gradlew clean build"
                    }
                }
            }
            stage("notification") {
                when {
                    changeset "**/notification/*.*"
                    beforeAgent true
                }
                steps {
                    dir("notification") {
                        setBuildStatus("Building notification", "PENDING");
                        sh "./gradlew clean build"
                    }
                }
            }
            stage("clients") {
                when {
                    changeset "**/clients/*.*"
                    beforeAgent true
                }
                steps {
                    dir("clients") {
                        setBuildStatus("Building clients", "PENDING");
                        sh "./gradlew clean build"
                    }
                }
            }
            stage("eureka-server") {
                when {
                    changeset "**/eureka-server/*.*"
                    beforeAgent true
                }
                steps {
                    dir("eureka-server") {
                        setBuildStatus("Building eureka-server", "PENDING");
                        sh "./gradlew clean build"
                    }
                }
            }
        }

        stage("test") {
            stage("notification-email") {
                when {
                    changeset "**/notification-email/*.*"
                    beforeAgent true
                }
                steps {
                    dir("notification-email") {
                        setBuildStatus("Building notification-email", "PENDING");
                        sh "./gradlew test"
                    }
                }
            }
            stage("notification-sms") {
                when {
                    changeset "**/notification-sms/*.*"
                    beforeAgent true
                }
                steps {
                    dir("notification-sms") {
                        setBuildStatus("Testing notification-sms", "PENDING");
                        sh "./gradlew test"
                    }
                }
            }
            stage("notification") {
                when {
                    changeset "**/notification/*.*"
                    beforeAgent true
                }
                steps {
                    dir("notification") {
                        setBuildStatus("Testing notification", "PENDING");
                        sh "./gradlew test"
                    }
                }
            }
            stage("clients") {
                when {
                    changeset "**/clients/*.*"
                    beforeAgent true
                }
                steps {
                    dir("clients") {
                        setBuildStatus("Testing clients", "PENDING");
                        sh "./gradlew test"
                    }
                }
            }
            stage("eureka-server") {
                when {
                    changeset "**/eureka-server/*.*"
                    beforeAgent true
                }
                steps {
                    dir("eureka-server") {
                        setBuildStatus("Testing eureka-server", "PENDING");
                        sh "./gradlew test"
                    }
                }
            }
        }

        stage("pushing to Dockerhub") {
            when {
                branch "master"
            }
            stage("notification-email") {
                when {
                    changeset "**/notification-email/*.*"
                    beforeAgent true
                }
                steps {
                    dir("notification-email") {
                        setBuildStatus("Pushing notification-email to Dockerhub", "PENDING");
                        sh "./gradlew jib"
                    }
                }
            }
            stage("notification-sms") {
                when {
                    changeset "**/notification-sms/*.*"
                    beforeAgent true
                }
                steps {
                    dir("notification-sms") {
                        setBuildStatus("Pushing notification-sms to Dockerhub", "PENDING");
                        sh "./gradlew jib"
                    }
                }
            }
            stage("notification") {
                when {
                    changeset "**/notification/*.*"
                    beforeAgent true
                }
                steps {
                    dir("notification") {
                        setBuildStatus("Pushing notification to Dockerhub", "PENDING");
                        sh "./gradlew jib"
                    }
                }
            }
            stage("eureka-server") {
                when {
                    changeset "**/eureka-server/*.*"
                    beforeAgent true
                }
                steps {
                    dir("eureka-server") {
                        setBuildStatus("Pushing eureka-server to Dockerhub", "PENDING");
                        sh "./gradlew jib"
                    }
                }
            }
        }

        stage("docker build") {
            stage("notification-email") {
                when {
                    changeset "**/notification-email/*.*"
                    beforeAgent true
                }
                steps {
                    dir("notification-email") {
                        setBuildStatus("Building notification-email docker image", "PENDING");
                        sh "./gradlew jibDockerBuild"
                    }
                }
            }
            stage("notification-sms") {
                when {
                    changeset "**/notification-sms/*.*"
                    beforeAgent true
                }
                steps {
                    dir("notification-sms") {
                        setBuildStatus("Building notification-sms docker image", "PENDING");
                        sh "./gradlew jibDockerBuild"
                    }
                }
            }
            stage("notification") {
                when {
                    changeset "**/notification/*.*"
                    beforeAgent true
                }
                steps {
                    dir("notification") {
                        setBuildStatus("Building notification docker image", "PENDING");
                        sh "./gradlew jibDockerBuild"
                    }
                }
            }
            stage("eureka-server") {
                when {
                    changeset "**/eureka-server/*.*"
                    beforeAgent true
                }
                steps {
                    dir("eureka-server") {
                        setBuildStatus("Building eureka-server docker image", "PENDING");
                        sh "./gradlew jibDockerBuild"
                    }
                }
            }
        }
    }

    post {
        success {
            setBuildStatus("Pipeline succeeded", "SUCCESS");
        }
        failure {
            setBuildStatus("Pipeline failed", "FAILURE");
        }
    }
}