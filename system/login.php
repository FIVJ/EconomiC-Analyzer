<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset='utf-8' />
    <link rel='apple-touch-icon' sizes='76x76' href='assets/img/apple-icon.png'>   
    <link rel='icon' type='image/png' sizes='96x96' href='assets/img/favicon.png'>
    <meta http-equiv='X-UA-Compatible' content='IE=edge,chrome=1' />
    <title>EconomiC Analyzer</title>
    <meta content='width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0' name='viewport' />
    <meta name='viewport' content='width=device-width' />
    <!--Meu estilo-->
    <link href='assets/css/estilologin.css' rel='stylesheet'>
</head>
<body><body>

<form method="post" action="logon.php" id="login-form" name="formlogin">
    <fieldset>

        <legend>Log in</legend>

        <label for="login">Login</label>
        <input type="text" id="login" name="login"/>
        <div class="clear"></div>

        <label for="password">Password</label>
        <input type="password" id="password" name="password"/>
        <div class="clear"></div>

        <label for="remember_me" style="padding: 0;">Forgot?</label>
        <a href="recover.php">Recover</a>
        <div class="clear"></div>

        <br />

        <input type="submit" style="margin: -20px 0 0 287px;" class="button" name="commit" value="Log in"/>
    </fieldset>
</form>

</body>

</html>