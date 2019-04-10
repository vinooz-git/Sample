package VmSetup
@Grab(group='org.ini4j', module='ini4j', version='0.5.4')
import org.ini4j.*;

def VMOperationCall(def propertyFileLoc)
{
Ini ini = new Ini(new File("D:\\Jenkins\\CofigFile.ini"));
System.out.println(ini.get("owner", "Name"));
//def content = readFile '..\\vars\\CofigFile.ini'
/*
  if (file.exists() && file.isFile()) 
  {
    String[] lines = file.text.split('\n')
	for(int i =1; i<lines.size(); i++)
	{
		def row = lines[i];
		String[] rowvalues = row.split(',');
		//println"Current row values :"+row
		def Action = rowvalues[0].trim(); def VmName = rowvalues[1].trim();
		def Network = rowvalues[2].trim(); def Snapshot = rowvalues[3].trim();
		tasks["node_" + VmName] = {
		if(Action.contains("VmPowerOff"))
			{
				VmPowerOff(VmName,Network)
				sleep 10;
			}
		if(Action.contains("VmRevert"))
			{
				VmRevert(VmName,Network,Snapshot)
				sleep 10;
			}
		if(Action.contains("VmPowerOn"))
			{
				VmPowerOn(VmName,Network)
				sleep 10;
			}		
		}
	}
	parallel tasks;
  } 
 else
 {
 echo "Exception Occurs, The Csv File not in Valid format"
 }
}
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
	vSphere buildStep: [$class: 'PowerOff', evenIfSuspended: false, ignoreIfNotExists: false, shutdownGracefully: false, vm: VmName], serverName: Network
	echo "${VmName} is Switched Off" */
	}
	