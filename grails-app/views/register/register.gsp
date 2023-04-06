<html>
<head>
	<meta name="layout" content="register"/>
	<s2ui:title messageCode='spring.security.ui.register.title'/>
	<style>
	table {
		border-collapse: collapse;
		table-layout: fixed;
		width: 310px;
	}
	table td {
		/* border: solid 1px #666; */
		width: 110px;
		word-wrap: break-word;
	}
	</style>

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
			<tr><td colspan="2"><b>You must be 13 years or older to register at this site. If you are younger than 13, have your parent register.</b></td></tr>
			<s2ui:textFieldRow name='username' size='20' labelCodeDefault='Username' required='true' />
			<s2ui:textFieldRow name='email' size='20' labelCodeDefault='E-mail' required='true' />
			<s2ui:passwordFieldRow name='password' size='20' labelCodeDefault='Password' required='true' />
			<s2ui:passwordFieldRow name='password2' size='20' labelCodeDefault='Password (again)' required='true'/>
			<s2ui:textFieldRow name='firstName' size='20' labelCodeDefault='First name' required='true'/>
			<s2ui:textFieldRow name='lastName' size='20' labelCodeDefault='Last name' required='true'/>
			<tr><td colspan="2"><b>Please provide this information, if it is known:</b></td></tr>
			<s2ui:textFieldRow name='org' size='20' labelCodeDefault='Organization'  />
			<s2ui:textFieldRow name='orgAddress' size='20' labelCodeDefault='Address'/>
			<s2ui:textFieldRow name='orgCity' size='20' labelCodeDefault='City'  />
			<s2ui:textFieldRow name='orgState' size='20' labelCodeDefault='State' />
			<s2ui:textFieldRow name='orgZip' size='5' labelCodeDefault='Zip' />
		<%--
		<s2ui:submitButton elementId='submit' messageCode='spring.security.ui.register.submit'/>
		--%>
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
