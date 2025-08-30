void DocumentLoader::vulnerable_func()
{
    if (m_deferMainResourceDataLoad)
        startDataLoadTimer();
    else
        handleSubstituteDataLoadNow(0);
}
