<html>
<head>
	<meta name="layout" content="${layoutRegister}"/>
	<s2ui:title messageCode='spring.security.ui.forgotPassword.title'/>
</head>
<body>
<s2ui:formContainer type='forgotPassword' focus='username' width='50%' >
	<s2ui:form beanName='forgotPasswordCommand' useToken="true">
		<g:if test='${emailSent}'>
			<br/><h3>&nbsp;&nbsp;&nbsp;<g:message code='spring.security.ui.forgotPassword.sent'/></h3>
		</g:if>
		<g:else>
		<br/>
		<div style="margin: 10px;"><g:message code='spring.security.ui.forgotPassword.description'/></div>
		<table>
			<s2ui:textFieldRow name='username' size='20' labelCodeDefault='Username'/>
		<%--
		<s2ui:submitButton elementId='submit' messageCode='spring.security.ui.forgotPassword.submit'/>
 		--%>
			<tr>
				<td colspan="1">
					<button type="submit" value="Register">Save</button>
				</td>
			</tr>
		</table>
		</g:else>
	</s2ui:form>
</s2ui:formContainer>
</body>
</html>
