import com.cloudbees.groovy.cps.NonCPS
import groovy.util.XmlSlurper
def call() 
{
def file = new File("D:\\Jenkins\\PipelineProperties.xml")
def config = new XmlSlurper().parse(file)
String VMList
VMList = config.VmOperation.RevertMachine.NEPTUNE;
println"Vm list is "+VMList
def VmName = propert.VmNames
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