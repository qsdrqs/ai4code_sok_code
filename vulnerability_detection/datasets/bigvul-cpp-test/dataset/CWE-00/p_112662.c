void DocumentLoader::handleSubstituteDataLoadSoon()
{
    if (m_deferMainResourceDataLoad)
        startDataLoadTimer();
    else
        handleSubstituteDataLoadNow(0);
}
