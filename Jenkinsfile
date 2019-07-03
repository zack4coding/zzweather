node {
   def mvnHome
   stage('Preparation') {
      // Get zzweather backend code from a GitHub repository
      git 'https://github.com/zack4coding/zzweather.git'
      // Get the Maven tool.
      // ** NOTE: This 'M3' Maven tool must be configured
      // **       in the global configuration.           
      mvnHome = tool 'M3'
   }
   stage('Test') {
      // Run the maven build
      withEnv(["MVN_HOME=$mvnHome"]) {
         if (isUnix()) {
            sh '"$MVN_HOME/bin/mvn" clean test'
         } else {
            bat(/"%MVN_HOME%\bin\mvn" clean test/)
         }
      }
   }
   stage('Build') {
      // Run the maven build
      withEnv(["MVN_HOME=$mvnHome"]) {
         if (isUnix()) {
            sh '"$MVN_HOME/bin/mvn" -Dmaven.test.failure.ignore clean package'
         } else {
            bat(/"%MVN_HOME%\bin\mvn" -Dmaven.test.failure.ignore clean package/)
         }
      }
   }
   stage('Deploy') {
      // Run the maven build
      withEnv(["MVN_HOME=$mvnHome"]) {
         if (isUnix()) {
            // Deployed in same vm with Jenkins. So stage can skip.
         } else {
         }
      }
   }
   stage('Run') {
      // Run the maven build
        withEnv(["MVN_HOME=$mvnHome"]) {
       if (isUnix()) {
          sh '"$MVN_HOME/bin/mvn" spring-boot:run'
       } else {
          bat(/"%MVN_HOME%\bin\mvn" spring-boot:run/)
       }
    }
   }
}
