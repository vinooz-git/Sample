def call() {
def vmname = null;
def prop = new PropertyReader();
def propert = prop.returnData("${WORKSPACE}/pipeline.properties")
if(propert.VmPowerOn == 'Yes')
{
vmname = propert.VmName
vSphere buildStep: [$class: 'PowerOn', timeoutInSeconds: 300, vm: vmname], serverName: 'Neptune'
}

if(propert.VmPowerOff == 'Yes')
{
vmname = propert.VmName
vSphere buildStep: [$class: 'PowerOff', evenIfSuspended: false, ignoreIfNotExists: false, shutdownGracefully: false, vm: vmname], serverName: 'Neptune'
}

if(propert.VmRevert == 'Yes')
{
def vmname = propert.VmName
}

}