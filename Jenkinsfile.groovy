pipeline{
    // triggers{
    //     pollSCM('H 0 * * 2')
    // }
    agent {label 'slave-2'}
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
        stage("testing"){
            steps{
                sh "mvn test"
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
            echo "Pipeline executed successfully"
        }
        failure{
            echo "Pipeline execution failed"
        }
    }
}