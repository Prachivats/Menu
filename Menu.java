import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.datatransfer.*;
import java.io.*;
import java.awt.Color.*;

class Menu extends JFrame implements ActionListener
{
Clipboard clipboard=getToolkit().getSystemClipboard();
JTextField jt=new JTextField();
JFrame jf=new JFrame("Notepad");
JMenuBar jb;
JMenuItem m1,m2,m3,m4;
JMenu menu;
JRadioButtonMenuItem rb;
String fname="";

Menu()
{
jf.add(jt);

jb=new JMenuBar();
//file
menu = new JMenu("File");
menu.setMnemonic(KeyEvent.VK_F);
jb.add(menu);
m1=new JMenuItem("New");
m1.addActionListener(this);
m1.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N,ActionEvent.CTRL_MASK));
menu.add(m1);
m2=new JMenuItem("Open");
m2.addActionListener(this);
m2.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O,ActionEvent.CTRL_MASK));
menu.add(m2);
m3=new JMenuItem("Save");
m3.addActionListener(this);
m3.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,ActionEvent.CTRL_MASK));
menu.add(m3);
m4=new JMenuItem("Save As");
//m4.addActionListener(this);
menu.add(m4);
menu.addSeparator();
m1=new JMenuItem("exit");
m1.addActionListener(this);
m1.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q,ActionEvent.CTRL_MASK));
menu.add(m1);
//Edit
menu = new JMenu("Edit");
menu.setMnemonic(KeyEvent.VK_E);
jb.add(menu);
m1=new JMenuItem("Undo");
//m1.addActionListener(this);
m1.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z,ActionEvent.CTRL_MASK));
menu.add(m1);
menu.addSeparator();
m2=new JMenuItem("Cut");
m2.addActionListener(this);
m2.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X,ActionEvent.CTRL_MASK));
menu.add(m2);
m3=new JMenuItem("Copy");
m3.addActionListener(this);
m3.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C,ActionEvent.CTRL_MASK));
menu.add(m3);
m4=new JMenuItem("Paste");
m4.addActionListener(this);
m4.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V,ActionEvent.CTRL_MASK));
menu.add(m4);
m1=new JMenuItem("Delete");
m1.addActionListener(this);
m1.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D,ActionEvent.CTRL_MASK));
menu.add(m1);

//view
menu = new JMenu("View");
menu.setMnemonic(KeyEvent.VK_W);
jb.add(menu);
rb=new JRadioButtonMenuItem("Status BAR");
if(rb.isSelected()==true)
rb.addActionListener(this);
rb.setSelected(true);
menu.add(rb);
//highlight
menu = new JMenu("Highlight");
menu.setMnemonic(KeyEvent.VK_H);
m3=new JMenuItem("Highlight");
m3.addActionListener(this);
m3.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_H,ActionEvent.CTRL_MASK));
menu.add(m3);
jb.add(menu);

    jf.setJMenuBar(jb);
	jf.setSize(300,400);
	jf.setVisible(true);
}
void filesave()
{
 FileDialog fd=new FileDialog(this,"Save the file with .txt extension",FileDialog.SAVE);
 fd.show();
 if(fd.getFile()!=null)
 {
 fname=fd.getDirectory() + fd.getFile();
 setTitle(fname);
 try
 {
 DataOutputStream dout=new DataOutputStream(new FileOutputStream(fname));
 String str=jt.getText();
 BufferedReader br1=new BufferedReader(new StringReader(str));
 while((str=br1.readLine())!=null)
 {
	dout.writeBytes(str+"\n");
	dout.close();
 }
 
 }
catch(Exception exc)
{
 System.out.println("File not found");
}
jt.requestFocus();
}
}
public void actionPerformed(ActionEvent e)
{

//exit
if(e.getActionCommand().equals("exit"))
{
int res;
res = JOptionPane.showConfirmDialog(this,"Do You Want to Save Changes", "File Exit", JOptionPane.YES_NO_CANCEL_OPTION );
if(res == JOptionPane.YES_OPTION)
{
filesave();
}
else if(res == JOptionPane.CANCEL_OPTION)
{
return;
}
}

//New
if(e.getActionCommand().equals("New"))
{
	jt.setText(" ");
}
//Open
if(e.getActionCommand().equals("Open"))
{
	FileDialog fd =new FileDialog(this,"Select a text file",FileDialog.LOAD);
	fd.show();
	if(fd.getFile()!=null)
	{
	
	fname=fd.getDirectory() + fd.getFile();
	setTitle(fname);
	readfile(fname);
	}
	jt.requestFocus();
}
/*if(e.getActionCommand().equals("Status BAR"))
{
jf.setLayout(new BorderLayout());
JPanel statusPanel = new JPanel();
statusPanel.setBorder(new JBorder(JBorder.LOWERED));
jf.add(statusPanel, BorderLayout.SOUTH);
statusPanel.setPreferredSize(new Dimension(jf.getWidth(), 16));
statusPanel.setLayout(new BoxLayout(statusPanel, BoxLayout.X_AXIS));
JLabel statusLabel = new JLabel("status");
statusLabel.setHorizontalAlignment(SwingConstants.LEFT);
statusPanel.add(statusLabel);
}
//undo
if(e.getActionCommand().equals("Undo")
{



}*/
//highlight
if(e.getActionCommand().equals("Highlight"))
{
String selection=jt.getSelectedText();
if(selection==null)
return;
JLabel lb=new JLabel(selection);

lb.setForeground(Color.YELLOW);
 }
//save
if(e.getActionCommand().equals("Save"))
{
 filesave();
}
//delete

//delete
if(e.getActionCommand().equals("Delete"))
{
String selection=jt.getSelectedText();
if(selection==null)
	return;
jt.replaceSelection("");

}
//cut
if(e.getActionCommand().equals("Cut"))
{
	//cut command 
        
             
            String selection=jt.getSelectedText();
             
            if(selection==null)
			{
                return;
            }
            StringSelection clipString=new StringSelection(selection);
            clipboard.setContents(clipString,clipString);
            jt.replaceSelection("");
}

//copy
if(e.getActionCommand().equals("Copy"))
{
	        String selection=jt.getSelectedText();
             
            if(selection==null){
                return;
            }
            StringSelection clipString=new StringSelection(selection);
            clipboard.setContents(clipString, clipString);
        

}
//paste
if(e.getActionCommand().equals("Paste"))
{
	        Transferable clip_data=clipboard.getContents(this);
             
            try{
                String clip_string=(String)clip_data.getTransferData(DataFlavor.stringFlavor);
                jt.replaceSelection(clip_string);
                 
            }catch(Exception excpt)
			{
            System.out.println("not a string");     
            }

}
}
void readfile(String fname)
{
BufferedReader br;
StringBuffer sb=new StringBuffer();
try
{
br=new BufferedReader(new FileReader(fname));
String o;
while((o=br.readLine())!=null)
sb.append(o + "\n");

jt.setText(sb.toString());
br.close();
}
catch(FileNotFoundException f)
{
System.out.println("File not found");
}
catch(IOException i)
{
System.out.println("File not found");
}
}
public static void main(String s[])
{
new Menu();

}
}