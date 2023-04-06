<html>
<head>
	<meta name="layout" content="register"/>
	<s2ui:title messageCode='spring.security.ui.register.title'/>
</head>
<body>
<s2ui:formContainer type='register' focus='username' width='800px'>
	<s2ui:form beanName='registerCommand'>
		<g:if test='${emailSent}'>
		<br/>
		&nbsp;&nbsp;&nbsp;<g:message code='spring.security.ui.register.sent'/>
		</g:if>
		<g:else>
		<br/>
		<table>
			<g:hiddenField name="version" value="${this?.version}" />
			<tr><td><b>You must be 13 years or older to register at this site.<br/>
				If you are younger than 13, have your parent register.</b></td></tr>
			<s2ui:textFieldRow name='username' size='40' labelCodeDefault='Username' required='true' />
			<s2ui:textFieldRow name='email' size='40' labelCodeDefault='E-mail' required='true' />
			<s2ui:passwordFieldRow name='password' size='40' labelCodeDefault='Password' required='true' />
			<s2ui:passwordFieldRow name='password2' size='40' labelCodeDefault='Password (again)' required='true'/>
			<s2ui:textFieldRow name='firstName' size='40' labelCodeDefault='First name' required='true'/>
			<s2ui:textFieldRow name='lastName' size='40' labelCodeDefault='Last name' required='true'/>
			<tr><td><b>Please provide this information, if it is known:</b></td></tr>
			<s2ui:textFieldRow name='org' size='40' labelCodeDefault='Organization'  />
			<s2ui:textFieldRow name='orgAddress' size='40' labelCodeDefault='Address'/>
			<s2ui:textFieldRow name='orgCity' size='40' labelCodeDefault='City'  />
			<s2ui:textFieldRow name='orgState' size='40' labelCodeDefault='State' />
			<s2ui:textFieldRow name='orgZip' size='5' labelCodeDefault='Zip' />
		<!--
		<s2ui:submitButton elementId='submit' messageCode='spring.security.ui.register.submit'/>
		-->
			<tr>
			<td colspan="1">
			<button type="submit" value="Register">Create</button>
			</td>
			</tr>
		</table>
		</g:else>
	</s2ui:form>
</s2ui:formContainer>
</body>
</html>
