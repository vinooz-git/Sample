import groovy.util.XmlSlurper
import java.util.Map
import jenkins.*
import jenkins.model.*
import hudson.*
import hudson.model.*
class ProertyReader
{
	public ProertyReader()
	{returnDatas()}
	def returnDatas()
	{
		InputStream input = null;
		Properties prop = new Properties();
		def hostname = "${env.NODE_NAME}"
		println "host name :"+hostname
		println "Value is ${env.WORKSPACE}"
		input = new FileInputStream("${env.WORKSPACE}"+"\\pipeline.properties");
		
		prop.load(input);
		return prop	 
	}
}