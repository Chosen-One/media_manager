/* @author Bilash Islam
 * AutoMation @build Pre-Alpha 
 * Current Phase - Adding Functionalities, White-Box Testing
 */

package pack1;

import java.io.File;
import java.io.IOException;

/* Bottom-Up Integration.
 * Technique - eXtreme 
 */

public class MainClass {
	
	public static void main(String [] args) throws IOException{
		
		/* test Driver 1
		 * input - initial file location and extension 
		 */
		File folder = new File("E:\\Downloads"); String ext = ".mkv";
		File[] list = folder.listFiles();
		
		/* test Driver 2
		 * input - target location (where the file will be moved)
		 */
		File folder2 = new File("E:\\Videos\\TV-Shows"); String target = "E:\\Videos\\TV-Shows\\";
		File[] list2 = folder2.listFiles();
		
		/* Stub
		 * movies database in "show" array
		 */
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
					String src = removeStop(show) + " " + season + ext;
					
					for(File file2:list2){
						
						if(removeStop(show).contains(file2.getName())){
							
								file.renameTo(new File(target + file2.getName() + "\\" + src)); ++count;
						}
					}
				}
			}	
		}
		System.out.println("Corrected " + count + " files.");		
	}
	
	/* @param String with full stops
	 * @return full stops replaced with spaces
	 */
	public static String removeStop(String in){
		
		char[] array = in.toCharArray();
		
		for(int i=0; i<array.length; ++i){
			
			if(array[i] == '.'){
				
				array[i] = ' ';
			}
		}
		
		return String.valueOf(array);
	}

}
