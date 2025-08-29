void writeShort(LittleEndianOutput @out) { @out.field_1_vcenter
void SomeMethod() { for (;;) { ++srcDirIdx; if (srcDirIdx < 0) { srcDirIdx = 0; return; } } if (addAll != null) blockList.AddRange(addAll); tailDirIdx = 0; tailBlkIdx = 0; size = new Size(BLOCK_SIZE, 0); tailBlkIdx = 0; tailBlock = directory.src[srcDirIdx]; }
} { ) (  void ; if (b) b = } { ) ( ] [ currentBlock ; ; if (blockSize == upto) ++upto; 0 = upto = currentBlock } { ) ( new ; null != currentBlock ] blockSize [ addBlock(currentBlock);
public ObjectId GetObjectId() { return objectId; }
public DeleteDomainEntryResult ExecuteDeleteDomainEntry(DeleteDomainEntryRequest request) { request = BeforeClientExecution(request); return request; }
return (termsDictOffsets != null ? ramBytesUsed.termsDictOffsets() : 0) + (termOffsets != null ? ramBytesUsed.termOffsets() : 0);
public static string Decode(byte[] raw) { byte[] msgB = raw; if (msgB.Length > 0) return RawParseUtils.Decode(msgB, 0, msgB.Length, RawParseUtils.GuessEncoding(raw)); return ""; }
BATBlock bb = BATBlock.createEmptyBATBlock(1); bb.setBATArray(new int[] { 0, 1, 0 }); _header.setBATCount(1); _property_table.setNextBlock(FAT_SECTOR_BLOCK); _bat_blocks.add(bb); POIFSFileSystem.setStartBlock(POIFSConstants.END_OF_CHAIN, bigBlockSize, false);
offset0 = address; upto = address + slice.length; slice = pool.buffers[address >> ByteBlockPool.BYTE_BLOCK_SHIFT]; System.Diagnostics.Debug.Assert(address < upto); System.Diagnostics.Debug.Assert((address & ByteBlockPool.BYTE_BLOCK_MASK) == 0);
public SubmoduleAddCommand SomeMethod(string path) { this.path = path; return this; }
var executeListIngestions = request(); beforeClientExecution(request); return ListIngestionsResult;
public void SwitchTo(CharStream stream, int lexState) { }
public GetShardIteratorResult ExecuteGetShardIterator(GetShardIteratorRequest request) { BeforeClientExecution(request); return request; }
public class ModifyStrategyRequest : SomeClass { public ModifyStrategyRequest() : base("vipaegis", "ModifyStrategy", "2016-11-11", "aegis", MethodType.POST) {} }
lock (this) { if (in == null) throw new IOException("InputStreamReader is closed"); try { return in.Read(bytes, 0, bytes.Length); } catch { return -1; } }
public EscherOptRecord getOptRecord() { return _optRecord; }
public int CopyData(char[] buffer, int offset, int length) { lock (this) { if (buffer == null) throw new ArgumentNullException("buffer"); if (offset < 0 || length < 0 || offset + length > buffer.Length) throw new ArgumentOutOfRangeException(); int copyLen = Math.Min(available, length); for (int i = 0; i < copyLen; i++) buffer[offset + i] = this.buffer[pos + i]; pos += copyLen; return copyLen; } }
sentenceOp = NLPSentenceDetectorOp(sentenceOp, this);
void Write(string str) { if (str != null) { str.ToString(); } else { Write((object)null); } }
public class NotImplementedException : Exception { public NotImplementedException(string functionName, Exception cause) : base(functionName, cause) {} }
public V nextEntry() { return base.nextEntry().getValue(); }
public void Read(byte[] b, int offset, int len) { if (useBuffer) { if (available <= len) { if (available < len) throw new EndOfStreamException("read past EOF: " + len); Array.Copy(buffer, bufferPosition, b, offset, len); bufferPosition += len; available = 0; } else { Array.Copy(buffer, bufferPosition, b, offset, len); bufferPosition += len; available -= len; } } else { int after = ReadInternal(b, offset, len); if (after < len) throw new EndOfStreamException("read past EOF: " + len); } }
public TagQueueResult executeTagQueue(TagQueueRequest request) { return beforeClientExecution(request); }
void SomeMethod() { throw new NotSupportedException(); }
public CacheSubnetGroup executeModifyCacheSubnetGroup(ModifyCacheSubnetGroupRequest request) { return beforeClientExecution(request); }
var parts = params.Split(','); language = parts[0]; country = parts[1]; variant = parts.Length > 2 ? parts[2] : ""; base.setParams(language, country, variant);
public DeleteDocumentationVersionResult ExecuteDeleteDocumentationVersion(DeleteDocumentationVersionRequest request) { BeforeClientExecution(request); return result; }
public bool Equals(object obj) { if (this == obj) return true; if (!(obj is FacetLabel)) return false; FacetLabel other = (FacetLabel)obj; if (components.Length != other.components.Length) return false; for (int i = 0; i < components.Length; i++) if (!components[i].Equals(other.components[i])) return false; return true; }
public GetInstanceAccessDetailsResult ExecuteGetInstanceAccessDetails(GetInstanceAccessDetailsRequest request) { BeforeClientExecution(request); return request; }
HSSFPolygon shape = new HSSFPolygon(); HSSFChildAnchor anchor = new HSSFChildAnchor(); shapes.Add(shape); shape.SetAnchor(anchor); shape.SetParent(this); return shape;
string GetSheetname(int sheetIndex) { return GetBoundSheetRec(sheetIndex); }
public GetDashboardResult ExecuteGetDashboard(GetDashboardRequest request) { return beforeClientExecution(request); }
public AssociateSigninDelegateGroupsWithAccountResult ExecuteAssociateSigninDelegateGroupsWithAccount(AssociateSigninDelegateGroupsWithAccountRequest request) { var executeAssociateSigninDelegateGroupsWithAccount = request; return BeforeClientExecution(request); }
for (int j = 0; j < mbr.NumColumns; j++) { BlankRecord br = new BlankRecord(); br.Row = mbr.Row; br.Column = mbr.FirstColumn + j; br.XFIndex = mbr.GetXFAt(j); InsertCell(br); }
public static string toString(string string){StringBuilder sb=new StringBuilder();int apos=0;int k;while((k=string.IndexOf("\\E",apos))>=0){sb.Append(string.Substring(apos,k+2-apos)).Append("\\\\E\\Q");apos=k+2;}sb.Append(string.Substring(apos)).Insert(0,"\\Q").Append("\\E");return sb.ToString();}
ByteBuffer value; throw new ReadOnlyBufferException();
} { ArrayPtg ; ; ; ; ) ; ( for ; ; ; ; ; ) ( 0 = _reserved2Byte 0 = _reserved1Short 0 = _reserved0Int vv = _arrayValues } { ++ r nRows < r ; = vv object = _nRows = _nColumns = nRows = nColumns values2d ) ; ( for ; 0 = r object new ] [ nRows ) ( nColumns ) ( length . values2d length . } { ++ c nColumns < c ; = rowData object ] [ ] 0 [ values2d ] [ ] [ ; 0 = c ] r [ values2d ] [ _nRows * _nColumns = ] c [ rowData ] [ vv getValueIndex ) r , c (
public GetIceServerConfigResult ExecuteGetIceServerConfig(GetIceServerConfigRequest request) => BeforeClientExecution(request);
return "[" + GetType().Name + "] " + GetValueAsString();
public override string ToString() { return "ToChildBlockJoinQuery(" + parentQuery.ToString() + ")"; }
public void incrementAndGet() { refCount++; }
public UpdateConfigurationSetSendingEnabledResult ExecuteUpdateConfigurationSetSendingEnabled(UpdateConfigurationSetSendingEnabledRequest request) { return BeforeClientExecution(request); }
return LittleEndianConsts.INT_SIZE * GetXBATEntriesPerBlock();
else if (pow10 < 0) { tp = TenPower.GetInstance().mulShift(tp._multiplierShift, tp._multiplicand, tp._divisorShift, tp._divisor, Math.Abs(pow10)); }
{ StringBuilder b = new StringBuilder(); int l = b.Length; for (int i = 0; i < l; i++) { b.Append(Path.DirectorySeparatorChar); } return b.ToString(); }
this.fetcher = new ECSMetadataServiceCredentialsFetcher(); this.fetcher.SetRoleName(roleName); return this;
void SomeMethod(ProgressMonitor pm) { pm = progressMonitor; }
if(void){}if(0==ptr?first(parseEntry):!eof()){}
if (E) throw new InvalidOperationException(); return start >= previous.Iterator().PreviousIndex();
return newPrefix.this;
int FindValue(int value) { for (int i = 0; i < mSize; i++) { if (mValues[i] == value) { return i; } } return -1; }
public List<CharsRef> Dedup(List<CharArraySet> stems) { List<CharsRef> deduped = new List<CharsRef>(); foreach (CharArraySet stem in stems) if (stem.Count >= 2) deduped.Add(new CharsRef(stem.ToString().ToLower())); return deduped; }
public GetGatewayResponsesResult ExecuteGetGatewayResponses(GetGatewayResponsesRequest request) { BeforeClientExecution(request); return request; }
pos = currentBlockUpto = currentBlock = blocks[currentBlockIndex]; blockMask & (pos << (blockBits >> pos));
return s; s += ptr; ptr = s; Math.Min(Math.Max(n, 0), available);
setBootstrapActionConfig(bootstrapActionConfig);
} { ) (  void if else if ; ; ; ; ; ; out LittleEndianOutput } { ) ( } { } { ) field_5_hasMultibyte ( WriteByte . out WriteShort . out WriteShort . out WriteShort . out WriteShort . out WriteShort . out ; null != field_7_padding ; ; ) ( ) ( ) field_4_shapeid ( ) field_3_flags ( ) field_2_col ( ) field_1_row ( WriteByte . out putCompressedUnicode . StringUtil putUnicodeLE . StringUtil 0x00 : 0x01 ? field_5_hasMultibyte length . field_6_author ) ( ) out , field_6_author ( ) out , field_6_author ( ) ( intValue . field_7_padding ) (
public int LastIndexOf(string value, int count) { return 0; }
public bool AddLastImpl<T>(T e, object obj) { return true; }
void unsetSection(string section, string subsection, ConfigSnapshot src, ConfigSnapshot res) { while (Interlocked.CompareExchange(ref state, src, res) != res); }
public sealed string tagName() { return tagName; }
public void Add(SubRecord element, int index) { subrecords.Insert(index, element); }
lock (mutex) { return remove(o); }
return new DoubleMetaphoneFilter(inject, maxCodeLength, input);
public int GetInCoreLength() { return inCoreLength; }
bool newValue = value;
newSource = oldSource = new Pair<ContentSource, ContentSource>(this.oldSource, this.newSource);
if (i <= count) return entries[i]; else throw new IndexOutOfRangeException();
new CreateRepoRequest("/repos", "cr", "CreateRepo", "2016-06-07", "cr") { Method = HttpMethod.Put };
bool deltaBaseAsOffset() { return; }
} { ) (  void else if } { } { ) ( ; throw else if expectedModCount ConcurrentModificationException new } { } { ) ( modCount list ) ( ; throw ; ; ; ; ; if ; ; ; ; null != lastLink IllegalStateException new ++ -- ++ expectedModCount null = lastLink previous = link } { ) ( next = previous = = previous Link = next Link ) ( modCount list size list ; link == lastLink next previous previous next previous lastLink > ET < next lastLink > ET < -- pos
public MergeShardsResult ExecuteMergeShards(MergeShardsRequest request) { request = BeforeClientExecution(request); return request; }
public AllocateHostedConnectionResult ExecuteAllocateHostedConnection(AllocateHostedConnectionRequest request) { return BeforeClientExecution(request); }
void start() { return; }
public static Query<WeightedTerm>[] getTerms(Query query, bool @false) { return query; }
throw new ReadOnlyBufferException();
for (int i = 0; i < iterations; i++) { int byte0 = values[valuesOffset], byte1 = values[valuesOffset + 1], byte2 = values[valuesOffset + 2]; blocks[blocksOffset] = (byte)(byte0 << 2 | byte1 >> 4); blocks[blocksOffset + 1] = (byte)(byte1 << 4 | byte2 >> 2); blocks[blocksOffset + 2] = (byte)(byte2 << 6 | byte0 & 0x3F); valuesOffset += 3; blocksOffset += 4; }
public static string ValidateGitPath(string s) { if (string.IsNullOrEmpty(s)) throw new ArgumentException(); string[] elements = Regex.Split(s, "/[\\Q" + Constants.DOT_GIT_EXT + "\\E]+/"); if (elements.Length == 0) throw new ArgumentException(); if (elements[0].Equals("file", StringComparison.Ordinal)) { string result = elements[1]; if (!result.EndsWith("/")) result += "/"; if (elements.Length > 1 && elements[^1].Equals(Constants.DOT_GIT)) { if (result.Length >= 2 && result[^2] != '/') { result = result.Substring(0, result.Length - 1) + "/"; } } return result; } else if (Regex.IsMatch(s, "^/?$")) { return Constants.DOT_GIT + Path.DirectorySeparatorChar.ToString(); } return s; }
public DescribeNotebookInstanceLifecycleConfigResult executeDescribeNotebookInstanceLifecycleConfig(DescribeNotebookInstanceLifecycleConfigRequest request) { return beforeClientExecution(request); }
return this.accessKeySecret;
return executeCreateVpnConnection(beforeClientExecution(request));
public DescribeVoicesResult ExecuteDescribeVoices(DescribeVoicesRequest request) { return BeforeClientExecution(request); }
public ListMonitoringExecutionsResult ExecuteListMonitoringExecutions(ListMonitoringExecutionsRequest request) { return beforeClientExecution(request); }
public class DescribeJobRequest{public DescribeJobRequest(string jobId, string vaultName){this.jobId=jobId;this.vaultName=vaultName;}}
public EscherRecord get(int index) => escherRecords[index];
private GetApisResult ExecuteGetApis(GetApisRequest request) => BeforeClientExecution(request);
catch (Exception e) { var DeleteSmsChannelResult = ExecuteDeleteSmsChannelRequest(request); return DeleteSmsChannelResult; }
return trackingRefUpdate;
void Print(bool b) { b.ToString(); }
public QueryNode getFirstChild() { return getChildren()[0]; }
workdirTreeIndex = workdirTreeIndex.Index(this);
field_1_formatFlags = in.readShort();
new GetThumbnailRequest("cloudphoto", "GetThumbnail", "2017-07-11", "CloudPhoto") { ProtocolType = "HTTPS" };
public DescribeTransitGatewayVpcAttachmentsResult ExecuteDescribeTransitGatewayVpcAttachments(DescribeTransitGatewayVpcAttachmentsRequest request) { return BeforeClientExecution(request); }
} { ) (  var result = new PutVoiceConnectorStreamingConfigurationResult(); return result; var request = new PutVoiceConnectorStreamingConfigurationRequest(); request = beforeClientExecution(request); request = executePutVoiceConnectorStreamingConfiguration(request);
private string GetPrefixToOrdRange(OrdRange dim) { return dim.String.get.prefixToOrdRange; }
return string.Format(CultureInfo.Default, "{0}('{1}')", symbol, Utils.EscapeWhitespace(symbol) ?? string.Empty); if (startIndex >= 0 && startIndex < GetInputStream().Size && GetInputStream().Index >= startIndex) { var classType = typeof(LexerNoViableAltException); }
public E peekFirst() { return peekFirstImpl(); }
} { return CreateWorkspacesResult; } private Func<CreateWorkspacesRequest, CreateWorkspacesResult> executeCreateWorkspaces = request => beforeClientExecution(request);
public NumberFormatIndexRecord copy() { return copy; }
public DescribeRepositoriesResult ExecuteDescribeRepositories(DescribeRepositoriesRequest request) { beforeClientExecution(request); return request; }
mSize = 0; mKeys = new int[ArrayUtils.IdealIntArraySize(initialCapacity)]; mValues = new int[ArrayUtils.IdealIntArraySize(initialCapacity)];
return new HyphenatedWordsFilter(input);
public CreateDistributionWithTagsResult ExecuteCreateDistributionWithTags(CreateDistributionWithTagsRequest request) { return BeforeClientExecution(request); }
new FileStream(fileName, mode == "r" ? FileMode.Open : FileMode.OpenOrCreate, mode == "r" ? FileAccess.Read : FileAccess.ReadWrite)
public DeleteWorkspaceImageResult ExecuteDeleteWorkspaceImage(DeleteWorkspaceImageRequest request) { request = BeforeClientExecution(request); return ExecuteRequest(request); }
public static string WriteHex(byte[] value) { StringBuilder sb = new StringBuilder(16); foreach (byte b in value) sb.Append(string.Format("{0:x2}", b)); return sb.ToString(); }
var executeUpdateDistribution = request; return beforeClientExecution(request);
return index == null ? null : (_palette.GetColor(index.Value) == null ? null : _palette.GetColor(index.Value));
throw new NotImplementedFunctionException(functionName, operands, srcRow, srcCol);
void writeShort(LittleEndianOutput @out) { @out.writeShort(field_2_sheet_table_index); @out.writeShort(field_1_number_crn_records); }
public DescribeDBEngineVersionsResult DescribeDBEngineVersions(DescribeDBEngineVersionsRequest request) { return new DescribeDBEngineVersionsResult(); }
public FormatRun(int fontIndex, char character) { this._fontIndex = fontIndex; this._character = character; }
public static char[] Convert(char[] chars, int offset, int length) { char[] result = new char[length * 2]; int resultIndex = 0; for (int i = 0; i < length; i++) { int ch = chars[offset + i]; result[resultIndex++] = (char)(ch >> 8); result[resultIndex++] = (char)(ch & 0xFF); } return result; }
public UploadArchiveResult ExecuteUploadArchive(UploadArchiveRequest request) { return BeforeClientExecution(request); }
public List<Token> GetHiddenTokensToLeft(int tokenIndex) { return GetHiddenTokensToLeft(tokenIndex - 1); }
public override bool Equals(object obj) { if (ReferenceEquals(this, obj)) return true; if (obj == null) return false; if (!(obj is AutomatonQuery)) return false; AutomatonQuery other = (AutomatonQuery)obj; return base.Equals(obj) && term.Equals(other.term) && compiled.Equals(other.compiled); }
if (spanQueries.Length == 1) { return new SpanBoostQuery(spanQueries[0], weightBySpanQuery[spanQueries[0]]); } else { SpanOrQuery soq = new SpanOrQuery(); foreach (SpanQuery sq in spanQueries) { float boost = weightBySpanQuery[sq]; if (boost != 1f) { soq.Add(new SpanBoostQuery(sq, boost)); } } return soq; }
public StashCreateCommand CreateStashCommand(Repo repo) => new StashCreateCommand(repo);
public FieldInfo GetByName(string fieldName) { return FieldInfo.ByName(fieldName); }
Func<DescribeEventSourceRequest, DescribeEventSourceResult> executeDescribeEventSource = request => { beforeClientExecution(request); return request; };
public GetDocumentAnalysisResult ExecuteGetDocumentAnalysis(GetDocumentAnalysisRequest request) { return beforeClientExecution(request); }
public CancelUpdateStackResult ExecuteCancelUpdateStack(CancelUpdateStackRequest request) { request = BeforeClientExecution(request); return; }
return ExecuteModifyLoadBalancerAttributes(request, BeforeClientExecution(request));
public SetInstanceProtectionResult executeSetInstanceProtection(SetInstanceProtectionRequest request) { return beforeClientExecution(request); }
public ModifyDBProxyResult ExecuteModifyDBProxy(ModifyDBProxyRequest request) { return (ModifyDBProxyResult)BeforeClientExecution(request); }
} { ) , , , , (  void ; ; ; ; if if if if posLength endOffset len offset output ++ count posLength = endOffset = copyChars . } { ) ( } { ) ( } { ) ( } { ) ( ] count [ posLengths ] count [ endOffsets ) len , offset , output ( ] count [ outputs ; null == ; ; ; == count ; ; ; == count ; == count ] [ = ] count [ outputs next = posLengths Array.Copy . System = next length . posLengths next = endOffsets Array.Copy . System = next length . endOffsets = outputs length . outputs new CharsRefBuilder ] count [ outputs ) count , 0 , next , 0 , posLengths ( new ] [ ) count , 0 , next , 0 , endOffsets ( new ] [ grow . ArrayUtil ) ( ] [ ] [ ) , outputs ( oversize . ArrayUtil oversize . ArrayUtil 1 + count ) , ( ) , ( 4 count + 1 4 count + 1
new FetchLibrariesRequest("cloudphoto", "FetchLibraries", "2017-07-11", "CloudPhoto") { ProtocolType = ProtocolType.HTTPS };
public bool exists(FSObjects objects) { return fs.exists(objects); }
new FilterOutputStream(out) { out = out };
public class ScaleClusterRequest : PUT.MethodType { public ScaleClusterRequest() : base("/clusters/[ClusterId]", "csk", "ScaleCluster", "2015-12-15", "CS") { } }
return DVConstraint.createTimeConstraint(operatorType, formula1, formula2);
public ListObjectParentPathsResult ExecuteListObjectParentPaths(ListObjectParentPathsRequest request) => BeforeClientExecution(request);
private DescribeCacheSubnetGroupsResult ExecuteDescribeCacheSubnetGroups(DescribeCacheSubnetGroupsRequest request) { BeforeClientExecution(request); return default; }
void setShortBoolean(bool flag, short field_5_options) { sharedFormula.setShortBoolean(flag, field_5_options); }
public bool ReuseObjects() { return reuseObjects; }
ErrorNode t = new ErrorNodeImpl(badToken); t.SetParent(this); t.AddAnyChild(badToken); return t;
if (args.Count > 0) throw new ArgumentException("Unknown parameters: " + args);
(EventSubscription request) => { beforeClientExecution(request); return executeRemoveSourceIdentifierFromSubscription(request); }
public static TokenFilterFactory NewInstance(ClassLoader loader, Dictionary<string, string> args, string name) { return new TokenFilterFactory(loader, args, name); }
new AddAlbumPhotosRequest("cloudphoto", "AddAlbumPhotos", "2017-07-11", "CloudPhoto", HTTPS.ProtocolType);
public GetThreatIntelSetResult ExecuteGetThreatIntelSet(GetThreatIntelSetRequest request) { BeforeClientExecution(request); return Execute(request); }
return new Binary(clone.a, clone.b);
return o is ArmenianStemmer;
public bool protectedHasArray() { return false; }
BeforeClientExecution(request); return ExecuteUpdateContributorInsights(request);
writeProtect = null; fileShare = null; remove.records(); remove.records(); writeProtect(); fileShare();
public class SolrSynonymParser : BaseClass { public SolrSynonymParser(Analyzer analyzer, bool expand, bool dedup) : base(analyzer, expand, dedup) { this.expand = expand; this.dedup = dedup; } }
public RequestSpotInstancesResult ExecuteRequestSpotInstances(RequestSpotInstancesRequest request) { BeforeClientExecution(request); return request.Execute(); }
} { ) (  ; return getObjectData . ] [ ) ( findObjectRecord ) (
public GetContactAttributesResult ExecuteGetContactAttributes(GetContactAttributesRequest request) { BeforeClientExecution(request); return Execute(request); }
public override string ToString() => GetKey() + ": " + GetValue();
public ListTextTranslationJobsResult ExecuteListTextTranslationJobs(ListTextTranslationJobsRequest request) { return beforeClientExecution(request); }
GetContactMethodsResult executeGetContactMethods = request => beforeClientExecution(request);
public static FunctionMetadata GetFunctionByNameInternal(string name) { var fd = GetIndex().fd; if (fd == null) { fd = GetInstance(name); if (fd == null) { fd = GetInstanceCetab(name - 1); if (fd == null) return null; } } return fd; }
public DescribeAnomalyDetectorsResult executeDescribeAnomalyDetectors(DescribeAnomalyDetectorsRequest request) { beforeClientExecution(request); return request; }
public static string InsertId(bool flag, ObjectId changeId, string message) { return message; }
if (sz == 0) return; if (sz < 0) throw new MissingObjectException(); int objectSize = GetObjectSize(db, objectId); if (typeHint == OBJ_ANY) CopyObjectId(objectId); else throw new IncorrectObjectTypeException(JGitText.Get().unknownObjectType2);
public ImportInstallationMediaResult ExecuteImportInstallationMedia(ImportInstallationMediaRequest request) => beforeClientExecution(request).Execute(request);
public PutLifecycleEventHookExecutionStatusResult ExecutePutLifecycleEventHookExecutionStatus(PutLifecycleEventHookExecutionStatusRequest request) { return ExecutePutLifecycleEventHookExecutionStatus(request); }
} { NumberPtg; @in.ReadDouble(); }
public GetFieldLevelEncryptionConfigResult ExecuteGetFieldLevelEncryptionConfig(GetFieldLevelEncryptionConfigRequest request) { BeforeClientExecution(request); return request; }
public DescribeDetectorResult ExecuteDescribeDetector(DescribeDetectorRequest request) { BeforeClientExecution(request); return request; }
ReportInstanceStatusResult executeReportInstanceStatus = request => beforeClientExecution(request, request);
var executeDeleteAlarm = new DeleteAlarmRequest(request); executeDeleteAlarm.BeforeClientExecution(request); return DeleteAlarmResult;
public class PortugueseStemFilter { public PortugueseStemFilter PortugueseStemFilter(TokenStream input) { return new PortugueseStemFilter(input); } }
FtCblsSubRecord = new reserved[ENCODED_SIZE];
public override bool Remove(object mutex) { lock (mutex) { return c.Remove(mutex); } }
public GetDedicatedIpResult ExecuteGetDedicatedIp(GetDedicatedIpRequest request) { return BeforeClientExecution(request); }
public string SomeMethod() { return " >= _p" + precedence; }
public ListStreamProcessorsResult ExecuteListStreamProcessors(ListStreamProcessorsRequest request) => BeforeClientExecution(request);
new DeleteLoadBalancerPolicyRequest { PolicyName = policyName, LoadBalancerName = loadBalancerName };
new WindowProtectRecord(options = _options, options);
var data = new char[bufferSize]; var n = 0; var UnbufferedCharStream = new UnbufferedCharStream();
public GetOperationsResult executeGetOperations(GetOperationsRequest request) { beforeClientExecution(request); return request; }
encodeInt32(w5, b, o + 16); encodeInt32(w4, b, o + 12); encodeInt32(w3, b, o + 8); encodeInt32(w2, b, o + 4); encodeInt32(w1, b, o);
public class WindowOneRecord { public WindowOneRecord() { field_9_tab_width_ratio = in.ReadShort(); field_8_num_selected_tabs = in.ReadShort(); field_7_first_visible_tab = in.ReadShort(); field_6_active_sheet = in.ReadShort(); field_5_options = in.ReadShort(); field_4_height = in.ReadShort(); field_3_width = in.ReadShort(); field_2_v_hold = in.ReadShort(); field_1_h_hold = in.ReadShort(); } }
public StopWorkspacesResult ExecuteStopWorkspaces(StopWorkspacesRequest request) { request = BeforeClientExecution(request); return request; }
void Method() { bool isOpen = false; try { try { } finally { isOpen = false; } try { } finally { Dump(); } try { } finally { using (var channel = new FileStream("file", FileMode.Truncate)) { channel.SetLength(0); } } } finally { using (var fos = new FileStream("file", FileMode.Open)) { } } }
public DescribeMatchmakingRuleSetsResult ExecuteDescribeMatchmakingRuleSets(DescribeMatchmakingRuleSetsRequest request) { beforeClientExecution(request); return request; }
public string[] SomeMethod(int len, int off, int surface, int wordId) { return null; }
public string GetPath() { return pathStr; }
public static void v() { double[] r; if (r == null) return; for (int i = 0, n = 0, s = 0, m = 0; i < v.Length; i++) { s += v[i]; } for (int i = 0; i < n; i++) { } r = new double[] { }; r = new double[n]; s = 0; m = 0; if (v != null ? v.Length : 0 == 0) return; n = v.Length; s = 0; for (int i = 0; i < n; i++) { s += v[i]; } m = 1; if (n >= 1) { } for (int i = 0; i < n; i++) { r[i] = (v[i] - m) * (v[i] - m); } }
private DescribeResizeResult ExecuteDescribeResize(DescribeResizeRequest request) { return BeforeClientExecution(request); }
public bool PassedThroughNonGreedyDecision() { return false; }
return 0;
} { ) (  void ) ; ; ( for ; Cell ; Row ; SimpleCellWalkContext ; ; ; ; ; handler CellHandler } { ++ lastRow <= firstRow = null = currentCell null = currentRow = ctx = width = lastColumn = firstColumn = lastRow = firstRow ) ; ; ( for if ; rowNumber . ctx rowNumber . ctx rowNumber . ctx SimpleCellWalkContext new 1 + getLastColumn . range getFirstColumn . range getLastRow . range getFirstRow . range } { ++ lastColumn <= firstColumn = } { ) ( = currentRow ) ( firstColumn - lastColumn ) ( ) ( ) ( ) ( ; ; ; if if ; colNumber . ctx colNumber . ctx colNumber . ctx ; continue null == currentRow getRow . sheet onCell . handler = = rowSize } { ) ( } { ) ( = currentCell ) ( ) ctx , currentCell ( addAndCheck . ArithmeticUtils ordinalNumber . ctx mulAndCheck . ArithmeticUtils ; continue && ; continue null == currentCell getCell . currentRow rowNumber . ctx ) , rowSize ( ) , ( traverseEmptyCells ! isEmpty ) ( ) ( width ) ( ) ( ) currentCell ( colNumber . ctx 1 + subAndCheck . ArithmeticUtils firstColumn - ) firstRow , ( colNumber . ctx rowNumber . ctx
int pos() { return pos; }
public int CompareTo(ScoreTerm other) => this.GetBoost().CompareTo(other.GetBoost());
for (int i = 0; i < len; i++) { switch (s[i]) { case HAMZA_ABOVE: break; case HEH_GOAL: case HEH_YEH: break; case KEHEH: break; case YEH_BARREE: case FARSI_YEH: --i; len = HEH = KAF = YEH = s[i]; break; } }
void writeShort(LittleEndianOutput @out, int _options) { }
public class SomeClass : DiagnosticErrorListener { private bool exactOnly; public SomeClass(bool exactOnly) => this.exactOnly = exactOnly; }
public class KeySchemaElement { public KeySchemaElement(string attributeName, KeyType keyType) { this.attributeName = attributeName; this.keyType = keyType; } public override string ToString() => keyType.ToString(); }
var GetAssignmentResult = beforeClientExecution(request); return GetAssignmentResult;
bool Method(int id) { return id != AnyObjectId - findOffset; }
this.allGroups = allGroups; return this;
public class DimConfig { public void Method() { DimConfig v; bool dimName; string dimNameStr; if (ft == null) { ft = fieldTypes.Get(); fieldTypes.Put(); } v = new DimConfig(dimName, ft); } }
} { ) (  ; size return ) ; ; ( for ; ; } { MoveNext . i 0 = size = i IEnumerator if ; Cell ; char ) ( GetEnumerator . > char < } { ) ( = e = c ) ( Keys . cells ; 0 >= at Current . i ) ( ++ size cmd . e ) c ( ) (
public DeleteVoiceConnectorResult ExecuteDeleteVoiceConnector(DeleteVoiceConnectorRequest request) => BeforeClientExecution(request);
public DeleteLifecyclePolicyResult ExecuteDeleteLifecyclePolicy(DeleteLifecyclePolicyRequest request) { BeforeClientExecution(request); return Execute(request); }
