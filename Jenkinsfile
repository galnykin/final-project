pipeline {
    agent any

    tools {
        maven 'MavenDefault'
        allure 'AllureDefault'
    }
    stages {
        stage('Build & Test') {
            steps {
                script {
                    if (isUnix()) {
                        sh 'mvn clean test'
                    } else {
                        bat 'mvn clean test'
                    }
                }
            }
        }
    }

    post {
        always {
            echo 'Generating Allure report...'
            allure([includeProperties: false,
                    jdk              : '',
                    results          : [[path: 'target/allure-results']]])
            archiveArtifacts artifacts: 'allure-report/**', fingerprint: true
        }

        success {
            echo 'Build completed successfully [OK]'
        }

        failure {
            echo 'Build failed [ERROR]'
        }

        unstable {
            echo 'Build is unstable [WARNING] — some tests may have failed'
        }
    }
}
