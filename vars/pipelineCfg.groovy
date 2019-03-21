def call() {
def propertyReader = new PropertyReader("${WORKSPACE}/pipeline.properties")
def runtimeString = 'python'
assert propertyReader.type == 'python'

}
