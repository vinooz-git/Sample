def call() 
{
		vSphere buildStep: [$class: 'PowerOn', timeoutInSeconds: 300, vm: 'pa-tst3-w10'], serverName: 'Neptune'
}
