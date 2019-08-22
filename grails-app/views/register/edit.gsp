<html>
<head>
    <meta name="layout" content="main"/>
    <title>Edit Profile</title>
</head>
<body>
<s2ui:formContainer type="update" focus='firstName' width='800px'>
    <s2ui:form beanName='profileCommand'>
        <fieldset class="form">
            <table>
                <tbody>
                <g:hiddenField name="version" value="${profileCommand.version}" />
                <g:hiddenField name='username' value="${profileCommand.username}" />
                <s2ui:textFieldRow name='firstName' size='40' labelCodeDefault='First name' required='true'/>
                <s2ui:textFieldRow name='lastName' size='40' labelCodeDefault='Last name' required='true'/>
                <s2ui:textFieldRow name='email' size='40' labelCodeDefault='E-mail' required='true' />
                <s2ui:passwordFieldRow name='password' size='40' labelCodeDefault='Password' required='true' />
                <s2ui:passwordFieldRow name='password2' size='40' labelCodeDefault='Password (again)' required='true'/>
                <tr><td>Please provide this information, if it is known: </td></tr>
                <s2ui:textFieldRow name='org' size='40' labelCodeDefault='Organization'  />
                <s2ui:textFieldRow name='orgAddress' size='40' labelCodeDefault='Address'/>
                <s2ui:textFieldRow name='orgCity' size='40' labelCodeDefault='City'  />
                <s2ui:textFieldRow name='orgState' size='40' labelCodeDefault='State' />
                <s2ui:textFieldRow name='orgZip' size='5' labelCodeDefault='Zip' />
                </tbody>
            </table>
        </fieldset>
        <fieldset class="buttons">
            <input class="save" type="submit" value="${message(code: 'default.button.update.label', default: 'Update')}" />
        </fieldset>
    </s2ui:form>
</s2ui:formContainer>
</body>
</html>
