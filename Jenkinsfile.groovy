pipeline{
    // triggers{
    //     pollSCM('H 0 * * 2')
    // }
    agent{
        label "slave-3"
    }
    tools { 
        maven 'maven' 
        jdk 'jdk 11' 
    }
    stages{
        stage("building"){
            steps{
                sh "mvn clean"
            }
        }

    }
    post{
        always{
            emailext body:  '''$PROJECT_NAME - Build # $BUILD_NUMBER - $BUILD_STATUS: Check console output at $BUILD_URL to view the results.''',
            subject: '$PROJECT_NAME - Build # $BUILD_NUMBER - $BUILD_STATUS!',
            to: 'rahul.soni@knoldus.com'
        }
        success{
            echo "pipeline executed successfully"
        }
        failure{
            echo "pipeline execution failed"
        }
    }
}