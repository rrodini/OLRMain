<!doctype html>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>Welcome to OLR</title>

    <asset:link rel="icon" href="favicon.ico" type="image/x-ico" />
    <asset:script type="text/javascript" src="javascripts/jquery-2.2.0.min.js"/>
<style>
    #navleft {
        float: left;
        position: relative;
        z-index: 5;
        background-color: #eee;
        border: .2em solid #fff;
        margin: 2em 2em 1em;
        padding: 1em;
        width: 16em;
        -moz-box-shadow: 0px 0px 1.25em #ccc;
        -webkit-box-shadow: 0px 0px 1.25em #ccc;
        box-shadow: 0px 0px 1.25em #ccc;
        -moz-border-radius: 0.6em;
        -webkit-border-radius: 0.6em;
        border-radius: 0.6em;

    }

    #navleft ul {
        font-size: 0.9em;
        list-style-type: none;
        margin-bottom: 0.6em;
        padding: 0;
    }

    #navleft li {
        line-height: 1.3;
    }

    #navleft li > a{
        cursor: default;
    }

    #navleft h1 {
        text-transform: uppercase;
        font-size: 1.1em;
        margin: 0 0 0.3em;
    }
</style>
</head>
<body>
    <content tag="nav" style="float: left;" >
        <li class="dropdown">
            <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Application Status <span class="caret"></span></a>
            <ul class="dropdown-menu">
                <li><a href="#">Environment: ${grails.util.Environment.current.name}</a></li>
                <li><a href="#">App profile: ${grailsApplication.config.grails?.profile}</a></li>
                <li><a href="#">App version:
                    <g:meta name="info.app.version"/></a>
                </li>
                <li role="separator" class="divider"></li>
                <li><a href="#">Grails version:
                    <g:meta name="info.app.grailsVersion"/></a>
                </li>
                <li><a href="#">Groovy version: ${GroovySystem.getVersion()}</a></li>
                <li><a href="#">JVM version: ${System.getProperty('java.version')}</a></li>
                <li role="separator" class="divider"></li>
                <li><a href="#">Reloading active: ${grails.util.Environment.reloadingAgentEnabled}</a></li>
            </ul>
        </li>
    </content>

    <div class="svg" role="presentation">
    <%--
        <div class="grails-logo-container">
            <asset:image src="grails-cupsonly-logo-white.svg" class="grails-logo"/>
        </div>
    --%>
    </div>
<sec:ifLoggedIn >
    <div id="navleft" >
        <h1>Info</h1>
        <ul>
            <li><g:link controller="html" action="playAGame">How to play a game</g:link></li>
            <li><g:link controller="html" action="constructAPlayerFile">How to construct a player file</g:link></li>
            <li><g:link controller="html" action="constructAQuestionFile">How to construct a question file</g:link></li>
        </ul>
        <h1>My Stuff (+public)</h1>
        <ul>
            <li><g:link controller="questionFile" action="index">Question files</g:link></li>
            <li><g:link controller="playerFile" action="index">Player files</g:link></li>
            <li><g:link controller="game" action="index?max=10">Past games</g:link></li>
        </ul>
    </div>
</sec:ifLoggedIn>
    <div id="content" role="main">
        <section class="row colset-2-its">
            <h1>Welcome to Open Lightning Round</h1>
            <sec:ifLoggedIn >
            <div align="center">
                <g:form controller="startGame" action = "index" >
                    <!-- fieldset class="buttons" -->
                        <g:submitButton name="start" class="save" value="Start Game" />
                    <!-- /fieldset  -->
                </g:form>

            </div>
            </sec:ifLoggedIn>
        </section>
    </div>

</body>
</html>
