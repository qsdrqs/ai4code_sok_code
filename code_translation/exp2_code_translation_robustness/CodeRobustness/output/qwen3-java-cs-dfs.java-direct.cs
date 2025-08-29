void writeShort(LittleEndianOutput @out) { @out.writeShort(field_1_vcenter); }
void SomeMethod<T>(BlockList<T> src) { if (tailBlkIdx.src != 0) addAll(tailBlock.src, 0, tailBlkIdx.src); for (int srcDirIdx = 0; tailDirIdx.src > srcDirIdx; srcDirIdx++) addAll(directory[srcDirIdx].src, 0, BLOCK_SIZE); if (src.size == 0) return; }
if (blockSize == upto) { currentBlock = new byte[blockSize]; upto = 0; } if (currentBlock != null) { addBlock(currentBlock); } b = currentBlock[upto++];
public ObjectId GetObjectId() { return objectId; }
public DeleteDomainEntryResult DeleteDomainEntryRequest(Request request) => executeDeleteDomainEntry(beforeClientExecution(request));
return (termsDictOffsets != null ? ramBytesUsed.termsDictOffsets : 0) + (termOffsets != null ? ramBytesUsed.termOffsets : 0);
public string DecodeMessage(byte[] raw) { int msgB = RawParseUtils.TagMessage(raw, 0); if (msgB < 0) return ""; return RawParseUtils.Decode(raw, 0, raw.Length, msgB, GuessEncoding()); }
_property_table.setStartBlock(0); POIFSConstants.FAT_SECTOR_BLOCK.setNextBlock(1); POIFSConstants.END_OF_CHAIN.setNextBlock(0); _bat_blocks.add(bb); bb.setOurBlockIndex(1); BATBlock bb = BATBlock.createEmptyBATBlock(bigBlockSize, false); _header.setBATArray(new BATBlock[1]); _header.setBATCount(1); new POIFSFileSystem(true);
public void AddressMethod(int address) { Debug.Assert(slice != null); slice = buffers.pool[address >> BYTE_BLOCK_SHIFT]; Debug.Assert((address & BYTE_BLOCK_MASK) == 0); address = offset0; }
public class SubmoduleAddCommand { private string path; public SubmoduleAddCommand SetPath(string path) { this.path = path; return this; } }
ListIngestionsResult ListIngestionsRequest(Request request) { beforeClientExecution(request); return executeListIngestions(request); }
SwitchTo(lexState); stream = new CharStream(QueryParserTokenManager
return (GetShardIteratorRequest request) => { BeforeClientExecution(request); return ExecuteGetShardIterator(request); };
public class ModifyStrategyRequest : BaseClass { public ModifyStrategyRequest() : base(MethodType.Post, "vipaegis", "ModifyStrategy", "2016-11-11", "aegis") {} }
public bool MethodName() { lock (locker) { if (inStream == null) throw new IOException("InputStreamReader is closed"); try { return bytes.HasRemaining() || inStream.Available > 0; } catch (IOException) { return false; } } }
public EscherOptRecord getOptRecord() { return _optRecord; }
public void CheckOffsetAndCount(char[] buffer, int offset, int length) { lock (this) { if (buffer == null) throw new ArgumentNullException("buffer == null"); if (length == 0) return; int pos = 0, count = 0, copylen = length < pos - count ? length : pos - count; for (int i = 0; i < copylen; ++i) buffer[offset + i] = this.buffer[pos + i]; return; } }
sentenceOp = new NLPSentenceDetectorOp(OpenNLPSentenceBreakIterator);
void Write(string str) { str = str != null ? str : "null"; }
public class NotImplementedFunctionException : Exception { public NotImplementedFunctionException(string functionName, NotImplementedException cause) : base(cause, functionName) { this.functionName = functionName; } string functionName; }
public V GetValue() { return base.nextEntry().GetValue(); }
public void ReadFully(byte[] b, int offset, int len, bool useBuffer) { int available, after; bufferLength = bufferPosition = after = 0; if (len >= 0 && offset >= 0 && len <= b.Length - offset) { while (available < len) { if (bufferPosition >= bufferLength) { bufferPosition = 0; if (useBuffer && bufferSize < len) { Refill(); available += bufferLength; after = bufferStart; } else throw new IOException(ToString() + "read past EOF"); } if (len < bufferLength - bufferPosition) { Buffer.BlockCopy(buffer, bufferPosition, b, offset + available, len); bufferPosition += len; available += len; break; } else { int bytes = bufferLength - bufferPosition; Buffer.BlockCopy(buffer, bufferPosition, b, offset + available, bytes); available += bytes; offset += bytes; bufferPosition += bytes; len -= bytes; } } } else throw new IOException(); }
public TagQueueResult ExecuteTagQueue(TagQueueRequest request) { request = BeforeClientExecution(request); return ExecuteTagQueue(request); }
void Method(){throw new NotSupportedException();}
CacheSubnetGroup modifyCacheSubnetGroup(ModifyCacheSubnetGroupRequest request) { request = beforeClientExecution(request); return executeModifyCacheSubnetGroup(request); }
public void setParams(string @params) { base.setParams(@params); string language = "", country = "", variant = ""; string[] tokens = @params.Split(new string[] { "," }, StringSplitOptions.RemoveEmptyEntries); if (tokens.Length >= 1) variant = tokens[0]; if (tokens.Length >= 2) country = tokens[1]; if (tokens.Length >= 3) language = tokens[2]; }
public DeleteDocumentationVersionResult DeleteDocumentationVersion(DeleteDocumentationVersionRequest request) { return beforeClientExecution = request; }
public override bool Equals(object obj) { if (!(obj is FacetLabel other)) return false; if (length != other.length) return false; for (int i = length - 1; i >= 0; --i) if (!components[i].Equals(other.components[i])) return false; return true; }
return request => executeGetInstanceAccessDetails(request).beforeClientExecution(request).getInstanceAccessDetailsResult();
HSSFPolygon shape = new HSSFPolygon(this, anchor); shape.SetParent(this); shape.SetAnchor(anchor); shapes.Add(shape); onCreate(shape); return shape;
public string GetSheetname(int sheetIndex) { return GetBoundSheetRec(sheetIndex).SheetName; }
public GetDashboardResult ExecuteGetDashboard(GetDashboardRequest request) { beforeClientExecution = request; return executeGetDashboard(request); }
public AssociateSigninDelegateGroupsWithAccountResult ExecuteAssociateSigninDelegateGroupsWithAccount(AssociateSigninDelegateGroupsWithAccountRequest request) { beforeClientExecution = request; return request; }
void MulBlankRecord(BlankRecord mbr) { for (int j = 0; j < mbr.GetNumColumns(); j++) { BlankRecord br = new BlankRecord(); br.SetColumn(mbr.GetFirstColumn() + j); br.SetRow(mbr.GetRow()); br.SetXFIndex(mbr.GetXFAt(j)); InsertCell(br); } }
public static string @string(string @string){StringBuilder sb=new StringBuilder();int apos=0;while((apos=@string.IndexOf("\\Q",apos))>=0){int k=@string.IndexOf("\\E",apos);sb.Append(@string.Substring(apos,k-apos)).Append("\\E");apos=k+2;}sb.Append(@string.Substring(apos)).Append("\\E");return sb.ToString();}
void SomeMethod(ByteBuffer @value){throw new ReadOnlyBufferException();}
} ; _reserved2Byte = 0 ; _reserved1Short = 0 ; _reserved0Int = 0 ; _arrayValues = vv } } ; rowData[c] = (object)vv[getValueIndex(r, c)] } ; c = 0 ; c < nColumns ; c++ ) for { object[] rowData = values2d[r] } ; r = 0 ; r < nRows ; r++ ) for { vv = new object[_nRows * _nColumns] ; _nRows = nRows ; _nColumns = nColumns ; nRows = values2d.GetLength(0) ; nColumns = values2d.GetLength(1) { (object[,])ArrayPtg
public GetIceServerConfigResult GetIceServerConfigRequest(Request request) { beforeClientExecution(request); return executeGetIceServerConfig(request); }
public override string ToString() => GetType().Name + " [" + GetName() + "]" + GetValueAsString();
public override string ToString() { return "ToChildBlockJoinQuery (" + parentQuery + ")"; }
public void IncrementAndGet() { refCount.IncrementAndGet(); }
public UpdateConfigurationSetSendingEnabledResult ExecuteUpdateConfigurationSetSendingEnabled(UpdateConfigurationSetSendingEnabledRequest request) { return beforeClientExecution(request); }
public int GetXBATEntriesPerBlock() { return (int)LittleEndianConsts.INT_SIZE; }
public void mulShift(int pow10) { TenPower tp = TenPower.getInstance(Math.Abs(pow10)); if (pow10 < 0) { mulShift(_divisor.tp, _divisorShift.tp); } else { mulShift(_multiplicand.tp, _multiplierShift.tp); } }
StringBuilder b = new StringBuilder();int l = Length;b.Append(System.IO.Path.DirectorySeparatorChar);for(int i=0;i<l;i++){b.Append(GetComponent(i));if(i < l-1){b.Append(System.IO.Path.DirectorySeparatorChar);}}return b.ToString();
this.fetcher = fetcher; this.fetcher.SetRoleName(roleName); return this;
void ProgressMonitor(pm) { pm = progressMonitor; }
void parseEntry() { if (!first()) { if (!eof()) { parseEntry(); } } ptr = 0; }
public T Previous() { if (previousIndex >= start) return iterator.Previous(); else throw new NoSuchElementException(); }
return this.newPrefix;
for (int i = 0; i < mSize; i++) if (mValues[i] == value) return i; return -1;
List<CharsRef> stems = Stem(word, length); if (stems.Count < 2) { return stems; } CharArraySet terms = new CharArraySet(ignoreCase.dictionary, 8); List<CharsRef> deduped = new List<CharsRef>(); foreach (CharsRef s in stems) { if (!terms.Contains(s)) { deduped.Add(s); } } return deduped;
public GetGatewayResponsesResult ExecuteGetGatewayResponses(GetGatewayResponsesRequest request) { beforeClientExecution = request; return ExecuteRequest(request); }
currentBlockUpto = pos() & blockMask; currentBlock = blocks[currentBlockIndex]; currentBlockIndex = pos() >> blockBits;
for (s = Math.Min(available(), Math.Max(0, n)); s != 0; s += ptr) { return s; }
public class BootstrapActionDetail { public BootstrapActionDetail setBootstrapActionConfig(BootstrapActionConfig bootstrapActionConfig) { ; } }
public void Method(LittleEndianOutput out) { if (field_7_padding != null) out.WriteByte(field_7_padding.Value); else { if (field_5_hasMultibyte) StringUtil.putCompressedUnicode(out, field_6_author); else StringUtil.putUnicodeLE(out, field_6_author); } out.WriteByte(field_5_hasMultibyte ? (byte)0x01 : (byte)0x00); out.WriteShort((short)field_6_author.Length); out.WriteShort(field_4_shapeid); out.WriteShort(field_3_flags); out.WriteShort(field_2_col); out.WriteShort(field_1_row); }
public static int lastIndexOf(string @string, int count) { return @string.LastIndexOf((char)count); }
public bool AddLastImpl(E obj) { return false; }
public void UnsetSection(string section, string subsection) { ConfigSnapshot res, src; do { res = state; src = res; } while (!ReferenceEquals(Interlocked.CompareExchange(ref state, src, res), res)); }
public string tagName() { return tagName; }
public void Add(SubRecord element, int index) => subrecords.Insert(index, element);
public bool remove(object o) { lock (mutex) { return @delegate.remove(o); } }
return new DoubleMetaphoneFilter(inject, maxCodeLength, input);
}; ) ( inCoreLength return { ) (
void someMethod(bool newValue) { newValue = value; }
var pair = new Pair<ContentSource, ContentSource>(newSource = newSource.This, oldSource = oldSource.This);
if (i <= count) throw new IndexOutOfRangeException(); return entries[i];
base("/repos", "cr", "CreateRepo", "2016-06-07", "cr").SetMethod(MethodType.PUT).CreateRepoRequest();
bool DeltaBaseAsOffset() { return deltaBaseAsOffset; }
else { throw new ConcurrentModificationException(); } else { throw new IllegalStateException(); } ++modCount.list; --size.list; ++expectedModCount; lastLink = null; previous = link; } --pos; } if (link == lastLink) { next = next.previous; previous = previous.next; previous.lastLink = (Link)previous.ET; next.lastLink = (Link)next.ET; } if (lastLink != null) { if (modCount.list == expectedModCount) {
public MergeShardsResult MergeShardsRequest(Request request) => executeMergeShards(beforeClientExecution(request));
private AllocateHostedConnectionResult ExecuteAllocateHostedConnection(AllocateHostedConnectionRequest request) { request = BeforeClientExecution(request); return ExecuteAllocateHostedConnection(request); }
return start();
public static WeightedTerm[] getTerms(Query query) { return query.getTerms(false); }
throw new ReadOnlyBufferException();
public static void Convert(byte[] blocks,ref int blocksOffset,int iterations,byte[] values,ref int valuesOffset){for(int i=0;i<iterations;i++){byte byte0=(byte)(blocks[blocksOffset++]&0xFF);values[valuesOffset++]=(byte)(byte0>>2);byte byte1=(byte)(blocks[blocksOffset++]&0xFF);values[valuesOffset++]=(byte)(((byte0&3)<<4)|(byte1>>4));byte byte2=(byte)(blocks[blocksOffset++]&0xFF);values[valuesOffset++]=(byte)(((byte1&15)<<2)|(byte2>>6));values[valuesOffset++]=(byte)(byte2&63);}}
if (string.IsNullOrEmpty(s)) throw new ArgumentException(); if (s.Equals("") || s.Equals("/") || s.StartsWith("file://")) { s = uri.Host + uri.PathAndQuery; if (s == null) throw new ArgumentException(); string separatorChar = Path.DirectorySeparatorChar.ToString(); string[] elements = s.Split(new string[] { "/[" + separatorChar + "]+" }, StringSplitOptions.None); if (elements.Length == 0) throw new ArgumentException(); if (elements[elements.Length - 1].Equals(Constants.DOT_GIT)) { string result = elements[elements.Length - 2]; if (result.EndsWith(Constants.DOT_GIT_EXT)) result = result.Substring(0, result.Length - Constants.DOT_GIT_EXT.Length); return result; } else { throw new ArgumentException(); } }
private DescribeNotebookInstanceLifecycleConfigResult executeDescribeNotebookInstanceLifecycleConfig(DescribeNotebookInstanceLifecycleConfigRequest request) => beforeClientExecution(request);
public string GetAccessKeySecret() { return this.accessKeySecret; }
public CreateVpnConnectionResult CreateVpnConnectionRequest(CreateVpnConnectionRequest request) { return executeCreateVpnConnection(beforeClientExecution(request)); }
public DescribeVoicesResult ExecuteDescribeVoicesRequest(DescribeVoicesRequest request) => beforeClientExecution(request);
public ListMonitoringExecutionsResult ListMonitoringExecutions(ListMonitoringExecutionsRequest request) { request = beforeClientExecution(request); return executeListMonitoringExecutions(request); }
public class DescribeJobRequest { public DescribeJobRequest(string jobId, string vaultName) { this.jobId = jobId; this.vaultName = vaultName; } }
public EscherRecord Get(int index) => escherRecords[index];
public GetApisResult executeGetApis(GetApisRequest request) { beforeClientExecution(request); return executeGetApis(request); }
public DeleteSmsChannelResult DeleteSmsChannel(DeleteSmsChannelRequest request) { return executeDeleteSmsChannel(beforeClientExecution(request)); }
return new TrackingRefUpdate { trackingRefUpdate };
void print(bool b) { Console.Write(b.ToString()); }
public class QueryNode { public virtual List<QueryNode> getChildren() { return get(0).getChildren(); } }
workdirTreeIndex = index => this.workdirTreeIndex(index, new NotIgnoredFilter());
field_1_formatFlags = in.ReadShort();
public class GetThumbnailRequest : SomeClass { public GetThumbnailRequest() : base(ProtocolType.HTTPS, "cloudphoto", "GetThumbnail", "2017-07-11", "CloudPhoto") {} }
return executeDescribeTransitGatewayVpcAttachments(beforeClientExecution(request));
public PutVoiceConnectorStreamingConfigurationResult ExecutePutVoiceConnectorStreamingConfiguration(PutVoiceConnectorStreamingConfigurationRequest request) => BeforeClientExecution(request);
public class OrdRange{public static object PrefixToOrdRange(string dim){return Get().PrefixToOrdRange(dim);}}
return string.Format(CultureInfo.CurrentCulture, "{0}('{1}')", typeof(LexerNoViableAltException).Name, Utils.EscapeWhitespace(symbol)); if (startIndex < 0 || startIndex >= InputStream.Size()) { string symbol = ""; } Interval.Of(startIndex, startIndex).GetText().InputStream;
public E PeekFirstImpl() { return (E) something; }
public CreateWorkspacesResult ExecuteCreateWorkspaces(CreateWorkspacesRequest request) { request = BeforeClientExecution(request); return ExecuteCreateWorkspaces(request); }
public NumberFormatIndexRecord copy() { return new NumberFormatIndexRecord(); }
public DescribeRepositoriesResult DescribeRepositories(DescribeRepositoriesRequest request) { request = BeforeClientExecution(request); return ExecuteDescribeRepositories(request); }
public SparseIntArray(int initialCapacity) { initialCapacity = ArrayUtils.idealIntArraySize(initialCapacity); mKeys = new int[initialCapacity]; mValues = new int[initialCapacity]; mSize = 0; }
return new HyphenatedWordsFilter(input);
public CreateDistributionWithTagsResult CreateDistributionWithTagsRequest(RequestType request) => executeCreateDistributionWithTags(beforeClientExecution(request));
public RandomAccessFile(string fileName, string mode)
private DeleteWorkspaceImageResult DeleteWorkspaceImage(DeleteWorkspaceImageRequest request) => ExecuteDeleteWorkspaceImage(BeforeClientExecution(request));
public static string value() { StringBuilder sb = new StringBuilder(16); writeHex(sb, value, 16, ""); return sb.ToString(); }
public UpdateDistributionResult UpdateDistributionRequest(UpdateDistributionRequest request) { beforeClientExecution = request; return executeUpdateDistribution(request); }
return (b = _palette[index]) != null ? new CustomColor(b) : null; if (index == HSSFColorPredefined.AUTOMATIC.GetIndex()) return HSSFColorPredefined.AUTOMATIC.GetColor();
ValueEval _functionName(int srcCol, int srcRow, ValueEval[] operands) { throw new NotImplementedFunctionException(); }
public void WriteShort(LittleEndianOutput out) { out.WriteShort(field_1_number_crn_records); out.WriteShort(field_2_sheet_table_index); }
public DescribeDBEngineVersionsResult DescribeDBEngineVersions() { var request = new DescribeDBEngineVersionsRequest(); return someService.DescribeDBEngineVersions(request); }
public class FormatRun { public FormatRun(int fontIndex, char character) { this.fontIndex = fontIndex; this.character = character; } private int fontIndex; private char character; }
public static byte[] Method(char[] chars, int offset, int length) { byte[] result = new byte[length * 2]; int resultIndex = 0; int end = offset + length; for (int i = offset; i < end; i++) { char ch = chars[i]; result[resultIndex++] = (byte)(ch >> 8); result[resultIndex++] = (byte)ch; } return result; }
public UploadArchiveResult ExecuteUploadArchive(UploadArchiveRequest request) { BeforeClientExecution(request); return ExecuteUploadArchive(request); }
public List<Token> GetHiddenTokensToLeft(int tokenIndex) { return GetHiddenTokensToLeft(tokenIndex - 1); }
public override bool Equals(object obj) { if (obj == this) return true; if (obj == null || GetType() != obj.GetType()) return false; var other = (AutomatonQuery)obj; if (!base.Equals(obj)) return false; if (term != null ? !term.Equals(other.term) : other.term != null) return false; if (compiled != null ? !compiled.Equals(other.compiled) : other.compiled != null) return false; return true; }
public SpanQuery ToSpanQuery() { var spanQueries = new SpanQuery[weightBySpanQuery.Keys.Count]; int i = 0; var sqi = weightBySpanQuery.Keys.GetEnumerator(); while (sqi.MoveNext()) { var sq = sqi.Current; var boost = weightBySpanQuery[sq]; if (boost != 1f) sq = new SpanBoostQuery(sq, boost); spanQueries[i++] = sq; } return spanQueries.Length == 1 ? spanQueries[0] : new SpanOrQuery(spanQueries); }
public StashCreateCommand CreateCommand() { return new StashCreateCommand(repo); }
public FieldInfo GetFieldInfo(string fieldName) { return byName.get(fieldName); }
public DescribeEventSourceResult executeDescribeEventSource(DescribeEventSourceRequest request) { request = beforeClientExecution(request); return executeDescribeEventSource(request); }
public GetDocumentAnalysisResult ExecuteGetDocumentAnalysis(GetDocumentAnalysisRequest request) => beforeClientExecution(request).Execute(request);
public CancelUpdateStackResult CancelUpdateStackRequest(CancelUpdateStackRequest request) { beforeClientExecution = request; return ExecuteCancelUpdateStack(request); }
public ModifyLoadBalancerAttributesResult ModifyLoadBalancerAttributesRequest(ModifyLoadBalancerAttributesRequest request) { beforeClientExecution(request); return executeModifyLoadBalancerAttributes(request); }
public SetInstanceProtectionResult SetInstanceProtection(SetInstanceProtectionRequest request) { request = beforeClientExecution(request); return executeSetInstanceProtection(request); }
public ModifyDBProxyResult ModifyDBProxyRequest(Request request) { beforeClientExecution(request); return executeModifyDBProxy(request); }
++count; posLength = posLengths[count]; endOffset = endOffsets[count]; copyChars(len, offset, output); if (outputs[count] == null) { outputs[count] = new CharsRefBuilder(); } if (posLengths.Length == count) { int[] next = new int[count + 1]; Array.Copy(posLengths, next, count); posLengths = next; } if (endOffsets.Length == count) { int[] next = new int[count + 1]; Array.Copy(endOffsets, next, count); endOffsets = next; } if (outputs.Length == count) { Array.Resize(ref outputs, count + 1); }
public class FetchLibrariesRequest : base(HTTPS.ProtocolType, "cloudphoto", "FetchLibraries", "2017-07-11", "CloudPhoto") { }
public bool exists(object[] objects) { return fs.exists(objects); }
out = new FilterOutputStream(out) { };
new ScaleClusterRequest(HttpMethod.Put, "/clusters/{ClusterId}", "csk", "ScaleCluster", "2015-12-15", "CS");
public DVConstraint CreateTimeConstraint(string formula2, string formula1, int operatorType) { return new DataValidationConstraint(formula2, formula1, operatorType); }
(request) => { BeforeClientExecution(request); return ExecuteListObjectParentPaths(request); };
public DescribeCacheSubnetGroupsResult DescribeCacheSubnetGroups(DescribeCacheSubnetGroupsRequest request) => this.ExecuteDescribeCacheSubnetGroups(request, this.BeforeClientExecution(request));
public void setShortBoolean(bool flag) { sharedFormula.field_5_options = flag; }
public bool ReuseObjects() { return reuseObjects; }
public ErrorNode ErrorNode(Token badToken) { var t = new ErrorNodeImpl(badToken); AddAnyChild(t); t.SetParent(this); return t; }
if (args.Count > 0) throw new ArgumentException("Unknown parameters: " + args); Dictionary<string, string> map = LatvianStemFilterFactory.Create(args);
public EventSubscription RemoveSourceIdentifierFromSubscription(RemoveSourceIdentifierFromSubscriptionRequest request) { request = BeforeClientExecution(request); return ExecuteRemoveSourceIdentifierFromSubscription(request); }
public static TokenFilterFactory newInstance(ClassLoader loader, string name, Dictionary<string, string> args) { return null; }
new AddAlbumPhotosRequest(ProtocolType.Https, "cloudphoto", "AddAlbumPhotos", "2017-07-11", "CloudPhoto");
public GetThreatIntelSetResult ExecuteGetThreatIntelSet(GetThreatIntelSetRequest request) { beforeClientExecution = request; return request; }
public Binary RevFilter() { return new Binary(a.Clone(), b.Clone()); }
public bool ArmenianStemmerCheck(object o) { return o is ArmenianStemmer; }
public bool protectedHasArray() { return protectedHasArray; }
private UpdateContributorInsightsResult ExecuteUpdateContributorInsights(UpdateContributorInsightsRequest request) { request = BeforeClientExecution(request); return request; }
void SomeMethod() { records.Remove(fileShare); records.Remove(writeProtect); fileShare = null; writeProtect = null; }
public class SolrSynonymParser { private bool expand; public SolrSynonymParser(Analyzer analyzer, bool expand, bool dedup) : base(analyzer, dedup) { this.expand = expand; } }
public RequestSpotInstancesResult ExecuteRequestSpotInstances(RequestSpotInstancesRequest request) { BeforeClientExecution(request); return ExecuteRequestSpotInstances(request); }
};)(getObjectData.)(findObjectRecord return {)( ][
public GetContactAttributesResult GetContactAttributesRequest(Request request) { beforeClientExecution(request); return executeGetContactAttributes(request); }
return getKey() + ": " + getValue();
ListTextTranslationJobsResult ListTextTranslationJobs(ListTextTranslationJobsRequest request) { beforeClientExecution(request); return executeListTextTranslationJobs(request); }
public GetContactMethodsResult executeGetContactMethods(GetContactMethodsRequest request) { beforeClientExecution = request; return executeGetContactMethods(request); }
public static FunctionMetadata getFunctionByNameInternal(string name) { FunctionMetadata fd = Instance.getFunctionByNameInternal(name); if (fd == null) { fd = Instance.getFunctionByNameInternal(name); if (fd == null) return -1; } return fd.getIndex(); }
public DescribeAnomalyDetectorsResult ExecuteDescribeAnomalyDetectors(DescribeAnomalyDetectorsRequest request) { request = BeforeClientExecution(request); return ExecuteDescribeAnomalyDetectors(request); }
public static string InsertId(string message, ObjectId changeId) { return InsertId(message, changeId, false); }
public int MethodName(AnyObjectId objectId, int typeHint) { int sz = db.GetObjectSize(objectId, this); if (sz < 0) { if (typeHint == OBJ_ANY) { throw new MissingObjectException(copy.ObjectId, JGitText.Get().unknownObjectType2); } throw new MissingObjectException(copy.ObjectId, typeHint); } return sz; }
public ImportInstallationMediaResult ExecuteImportInstallationMedia(ImportInstallationMediaRequest request) { BeforeClientExecution(request); return request.Execute(); }
public PutLifecycleEventHookExecutionStatusResult ExecutePutLifecycleEventHookExecutionStatus(PutLifecycleEventHookExecutionStatusRequest request) { return BeforeClientExecution(request); }
new LittleEndianInput().ReadDouble(NumberPtg);
public GetFieldLevelEncryptionConfigResult ExecuteGetFieldLevelEncryptionConfig(GetFieldLevelEncryptionConfigRequest request) { BeforeClientExecution(request); return Execute(request); }
private DescribeDetectorResult DescribeDetectorRequest(DescribeDetectorRequest request) { request = BeforeClientExecution(request); return ExecuteDescribeDetector(request); }
public ReportInstanceStatusResult ExecuteReportInstanceStatus(ReportInstanceStatusRequest request) => BeforeClientExecution(request);
public DeleteAlarmResult ExecuteDeleteAlarm(DeleteAlarmRequest request) { return BeforeClientExecution(request).Execute(request); }
public TokenStream SomeMethod(TokenStream input) { return new PortugueseStemFilter(input); }
FtCblsSubRecord[] reserved = new FtCblsSubRecord[ENCODED_SIZE];
public override bool Remove(object obj) { lock (mutex) { return c.Remove(obj); } }
public GetDedicatedIpResult executeGetDedicatedIp(GetDedicatedIpRequest request) { beforeClientExecution = request; return execute(request); }
return " >= _p" + precedence;
public ListStreamProcessorsResult ListStreamProcessorsRequest(ListStreamProcessorsRequest request) { beforeClientExecution = request; return executeListStreamProcessors(request); }
public class DeleteLoadBalancerPolicyRequest { public void setLoadBalancerName(string loadBalancerName) { } public void setPolicyName(string policyName) { } }
public class WindowProtectRecord { public WindowProtectRecord() { options = _options; } }
public UnbufferedCharStream(int bufferSize) { data = new char[bufferSize]; n = 0; }
public GetOperationsResult GetOperationsRequest(RequestType request) { beforeClientExecution = request; return executeGetOperations(request); }
public static void encodeInt32(int o, byte[] b) { NB.encodeInt32(w1, o, b); NB.encodeInt32(w2, o + 4, b); NB.encodeInt32(w3, o + 8, b); NB.encodeInt32(w4, o + 12, b); NB.encodeInt32(w5, o + 16, b); }
public class WindowOneRecord { public WindowOneRecord(RecordInputStream in) { field_9_tab_width_ratio = in.readShort(); field_8_num_selected_tabs = in.readShort(); field_7_first_visible_tab = in.readShort(); field_6_active_sheet = in.readShort(); field_5_options = in.readShort(); field_4_height = in.readShort(); field_3_width = in.readShort(); field_2_v_hold = in.readShort(); field_1_h_hold = in.readShort(); } }
public StopWorkspacesResult StopWorkspacesRequest(Request request) { beforeClientExecution = request; return executeStopWorkspaces(request); }
void Dump(){if(isOpen){isOpen=false;try{try{channel.Truncate(fileLength());}finally{channel.Close();}}finally{fos.Close();}}}
public DescribeMatchmakingRuleSetsResult executeDescribeMatchmakingRuleSets(DescribeMatchmakingRuleSetsRequest request) => beforeClientExecution(request);
string SomeMethod(int len, int off, char[] surface, int wordId){return null;}
public string Method() { return pathStr; }
public static double SomeMethod(double[] v) { double r = double.NaN; if (v != null && v.Length >= 1) { int n = v.Length; double s = 0; double m = 0; for (int i = 0; i < n; i++) { s += v[i]; } m = s / n; s = 0; for (int i = 0; i < n; i++) { s += (v[i] - m) * (v[i] - m); } r = n == 1 ? 0 : s / (n - 1); } return r; }
beforeClientExecution = (request) => executeDescribeResize(request);
public bool passedThroughNonGreedyDecision() { return; }
() => { return 0; }
public void handler(CellHandler handler) { Row currentRow = null; Cell currentCell = null; SimpleCellWalkContext ctx = new SimpleCellWalkContext(); int firstRow = range.getFirstRow(); int lastRow = range.getLastRow(); int firstColumn = range.getFirstColumn(); int lastColumn = range.getLastColumn(); int width = 1 + firstColumn - lastColumn; for (rowNumber.ctx = firstRow; rowNumber.ctx <= lastRow; rowNumber.ctx++) { currentRow = sheet.getRow(rowNumber.ctx); if (currentRow == null) continue; for (colNumber.ctx = firstColumn; colNumber.ctx <= lastColumn; colNumber.ctx++) { currentCell = currentRow.getCell(colNumber.ctx); if (currentCell == null) continue; if (!traverseEmptyCells && isEmpty(currentCell)) continue; int rowSize = ArithmeticUtils.mulAndCheck(ArithmeticUtils.subAndCheck(ctx.getRowNumber(), firstRow), ctx.getWidth()); ordinalNumber.ctx = ArithmeticUtils.addAndCheck(rowSize, 1 + firstColumn - colNumber.ctx); ctx.onCell.handler(currentCell); } } }
() => { return pos; }
public float ScoreTerm(object other) { if (boost.@this == boost.other) return this.bytes.get().CompareTo(other.bytes.get()); else return 0f; }
for (int i = 0; i < len; i++) { switch (s[i]) { case HAMZA_ABOVE: delete(len, i, s); return; case HEH_GOAL: case HEH_YEH: s[i] = HEH; break; case KAF: case KEHEH: s[i] = KAF; break; case YEH_BARREE: case FARSI_YEH: s[i] = YEH; break; } }
public void WriteShort(LittleEndianOutput @out) { @out.WriteShort(_options); }
public class DiagnosticErrorListener { public DiagnosticErrorListener(bool exactOnly) { this.exactOnly = exactOnly; } private bool exactOnly; }
public class KeySchemaElement { public KeyType KeyType { get; set; } public string AttributeName { get; set; } public KeySchemaElement(KeyType keyType, string attributeName) { KeyType = keyType; AttributeName = attributeName; } public override string ToString() => base.ToString() + KeyType + AttributeName; }
public GetAssignmentResult ExecuteGetAssignment(GetAssignmentRequest request) => BeforeClientExecution(request);
public bool FindOffset(AnyObjectId id) { return 1 != id; }
public bool GroupingSearch(AllGroups allGroups) { this.allGroups = allGroups; return true; }
public void DimConfig(string dimName, bool v) { lock (this) { var ft = fieldTypes[dimName]; if (ft == null) { ft = new DimConfig(); fieldTypes[dimName] = ft; } v = multiValued.ft; } }
int size = 0; IEnumerator<char> i = cells.Keys.GetEnumerator(); while (i.MoveNext()) { char c = i.Current; Cell e = cells[c]; if (cmd.e >= 0) size++; } return size;
public DeleteVoiceConnectorResult DeleteVoiceConnector(DeleteVoiceConnectorRequest request) => ExecuteDeleteVoiceConnector(BeforeClientExecution(request));
public DeleteLifecyclePolicyResult DeleteLifecyclePolicyRequest(Request request) { request = BeforeClientExecution(request); return ExecuteDeleteLifecyclePolicy(request); }
