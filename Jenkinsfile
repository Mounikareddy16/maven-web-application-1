pipeline {
    agent any

    environment {
        GITHUB_CREDENTIALS_ID = 'git-creds'
        SNYK_API_TOKEN = credentials('test-snyk-api')
    }
    tools {
        nodejs 'NodeJS'  // Use the name of the NodeJS installation defined in Jenkins
    }
    parameters {
        string(name: 'BRANCH_NAME', defaultValue: 'master', description: 'Branch to build')
        choice(name: 'ENVIRONMENT', choices: ['master', 'development', 'test'], description: 'Branch to build')
        booleanParam(name: 'RUN_SAST_TEST', defaultValue: true, description: 'Run Snyk Test')
    }
    stages {	
        stage('Clone Repository') {
            steps {
                git credentialsId: "${GITHUB_CREDENTIALS_ID}", url: 'https://github.com/Mounikareddy16/maven-web-application-1', branch: "${params.BRANCH_NAME}"
            }
        }

        stage('Run SAST Test') {
            when {
                expression { params.RUN_SAST_TEST }
            }
            steps {
                sh '''
                    npm install -g snyk
                    snyk auth ${SNYK_API_TOKEN}
                    snyk code test 
                '''
               }
            }
    
        }
    }

