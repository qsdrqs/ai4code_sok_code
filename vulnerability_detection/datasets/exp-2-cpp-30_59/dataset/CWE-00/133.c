void nodeRename(Proxy &node, const RegexMatchConfigs &rename_array, extra_settings &ext)
{
    std::string &remark = node.Remark, original_remark = node.Remark, returned_remark, real_rule;

    for(const RegexMatchConfig &x : rename_array)
    {
        if(!x.Script.empty())
        {
            script_safe_runner(ext.js_runtime, ext.js_context, [&](qjs::Context &ctx)
            {
                std::string script = x.Script;
                if(startsWith(script, "path:"))
                    script = fileGet(script.substr(5), true);
                try
                {
                    ctx.eval(script);
                    auto rename = (std::function<std::string(const Proxy&)>) ctx.eval("rename");
                    returned_remark = rename(node);
                    if(!returned_remark.empty())
                        remark = returned_remark;
                }
                catch (qjs::exception)
                {
                    script_print_stack(ctx);
                }
            }, global.scriptCleanContext);
            continue;
        }
        if(applyMatcher(x.Match, real_rule, node) && real_rule.size())
            remark = regReplace(remark, real_rule, x.Replace);
    }
    if(remark.empty())
        remark = original_remark;
    return;
}