<html>
<head>
	<meta name="layout" content="${layoutRegister}"/>
	<s2ui:title messageCode='spring.security.ui.resetPassword.title'/>
</head>
<body>
<s2ui:formContainer type='resetPassword' focus='password' width='475px'>
	<s2ui:form beanName='resetPasswordCommand'>
		<g:hiddenField name='t' value='${token}'/>
		<div class="sign_in">
			<br/>
			<h3>&nbsp;&nbsp;&nbsp;<g:message code='spring.security.ui.resetPassword.description'/></h3>
			<table>
				<s2ui:passwordFieldRow name='password' labelCodeDefault='Password'/>
				<s2ui:passwordFieldRow name='password2' labelCodeDefault='Password (again)'/>
			<%--
			<s2ui:submitButton elementId='submit' messageCode='spring.security.ui.resetPassword.submit'/>
			--%>
			<tr>
				<td colspan="1">
					<button type="submit" value="Register">Send</button>
				</td>
			</tr>
			</table>
		</div>
	</s2ui:form>
</s2ui:formContainer>
</body>
</html>
