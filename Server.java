import java.io.*;
import java.lang.Thread;
import java.net.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
public class Server extends JFrame
{
	JLabel l1;
	JTextField t1;
	JTextArea ta1;
	JButton send,cnt,dscnt;
	String msg="";
	boolean con=true;
	Socket socket=null;
	ServerSocket ss=null;
	DataInputStream dis=null;
	DataOutputStream dos=null;
	public Server(String s)
	{
		super(s);
	}
	public void setView()
	{
		 l1=new JLabel("Enter your Message ");
		 t1=new JTextField();
		 ta1=new JTextArea();
		 send=new JButton("SEND");
		 dscnt=new JButton("DISCONNECT");
		 cnt=new JButton("CONNECT");	
		 setLayout(null);
		 l1.setBounds(25,25,60,25);
		 t1.setBounds(100,25,150,30);
		 ta1.setBounds(10,60,280,200);
		 send.setBounds(135,260,25,20);
		 cnt.setBounds(10,280,50,20);
		 dscnt.setBounds(240,280,50,20);
		 this.add(l1);
		 this.add(t1);
		 this.add(ta1);
		 this.add(send);
		 this.add(cnt);
		 this.add(dscnt);
		 send.addActionListener(new ActionListener()
		 {
			 public void actionPerformed(ActionEvent ae)
			 {
				 try
					{
						if(con)
						{
							//ss=new ServerSocket(3000);
							//socket=ss.accept();
							//dis=new DataInputStream(socket.getInputStream());
							//dos=new DataOutputStream(socket.getOutputStream());
							msg=t1.getText();
							ta1.setText(ta1.getText()+"\nServer:"+msg);
							dos.writeUTF(msg);	
							msg=(String)dis.readUTF();
							ta1.setText(ta1.getText()+"\nClient:"+msg);
							//dos.flush();
						}
						else
						{
							ta1.setText("----service is disconnected----");
						}
					}catch(Exception e)
					   {
					   System.out.print("Error1:"+e);
					   }
			  }
		 });
		 cnt.addActionListener(new ActionListener()
		 {
			 public void actionPerformed(ActionEvent ae)
			 {
				 try{	              
						ss=new ServerSocket(3000);
						socket=ss.accept();											
						dis=new DataInputStream(socket.getInputStream());
						dos=new DataOutputStream(socket.getOutputStream());			
						ta1.setText("server is now connected to client. \nReady to chat");
						msg=(String)dis.readUTF();						
						ta1.setText(ta1.getText()+"\nClient:"+msg);
					}catch(Exception e)
					{
						e.printStackTrace();
					}
			 }
		 });
		 dscnt.addActionListener(new ActionListener()
		 {
			 public void actionPerformed(ActionEvent ae)
			 {
				 con=false;				
			 }
		 });
	}
	public static void main(String args[])
	{
		Server jf=new Server("SERVER");
		jf.setSize(300,300);
		jf.setVisible(true);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.setView();
	}
}