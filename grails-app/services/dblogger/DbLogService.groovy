package dblogger

import grails.transaction.Transactional
import org.springframework.jms.annotation.JmsListener
import org.springframework.jms.listener.AbstractJmsListeningContainer
import org.springframework.stereotype.Component

@Transactional
@Component
class DbLogService {

    def grailsApplication

//    @JmsListener(destination = "loggingEvent", containerFactory = "loggingEventJmsContainerFactory")
    @JmsListener(destination = "loggingEvent")
    def onMessage(event){
        AbstractJmsListeningContainer
        // Avoid recursion for this class logging it's own errors!
        if (event.loggerName != "grails.app.services.log.DbLogService") {
            def logEvent = LogEvent.createLogEvent(event, grailsApplication.config.dependency.dbLogService.namespace?:null)
            try {
                if (!logEvent.save()) {
                    log.error("LogEvent.save() failed.\nErrors:${logEvent.errors.allErrors}")
                }
            } catch (Exception e) {
                log.error("LogEvent.save() failed with exception", e)
            }
        }
    }

}
