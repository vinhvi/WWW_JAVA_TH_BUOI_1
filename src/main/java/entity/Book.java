package entity;

public class Book {
	private int id;
	private String name;
	private String nxb;
	private float price;

	public Book() {
		super();
	}

	public Book(int id, String name, String nxb, float price) {
		super();
		this.id = id;
		this.name = name;
		this.nxb = nxb;
		this.price = price;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNxb() {
		return nxb;
	}

	public void setNxb(String nxb) {
		this.nxb = nxb;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "Book [id=" + id + ", name=" + name + ", nxb=" + nxb + ", price=" + price + "]";
	}

}
