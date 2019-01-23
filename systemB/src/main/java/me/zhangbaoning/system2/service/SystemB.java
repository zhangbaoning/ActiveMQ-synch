package me.zhangbaoning.system2.service;

import me.zbn.ActiveMQsynch.entity.Test1;
import me.zhangbaoning.system2.dao.Test1Dao;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.hibernate.FlushMode;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Component;

import javax.jms.*;
import javax.persistence.EntityManagerFactory;

/**
 * @author: zhangbaoning
 * @date: 2019/1/22
 * @since: JDK 1.8
 * @description: TODO
 */
@Component
public class SystemB implements ApplicationRunner {
    @Autowired
    private Test1Dao test1Dao;
    @Autowired
    private EntityManagerFactory factory;
    @Override
    public void run(ApplicationArguments args) throws Exception {

        //1、创建工厂连接对象，需要制定ip和端口号
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://127.0.0.1:61616");
        // 让能收到所有实体类消息
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
        MessageConsumer consumer = session.createConsumer(queue);
        //7、向consumer对象中设置一个messageListener对象，用来接收消息
        consumer.setMessageListener(new MessageListener() {
            @Override
            public void onMessage(Message message) {
                if (message instanceof ObjectMessage) {
                    ObjectMessage objectMessage = (ObjectMessage) message;
                    try {
                        System.out.println("接受到消息");
                        Object object = objectMessage.getObject();
                        if (object instanceof Test1){
                            test1Dao.save( (Test1) object);
                        }
                    } catch (JMSException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        //8、程序等待接收用户消息
        System.in.read();
        //9、关闭资源
        consumer.close();
        session.close();
        connection.close();
    }
}
