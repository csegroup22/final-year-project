package org.g22.file;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

public class FileSelector
{
	JFileChooser fileChooser=new JFileChooser();
	
	public ArrayList<File> getFiles()
	{
		fileChooser.setCurrentDirectory(new File("/home/nirmal/Documents/Final-Year-Project/Input/"));
		fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("JavaScript Object Notation", "json"));
		fileChooser.setMultiSelectionEnabled(true);
		if(fileChooser.showOpenDialog(null)==JFileChooser.APPROVE_OPTION)
		{
			return new ArrayList<File>(Arrays.asList(fileChooser.getSelectedFiles()));
		}
		return null;
	}
	public String getCurrentDirectory()
	{
		return fileChooser.getCurrentDirectory().toString();
	}
}
