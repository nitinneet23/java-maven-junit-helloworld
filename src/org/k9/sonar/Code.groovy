package org.k9.sonar
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
    step([$class: 'CheckStylePublisher', unstableTotalAll:'300'])
            step([$class: 'PmdPublisher', unstableTotalAll:'20'])
            step([$class: 'FindBugsPublisher', pattern: '**/findbugsXml.xml', unstableTotalAll:'20'])
            step([$class: 'JacocoPublisher'])
            publishHTML (target: [keepAll: true, reportDir: 'target/site', reportFiles: 'project-info.html', reportName: "Site Report"])

  }
}
}
}
