pipeline {
    agent any

    environment {
        GITHUB_CREDENTIALS_ID = 'git-creds'
        SNYK_API_TOKEN = credentials('test-snyk-api')
    }



    parameters {
        string(name: 'BRANCH_NAME', defaultValue: 'master', description: 'Branch to build')
        choice(name: 'ENVIRONMENT', choices: ['development', 'test'], description: 'Deployment environment')
        booleanParam(name: 'RUN_SNYK_TEST', defaultValue: true, description: 'Run Snyk Test')
    }

 

        stage('Clone Repository') {
            steps {
                dir('maven-web-application-1') {  // Adjust the directory name as needed
                    git credentialsId: "${GITHUB_CREDENTIALS_ID}", url: 'https://github.com/Mounikareddy16/maven-web-application-1', branch: "${params.BRANCH_NAME}"
                }
            }
        }


        stage('Run Snyk Test') {
            when {
                expression { params.RUN_SNYK_TEST }
            }
            steps {
                dir('maven-web-application-1') {  // Ensure the correct directory
                    sh '''
                        npm install -g snyk
                        snyk auth ${SNYK_API_TOKEN}
                        snyk code test 
                    '''
                }
            }
        }
}


