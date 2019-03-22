import com.cloudbees.groovy.cps.NonCPS
def call() 
{
def propert = new PropertyReader()
def VmName = 'pa-tst3-w10'
if(propert.VmPowerOff == 'Yes')
	{
	VmSwitchOff(VmName)
	}
if(propert.VmPowerOn == 'Yes')
	{
	VmSwitchOn(propert.VmName)
	}
}	

@NonCPS
def VmSwitchOn(vmname1)
{
	vSphere buildStep: [$class: 'PowerOff', evenIfSuspended: false, ignoreIfNotExists: false, shutdownGracefully: false, vm: vmname1], serverName: 'Neptune'
	
}
@NonCPS
def VmSwitchOff(vmname1)
{
	vSphere buildStep: [$class: 'PowerOff', evenIfSuspended: false, ignoreIfNotExists: false, shutdownGracefully: false, vm: vmname1], serverName: 'Neptune'
	
}
@NonCPS
def VmRevert(vmname1)
{
	vSphere buildStep: [$class: 'PowerOff', evenIfSuspended: false, ignoreIfNotExists: false, shutdownGracefully: false, vm: vmname1], serverName: 'Neptune'
	
}