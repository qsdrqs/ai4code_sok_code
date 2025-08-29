(LittleEndianOutput [email protected]) => { [email protected].writeShort(); }
void Method(BlockList<T> src) { if (src.size == 0) srcDirIdx = 0; for (; srcDirIdx < src.tailDirIdx; srcDirIdx++) addAll(src.directory[srcDirIdx], 0, BLOCK_SIZE); if (src.tailBlkIdx != 0) addAll(src.tailBlock, 0, src.tailBlkIdx); }
public void AddByte(byte b) { if (currentBlock == null || upto == blockSize) { if (currentBlock != null) { AddBlock(currentBlock); } currentBlock = new byte[blockSize]; upto = 0; } currentBlock[upto++] = b; }
public class ObjectId { public ObjectId() { } }
DeleteDomainEntryResult DeleteDomainEntryResult(DeleteDomainEntryRequest request) { request = BeforeClientExecution(request); return ExecuteDeleteDomainEntry(request); }
() => ((termOffsets != null) ? termOffsets.ramBytesUsed() : 0) + ((termsDictOffsets != null) ? termsDictOffsets.ramBytesUsed() : 0)
public string GetMessage() { byte[] raw = buffer; int msgB = RawParseUtils.TagMessage; if (msgB < 0) return ""; return RawParseUtils.Decode(GuessEncoding(), raw, msgB, raw.Length); }
public POIFSFileSystem() { Initialize(true); _header.SetBATCount(1); _header.SetBATArray(new int[] { 1 }); BATBlock bb = BATBlock.CreateEmptyBATBlock(bigBlockSize, false); bb.SetOurBlockIndex(1); _batBlocks.Add(bb); SetNextBlock(0, POIFSConstants.END_OF_CHAIN); SetNextBlock(1, POIFSConstants.FAT_SECTOR_BLOCK); _propertyTable.SetStartBlock(0); }
void SomeMethod(int address) { slice = pool.buffers[address >> ByteBlockPool.BYTE_BLOCK_SHIFT]; Debug.Assert(slice != null); upto = address & ByteBlockPool.BYTE_BLOCK_MASK; offset0 = address; Debug.Assert(upto < (1 << ByteBlockPool.BYTE_BLOCK_SHIFT)); }
public class SubmoduleAddCommand { private string path; public SubmoduleAddCommand(string path) { this.path = path; } }
public ListIngestionsResult ListIngestionsResult() { request = beforeClientExecution(request); return executeListIngestions(request); }
public QueryParserTokenManager(CharStream stream, int lexState) : base(stream) { SwitchTo(lexState); }
public GetShardIteratorResult GetShardIteratorResult(GetShardIteratorRequest request) { request = BeforeClientExecution(request); return ExecuteGetShardIterator(request); }
public class ModifyStrategyRequest : BaseClass { public ModifyStrategyRequest() : base("aegis", "2016-11-11", "ModifyStrategy", "vipaegis") {} }
public bool CheckData() { lock (lockObj) { if (inputStream == null) throw new IOException("InputStreamReader is closed"); try { return bytes.HasRemaining() || inputStream.DataAvailable; } catch { return false; } } }
public EscherOptRecord() { }
public int Read(byte[] buffer, int offset, int length) { lock (this) { if (buffer == null) throw new ArgumentNullException("buffer"); if (offset < 0 || length < 0 || offset + length > buffer.Length) throw new ArgumentOutOfRangeException(); if (length == 0) return 0; int copylen = Math.Min(count - pos, length); for (int i = 0; i < copylen; i++) buffer[offset + i] = (byte)this.buffer[pos + i]; return copylen; } }
public class OpenNLPSentenceBreakIterator { public OpenNLPSentenceBreakIterator() { this.sentenceOp = sentenceOp; } }
void WriteString(string str) { Write(str ?? "null"); }
public class NotImplementedException : Exception { private string _functionName; public NotImplementedException(string functionName, NotImplementedException cause) : base(functionName, cause) { _functionName = functionName; } }
V V() { return base.nextEntry.getValue(); }
public void Read(byte[] b, int offset, int len, bool useBuffer) { int available = bufferLength - bufferPosition; if (len <= available) { if (len > 0) Buffer.BlockCopy(buffer, bufferPosition, b, offset, len); bufferPosition += len; } else { if (available > 0) { Buffer.BlockCopy(buffer, bufferPosition, b, offset, available); offset += available; len -= available; bufferPosition += available; } if (useBuffer && len < bufferSize) { refill(); if (bufferLength < len) { Buffer.BlockCopy(buffer, 0, b, offset, bufferLength); throw new EndOfStreamException("read past EOF: " + this); } else { Buffer.BlockCopy(buffer, 0, b, offset, len); } } else { long after = bufferStart + bufferPosition + len; if (after > length()) throw new EndOfStreamException("read past EOF: " + this); readInternal(b, offset, len); bufferStart = after; bufferPosition = 0; bufferLength = 0; } } }
TagQueueResult TagQueueResult(TagQueueRequest request) => executeTagQueue(request = beforeClientExecution);
void Method() { throw new System.NotImplementedException(); }
public CacheSubnetGroup CacheSubnetGroup() { request = beforeClientExecution(request); return executeModifyCacheSubnetGroup(request); }
public void setParams(string @params) { base.setParams(@params); language = country = variant = ""; string[] tokens = @params.Split(new char[] { ',' }, StringSplitOptions.RemoveEmptyEntries); if (tokens.Length > 0) language = tokens[0]; if (tokens.Length > 1) country = tokens[1]; if (tokens.Length > 2) variant = tokens[2]; }
public DeleteDocumentationVersionResult DeleteDocumentationVersion(DeleteDocumentationVersionRequest request) { request = BeforeClientExecution(request); return ExecuteDeleteDocumentationVersion(request); }
public override bool Equals(object obj) { if (!(obj is FacetLabel)) return false; FacetLabel other = (FacetLabel)obj; if (length != other.length) return false; for (int i = length - 1; ; i--) { if (!components[i].Equals(other.components[i])) return false; } return true; }
public GetInstanceAccessDetailsResult GetInstanceAccessDetailsResult(GetInstanceAccessDetailsRequest request) { request = BeforeClientExecution(request); return executeGetInstanceAccessDetails; }
public HSSFPolygon CreatePolygon(HSSFChildAnchor anchor) { HSSFPolygon shape = new HSSFPolygon(this, anchor); shape.SetParent(); shape.SetAnchor(anchor); shapes.Add(shape); OnCreate(shape); return shape; }
string GetSheetName(int sheetIndex) => GetBoundSheetRec(sheetIndex).Sheetname;
public GetDashboardResult GetDashboardResult(GetDashboardRequest request) { request = beforeClientExecution(request); return executeGetDashboard; }
public AssociateSigninDelegateGroupsWithAccountResult AssociateSigninDelegateGroupsWithAccount(AssociateSigninDelegateGroupsWithAccountRequest request) { request = beforeClientExecution; return executeAssociateSigninDelegateGroupsWithAccount(request); }
public void ProcessMulBlankRecord(MulBlankRecord mbr) { for (int j = 0; j < mbr.NumColumns; j++) { BlankRecord br = new BlankRecord(); br.Column = (short)(j + mbr.FirstColumn); br.Row = mbr.Row; br.XFIndex = mbr.XFAt; InsertCell(br); } }
public static string EscapeFunction(string inputString){StringBuilder sb=new StringBuilder();sb.Append("\\Q");int apos=0;int k;while((k=inputString.IndexOf("\\E",apos))>=0){sb.Append(inputString.Substring(apos,k+2-apos)).Append("\\\\E\\Q");apos=k+2;}return sb.Append(inputString.Substring(apos)).Append("\\E").ToString();}
public ByteBuffer(ByteBuffer value) { throw new ReadOnlyBufferException(); }
public ArrayPtg(object[][] values2d) { int nColumns = values2d[0].Length; int nRows = values2d.Length; int _nColumns = nColumns; int _nRows = nRows; object[] vv = new object[_nColumns * _nRows]; for (int r = 0; r < nRows; r++) { object[] rowData = values2d[r]; for (int c = 0; c < nColumns; c++) { vv[GetValueIndex(c, r)] = rowData[c]; } } _arrayValues = vv; _reserved0Int = 0; _reserved1Short = 0; _reserved2Byte = 0; }
public GetIceServerConfigResult GetIceServerConfigResult(GetIceServerConfigRequest request) { request = this.beforeClientExecution; return executeGetIceServerConfig(request); }
public string String() { return GetType().Name + " [" + GetValueAsString() + "]"; }
public string String() { return "ToChildBlockJoinQuery (" + parentQuery.ToString() + ")"; }
void Increment() { Interlocked.Increment(ref refCount); }
public UpdateConfigurationSetSendingEnabledResult UpdateConfigurationSetSendingEnabledResult(UpdateConfigurationSetSendingEnabledRequest request) { request = beforeClientExecution; return executeUpdateConfigurationSetSendingEnabled(request); }
() => getXBATEntriesPerBlock() *
void pow10(int pow10) { TenPower tp = TenPower.GetInstance(Math.Abs(pow10)); if (pow10 < 0) { mulShift(tp._divisor, tp._divisorShift); } else { mulShift(tp._multiplicand, tp._multiplierShift); } }
string ToString(){StringBuilder b=new StringBuilder();int l=length;b.Append(System.IO.Path.DirectorySeparatorChar);for(int i=0;i<l;i++){b.Append(getComponent(i));if(i<l-1)b.Append(System.IO.Path.DirectorySeparatorChar);}return b.ToString();}
public InstanceProfileCredentialsProvider(ECSMetadataServiceCredentialsFetcher fetcher) { this.fetcher = fetcher; SetRoleName(roleName); }
void Method(ProgressMonitor pm) { }
void Method() { if (!first) { ptr = 0; if (!eof()) parseEntry(); } }
public E E() { if (iterator.PreviousIndex() >= start) { return iterator.Previous(); } throw new NoSuchElementException(); }
public class String { public String() {} }
int CheckValue(int value) { for (int i = 0; i < mSize; i++) if (mValues[i] == value) return -1; }
List<CharsRef> stems = Stem(word, length); if (stems.Count < 2) { return stems; } HashSet<string> terms = new HashSet<string>(dictionary.ignoreCase ? StringComparer.OrdinalIgnoreCase : StringComparer.Ordinal); List<CharsRef> deduped = new List<CharsRef>(); foreach (CharsRef s in stems) { string key = new string(s.Chars, 0, s.Length); if (!terms.Contains(key)) { deduped.Add(s); terms.Add(key); } } return deduped;
public GetGatewayResponsesResult GetGatewayResponsesResult(GetGatewayResponsesRequest request) { request = beforeClientExecution(request); return executeGetGatewayResponses; }
void updateCurrentBlock(int pos) {     currentBlockIndex = (int)(pos >> blockBits);     currentBlock = blocks[currentBlockIndex];     currentBlockUpto = (int)(pos & blockMask); }
(n) => { s = Math.Min(available(), Math.Max(0, n)); ptr += s; }
public BootstrapActionDetail() { SetBootstrapActionConfig(bootstrapActionConfig); }
public void Write(LittleEndianOutput output) { output.WriteShort(field_1_row); output.WriteShort(field_2_col); output.WriteShort(field_3_flags); output.WriteShort(field_4_shapeid); output.WriteShort((short)field_6_author.Length); output.WriteByte(field_5_hasMultibyte ? (byte)0x01 : (byte)0x00); if (field_5_hasMultibyte) StringUtil.PutUnicodeLE(field_6_author, output); else StringUtil.PutCompressedUnicode(field_6_author, output); if (field_7_padding.HasValue) output.WriteByte((byte)field_7_padding.Value); }
(string @string) => lastIndexOf;
public bool Add(E obj) { return addLastImpl; }
private void SomeMethod(string section, string subsection) { ConfigSnapshot src, res; do { src = state; res = UnsetSection(src, section, subsection); } while (Interlocked.CompareExchange(ref state, res, src) != src); }
string String() { return tagName; }
subrecords.Insert(index, element);
public bool RemoveMethod() { lock (mutex) { return @delegate().Remove(o); } }
public static DoubleMetaphoneFilter CreateDoubleMetaphoneFilter(TokenStream input) => new DoubleMetaphoneFilter(input);
int getInCoreLength() {     return inCoreLength; }
void Method(bool newValue) { }
public class Pair { private ContentSource oldSource; private ContentSource newSource; public Pair(ContentSource oldSource, ContentSource newSource) { this.oldSource = oldSource; this.newSource = newSource; } }
(int i) => { if (i < 0 || i >= entries.Length) throw new ArgumentOutOfRangeException(nameof(i), i, "Index was outside the bounds of the array."); return entries[i]; };
public CreateRepoRequest() : base("cr", "2016-06-07", "CreateRepo", "cr") { "/repos"; setMethod(); }
bool MyMethod() { }
void Method(){if(expectedModCount==list.modCount){if(lastLink!=null){Link<ET>next=lastLink.next;Link<ET>previous=lastLink.previous;next.previous=previous;previous.next=next;if(lastLink==link){pos--;}link=previous;lastLink=null;expectedModCount++;list.size--;list.modCount++;}else{throw new IllegalStateException();}}else{throw new ConcurrentModificationException();}}
public MergeShardsResult MergeShardsResult() { request = beforeClientExecution(request); return executeMergeShards(request); }
public AllocateHostedConnectionResult AllocateHostedConnectionResult(AllocateHostedConnectionRequest request) { request = beforeClientExecution(request); return executeAllocateHostedConnection; }
void myMethod() { }
public static WeightedTerm WeightedTerm(Query query) { return getTerms(query, false); }
public ByteBuffer() { throw new ReadOnlyBufferException(); }
public static void ProcessBlocks(byte[] blocks, ref int blocksOffset, byte[] values, ref int valuesOffset, int iterations) { for (int i = 0; i < iterations; ++i) { int byte0 = blocks[blocksOffset++]; values[valuesOffset++] = (byte)(byte0 >> 2); int byte1 = blocks[blocksOffset++]; values[valuesOffset++] = (byte)(((byte0 & 3) << 4) | (byte1 >> 4)); int byte2 = blocks[blocksOffset++]; values[valuesOffset++] = (byte)(((byte1 & 15) << 2) | (byte2 >> 6)); values[valuesOffset++] = (byte)(byte2 & 63); } }
public string TranslateMethod(){string s = GetPath();if(s == "/" || s == "")s = GetHost();if(s == null)throw new ArgumentException();string[] elements;if(scheme == "file" || LOCAL_FILE.IsMatch(s))elements = Regex.Split(s, @"[\\/]");else elements = Regex.Split(s, "/+");if(elements.Length == 0)throw new ArgumentException();string result = elements[elements.Length - 1];if(Constants.DOT_GIT == result)result = elements[elements.Length - 2];else if(result.EndsWith(Constants.DOT_GIT_EXT))result = result.Substring(0, result.Length - Constants.DOT_GIT_EXT.Length);return result;}
public DescribeNotebookInstanceLifecycleConfigResult DescribeNotebookInstanceLifecycleConfig(DescribeNotebookInstanceLifecycleConfigRequest request) { request = beforeClientExecution; return executeDescribeNotebookInstanceLifecycleConfig(request); }
string String() { return null; }
public CreateVpnConnectionResult CreateVpnConnectionResult() { request = beforeClientExecution(request); return executeCreateVpnConnection(request); }
public DescribeVoicesResult describeVoices() {     request = beforeClientExecution(request);     return executeDescribeVoices(request); }
public ListMonitoringExecutionsResult ExecuteListMonitoringExecutions(ListMonitoringExecutionsRequest request) { request = BeforeClientExecution(request); return executeListMonitoringExecutions; }
public DescribeJobRequest(String vaultName, String jobId) {     setVaultName(vaultName);     setJobId(jobId); }
public EscherRecord getEscherRecord(int index) {     return escherRecords.get(index); }
public GetApisResult GetApisResult(GetApisRequest request) { beforeClientExecution = request; return executeGetApis(request); }
public DeleteSmsChannelResult DeleteSmsChannel(DeleteSmsChannelRequest request) { request = BeforeClientExecution(request); return ExecuteDeleteSmsChannel; }
public class TrackingRefUpdate { public TrackingRefUpdate() {} }
Console.WriteLine(b == null ? "null" : b.Value.ToString().ToLower());
public QueryNode GetFirstChild() => GetChildren()[0];
public NotIgnoredFilter(WorkdirTreeIndex workdirTreeIndex) { this.workdirTreeIndex = workdirTreeIndex; }
public AreaRecord(RecordInputStream in) { field_1_formatFlags = in.readShort(); }
new GetThumbnailRequest(ProtocolType.HTTPS);
public DescribeTransitGatewayVpcAttachmentsResult DescribeTransitGatewayVpcAttachmentsResult() { request = BeforeClientExecution(request); return ExecuteDescribeTransitGatewayVpcAttachments(request); }
public dynamic PutVoiceConnectorStreamingConfigurationResult() { request = BeforeClientExecution(request); return ExecutePutVoiceConnectorStreamingConfiguration(request); }
public ReturnType OrdRange() { return prefixToOrdRange[dim]; }
string Symbol = ""; if (startIndex >= 0 && startIndex < getInputStream().size) { Symbol = getInputStream().getText(Interval.Create(startIndex, startIndex)); Symbol = Utils.EscapeWhitespace(Symbol, false); } return string.Format(System.Globalization.CultureInfo.CurrentCulture, "{0}('{1}')", typeof(LexerNoViableAltException).Name, Symbol);
public E E() { return peekFirstImpl; }
public CreateWorkspacesResult CreateWorkspacesResult() { request = beforeClientExecution(request); return executeCreateWorkspaces(request); }
public NumberFormatIndexRecord NumberFormatIndexRecord() { return copy; }
public DescribeRepositoriesResult DescribeRepositoriesResult() { request = beforeClientExecution(request); return executeDescribeRepositories(request); }
public SparseIntArray(int initialCapacity) { initialCapacity = ArrayUtils.IdealIntArraySize(initialCapacity); mKeys = new int[initialCapacity]; mValues = new int[initialCapacity]; mSize = 0; }
public HyphenatedWordsFilter HyphenatedWordsFilter() { return new HyphenatedWordsFilter(input); }
public CreateDistributionWithTagsResult CreateDistributionWithTagsResult (  ) { request = beforeClientExecution ( request ) ; return executeCreateDistributionWithTags ( request ) ; }
public class RandomAccessFile { public RandomAccessFile(string fileName, string mode) { new File(fileName); } }
public DeleteWorkspaceImageResult DeleteWorkspaceImageResult(DeleteWorkspaceImageRequest request) { request = BeforeClientExecution(request); return ExecuteDeleteWorkspaceImage; }
public static string ConvertToHex(int value) { StringBuilder sb = new StringBuilder(16); WriteHex(sb, value, 16, ""); return sb.ToString(); }
public UpdateDistributionResult UpdateDistribution(UpdateDistributionRequest request) { request = BeforeClientExecution(request); return ExecuteUpdateDistribution; }
if (index == HSSFColorPredefined.AUTOMATIC.GetIndex()) { return HSSFColorPredefined.AUTOMATIC.GetColor(); } byte[] b = _palette.GetColor(index); return b == null ? null : new CustomColor();
ValueEval(ValueEval operands, int srcRow, int srcCol) { throw new NotImplementedFunctionException(_functionName); }
void Method() { @out.Write((short)field_1_number_crn_records); @out.Write((short)field_2_sheet_table_index); }
public DescribeDBEngineVersionsResult DescribeDBEngineVersionsResult() { return describeDBEngineVersions(new DescribeDBEngineVersionsRequest()); }
public FormatRun(char character, int fontIndex) { this._character = character; this._fontIndex = fontIndex; }
public static byte[] Convert(char[] chars, int offset, int length) { byte[] result = new byte[length * 2]; int end = offset + length, resultIndex = 0; for (int i = offset; i < end; ++i) { char ch = chars[i]; result[resultIndex++] = (byte)(ch >> 8); result[resultIndex++] = (byte)ch; } return result; }
public UploadArchiveResult UploadArchive(UploadArchiveRequest request) { request = BeforeClientExecution(request); return ExecuteUploadArchive(request); }
public List<HiddenToken> List(int tokenIndex) => getHiddenTokensToLeft(tokenIndex, -1);
public override bool Equals(object obj) { if (this == obj) return true; if (!base.Equals(obj)) return false; if (GetType() != obj.GetType()) return false; AutomatonQuery other = (AutomatonQuery)obj; if (!compiled.Equals(other.compiled)) return false; if (term == null) { if (other.term != null) return false; } else if (!term.Equals(other.term)) return false; return true; }
public SpanQuery SpanQuery() { SpanQuery[] spanQueries = new SpanQuery[size()]; int i = 0; foreach (var sq in weightBySpanQuery.Keys) { float boost = weightBySpanQuery[sq]; if (boost != 1.0f) { sq = new SpanBoostQuery(sq, boost); } spanQueries[i++] = sq; } return spanQueries.Length == 1 ? spanQueries[0] : new SpanOrQuery(spanQueries); }
public StashCreateCommand StashCreateCommand() { return new StashCreateCommand(); }
FieldInfo() { return byName[fieldName]; }
public DescribeEventSourceResult DescribeEventSource(DescribeEventSourceRequest request) { request = beforeClientExecution; return executeDescribeEventSource(request); }
public object GetDocumentAnalysisResult() { request = beforeClientExecution(request); return executeGetDocumentAnalysis(request); }
public CancelUpdateStackResult CancelUpdateStackResult() { request = BeforeClientExecution(request); return ExecuteCancelUpdateStack(request); }
public ModifyLoadBalancerAttributesResult ModifyLoadBalancerAttributesResult(ModifyLoadBalancerAttributesRequest request) { request = beforeClientExecution; return executeModifyLoadBalancerAttributes(request); }
SetInstanceProtectionResult SetInstanceProtectionResult() { request = beforeClientExecution(request); return executeSetInstanceProtection(request); }
public ModifyDBProxyResult ModifyDBProxyResult(ModifyDBProxyRequest request) { request = BeforeClientExecution(request); return ExecuteModifyDBProxy; }
public void AddData(CharsRefBuilder output, int offset, int len, int endOffset, int posLength) { if (count == outputs.Length) { Array.Resize(ref outputs, outputs.Length * 2); } if (count == endOffsets.Length) { int[] next = new int[endOffsets.Length * 2]; Array.Copy(endOffsets, 0, next, 0, count); endOffsets = next; } if (count == posLengths.Length) { int[] next = new int[posLengths.Length * 2]; Array.Copy(posLengths, 0, next, 0, count); posLengths = next; } if (outputs[count] == null) { outputs[count] = new CharsRefBuilder(); } outputs[count].CopyChars(output, offset, len); endOffsets[count] = endOffset; posLengths[count] = posLength; count++; }
public FetchLibrariesRequest() : base("CloudPhoto", "2017-07-11", "FetchLibraries", "cloudphoto") { ; }
public bool Exists() { return fs.Exists; }
public FilterOutputStream(Stream stream) { this.out = stream; }
var request = new ScaleClusterRequest("/clusters/[ClusterId]") { Method = HttpMethod.Put };
public static DataValidationConstraint CreateConstraint(int operatorType, string formula1, string formula2) { return DVConstraint.CreateTimeConstraint(operatorType, formula1, formula2); }
ListObjectParentPathsResult ListObjectParentPathsResult() { request = beforeClientExecution(request); return executeListObjectParentPaths(request); }
public DescribeCacheSubnetGroupsResult DescribeCacheSubnetGroups(DescribeCacheSubnetGroupsRequest request) { request = BeforeClientExecution(request); return ExecuteDescribeCacheSubnetGroups(request); }
field_5_options = sharedFormula.setShortBoolean(field_5_options, flag);
bool Method() { }
public ErrorNodeImpl ErrorNode(Token badToken) { ErrorNodeImpl t = new ErrorNodeImpl(badToken); addAnyChild(t); t.setParent; return t; }
public LatvianStemFilterFactory(IDictionary args) : base(args) { if (args.Count > 0) throw new ArgumentException("Unknown parameters: " + args); }
public class EventSubscription { public dynamic EventSubscription() { request = beforeClientExecution(request); return executeRemoveSourceIdentifierFromSubscription(request); } }
public static TokenFilterFactory TokenFilterFactory(string name, Dictionary<string, string> args) { return loader.NewInstance(name, args); }
public AddAlbumPhotosRequest() : base("CloudPhoto", "2017-07-11", "AddAlbumPhotos", "cloudphoto") { }
public GetThreatIntelSetResult GetThreatIntelSetResult(GetThreatIntelSetRequest request) { request = BeforeClientExecution(request); return ExecuteGetThreatIntelSet; }
Binary RevFilter() { return new Binary(a.Clone(), b.Clone()); }
public boolean myMethod(Object o) {     return true; }
public bool CheckArray() => ProtectedHasArray();
public UpdateContributorInsightsResult UpdateContributorInsights(UpdateContributorInsightsRequest request) { request = beforeClientExecution; return executeUpdateContributorInsights(request); }
public void Cleanup() { records.Remove(fileShare); records.Remove(writeProtect); writeProtect = null; }
public SolrSynonymParser(bool dedup, bool expand, Analyzer analyzer) : base(dedup, analyzer) { this.expand = expand; }
public RequestSpotInstancesResult ExecuteRequestSpotInstances() { request = BeforeClientExecution(request); return ExecuteRequestSpotInstances(request); }
() => { return findObjectRecord.getObjectData(); }
public GetContactAttributesResult GetContactAttributesResult(GetContactAttributesRequest request) { request = beforeClientExecution; return executeGetContactAttributes(request); }
public override string ToString() { return getKey + ": " + getValue(); }
public ListTextTranslationJobsResult ListTextTranslationJobsResult() { request = beforeClientExecution(request); return executeListTextTranslationJobs(request); }
public GetContactMethodsResult GetContactMethodsResult() { request = beforeClientExecution(request); return executeGetContactMethods(request); }
public static int SomeMethod() { FunctionMetadata fd = GetInstance().GetFunctionByNameInternal(name); if (fd == null) { fd = GetInstanceCetab().GetFunctionByNameInternal(name); if (fd == null) { return -1; } } return fd.GetIndex(); }
DescribeAnomalyDetectorsResult DescribeAnomalyDetectors(DescribeAnomalyDetectorsRequest request) { request = beforeClientExecution; return executeDescribeAnomalyDetectors(request); }
public static string MethodName(string message, ObjectId changeId) { return insertId; }
long sz = db.ObjectSize; if (sz < 0) { if (typeHint == ObjectType.OBJ_ANY) throw new MissingObjectException(objectId.Copy(), JGitText.Get().unknownObjectType2); throw new MissingObjectException(objectId.Copy(), typeHint); } return sz;
public ImportInstallationMediaResult ImportInstallationMediaResult() { request = BeforeClientExecution(request); return ExecuteImportInstallationMedia(request); }
public PutLifecycleEventHookExecutionStatusResult PutLifecycleEventHookExecutionStatusResult() { request = BeforeClientExecution(request); return ExecutePutLifecycleEventHookExecutionStatus(request); }
class NumberPtg { public NumberPtg() { in.ReadDouble(); } }
public GetFieldLevelEncryptionConfigResult GetFieldLevelEncryptionConfigResult(GetFieldLevelEncryptionConfigRequest request) { request = beforeClientExecution; return executeGetFieldLevelEncryptionConfig(request); }
public DescribeDetectorResult DescribeDetectorResult(DescribeDetectorRequest request) { request = beforeClientExecution(request); return executeDescribeDetector; }
public ReportInstanceStatusResult ReportInstanceStatusResult(ReportInstanceStatusRequest request) { request = beforeClientExecution; return executeReportInstanceStatus(request); }
public DeleteAlarmResult DeleteAlarm(DeleteAlarmRequest request) { request = BeforeClientExecution(request); return executeDeleteAlarm; }
public TokenStream TokenStream() { return new PortugueseStemFilter(input); }
public FtCblsSubRecord() { reserved = new SomeType(); }
public bool RemoveMethod(object obj) { lock (mutex) { return c.Remove(obj); } }
public GetDedicatedIpResult GetDedicatedIpResult() { request = beforeClientExecution(request); return executeGetDedicatedIp(request); }
string String() { return; }
public ListStreamProcessorsResult ListStreamProcessorsResult(ListStreamProcessorsRequest request) { request = beforeClientExecution; return executeListStreamProcessors(request); }
DeleteLoadBalancerPolicyRequest(String loadBalancerName, String policyName) {     setLoadBalancerName(loadBalancerName);     setPolicyName(policyName); }
public WindowProtectRecord(object options) { }
public class UnbufferedCharStream { private char[] data; public UnbufferedCharStream(int bufferSize) { data = new char[bufferSize]; } }
public GetOperationsResult GetOperationsResult(GetOperationsRequest request) { request = BeforeClientExecution(request); return ExecuteGetOperations; }
public static void EncodeData(byte[] b, int o) { NB.EncodeInt32(b, o, w1); NB.EncodeInt32(b, o + 4, w2); NB.EncodeInt32(b, o + 8, w3); NB.EncodeInt32(b, o + 12, w4); NB.EncodeInt32(b, o + 16, w5); }
public WindowOneRecord(RecordInputStream input) { field_1_h_hold = input.ReadShort(); field_2_v_hold = input.ReadShort(); field_3_width = input.ReadShort(); field_4_height = input.ReadShort(); field_5_options = input.ReadShort(); field_6_active_sheet = input.ReadShort(); field_7_first_visible_tab = input.ReadShort(); field_8_num_selected_tabs = input.ReadShort(); field_9_tab_width_ratio = input.ReadShort(); }
public StopWorkspacesResult StopWorkspacesResult(StopWorkspacesRequest request) { request = BeforeClientExecution(request); return ExecuteStopWorkspaces(request); }
private void CloseResources() { if (isOpen) { isOpen = false; try { Dump(); } finally { try { channel.SetLength(fileLength); } finally { try { channel.Close(); } finally { fos.Close(); } } } } }
public DescribeMatchmakingRuleSetsResult DescribeMatchmakingRuleSetsResult() { request = BeforeClientExecution(request); return ExecuteDescribeMatchmakingRuleSets(request); }
public String(int wordId, char[] surface, int off, int len) { }
public String() { }
public static double ComputeVariance(double[] v) { double r = double.NaN; if (v != null && v.Length >= 1) { double m = 0, s = 0; int n = v.Length; for (int i = 0; i < n; i++) s += v[i]; m = s / n; s = 0; for (int i = 0; i < n; i++) s += (v[i] - m) * (v[i] - m); r = (n == 1) ? 0 : s; } return r; }
public DescribeResizeResult DescribeResizeResult() { request = beforeClientExecution(request); return executeDescribeResize(request); }
public bool CheckDecision() { return passedThroughNonGreedyDecision; }
() => { return end; }
void ProcessCells(CellHandler handler) { int firstRow = range.FirstRow; int lastRow = range.LastRow; int firstColumn = range.FirstColumn; int lastColumn = range.LastColumn; int width = lastColumn - firstColumn + 1; SimpleCellWalkContext ctx = new SimpleCellWalkContext(); IRow currentRow = null; ICell currentCell = null; for (ctx.RowNumber = firstRow; ctx.RowNumber <= lastRow; ++ctx.RowNumber) { currentRow = sheet.GetRow(ctx.RowNumber); if (currentRow == null) { continue; } for (ctx.ColNumber = firstColumn; ctx.ColNumber <= lastColumn; ++ctx.ColNumber) { currentCell = currentRow.GetCell(ctx.ColNumber); if (currentCell == null) { continue; } if (IsEmpty(currentCell) && !TraverseEmptyCells) { continue; } int rowSize = ArithmeticUtils.MulAndCheck(ArithmeticUtils.SubAndCheck(ctx.RowNumber, firstRow), width); ctx.OrdinalNumber = ArithmeticUtils.AddAndCheck(rowSize, (ctx.ColNumber - firstColumn + 1)); handler.OnCell(currentCell, ctx); } } }
() -> { }
public int CompareTo(ScoreTerm other) { if (this.boost == other.boost) return other.bytes.get().CompareTo(this.bytes.get()); else return this.boost.CompareTo(other.boost); }
for(int i=0;i<len;i++){switch(s[i]){case FARSI_YEH:case YEH_BARREE:s[i]=YEH;goto case KEHEH;case KEHEH:s[i]=KAF;break;case HEH_YEH:case HEH_GOAL:s[i]=HEH;break;case HAMZA_ABOVE:len=Delete(s,i,len);i--;break;default:break;}}return len;
void writeShort(LittleEndianOutput out) {     out.writeShort(); }
public DiagnosticErrorListener(boolean exactOnly) {     this.exactOnly = exactOnly; }
public KeySchemaElement(string attributeName, KeyType keyType) { SetAttributeName(attributeName); SetKeyType(keyType.ToString()); }
public object GetAssignmentResult() { request = BeforeClientExecution(request); return ExecuteGetAssignment(request); }
public bool Contains(AnyObjectId id) { return findOffset(id) != -1; }
public class GroupingSearch {     private boolean allGroups;      public GroupingSearch setAllGroups(boolean allGroups) {         this.allGroups = allGroups;         return this;     } }
public void SomeMethod(string dimName, bool v) { lock (_lock) { DimConfig ft; if (!fieldTypes.TryGetValue(dimName, out ft)) { ft = new DimConfig(); fieldTypes[dimName] = ft; } ft.MultiValued = v; } }
int size = 0; foreach (char c in cells.Keys) { Cell e = at(c); if (e.cmd >= 0) { size++; } } return size;
public DeleteVoiceConnectorResult DeleteVoiceConnector(DeleteVoiceConnectorRequest request) => executeDeleteVoiceConnector(request = beforeClientExecution);
public DeleteLifecyclePolicyResult DeleteLifecyclePolicyResult(DeleteLifecyclePolicyRequest request) { request = beforeClientExecution; return executeDeleteLifecyclePolicy(request); }
