def call() {
def prop = new PropertyReader();
def propert = prop.returnData("${WORKSPACE}/pipeline.properties")
def runtimeString = 'ICA'
assert propert.type == runtimeString
assert propert.runTests == 'false'

}
