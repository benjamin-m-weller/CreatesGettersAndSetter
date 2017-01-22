package createsgettersandsetters;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;


/**
 *
 * @author Ben Weller
 */
public class CreatesGettersAndSetters {

    /**
     * This method generates the "setter" text for a given set of parameters
     * @param accessModifier This is the access modifier of the instance variable. The setter will have the same modifier.
     * @param myVariableType This is the type of the instance variable, it's used as it should be in the setter.
     * @param myVariableName This is the instance variable name it will be used appropriately in the setter.
     * @return This is the completed setter method.
     */
    public static String getSetterText(String accessModifier, String myVariableType, String myVariableName) 
    {
        return accessModifier+" void set" + myVariableName + "(" + myVariableType + " " + myVariableName + ")"
                + "\n{\n"
                + "this." + myVariableName + "=" + myVariableName + ";"
                + "\n}\n";
    }

    /**
     This method generates the "getter" text for a given set of parameters
     * @param accessModifier This is the access modifier of the instance variable. The getter will have the same modifier.
     * @param myVariableType This is the type of the instance variable, it's used as it should be in the getter.
     * @param myVariableName This is the instance variable name it will be used appropriately in the getter.
     * @return This is the completed getter method. 
     */
    public static String getGetterText(String accessModifier, String myVariableType, String myVariableName) 
    {
        return accessModifier+" " + myVariableType + " get" + myVariableName + "()"
                + "\n{\n"
                + "return " + myVariableName + ";"
                + "\n}\n";
    }
    
    /**
     * This method puts a string on the system clipboard
     * @param addMe This is the string to be added.
     * @param c This is an instance of the system clipboard.
     */
    public static void setStringToClipboard(String addMe, Clipboard c)
    {
        StringSelection selection = new StringSelection(addMe);
        c.setContents(selection, selection);
    }
    
    /**
     * This method returns the string currently on the clipboard.
     * @param c This is an instance of the system clipboard.
     * @return This is the string that was on the clipboard.
     */
    public static String getStringFromClipboard(Clipboard c) 
    {
        Transferable t = c.getContents(null);
        String data = "";
        try {
            if (t.isDataFlavorSupported(DataFlavor.stringFlavor)) {
                Object o = t.getTransferData(DataFlavor.stringFlavor);
                data = (String) t.getTransferData(DataFlavor.stringFlavor);
                return data;
            }
        } catch (Exception e) {
            //Something went wrong
        }
        return null;
    }
    
    public static void main(String[] args) 
    {
        //I first thought about having the user interact and place in file paths
        //following that thought I rememebr you could interact with the system keyboard via Java
        //That solution is infinitely easier.
        
        //Getting the system clipboard
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        
        //Now to parse it.
        String[] lines=getStringFromClipboard(clipboard).replaceAll(";","").split("\n");
        
        
        //I'm going to use the string builder class becuase I know that regular string concatenation is slow
        StringBuilder bob=new StringBuilder();
        
        
        for (String s: lines)
        {
            String[] myStringArray = s.split(" ");
            
            /*
            It should be the case that index:
            0=access modifier
            1=type
            2=inentifier w/o the semicolon following
             */
            
            bob.append(getSetterText(myStringArray[0], myStringArray[1], myStringArray[2]));
            bob.append(getGetterText(myStringArray[0], myStringArray[1], myStringArray[2]));
        }
        
        //Now that we have everything situated lets get from the string builder, and attach it to the system clipboard
        setStringToClipboard(bob.toString(), clipboard);
    }
}


//Deprecated code, I understand this may be bad practice but I sometimes do if for my own personal projects.
//
//        File fileForInput;
//        File fileForOutput;
//        
//        String filePathInput=JOptionPane.showInputDialog("Please enter the full pathname for the .txt file that contains your instance variables.\n"
//                + "The instance variables must be in the \"accessModifier\" \"dataType\" \"instanceVariableName\" format.");
//        String filePathOutput=JOptionPane.showInputDialog("Please enter the full pathname for the .txt file that will contain the output. \n"
//                + "If the .txt file doesn't currently exist the program will attempt to make one."
//                + "\nThe program assumes that you forgot to add the .txt at the end of your path.");
//        
//        if (filePathInput == null || filePathOutput == null) 
//        {
//            //Pop up here
//            System.exit(0);
//        }
//        
//        //Testing the files
//        fileForInput=new File(filePathInput);
//        fileForOutput=new File(filePathOutput);
//        
//        //Here we will test and see if the files exist....if not we will loop.
//           
//           
////        try
////        {
////            fileForInput=new File(filePathInput);
////        }
////        catch (FileNotFoundException fnfe)
////        {
////            
////        }
//        
//        
//        
//        PrintWriter fileOutput = new PrintWriter(new File(filePathInput)) //This is file reader
//        Scanner fileReader = new Scanner(new File()); //This is the file writer
//        ArrayList<String> listOfInstanceVariableNames = new ArrayList();
//        ArrayList<String> listOfInstanceVariableTypes = new ArrayList();
//
//
//        while (fileReader.hasNextLine()) {
//            String[] myStringArray = fileReader.nextLine().split(" ");
//            listOfInstanceVariableNames.add(myStringArray[1]);
//            listOfInstanceVariableTypes.add(myStringArray[2]);
//        }