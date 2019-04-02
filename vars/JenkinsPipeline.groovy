def call() 
{
 node ('master')
  {
    stage('VmSetup') 
	{
	VmOperation()
	echo "Vm operations completed"
    }
  }
    stage('BuildDownload')
	{
	node('pa-tst4-w7') 
	{
	checkout([$class: 'SubversionSCM', additionalCredentials: [], excludedCommitMessages: '', excludedRegions: '', excludedRevprop: '', excludedUsers: '', filterChangelog: false, ignoreDirPropChanges: false, includedRegions: '', locations: [[cancelProcessOnExternalsFail: true, credentialsId: '3c8a141a-24e6-4010-a36b-8ab9209b42d8', depthOption: 'infinity', ignoreExternalsOption: true, local: '.', remote: 'http://brdc-svn.network.internal/svn/AutomationFrameworks/branches/7.1_Universal%20Viewer/Functional_Automation/Scripts/Selenium/Scripts']], quietOperation: true, workspaceUpdater: [$class: 'UpdateUpdater']])
	}
    echo "Test Stage Completed"
    }
	stage('Script Extraction')
	{
    
    }
	stage('TestSetup')
	{
    
    }
	
  }
}
