bool
ClientRequestContext::sslBumpAccessCheck()
{
    if (!http->getConn()) {
        http->al->ssl.bumpMode = Ssl::bumpEnd; // SslBump does not apply; log -
        return false;
    }

    const Ssl::BumpMode bumpMode = http->getConn()->sslBumpMode;
    if (http->request->flags.forceTunnel) {
        debugs(85, 5, "not needed; already decided to tunnel " << http->getConn());
        if (bumpMode != Ssl::bumpEnd)
            http->al->ssl.bumpMode = bumpMode; // inherited from bumped connection
        return false;
    }

    // If SSL connection tunneling or bumping decision has been made, obey it.
    if (bumpMode != Ssl::bumpEnd) {
        debugs(85, 5, HERE << "SslBump already decided (" << bumpMode <<
               "), " << "ignoring ssl_bump for " << http->getConn());

        // We need the following "if" for transparently bumped TLS connection,
        // because in this case we are running ssl_bump access list before
        // the doCallouts runs. It can be removed after the bug #4340 fixed.
        // We do not want to proceed to bumping steps:
        //  - if the TLS connection with the client is already established
        //    because we are accepting normal HTTP requests on TLS port,
        //    or because of the client-first bumping mode
        //  - When the bumping is already started
        if (!http->getConn()->switchedToHttps() &&
                !http->getConn()->serverBump())
            http->sslBumpNeed(bumpMode); // for processRequest() to bump if needed and not already bumped
        http->al->ssl.bumpMode = bumpMode; // inherited from bumped connection
        return false;
    }

    // If we have not decided yet, decide whether to bump now.

    // Bumping here can only start with a CONNECT request on a bumping port
    // (bumping of intercepted SSL conns is decided before we get 1st request).
    // We also do not bump redirected CONNECT requests.
    if (http->request->method != Http::METHOD_CONNECT || http->redirect.status ||
            !Config.accessList.ssl_bump ||
            !http->getConn()->port->flags.tunnelSslBumping) {
        http->al->ssl.bumpMode = Ssl::bumpEnd; // SslBump does not apply; log -
        debugs(85, 5, HERE << "cannot SslBump this request");
        return false;
    }

    // Do not bump during authentication: clients would not proxy-authenticate
    // if we delay a 407 response and respond with 200 OK to CONNECT.
    if (error && error->httpStatus == Http::scProxyAuthenticationRequired) {
        http->al->ssl.bumpMode = Ssl::bumpEnd; // SslBump does not apply; log -
        debugs(85, 5, HERE << "no SslBump during proxy authentication");
        return false;
    }

    if (error) {
        debugs(85, 5, "SslBump applies. Force bump action on error " << errorTypeName(error->type));
        http->sslBumpNeed(Ssl::bumpBump);
        http->al->ssl.bumpMode = Ssl::bumpBump;
        return false;
    }

    debugs(85, 5, HERE << "SslBump possible, checking ACL");

    ACLFilledChecklist *aclChecklist = clientAclChecklistCreate(Config.accessList.ssl_bump, http);
    aclChecklist->nonBlockingCheck(sslBumpAccessCheckDoneWrapper, this);
    return true;
}