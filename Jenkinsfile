pipeline {
    agent any
    //triggers { cron("H/5 * * * *") }
    options {
      buildDiscarder logRotator(artifactDaysToKeepStr: '', artifactNumToKeepStr: '10', daysToKeepStr: '', numToKeepStr: '10')
    }
    parameters {
        choice(name: "N_USERS", choices: ["5", "100", "200", "400", "500"], description: "Number of users for back-end jobs")
    }
    stages {
        stage('Configuring') {
            steps{
                bat(/mkdir ${env.WORKSPACE}\${BUILD_NUMBER}/)
            }
        }
        stage('Cloning Git repo') {
            steps {
                 dir("${env.WORKSPACE}/${BUILD_NUMBER}"){
                    git url:'https://github.com/leonidesfernando/gatling-pef-test.git', branch: 'master'
                 }
            }
        }
        stage('Running Gatling') {
            steps {
                dir("${env.WORKSPACE}/${BUILD_NUMBER}/"){
                    sh 'mvn clean gatling:test -Dusers=$params.N_USERS'
                }
            }
            post {
                always {
                    gatlingArchive()
                }
            }
        }
    }
}

