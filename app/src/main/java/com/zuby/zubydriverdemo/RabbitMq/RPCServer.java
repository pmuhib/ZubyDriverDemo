package com.zuby.zubydriverdemo.RabbitMq;

import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Envelope;

import java.io.IOException;
import java.util.Random;

import org.json.JSONException;
import org.json.JSONObject;

public class RPCServer 
{

  private static final String RPC_QUEUE_NAME = "rpc_queueCAB7890_00000002";
  private Connection connection;
  private Channel channel;
  private static Consumer consumer;

  public RPCServer() throws IOException
  {
    ConnectionFactory factory = new ConnectionFactory();
    factory.setHost("ec2-13-210-165-51.ap-southeast-2.compute.amazonaws.com");
    factory.setUsername("citymapper");
    factory.setPassword("citymapper");
    factory.setPort(5672);
    factory.setVirtualHost("dispatch");

		
	

    connection = factory.newConnection();
    channel = connection.createChannel();
    channel.queueDeclare(RPC_QUEUE_NAME, false, false, false, null);

    channel.basicQos(1);

  }

  public void startConsuming() throws IOException
  {
      consumer = new DefaultConsumer(channel) 
      {
        @Override
        public void handleDelivery(
		String consumerTag, Envelope envelope
		, AMQP.BasicProperties properties, byte[] body) 
                 throws IOException {
              AMQP.BasicProperties replyProps = new AMQP.BasicProperties.Builder()
                  .correlationId(properties.getCorrelationId())
                  .build();


          try 
          {
            String message = new String(body,"UTF-8");

            System.out.println("received client request"+" "+message);
            System.out.println(
		"replyTo and correlation id"+"    correlation id     "+properties.getCorrelationId()+"  replyto  "+properties.getReplyTo()); 
            
            
          }
          catch (RuntimeException e){
            System.out.println(" [.] " + e.toString());
          }
          finally 
          {
        	  String response = "";
        	//  Random rand = new Random();
        	//  int n = rand.nextInt(2) ;

        	// System.out.println("integer"+" "+n);
        	 
        	// if(n==0)
        	// {
        	//	 response = "accept";
        	// }
        	// else
        	// {
        	//	 response = "reject";
        	// }

		JSONObject jsonobject = new JSONObject();
      		try
      		{
		jsonobject.put("message", "True");
		jsonobject.put("driver_id", "Mayank");
		jsonobject.put("driver_lat", "23.33");
		jsonobject.put("driver_long", "23.54");
		jsonobject.put("car_id", "1234");
      		} 
     		 catch (JSONException e) 
		{
		// TODO Auto-generated catch block
		e.printStackTrace();
    		  }	
	
		response = jsonobject.toString();		

		System.out.println("response"+" "+response);
        	  
            channel.basicPublish( "", properties.getReplyTo(), replyProps, response.getBytes("UTF-8"));
            channel.basicAck(envelope.getDeliveryTag(), false);
            // RabbitMq consumer worker thread notifies the RPC server owner thread 
            synchronized(this) 
            {
            	this.notify();
            }
          }
        }
      };
    
      while (true) {
        System.out.println(" [x] Awaiting RPC requests");

        channel.basicConsume(RPC_QUEUE_NAME, false, consumer);
        //Wait and be prepared to consume the message from RPC client.
      	synchronized(consumer) {
      	    try {
      			consumer.wait();
      			
      	    } catch (InterruptedException e) 
      		{
      	    	e.printStackTrace();	    	
      	    }
      	}
      }
  
  }  

  public void deleteAndClose() throws IOException {
      channel.queueDelete(RPC_QUEUE_NAME);
      connection.close();
  }

  public static void main(String[] argv) 
  {
    RPCServer rpcserver = null;

    try
    {

      rpcserver = new RPCServer();
      rpcserver.startConsuming();

    } catch (IOException  e) {

          e.printStackTrace();

    } finally {
       if (rpcserver != null)
        try {
	  rpcserver.deleteAndClose();
          
        } catch (IOException _ignore) {}
    }
  }
}
