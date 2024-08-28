pipeline {
    
    agent any

    environment {
        COMPOSE_HTTP_TIMEOUT = 300
        DOCKER_CLI_TIMEOUT = 300  // Optional but recommended
    	EMAIL_RECIPIENTS = 'sharmaarvindsharma935@gmail.com'
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
                        set DEVICE_PORT=${params.DEVICE_PORT}
                        docker-compose up --scale ${service}=1
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
            	  archiveArtifacts artifacts: "output/${params.SERVICE}/report.html", followSymlinks: false
            	  bat "docker-compose down --rmi all --volumes --remove-orphans"
            	  bat "docker system prune -f"
            	  bat "docker image rm shaarv70/appium:${env.BUILD_NUMBER}"
            	  bat "docker logout"
               }
        success {
            emailext (
                to: "${EMAIL_RECIPIENTS}",
                subject: "Job '${env.JOB_NAME} [${env.BUILD_NUMBER}]' Succeeded",
                body: """
                    <p>Good news, the pipeline succeeded!</p>
                    <p>Job: ${env.JOB_NAME}</p>
                    <p>Build Number: ${env.BUILD_NUMBER}</p>
                    <p>Service: ${params.SERVICE}</p>
                    <p>Check the details: ${env.BUILD_URL}</p>
                """,
                mimeType: 'text/html',
                attachmentsPattern: "output/${params.SERVICE}/report.html",
                replyTo: 'arvindsharma50480@gmail.com',
    			from: 'arvindsharma50480@gmail.com'
            )
        }
        failure {
            emailext (
                to: "${EMAIL_RECIPIENTS}",
                subject: "Job '${env.JOB_NAME} [${env.BUILD_NUMBER}]' Failed",
                body: """
                    <p>Unfortunately, the pipeline failed.</p>
                    <p>Job: ${env.JOB_NAME}</p>
                    <p>Build Number: ${env.BUILD_NUMBER}</p>
                    <p>Service: ${params.SERVICE}</p>
                    <p>Check the details: ${env.BUILD_URL}</p>
                """,
                mimeType: 'text/html',
                attachmentsPattern: "output/${params.SERVICE}/report.html",
                replyTo: 'arvindsharma50480@gmail.com',
    			from: 'arvindsharma50480@gmail.com'
            )
        }
       
    }
}
