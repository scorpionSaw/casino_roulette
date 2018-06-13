<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<link rel="stylesheet" href="/assets/css/stylesheet-light.css"></link>
</head>
<body class="o-layout-grow-last">
    <div class="c-sidebar u-padding-top-xl">
        <img alt="CHU Tivoli logo" src="/assets/images/chutivoli-logo-hq.png" class="c-sidebar__logo u-margin-top-xl">
    </div>
    <form method="post" class="o-layout-wrapper u-padding-top-xl u-margin-top-xl">
        <header class="u-margin-bottom-xl">
            <h1 class="c-text-header u-text-color-accent">App Name</h1>
        </header>
        <label class="c-label">
            Login
            <input type="text" name="username" class="c-input-text" autofocus />
        </label>
        <label class="c-label">
            Mot de passe
            <input type="password" name="password" class="c-input-text" />
        </label>
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
        <button type="submit" class="c-btn c-btn--primary  ">Se connecter</button>
        <c:if test="${param.error != null}">
            <span class="u-text-error u-padding-left-xl">le login et le mot de passe ne correspondent pas</span>
        </c:if>
    </form>
</body>
</html>