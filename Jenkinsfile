pipeline {
    agent any

    environment {
        COMPOSE_HTTP_TIMEOUT = 300
        DOCKER_CLI_TIMEOUT = 300  // Optional but recommended
    }

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

 		stage('Pre-pull Docker Image') {
            steps {
               		bat "docker pull shaarv70/appium:${env.BUILD_NUMBER}"
            }
        }
        stage('Run Tests') {
            steps {
                script {
                    def service = params.SERVICE
                    bat """
                        set DEVICE_PORT=${DEVICE_PORT}
                        docker-compose up --scale ${SERVICE}=1
                    """
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
           
           			archiveArtifacts artifacts: 'extent-test-output/report.html', followSymlinks: false
                    bat "docker-compose down --rmi all --volumes --remove-orphans"
                    bat "docker system prune -f"
                    bat "docker logout"
                    bat "docker image rm shaarv70/appium:latest"
                    bat "docker image rm shaarv70/appium:${env.BUILD_NUMBER}"
                
            	
        		}
    	}
}
