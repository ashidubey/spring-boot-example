pipeline {
    agent{
            label "master"
        }
        tools {
            maven 'maven'
            // jdk 'jdk8'
        }
    stages {

        stage('Testing') {
            steps {
                echo 'Testing the application...'
                sh 'mvn clean test'
            }
        }
    }
    post {
        success{
        echo "Testing stage successful"
        }
        failure{
        echo "Testing stage failed"
        }
    }
}