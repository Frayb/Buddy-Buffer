import java.io.IOException;
import java.util.Scanner;

/**
 * 
 * @author Fraol Bekele
 * Date: March 28, 2022
 * Course: ICS462 Operating System
 * Program description: A program that implements FIFO,LRU and Optimal page replacement algorithm using a given
 *			 page-reference string and calculating the page fault incurred.
 * 
 */
public class Paging {	
	
	Scanner input=new Scanner(System.in);
	int numOfPages;// the number of pages
	int page[];
	int numOfFrames;// the number of page frames
	int frames[];
	int faults;
	int count;
	
/**
 * constructor of Paging class
 * initializing the instance variables
 * @throws IOException
 */
	public Paging() throws IOException{
			
		this.numOfPages=30;
		this.page=new int[numOfPages];
			
}
	
	/**
	 * Function to reset the frame array
	 */
	public void reset(){
		int j;
		for(j=1;j<numOfFrames;j++)
		frames[j]=0;
		faults=0;
		count=1;
		}


	
	/**
	 * page reference for page - reference string 
	 * @throws IOException
	 */
	public void read() throws IOException{
		
		// initializing the page frame with the given page reference string
		for(int i=0;i<numOfPages;i++){
		
			page=new int[] {0,7,0,1,2,0,8,9,0,3,0,4,5,6,7,0,8,9,1,2,3,4,5,9,7,8,5,6,0,3};
		}
		for(int i=1;i<numOfFrames;i++)
			
		frames[i]=-1;
	}
	/**
	 * FIFO page replacement algorithm
	 */
	public void fifo(){
		int i,j,k=0;
		reset();
		boolean found=false;
		
		for(i=0;i<numOfPages;i++){
			for(j=0;j<numOfFrames;j++){
				if(page[i]==frames[j])
					found=true;
				}
			if(found==false){
				frames[k]=page[i];
				if(k==numOfFrames-1)
				k=0;
				else
					k++;
				faults++;
		
		}
		
		found=false;
		}
		System.out.println("\t"+"FIFO has  "+faults +" page faults");
		
}

	/**
	 * LRU page replacement algorithm
	 */
	public void lru(){
		
		int i,j,duration[],max;
		reset();
		duration=new int[numOfFrames];
		boolean found=false;
		for(i=0;i<numOfPages;i++)
		{
		for(j=0;j<numOfFrames;j++)
		duration[j]++;
		for(j=0;j<numOfFrames;j++)
		{
		if(page[i]==frames[j])
		{
		found=true;
		duration[j]=0;
		}
		}
		if(found==false)
		{
		max=0;
		for(j=0;j<numOfFrames;j++)
		{
		if(duration[j]>duration[max])
		max=j;
		}
		frames[max]=page[i];
		duration[max]=0;
		faults++;
		}
		
		
		found=false;
		
		}
		System.out.println("\t"+"LRU has  "+faults +" page faults");
		
		}

		/**
		 * Optimal page replacement algorithm
		 */
	public void opt(){
	
		int i,j=0,k,duration[],max,flag[];
		reset();
		duration=new int[numOfFrames];
		flag=new int[numOfFrames];
		boolean found=false;
		
		for(i=0;i<numOfPages;i++)
		{
		for(j=0;j<numOfFrames;j++)
		{
		flag[j]=0;
		duration[j]=numOfPages;
		}
		
		for(k=i+1;k<numOfPages;k++)
		{
		for(j=0;j<numOfFrames;j++)
		if(page[k]==frames[j]&&flag[j]==0)
		{
		duration[j]=k;
		flag[j]=1;
		}
		}
		
		for(j=0;j<numOfFrames;j++)
		if(page[i]==frames[j])
		found=true;
		if(found==false)
		{
		max=0;
		for(j=0;j<numOfFrames;j++)
		{
		if(duration[j]>duration[max])
		max=j;
		if(frames[j]<0)
		{
		max=j;
		break;
		}
		}
		frames[max]=page[i];
		faults++;
		}
		
		
		found=false;
		
		}
		System.out.println("\t"+ "Optimal page replacement has  "+faults +" page faults");
		
		}
	
	//Driver
	public static void main(String[] args) throws IOException{
		
		System.out.println("Fraol Bekele, 03.28.2022, Assignment 5.");
		
		Paging p = new Paging();
		p.read();
		//implementing the replacement algorithm for page frame varying from 1 to 7
		for ( int i=1; i<=7;i++){
			//System.out.println();
			System.out.println("For " + i + " page Frames and using page reference string: 4,1,8,7,3,6,6,2,7,6,7,8,8,1,1,7,4,8,8,8");
			//System.out.println();
			p.numOfFrames=i;
			p.frames=new int[i];
			p.count=1;
			p.fifo();
			p.lru();
			System.out.println("-----------------------------------------------------------------------------------------------------------------");
				
			}
		
		for ( int i=1; i<=7;i++){
			//System.out.println();
			System.out.println("For " + i + " page Frames and using page reference string: 0,7,0,1,2,0,8,9,0,3,0,4,5,6,7,0,8,9,1,2 ");
			//System.out.println();
			p.numOfFrames=i;
			p.frames=new int[i];
			p.count=1;
			p.fifo();
			p.lru();
			System.out.println("-----------------------------------------------------------------------------------------------------------------");
				
		}

		for ( int i=1; i<=7;i++){
			//System.out.println();
			System.out.println("For " + i + " page Frames and using page reference string: 0,7,0,1,2,0,8,9,0,3,0,4,5,6,7,0,8,9,1,2 ");
			//System.out.println();
			p.numOfFrames=i;
			p.frames=new int[i];
			p.count=1;
			p.fifo();
			p.lru();
			System.out.println("-----------------------------------------------------------------------------------------------------------------");
				
		}
		
		}
}

