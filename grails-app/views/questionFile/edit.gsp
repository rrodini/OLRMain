<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'questionFile.label', default: 'QuestionFile')}" />
        <title><g:message code="default.edit.label" args="[entityName]" /></title>
    </head>
    <body>
        <a href="#edit-questionFile" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
        <div class="nav" role="navigation">
            <ul>
                <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
                <li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
                <li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
            </ul>
        </div>
        <div id="edit-questionFile" class="content scaffold-edit" role="main">
            <h1><g:message code="default.edit.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message" role="status">${flash.message}</div>
            </g:if>
            <g:if test="${flash.error}">
                <div class="error" role="status">${flash.error}</div>
            </g:if>
            <g:hasErrors bean="${this.questionFile}">
            <ul class="errors" role="alert">
                <g:eachError bean="${this.questionFile}" var="error">
                    <li> <g:message error="${error}"/></li>
                </g:eachError>
            </ul>
            </g:hasErrors>
            <g:form resource="${this.questionFile}" method="PUT">
                <g:hiddenField name="version" value="${this.questionFile?.version}" />
                <fieldset class="form">
                    <!-- f:all bean="questionFile"/ -->
                    <!-- only the questionsJson, publik values to be edited. -->
                    <f:field bean="questionFile" property="questionsJson"/>
                    <f:field bean="questionFile" property="publik"/>
                </fieldset>
                <fieldset class="buttons">
                    <input class="save" type="submit" value="${message(code: 'default.button.update.label', default: 'Update')}" />
                </fieldset>
            </g:form>
        </div>
    </body>
</html>
