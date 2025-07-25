package de.fh_dortmund.swt2.backend.utils.messaging;

public abstract class MqttConfig {
   
   protected final String broker = System.getenv("MOSQUITTO_HOST");
   protected final int qos = 1;
   protected Boolean hasSSL = false; /*By default SSL is disabled */
   protected Integer port = Integer.parseInt(System.getenv("MOSQUITTO_PORT")); /* Default port */
   //protected final String userName = "your username";
   //protected final String password = "Password";
   protected final String TCP = "tcp://";
   protected final String SSL = "ssl://";

   /**
    * Custom Configuration
    * 
    * @param broker
    * @param port
    * @param ssl
    * @param withUserNamePass
    */
   protected abstract void config(String broker, Integer port, Boolean ssl, Boolean withUserNamePass);

   /**
    * Default Configuration
    */
   protected abstract void config();
   

}
