import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.time.LocalDateTime;

public class TCPServer extends Thread
{

	private ServerSocket server;
	
	public TCPServer(int port) throws IOException
	{
		server=new ServerSocket(port);
		server.setSoTimeout(1000);
	}
	
	public void run()
	{
		Socket connection = null;
		OutputStream out = null;
		byte[] bufferOutput=new byte[1024];
		
		while(!interrupted())
		{
				try 
			{
				connection=server.accept();
				out=connection.getOutputStream();
				bufferOutput=LocalDateTime.now().toString().getBytes("ISO-8859-1");
				out.write(bufferOutput);
				out.flush();
				
			} 
			catch (SocketTimeoutException e) 
			{
				System.err.println("Timeout");
			}
			catch (IOException e) 
			{
				
				e.printStackTrace();
			}
			
			if ((connection!=null) && !(connection.isClosed()) )
			{
				try 
				{
					out.close();
					connection.shutdownOutput();
					connection.close();
				} 
				catch (IOException e) 
				{
					e.printStackTrace();
				}
				
			}
		}
		
	}
	
	public static void main(String[] args) 
	{
		ConsoleInput tastiera= new ConsoleInput();
		try 
		{
			TCPServer server= new TCPServer(13);
			server.start();
			tastiera.readLine();
			server.interrupt();
			server.join();
		} 
		catch (IOException e) 
		{
			System.err.println("Impossibile istanziare il server");
		} 
		catch (InterruptedException e) 
		{
		
		}
		
		

	}

}
