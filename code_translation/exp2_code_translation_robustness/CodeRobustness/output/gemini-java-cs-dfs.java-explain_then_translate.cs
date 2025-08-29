public void WriteVcenter(System.IO.BinaryWriter writer) { writer.Write(this.Field1Vcenter); }
public void AddAll<T>(BlockList<T> src) { if (src.Size == 0) return; for (int srcDirIdx = 0; srcDirIdx < src.TailDirIdx; srcDirIdx++) AddAll(src.Directory[srcDirIdx], 0, BLOCK_SIZE); if (src.TailBlkIdx != 0) AddAll(src.TailBlock, 0, src.TailBlkIdx); }
public void Add(byte b) { if (upto == blockSize) { if (currentBlock != null) { AddBlock(currentBlock); } currentBlock = new byte[blockSize]; upto = 0; } currentBlock[upto++] = b; }
public ObjectId ObjectId => objectId;
DeleteDomainEntryResponse response = client.DeleteDomainEntry(new DeleteDomainEntryRequest { DomainName = "example.com" });
return (termOffsets?.RamBytesUsed() ?? 0) + (termsDictOffsets?.RamBytesUsed() ?? 0);
public sealed string GetDecodedMessage() { byte[] raw = buffer; int msgB = RawParseUtils.TagMessage(raw, 0); if (msgB < 0) { return ""; } return RawParseUtils.Decode(RawParseUtils.GuessEncoding(raw, msgB, raw.Length)); }
if (true) { _header.BATCount = 1; _header.SetBATArray(new int[] { 1 }); BATBlock bb = BATBlock.CreateEmptyBATBlock(bigBlockSize, false); bb.SetOurBlockIndex(1); _bat_blocks.Add(bb); bb.SetNextBlock(0, POIFSConstants.END_OF_CHAIN); bb.SetNextBlock(1, POIFSConstants.FAT_SECTOR_BLOCK); _property_table.StartBlock = 0; }
// This code is likely part of a method body, for example: // void setAddress(int address) {     slice = pool.buffers[address >> ByteBlockPool.BYTE_BLOCK_SHIFT];     assert slice != null;     upto = address & ByteBlockPool.BYTE_BLOCK_MASK;     offset0 = address;     assert upto < slice.length; // }
} ; this return ; path = path . this { ) path string (  SubmoduleAddCommand
ListIngestionsResponse response = await client.ListIngestionsAsync(new ListIngestionsRequest());
} ; ) lexState ( SwitchTo ; ) stream ( { ) lexState , stream CharStream ( QueryParserTokenManager
} ; ) request ( executeGetShardIterator return ; ) request ( beforeClientExecution = request { ) request GetShardIteratorRequest ( GetShardIteratorResult
await apiClient.PostAsync("vipaegis", "ModifyStrategy", "2016-11-11", "aegis", new ModifyStrategyRequest());
// This method is likely part of a class like InputStreamReader boolean ready() throws IOException {     synchronized (lock) {         if (in == null) {             throw new IOException("InputStreamReader is closed");         }         try {             // The catch block is missing its exception type, but it's implied to be IOException.             return bytes.hasRemaining() || in.available() > 0;         } catch (Exception e) {              return false;         }     } }
public EscherOptRecord getOptRecord() {     return _optRecord; }
public int Read(char[] buffer, int offset, int count) { lock (this) { if (buffer == null) throw new System.ArgumentNullException(nameof(buffer)); if (offset < 0 || count < 0 || buffer.Length - offset < count) throw new System.ArgumentOutOfRangeException("offset or count"); if (count == 0) return 0; int charsToCopy = System.Math.Min(_length - _pos, count); if (charsToCopy <= 0) return 0; _source.CopyTo(_pos, buffer, offset, charsToCopy); _pos += charsToCopy; return charsToCopy; } }
NLPSentenceDetectorOp sentenceOp = new NLPSentenceDetectorOp(new OpenNLPSentenceBreakIterator());
// The provided Java code is invalid and cannot be translated.
throw new System.NotImplementedException();
} ; ) ( GetValue . ) ( nextEntry . base return { ) (  V
public void Read(byte[] b, int offset, int len, bool useBuffer){int available = bufferLength - bufferPosition;if (len <= available){if (len > 0) Array.Copy(buffer, bufferPosition, b, offset, len);bufferPosition += len;}else{if (available > 0){Array.Copy(buffer, bufferPosition, b, offset, available);offset += available;len -= available;bufferPosition += available;}if (useBuffer && len < bufferSize){Refill();if (bufferLength < len){Array.Copy(buffer, 0, b, offset, bufferLength);throw new System.IO.EndOfStreamException("read past EOF: " + this);}else{Array.Copy(buffer, 0, b, offset, len);bufferPosition = len;}}else{long after = bufferStart + bufferPosition + len;if (after > length){throw new System.IO.EndOfStreamException("read past EOF: " + this);}ReadInternal(b, offset, len);bufferStart = after;bufferPosition = 0;bufferLength = 0;}}}
} ; ) request ( executeTagQueue return ; ) request ( beforeClientExecution = request { ) request TagQueueRequest (  TagQueueResult
void MethodName() { throw new NotSupportedException(); }
return client.ModifyCacheSubnetGroup(new ModifyCacheSubnetGroupRequest());
public override void SetParams(string parameters) { base.SetParams(parameters); language = country = variant = ""; var tokens = parameters.Split(','); if (tokens.Length > 0) language = tokens[0]; if (tokens.Length > 1) country = tokens[1]; if (tokens.Length > 2) variant = tokens[2]; }
var response = await client.DeleteDocumentationVersionAsync(new DeleteDocumentationVersionRequest());
public override bool Equals(object obj) { if (obj is not FacetLabel other) { return false; } if (length != other.length) { return false; } for (int i = length - 1; i >= 0; i--) { if (!components[i].Equals(other.components[i])) { return false; } } return true; }
GetInstanceAccessDetailsResponse response = await client.GetInstanceAccessDetailsAsync(request);
// Assumed method signature public HSSFPolygon createPolygon(HSSFChildAnchor anchor) {     HSSFPolygon shape = new HSSFPolygon(this, anchor);     shape.setParent(this);     shape.setAnchor(anchor);     shapes.add(shape);     onCreate(shape);     return shape; }
public string GetSheetName(int sheetIndex) => GetBoundSheetRec(sheetIndex).GetSheetName();
GetDashboardResponse response = await cloudWatchClient.GetDashboardAsync(new GetDashboardRequest { DashboardName = "your-dashboard-name" });
AssociateSigninDelegateGroupsWithAccountResponse response = await client.AssociateSigninDelegateGroupsWithAccountAsync(request);
void MulBlankRecord(MulBlankRecord mbr) {     for (int j = 0; j < mbr.getNumColumns(); j++) {         BlankRecord br = new BlankRecord();         br.setColumn(mbr.getFirstColumn() + j);         br.setRow(mbr.getRow());         br.setXFIndex(mbr.getXFAt(j));         insertCell(br);     } }
public static string Quote(string s) { var sb = new System.Text.StringBuilder("\\Q"); int c = 0; int i; while ((i = s.IndexOf("\\E", c)) != -1) { sb.Append(s.Substring(c, i - c)).Append("\\E\\\\E\\Q"); c = i + 2; } return sb.Append(s.Substring(c)).Append("\\E").ToString(); }
throw new NotSupportedException();
public ArrayPtg(object[][] values2d) { int nColumns = values2d[0].Length; int nRows = values2d.Length; _nColumns = nColumns; _nRows = nRows; object[] vv = new object[_nRows * _nColumns]; for (int r = 0; r < nRows; ++r) { object[] rowData = values2d[r]; for (int c = 0; c < nColumns; ++c) { vv[GetValueIndex(c, r)] = rowData[c]; } } _arrayValues = vv; _reserved0Int = 0; _reserved1Short = 0; _reserved2Byte = 0; }
return ExecuteGetIceServerConfig(new GetIceServerConfigRequest { BeforeClientExecution = request => { } });
@Override public String toString() {     return getClass().getName() + "[" + getValueAsString() + "]"; }
public string ToString(string field) { return $"ToChildBlockJoinQuery ({parentQuery.ToString()})"; }
} ; ) refCount ref ( Increment . Interlocked { ) ( void public
UpdateConfigurationSetSendingEnabledResponse response = await sesClient.UpdateConfigurationSetSendingEnabledAsync(new UpdateConfigurationSetSendingEnabledRequest { ConfigurationSetName = "config-set-name", Enabled = true });
;
}};) _multiplierShift.tp, _multiplicand.tp(mulShift { else };) _divisorShift.tp, _divisor.tp(mulShift {) 0 < pow10(if ;) ) pow10(Math.Abs(getInstance.TenPower = tp TenPower {) pow10( void
public override string ToString() { var b = new System.Text.StringBuilder(); int l = Length(); b.Append(System.IO.Path.DirectorySeparatorChar); for (int i = 0; i < l; i++) { b.Append(GetComponent(i)); if (i < l - 1) { b.Append(System.IO.Path.DirectorySeparatorChar); } } return b.ToString(); }
public EcsMetadataServiceCredentialsFetcher SetRoleName(string roleName) { this.RoleName = roleName; return this; }
void setProgressMonitor(ProgressMonitor pm) {     this.progressMonitor = pm; }
if (!first) { ptr = 0; } if (!eof) { parseEntry(); }
if (iterator.PreviousIndex() >= start) { return iterator.Previous(); } throw new InvalidOperationException();
public string NewPrefix => this.newPrefix;
for (int i = 0; i < mSize; i++) { if (mValues[i] == value) return i; } return -1;
if (stems.Count < 2) { return stems; } var terms = new CharArraySet(8, dictionary.ignoreCase); var deduped = new List<CharsRef>(); foreach (var s in stems) { if (!terms.Contains(s)) { deduped.Add(s); terms.Add(s); } } return deduped;
return ExecuteGetGatewayResponses(request);
currentBlockIndex = pos >> blockBits; currentBlock = blocks[currentBlockIndex]; currentBlockUpto = pos & blockMask;
s = Math.Min(available(), Math.Max(0, n)); ptr += s; return s;
BootstrapActionConfig = new BootstrapActionConfig(bootstrapActionDetail);
public void Serialize(BinaryWriter writer) { writer.Write((short)field_1_row); writer.Write((short)field_2_col); writer.Write((short)field_3_flags); writer.Write((short)field_4_shapeid); writer.Write((short)field_6_author.Length); writer.Write((byte)(field_5_hasMultibyte ? 0x01 : 0x00)); if (field_5_hasMultibyte) { StringUtil.PutUnicodeLE(field_6_author, writer); } else { StringUtil.PutCompressedUnicode(field_6_author, writer); } if (field_7_padding != null) { writer.Write((byte)field_7_padding.Value); } }
return string.LastIndexOf(String, count);
private bool AddLastImpl(E item) { throw new System.NotImplementedException(); }
public void UnsetSection(string section, string subsection) { ConfigSnapshot current, updated; do { current = _state; updated = current.WithSectionRemoved(section, subsection); } while (System.Threading.Interlocked.CompareExchange(ref _state, updated, current) != current); }
public string TagName => tagName;
} ; ) element , index ( add . subrecords { ) element SubRecord , index (  void
bool TranslatedMethod(object o) { lock (mutex) { return delegate.Remove(o); } }
return new DoubleMetaphoneFilter(input, maxCodeLength, inject);
return inCoreLength;
public void setValue(boolean newValue) {     this.value = newValue; }
public Pair(ContentSource oldSource, ContentSource newSource) { this.oldSource = oldSource; this.newSource = newSource; }
{ if (count <= i) throw new IndexOutOfRangeException(); return entries[i]; }
public CreateRepo() : base(HttpMethod.Put, "/repos", "cr", "CreateRepo", "2016-06-07", typeof(CreateRepoRequest)) { }
bool DeltaBaseAsOffset() => deltaBaseAsOffset;
void remove() {     // 1. Check for concurrent modification     if (expectedModCount == list.modCount) {         // 2. Check if remove() is in a valid state         if (lastLink != null) {             // 3. Unlink the node from the list             Link<E> next = lastLink.next;             Link<E> previous = lastLink.previous;             previous.next = next;             next.previous = previous;              // 4. Adjust iterator position if necessary             if (lastLink == link) {                 pos--;             }             link = previous;              // 5. Update state after removal             lastLink = null; // Prevent a second remove() call             expectedModCount++;             list.size--;             list.modCount++;         } else {             // Thrown if remove() is called without a preceding next() or previous()             throw new IllegalStateException();         }     } else {         // Thrown if the list was modified elsewhere         throw new ConcurrentModificationException();     } }
return await client.MergeShardsAsync(request);
AllocateHostedConnectionResponse response = await client.AllocateHostedConnectionAsync(request);
} ; start return { ) (
// Assumed supporting classes for context class Query { /* ... */ } class WeightedTerm { /* ... */ }  public class SearchUtils {     public static final WeightedTerm[] getTerms(Query query) {         // The implementation is missing, so we'll assume it returns an empty array.         return new WeightedTerm[0];     } }
throw new NotSupportedException();
}} ; 63 & byte2 = ] ++ valuesOffset [ values ; ) 6 >> byte2 ( | ) 2 << ) 15 & byte1 ( ( = ] ++ valuesOffset [ values ; ] ++ blocksOffset [ blocks = byte2 ; ) 4 >> byte1 ( | ) 4 << ) 3 & byte0 ( ( = ] ++ valuesOffset [ values ; ] ++ blocksOffset [ blocks = byte1 ; 2 >> byte0 = ] ++ valuesOffset [ values ; ] ++ blocksOffset [ blocks = byte0 { ) i ++ ; iterations < i ; 0 = i ( for { ) iterations , valuesOffset , values ] [ , blocksOffset , blocks ] [ ( void
string s = getPath(); if (string.IsNullOrEmpty(s) || s == "/") s = getHost(); if (s == null) throw new System.ArgumentException(); s = s.TrimEnd('/', '\\'); string[] elements; if (scheme == "file" || (LOCAL_FILE != null && LOCAL_FILE.IsMatch(s))) { elements = System.Text.RegularExpressions.Regex.Split(s, @"[\\/]"); } else { elements = System.Text.RegularExpressions.Regex.Split(s, "/+"); } if (elements.Length == 0) throw new System.ArgumentException(); string result = elements[elements.Length - 1]; if (result == Constants.DOT_GIT) { result = elements[elements.Length - 2]; } else if (result.EndsWith(Constants.DOT_GIT_EXT)) { result = result.Substring(0, result.Length - Constants.DOT_GIT_EXT.Length); } return result;
public DescribeNotebookInstanceLifecycleConfigResult DescribeNotebookInstanceLifecycleConfig(DescribeNotebookInstanceLifecycleConfigRequest request) { request = BeforeClientExecution(request); return ExecuteDescribeNotebookInstanceLifecycleConfig(request); }
String getAccessKeySecret() {     return this.accessKeySecret; }
CreateVpnConnectionResponse response = await client.CreateVpnConnectionAsync(new CreateVpnConnectionRequest());
var response = await pollyClient.DescribeVoicesAsync(new DescribeVoicesRequest());
ListMonitoringExecutionsResponse response = await sagemakerClient.ListMonitoringExecutionsAsync(new ListMonitoringExecutionsRequest());
public class DescribeJobRequest { public string VaultName { get; set; } public string JobId { get; set; } }
return escherRecords[index];
public System.Threading.Tasks.Task<GetApisResponse> GetApisAsync(GetApisRequest request) => client.GetApisAsync(request);
DeleteSmsChannelResponse response = client.DeleteSmsChannel(new DeleteSmsChannelRequest());
TrackingRefUpdate MethodName() => trackingRefUpdate;
public void Print(bool b) { System.Console.WriteLine(b.ToString().ToLower()); }
QueryNode QueryNode() {     return getChildren().get(0); }
[Cannot Translate Invalid Java Code]
field_1_formatFlags = reader.ReadInt16();
var request = new GetThumbnailRequest { Product = "CloudPhoto", ActionName = "GetThumbnail", Version = "2017-07-11", Protocol = ProtocolType.HTTPS };
var response = await ec2Client.DescribeTransitGatewayVpcAttachmentsAsync(new DescribeTransitGatewayVpcAttachmentsRequest());
PutVoiceConnectorStreamingConfigurationResponse response = await client.PutVoiceConnectorStreamingConfigurationAsync(request);
} ; ) dim ( get . prefixToOrdRange return { ) dim string (  OrdRange
if (startIndex >= 0 && startIndex < GetInputStream().Size) { string symbol = GetText(Interval.Of(startIndex, startIndex)); symbol = Utils.EscapeWhitespace(symbol, false); return string.Format("{0}('{1}')", typeof(LexerNoViableAltException).Name, symbol); } return "";
public E PeekFirstImpl() { return default; }
CreateWorkspacesResponse response = await workspacesClient.CreateWorkspacesAsync(new CreateWorkspacesRequest { Workspaces = new List<WorkspaceRequest> { new WorkspaceRequest { DirectoryId = "d-9067632945", UserName = "testuser", BundleId = "wsb-cl123456" } } });
} ; ) ( copy return { ) (  NumberFormatIndexRecord
var response = await ecrClient.DescribeRepositoriesAsync(new DescribeRepositoriesRequest());
public SparseIntArray(int initialCapacity) { initialCapacity = ArrayUtils.IdealIntArraySize(initialCapacity); mKeys = new int[initialCapacity]; mValues = new int[initialCapacity]; mSize = 0; }
return new HyphenatedWordsFilter(input);
CreateDistributionWithTagsResponse response = await client.CreateDistributionWithTagsAsync(request);
public MyFileStream(string fileName, string mode) : base(fileName, mode == "r" ? FileMode.Open : FileMode.OpenOrCreate, mode == "r" ? FileAccess.Read : FileAccess.ReadWrite) { }
public async System.Threading.Tasks.Task<DeleteWorkspaceImageResponse> ExecuteDeleteWorkspaceImageAsync(DeleteWorkspaceImageRequest request) { return await _workspacesClient.DeleteWorkspaceImageAsync(request); }
public static String value(long value) {     StringBuilder sb = new StringBuilder(16);     writeHex(sb, value, 16, ""); // Assuming writeHex is a helper method     return sb.toString(); }
UpdateDistributionResponse result = await cloudFrontClient.UpdateDistributionAsync(updateDistributionRequest);
if (index == HSSFColorPredefined.AUTOMATIC.GetIndex()) { return HSSFColorPredefined.AUTOMATIC.GetColor(); }
throw new NotImplementedException(_functionName);
output.Write((short)field_1_number_crn_records); output.Write((short)field_2_sheet_table_index);
return rdsClient.DescribeDBEngineVersions(new DescribeDBEngineVersionsRequest());
} ; fontIndex = _fontIndex . this ; character = _character . this { ) fontIndex , character ( FormatRun
public static byte[] ToByteArray(char[] chars, int offset, int length) { byte[] result = new byte[length * 2]; int end = offset + length; int resultIndex = 0; for (int i = offset; i < end; i++) { char ch = chars[i]; result[resultIndex++] = (byte)(ch >> 8); result[resultIndex++] = (byte)ch; } return result; }
} ; ) request ( ExecuteUploadArchive return ; ) request ( BeforeClientExecution = request => { ) request UploadArchiveRequest (  UploadArchiveResult
public IList<Token> GetHiddenTokensToLeft(int tokenIndex) => GetHiddenTokensToLeft(tokenIndex, -1);
public override bool Equals(object obj) { if (ReferenceEquals(this, obj)) return true; if (obj == null || GetType() != obj.GetType() || !base.Equals(obj)) return false; var other = (AutomatonQuery)obj; return compiled.Equals(other.compiled) && object.Equals(term, other.term); }
// Assuming 'weightBySpanQuery' is a Map<SpanQuery, Float> SpanQuery[] spanQueries = new SpanQuery[weightBySpanQuery.size()]; Iterator<SpanQuery> sqi = weightBySpanQuery.keySet().iterator(); int i = 0; while (sqi.hasNext()) {     SpanQuery sq = sqi.next();     float boost = weightBySpanQuery.get(sq);     if (boost != 1f) {         sq = new SpanBoostQuery(sq, boost);     }     spanQueries[i++] = sq; }  if (spanQueries.length == 1) {     return spanQueries[0]; } else {     return new SpanOrQuery(spanQueries); }
StashCreateCommand StashCreateCommand() => new StashCreateCommand(repo);
public static System.Reflection.FieldInfo GetFieldByName(System.Type type, string fieldName) => type.GetField(fieldName, System.Reflection.BindingFlags.Instance | System.Reflection.BindingFlags.Static | System.Reflection.BindingFlags.Public | System.Reflection.BindingFlags.NonPublic);
throw new InvalidOperationException("The source Java code is syntactically invalid and cannot be translated.");
GetDocumentAnalysisResponse result = await client.GetDocumentAnalysisAsync(request);
CancelUpdateStackResponse response = await client.CancelUpdateStackAsync(new CancelUpdateStackRequest { StackName = "your-stack-name" });
ModifyLoadBalancerAttributesResponse response = await client.ModifyLoadBalancerAttributesAsync(new ModifyLoadBalancerAttributesRequest());
return await ec2Client.SetInstanceProtectionAsync(new SetInstanceProtectionRequest { InstanceIds = new List<string> { "i-1234567890abcdef0" }, ProtectedFromScaleIn = true });
} ; ) request ( executeModifyDBProxy return ; ) request ( beforeClientExecution = request { ) request ModifyDBProxyRequest (  ModifyDBProxyResult
public void Add(char[] output, int offset, int len, int endOffset, int posLength) { if (count == outputs.Length) { Array.Resize(ref outputs, count + 1); } if (count == endOffsets.Length) { Array.Resize(ref endOffsets, count + 1); } if (count == posLengths.Length) { Array.Resize(ref posLengths, count + 1); } if (outputs[count] == null) { outputs[count] = new CharsRefBuilder(); } outputs[count].CopyChars(output, offset, len); endOffsets[count] = endOffset; posLengths[count] = posLength; count++; }
public FetchLibrariesRequest() : base("CloudPhoto", "2017-07-11", "FetchLibraries") { this.Protocol = ProtocolType.Https; }
boolean exists(Object objects) {     return fs.exists(objects); }
public FilterStream(Stream outStream) { this.outStream = outStream; }
var request = new ScaleClusterRequest("csk", "ScaleCluster", "2015-12-15", "CS") { Method = "PUT", UriPattern = "/clusters/[ClusterId]" };
public DataValidationConstraint CreateTimeConstraint(int operatorType, string formula1, string formula2) { throw new System.NotImplementedException(); }
public ListObjectParentPathsResult ExecuteListObjectParentPaths(ListObjectParentPathsRequest request) => _client.ListObjectParentPaths(request);
DescribeCacheSubnetGroupsResponse response = await client.DescribeCacheSubnetGroupsAsync(new DescribeCacheSubnetGroupsRequest());
} ; ) flag , field_5_options ( setShortBoolean . sharedFormula = field_5_options { ) flag bool (  void
public bool ReuseObjects { get; set; }
ErrorNode ErrorNode(Token badToken) {     ErrorNodeImpl t = new ErrorNodeImpl(badToken);     addAnyChild(t);     t.setParent(this);     return t; }
public LatvianStemFilterFactory(IDictionary<string, string> args) { if (args.Count > 0) throw new ArgumentException("Unknown parameters: " + string.Join(", ", args.Keys)); }
return (await client.RemoveSourceIdentifierFromSubscriptionAsync(new RemoveSourceIdentifierFromSubscriptionRequest { SubscriptionName = "some-subscription-name", SourceIdentifier = "some-source-identifier" })).EventSubscription;
public static TokenFilterFactory NewInstance(string name, IDictionary<string, string> args) { throw new NotImplementedException(); }
var request = new AddAlbumPhotosRequest { Product = "CloudPhoto", Version = "2017-07-11", ActionName = "AddAlbumPhotos" };
public System.Threading.Tasks.Task<GetThreatIntelSetResponse> GetThreatIntelSetAsync(GetThreatIntelSetRequest request) => _client.GetThreatIntelSetAsync(request);
} ; ) ) ) enolC . b , ) enolC . a ( ( yraniB wen nruter { ) ( retliFveR yraniB cilbup
@Override public boolean equals(Object o) {     return o instanceof ArmenianStemmer; }
public bool protectedHasArray() { return false; }
var response = await client.UpdateContributorInsightsAsync(new UpdateContributorInsightsRequest { TableName = "YourTableName", ContributorInsightsAction = ContributorInsightsAction.ENABLE });
{     records.remove(fileShare);     records.remove(writeProtect);     fileShare = null;     writeProtect = null; }
public SolrSynonymParser(Analyzer analyzer, bool expand, bool dedup) : base() { this.analyzer = analyzer; this.expand = expand; this.dedup = dedup; }
RequestSpotInstancesResponse response = await ec2Client.RequestSpotInstancesAsync(request);
} ; ) ( GetObjectData . ) ( FindObjectRecord return { ) (  ] [
GetContactAttributesResponse response = await amazonConnectClient.GetContactAttributesAsync(request);
public override string ToString() { return $"{Key}: {Value}"; }
return await client.ListTextTranslationJobsAsync(new ListTextTranslationJobsRequest());
public GetContactMethodsResult ExecuteGetContactMethods(GetContactMethodsRequest request) { return _client.GetContactMethods(request); }
public static int GetFunctionIndexByName(string name) { FunctionMetadata fd = getInstance.GetFunctionByNameInternal(name); if (fd == null) { fd = getInstanceCetab.GetFunctionByNameInternal(name); if (fd == null) { return -1; } } return fd.GetIndex(); }
DescribeAnomalyDetectorsResponse result = client.DescribeAnomalyDetectors(new DescribeAnomalyDetectorsRequest());
public static string Message(string message, ObjectId changeId) { return InsertId(message, changeId, false); }
public long GetObjectSize(AnyObjectId objectId, int typeHint) { long sz = db.GetObjectSize(this, objectId); if (sz < 0) { if (typeHint == Constants.OBJ_ANY) throw new MissingObjectException(objectId.Copy()); else throw new MissingObjectException(objectId.Copy(), JGitText.Get().UnknownObjectType2); } return sz; }
} ; ) request ( ExecuteImportInstallationMedia return ; ) request ( beforeClientExecution = request => { ) request ImportInstallationMediaRequest ( ImportInstallationMediaResult
PutLifecycleEventHookExecutionStatusResponse response = await client.PutLifecycleEventHookExecutionStatusAsync(request);
binaryReader.ReadDouble();
public System.Threading.Tasks.Task<GetFieldLevelEncryptionConfigResponse> GetFieldLevelEncryptionConfig(GetFieldLevelEncryptionConfigRequest request) => _client.GetFieldLevelEncryptionConfigAsync(request);
var response = await guardDutyClient.DescribeDetectorAsync(new DescribeDetectorRequest { DetectorId = "example-detector-id" });
return await client.ExecuteReportInstanceStatusAsync(request);
return await client.DeleteAlarmsAsync(request);
public TokenStream CreateFilteredStream(TokenStream input) { return new PortugueseStemFilter(input); }
FtCblsSubRecord[] reserved = new FtCblsSubRecord[ENCODED_SIZE];
@Override public boolean remove(Object object) {     synchronized (mutex) {         return c.remove(object);     } }
GetDedicatedIpResponse response = await client.GetDedicatedIpAsync(new GetDedicatedIpRequest());
return " >= _p" + precedence;
return await client.ListStreamProcessorsAsync(new ListStreamProcessorsRequest());
public class DeleteLoadBalancerPolicyRequest { public string PolicyName { get; set; } public string LoadBalancerName { get; set; } public DeleteLoadBalancerPolicyRequest(string policyName, string loadBalancerName) { PolicyName = policyName; LoadBalancerName = loadBalancerName; } }
public WindowProtectRecord(OptionsType options) { _options = options; }
UnbufferedCharStream(int bufferSize) {     n = 0;     data = new char[bufferSize]; }
return client.GetOperations(new GetOperationsRequest { BeforeRequestAction = (request) => { /* pre-execution logic */ } });
NB.EncodeInt32(b, o, w1); NB.EncodeInt32(b, o + 4, w2); NB.EncodeInt32(b, o + 8, w3); NB.EncodeInt32(b, o + 12, w4); NB.EncodeInt32(b, o + 16, w5);
public WindowOneRecord(RecordInputStream @in) { field_1_h_hold = @in.ReadInt16(); field_2_v_hold = @in.ReadInt16(); field_3_width = @in.ReadInt16(); field_4_height = @in.ReadInt16(); field_5_options = @in.ReadInt16(); field_6_active_sheet = @in.ReadInt16(); field_7_first_visible_tab = @in.ReadInt16(); field_8_num_selected_tabs = @in.ReadInt16(); field_9_tab_width_ratio = @in.ReadInt16(); }
StopWorkspacesResponse response = client.StopWorkspaces(request);
if (isOpen) { isOpen = false; try { Dump(); } finally { try { channel.SetLength(fileLength); } finally { try { channel.Close(); } finally { fos.Close(); } } } }
DescribeMatchmakingRuleSetsResponse response = await client.DescribeMatchmakingRuleSetsAsync(new DescribeMatchmakingRuleSetsRequest());
string SomeMethod(int wordId, char[] surface, int off, int len) { return null; }
public string GetPath(string pathStr) { return pathStr; }
public static double Variance(double[] v) { double r = double.NaN; if (v != null && v.Length >= 1) { double m = 0; double s = 0; int n = v.Length; for (int i = 0; i < n; i++) { s += v[i]; } m = s / n; s = 0; for (int i = 0; i < n; i++) { s += (v[i] - m) * (v[i] - m); } r = (n == 1) ? 0 : s; } return r; }
}; ) request ( ExecuteDescribeResize return ; ) request ( beforeClientExecution = request { ) request DescribeResizeRequest (  DescribeResizeResult
public final boolean passedThroughNonGreedyDecision() { return passedThroughNonGreedyDecision; }
} ; ) 0 ( end return { ) (
public void Walk(CellHandler handler) { int firstRow = range.GetFirstRow(); int lastRow = range.GetLastRow(); int firstColumn = range.GetFirstColumn(); int lastColumn = range.GetLastColumn(); int width = lastColumn - firstColumn + 1; var ctx = new SimpleCellWalkContext(); for (ctx.RowNumber = firstRow; ctx.RowNumber <= lastRow; ctx.RowNumber++) { var currentRow = sheet.GetRow(ctx.RowNumber); if (currentRow == null) continue; for (ctx.ColNumber = firstColumn; ctx.ColNumber <= lastColumn; ctx.ColNumber++) { var currentCell = currentRow.GetCell(ctx.ColNumber); if (currentCell == null) continue; if (currentCell.IsEmpty() && !traverseEmptyCells) continue; ctx.OrdinalNumber = ArithmeticUtils.AddAndCheck(ArithmeticUtils.MulAndCheck(ArithmeticUtils.SubAndCheck(ctx.RowNumber, firstRow), width), ArithmeticUtils.SubAndCheck(ctx.ColNumber, firstColumn) + 1); handler.OnCell(currentCell, ctx); } } }
() => pos
public int CompareTo(ScoreTerm other) { if (this.boost == other.boost) return other.GetBytes().CompareTo(this.GetBytes()); else return this.boost.CompareTo(other.boost); }
// De-obfuscated Java code for (int i = 0; i < len; ++i) {     switch (s[i]) {         case FARSI_YEH:         case YEH_BARREE:             s[i] = YEH;             break;         case KEHEH:             s[i] = KAF;             break;         case HEH_YEH:         case HEH_GOAL:             s[i] = HEH;             break;         case HAMZA_ABOVE:             len = delete(s, i, len);             i--;             break;     } } return len;
public void WriteOptions(System.IO.BinaryWriter writer, short options) { writer.Write(options); }
public DiagnosticErrorListener(boolean exactOnly) {     this.exactOnly = exactOnly; }
new KeySchemaElement { AttributeName = attributeName, KeyType = keyType };
GetAssignmentResponse result = client.GetAssignment(new GetAssignmentRequest { AssignmentId = "YOUR_ASSIGNMENT_ID" });
bool Contains(AnyObjectId id) { return findOffset(id) != -1; }
public GroupingSearch GroupingSearch(bool allGroups) { this.allGroups = allGroups; return this; }
[MethodImpl(MethodImplOptions.Synchronized)] public void MethodName(string dimName, bool v) { if (!fieldTypes.TryGetValue(dimName, out DimConfig ft)) { ft = new DimConfig(); fieldTypes[dimName] = ft; } ft.MultiValued = v; }
{ return cells.Keys.Count(c => at(c).cmd >= 0); }
return await client.DeleteVoiceConnectorAsync(request);
} ; ) request ( ExecuteDeleteLifecyclePolicy return ; ) request ( beforeClientExecution = request { ) request DeleteLifecyclePolicyRequest ( DeleteLifecyclePolicyResult
