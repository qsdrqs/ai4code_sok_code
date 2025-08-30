llvm::Function* Decoder::splitFunctionOn(
		utils::Address addr,
		llvm::BasicBlock* splitOnBb)
{
	LOG << "\t\t\t\t" << "S: splitFunctionOn @ " << addr << " on "
			<< splitOnBb->getName().str() << std::endl;

	if (splitOnBb->getPrevNode() == nullptr)
	{
		LOG << "\t\t\t\t" << "S: BB first @ " << addr << std::endl;
		return splitOnBb->getParent();
	}
	std::set<BasicBlock*> newFncStarts;
	if (!canSplitFunctionOn(addr, splitOnBb, newFncStarts))
	{
		LOG << "\t\t\t\t" << "S: !canSplitFunctionOn() @ " << addr << std::endl;
		return nullptr;
	}

	llvm::Function* ret = nullptr;
	std::set<Function*> newFncs;
	for (auto* splitBb : newFncStarts)
	{
		Address splitAddr = getBasicBlockAddress(splitBb);

		LOG << "\t\t\t\t" << "S: splitting @ " << splitAddr << " on "
				<< splitBb->getName().str() << std::endl;

		std::string name = _names->getPreferredNameForAddress(splitAddr);
		if (name.empty())
		{
			name = names::generateFunctionName(splitAddr, _config->getConfig().isIda());
		}

		Function* oldFnc = splitBb->getParent();
		Function* newFnc = Function::Create(
				FunctionType::get(oldFnc->getReturnType(), false),
				oldFnc->getLinkage(),
				name);
		oldFnc->getParent()->getFunctionList().insertAfter(
				oldFnc->getIterator(),
				newFnc);

		addFunction(splitAddr, newFnc);

		newFnc->getBasicBlockList().splice(
				newFnc->begin(),
				oldFnc->getBasicBlockList(),
				splitBb->getIterator(),
				oldFnc->getBasicBlockList().end());

		newFncs.insert(oldFnc);
		newFncs.insert(newFnc);
		if (splitOnBb == splitBb)
		{
			ret = newFnc;
		}
	}
	assert(ret);

	for (Function* f : newFncs)
	for (BasicBlock& b : *f)
	{
		auto* br = dyn_cast<BranchInst>(b.getTerminator());
		if (br
				&& (br->getSuccessor(0)->getParent() != br->getFunction()
				|| br->getSuccessor(0)->getPrevNode() == nullptr))
		{
			auto* callee = br->getSuccessor(0)->getParent();
			auto* c = CallInst::Create(callee, "", br);
			if (auto* retObj = getCallReturnObject())
			{
				auto* cc = cast<Instruction>(
						IrModifier::convertValueToTypeAfter(c, retObj->getValueType(), c));
				auto* s = new StoreInst(cc, retObj);
				s->insertAfter(cc);
			}

			ReturnInst::Create(
					br->getModule()->getContext(),
					UndefValue::get(br->getFunction()->getReturnType()),
					br);
			br->eraseFromParent();
		}

		// Test.
		for (auto* s : successors(&b))
		{
			if (b.getParent() != s->getParent())
			{
				dumpModuleToFile(_module, _config->getOutputDirectory());
			}
			assert(b.getParent() == s->getParent());
		}
	}

	return ret;
}