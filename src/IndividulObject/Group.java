package IndividulObject;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.TimerTask;



public class Group extends TimerTask{
	private int [] StudentID;
    private String[] StudentName;
    public boolean ThreadStop;
    private BufferedReader br;
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
		// TODO Auto-generated method stub
		try {
    		while(input)
    		{
    			String line=br.readLine();
    			if(line==null){
    				input=false;
    				break;
    			}
    			else{
    				try{
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
