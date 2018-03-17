package org.k9.gitcheckout
import org.k9.*

class Git {
  def config
  def script

  Git(script,config) {
    this.config = config
    this.script = script
  }
  
  void checkout(){   
  this.script.stage ('git checkout') {
  this.script.git(url:this.config.giturl, branch:this.config.branch)
  }
  }
}
