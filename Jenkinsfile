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
                sh 'snykSecurity severity: 'high', snykInstallation: 'snyk@latest', snykTokenId: 'b367fcaf-8e15-4ef5-ae9a-dcc94f43df5a''
               }
            }
    
        }
    }

