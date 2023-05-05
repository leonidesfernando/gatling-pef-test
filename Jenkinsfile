pipeline {
    agent any
    //triggers { cron("H/5 * * * *") }
    options {
      buildDiscarder logRotator(artifactDaysToKeepStr: '', artifactNumToKeepStr: '10', daysToKeepStr: '', numToKeepStr: '10')
    }
    parameters {
        choice(name: "N_USERS", choices: ["50", "100", "200", "400", "500"], description: "Number of users for back-end jobs")
        string(name: "TIME_DURATION", defaultValue: "20", trim: true, description: "Time duration")
        choice(name: "TIME_UNIT", choices: ["SECONDS", "MINUTES", "HOURS"], description: "Time unit for time duration")
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
                    sh 'mvn clean gatling:test -Dusers = $params.N_USERS -DTIME_DURATION = $params.TIME_DURATION -DTIME_UNIT = $params.TIME_UNIT'
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

