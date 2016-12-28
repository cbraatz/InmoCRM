package crm;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.core.util.StatusPrinter;

public class CrmLogger {

	public CrmLogger() {
		
	}
	public static void logInfo(Class fromClass, String message){
		LoggerContext lc = (LoggerContext) LoggerFactory.getILoggerFactory();
		StatusPrinter.print(lc);
		final Logger log = LoggerFactory.getLogger(fromClass);
		log.info message
	}
	public static void logException(Class fromClass, String message, Exception exception){
		LoggerContext lc = (LoggerContext) LoggerFactory.getILoggerFactory();
		StatusPrinter.print(lc);
		final Logger log = LoggerFactory.getLogger(fromClass);
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		exception.printStackTrace(pw);
		log.error "Message: ${message}\nError: ${exception.message}\nStacktrace:\n ${sw.toString()}"
	}
	public static void logError(Class fromClass, String message){
		LoggerContext lc = (LoggerContext) LoggerFactory.getILoggerFactory();
		StatusPrinter.print(lc);
		final Logger log = LoggerFactory.getLogger(fromClass);
		log.error "Message: ${message}"
	}
}
