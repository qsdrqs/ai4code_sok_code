void readTOMLConf(toml::value &root)
{
    const auto &section_common = toml::find(root, "common");
    string_array default_url, insert_url;

    find_if_exist(section_common, "default_url", default_url, "insert_url", insert_url);
    global.defaultUrls = join(default_url, "|");
    global.insertUrls = join(insert_url, "|");

    bool filter = false;
    find_if_exist(section_common,
                  "api_mode", global.APIMode,
                  "api_access_token", global.accessToken,
                  "exclude_remarks", global.excludeRemarks,
                  "include_remarks", global.includeRemarks,
                  "enable_insert", global.enableInsert,
                  "prepend_insert_url", global.prependInsert,
                  "enable_filter", filter,
                  "default_external_config", global.defaultExtConfig,
                  "base_path", global.basePath,
                  "clash_rule_base", global.clashBase,
                  "surge_rule_base", global.surgeBase,
                  "surfboard_rule_base", global.surfboardBase,
                  "mellow_rule_base", global.mellowBase,
                  "quan_rule_base", global.quanBase,
                  "quanx_rule_base", global.quanXBase,
                  "loon_rule_base", global.loonBase,
                  "proxy_config", global.proxyConfig,
                  "proxy_ruleset", global.proxyRuleset,
                  "proxy_subscription", global.proxySubscription,
                  "append_proxy_type", global.appendType
    );

    if(filter)
        find_if_exist(section_common, "filter_script", global.filterScript);
    else
        global.filterScript.clear();

    safe_set_streams(toml::find_or<RegexMatchConfigs>(root, "userinfo", "stream_rule", RegexMatchConfigs{}));
    safe_set_times(toml::find_or<RegexMatchConfigs>(root, "userinfo", "time_rule", RegexMatchConfigs{}));

    const auto &section_node_pref = toml::find(root, "node_pref");

    find_if_exist(section_node_pref,
                  "udp_flag", global.UDPFlag,
                  "tcp_fast_open_flag", global.TFOFlag,
                  "skip_cert_verify_flag", global.skipCertVerify,
                  "tls13_flag", global.TLS13Flag,
                  "sort_flag", global.enableSort,
                  "sort_script", global.sortScript,
                  "filter_deprecated_nodes", global.filterDeprecated,
                  "append_sub_userinfo", global.appendUserinfo,
                  "clash_use_new_field_name", global.clashUseNewField,
                  "clash_proxies_style", global.clashProxiesStyle
    );

    auto renameconfs = toml::find_or<std::vector<toml::value>>(section_node_pref, "rename_node", {});
    importItems(renameconfs, "rename_node", false);
    safe_set_renames(toml::get<RegexMatchConfigs>(toml::value(renameconfs)));

    const auto &section_managed = toml::find(root, "managed_config");

    find_if_exist(section_managed,
                  "write_managed_config", global.writeManagedConfig,
                  "managed_config_prefix", global.managedConfigPrefix,
                  "config_update_interval", global.updateInterval,
                  "config_update_strict", global.updateStrict,
                  "quanx_device_id", global.quanXDevID
    );

    const auto &section_surge_external = toml::find(root, "surge_external_proxy");
    find_if_exist(section_surge_external,
                  "surge_ssr_path", global.surgeSSRPath,
                  "resolve_hostname", global.surgeResolveHostname
    );

    const auto &section_emojis = toml::find(root, "emojis");

    find_if_exist(section_emojis,
                  "add_emoji", global.addEmoji,
                  "remove_old_emoji", global.removeEmoji
    );

    auto emojiconfs = toml::find_or<std::vector<toml::value>>(section_emojis, "emoji", {});
    importItems(emojiconfs, "emoji", false);
    safe_set_emojis(toml::get<RegexMatchConfigs>(toml::value(emojiconfs)));

    auto groups = toml::find_or<std::vector<toml::value>>(root, "custom_groups", {});
    importItems(groups, "custom_groups", false);
    global.customProxyGroups = toml::get<ProxyGroupConfigs>(toml::value(groups));

    const auto &section_ruleset = toml::find(root, "ruleset");

    find_if_exist(section_ruleset,
                  "enabled", global.enableRuleGen,
                  "overwrite_original_rules", global.overwriteOriginalRules,
                  "update_ruleset_on_request", global.updateRulesetOnRequest
    );

    auto rulesets = toml::find_or<std::vector<toml::value>>(root, "rulesets", {});
    importItems(rulesets, "rulesets", false);
    global.customRulesets = toml::get<RulesetConfigs>(toml::value(rulesets));

    const auto &section_template = toml::find(root, "template");

    global.templatePath = toml::find_or(section_template, "template_path", "template");

    eraseElements(global.templateVars);
    operate_toml_kv_table(toml::find_or<std::vector<toml::table>>(section_template, "globals", {}), "key", "value", [&](const toml::value &key, const toml::value &value)
    {
        global.templateVars[key.as_string()] = value.as_string();
    });

    webServer.reset_redirect();
    operate_toml_kv_table(toml::find_or<std::vector<toml::table>>(root, "aliases", {}), "uri", "target", [&](const toml::value &key, const toml::value &value)
    {
        webServer.append_redirect(key.as_string(), value.as_string());
    });

    auto tasks = toml::find_or<std::vector<toml::value>>(root, "tasks", {});
    importItems(tasks, "tasks", false);
    global.cronTasks = toml::get<CronTaskConfigs>(toml::value(tasks));

    const auto &section_server = toml::find(root, "server");

    find_if_exist(section_server,
                  "listen", global.listenAddress,
                  "port", global.listenPort,
                  "serve_file_root", webServer.serve_file_root
    );
    webServer.serve_file = !webServer.serve_file_root.empty();

    const auto &section_advanced = toml::find(root, "advanced");

    std::string log_level;
    bool enable_cache = true;
    int cache_subscription = global.cacheSubscription, cache_config = global.cacheConfig, cache_ruleset = global.cacheRuleset;

    find_if_exist(section_advanced,
                  "log_level", log_level,
                  "print_debug_info", global.printDbgInfo,
                  "max_pending_connections", global.maxPendingConns,
                  "max_concurrent_threads", global.maxConcurThreads,
                  "max_allowed_rulesets", global.maxAllowedRulesets,
                  "max_allowed_rules", global.maxAllowedRules,
                  "max_allowed_download_size", global.maxAllowedDownloadSize,
                  "enable_cache", enable_cache,
                  "cache_subscription", cache_subscription,
                  "cache_config", cache_config,
                  "cache_ruleset", cache_ruleset,
                  "script_clean_context", global.scriptCleanContext,
                  "async_fetch_ruleset", global.asyncFetchRuleset,
                  "skip_failed_links", global.skipFailedLinks
    );

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

    if(enable_cache)
    {
        global.cacheSubscription = cache_subscription;
        global.cacheConfig = cache_config;
        global.cacheRuleset = cache_ruleset;
    }
    else
    {
        global.cacheSubscription = global.cacheConfig = global.cacheRuleset = 0;
    }
}