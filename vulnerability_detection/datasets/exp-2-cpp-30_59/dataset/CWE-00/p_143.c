void Parser::AddArgumentsNodeToVars(ParseNodePtr pnodeFnc)
{
    if((pnodeFnc->grfpn & PNodeFlags::fpnArguments_overriddenByDecl) || pnodeFnc->sxFnc.IsLambda())
    {
        // In any of the following cases, there is no way to reference the built-in 'arguments' variable (in the order of checks
        // above):
        //     - A function parameter is named 'arguments'
        //     - There is a nested function declaration (or named function expression in compat modes) named 'arguments'
        //     - In compat modes, the function is named arguments, does not have a var declaration named 'arguments', and does
        //       not call 'eval'
        pnodeFnc->sxFnc.SetHasReferenceableBuiltInArguments(false);
    }
    else
    {
        ParseNodePtr argNode = nullptr;
        if(m_ppnodeVar == &pnodeFnc->sxFnc.pnodeVars)
        {
            // There were no var declarations in the function
            argNode = CreateVarDeclNode(wellKnownPropertyPids.arguments, STVariable, true, pnodeFnc);
        }
        else
        {
            // There were var declarations in the function, so insert an 'arguments' local at the beginning of the var list.
            // This is done because the built-in 'arguments' variable overrides an 'arguments' var declaration until the
            // 'arguments' variable is assigned. By putting our built-in var declaration at the beginning, an 'arguments'
            // identifier will resolve to this symbol, which has the fpnArguments flag set, and will be the built-in arguments
            // object until it is replaced with something else.
            ParseNodePtr *const ppnodeVarSave = m_ppnodeVar;
            m_ppnodeVar = &pnodeFnc->sxFnc.pnodeVars;
            argNode = CreateVarDeclNode(wellKnownPropertyPids.arguments, STVariable, true, pnodeFnc);
            m_ppnodeVar = ppnodeVarSave;
        }

        Assert(argNode);
        argNode->grfpn |= PNodeFlags::fpnArguments;

        // When a function definition with the name arguments occurs in the body the declaration of the arguments symbol will
        // be set to that function declaration. We should change it to arguments declaration from the param scope as it may be
        // used in the param scope and we have to load the arguments.
        argNode->sxVar.sym->SetDecl(argNode);

        pnodeFnc->sxFnc.SetHasReferenceableBuiltInArguments(true);
    }
}