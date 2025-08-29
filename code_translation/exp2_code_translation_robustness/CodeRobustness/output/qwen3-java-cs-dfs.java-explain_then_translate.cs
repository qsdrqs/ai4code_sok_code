void LittleEndianOutput(DataOutput out, boolean field_1_vcenter) {     out.writeShort(field_1_vcenter ? 1 : 0); }
if (size.src == 0) return; if (tailBlkIdx.src != 0) this.addAll(tailBlock.src, 0, BLOCK_SIZE); for (int srcDirIdx = 0; srcDirIdx < tailDirIdx.src; srcDirIdx++) this.addAll(directory.src[srcDirIdx], 0, BLOCK_SIZE);
void Method(byte b) { if (currentBlock == null) { currentBlock = new byte[blockSize]; upto = 0; AddBlock(currentBlock); } if (upto == blockSize) { currentBlock = new byte[blockSize]; upto = 0; AddBlock(currentBlock); } currentBlock[upto++] = b; }
public ObjectId GetObjectId() => objectId;
public DeleteDomainEntryResult DeleteDomainEntry(DeleteDomainEntryRequest request) { request = BeforeClientExecution(request); return ExecuteDeleteDomainEntry(request); }
return (termOffsets != null ? ramBytesUsed.termOffsets() : 0) + (termsDictOffsets != null ? ramBytesUsed.termsDictOffsets() : 0);
public string MethodName() { raw = buffer; int msgB = RawParseUtils.TagMessage(raw, 0); if (msgB < 0) return ""; return RawParseUtils.Decode(RawParseUtils.GuessEncoding(raw), raw, msgB, length); }
_property_table.setStartBlock(0); POIFSConstants.setNextBlock(FAT_SECTOR_BLOCK, 1); POIFSConstants.setNextBlock(END_OF_CHAIN, 0); _bat_blocks.add(bb); bb.setOurBlockIndex(1); BATBlock bb = BATBlock.createEmptyBATBlock(false, bigBlockSize); _header.setBATArray(new BATBlock[] { bb }); _header.setBATCount(1); new POIFSFileSystem();
public unsafe void AddressMethod(int address) { int slice = (address & ByteBlockPool.BYTE_BLOCK_MASK) >> ByteBlockPool.BYTE_BLOCK_SHIFT; Debug.Assert(ByteBlockPool.buffers[slice] != null); int offset = address & ByteBlockPool.BYTE_BLOCK_MASK; }
void SubmoduleAddCommand(String path) {     this.path = path;     return; }
public ListIngestionsResult ListIngestionsRequest(ListIngestionsRequest request) { request = BeforeClientExecution(request); return ExecuteListIngestions(request); }
public QueryParserTokenManager(CharStream stream, int lexState) {     SwitchTo(lexState); }
public GetShardIteratorResult ExecuteGetShardIterator(GetShardIteratorRequest request) { request = BeforeClientExecution(request); return request.Execute(); }
new ModifyStrategyRequest { Method = MethodType.POST, Domain = "vipaegis", Action = "ModifyStrategy", Version = "2016-11-11", Service = "aegis" };
public bool HasRemaining() { try { lock (@lock) { if (in == null) throw new IOException("InputStreamReader is closed"); return in.Available > 0 || bytes.HasRemaining(); } } catch (IOException) { return false; } }
return new EscherOptRecord { _optRecord = ... };
public int Read(char[] buffer, int offset, int length) { if (buffer == null) throw new ArgumentNullException("buffer"); if (length == 0) return 0; lock (this) { if (offset < 0 || length < 0 || offset + length > buffer.Length) throw new ArgumentOutOfRangeException(); int copylen = Math.Min(length, count - pos); for (int i = 0; i < copylen; i++) buffer[offset + i] = this.buffer[pos + i]; pos += copylen; return copylen; } }
var sentenceOp = new NLPSentenceDetectorOp(OpenNLPSentenceBreakIterator);
void Write(string str) { if (str != null) { str.ToString(); } }
public class NotImplementedFunctionException : NotImplementedException { public NotImplementedFunctionException(string cause, string functionName) : base(cause) { } }
public override V NextEntry() => base.NextEntry().Value;
public void ReadInternal(byte[] b, int offset, int len, bool useBuffer) { bufferLength = 0; bufferPosition = 0; int after = bufferStart; ReadInternal(b, offset, len); if (length > after) throw new EOFException(this + "read past EOF: "); else { len = bufferPosition; after = bufferStart + bufferPosition + len; } if (len < bufferLength) { Buffer.BlockCopy(buffer, 0, b, offset, bufferLength); throw new EOFException(this + "read past EOF: "); } else { Refill(); } available += bufferPosition; available -= len; available += offset; if (available > 0) { Buffer.BlockCopy(buffer, bufferPosition, b, offset, available); } else { len += bufferPosition; Buffer.BlockCopy(buffer, bufferPosition, b, offset, len); } if (available <= len) { available = bufferLength - bufferPosition; } }
public TagQueueResult TagQueueRequest(RequestType request) { request = BeforeClientExecution(request); return ExecuteTagQueue(request); }
void MyMethod() { throw new NotSupportedException(); }
private CacheSubnetGroup CacheSubnetGroup(ModifyCacheSubnetGroupRequest request) => executeModifyCacheSubnetGroup(beforeClientExecution(request));
public void setParams(string @params) { base.setParams(@params); string language = ""; string country = ""; string variant = ""; string[] tokens = @params.Split(new char[] { ',' }, StringSplitOptions.RemoveEmptyEntries); if (tokens.Length >= 1) variant = tokens[0]; if (tokens.Length >= 2) country = tokens[1]; if (tokens.Length >= 3) language = tokens[2]; }
public DeleteDocumentationVersionResult DeleteDocumentationVersionRequest(AmazonWebServiceRequest request) { request = BeforeClientExecution(request); return ExecuteDeleteDocumentationVersion(request); }
public override bool Equals(object obj) { if (!(obj is FacetLabel other)) return false; if (components.Length != other.components.Length) return false; for (int i = components.Length - 1; i >= 0; i--) if (!components[i].Equals(other.components[i])) return false; return true; }
public GetInstanceAccessDetailsResult ExecuteGetInstanceAccessDetails(GetInstanceAccessDetailsRequest request) => BeforeClientExecution(request);
public HSSFPolygon HSSFPolygon(HSSFChildAnchor anchor) { HSSFPolygon shape = new HSSFPolygon(this, anchor); shape.SetParent(this); shape.SetAnchor(anchor); shapes.Add(shape); shape.OnCreate(); return shape; }
public string GetSheetname(int sheetIndex) { return GetBoundSheetRec(sheetIndex).GetSheetname(); }
private GetDashboardResult GetDashboardRequest(Request request) { request = beforeClientExecution(request); return executeGetDashboard(request); }
public AssociateSigninDelegateGroupsWithAccountResult AssociateSigninDelegateGroupsWithAccount(AssociateSigninDelegateGroupsWithAccountRequest request) { BeforeClientExecution(request); return Execute(request); }
for (int j = 0; j < mbr.NumColumns; j++) { BlankRecord br = new BlankRecord(); br.Column = mbr.FirstColumn + j; br.Row = mbr.Row; br.XFIndex = mbr.GetXFAt(j); insertCell(br); }
public static string EscapeString(string string){StringBuilder sb=new StringBuilder();sb.Append("\\Q");int apos=0,k;while((k=string.IndexOf("\\E",apos,StringComparison.Ordinal))>=0){sb.Append(string.Substring(apos,k-apos)).Append("\\E\\Q");apos=k+2;}sb.Append(string.Substring(apos)).Append("\\E");return sb.ToString();}
public ByteBuffer put(byte value) { throw new ReadOnlyBufferException(); }
public class ArrayPtg { private int _reserved0Int; private short _reserved1Short; private byte _reserved2Byte; private object[] _arrayValues; public ArrayPtg(object[][] values2d) { _reserved0Int = 0; _reserved1Short = 0; _reserved2Byte = 0; int nRows = values2d.Length; int nColumns = values2d[0].Length; object[] vv = new object[nRows * nColumns]; for (int r = 0; r < nRows; r++) { object[] rowData = values2d[r]; for (int c = 0; c < nColumns; c++) { vv[getValueIndex(r, c)] = rowData[c]; } } _arrayValues = vv; } private int getValueIndex(int r, int c) { return r * nColumns + c; } }
public GetIceServerConfigResult GetIceServerConfigRequest(GetIceServerConfigRequest request) { beforeClientExecution = request; return executeGetIceServerConfig(request); }
public String toString() {     return getClass() + " [" + getName() + "]" + getValueAsString(); }
public string ToString(string field) => "ToChildBlockJoinQuery (" + parentQuery.ToString() + ")";
public final void incrementAndGet() {     refCount++; }
public UpdateConfigurationSetSendingEnabledResult UpdateConfigurationSetSendingEnabled(UpdateConfigurationSetSendingEnabledRequest request) { request = BeforeClientExecution(request); return ExecuteUpdateConfigurationSetSendingEnabled(request); }
public int GetXBATEntriesPerBlock() { return LittleEndianConsts.INT_SIZE; }
void mulShift(TenPower multiplicandTp, TenPower multiplierShiftTp) { if (pow10 < 0) mulShift(TenPower.GetInstance(Math.Abs(pow10)), divisorTp); else { TenPower tp = TenPower.GetInstance(pow10); } }
string ToString(){var b=new System.Text.StringBuilder();int l=this.length();b.Append(System.IO.Path.DirectorySeparatorChar);for(int i=0;i<l;i++){b.Append(getComponent(i));if(i<l-1)b.Append(System.IO.Path.DirectorySeparatorChar);}return b.ToString();}
public class InstanceProfileCredentialsProvider { private ECSMetadataServiceCredentialsFetcher fetcher; public InstanceProfileCredentialsProvider(ECSMetadataServiceCredentialsFetcher fetcher) { this.fetcher = fetcher; } public ECSMetadataServiceCredentialsFetcher SetRoleName(string roleName) { return this.fetcher.SetRoleName(roleName); } }
public void SomeMethod() { ProgressMonitor pm = progressMonitor; }
void ParseMethod() { if (!IsFirst()) { if (!IsEof()) ParseEntry(); ptr = 0; } }
public E SomeMethod() { if (iterator.previousIndex() >= start) return iterator.previous(); else throw new InvalidOperationException("No such element"); }
() -> { return this.newPrefix; }
for (int i = 0; i < mSize; i++) { if (mValues[i] == value) return i; } return -1;
public List<CharsRef> Process(char[] word, int length) { List<CharsRef> stems = Stem(word, length); if (stems.Count < 2) return stems; CharArraySet terms = new CharArraySet(ignoreCase, 8); List<CharsRef> deduped = new List<CharsRef>(); foreach (CharsRef s in stems) { if (!terms.Contains(s)) deduped.Add(s); } return deduped; }
public GetGatewayResponsesResult ExecuteGetGatewayResponses(GetGatewayResponsesRequest request) { request = BeforeClientExecution(request); return request; }
currentBlockIndex = pos >> blockBits; currentBlock = blocks[currentBlockIndex]; currentBlockUpto = pos & blockMask;
n => { int s = Math.Min(available(), Math.Max(0, n)); ptr += s; return s; }
public class BootstrapActionDetail {     public void setBootstrapActionConfig(BootstrapActionConfig bootstrapActionConfig) {     } }
public void Write(LittleEndianOutput out) { if (field_7_padding != null) out.WriteByte(field_7_padding.Value); if (field_5_hasMultibyte) StringUtil.PutCompressedUnicode(out, field_6_author); else StringUtil.PutUnicodeLE(out, field_6_author); out.WriteByte((byte)(field_5_hasMultibyte ? 0x01 : 0x00)); out.WriteShort((short)field_6_author.Length); out.WriteShort(field_4_shapeid); out.WriteShort(field_3_flags); out.WriteShort(field_2_col); out.WriteShort(field_1_row); }
public int LastIndexOf(string str, int count) => str.LastIndexOf(str, count);
private bool addLastImpl<T>(T obj) where T : class { return; }
public void unsetSection(string section, string subsection) { ConfigSnapshot src, res; do { src = state; res = unsetSection(section, subsection, src); } while (Interlocked.CompareExchange(ref state, res, src) != src); }
public final String getTagName() {     return tagName; }
public void AddSubRecord(SubRecord element, int index) { subrecords.Insert(index, element); }
bool Remove(object o) { lock (mutex) { return delegate.Remove(o); } }
return new DoubleMetaphoneFilter(input, maxCodeLength, inject);
public int GetInCoreLength() { return inCoreLength; }
void someMethod(boolean newValue) {     newValue = value; }
newSource = this; oldSource = this;
if (i <= count) throw new IndexOutOfRangeException(); return entries[i];
public class CreateRepoRequest : BaseRequest { public CreateRepoRequest() : base("PUT", "/repos", "CreateRepo", "2016-06-07", "cr") { } }
public boolean isDeltaBaseAsOffset() {     return deltaBaseAsOffset; }
if (modCount != expectedModCount) throw new InvalidOperationException(); if (lastLink == null) throw new InvalidOperationException(); previous.next = next; next.previous = previous; list.size--; list.modCount++; expectedModCount++; lastLink = null; pos--;
public MergeShardsResult ExecuteMergeShards(MergeShardsRequest request) => beforeClientExecution(request);
private AllocateHostedConnectionResult ExecuteAllocateHostedConnection(AllocateHostedConnectionRequest request) { request = BeforeClientExecution(request); return ExecuteAllocateHostedConnection(request); }
public int Method() { return start; }
public static WeightedTerm[] getTerms(Query query) { return new WeightedTerm[] { new WeightedTerm(false, query) }; }
public ByteBuffer AsReadOnlyBuffer() { throw new System.NotSupportedException(); }
private void Method(int iterations, int[] values, ref int valuesOffset, byte[] blocks, ref int blocksOffset) { for (int i = 0; i < iterations; i++) { int byte0 = blocks[blocksOffset++]; values[valuesOffset++] = byte0 >> 2; int byte1 = blocks[blocksOffset++]; values[valuesOffset++] = ((byte0 & 3) << 4) | (byte1 >> 4); int byte2 = blocks[blocksOffset++]; values[valuesOffset++] = ((byte1 & 0x0F) << 2) | (byte2 >> 6); values[valuesOffset++] = byte2 & 0x3F; } }
public string ProcessPath(string input) { if (input == null) throw new ArgumentException("Input cannot be null"); string result = string.IsNullOrEmpty(input) || input == "/" || input.StartsWith("file://") ? new Uri(input).Host : new Uri(input).PathAndQuery; if (string.IsNullOrEmpty(result) || result == "/") throw new ArgumentException("Invalid path"); string[] elements = Regex.Split(result, "/[\\\" + Path.DirectorySeparatorChar + "]"); if (elements == null || elements.Length == 0) throw new ArgumentException("Invalid path structure"); if (result.EndsWith(Constants.DOT_GIT)) elements = elements.Take(elements.Length - 1).ToArray(); if (elements.Length >= 2 && elements[elements.Length - 2] == Constants.DOT_GIT) result = result.Substring(0, result.Length - Constants.DOT_GIT_EXT.Length); return result; }
public DescribeNotebookInstanceLifecycleConfigResult DescribeNotebookInstanceLifecycleConfig(DescribeNotebookInstanceLifecycleConfigRequest request) => executeDescribeNotebookInstanceLifecycleConfig(beforeClientExecution(request));
public String getAccessKeySecret() {     return this.accessKeySecret; }
public CreateVpnConnectionResult CreateVpnConnection(CreateVpnConnectionRequest request) { request = BeforeClientExecution(request); return ExecuteCreateVpnConnection(request); }
public DescribeVoicesResult DescribeVoicesRequest(Request request) { request = BeforeClientExecution(request); return ExecuteDescribeVoices(request); }
public ListMonitoringExecutionsResult ExecuteListMonitoringExecutions(ListMonitoringExecutionsRequest request) { request = BeforeClientExecution(request); return request.ExecuteListMonitoringExecutions(); }
public class DescribeJobRequest { public DescribeJobRequest(string jobId, string vaultName) { SetJobId(jobId); SetVaultName(vaultName); } }
public EscherRecord Get(int index) { return escherRecords[index]; }
GetApisResult GetApisRequest(GetApisRequest request) => executeGetApis(beforeClientExecution(request));
public DeleteSmsChannelResult DeleteSmsChannel(DeleteSmsChannelRequest request) => executeDeleteSmsChannel(beforeClientExecution(request));
return new TrackingRefUpdate();
public static void Print(bool b) { Console.WriteLine(b.ToString()); }
public Node QueryNode() { return GetChildren()[0]; }
public class SomeClass { private int workdirTreeIndex; public SomeClass(NotIgnoredFilter filter) { workdirTreeIndex = index; } }
public AreaRecord(RecordInputStream in) { field_1_formatFlags = in.readShort(); }
public class GetThumbnailRequest : RpcRequest { public GetThumbnailRequest() : base(HTTPS.ProtocolType, "cloudphoto", "GetThumbnail", "2017-07-11", "CloudPhoto") {} }
public DescribeTransitGatewayVpcAttachmentsResult DescribeTransitGatewayVpcAttachmentsRequest(RequestType request) { beforeClientExecution = request; return executeDescribeTransitGatewayVpcAttachments(request); }
public PutVoiceConnectorStreamingConfigurationResult ExecutePutVoiceConnectorStreamingConfiguration(PutVoiceConnectorStreamingConfigurationRequest request) => BeforeClientExecution(request);
public string OrdRange(string dim) { return get.prefixToOrdRange(dim); }
} ; ) symbol , ) ( GetType . Name . LexerNoViableAltException , "{0}('{1}')" , ) ( CurrentCulture ( Format . string return } ; ) false , symbol ( Utils . EscapeWhitespace = symbol ; ) ) StartIndex , StartIndex ( Interval . New ( GetText . ) ( InputStream get = symbol { ) ) ( Count . ) ( InputStream get < StartIndex && 0 >= StartIndex ( if ; "" = symbol string { ) (  string
E PeekFirstImpl() { return peekFirstImpl; }
public CreateWorkspacesResult ExecuteCreateWorkspaces(CreateWorkspacesRequest request) { return beforeClientExecution(request); }
public class NumberFormatIndexRecord {     public NumberFormatIndexRecord copy() {         return new NumberFormatIndexRecord();     } }
public DescribeRepositoriesResult DescribeRepositories(DescribeRepositoriesRequest request) { return executeDescribeRepositories(beforeClientExecution(request)); }
public SparseIntArray(int initialCapacity) { initialCapacity = ArrayUtils.IdealIntArraySize(initialCapacity); mKeys = new int[initialCapacity]; mValues = new int[initialCapacity]; mSize = 0; }
public HyphenatedWordsFilter HyphenatedWordsFilter(TokenStream input) {     return new HyphenatedWordsFilter(input); }
public CreateDistributionWithTagsResult CreateDistributionWithTagsRequest(RequestType request) { request = beforeClientExecution(request); return executeCreateDistributionWithTags(request); }
new FileStream(fileName, mode == "r" ? FileMode.Open : FileMode.OpenOrCreate, mode == "r" ? FileAccess.Read : FileAccess.ReadWrite);
public DeleteWorkspaceImageResult DeleteWorkspaceImage(DeleteWorkspaceImageRequest request) { BeforeClientExecution(request); return ExecuteDeleteWorkspaceImage(request); }
public static string ToString(int value) { StringBuilder sb = new StringBuilder(16); WriteHex(value, 16, "", sb); return sb.ToString(); }
public UpdateDistributionResult UpdateDistributionRequest(RequestType request) => ExecuteUpdateDistribution(BeforeClientExecution(request));
public HSSFColor GetColor(int index, CustomColor b) { return index == HSSFColorPredefined.AUTOMATIC.Index ? HSSFColorPredefined.AUTOMATIC.Color : b == null ? new CustomColor(_palette[index]) : b.GetColor(index); }
public static ValueEval _functionName(int srcCol, int srcRow, ValueEval[] operands) {     throw new NotImplementedFunctionException(); }
public void SomeMethod(LittleEndianOutput @out) { @out.writeShort(field_1_number_crn_records); @out.writeShort(field_2_sheet_table_index); }
public DescribeDBEngineVersionsResult describeDBEngineVersions() {     return new DescribeDBEngineVersionsRequest(); }
public FormatRun(int _fontIndex, int _character) { this.fontIndex = _fontIndex; this.character = _character; }
public static byte[] Convert(char[] chars, int offset, int length) { byte[] result = new byte[length * 2]; int resultIndex = 0; int end = offset + length; for (int i = offset; i < end; i++) { char ch = chars[i]; result[resultIndex++] = (byte)((int)ch >> 8); result[resultIndex++] = (byte)ch; } return result; }
private UploadArchiveResult executeUploadArchive(UploadArchiveRequest request) {     beforeClientExecution(request);     return execute(request); }
public List<Token> GetHiddenTokensToLeft(int tokenIndex) { return GetHiddenTokensToLeft(tokenIndex - 1); }
public override bool Equals(object obj) { if (this == obj) return true; if (!base.Equals(obj)) return false; if (GetType() != obj.GetType()) return false; AutomatonQuery other = (AutomatonQuery)obj; if (!compiled.Equals(other.compiled)) return false; if (term == null) { if (other.term != null) return false; } else { if (!term.Equals(other.term)) return false; } return true; }
public SpanQuery SomeMethod() { SpanQuery[] spanQueries = new SpanQuery[weightBySpanQuery.Keys.Count]; int i = 0; foreach (var sq in weightBySpanQuery.Keys) { float boost = weightBySpanQuery[sq]; if (boost != 1f) sq = new SpanBoostQuery(sq, boost); spanQueries[i++] = sq; } return spanQueries.Length == 1 ? spanQueries[0] : new SpanOrQuery(spanQueries); }
public StashCreateCommand createStashCreateCommand(RepoType repo) {     return new StashCreateCommand(repo); }
public class FieldInfo{public FieldInfo(string fieldName){}public static FieldInfo ByName(string fieldName){return new FieldInfo(fieldName);}}
DescribeEventSourceResult executeDescribeEventSource(DescribeEventSourceRequest request) {     request = beforeClientExecution(request);     return executeDescribeEventSource(request); }
public GetDocumentAnalysisResult ExecuteGetDocumentAnalysis(GetDocumentAnalysisRequest request) { request = BeforeClientExecution(request); return ExecuteGetDocumentAnalysis(request); }
public CancelUpdateStackResult CancelUpdateStackRequest(Request request) { beforeClientExecution = request; return executeCancelUpdateStack(request); }
public ModifyLoadBalancerAttributesResult ExecuteModifyLoadBalancerAttributes(ModifyLoadBalancerAttributesRequest request) { BeforeClientExecution(request); return request; }
SetInstanceProtectionResult ExecuteSetInstanceProtection(SetInstanceProtectionRequest request) { beforeClientExecution = request; return request; }
public ModifyDBProxyResult ExecuteModifyDBProxy(ModifyDBProxyRequest request) { return beforeClientExecution(request); }
count++; posLength = posLengths[count]; endOffset = endOffsets[count]; copyChars(output, offset, len); if (outputs[count] == null) { outputs[count] = new CharsRefBuilder(); } if (posLengths.Length == count) { int[] next = new int[ArrayUtil.Oversize(posLengths.Length, count + 1)]; Array.Copy(posLengths, next, posLengths.Length); posLengths = next; } if (endOffsets.Length == count) { int[] next = new int[ArrayUtil.Oversize(endOffsets.Length, count + 1)]; Array.Copy(endOffsets, next, endOffsets.Length); endOffsets = next; } if (outputs.Length == count) { outputs = ArrayUtil.Grow(outputs, count + 1); }
; ) HTTPS . ProtocolType (  ; ) "cloudphoto" , "FetchLibraries" , "2017-07-11" , "CloudPhoto" ( base { ) ( FetchLibrariesRequest
public bool exists(object objects) { return fs.exists(objects); }
public class MyStream : Stream { private Stream out; public MyStream(Stream out) { this.out = out; } }
base.PUT("/clusters/[ClusterId]", "csk", "ScaleCluster", "2015-12-15", "CS");
public DVConstraint DataValidationConstraint(string operatorType, string formula1, string formula2) => createTimeConstraint(typeof(DVConstraint), operatorType, formula1, formula2);
public ListObjectParentPathsResult ExecuteListObjectParentPathsRequest(ListObjectParentPathsRequest request) { BeforeClientExecution(request); return ExecuteListObjectParentPaths(request); }
protected DescribeCacheSubnetGroupsResult ExecuteDescribeCacheSubnetGroups(DescribeCacheSubnetGroupsRequest request) => BeforeClientExecution(request);
} ; ) flag , field_5_options ( setShortBoolean . sharedFormula = field_5_options { ) flag boolean ( void
public boolean reuseObjects() {     return reuseObjects; }
return (ErrorNodeImpl)AddAnyChild(new ErrorNodeImpl(badToken)).SetParent(this);
if (args.Count > 0) throw new ArgumentException("Unknown parameters: " + args);
public EventSubscription RemoveSourceIdentifierFromSubscriptionRequest(Request request) => ExecuteRemoveSourceIdentifierFromSubscription(BeforeClientExecution(request));
public static TokenFilterFactory newInstance(string name, Dictionary<string, string> args) => loader;
public class AddAlbumPhotosRequest : BaseClass { public AddAlbumPhotosRequest() : base(ProtocolType.HTTPS, "cloudphoto", "AddAlbumPhotos", "2017-07-11", "CloudPhoto") {} }
public GetThreatIntelSetResult executeGetThreatIntelSet(GetThreatIntelSetRequest request) { return beforeClientExecution(request); }
public class RevFilter { public Binary RevFilter() { return new Binary(a.Clone(), b.Clone()); } }
return new ArmenianStemmer() is object;
public bool hasArray() { return protectedHasArray; }
public UpdateContributorInsightsResult UpdateContributorInsightsRequest(UpdateContributorInsightsRequest request) { beforeClientExecution = request; return executeUpdateContributorInsights(request); }
void someMethod() {     remove.records(writeProtect);     remove.records(fileShare);     writeProtect = null;     fileShare = null; }
public SolrSynonymParser(Analyzer analyzer, bool expand, bool dedup) : base(analyzer, expand, dedup) { this.expand = expand; }
public RequestSpotInstancesResult ExecuteRequestSpotInstances(RequestSpotInstancesRequest request) { request = BeforeClientExecution(request); return request.Execute(); }
return findObjectRecord().getObjectData();
public GetContactAttributesResult ExecuteGetContactAttributes(GetContactAttributesRequest request) { BeforeClientExecution(request); return request; }
public String toString() {     return getKey() + ": " + getValue(); }
public ListTextTranslationJobsResult ExecuteListTextTranslationJobs(ListTextTranslationJobsRequest request) { request = BeforeClientExecution(request); return ExecuteListTextTranslationJobs(request); }
public GetContactMethodsResult GetContactMethodsRequest(GetContactMethodsRequest request) { request = beforeClientExecution(request); return executeGetContactMethods(request); }
public static FunctionMetadata GetFunctionByNameInternal(string name) { FunctionMetadata fd = GetInstance().GetFunctionByNameInternal(name); if (fd == null) { fd = GetInstanceCetab().GetFunctionByNameInternal(name); if (fd == null) return null; } return fd; }
return ExecuteDescribeAnomalyDetectors(beforeClientExecution(request));
public static string InsertId(ObjectId changeId, string message) { return (message, changeId, false).ToString(); }
public int GetObjectSize(AnyObjectId objectId, int typeHint) { int sz = db.GetObjectSize(objectId, typeHint); if (sz < 0) throw new MissingObjectException(JGitText.Get().Copy(objectId), unknownObjectType2); if (typeHint == OBJ_ANY) { sz = db.GetObjectSize(objectId, typeHint); if (sz < 0) throw new MissingObjectException(JGitText.Get().Copy(objectId), unknownObjectType2); } return sz; }
public ImportInstallationMediaResult ImportInstallationMediaRequest(ImportInstallationMediaRequest request) => executeImportInstallationMedia(beforeClientExecution(request));
public PutLifecycleEventHookExecutionStatusResult executePutLifecycleEventHookExecutionStatus(PutLifecycleEventHookExecutionStatusRequest request) { return beforeClientExecution(request); }
public class NumberPtg { public NumberPtg(BinaryReader @in) { double d = @in.ReadDouble(); } }
public GetFieldLevelEncryptionConfigResult ExecuteGetFieldLevelEncryptionConfig(GetFieldLevelEncryptionConfigRequest request) { BeforeClientExecution(request); return ExecuteRequest(request); }
public DescribeDetectorResult DescribeDetector(DescribeDetectorRequest request) { request = beforeClientExecution(request); return executeDescribeDetector(request); }
public ReportInstanceStatusResult ExecuteReportInstanceStatus(ReportInstanceStatusRequest request) => BeforeClientExecution(request);
public DeleteAlarmResult ExecuteDeleteAlarm(DeleteAlarmRequest request) => beforeClientExecution(request);
public TokenStream CreateFilter(TokenStream input) => new PortugueseStemFilter(input);
FtCblsSubRecord[] array = new FtCblsSubRecord[ENCODED_SIZE]; array[ENCODED_SIZE] = new FtCblsSubRecord();
public override bool Object() { lock(mutex) { return c.Remove(object); } }
public GetDedicatedIpResult executeGetDedicatedIp(GetDedicatedIpRequest request) { request = (GetDedicatedIpRequest)request; beforeClientExecution(request); return executeGetDedicatedIp(request); }
() -> { return " >= _p" + precedence; }
public ListStreamProcessorsResult ListStreamProcessorsRequest(Request request) { request = BeforeClientExecution(request); return ExecuteListStreamProcessors(request); }
public class DeleteLoadBalancerPolicyRequest{public DeleteLoadBalancerPolicyRequest(string policyName,string loadBalancerName){SetPolicyName(policyName);SetLoadBalancerName(loadBalancerName);}}
WindowProtectRecord record = new WindowProtectRecord(options) {     {         options = _options;     } };
public class UnbufferedCharStream {     private char[] data;     private int n;      public UnbufferedCharStream(int bufferSize) {         data = new char[bufferSize];         n = 0;     } }
public GetOperationsResult ExecuteGetOperations(GetOperationsRequest request) { BeforeClientExecution(request); return request; }
public static void EncodeInt32_NB(byte[] b, int o, int w1, int w2, int w3, int w4, int w5) { byte[] t; t=BitConverter.GetBytes(w1);if(BitConverter.IsLittleEndian)Array.Reverse(t);Buffer.BlockCopy(t,0,b,o,4);t=BitConverter.GetBytes(w2);if(BitConverter.IsLittleEndian)Array.Reverse(t);Buffer.BlockCopy(t,0,b,o+4,4);t=BitConverter.GetBytes(w3);if(BitConverter.IsLittleEndian)Array.Reverse(t);Buffer.BlockCopy(t,0,b,o+8,4);t=BitConverter.GetBytes(w4);if(BitConverter.IsLittleEndian)Array.Reverse(t);Buffer.BlockCopy(t,0,b,o+12,4);t=BitConverter.GetBytes(w5);if(BitConverter.IsLittleEndian)Array.Reverse(t);Buffer.BlockCopy(t,0,b,o+16,4); }
public class WindowOneRecord { private short field_9_tab_width_ratio; private short field_8_num_selected_tabs; private short field_7_first_visible_tab; private short field_6_active_sheet; private short field_5_options; private short field_4_height; private short field_3_width; private short field_2_v_hold; private short field_1_h_hold; public WindowOneRecord(BinaryReader reader) { field_9_tab_width_ratio = reader.ReadInt16(); field_8_num_selected_tabs = reader.ReadInt16(); field_7_first_visible_tab = reader.ReadInt16(); field_6_active_sheet = reader.ReadInt16(); field_5_options = reader.ReadInt16(); field_4_height = reader.ReadInt16(); field_3_width = reader.ReadInt16(); field_2_v_hold = reader.ReadInt16(); field_1_h_hold = reader.ReadInt16(); } }
public StopWorkspacesResult ExecuteStopWorkspaces(StopWorkspacesRequest request) { BeforeClientExecution(request); return ExecuteStopWorkspaces(request); }
void Method() { if (!isOpen) throw new IOException(); using (var fs = new FileStream("file", FileMode.Truncate)) { fs.SetLength(fileLength); dump(); } }
public DescribeMatchmakingRuleSetsResult ExecuteDescribeMatchmakingRuleSets(DescribeMatchmakingRuleSetsRequest request) { beforeClientExecution = request; return request; }
string Method(int len, int off, char[] surface, int wordId){return null;}
public string GetPathStr() => pathStr;
public static double SomeMethod(double[] v){double r=Double.NaN;if(v!=null&&v.Length>=1){int n=v.Length;if(n==1)r=0;else{double s=0;for(int i=0;i<n;s+=v[i++]);double m=s/n;s=0;for(int i=0;i<n;s+=(v[i]-m)*(v[i++]-m));r=Math.Sqrt(s/(n-1));}}return r;}
public DescribeResizeResult ExecuteDescribeResize(DescribeResizeRequest request) => request.BeforeClientExecution();
public final boolean passedThroughNonGreedyDecision() {     return something; }
} ; ) 0 ( end return { ) (
public void WalkCells(CellHandler handler) { var ctx = new SimpleCellWalkContext(); int firstRow = range.GetFirstRow(), lastRow = range.GetLastRow(), firstColumn = range.GetFirstColumn(), lastColumn = range.GetLastColumn(), width = lastColumn - firstColumn + 1; for (ctx.RowNumber = firstRow; ctx.RowNumber <= lastRow; ctx.RowNumber++) { IRow currentRow = ctx.Sheet.GetRow(ctx.RowNumber); if (currentRow == null) continue; for (ctx.ColNumber = firstColumn; ctx.ColNumber <= lastColumn; ctx.ColNumber++) { ICell currentCell = currentRow.GetCell(ctx.ColNumber); if (currentCell == null) continue; if (!TraverseEmptyCells && currentCell.IsEmpty()) continue; int rowSize = ArithmeticUtils.MulAndCheck(width, ArithmeticUtils.SubAndCheck(ctx.RowNumber, firstRow)); int ordinalNumber = ArithmeticUtils.AddAndCheck(rowSize, ArithmeticUtils.SubAndCheck(ctx.ColNumber, firstColumn)); ctx.OrdinalNumber = ordinalNumber; handler(ctx, currentCell); } } }
public Position getPosition() {     return pos; }
public int CompareTo(ScoreTerm other) { if (this.boost == other.boost) return this.bytes.CompareTo(other.bytes); else return this.boost.CompareTo(other.boost); }
for (int i = 0; i < len; ++i) { switch (s[i]) { case HAMZA_ABOVE: len = delete(len, i, s); return len; case HEH_GOAL: case HEH_YEH: break; case KAF: case KEHEH: break; case YEH_BARREE: case FARSI_YEH: break; } }
public void WriteShort(LittleEndianOutput @out) { }
public DiagnosticErrorListener(bool exactOnly) { this.exactOnly = exactOnly; }
public class KeySchemaElement { private KeyType keyType; private string attributeName; public KeySchemaElement(KeyType keyType, string attributeName) { SetKeyType(keyType); SetAttributeName(attributeName); } public override string ToString() { return "KeySchemaElement{keyType=" + keyType + ", attributeName='" + attributeName + "'}"; } private void SetKeyType(KeyType keyType) { this.keyType = keyType; } private void SetAttributeName(string attributeName) { this.attributeName = attributeName; } }
public GetAssignmentResult GetAssignmentRequest(GetAssignmentRequest request) { request = beforeClientExecution(request); return executeGetAssignment(request); }
public bool findOffset(AnyObjectId id) { return 1 - id != default(int); }
public bool GroupingSearch(object allGroups) { this.allGroups = allGroups; return true; }
public void ConfigureField(string dimName, bool v) { lock (this) { DimConfig ft = fieldTypes.ContainsKey(dimName) ? fieldTypes[dimName] : null; if (ft == null) { ft = new DimConfig(); fieldTypes[dimName] = ft; } multiValued.ft = v; } }
int size = 0; foreach (Character c in cells.Keys) { Cell cmd = cells[c]; if (cmd.e >= 0) size++; } return size;
public DeleteVoiceConnectorResult DeleteVoiceConnectorRequest(DeleteVoiceConnectorRequest request) { beforeClientExecution(request); return executeDeleteVoiceConnector(request); }
public DeleteLifecyclePolicyResult DeleteLifecyclePolicy(DeleteLifecyclePolicyRequest request) { request = BeforeClientExecution(request); return ExecuteDeleteLifecyclePolicy(request); }
