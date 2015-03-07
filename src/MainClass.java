/* @author Bilash Islam
 * media manager
 */

import java.io.File;
import java.io.IOException;

public class MainClass {

	public static File intialFolder = new File("E:\\Downloads"); 
	public static File[] intialFolderList = intialFolder.listFiles();
	
	public static File targetFolder = new File("E:\\Videos\\TV-Shows"); 
	public static File[] targetFolderList = targetFolder.listFiles();
	public static String targetPath = "E:\\Videos\\TV-Shows\\";
	public static String ext = ".mkv";
	
	public static String[] shows = {"The.Vampire.Diaries",
									"The.Walking.Dead", 
									"The.Flash", 
									"Arrow", 
									"Supernatural",
									"Marco.Polo"};
	
	public static void main(String [] args) throws IOException{

		int amountOfShowsCorrected = 0;
		for(File intialFile:intialFolderList){
			String fileName = intialFile.getName();
			for(String show:shows){
				
				if(fileName.contains(show)){
					String seasonInfo = extractSeasonInfo(fileName);
					String withoutFullStop = removeFullStop(show);
					String finalString = withoutFullStop+ " " +seasonInfo+ext;
					
					for(File targetFile:targetFolderList){
						
						if(withoutFullStop.contains(targetFile.getName())){
							targetFile.renameTo(new File(targetPath + targetFile.getName() + "\\" + finalString)); 
							++amountOfShowsCorrected;
						}
					}
				}
			}	
		}
		System.out.println("Corrected " + amountOfShowsCorrected + " files.");		
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
		while(fileName.charAt(startIndex) != 'S'){
			++startIndex;		
		}
		return fileName.substring(startIndex, startIndex+6);
	}
	
	

}
