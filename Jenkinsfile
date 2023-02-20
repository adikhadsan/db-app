pipeline{
    agent any
     environment {
		DOCKERHUB_CREDENTIALS = credentials('DockerHub')
	        GIT_COMMIT = sh(returnStdout: true, script: 'git rev-parse HEAD')
	        PORT_mysql= 5000
	        PORT_app= 9192
	        fname= "demo"
	        docker= sh (script: 'docker --version',returnStdout: true)
	}
    stages {
	  /*  stage('name'){
		    steps {
			    
			    sh'echo $JOB_NAME'
	                  //  sh'job=${JOB_NAME}'
	                 //   sh'echo $job'
		    }
	    }*/
       stage('docker check on remote ') {
	       when { environment name: 'docker', value: '' }
	       steps {
		       sh 'ansible-playbook docker-playbook.yml'
	       }
       }  
	    
	   
	 stage('docker login on remote machine'){
		 steps{
			 sh 'ansible-playbook login.yml --extra-vars "uname=8485012281 passwd=Aditya@123"
		 }
	 }   
    
	stage ('mysql run on remote') {
	    steps {
// 		    sh 'docker run -d -p $PORT_mysql:3306 --net static --ip 10.11.0.12 --name mysql-$GIT_COMMIT -e MYSQL_ROOT_PASSWORD=root mysql'  
// 		    sh 'sleep 30'
		    sh 'ansible-playbook container-playbook.yml --extra-vars “image_name=mysql port=5000 passwd=root”'
	    }
	}

        stage('maven location') {
             steps {
            
                sh'''
                  pwd
                  cd /var/lib/jenkins/workspace/${JOB_NAME}/
                  ls
                  mvn clean
                  mvn install
              
              
                  '''
             }
         }
	
// 	stage('	Copy jar file'){
// 	     steps{
// 		 sh'pwd'    
// 		 sh'cp /var/lib/jenkins/workspace/$JOB_NAME/target/*.war .'
// 		 sh'ls'    
// 		// sh 'docker build -t spring-img --build-arg dokcerjob=$JOB_NAME .'
// 	     }
// 	 }
	    
	  
	
	    
	    stage('git commit id'){
		    steps{
// 			    sh'git_id=$(git rev-parse --short "$GITHUB_SHA")'
			    sh'echo $GIT_COMMIT'
		    }
	    }
	
	 stage('docker build'){
	     steps{
		     sh'docker build --build-arg file-name="${fname}" -t 8485012281/db-application:$GIT_COMMIT .'
		// sh 'docker build -t spring-img-jar --build-arg dokcerjob=$JOB_NAME .'
	     }
	 } 
	 stage('image check'){
	     steps{
		 sh'sleep 30'
		 sh'docker images'
	     }
	 }
	 stage('docker login'){
	     steps{

		sh 'echo $DOCKERHUB_CREDENTIALS_USR'
		sh 'echo $DOCKERHUB_CREDENTIALS_PSW'
			sh 'echo $DOCKERHUB_CREDENTIALS_PSW | docker login -u $DOCKERHUB_CREDENTIALS_USR -p $DOCKERHUB_CREDENTIALS_PSW'
	     }
	 } 
	 stage('docker push'){
	     steps{
		 sh 'docker push 8485012281/db-application:$GIT_COMMIT'
	     }
	 }
	 
	    
	 stage('docker run on remote'){
	     steps{
		 ansible-playbook application.yml --extra-vars "image_name=8485012281/db-application:$GIT_COMMIT port=9192" 
// 		 sh 'docker run -d -p $PORT_app:8080 --net static --ip 10.11.0.13 --name db-application-$GIT_COMMIT 8485012281/db-application:$GIT_COMMIT'
		 sh 'sleep 30'
		 sh 'docker ps'
	     }
	 }
    }
}
