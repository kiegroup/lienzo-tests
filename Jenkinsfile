@Library('jenkins-pipeline-shared-libraries')_

pipeline {
    agent {
        label 'submarine-static || kie-rhel7'
    }
    tools {
        maven 'kie-maven-3.5.4'
        jdk 'kie-jdk1.8'
    }
    options {
        buildDiscarder logRotator(artifactDaysToKeepStr: '', artifactNumToKeepStr: '', daysToKeepStr: '', numToKeepStr: '10')
        // timeout(time: 90, unit: 'MINUTES')
    }
    stages {
        stage('CleanWorkspace') {
          steps {
            cleanWs()
          }
        }
        stage('Initialize') {
            steps {
                sh 'printenv'
            }
        }
        stage('Build lienzo-core') {
            steps {
                dir("lienzo-core") {
                    script {
                        githubscm.checkoutIfExists('lienzo-core', "$CHANGE_AUTHOR", "$CHANGE_BRANCH", 'kiegroup', "$CHANGE_TARGET")
                        maven.runMavenWithSubmarineSettings('clean install', true)
                    }
                }
            }
        }
        stage('Build lienzo-tests') {
            steps {
                dir("lienzo-tests") {
                    script {
                        maven.runMavenWithSubmarineSettings('clean install', true)
                    }
                }
            }
        }
    }
    post {
        unstable {
            script {
                mailer.sendEmailFailure()
            }
        }
        failure {
            script {
                mailer.sendEmailFailure()
            }
        }
        always {
            // Currently there are no tests in submarine-examples
            //junit '**/target/surefire-reports/**/*.xml'
            cleanWs()
        }
    }
}