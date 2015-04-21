/* @author Bilash Islam
 * media manager
 */

import java.io.File;
import java.io.IOException;

public class MainClass {

	public static File initialFolder = new File("E:\\Downloads"); 
	public static File[] initialFolderList = initialFolder.listFiles();
	public static String initialFolderPath = "E:\\Downloads\\";
	
	public static File targetFolder = new File("E:\\Videos\\TV-Shows"); 
	public static File[] targetFolderList = targetFolder.listFiles();
	public static String targetPath = "E:\\Videos\\TV-Shows\\";
	
	public static final String ext = ".mkv";
	
	public static String[] shows = {"The.Vampire.Diaries",
									"The.Originals",
									"The.Walking.Dead", 
									"Vikings", 
									"Arrow",
									"iZombie",
									"Game.of.Thrones",
									"Game.Of.Thrones",
									"Marco.Polo",
									"Lost"};
	
	public static void main(String [] args) throws IOException{
	
		for(File initialFile:initialFolderList){
			
			String fileName = initialFile.getName();
			
			if(!fileName.contains(".mkv")){
				
				File videoFolder = new File(initialFolderPath + fileName);
				File[] videoFolderList = videoFolder.listFiles();
				
				for(File videoFile:videoFolderList){
					
					fileName = videoFile.getName();
					renameAndDeploy(fileName, videoFile);
				}
				
			}
			else{				
				renameAndDeploy(fileName, initialFile);
			}
		}
	}
	
	public static void renameAndDeploy(String fileName, File videoFile){

		for(String show:shows){
			
			if(fileName.contains(show)){
				
				String seasonInfo = extractSeasonInfo(fileName);
				String withoutFullStop = removeFullStop(show);
				String finalString = withoutFullStop+ " " +seasonInfo+ext;
				System.out.println(finalString);
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
		while(fileName.charAt(startIndex) != 'S'){
			++startIndex;		
		}
		return fileName.substring(startIndex, startIndex+6);
	}
	
}
