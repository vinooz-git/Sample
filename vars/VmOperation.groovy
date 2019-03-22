def call() 
{
def prop = new PropertyReader();
def propert = prop.returnData("${WORKSPACE}/pipeline.properties")

	if(propert.VmPowerOn == 'Yes')
	{
		def vmname = propert.VmName
		vSphere buildStep: [$class: 'PowerOn', timeoutInSeconds: 300, vm: vmname], serverName: 'Neptune'
	}
	vmname = null  
	propert = null
	prop = null
}