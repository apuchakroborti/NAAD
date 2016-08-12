package IndividulObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class D {
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
 
    public void putStudentList(String[] Name,int[] ID,int chunkOfStudent ){
    	for(int i=0;i<chunkOfStudent;i++){
	long  time=System.currentTimeMillis()%10000;
	String Password=Name[i]+ID[i]+time+"cfkshfjksg";
	StudentName[i]=Name[i];
	StudentID[i]=ID[i];
	StudentPassword[i]=Password;
    	}
    	TotalStudent=TotalStudent+chunkOfStudent;
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
