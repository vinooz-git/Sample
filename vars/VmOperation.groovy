import com.cloudbees.groovy.cps.NonCPS
def call() 
{
def propert = PropertyReader()
def VmName = propert.VmName
def snapshot = propert.snapshotName
if(propert.VmPowerOn == 'Yes')
	{
	VmSwitchOn(VmName)
	}
if(propert.VmPowerOff == 'Yes')
	{
	VmSwitchOff(VmName)
	}
if(propert.VmRevert == 'Yes')
	{
	VmRevert(VmName,snapshot)
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
def VmRevert(vmname1)
{
	vSphere buildStep: [$class: 'RevertToSnapshot', snapshotName: snapshot, vm: VmName], serverName: 'Neptune'
}