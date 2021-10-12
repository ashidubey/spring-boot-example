pipeline {
    agent {
        label 'slave-2'
    }
    stages {
        stage("Production") {
            steps {
                //echo "Production branch"
                sh "mvn clean"
            }
        }
    }
    post {
        always{
            emailext body:  '''$PROJECT_NAME - Build # $BUILD_NUMBER - $BUILD_STATUS: Check console output at $BUILD_URL to view the results.''',
            subject: '$PROJECT_NAME - Build # $BUILD_NUMBER - $BUILD_STATUS!',
            to: 'ashi.dubey@knoldus.com'
        }
        success {
            echo "Packaging successful"
        }
        failure {
            echo "Packaging unsuccessful"
        }
    }
}