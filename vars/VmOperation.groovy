import com.cloudbees.groovy.cps.NonCPS
import groovy.util.XmlSlurper
def call() 
{
def file = new File("D:\\Jenkins\\VmOperationDetails.csv")
if (file.exists() && file.isFile()) 
{
    String[] lines = file.text.split('\n')
	for(int i =1; i<lines.size(); i++)
	{
		def row = lines[i];
		String[] rowvalues = row.split(',');
		println"Current row values :"+row
		def Action = rowvalues[0].trim(); def VmName = rowvalues[1].trim();
		def Network = rowvalues[2].trim(); def Snapshot = rowvalues[3].trim();
		if(Action.equalsIgnoreCase("VmRevert"))
			{
				VmRevert(VmName,Network,Snapshot)
			}
		if(Action.equalsIgnoreCase("VmPowerOn"))
			{
				VmPowerOn(VmName,Network)
			}
		if(Action.equalsIgnoreCase("VmPowerOff"))
			{
				VmPowerOff(VmName,Network)
			}
	}
} 
else
{
 echo "Exception Occurs, The Csv File not in Valid format"
}

}
@NonCPS
def VmRevert(VmName,Network,Snapshot)
	{
	echo "${Snapshot} is th snapshot name"
	echo "${VmName} is th VM NAME"
	echo "${Network} is the Network"
	//vSphere buildStep: [$class: 'RevertToSnapshot', snapshotName: 'BaseImage', vm: 'pa-tst15-w7'], serverName: 'NEPTUNE'
	vSphere buildStep: [$class: 'RevertToSnapshot', snapshotName: Snapshot, vm: VmName], serverName: Network
	echo "${VmName} is Reverted to ${snapshot} - Snapshot"
	}
@NonCPS
def VmPowerOn(VmName,Network)
	{
	vSphere buildStep: [$class: 'PowerOn', timeoutInSeconds: 260, vm: VmName], serverName: Network
	echo "${VmName} is Switched ON"
	}
@NonCPS
def VmPowerOff(VmName,Network)
	{
	vSphere buildStep: [$class: 'PowerOff', evenIfSuspended: false, ignoreIfNotExists: false, shutdownGracefully: false, vm: VmName], serverName: Network
	echo "${VmName} is Switched Off"
	}
