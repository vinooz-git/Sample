def call() 
{
 node 
  {
    stage('VmSetup') 
	{
	def propertyFileLoc = "D:\\Jenkins\\VmOperationDetails.csv"
    def methodcall = new VmSetup.VmOperation()
	methodcall.VMOperationCall(propertyFileLoc)
	echo "Vm operations completed"
    }
    stage('BuildDownload')
	{
	def methodcall = new BuildLibrary.BuildOperation()
	methodcall.BuildOperationCall(propertyFileLoc)
	
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
