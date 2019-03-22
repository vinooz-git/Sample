import com.cloudbees.groovy.cps.NonCPS
def call() 
{
 def VmName = 'pa-tst3-w10'
def propert = 'Yes'
if(propert == 'Yes')
	{
	VmSwitchOn(VmName)
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