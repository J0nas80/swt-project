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

@Component
public class MqttSender extends MqttConfig implements MqttCallback{

	//private static final String fota_fetch_record = "fota_fetch_record";
    private String brokerUrl = null;
    final private String colon = ":";
    final private String clientId = UUID.randomUUID().toString();

    private MqttClient mqttClient = null;
    private MqttConnectOptions connectionOptions = null;
    private MemoryPersistence persistence = null;

	private MqttSender() {
        this.config();
    }

    private MqttSender(String broker, Integer port, Boolean ssl, Boolean withUserNamePass) {
        this.config(broker, port, ssl, withUserNamePass);
    }

   	public static MqttSender getInstance() {
        return new MqttSender();
    }

   
    public static MqttSender getInstance(String broker, Integer port, Boolean ssl, Boolean withUserNamePass) {
		return new MqttSender(broker, port, ssl, withUserNamePass);
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
		throw new UnsupportedOperationException("Unimplemented method 'messageArrived'");
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
