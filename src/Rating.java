class Item{
	public int id;
	public int ratNum;
	public double ratSum;
	public double ratAvg;
	public LinkedList<Rating> itemRat; 
	
	public Item(){
		id=0;
		ratNum=0;
		ratSum=0;
		ratAvg=-1;
		itemRat=new LinkedList<Rating>();
	}
}

class User{
	public int id;
	public int numRat;
	public double ratSum;
	public double ratAvg;
	public LinkedList<Rating> userRat; 
	
	public User(){
		id=0;
		numRat=0;
		ratSum=0;
		ratAvg=-1;
		userRat=new LinkedList<Rating>();
	}
}

public class Rating {
	private int userId;
	private int itemId;
	private int value; // The value of the rating
	
	// Constructor
	public Rating(int userId, int itemId, int value){
		this.userId=userId;
		this.itemId=itemId;
		this.value=value;
	}

	public int getUserId() {
		return userId;
	}

	public int getItemId() {
		return itemId;
	}

	public int getValue() {
		return value;
	}
	
	
	// Getters... (No setters. This class is immutable)
}

