agent any

 parameters {
        choice choices: ['5555', '5557'], description: 'Select the device', name: 'DEVICE_PORT'
      }

      stages{
      
      
         stage ('Building Application'){
         
       	   steps {
                script {
                    // Define the source and destination paths
                    def sourcePath = 'C:\Users\arvin\ApppiumApp\Android.SauceLabs.Mobile.Sample.app.2.7.1.apk'
                    def destinationPath = "${env.WORKSPACE}\\src\\test\\resources\\app\\"

                    // Copy the APK file using xcopy
                    bat "xcopy /Y /I \"${sourcePath}\" \"${destinationPath}\""
       	  
       	   }
       	}

          stage('Build JAR'){
              steps{
                    bat "mvn clean package -DskipTests"
              }

          }
           stage('Build Image'){
                steps{
                     bat "docker build -t=shaarv70/appium:latest ."
              }

           }
            stage('Push Image'){
                environment{
                  DOCKER_HUB =credentials('dockerhub-creds')
                }
                steps{
                     bat 'docker login -u %DOCKER_HUB_USR% -p %DOCKER_HUB_PSW%'
                     bat "docker tag shaarv70/appium:latest shaarv70/appium:${env.BUILD_NUMBER}"
                     bat "docker push shaarv70/appium:${env.BUILD_NUMBER}"
               }
           }

            
             stage('Run Tests'){
                steps{

                     sh "docker-compose up --pull=always --scale ${params.DEVICE_PORT}=1"
                     script{
                         if(fileExists('output/Regression/testng-failed.xml') ||fileExists('output/Release/testng-failed.xml'))
                         error('failed tests found')

                     }
              }

           }
      
      }
    
    post{
         always{
         bat "docker-compose down"
         bat "docker logout"
         bat "docker image rm shaarv70/appium:latest"
      	 bat"docker image rm shaarv70/appium:${env.BUILD_NUMBER}"
      	 archiveArtifacts artifacts: 'extent-test-output/report.html', followSymlinks:false
         }
      }

     