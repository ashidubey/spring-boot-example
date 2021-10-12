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
        success{
            echo "========pipeline executed successfully ========"
        }
        failure{
            echo "========pipeline execution failed========"
        }
    }
}