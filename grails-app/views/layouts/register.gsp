<!doctype html>
<html class="no-js" lang="">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <title>
        <g:layoutTitle default="OLR"/>
    </title>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <asset:stylesheet src="application.css"/>
    <g:layoutHead/>
</head>

<body>
<div class="navbar navbar-default navbar-static-top" role="navigation">
    <div class="container">
        <a class="navbar-brand" href="/#">
            <i class="fa grails-icon">
                <asset:image src="olr-logo.svg"/>
            </i>OLR
        </a>
    </div>
</div>
</div>

<g:layoutBody/>
<asset:javascript src='spring-security-ui-register.js'/>
<s2ui:showFlash/>
<s2ui:deferredScripts/>
</body>
</html>
