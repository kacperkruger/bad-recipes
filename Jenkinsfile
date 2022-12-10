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
        stage("pre build") {
            steps {
                setBuildStatus("Build succeeded", "SUCCESS");
            }
        }

        stage("build") {
            steps {
                sh "./gradlew clean build"
            }
        }

        stage("test") {
            steps {
                sh "./gradlew test"
            }
        }
    }
    post {
        success {
            setBuildStatus("Build succeeded", "SUCCESS");
        }
        failure {
            setBuildStatus("Build failed", "FAILURE");
        }
    }
}