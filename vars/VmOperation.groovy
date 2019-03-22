def call() 
{
def prop = new PropertyReader();
def propert = prop.returnData("${WORKSPACE}/pipeline.properties")
if(propert.VmPowerOff == 'Yes')
	{
	VmSwitchOn()
	}
		
}
def VmSwitchOn()
{
def vmname1 = propert.VmName
		vSphere buildStep: [$class: 'PowerOff', evenIfSuspended: false, ignoreIfNotExists: false, shutdownGracefully: false, vm: vmname1], serverName: 'Neptune'
}