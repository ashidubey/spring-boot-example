pipeline {
    agent{
            label "slave-1"
        }
        tools {
            maven 'maven'
            // jdk 'jdk 11'
        }
    stages {

        stage('Testing') {
            steps {
                echo 'Testing the application...'
                sh "mvn clean"
            }
        }
    }
    post {
        //always{
          //          mail to: 'ashidubey9876@gmail.com',
        	//		subject: "Pipeline: ${currentBuild.fullDisplayName} is ${currentBuild.currentResult}",
        	//		body: "${currentBuild.currentResult}: Job ${env.JOB_NAME} build ${env.BUILD_NUMBER}\n More info at: ${env.BUILD_URL}"
             //   }
        success{
        echo "Testing stage successful"
        }
        failure{
        echo "Testing stage failed"
        }
    }
}