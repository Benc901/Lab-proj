package iBookServer;

import javax.swing.UIManager.*;

import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

import iBookServer.serverUI;

import ocsf.server.*;



/**
 * <b>GHeathServer</b> - the server side main class. responsible of handle the 
 *<br> communication between the DB, through the JDBC driver, and the client side.
 *<br><br>
 *{@code extends AbstractServer} - from OCSF framework
 *
 * @author G11
 *@see ocsf.server.AbstractServer
 */
public class IBookServer extends AbstractServer {
	
	/**
	 * The UI farme instance for the server.
	 */
	private static IBookServer server;
	private static serverUI window;
	public static String IP;
	
	/**
	 * The default port to connect on.
	 */
	final public static int DEFAULT_PORT = 5555;
	
	/**
	 * Register to the JDBC driver and initial sqlCon 
	 */
	static mysqlConnection sqlCon;

	/**
	 * <b>Constructor</b> - Initialize the server
	 * 
	 * @param port
	 */
	public IBookServer(int port) {
		super(port);
	}
	@Override
	protected void handleMessageFromClient(Object obj, ConnectionToClient client){
		
		HashMap<String, Object> returnObj = new HashMap<String, Object>(); 
		
		Map<String, Object> map = (HashMap<String, Object>) obj; 
		String op = (String) map.get("op");
		
		switch (op) {
			case "Login": 
				display(" : " + op, client);
				returnObj.put("op", "Login");
				returnObj.put("obj", sqlCon.login(map.get("obj")));
				break;
			case "RecoverPassword":
				display(" : " + op, client);
				returnObj.put("op", "RecoverPassword");
				//returnObj.put("obj", sqlCon.recoverPassword(map.get("obj")));				
				break;	
			case "Logout":
            	display(" : " + op, client);
				returnObj.put("op", "Logout");
				returnObj.put("obj", sqlCon.logout(map.get("obj")));
				break;	
			case "SearchBook": 
				display(" : " + op, client);
				returnObj.put("op", "SearchBook");
				returnObj.put("arr", sqlCon.SearchBook((String)map.get("text"),(String)map.get("cb")));
				break;
			case "SearchReview": 
				display(" : " + op, client);
				returnObj.put("op", "SearchReview");
				returnObj.put("arr", sqlCon.SearchReview((String)map.get("text"),(String)map.get("cb")));
				break;	
		}
		
		try {
			client.sendToClient(returnObj);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	public static void main(String[] args) {
		int port = 0;      // Port to listen on
		
		try {
		    for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
		        if ("Nimbus".equals(info.getName())) {
		            UIManager.setLookAndFeel(info.getClassName());
		            break;
		        }
		    }
		} catch (Exception e) {
		    // If Nimbus is not available, you can set the GUI to another look and feel.
		}
		
		try {
			port = Integer.parseInt(args[0]);     // Get port from command line
		} catch (Throwable t) {
			port = DEFAULT_PORT;    // Set port to 5555
		}
	
		
		IBookServer sv = new IBookServer(port);
		window= new serverUI(sv);
		server=sv;
		StartServer(5555);
		
		sqlCon = new mysqlConnection(window,"root","1234");
		
		window.setVisible(true);
		/*try {
			sv.listen(); // Start listening for connections 
		} catch (Exception ex) {
			display("ERROR - Could not listen for clients!", null);
		}*/
	}

	
	public static void StartServer(int port){
		
		
		try {
			server.listen();
		} catch (IOException e) {
			System.out.println("ERROR - Could not listen for clients!");
		}
		if(server.isListening()){
			  System.out.println("Listenning on port "+ port);
			  window.label_2.setText("Online");
			  window.label_2.setForeground(Color.GREEN);
		}else {
			window.label_2.setText("Offline");
			  window.label_2.setForeground(Color.RED);
		}
		
		try {IP = "" + InetAddress.getLocalHost().getHostAddress();} 
		catch (UnknownHostException e) {e.printStackTrace();}
		window.lblIp.setText(IP);
	}
	
public static void StopServer(){
		
		boolean catched = false;
		
		try {
			server.close();
		} catch (IOException e) {
			System.err.println("Cant close connection - StopServer");
			catched=true;
		}
		if(!catched){
			window.label_2.setText("Offline");
			  window.label_2.setForeground(Color.RED);
		}else{
			window.label_2.setText("Online");
			  window.label_2.setForeground(Color.GREEN);
		}
		
		
	}
	

public static void ConnectToSQL(String username,String password){
	try {

		sqlCon = new mysqlConnection(window,username,password);
		

	}catch (Exception ex){
		//System.err.println("Sql Connection error");
		}// exception thrown in the constructor if needed.
	
		
}

	private static void display(String message, ConnectionToClient client) {
		window.display(message, client);
		
	}
	
	
	
}// End of EchoServer class