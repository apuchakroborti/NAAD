package StudentPasswordTimer;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.security.acl.Group;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Random;

import com.sun.istack.internal.logging.Logger;

import java.util.Timer;
import java.util.TimerTask;

public class Student {
	
	
	Student() throws FileNotFoundException{
	start();
	}
	
	public void start() throws FileNotFoundException{
		
		D ob_d=new D();
		//TimerTask ob_MyB = new MyB(ob_d);
		//TimerTask ob_student=new student(ob_d);
		//TimerTask group=new Group(ob_MyB,ob_student);
		
		MyB ob_MyB = new MyB(ob_d);
		student ob_student=new student(ob_d);
		Group group=new Group(ob_MyB,ob_student);
		
		while(true){
			
			  Timer timerGroup = new Timer(true);
		        timerGroup.scheduleAtFixedRate(group, 2*1000, 10*1000);
		        System.out.println("TimerTask started Group");
				
		        try{
		        	Thread.sleep(10*1000);
		        }catch(Exception e){
		        	
		        }
			
		Timer timerMyB = new Timer(true);
        timerMyB.scheduleAtFixedRate(ob_MyB, 2*1000, 10*1000);
        System.out.println("TimerTask started MyB");
        try{
        	Thread.sleep(30*1000);
        }catch(Exception e){
        	
        }
      
        
        Timer timerStudent = new Timer(true);
        timerStudent.scheduleAtFixedRate(ob_student, 2*1000, 10*1000);
        System.out.println("TimerTask started Student");
        try{
        	Thread.sleep(20*1000);
        }catch(Exception e){
        	
        }

        break;
     
		}
		
		
	}

	
	class MyB extends TimerTask{
		private int [] StudentID;
	    private String[] StudentName;
	    private int StudentIndex;
	    private D d;
	    MyB(D ob_d){
	    	StudentID= new int[500];
	        StudentName=new String[500];
	        StudentIndex=0;
	        d=ob_d;
	    }
	    
	    @Override
	    public void run() {
	    	
	    	try {
				Thread.sleep(20*1000);
				if(StudentIndex>0){   
				 for(int i=0;i<StudentIndex;i++){
					 Thread.sleep(50);
			    	   System.out.println(" B received the list below:\n "+StudentName[i]+" and ID: "+StudentID[i]);
			       }
				}
				d.putStudentList(StudentName, StudentID, StudentIndex);
				//Sending the list to D
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	       // long prevTime = System.currentTimeMillis()%10000;
	      //  System.out.println("end of MyB Thread  "+StudentIndex);
	    	
	       // System.out.println("Timer task started at:"+new Date());
	       // completeTask();
	       // System.out.println("Timer task finished at:"+new Date());
	    }
	    
	    
	 
	    public void AddToList(String Name,int ID ){
	    	for(int i=0;i<StudentIndex;i++){
	    		if(ID==StudentID[i]){
	    			System.out.println("Sorry You are enlisted and ID is "+StudentID[i]);
	    			return;
	    		}
	    	}
	    	// System.out.println("in the  MyB Thread  "+ID+"  "+StudentIndex);
	    	StudentID[StudentIndex]=ID;
	    	StudentName[StudentIndex]=Name;
	    	StudentIndex=StudentIndex+1;
	    }
	}
	
	class D{
		private int [] StudentID;
	    private String[] StudentName;
	    private String[] StudentPassword;
	    private int TotalStudent;
	    D(){
	    	StudentID= new int[500];
	        StudentName=new String[500];
	        StudentPassword=new String[500];
	        TotalStudent=0;
	    }
	 
	    public void putStudentList(String[] Name,int[] ID,int total_student ){
	    	for(int i=0;i<total_student;i++){
		long  time=System.currentTimeMillis()%10000;
		String Password=Name[i]+ID[i]+time;
		StudentName[i]=Name[i];
		StudentID[i]=ID[i];
		StudentPassword[i]=Password;
		TotalStudent=total_student;
	    	}
	    	//GivePassword();
	    	
	    }
	    public void GivePassword(){
	    	for(int i=0;i<TotalStudent;i++){
	    		System.out.println("Student Name: "+StudentName[i]+" Id: "+StudentID[i]+" Password: "+StudentPassword[i]);
	    	}
	    }
	    public void request(int ID)
	    {
	    	if(TotalStudent>0){
	    		boolean givePassword=true;
	    		for(int i=0;i<TotalStudent;i++){
	    			if(ID==StudentID[i]){
	    				System.out.println("Name :"+StudentName[i]+" ID:"+StudentID[i]+" Password:"+StudentPassword[i]);
	    				//
			    		String timeStamp = new SimpleDateFormat("yyyy MM dd_HH mm ss").format(Calendar.getInstance().getTime());

	    				System.out.println("Response Time Time: "+timeStamp );
	    			//
	    				givePassword=false;
	    			}
	    		}
	    		if(givePassword)System.out.println("ID:"+ID+" is not in the list.");
	    	}
	    	else{
	    		System.out.println("ID:"+ID+" is not in the list.");
	    	    	
	    	}
	    	}
	}
	
