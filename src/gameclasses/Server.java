package gameclasses;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
/**
 * the server class of the program
 * @author hamza
 *
 */
public class Server {
	
	private ServerSocket serverSocket;
	private ExecutorService pool;
	
	public Server(int portNumber) {
		try {
			serverSocket = new ServerSocket(portNumber);
			pool = Executors.newCachedThreadPool();
		}catch(IOException e) {
		}
	}
	/**
	 * communicates with client
	 */
	public void communicateClient() {
		try {
			while (true) {
				
				Player xPlayer = new Player (serverSocket.accept(),'X');
				Player oPlayer = new Player (serverSocket.accept(),'O');
				
				Referee theRef = new Referee();
				
				theRef.setxPlayer(xPlayer);
				theRef.setoPlayer(oPlayer);

				Game theGame = new Game();
				theGame.appointReferee(theRef);
				
				pool.execute(theGame);
			}
		} catch (Exception e) {
			e.printStackTrace();
			pool.shutdown();
		}
	}

	public static void main(String[] args) {
		Server server = new Server(9000);
		System.out.println("Server is running");
		server.communicateClient();

	}

}
