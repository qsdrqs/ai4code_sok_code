bool CModules::GetModInfo(CModInfo& ModInfo, const CString& sModule,
                          CString& sRetMsg) {
    CString sModPath, sTmp;

    bool bSuccess;
    bool bHandled = false;
    GLOBALMODULECALL(OnGetModInfo(ModInfo, sModule, bSuccess, sRetMsg),
                     &bHandled);
    if (bHandled) return bSuccess;

    if (!FindModPath(sModule, sModPath, sTmp)) {
        sRetMsg = t_f("Unable to find module {1}.")(sModule);
        return false;
    }

    return GetModPathInfo(ModInfo, sModule, sModPath, sRetMsg);
}