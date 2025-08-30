std::string subconverter(RESPONSE_CALLBACK_ARGS)
{
    std::string &argument = request.argument;
    int *status_code = &response.status_code;

    std::string argTarget = getUrlArg(argument, "target"), argSurgeVer = getUrlArg(argument, "ver");
    tribool argClashNewField = getUrlArg(argument, "new_name");
    int intSurgeVer = argSurgeVer.size() ? to_int(argSurgeVer, 3) : 3;
    if(argTarget == "auto")
        matchUserAgent(request.headers["User-Agent"], argTarget, argClashNewField, intSurgeVer);

    /// don't try to load groups or rulesets when generating simple subscriptions
    bool lSimpleSubscription = false;
    switch(hash_(argTarget))
    {
    case "ss"_hash: case "ssd"_hash: case "ssr"_hash: case "sssub"_hash: case "v2ray"_hash: case "trojan"_hash: case "mixed"_hash:
        lSimpleSubscription = true;
        break;
    case "clash"_hash: case "clashr"_hash: case "surge"_hash: case "quan"_hash: case "quanx"_hash: case "loon"_hash: case "surfboard"_hash: case "mellow"_hash:
        break;
    default:
        *status_code = 400;
        return "Invalid target!";
    }
    //check if we need to read configuration
    if((!global.APIMode || global.CFWChildProcess) && !global.generatorMode)
        readConf();

    /// string values
    std::string argUrl = urlDecode(getUrlArg(argument, "url"));
    std::string argGroupName = urlDecode(getUrlArg(argument, "group")), argUploadPath = getUrlArg(argument, "upload_path");
    std::string argIncludeRemark = urlDecode(getUrlArg(argument, "include")), argExcludeRemark = urlDecode(getUrlArg(argument, "exclude"));
    std::string argCustomGroups = urlSafeBase64Decode(getUrlArg(argument, "groups")), argCustomRulesets = urlSafeBase64Decode(getUrlArg(argument, "ruleset")), argExternalConfig = urlDecode(getUrlArg(argument, "config"));
    std::string argDeviceID = getUrlArg(argument, "dev_id"), argFilename = urlDecode(getUrlArg(argument, "filename")), argUpdateInterval = getUrlArg(argument, "interval"), argUpdateStrict = getUrlArg(argument, "strict");
    std::string argRenames = urlDecode(getUrlArg(argument, "rename")), argFilterScript = urlDecode(getUrlArg(argument, "filter_script"));

    /// switches with default value
    tribool argUpload = getUrlArg(argument, "upload"), argEmoji = getUrlArg(argument, "emoji"), argAddEmoji = getUrlArg(argument, "add_emoji"), argRemoveEmoji = getUrlArg(argument, "remove_emoji");
    tribool argAppendType = getUrlArg(argument, "append_type"), argTFO = getUrlArg(argument, "tfo"), argUDP = getUrlArg(argument, "udp"), argGenNodeList = getUrlArg(argument, "list");
    tribool argSort = getUrlArg(argument, "sort"), argUseSortScript = getUrlArg(argument, "sort_script");
    tribool argGenClashScript = getUrlArg(argument, "script"), argEnableInsert = getUrlArg(argument, "insert");
    tribool argSkipCertVerify = getUrlArg(argument, "scv"), argFilterDeprecated = getUrlArg(argument, "fdn"), argExpandRulesets = getUrlArg(argument, "expand"), argAppendUserinfo = getUrlArg(argument, "append_info");
    tribool argPrependInsert = getUrlArg(argument, "prepend"), argGenClassicalRuleProvider = getUrlArg(argument, "classic"), argTLS13 = getUrlArg(argument, "tls13");

    std::string base_content, output_content;
    ProxyGroupConfigs lCustomProxyGroups = global.customProxyGroups;
    RulesetConfigs lCustomRulesets = global.customRulesets;
    string_array lIncludeRemarks = global.includeRemarks, lExcludeRemarks = global.excludeRemarks;
    std::vector<RulesetContent> lRulesetContent;
    extra_settings ext;
    std::string subInfo, dummy;
    int interval = argUpdateInterval.size() ? to_int(argUpdateInterval, global.updateInterval) : global.updateInterval;
    bool authorized = !global.APIMode || getUrlArg(argument, "token") == global.accessToken, strict = argUpdateStrict.size() ? argUpdateStrict == "true" : global.updateStrict;

    if(std::find(gRegexBlacklist.cbegin(), gRegexBlacklist.cend(), argIncludeRemark) != gRegexBlacklist.cend() || std::find(gRegexBlacklist.cbegin(), gRegexBlacklist.cend(), argExcludeRemark) != gRegexBlacklist.cend())
        return "Invalid request!";

    /// for external configuration
    std::string lClashBase = global.clashBase, lSurgeBase = global.surgeBase, lMellowBase = global.mellowBase, lSurfboardBase = global.surfboardBase;
    std::string lQuanBase = global.quanBase, lQuanXBase = global.quanXBase, lLoonBase = global.loonBase, lSSSubBase = global.SSSubBase;

    /// validate urls
    argEnableInsert.define(global.enableInsert);
    if(!argUrl.size() && (!global.APIMode || authorized))
        argUrl = global.defaultUrls;
    if((!argUrl.size() && !(global.insertUrls.size() && argEnableInsert)) || !argTarget.size())
    {
        *status_code = 400;
        return "Invalid request!";
    }

    /// load request arguments as template variables
    string_array req_args = split(argument, "&");
    string_map req_arg_map;
    for(std::string &x : req_args)
    {
        string_size pos = x.find("=");
        if(pos == x.npos)
        {
            req_arg_map[x] = "";
            continue;
        }
        if(x.substr(0, pos) == "token")
            continue;
        req_arg_map[x.substr(0, pos)] = x.substr(pos + 1);
    }
    req_arg_map["target"] = argTarget;
    req_arg_map["ver"] = std::to_string(intSurgeVer);

    /// save template variables
    template_args tpl_args;
    tpl_args.global_vars = global.templateVars;
    tpl_args.request_params = req_arg_map;

    /// check for proxy settings
    std::string proxy = parseProxy(global.proxySubscription);

    /// check other flags
    ext.append_proxy_type = argAppendType.get(global.appendType);
    if((argTarget == "clash" || argTarget == "clashr") && argGenClashScript.is_undef())
        argExpandRulesets.define(true);

    ext.clash_proxies_style = global.clashProxiesStyle;

    /// read preference from argument, assign global var if not in argument
    ext.tfo.define(argTFO).define(global.TFOFlag);
    ext.udp.define(argUDP).define(global.UDPFlag);
    ext.skip_cert_verify.define(argSkipCertVerify).define(global.skipCertVerify);
    ext.tls13.define(argTLS13).define(global.TLS13Flag);

    ext.sort_flag = argSort.get(global.enableSort);
    argUseSortScript.define(global.sortScript.size() != 0);
    if(ext.sort_flag && argUseSortScript)
        ext.sort_script = global.sortScript;
    ext.filter_deprecated = argFilterDeprecated.get(global.filterDeprecated);
    ext.clash_new_field_name = argClashNewField.get(global.clashUseNewField);
    ext.clash_script = argGenClashScript.get();
    ext.clash_classical_ruleset = argGenClassicalRuleProvider.get();
    if(!argExpandRulesets)
        ext.clash_new_field_name = true;
    else
        ext.clash_script = false;

    ext.nodelist = argGenNodeList;
    ext.surge_ssr_path = global.surgeSSRPath;
    ext.quanx_dev_id = argDeviceID.size() ? argDeviceID : global.quanXDevID;
    ext.enable_rule_generator = global.enableRuleGen;
    ext.overwrite_original_rules = global.overwriteOriginalRules;
    if(!argExpandRulesets)
        ext.managed_config_prefix = global.managedConfigPrefix;

    /// load external configuration
    if(argExternalConfig.empty())
        argExternalConfig = global.defaultExtConfig;
    if(argExternalConfig.size())
    {
        //std::cerr<<"External configuration file provided. Loading...\n";
        writeLog(0, "External configuration file provided. Loading...", LOG_LEVEL_INFO);
        ExternalConfig extconf;
        extconf.tpl_args = &tpl_args;
        if(loadExternalConfig(argExternalConfig, extconf) == 0)
        {
            if(!ext.nodelist)
            {
                checkExternalBase(extconf.sssub_rule_base, lSSSubBase);
                if(!lSimpleSubscription)
                {
                    checkExternalBase(extconf.clash_rule_base, lClashBase);
                    checkExternalBase(extconf.surge_rule_base, lSurgeBase);
                    checkExternalBase(extconf.surfboard_rule_base, lSurfboardBase);
                    checkExternalBase(extconf.mellow_rule_base, lMellowBase);
                    checkExternalBase(extconf.quan_rule_base, lQuanBase);
                    checkExternalBase(extconf.quanx_rule_base, lQuanXBase);
                    checkExternalBase(extconf.loon_rule_base, lLoonBase);

                    if(extconf.surge_ruleset.size())
                        lCustomRulesets = extconf.surge_ruleset;
                    if(extconf.custom_proxy_group.size())
                        lCustomProxyGroups = extconf.custom_proxy_group;
                    ext.enable_rule_generator = extconf.enable_rule_generator;
                    ext.overwrite_original_rules = extconf.overwrite_original_rules;
                }
            }
            if(extconf.rename.size())
                ext.rename_array = extconf.rename;
            if(extconf.emoji.size())
                ext.emoji_array = extconf.emoji;
            if(extconf.include.size())
                lIncludeRemarks = extconf.include;
            if(extconf.exclude.size())
                lExcludeRemarks = extconf.exclude;
            argAddEmoji.define(extconf.add_emoji);
            argRemoveEmoji.define(extconf.remove_old_emoji);
        }
    }
    else
    {
        if(!lSimpleSubscription)
        {
            /// loading custom groups
            if(argCustomGroups.size() && !ext.nodelist)
            {
                string_array vArray = split(argCustomGroups, "@");
                lCustomProxyGroups = INIBinding::from<ProxyGroupConfig>::from_ini(vArray);
            }

            /// loading custom rulesets
            if(argCustomRulesets.size() && !ext.nodelist)
            {
                string_array vArray = split(argCustomRulesets, "@");
                lCustomRulesets = INIBinding::from<RulesetConfig>::from_ini(vArray);
            }
        }
    }
    if(ext.enable_rule_generator && !ext.nodelist && !lSimpleSubscription)
    {
        if(lCustomRulesets != global.customRulesets)
            refreshRulesets(lCustomRulesets, lRulesetContent);
        else
        {
            if(global.updateRulesetOnRequest)
                refreshRulesets(global.customRulesets, global.rulesetsContent);
            lRulesetContent = global.rulesetsContent;
        }
    }

    if(!argEmoji.is_undef())
    {
        argAddEmoji.set(argEmoji);
        argRemoveEmoji.set(true);
    }
    ext.add_emoji = argAddEmoji.get(global.addEmoji);
    ext.remove_emoji = argRemoveEmoji.get(global.removeEmoji);
    if(ext.add_emoji && ext.emoji_array.empty())
        ext.emoji_array = safe_get_emojis();
    if(argRenames.size())
        ext.rename_array = INIBinding::from<RegexMatchConfig>::from_ini(split(argRenames, "`"), "@");
    else if(ext.rename_array.empty())
        ext.rename_array = safe_get_renames();

    /// check custom include/exclude settings
    if(argIncludeRemark.size() && regValid(argIncludeRemark))
        lIncludeRemarks = string_array{argIncludeRemark};
    if(argExcludeRemark.size() && regValid(argExcludeRemark))
        lExcludeRemarks = string_array{argExcludeRemark};

    /// initialize script runtime
    if(authorized && !global.scriptCleanContext)
    {
        ext.js_runtime = new qjs::Runtime();
        script_runtime_init(*ext.js_runtime);
        ext.js_context = new qjs::Context(*ext.js_runtime);
        script_context_init(*ext.js_context);
    }

    //start parsing urls
    RegexMatchConfigs stream_temp = safe_get_streams(), time_temp = safe_get_times();

    //loading urls
    string_array urls;
    std::vector<Proxy> nodes, insert_nodes;
    int groupID = 0;

    parse_settings parse_set;
    parse_set.proxy = &proxy;
    parse_set.exclude_remarks = &lExcludeRemarks;
    parse_set.include_remarks = &lIncludeRemarks;
    parse_set.stream_rules = &stream_temp;
    parse_set.time_rules = &time_temp;
    parse_set.sub_info = &subInfo;
    parse_set.authorized = authorized;
    parse_set.request_header = &request.headers;
    parse_set.js_runtime = ext.js_runtime;
    parse_set.js_context = ext.js_context;

    if(global.insertUrls.size() && argEnableInsert)
    {
        groupID = -1;
        urls = split(global.insertUrls, "|");
        importItems(urls, true);
        for(std::string &x : urls)
        {
            x = regTrim(x);
            writeLog(0, "Fetching node data from url '" + x + "'.", LOG_LEVEL_INFO);
            if(addNodes(x, insert_nodes, groupID, parse_set) == -1)
            {
                if(global.skipFailedLinks)
                    writeLog(0, "The following link doesn't contain any valid node info: " + x, LOG_LEVEL_WARNING);
                else
                {
                    *status_code = 400;
                    return "The following link doesn't contain any valid node info: " + x;
                }
            }
            groupID--;
        }
    }
    urls = split(argUrl, "|");
    importItems(urls, true);
    groupID = 0;
    for(std::string &x : urls)
    {
        x = regTrim(x);
        //std::cerr<<"Fetching node data from url '"<<x<<"'."<<std::endl;
        writeLog(0, "Fetching node data from url '" + x + "'.", LOG_LEVEL_INFO);
        if(addNodes(x, nodes, groupID, parse_set) == -1)
        {
            if(global.skipFailedLinks)
                writeLog(0, "The following link doesn't contain any valid node info: " + x, LOG_LEVEL_WARNING);
            else
            {
                *status_code = 400;
                return "The following link doesn't contain any valid node info: " + x;
            }
        }
        groupID++;
    }
    //exit if found nothing
    if(!nodes.size() && !insert_nodes.size())
    {
        *status_code = 400;
        return "No nodes were found!";
    }
    if(subInfo.size() && argAppendUserinfo.get(global.appendUserinfo))
        response.headers.emplace("Subscription-UserInfo", subInfo);

    if(request.method == "HEAD")
        return "";

    argPrependInsert.define(global.prependInsert);
    if(argPrependInsert)
    {
        std::move(nodes.begin(), nodes.end(), std::back_inserter(insert_nodes));
        nodes.swap(insert_nodes);
    }
    else
    {
        std::move(insert_nodes.begin(), insert_nodes.end(), std::back_inserter(nodes));
    }
    //run filter script
    std::string filterScript = global.filterScript;
    if(authorized && !argFilterScript.empty())
        filterScript = argFilterScript;
    if(filterScript.size())
    {
        if(startsWith(filterScript, "path:"))
            filterScript = fileGet(filterScript.substr(5), false);
        /*
        duk_context *ctx = duktape_init();
        if(ctx)
        {
            defer(duk_destroy_heap(ctx);)
            if(duktape_peval(ctx, filterScript) == 0)
            {
                auto filter = [&](const Proxy &x)
                {
                    duk_get_global_string(ctx, "filter");
                    duktape_push_Proxy(ctx, x);
                    duk_pcall(ctx, 1);
                    return !duktape_get_res_bool(ctx);
                };
                nodes.erase(std::remove_if(nodes.begin(), nodes.end(), filter), nodes.end());
            }
            else
            {
                writeLog(0, "Error when trying to parse script:\n" + duktape_get_err_stack(ctx), LOG_LEVEL_ERROR);
                duk_pop(ctx); /// pop err
            }
        }
        */
        script_safe_runner(ext.js_runtime, ext.js_context, [&](qjs::Context &ctx)
        {
            try
            {
                ctx.eval(filterScript);
                auto filter = (std::function<bool(const Proxy&)>) ctx.eval("filter");
                nodes.erase(std::remove_if(nodes.begin(), nodes.end(), filter), nodes.end());
            }
            catch(qjs::exception)
            {
                script_print_stack(ctx);
            }
        }, global.scriptCleanContext);
    }

    //check custom group name
    if(argGroupName.size())
        for(Proxy &x : nodes)
            x.Group = argGroupName;

    //do pre-process now
    preprocessNodes(nodes, ext);

    /*
    //insert node info to template
    int index = 0;
    std::string template_node_prefix;
    for(Proxy &x : nodes)
    {
        template_node_prefix = std::to_string(index) + ".";
        tpl_args.node_list[template_node_prefix + "remarks"] = x.remarks;
        tpl_args.node_list[template_node_prefix + "group"] = x.Group;
        tpl_args.node_list[template_node_prefix + "groupid"] = std::to_string(x.GroupId);
        index++;
    }
    */

    ProxyGroupConfigs dummy_group;
    std::vector<RulesetContent> dummy_ruleset;
    std::string managed_url = base64Decode(urlDecode(getUrlArg(argument, "profile_data")));
    if(managed_url.empty())
        managed_url = global.managedConfigPrefix + "/sub?" + argument;

    //std::cerr<<"Generate target: ";
    proxy = parseProxy(global.proxyConfig);
    switch(hash_(argTarget))
    {
    case "clash"_hash: case "clashr"_hash:
        writeLog(0, argTarget == "clashr" ? "Generate target: ClashR" : "Generate target: Clash", LOG_LEVEL_INFO);
        tpl_args.local_vars["clash.new_field_name"] = ext.clash_new_field_name ? "true" : "false";
        response.headers["profile-update-interval"] = std::to_string(interval / 3600);
        if(ext.nodelist)
        {
            YAML::Node yamlnode;
            proxyToClash(nodes, yamlnode, dummy_group, argTarget == "clashr", ext);
            output_content = YAML::Dump(yamlnode);
        }
        else
        {
            if(render_template(fetchFile(lClashBase, proxy, global.cacheConfig), tpl_args, base_content, global.templatePath) != 0)
            {
                *status_code = 400;
                return base_content;
            }
            output_content = proxyToClash(nodes, base_content, lRulesetContent, lCustomProxyGroups, argTarget == "clashr", ext);
        }

        if(argUpload)
            uploadGist(argTarget, argUploadPath, output_content, false);
        break;
    case "surge"_hash:

        writeLog(0, "Generate target: Surge " + std::to_string(intSurgeVer), LOG_LEVEL_INFO);

        if(ext.nodelist)
        {
            output_content = proxyToSurge(nodes, base_content, dummy_ruleset, dummy_group, intSurgeVer, ext);

            if(argUpload)
                uploadGist("surge" + argSurgeVer + "list", argUploadPath, output_content, true);
        }
        else
        {
            if(render_template(fetchFile(lSurgeBase, proxy, global.cacheConfig), tpl_args, base_content, global.templatePath) != 0)
            {
                *status_code = 400;
                return base_content;
            }
            output_content = proxyToSurge(nodes, base_content, lRulesetContent, lCustomProxyGroups, intSurgeVer, ext);

            if(argUpload)
                uploadGist("surge" + argSurgeVer, argUploadPath, output_content, true);

            if(global.writeManagedConfig && global.managedConfigPrefix.size())
                output_content = "#!MANAGED-CONFIG " + managed_url + (interval ? " interval=" + std::to_string(interval) : "") \
                 + " strict=" + std::string(strict ? "true" : "false") + "\n\n" + output_content;
        }
        break;
    case "surfboard"_hash:
        writeLog(0, "Generate target: Surfboard", LOG_LEVEL_INFO);

        if(render_template(fetchFile(lSurfboardBase, proxy, global.cacheConfig), tpl_args, base_content, global.templatePath) != 0)
        {
            *status_code = 400;
            return base_content;
        }
        output_content = proxyToSurge(nodes, base_content, lRulesetContent, lCustomProxyGroups, -3, ext);
        if(argUpload)
            uploadGist("surfboard", argUploadPath, output_content, true);

        if(global.writeManagedConfig && global.managedConfigPrefix.size())
            output_content = "#!MANAGED-CONFIG " + managed_url + (interval ? " interval=" + std::to_string(interval) : "") \
                 + " strict=" + std::string(strict ? "true" : "false") + "\n\n" + output_content;
        break;
    case "mellow"_hash:
        writeLog(0, "Generate target: Mellow", LOG_LEVEL_INFO);

        if(render_template(fetchFile(lMellowBase, proxy, global.cacheConfig), tpl_args, base_content, global.templatePath) != 0)
        {
            *status_code = 400;
            return base_content;
        }
        output_content = proxyToMellow(nodes, base_content, lRulesetContent, lCustomProxyGroups, ext);

        if(argUpload)
            uploadGist("mellow", argUploadPath, output_content, true);
        break;
    case "sssub"_hash:
        writeLog(0, "Generate target: SS Subscription", LOG_LEVEL_INFO);

        if(render_template(fetchFile(lSSSubBase, proxy, global.cacheConfig), tpl_args, base_content, global.templatePath) != 0)
        {
            *status_code = 400;
            return base_content;
        }
        output_content = proxyToSSSub(base_content, nodes, ext);
        if(argUpload)
            uploadGist("sssub", argUploadPath, output_content, false);
        break;
    case "ss"_hash:
        writeLog(0, "Generate target: SS", LOG_LEVEL_INFO);
        output_content = proxyToSingle(nodes, 1, ext);
        if(argUpload)
            uploadGist("ss", argUploadPath, output_content, false);
        break;
    case "ssr"_hash:
        writeLog(0, "Generate target: SSR", LOG_LEVEL_INFO);
        output_content = proxyToSingle(nodes, 2, ext);
        if(argUpload)
            uploadGist("ssr", argUploadPath, output_content, false);
        break;
    case "v2ray"_hash:
        writeLog(0, "Generate target: v2rayN", LOG_LEVEL_INFO);
        output_content = proxyToSingle(nodes, 4, ext);
        if(argUpload)
            uploadGist("v2ray", argUploadPath, output_content, false);
        break;
    case "trojan"_hash:
        writeLog(0, "Generate target: Trojan", LOG_LEVEL_INFO);
        output_content = proxyToSingle(nodes, 8, ext);
        if(argUpload)
            uploadGist("trojan", argUploadPath, output_content, false);
        break;
    case "mixed"_hash:
        writeLog(0, "Generate target: Standard Subscription", LOG_LEVEL_INFO);
        output_content = proxyToSingle(nodes, 15, ext);
        if(argUpload)
            uploadGist("sub", argUploadPath, output_content, false);
        break;
    case "quan"_hash:
        writeLog(0, "Generate target: Quantumult", LOG_LEVEL_INFO);
        if(!ext.nodelist)
        {
            if(render_template(fetchFile(lQuanBase, proxy, global.cacheConfig), tpl_args, base_content, global.templatePath) != 0)
            {
                *status_code = 400;
                return base_content;
            }
        }

        output_content = proxyToQuan(nodes, base_content, lRulesetContent, lCustomProxyGroups, ext);

        if(argUpload)
            uploadGist("quan", argUploadPath, output_content, false);
        break;
    case "quanx"_hash:
        writeLog(0, "Generate target: Quantumult X", LOG_LEVEL_INFO);
        if(!ext.nodelist)
        {
            if(render_template(fetchFile(lQuanXBase, proxy, global.cacheConfig), tpl_args, base_content, global.templatePath) != 0)
            {
                *status_code = 400;
                return base_content;
            }
        }

        output_content = proxyToQuanX(nodes, base_content, lRulesetContent, lCustomProxyGroups, ext);

        if(argUpload)
            uploadGist("quanx", argUploadPath, output_content, false);
        break;
    case "loon"_hash:
        writeLog(0, "Generate target: Loon", LOG_LEVEL_INFO);
        if(!ext.nodelist)
        {
            if(render_template(fetchFile(lLoonBase, proxy, global.cacheConfig), tpl_args, base_content, global.templatePath) != 0)
            {
                *status_code = 400;
                return base_content;
            }
        }

        output_content = proxyToLoon(nodes, base_content, lRulesetContent, lCustomProxyGroups, ext);

        if(argUpload)
            uploadGist("loon", argUploadPath, output_content, false);
        break;
    case "ssd"_hash:
        writeLog(0, "Generate target: SSD", LOG_LEVEL_INFO);
        output_content = proxyToSSD(nodes, argGroupName, subInfo, ext);
        if(argUpload)
            uploadGist("ssd", argUploadPath, output_content, false);
        break;
    default:
        writeLog(0, "Generate target: Unspecified", LOG_LEVEL_INFO);
        *status_code = 500;
        return "Unrecognized target";
    }
    writeLog(0, "Generate completed.", LOG_LEVEL_INFO);
    if(argFilename.size())
        response.headers.emplace("Content-Disposition", "attachment; filename=\"" + argFilename + "\"; filename*=utf-8''" + urlEncode(argFilename));
    return output_content;
}