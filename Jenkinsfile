#! groovy

slack_channel = 'weather_app_release'

pipeline {
    agent any

    environment {
        BUILD_TYPE = "$env.BUILD_TYPE_RELEASE"
        FIREBASE_APP_ID = "$env.FIREBASE_APP_ID_RELEASE"
        FIREBASE_CI_TOKEN = "$env.FIREBASE_CI_TOKEN"
        CHANNEL = "$env.CHANNEL"
        SLACK_URL = "$env.SLACK_URL"
        LC_ALL = "en_US.UTF-8"
        LANG = "en_US.UTF-8"
    }

    stages {

         stage('Setup') {
           steps {
             echo "Setup"
             // Install bundler in order to use fastlane
             sh "gem install bundler"
             // set the local path for bundles in vendor/bundle
             sh "bundle config set --local path 'vendor/bundle'"
             // install bundles if they're not installed
             sh "bundle check || bundle install --jobs=4 --retry=3"
           }
         }

         stage('Init') {
            // check git commit message contains "skip ci" if found don't run the pipeline
            steps {
                script {
                    lastCommitInfo = sh(script: "git log -1", returnStdout: true).trim()
                    commitContainsSkip = sh(script: "git log -1 | grep 'skip ci'", returnStatus: true)
                    slackMessage = "*${env.JOB_NAME}* *${env.BRANCH_NAME}* received a new commit. \nHere is commit info: ${lastCommitInfo}\n*Console Output*: <${BUILD_URL}/console | (Open)>"
                    slack_send(slackMessage)
                    if (commitContainsSkip == 0) {
                        skippingText = " Skipping Build for *${env.BRANCH_NAME}* branch."
                        currentBuild.result = 'ABORTED'
                        slack_send(skippingText,"warning")
                        error('BUILD SKIPPED')
                    }
                }
            }
        }

        stage('Run unit tests') {
            steps {
                echo "Testing"
                sh "bundle exec fastlane android tests"
            }
        }

        stage('Build and upload to firebase app distribution') {
              when {
                anyOf {
                    branch "development";
                    branch "staging";
                    branch "feature/*";
                }
              }

              // call fastlane lane for generate apk and uploading to firebase console
              steps {
                echo "Building"
                sh "bundle exec fastlane android build --env master"
              }
        }
    }

    post {
        always {
            // delete the workspace
            sh "chmod -R 777 ."
            deleteDir()
        }
        success {
             slack_send("Jenkins job for development completed successfully.","#0066ff")
        }
        aborted {
            slack_send("Jenkins job for development Skipped/Aborted.","warning")
        }
        failure {
            slack_send("staging Something went wrong.Build failed. Check here: Console Output*: <${BUILD_URL}/console | (Open)>","danger")
        }
    }
}

def slack_send(slackMessage, messageColor="good") {
    slackSend channel: slack_channel , color: messageColor, message: slackMessage
}