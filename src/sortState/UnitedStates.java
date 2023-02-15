package sortState;

import java.util.*;
import java.io.*; 

public class UnitedStates
{
	// instance variables
	private ArrayList <State> states;
	
	public UnitedStates()
	{
	   states = new ArrayList <State> ();
	   
	   readFile();
	   //printStates();
	   
	   System.out.println();
	   System.out.println("=========================");
	   System.out.println("  Sorted by State");
	   System.out.println("=========================");
	   printStates();	
	   
	   menu();
	}
	
	
	
	/*
	 * Merge Sort
	 * 
	 * Use a merge sort to order the ArrayList
	 * by the state's name
	 */
	
	//I made two methods as I found it easier since I did have some trouble understanding how to do this properly.
	 private void mergeSort(ArrayList <State> A) {
	        if (A.size() > 1) {
	            int q = A.size()/2;
	            ArrayList <State> leftArray = new ArrayList <State> ();
	            ArrayList <State> rightArray = new ArrayList <State> ();
	            //leftArray = Arrays.copyOf(A, 0, q-1);
	           for (int i = 0; i < q; i++) {
	        	   leftArray.add(A.get(i));
	           }
	           
	           for (int i = q; i < A.size(); i++) {
	        	   rightArray.add(A.get(i));
	           }
	            
	            //rightArray = Arrays.copyOf(A ,q,A.size()-1);

	            mergeSort(leftArray);
	            mergeSort(rightArray);

	            merge(A,leftArray,rightArray);
	        }
	    }
	 
	 private void merge(ArrayList <State> a, ArrayList <State> l, ArrayList <State> r) {
	        int totElem = l.size() + r.size();
	        //int[] a = new int[totElem];
	        int i,li,ri,j;
	        i = li = ri = j = 0;
	        while ( i < totElem) {
	            if ((li < l.size()) && (ri < r.size())) {
	            	j = l.get(li).getName().compareToIgnoreCase(r.get(ri).getName());
	                if (j < 0) {
	                	a.set(i, l.get(li));
	                    i++;
	                    li++;
	                }
	                else {
	                	a.set(i,r.get(ri));
	                    i++;
	                    ri++;
	                }
	            }
	            else {
	                if (li >= l.size()) {
	                    while (ri < r.size()) {
	                    	a.set(i,r.get(ri));
	                        i++;
	                        ri++;
	                    }
	                }
	                if (ri >= r.size()) {
	                    while (li < l.size()) {
	                    	a.set(i, l.get(li));
	                        li++;
	                        i++;
	                    }
	                }
	            }
	        }
	 }
	 
	 
	public void sortStates(int left, int right) {
		mergeSort(states);
	}
	
	
	/*
	 * Quick Sort
	 * 
	 * Use a selection sort to order the ArrayList
	 * by the state's capital
	 */
	public void sortCapitals(ArrayList <State> a, int si, int ei) {
		//si = starting index, ei = ending index
		
	    if(ei<=si || si>=ei){}

	    else{ 
	    	State pivot = a.get(si); 
	        int i = si+1; State tmp; int k;

	         
	        for(int j = si+1; j<= ei; j++){
	        	k = pivot.getCapital().compareToIgnoreCase(a.get(j).getCapital());
	            if(k > 0){
	                tmp = a.get(j); 
	                a.set(j,a.get(i)); 
	                a.set(i, tmp);
	                i++; 
	            }
	        }

	        //put pivot in right position
	        a.set(si, a.get(i-1));
	        a.set(i-1, pivot);

	        
	        sortCapitals(a, si, i-2); 
	        sortCapitals(a, i, ei); 
	    }
	}
	
	
	
	
	

    public void menu()
	{
		
		boolean quitSelected = false;
		
		while (!quitSelected) {
			Scanner inKey = new Scanner(System.in);
			
			System.out.println("\n");
			System.out.println("=========================");
			System.out.println("          Menu");
			System.out.println("=========================");
			System.out.println("  1 - Sort by State");
			System.out.println("  2 - Sort by Capital");
			System.out.println("  3 - Exit");
			System.out.print("\n   Enter Selection: ");
			
			
			int choice = inKey.nextInt();
			
			
			switch (choice) {
			case (1):
	
				System.out.println("\n");
				System.out.println("=========================");
				System.out.println("  Sorted by State");
				System.out.println("=========================");
				sortStates(0, states.size() - 1);
				printStates();
				
				break;
			case (2):
	
				System.out.println("\n");
				System.out.println("=========================");
				System.out.println("  Sorted by Capital");
				System.out.println("=========================");
				sortCapitals(states, 0, states.size() - 1);
				printStates();	
				
				break;
			case (3):
				System.out.println("\n\nGoodbye!");
				quitSelected = true;
				break;
			default:
				System.out.println("\nNot a valid Choice:");
				
	
			}
		
		}
		

	}
	
	
	
	
	
	public void printStates()
	{
		for(State s : states)
		{
			System.out.printf("%-15s", s.getName());
			System.out.printf("%-15s", s.getCapital());
			System.out.printf("%-25s", s.getNickname());
			System.out.printf("%10s\n", s.getPopulation());	
		}
	}
	
	public void readFile()
	{
		Scanner scan = null;
		try
		{
			scan = new Scanner(new File("states.txt"));
		}
		catch(FileNotFoundException ex)
		{
			System.out.println("File not Found!");
		}
		
		String name;
		String capital;
		String nickname;
		int population;
		while(scan.hasNextLine())
		{
			name = scan.nextLine();
			capital = scan.nextLine();
			nickname = scan.nextLine();
			population = scan.nextInt();
			if(scan.hasNextLine())
			{
			   String dummy = scan.nextLine();	
			}
			  
			
			State state = new State(name, capital, nickname, population);
			states.add(state);
		}
		
		
		
	}
}