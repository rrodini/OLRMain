<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="main" />
    <title>How to construct a bulk question file</title>
</head>

<body>
<div class="panel-body">
<p>Below is a bulk Question File (Json syntax).</p>
<pre>
{
"description": "SAMPLE. This question file is a simple example",
"questions_and_answers": [
 {"q": "What is 1+1?", "a": "2", "f":  0},
 {"q": "What is the sum of the &lt;i&gt;interior&lt;/i&gt; angles of a triangle?", "a": "180&lt;sup&gt;o&lt;/sup&gt;", "f": 0},
 {"q": "What is the value of `((2^4)^9)/((4^8)^2)`?", "a": "16", "f":  0},
 {"q": "How many squares of any size are in this figure consisting of adjacent unit squares surrounding a larger square? `figure-10`", "a": "18", "f": 0}
],
"figures" : [
{"name" : "figure-10", "src":"figure-10-tiny.png", "val":"<div><img src=data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAEkAAABJAQMAAABLvZmfAAAABlBMVEX///8jHyCZ/QWRAAAALUlEQVQoz2P4DwMNDA0MCgwcDEwMDEOPieYLBpDo0GP+//8AyGQe8r4YXikKAHXFeVRwVlYzAAAAAElFTkSuQmCC></div>"}
]
}
</pre>
<p>Facts:</p>
<ol>
    <li>Questions Files are in <a href="https://www.w3schools.com/js/js_json_syntax.asp" target="_blank">Json</a> syntax. The syntax is exacting.  Question files must also obey this
        <a href="https://github.com/rrodini/OLRMain/blob/29c65b4dfd79a27fb927dd1de3249ba96c619742/src/main/resources/questionFileSchema.json"  target="_blank">schema</a>
        or else they will not be accepted.</li>
    <li>Almost all fields are delimited by quotation marks, except for the "f" value which is a number.</li>
    <li>Questions are implicitly numbered by their order in the "questions_and_answers" array.</li>
    <li>Q1 shows the simplest question and answer where both are just text strings.</li>
    <li>Q2 demonstrates in-line formatting using pure HTML tags.  These tags can be used in the answer field too.</li>
    <li>Q3 demonstrates the use of <a href="http://asciimath.org/" target="_blank">ASCII Math</a> to format a complex formula. The formula must be delimited by backtick characters ( ` ).</li>
    <li>Q4 demonstrates a question that displays a figure. Figures are supported but the construction process it too complicated to explain.</li>
</ol>
<p>
Presently, Question Files must be prepared off-line, and then bulk-loaded into OLR. I am trying to figure out a way to prepare them on-line.
If you need to edit a Question File you should either: 1) make tiny edits or 2) replace the entire contents of file.
</p>
</div>
</body>
</html>