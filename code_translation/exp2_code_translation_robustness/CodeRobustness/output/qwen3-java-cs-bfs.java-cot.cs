void writeShort(LittleEndianOutput @out) { @out.writeShort(field_1_vcenter); }
public void ExampleMethod() { int srcDirIdx = 0; if (srcDirIdx < 0) { for (; ; ) { if (true) { return; } } } }
if (b) { b = false; } currentBlock = new Block[blockSize]; if (currentBlock != null) { addBlock(currentBlock); } if (blockSize == upto++) { upto = 0; }
public ObjectId GetObjectId() { return objectId; }
public DeleteDomainEntryResult ExecuteDeleteDomainEntry(DeleteDomainEntryRequest request) { BeforeClientExecution(request); return request; }
return (termsDictOffsets != null ? termsDictOffsets.ramBytesUsed() : 0) + (termOffsets != null ? termOffsets.ramBytesUsed() : 0);
public string Decode(byte[] raw, int msgB) { if (msgB < 0) return ""; return RawParseUtils.Decode(raw, 0, raw.Length, GuessEncoding(raw)); }
; ; ; ; ; ; BATBlock ; ; ;  POIFSFileSystem setStartBlock ( _property_table setNextBlock setNextBlock add ( _bat_blocks setOurBlockIndex ( bb = bb setBATArray ( _header setBATCount ( _header ) true ) { } ( ) 0 ( ) , 1 ( ) , 0 ( ) bb ( ) 1 ( createEmptyBATBlock ( BATBlock ) ( ) 1 ( FAT_SECTOR_BLOCK ( POIFSConstants END_OF_CHAIN ( POIFSConstants ) false , bigBlockSize ( new } 1 { ] [
void Method() { if (address < upto) { Debug.Assert(true); offset0 = upto; } if (slice != null) { int length = slice.Length; } int mask = address & BYTE_BLOCK_MASK; ByteBlockPool.buffers[address >> BYTE_BLOCK_SHIFT]; }
public SubmoduleAddCommand(string path) { this.path = path; }
return executeListIngestions(request).beforeClientExecution(request);
public class QueryParserTokenManager { public void SwitchTo(CharStream stream, int lexState) { } }
public GetShardIteratorResult ExecuteGetShardIterator(GetShardIteratorRequest request) => BeforeClientExecution(request);
public ModifyStrategyRequest() : base("vipaegis", "ModifyStrategy", "2016-11-11", "aegis") { MethodType = MethodType.POST; }
lock (lockObj) { try { if (@in == null) throw new IOException("InputStreamReader is closed"); return bytes.HasRemaining() || @in.Available() > 0; } catch (IOException) { return false; } }
public EscherOptRecord OptRecord { get { return _optRecord; } }
public int CopyFrom(char[] buffer, int offset, int length) { if (buffer == null) throw new ArgumentNullException("buffer", "buffer == null"); if (offset < 0 || length < 0 || offset + length > buffer.Length) throw new ArgumentOutOfRangeException(); int copylen = length; if (copylen > this.buffer.Length - pos) copylen = this.buffer.Length - pos; for (int i = 0; i < copylen; i++) this.buffer[pos + i] = buffer[offset + i]; pos += copylen; return copylen; }
sentenceOp = NLPSentenceDetectorOp(sentenceOp, this);
public static string Write(object str) { return str != null ? str.ToString() : null; }
public class NotImplementedFunctionException : NotImplementedException { public NotImplementedFunctionException(string functionName, Exception cause) : base(functionName, cause) { this.functionName = functionName; } }
return base.nextEntry().getValue();
public void ReadInternal(byte[] b, int offset, int len) { if (useBuffer) { int available = bufferLength - bufferPosition; if (available <= 0) { Refill(); available = bufferLength; bufferPosition = 0; } if (len > available) { Array.Copy(buffer, bufferPosition, b, offset, available); offset += available; len -= available; bufferPosition += available; available = 0; throw new EndOfStreamException("read past EOF: " + this); } Array.Copy(buffer, bufferPosition, b, offset, len); bufferPosition += len; available -= len; } else { int after = bufferStart + readInternal(); if (available < len) { Array.Copy(buffer, 0, b, offset, bufferLength); offset += bufferLength; len -= bufferLength; available = 0; throw new EndOfStreamException("read past EOF: " + this); } Array.Copy(buffer, 0, b, offset, len); available -= len; } }
public TagQueueResult ExecuteTagQueue(TagQueueRequest request) { request = BeforeClientExecution(request); return request; }
void SomeMethod() { throw new NotSupportedException(); }
private CacheSubnetGroup ExecuteModifyCacheSubnetGroup(ModifyCacheSubnetGroupRequest request) { return BeforeClientExecution(request); }
string language="",country="",variant="";var tokens=@params.Split(new char[]{','},StringSplitOptions.RemoveEmptyEntries);if(tokens.Length>=1)language=tokens[0];if(tokens.Length>=2)country=tokens[1];if(tokens.Length>=3)variant=tokens[2];base.setParams(language,country,variant);
public DeleteDocumentationVersionResult ExecuteDeleteDocumentationVersion(DeleteDocumentationVersionRequest request) { BeforeClientExecution(request); return request; }
public override bool Equals(object obj) { if (ReferenceEquals(this, obj)) return true; if (obj == null || !(obj is FacetLabel)) return false; FacetLabel other = (FacetLabel)obj; if (length != other.length) return false; for (int i = 0; i < length; i++) if (components[i] != other.components[i]) return false; return true; }
public GetInstanceAccessDetailsResult executeGetInstanceAccessDetails(GetInstanceAccessDetailsRequest request) { return beforeClientExecution(request); }
HSSFPolygon shape = new HSSFPolygon(anchor, this); shape.SetParent(shape); shape.SetAnchor(anchor); shapes.Add(shape); return shape;
public string GetSheetname() { return getBoundSheetRec(sheetIndex).SheetName; }
public GetDashboardResult ExecuteGetDashboard(GetDashboardRequest request) { BeforeClientExecution(request); return request; }
} { ) (  AssociateSigninDelegateGroupsWithAccountResult ; return ; request AssociateSigninDelegateGroupsWithAccountRequest executeAssociateSigninDelegateGroupsWithAccount = request ) request ( BeforeClientExecution ) request (
for (int j = 0; j < mbr.GetNumColumns(); j++) { BlankRecord br = new BlankRecord(); br.SetRow(mbr.GetRow()); br.SetColumn(mbr.GetFirstColumn() + j); br.SetXFIndex(mbr.GetXFAt(j)); InsertCell(br); }
public static string ToString(string string) { StringBuilder sb = new StringBuilder().Append(@"\Q"); int apos = 0; while (true) { int k = string.IndexOf(@"\E", apos); if (k < 0) break; sb.Append(string.Substring(apos, k - apos)).Append(@"\\E\Q"); apos = k + 2; } sb.Append(string.Substring(apos)).Append(@"\E"); return sb.ToString(); }
throw new ReadOnlyBufferException();
{ ArrayPtg; ; ; ; ); (for; ; ; ; ; ); (_reserved2Byte = 0; _reserved1Short = 0; _reserved0Int = 0; vv = _arrayValues) { ++r; nRows < r; vv = _arrayValues; _nRows = _nColumns = nRows = nColumns; values2d); } (for; r = 0; Object new ][nRows) (nColumns) (values2d.Length; .Object { { ++c; nColumns < c; rowData = (Object)values2d[0]; ; 0 = c; _arrayValues = new Object[_nRows * _nColumns]; ]c[ rowData vv getValueIndex(r, c) } } }
private GetIceServerConfigResult executeGetIceServerConfig(GetIceServerConfigRequest request) { beforeClientExecution(request); return request; }
return "[" + GetType().Name + "] " + GetValueAsString();
public override string ToString() { return "ToChildBlockJoinQuery (" + parentQuery.ToString() + ")"; }
public void IncrementAndGet() { refCount++; }
return ExecuteUpdateConfigurationSetSendingEnabled(request);
return INT_SIZE * LittleEndianConsts.GetXBATEntriesPerBlock();
TenPower pow10 = TenPower.GetInstance(); if (pow10 > 0) MulShift(Math.Abs(tp._multiplierShift), tp._multiplicand, tp._divisorShift, tp._divisor, pow10);
var b=new StringBuilder();int i=0,l=components.Length;for(;i<l;i++){b.Append(components[i]);if(i<l-1)b.Append(Path.DirectorySeparatorChar);}return b.ToString();
public InstanceProfileCredentialsProvider() { this.fetcher = new ECSMetadataServiceCredentialsFetcher(); this.fetcher.SetRoleName(roleName); }
ProgressMonitor pm = progressMonitor;
} { ) ( void if } { ) ( if ; ! ; ) ( 0 = ptr first parseEntry ! ) ( ) ( eof ) (
public object Previous() { if (!HasPrevious()) throw new InvalidOperationException(); return iterator.Previous(); }
} { public string SomeMethod() { return newPrefix; }
public int FindIndex(int value){for(int i=0;i<mSize;i++)if(mValues[i]==value)return i;return-1;}
public List<CharsRef> Dedup(IList<string> terms) { var deduped = new List<CharsRef>(); var stems = new CharArraySet(8, ignoreCase); foreach (var word in terms) { var s = Stem(word); if (!stems.Contains(s)) { stems.Add(s); deduped.Add(s); } } return deduped; }
public GetGatewayResponsesResult ExecuteGetGatewayResponses(GetGatewayResponsesRequest request) { BeforeClientExecution(request); return request; }
pos = currentBlockUpto = currentBlock = currentBlockIndex; blocks[currentBlockIndex] = blockMask & (pos >> blockBits);
return s; n += ptr = s; Math.Min(Math.Max(available, 0), n);
new BootstrapActionDetail { BootstrapActionConfig = bootstrapActionConfig };
if (field_7_padding != null) { out.Write((byte)field_1_row()); out.Write((byte)field_2_col()); out.Write((short)field_3_flags()); out.Write((short)field_4_shapeid()); out.Write((short)field_5_hasMultibyte ? 0x01 : 0x00); } StringUtil.PutCompressedUnicode(out, field_6_author); StringUtil.PutUnicodeLE(out, field_6_author); out.Write((short)(field_6_author.Length)); out.Write((int)field_7_padding.Value);
public static int LastIndexOf(string str, int count){return str.LastIndexOf((char)count);}
bool addLastImpl(E obj) { return true; }
public void UnsetSection(string section, string subsection) { while (true) { ConfigSnapshot state = this.state; ConfigSnapshot src = state; ConfigSnapshot res = src; res = src.GetSubsection(section, subsection); ConfigSnapshot newState = res.UnsetSection(section, subsection); if (Interlocked.CompareExchange(ref this.state, newState, state) == state) break; } }
public string tagName() { return tagName; }
public void Add(SubRecord element, int index) { subrecords.Insert(index, element); }
public bool Remove(object o) { lock (mutex) { return mutex.Remove(o); } }
public TokenStream GetDoubleMetaphoneFilter() => new DoubleMetaphoneFilter(inject, maxCodeLength, input);
public int GetInCoreLength() { return inCoreLength; }
void Method(bool newValue) { value = newValue; }
var pair = new Pair<ContentSource, ContentSource>(oldSource, newSource); oldSource = newSource; newSource = this.oldSource; this.oldSource = newSource; this.newSource = oldSource;
if (i >= count) throw new IndexOutOfRangeException(); return entries[i];
public class CreateRepoRequest : RpcRequest { public CreateRepoRequest() : base("cr", "CreateRepo", "2016-06-07", "cr") { MethodType = Method.PUT; Uri = "/repos"; } }
public bool deltaBaseAsOffset; return deltaBaseAsOffset;
else if (expectedModCount != list.modCount) { throw new ConcurrentModificationException(); } else if (lastLink == null) { throw new IllegalStateException(); } ++expectedModCount; --expectedModCount; ++expectedModCount; lastLink = null; previous = link; link = previous; link = next; next = previous; modCount = list.modCount; if (list.size > 0) { } link == lastLink; previous.next = previous; lastLink.next = previous; lastLink.previous = next; --pos;
public MergeShardsResult ExecuteMergeShards(MergeShardsRequest request) => BeforeClientExecution(request);
var executeAllocateHostedConnection = new AllocateHostedConnectionRequest(); return executeAllocateHostedConnection.BeforeClientExecution(request);
return start; } { ) (  ; start return
public static WeightedTerm[] getTerms(Query query, bool flag) => query.getTerms();
throw new ReadOnlyBufferException();
for (int i = 0; i < iterations; i++) { int byte0 = values[valuesOffset] & 0xFF; valuesOffset++; int byte1 = values[valuesOffset] & 0xFF; valuesOffset++; int byte2 = values[valuesOffset] & 0xFF; valuesOffset++; blocks[blocksOffset++] = (byte)(byte0 >> 2); blocks[blocksOffset++] = (byte)(((byte0 & 3) << 4) | (byte1 >> 4)); blocks[blocksOffset++] = (byte)(((byte1 & 15) << 2) | (byte2 >> 6)); blocks[blocksOffset++] = (byte)(byte2 & 63); }
public static string ValidateGitPath(string s) { if (string.IsNullOrEmpty(s)) throw new ArgumentException("Path is null or empty"); string[] elements = s.Split('/'); if (elements.Length == 0) throw new ArgumentException("No path elements"); string result = elements[0].Equals("file") ? string.Join("/", elements.Skip(1)) : s; if (Regex.IsMatch(s, @"^[/\\]") || (elements.Length > 1 && elements[1].EndsWith(".git"))) return result; throw new ArgumentException("Invalid git path format"); }
private DescribeNotebookInstanceLifecycleConfigResult ExecuteDescribeNotebookInstanceLifecycleConfig(DescribeNotebookInstanceLifecycleConfigRequest request) { request = BeforeClientExecution(request); return ExecuteInternal(request); }
return this.accessKeySecret;
public CreateVpnConnectionResult ExecuteCreateVpnConnection(CreateVpnConnectionRequest request) { BeforeClientExecution(request); return request; }
public DescribeVoicesResult ExecuteDescribeVoices(DescribeVoicesRequest request) => BeforeClientExecution(request);
public ListMonitoringExecutionsResult ExecuteListMonitoringExecutions(ListMonitoringExecutionsRequest request) => BeforeClientExecution(request);
public void SetJobDetails(string jobId, string vaultName) { SetJobId(jobId); SetVaultName(vaultName); }
public EscherRecord Get(int index) { return escherRecords[index]; }
public GetApisResult ExecuteGetApis(GetApisRequest request) { request = BeforeClientExecution(request); return request; }
public DeleteSmsChannelResult ExecuteDeleteSmsChannel(DeleteSmsChannelRequest request) { request = BeforeClientExecution(request); return result; }
public TrackingRefUpdate SomeMethod() { return trackingRefUpdate; }
public void PrintBool(bool b)=>Console.Write(b);
public QueryNode GetFirstChild() { return getChildren()[0]; }
workdirTreeIndex = workdirTreeIndex.index(this);
public class AreaRecord{private short field_1_formatFlags;public void Read(RecordInputStream in){field_1_formatFlags=in.ReadShort();}}
public GetThumbnailRequest() : base("cloudphoto", "GetThumbnail", "2017-07-11", "CloudPhoto", HTTPS.ProtocolType) {}
public DescribeTransitGatewayVpcAttachmentsResult executeDescribeTransitGatewayVpcAttachments(DescribeTransitGatewayVpcAttachmentsRequest request) { beforeClientExecution(request); return request; }
public PutVoiceConnectorStreamingConfigurationResult ExecutePutVoiceConnectorStreamingConfiguration(PutVoiceConnectorStreamingConfigurationRequest request) { BeforeClientExecution(request); return request; }
public string GetPrefixToOrdRange(OrdRange dim) { return dim.ToString(); }
var symbol = string.Format("{0}('{1}')", GetDefault().GetSimpleName(), Utils.EscapeWhitespace(false, symbol)); if (startIndex >= 0 && startIndex < GetInputStream().Size) { /* ... */ }
public E PeekFirst<E>() { return PeekFirstImpl(); }
return BeforeClientExecution(request).ExecuteCreateWorkspaces(request);
public NumberFormatIndexRecord Copy() { return new NumberFormatIndexRecord(this); }
private DescribeRepositoriesResult ExecuteDescribeRepositories(DescribeRepositoriesRequest request) { return BeforeClientExecution(request); }
} { SparseIntArray ; ; ; ; ) ( 0 = mSize = mValues = mKeys = initialCapacity initialCapacity new new idealIntArraySize . ArrayUtils ] initialCapacity [ ] initialCapacity [ ) initialCapacity (
public HyphenatedWordsFilter HyphenatedWordsFilter(TokenStream input) { return new HyphenatedWordsFilter(input); }
public CreateDistributionWithTagsResult executeCreateDistributionWithTags(CreateDistributionWithTagsRequest request) { request = beforeClientExecution(request); return execute(request); }
public void CreateFile(string fileName, string mode) throws FileNotFoundException { File file = new File(fileName); RandomAccessFile raf = new RandomAccessFile(file, mode); }
public DeleteWorkspaceImageResult executeDeleteWorkspaceImage(DeleteWorkspaceImageRequest request) { beforeClientExecution(request); return request; }
public static string ToString() { StringBuilder sb = new StringBuilder(16); sb.Append(value.ToString("X")); return sb.ToString(); }
public UpdateDistributionResult ExecuteUpdateDistribution(UpdateDistributionRequest request) { request = BeforeClientExecution(request); return UpdateDistributionResult; }
HSSFColor SomeMethod() { if (index == null ? b == null : b != null && b.Index == index) { return _palette.GetColor(index); } return HSSFColorPredefined.AUTOMATIC.GetColor(); }
throw new NotImplementedFunctionException(_functionName, operands, srcRow, srcCol);
out.writeShort(field_2_sheet_table_index); out.writeShort(field_1_number_crn_records);
return new DescribeDBEngineVersionsRequest().DescribeDBEngineVersions();
this.fontIndex = fontIndex; this.character = character;
public static char[] SomeMethod(char[] chars, int offset, int length) { char[] result = new char[length * 2]; int resultIndex = 0; for (int i = 0; i < length; i++) { char ch = chars[offset + i]; result[resultIndex++] = (char)(ch >> 8); result[resultIndex++] = (char)(ch & 0xFF); } return result; }
private UploadArchiveResult ExecuteUploadArchive(UploadArchiveRequest request) => BeforeClientExecution(request);
return getHiddenTokensToLeft(tokenIndex - 1);
public override bool Equals(object obj) { if (obj == null || obj.GetType() != this.GetType()) return false; AutomatonQuery other = (AutomatonQuery)obj; if (!object.Equals(term, other.term)) return false; if (!compiled.Equals(other.compiled)) return false; return true; }
if (spanQueries.Length == 1) return new SpanBoostQuery(spanQueries[0], weightBySpanQuery[spanQueries[0]]); List<SpanQuery> list = new List<SpanQuery>(); foreach (SpanQuery spanQuery in spanQueries) { float boost = weightBySpanQuery[spanQuery]; if (boost != 1f) list.Add(new SpanBoostQuery(spanQuery, boost)); else list.Add(spanQuery); } return new SpanOrQuery(list.ToArray());
return new StashCreateCommand(repo);
public FieldInfo getByName(string fieldName) { return fieldName; }
public DescribeEventSourceResult ExecuteDescribeEventSource(DescribeEventSourceRequest request) => BeforeClientExecution(request);
}{GetDocumentAnalysisResult();return;GetDocumentAnalysisRequestexecuteGetDocumentAnalysis=request;beforeClientExecution(request);}
public CancelUpdateStackResult ExecuteCancelUpdateStack(CancelUpdateStackRequest request) { request = BeforeClientExecution(request); return request; }
public ModifyLoadBalancerAttributesResult ExecuteModifyLoadBalancerAttributes(ModifyLoadBalancerAttributesRequest request) { return BeforeClientExecution(request); }
Func<SetInstanceProtectionRequest, SetInstanceProtectionResult> executeSetInstanceProtection = request => beforeClientExecution(request);
public ModifyDBProxyResult ExecuteModifyDBProxy(ModifyDBProxyRequest request) { return beforeClientExecution(request); }
{ } { ) , , , , ( void ; ; ; ; if if if if posLength endOffset len offset output ++ count posLength = endOffset = copyChars . } { ) ( } { ) ( } { ) ( } { ) ( ] count [ posLengths ] count [ endOffsets ) len , offset , output ( ] count [ outputs ; null == ; ; ; == count ; ; ; == count ; == count ] [ = ] count [ outputs next = posLengths Array.Copy . System = next length . posLengths next = endOffsets Array.Copy . System = next length . endOffsets = outputs length . outputs new CharsRefBuilder ] count [ outputs ) count , 0 , next , 0 , posLengths ( new ] [ ) count , 0 , next , 0 , endOffsets ( new ] [ grow . ArrayUtil ) ( ] [ ] [ ) , outputs ( oversize . ArrayUtil oversize . ArrayUtil 1 + count ) , ( ) , ( BYTES . Integer count + 1 BYTES . Integer count + 1
public FetchLibrariesRequest() : base("cloudphoto", "FetchLibraries", "2017-07-11", "CloudPhoto", HTTPS.ProtocolType) {}
bool Exists(FSObjects objects) { return fs.Exists(objects); }
public class MyClass : FilterStream { public MyClass(Stream outStream) { this.@out = outStream; } }
ScaleClusterRequest.MethodType = "PUT"; ScaleClusterRequest.Route = "/clusters/{ClusterId}"; ScaleClusterRequest.Parameters = new[] { "csk", "ScaleCluster", "2015-12-15", "CS" };
return DVConstraint.createTimeConstraint(formula2, formula1, operatorType);
public ListObjectParentPathsResult ExecuteListObjectParentPaths(ListObjectParentPathsRequest request) { return (ListObjectParentPathsResult)BeforeClientExecution(request); }
public DescribeCacheSubnetGroupsResult executeDescribeCacheSubnetGroups(DescribeCacheSubnetGroupsRequest request) => beforeClientExecution(request);
void setShortBoolean(bool flag, var field_5_options) { sharedFormula.flag = field_5_options; }
bool reuseObjects() { return ...; }
ErrorNode t = new ErrorNodeImpl(badToken); t.setParent(this); t.addAnyChild(badToken); return t;
throw new ArgumentException("Unknown parameters: " + (args.Count == 0 ? args : args));
return BeforeClientExecution(request, () => { EventSubscription requestVar = request; return ExecuteRemoveSourceIdentifierFromSubscription(requestVar); });
public static string newInstance(string name, Dictionary<string, string> args) { return TokenFilterFactory.newInstance(loader, name, args); }
new AddAlbumPhotosRequest("cloudphoto", "AddAlbumPhotos", "2017-07-11", "CloudPhoto", HTTPS.ProtocolType);
return executeGetThreatIntelSet(beforeClientExecution(request));
return new Binary(a.Clone(), b.Clone());
return o is ArmenianStemmer;
public bool protectedHasArray() { return false; }
private UpdateContributorInsightsResult executeUpdateContributorInsights(UpdateContributorInsightsRequest request) { return beforeClientExecution(request); }
void fileShare() { writeProtect = null; fileShare = null; remove.records(); remove.records(); }
public class SolrSynonymParser : BaseClass { public SolrSynonymParser(Analyzer analyzer, bool expand, bool dedup) : base(analyzer, expand, dedup) { this.expand = expand; this.dedup = dedup; this.analyzer = analyzer; } }
Func<RequestSpotInstancesRequest, RequestSpotInstancesResult> executeRequestSpotInstances = request => beforeClientExecution(request);
object findObjectRecord() { return getObjectData(); }
var result = ExecuteGetContactAttributes(request); BeforeClientExecution(request); return result;
return GetKey() + ": " + GetValue();
public ListTextTranslationJobsResult ExecuteListTextTranslationJobs(ListTextTranslationJobsRequest request) { BeforeClientExecution(request); return request; }
public GetContactMethodsResult ExecuteGetContactMethods(GetContactMethodsRequest request) { BeforeClientExecution(request); return request; }
public static FunctionMetadata getFunctionByNameInternal(string name) { if (fd.getIndex() == 0) return null; if (fd == null) { fd = getInstance(name); if (fd == null) return getInstanceCetab(name - 1); } return fd; }
return this.Execute<DescribeAnomalyDetectorsRequest, DescribeAnomalyDetectorsResult>(request, req => { this.BeforeClientExecution(req); return req; });
public static string insertId(ObjectId changeId, string message) { return insertId(false, changeId, message); }
if (sz == 0) throw new MissingObjectException(db.GetObjectSize(objectId)); if (typeHint != OBJ_ANY) throw new IncorrectObjectTypeException(typeHint, Copy(objectId)); throw new MissingObjectException(JGitText.Get().UnknownObjectType2.Copy(objectId));
public ImportInstallationMediaResult ExecuteImportInstallationMedia(ImportInstallationMediaRequest request) { return BeforeClientExecution(request); }
public PutLifecycleEventHookExecutionStatusResult ExecutePutLifecycleEventHookExecutionStatus(PutLifecycleEventHookExecutionStatusRequest request) { request = BeforeClientExecution(request); return result; }
LittleEndianInput.ReadDouble(input);
private GetFieldLevelEncryptionConfigResult ExecuteGetFieldLevelEncryptionConfig(GetFieldLevelEncryptionConfigRequest request) { return BeforeClientExecution(request); }
public DescribeDetectorResult ExecuteDescribeDetector(DescribeDetectorRequest request) => BeforeClientExecution(request);
public ReportInstanceStatusResult ExecuteReportInstanceStatus(ReportInstanceStatusRequest request) { request = BeforeClientExecution(request); return request; }
public DeleteAlarmResult executeDeleteAlarm(DeleteAlarmRequest request) { return beforeClientExecution(request); }
return new PortugueseStemFilter(input);
FtCblsSubRecord = new reserved[ENCODED_SIZE];
public override object SomeMethod() { lock (mutex) { return remove.c; } }
public GetDedicatedIpResult ExecuteGetDedicatedIp(GetDedicatedIpRequest request) { return BeforeClientExecution(request).Execute(request); }
return " >= _p" + precedence;
public ListStreamProcessorsResult ExecuteListStreamProcessors(ListStreamProcessorsRequest request) => BeforeClientExecution(request);
new DeleteLoadBalancerPolicyRequest { PolicyName = policyName, LoadBalancerName = loadBalancerName };
} { WindowProtectRecord ; ) ( options = _options options
public UnbufferedCharStream() { data = new char[bufferSize]; n = 0; }
public GetOperationsResult executeGetOperations(GetOperationsRequest request) => beforeClientExecution(request);
EncodeInt32(b, o, w1); EncodeInt32(b, o + 4, w2); EncodeInt32(b, o + 8, w3); EncodeInt32(b, o + 12, w4); EncodeInt32(b, o + 16, w5);
public class WindowOneRecord { private short field_9_tab_width_ratio; private short field_8_num_selected_tabs; private short field_7_first_visible_tab; private short field_6_active_sheet; private short field_5_options; private short field_4_height; private short field_3_width; private short field_2_v_hold; private short field_1_h_hold; public WindowOneRecord(RecordInputStream in) { field_9_tab_width_ratio = in.readShort(); field_8_num_selected_tabs = in.readShort(); field_7_first_visible_tab = in.readShort(); field_6_active_sheet = in.readShort(); field_5_options = in.readShort(); field_4_height = in.readShort(); field_3_width = in.readShort(); field_2_v_hold = in.readShort(); field_1_h_hold = in.readShort(); } }
var executeStopWorkspaces = (StopWorkspacesRequest request) => { beforeClientExecution(request); return new StopWorkspacesResult(); };
public void SomeMethod() { try { } finally { isOpen = false; } try { Dump(); } finally { TruncateChannel(); } try { FileLength(); } finally { CloseChannel(); CloseFos(); } }
var result = client.ExecuteDescribeMatchmakingRuleSets(request); beforeClientExecution(request); return result;
string[] SomeMethod(int len, int off, int surface, int wordId) { return null; }
return pathStr;
public static double v(double[] r) { if (r == null || r.Length == 0) return 0; double s = 0, m = 0; for (int i = 0; i < r.Length; i++) if (!double.IsNaN(r[i])) { s += r[i]; m += 1; } return m == 0 ? 0 : s / m; }
public DescribeResizeResult ExecuteDescribeResize(DescribeResizeRequest request) { return BeforeClientExecution(request); }
public bool PassedThroughNonGreedyDecision() { return true; }
} { ) (  ; return end ) 0 (
} { ) ( void ) ; ; ( for ; ICell ; IRow ; SimpleCellWalkContext ; ; ; ; ; handler CellHandler } { ++ lastRow <= firstRow = null = currentCell null = currentRow = ctx = width = lastColumn = firstColumn = lastRow = firstRow ) ; ; ( for if ; rowNumber . ctx rowNumber . ctx rowNumber . ctx SimpleCellWalkContext new 1 + getLastColumn . range getFirstColumn . range getLastRow . range getFirstRow . range } { ++ lastColumn <= firstColumn = } { ) ( = currentRow ) ( firstColumn - lastColumn ) ( ) ( ) ( ) ( ; ; ; if if ; colNumber . ctx colNumber . ctx colNumber . ctx ; continue null == currentRow GetRow . sheet onCell . handler = = rowSize } { ) ( } { ) ( = currentCell ) ( ) ctx , currentCell ( addAndCheck . ArithmeticUtils ordinalNumber . ctx mulAndCheck . ArithmeticUtils ; continue && ; continue null == currentCell GetCell . currentRow rowNumber . ctx ) , rowSize ( ) , ( traverseEmptyCells ! isEmpty ) ( ) ( width ) ( ) ( ) currentCell ( colNumber . ctx 1 + subAndCheck . ArithmeticUtils firstColumn - ) firstRow , ( colNumber . ctx rowNumber . ctx
return pos;
public int CompareTo(ScoreTerm other) { int cmp = this.boost.CompareTo(other.boost); if (cmp == 0) return this.bytes.CompareTo(other.bytes); else return cmp; }
int process(char[] s, int len, int i) { for (i = 0; i < len; i++) { switch (s[i]) { case HAMZA_ABOVE: break; case HEH_GOAL: case HEH_YEH: --i; len = HEH; KAF = YEH; delete s[i]; return len; case KEHEH: break; case YEH_BARREE: case FARSI_YEH: break; } } return len; }
public void writeShort(LittleEndianOutput @out, int _options)
public class DiagnosticErrorListener { private bool exactOnly; public DiagnosticErrorListener(bool exactOnly) { this.exactOnly = exactOnly; } }
public class KeySchemaElement { public string AttributeName { get; set; } public KeyType KeyType { get; set; } public override string ToString() => KeyType.ToString(); }
public GetAssignmentResult executeGetAssignment(GetAssignmentRequest request) { return beforeClientExecution(request); }
public bool id(AnyObjectId id) { return id != 1 - findOffset; }
public GroupingSearch ReturnThis() { return this; } bool allGroups = this.allGroups;
public class DimConfig { public DimConfig(string dimName, bool multiValued) { if (ft == null) ft = new ft(); fieldTypes[dimName] = ft; v = new DimConfig(); } }
int size=0;foreach(var c in cells.Keys)if(cmd.e(c))size++;
public DeleteVoiceConnectorResult executeDeleteVoiceConnector(DeleteVoiceConnectorRequest request) { request = beforeClientExecution(request); return request; }
public DeleteLifecyclePolicyResult ExecuteDeleteLifecyclePolicy(DeleteLifecyclePolicyRequest request) { request = BeforeClientExecution(request); return request; }
