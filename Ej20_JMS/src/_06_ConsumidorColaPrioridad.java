import java.util.Properties;

import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueSession;
import javax.jms.TextMessage;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;


public class _06_ConsumidorColaPrioridad {

	public static void main(String[] args) {
		
		Properties p = new Properties();
		p.setProperty("java.naming.factory.initial", "com.sun.enterprise.naming.SerialInitContextFactory");
		p.setProperty("java.naming.factory.url.pkgs", "com.sun.enterprise.naming");
		p.setProperty("java.naming.factory.state", "com.sun.corba.ee.impl.presentation.rmi.JNDIStateFactoryImpl");
		p.setProperty("org.omg.CORBA.ORBInitialHost", "localhost");
		p.setProperty("org.omg.CORBA.ORBInitialPort", "3700");		
		
		try {
			Context ic = new InitialContext(p);

			QueueConnectionFactory queueConnFac = (QueueConnectionFactory) ic.lookup("jms/queueConnectionFactory");
			QueueConnection qcx = queueConnFac.createQueueConnection(); 
			//1er parámetro: si hay transaccion o no
			//2º parámetro: Como se realiza el ACK del mensaje
			QueueSession sesion = qcx.createQueueSession(false, QueueSession.AUTO_ACKNOWLEDGE);
			
			Queue cola = (Queue) ic.lookup("jms/cola");
			MessageConsumer receptor = sesion.createConsumer(cola, " JMSPriority>4 AND cantidad>10 ");

			//Arrancamos la conexión
			qcx.start(); 
			
			//Para recibir los mensajes:
			TextMessage txtMsg = (TextMessage) receptor.receive();
			System.out.println("Mensaje recibido:"+txtMsg.getText());
			System.out.println("Prioridad:"+txtMsg.getJMSPriority());
			System.out.println("Cantidad:"+txtMsg.getIntProperty("cantidad"));
			
			sesion.close();
			qcx.close();
			
			
		} catch (NamingException e) {
			e.printStackTrace();
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}
}
