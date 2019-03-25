import com.cloudbees.groovy.cps.NonCPS
def call() 
{
def propert = PropertyReader()
def VmName = propert.VmName
def snapshot = propert.snapshotName
	if(propert.VmPowerOn == 'Yes')
		{
		VmSwitchOn(VmName)
		echo "${VmName} is Switched ON"
		}
	if(propert.VmPowerOff == 'Yes')
		{
		VmSwitchOff(VmName)
		echo "${VmName} is Switched Off"
		}
	if(propert.VmRevert == 'Yes')
		{
		VmRevert(VmName,snapshot)
		echo "${VmName} is Reverted to ${snapshot} - Snapshot"
		}
}	
@NonCPS
def VmSwitchOn(vmname1)
{
	vSphere buildStep: [$class: 'PowerOn', timeoutInSeconds: 260, vm: vmname1], serverName: 'Neptune'
}
@NonCPS
def VmSwitchOff(vmname1)
{
	vSphere buildStep: [$class: 'PowerOff', evenIfSuspended: false, ignoreIfNotExists: false, shutdownGracefully: false, vm: vmname1], serverName: 'Neptune'
}
@NonCPS
def VmRevert(VmName, snapshot)
{
	vSphere buildStep: [$class: 'RevertToSnapshot', snapshotName: snapshot, vm: VmName], serverName: 'Neptune'
}