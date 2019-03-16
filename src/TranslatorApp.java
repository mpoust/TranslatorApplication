import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.Box;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/*
 * Michael Poust
 * mbp3@pct.edu
 * 11/15/2016
 * 
 * This is the TranslatorApp that will create a GUI interface to select 
 * an item (English Word) from a JComboBox to translate into Spanish.
 */

public class TranslatorApp extends JFrame {
	
	private BST<String, String> bst;
	
	private JPanel 				centerPnl;
	private JLabel				keyLbl;
	private Box 				keyBox;
	private JComboBox<String> 	keyCombo;
	private Iterator<String> 	keys;
	private ArrayList<String> 	keyList;
	private JLabel				translateLbl;
	private Box 				translateBox;
	private JTextField 			translatedText;
	
	public TranslatorApp(BST<String, String> bst){
		
		this.bst = bst;
		
		centerPnl 		= new JPanel();
		keyLbl			= new JLabel("Select Word To Translate:");
		keyBox 			= Box.createVerticalBox();
		keyCombo 		= new JComboBox<String>();
		keys 			= bst.inOrder().iterator();
		keyList 		= new ArrayList<String>();
		translateLbl 	= new JLabel("Translated Text:");
		translateBox 	= Box.createVerticalBox();
		translatedText 	= new JTextField(5);
		
		translatedText.setEditable(false);
		
		translateBox.setMaximumSize(new Dimension(200, 50));
		translateBox.add(translateLbl);
		translateBox.add(translatedText);
		
		while(keys.hasNext())
			keyList.add(keys.next());
		
		keyCombo.setModel(new DefaultComboBoxModel(keyList.toArray()));
		keyCombo.setEditable(false);
		keyCombo.setMaximumSize(new Dimension(150, 25));
		keyCombo.setAlignmentX(Component.LEFT_ALIGNMENT);
		keyCombo.addActionListener(new SelectionHandler());
		
		keyBox.add(keyLbl);
		keyBox.add(keyCombo);
		
		setTitle("Translator Application");
		setSize(450, 150);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());

		centerPnl.setLayout(new GridLayout(2,3));
		centerPnl.add(keyBox);
		centerPnl.add(translateBox);

		add(centerPnl, BorderLayout.CENTER);
		
		setVisible(true);
	}
	
	private class SelectionHandler implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			String translate 	= (String) keyCombo.getSelectedItem();
			String value 		= (String) bst.getVal(translate);
			translatedText.setText(value);
		}
		
	}
}