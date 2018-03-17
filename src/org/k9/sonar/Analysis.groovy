package org.k9.sonar
import org.k9.*

class Analysis {
  def config
  def script

  Analysis(script,config) {
      this.config = config
      this.script = script
  }
  
  void checkout(){   
      this.script.stage ('git checkout') {
      this.script.git(url:this.config.giturl, branch:this.config.branch)
    }
  }

void codeanalysis(){   
  this.script.stage ('SonarQube Analysis') {
    this.script.sh "mvn clean package sonar:sonar" + 
            '-f all/pom.xml ' +
            '-Dsonar.projectKey=com.huettermann:all:master ' +
            '-Dsonar.login=$SONAR_UN ' +
            '-Dsonar.password=$SONAR_PW ' +
            '-Dsonar.language=java ' +
            '-Dsonar.sources=. ' +
            '-Dsonar.tests=. ' +
            '-Dsonar.test.inclusions=**/*Test*/** ' +
            '-Dsonar.exclusions=**/*Test*/**'
}
}
}
