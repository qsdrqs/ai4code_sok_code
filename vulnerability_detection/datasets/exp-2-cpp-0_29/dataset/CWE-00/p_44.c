QString Utility::ConvertLineEndings(const QString &text)
{
    QString newtext(text);
    return newtext.replace("\x0D\x0A", "\x0A").replace("\x0D", "\x0A");
}