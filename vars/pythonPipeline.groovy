def call() {
  node {
    stage('VmSetup') {
      VmOperation()
    }
    def p = pipelineCfg()
    
    stage('Test') {
    echo "Test Stage Completed"
      }
    }
  }
