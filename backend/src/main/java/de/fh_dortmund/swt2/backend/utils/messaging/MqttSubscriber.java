package de.fh_dortmund.swt2.backend.utils.messaging;

import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.springframework.stereotype.Component;

import de.fh_dortmund.swt2.backend.utils.observer.IObserver;

@Component
public class MqttSubscriber extends MqttConfig implements MqttCallback {
	
	private String brokerUrl = null;
    final private String colon = ":";
    final private String clientId = UUID.randomUUID().toString();

    private MqttClient mqttClient = null;
    private MqttConnectOptions connectionOptions = null;
    private MemoryPersistence persistence = null;

	private List<IObserver> mqttObservers;

	private MqttSubscriber() {
		mqttObservers = new LinkedList<IObserver>();
        this.config();
    }

    private MqttSubscriber(String broker, Integer port, Boolean ssl, Boolean withUserNamePass) {
		mqttObservers = new LinkedList<IObserver>();
        this.config(broker, port, ssl, withUserNamePass);
    }

   	public static MqttSubscriber getInstance() {
        return new MqttSubscriber();
    }

   
    public static MqttSubscriber getInstance(String broker, Integer port, Boolean ssl, Boolean withUserNamePass) {
		return new MqttSubscriber(broker, port, ssl, withUserNamePass);
    }

	public void registerObserver(IObserver observer)
	{
		System.out.println("Obserser registered.");
		mqttObservers.add(observer);
	}

	public void removeObserver(IObserver observer)
	{
		mqttObservers.remove(observer);
	}

	public void subscribeMessage(String topic) {
		try {
			this.mqttClient.subscribe(topic, this.qos);
			System.out.println("Subscribe " + topic);
		} catch (MqttException me) {
			System.out.println("Cannt Read Topic "+topic);
		}
	}

	@Override
	public void connectionLost(Throwable cause) {
	}

	@Override
	public void messageArrived(String topic, MqttMessage message) throws Exception {
		System.out.println("Messag received: " + topic );
		for(IObserver o:mqttObservers) {
			o.update(topic, new String(message.getPayload()));
		}	
	}

	@Override
	public void deliveryComplete(IMqttDeliveryToken token) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'deliveryComplete'");
	}

	@Override
	protected void config(String broker, Integer port, Boolean ssl, Boolean withUserNamePass) {
        String protocol = this.TCP;

        this.brokerUrl = protocol + broker + colon + port;
        this.persistence = new MemoryPersistence();
        this.connectionOptions = new MqttConnectOptions();

        try {
            this.mqttClient = new MqttClient(brokerUrl, clientId, persistence);
            this.connectionOptions.setCleanSession(true);
            //this.connectionOptions.setPassword(this.password.toCharArray());
            //this.connectionOptions.setUserName(this.userName);
            this.mqttClient.connect(this.connectionOptions);
            this.mqttClient.setCallback(this);
        } catch (MqttException me) {
            //throw new com.bms.exceptions.MqttException("Not Connected");
        }
    }

    @Override
    protected void config() {
        this.brokerUrl = this.TCP + this.broker + colon + this.port;
        this.persistence = new MemoryPersistence();
        this.connectionOptions = new MqttConnectOptions();
        try {
            this.mqttClient = new MqttClient(brokerUrl, clientId, persistence);
            this.connectionOptions.setCleanSession(true);
            //this.connectionOptions.setPassword(this.password.toCharArray());
            //this.connectionOptions.setUserName(this.userName);
            this.mqttClient.connect(this.connectionOptions);
            this.mqttClient.setCallback(this);
        } catch (MqttException me) {
		//throw new com.bms.exceptions.MqttException("Not Connected");
        }
    }


}
