pipeline{


agent any

 parameters {
        
        choice choices: ['5555', '5557'], description: 'Select the device', name: 'DEVICE_PORT'
       
           }

      stages{
      
      	stage ('Building Application'){
         
       	   steps {
          
               script {
       
                    // Define the source and destination paths
                    def sourcePath = 'C:\\Users\\arvin\\ApppiumApp\\Android.SauceLabs.Mobile.Sample.app.2.7.1.apk'
                    def destinationPath = "${env.WORKSPACE}\\src\\test\\resources\\app\\"

                    // Copy the APK file using xcopy
                    bat "xcopy /Y /I \"${sourcePath}\" \"${destinationPath}\""
       	  
       	        }
       	
       	    }

	  }
         
        stage('Build JAR'){
        
          steps  {
                    bat "mvn clean package -DskipTests"
              
                 }
           }
           
           
        stage('Build Image'){
           
          steps  {
                     bat "docker build -t=shaarv70/appium:latest ."
              
                 }
		  }
          
          
        stage('Push Image'){
        
          environment
          			 {
          				DOCKER_HUB =credentials('dockerhub-creds')
            		 }
            
          steps {
                     bat 'docker login -u %DOCKER_HUB_USR% -p %DOCKER_HUB_PSW%'
                     bat "docker tag shaarv70/appium:latest shaarv70/appium:${env.BUILD_NUMBER}"
                     bat "docker push shaarv70/appium:${env.BUILD_NUMBER}"
               }
           }

            
        stage('Run Tests'){
               
           steps{
					 bat "DEVICE_PORT=${params.DEVICE_PORT} docker-compose up --pull=always"
                     
                     script{
                        
                     def failedTestFound = fileExists('output/Regression/testng-failed.xml') || 
                                           fileExists('output/Release/testng-failed.xml')
                   	 if (failedTestFound) {
                     error('Failed tests found')

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

  }   