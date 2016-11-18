import java.util.Scanner;
import java.io.File;

public class RatingManager {
	private static LinkedList<Item> items;
	private static LinkedList<User> users;
	
	// Constructor
	public RatingManager(){
		items=new LinkedList<Item>();
		users=new LinkedList<User>();
	}
	
	// Read ratings from a file and create a RatingManager object that stores these ratings
	public static RatingManager read(String fileName){
		RatingManager rm=new RatingManager();
		
		Scanner x = null;
		//opening the file
		try{
			x=new Scanner(new File(fileName));
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		//reading the file
		String userid="";
		String itemid="";
		String rating="";
	
		while(x.hasNext()){
			//String.format("%s", x.next()) << return a String 
			userid=String.format("%s", x.next());
			int ui=Integer.parseInt(userid);
			
			itemid=String.format("%s",x.next());
			int id=Integer.parseInt(itemid);
			
			rating=String.format("%s", x.next());
			int ra=Integer.parseInt(rating);
			
			x.next();//to skip the time
			
			Rating r=new Rating(ui,id,ra);
			
			//Searching for the user
			User uu=searchUsers(ui,rm);
			//If there isn't a user with this id we create a new one
			if(uu==null){
				uu=new User();
				uu.id=ui;
				uu.numRat=1;
				uu.ratSum=ra;
				uu.ratAvg=ra;
				uu.userRat.insert(r);
				rm.users.insert(uu);
			}else
			{
				uu.numRat++;
				uu.ratSum+=ra;
				uu.ratAvg=uu.ratSum/uu.numRat;
				uu.userRat.insert(r);
			}
			
			
			//searching for the item
			Item ii=searchItems(id,rm);
			//If there isn't an item with this id we create a new one
			if(ii==null){
				ii=new Item();
				ii.id=ui;
				ii.ratNum=1;
				ii.ratSum=ra;
				ii.ratAvg=ra;
				ii.itemRat.insert(r);
				rm.items.insert(ii);
			}else
			{
				ii.ratNum++;
				ii.ratSum+=ra;
				ii.ratAvg=uu.ratSum/uu.numRat;
				ii.itemRat.insert(r);
			}
			
			
			
		}//end of loop
		
		//closing the file
		x.close();
		
		return rm;
	}
	 
	// Add a rating
	public void addRating(Rating rating){
		//searching for the user then storing the rating into it
		User uu=searchUsers(rating.getUserId());
			uu.numRat++;
			uu.ratSum+=rating.getValue();
			uu.ratAvg=uu.ratSum/uu.numRat;
			uu.userRat.insert(rating);
		//searching for the item then storing the rating into it
		Item ii=searchItems(rating.getItemId());
			ii.ratNum++;
			ii.ratSum+=rating.getValue();
			ii.ratAvg=uu.ratSum/uu.numRat;
			ii.itemRat.insert(rating);
		
	}
	
	// Return all ratings given by user i. Search should be efficient. 
	public LinkedList<Rating> getUserRatings(int i){
		User u=searchUsers(i);
		if(u==null)
			return null;
		return u.userRat;
	}
	
	// Return all ratings given to item j. Search should be efficient.
	public LinkedList<Rating> getItemRatings(int j){
		Item itm=searchItems(j);
		if(itm==null)
			return null;
		return itm.itemRat;
	}
	
	// Return the average rating of item j. If i has no ratings, -1 is returned
	public double getAverageItemRating(int j){
		Item itm=searchItems(j);
		if(itm==null)
			return -1;
		
		return itm.ratAvg;
		
	}
	
	// Return the average rating given by user i. If i has no ratings, -1 is returned
	public double getAverageUserRating(int i){
		User u=searchUsers(i);
		if(u==null)
			return -1;
		return u.ratAvg;
	}
	
	// Return the list of all items having the highest average rating (for example if the highest average rating is 4.9, the method should return all items with average rating 4.9)
	public LinkedList<Integer> getHighestRatedItems(){
		LinkedList<Integer> l1=new LinkedList<Integer>();
		double x=-1;
		while(!items.last()){
			if(items.retrieve().ratAvg>x)
				x=items.retrieve().ratAvg;
		}
		if(items.retrieve().ratAvg>x)
			x=items.retrieve().ratAvg;
		
		while(!items.last()){
			if(items.retrieve().ratAvg==x)
				l1.insert(items.retrieve().id);
		}
		if(items.retrieve().ratAvg==x)
			l1.insert(items.retrieve().id);
		
		return l1;
	}

	//*****************************************Private Methods*****************************************************
	private Item searchItems(int id){
		if(items.empty())
			return null;
		items.findFirst();
		while(!items.last()){
			if(items.retrieve().id==id)
				return items.retrieve();
			items.findNext();
		}
		if(items.retrieve().id==id)
			return items.retrieve();
		
		return null;
	}
	private static Item searchItems(int id,RatingManager ra){
		if(ra.items.empty())
			return null;
		ra.items.findFirst();
		while(!ra.items.last()){
			if(ra.items.retrieve().id==id)
				return ra.items.retrieve();
			ra.items.findNext();
		}
		if(ra.items.retrieve().id==id)
			return ra.items.retrieve();
		
		return null;
	}
	
	private User searchUsers(int id){
		if(users.empty())
			return null;
		users.findFirst();
		while(!users.last()){
			if(users.retrieve().id==id)
				return users.retrieve();
			users.findNext();
		}
		if(users.retrieve().id==id)
			return users.retrieve();
		
		return null;
	}
	private static User searchUsers(int id,RatingManager ra){
		if(ra.users.empty())
			return null;
		ra.users.findFirst();
		while(!ra.users.last()){
			if(ra.users.retrieve().id==id)
				return ra.users.retrieve();
			ra.users.findNext();
		}
		if(ra.users.retrieve().id==id)
			return ra.users.retrieve();
		
		return null;
	}
}
