public void writeShort(LittleEndianOutput out) {     out.writeShort(field_1_vcenter); }
int srcDirIdx = 0; while (true) { if (src != null) { BlockList.addAll(0, tailBlkIdx, 0, BLOCK_SIZE); } if (++srcDirIdx >= someLimit) return; }
if (currentBlock == null) { currentBlock = new Block(blockSize); } if (blockSize == upto) { addBlock(currentBlock); currentBlock = null; upto = 0; }
public ObjectId getObjectId() {     return objectId; }
private DeleteDomainEntryResult executeDeleteDomainEntry(DeleteDomainEntryRequest request) { return beforeClientExecution(request); }
return (termOffsets != null ? ramBytesUsed.termOffsets() : 0) + (termsDictOffsets != null ? ramBytesUsed.termsDictOffsets() : 0);
public static string Decode(byte[] raw) { if (raw == null || raw.Length == 0) return ""; int encoding = RawParseUtils.GuessEncoding(raw, 0, raw.Length); return RawParseUtils.Decode(encoding, raw, 0, raw.Length); }
BATBlock bb = createEmptyBATBlock(); bb.SetNextBlock(FAT_SECTOR_BLOCK, POIFSConstants.END_OF_CHAIN); _property_table.SetNextBlock(bb); _bat_blocks.Add(bb); _header.SetBATCount(1); POIFSFileSystem.SetStartBlock(bb); return true;
Debug.Assert(address < upto); offset0 = upto; Debug.Assert(slice != null); ByteBlockPool.buffers[(address >> ByteBlockPool.BYTE_BLOCK_SHIFT) & ByteBlockPool.BYTE_BLOCK_MASK][address & ByteBlockPool.BYTE_BLOCK_MASK];
public class SubmoduleAddCommand { private string path; public SubmoduleAddCommand(string path) { this.path = path; } }
public ListIngestionsResult ExecuteListIngestions(ListIngestionsRequest request) { BeforeClientExecution(request); return request; }
public class QueryParserTokenManager {     public void SwitchTo(CharStream stream, int lexState) {         // implementation     } }
return executeGetShardIterator(request => beforeClientExecution(request));
public class ModifyStrategyRequest : BaseClass { public ModifyStrategyRequest() : base("vipaegis", "ModifyStrategy", "2016-11-11", "aegis") { MethodType = "POST"; } }
public bool Available() { lock (lockObj) { if (in == null) throw new IOException("InputStreamReader is closed"); try { return in.HasRemaining(); } catch (IOException) { return false; } } }
public EscherOptRecord getOptRecord() {     return _optRecord; }
public int Copylen(char[] buffer, int offset, int length) { if (buffer == null) throw new ArgumentNullException(nameof(buffer)); if (offset < 0 || length < 0 || buffer.Length - offset < length) throw new ArgumentException("Invalid offset or length"); int copyCount = Math.Min(length, count); for (int i = 0; i < copyCount; i++) buffer[offset + i] = this[pos + i]; pos += copyCount; copylen += copyCount; return copyCount; }
public class OpenNLPSentenceBreakIterator { private NLPSentenceDetectorOp sentenceOp; public OpenNLPSentenceBreakIterator() { sentenceOp = new NLPSentenceDetectorOp(this); } }
string write(object str) => str != null ? str.ToString() : null;
public class NotImplementedFunctionException : Exception { private string functionName; public NotImplementedFunctionException(string functionName, Exception cause) : base(functionName, cause) { this.functionName = functionName; } }
public V someMethod() {     return super.nextEntry().getValue(); }
public void readInternal(byte[] b, int offset, int len) { if (useBuffer) { int available = bufferLength - bufferPosition; if (available < len) { bufferStart += bufferPosition; int bytesRead = readInternal(buffer, 0, bufferSize); if (bytesRead == -1) throw new EndOfStreamException(this + "read past EOF: " + len); bufferPosition = 0; bufferLength = bytesRead; available = bufferLength; } if (available < len) throw new EndOfStreamException(this + "read past EOF: " + len); Buffer.BlockCopy(buffer, bufferPosition, b, offset, len); bufferPosition += len; } else { int totalRead = 0; while (totalRead < len) { int bytesRead = readInternal(b, offset + totalRead, len - totalRead); if (bytesRead == -1) throw new EndOfStreamException(this + "read past EOF: " + len); totalRead += bytesRead; } } }
public TagQueueResult ExecuteTagQueue(TagQueueRequest request) { return BeforeClientExecution(request); }
void someMethod() {     throw new UnsupportedOperationException(); }
private Func<ModifyCacheSubnetGroupRequest, ModifyCacheSubnetGroupRequest> executeModifyCacheSubnetGroup = request => { BeforeClientExecution(request); return request; };
public void SetParams(string param) { string[] tokens = param.Split(','); if (tokens.Length > 0) language = tokens[0]; if (tokens.Length > 1) country = tokens[1]; if (tokens.Length > 2) variant = tokens[2]; }
public DeleteDocumentationVersionResult ExecuteDeleteDocumentationVersion(DeleteDocumentationVersionRequest request) { return BeforeClientExecution(request); }
public override bool Equals(object obj) { if (ReferenceEquals(this, obj)) return true; if (!(obj is FacetLabel)) return false; FacetLabel other = (FacetLabel)obj; if (components.Length != other.components.Length) return false; for (int i = components.Length - 1; i >= 0; --i) if (!components[i].Equals(other.components[i])) return false; return true; }
public GetInstanceAccessDetailsResult executeGetInstanceAccessDetails(GetInstanceAccessDetailsRequest request) {     return beforeClientExecution(request).execute(request); }
HSSFPolygon shape = new HSSFPolygon(this, anchor); shapes.Add(shape); shape.setAnchor(anchor); shape.setParent(this);
public string GetSheetname(int sheetIndex) => GetBoundSheetRec(sheetIndex).SheetName;
public GetDashboardResult ExecuteGetDashboard(GetDashboardRequest request) { return BeforeClientExecution(request); }
public AssociateSigninDelegateGroupsWithAccountResult executeAssociateSigninDelegateGroupsWithAccount(AssociateSigninDelegateGroupsWithAccountRequest request) { beforeClientExecution(request); return executeAssociateSigninDelegateGroupsWithAccount(request); }
for (int j = 0; j < mbr.getNumColumns(); j++) { BlankRecord br = new BlankRecord(); br.setRow(mbr.getRow()); br.setColumn(j + mbr.getFirstColumn()); br.setXFIndex(mbr.getXFAt(mbr.getRow(), j + mbr.getFirstColumn())); insertCell(br); }
public static string toString(string stringVar) { StringBuilder sb = new StringBuilder(); int apos = 0; while ((apos = stringVar.IndexOf("\\E", apos)) >= 0) { sb.Append(stringVar.Substring(0, apos)); sb.Append("\\E\\Q"); apos += 2; } sb.Append(stringVar); return sb.ToString(); }
public void SomeMethod(ByteBuffer buffer) { throw new NotSupportedException(); }
{ int _reserved2Byte = 0, _reserved1Short = 0, _reserved0Int = 0; object[] _arrayValues; int _nRows = 0, _nColumns = 0, nRows = 0, nColumns = 0; object[,] values2d = new object[nRows, nColumns]; _arrayValues = new object[_nRows * _nColumns]; for (int r = 0; r < nRows; r++) { for (int c = 0; c < nColumns; c++) { _arrayValues[getValueIndex(r, c)] = values2d[r, c]; } } }
public GetIceServerConfigResult executeGetIceServerConfig(GetIceServerConfigRequest request) {     return beforeClientExecution(request); }
public String someMethod() {     return getClass().getName() + "[" + getValueAsString() + "]"; }
public override string ToString() { return "ToChildBlockJoinQuery(" + parentQuery.ToString() + ")"; }
public void incrementAndGet() { refCount++; }
private UpdateConfigurationSetSendingEnabledResult ExecuteUpdateConfigurationSetSendingEnabled(UpdateConfigurationSetSendingEnabledRequest request) { return BeforeClientExecution(request); }
public int getXBATEntriesPerBlock() {     return INT_SIZE * LittleEndianConsts.getXBATEntriesPerBlock(); }
public void SomeMethod(TenPower pow10, int _multiplierShift, int _multiplicand, int _divisorShift, int _divisor) { if (pow10 != null) { var tp = TenPower.GetInstance(); tp.mulShift(Math.Abs(_multiplierShift), _multiplicand, _divisorShift, _divisor); } }
var b=new System.Text.StringBuilder();int l=components.Length;for(int i=0;i<l;i++){b.Append(components[i]);if(i<l-1)b.Append(System.IO.Path.DirectorySeparatorChar);}return b.ToString();
public InstanceProfileCredentialsProvider SetRoleName(string roleName) { fetcher.SetRoleName(roleName); return this; }
ProgressMonitor pm = progressMonitor;
void ParseEntry() { if (first) { ptr = 0; } if (!Eof()) { /* handle error */ } }
if (start >= previousIndex) throw new InvalidOperationException(); return element;
public MyClass newPrefix(String newPrefix) {     return new MyClass(newPrefix + this); }
int FindIndex(int value){for(int i=0;i<mSize;i++)if(value==mValues[i])return i-1;return 0;}
public List<CharsRef> deduped = new List<CharsRef>(); public CharArraySet stems = new CharArraySet(ignoreCase, 8); foreach (char[] s in terms) { if (!stems.Contains(s)) { stems.Add(s); deduped.Add(new CharsRef(s)); } } return deduped;
public GetGatewayResponsesResult ExecuteGetGatewayResponses(GetGatewayResponsesRequest request) { BeforeClientExecution(request); return request; }
pos = currentBlockUpto = currentBlock = currentBlockIndex; blocks[currentBlockIndex]; (pos >> blockBits) & blockMask;
int Method(int n){int s=0;int ptr=0;while(available){s+=ptr;ptr=Math.Min(Math.Max(0,n),ptr+n);}return s;}
var bootstrapActionConfig = new BootstrapActionConfig(); setBootstrapActionConfig(bootstrapActionConfig);
// Translated C# code (single line as requested) out.Write((byte)(field_5_hasMultibyte ? 0x00 : 0x01)); out.Write(field_1_row); out.Write(field_2_col); out.Write(field_3_flags); out.Write(field_4_shapeid); if (field_7_padding != null) out.Write(field_7_padding.intValue); StringUtil.PutCompressedUnicode(out, field_6_author); StringUtil.PutUnicodeLE(out, field_6_author);
public int LastIndexOf(string str, int count) { return str.LastIndexOf((char)count); }
public bool AddLastImpl(E obj) { return true; }
public void UnsetSection(string section, string subsection, ConfigSnapshot src) { ConfigSnapshot res; do { res = state; } while (!ReferenceEquals(Interlocked.CompareExchange(ref state, src, res), res)); }
public string TagName() { return tagName; }
public void add(SubRecord element, int index) {     subrecords.add(index, element); }
delegate (object o) { lock (mutex) { return remove(o); } }
return new DoubleMetaphoneFilter(inject, maxCodeLength, input);
public int getInCoreLength() {     return inCoreLength; }
bool newValue = value;
newSource = oldSource = new ContentSource(); var pair = new Pair<ContentSource, ContentSource>(newSource, oldSource);
if (i <= count) return entries[i]; else throw new IndexOutOfRangeException();
public class CreateRepoRequest : RpcRequest { public CreateRepoRequest() : base("cr", "CreateRepo", "2016-06-07", "cr") { MethodType = MethodType.PUT; UriPattern = "/repos"; } }
public boolean deltaBaseAsOffset() {     return deltaBaseAsOffset; }
if (modCount != expectedModCount) throw new ConcurrentModificationException(); if (lastLink == null) throw new InvalidOperationException(); previous = lastLink; lastLink = lastLink.next; expectedModCount = modCount; pos++;
public MergeShardsResult ExecuteMergeShards(MergeShardsRequest request) { request = BeforeClientExecution(request); return request; }
private AllocateHostedConnectionResult ExecuteAllocateHostedConnection(AllocateHostedConnectionRequest request) { request = BeforeClientExecution(request); return request; }
void start() { return; }
public static Query GetTerms(Query query, bool flag) { return query; }
throw new ReadOnlyBufferException();
public void EncodeBlock(byte[] blocks, int blocksOffset, byte[] values, int valuesOffset, int iterations) { for (int i = 0; i < iterations; i++) { int byte0 = values[valuesOffset++]; int byte1 = values[valuesOffset++]; int byte2 = values[valuesOffset++]; blocks[blocksOffset++] = (byte)((byte0 >> 2) & 0x3F); blocks[blocksOffset++] = (byte)(((byte0 & 0x03) << 4) | (byte1 >> 4)); blocks[blocksOffset++] = (byte)(((byte1 & 0x0F) << 2) | (byte2 >> 6)); blocks[blocksOffset++] = (byte)(byte2 & 0x3F); } }
public static string ProcessGitPath(string s) { if (string.IsNullOrEmpty(s) || !s.StartsWith("file://")) throw new ArgumentException("Invalid path"); string[] elements = s.Split('/'); if (elements.Length < 2 || elements[^1] != ".git") throw new ArgumentException("Invalid elements"); string result = string.Join("/", elements); if (result.EndsWith("/")) result = result[..^1]; return result; }
public DescribeNotebookInstanceLifecycleConfigResult ExecuteDescribeNotebookInstanceLifecycleConfig(DescribeNotebookInstanceLifecycleConfigRequest request) { BeforeClientExecution(request); return request; }
public String getAccessKeySecret() {     return this.accessKeySecret; }
public CreateVpnConnectionResult ExecuteCreateVpnConnection(CreateVpnConnectionRequest request) => BeforeClientExecution(request);
public DescribeVoicesResult ExecuteDescribeVoices(DescribeVoicesRequest request) { beforeClientExecution(request); return execute(request); }
public ListMonitoringExecutionsResult ExecuteListMonitoringExecutions(ListMonitoringExecutionsRequest request) => BeforeClientExecution(request);
public class DescribeJobRequest { public string JobId { get; set; } public string VaultName { get; set; } }
public EscherRecord Get(int index) { return escherRecords[index]; }
Func<GetApisRequest, GetApisResult> executeGetApis = request => this.beforeClientExecution(request);
private DeleteSmsChannelResult executeDeleteSmsChannel(DeleteSmsChannelRequest request) {     request = beforeClientExecution(request);     // execute the request and obtain result     return result; }
public TrackingRefUpdate getTrackingRefUpdate() {     return trackingRefUpdate; }
void printBoolean(boolean b) {     System.out.print(String.valueOf(b)); }
public QueryNode GetChild() { return (QueryNode)getChildren()[0]; }
workdirTreeIndex = ((NotIgnoredFilter)this).index.workdirTreeIndex;
field_1_formatFlags = reader.ReadInt16();
public class GetThumbnailRequest : SomeSuperClass { public GetThumbnailRequest() : base("cloudphoto", "GetThumbnail", "2017-07-11", "CloudPhoto") { ProtocolType = HTTPS.ProtocolType; } }
private DescribeTransitGatewayVpcAttachmentsResult ExecuteDescribeTransitGatewayVpcAttachments(DescribeTransitGatewayVpcAttachmentsRequest request) { BeforeClientExecution(request); return request; }
private PutVoiceConnectorStreamingConfigurationResult ExecutePutVoiceConnectorStreamingConfiguration(PutVoiceConnectorStreamingConfigurationRequest request) => BeforeClientExecution(request);
public static string prefixToOrdRange(int dim) { return OrdRange.get(dim); }
public string SomeMethod(string symbol, int startIndex) { if (startIndex < 0 || startIndex >= GetInputStream().Size) return string.Format(CultureInfo.CurrentCulture, "%s('%s')", GetType().Name, symbol); var inputStream = GetInputStream(); var interval = new Interval(startIndex, inputStream.Index); var text = Utils.GetText(interval, inputStream); return string.Format(CultureInfo.CurrentCulture, "%s('%s')", GetType().Name, escapeWhitespace(text)); }
public E PeekFirstImpl() { return peekFirstImpl; }
Func<CreateWorkspacesRequest, CreateWorkspacesResult> executeCreateWorkspaces = request => beforeClientExecution(request);
public NumberFormatIndexRecord Copy() { return new NumberFormatIndexRecord(); }
private DescribeRepositoriesResult ExecuteDescribeRepositories(DescribeRepositoriesRequest request) => BeforeClientExecution(request);
mSize = 0; mKeys = mValues = null; initialCapacity = ArrayUtils.idealIntArraySize(initialCapacity); mKeys = new int[initialCapacity]; mValues = new int[initialCapacity];
return new HyphenatedWordsFilter(input);
public CreateDistributionWithTagsResult ExecuteCreateDistributionWithTags(CreateDistributionWithTagsRequest request) { request = BeforeClientExecution(request); return request; }
new FileStream(fileName, mode == "r" ? FileMode.Open : FileMode.OpenOrCreate, mode == "r" ? FileAccess.Read : FileAccess.ReadWrite);
return BeforeClientExecution(request);
public static string WriteHex(int value) { return new StringBuilder().Append(Convert.ToString(value, 16)).ToString(); }
private UpdateDistributionResult executeUpdateDistribution(UpdateDistributionRequest request) => beforeClientExecution(request);
return index >= 0 && index < _palette.Length ? (_palette[index] ?? HSSFColorPredefined.AUTOMATIC.GetColor()) : HSSFColorPredefined.AUTOMATIC.GetColor();
throw new NotImplementedFunctionException(_functionName, operands, srcRow, srcCol);
out.WriteShort(field_2_sheet_table_index); out.WriteShort(field_1_number_crn_records);
public DescribeDBEngineVersionsResult describeDBEngineVersions() {     return new DescribeDBEngineVersionsRequest(); }
new FormatRun(this._fontIndex, this._character);
public static char[] ConvertChars(char[] chars, int offset, int length) { char[] result = new char[2 * length]; int resultIndex = 0; for (int i = offset; i < offset + length; i++) { int ch = chars[i]; result[resultIndex++] = (char)(ch >> 8); result[resultIndex++] = (char)(ch & 0xFF); } return result; }
public UploadArchiveResult ExecuteUploadArchive(UploadArchiveRequest request) { return BeforeClientExecution(request); }
List<Token> getHiddenTokensToLeft(int tokenIndex) {     return tokenList.getHiddenTokensToLeft(tokenIndex - 1); }
public override bool Equals(object obj) { if (this == obj) return true; if (obj == null || GetType() != obj.GetType()) return false; AutomatonQuery other = (AutomatonQuery)obj; if (term == null ? other.term != null : !term.Equals(other.term)) return false; if (compiled == null ? other.compiled != null : !compiled.Equals(other.compiled)) return false; return base.Equals(obj); }
public SpanQuery TranslateQueries(SpanQuery[] spanQueries, Dictionary<SpanQuery, float> weightBySpanQuery) { var spanOrQuery = new SpanOrQuery(); foreach (var sq in spanQueries) { var boost = weightBySpanQuery.TryGetValue(sq, out var b) ? b : 1.0f; spanOrQuery.Add(boost != 1.0f ? new SpanBoostQuery(sq, boost) : sq); } return spanOrQuery; }
return new StashCreateCommand(repo);
public FieldInfo GetByName(string fieldName) { return typeof(SomeClass).GetField(fieldName); }
return BeforeClientExecution(request).ExecuteDescribeEventSource(request);
private GetDocumentAnalysisResult ExecuteGetDocumentAnalysis(GetDocumentAnalysisRequest request) => BeforeClientExecution(request);
var executeCancelUpdateStack = (CancelUpdateStackRequest request) => { beforeClientExecution(request); return request; };
BeforeClientExecution(request); return ExecuteModifyLoadBalancerAttributes(request);
public SetInstanceProtectionResult ExecuteSetInstanceProtection(SetInstanceProtectionRequest request) { return request.BeforeClientExecution(request); }
public ModifyDBProxyResult ExecuteModifyDBProxy(ModifyDBProxyRequest request) { return BeforeClientExecution(request); }
// Translated C# code (speculative based on common patterns): public class SomeClass {     private int[] posLengths = new int[0];     private int[] endOffsets = new int[0];     private char[][] outputs = new char[0][];     private int count;      public void AddData(int posLength, int endOffset, char[] output) {         if (count >= posLengths.Length) {             int newSize = ArrayUtil.Oversize(count + 1, 4);             Array.Resize(ref posLengths, newSize);             Array.Resize(ref endOffsets, newSize);             Array.Resize(ref outputs, newSize);         }         posLengths[count] = posLength;         endOffsets[count] = endOffset;         outputs[count++] = output;     } }  public static class ArrayUtil {     public static int Oversize(int minSize, int bytePerElement) {         int newSize = 1;         while (newSize < minSize) newSize <<= 1;         return newSize;     } }
public class FetchLibrariesRequest : BaseClass { public FetchLibrariesRequest() : base("cloudphoto", "FetchLibraries", "2017-07-11", "CloudPhoto", ProtocolType.HTTPS) {} }
public bool Exists(object obj) { return System.IO.File.Exists(obj.ToString()); }
public class FilterOutputStream : Stream { protected Stream out; public FilterOutputStream(Stream out) { this.out = out; } }
; ; ScaleClusterRequest setMethod ) "/clusters/[ClusterId]" ( ; ) "csk" , "ScaleCluster" , "2015-12-15" , "CS" ( base { ) ( ) ( PUT , MethodType
return helper.CreateTimeConstraint(operatorType, formula1, formula2);
private ListObjectParentPathsResult ExecuteListObjectParentPaths(ListObjectParentPathsRequest request) => BeforeClientExecution(request);
return this.Execute<DescribeCacheSubnetGroupsRequest, DescribeCacheSubnetGroupsResult>(request, new Amazon.Runtime.Internal.PreExecutionContextCallback(this.BeforeClientExecution));
void setShortBoolean(bool flag, short field_5_options) { this.field_5_options = field_5_options; sharedFormula.flag = flag; }
public bool reuseObjects() { return true; }
ErrorNode t = new ErrorNodeImpl(badToken); t.SetParent(this); AddAnyChild(t); return t;
if (args.Count > 0) throw new ArgumentException("Unknown parameters: " + args);
public EventSubscription ExecuteRemoveSourceIdentifierFromSubscription(RemoveSourceIdentifierFromSubscriptionRequest request) { return BeforeClientExecution(request); }
public static TokenFilterFactory newInstance(Dictionary<string, string> args, string name) { return new TokenFilterFactory(loader, args, name); }
public class AddAlbumPhotosRequest : SomeBaseClass { public AddAlbumPhotosRequest() : base("cloudphoto", "AddAlbumPhotos", "2017-07-11", "CloudPhoto") => ProtocolType = ProtocolType.HTTPS; }
public GetThreatIntelSetResult ExecuteGetThreatIntelSet(GetThreatIntelSetRequest request) => BeforeClientExecution(request);
return new Binary(((YourClass)Clone()).a, ((YourClass)Clone()).b);
return o instanceof ArmenianStemmer;
public bool HasArray() { return protectedHasArray; }
(request) => { beforeClientExecution(request); return new UpdateContributorInsightsResult(); }
// Invalid Java input; no meaningful C# translation possible
public class SolrSynonymParser : BaseClass { public SolrSynonymParser(Analyzer analyzer, bool dedup, bool expand) : base(analyzer, dedup, expand) { this.expand = expand; } }
new RequestSpotInstancesResult((request) => { var executeRequestSpotInstances = request; return beforeClientExecution(request); });
public ObjectData findObjectRecord() {     return getObjectData()[someIndex]; }
public GetContactAttributesResult ExecuteGetContactAttributes(GetContactAttributesRequest request) { return ExecuteGetContactAttributes(BeforeClientExecution(request)); }
return GetKey() + ": " + GetValue();
private ListTextTranslationJobsResult ExecuteListTextTranslationJobs(ListTextTranslationJobsRequest request) { request = BeforeClientExecution(request); return request; }
public GetContactMethodsResult executeGetContactMethods(GetContactMethodsRequest request) {     return beforeClientExecution(request); }
public static FunctionMetadata GetFunctionByNameInternal(string name) { FunctionMetadata fd; if ((fd = GetIndex()) == null) { if ((fd = GetInstance(name)) == null) { return null; } } return fd; }
public DescribeAnomalyDetectorsResult ExecuteDescribeAnomalyDetectors(DescribeAnomalyDetectorsRequest request) => Execute<DescribeAnomalyDetectorsResult>(BeforeClientExecution(request));
public static string InsertId(bool flag, ObjectId changeId, string message) { return changeId.ToString(); }
if (sz < 0) throw new MissingObjectException(); if (typeHint == OBJ_ANY) { copy.ObjectId = objectId; throw new MissingObjectException(JGitText.Instance.UnknownObjectType2); }
} { ) (  ImportInstallationMediaResult ; return ; request ImportInstallationMediaRequest executeImportInstallationMedia = request ) request ( beforeClientExecution ) request (
public PutLifecycleEventHookExecutionStatusResult ExecutePutLifecycleEventHookExecutionStatus(PutLifecycleEventHookExecutionStatusRequest request) => BeforeClientExecution(request);
double d = in.ReadDouble();
private GetFieldLevelEncryptionConfigResult ExecuteGetFieldLevelEncryptionConfig(GetFieldLevelEncryptionConfigRequest request) { request = BeforeClientExecution(request); return request; }
private DescribeDetectorResult executeDescribeDetector(DescribeDetectorRequest request) {     return beforeClientExecution(request); }
return ExecuteReportInstanceStatus(request, BeforeClientExecution(request));
public DeleteAlarmResult ExecuteDeleteAlarm(DeleteAlarmRequest request) { return BeforeClientExecution(request); }
return new PortugueseStemFilter(input);
FtCblsSubRecord = new Reserved[ENCODED_SIZE];
@Override public synchronized boolean remove(Object object) {     synchronized (mutex) {         return c.remove(object);     } }
public GetDedicatedIpResult ExecuteGetDedicatedIp(GetDedicatedIpRequest request) { BeforeClientExecution(request); return Execute(request); }
return " >= _p" + precedence;
public ListStreamProcessorsResult ExecuteListStreamProcessors(ListStreamProcessorsRequest request) => beforeClientExecution(request);
public class DeleteLoadBalancerPolicyRequest { public DeleteLoadBalancerPolicyRequest(string policyName, string loadBalancerName) { SetPolicyName(policyName); SetLoadBalancerName(loadBalancerName); } public void SetPolicyName(string policyName) => _policyName = policyName; public void SetLoadBalancerName(string loadBalancerName) => _loadBalancerName = loadBalancerName; private string _policyName; private string _loadBalancerName; }
public class WindowProtectRecord {     private int _options;     public WindowProtectRecord(int options) {         this._options = options;     } }
char[] data = new char[bufferSize]; int n = 0;
public GetOperationsResult ExecuteGetOperations(GetOperationsRequest request) { return BeforeClientExecution(request); }
void encodeInt32(int w1, byte[] b, int o) { EncodeInt32(w1, b, o); EncodeInt32(w2, b, o + 4); EncodeInt32(w3, b, o + 8); EncodeInt32(w4, b, o + 12); EncodeInt32(w5, b, o + 16); }
field_9_tab_width_ratio = reader.ReadInt16(); field_8_num_selected_tabs = reader.ReadInt16(); field_7_first_visible_tab = reader.ReadInt16(); field_6_active_sheet = reader.ReadInt16(); field_5_options = reader.ReadInt16(); field_4_height = reader.ReadInt16(); field_3_width = reader.ReadInt16(); field_2_v_hold = reader.ReadInt16(); field_1_h_hold = reader.ReadInt16();
public StopWorkspacesResult ExecuteStopWorkspaces(StopWorkspacesRequest request) { request = BeforeClientExecution(request); return request.ExecuteStopWorkspaces(); }
public void MyMethod() { bool isOpen = false; FileStream fos = null; try { fos = new FileStream("file.txt", FileMode.OpenOrCreate); fos.SetLength(0); isOpen = true; } finally { if (isOpen) fos.Close(); } }
public DescribeMatchmakingRuleSetsResult executeDescribeMatchmakingRuleSets(DescribeMatchmakingRuleSetsRequest request) {     beforeClientExecution(request);     return request; }
public String[] someMethod(int len, int off, int surface, int wordId) {     return null; }
public String getPathStr() {     return pathStr; }
public static double someMethod(double[] v) { double r = 0.0; if (v == null) return r; int n = v.Length; if (n == 0) return r; double s = 0.0; for (int i = 0; i < n; i++) s += v[i]; double m = s / n; s = 0.0; for (int i = 0; i < n; i++) s += (v[i] - m) * (v[i] - m); r = Math.Sqrt(s / n); return r; }
public DescribeResizeResult ExecuteDescribeResize(DescribeResizeRequest request) { BeforeClientExecution(request); return result; }
public bool PassedThroughNonGreedyDecision() { return false; }
} { ) (  ; return end ) 0 (
// Translated C# code (single line, as requested) for (int row = firstRow; row <= lastRow; row++) { for (int col = firstColumn; col <= lastColumn; col++) { if (traverseEmptyCells && sheet.GetRow(row) == null) continue; var cell = sheet.GetRow(row)?.GetCell(col); if (cell == null) continue; ctx.RowNumber = row; ctx.ColNumber = col; ctx.OrdinalNumber = ArithmeticUtils.MulAndCheck(ctx.RowNumber, width) + ArithmeticUtils.SubAndCheck(ctx.ColNumber, firstColumn); handler.OnCell(ctx, cell); } }
public int getPosition() {     return pos; }
public int CompareTo(ScoreTerm other) { int cmp = this.boost.CompareTo(other.boost); if (cmp == 0) { return CompareBytes(this.bytes, other.bytes); } return cmp; }
public static int ProcessArabicChars(ref char[] s, int len) { for (int i = 0; i < len; i++) { switch (s[i]) { case HAMZA_ABOVE: case HEH_GOAL: case HEH_YEH: case KEHEH: case YEH_BARREE: case FARSI_YEH: for (int j = i; j < len - 1; j++) s[j] = s[j + 1]; len--; i--; break; } } return len; }
public void writeShort(LittleEndianOutput @out, int _options)
public DiagnosticErrorListener(bool exactOnly) { this.exactOnly = exactOnly; }
public class KeySchemaElement { private string attributeName; private KeyType keyType; public void SetAttributeName(string attributeName) { this.attributeName = attributeName; } public void SetKeyType(KeyType keyType) { this.keyType = keyType; } public override string ToString() { return keyType.ToString(); } }
private GetAssignmentResult executeGetAssignment(GetAssignmentRequest request) {     return beforeClientExecution(request); }
boolean someMethod(int id) {     return id != AnyObjectId - findOffset; }
public class GroupingSearch { private bool allGroups; public GroupingSearch SetAllGroups() { allGroups = true; return this; } }
// Invalid Java code cannot be translated meaningfully
for (int i = 0; i < size; i++) { if (iterator.MoveNext()) { var c = cells.ElementAt(i); cmd.e = c; } }
public DeleteVoiceConnectorResult ExecuteDeleteVoiceConnector(DeleteVoiceConnectorRequest request) { return BeforeClientExecution(request); }
private DeleteLifecyclePolicyResult ExecuteDeleteLifecyclePolicy(DeleteLifecyclePolicyRequest request) => BeforeClientExecution(request);
