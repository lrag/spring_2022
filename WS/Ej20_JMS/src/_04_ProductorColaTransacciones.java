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

//http://pastie.org/10825100
public class _04_ProductorColaTransacciones {

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
			QueueSession sesion = qcx.createQueueSession(true, QueueSession.AUTO_ACKNOWLEDGE);
			
			Queue cola = (Queue) ic.lookup("jms/cola");
			MessageProducer productor = sesion.createProducer(cola);

			//Arrancamos la conexión (necesario si tenemos transacción)
			qcx.start();
			
			//Para enviar los mensajes necesitamos la sesion y el productor
			TextMessage txtMsg = sesion.createTextMessage("HOLA RADIOLA");
			productor.send(txtMsg);
			TextMessage txtMsg2 = sesion.createTextMessage("HOLO, QUE ES MÁS GORDO");
			productor.send(txtMsg2);
			
			//Hacemos commit o rollback según nos convenga
			sesion.commit();
			//sesion.rollback();

			TextMessage txtMsg3 = sesion.createTextMessage("333");
			productor.send(txtMsg3);
			TextMessage txtMsg4 = sesion.createTextMessage("444");
			productor.send(txtMsg4);			
			sesion.rollback();
			
			System.out.println("Mensaje enviado");
			
			sesion.close();
			qcx.close();
		
		} catch (NamingException e) {
			e.printStackTrace();
		} catch (JMSException e) {
			e.printStackTrace();
		}		
	}		
}
