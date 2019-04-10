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
	//def methodcall = new BuildLibrary.BuildOperation()
	//def nodeName = "pa-tst4-w7"
	//	node(nodeName) 
	//	{
	//	methodcall.BuildOperationCall(propertyFileLoc)
	//	}
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
