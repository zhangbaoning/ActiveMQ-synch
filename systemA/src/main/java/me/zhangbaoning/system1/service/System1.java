package me.zhangbaoning.system1.service;

import me.zhangbaoning.system1.dao.Test1Dao;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import javax.jms.*;
import java.util.List;

/**
 * @author: zhangbaoning
 * @date: 2019/1/22
 * @since: JDK 1.8
 * @description: TODO
 */
@Component
public class System1 implements ApplicationRunner {
    @Autowired
    private Test1Dao test1Dao;
    @Override
    public void run(ApplicationArguments args){
        try {
            //1、创建工厂连接对象，需要制定ip和端口号
            ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://127.0.0.1:61616");
            ((ActiveMQConnectionFactory) connectionFactory).setTrustAllPackages(true);
            //2、使用连接工厂创建一个连接对象
            Connection connection = connectionFactory.createConnection();
            //3、开启连接
            connection.start();
            //4、使用连接对象创建会话（session）对象
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            //5、使用会话对象创建目标对象，包含queue和topic（一对一和一对多）
            Queue queue = session.createQueue("test-queue");
            //6、使用会话对象创建生产者对象
            MessageProducer producer = session.createProducer(queue);
            //7、使用会话对象创建一个消息对象
            List list = test1Dao.findAll();
            System.out.println(list);
            test1Dao.findAll().forEach(
                    test1 -> {
                        TextMessage textMessage = null;
                        try {
                          ObjectMessage objectMessage=   session.createObjectMessage(test1);
                            //8、发送消息
                            producer.send(objectMessage);
                        } catch (JMSException e) {
                            e.printStackTrace();
                        }
                    }
            );

            //9、关闭资源
            producer.close();
            session.close();
            connection.close();
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

}
