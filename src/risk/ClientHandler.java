
package risk;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClientHandler
{
    public static boolean connected = false;
    private static         Socket MyClient;
    private static         BufferedReader br;
    private static         PrintWriter out;

	private static String hostIP = null;
	private static int hostPort = -1;

//	private static Socket server = null;
//	private static PrintWriter serverOut = null;
//	private static BufferedReader serverIn = null;

	public static void connect(String ip, int port) throws UnknownHostException, IOException
	{
		hostIP = ip;
		hostPort = port;
            MyClient = new Socket(ip, port);
            br = new BufferedReader(new InputStreamReader(MyClient.getInputStream()));
            out = new PrintWriter(MyClient.getOutputStream(), true);
                
                
//		server = new Socket();
//		server.connect(new InetSocketAddress(ip, port), 6000);
//		serverOut = new PrintWriter(server.getOutputStream(), true);
//		serverIn = new BufferedReader(new InputStreamReader(server.getInputStream()));
		connected = true;
		recieveInfo();
	}

	public static void disconnect()
	{
		try
		{
			if (MyClient != null)
				MyClient.close();
		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		hostIP = null;
		hostPort = -1;
		MyClient = null;
		out = null;
		br = null;
		connected = false;
		Connect.setGameStarted(false);
		FrameManager.reset();
	}
    public static void sendInfo()
    {
		if (connected)
		{
			out.println(1+":"+1);
                        out.flush(); 
		}        
    }

	public static void sendDisconnect()
	{
		if (connected)
		{
			out.println("esc");
		}
	}


	private static void recieveInfo()
	{
		new Thread(new Runnable() {

			@Override
			public void run()
			{
				String inputLine;

				try
				{
					while ((inputLine = br.readLine()) != null)
					{
						try
						{
							if (inputLine.equals("esc"))
							{
								disconnect();
								return;
							}
                                                        
							int firstVal = Integer.parseInt(inputLine.split(":")[0]);
							int secondVal = Integer.parseInt(inputLine.split(":")[1]);
						}
						catch (NumberFormatException e)
						{
							e.printStackTrace();
						}
						catch (NullPointerException e)
						{
							disconnect();
						}
					}
				}
				catch (IOException e)
				{
					disconnect();
				}

			}
		}).start();
	}

	public static boolean isConnected()
	{
		return connected;
	}
}
