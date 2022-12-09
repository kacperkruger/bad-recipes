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
                sh "./gradlew clean build"
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