def call() {
Properties properties = new Properties()
File propertiesFile = new File(${WORKSPACE}/pipeline.properties)
propertiesFile.withInputStream {
    properties.load(it)
}

def runtimeString = 'type'
assert properties."$runtimeString" == 'python'

}
