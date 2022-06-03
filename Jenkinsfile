pipeline {
    agent any

    tools {
        jdk 'openjdk-17'
        // nodejs 'nodejs-17.8'
    }

    stages {
        stage('Build'){
            parallel {
                stage('Build backend') {
                    environment {
                        mvn = tool 'maven-3.8.5'
                    }

                    steps {
                        updateGitlabCommitStatus name: 'pipeline', state: 'running'
                      //  sh "${mvn}/bin/mvn package"
                      sh "ls -l"
                    }
                }
            }
        }

        stage('Images') {
            parallel {
                stage('Build backend images') {
                steps{sh "ls -l"}
                }

                stage('Pulling to server') {
                    steps{
                    sh "ls -l"
                    }
                }
            }
        }

        // stage('Deploy to Kubernetes') {
        //     steps {
        //         withCredentials([file(credentialsId: 'kubeconfig', variable: 'CONFIG')]) {
        //             sh "kubectl set image deployment/dudel-backend-deployment dudel-backend=registry.borodun.works/colleges/dudel/backend:${env.BUILD_NUMBER} --kubeconfig=\"$CONFIG\" -n gitlab"
        //             sh "kubectl set image deployment/dudel-frontend-deployment dudel-frontend=registry.borodun.works/colleges/dudel/frontend:${env.BUILD_NUMBER} --kubeconfig=\"$CONFIG\" -n gitlab"
        //         }
        //     }
        // }

        // stage('SonarQube Analysis') {
        //     environment {
        //         sonar = tool 'sonarqube-4.7.0'
        //     }

        //     steps {
        //         withSonarQubeEnv(installationName: 'SonarQube', credentialsId: 'sonar-token') {
        //             sh "${sonar}/bin/sonar-scanner"
        //         }
        //     }
        // }
    }

    post {
        failure {
            updateGitlabCommitStatus name: 'pipeline', state: 'failed'
        }
        success {
            updateGitlabCommitStatus name: 'pipeline', state: 'success'
        }
        aborted {
            updateGitlabCommitStatus name: 'pipeline', state: 'canceled'
        }
    }
}
