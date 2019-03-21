def call() {
def prop = new PropertyReader();
def propert = prop.returnData("${WORKSPACE}/pipeline.properties")
if(propert.VmPowerOn == Yes)
{
vSphere buildStep: [$class: 'PowerOn', timeoutInSeconds: 180, vm: 'pa-tst3-w10'], serverName: 'Neptune'
}

}