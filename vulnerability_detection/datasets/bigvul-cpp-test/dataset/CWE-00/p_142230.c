void FileManagerBrowserTestBase::StartTest() {
  LOG(INFO) << "FileManagerBrowserTest::StartTest " << GetFullTestCaseName();
  static const base::FilePath test_extension_dir =
      base::FilePath(FILE_PATH_LITERAL("ui/file_manager/integration_tests"));
  LaunchExtension(test_extension_dir, GetTestExtensionManifestName());
  RunTestMessageLoop();
}
