package VmSetup
class VmOperation
{
public VmOperation()
{
println"Vm operation Method working fine";
}
def file = new File("D:\\Jenkins\\VmOperationDetails.csv");

def VmRevert(VmName,Network,Snapshot)
	{
	vSphere buildStep: [$class: 'RevertToSnapshot', snapshotName: Snapshot, vm: VmName], serverName: Network
	echo "${VmName} is Reverted to ${snapshot} - Snapshot"
	}

def VmPowerOn(VmName,Network)
	{
	vSphere buildStep: [$class: 'PowerOn', timeoutInSeconds: 260, vm: VmName], serverName: Network
	echo "${VmName} is Switched ON"
	}

def VmPowerOff(VmName,Network)
	{
	vSphere buildStep: [$class: 'PowerOff', evenIfSuspended: false, ignoreIfNotExists: false, shutdownGracefully: false, vm: VmName], serverName: Network
	echo "${VmName} is Switched Off"
	}
}