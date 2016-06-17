package dblogger

import org.apache.log4j.spi.LoggingEvent

class LogEvent {

    static int MAX_STRING_LENGTH = 4000
    static int DEFAULT_STRING_LENGTH = 255

    String namespace
    String runtimeContext
    String loggerName
    String severity /*level is reserved!*/
    Date timestamp
    String message
    String throwableInformation

    /*
    I tried using a custom constructor LogEvent(LoggingEvent event, String ns) but
    the custom setters wouldn't get called reliably!
     */
    static LogEvent createLogEvent(LoggingEvent event, String ns=null) {
        LogEvent e = new LogEvent([namespace: ns,
                    runtimeContext: event.threadName,
                    loggerName : event.loggerName,
                    severity : event.level.toString(),
                    timestamp : new Date(event.timeStamp),
                    message : event.renderedMessage.toString(),
                    throwableInformation : event.throwableInformation?.throwableStrRep])
        return e
    }
    static mapping = {
        datasource 'georgian'
        table name: "log_event", schema: "georgian"
        id generator: 'native'
        sort "id"
    }
    static constraints = {
        namespace nullable: true, maxSize: 255
        loggerName nullable: false, maxSize: 255
        severity nullable: true, maxSize: 20
        timestamp nullable: false
        message nullable: false, maxSize: 4000
        runtimeContext nullable: true, maxSize: 4000
        throwableInformation nullable:  true, maxSize: 4000
    }

    public void setNamespace(String value) {
        //TODO: replace with lookup of maxSize constraint
        this.namespace = trimToLength(value)
    }
    public void setLoggerName(String value) {
        //TODO: replace with lookup of maxSize constraint
        this.loggerName = trimToLength(value)
    }
    public void setSeverity(String value) {
        //TODO: replace with lookup of maxSize constraint
        this.severity = trimToLength(value, 20)
    }
    public void setMessage(String value) {
        //TODO: replace with lookup of maxSize constraint
        this.message = trimToLength(value, MAX_STRING_LENGTH)
    }
    public void setRuntimeContext(String value) {
        //TODO: replace with lookup of maxSize constraint
        this.runtimeContext = trimToLength(value, MAX_STRING_LENGTH)
    }
    public void setThrowableInformation(String value) {
        //TODO: replace with lookup of maxSize constraint
        this.throwableInformation = trimToLength(value, MAX_STRING_LENGTH)
    }

    def trimToLength(String s, int maxLength = DEFAULT_STRING_LENGTH) {
        def trimmed = s?.length() > maxLength ? s.substring(0,maxLength-2) + '*' : s
    }
}
