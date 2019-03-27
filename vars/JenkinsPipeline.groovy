def call() 
{
 node 
  {
    stage('VmSetup') 
	{
      VmOperation()
	  echo "Vm operations completed"
    }
    stage('BuildDownload')
	{
	node('pa-tst6-w7') 
	{
	svn 'http://brdc-svn.network.internal/svn/AutomationFrameworks/branches/7.1_Universal%20Viewer/Functional_Automation/Scripts/Selenium/Scripts/'
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
