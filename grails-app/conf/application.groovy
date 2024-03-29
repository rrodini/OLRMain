// This groovy file is dedicated to Spring Security settings.

import org.slf4j.Logger
import org.slf4j.LoggerFactory

Logger log = LoggerFactory.getLogger(this.class.name)
// Added by the Spring Security Core plugin:
grails.plugin.springsecurity.userLookup.userDomainClassName = 'org.olr.admin.User'
grails.plugin.springsecurity.userLookup.authorityJoinClassName = 'org.olr.admin.UserRole'
grails.plugin.springsecurity.authority.className = 'org.olr.admin.Role'
// password complexity
grails.plugin.springsecurity.ui.password.minLength = 6
grails.plugin.springsecurity.ui.password.maxLength = 32
grails.plugin.springsecurity.ui.password.validationRegex = "^.*(?=.*\\d)(?=.*[a-zA-Z]).*\$"  // don't require special characters

// remember me cookie
rememberMe.cookieName = "OLR_remember_me"
rememberMe.key = "OLR_Rocks"
rememberMe.tokenValiditySeconds = 1209600   // 14 days
// Added as per GRIANA2 book
grails.plugin.springsecurity.userLookup.usernamePropertyName = "username"
grails.plugin.springsecurity.userLookup.passwordPropertyName = "password"
grails.plugin.springsecurity.authority.nameField = "authority"
grails.plugin.springsecurity.logout.postOnly=false
// hierarchical roles added
grails.plugin.springsecurity.roleHierarchy = '''
  ROLE_ADMIN > ROLE_USER
'''
// registration settings
grails.plugin.springsecurity.ui.register.requireEmailValidation = true
grails.plugin.springsecurity.ui.register.emailBody = "Welcome \${user.username} to Open Lightning Round.  To finish registration " + "<a href='\${url}'>click here</a>."
grails.plugin.springsecurity.ui.register.emailFrom = "admin@openlightninground.com"
grails.plugin.springsecurity.ui.register.emailSubject = "OLR registration"
grails.plugin.springsecurity.ui.register.postRegisterUrl = '/logout'
// forgot password settings
grails.plugin.springsecurity.ui.forgotPassword.requireForgotPassEmailValidation = true
grails.plugin.springsecurity.ui.forgotPassword.emailBody = "So \${user.username}, you forgot your OLR password? This happens to everyone!  Just " + "<a href='\${url}'>click here</a> to reset your password."
grails.plugin.springsecurity.ui.forgotPassword.emailFrom = "admin@openlightninground.com"
grails.plugin.springsecurity.ui.forgotPassword.emailSubject = "OLR forgotten password"
grails.plugin.springsecurity.ui.forgotPassword.postRegisterUrl = '/'

// default if staticRules which requires controller annotations
//  changed to InterceptMap for "declarative" security
grails.plugin.springsecurity.securityConfigType = "InterceptUrlMap"
// fire the authentication success event to track last login timestamp
grails.plugin.springsecurity.useSecurityEventListener = true
grails.plugin.springsecurity.onInteractiveAuthenticationSuccessEvent = { e, appCtx ->
// handle InteractiveAuthenticationSuccessEvent
	org.olr.admin.User.withTransaction {
		def username= e.getAuthentication().getPrincipal().username
		log.error("login: ${username}")
		def user = org.olr.admin.User.findByUsername(username)
		if (user) {
			user.lastLoginTime = new Date()
			user.save(failOnError: true)
		} else {
			log.error("login: cannot find ${username} by name!")
		}
	}
}
// handle AuthenticationFailureEvent - NOT WORKING
grails.plugin.springsecurity.onAuthenticationFailureBadCredentialsEvent = { e , appCtx ->
	println "Bad credentials entered."
}

// enable file uploads (multipart requests)
grails.web.disable.multipart=false
// end additions

grails.plugin.springsecurity.interceptUrlMap = [
	[pattern: '/',               access: ['permitAll']],
	[pattern: '/error',          access: ['permitAll']],
	[pattern: '/index',          access: ['permitAll']],
	[pattern: '/index.gsp',      access: ['permitAll']],
	[pattern: '/shutdown',       access: ['permitAll']],
	[pattern: '/assets/**',      access: ['permitAll']],
	[pattern: '/**/js/**',       access: ['permitAll']],
	[pattern: '/**/css/**',      access: ['permitAll']],
	[pattern: '/**/images/**',   access: ['permitAll']],
	[pattern: '/**/favicon.ico', access: ['permitAll']],
	[pattern: '/login/**',       access: ['permitAll']],
	[pattern: '/logout/**',      access: ['permitAll']],
	[pattern: '/register/**',    access: ['permitAll']],
// TODO: review the 2 lines below
	[pattern: '/endGame/**',     access: ['permitAll']],
	[pattern: '/gameSummary/**', access: ['permitAll']],
//  below requires package naming convention plus custom url mappings
	[pattern: '/index_admin/**', access: ['ROLE_ADMIN']],
	[pattern: '/admin/**',       access: ['ROLE_ADMIN']],
	[pattern: '/**/',            access: ['ROLE_USER']],
]
// below is unaltered
grails.plugin.springsecurity.filterChain.chainMap = [
	[pattern: '/assets/**',      filters: 'none'],
	[pattern: '/**/js/**',       filters: 'none'],
	[pattern: '/**/css/**',      filters: 'none'],
	[pattern: '/**/images/**',   filters: 'none'],
	[pattern: '/**/favicon.ico', filters: 'none'],
//	[pattern: '/logout/**',      filters: 'none'],
//	[pattern: '/logoff/**',      filters: 'none'],
	[pattern: '/**',             filters: 'JOINED_FILTERS']
]
// below is to force all traffic over HTTPS connection
environments {
	development{
		grails.plugin.springsecurity.secureChannel.definition = [
				[pattern: '/**',             access: 'REQUIRES_INSECURE_CHANNEL'],
		]
	}

	production{
		// lines below are only for production
		grails.plugin.springsecurity.successHandler.defaultTargetUrl = "https://olrmain.herokuapp.com/"
//		grails.plugin.springsecurity.logout.afterLogoutUrl = "https://olrmain.herokuapp.com"
		grails.serverURL = "https://olrmain.herokuapp.com"
		grails.plugin.springsecurity.auth.forceHttps = true
		grails.plugin.springsecurity.secureChannel.useHeaderCheckChannelSecurity = true
		grails.plugin.springsecurity.secureChannel.secureHeaderName = 'X-Forwarded-Proto'
		grails.plugin.springsecurity.secureChannel.secureHeaderValue = 'http'
		grails.plugin.springsecurity.secureChannel.insecureHeaderName = 'X-Forwarded-Proto'
		grails.plugin.springsecurity.secureChannel.insecureHeaderValue = 'https'
		grails.plugin.springsecurity.portMapper.httpPort = 80
		grails.plugin.springsecurity.portMapper.httpsPort = 443
		grails.plugin.springsecurity.secureChannel.definition = [
				[pattern: 'assets/**',	  access: 'ANY_CHANNEL'],
				[pattern: '/**',          access: 'REQUIRES_SECURE_CHANNEL'],
		]
	}
}