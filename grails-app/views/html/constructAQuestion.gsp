<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="main" />
    <title>How to construct a question</title>
</head>

<body>
<div class="panel-body">
<p>There are two ways to construct a Question File for a game:
    <ol>
    <li>Create individual questions and group them into a Question File.</li>
    <li>Bulk load an entire Question File.</li>
    </ol>
</p>
<p>Since #2 is the province of OLR experts, let's stick to #1. #1 is a two step process.
The first step is described on this page, and the second step on another
<g:link controller="html" action="constructAQuestionFile">page</g:link>.</p>
<p>
You (as teacher, coach, emcee) can define individual questions on this <g:link controller="question" action="create">screen</g:link>.
Here's how each field is used:
<div><g:img src="question_field.png"></g:img></div>
<p>
This is the text of the question.  You can use any keyboard character and/or html character entity (e.g. &amp;pi; for &pi;) within it.
You can also use html markup (e.g. &lt;sup&gt; for superscript as in 4&lt;sup&gt;2&lt;&sol;sup&gt; produces 4<sup>2</sup>=16.
</p>
<p>
Some questions might require a complicated mathematical formula such as:
<div><g:img src="ascii_math_formula.png"></g:img></div>
<p>
This might seem impossible, but it is made easy by entering the formula in
<a href="http://asciimath.org/">ASCII Math</a> notation enclosed within backticks.
The image above was created by this simple string: `((n(n+1))/2)`.
</p>
<p>
A question can even reference a figure (a .png file) that can be loaded by the file chooser.
    <div><g:img src="file_chooser_field.png"></g:img></div>
</p>
<p>
The .png file should be "small" and run through a minimizer like the one found at
<a href="http://tinypng.com">tinypng.com</a>.  The figure will always be
positioned in the bottom right of the question and just needs to be referenced
in the text of the question.
</p>
<p>
The next field is important for the presentation of the question during the game.
    <div><g:img src="format_field.png"></g:img></div>
</p>
<p>
This field has a value 1 &hellip; 5 and determines how the question is formatted on the
projector screen.  It is best explained in context of the<g:img src="preview_question_field.png"></g:img>
button.
</p>
<p>
Click this button and a pop-up window will show you how the question will be centered horizontally and vertically.
If the format doesn't look right in the pop-up window forcibly scroll to the bottom of the window and click
a radio button to the right of the current selection.
</p>
<p>
Here's an example.  The images below show a preview of the same question.  The first image has "format 3",
and is not centered properly.
    <div><g:img src="preview_format_3.png"></g:img></div>
</p>
By clicking the next radio button the image has "format 4" and looks better.
    <div><g:img src="preview_format_4.png"></g:img></div>
<p>
Just close the pop-up windows and the format will be remembered.
</p>
<p>
The next field is the answer field.
    <div><g:img src="answer_field.png"></g:img></div>
</p>
<p>The answer is typically short and has units expressed within parentheses to indicate
the units are an optional part of the answer.
</p>
<p>Finally there are the difficulty and grade level fields.
    <div><g:img src="difficulty_gradeLevel_fields.png"></g:img></div>
</p>These fields exist only to help you when selecting questions for a game. They can be ignored,
or one or the other can be used to characterize each question.
<p>
<p> The important thing is that the fields are independent from one another.
So, there is no such thing as a "easy 8th grade question" which may be difficult
for a 6th grader. It's more likely that the question is "easy" because it is based
on material taught by the 6th grade.
</p>
<p>Once you build up your bank of questions, you are ready to constitute a Question
File that can be used in a game.
</p>
</div>
</body>
</html>