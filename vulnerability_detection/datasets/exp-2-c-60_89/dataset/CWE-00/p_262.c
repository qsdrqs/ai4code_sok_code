static void qmp_query_auth(int auth, int subauth,
                           VncPrimaryAuth *qmp_auth,
                           VncVencryptSubAuth *qmp_vencrypt,
                           bool *qmp_has_vencrypt)
{
    switch (auth) {
    case VNC_AUTH_VNC:
        *qmp_auth = VNC_PRIMARY_AUTH_VNC;
        break;
    case VNC_AUTH_RA2:
        *qmp_auth = VNC_PRIMARY_AUTH_RA2;
        break;
    case VNC_AUTH_RA2NE:
        *qmp_auth = VNC_PRIMARY_AUTH_RA2NE;
        break;
    case VNC_AUTH_TIGHT:
        *qmp_auth = VNC_PRIMARY_AUTH_TIGHT;
        break;
    case VNC_AUTH_ULTRA:
        *qmp_auth = VNC_PRIMARY_AUTH_ULTRA;
        break;
    case VNC_AUTH_TLS:
        *qmp_auth = VNC_PRIMARY_AUTH_TLS;
        break;
    case VNC_AUTH_VENCRYPT:
        *qmp_auth = VNC_PRIMARY_AUTH_VENCRYPT;
        *qmp_has_vencrypt = true;
        switch (subauth) {
        case VNC_AUTH_VENCRYPT_PLAIN:
            *qmp_vencrypt = VNC_VENCRYPT_SUB_AUTH_PLAIN;
            break;
        case VNC_AUTH_VENCRYPT_TLSNONE:
            *qmp_vencrypt = VNC_VENCRYPT_SUB_AUTH_TLS_NONE;
            break;
        case VNC_AUTH_VENCRYPT_TLSVNC:
            *qmp_vencrypt = VNC_VENCRYPT_SUB_AUTH_TLS_VNC;
            break;
        case VNC_AUTH_VENCRYPT_TLSPLAIN:
            *qmp_vencrypt = VNC_VENCRYPT_SUB_AUTH_TLS_PLAIN;
            break;
        case VNC_AUTH_VENCRYPT_X509NONE:
            *qmp_vencrypt = VNC_VENCRYPT_SUB_AUTH_X509_NONE;
            break;
        case VNC_AUTH_VENCRYPT_X509VNC:
            *qmp_vencrypt = VNC_VENCRYPT_SUB_AUTH_X509_VNC;
            break;
        case VNC_AUTH_VENCRYPT_X509PLAIN:
            *qmp_vencrypt = VNC_VENCRYPT_SUB_AUTH_X509_PLAIN;
            break;
        case VNC_AUTH_VENCRYPT_TLSSASL:
            *qmp_vencrypt = VNC_VENCRYPT_SUB_AUTH_TLS_SASL;
            break;
        case VNC_AUTH_VENCRYPT_X509SASL:
            *qmp_vencrypt = VNC_VENCRYPT_SUB_AUTH_X509_SASL;
            break;
        default:
            *qmp_has_vencrypt = false;
            break;
        }
        break;
    case VNC_AUTH_SASL:
        *qmp_auth = VNC_PRIMARY_AUTH_SASL;
        break;
    case VNC_AUTH_NONE:
    default:
        *qmp_auth = VNC_PRIMARY_AUTH_NONE;
        break;
    }
}