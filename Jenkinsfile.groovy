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
            mail to: 'ashidubey9876@gmail.com',
			subject: "Pipeline: ${currentBuild.fullDisplayName} is ${currentBuild.currentResult}",
			body: "${currentBuild.currentResult}: Job ${env.JOB_NAME} build ${env.BUILD_NUMBER}\n More info at: ${env.BUILD_URL}"
        }
        success{
            echo "========pipeline executed successfully ========"
        }
        failure{
            echo "========pipeline execution failed========"
        }
    }
}