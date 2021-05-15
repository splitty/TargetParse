package org.TargetParse;
//Copyright for jSoup
//Copyright © 2009 - 2013 Jonathan Hedley (jonathan@hedley.net)

//Permission is hereby granted, free of charge, to any person obtaining a copy 
//of this software and associated documentation files (the "Software"), to deal 
//in the Software without restriction, including without limitation the rights 
//to use, copy, modify, merge, publish, distribute, sublicense, and/or sell 
//copies of the Software, and to permit persons to whom the Software is 
//furnished to do so, subject to the following conditions:


//THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR 
//IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, 
//FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE 
//AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER 
//LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, 
//OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE 
//SOFTWARE.


//The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

// Luke Labenski

//The comments you find throughout will help to explain what this software does and how it works.
//First off, these are the imports.  Imports are designed to act as the system to tell the computer what to look for when picking resources.
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import static org.TargetParse.getFiles.retrieve;

//This is the "main" class, called Downloader.
public class Downloader extends JFrame{
	
	
	
	//Identifying strings and variables used throughout the software as ways of moving information
    static String webSiteURL1;
    static String webSiteURL2;
    static String webSiteURL3;
    static String webSiteURL4;
    static String webSiteURL5;
    static String webSiteURL6;
    static String webSiteURL7;
    static String webSiteURL8;
    static String webSiteURL9;
    static String webSiteURL0;
    static String folderPath;
    static String[] sitesToParse;
    JTextField userInput1 = new JTextField(16);
    JTextField userInput2 = new JTextField(16);
    JTextField userInput3 = new JTextField(16);
    JTextField userInput4 = new JTextField(16);
    JTextField userInput5= new JTextField(16);
    JTextField userInput6 = new JTextField(16);
    JTextField userInput7 = new JTextField(16);
    JTextField userInput8 = new JTextField(16);
    JTextField userInput9 = new JTextField(16);
    JTextField userInput0 = new JTextField(16);
    JFileChooser chooser = new JFileChooser();
    final static private JFrame frm = new JFrame();
    static JPanel pnl = new JPanel();
    JTextField filelocation = new JTextField(16);
    JButton submit = new JButton("Submit");
    JButton getFileLocation = new JButton("Get File Location");
    JLabel label1 = new JLabel();
    JLabel label2 = new JLabel();
    static JLabel imageLabel = new JLabel();
    JLabel headerLabel = new JLabel();
    private static final long serialVersionUID = 1L;
    
    
    
    //This bit here will create and display the User Interface.  Feel free to modify it to make it more pretty and less simplistic
	private void createAndShowGUI() throws IOException
    {
        //Describing the GUI to the system to display it properly
        pnl.setPreferredSize(new Dimension(250,500));
        label1 = new JLabel("Website URL Including HTTP://");
        label2 = new JLabel("Save Location for Downloads");
        ImageIcon ii = new ImageIcon(this.getClass().getResource(
                    "loader.gif"));
            imageLabel.setIcon(ii);
        pnl.add(imageLabel);
        imageLabel.setVisible(false);
        pnl.add(label1);
        pnl.add(userInput1);
        pnl.add(userInput2);
        pnl.add(userInput3);
        pnl.add(userInput4);
        pnl.add(userInput5);
        pnl.add(userInput6);
        pnl.add(userInput7);
        pnl.add(userInput8);
        pnl.add(userInput9);
        pnl.add(userInput0);
        pnl.add(label2);
        pnl.add(filelocation);
        pnl.add(submit);
        pnl.add(getFileLocation);
        frm.add(pnl, BorderLayout.CENTER);
        frm.setLocation(150, 100);
        frm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frm.setResizable(false);
        validate();
        repaint();
        frm.pack();
        frm.setVisible(true);
        
        //Pressing of the getFileLocation button calls upon this action
        getFileLocation.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		//Calls the FileLocation class
                FileLocation();
              }
            });
        //Pressing the submit button calls upon this action
        submit.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {    
        	//This is different, as it sets the loading bar .gif in place and then refreshes the GUI to display it.
        		imageLabel.setVisible(true);
        		pnl.repaint();
        		frm.repaint();
        		//Calls the submitAction class
                submitAction();
              }
            });
    }
	//Class to turn off the loading bar
    public static void turnOffBar() {
        imageLabel.setVisible(false);
        pnl.repaint();
        frm.repaint();
    }
    //Class to turn on the loading bar
    public static void turnOnBar() {
        imageLabel.setVisible(true);
        pnl.repaint();
        frm.repaint();
    }
    //Where the user input gets converted into a String Array, which then executes the parsing class
    public void submitAction() {
    	//Get Strings from the user inputed text and the obtained file location
        webSiteURL1 = userInput1.getText();
        webSiteURL2 = userInput2.getText();
        webSiteURL3 = userInput3.getText();
        webSiteURL4 = userInput4.getText();
        webSiteURL5 = userInput5.getText();
        webSiteURL6 = userInput6.getText();
        webSiteURL7 = userInput7.getText();
        webSiteURL8 = userInput8.getText();
        webSiteURL9 = userInput9.getText();
        webSiteURL0 = userInput0.getText();
        folderPath = filelocation.getText();
        //Declare the String Array has 10 strings inside of it
        sitesToParse = new String[10];
        //Declare the 10 strings inside the Array
        sitesToParse[0] = webSiteURL1;
        sitesToParse[1] = webSiteURL2;
        sitesToParse[2] = webSiteURL3;
        sitesToParse[3] = webSiteURL4;
        sitesToParse[4] = webSiteURL5;
        sitesToParse[5] = webSiteURL6;
        sitesToParse[6] = webSiteURL7;
        sitesToParse[7] = webSiteURL8;
        sitesToParse[8] = webSiteURL9;
        sitesToParse[9] = webSiteURL0;
        //Grab the getFiles Class to make it usable and able to be called
        retrieve soupIt = new retrieve();
        //Execute the "soupIt" a.k.a getFiles Class
        try {
        soupIt.execute();
        } catch(Exception e){
        }
    }
    
    

    
    
    //Class to obtain the File Location selected by the user when they press the "Get File Location" button
    private void FileLocation() {
    	//Sets the current directory to navigate from
    	chooser.setCurrentDirectory(new java.io.File("."));
    	//Just the text that is displayed on the new frame/window
        chooser.setDialogTitle("File Chooser");
        //Allow for only folders/directories to be chosen
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        chooser.setAcceptAllFileFilterUsed(false);
        //If a viable directory is selected, the directory path is passed into the text box.
        if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
          System.out.println("getCurrentDirectory(): " + chooser.getCurrentDirectory());
          System.out.println("getSelectedFile() : " + chooser.getSelectedFile());
        } else {
          System.out.println("No Selection");
        }
        String path=chooser.getSelectedFile().getAbsolutePath();
        filelocation.setText(path);
    }
    

 
			
		
        
        
        
        
    //The main class which calls upon the GUI to show itself.  This will create a new instance of the Downloader class and 
    //continue to run the createAndShowGui class inside the instance.
    public static void main(String args[]) {
       //Giving it that "gray-ish" feel and look and showing any errors that occur
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Downloader.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Downloader.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Downloader.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Downloader.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        SwingUtilities.invokeLater(new Runnable()
        {
            @Override
            public void run()
            {
                Downloader pf = new Downloader();
                try {
					pf.createAndShowGUI();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }
        });
    }
    
    
}