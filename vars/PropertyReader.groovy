class PropertyReader {

   String filePath
   PropertyReader(String filePath) {
        this.filePath = filePath
	InputStream input = null;
	input = new FileInputStream(filePath);
	prop.load(input);
	return prop	
    }
    
}