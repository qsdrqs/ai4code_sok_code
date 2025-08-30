void readConf()
{
    guarded_mutex guard(gMutexConfigure);
    //std::cerr<<"Reading preference settings..."<<std::endl;
    writeLog(0, "Reading preference settings...", LOG_LEVEL_INFO);

    eraseElements(global.excludeRemarks);
    eraseElements(global.includeRemarks);
    eraseElements(global.customProxyGroups);
    eraseElements(global.customRulesets);

    try
    {
        std::string prefdata = fileGet(global.prefPath, false);
        if(prefdata.find("common:") != prefdata.npos)
        {
            YAML::Node yaml = YAML::Load(prefdata);
            if(yaml.size() && yaml["common"])
                return readYAMLConf(yaml);
        }
        toml::value conf = parseToml(prefdata, global.prefPath);
        if(!conf.is_uninitialized() && toml::find_or<int>(conf, "version", 0))
            return readTOMLConf(conf);
    }
    catch (YAML::Exception &e)
    {
        //ignore yaml parse error
    }
    catch (toml::exception &e)
    {
        //ignore toml parse error
        writeLog(0, e.what(), LOG_LEVEL_DEBUG);
    }

    INIReader ini;
    ini.allow_dup_section_titles = true;
    //ini.do_utf8_to_gbk = true;
    int retVal = ini.ParseFile(global.prefPath);
    if(retVal != INIREADER_EXCEPTION_NONE)
    {
        //std::cerr<<"Unable to load preference settings. Reason: "<<ini.GetLastError()<<"\n";
        writeLog(0, "Unable to load preference settings. Reason: " + ini.GetLastError(), LOG_LEVEL_FATAL);
        return;
    }

    string_array tempArray;

    ini.EnterSection("common");
    ini.GetBoolIfExist("api_mode", global.APIMode);
    ini.GetIfExist("api_access_token", global.accessToken);
    ini.GetIfExist("default_url", global.defaultUrls);
    global.enableInsert = ini.Get("enable_insert");
    ini.GetIfExist("insert_url", global.insertUrls);
    ini.GetBoolIfExist("prepend_insert_url", global.prependInsert);
    if(ini.ItemPrefixExist("exclude_remarks"))
        ini.GetAll("exclude_remarks", global.excludeRemarks);
    if(ini.ItemPrefixExist("include_remarks"))
        ini.GetAll("include_remarks", global.includeRemarks);
    global.filterScript = ini.GetBool("enable_filter") ? ini.Get("filter_script"): "";
    ini.GetIfExist("base_path", global.basePath);
    ini.GetIfExist("clash_rule_base", global.clashBase);
    ini.GetIfExist("surge_rule_base", global.surgeBase);
    ini.GetIfExist("surfboard_rule_base", global.surfboardBase);
    ini.GetIfExist("mellow_rule_base", global.mellowBase);
    ini.GetIfExist("quan_rule_base", global.quanBase);
    ini.GetIfExist("quanx_rule_base", global.quanXBase);
    ini.GetIfExist("loon_rule_base", global.loonBase);
    ini.GetIfExist("default_external_config", global.defaultExtConfig);
    ini.GetBoolIfExist("append_proxy_type", global.appendType);
    ini.GetIfExist("proxy_config", global.proxyConfig);
    ini.GetIfExist("proxy_ruleset", global.proxyRuleset);
    ini.GetIfExist("proxy_subscription", global.proxySubscription);

    if(ini.SectionExist("surge_external_proxy"))
    {
        ini.EnterSection("surge_external_proxy");
        ini.GetIfExist("surge_ssr_path", global.surgeSSRPath);
        ini.GetBoolIfExist("resolve_hostname", global.surgeResolveHostname);
    }

    if(ini.SectionExist("node_pref"))
    {
        ini.EnterSection("node_pref");
        /*
        ini.GetBoolIfExist("udp_flag", udp_flag);
        ini.GetBoolIfExist("tcp_fast_open_flag", tfo_flag);
        ini.GetBoolIfExist("skip_cert_verify_flag", scv_flag);
        */
        global.UDPFlag.set(ini.Get("udp_flag"));
        global.TFOFlag.set(ini.Get("tcp_fast_open_flag"));
        global.skipCertVerify.set(ini.Get("skip_cert_verify_flag"));
        global.TLS13Flag.set(ini.Get("tls13_flag"));
        ini.GetBoolIfExist("sort_flag", global.enableSort);
        global.sortScript = ini.Get("sort_script");
        ini.GetBoolIfExist("filter_deprecated_nodes", global.filterDeprecated);
        ini.GetBoolIfExist("append_sub_userinfo", global.appendUserinfo);
        ini.GetBoolIfExist("clash_use_new_field_name", global.clashUseNewField);
        ini.GetIfExist("clash_proxies_style", global.clashProxiesStyle);
        if(ini.ItemPrefixExist("rename_node"))
        {
            ini.GetAll("rename_node", tempArray);
            importItems(tempArray, false);
            auto configs = INIBinding::from<RegexMatchConfig>::from_ini(tempArray, "@");
            safe_set_renames(configs);
            eraseElements(tempArray);
        }
    }

    if(ini.SectionExist("userinfo"))
    {
        ini.EnterSection("userinfo");
        if(ini.ItemPrefixExist("stream_rule"))
        {
            ini.GetAll("stream_rule", tempArray);
            importItems(tempArray, false);
            auto configs = INIBinding::from<RegexMatchConfig>::from_ini(tempArray, "|");
            safe_set_streams(configs);
            eraseElements(tempArray);
        }
        if(ini.ItemPrefixExist("time_rule"))
        {
            ini.GetAll("time_rule", tempArray);
            importItems(tempArray, false);
            auto configs = INIBinding::from<RegexMatchConfig>::from_ini(tempArray, "|");
            safe_set_times(configs);
            eraseElements(tempArray);
        }
    }

    ini.EnterSection("managed_config");
    ini.GetBoolIfExist("write_managed_config", global.writeManagedConfig);
    ini.GetIfExist("managed_config_prefix", global.managedConfigPrefix);
    ini.GetIntIfExist("config_update_interval", global.updateInterval);
    ini.GetBoolIfExist("config_update_strict", global.updateStrict);
    ini.GetIfExist("quanx_device_id", global.quanXDevID);

    ini.EnterSection("emojis");
    ini.GetBoolIfExist("add_emoji", global.addEmoji);
    ini.GetBoolIfExist("remove_old_emoji", global.removeEmoji);
    if(ini.ItemPrefixExist("rule"))
    {
        ini.GetAll("rule", tempArray);
        importItems(tempArray, false);
        auto configs = INIBinding::from<RegexMatchConfig>::from_ini(tempArray, ",");
        safe_set_emojis(configs);
        eraseElements(tempArray);
    }

    if(ini.SectionExist("rulesets"))
        ini.EnterSection("rulesets");
    else
        ini.EnterSection("ruleset");
    global.enableRuleGen = ini.GetBool("enabled");
    if(global.enableRuleGen)
    {
        ini.GetBoolIfExist("overwrite_original_rules", global.overwriteOriginalRules);
        ini.GetBoolIfExist("update_ruleset_on_request", global.updateRulesetOnRequest);
        if(ini.ItemPrefixExist("ruleset"))
        {
            string_array vArray;
            ini.GetAll("ruleset", vArray);
            importItems(vArray, false);
            global.customRulesets = INIBinding::from<RulesetConfig>::from_ini(vArray);
        }
        else if(ini.ItemPrefixExist("surge_ruleset"))
        {
            string_array vArray;
            ini.GetAll("surge_ruleset", vArray);
            importItems(vArray, false);
            global.customRulesets = INIBinding::from<RulesetConfig>::from_ini(vArray);
        }
    }
    else
    {
        global.overwriteOriginalRules = false;
        global.updateRulesetOnRequest = false;
    }

    if(ini.SectionExist("proxy_groups"))
        ini.EnterSection("proxy_groups");
    else
        ini.EnterSection("clash_proxy_group");
    if(ini.ItemPrefixExist("custom_proxy_group"))
    {
        string_array vArray;
        ini.GetAll("custom_proxy_group", vArray);
        importItems(vArray, false);
        global.customProxyGroups = INIBinding::from<ProxyGroupConfig>::from_ini(vArray);
    }

    ini.EnterSection("template");
    ini.GetIfExist("template_path", global.templatePath);
    string_multimap tempmap;
    ini.GetItems(tempmap);
    eraseElements(global.templateVars);
    for(auto &x : tempmap)
    {
        if(x.first == "template_path")
            continue;
        global.templateVars[x.first] = x.second;
    }
    global.templateVars["managed_config_prefix"] = global.managedConfigPrefix;

    if(ini.SectionExist("aliases"))
    {
        ini.EnterSection("aliases");
        ini.GetItems(tempmap);
        webServer.reset_redirect();
        for(auto &x : tempmap)
            webServer.append_redirect(x.first, x.second);
    }

    if(ini.SectionExist("tasks"))
    {
        string_array vArray;
        ini.EnterSection("tasks");
        ini.GetAll("task", vArray);
        importItems(vArray, false);
        global.enableCron = !vArray.empty();
        global.cronTasks = INIBinding::from<CronTaskConfig>::from_ini(vArray);
        refresh_schedule();
    }

    ini.EnterSection("server");
    ini.GetIfExist("listen", global.listenAddress);
    ini.GetIntIfExist("port", global.listenPort);
    webServer.serve_file_root = ini.Get("serve_file_root");
    webServer.serve_file = !webServer.serve_file_root.empty();

    ini.EnterSection("advanced");
    std::string log_level;
    ini.GetIfExist("log_level", log_level);
    ini.GetBoolIfExist("print_debug_info", global.printDbgInfo);
    if(global.printDbgInfo)
        global.logLevel = LOG_LEVEL_VERBOSE;
    else
    {
        switch(hash_(log_level))
        {
        case "warn"_hash:
            global.logLevel = LOG_LEVEL_WARNING;
            break;
        case "error"_hash:
            global.logLevel = LOG_LEVEL_ERROR;
            break;
        case "fatal"_hash:
            global.logLevel = LOG_LEVEL_FATAL;
            break;
        case "verbose"_hash:
            global.logLevel = LOG_LEVEL_VERBOSE;
            break;
        case "debug"_hash:
            global.logLevel = LOG_LEVEL_DEBUG;
            break;
        default:
            global.logLevel = LOG_LEVEL_INFO;
        }
    }
    ini.GetIntIfExist("max_pending_connections", global.maxPendingConns);
    ini.GetIntIfExist("max_concurrent_threads", global.maxConcurThreads);
    ini.GetNumberIfExist("max_allowed_rulesets", global.maxAllowedRulesets);
    ini.GetNumberIfExist("max_allowed_rules", global.maxAllowedRules);
    ini.GetNumberIfExist("max_allowed_download_size", global.maxAllowedDownloadSize);
    if(ini.ItemExist("enable_cache"))
    {
        if(ini.GetBool("enable_cache"))
        {
            ini.GetIntIfExist("cache_subscription", global.cacheSubscription);
            ini.GetIntIfExist("cache_config", global.cacheConfig);
            ini.GetIntIfExist("cache_ruleset", global.cacheRuleset);
            ini.GetBoolIfExist("serve_cache_on_fetch_fail", global.serveCacheOnFetchFail);
        }
        else
        {
            global.cacheSubscription = global.cacheConfig = global.cacheRuleset = 0; //disable cache
            global.serveCacheOnFetchFail = false;
        }
    }
    ini.GetBoolIfExist("script_clean_context", global.scriptCleanContext);
    ini.GetBoolIfExist("async_fetch_ruleset", global.asyncFetchRuleset);
    ini.GetBoolIfExist("skip_failed_links", global.skipFailedLinks);

    //std::cerr<<"Read preference settings completed."<<std::endl;
    writeLog(0, "Read preference settings completed.", LOG_LEVEL_INFO);
}