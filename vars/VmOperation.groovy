import com.cloudbees.groovy.cps.NonCPS
def call() 
{
def propert = PropertyReader()
def VmName = propert.VmName
def snapshot = propert.snapshotName
def Network = propert.Network
	if(propert.VmPowerOn == 'Yes')
		{
		VmSwitchOn(VmName,Network)
		echo "${VmName} is Switched ON"
		}
	if(propert.VmPowerOff == 'Yes')
		{
		VmSwitchOff(VmName,Network)
		echo "${VmName} is Switched Off"
		}
	if(propert.VmRevert == 'Yes')
		{
		VmRevert(VmName,snapshot,Network)
		echo "${VmName} is Reverted to ${snapshot} - Snapshot"
		}
}	
@NonCPS
def VmSwitchOn(vmname1)
{
	vSphere buildStep: [$class: 'PowerOn', timeoutInSeconds: 260, vm: vmname1], serverName: Network
}
@NonCPS
def VmSwitchOff(vmname1)
{
	vSphere buildStep: [$class: 'PowerOff', evenIfSuspended: false, ignoreIfNotExists: false, shutdownGracefully: false, vm: vmname1], serverName: Network
}
@NonCPS
def VmRevert(VmName, snapshot)
{
	vSphere buildStep: [$class: 'RevertToSnapshot', snapshotName: snapshot, vm: VmName], serverName: Network
}