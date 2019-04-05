def call() 
{
 node 
  {
    stage('VmSetup') 
	{
	  def propertyFileLoc = "D:\\Jenkins\\VmOperationDetails.csv"
      def methodcall = new VmSetup.VmOperation(propertyFileLoc)
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
