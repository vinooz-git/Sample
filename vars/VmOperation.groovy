import com.cloudbees.groovy.cps.NonCPS
def call() 
{
def prop = new PropertyReader();
def propert = prop.returnData("${WORKSPACE}/pipeline.properties")
if(propert.VmPowerOff == 'Yes')
	{
	VmSwitchOn(propert.VmName)
	}
}	
@NonCPS
def VmSwitchOn(vmname1)
{
	try
	{
		vSphere buildStep: [$class: 'PowerOff', evenIfSuspended: false, ignoreIfNotExists: false, shutdownGracefully: false, vm: vmname1], serverName: 'Neptune'
	}
	catch(Exception e)
	{
	
	}
}