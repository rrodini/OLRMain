<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="main" />
    <title>How to construct a question</title>
</head>

<body>
<div class="panel-body">
    <p>So, you have created many questions that you would like to use in a game. Here's how to
    build a Question File from them.</p>
    <p>Click the link labeled
    <g:link controller="questionList" action="index">Build a Question File</g:link>.
     A screen like below will appear.
    <div><g:img src="question_filter_1.png"></g:img></div>
    </p>
    <p>Initially, all of the Questions that you have created will be at the bottom of
    the screen (at least the first page of them).
    You may want only a subset of these questions, and that's the purpose of the filter
    options at the top of the screen.</p>
    <p>Say you only want questions 5 through 10.  In this case click the "Id filter" check box and then
    enter 5 in the "Start" field and 10 in the "End" field. Then click the
    <g:img src="filter_button.png"></g:img> button.</p>
    <p>The filtered questions appear at the bottom.
    <div><g:img src="questions_5_10.png"></g:img></div></p>
    <p>These can be saved by clicking the <g:img src="save_button.png"></g:img> button.
    </p>
    <p>when you click  <g:img src="save_button.png"></g:img> a pop-up window will appear
    where you must type in the "Description" text for the Question File.
    <div><g:img src="qf_description_popup.png"></g:img></div></p>
    <p>Your Question File can be found by its "Id" value or its "Description" value later when you
    start a game.</p>
    <p><b>Note:</b> All filter options work by <b>AND</b>ing the checked filters together.
    Be careful that the filtered questions are the ones that you want.
    </p>
</div>
</body>
</html>