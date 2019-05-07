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
		println "fileContent0 :"+fileContent[0];
		println "fileContent :"+fileContent[1];
		for(int j=0; j<fileContent.size(); j++)
		{
		//"$VmPowerOff,pa-tst4-ws16,Neptune $VmRevert pa-tst4-ws16,neptune,baseimage  $VmPowerOn pa-tst4-ws16,neptune"
			String[] WholeStr = fileContent[j].split('\\$');
			println "WholeStr :"+WholeStr[1];
		//$VmPowerOff pa-tst4-ws16,Neptune
			for(int k=1; k<WholeStr.size(); k++)
			{
				String[] splitStr = WholeStr[k].split(',');
				println "SplitStr :"+splitStr[1];
				for(int i =1; i< splitStr.size(); i++ )
				{
					arg.add(splitStr[i]);
				}
				println"Arg "+arg.toString()
				def test = new VmSetup.VmOperation()
				Object[] methodArgs = new Object[] {arg};
				test.metaClass.methods.each { method ->
				if (method.name == splitStr[0]) {
				method.invoke(test, methodArgs)
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
