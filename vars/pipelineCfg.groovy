def call() {
def prop = new PropertyReader();
def propert = prop.returnData("${WORKSPACE}/pipeline.properties")
def runtimeString = 'python'
assert propert.type == 'python'

}
