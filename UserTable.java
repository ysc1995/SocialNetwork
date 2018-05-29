

import java.util.ArrayList;

public class UserTable{
    private BSTNode root;
    private int size;

    public void add(User user) {
    	
    	
    		if (size == 0) {
    	
            root = new BSTNode(user.getName(), user);
            size++;
        } else {
            root = reAdd(user, root);
        
    	}
    }

    private BSTNode reAdd(User user, BSTNode node) {
        if (node == null) {
            size++;
            return (new BSTNode(user.getName(), user));
        }
        if (node.key.compareTo(user.getName()) < 0) {
            node.right = reAdd(user, node.right);
        } else if (node.key.compareTo(user.getName()) > 0) {
            node.left = reAdd(user, node.left);
        }
        return node;
    }

    public User remove(String user) {
        BSTNode re = new BSTNode(null, null);
        root = reRemove(root, user, re);
        return re.data;
    }

    private BSTNode reRemove(BSTNode cur, String user, BSTNode re) {
        //user name is bigger than the current node based on alphabetic order
        if (cur.key.compareTo(user) < 0) {
            cur.right = reRemove(cur.right, user, re);
            return cur;
        //user name is smaller than the current node based on alphabetic order
        } else if (cur.key.compareTo(user) > 0) {
            cur.left = reRemove(cur.left, user, re);
            return cur;
        }
        //find the user
        size--;
        re.data = cur.data;
        re.key = cur.key;
        //node has no child
        if (cur.left == null && cur.right == null) {
            return null;
        //node has a right child
        } else if (cur.left == null) {
            return cur.right;
        //node has a left child
        } else if (cur.right == null) {
            return cur.left;
        }
        //node has two children
        BSTNode replace = new BSTNode(null, null);
        cur.left = findPre(cur.left, replace);
        cur.data = replace.data;
        return cur;
    }

    private BSTNode findPre(BSTNode cur, BSTNode replace) {
        if (cur.right == null) {
            replace.data = cur.data;
            replace.key = cur.key;
            return cur.left;
        }
        cur.right = findPre(cur.right, replace);
        return cur;
    }

    public User get(String user) {
        return reGet(user, root);
    }

    private User reGet(String user, BSTNode node) {
        if (node == null) {
            return null;
        }
        if (user.compareTo(node.key) < 0) {
            return reGet(user, node.left);
        } else if (user.compareTo(node.key) > 0) {
            return reGet(user, node.right);
        }
        return node.data;
    }

    public boolean contains(String user) {
        return reContains(user, root);
    }

    private boolean reContains(String user, BSTNode node) {
        if (node == null) {
            return false;
        }
        if (user.compareTo(node.key) < 0) {
            return reContains(user, node.left);
        } else if (user.compareTo(node.key) > 0) {
            return reContains(user, node.right);
        }
        return true;
    }

    public int size() {
        return size;
    }
}

class BSTNode {
	BSTNode left;
	BSTNode right;
	User data;
	String key;

	public BSTNode(String key, User data) {
		this.key = key;
		this.data = data;
	}
}