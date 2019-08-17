<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'questionFile.label', default: 'QuestionFile')}" />
        <title><g:message code="default.show.label" args="[entityName]" /></title>
    </head>
    <body>
        <a href="#show-questionFile" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
        <div class="nav" role="navigation">
            <ul>
                <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
                <li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
                <li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
            </ul>
        </div>
        <div id="show-questionFile" class="content scaffold-show" role="main">
            <h1><g:message code="default.show.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message" role="status">${flash.message}</div>
            </g:if>
            <!-- f:display bean="questionFile" / -->
            <ol class="property-list questionFile" >
            <f:with bean="questionFile"  >
                <li class="fieldcontain">
                <label class="property-label" for="id">Id</label>
                    <f:display property="id" class ="property-value"/>
                </li>
                <li class="fieldcontain">
                    <label class="property-label" for="questionsJson" >Description</label>
                    <f:display property="questionsJson" class ="property-value"/>
                </li>
                <li class="fieldcontain">
                    <label class="property-label" for="owner" >Owner</label>
                        <f:display property="owner" value="${questionFile.owner.toString()}" class ="property-value"/>
                </li>
                <li class="fieldcontain">
                    <label class="property-label" for="publik" >Publik</label>
                    <f:display property="publik" class ="property-value"/>
                </li>
            </f:with>
            </ol>
            <g:form resource="${this.questionFile}" method="DELETE">
                <fieldset class="buttons">
                    <g:link class="edit" action="edit" resource="${this.questionFile}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
                    <input class="delete" type="submit" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
                </fieldset>
            </g:form>
        </div>
    </body>
</html>
