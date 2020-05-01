<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main" />
    <g:set var="entityName" value="${message(code: 'questionList.label', default: 'QuestionList')}" />
    <title><g:message code="default.list.label" args="[entityName]" /></title>
    <asset:javascript type="text/javascript" src="jquery-2.2.0.min.js"/>
    <asset:javascript type="text/javascript" src="questionList.js"/>
    <asset:javascript type="text/javascript" src="questionList-tooltip.js"/>
</head>
<body>
<a href="#list-question" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
<div class="nav" role="navigation">
    <ul>
        <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
    </ul>
</div>
<g:form controller="questionList" action="filter" >
    <!--  Errors from the FilterCommand object here.  -->
    <g:hasErrors bean="${filterCommand}" >
        <ul class="errors" role="alert">
            <g:eachError bean="${filterCommand}" var="error">
                <li ><g:message error="${error}"/></li>
            </g:eachError>
        </ul>
    </g:hasErrors>
    <div class=container">
    <div class="row">
        <div class="col-lg-3" style="border: 1px solid black;margin: 2px;">
            <div>Id filter
            <g:checkBox name="idFilter" value="${command?.idFilter}"></g:checkBox></div>
            <div>Start
            <g:field name="idStart" type="input" value="${command?.idStart}" ></g:field></div>
            <div>End
            <g:field name="idEnd" type="input" value="${command?.idEnd}"></g:field></div>
        </div>
        <div class="col-lg-3" style="border: 1px solid black;margin: 2px;">
            <div>Difficulty filter
                <g:checkBox name="difficultyFilter"  value="${command?.difficultyFilter}"></g:checkBox></div>
            <div><g:select name="difficulty" from="${['1 - Easy (only)', '2 - Medium (and below)',
                                                      '3 - Hard (and below)','4 - Very hard (and below)']}"
                           multiple="false"  value="${command?.difficulty}">
                 </g:select>
            </div>
        </div>
        <div class="col-lg-3" style="border: 1px solid black;margin: 2px;">
            <div>Grade level filter
                <g:checkBox name="gradeLevelFilter" value="${command?.gradeLevelFilter}" ></g:checkBox></div>
            <div><g:select name="gradeLevel" from="${['6 grade (only)', '7 grade (and below)', '8 grade (and below)']}"
                           multiple="false"  value="${command?.gradeLevel}">
            </g:select>
            </div>
        </div>
    </div>
<%--
    <div class="row">
            <div class="col-lg-2" style="border: 1px solid black;margin: 2px;">
                <div>Include publik?
                <g:checkBox name="publikFilter" value="${command?.publikFilter}" ></g:checkBox></div>
            </div>
    </div>
--%>
    </div>
    <fieldset class="buttons">
        <g:submitButton name="filter" class="edit" value="${message(code: 'default.button.filter.label', default: 'Filter')}" />
    </fieldset>
</g:form>

<form id="questionListForm" action="${createLink(controller:'QuestionList', action:'save')}" >
<!--  Errors from the QFCommand object here.  -->
    <g:hasErrors bean="${qfCommand}" >
        <ul class="errors" role="alert">
            <g:eachError bean="${qfCommand}" var="error">
                <li ><g:message error="${error}"/></li>
            </g:eachError>
        </ul>
    </g:hasErrors>
<div id="list-question" class="content scaffold-list" role="main">
<%--
    <div class="fieldcontain">
        <label class="property-label" for="description">Description</label>
        <div id="description"><g:textArea name="description" class="property-value" cols="40" rows="2"/></div>
    </div>
--%>
    <h1><g:message code="default.list.label" args="['Question']" /></h1>
    <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
    </g:if>
    <g:hiddenField name="description" value=""></g:hiddenField>
    <g:hiddenField name="questionCount" value="${questionCount}"></g:hiddenField>
    <!-- the filter values are need to reconstitute the query -->
    <g:hiddenField name="idFilter" value="${command?.idFilter}" />
    <g:hiddenField name="idStart" value="${command?.idStart}" ></g:hiddenField>
    <g:hiddenField name="idEnd" value="${command?.idEnd}"></g:hiddenField>
    <g:hiddenField name="difficultyFilter"  value="${command?.difficultyFilter}"></g:hiddenField>
    <g:hiddenField name="difficulty" value="${command?.difficulty}"></g:hiddenField>
    <g:hiddenField name="gradeLevelFilter" value="${command?.gradeLevelFilter}"></g:hiddenField>
    <g:hiddenField name="gradeLevel" value="${command?.gradeLevel}"></g:hiddenField>
    <f:table collection="${questionList}" properties="['id','qText','difficulty','gradeLevel']" />

    <div class="pagination">
        <g:paginate total="${questionCount ?: 0}" />
    </div>
</div>
    <fieldset class="buttons">
        <g:submitButton name="save" id="btnSave" class="save" value="${message(code: 'default.button.save.label', default: 'Save')}" />
    </fieldset>
</form>
</body>
</html>