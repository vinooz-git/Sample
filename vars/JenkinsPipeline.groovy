def call() 
{
 node 
  {
    stage('VmSetup') 
	{
      def methodcall = new VmSetup.VmOperation()
	  methodcall.VMOperationCall()
	  echo "Vm operations completed"
    }
    stage('BuildDownload')
	{
	node('pa-tst4-w7') 
	{
	}
    echo "Test Stage Completed"
    }
	stage('Script Extraction')
	{
    
    }
	stage('TestSetup')
	{
    
    }
	
  }
}
