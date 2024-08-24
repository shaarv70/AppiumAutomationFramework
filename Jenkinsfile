pipeline {
    agent any

    parameters {
        choice(name: 'SERVICE', choices: ['Regression', 'Release'], description: 'Select the service to run')
        choice(name: 'DEVICE_PORT', choices: ['5555', '5557'], description: 'Select the device port')
    }

    stages {
        stage('Building Application') {
            steps {
                script {
                    // Define the source and destination paths
                    def sourcePath = 'C:\\Users\\Asus\\ApppiumApp\\Android.SauceLabs.Mobile.Sample.app.2.7.1.apk'
                    def destinationPath = "${env.WORKSPACE}\\src\\test\\resources\\app\\"

                    // Copy the APK file using xcopy
                    bat "xcopy /Y /I \"${sourcePath}\" \"${destinationPath}\""
                }
            }
        }

        stage('Build JAR') {
            steps {
                bat "mvn clean package -DskipTests"
            }
        }

        stage('Build Image') {
            steps {
                bat "docker build -t=shaarv70/appium:latest ."
            }
        }

        stage('Push Image') {
            environment {
                DOCKER_HUB = credentials('dockerhub-creds')
            }
            steps {
                bat 'docker login -u %DOCKER_HUB_USR% -p %DOCKER_HUB_PSW%'
                bat "docker tag shaarv70/appium:latest shaarv70/appium:${env.BUILD_NUMBER}"
                bat "docker push shaarv70/appium:${env.BUILD_NUMBER}"
            }
        }

        stage('Run Tests') {
            steps {
                script {
                    def service = params.SERVICE
                    bat "docker-compose up --pull=always --scale ${service}=1 --force-recreate"
                }

                script {
                    def failedTestFound = fileExists("output/${params.SERVICE}/testng-failed.xml")
                    if (failedTestFound) {
                        error('Failed tests found')
                    }
                }
            }
        }
    }

    post {
        always {
            bat "docker-compose down"
            bat "docker logout"
            bat "docker image rm shaarv70/appium:latest"
            bat "docker image rm shaarv70/appium:${env.BUILD_NUMBER}"
            archiveArtifacts artifacts: 'extent-test-output/report.html', followSymlinks: false
        }
    }
}
