import groovy.util.XmlSlurper
import java.util.Map
import jenkins.*
import jenkins.model.*
import hudson.*
import hudson.model.*

class ProertyReader
{
	def returnDatas(String filepath)
	{
	    InputStream input = null;
		Properties prop = new Properties();
		input = new FileInputStream(filepath);
		prop.load(input);
		return prop	 
	}
}