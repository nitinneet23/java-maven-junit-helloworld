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
    this.script.sh "mvn clean package sonar:sonar"
}
}
}
