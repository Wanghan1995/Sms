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
		
		System.out.println("*******学生信息管理系统******");
		System.out.println("*1)查找所有学生信息");
		System.out.println("*2)录入学生信息");
		System.out.println("*3)删除学生信息");
		System.out.println("*4)通过id查找学生信息");
		System.out.println("*5)修改学生信息");
		System.out.println("*exit  退出学生管理系统！");
		System.out.println("*help  获取帮助");
		System.out.println("*****************************");
		System.out.println("请输入对应指令！");
	}


	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
		Sms sms = new Sms();
		sms.menu();
		while(true){
			
			System.out.print("*请输入对应指令:");
			String option = sc.nextLine();
			switch(option){
				case "1":
					System.out.println("以下是所有学生的信息：");
					Student[] stus = sms.queryAll();
					for(Student stu: stus){
						System.out.println(stu);
					}
					System.out.println("总共查询到"+sms.index+"个学生");
					break;
				case "2":
					while(true){
						System.out.println("请输入学生的信息【id#name#age】或者输入【break】返回上一级目录");
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
						System.out.println("保存成功");
					}
					break;
				case "3":
					while(true){
						System.out.println("请输入要删除的学生的id或者输入break返回上一级目录");
						String idStr = sc.nextLine();
						if(idStr.equals("break")){
							break;
						}
						long id =  Long.parseLong(idStr);
						Student stu = sms.queryById(id);
						if(stu==null){
							System.out.println("您要删除的用户不存在！");
							continue;
						}
						sms.deleteById(id);
						System.out.println("删除成功！");
					}
					break;
				case "4":
					while(true){
						System.out.println("请输入要查询学生的id或者输入break返回上一级目录");
						String idStr = sc.nextLine();
						if(idStr.equals("break")){
							break;
						}
						long id =  Long.parseLong(idStr);
						Student stu = sms.queryById(id);
						System.out.println(stu==null?"sorry,not found!":"以下是学生信息\n"+stu);
					}
					break;
				case "5":
					while(true){
						System.out.println("请输入要修改的学生的id或者输入break返回上一级目录");
						String idStr = sc.nextLine();
						if(idStr.equals("break")){
							break;
						}
						long id =  Long.parseLong(idStr);
						Student stu = sms.queryById(id);
						if(stu==null){
							System.out.println("您要修改的用户不存在！");
							continue;
						}
						System.out.println("原信息："+stu);
						System.out.println("请输入新信息【name#age】:");
						String str = sc.nextLine();
						String[] stuArr= str.split("#");
						String name = stuArr[0];
						int age = Integer.parseInt(stuArr[1]);
						Student newStu = new Student(id,name,age);
						sms.update(newStu);
						System.out.println("修改成功！");
					}
					break;
				case "exit":
					System.out.println("欢迎下次使用！");
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