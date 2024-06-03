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
        stage('Clone Repository') {
            steps {
                git credentialsId: "${GITHUB_CREDENTIALS_ID}", url: 'https://github.com/Mounikareddy16/maven-web-application-1'
            }
        }

        stage('Run SAST Test') {
            steps {
                snykSecurity severity: 'high', snykInstallation: 'snyk@latest', snykTokenId: 'SNYK_API_TOKEN'
               }
            }
    
        }
    }

