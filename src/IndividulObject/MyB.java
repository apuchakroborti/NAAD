package IndividulObject;
import java.util.TimerTask;
public class MyB extends TimerTask{
	private int [] StudentID;
	private int [] WholeStudentID;
    private String[] StudentName;
    private int StudentIndex;
    private int WholeStudentIndex;
    private D d;
    MyB(D ob_d){
    	StudentID= new int[500];
    	WholeStudentID= new int[500];
        StudentName=new String[500];
        StudentIndex=0;
        WholeStudentIndex=0;
        d=ob_d;
    }

	@Override
	public void run() {
		// TODO Auto-generated method stub
    	try {
			Thread.sleep(20*1000);
			if(StudentIndex>0){
				d.putStudentList(StudentName, StudentID, StudentIndex);
				for(int i=0;i<StudentIndex;i++){
					StudentName[i]="";
					StudentID[i]=-1;
				}
				StudentIndex=0;
				Thread.sleep(20*1000);
				/*
			 for(int i=0;i<StudentIndex;i++){	 
		    	   System.out.println(" B received the list below:\n "+StudentName[i]+" and ID: "+StudentID[i]);
		       }*/
			}
			else{
				   System.out.println("There is no student list that has come and Previous list is sent.\n ");
			}
			
			//Sending the list to D
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	 public void AddToList(String Name,int ID ){
	    	for(int i=0;i<WholeStudentIndex;i++){
	    		if(ID==WholeStudentID[i]){
	    			System.out.println("Sorry You are enlisted and ID is "+WholeStudentID[i]);
	    			return;
	    		}
	    	}
	    	//System.out.println("in the  MyB Thread  "+ID+"  "+StudentIndex);
	    	StudentID[StudentIndex]=ID;
	    	WholeStudentID[WholeStudentIndex]=ID;
	    	StudentName[StudentIndex]=Name;
	    	StudentIndex=StudentIndex+1;
	    	WholeStudentIndex=WholeStudentIndex+1;
	    	System.out.println("B Received :Name of the student "+Name+"  and ID: "+ID);
	    }

}
