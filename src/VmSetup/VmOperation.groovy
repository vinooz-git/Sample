import com.cloudbees.groovy.cps.NonCPS
import groovy.util.XmlSlurper
package VmSetup
class VmOperation
{
	node
	{
		 
		def jenkins = Jenkins.instance
		 
		def item = Jenkins.instance.getItemByFullName("${env.JOB_NAME}")
		println"new item"+item
		println"new item Job name is ${env.JOB_NAME}"
		def workspacePath = Jenkins.instance.getWorkspaceFor(item)
		
		println "Workspace path - " + workspacePath
		
		println "Workspace new Path ${WORKSPACE}"
		println "JENKINS_HOME new Path ${JENKINS_HOME}"
		println "JOB_NAME new Path ${JOB_NAME}"
		println "JOB_BASE_NAME new Path ${JOB_BASE_NAME}"
	}
def file = new File("D:\\Jenkins\\VmOperationDetails.csv")
  if (file.exists() && file.isFile()) 
  {
    String[] lines = file.text.split('\n')
	for(int i =1; i<lines.size(); i++)
	{
		def row = lines[i];
		String[] rowvalues = row.split(',');
		//println"Current row values :"+row
		def Action = rowvalues[0].trim(); def VmName = rowvalues[1].trim();
		def Network = rowvalues[2].trim(); def Snapshot = rowvalues[3].trim();
		if(Action.equalsIgnoreCase("VmRevert"))
			{
				VmRevert(VmName,Network,Snapshot)
				sleep 10;
			}
		if(Action.equalsIgnoreCase("VmPowerOn"))
			{
				VmPowerOn(VmName,Network)
				sleep 10;
			}
		if(Action.equalsIgnoreCase("VmPowerOff"))
			{
				VmPowerOff(VmName,Network)
				sleep 10;
			}
	}
  } 
 else
 {
 echo "Exception Occurs, The Csv File not in Valid format"
 }
}
@NonCPS
def VmRevert(VmName,Network,Snapshot)
	{
	vSphere buildStep: [$class: 'RevertToSnapshot', snapshotName: Snapshot, vm: VmName], serverName: Network
	echo "${VmName} is Reverted to ${snapshot} - Snapshot"
	}
@NonCPS
def VmPowerOn(VmName,Network)
	{
	vSphere buildStep: [$class: 'PowerOn', timeoutInSeconds: 260, vm: VmName], serverName: Network
	echo "${VmName} is Switched ON"
	}
@NonCPS
def VmPowerOff(VmName,Network)
	{
	vSphere buildStep: [$class: 'PowerOff', evenIfSuspended: false, ignoreIfNotExists: false, shutdownGracefully: false, vm: VmName], serverName: Network
	echo "${VmName} is Switched Off"
	}
