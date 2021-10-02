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
	private Scanner scan = new Scanner(System.in);
	
	public TodoDB(String dbName, String tableName) throws ClassNotFoundException {
		Class.forName("org.sqlite.JDBC");
		
		this.dbFile = dbName;
		this.tableName = tableName;
	}
	
	public void createTodo() throws SQLException {
		Connection connect = DriverManager.getConnection("jdbc:sqlite:" + this.dbFile);
		Statement stat = connect.createStatement();
		
		System.out.println("\n=== 데이터 추가 ===");
		System.out.print("가수명 : ");
		String name = scan.nextLine();
		System.out.print("활동유형 : ");
		String a_type = scan.nextLine();
		System.out.print("활동연대 : ");
		String a_year = scan.nextLine();
		System.out.print("데뷔 : ");
		String debut = scan.nextLine();
		
		String createInsert = "insert into " + this.tableName + " (name, a_type, a_year, debut, regdate)"
				+ "values ('" + name + "', '" + a_type + "', '" + a_year + "', '" + debut + "', datetime('now', 'localtime'));";
		if(stat.executeUpdate(createInsert) > 0)
			System.out.println("데이터가 추가되었습니다.");
		else
			System.err.println("데이터 추가에 실패했습니다!");
		
		stat.close();
		connect.close();
	}
	
	public void readTodo() throws SQLException {
		Connection connect = DriverManager.getConnection("jdbc:sqlite:" + this.dbFile);
		Statement stat = connect.createStatement();
		
		System.out.println("\n=== 데이터 조회 ===");
		String readSelect = "select * from " + tableName;
		ResultSet result = stat.executeQuery(readSelect);
		if(result.isBeforeFirst())
			System.out.println(String.format("No %-10s %-7s %-7s %-7s %s", "이름", "활동유형", "활동연대", "데뷔", "저장날짜"));
		while(result.next()) {
			String id = result.getString("id");
			String name = result.getString("name");
			String a_type = result.getString("a_type");
			String a_year = result.getString("a_year");
			String debut = result.getString("debut");
			String regdate = result.getString("regdate");
			System.out.println(String.format("%2s %-10s %-7s %-7s %-7s %s", id, name, a_type, a_year, debut, regdate));
		}
		stat.close();
		connect.close();
	}
	
	public void updateTodo() throws SQLException {
		Connection connect = DriverManager.getConnection("jdbc:sqlite:" + this.dbFile);
		Statement stat = connect.createStatement();
		
		System.out.println("\n=== 데이터 수정 ===");
		System.out.print("수정할 가수 이름 : ");
		String target = scan.nextLine();
		
		System.out.print("가수명 : ");
		String name = scan.nextLine();
		System.out.print("활동유형 : ");
		String a_type = scan.nextLine();
		System.out.print("활동연대 : ");
		String a_year = scan.nextLine();
		System.out.print("데뷔 : ");
		String debut = scan.nextLine();
		
		String updateUpdate = "update " + this.tableName
				+ " set name = '" + name + "', a_type = '" + a_type
				+ "', a_year = '" + a_year + "', debut = '" + debut
				+ "', regdate = datetime('now', 'localtime') where name = '" + target + "';";
		if(stat.executeUpdate(updateUpdate) > 0)
			System.out.println("데이터가 수정되었습니다.");
		else
			System.err.println("데이터 수정에 실패했습니다!");
		
		stat.close();
		connect.close();
	}
	
	public void deleteTodo() throws SQLException {
		Connection connect = DriverManager.getConnection("jdbc:sqlite:" + this.dbFile);
		Statement stat = connect.createStatement();
		
		System.out.println("\n=== 데이터 삭제 ===");
		System.out.print("삭제할 가수 이름 : ");
		String name = scan.nextLine();
		
		String deleteDelete = "delete from " + this.tableName + " where name = '" + name + "';";
		if(stat.executeUpdate(deleteDelete) > 0)
			System.out.println("데이터가 수정되었습니다.");
		else
			System.err.println("데이터 수정에 실패했습니다!");
		
		stat.close();
		connect.close();
	}
}
