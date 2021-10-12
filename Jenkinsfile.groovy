pipeline{
    // triggers{
    //     pollSCM('H 0 * * 2')
    // }
    agent {label 'slave-1'}
    tools { 
        maven 'maven' 
        jdk 'jdk 11' 
    }
    options {
    buildDiscarder(logRotator(numToKeepStr: '5'))
  }
    stages{
        stage("cleaning"){
            steps{
                sh "mvn clean"
            }
        }
        stage("compile"){
            steps{
                sh "mvn compile"
            }
        }
        stage("packaging"){
            steps{
                sh 'mvn package'
            }
        }

    }
    post{
        always{
            emailext body:  '''$PROJECT_NAME - Build # $BUILD_NUMBER - $BUILD_STATUS: Check console output and logs at -> $BUILD_URL ''',
            subject: '$PROJECT_NAME - Build # $BUILD_NUMBER - $BUILD_STATUS!',
            to: 'ashi.dubey@knoldus.com'
        }
        success{
            echo "pipeline executed successfully"
        }
        failure{
            echo "pipeline execution failed"
        }
    }
}