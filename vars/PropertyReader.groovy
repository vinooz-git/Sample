class PropertyReader {

    String filePath

    PropertyReader(String filePath) {
        this.filePath = filePath
    }

    def propertyMissing(String name) {
        Properties props = new Properties()
        File propsFile = new File(filePath)
        propsFile.withInputStream {
            props.load it
        }
        props."$name"
    }

    def methodMissing(String name, args) {
        Properties props = new Properties()
        File propsFile = new File(filePath)

        props.load propsFile.newDataInputStream()
        props.setProperty name, args.toString() - '[' - ']'
        props.store propsFile.newWriter(), null
    }

}