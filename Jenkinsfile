pipeline {
    agent any

    environment {
        GITHUB_CREDENTIALS_ID = 'git-creds'
        SNYK_API_TOKEN = credentials('test-snyk-api')
    }
    tools {
        nodejs 'NodeJS'  // Use the name of the NodeJS installation defined in Jenkins
        maven 'maven'
        jdk 'java'
    }
    stages {		
        stage('Checkout') {
            steps {
                // Check out the specific branch
                checkout([
                    $class: 'GitSCM',
                    branches: [[name: env.BRANCH_NAME]],
                    doGenerateSubmoduleConfigurations: false,
                    extensions: [],
                    submoduleCfg: [],
                    userRemoteConfigs: [[url: 'https://github.com/Mounikareddy16/maven-web-application-1']]
                ])
            }
        }       
        stage('new file added') {
            steps {
                sh '#sh test.sh'
            }
        }        
        stage('Run SAST Test') {
            steps {
                sh '''
                    #snyk auth ${SNYK_API_TOKEN}
                    snyk auth 'f557c5e3-ea14-40fe-ae60-71e7367f91fa'
                    #snyk code test 
                '''
               }
            }
        stage('Run SCA Scan') {
            steps {
                sh 'snyk test --json>report.json

             }
             post {
                always {
                     sh 'snyk monitor --org=mouni.prani16 --project-name=Mounikareddy16/maven-web-application-1'
               }
          }

         } 

    
    }
  
}


