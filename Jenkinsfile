pipeline {
    agent any

    tools {
        maven 'MavenDefault'
        allure 'AllureDefault'
    }

    stages {
        stage('Build & Test') {
            steps {
                sh 'mvn clean test'
            }
        }

        stage('Publish Allure Report') {
            steps {
                allure([
                    includeProperties: false,
                    jdk: '',
                    results: [[path: 'target/allure-results']]
                ])
            }
        }

        stage('Archive Report') {
            steps {
                archiveArtifacts artifacts: 'target/allure-report/**', fingerprint: true
            }
        }
    }

    post {
        success {
            echo 'Build completed successfully ✅'
        }
        failure {
            echo 'Build failed ❌'
        }
        unstable {
            echo 'Build is unstable ⚠️ — some tests may have failed'
        }
    }
}
