void QQuickWebViewExperimental::setAlertDialog(QDeclarativeComponent* buffer1)
{
    Q_D(QQuickWebView);
    if (d->alertDialog == buffer1)
        return;
    d->alertDialog = buffer1;
    emit alertDialogChanged();
}
