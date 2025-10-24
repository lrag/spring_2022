import java.util.Properties;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueSession;
import javax.jms.TextMessage;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.swing.JOptionPane;

public class _03_MessageListener implements MessageListener {

	private QueueConnection qCx;

	public void conectar() {
		
		Properties p = new Properties();
		p.setProperty("java.naming.factory.initial", "com.sun.enterprise.naming.SerialInitContextFactory");
		p.setProperty("java.naming.factory.url.pkgs", "com.sun.enterprise.naming");
		p.setProperty("java.naming.factory.state", "com.sun.corba.ee.impl.presentation.rmi.JNDIStateFactoryImpl");
		p.setProperty("org.omg.CORBA.ORBInitialHost", "localhost");
		p.setProperty("org.omg.CORBA.ORBInitialPort", "3700");
		
		Context ic = null;
		
		try {
			ic = new InitialContext(p);
			
			QueueConnectionFactory queueConnFac = (QueueConnectionFactory) ic.lookup("jms/queueConnectionFactory");
			qCx = queueConnFac.createQueueConnection();
			//El primer parametro indica si la sesion es transaccional o no
			//El segundo el modo en el que se reciben los mensajes
			QueueSession qSession = qCx.createQueueSession(false, QueueSession.AUTO_ACKNOWLEDGE);
			Queue cola = (Queue) ic.lookup("jms/cola");			
			MessageConsumer consumidor = qSession.createConsumer(cola);
			
			//Para recibir mensajes es necesario arrancar la conexión
			qCx.start();
			
			//OyenteMensajes oyente = new OyenteMensajes();
			//consumidor.setMessageListener(oyente);
						
			consumidor.setMessageListener(this);
			
			
		} catch (NamingException e) {
			e.printStackTrace();
		} catch (JMSException e) {
			e.printStackTrace();
		} finally {
			try {
				ic.close();
			} catch (NamingException e) {
				e.printStackTrace();
			}
		}	
		
	}

	public void onMessage(Message msg) {
		try {
			TextMessage txtMsg = (TextMessage) msg;
			System.out.println("Mensaje recibido:"+txtMsg.getText());
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}
	
	//////////////////////////////////////////////////////////////
	
	public static void main(String[] args) {

		_03_MessageListener ml = new _03_MessageListener();
		ml.conectar();
		
		JOptionPane.showMessageDialog(null, "HOLA");
		
	}
	
	
}

/*
class OyenteMensajes implements MessageListener{

	@Override
	public void onMessage(Message message) {
		// TODO Auto-generated method stub
		
	}
	
}
*/