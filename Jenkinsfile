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

    parameters {
        booleanParam(name: 'RUN_CHECKOUT', defaultValue: true, description: 'Run the Checkout stage')
        booleanParam(name: 'RUN_NEW_FILE_ADDED', defaultValue: true, description: 'Run the new file added stage')
        booleanParam(name: 'RUN_SAST_TEST', defaultValue: true, description: 'Run the SAST Test stage')
        booleanParam(name: 'RUN_SCA_SCAN', defaultValue: true, description: 'Run the SCA Scan stage')
        booleanParam(name: 'RUN_IAC_SCAN', defaultValue: true, description: 'Run the IAC Scan stage')
    }

    stages {
        stage('Checkout') {
            when {
                expression { return params.RUN_CHECKOUT }
            }
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
            when {
                expression { return params.RUN_NEW_FILE_ADDED }
            }
            steps {
                sh 'sh test.sh'
            }
        }        
        stage('Run SAST Test') {
            when {
                expression { return params.RUN_SAST_TEST }
            }
            steps {
                sh '''
                    #snyk auth ${SNYK_API_TOKEN}
                    snyk auth 'f557c5e3-ea14-40fe-ae60-71e7367f91fa'
                    snyk code test 
                '''
            }
        }
        stage('Run SCA Scan') {
            when {
                expression { return params.RUN_SCA_SCAN }
            }
            steps {
                sh '#snyk test --json>report.json'
            }
            post {
                always {
                    sh 'snyk monitor --org=mouni.prani16 --project-name=Mounikareddy16/maven-web-application-1'
                }
            }
        } 
        stage('Run IAC Scan') {
            when {
                expression { return params.RUN_IAC_SCAN }
            }
            steps {
                sh 'snyk iac test > iac_report.json --report'
            }
            post {
                always {
                    sh 'snyk monitor --org=mouni.prani16 --project-name=Mounikareddy16/maven-web-application-1'
                    cleanWs notFailBuild: true, patterns: [[pattern: 'iac_report.json', type: 'EXCLUDE']]
                }
            }
        }
    }
}
