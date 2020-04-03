<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>Game Summary for ${gameId}</title>
    <asset:link rel="icon" href="favicon.ico" type="image/x-ico" />
    <asset:script type="text/javascript" src="javascripts/jquery-2.2.0.min.js"/>
</head>
<body>
<div id="content" role="main">
    <g:form action="update" id="${gameId}">
    <fieldset class="form">
        <h1>Game Summary</h1>
        <p>Id: ${gameId}</p>
        <p>Start time: <g:formatDate date="${startTime}" type="time"  style="SHORT"/>
        <p>End time: <g:formatDate date="${endTime}" type="time"  style="SHORT"/>
        <p>Emcee: ${emceeName}</p>
        <p>Room No: ${roomNo}</p>
        <p>Game type: ${gameType}</p>
        <p>Question file id: ${questionFileId}</p>
        <p>Player file id: ${playerFileId}</p>
        <p>Question count: ${questionCount}</p>
        <p>Duration: ${duration}</p>
        <p>Enter notes for this game:</p>
        <g:textArea id='description' name="description" rows="3" cols="70" >${description}</g:textArea>
    <fieldset class="form">
    </g:form>
    <g:submitButton name="update" value="Update"/>
</div>
</body>
</html>