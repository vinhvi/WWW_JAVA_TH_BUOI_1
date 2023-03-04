package controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.jdbc.pool.DataSource;

import entity.Book;
import until.BookUntil;

@WebServlet("/BookController")
public class BookControler extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private BookUntil bookUntil;

	@Resource(name = "jdbc/Sach")
	private DataSource dataSource;

	@Override
	public void init() throws ServletException {
		super.init();
		try {
			bookUntil = new BookUntil(dataSource);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void doGet(HttpServletRequest req, HttpServletResponse res) {
		try {
			String theCommand = req.getParameter("command");
			if (theCommand == null) {
				theCommand = "LIST";
			}
			switch (theCommand) {
			case "LIST":
				listBooks(req, res);
				break;
			case "ADD":
				addBook(req, res);
				break;
			case "LOAD":
				getBookId(req, res);
				break;
			case "UPDATE":
				updateBook(req, res);
				break;
			case "DELETE":
				deletBook(req, res);
				break;
			default:
				listBooks(req, res);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	private void listBooks(HttpServletRequest req, HttpServletResponse res) throws Exception {
		List<Book> books = bookUntil.getBooks();

		req.setAttribute("BOOK_LIST", books);

		RequestDispatcher dispatcher = req.getRequestDispatcher("/list-books.jsp");
		dispatcher.forward(req, res);
	}

	private void addBook(HttpServletRequest req, HttpServletResponse res) throws Exception {
		String id = req.getParameter("id");
		String name = req.getParameter("name");
		String nxb = req.getParameter("nxb");
		String price = req.getParameter("price");

		int bookId = Integer.parseInt(id);
		float gia = Float.parseFloat(price);

		Book book = new Book(bookId, name, nxb, gia);

		bookUntil.addBook(book);

		listBooks(req, res);
	}

	private void getBookId(HttpServletRequest req, HttpServletResponse res) throws Exception {
		String bookId = req.getParameter("id");
		Book theBook = bookUntil.getById(bookId);
		req.setAttribute("THE_BOOK", theBook);

		RequestDispatcher dispatcher = req.getRequestDispatcher("/update-book.jsp");
		dispatcher.forward(req, res);

	}

	private void updateBook(HttpServletRequest req, HttpServletResponse res) throws Exception {

		int id = Integer.parseInt(req.getParameter("id"));
		String name = req.getParameter("name");
		String nxb = req.getParameter("nxb");
		float price = Float.parseFloat(req.getParameter("price"));

		Book thebook = new Book(id, name, nxb, price);
		bookUntil.updateBook(thebook);
		;
		listBooks(req, res);
	}

	private void deletBook(HttpServletRequest req, HttpServletResponse res) throws Exception {
		String theBookId = req.getParameter("id");
		bookUntil.deleteBook(theBookId);
		listBooks(req, res);
	}
}
