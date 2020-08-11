//customer class for customer objects
public class Customer {
	//time of arrival 
	private int toa;
	private int id;
	private int tol;
	//wait is the wait time of the customer
	private int wait;
	private int tos;
	//constructor
	public Customer(int id) {
		this.id = id;
	}
	//general getters and setters
	public int getToa() {
		return toa;
	}
	public void setToa(int toa) {
		this.toa = toa;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getWait() {
		return wait;
	}
	public void setWait(int totaltime) {
		this.wait = totaltime;
	}
	public int getTol() {
		return tol;
	}
	public void setTol(int tol) {
		this.tol = tol;
	}
	public int getTos() {
		return tos;
	}
	public void setTos(int tos) {
		this.tos = tos;
	}
	
	
}
