package dblogger

import grails.core.GrailsApplication
import grails.util.Environment
import grails.plugins.*

class ApplicationController implements PluginManagerAware {

    GrailsApplication grailsApplication
    GrailsPluginManager pluginManager

    def index() {
        log.info("hello todd")
        [grailsApplication: grailsApplication, pluginManager: pluginManager]
    }
}
