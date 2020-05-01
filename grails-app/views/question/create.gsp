<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'question.label', default: 'Question')}" />
        <title><g:message code="default.create.label" args="[entityName]" /></title>
        <asset:javascript type="text/javascript" src="jquery-2.2.0.min.js"/>
        <asset:javascript type="text/javascript" src="projector.js"/>
        <asset:javascript type="text/javascript" src="question-tooltip.js"/>
    </head>
    <body>
        <a href="#create-question" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
        <div class="nav" role="navigation">
            <ul>
                <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
                <li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
            </ul>
        </div>
        <div id="create-question" class="content scaffold-create" role="main">
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


            <g:hasErrors bean="${this.question}">
            <ul class="errors" role="alert">
                <g:eachError bean="${this.question}" var="error">
                <li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
                </g:eachError>
            </ul>
            </g:hasErrors>
            <!-- g:form resource="${this.question}" method="POST" -->
            <g:uploadForm action="save" method="POST">
                <fieldset class="form" >
                    <!-- f:all bean="question"/ -->
                    <f:field bean="question" property="qText" label="Question"  />
                    <div class="fieldcontain" >
                    <label >Figure (optional)</label>
                    <input type="file" id="imgFile"  accept="png"  name="imgFile" onchange="loadFile"
                           data-toggle="tooltip" title="Choose a .png file."/>
                    </div>
                    <f:field bean="question" property="format" default="3"/>
                    <div style="display: flex; justify-content:center; margin-top: 8px">
                        <button id="btnQuestion">Preview Question</button>
                    </div>
                    <f:field bean="question" property="aText" label="Answer" />
                    <f:field bean="question" property="difficulty"  default="2"/>
                    <f:field bean="question" property="gradeLevel" default="8"/>
                    <!-- f:field bean="question" property="publik"/ -->
                </fieldset >
                <fieldset class="buttons">
                    <g:submitButton name="create" class="save" value="${message(code: 'default.button.create.label', default: 'Create')}" />
                </fieldset>
            </g:uploadForm>
        </div>
    </body>
</html>
