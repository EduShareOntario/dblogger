import dblogger.DbLogService
import org.apache.activemq.ActiveMQConnectionFactory
import org.springframework.jms.config.SimpleJmsListenerContainerFactory
import org.springframework.jms.core.JmsMessagingTemplate
import org.springframework.jms.core.JmsTemplate

//import org.apache.activemq.jms.pool.PooledConnectionFactory
//import org.apache.activemq.spring.ActiveMQConnectionFactory

// Place your Spring DSL code here
beans = {
    // the following has no effect on jmsConnectionFactory being undefined
//    springConfig.addAlias 'pooledJmsConnectionFactory', 'jmsConnectionFactory'
    // The following bombs with resources null!
//    springConfig.addBeanConfiguration("jmsConnectionFactory", springConfig.getBeanConfig("pooledJmsConnectionFactory"));
//    jmsConnectionFactory(PooledConnectionFactory) {
//        connectionFactory = { ActiveMQConnectionFactory cf ->
//            brokerURL = 'tcp://localhost:61716'
//        }
//    }
//    loggingEventJmsConnectionFactory(ActiveMQConnectionFactory)
//    connectionFactory(ActiveMQConnectionFactory)
//    jmsTemplate(JmsTemplate) {
//        connectionFactory = loggingEventJmsConnectionFactory
//    }
    // todo: make sure the jmsTemplate bean is provided automatically
    dbLogService(DbLogService)
//    loggingEventJmsContainerFactory(SimpleJmsListenerContainerFactory, loggingEventJmsConnectionFactory)
}
