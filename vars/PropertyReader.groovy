import groovy.util.XmlSlurper
import java.util.Map
import jenkins.*
import jenkins.model.*
import hudson.*
import hudson.model.*

class ProertyReader
{
node {
    def WORKSPACE = pwd()
}
	public ProertyReader()
	{returnDatas()}
	def returnDatas()
	{
		InputStream input = null;
		Properties prop = new Properties();
		
		println "WORKSPACE Path is :"+WORKSPACE
		
		input = new FileInputStream("${WORKSPACE}"+"\\pipeline.properties");
		
		prop.load(input);
		return prop	 
	}
}