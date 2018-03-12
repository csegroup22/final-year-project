package org.g22.main;

import java.io.IOException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

public class Main
{
	public static void main(String[] args) throws JsonParseException, JsonMappingException, IOException
	{
		ProjectInterface projectInterface=new ProjectInterface();
		projectInterface.runProject();
	}
}
