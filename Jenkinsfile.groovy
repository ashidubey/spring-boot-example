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
        success {
            echo "Packaging successful"
        }
        failure {
            echo "Packaging unsuccessful"
        }
    }
}