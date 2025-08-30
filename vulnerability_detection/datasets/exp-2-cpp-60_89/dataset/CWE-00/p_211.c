int main(int argc, char** argv)
{
	CommandLineOptions options;
	options.args = argv;
	while(*++options.args)
	{
		if(!strcmp(*options.args, "--function") || !strcmp(*options.args, "-f"))
		{
			if(!*++options.args)
			{
				showHelp();
				return EXIT_FAILURE;
			}
			options.functionName = *options.args;
		}
		else if(!strcmp(*options.args, "--check") || !strcmp(*options.args, "-c"))
		{
			options.onlyCheck = true;
		}
		else if(!strcmp(*options.args, "--debug") || !strcmp(*options.args, "-d"))
		{
			Log::setCategoryEnabled(Log::debug, true);
		}
		else if(!strcmp(*options.args, "--disable-emscripten"))
		{
			options.enableEmscripten = false;
		}
		else if(!strcmp(*options.args, "--enable-thread-test"))
		{
			options.enableThreadTest = true;
		}
		else if(!strcmp(*options.args, "--precompiled"))
		{
			options.precompiled = true;
		}
		else if(!strcmp(*options.args, "--"))
		{
			++options.args;
			break;
		}
		else if(!strcmp(*options.args, "--help") || !strcmp(*options.args, "-h"))
		{
			showHelp();
			return EXIT_SUCCESS;
		}
		else if(!options.filename)
		{
			options.filename = *options.args;
		}
		else
		{
			break;
		}
	}

	if(!options.filename)
	{
		showHelp();
		return EXIT_FAILURE;
	}

	// Treat any unhandled exception (e.g. in a thread) as a fatal error.
	Runtime::setUnhandledExceptionHandler([](Runtime::Exception&& exception) {
		Errors::fatalf("Runtime exception: %s\n", describeException(exception).c_str());
	});

	return run(options);
}