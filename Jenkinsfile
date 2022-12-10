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
        dockerhub = credentials("dockerhub")
    }

    stages {
        stage("build") {
            steps {
                setBuildStatus("Building", "PENDING");
                sh "./gradlew clean build"
            }
        }

        stage("test") {
            steps {
                setBuildStatus("Testing", "PENDING");
                sh "./gradlew test"
            }
        }

        stage("pushing to Dockerhub") {
            when {
                branch "master"
            }
            steps {
                setBuildStatus("Pushing docker images", "PENDING");
                sh "./gradlew jib -x :jib"
            }
        }

        stage("docker build") {
            steps {
                setBuildStatus("Building docker images", "PENDING");
                sh "./gradlew jibDockerBuild -x :jibDockerBuild"
            }
        }
    }

    post {
        success {
            steps {
                setBuildStatus("Pipeline succeeded", "SUCCESS");
            }
        }
        failure {
            steps {
                setBuildStatus("Pipeline failed", "FAILURE");
            }
        }
    }
}