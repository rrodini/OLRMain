<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'question.label', default: 'Question')}" />
        <title><g:message code="default.edit.label" args="[entityName]" /></title>
        <asset:javascript type="text/javascript" src="jquery-2.2.0.min.js"/>
        <asset:javascript type="text/javascript" src="projector.js"/>
        <asset:javascript type="text/javascript" src="question-tooltip.js"/>
    </head>
    <body>
        <a href="#edit-question" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
        <div class="nav" role="navigation">
            <ul>
                <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
                <li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
                <li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
            </ul>
        </div>
        <div id="edit-question" class="content scaffold-edit" role="main">
            <h1><g:message code="default.edit.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message" role="status">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${this.question}">
            <ul class="errors" role="alert">
                <g:eachError bean="${this.question}" var="error">
                <li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
                </g:eachError>
            </ul>
            </g:hasErrors>
<%--
            <g:form resource="${this.question}" method="PUT">
                <g:hiddenField name="version" value="${this.question?.version}" />
                <fieldset class="form">
                    <!-- f:all bean="question"/ -->
                    <f:field bean="question" property="qText"/>
                    <f:field bean="question" property="aText"/>
                    <f:field bean="question" property="format"/>
                    <f:field bean="question" property="difficulty"/>
                    <f:field bean="question" property="gradeLevel"/>
                </fieldset>
                <fieldset class="buttons">
                    <input class="save" type="submit" value="${message(code: 'default.button.update.label', default: 'Update')}" />
                </fieldset>
            </g:form>
--%>
            <g:uploadForm resource="${this.question}" action="update" method="PUT">
                <g:hiddenField name="version" value="${this.question?.version}" />
                <g:hiddenField name="figureBase64" value="${this.question?.figureBase64}" id="figureBase64" />
                <fieldset class="form" >
                    <!-- f:all bean="question"/ -->
                    <f:field bean="question" property="qText" label="Question" />
                    <div class="fieldcontain">
                        <label class="property-label" for="figureName">Figure name</label>
                        <div id="figureName"><f:display bean="question" property="figureName" class="property-value"/></div>
                    </div>
                    <div class="fieldcontain" >
                        <label>Figure (new)</label>
                        <input type="file" id="imgFile" accept="png" name="imgFile" onchange="loadFile"/>
                    </div>
                    <f:field bean="question" property="format" default="3"/>
                    <div style="display: flex; justify-content:center; margin-top: 8px">
                        <button id="btnQuestion">View Question</button>
                    </div>
                    <f:field bean="question" property="aText" label="Answer" />
                    <f:field bean="question" property="difficulty"  default="2"/>
                    <f:field bean="question" property="gradeLevel" default="8"/>
                    <!-- f:field bean="question" property="publik"/ -->
                </fieldset >
                <fieldset class="buttons">
                    <!-- g:submitButton name="update" class="save" value="${message(code: 'default.button.update.label', default: 'Update')}" / -->
                    <input class="save" type="submit" value="${message(code: 'default.button.update.label', default: 'Update')}" />
                </fieldset>
            </g:uploadForm>

        </div>
    </body>
</html>
