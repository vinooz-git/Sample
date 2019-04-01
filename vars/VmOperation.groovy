import com.cloudbees.groovy.cps.NonCPS
def call() 
{
node {
    def WORKSPACE = pwd()
    echo "${WORKSPACE}"
}
def propertyreader = new PropertyReader()
def propert = propertyreader.returnDatas(WORKSPACE)
def VmName = propert.VmName
def snapshot = propert.snapshotName
def Network = propert.Network
	if(propert.VmPowerOn == 'Yes')
		{
		vSphere buildStep: [$class: 'PowerOn', timeoutInSeconds: 260, vm: VmName], serverName: Network
		echo "${VmName} is Switched ON"
		}
	if(propert.VmPowerOff == 'Yes')
		{
		vSphere buildStep: [$class: 'PowerOff', evenIfSuspended: false, ignoreIfNotExists: false, shutdownGracefully: false, vm: VmName], serverName: Network
		echo "${VmName} is Switched Off"
		}
	if(propert.VmRevert == 'Yes')
		{
		vSphere buildStep: [$class: 'RevertToSnapshot', snapshotName: snapshot, vm: VmName], serverName: Network
		echo "${VmName} is Reverted to ${snapshot} - Snapshot"
		}
}	