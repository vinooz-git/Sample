def call() 
{
def prop = new PropertyReader();
def propert = prop.returnData("${WORKSPACE}/pipeline.properties")

	if(propert.VmPowerOn == 'Yes')
	{
		def vmname = propert.VmName
		vSphere buildStep: [$class: 'PowerOn', timeoutInSeconds: 300, vm: vmname], serverName: 'Neptune'
	}
	def vmname = null
	if(propert.VmPowerOff == 'Yes')
	{
		def vmname1 = propert.VmName
		vSphere buildStep: [$class: 'PowerOff', evenIfSuspended: false, ignoreIfNotExists: false, shutdownGracefully: false, vm: vmname1], serverName: 'Neptune'
	}
	def vmname1 = null
  
}