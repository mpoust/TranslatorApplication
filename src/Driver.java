
/*
 * Michael Poust
 * mbp3@pct.edu
 * 11/15/2016
 * 
 * This is the driver that will create a BST and insert all the values from the 
 * associated text file into the tree.
 * 
 * Will also create an instance of the GUI translator.
 */

public class Driver {
	public static void main(String[] args) {
		BST<String, String> bst = new BST<String, String>();
		bst.insert();
		TranslatorApp translator = new TranslatorApp(bst);
		translator.getContentPane().setLayout(null);
	}
}