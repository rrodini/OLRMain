<g:set var='securityConfig' value='${applicationContext.springSecurityService.securityConfig}'/>
<html>
<head>
<%--
<meta name="layout" content="${layoutUi}"/>
--%>
	<meta name="layout" content="main"/>
<s2ui:title messageCode='spring.security.ui.login.title'/>
<%--
<asset:stylesheet src='spring-security-ui-auth.css'/>
--%>
</head>
<body>
<p/>
<div class="login s2ui_center ui-corner-all" style='text-align:center;'>
<!-- div class="login-inner" -->
	<s2ui:form type='login' focus='username'>
	<div class="sign-in">
<h2><g:message code='spring.security.ui.login.signin'/></h2>
	<g:if test="${params? params.login_error : 0}">
		<div class="errors" role="alert">params.login_error</div>
	</g:if>
<table>
<tr>
<td><label for="username"><g:message code='spring.security.ui.login.username'/></label></td>
<td><input type="text" name="${securityConfig.apf.usernameParameter}" id="username" class='formLogin' size="20"/></td>
</tr>
<tr>
<td><label for="password"><g:message code='spring.security.ui.login.password'/></label></td>
<td><input type="password" name="${securityConfig.apf.passwordParameter}" id="password" class="formLogin" size="20"/></td>
</tr>
	<tr>
	<td colspan="1">
		<span class="forgot-link">
			<g:link controller='register' action='forgotPassword'><g:message code='spring.security.ui.login.forgotPassword'/></g:link>
		</span>
	</td>
	<td colspan='1'>
		<input type="checkbox" class="checkbox" name="${securityConfig.rememberMe.parameter}" id="remember_me" />
		<label for='remember_me'><g:message code='spring.security.ui.login.rememberme'/></label>
	</td>
	</tr>
	<tr>
	<td colspan='1'>
		<%--
		<s2ui:linkButton elementId='register' controller='register' messageCode='spring.security.ui.login.register'/>
		<s2ui:submitButton  elementId='loginButton' text="Login"  />
		--%>
		<button type="submit" value="Login">Login</button>
	</td>
	</tr>
</table>
</div>
	</s2ui:form>
</div>
</body>
</html>
