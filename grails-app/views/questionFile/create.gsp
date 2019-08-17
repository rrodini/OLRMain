<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'questionFile.label', default: 'QuestionFile')}" />
        <title><g:message code="default.create.label" args="[entityName]" /></title>
    </head>
    <body>
        <a href="#create-questionFile" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
        <div class="nav" role="navigation">
            <ul>
                <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
                <li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
            </ul>
        </div>
        <div id="create-questionFile" class="content scaffold-create" role="main">
            <h1><g:message code="default.create.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message" role="status">${flash.message}</div>
            </g:if>
            <!--  Errors from the command object here.  -->
            <g:hasErrors bean="${command}" >
                <ul class="errors" role="alert">
                    <g:eachError bean="${command}" var="error">
                        <li ><g:message error="${error}"/></li>
                    </g:eachError>
                </ul>
            </g:hasErrors>
            <!--  Errors from validation here.  -->
            <g:if test="${problems.size}">
                The json file has these syntax errors:<br>
                <ul class="errors" role="alert">
                    <li>The json file has these syntax errors:</li>
                    <g:each in="${problems}" var="error">
                        <li ><${error}"/></li>
                    </g:each>
                </ul>
            </g:if>
            <!--  Errors from the questionFile save here.  -->
            <g:hasErrors bean="${this.questionFile}" >
                <ul class="errors" role="alert">
                    <g:eachError bean="${questionFile}" var="error">
                        <li ><g:message error="${error}"/></li>
                    </g:eachError>
                </ul>
            </g:hasErrors>

        <%--
                    <g:form resource="${this.questionFile}" method="POST">
                        <fieldset class="form">
                            <f:all bean="questionFile"/>
                        </fieldset>
                        <fieldset class="buttons">
                            <g:submitButton name="create" class="save" value="${message(code: 'default.button.create.label', default: 'Create')}" />
                        </fieldset>
                    </g:form>
         --%>
            <g:uploadForm action="save" method="POST" >
                <fieldset class="form">
                    <input type="file" name="jsonFile" />
                    <f:field bean="questionFile" property="publik"/>
                </fieldset>
                <fieldset class="buttons">
                    <g:submitButton name="create" class="save" value="${message(code: 'default.button.create.label', default: 'Create')}" />
                </fieldset>
            </g:uploadForm>
        </div>
    </body>
</html>
