void setBuildStatus(String message, String context, String state) {
    withCredentials([string(credentialsId: 'github-commit-status-token', variable: 'TOKEN')]) {
        sh """
            set -x
            curl \"https://api.github.com/repos/org/repo/statuses/$GIT_COMMIT?access_token=$TOKEN\" \
                -H \"Content-Type: application/json\" \
                -X POST \
                -d \"{\\\"description\\\": \\\"$message\\\", \\\"state\\\": \\\"$state\\\", \\\"context\\\": \\\"$context\\\", \\\"target_url\\\": \\\"$BUILD_URL\\\"}\"
        """
    }
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
                setBuildStatus("Compiling", "compile", "pending");
                script{
                    try {
                        sh "./gradlew clean build"
                        sh(setBuildStatus("Build complete", "compile", "success"));
                    } catch (err) {
                        sh(setBuildStatus("Failed", "pl-compile", "failure"));
                        throw err
                    }
                }
            }
        }

        stage("test") {
            steps {
                withGradle() {
                    sh "./gradlew test"
                }
            }
        }
    }
}