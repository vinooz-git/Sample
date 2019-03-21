def call() {
  node {
    stage('Checkout') {
      echo "Checkout completed"
    }
    def p = pipelineCfg()
    
    stage('Test') {
    echo "Test Stage Completed"
      }
    }
  }
