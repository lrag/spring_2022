import java.util.Properties;

import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueSession;
import javax.jms.TextMessage;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class _01_ProductorCola {

	public static void main(String[] args) throws NamingException {
		
		Properties p = new Properties();
		p.setProperty("java.naming.factory.initial", "com.sun.enterprise.naming.SerialInitContextFactory");
		p.setProperty("java.naming.factory.url.pkgs", "com.sun.enterprise.naming");
		p.setProperty("java.naming.factory.state", "com.sun.corba.ee.impl.presentation.rmi.JNDIStateFactoryImpl");
		p.setProperty("org.omg.CORBA.ORBInitialHost", "localhost");
		p.setProperty("org.omg.CORBA.ORBInitialPort", "3700");
		
		Context ic = new InitialContext(p);		
		System.out.println(ic);		
		
		try {

			QueueConnectionFactory queueConnFac = 
					(QueueConnectionFactory) ic.lookup("jms/queueConnectionFactory");
			QueueConnection qCx = queueConnFac.createQueueConnection();
			//El primer parametro indica si la sesion es transaccional o no
			//El segundo el modo en el que se reciben los mensajes
			QueueSession qSession = qCx.createQueueSession(false, QueueSession.AUTO_ACKNOWLEDGE);
			Queue cola = (Queue) ic.lookup("jms/cola");			
			MessageProducer productor = qSession.createProducer(cola);
			
			//Para enviar mensajes no es necesario arrancar la conexión
			qCx.start();
			TextMessage msg = qSession.createTextMessage("HOLA RADIOLA");
			productor.send(msg);
			
			qCx.stop();
			qCx.close();
			
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
