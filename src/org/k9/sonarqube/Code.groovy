package org.k9.sonarqube
import org.k9.*

class Code {
  def config
  def script

  Code(script,config) {
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
      withSonarQubeEnv('Sonarqube') { 
    this.script.sh "mvn clean package sonar:sonar"
  }
}
}
}
