package IndividulObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Random;
import java.util.TimerTask;



public class student extends TimerTask{
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
	public void run() {
		// TODO Auto-generated method stub
		System.out.println("Students are starting  to want Pasword:");
		
		try {
			//Thread.sleep(30*1000);
			if(StudentIndex>0){
				for(int i=0;i<StudentIndex;i++){
					Thread.sleep(30*1000);
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
		        //System.out.println("Password given:");
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
