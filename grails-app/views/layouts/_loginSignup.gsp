<%--
 Login / Sign up section
<span class="login" >
--%>
<span >
    <sec:ifNotLoggedIn>
        <g:link controller="login" action="index">Login</g:link>
        <g:link controller="register"> | Sign up</g:link>
    </sec:ifNotLoggedIn>
    <sec:ifLoggedIn >
        <span class="login-name"  >
        <sec:username /> |
        </span>
        <g:link controller="logout">Logout</g:link>
        <g:link controller="register" action="edit"> | Profile...</g:link>
    </sec:ifLoggedIn>
</span>