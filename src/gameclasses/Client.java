package gameclasses;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
/**
 * the client class of the game
 * @author hamza
 *
 */
public class Client {
	
	private PrintWriter socketOut;
	private Socket aSocket;
	private BufferedReader stdIn;
	private BufferedReader socketIn;
	
	public Client(String serverName, int portNumber) {
		try {
			aSocket = new Socket(serverName,portNumber);
			stdIn = new BufferedReader( new InputStreamReader(System.in));
			socketIn= new BufferedReader(new InputStreamReader(aSocket.getInputStream()));
			socketOut= new PrintWriter((aSocket.getOutputStream()), true);
		}catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * communicates with the server
	 */
	public void communicateServer() {
		try {
		while(true) {
			String read ="";
			while(true) {
				read = socketIn.readLine();
				if(read.contains("\0")) {
					read=read.replace("\0", "");
					System.out.println(read);
					break;
				}
				if(read.contentEquals("QUIT")) {
					return;
				}
				System.out.println(read);
			}
			read = stdIn.readLine();
			socketOut.println(read);
			socketOut.flush();
	}
		}catch(Exception e) {
		e.printStackTrace();
	}
		finally 
		{
			try {
				stdIn.close();
				aSocket.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		
	}
	public static void main(String[] args) {
		Client aClient = new Client("localhost",9000);
		aClient.communicateServer();

	}

}
