pipeline{
    // triggers{
    //     pollSCM('H 0 * * 2')
    // }
    agent {label 'slave-3'}
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
        stage("deploying"){
           steps{
               sh 'scp -i "/home/knoldus/Downloads/aws-auth.pem" /var/lib/jenkins/workspace/capstone-demo_development/target/hello-0.0.1-SNAPSHOT.jar ubuntu@15.206.147.135:/home/ubuntu'
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
