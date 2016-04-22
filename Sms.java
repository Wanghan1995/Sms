package com.wang.han;

import java.util.Scanner;

public class Sms{
	private Student[] stus = new Student[3];
	private int index;
	public void save(Student student){
		if(index >=stus.length){
			Student[] demo = new Student[stus.length+3];
			System.arraycopy(stus,0,demo,0,index);
			stus = demo;
		}
		stus[index++]=student;
	}
	public void update(Student student){
		for(int i= 0;i<index;i++){
			if(student.getId()==stus[i].getId()){
				stus[i].setName(student.getName());
				stus[i].setAge(student.getAge());
			}
		}
	}
	public  void deleteById(long id){
		int num = getIndexById(id);
		
			for(int i=num;i<index-1;i++){
				stus[i]=stus[i+1];
			}
			stus[--index]=null;
			
		}


	public Student[] queryAll(){
		Student[] demo = new Student[index];
		System.arraycopy(stus,0,demo,0,index);
		return demo;
	
	}

	public Student queryById(long id){
		int num = getIndexById(id);	
		return num==-1?null:stus[num];	
	}

	private int getIndexById(long id){
		int num =-1;
		for(int i =0;i<index;i++){
			if(stus[i].getId()==id){
				num=i;
				break;
			}
		}
		return num;
	}
	public void menu(){
		
		System.out.println("*******ѧ����Ϣ����ϵͳ******");
		System.out.println("*1)��������ѧ����Ϣ");
		System.out.println("*2)¼��ѧ����Ϣ");
		System.out.println("*3)ɾ��ѧ����Ϣ");
		System.out.println("*4)ͨ��id����ѧ����Ϣ");
		System.out.println("*5)�޸�ѧ����Ϣ");
		System.out.println("*exit  �˳�ѧ������ϵͳ��");
		System.out.println("*help  ��ȡ����");
		System.out.println("*****************************");
		System.out.println("�������Ӧָ�");
	}


	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
		Sms sms = new Sms();
		sms.menu();
		while(true){
			
			System.out.print("*�������Ӧָ��:");
			String option = sc.nextLine();
			switch(option){
				case "1":
					System.out.println("����������ѧ������Ϣ��");
					Student[] stus = sms.queryAll();
					for(Student stu: stus){
						System.out.println(stu);
					}
					System.out.println("�ܹ���ѯ��"+sms.index+"��ѧ��");
					break;
				case "2":
					while(true){
						System.out.println("������ѧ������Ϣ��id#name#age���������롾break��������һ��Ŀ¼");
						String  stuStr = sc.nextLine();
						
						if(stuStr.equals("break")){
							break;
						}
						String[] stuArr = stuStr.split("#");
						long id = Long.parseLong(stuArr[0]);
						String name  = stuArr[1];
						int age = Integer.parseInt(stuArr[2]);
						Student stu =  new Student(id,name,age);

						sms.save(stu);
						System.out.println("����ɹ�");
					}
					break;
				case "3":
					while(true){
						System.out.println("������Ҫɾ����ѧ����id��������break������һ��Ŀ¼");
						String idStr = sc.nextLine();
						if(idStr.equals("break")){
							break;
						}
						long id =  Long.parseLong(idStr);
						Student stu = sms.queryById(id);
						if(stu==null){
							System.out.println("��Ҫɾ�����û������ڣ�");
							continue;
						}
						sms.deleteById(id);
						System.out.println("ɾ���ɹ���");
					}
					break;
				case "4":
					while(true){
						System.out.println("������Ҫ��ѯѧ����id��������break������һ��Ŀ¼");
						String idStr = sc.nextLine();
						if(idStr.equals("break")){
							break;
						}
						long id =  Long.parseLong(idStr);
						Student stu = sms.queryById(id);
						System.out.println(stu==null?"sorry,not found!":"������ѧ����Ϣ\n"+stu);
					}
					break;
				case "5":
					while(true){
						System.out.println("������Ҫ�޸ĵ�ѧ����id��������break������һ��Ŀ¼");
						String idStr = sc.nextLine();
						if(idStr.equals("break")){
							break;
						}
						long id =  Long.parseLong(idStr);
						Student stu = sms.queryById(id);
						if(stu==null){
							System.out.println("��Ҫ�޸ĵ��û������ڣ�");
							continue;
						}
						System.out.println("ԭ��Ϣ��"+stu);
						System.out.println("����������Ϣ��name#age��:");
						String str = sc.nextLine();
						String[] stuArr= str.split("#");
						String name = stuArr[0];
						int age = Integer.parseInt(stuArr[1]);
						Student newStu = new Student(id,name,age);
						sms.update(newStu);
						System.out.println("�޸ĳɹ���");
					}
					break;
				case "exit":
					System.out.println("��ӭ�´�ʹ�ã�");
					System.exit(0);
					
					break;
				case "help":
					sms.menu();
					break;
				default:
			}
		}
	}		
}