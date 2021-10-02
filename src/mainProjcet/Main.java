package mainProjcet;

import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		try {
			String dbName = "myfirst.db";
			String tableName = "g_artists";
			int menu = -1;
			
			Scanner scan = new Scanner(System.in);
			TodoDB database = new TodoDB(dbName, tableName);
			System.out.println("1. 추가\n2. 조회\n3. 수정\n4. 삭제\n0. 종료");
			while(menu != 0) {
				System.out.print(" >>> ");
				menu = scan.nextInt();
				switch(menu) {
					case 0:
						break;
					case 1:
						database.createTodo();
						break;
					case 2:
						database.readTodo();
						break;
					case 3:
						database.updateTodo();
						break;
					case 4:
						database.deleteTodo();
						break;
				}
			}
			System.out.println("종료되었습니다.");
			scan.close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
