import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class texteditor extends JFrame implements ActionListener {
    JScrollPane scrollPane;
    JTextArea textArea;
    JSpinner fontsizespinner;
    JComboBox fontbox;

    JMenuBar menuBar;
    JMenu filemenu;
    JMenuItem Save;
    JMenuItem Exit;

    JLabel fontsizelable;
    texteditor(){ // this is constructor of class text editor.
        this.setSize(500,500); // this is over frame initial size.
        this.setTitle("TextEditor - Vivek");// this is over frame title
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // this for  close the text editor.
        this.setLayout(new FlowLayout());
        // text area
        textArea = new JTextArea();//create the object of jtextarea.
        textArea.setSelectedTextColor(Color.RED); // if user select the text that color convert in red color.
        textArea.setFont(new Font("Arial", Font.PLAIN, 18));// this is default text if user wont to chang style they can do it.

        // pane
        scrollPane = new JScrollPane(textArea); // create the object of scrollpane
        scrollPane.setPreferredSize(new Dimension(450,450)); // initial size of scrollpane.

        // font size spinner
        fontsizespinner = new JSpinner(); // create the spinner.
        // fontsizespinner.setPreferredSize(new Dimension(50,25));// then select the dimension on the spinner box.
        fontsizespinner.setSize(new Dimension(50,25)); // same as above line.
        fontsizespinner.setValue(18);//select the default set text value.

        fontsizespinner.addChangeListener(new ChangeListener() { // if according the change size the text.
            @Override
            public void stateChanged(ChangeEvent e) {
                textArea.setFont(new Font(textArea.getFont().getFamily(), Font.PLAIN, (int)fontsizespinner.getValue()));//this is same the same as the line no 20 textArea.setFont that means(same textstyle as a text area , plane,  the value is a user select size in aspineer).
            }
        });

        // font scroll bar
        String [] fonts = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
        fontbox = new JComboBox(fonts);
        fontbox.addActionListener(this);

        //creating a menu bar
        menuBar = new JMenuBar(); // create the MenuBar
        filemenu = new JMenu("File"); // create the file menu.(in MenuBar)
        Save = new JMenuItem("Save");// create the save menu.(in file menu)
        Exit = new JMenuItem("Exit"); // create the exit menu.(in file menu)
        Exit.addActionListener(this);
        Save.addActionListener(this);
        filemenu.add(Save); // add this menu of file menu.
        filemenu.add(Exit); // add this menu in file menu.
        menuBar.add(filemenu); // add the file menu in menuBar.

        // add all the element in my Jframe
        fontsizelable = new JLabel("Font Size");

        this.setJMenuBar(menuBar);
        this.add(fontsizelable);
        this.add(fontsizespinner); // add the frame in spinner.
        this.add(fontbox); // add the frame in font box.
        this.add(scrollPane);// add the frame in pane.

        this.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) { // every action perform into Jframe write to in methods.
        // this code is for help to change the font style.
      if(e.getSource() == fontbox){
          textArea.setFont(new Font((String)fontbox.getSelectedItem(), Font.PLAIN, (int)fontsizespinner.getValue()));
      }

      // this code for help to operation on exit button.
      if(e.getSource() == Exit){
        System.exit(29);
      }

      // this code for help to operation on save button.
      if(e.getSource() == Save){
          JFileChooser file_chooser = new JFileChooser();
          file_chooser.setCurrentDirectory(new File("."));
          int response = file_chooser.showSaveDialog(null);

          if(response == 0){
              // I need to save this file
              File file = new File(file_chooser.getSelectedFile().getAbsolutePath());
              PrintWriter text;
              try{
                  text = new PrintWriter(file);
                  text.println(textArea.getText());
              } catch(FileNotFoundException ex){
                  throw new RuntimeException(ex);
              }
              text.close();
          }
      }
    }
}
