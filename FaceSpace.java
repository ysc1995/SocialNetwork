

import java.util.LinkedList;
import java.util.HashSet;

public class FaceSpace {
	UserTable table = new UserTable();

	/**
     * Adds/updates a user profile with a given name to the network.
     * @param user the user to be added
     */
    public void add(String user) {
        
        if (!table.contains(user)) {
            table.add(new User(user));
        }
        else{
        	System.out.println(user + " is already added.");
        }
    }

    /**
     * Deletes a user profile with a given name from the network.
     * The user is also removed from their list of friends.
     * @param user the user to be deleted
     */
    public void delete(String user) {
        if (table.contains(user)) {
            User u = table.get(user);
            for (User friend : u.getFriends()) {
                friend.removeFriend(u);
            }
            table.remove(user);
        }
    }

    /**
     * Retrieves the user profile with a given name and displays all its
     * information
     * @param user the user serached for
     */
    public void search(String user) {
        User userNode = table.get(user);
        if (userNode != null) {
            table.get(user).output();
        }
    }

    /**
     * Adds a profile with a given name to the list of friends of the
     * given user. And friendships are reciprocal.
     * @param user   the user that will add a friend
     * @param friend the friend that will be added to the user
     */
    public void addFriend(String user, String friend) {
        //only users present in the network can be added as friends.
        User userNode = table.get(user);
        User friendNode = table.get(friend);
        //when both user present in the network, but friend does not
        if (userNode != null && friendNode == null) {
            User add = new User(friend);
            add.addFriends(userNode);
            userNode.addFriends(add);
            table.add(add);
        } else if (userNode != null && friendNode != null) {
            userNode.addFriends(friendNode);
            friendNode.addFriends(userNode);
        }
    }

    /**
     * Removes a profile with a given name from the list of friends of
     * the given user. Also reciprocal.
     * @param user   the user that will delete a friend from its list
     * @param friend the friend that will be deleted
     */
    public void removeFriend(String user, String friend) {
        User u = table.get(user);
        for (User v : u.getFriends()) {
            if (v.getName().equals(friend)) {
                v.removeFriend(u);
                u.removeFriend(v);
                return;
            }
        }
    }

    /**
     * Computes the shortest path between two given users.
     * @param  user   one user
     * @param  friend the given user's friend
     * @return        the shortest path between them
     * 				  -1 means no path
     */
    public int findShortestPath(String user, String friend) {
    	if(table.get(user)==null||table.get(friend)==null){
    		return -1;
    	}
        HashSet<String> visitHash = new HashSet<>();
        LinkedList<String> queue = new LinkedList<>();
        queue.offer(user);
        table.get(user).setDis(0);
        while (queue.size() > 0) {
            String next = queue.poll();
            User parent = table.get(next);
            
            if (next.equals(friend)) {
                int min = parent.getDis();
                for (User u : parent.getFriends()) {
                    if (min > u.getDis() + 1) {
                        min = u.getDis() + 1;
                    }
                }
                return min;
            }
            if (!visitHash.contains(next)) {
                visitHash.add(next);
                for (User u : parent.getFriends()) {
                    if (!visitHash.contains(u.getName())) {
                      
                        if (!queue.contains(u.getName())) {
                            queue.offer(u.getName());
                            u.setDis(parent.getDis() + 1);
                            }
                    }
                    
                }
            }
        }
        return -1;
    }
    
    public void ShowShortestPath(String user, String friend){
    	int a = findShortestPath(user,friend);
    	if (a>-1){
    		System.out.println("shortest path bewteen " + user +" and " + friend+ " is "+a);
    	}
    	else{
    		System.out.println("No Path between "+user+" and "+friend);
    	}
    }


    public static void main(String[] args) {
        FaceSpace a = new FaceSpace();
        System.out.println("add Lori");
        a.add("Lori");
        System.out.println("show Lori's profile: ");
        a.search("Lori");
        a.add("Lori");
        System.out.println();
        
        System.out.println("Add Andrew, Bill, Victor to Lori's friend.");
        System.out.println("show Lori's profile: ");
        
        a.addFriend("Lori", "Andrew");
        a.addFriend("Lori", "Bill");
        a.addFriend("Lori", "Victor");
        a.search("Lori");
        System.out.println();
        System.out.println("show Andrew's profile: ");
        a.search("Andrew");
        System.out.println();
        
        System.out.println("delete Andrew");
        System.out.println();
        
        a.delete("Andrew");
        System.out.println("show Lori's profile: ");
        a.search("Lori");
        System.out.println();
        System.out.println("show Bill's profile: ");
        a.search("Bill");
        System.out.println();
        System.out.println("show Victor's profile: ");
        a.search("Victor");
        System.out.println();

        System.out.println("Add Michael to Bill's friend.");
        a.addFriend("Bill", "Michael");
        System.out.println("Add Victor to Bill's friend.");
        a.addFriend("Bill", "Victor");
        System.out.println();
        
        a.ShowShortestPath("Andrew","Victor");
        a.ShowShortestPath("Andrew","Michael");
        a.ShowShortestPath("Michael","Victor");
        a.ShowShortestPath("Victor","Michael");
        System.out.println();

        System.out.println("remove Victor from Lori's friend");
        a.removeFriend("Lori", "Victor");
        a.search("Lori");
        
    }
}