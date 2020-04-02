<%--
 Login / Sign up section
<span class="login" >
--%>
<span >
    <sec:ifNotLoggedIn>
        <g:link controller="login" action="index">Login</g:link>
        <span>|</span>
        <g:link controller="register">Sign up</g:link>
    </sec:ifNotLoggedIn>
    <sec:ifLoggedIn >
        <span style="font-size:18px" >
        <sec:username />
        </span>
        <span>|</span>
        <g:link controller="logout">Logout</g:link>
        <span>|</span>
        <g:link controller="register" action="edit">Profile...</g:link>
    </sec:ifLoggedIn>
</span>