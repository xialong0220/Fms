package com.xialong;

import java.util.Scanner;

import javax.print.DocFlavor.STRING;

public class Fms {
	public void menu() {
		System.out.println("************************");
		System.out.println("*  1.查看所有粉丝信息                *");
		System.out.println("*  2.添加粉丝                               *");
		System.out.println("*  3.删除粉丝信息                        *");
		System.out.println("*  4.查询粉丝信息                        *");
		System.out.println("*  5.修改粉丝信息                        *");
		System.out.println("*  help.帮助                                *");
		System.out.println("*  exit.退出                                *");
		System.out.println("************************");
	}

	// 创建数组，存放粉丝信息
	private Fans[] fans = new Fans[3];
	// 记录数组索引
	private int index = 0;

	// 添加粉丝
	public void add(Fans fan) {
		if (index >= fans.length) {
			Fans[] demo = new Fans[fans.length + 3];
			System.arraycopy(fans, 0, demo, 0, fans.length);
			fans = demo;
		}
		fans[index++] = fan;
	}

	// 查看所有粉丝信息
	public Fans[] queryAll() {
		Fans[] demo = new Fans[index];
		System.arraycopy(fans, 0, demo, 0, index);
		return demo;
	}

	// 通过id查找该粉丝所在的位置
	private int queryIndexById(long id) {
		int fanIndex = -1;
		for (int i = 0; i <= index; i++) {
			if (fans[i].getId() == id) {
				fanIndex = i;
				break;
			}
		}
		return fanIndex;
	}

	// 通过id查询粉丝信息
	public Fans queryById(long id) {
		int fanIndex = queryIndexById(id);
		return fanIndex == -1 ? null : fans[fanIndex];
	}

	// 通过id删除粉丝信息
	public void deleteById(long id) {
		int fanIndex = queryIndexById(id);
		if (fanIndex != -1) {
			for (int i = fanIndex; i < index - 1; i++) {
				fans[i] = fans[i + 1];
			}
			fans[--index] = null;
		}
	}

	// 修改粉丝信息
	public void update(Fans fan) {
		for (int i = 0; i < index; i++) {
			if (fan.getId() == fans[i].getId()) {
				fans[i].setName(fan.getName());
				fans[i].setAge(fan.getAge());
			}
		}
	}

	public static void main(String[] args) {
		Fms fms = new Fms();
		System.out.println("请登录：");
		Scanner scanner = new Scanner(System.in);
		while(true){
			System.out.println("用户名：");
			String userName = scanner.nextLine();
			System.out.println("密    码：");
			String userPwd = scanner.nextLine();
			if(userName.equals("admin")&&userPwd.equals("BoYoung")){
				break;
			}
			else{
				System.out.println("用户名或密码错误，请重新输入！");
			}
		}
		System.out.println("欢迎来到朴宝英粉丝团！");
		fms.menu();
		while (true) {
			System.out.println("请输入功能编号：");
			String option = scanner.nextLine();
			switch (option) {
			case "1":
				System.out.println("以下是所有粉丝信息：");
				System.out.println("NO.\t姓名\t年龄\t性别\t头衔");
				Fans[] fans = fms.queryAll();
				for (Fans fan : fans) {
					System.out.println(fan);
				}
				System.out.println("共计" + fans.length + "人");
				break;
				case "2"://添加
					while(true){
						System.out.println("请输入粉丝信息【id name age sex rank】或者输入back回到上一级目录");
						String fanStr = scanner.nextLine();
						if(fanStr.equals("back")){
							break;
						}
						String[] fanArr = fanStr.split(" ");
						long id = Long.parseLong(fanArr[0]);
						String name = fanArr[1];
						int age = Integer.parseInt(fanArr[2]);
						String sex = fanArr[3];
						String rank = fanArr[4];
						//封装对象
						Fans fan = new Fans(id,name,age,sex,rank);
						fms.add(fan);
						System.out.println("添加成功！");
					}
					break;
				case "3"://删除
					while(true){
						System.out.print("请输入您要删除粉丝的id或back返回上一级目录:");
						String id = scanner.nextLine();
						if(id.equals("back")){
							break;
						}
					    fms.deleteById(Long.parseLong(id));
						System.out.println("删除成功！粉丝个数为："+fms.index);
					}
					break;
				case "4"://查询
					while(true){
						System.out.print("请输入您要查询粉丝的id或back返回上一级目录:");
						String id = scanner.nextLine();
						if(id.equals("back")){
							break;
						}
						Fans fan = fms.queryById(Long.parseLong(id));
						System.out.println("以下是您要查找的粉丝的信息：");
						System.out.println("NO.\t姓名\t年龄\t性别\t头衔");
						System.out.println(fan!=null?fan:"not found!");
					}
					break;
                case "5"://修改
					while(true){
						System.out.print("请输入您要修改粉丝的id或back返回上一级目录:");
						String id = scanner.nextLine();
						if(id.equals("back")){
							break;
						}
						Fans fan = fms.queryById(Long.parseLong(id));
						if(fan == null){
							System.out.println("该粉丝不存在！");
							continue;
						}
						System.out.println("原信息为：");
						System.out.println("NO.\t姓名\t年龄\t性别\t头衔");
						System.out.println(fan);
						System.out.println("请输入您要修改的信息【name age 性别 头衔】");
						String fanStr = scanner.nextLine();
						String[] fanArr = fanStr.split(" ");
						String name = fanArr[0];
						int age = Integer.parseInt(fanArr[1]);
						String sex = fanArr[2];
						String rank = fanArr[3];
						Fans newfan = new Fans(Long.parseLong(id),name,age,sex,rank);

						fms.update(newfan);
						System.out.println("修改成功！");
					}
					break; 
                case "help":
					fms.menu();
					break;
				case "exit":
					System.out.println("bye bye");
					System.exit(0);
				default:
					System.out.println("输入出错，请重新输入！");
			}
		}
	}

}
