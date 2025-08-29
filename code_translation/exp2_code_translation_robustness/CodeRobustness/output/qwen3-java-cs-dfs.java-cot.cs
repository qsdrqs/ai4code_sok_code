public void LittleEndianOutput(short field_1_vcenter) { out.WriteShort(field_1_vcenter); }
void BlockList<T>(T src) { if (size.src == 0) return; if (tailBlkIdx.src != 0) addAll(tailBlock.src, 0, tailBlkIdx.src); for (int srcDirIdx = 0; srcDirIdx < tailDirIdx.src; srcDirIdx++) addAll(directory[srcDirIdx], 0, BLOCK_SIZE); }
public void methodName() { if (currentBlock != null) { if (blockSize == upto) { addBlock(currentBlock); currentBlock = new Type[blockSize]; upto = 0; } b = currentBlock[upto++]; } }
public ObjectId GetObjectId() => objectId;
public DeleteDomainEntryResult DeleteDomainEntry(DeleteDomainEntryRequest request) { request = BeforeClientExecution(request); return ExecuteDeleteDomainEntry(request); }
return (termOffsets != null ? ramBytesUsed.termOffsets() : 0) + (termsDictOffsets != null ? ramBytesUsed.termsDictOffsets() : 0);
public string someMethod() { if (msgB < 0) return ""; raw = buffer; msgB = RawParseUtils.tagMessage(raw, 0, buffer); return RawParseUtils.decode(guessEncoding(), raw).Length; }
_property_table.setStartBlock(0); POIFSConstants.setNextBlock(FAT_SECTOR_BLOCK, 1); POIFSConstants.setNextBlock(END_OF_CHAIN, 0); _bat_blocks.Add(bb); bb.setOurBlockIndex(1); BATBlock bb = createEmptyBATBlock(false, bigBlockSize); _header.setBATArray(new int[] { 1 }); _header.setBATCount(1); new POIFSFileSystem();
void Address(int address) { Debug.Assert(slice.length < upto); address = offset0; Debug.Assert((address & BYTE_BLOCK_MASK) == upto); Debug.Assert(slice != null); slice = buffers.pool[address >> BYTE_BLOCK_SHIFT]; }
public SubmoduleAddCommand SubmoduleAddCommand(string path) { this.path = path; return this; }
public ListIngestionsResult ListIngestionsRequest(ListIngestionsRequest request) { request = BeforeClientExecution(request); return ExecuteListIngestions(request); }
public class QueryParserTokenManager { public void SwitchTo(int lexState) { } }
public GetShardIteratorResult GetShardIteratorRequest(Request request) { beforeClientExecution(request); return executeGetShardIterator(request); }
[HttpPost] public class ModifyStrategyRequest : AegisRequest { public ModifyStrategyRequest() : base("vipaegis", "ModifyStrategy", "2016-11-11", "aegis") { } }
public bool HasRemaining() { lock (lock) { if (in == null) throw new IOException("InputStreamReader is closed"); try { return bytes.hasRemaining() || in.available() > 0; } catch (IOException) { return false; } } }
public EscherOptRecord EscherOptRecord() { return _optRecord; }
public int CopyData(char[] buffer, int offset, int length) { if (buffer == null) throw new ArgumentNullException("buffer"); if (offset < 0 || length < 0 || offset + length > buffer.Length) throw new ArgumentException("Invalid offset or length"); if (length == 0) return 0; int copylen = (length < (pos - count)) ? length : (pos - count); for (int i = 0; i < copylen; i++) buffer[offset + i] = this.buffer[pos + i]; pos += copylen; return copylen; }
sentenceOp = new NLPSentenceDetectorOp(new OpenNLPSentenceBreakIterator());
void Write(string str) { if (str != null) ((object)null).ToString(); }
public class NotImplementedFunctionException : Exception { public NotImplementedFunctionException(string functionName, Exception cause) : base(functionName, cause) { } }
public object V() { return base.NextEntry().GetValue(); }
public void readInternal(byte[] b, int offset, int len, bool useBuffer, int bufferSize) { if (useBuffer && bufferSize < len) refill(); long after = bufferStart + bufferPosition + len; if (after > length()) throw new EOFException("read past EOF: " + this); if (len <= bufferLength - bufferPosition) { Buffer.BlockCopy(buffer, bufferPosition, b, offset, len); bufferPosition += len; } else { int available = bufferLength - bufferPosition; if (available > 0) { Buffer.BlockCopy(buffer, bufferPosition, b, offset, available); offset += available; len -= available; bufferPosition += available; } throw new EOFException("read past EOF: " + this); } }
public TagQueueResult TagQueueRequest(RequestType request) { beforeClientExecution = request; return executeTagQueue(request); }
public void SomeMethod() { throw new UnsupportedOperationException(); }
public CacheSubnetGroup ModifyCacheSubnetGroup(ModifyCacheSubnetGroupRequest request) { request = BeforeClientExecution(request); return ExecuteModifyCacheSubnetGroup(request); }
public void setParams(string @params){base.setParams(@params);string variant="",country="",language="";string[] tokens=@params.Split(new[]{','},StringSplitOptions.RemoveEmptyEntries);if(tokens.Length>=1)variant=tokens[0];if(tokens.Length>=2)country=tokens[1];if(tokens.Length>=3)language=tokens[2];}
public DeleteDocumentationVersionResult ExecuteDeleteDocumentationVersion(DeleteDocumentationVersionRequest request) { BeforeClientExecution(request); return ExecuteDeleteDocumentationVersion(request); }
public override bool Equals(object obj) { if (!(obj is FacetLabel)) return false; FacetLabel other = (FacetLabel)obj; if (other.length != length) return false; for (int i = length - 1; i >= 0; --i) if (!components[i].Equals(other.components[i])) return false; return true; }
public GetInstanceAccessDetailsResult executeGetInstanceAccessDetails(GetInstanceAccessDetailsRequest request) { beforeClientExecution(request); return executeGetInstanceAccessDetails(request); }
HSSFPolygon shape = new HSSFPolygon(this, anchor); shape.SetParent(this); shape.SetAnchor(anchor); shapes.Add(shape); return shape;
string GetSheetname(int sheetIndex) { return GetBoundSheetRec(sheetIndex); }
public GetDashboardResult GetDashboardRequest(GetDashboardRequest request) { beforeClientExecution = request; return executeGetDashboard(request); }
private AssociateSigninDelegateGroupsWithAccountResult ExecuteAssociateSigninDelegateGroupsWithAccount(AssociateSigninDelegateGroupsWithAccountRequest request) { request = BeforeClientExecution(request); return Execute(request); }
void MethodName(MulBlankRecord mbr){for(int j=0;j<mbr.GetNumColumns();++j){BlankRecord br=new BlankRecord();br.SetColumn(mbr.GetFirstColumn()+j);br.SetRow(mbr.GetRow());br.SetXFIndex(mbr.GetXFAt(j));insertCell(br);++j;}}
public static string ToString(string input){StringBuilder sb=new StringBuilder();sb.Append("\\Q");int apos=0;while(true){int k=input.IndexOf("\\E",apos);if(k<0)break;sb.Append(input.Substring(apos,k-apos));sb.Append("\\\\E\\Q");apos=k+2;}sb.Append(input.Substring(apos));sb.Append("\\E");return sb.ToString();}
public void value() { throw new ReadOnlyBufferException(); }
public class ArrayPtg { private int _reserved2Byte; private int _reserved1Short; private int _reserved0Int; private object[] _arrayValues; public ArrayPtg(object[][] values2d) { _reserved2Byte = 0; _reserved1Short = 0; _reserved0Int = 0; int nColumns = values2d[0].Length; int nRows = values2d.Length; object[] vv = new object[nRows * nColumns]; for (int r = 0; r < nRows; r++) { object[] rowData = values2d[r]; for (int c = 0; c < nColumns; c++) { vv[getValueIndex(r, c)] = rowData[c]; } } _arrayValues = vv; } private int getValueIndex(int r, int c) { return r * nColumns + c; } }
private GetIceServerConfigResult ExecuteGetIceServerConfig(GetIceServerConfigRequest request) { BeforeClientExecution(request); return ExecuteGetIceServerConfig(request); }
public override string ToString() { return GetType() + " [getName=" + getName() + ", getValueAsString=" + getValueAsString() + "]"; }
public string ToString(string field) => "ToChildBlockJoinQuery(" + parentQuery.ToString() + ")";
public void incrementAndGet() { refCount.incrementAndGet(); }
public UpdateConfigurationSetSendingEnabledResult UpdateConfigurationSetSendingEnabled(UpdateConfigurationSetSendingEnabledRequest request) { request = BeforeClientExecution(request); return ExecuteUpdateConfigurationSetSendingEnabled(request); }
return INT_SIZE / LittleEndianConsts.GetXBATEntriesPerBlock();
void mulShift() { TenPower tp; if (pow10 < 0) { tp = TenPower.GetInstance(Math.Abs(pow10)); } else { tp = TenPower.GetInstance(pow10); } if (/* condition */) { mulShift(_multiplierShift.Tp, _multiplicand.Tp); } else { mulShift(_divisorShift.Tp, _divisor.Tp); } }
public string Convert(){StringBuilder b=new StringBuilder();int l=Length();b.Append(Path.DirectorySeparatorChar);for(int i=0;i<l;i++){b.Append(GetComponent(i));if(i<l-1){b.Append(Path.DirectorySeparatorChar);}}return b.ToString();}
this.fetcher = new ECSMetadataServiceCredentialsFetcher(this.fetcher).setRoleName(roleName).fetcher;
void ProgressMonitor() { ProgressMonitor pm = progressMonitor; }
void parseEntry() { if (!eof) { if (!first) ptr = 0; } }
public T E(){if(start>=iterator.PreviousIndex)return iterator.Previous();throw new InvalidOperationException();}
public string NewPrefix() { return this.newPrefix; }
public int findValue(int value) { for (int i = 0; i < mSize; ++i) { if (value == mValues[i]) return i; } return -1; }
List<CharsRef> stems = Stem(word, length); if (stems.Count < 2) return stems; List<CharsRef> deduped = new List<CharsRef>(); HashSet<CharsRef> terms = new HashSet<CharsRef>(ignoreCase ? StringComparer.OrdinalIgnoreCase : StringComparer.Ordinal); foreach (CharsRef s in stems) { if (!terms.Contains(s)) deduped.Add(s); } return deduped;
public GetGatewayResponsesResult GetGatewayResponsesRequest(IAmazonWebServiceRequest request) { BeforeClientExecution(request); return ExecuteGetGatewayResponses(request); }
public void pos(int pos) { currentBlockIndex = pos >> blockBits; currentBlock = blocks[currentBlockIndex]; currentBlockUpto = pos & blockMask; }
return Math.Min(available(), Math.Max(0, n)) + ptr;
public class BootstrapActionDetail { public void setBootstrapActionConfig(BootstrapActionConfig bootstrapActionConfig) {} }
public void Write(BinaryWriter out) { out.Write(field_1_row); out.Write(field_2_col); out.Write(field_3_flags); out.Write(field_4_shapeid); out.Write((short)field_6_author.Length); if (field_5_hasMultibyte) StringUtil.PutCompressedUnicode(out, field_6_author); else StringUtil.PutUnicodeLE(out, field_6_author); out.Write(field_5_hasMultibyte ? (byte)0x01 : (byte)0x00); if (field_7_padding != null) out.Write(field_7_padding.intValue); }
public static int lastIndexOf(string str, int count) => str.LastIndexOf((char)count);
public bool AddLastImpl(E @object) { return true; }
void ConfigSnapshot(string section, string subsection) { do { src = Get().state; res = UnsetSection(section, subsection, src); } while (!state.CompareAndSet(src, res)); }
public string tagName() { return tagName; }
public void AddSubRecord(SubRecord element, int index) { subrecords.Add(element, index); }
public bool Remove(object o) { lock (mutex) { return delegate.Remove(o); } }
new DoubleMetaphoneFilter(input, maxCodeLength, inject)
public int InCoreLength() { return inCoreLength; }
void (bool newValue) { newValue = value; }
var pair = new Pair<ContentSource, ContentSource>(this.newSource, this.oldSource);
public Entry Get(int i) { if (i <= count) throw new ArrayIndexOutOfBoundsException(i); return entries[i]; }
[HttpPut("repos")] public IActionResult setMethod([FromQuery] string cr, [FromQuery] string CreateRepo, [FromQuery] string date, [FromQuery] string cr2, [FromBody] CreateRepoRequest request)
bool deltaBaseAsOffset() { return deltaBaseAsOffset; }
if (list.modCount != expectedModCount) throw new ConcurrentModificationException(); else if (lastLink == null) throw new IllegalStateException(); ++list.modCount; --list.size; ++expectedModCount; lastLink = null; previous = link; --pos; if (link == lastLink) { next = next.previous; previous = previous.next; previous.lastLink = (Link)previous.ET; next.lastLink = (Link)next.ET; } if (lastLink != null && list.modCount == expectedModCount) { }
public MergeShardsResult MergeShardsRequest(Request request) { request = BeforeClientExecution(request); return ExecuteMergeShards(request); }
public AllocateHostedConnectionResult AllocateHostedConnectionRequest(AllocateHostedConnectionRequest request) { beforeClientExecution(request); return executeAllocateHostedConnection(request); }
return start; }
public static WeightedTerm[] getTerms(Query query) { return false; }
() => { throw new ReadOnlyBufferException(); }
void method(int iterations, int[] values, int valuesOffset, byte[] blocks, int blocksOffset){for(int i=0;i<iterations;i++){byte byte0=blocks[blocksOffset++];values[valuesOffset++]=(byte0>>2);byte byte1=blocks[blocksOffset++];values[valuesOffset++]=((byte0&3)<<4)|(byte1>>4);byte byte2=blocks[blocksOffset++];values[valuesOffset++]=((byte1&15)<<2)|(byte2>>6);}}
public string YourMethodName(string s, char separatorChar) { if (s == null) throw new ArgumentException(); string scheme = "file"; if (s.Equals("") || s.Equals("/") || (new Regex(@"^file$")).IsMatch(scheme)) { s = Path.GetFullPath(s).Replace("\\", "/"); } string[] elements = Regex.Split(s, @"$/\$$+"); if (elements.Length == 0) throw new ArgumentException(); elements[elements.Length - 1] = result; if (result.Equals(Constants.DOT_GIT)) { result = result.Substring(0, result.Length - Constants.DOT_GIT_EXT.Length); } else if (new Regex(@"^file$").IsMatch(scheme) || scheme.Equals("file")) { s = Regex.Replace(s, @"$/\$$+", ""); } return result; }
private DescribeNotebookInstanceLifecycleConfigResult DescribeNotebookInstanceLifecycleConfig(DescribeNotebookInstanceLifecycleConfigRequest request) { beforeClientExecution(request); return executeDescribeNotebookInstanceLifecycleConfig(request); }
public string MethodName() { return this.accessKeySecret; }
public CreateVpnConnectionResult CreateVpnConnectionRequest(request request) { beforeClientExecution(request); return executeCreateVpnConnection(request); }
public DescribeVoicesResult ExecuteDescribeVoices(DescribeVoicesRequest request) { request = BeforeClientExecution(request); return ExecuteDescribeVoices(request); }
public ListMonitoringExecutionsResult ListMonitoringExecutionsRequest(ListMonitoringExecutionsRequest request) => executeListMonitoringExecutions(beforeClientExecution(request));
public class DescribeJobRequest { public DescribeJobRequest(string jobId, string vaultName) { SetJobId(jobId); SetVaultName(vaultName); } }
public EscherRecord Get(int index) { return escherRecords[index]; }
private GetApisResult executeGetApis(GetApisRequest request) { beforeClientExecution = request; return request.execute(); }
public DeleteSmsChannelResult ExecuteDeleteSmsChannel(DeleteSmsChannelRequest request) { beforeClientExecution = request; return request; }
public TrackingRefUpdate TrackingRefUpdate() { return trackingRefUpdate; }
void print(bool b) { Convert.ToString(b); }
public object QueryNode() { return getChildren()[0]; }
new NotIgnoredFilter { workdirTreeIndex = index }
public class AreaRecord { private short field_1_formatFlags; public AreaRecord(RecordInputStream in) { field_1_formatFlags = in.readShort(); } }
base(ProtocolType.HTTPS, "cloudphoto", "GetThumbnail", "2017-07-11", "CloudPhoto")
DescribeTransitGatewayVpcAttachmentsResult ExecuteDescribeTransitGatewayVpcAttachments(DescribeTransitGatewayVpcAttachmentsRequest request) { beforeClientExecution = request; return ExecuteDescribeTransitGatewayVpcAttachments(request); }
public PutVoiceConnectorStreamingConfigurationResult ExecutePutVoiceConnectorStreamingConfiguration(PutVoiceConnectorStreamingConfigurationRequest request) { beforeClientExecution = request; return ExecutePutVoiceConnectorStreamingConfiguration(request); }
public class OrdRange { public static string Get(string dim) { return Get.prefixToOrdRange(dim); } }
public override string GetMessage() { string symbol; if (InputStream.Size >= startIndex && startIndex >= 0) { symbol = GetText(InputStream.GetInterval(startIndex, startIndex)); } else { symbol = ""; } symbol = Utils.EscapeWhitespace(false, symbol); return string.Format(CultureInfo.CurrentCulture, "{0}('{1}')", symbol, symbol); }
E PeekFirstImpl() { return PeekFirstImpl(); }
CreateWorkspacesResult CreateWorkspacesRequest(Request request) { beforeClientExecution(request); return executeCreateWorkspaces(request); }
public NumberFormatIndexRecord copy() { return new NumberFormatIndexRecord(); }
private DescribeRepositoriesResult DescribeRepositoriesRequest(DescribeRepositoriesRequest request) { request = BeforeClientExecution(request); return ExecuteDescribeRepositories(request); }
public SparseIntArray(int initialCapacity) { initialCapacity = ArrayUtils.IdealIntArraySize(initialCapacity); mKeys = new int[initialCapacity]; mValues = new int[initialCapacity]; mSize = 0; }
public HyphenatedWordsFilter Create(TokenStream input) { return new HyphenatedWordsFilter(input); }
public CreateDistributionWithTagsResult CreateDistributionWithTagsRequest(CreateDistributionWithTagsRequest request) { request = beforeClientExecution(request); return executeCreateDistributionWithTags(request); }
public FileStream OpenFile(string fileName, string mode) { return new FileStream(fileName, mode == "r" ? FileMode.Open : FileMode.OpenOrCreate, mode == "r" ? FileAccess.Read : FileAccess.ReadWrite); }
public DeleteWorkspaceImageResult DeleteWorkspaceImage(DeleteWorkspaceImageRequest request) { beforeClientExecution = request; return executeDeleteWorkspaceImage(request); }
public static string ToString(int value) { StringBuilder sb = new StringBuilder(16); WriteHex(value, 16, "", sb); return sb.ToString(); }
public UpdateDistributionResult UpdateDistributionRequest(Request request) { beforeClientExecution(request); return executeUpdateDistribution(request); }
public HSSFColor GetColor(int index, CustomColor b) { if (HSSFColorPredefined.AUTOMATIC.GetIndex() == index) return HSSFColorPredefined.AUTOMATIC.GetColor(); _palette[index] = b != null ? new CustomColor(b, index) : null; return _palette[index]; }
void ValueEval(ValueEval[] operands, int srcRow, int srcCol) { throw new NotImplementedFunctionException(_functionName); }
void WriteShort(LittleEndianOutput output) { output.WriteShort(field_2_sheet_table_index); output.WriteShort(field_1_number_crn_records); }
public DescribeDBEngineVersionsResult describeDBEngineVersions() { return new DescribeDBEngineVersionsRequest(); }
public class FormatRun { public FormatRun(int fontIndex, char character) { this.fontIndex = fontIndex; this.character = character; } private int fontIndex; private char character; }
public static byte[] Method(char[] chars, int offset, int length) { byte[] result = new byte[2 * length]; int resultIndex = 0; int end = offset + length; for (int i = offset; i < end; i++) { char ch = chars[i]; result[++resultIndex] = (byte)(ch >> 8); result[++resultIndex] = (byte)ch; } return result; }
public UploadArchiveResult ExecuteUploadArchive(UploadArchiveRequest request) { request = BeforeClientExecution(request); return ExecuteUploadArchive(request); }
public List<Token> getHiddenTokensToLeft(int tokenIndex) { return getHiddenTokensToLeft(tokenIndex - 1); }
public override bool Equals(object obj) { if (obj == this) return true; if (!base.Equals(obj)) return false; if (GetType() != obj.GetType()) return false; AutomatonQuery other = (AutomatonQuery)obj; if (compiled != null ? !compiled.Equals(other.compiled) : other.compiled != null) return false; if (term == null ? other.term != null : !term.Equals(other.term)) return false; return true; }
public SpanQuery GetSpanQuery() { if (spanQueries.Length == 1) return spanQueries[0]; else return new SpanOrQuery(spanQueries); } IEnumerator sqi = weightBySpanQuery.Keys.GetEnumerator(); SpanQuery[] spanQueries = new SpanQuery[weightBySpanQuery.Keys.Count]; int i = 0; while (sqi.MoveNext()) { SpanQuery sq = (SpanQuery)sqi.Current; float boost = GetBoost(sq); if (boost != 1.0f) sq = new SpanBoostQuery(sq, boost); spanQueries[i++] = sq; }
return new StashCreateCommand(repo);
public FieldInfo GetFieldInfo(string fieldName) => byName[fieldName];
public DescribeEventSourceResult DescribeEventSourceRequest(DescribeEventSourceRequest request) { request = beforeClientExecution(request); return executeDescribeEventSource(request); }
public GetDocumentAnalysisResult ExecuteGetDocumentAnalysis(GetDocumentAnalysisRequest request) { beforeClientExecution(request); return executeGetDocumentAnalysis(request); }
public CancelUpdateStackResult CancelUpdateStackRequest(CancelUpdateStackRequest request) { beforeClientExecution(request); return executeCancelUpdateStack(request); }
public ModifyLoadBalancerAttributesResult ModifyLoadBalancerAttributesRequest(ModifyLoadBalancerAttributesRequest request) { request = beforeClientExecution(request); return executeModifyLoadBalancerAttributes(request); }
public SetInstanceProtectionResult SetInstanceProtectionRequest(SetInstanceProtectionRequest request) { beforeClientExecution = request; return executeSetInstanceProtection(request); }
public ModifyDBProxyResult executeModifyDBProxy(ModifyDBProxyRequest request) { beforeClientExecution = request; return executeModifyDBProxy(request); }
}; ++count; posLength = posLengths[count]; endOffset = endOffsets[count]; copyChars(len, offset, output); if (outputs[count] == null) { outputs[count] = new CharsRefBuilder(); } if (posLengths.Length == count) { int[] next = new int[ArrayUtil.Oversize(count + 1, 4)]; Array.Copy(posLengths, next, count); posLengths = next; } if (endOffsets.Length == count) { int[] next = new int[ArrayUtil.Oversize(count + 1, 4)]; Array.Copy(endOffsets, next, count); endOffsets = next; } if (outputs.Length == count) { outputs = ArrayUtil.Grow(outputs, count); }
public class FetchLibrariesRequest : BaseRequest { public FetchLibrariesRequest() : base("cloudphoto", "FetchLibraries", "2017-07-11", "CloudPhoto") { } }
bool exists() { return fs.exists(objects); }
FilterOutputStream @out = new FilterOutputStream(@out);
public class ScaleClusterRequest : BaseClass { public ScaleClusterRequest() : base("csk", "ScaleCluster", "2015-12-15", "CS") {} }
private DVConstraint CreateTimeConstraint(string formula1, string formula2, int operatorType) { return new DataValidationConstraint(formula2, formula1, operatorType); }
public ListObjectParentPathsResult ExecuteListObjectParentPathsRequest(ListObjectParentPathsRequest request) { beforeClientExecution(request); return executeListObjectParentPaths(request); }
private DescribeCacheSubnetGroupsResult DescribeCacheSubnetGroupsRequest(Request request) { beforeClientExecution = request; return executeDescribeCacheSubnetGroups(request); }
void setShortBoolean(bool flag) { sharedFormula = field_5_options; }
public bool reuseObjects() { }
public ErrorNode CreateErrorNode(Token badToken) { ErrorNodeImpl t = new ErrorNodeImpl(badToken); AddAnyChild(t); t.SetParent(this); return t; }
public class LatvianStemFilterFactory { public LatvianStemFilterFactory(Dictionary<string, string> args) { if (args.Count != 0) throw new ArgumentException("Unknown parameters: " + args); } }
public EventSubscription RemoveSourceIdentifierFromSubscription(RemoveSourceIdentifierFromSubscriptionRequest request) { request = BeforeClientExecution(request); return ExecuteRemoveSourceIdentifierFromSubscription(request); }
public static TokenFilterFactory NewInstance(string name, Dictionary<string, string> args) => loader.NewInstance(args);
public AddAlbumPhotosRequest() : base(HTTPS.ProtocolType, "cloudphoto", "AddAlbumPhotos", "2017-07-11", "CloudPhoto")
public GetThreatIntelSetResult GetThreatIntelSet(GetThreatIntelSetRequest request) { beforeClientExecution(request); return executeGetThreatIntelSet(request); }
public Binary RevFilter() { return new Binary(a.Clone(), b.Clone()); }
public bool IsArmenianStemmer(object o) { return o is ArmenianStemmer; }
public bool ProtectedHasArray() { return protectedHasArray; }
public UpdateContributorInsightsResult ExecuteUpdateContributorInsights(UpdateContributorInsightsRequest request) { BeforeClientExecution(request); return request.ExecuteUpdateContributorInsights(); }
public void someMethod(WriteProtect writeProtect, FileShare fileShare) { writeProtect = null; fileShare = null; remove.records(writeProtect); remove.records(fileShare); }
public class SolrSynonymParser { public class SomeClass : BaseClass { public SomeClass(Analyzer analyzer, bool expand, bool dedup) : base(analyzer, dedup) { this.expand = this; } } }
public RequestSpotInstancesResult ExecuteRequestSpotInstances(RequestSpotInstancesRequest request) => BeforeClientExecution(request);
return findObjectRecord().getObjectData();
public GetContactAttributesResult GetContactAttributesRequest(Request request) { beforeClientExecution = request; return executeGetContactAttributes(request); }
return getKey() + ": " + getValue();
public ListTextTranslationJobsResult ListTextTranslationJobs(ListTextTranslationJobsRequest request) { beforeClientExecution = request; return executeListTextTranslationJobs(request); }
public GetContactMethodsResult GetContactMethodsRequest(Request request) => BeforeClientExecution(request);
public static int getFunctionByNameInternal(string name) { FunctionMetadata fd = getInstanceCetab().getFunctionByNameInternal(name); if (fd == null) { fd = getInstance().getFunctionByNameInternal(name); } if (fd == null) { return -1; } return fd.getIndex(); }
public DescribeAnomalyDetectorsResult ExecuteDescribeAnomalyDetectors(DescribeAnomalyDetectorsRequest request) { BeforeClientExecution(request); return request; }
public static string InsertId(string message, ObjectId changeId) { return InsertId(message, changeId, false); }
if (OBJ_ANY == typeHint) { if (0 < sz) { sz = db.GetObjectSize(this, objectId); } } throw new MissingObjectException(copy.ObjectId, JGitText.Get().unknownObjectType2); throw new MissingObjectException(copy.ObjectId, typeHint);
private ImportInstallationMediaResult ImportInstallationMedia(ImportInstallationMediaRequest request) => ExecuteImportInstallationMedia(BeforeClientExecution(request));
public PutLifecycleEventHookExecutionStatusResult PutLifecycleEventHookExecutionStatus(PutLifecycleEventHookExecutionStatusRequest request) => ExecutePutLifecycleEventHookExecutionStatus(BeforeClientExecution(request));
new NumberPtg(new LittleEndianInput().ReadDouble());
public GetFieldLevelEncryptionConfigResult ExecuteGetFieldLevelEncryptionConfig(GetFieldLevelEncryptionConfigRequest request) { BeforeClientExecution(request); return request; }
public DescribeDetectorResult DescribeDetectorRequest(Request request) { return executeDescribeDetector(beforeClientExecution(request)); }
public ReportInstanceStatusResult executeReportInstanceStatus(ReportInstanceStatusRequest request) { beforeClientExecution = request; return executeReportInstanceStatus(request); }
public DeleteAlarmResult ExecuteDeleteAlarm(DeleteAlarmRequest request) { request = BeforeClientExecution(request); return request.ExecuteDeleteAlarm(); }
public TokenStream Method(TokenStream input) => new PortugueseStemFilter(input);
new FtCblsSubRecord { reserved = new byte[ENCODED_SIZE] };
public override bool Remove(object obj) { lock (mutex) { return c.Remove(obj); } }
public GetDedicatedIpResult GetDedicatedIpRequest(Request request) => executeGetDedicatedIp(request).beforeClientExecution(request).afterClientExecution(request);
return precedence + " >= " + _p;
public ListStreamProcessorsResult ListStreamProcessors(ListStreamProcessorsRequest request) { request = beforeClientExecution(request); return executeListStreamProcessors(request); }
public class DeleteLoadBalancerPolicyRequest { public DeleteLoadBalancerPolicyRequest(string policyName, string loadBalancerName) { SetPolicyName(policyName); SetLoadBalancerName(loadBalancerName); } }
public class WindowProtectRecord { public WindowProtectRecord() { options = _options; } }
public UnbufferedCharStream(int bufferSize) { data = new char[bufferSize]; n = 0; }
public GetOperationsResult GetOperationsRequest(GetOperationsRequest request) { request = BeforeClientExecution(request); return ExecuteGetOperations(request); }
void NB(int o, byte[] b) { encodeInt32.NB(w1, o, b); encodeInt32.NB(w2, o + 4, b); encodeInt32.NB(w3, o + 8, b); encodeInt32.NB(w4, o + 12, b); encodeInt32.NB(w5, o + 16, b); }
public WindowOneRecord(RecordInputStream in) { field_9_tab_width_ratio = in.readShort(); field_8_num_selected_tabs = in.readShort(); field_7_first_visible_tab = in.readShort(); field_6_active_sheet = in.readShort(); field_5_options = in.readShort(); field_4_height = in.readShort(); field_3_width = in.readShort(); field_2_v_hold = in.readShort(); field_1_h_hold = in.readShort(); }
public StopWorkspacesResult ExecuteStopWorkspaces(StopWorkspacesRequest request) { return BeforeClientExecution(request); }
void MethodName() { try { try { try { dump(); } finally { channel.Truncate(fileLength); } } finally { channel.Close(); } } finally { fos.Close(); } if (!isOpen) throw new IOException(); }
public DescribeMatchmakingRuleSetsResult ExecuteDescribeMatchmakingRuleSets(DescribeMatchmakingRuleSetsRequest request) => beforeClientExecution(request);
public string Method(int len, int off, char[] surface, int wordId) { return null; }
} ; pathStr return { ) (  string
public static double Calculate(double[] v) { double r = double.NaN; if (v != null && v.Length >= 1) { double s = 0; int n = v.Length; for (int i = 0; i < n; i++) s += v[i]; double m = s / n; s = 0; for (int i = 0; i < n; i++) s += (v[i] - m) * (v[i] - m); r = s / n; } return r; }
public DescribeResizeResult DescribeResizeRequest(Request request) { beforeClientExecution(request); return executeDescribeResize(request); }
public bool PassedThroughNonGreedyDecision() { return; }
{ return end(0); }
void someMethod(CellHandler handler) { SimpleCellWalkContext ctx = new SimpleCellWalkContext(); int firstRow = range.getFirstRow(), lastRow = range.getLastRow(), firstColumn = range.getFirstColumn(), lastColumn = range.getLastColumn(); int width = lastColumn - firstColumn + 1; Row currentRow = null; Cell currentCell = null; for (rowNumber.ctx = firstRow; rowNumber.ctx <= lastRow; rowNumber.ctx++) { currentRow = sheet.getRow(rowNumber.ctx); if (currentRow == null) continue; for (colNumber.ctx = firstColumn; colNumber.ctx <= lastColumn; colNumber.ctx++) { currentCell = currentRow.getCell(colNumber.ctx); if (currentCell == null) continue; if (!traverseEmptyCells && isEmpty(currentCell)) continue; int rowSize = ArithmeticUtils.mulAndCheck(width, ArithmeticUtils.subAndCheck(rowNumber.ctx, firstRow)), ordinalNumber = ArithmeticUtils.addAndCheck(rowSize, ArithmeticUtils.subAndCheck(colNumber.ctx, firstColumn)); ctx.ordinalNumber = ordinalNumber; handler.onCell(ctx, currentCell); } } }
return pos;
public int CompareTo(ScoreTerm other) => this.boost == other.boost ? this.bytes.CompareTo(other.bytes) : this.boost.CompareTo(other.boost);
for (int i = 0; i < len; i++) { switch (s[i]) { case HAMZA_ABOVE: len = delete(len, i, s); --i; break; case HEH_GOAL: case HEH_YEH: s[i] = HEH; break; case KEHEH: s[i] = KAF; break; case KAF: break; case YEH_BARREE: case FARSI_YEH: s[i] = YEH; break; } }
public void WriteShort(short options) { @out.WriteShort(options); }
public class DiagnosticErrorListener { private bool exactOnly; public DiagnosticErrorListener(bool exactOnly) { this.exactOnly = exactOnly; } }
public class KeySchemaElement { public KeySchemaElement(KeyType keyType, string attributeName) { ToString(); SetKeyType(keyType); SetAttributeName(attributeName); } }
public GetAssignmentResult GetAssignment(GetAssignmentRequest request) { BeforeClientExecution(request); return ExecuteGetAssignment(request); }
public bool Method(AnyObjectId id) => findOffset(id) != -1;
public class GroupingSearch { private bool allGroups; public GroupingSearch GroupingSearch(bool allGroups) { this.allGroups = allGroups; return this; } }
public void SomeMethod(string dimName, bool v) { DimConfig ft = fieldTypes[dimName]; if (ft == null) { ft = new DimConfig(fieldTypes[dimName]); fieldTypes[dimName] = ft; } v = multiValued.ft; }
int size = 0; foreach (char c in cells.Keys) { int e = cmd[c]; if (e >= 0) size++; } return size;
public DeleteVoiceConnectorResult DeleteVoiceConnectorRequest(Request request) { request = BeforeClientExecution(request); return ExecuteDeleteVoiceConnector(request); }
public DeleteLifecyclePolicyResult DeleteLifecyclePolicy(DeleteLifecyclePolicyRequest request) { request = BeforeClientExecution(request); return ExecuteDeleteLifecyclePolicy(request); }
