def call() 
{
 node 
  {
    stage('VmSetup') 
	{
      VmOperation()
	  echo "Vm operations completed"
    }
    stage('BuildDownload')
	{
    echo "Test Stage Completed"
    }
	stage('Script Extraction')
	{
    
    }
	stage('Script Extraction')
	{
    
    }
	stage('TestSetup')
	{
    
    }
	
  }
}
