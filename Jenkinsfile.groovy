pipeline{
    // triggers{
    //     pollSCM('H 0 * * 2')
    // }
    agent{
        label "master"
    }
    tools { 
        maven 'maven' 
        jdk 'jdk11' 
    }
    stages{
        stage("building"){
            steps{
                sh "mvn clean package"
            }
        }

    }
    post{
        // always{
        //     //echo "========always========"
        // }
        success{
            echo "========pipeline executed successfully ========"
        }
        failure{
            echo "========pipeline execution failed========"
        }
    }
}