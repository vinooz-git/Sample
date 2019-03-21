def call() {
Properties prop = new Properties();
	InputStream input = null;
	input = new FileInputStream("${WORKSPACE}/pipeline.properties");
	prop.load(input);
def runtimeString = 'python'
assert prop.type == 'python'

}
