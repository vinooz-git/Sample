import com.cloudbees.groovy.cps.NonCPS
import groovy.util.XmlSlurper
def call() 
{
def file = new File("D:\\Jenkins\\VmOperationDetails.csv")
if (file.exists() && file.isFile()) {
    String[] lines = file.text.split('\n')
    List<String[]> rows = lines.collect {
        it.split(',')
    }
	println"the Line is "+rows.toString()
} else
 {
 echo "Exception Occurs, The Csv File not in Valid format  "
}
String VMList
println"Vm list is "+VMList
}
@NonCPS
def VmPowerOn()
	{
	VMList = config.VmOperation.PowerOnMachine.VmName;
	if(VMList!="")
      {
            def PowerOn_Machines = [:]
            String[] addittionalServers = addittionalServersList.split(',');
			
	vSphere buildStep: [$class: 'PowerOn', timeoutInSeconds: 260, vm: VmName], serverName: Network
	echo "${VmName} is Switched ON"
	  }
	}
@NonCPS
def VmPowerOff()
	{
	vSphere buildStep: [$class: 'PowerOff', evenIfSuspended: false, ignoreIfNotExists: false, shutdownGracefully: false, vm: VmName], serverName: Network
	echo "${VmName} is Switched Off"
	}
@NonCPS
def VmRevert()
	{
	vSphere buildStep: [$class: 'RevertToSnapshot', snapshotName: snapshot, vm: VmName], serverName: Network
	echo "${VmName} is Reverted to ${snapshot} - Snapshot"
	}