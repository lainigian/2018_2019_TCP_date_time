import java.io.IOException;
import java.io.InputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

public class TCPClient 
{
	private String server_address;
	private int server_port;

	public TCPClient(String server, int port)
	{
		server_address=server;
		server_port=port;
	}
	
	public String sendAndReceive() throws IOException
	{
		Socket clientSocket= new Socket();
		InetSocketAddress server=new InetSocketAddress(server_address, server_port);
		InputStream input;
		int n;
		String messaggioRicevuto;
		byte[] bufferInput= new byte[1024];
		
		clientSocket.connect(server, 1000);
		clientSocket.setSoTimeout(1000);
		input= clientSocket.getInputStream();
		
		while ((n=input.read(bufferInput))!=-1)
		{
			
		}
		
		clientSocket.shutdownInput();
		clientSocket.close();
		messaggioRicevuto=new String(bufferInput,"ISO-8859-1");
		return messaggioRicevuto;
		
	}
	public static void main(String[] args) 
	{
		String server="127.0.0.1";
		int port=13;
		String dateTime;
		
		TCPClient client= new TCPClient(server, port);
		
		try 
		{
			dateTime=client.sendAndReceive();
			System.out.println(dateTime);
		} 
		catch (IOException e) 
		{
			System.err.println("Il server non risponde");
		}
		

	}

}
