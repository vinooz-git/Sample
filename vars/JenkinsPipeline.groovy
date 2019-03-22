def call() 
{
 node 
  {
    stage('VmSetup') 
	{
      VmOperation()
    }
    stage('Test')
	{
    echo "Test Stage Completed"
    }
  }
}
