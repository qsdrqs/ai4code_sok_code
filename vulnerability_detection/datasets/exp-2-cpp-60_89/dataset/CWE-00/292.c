bool CModules::LoadModule(const CString& sModule, const CString& sArgs,
                          CModInfo::EModuleType eType, CUser* pUser,
                          CIRCNetwork* pNetwork, CString& sRetMsg) {
    sRetMsg = "";

    if (FindModule(sModule) != nullptr) {
        sRetMsg = t_f("Module {1} already loaded.")(sModule);
        return false;
    }

    bool bSuccess;
    bool bHandled = false;
    _GLOBALMODULECALL(OnModuleLoading(sModule, sArgs, eType, bSuccess, sRetMsg),
                      pUser, pNetwork, nullptr, &bHandled);
    if (bHandled) return bSuccess;

    CString sModPath, sDataPath;
    CModInfo Info;

    if (!FindModPath(sModule, sModPath, sDataPath)) {
        sRetMsg = t_f("Unable to find module {1}")(sModule);
        return false;
    }
    Info.SetName(sModule);
    Info.SetPath(sModPath);

    ModHandle p = OpenModule(sModule, sModPath, Info, sRetMsg);

    if (!p) return false;

    if (!Info.SupportsType(eType)) {
        dlclose(p);
        sRetMsg = t_f("Module {1} does not support module type {2}.")(
            sModule, CModInfo::ModuleTypeToString(eType));
        return false;
    }

    if (!pUser && eType == CModInfo::UserModule) {
        dlclose(p);
        sRetMsg = t_f("Module {1} requires a user.")(sModule);
        return false;
    }

    if (!pNetwork && eType == CModInfo::NetworkModule) {
        dlclose(p);
        sRetMsg = t_f("Module {1} requires a network.")(sModule);
        return false;
    }

    CModule* pModule =
        Info.GetLoader()(p, pUser, pNetwork, sModule, sDataPath, eType);
    pModule->SetDescription(Info.GetDescription());
    pModule->SetArgs(sArgs);
    pModule->SetModPath(CDir::ChangeDir(CZNC::Get().GetCurPath(), sModPath));
    push_back(pModule);

    bool bLoaded;
    try {
        bLoaded = pModule->OnLoad(sArgs, sRetMsg);
    } catch (const CModule::EModException&) {
        bLoaded = false;
        sRetMsg = t_s("Caught an exception");
    }

    if (!bLoaded) {
        UnloadModule(sModule, sModPath);
        if (!sRetMsg.empty())
            sRetMsg = t_f("Module {1} aborted: {2}")(sModule, sRetMsg);
        else
            sRetMsg = t_f("Module {1} aborted.")(sModule);
        return false;
    }

    if (!sRetMsg.empty()) {
        sRetMsg += " ";
    }
    sRetMsg += "[" + sModPath + "]";
    return true;
}