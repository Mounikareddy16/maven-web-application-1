pipeline {
    agent any

    environment {
        GITHUB_CREDENTIALS_ID = 'git-creds'
        SNYK_API_TOKEN = credentials('test-snyk-api')
    }

    tools {
        nodejs 'NodeJS'  // Use the name of the NodeJS installation defined in Jenkins
    }

    stages {
        stage('Print Node and npm Version') {
            steps {
                sh 'node -v'
                sh 'npm -v'
                sh 'echo $PATH'
            }
        }

        stage('Clone Repository') {
            steps {
                git credentialsId: "${GITHUB_CREDENTIALS_ID}", url: 'https://github.com/Mounikareddy16/maven-web-application-1'
            }
        }

        stage('Run Snyk Test') {
            steps {
                sh '''
                    npm install -g snyk
                    snyk auth ${SNYK_API_TOKEN}
                    snyk code test 
                '''
            }
        }
        stage('Report') {
            steps {
                echo 'hello world'
            }
        }    
    }


}
