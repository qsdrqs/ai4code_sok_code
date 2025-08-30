public void install(
            String displayName, String description, String[] dependencies,
            String account, String password, String config) throws URISyntaxException {

        String javaHome = System.getProperty("java.home");
        String javaBinary = javaHome + "\\bin\\java.exe";

        File jar = new File(WindowsService.class.getProtectionDomain().getCodeSource().getLocation().toURI());
        String command = javaBinary
                + " -Duser.dir=\"" + jar.getParentFile().getAbsolutePath() + "\""
                + " -jar \"" + jar.getAbsolutePath() + "\""
                + " --service \"" + config + "\"";

        StringBuilder dep = new StringBuilder();

        if (dependencies != null) {
            for (String s : dependencies) {
                dep.append(s);
                dep.append("\0");
            }
        }
        dep.append("\0");

        SERVICE_DESCRIPTION desc = new SERVICE_DESCRIPTION();
        desc.lpDescription = description;

        SC_HANDLE serviceManager = openServiceControlManager(null, Winsvc.SC_MANAGER_ALL_ACCESS);

        if (serviceManager != null) {
            SC_HANDLE service = ADVAPI_32.CreateService(serviceManager, serviceName, displayName,
                    Winsvc.SERVICE_ALL_ACCESS, WinNT.SERVICE_WIN32_OWN_PROCESS, WinNT.SERVICE_AUTO_START,
                    WinNT.SERVICE_ERROR_NORMAL,
                    command,
                    null, null, dep.toString(), account, password);

            if (service != null) {
                ADVAPI_32.ChangeServiceConfig2(service, Winsvc.SERVICE_CONFIG_DESCRIPTION, desc);
                ADVAPI_32.CloseServiceHandle(service);
            }
            ADVAPI_32.CloseServiceHandle(serviceManager);
        }
    }