package Utils
class CsvReader {
	List ReadCSVFile(String header)
	{
	println"readcsvfile"
		int row,col,rowCount,colCount = 0;
		def file = new File("D:\\Jenkins\\JobConfig.csv")
		def Arrayvalues = [];
		String[] lines = file.text.split('\n')
		rowCount = lines.size();
		for(int i=0; i<rowCount; i++)
		{
			if(lines[i].contains(header))
			{
				row = i+1
				break
			}
		}
		for(int j=row; j<rowCount; j++)
		{
			if(lines[j].contains("@Stage") || lines[j] == "<EOF>" || lines[j] == '\r' )
				break
			else
				Arrayvalues.add(lines[j].toString())
		}
		int arrLength = Arrayvalues.size()
		println"arrLength:$arrLength"
		for(int k=0; k<arrLength; k++)
		{
			Arrayvalues[k] = Arrayvalues[k].replace('', '')
			Arrayvalues[k] = Arrayvalues[k].replace('(', ' ')
			Arrayvalues[k] = Arrayvalues[k].replace(')', '')
			println"Arrayvalues[k] : "+Arrayvalues[k]
		}
		println"Arrayvalues "+Arrayvalues.toString()
		return Arrayvalues
	}
	List ReadCSVFile(String header, String field)
	{
		field = null //Declared that variable to use same method name with different return type
		int row,col,rowCount,colCount = 0;
		def Arrayvalues = [];
		def file = new File("D:\\Jenkins\\JobConfig.csv")
		String[] lines = file.text.split('\n')
		rowCount = lines.size();
		for(int i =0; i<rowCount; i++)
		{
			if(lines[i].contains(header))
			{
				row = i+2
				break
			}
		}
		lines[row] = lines[row].replace('{', '')
		lines[row] = lines[row].replace('}', '')
		lines[row] = lines[row].replace(' ', '')
		String[] value = lines[row].split(',')
		int arrLength = value.size()
		for(int j=0; j<arrLength; j++)
		{
			Arrayvalues.add(value[j].toString())
		}
		return Arrayvalues
	}
	
}