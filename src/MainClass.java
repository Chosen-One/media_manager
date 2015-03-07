/* @author Bilash Islam
 * media manager
 */

import java.io.File;
import java.io.IOException;

public class MainClass {
	
	public static void main(String [] args) throws IOException{

		File folder = new File("E:\\Downloads"); String ext = ".mkv";
		File[] list = folder.listFiles();
		
		File folder2 = new File("E:\\Videos\\TV-Shows"); String target = "E:\\Videos\\TV-Shows\\";
		File[] list2 = folder2.listFiles();
		
		String[] shows = {"The.Vampire.Diaries", "The.Walking.Dead", "The.Flash", "Arrow", "Supernatural","Marco.Polo"};
		int count = 0;
		for(File file:list){
			
			String name = file.getName();
			for(String show:shows){
				
				if(name.contains(show)){
					
					int sI = 1; 
					while(name.charAt(sI) != 'S'){
						++sI;		
					}
					String season = name.substring(sI, sI+6);
					String src = removeFullStop(show) + " " + season + ext;
					
					for(File file2:list2){
						
						if(removeFullStop(show).contains(file2.getName())){
							
								file.renameTo(new File(target + file2.getName() + "\\" + src)); ++count;
						}
					}
				}
			}	
		}
		System.out.println("Corrected " + count + " files.");		
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

}
