/*
Scope : Jenkins CI Pipeine Template for  all Project
Parameters :
  propertyFileLoc - Project pipeline Property file location  
*/
def call() 
{
 def propertyFileLoc = "D:\\Jenkins\\JobConfig.csv"
 node 
  {
    stage('VmSetup') 
	{
    //def methodcall = new VmSetup.VmOperation()
	   def arg = [];
	   def reader = new Utils.CsvReader()
		def fileContent = [];
		fileContent = reader.ReadCSVFile("VmSetup")
		for(int j=0; j<fileContent.size(); i++)
		{
		//"$VmPowerOff,pa-tst4-ws16,Neptune $VmRevert pa-tst4-ws16,neptune,baseimage  $VmPowerOn pa-tst4-ws16,neptune"
			String[] WholeStr = fileContent[j].split('$');
			println "WholeStr :"+WholeStr;
		//$VmPowerOff pa-tst4-ws16,Neptune
			for(int k=0; k<splitStr.size(); k++)
			{
				String[] SplitStr = fileContent[j].split(',');
				println "SplitStr :"+SplitStr;
				for(int i =1; i< splitStr.size(); i++ )
				{
					arg.add(splitStr[i]);
				}
				println"Arg "+arg as String[]
				def test = new VmSetup.VmOperation()
				test.metaClass.methods.each { method ->
				if (method.name == splitStr[0]) {
				method.invoke(test, arg as String[])
				}
			}
		  }
		}
	
	//methodcall.VMOperationCall(propertyFileLoc)
	echo "Vm operations completed"
    }
    stage('Exec_ServerSetup')
	{
	//def methodcall = new BuildLibrary.BuildOperation()
	//methodcall.BuildOperationCall(propertyFileLoc)
	echo "Execution Server side setup Stage Completed"
    }
	stage('Exec_ClientSetup')
	{
    
    }
	stage('Client')
	{
    
    }
  }
}
