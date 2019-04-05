def call() 
{
 node 
  {
    stage('VmSetup') 
	{
	def PWD = pwd();
	println"Directory path :"+PWD
	 // def propertyFileLoc = "D:\\Jenkins\\VmOperationDetails.csv"
     //def methodcall = new VmSetup.VmOperation()
	//  methodcall.VMOperationCall(propertyFileLoc)
	  //echo "Vm operations completed"
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
