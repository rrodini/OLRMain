<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'question.label', default: 'Question')}" />
        <title><g:message code="default.show.label" args="[entityName]" /></title>
    </head>
    <body>
        <a href="#show-question" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
        <div class="nav" role="navigation">
            <ul>
                <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
                <li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
                <li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
            </ul>
        </div>
        <div id="show-question" class="content scaffold-show" role="main">
            <h1><g:message code="default.show.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message" role="status">${flash.message}</div>
            </g:if>
        <%-- original
            <f:display bean="question" />
        --%>
        <%--
            <div  style="overflow: hidden; text-overflow:ellipsis; max-width: 10ch;" >
            <f:display bean="question" property="figureBase64"/>
            </div>
        --%>
            <ol class="property-list question" >
            <f:with bean="question" >
                <li class="fieldcontain">
                    <label class="property-label" for="id">Id</label>
                    <f:display property="id" class="property-value"/>
                </li>
                <li class="fieldcontain">
                    <label class="property-label" for="question">Question</label>
                    <div class="property-value-truncate" >
                    <f:display property="qText" />
                    </div>
                </li>
                <li class="fieldcontain">
                    <label class="property-label" for="answer">Answer</label>
                    <f:display property="aText" class="property-value" />
                </li>
                <li class="fieldcontain">
                    <label class="property-label" for="figure">Figure</label>
            <g:if test="${question.figureBase64 != null}">
            <!-- display the figure as a "data URI". -->
            <img src="data:image/png;base64,
            <f:display property="figureBase64" />
            " 
            </g:if>
                </li>
                <li class="fieldcontain">
                    <label class="property-label" for="figureName">Figure name</label>
                    <f:display property="figureName" class="property-value"/>
                </li>
                <li class="fieldcontain">
                    <label class="property-label" for="format">Format</label>
                    <f:display property="format" class="property-value"/>
                </li>
                <li class="fieldcontain">
                    <label class="property-label" for="difficulty">Difficulty</label>
                    <f:display property="difficulty" class="property-value" />
                </li>
                <li class="fieldcontain">
                    <label class="property-label" for="gradeLevel">Grade level</label>
                    <f:display property="gradeLevel" class="property-value" />
                </li>
            <!--
                <li class="fieldcontain">
                    <label class="property-label" for="publik">Publik</label>
                    <f:display property="publik" class="property-value" />
                </li>
            -->
            </f:with>
            </ol>
            <g:form resource="${this.question}" method="DELETE">
                <fieldset class="buttons">
                    <g:link class="edit" action="edit" resource="${this.question}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
                    <input class="delete" type="submit" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
                </fieldset>
            </g:form>
        </div>
    </body>
</html>
