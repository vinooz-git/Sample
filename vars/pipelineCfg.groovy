def call() {
def prop = new Properties("${WORKSPACE}/pipeline.properties");
def runtimeString = 'python'
assert prop.type == 'python'

}
