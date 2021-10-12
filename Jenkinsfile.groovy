pipeline {
    agent {
        label 'slave-2'
    }
    stages {
        stage("Production") {
            steps {
                //echo "Production branch"
                sh "mvn clean package"
            }
        }
    }
    post {
        always{
            mail to: 'ashidubey9876@gmail.com',
			subject: "Pipeline: ${currentBuild.fullDisplayName} is ${currentBuild.currentResult}",
			body: "${currentBuild.currentResult}: Job ${env.JOB_NAME} build ${env.BUILD_NUMBER}\n More info at: ${env.BUILD_URL}"
        }
        success {
            echo "Packaging successful"
        }
        failure {
            echo "Packaging unsuccessful"
        }
    }
}