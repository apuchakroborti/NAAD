package IndividulObject;

import java.io.FileNotFoundException;
import java.util.Timer;



public class StudentMain1 {

	public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub
		D ob_d=new D();
		//TimerTask ob_MyB = new MyB(ob_d);
		//TimerTask ob_student=new student(ob_d);
		//TimerTask group=new Group(ob_MyB,ob_student);
		
		MyB ob_MyB = new MyB(ob_d);
		student ob_student=new student(ob_d);
		Group group=new Group(ob_MyB,ob_student);
		
		while(true){		
			  Timer timerGroup = new Timer(true);
		        timerGroup.scheduleAtFixedRate(group,0, 10*1000);
		        System.out.println("TimerTask started Group");
		        try{
		        	Thread.sleep(30*1000);
		        }catch(Exception e){
		        }
		Timer timerMyB = new Timer(true);
        timerMyB.scheduleAtFixedRate(ob_MyB,0, 10*1000);
        System.out.println("TimerTask started MyB");
        try{
        	Thread.sleep(30*1000);
        }catch(Exception e){
        	
        }
        Timer timerStudent = new Timer(true);
        timerStudent.scheduleAtFixedRate(ob_student,0, 10*1000);
        System.out.println("TimerTask started Student");
        try{
        	Thread.sleep(20*1000);
        }catch(Exception e){	
        }
        break;
		}
	}

}