	class student extends TimerTask{
		private int [] StudentID;
		private int [] StudentStoredID;
	    //private String[] StudentName;
	    private int StudentIndex;
	    private D obD;
	    private Random rand;
	    student(D d){
	    	StudentID= new int[500];
	    	StudentStoredID=new int[500];
	        //StudentName=new String[500];
	        StudentIndex=0;
	        obD=d;
	        rand=new Random();
	    }
	    @Override
	    public void run(){
	    	try {
				Thread.sleep(30*1000);
				if(StudentIndex>0){
					for(int i=0;i<StudentIndex;i++){
			    	
			    	int id=rand.nextInt(StudentIndex);
			    	System.out.println("Request for Password ID No: "+StudentStoredID[id]);
			    	
			    	//int CurrentPassID=StudentID[id];
			    	if(id>=0 && id<StudentIndex && StudentID[id]!=0){ 		
			    		String timeStamp = new SimpleDateFormat("yyyy MM dd_HH mm ss").format(Calendar.getInstance().getTime());
			    		System.out.println("Requesting Time: "+timeStamp );
			    		//
			    		obD.request(StudentID[id]);
			    		StudentID[id]=0;
			    	}
			    	else{
			    		System.out.println("Password Already given ID No: "+StudentStoredID[id]);    		
			    	}
			    	}
			        System.out.println("Password given:");
				}
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }
	    public void setStudentId(int ID){
	    StudentID[StudentIndex]=ID;
	    StudentStoredID[StudentIndex]=ID;
	    StudentIndex=StudentIndex+1;
	    }
	}
	class Group extends TimerTask{
		private int [] StudentID;
	    private String[] StudentName;
	    public boolean ThreadStop;
	    private BufferedReader br;
	    //private MyTimer B;
	  // private B ob_b ;
	    //private TimerTask ob_MyB;
	    //private TimerTask ob_student;
	    
	    private MyB ob_MyB;
	    private student ob_student;
	    
	    
	    private boolean input;
	    Group(MyB B,student student_ob) throws FileNotFoundException{
	         StudentID= new int[500];
	         StudentName=new String[500];
	         br = new BufferedReader(new FileReader("StudentData.txt"));
	         input=true;
	         //ob_b=b;
	         ob_MyB=B;
	         ob_student=student_ob;
	    }	
		@Override
	    public void run() {	
			try {
	    		//String line;
	    		while(input)
	    		{
	    			String line=br.readLine();
	    			if(line==null){
	    				input=false;
	    				break;
	    			}
	    			else{
	    				try{
			//	String line = br.readLine();
				String name="";
				char teacher;
				int id=0;
				String strID="";
				teacher=line.charAt(line.length()-1);
				int i=0;
				for(i=0;i<line.length();i++){
					if(line.charAt(i)==' '){
						break;
					}
				name=name+line.charAt(i);
				}
				i++;//the next index of the space
				for(;i<line.length();i++){
					if(line.charAt(i)==' '){
						break;
					}
				strID=strID+line.charAt(i);
				}
				//System.out.println("Name of the student "+name+"  and ID: "+strID+"   "+teacher);//for checking
				id=Integer.parseInt(strID);
				//setting the student id into the student
				 ob_student.setStudentId(id);//change
				
				//A(name,id);//for checking
				if(teacher=='A'){
					A(name,id);
				}
				else if(teacher=='C'){
					C(name,id);
				}
				else if(teacher=='E'){
					E(name,id);
				}
				//line="";
	    		}catch(Exception e){
					//System.out.println("Cannot take Input from file due to other thread");
				}
	    			}
	    		}
				
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	//ob_b.print();
	       // System.out.println("Timer task started at:"+new Date());
	        //completeTask();
	        //System.out.println("Timer task finished at:"+new Date());
	    }
		 public void A(String Name,int ID)
		    {
		    	//System.out.println("Name of the student "+Name+"  and ID: "+ID);
		    	//ob_b.AddToList(Name,ID);
		    	ob_MyB.AddToList(Name,ID);
		        
		    }
		    public void C(String Name,int ID)
		    {
		    	//System.out.println("Name of the student "+Name+" and ID: "+ID);
		     	ob_MyB.AddToList(Name,ID);
		        
		    	// 	ob_b.AddToList(Name,ID);
		    }
		    public void E(String Name,int ID)
		    {
		    	//System.out.println("Name of the student "+Name+" and ID: "+ID);	
		    	//ob_b.AddToList(Name,ID);
		     	ob_MyB.AddToList(Name,ID);
		        
		    }

		
	}
	
	
	
}
	//
/*
 class B{
	private int [] StudentID;
    private String[] StudentName;
    private int StudentIndex;
    B(){
    	StudentID= new int[500];
        StudentName=new String[500];
        StudentIndex=0;
    }
    public void print(){
        long prevTime = System.currentTimeMillis()%10000;
        if(StudentIndex>0){
       for(int i=0;i<StudentIndex;i++){
    	   System.out.println("Student Name "+StudentName[i]+" and ID: "+StudentID[i]);
			
       }
        }
        System.out.println("end of B");
        
    }
    public void AddToList(String Name,int ID ){
    	for(int i=0;i<StudentIndex;i++){
    		if(ID==StudentID[i]){
    			System.out.println("Sorry You are enlisted and ID is "+StudentID[i]);
    			return;
    		}
    	}
    	StudentID[StudentIndex]=ID;
    	StudentName[StudentIndex]=Name;
    	StudentIndex++;
    }
    public void SendToD()
    {
    	
    }
}

 */

 
/*

 class MyTimer extends Thread{
	private int [] StudentID;
    private String[] StudentName;
    private int StudentIndex;
    MyTimer(){
    	StudentID= new int[500];
        StudentName=new String[500];
        StudentIndex=0;
    }
    public void run(){
        long prevTime = System.currentTimeMillis()%10000;
        if(StudentIndex>0){
       for(int i=0;i<StudentIndex;i++){
    	   System.out.println("Student Name "+StudentName[i]+" and ID: "+StudentID[i]);
			
       }
        }
        System.out.println("end of Thread");
    }
    public void AddToList(String Name,int ID ){
    	for(int i=0;i<StudentIndex;i++){
    		if(ID==StudentID[i]){
    			System.out.println("Sorry You are enlisted and ID is "+StudentID[i]);
    			return;
    		}
    	}
    	StudentID[StudentIndex]=ID;
    	StudentName[StudentIndex]=Name;
    	StudentIndex++;
    }
}
 

*/
	
