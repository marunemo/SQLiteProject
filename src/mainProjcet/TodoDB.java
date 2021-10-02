package mainProjcet;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class TodoDB {
	private String dbFile;
	private String tableName;
	
	public TodoDB(String dbName, String tableName) throws ClassNotFoundException {
		Class.forName("org.sqlite.JDBC");
		
		this.dbFile = dbName;
		this.tableName = tableName;
	}
	
	public void createTodo() throws SQLException {
		Connection connect = DriverManager.getConnection("jdbc:sqlite:" + this.dbFile);
		Scanner scan = new Scanner(System.in);
		Statement stat = connect.createStatement();
		
		System.out.println("\n=== 데이터 추가 ===");
		System.out.print("제목 : ");
		String title = scan.nextLine();
		System.out.print("내용 : ");
		String desc = scan.nextLine();
		
		String createInsert = "insert into " + this.tableName + " (title, desc) values ('"
				+ title + "', '" + desc + "')";
		if(stat.executeUpdate(createInsert + ";") > 0)
			System.out.println("데이터가 추가되었습니다.");
		else
			System.err.println("데이터 추가에 실패했습니다!");
		
		stat.close();
		scan.close();
		connect.close();
	}
	
	public void readTodo() throws SQLException {
		Connection connect = DriverManager.getConnection("jdbc:sqlite:" + this.dbFile);
		Statement stat = connect.createStatement();
		
		System.out.println("\n=== 데이터 조회 ===");
		String readSelect = "select * from " + tableName;
		ResultSet result = stat.executeQuery(readSelect);
		while(result.next()) {
			String id = result.getString("id");
			String title = result.getString("title");
			String desc = result.getString("desc");
			System.out.println(id + ". " + title + " - " + desc);
		}
		stat.close();
		connect.close();
	}
	
	public void updateTodo() throws SQLException {
		Connection connect = DriverManager.getConnection("jdbc:sqlite:" + this.dbFile);
		Scanner scan = new Scanner(System.in);
		Statement stat = connect.createStatement();
		
		System.out.println("\n=== 데이터 수정 ===");
		System.out.print("수정할 제목 : ");
		String title = scan.nextLine();
		
		System.out.print("새 제목 : ");
		String newTitle = scan.nextLine();
		System.out.print("새 내용 : ");
		String newDesc = scan.nextLine();
		
		String updateUpdate = "update " + this.tableName + " set title = '" + newTitle
				+ "', desc = '" + newDesc + "' where title = " + title;
		if(stat.executeUpdate(updateUpdate + ";") > 0)
			System.out.println("데이터가 수정되었습니다.");
		else
			System.err.println("데이터 수정에 실패했습니다!");
		
		stat.close();
		scan.close();
		connect.close();
	}
	
	public void deleteTodo() throws SQLException {
		Connection connect = DriverManager.getConnection("jdbc:sqlite:" + this.dbFile);
		Scanner scan = new Scanner(System.in);
		Statement stat = connect.createStatement();
		
		System.out.println("\n=== 데이터 삭제 ===");
		System.out.print("삭제할 제목 : ");
		String title = scan.nextLine();
		
		String deleteDelete = "delete from " + this.tableName + " where title = " + title;
		if(stat.executeUpdate(deleteDelete + ";") > 0)
			System.out.println("데이터가 수정되었습니다.");
		else
			System.err.println("데이터 수정에 실패했습니다!");
		
		stat.close();
		scan.close();
		connect.close();
	}
}
