import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
//!!!!!!!!!IMPORTANT: I ONLY USED ARRAYLISTS FOR INPUT FILE AND PRINTING ANSWERS, EVERYTHING WAS DONE WITH QUEUE LINKED LIST!! :D
public class Driver {
	//create to array to store customers that are read in from the file
	//create queue linked list to handle enqueue and dequeue and further calculations
	static ArrayList<Customer> C = new ArrayList<>();
	static Queue<Customer> queue = new Queue<>();
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		//create scanner/buffered reader and file object read in file
		Scanner input = new Scanner(new File(args[0]));
		
		FileReader fileReader = new FileReader(args[0]);
		BufferedReader bufferedReader = new BufferedReader(fileReader);
		//sorts values into customer objects
		String line;
		int t = Integer.parseInt(bufferedReader.readLine());
		while((line = bufferedReader.readLine()) != null) {
			String[] values = line.split(":");
			//System.out.println(values[0]);
			if(values[0].equals("ID-NUMBER")) {
				addC(C, values[1].replace(" ", ""));
				
			}
			else if (values[0].equals("ARRIVAL-TIME")){
				
				String [] timeofarrival = Arrays.copyOfRange(values, 1, values.length);
				for(int i=0;i<timeofarrival.length-1;i++) {
					timeofarrival[i] = timeofarrival[i].replaceAll(" ", "");
				}
				addCTOA(C, timeofarrival);
				
			}
			
 		}
		
		//calls methods that handle time conversion, queue, and printing the output
		
		time(C);
		que(C,queue,t);
		output(C,args[1]);
	}
	
	
	private static ArrayList<Customer> addCTOA(ArrayList<Customer> c, String[] string) {
		//converts time to seconds
		int toa = (Integer.parseInt(string[0])*3600)+ (Integer.parseInt(string[1])*60)+(Integer.parseInt(string[2]));
		c.get(c.size()-1).setToa(toa);
		return c;
		
		
	}
	public static ArrayList<Customer> time(ArrayList<Customer> c){
		//handles times greater in the P.M.
		for(int i =0; i<c.size();i++) {
			if(i>= 7) {
				c.get(i).setToa((c.get(i).getToa()+(12*3600)));
			}
		}
		return c;
	}
	public static ArrayList<Customer> addC(ArrayList<Customer> C, String a) {
		//adds customer to array
		int id = Integer.parseInt(a);
		Customer b = new Customer(id);
		C.add(b);
		return C;
	}
	//arraylist of values that need to be stored
	static ArrayList<Integer> values = new ArrayList<>();
	public static ArrayList<Integer> que(ArrayList<Customer> c, Queue<Customer> customers, int constant) {
		//all queue work is done here
		//initialize all values that need to be calculated or used
		int cTime = 3600*9;
		int maxLine =0;
		int breaktime =0;
		int i = 0;
		int idle =0;
		int serviced =0;
		
		//double while loop based on stored open and close time
		//increments break time 
		//increments max amount of people on line
		
		while(cTime < (17*3600 )&& (i < c.size() || customers.getSize()>0)) {
			
			while(c.get(i).getToa()<= cTime) {
				customers.enqueue(c.get(i));
				i+=1;
			}
			
			if(customers.getSize()>maxLine) {
				maxLine = customers.getSize();
			}
			
			if(customers.getSize()>0) {
				Customer remove = customers.dequeue();
				remove.setWait(cTime-remove.getToa());
				cTime += constant;
				serviced +=1;
			}
			
			else {
				idle += c.get(i).getToa() - cTime;
				if(c.get(i).getToa() - cTime>breaktime) {
					breaktime = c.get(i).getToa()  - cTime;
				}
				cTime = c.get(i).getToa();
			}
		}
		if(cTime< (17*3600)) {
			breaktime += (17*3600 - cTime);
		}
		
		values.add(maxLine);
		values.add(breaktime);
		values.add(idle);
		values.add(serviced);
		return values;
	}
	
	public static void output(ArrayList<Customer> c, String queries) throws FileNotFoundException {
		//output method that reads in queries and answers them accordingly 
		//also outputs in terminal and myoutput file 
		Scanner f = new Scanner(new File(queries));
		PrintWriter o = new PrintWriter(new File("myoutput.txt"));
		
		while(f.hasNextLine()) {
			String line = f.nextLine();
			if(line.equals("NUMBER-OF-CUSTOMERS-SERVED")) {
				System.out.println("NUMBER-OF-CUSTOMERS-SERVED: " + values.get(3));
				o.println("NUMBER-OF-CUSTOMERS-SERVED: " + values.get(3));
			}
			else if(line.equals("LONGEST-BREAK-LENGTH")) {
				System.out.println("LONGEST-BREAK-LENGTH: "+ values.get(1));
				o.println("LONGEST-BREAK-LENGTH: "+ values.get(1));
			}
			else if(line.equals("TOTAL-IDLE-TIME")) {
				System.out.println("TOTAL-IDLE-TIME: " + values.get(2));
				o.println("TOTAL-IDLE-TIME: " + values.get(2));
			}
			else if(line.equals("MAXIMUM-NUMBER-OF-PEOPLE-IN-QUEUE-AT-ANY-TIME")) {
				System.out.println("MAXIMUM-NUMBER-OF-PEOPLE-IN-QUEUE-AT-ANY-TIME: " + values.get(0) );
				o.println("MAXIMUM-NUMBER-OF-PEOPLE-IN-QUEUE-AT-ANY-TIME: " + values.get(0) );
			}
			else if(line.split(" ")[0].equals("WAITING-TIME-OF")) {
				int id = Integer.parseInt(line.split(" ")[1]);
				System.out.println("WAITING-TIME-OF " +  + c.get(id-1).getId() +": "+  c.get(id-1).getWait());
				o.println("WAITING-TIME-OF " +  + c.get(id-1).getId() +": "+  c.get(id-1).getWait());
			}
		
		}
		o.close();
	}
	
	
	

}
