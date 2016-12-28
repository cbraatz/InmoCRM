// File: grails-app/conf/logback-grails.groovy
import grails.util.BuildSettings
import grails.util.Environment
 
import org.springframework.boot.ApplicationPid
 import java.nio.charset.Charset
 
// Log information about the configuration.
statusListener(OnConsoleStatusListener)
 
// Get PID for Grails application.
// We use it in the logging output.
if (!System.getProperty("PID")) {
    System.setProperty("PID", (new ApplicationPid()).toString())
}
 
conversionRule 'clr', org.springframework.boot.logging.logback.ColorConverter
conversionRule 'wex', org.springframework.boot.logging.logback.WhitespaceThrowableProxyConverter
 
// See http://logback.qos.ch/manual/groovy.html for details on configuration
appender('STDOUT', ConsoleAppender) {
    encoder(PatternLayoutEncoder) {
        charset = Charset.forName('UTF-8')
        pattern =
                '%clr(%d{yyyy/MM/dd HH:mm:ss.SSS}){faint} ' + // Date
                        '%clr(%5p) ' + // Log level
                        '%clr(%property{PID}){magenta} ' + // PID
                        '%clr(--**){faint} %clr([%15.15t]){faint} ' + // Thread
                        '%clr(%-40.40logger{39}){cyan} %clr(:){faint} ' + // Logger
                        '%m%n%wex' // Message
    }
}
 
//if(Environment.current == Environment.DEVELOPMENT) {
 
    //def targetDir = BuildSettings.TARGET_DIR
   // if(targetDir) {
 
appender("FILE", FileAppender) {
     //file = "${targetDir}/stacktrace.log"
	file = "fullLog.log"
    append = true
    encoder(PatternLayoutEncoder) {
         pattern = '%clr(%d{yyyy/MM/dd HH:mm:ss.SSS}){faint} ' + // Date
		 '%level %logger'+
		 '%clr(%5p) ' + // Log level
		 '%clr(%property{PID}){magenta} ' + // PID
		 '%clr(-->>){faint} %clr([%15.15t]){faint} ' + // Thread
		 '%clr(%-40.40logger{39}){cyan} %clr(:){faint} ' + // Logger
		 '%m%n%wex' // Message
    }
}
logger("root", WARN, ['FILE'], false )
