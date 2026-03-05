import java.util.Properties;

import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueSession;
import javax.jms.TextMessage;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;


public class _02_ConsumidorSincrono {

	public static void main(String[] args) {
		
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
			QueueConnection qCx = queueConnFac.createQueueConnection();
			//El primer parametro indica si la sesion es transaccional o no
			//El segundo el modo en el que se reciben los mensajes
			QueueSession qSession = qCx.createQueueSession(false, QueueSession.AUTO_ACKNOWLEDGE);
			Queue cola = (Queue) ic.lookup("jms/cola");			
			MessageConsumer consumidor = qSession.createConsumer(cola);
			
			//Para recibir mensajes es necesario arrancar la conexión
			qCx.start();
			
			TextMessage txtMsg = (TextMessage) consumidor.receive();
			//consumidor.receive(10000); Espera diez segundos
			//consumidor.receiveNoWait() No espera
			
			System.out.println("Mensaje recibido:"+txtMsg.getText());
			
			qCx.stop();
			qCx.close();
			
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
	
	
}
