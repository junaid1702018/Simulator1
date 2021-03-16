import java.net.*;
import java.sql.Timestamp;
import java.util.Date;
import java.io.*;

public class Server extends Thread {
	// public static int waitTime = 100;
	// public static boolean running = false;
	public static Server_GUI s_g = new Server_GUI();
	protected Socket clientSocket;

	public Server(Socket accept) {
		// TODO Auto-generated constructor stub
		clientSocket = accept;
		start();
	}

	public static void main(String[] args) throws IOException {
		s_g.setVisible(true);
		ServerSocket serverSocket = null;
		while (true) {

			try {
				serverSocket = new ServerSocket(10099);
				System.out.println("Connection Socket Created");
				try {
					while (true) {
						System.out.println("Waiting for Connection");
						new Server(serverSocket.accept());
					}
				} catch (IOException e) {
					System.out.println("Accept failed.");
					System.exit(1);
				}
			} catch (IOException e) {
				System.out.println("Could not listen on port: 10099.");
				System.exit(1);
			} finally {
				try {
					serverSocket.close();
				} catch (IOException e) {
					System.err.println("Could not close port: 10099.");
					System.exit(1);
				}
			}
		}
	}

	/****
	 * public void handleApp() { try { ObjectOutputStream oos = new
	 * ObjectOutputStream(clientSocket.getOutputStream());
	 * 
	 * while (true) { Random rand = new Random();
	 * 
	 * oos.writeInt(rand.nextInt(100)); Thread.sleep(waitTime); }
	 * 
	 * } catch (IOException e) { // TODO Auto-generated catch block
	 * e.printStackTrace(); } catch (InterruptedException e) { // TODO
	 * Auto-generated catch block e.printStackTrace(); } }
	 ****/

	public void run() {
		// handleApp();

		try {
			ObjectInputStream objectInput = new ObjectInputStream(clientSocket.getInputStream());
			// running = true;
			String ClientIP = clientSocket.getRemoteSocketAddress().toString();
			System.out.println(ClientIP + " is  now connected.");
			DeviceInfo di = (DeviceInfo) objectInput.readObject();
			Date date = new Date();
			long time = date.getTime();
			// Passed the milliseconds to constructor of Timestamp class
			Timestamp recieve_time = new Timestamp(time);
			System.out.println("Device ID= " + di.ID + " Latitude=" + di.latitude + " Data1=  " + di.gas_sensor
					+ " Send time= " + di.send_time + " Recieve time= " + recieve_time);
			s_g.add_data(di.ID, di.longitude, di.latitude, di.temperature_sensor, di.gas_sensor, di.send_time,
					recieve_time);

			objectInput.close();
			// clientSocket.close();
		} catch (IOException e) {
			System.out.println("Problem with Communication Server");
			System.exit(1);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
