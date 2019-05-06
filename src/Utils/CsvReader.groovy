class CSVReader {
	List ReadCSVFile(String header)
	{
		int row,col,rowCount,colCount = 0;
		def file = new File("C:/Newrepo/vars/ConfigParam.csv")
		def Arrayvalues = [];
		String[] lines = file.text.split('\n')
		rowCount = lines.size();
		for(int i=0; i<rowCount; i++)
		{
			if(lines[i].contains(header))
			{
				row = i+2
				break
			}
		}
		for(int j=row; j<rowCount; j++)
		{
			if(lines[j].contains("@Stage") || lines[j] == "<EOF>" || lines[j] == '\r')
				break
			else
				Arrayvalues.add(lines[j].toString())
		}
		int arrLength = Arrayvalues.size()
		for(int k=0; k<arrLength; k++)
		{
			Arrayvalues[k] = Arrayvalues[k].replace('{', '')
			Arrayvalues[k] = Arrayvalues[k].replace('}', '')
			Arrayvalues[k] = Arrayvalues[k].replace(' ', '')
		}
		return Arrayvalues
	}
	List ReadCSVFile(String header, String field)
	{
		def field = null //Declared that variable to use same method name with different return type
		int row,col,rowCount,colCount = 0;
		def Arrayvalues = [];
		def file = new File("C:/Newrepo/vars/ConfigParam.csv")
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