pipeline {
    agent any

    tools {
        maven 'MavenDefault'
        allure 'AllureDefault'
    }

    stages {
        stage('Build & Test') {
            steps {
                bat 'mvn clean test'
            }
        }
    }

    post {
        always {
            echo 'Generating Allure report...'
            allure([
                includeProperties: false,
                jdk: '',
                results: [[path: 'target\\allure-results']]
            ])
            archiveArtifacts artifacts: 'allure-report/**', fingerprint: true
        }

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
