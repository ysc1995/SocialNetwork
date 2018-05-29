

import java.util.ArrayList;

public class User {
	private String name;
	private ArrayList<User> friends;
	private int dis;

	public User(String name) {
		this(name, new ArrayList<User>());
	}

	public User(String name, ArrayList<User> friends) {
		this.name = name;
		this.friends = friends;
	}

	public String getName() {
		return name;
	}

	public ArrayList<User> getFriends() {
		return friends;
	}

	public void addFriends(User friend) {
		friends.add(friend);
	}

	public boolean removeFriend(User friend) {
		for (int i = 0; i < friends.size(); i++) {
			if (friends.get(i).equals(friend)) {
				friends.remove(i);
			}
		}
		return true;
	}

	public boolean equals(User u) {
		return (name.equals(u.getName())
			&& friends.size() == u.getFriends().size());
	}

	public void output() {
		System.out.println("User: " + name);
		System.out.print("Friends: ");
		for (User u : friends) {
			System.out.print(u.getName() + " ");
		}
		System.out.println(" ");
	}

	public void setDis(int i) {
		dis = i;
	}

	public int getDis() {
		return dis;
	}
}