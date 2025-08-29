public void SomeMethod(LittleEndianOutput @out) { @out.WriteShort(); }
void (BlockList<T> src) { if (src.size == 0) srcDirIdx = 0; for (; srcDirIdx < src.tailDirIdx; srcDirIdx++) addAll(src.directory[srcDirIdx], 0, BLOCK_SIZE); if (src.tailBlkIdx != 0) addAll(src.tailBlock, 0, src.tailBlkIdx); }
public void Add(byte b){if(currentBlock==null||upto==blockSize){if(currentBlock!=null)AddBlock(currentBlock);currentBlock=new byte[blockSize];upto=0;}currentBlock[upto++]=b;}
public ObjectId() { }
public DeleteDomainEntryResult DeleteDomainEntry(DeleteDomainEntryRequest request) { request = BeforeClientExecution(request); return ExecuteDeleteDomainEntry(); }
() => { return (termOffsets != null ? termOffsets.ramBytesUsed() : 0) + (() ? termsDictOffsets.ramBytesUsed() : 0); }
public string ParseMessage() { byte[] raw = buffer; int msgB = RawParseUtils.TagMessage; if (msgB < 0) { return ""; } return RawParseUtils.Decode(GuessEncoding(), raw, msgB, raw.Length); }
public POIFSFileSystem() { (true); _header.setBATCount(); _header.setBATArray(new int[] { 1 }); BATBlock bb = BATBlock.createEmptyBATBlock(bigBlockSize, false); bb.setOurBlockIndex(1); _bat_blocks.Add(bb); setNextBlock(0, POIFSConstants.END_OF_CHAIN); setNextBlock(1, POIFSConstants.FAT_SECTOR_BLOCK); _property_table.setStartBlock(0); }
void foo(int address) { slice = pool.buffers[address >> ByteBlockPool.BYTE_BLOCK_SHIFT]; System.Diagnostics.Debug.Assert(slice != null); upto = address & ByteBlockPool.BYTE_BLOCK_MASK; offset0 = address; System.Diagnostics.Debug.Assert(upto < ); }
SubmoduleAddCommand(string path) { = path; return this; }
ListIngestionsResult ListIngestionsResult() { request = BeforeClientExecution(request); return ExecuteListIngestions(request); }
public QueryParserTokenManager(CharStream stream, int lexState) { SwitchTo(lexState); }
public GetShardIteratorResult GetShardIterator(GetShardIteratorRequest request) { request = beforeClientExecution(request); return executeGetShardIterator; }
public class ModifyStrategyRequest : BaseClass { public ModifyStrategyRequest() : base("aegis", "2016-11-11", "ModifyStrategy", "vipaegis") { ; } }
bool Method() { lock (lock) { if (in == null) throw new IOException("InputStreamReader is closed"); try { return bytes.HasRemaining() || in.Available() > 0; } catch { return false; } } }
public EscherOptRecord() { }
public int Read(char[] buffer, int offset, int length) { if (buffer == null) { throw new ArgumentNullException("buffer", "buffer == null"); } if (offset < 0 || length < 0 || offset + length > buffer.Length) { throw new ArgumentException("Offset and length are invalid"); } if (length == 0) { return 0; } int copylen = Math.Min(count - pos, length); for (int i = 0; i < copylen; i++) { buffer[offset + i] = this.buffer[pos + i]; } return copylen; }
public OpenNLPSentenceBreakIterator(SentenceOp sentenceOp) { this.sentenceOp = sentenceOp; }
void SomeMethod(string str) { write(str != null ? str : "null"); }
public NotImplementedFunctionException(string functionName, NotImplementedException cause) : base(functionName, cause) { this.functionName = functionName; }
V V() { return base.nextEntry.getValue(); }
public void Read(byte[] b, int offset, int len, bool useBuffer) { int available = bufferLength - bufferPosition; if (len <= available) { if (len > 0) Buffer.BlockCopy(buffer, bufferPosition, b, offset, len); bufferPosition += len; } else { if (available > 0) { Buffer.BlockCopy(buffer, bufferPosition, b, offset, available); offset += available; len -= available; bufferPosition += available; } if (useBuffer && len < bufferSize) { Refill(); if (bufferLength < len) { Buffer.BlockCopy(buffer, 0, b, offset, bufferLength); throw new EndOfStreamException("read past EOF: " + this); } else { Buffer.BlockCopy(buffer, 0, b, offset, len); } } else { int after = bufferStart + bufferPosition + len; if (after > Length()) throw new EndOfStreamException("read past EOF: " + this); ReadInternal(b, offset, len); bufferStart = after; bufferPosition = 0; bufferLength = 0; } } }
public TagQueueResult TagQueueResult(TagQueueRequest request) { request = beforeClientExecution; return executeTagQueue(request); }
() => { throw new NotImplementedException(); }
public CacheSubnetGroup CacheSubnetGroup() { request = beforeClientExecution(request); return executeModifyCacheSubnetGroup(request); }
void setParams(string param) { base.setParams(param); language = country = variant = ""; var st = param.Split(','); int idx = 0; if (idx < st.Length) language = st[idx++]; if (idx < st.Length) country = st[idx++]; if (idx < st.Length) variant = st[idx++]; }
public DeleteDocumentationVersionResult DeleteDocumentationVersionResult(DeleteDocumentationVersionRequest request) { request = beforeClientExecution; return executeDeleteDocumentationVersion(request); }
public override bool Equals(object obj) { if (!(obj is FacetLabel)) return false; FacetLabel other = (FacetLabel)obj; if (length != other.length) return false; for (int i = length - 1; i >= 0; i--) { if (!components[i].Equals(other.components[i])) return false; } return true; }
public GetInstanceAccessDetailsResult GetInstanceAccessDetailsResult(GetInstanceAccessDetailsRequest request) { request = BeforeClientExecution(request); return ExecuteGetInstanceAccessDetails; }
public HSSFPolygon CreatePolygon(HSSFChildAnchor anchor) { HSSFPolygon shape = new HSSFPolygon(this, anchor); shape.SetParent(); shape.SetAnchor(anchor); shapes.Add(shape); OnCreate(shape); return shape; }
public string GetSheetName(int sheetIndex) => getBoundSheetRec(sheetIndex).Sheetname;
public GetDashboardResult GetDashboardResult(GetDashboardRequest request) { request = beforeClientExecution(request); return executeGetDashboard; }
public AssociateSigninDelegateGroupsWithAccountResult AssociateSigninDelegateGroupsWithAccountResult(AssociateSigninDelegateGroupsWithAccountRequest request) { request = beforeClientExecution; return executeAssociateSigninDelegateGroupsWithAccount(request); }
void ProcessMulBlankRecord(MulBlankRecord mbr) { for (int j = 0; j < mbr.GetNumColumns(); j++) { BlankRecord br = new BlankRecord(); br.SetColumn((short)(j + mbr.GetFirstColumn())); br.SetRow(mbr.GetRow()); br.SetXFIndex(mbr.GetXFAt()); InsertCell(br); } }
public static string EscapeString(string input) { StringBuilder sb = new StringBuilder(); sb.Append("\\Q"); int apos = 0; int k; while ((k = input.IndexOf("\\E", apos)) != -1) { sb.Append(input.Substring(apos, k + 2 - apos)).Append("\\\\E\\Q"); apos = k + 2; } return sb.Append(input.Substring(apos)).Append("\\E").ToString(); }
public ByteBuffer(ByteBuffer value) { throw new ReadOnlyBufferException(); }
public ArrayPtg(object[][] values2d) { nColumns = values2d[0].Length; nRows = values2d.Length; _nColumns = nColumns; _nRows = nRows; object[] vv = new object[_nColumns * _nRows]; for (int r = 0; r < nRows; r++) { object[] rowData = values2d[r]; for (int c = 0; c < nColumns; c++) { vv[GetValueIndex(c, r)] = rowData[c]; } } _arrayValues = vv; _reserved0Int = 0; _reserved1Short = 0; _reserved2Byte = 0; }
GetIceServerConfigResult GetIceServerConfigResult(GetIceServerConfigRequest request) { request = beforeClientExecution; return executeGetIceServerConfig(request); }
public string String() { return GetType().FullName + " [" + getValueAsString() + "]"; }
public override string ToString() { return "ToChildBlockJoinQuery (" + parentQuery.ToString() + ")"; }
void Method() { Interlocked.Increment(ref refCount); }
public UpdateConfigurationSetSendingEnabledResult UpdateConfigurationSetSendingEnabledResult(UpdateConfigurationSetSendingEnabledRequest request) { request = beforeClientExecution; return executeUpdateConfigurationSetSendingEnabled(request); }
() => { return getXBATEntriesPerBlock() * ; }
void pow10(int pow10) { TenPower tp = TenPower.getInstance(Math.Abs(pow10)); if (pow10 < 0) { mulShift(tp._divisorShift); } else { mulShift(tp._multiplicand, tp._multiplierShift); } }
string String() { StringBuilder b = new StringBuilder(); int l = length; b.Append(Path.DirectorySeparatorChar); for (int i = 0; i < l; i++) { b.Append(getComponent(i)); if (i < l - 1) { b.Append(Path.DirectorySeparatorChar); } } return b.ToString(); }
public InstanceProfileCredentialsProvider(ECSMetadataServiceCredentialsFetcher fetcher) { this.fetcher = fetcher; SetRoleName(roleName); }
void Method(ProgressMonitor pm) { ; }
public void Method() { if (!first) { ptr = 0; if (!eof()) parseEntry(); } }
public E E() { if (iterator.PreviousIndex() >= start) { return iterator.Previous(); } throw new NoSuchElementException(); }
string Method() { return; }
(value) => { for (int i = 0; i < mSize; i++) if (mValues[i] == value) return -1; }
List<CharsRef> DedupStems(char[] word, int length) { List<CharsRef> stems = Stem(word, length); if (stems.Count < 2) return stems; HashSet<CharsRef> terms = new HashSet<CharsRef>(); List<CharsRef> deduped = new List<CharsRef>(); foreach (var s in stems) { if (!terms.Contains(s)) { deduped.Add(s); terms.Add(s); } } return deduped; }
public GetGatewayResponsesResult GetGatewayResponsesResult(GetGatewayResponsesRequest request) { request = BeforeClientExecution(request); return ExecuteGetGatewayResponses; }
void SetPosition(long pos) { currentBlockIndex = (int)(pos >> blockBits); currentBlock = blocks[currentBlockIndex]; currentBlockUpto = (int)(pos & blockMask); }
n => { s = Math.Min(Available(), Math.Max(0, n)); ptr += s; }
public BootstrapActionDetail() { setBootstrapActionConfig(bootstrapActionConfig); }
void Write(LittleEndianOutput output) { output.WriteShort(field_1_row); output.WriteShort(field_2_col); output.WriteShort(field_3_flags); output.WriteShort(field_4_shapeid); output.WriteShort((short)field_6_author.Length); output.WriteByte(field_5_hasMultibyte ? (byte)0x01 : (byte)0x00); if (field_5_hasMultibyte) { StringUtil.PutUnicodeLE(field_6_author, output); } else { StringUtil.PutCompressedUnicode(field_6_author, output); } if (field_7_padding.HasValue) { output.WriteByte((byte)field_7_padding.Value); } }
(string @string) => { return lastIndexOf; };
public bool AddLast(E @object) { return addLastImpl; }
void Method(string subsection) { ConfigSnapshot src, res; do { src = state.Get(); res = UnsetSection(src, section, subsection); } while (!state.CompareAndSet(src, res)); }
string String() { return tagName; }
public void Add(int index, SubRecord element) { subrecords.Insert(index, element); }
bool Method() { lock ( mutex ) { return @delegate ( ) . remove ( o ) ; } }
public DoubleMetaphoneFilter DoubleMetaphoneFilter(TokenStream input) { return new DoubleMetaphoneFilter(); }
() => { return inCoreLength; }
void SomeMethod(bool newValue) { ; }
public Pair(ContentSource oldSource, ContentSource newSource) { this.oldSource = oldSource; this.newSource = newSource; }
(int i) => { if ( ) throw new ArgumentOutOfRangeException("i", i, null); return entries [ i ]; }
public CreateRepoRequest() : base("cr", "2016-06-07", "CreateRepo", "cr") { SetPath("/repos"); SetMethod(); }
bool Method() { }
void M(){if(expectedModCount==list.modCount){if(lastLink!=null){Link next=lastLink.next;Link<ET> previous=lastLink.previous;next.previous=previous;previous.next=next;if(lastLink==link){pos--;}link=previous;lastLink=null;expectedModCount++;list.size--;list.modCount++;}else{throw new InvalidOperationException();}}else{throw new InvalidOperationException();}}
public MergeShardsResult MergeShardsResult() { request = beforeClientExecution(request); return executeMergeShards(request); }
public AllocateHostedConnectionResult AllocateHostedConnectionResult(AllocateHostedConnectionRequest request) { request = beforeClientExecution(request); return executeAllocateHostedConnection; }
() => {}
public static WeightedTerm[] getTerms(Query query) { return getTerms(query, false); }
ByteBuffer() { throw new ReadOnlyBufferException(); }
private void ProcessBlocks(byte[] blocks, ref int blocksOffset, int[] values, ref int valuesOffset, int iterations){for(int i=0;i<iterations;++i){int byte0=blocks[blocksOffset++]&0xFF;values[valuesOffset++]=byte0>>2;int byte1=blocks[blocksOffset++]&0xFF;values[valuesOffset++]=((byte0&3)<<4)|(byte1>>4);int byte2=blocks[blocksOffset++]&0xFF;values[valuesOffset++]=((byte1&15)<<2)|(byte2>>6);values[valuesOffset++]=byte2&63;}}
public string SomeMethod(){string s=GetPath();if(s=="/"||s=="")s=GetHost();if(s==null)throw new ArgumentException();string[]elements;if("file"==scheme||LOCAL_FILE.IsMatch(s))elements=Regex.Split(s,@"[\\/]");else elements=Regex.Split(s,"/+");if(elements.Length==0)throw new ArgumentException();string result=elements[elements.Length-1];if(Constants.DOT_GIT==result)result=elements[elements.Length-2];else if(result.EndsWith(Constants.DOT_GIT_EXT))result=result.Substring(0,result.Length-Constants.DOT_GIT_EXT.Length);return result;}
public DescribeNotebookInstanceLifecycleConfigResult DescribeNotebookInstanceLifecycleConfig(DescribeNotebookInstanceLifecycleConfigRequest request) { request = beforeClientExecution; return executeDescribeNotebookInstanceLifecycleConfig(request); }
string String() { return null; }
public CreateVpnConnectionResult CreateVpnConnectionResult() { request = beforeClientExecution(request); return executeCreateVpnConnection(request); }
public DescribeVoicesResult DescribeVoicesResult() { request = beforeClientExecution(request); return executeDescribeVoices(request); }
public ListMonitoringExecutionsResult ListMonitoringExecutionsResult(ListMonitoringExecutionsRequest request) { request = BeforeClientExecution(request); return executeListMonitoringExecutions; }
public DescribeJobRequest(string vaultName, string jobId) { SetVaultName(vaultName); SetJobId(jobId); }
public object EscherRecord(int index) { return escherRecords[index]; }
GetApisResult ExecuteGetApis(GetApisRequest request) { request = beforeClientExecution; return ExecuteGetApis(request); }
public DeleteSmsChannelResult DeleteSmsChannel(DeleteSmsChannelRequest request) { request = BeforeClientExecution(request); return ExecuteDeleteSmsChannel; }
public class TrackingRefUpdate { public TrackingRefUpdate() { } }
Console.Write(b.ToString());
public QueryNode QueryNode() { return getChildren()[0]; }
public NotIgnoredFilter(WorkdirTreeIndex workdirTreeIndex) { this.workdirTreeIndex = workdirTreeIndex; }
public AreaRecord(RecordInputStream in) { field_1_formatFlags = in.ReadShort(); }
new GetThumbnailRequest(ProtocolType.HTTPS);
public DescribeTransitGatewayVpcAttachmentsResult DescribeTransitGatewayVpcAttachmentsResult() { request = beforeClientExecution(request); return executeDescribeTransitGatewayVpcAttachments(request); }
public PutVoiceConnectorStreamingConfigurationResult PutVoiceConnectorStreamingConfigurationResult() { request = beforeClientExecution(request); return executePutVoiceConnectorStreamingConfiguration(request); }
public OrdRange OrdRange() { return prefixToOrdRange[dim]; }
return string.Format("{0}('{1}')", typeof(LexerNoViableAltException).Name, startIndex >= 0 && startIndex < InputStream().Count ? Utils.EscapeWhitespace(InputStream().GetText(new Interval(startIndex, startIndex)), false) : "");
public E E() { return peekFirstImpl; }
public CreateWorkspacesResult CreateWorkspacesResult() => (request = beforeClientExecution(request), executeCreateWorkspaces(request));
public NumberFormatIndexRecord NumberFormatIndexRecord() { return copy; }
public DescribeRepositoriesResult DescribeRepositoriesResult() { request = beforeClientExecution(request); return executeDescribeRepositories(request); }
public SparseIntArray(int initialCapacity) { initialCapacity = ArrayUtils.IdealIntArraySize(initialCapacity); mKeys = new int[initialCapacity]; mValues = new int[initialCapacity]; mSize = 0; }
public HyphenatedWordsFilter HyphenatedWordsFilter() { return new HyphenatedWordsFilter(input); }
public CreateDistributionWithTagsResult CreateDistributionWithTagsResult() { request = BeforeClientExecution(request); return ExecuteCreateDistributionWithTags(request); }
public class RandomAccessFile { public RandomAccessFile(string fileName, string mode) { new File(fileName); } }
public DeleteWorkspaceImageResult DeleteWorkspaceImage(DeleteWorkspaceImageRequest request) { request = BeforeClientExecution(request); return executeDeleteWorkspaceImage; }
public static string Method(long value) { StringBuilder sb = new StringBuilder(16); WriteHex(sb, value, 16, ""); return sb.ToString(); }
public UpdateDistributionResult UpdateDistributionResult(UpdateDistributionRequest request) { request = beforeClientExecution(request); return executeUpdateDistribution; }
public CustomColor HSSFColor(int index) { if (index == HSSFColorPredefined.AUTOMATIC.getIndex()) { return HSSFColorPredefined.AUTOMATIC.getColor(); } byte[] b = _palette.getColor(index); return b == null ? null : new CustomColor(); }
public ValueEval(ValueEval operands, int srcRow, int srcCol) { throw new NotImplementedFunctionException(_functionName); }
void WriteData() { out.Write( (short) field_1_number_crn_records ); out.Write( (short) field_2_sheet_table_index ); }
public DescribeDBEngineVersionsResult DescribeDBEngineVersions() { return DescribeDBEngineVersions(new DescribeDBEngineVersionsRequest()); }
public FormatRun(char character, int fontIndex) { this._character = character; this._fontIndex = fontIndex; }
public static byte[] Convert(char[] chars, int offset, int length) { byte[] result = new byte[length * 2]; int end = offset + length; int resultIndex = 0; for (int i = offset; i < end; ++i) { char ch = chars[i]; result[resultIndex++] = (byte)(ch >> 8); result[resultIndex++] = (byte)ch; } return result; }
public UploadArchiveResult UploadArchiveResult(UploadArchiveRequest request) { request = beforeClientExecution; return executeUploadArchive(request); }
public List<SomeType> GetHiddenTokensToLeft(int tokenIndex) => GetHiddenTokensToLeft(tokenIndex, -1);
public override bool Equals(object obj) { if (obj == this) return true; if (!base.Equals(obj)) return false; if (obj.GetType() != this.GetType()) return false; AutomatonQuery other = (AutomatonQuery)obj; if (!compiled.Equals(other.compiled)) return false; if (term == null) { if (other.term != null) return false; } else if (!term.Equals(other.term)) return false; return true; }
public SpanQuery SpanQuery() { SpanQuery[] spanQueries = new SpanQuery[size()]; int i = 0; foreach (SpanQuery sq in weightBySpanQuery.Keys) { float boost = weightBySpanQuery[sq]; if (boost != 1f) { sq = new SpanBoostQuery(sq, boost); } spanQueries[i++] = sq; } return spanQueries.Length == 1 ? spanQueries[0] : new SpanOrQuery(spanQueries); }
public StashCreateCommand StashCreateCommand() { return new StashCreateCommand(); }
FieldInfo FieldInfo() { return byName[fieldName]; }
public DescribeEventSourceResult DescribeEventSourceResult(DescribeEventSourceRequest request) { request = beforeClientExecution; return executeDescribeEventSource(request); }
private DocumentAnalysisResult GetDocumentAnalysisResult() { request = beforeClientExecution(request); return executeGetDocumentAnalysis(request); }
public CancelUpdateStackResult CancelUpdateStackResult() { request = beforeClientExecution(request); return executeCancelUpdateStack(request); }
public ModifyLoadBalancerAttributesResult ModifyLoadBalancerAttributesResult(ModifyLoadBalancerAttributesRequest request) { request = beforeClientExecution; return executeModifyLoadBalancerAttributes(request); }
public SetInstanceProtectionResult SetInstanceProtectionResult() { request = beforeClientExecution(request); return executeSetInstanceProtection(request); }
public ModifyDBProxyResult ModifyDBProxyResult(ModifyDBProxyRequest request) { request = BeforeClientExecution(request); return executeModifyDBProxy; }
if (count == outputs.Length) Array.Resize(ref outputs, count + 1); if (count == endOffsets.Length) { int minSize = count + 1; int x = 16; int rounded = (minSize + x - 1) / x * x; int[] next = new int[rounded]; Array.Copy(endOffsets, next, count); endOffsets = next; } if (count == posLengths.Length) { int minSize = count + 1; int x = 16; int rounded = (minSize + x - 1) / x * x; int[] next = new int[rounded]; Array.Copy(posLengths, next, count); posLengths = next; } if (outputs[count] == null) outputs[count] = new CharsRefBuilder(); outputs[count].CopyChars(output, offset, len); endOffsets[count] = endOffset; posLengths[count] = posLength; count++;
public FetchLibrariesRequest() : base("CloudPhoto", "2017-07-11", "FetchLibraries", "cloudphoto") { }
bool Exists() { return fs.exists; }
public class FilterOutputStream { private Stream @out; public FilterOutputStream(Stream @out) { this.@out = @out; } }
new ScaleClusterRequest("/clusters/[ClusterId]").SetMethod(MethodType.PUT);
public DataValidationConstraint Create(int operatorType, string formula1, string formula2) => DVConstraint.CreateTimeConstraint(operatorType, formula1, formula2);
public ListObjectParentPathsResult ListObjectParentPathsResult() { request = beforeClientExecution(request); return executeListObjectParentPaths(request); }
DescribeCacheSubnetGroupsResult DescribeCacheSubnetGroupsResult(DescribeCacheSubnetGroupsRequest request){request=beforeClientExecution(request);return executeDescribeCacheSubnetGroups;}
field_5_options = sharedFormula.setShortBoolean(field_5_options, flag);
bool () { }
public ErrorNode(Token badToken) { ErrorNodeImpl t = new ErrorNodeImpl(badToken); addAnyChild(t); t.SetParent(this); return t; }
LatvianStemFilterFactory(Dictionary<string,object> args) : base(args) { if (args.Count > 0) throw new ArgumentException("Unknown parameters: " + args); }
public object EventSubscription() { request = BeforeClientExecution(request); return ExecuteRemoveSourceIdentifierFromSubscription(request); }
public static TokenFilterFactory TokenFilterFactory(Dictionary<string, string> args) { return loader.newInstance(name, args); }
public AddAlbumPhotosRequest() : base("CloudPhoto", "2017-07-11", "AddAlbumPhotos", "cloudphoto") { }
public GetThreatIntelSetResult GetThreatIntelSetResult(GetThreatIntelSetRequest request) { request = beforeClientExecution(request); return executeGetThreatIntelSet; }
return new Binary((object[])a.Clone(), (object[])b.Clone());
bool MyMethod(object o) { return; }
public bool SomeMethod() { return ProtectedHasArray(); }
private UpdateContributorInsightsResult UpdateContributorInsights(UpdateContributorInsightsRequest request) { request = beforeClientExecution; return executeUpdateContributorInsights(request); }
void SomeMethod() { records.Remove(fileShare); records.Remove(writeProtect);; writeProtect = null; }
public SolrSynonymParser(bool dedup, bool expand, Analyzer analyzer) : base(dedup, analyzer) { this.expand = expand; }
public RequestSpotInstancesResult RequestSpotInstancesResult() { request = beforeClientExecution(request); return executeRequestSpotInstances(request); }
() => findObjectRecord.getObjectData();
public GetContactAttributesResult GetContactAttributesResult(GetContactAttributesRequest request) { request = beforeClientExecution; return executeGetContactAttributes(request); }
public string String() { return getKey + ": " + GetValue(); }
public ListTextTranslationJobsResult ListTextTranslationJobsResult() { request = BeforeClientExecution(request); return ExecuteListTextTranslationJobs(request); }
public GetContactMethodsResult GetContactMethodsResult() { request = beforeClientExecution(request); return executeGetContactMethods(request); }
public static int SomeMethod() { FunctionMetadata fd = getInstance().getFunctionByNameInternal(name); if (fd == null) { fd = getInstanceCetab().getFunctionByNameInternal(name); if (fd == null) { return -1; } } return fd.getIndex(); }
public DescribeAnomalyDetectorsResult Method(DescribeAnomalyDetectorsRequest request) { request = beforeClientExecution; return executeDescribeAnomalyDetectors(request); }
public static string InsertId(string message, ObjectId changeId) { return insertId; }
public long GetObjectSize(AnyObjectId objectId, ObjectType typeHint) { long sz = db.GetObjectSize(); if (sz < 0) { if (typeHint == ObjectType.Any) throw new MissingObjectException(objectId.Copy(), JGitText.Get().UnknownObjectType2); throw new MissingObjectException(objectId.Copy(), typeHint); } return sz; }
public ImportInstallationMediaResult ImportInstallationMediaResult() { request = BeforeClientExecution(request); return ExecuteImportInstallationMedia(request); }
public PutLifecycleEventHookExecutionStatusResult PutLifecycleEventHookExecutionStatusResult() { request = beforeClientExecution(request); return executePutLifecycleEventHookExecutionStatus(request); }
NumberPtg ( ) { ( reader . ReadDouble ( ) ) ; }
public GetFieldLevelEncryptionConfigResult GetFieldLevelEncryptionConfigResult(GetFieldLevelEncryptionConfigRequest request) { request = beforeClientExecution; return executeGetFieldLevelEncryptionConfig(request); }
DescribeDetectorResult DescribeDetectorResult(DescribeDetectorRequest request) { request = beforeClientExecution(request); return executeDescribeDetector; }
private ReportInstanceStatusResult ReportInstanceStatusResult(ReportInstanceStatusRequest request) { request = beforeClientExecution; return executeReportInstanceStatus(request); }
public DeleteAlarmResult Execute(DeleteAlarmRequest request) { request = BeforeClientExecution(request); return ExecuteDeleteAlarm; }
public TokenStream TokenStream() { return new PortugueseStemFilter(input); }
reserved = new FtCblsSubRecord();
public bool SomeMethod(object obj) { lock (mutex) { return c.Remove(obj); } }
public GetDedicatedIpResult GetDedicatedIpResult() { request = beforeClientExecution(request); return executeGetDedicatedIp(request); }
public String() { return; }
public SomeType ListStreamProcessorsResult(ListStreamProcessorsRequest request) { request = beforeClientExecution; return executeListStreamProcessors(request); }
public DeleteLoadBalancerPolicyRequest(string loadBalancerName, string policyName) { SetLoadBalancerName(loadBalancerName); SetPolicyName(policyName); }
public WindowProtectRecord(object options) { }
public class UnbufferedCharStream { private char[] data; public UnbufferedCharStream(int bufferSize) { data = new char[bufferSize]; } }
public GetOperationsResult GetOperationsResult(GetOperationsRequest request) { request = BeforeClientExecution(request); return executeGetOperations; }
void MethodName(byte[] b, object o) { NB.encodeInt32(b, o, w1); NB.encodeInt32(b, o + 4, w2); NB.encodeInt32(b, o + 8, w3); NB.encodeInt32(b, o + 12, w4); NB.encodeInt32(b, o + 16, w5); }
private WindowOneRecord(RecordInputStream in) { field_1_h_hold = in.ReadShort(); field_2_v_hold = in.ReadShort(); field_3_width = in.ReadShort(); field_4_height = in.ReadShort(); field_5_options = in.ReadShort(); field_6_active_sheet = in.ReadShort(); field_7_first_visible_tab = in.ReadShort(); field_8_num_selected_tabs = in.ReadShort(); field_9_tab_width_ratio = in.ReadShort(); }
public StopWorkspacesResult StopWorkspacesResult(StopWorkspacesRequest request) { request = beforeClientExecution; return executeStopWorkspaces(request); }
public void Method() { if (isOpen) { isOpen = false; try { dump(); } finally { try { channel.SetLength(fileLength); } finally { try { channel.Close(); } finally { fos.Close(); } } } } }
public DescribeMatchmakingRuleSetsResult DescribeMatchmakingRuleSetsResult() { request = beforeClientExecution(request); return executeDescribeMatchmakingRuleSets(request); }
public String(int wordId, string[] surface, int off, int len) { }
public String() { }
public static double SomeMethod(double[] v) { double r = double.NaN; if (v != null && v.Length >= 1) { double m = 0, s = 0; int n = v.Length; for (int i = 0; i < n; i++) s += v[i]; m = s / n; s = 0; for (int i = 0; i < n; i++) s += (v[i] - m) * (v[i] - m); r = (n == 1) ? 0 : s; } return r; }
DescribeResizeResult DescribeResizeResult() { request = beforeClientExecution(request); return executeDescribeResize(request); }
public bool BooleanMethod() { return passedThroughNonGreedyDecision; }
() { return end; }
void ProcessCells(CellHandler handler) { int firstRow = range.GetFirstRow(), lastRow = range.GetLastRow(), firstColumn = range.GetFirstColumn(), lastColumn = range.GetLastColumn(), width = lastColumn - firstColumn + 1; SimpleCellWalkContext ctx = new SimpleCellWalkContext(); Row currentRow; Cell currentCell; for (ctx.rowNumber = firstRow; ctx.rowNumber <= lastRow; ++ctx.rowNumber) { currentRow = sheet.GetRow(ctx.rowNumber); if (currentRow == null) continue; for (ctx.colNumber = firstColumn; ctx.colNumber <= lastColumn; ++ctx.colNumber) { currentCell = currentRow.GetCell(ctx.colNumber); if (currentCell == null) continue; if (IsEmpty(currentCell) && !traverseEmptyCells) continue; int rowSize = ArithmeticUtils.MulAndCheck(() => ArithmeticUtils.SubAndCheck(ctx.rowNumber, firstRow), () => width); ctx.ordinalNumber = ArithmeticUtils.AddAndCheck(() => rowSize, () => (ctx.colNumber - firstColumn + 1)); handler.OnCell(currentCell, ctx); } } }
() => { }
(ScoreTerm other) => { if (this.boost == other.boost) return other.bytes.Get().CompareTo(this.bytes.Get()); else return this.boost.CompareTo(other.boost); }
int f(char[]s,int len){for(int i=0;i<len;i++){switch(s[i]){case FARSI_YEH:case YEH_BARREE:s[i]=YEH;goto case KEHEH;case KEHEH:s[i]=KAF;break;case HEH_YEH:case HEH_GOAL:s[i]=HEH;break;case HAMZA_ABOVE:len=delete(s,i,len);i--;break;default:break;}}}return len;}
void SomeMethod(LittleEndianOutput @out) { @out.writeShort; }
public DiagnosticErrorListener() { this.exactOnly = exactOnly; }
public KeySchemaElement(string attributeName) { SetAttributeName(attributeName); SetKeyType(keyType.ToString()); }
public AssignmentResult GetAssignmentResult() { request = beforeClientExecution(request); return executeGetAssignment(request); }
bool (AnyObjectId id) { return findOffset(id) != ; }
GroupingSearch(bool allGroups) { this.allGroups = allGroups; }
public void SetMultiValued(string dimName, bool v) { lock (this) { DimConfig ft = fieldTypes[dimName]; if (ft == null) { ft = new DimConfig(); fieldTypes[dimName] = ft; } ft.MultiValued = v; } }
int size = 0; foreach (var c in cells.Keys) { var e = at(c); if (e.cmd >= 0) size++; } return size;
public DeleteVoiceConnectorResult DeleteVoiceConnector(DeleteVoiceConnectorRequest request) { request = beforeClientExecution; return executeDeleteVoiceConnector(request); }
public DeleteLifecyclePolicyResult DeleteLifecyclePolicyResult(DeleteLifecyclePolicyRequest request) { request = BeforeClientExecution; return ExecuteDeleteLifecyclePolicy(request); }
