pipeline {
    agent any

    environment {
        // Define environment variables if needed
        MAVEN_HOME = tool 'Maven'
    }

    stages {
        stage('Checkout') {
            steps {
                    // Check out the source code from the GitHub repository
                    checkout([$class: 'GitSCM', branches: [[name: '*/master']], userRemoteConfigs: [[url: 'https://github.com/KT-GitHub-code/HearthstonePackTracker-Backend.git']]])
                }
        }

        stage('Build') {
            steps {
                // Build the project using Maven
                sh "${MAVEN_HOME}/bin/mvn clean install"
            }
        }

        stage('Test') {
            steps {
                // Run tests using Maven
                sh "${MAVEN_HOME}/bin/mvn test"
            }
        }

        stage('Deploy') {
            steps {
                // Add deployment steps if applicable
            }
        }
    }

    post {
        success {
            // Actions to perform on success
            echo 'Build successful!'
        }
        failure {
            // Actions to perform on failure
            echo 'Build failed!'
        }
    }
}
