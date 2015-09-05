/* @author Bilash Islam
 * media manager
 */

import java.io.File;
import java.io.IOException;
import java.nio.file.Files; 
import java.nio.file.Paths;
import java.util.ArrayList;

public class MainClass {

	public static final String showsTextFile = "C:\\Users\\bilas\\Documents\\Workspace\\media_manager\\src\\showsList.txt"; 
	
	public static File initialFolder = new File("E:\\Downloads"); 
	public static File[] initialFolderList = initialFolder.listFiles();
	public static String initialFolderPath = "E:\\Downloads\\";
	
	public static File targetFolder = new File("E:\\Videos\\TV-Shows"); 
	public static File[] targetFolderList = targetFolder.listFiles();
	public static String targetPath = "E:\\Videos\\TV-Shows\\";
	
	public static final String ext = ".mkv";

	public static ArrayList<String> shows = new ArrayList<String>();
	
	public static void main(String [] args) throws IOException{

		Files.lines(Paths.get(showsTextFile)).forEach(line -> shows.add(line));
		
		for(File initialFile:initialFolderList){
			
			String fileName = initialFile.getName();
			
			for(String show:shows){
			
				if(fileName.contains(show) && !fileName.contains(ext)){
					
					File videoFolder = new File(initialFolderPath + fileName);
					File[] videoFolderList = videoFolder.listFiles();
	
					for(File videoFile:videoFolderList){
						
						fileName = videoFile.getName();
						
						if(fileName.contains(ext)){
							renameAndDeploy(fileName, videoFile);
							deleteFolder(videoFolder);
						}
					}					
				}
				else if(fileName.contains(show) && fileName.contains(ext)){				
					renameAndDeploy(fileName, initialFile);
				}
			}
		}
	}
	
	public static void renameAndDeploy(String fileName, File videoFile){

		for(String show:shows){
			
			if(fileName.contains(show)){
				
				String seasonInfo = extractSeasonInfo(fileName);
				String withoutFullStop = removeFullStop(show);
				String finalString = withoutFullStop+ " " +seasonInfo+ext;

				for(File targetFile:targetFolderList){
					
					if(withoutFullStop.equalsIgnoreCase(targetFile.getName())){
					
						videoFile.renameTo(new File(targetPath + targetFile.getName() + "\\" + finalString)); 
					}
				}
			}
		}
	}
	
	public static String removeFullStop(String in){
		
		char[] array = in.toCharArray();
		for(int i=0; i<array.length; ++i){
			if(array[i] == '.'){
				array[i] = ' ';
			}
		}
		return String.valueOf(array);
	}
	
	public static String extractSeasonInfo(String fileName){
		
		int startIndex = 1; 
		while(fileName.charAt(startIndex) != 'S' || fileName.charAt(startIndex+1) != '0'){
			++startIndex;		
		}
		return fileName.substring(startIndex, startIndex+6);
	}
	
	public static void deleteFolder(File folder) {
	    File[] files = folder.listFiles();
	    if(files!=null) {
	        for(File f: files) {
	            if(f.isDirectory()) {
	                deleteFolder(f);
	            } else {
	                f.delete();
	            }
	        }
	    }
	    folder.delete();
	}
}
