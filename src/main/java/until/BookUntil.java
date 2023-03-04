package until;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.tomcat.jdbc.pool.DataSource;

import entity.Book;

public class BookUntil {
	private DataSource dataSource;

	public BookUntil(DataSource theDataSource) {
		dataSource = theDataSource;
	}

	public void close(Connection myConn, Statement myStmt, ResultSet myRs) {
		try {
			if (myRs != null) {
				myRs.close();
			}
			if (myStmt != null) {
				myStmt.close();
			}
			if (myConn != null) {
				myConn.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<Book> getBooks() throws Exception {
		List<Book> books = new ArrayList<>();
		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRs = null;

		try {
			myConn = dataSource.getConnection();
			String sql = "select * from tb_Sach";
			myStmt = myConn.createStatement();
			myRs = myStmt.executeQuery(sql);
			while (myRs.next()) {
				int id = myRs.getInt("id");
				String name = myRs.getString("name");
				String nxb = myRs.getString("nxb");
				float pricce = myRs.getFloat("price");

				Book tempBook = new Book(id, name, nxb, pricce);
				books.add(tempBook);
			}

		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			close(myConn, myStmt, myRs);
		}
		return books;

	}

	public void addBook(Book theBook) throws Exception {
		Connection myConnection = null;
		PreparedStatement myStmt = null;
		try {
			myConnection = dataSource.getConnection();
			String sql = "insert into tb_Sach" + "(id,name,nxb,price)" + "values (?,?,?,?)";
			myStmt = myConnection.prepareStatement(sql);
			myStmt.setInt(1, theBook.getId());
			myStmt.setString(2, theBook.getName());
			myStmt.setString(3, theBook.getNxb());
			myStmt.setFloat(4, theBook.getPrice());
			myStmt.execute();
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			close(myConnection, myStmt, null);
		}

	}

	public void updateBook(Book theBook) throws Exception {
		Connection myConnection = null;
		PreparedStatement myStmt = null;
		try {
			myConnection = dataSource.getConnection();
			String sql = "update tb_Sach" + "set name=?,nxb=?,price=?" + "whereid=?";
			myStmt = myConnection.prepareStatement(sql);

			myStmt.setString(1, theBook.getName());
			myStmt.setString(2, theBook.getNxb());
			myStmt.setFloat(3, theBook.getPrice());
			myStmt.setInt(4, theBook.getId());
			myStmt.execute();
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			close(myConnection, myStmt, null);
		}

	}

	public Book getById(String id) throws Exception {
		Book theBook = null;
		Connection myConnection = null;
		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		try {
			int bookId = Integer.parseInt(id);
			myConnection = dataSource.getConnection();
			String sql = "select * from tb_Sach where id = ?";
			myStmt = myConnection.prepareStatement(sql);
			myStmt.setInt(1, bookId);
			myStmt.executeQuery();
			if (myRs.next()) {
				String name = myRs.getString("name");
				String nxb = myRs.getString("nxb");
				float price = myRs.getFloat("price");

				theBook = new Book(bookId, name, nxb, price);
			} else {
				throw new Exception("dell tìm thấy book id: " + bookId);
			}
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			close(myConnection, myStmt, null);
		}
		return theBook;

	}

	public void deleteBook(String idBook) throws Exception {
		Connection myConnection = null;
		PreparedStatement myStmt = null;
		try {
			int id = Integer.parseInt(idBook);
			myConnection = dataSource.getConnection();
			String sql = "delete from tb_Sach where id=?";
			myStmt = myConnection.prepareStatement(sql);
			myStmt.setInt(1, id);
			myStmt.execute();
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			close(myConnection, myStmt, null);
		}

	}

}