	/*
		class Group extends Thread{
		private int [] StudentID;
	    private String[] StudentName;
	    public boolean ThreadStop;
	    private BufferedReader br;
	    //private MyTimer B;
	  // private B ob_b ;
	    private MyB ob_myb;
	    private Student student;
	    private boolean input;
	    Group(MyB b,Student student_ob) throws FileNotFoundException{
	         StudentID= new int[500];
	         StudentName=new String[500];
	         br = new BufferedReader(new FileReader("StudentData.txt"));
	         input=true;
	         //ob_b=b;
	         ob_myb=b;
	         student=student_ob;
	    }
	    public void run(){ 
	    	try {
	    		//String line;
	    		while(input)
	    		{
	    			String line=br.readLine();
	    			if(line==null){
	    				input=false;
	    				break;
	    			}
	    			else{
	    				try{
			//	String line = br.readLine();
				String name="";
				char teacher;
				int id=0;
				String strID="";
				
				teacher=line.charAt(line.length()-1);
				int i=0;
				for(i=0;i<line.length();i++){
					if(line.charAt(i)==' '){
						break;
					}
				name=name+line.charAt(i);
				}
				i++;//the next index of the space
				for(;i<line.length();i++){
					if(line.charAt(i)==' '){
						break;
					}
				strID=strID+line.charAt(i);
				}
				//System.out.println("Name of the student "+name+"  and ID: "+strID+"   "+teacher);//for checking
				id=Integer.parseInt(strID);
				//setting the student id into the student
				student.setStudentId(id);
				
				//A(name,id);//for checking
				if(teacher=='A'){
					A(name,id);
				}
				else if(teacher=='C'){
					C(name,id);
				}
				else if(teacher=='E'){
					E(name,id);
				}
				//line="";
	    		}catch(Exception e){
					//System.out.println("Cannot take Input from file due to other thread");
				}
	    			}
	    		}
				
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	//ob_b.print();
	    
	}
	    public void A(String Name,int ID)
	    {
	    	//System.out.println("Name of the student "+Name+"  and ID: "+ID);
	    	//ob_b.AddToList(Name,ID);
	    	ob_myb.AddToList(Name,ID);
	        
	    }
	    public void C(String Name,int ID)
	    {
	    	//System.out.println("Name of the student "+Name+" and ID: "+ID);
	     	ob_myb.AddToList(Name,ID);
	        
	    	// 	ob_b.AddToList(Name,ID);
	    }
	    public void E(String Name,int ID)
	    {
	    	//System.out.println("Name of the student "+Name+" and ID: "+ID);	
	    	//ob_b.AddToList(Name,ID);
	     	ob_myb.AddToList(Name,ID);
	        
	    }
	    
	}
	*/