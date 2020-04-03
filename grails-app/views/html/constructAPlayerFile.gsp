<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="main" />
    <title>How to construct a player file</title>
</head>

<body>
<div class="panel-body">
<p>Below is a sample Player File (Json syntax).  This file is only used for the Countdown Round (CDR) game.</p>
<pre>
{
"description": "Middle School Mathletes for CDR.",
"players" : [
 { "rank": 1, "name":"Justenn"},
 { "rank": 2, "name":"Allen"},
 { "rank": 3, "name":"Dylan"},
 { "rank": 4, "name":"Eric"},
 { "rank": 5, "name":"Rahul"},
 { "rank": 6, "name":"Charlotte"},
 { "rank": 7, "name":"Stanley"},
 { "rank": 8, "name":"Sriman"},
 { "rank": 9, "name":"Anish"},
 { "rank":10, "name":"Arvant"}
 ]
}
</pre>
<p>Facts:</p>
<ol>
    <li>Player Files are in <a href="https://www.w3schools.com/js/js_json_syntax.asp" target="_blank">Json</a> syntax. The syntax is exacting.  Player files must also obey this
        <a href="https://github.com/rrodini/OLRMain/blob/29c65b4dfd79a27fb927dd1de3249ba96c619742/src/main/resources/playerFileSchema.json"  target="_blank">schema</a>
        or else they will not be accepted.</li>
    <li>All fields are delimited by quotation marks except for player "rank" value which is a number.</li>
    <li>The "rank" field indicates the player's rank in the Countdown Round.</li>
    <li>The "name" field is the player's first name.  The "name" value must be unique among all of the names.</li>
    <li>There can be as few as 2 and as many as 10 players.</li>
    <li>Countdown Round procedes according to <a href="https://www.mathcounts.org/official-rules-procedures" target=_blank>
        MathCounts® competition rules.</a></li>
</ol>
<p>
    Presently, Player Files must be prepared off-line, and then bulk-loaded into OLR. Or, you can use one of the pre-loaded Player Files
    where players are given names like "Student1", "Student2", etc.
</p>
</div>
</body>
</html>