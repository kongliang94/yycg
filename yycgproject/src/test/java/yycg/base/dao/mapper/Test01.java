package yycg.base.dao.mapper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javassist.expr.NewArray;

public class Test01 {

	public static void main(String[] args){
		new SStudent();
		
//		ThreadTest test=new ThreadTest();
//		test.start();
		
//		String string="qweq";
//		String string2=new String("qweq");
//		
//		System.out.println(string.hashCode());
//		System.out.println(string2.hashCode());
//		System.out.println(string.equals(string2));
//		List<String> strings=new ArrayList<String>();
//		strings.add(string);
//		
//		System.out.println(strings.contains(string2));
//		Student student=new Student("001");
//		Student student2=student;
//		List<Student> students=new ArrayList<Student>();
//		students.add(student);
//		System.out.println(student.equals(student2));
//		Map<Student, Object> map=new HashMap<Student, Object>();
//		map.put(student, new Object());
//		System.out.println("list是否包含"+students.contains(new Student("001")));
//		System.out.println("map是否包含"+map.containsKey(new Student("001")));
//		System.out.println(map.hashCode());
	}
}
class Student{
	public String stuId="11111";

	public Student(){
		tellId();
	}
	
	void tellId(){
		System.out.println("父类的"+stuId);
	}
}
class SStudent extends Student{
	private String stuId="22222";

	public SStudent(){
		tellId();
	}
	
	void tellId(){
		System.out.println("子类的"+stuId);
	}
}
class ThreadTest extends Thread{
	
	@Override
	public void start(){
		System.out.println("线程启动");
		try {
			sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//System.out.println("线程2");
	}
}

