package de.fh_dortmund.swt2.fake_service.utils.messaging;

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
import org.javatuples.Pair;
import org.springframework.stereotype.Component;

import de.fh_dortmund.swt2.fake_service.model.IObserver;

@Component
public class MqttImpl extends MqttConfig implements MqttCallback{

	//private static final String fota_fetch_record = "fota_fetch_record";
    private String brokerUrl = null;
    final private String colon = ":";
    final private String clientId = UUID.randomUUID().toString();

    private MqttClient mqttClient = null;
    private MqttConnectOptions connectionOptions = null;
    private MemoryPersistence persistence = null;
 	private List<Pair<String, IObserver>> topicObserver;
	
	private static MqttImpl instance;

	private MqttImpl() {
		topicObserver = new LinkedList<Pair<String, IObserver>>();
        this.config();
    }

    private MqttImpl(String broker, Integer port, Boolean ssl, Boolean withUserNamePass) {
		topicObserver = new LinkedList<Pair<String, IObserver>>();
        this.config(broker, port, ssl, withUserNamePass);
    }

   	public static MqttImpl getInstance() {
		if(instance == null)
			instance = new MqttImpl();
        return instance;
    }

   
    public static MqttImpl getInstance(String broker, Integer port, Boolean ssl, Boolean withUserNamePass) {
        if(instance == null)
			instance = new MqttImpl(broker, port, ssl, withUserNamePass);

		return instance;
    }

    public void publishMessage(String topic, String message) {

        try {
            MqttMessage mqttmessage = new MqttMessage(message.getBytes());
            mqttmessage.setQos(this.qos);
            mqttmessage.setRetained(false);
            this.mqttClient.publish(topic, mqttmessage);
        } catch (MqttException me) {
        }
    }
	
	public void detach(IObserver observer) {
		topicObserver.remove(topicObserver.stream().filter(x -> x.getValue1() == observer).findFirst().get());
	}
    
    public void disconnect() {
        try {
            this.mqttClient.disconnect();
        } catch (MqttException me) {
        }
    }

    @Override
    public void connectionLost(Throwable cause) {
		this.config();
    }

    @Override
    public void deliveryComplete(IMqttDeliveryToken arg0) {
		
    }

    
    @Override
    public void messageArrived(String topic, MqttMessage message) throws Exception {
    	topicObserver.stream().filter(p -> p.getValue0() == topic).forEach(p -> p.getValue1().update(new String(message.getPayload())));
    }

	public void subscribeMessage(String topic, IObserver observer) {
		try {
			this.mqttClient.subscribe(topic, this.qos);
			topicObserver.add(new Pair<String, IObserver>(topic, observer));
		} catch(MqttException me) {
			
		}
	}

    @Override
	protected void config(String broker, Integer port, Boolean ssl, Boolean withUserNamePass) {
        String protocal = this.TCP;

        this.brokerUrl = protocal + broker + colon + port;
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
