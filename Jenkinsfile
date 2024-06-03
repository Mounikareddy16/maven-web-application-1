pipeline {
    agent any

    environment {
        GITHUB_CREDENTIALS_ID = 'git-creds'
        SNYK_API_TOKEN = credentials('test-snyk-api')  // Ensure this ID matches the one in Jenkins
    }

    tools {
        nodejs 'NodeJS'  // Use the name of the NodeJS installation defined in Jenkins
        maven 'Maven'  // Use the name of the Maven installation defined in Jenkins
    }

    parameters {
        string(name: 'BRANCH_NAME', defaultValue: 'main', description: 'Branch to build')
    }

    triggers {
        // Trigger the pipeline when a PR is created or updated
        githubPullRequest {
            admins(['github-username'])
            useGitHubHooks()
            triggerPhrase('Jenkins, test this please')
            onlyTriggerPhrase()
        }
    }

    stages {
        stage('Clone Repository') {
            steps {
                git(
                    url: 'https://github.com/Mounikareddy16/maven-web-application-1.git',
                    credentialsId: "${GITHUB_CREDENTIALS_ID}",
                    branch: "${params.BRANCH_NAME}",
                    extensions: [
                        [$class: 'CloneOption', noTags: false, shallow: false, depth: 0, timeout: 10],
                        [$class: 'CleanBeforeCheckout'],
                        [$class: 'PruneStaleBranch']
                    ]
                )
            }
        }

        stage('Install Dependencies') {
            steps {
                sh 'npm install'
                sh 'mvn clean install'
            }
        }

        stage('Run Snyk Test') {
            steps {
                withEnv(["SNYK_API_TOKEN=${SNYK_API_TOKEN}"]) {
                    sh '''
                        npm install -g snyk
                        snyk auth ${SNYK_API_TOKEN}
                        snyk test --all-projects --json > snyk_report.json
                    '''
                }
            }
        }

    }

}
