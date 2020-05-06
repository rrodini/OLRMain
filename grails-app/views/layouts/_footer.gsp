<div class="footer" role="contentinfo"></div>
<sec:ifLoggedIn >
<div class="panel-body">
    <a href="${createLink(controller:'html', action:'aboutUs')}">About</a>
    <a href="${createLink(controller:'contact', action:'create')}">Contact</a>
    <a href="${createLink(controller:'html', action:'license')}">License/Trademarks</a>
    Version: <g:meta name="info.app.version" />
</div>
<div id="spinner" class="spinner" style="display:none;">
    <g:message code="spinner.alt" default="Loading&hellip;"/>
</div>
</sec:ifLoggedIn>
