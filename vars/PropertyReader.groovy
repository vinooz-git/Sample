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
		ssh label: '', script: 'pwd > workspace'
		workspace = readFile('workspace').trim()
		println "workSpace is. "+workspace
		println "Value is ${WORKSPACE}"
		def PWD = pwd();
		println "host name :"+PWD
		println "Value is ${env.WORKSPACE}"
		input = new FileInputStream("${env.WORKSPACE}"+"\\pipeline.properties");
		
		prop.load(input);
		return prop	 
	}
}