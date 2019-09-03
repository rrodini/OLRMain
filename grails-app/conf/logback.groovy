import ch.qos.logback.classic.Level
import grails.util.BuildSettings
import grails.util.Environment
import grails.core.GrailsApplication
import org.springframework.boot.logging.logback.ColorConverter
import org.springframework.boot.logging.logback.WhitespaceThrowableProxyConverter

import java.nio.charset.Charset

conversionRule 'clr', ColorConverter
conversionRule 'wex', WhitespaceThrowableProxyConverter
GrailsApplication grailsApplication

// See http://logback.qos.ch/manual/groovy.html for details on configuration
appender('STDOUT', ConsoleAppender) {
    encoder(PatternLayoutEncoder) {
        charset = Charset.forName('UTF-8')

        pattern =
                '%clr(%d{yyyy-MM-dd HH:mm:ss.SSS}){faint} ' + // Date
                        '%clr(%5p) ' + // Log level
                        '%clr(---){faint} %clr([%15.15t]){faint} ' + // Thread
                        '%clr(%-40.40logger{39}){cyan} %clr(:){faint} ' + // Logger
                        '%m%n%wex' // Message
    }
}

def targetDir = BuildSettings.TARGET_DIR
if (Environment.isDevelopmentMode() && targetDir != null) {
    appender("FULL_STACKTRACE", FileAppender) {
        file = "${targetDir}/stacktrace.log"
        append = true
        encoder(PatternLayoutEncoder) {
            pattern = "%level %logger - %msg%n"
        }
    }
    logger("StackTrace", ERROR, ['FULL_STACKTRACE'], false)
}
// original below
//root(ERROR, ['STDOUT'])
// Heroku expects all logs to STDOUT
println  "LOG_LEVEL: ${System.getenv("LOG_LEVEL")}"
println  "GRAILS_ENV: ${System.getenv("GRAILS_ENV")}"
println  "Environment.current: ${Environment.current}"
println  "tomcat: ${GrailsApplication.config.getProperty('server.tomcat.remote-ip-header')}"
println  "tomcat: ${GrailsApplication.config.getProperty('server.tomcat.protocol-header')}"
def level = ERROR
switch (System.getenv("LOG_LEVEL")) {
    case "TRACE": level = TRACE; break;   // most granular
    case "DEBUG": level = DEBUG; break;
    case "INFO":  level = INFO; break;
    case "WARN":  level = WARN; break;
    case "ERROR": level = ERROR; break;  // least granular
    case "ALL":   level = ALL; break;
    case "OFF":   level = OFF; break;
}

root(level, ['STDOUT'])
// what to log?
//logger 'org.springframework.security', TRACE, ['STDOUT'], false
//logger 'grails.plugin.springsecurity', DEBUG, ['STDOUT'], false
//logger 'grails.plugin.formfields', TRACE, ['STDOUT'], false
