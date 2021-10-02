package mainProjcet;

import java.util.Scanner;

public class Main {
	public static void main() {
		try {
			String dbName = "mySQLite.db";
			String tableName = "todolist";
			int menu = -1;
			
			Scanner scan = new Scanner(System.in);
			TodoDB database = new TodoDB(dbName, tableName);
			System.out.println("1. 추가\n2. 조회\n3. 수정\n4. 삭제\n 0. 종료");
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
			scan.close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
