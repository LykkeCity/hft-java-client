pipeline {
    agent any
        stages {
            withMaven(
                    maven: 'M3',
                    mavenLocalRepo: '.repository') {

                stage('Clean') {
                steps {
                    sh "mvn clean"
                }
            }
            stage('Validate') {
                steps {
                    sh "mvn validate"
                }
            }
            stage('Compile') {
                steps {
                    sh "mvn compile"
                }
            }
            stage('Test') {
                steps {
                    sh "mvn test"
                }
            }
            stage('Package') {
                steps {
                    sh "mvn package"
                }
            }
            stage('verify') {
                steps {
                    sh "mvn package"
                }
            }
            stage('install') {
                steps {
                    sh "mvn package"
                }
            }

            stage('Release') {
                steps {
                    sh "mvn -P sign-artifacts release:prepare release:perform"
                }
            }
        }
    }
}

