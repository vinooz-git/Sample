/*
Scope : Perform VM operations - PowerOFF/ON, Revert based on Property file details
Parameters :
  propertyFileLoc - Project pipeline Property file 
*/
package VmSetup

class VmOperation
{
/*
//PropertyFile Keywords and given variables should be matched
def poweroff = "VmPowerOff"
def poweron = "VmPowerOn"    
def revert = "VmRevert"
def tasks = [:]                                           //Empty map 
def reader = new Utils.CsvReader()
def fileContent = [];
fileContent = reader.ReadCSVFile("VmSetup")
println "File Content:"+fileContent.get(0);
fileContent = reader.ReadCSVFile("VmSetup","field")
println "File Content:"+fileContent.get(0);

    String[] lines = file.text.split('\n')
	for(int i =1; i<lines.size(); i++)
	{
		def row = lines[i];
		String[] rowvalues = row.split(',');
		def Action = rowvalues[0].trim(); 
		if(Action.contains(poweroff)||Action.contains(revert)||Action.contains(poweron))
		{
			def VmName = rowvalues[1].trim();
			def Network = rowvalues[2].trim(); def Snapshot = rowvalues[3].trim();
			tasks["node_" + VmName] = {                      //Parallel execution
			if(Action.contains(poweroff))
				{
					VmPowerOff(VmName,Network)
					sleep 10;
				}
			if(Action.contains(revert))
				{
					VmRevert(VmName,Network,Snapshot)
					sleep 10;
				}
			if(Action.contains(poweron))
				{
					VmPowerOn(VmName,Network)
					sleep 10;
				}		
			}
		}
	}
	parallel tasks;
  */

def VmRevert(VmName,Network,Snapshot)
	{
	vSphere buildStep: [$class: 'RevertToSnapshot', snapshotName: Snapshot, vm: VmName], serverName: Network
	echo "${VmName} is Reverted to ${Snapshot} - Snapshot"
	}

def VmPowerOn(VmName,Network)
	{
	vSphere buildStep: [$class: 'PowerOn', timeoutInSeconds: 260, vm: VmName], serverName: Network
	echo "${VmName} is Switched ON"
	}

def VmPowerOff(VmName,Network)
	{
	println"VmName :"+VmName
	println"Network :"+Network
	vSphere buildStep: [$class: 'PowerOff', evenIfSuspended: false, ignoreIfNotExists: false, shutdownGracefully: false, vm: VmName], serverName: Network
	echo "${VmName} is Switched Off"
	}
}