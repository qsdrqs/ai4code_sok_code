static const char *vulnerable_func(request_rec *r)
{
    return r->user;
}
