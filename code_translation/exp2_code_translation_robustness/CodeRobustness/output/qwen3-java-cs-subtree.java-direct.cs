(LittleEndianOutput @out) => @out.writeShort();
void SomeMethod(BlockList<T> src) { if (src.size == 0) srcDirIdx = 0; for (; srcDirIdx < src.tailDirIdx; srcDirIdx++) addAll(src.directory[srcDirIdx], 0, BLOCK_SIZE); if (src.tailBlkIdx != 0) addAll(src.tailBlock, 0, src.tailBlkIdx); }
public void Add(byte b) { if (someCondition) { if (currentBlock != null) { AddBlock(currentBlock); } currentBlock = new byte[blockSize]; upto = 0; } currentBlock[upto++] = b; }
internal ObjectId() { }
public DeleteDomainEntryResult DeleteDomainEntryResult(DeleteDomainEntryRequest request) { request = beforeClientExecution(request); return executeDeleteDomainEntry; }
() => ( ( termOffsets != null ) ? termOffsets.ramBytesUsed() : 0 ) + ( ( () => {} ) ? termsDictOffsets.ramBytesUsed() : 0 )
public sealed string Method() { byte[] raw = buffer; msgB = RawParseUtils.TagMessage; if (msgB < 0) return ""; return RawParseUtils.Decode(GuessEncoding(), raw, msgB, raw.Length); }
public POIFSFileSystem() { (true); _header.setBATCount(); _header.setBATArray(new[] { 1 }); BATBlock bb = BATBlock.createEmptyBATBlock(bigBlockSize, false); bb.setOurBlockIndex(1); _bat_blocks.Add(bb); setNextBlock(0, POIFSConstants.END_OF_CHAIN); setNextBlock(1, POIFSConstants.FAT_SECTOR_BLOCK); _property_table.setStartBlock(0); }
slice = pool.buffers[address >> ByteBlockPool.BYTE_BLOCK_SHIFT]; Debug.Assert(slice != null); upto = address & ByteBlockPool.BYTE_BLOCK_MASK; offset0 = address; Debug.Assert(upto < );
public SubmoduleAddCommand SubmoduleAddCommand(string path) { this.path = path; return this; }
private ListIngestionsResult ListIngestionsResult() { request = beforeClientExecution(request); return executeListIngestions(request); }
public QueryParserTokenManager(CharStream stream, int lexState) { SwitchTo(lexState); }
private GetShardIteratorResult GetShardIteratorResult(GetShardIteratorRequest request) { request = BeforeClientExecution(request); return ExecuteGetShardIterator; }
public ModifyStrategyRequest() : base("aegis", "2016-11-11", "ModifyStrategy", "vipaegis") { }
public bool MyMethod() { lock (lock) { if (@in == null) throw new IOException("InputStreamReader is closed"); try { return bytes.HasRemaining || @in.Available() > 0; } catch { return false; } } }
public EscherOptRecord() { }
[MethodImpl(MethodImplOptions.Synchronized)] public int Read(byte[] buffer, int offset, int length) { if (buffer == null) throw new ArgumentNullException("buffer"); if (offset < 0 || length < 0 || offset + length > buffer.Length) throw new ArgumentException("Invalid offset or length"); if (length == 0) return 0; int copylen = Math.Min(count - pos, length); for (int i = 0; i < copylen; i++) buffer[offset + i] = (byte)this.buffer[pos + i]; return copylen; }
public OpenNLPSentenceBreakIterator(SentenceOp sentenceOp) { this.sentenceOp = sentenceOp; }
void SomeMethod(string str) { write(str != null ? str : "null"); }
public NotImplementedFunctionException(string functionName, NotImplementedException cause) : base(functionName, cause) { this.functionName = functionName; }
public V V() { return base.nextEntry.GetValue(); }
public sealed void Read(byte[] b, int offset, int len, bool useBuffer) { int available = bufferLength - bufferPosition; if (len <= available) { if (len > 0) Buffer.BlockCopy(buffer, bufferPosition, b, offset, len); bufferPosition += len; } else { if (available > 0) { Buffer.BlockCopy(buffer, bufferPosition, b, offset, available); offset += available; len -= available; bufferPosition += available; } if (useBuffer && len < bufferSize) { Refill(); if (bufferLength < len) { Buffer.BlockCopy(buffer, 0, b, offset, bufferLength); throw new IOException("read past EOF: " + this); } else { Buffer.BlockCopy(buffer, 0, b, offset, len); } } else { long after = bufferStart + bufferPosition + len; if (after > Length()) throw new IOException("read past EOF: " + this); ReadInternal(b, offset, len); bufferStart = after; bufferPosition = 0; bufferLength = 0; } } }
public TagQueueResult TagQueueResult(TagQueueRequest request) { request = BeforeClientExecution; return ExecuteTagQueue(request); }
void Method() { throw new NotSupportedException(); }
public SomeReturnType CacheSubnetGroup() { request = beforeClientExecution(request); return executeModifyCacheSubnetGroup(request); }
public void setParams(string p) { base.setParams(p); language = country = variant = ""; var tokens = p.Split(new[] { ',' }, StringSplitOptions.RemoveEmptyEntries); if (tokens.Length >= 1) language = tokens[0]; if (tokens.Length >= 2) country = tokens[1]; if (tokens.Length >= 3) variant = tokens[2]; }
public DeleteDocumentationVersionResult DeleteDocumentationVersionResult(DeleteDocumentationVersionRequest request) { request = this.beforeClientExecution; return this.executeDeleteDocumentationVersion(request); }
public override bool Equals(object obj) { if (!(obj is FacetLabel)) return false; FacetLabel other = (FacetLabel)obj; if (length != other.length) return false; for (int i = length - 1; ; i--) if (!components[i].Equals(other.components[i])) return false; return true; }
public GetInstanceAccessDetailsResult GetInstanceAccessDetailsResult(GetInstanceAccessDetailsRequest request) { request = beforeClientExecution(request); return executeGetInstanceAccessDetails; }
public HSSFPolygon HSSFPolygon(HSSFChildAnchor anchor) { HSSFPolygon shape = new HSSFPolygon(this, anchor); shape.SetParent(); shape.SetAnchor(anchor); shapes.Add(shape); OnCreate(shape); return shape; }
public string GetSheetName(int sheetIndex) => getBoundSheetRec(sheetIndex).Sheetname;
public GetDashboardResult GetDashboardResult(GetDashboardRequest request) { request = beforeClientExecution(request); return executeGetDashboard; }
AssociateSigninDelegateGroupsWithAccountResult AssociateSigninDelegateGroupsWithAccountResult(AssociateSigninDelegateGroupsWithAccountRequest request) { request = beforeClientExecution; return executeAssociateSigninDelegateGroupsWithAccount(request); }
void ProcessMulBlankRecord(MulBlankRecord mbr) { for (int j = 0; j < mbr.GetNumColumns(); j++) { BlankRecord br = new BlankRecord(); br.Column = (short)(j + mbr.FirstColumn); br.Row = mbr.Row; br.XFIndex = mbr.XFAt; insertCell(br); } }
public static string Escape() { StringBuilder sb = new StringBuilder(); sb.Append("\\Q"); int apos = 0; int k; while ((k = string.IndexOf("\\E", apos, StringComparison.Ordinal)) >= 0) { sb.Append(string.Substring(apos, k + 2 - apos)).Append("\\\\E\\Q"); apos = k + 2; } return sb.Append(string.Substring(apos)).Append("\\E").ToString(); }
public ByteBuffer(object value) { throw new ReadOnlyBufferException(); }
public ArrayPtg(object values2d) { int nColumns=((object[][])values2d)[0].Length; int nRows=((object[][])values2d).Length; short _nColumns=(short)nColumns; short _nRows=(short)nRows; object[] vv=new object[_nColumns*_nRows]; for(int r=0;r<nRows;r++){object[] rowData=((object[][])values2d)[r];for(int c=0;c<nColumns;c++){vv[GetValueIndex(c,r)]=rowData[c];}}_arrayValues=vv;_reserved0Int=0;_reserved1Short=0;_reserved2Byte=0;}
GetIceServerConfigResult GetIceServerConfig(GetIceServerConfigRequest request) { request = beforeClientExecution; return executeGetIceServerConfig(request); }
public override string ToString() { return GetType().FullName + " [" + GetValueAsString() + "]"; }
public override string ToString() { return $"ToChildBlockJoinQuery ({parentQuery.ToString()})"; }
void Method() { Interlocked.Increment(ref refCount); }
public UpdateConfigurationSetSendingEnabledResult UpdateConfigurationSetSendingEnabledResult(UpdateConfigurationSetSendingEnabledRequest request) { request = beforeClientExecution; return executeUpdateConfigurationSetSendingEnabled(request); }
(  ) { return getXBATEntriesPerBlock ( ) * ; }
void pow10() { TenPower tp = TenPower.GetInstance(Math.Abs(pow10)); if (pow10 < 0) { mulShift( , tp._divisorShift); } else { mulShift(tp._multiplicand, tp._multiplierShift); } }
public string String() { StringBuilder b = new StringBuilder(); int l = length; b.Append(System.IO.Path.DirectorySeparatorChar); for (int i = 0; i < l; i++) { b.Append(getComponent(i)); if (i < l - 1) { b.Append(System.IO.Path.DirectorySeparatorChar); } } return b.ToString(); }
public InstanceProfileCredentialsProvider(ECSMetadataServiceCredentialsFetcher fetcher) { this.fetcher = fetcher; SetRoleName(roleName); }
void MyMethod(ProgressMonitor pm) { }
void MethodName() { if (!first) { ptr = 0; if (!eof()) parseEntry(); } }
T E() { if (iterator.PreviousIndex() >= start) return iterator.Previous(); throw new InvalidOperationException(); }
void String() { return; }
for (int i = 0; i < mSize; i++) { if (mValues[i] == value) return -1; }
List<CharsRef> stems = stem(word, length); if (stems.Count < 2) { return stems; } CharArraySet terms = new CharArraySet(8, dictionary.ignoreCase); List<CharsRef> deduped = new List<CharsRef>(); foreach (CharsRef s in stems) { if (!terms.Contains(s)) { deduped.Add(s); terms.Add(s); } } return deduped;
public GetGatewayResponsesResult ExecuteGetGatewayResponses(GetGatewayResponsesRequest request) { request = BeforeClientExecution(request); return executeGetGatewayResponses; }
void Method(long pos) { currentBlockIndex = (int)(pos >> blockBits); currentBlock = null; currentBlockUpto = (int)(pos & blockMask); }
(n) => { s = Math.Min(available(), Math.Max(0, n)); ptr += s; }
public BootstrapActionDetail() { SetBootstrapActionConfig(this.bootstrapActionConfig); }
public void Serialize(LittleEndianOutput @out) { @out.WriteInt16(field_1_row); @out.WriteInt16(field_2_col); @out.WriteInt16(field_3_flags); @out.WriteInt16(field_4_shapeid); @out.WriteInt16(field_6_author.Length); @out.WriteByte(field_5_hasMultibyte ? (byte)0x01 : (byte)0x00); if (field_5_hasMultibyte) { StringUtil.PutUnicodeLE(field_6_author, @out); } else { StringUtil.PutCompressedUnicode(field_6_author, @out); } if (field_7_padding.HasValue) { @out.WriteByte((byte)field_7_padding.Value); } }
(string @string) => { return lastIndexOf; };
bool AddLastImpl<E>(E obj) { return addLastImpl; }
private void A(string section, string subsection) { object src, res; do { src = state; res = unsetSection(src, section, subsection); } while (!object.ReferenceEquals(Interlocked.CompareExchange(ref state, res, src), src)); }
public string String() { return tagName; }
(int index, SubRecord element) => subrecords.Add(element);
bool SomeMethod() { lock (mutex) { return delegate().remove(o); } }
public DoubleMetaphoneFilter DoubleMetaphoneFilter(TokenStream input) { return new DoubleMetaphoneFilter(); }
int GetInCoreLength() { return inCoreLength; }
void MethodName(bool newValue) { }
public Pair(ContentSource oldSource, ContentSource newSource) { this.oldSource = oldSource; this.newSource = newSource; }
i => { if (i < 0 || i >= entries.Length) throw new ArgumentOutOfRangeException("i"); return entries[i]; }
public CreateRepoRequest() : base("cr", "2016-06-07", "CreateRepo", "cr") { "/repos"; setMethod(); }
bool Method() { }
void RemoveNode() { if (expectedModCount == list.modCount) { if (lastLink != null) { Link next = lastLink.next; Link<ET> previous = lastLink.previous; next.previous = previous; previous.next = next; if (lastLink == link) { pos--; } link = previous; lastLink = null; expectedModCount++; list.size--; list.modCount++; } else { throw new InvalidOperationException(); } } else { throw new InvalidOperationException(); } }
public MergeShardsResult MergeShardsResult() { request = beforeClientExecution(request); return executeMergeShards(request); }
AllocateHostedConnectionResult AllocateHostedConnectionResult(AllocateHostedConnectionRequest request) { request = beforeClientExecution(request); return executeAllocateHostedConnection; }
() => { }
public static WeightedTerm WeightedTerm(Query query) { return getTerms(query, false); }
public ByteBuffer() { throw new ReadOnlyBufferException(); }
void Process(byte[] blocks, int blocksOffset, byte[] values, int valuesOffset, int iterations) { for (int i = 0; i < iterations; ++i) { byte byte0 = blocks[blocksOffset++]; values[valuesOffset++] = (byte)(byte0 >> 2); byte byte1 = blocks[blocksOffset++]; values[valuesOffset++] = (byte)((byte0 & 3) << 4 | (byte1 >> 4)); byte byte2 = blocks[blocksOffset++]; values[valuesOffset++] = (byte)((byte1 & 0xF) << 2 | (byte2 >> 6)); values[valuesOffset++] = (byte)(byte2 & 0x3F); } }
string GetResult(){string s=GetPath();if("/".Equals(s)||"".Equals(s))s=GetHost();if(s==null)throw new ArgumentException();string[]elements;if("file".Equals(scheme)||LOCAL_FILE.IsMatch(s))elements=Regex.Split(s,"[\\\\/]");else elements=Regex.Split(s,"/+");if(elements.Length==0)throw new ArgumentException();string result=elements[elements.Length-1];if(Constants.DOT_GIT.Equals(result))result=elements[elements.Length-2];else if(result.EndsWith(Constants.DOT_GIT_EXT))result=result.Substring(0,result.Length-Constants.DOT_GIT_EXT.Length);return result;}
public DescribeNotebookInstanceLifecycleConfigResult DescribeNotebookInstanceLifecycleConfig(DescribeNotebookInstanceLifecycleConfigRequest request) { request = beforeClientExecution; return executeDescribeNotebookInstanceLifecycleConfig(request); }
void String() { return; }
public CreateVpnConnectionResult CreateVpnConnection() { request = BeforeClientExecution(request); return ExecuteCreateVpnConnection(request); }
public DescribeVoicesResult DescribeVoicesResult() { request = beforeClientExecution(request); return executeDescribeVoices(request); }
public ListMonitoringExecutionsResult ListMonitoringExecutionsResult(ListMonitoringExecutionsRequest request) { request = BeforeClientExecution(request); return ExecuteListMonitoringExecutions(request); }
public DescribeJobRequest(string vaultName, string jobId) { SetVaultName(vaultName); SetJobId(jobId); }
public EscherRecord EscherRecord(int index) { return escherRecords[index]; }
public GetApisResult GetApisResult(GetApisRequest request) { request = beforeClientExecution; return executeGetApis(request); }
public DeleteSmsChannelResult DeleteSmsChannel(DeleteSmsChannelRequest request) { request = beforeClientExecution(request); return executeDeleteSmsChannel; }
public class TrackingRefUpdate { public TrackingRefUpdate() { } }
void Method() { Console.Write(b.ToString()); }
QueryNode QueryNode() { return getChildren[0]; }
public NotIgnoredFilter(WorkdirTreeIndex workdirTreeIndex) => this.workdirTreeIndex = workdirTreeIndex;
public AreaRecord(RecordInputStream @in) { field_1_formatFlags = @in.ReadShort(); }
new GetThumbnailRequest(ProtocolType.HTTPS);
public DescribeTransitGatewayVpcAttachmentsResult DescribeTransitGatewayVpcAttachmentsResult() { request = beforeClientExecution(request); return executeDescribeTransitGatewayVpcAttachments(request); }
public PutVoiceConnectorStreamingConfigurationResult PutVoiceConnectorStreamingConfigurationResult() { request = beforeClientExecution(request); return executePutVoiceConnectorStreamingConfiguration(request); }
public OrdRange OrdRange() { return prefixToOrdRange.get(dim); }
string String() { string symbol = ""; if (startIndex >= 0 && startIndex < GetInputStream().Size) { symbol = GetInputStream().GetText(new Interval(startIndex, startIndex)); symbol = Utils.EscapeWhitespace(symbol, false); } return string.Format(System.Globalization.CultureInfo.CurrentCulture, "{0}('{1}')", typeof(LexerNoViableAltException).Name, symbol); }
E E() { return peekFirstImpl; }
public CreateWorkspacesResult CreateWorkspacesResult() { request = beforeClientExecution(request); return executeCreateWorkspaces(request); }
public NumberFormatIndexRecord NumberFormatIndexRecord() { return copy; }
public DescribeRepositoriesResult DescribeRepositories() { request = beforeClientExecution(request); return executeDescribeRepositories(request); }
public SparseIntArray(int initialCapacity) { initialCapacity = ArrayUtils.idealIntArraySize(initialCapacity); mKeys = new int[initialCapacity]; mValues = new int[initialCapacity]; mSize = 0; }
public HyphenatedWordsFilter HyphenatedWordsFilter() => new HyphenatedWordsFilter(input);
public CreateDistributionWithTagsResult CreateDistributionWithTagsResult ( ) { request = beforeClientExecution ( request ) ; return executeCreateDistributionWithTags ( request ) ; }
public RandomAccessFile(string fileName, string mode) { new FileStream(fileName, mode == "r" ? FileMode.OpenExisting : FileMode.OpenOrCreate, mode == "r" ? FileAccess.Read : FileAccess.ReadWrite); }
public DeleteWorkspaceImageResult DeleteWorkspaceImage(DeleteWorkspaceImageRequest request) { request = BeforeClientExecution(request); return executeDeleteWorkspaceImage; }
public static string Method(int value) { StringBuilder sb = new StringBuilder(16); writeHex(sb, value, 16, ""); return sb.ToString(); }
public UpdateDistributionResult UpdateDistribution(UpdateDistributionRequest request) { request = BeforeClientExecution(request); return ExecuteUpdateDistribution; }
return index == HSSFColorPredefined.AUTOMATIC.Index ? HSSFColorPredefined.AUTOMATIC.GetColor() : (_palette.GetColor(index) == null ? null : new CustomColor());
public ValueEval(ValueEval operands, int srcRow, int srcCol) { throw new NotImplementedFunctionException(_functionName); }
void Method() { out.Write((short)field_1_number_crn_records); out.Write((short)field_2_sheet_table_index); }
public DescribeDBEngineVersionsResult DescribeDBEngineVersionsResult() => describeDBEngineVersions(new DescribeDBEngineVersionsRequest());
public FormatRun(char character, int fontIndex) { this._character = character; this._fontIndex = fontIndex; }
public static byte[] Convert(char[] chars, int offset, int length) { byte[] result = new byte[length * 2]; int end = offset + length, resultIndex = 0; for (int i = offset; i < end; ++i) { char ch = chars[i]; result[resultIndex++] = (byte)(ch >> 8); result[resultIndex++] = (byte)ch; } return result; }
public UploadArchiveResult UploadArchiveResult(UploadArchiveRequest request) => executeUploadArchive(beforeClientExecution);
List<object> List(int tokenIndex) { return getHiddenTokensToLeft(tokenIndex, -1); }
public override bool Equals(object obj) { if (ReferenceEquals(this, obj)) return true; if (!base.Equals(obj)) return false; if (GetType() != obj.GetType()) return false; AutomatonQuery other = (AutomatonQuery)obj; if (!compiled.Equals(other.compiled)) return false; if (term == null) { if (other.term != null) return false; } else if (!term.Equals(other.term)) return false; return true; }
SpanQuery SpanQuery() { SpanQuery[] spanQueries = new SpanQuery[weightBySpanQuery.Count]; int i = 0; foreach (var sq in weightBySpanQuery.Keys) { var boost = weightBySpanQuery[sq]; if (boost != 1f) { sq = new SpanBoostQuery(sq, boost); } spanQueries[i++] = sq; } return spanQueries.Length == 1 ? spanQueries[0] : new SpanOrQuery(spanQueries); }
public StashCreateCommand StashCreateCommand() { return new StashCreateCommand(); }
public FieldInfo FieldInfo() { return byName[fieldName]; }
DescribeEventSourceResult Execute(DescribeEventSourceRequest request) { request = beforeClientExecution; return executeDescribeEventSource(request); }
public DocumentAnalysisResult GetDocumentAnalysisResult() { request = beforeClientExecution(request); return executeGetDocumentAnalysis(request); }
public CancelUpdateStackResult CancelUpdateStackResult() { request = beforeClientExecution(request); return executeCancelUpdateStack(request); }
public ModifyLoadBalancerAttributesResult ModifyLoadBalancerAttributesResult(ModifyLoadBalancerAttributesRequest request) { request = beforeClientExecution; return executeModifyLoadBalancerAttributes(request); }
SetInstanceProtectionResult SetInstanceProtectionResult() { request = beforeClientExecution(request); return executeSetInstanceProtection(request); }
public ModifyDBProxyResult ModifyDBProxyResult(ModifyDBProxyRequest request) { request = beforeClientExecution(request); return executeModifyDBProxy; }
void Method(char[] output, int offset, int len, int endOffset, int posLength) { if (count == outputs.Length) { Array.Resize(ref outputs, count + 1); } if (count == endOffsets.Length) { int[] next = new int[count + 1]; Array.Copy(endOffsets, next, count); endOffsets = next; } if (count == posLengths.Length) { int[] next = new int[count + 1]; Array.Copy(posLengths, next, count); posLengths = next; } if (outputs[count] == null) { outputs[count] = new CharsRefBuilder(); } outputs[count].CopyChars(output, offset, len); endOffsets[count] = endOffset; posLengths[count] = posLength; count++; }
public class FetchLibrariesRequest : SomeBaseClass { public FetchLibrariesRequest() : base("CloudPhoto", "2017-07-11", "FetchLibraries", "cloudphoto") { ; } }
bool Exists() { return fs.Exists; }
public FilterOutputStream(Stream @out) { this.out = @out; }
new ScaleClusterRequest("/clusters/[ClusterId]").setMethod(MethodType.PUT);
public DataValidationConstraint DataValidationConstraint(int operatorType, string formula1, string formula2) { return DVConstraint.CreateTimeConstraint(operatorType, formula1, formula2); }
public ListObjectParentPathsResult ListObjectParentPathsResult() { request = beforeClientExecution(request); return executeListObjectParentPaths(request); }
public DescribeCacheSubnetGroupsResult DescribeCacheSubnetGroupsResult(DescribeCacheSubnetGroupsRequest request) { request = BeforeClientExecution(request); return ExecuteDescribeCacheSubnetGroups; }
field_5_options = sharedFormula.setShortBoolean(field_5_options, flag);
bool () { }
public ErrorNode(Token badToken) { ErrorNodeImpl t = new ErrorNodeImpl(badToken); AddAnyChild(t); t.SetParent(); return t; }
public LatvianStemFilterFactory(Dictionary<object,object> args):base(args){if(args.Count>0)throw new ArgumentException("Unknown parameters: "+args);}
public object EventSubscription() { request = beforeClientExecution(request); return executeRemoveSourceIdentifierFromSubscription(request); }
public static TokenFilterFactory TokenFilterFactory(string name, Dictionary<string, string> args) { return loader.newInstance(name, args); }
public AddAlbumPhotosRequest() : base("CloudPhoto", "2017-07-11", "AddAlbumPhotos", "cloudphoto") {}
public GetThreatIntelSetResult GetThreatIntelSetResult(GetThreatIntelSetRequest request) { request = beforeClientExecution(request); return executeGetThreatIntelSet; }
public Binary RevFilter() { return new Binary(a.Clone(), b.Clone()); }
bool Method(object o) { return; }
bool Method() { return ProtectedHasArray(); }
UpdateContributorInsightsResult UpdateContributorInsightsResult(UpdateContributorInsightsRequest request) { request = beforeClientExecution; return executeUpdateContributorInsights(request); }
void Method() { records.Remove(fileShare); records.Remove(writeProtect); writeProtect = null; }
public SolrSynonymParser(bool dedup, bool expand, Analyzer analyzer) : base(dedup, analyzer) { this.expand = expand; }
public RequestSpotInstancesResult RequestSpotInstancesResult() => (request = beforeClientExecution(request), executeRequestSpotInstances(request));
() => findObjectRecord.getObjectData();
GetContactAttributesResult GetContactAttributes(GetContactAttributesRequest request) { request = this.beforeClientExecution; return ExecuteGetContactAttributes(request); }
public override string ToString() { return getKey + ": " + getValue(); }
public ListTextTranslationJobsResult ListTextTranslationJobsResult() { request = beforeClientExecution(request); return executeListTextTranslationJobs(request); }
public GetContactMethodsResult GetContactMethodsResult() { request = BeforeClientExecution(request); return ExecuteGetContactMethods(request); }
public static int GetFunctionIndex(string name) { FunctionMetadata fd = GetInstance().GetFunctionByNameInternal(name); if (fd == null) { fd = GetInstanceCetab().GetFunctionByNameInternal(name); if (fd == null) return -1; } return fd.GetIndex(); }
public DescribeAnomalyDetectorsResult DescribeAnomalyDetectors(DescribeAnomalyDetectorsRequest request) { request = beforeClientExecution; return executeDescribeAnomalyDetectors(request); }
public static string InsertId(string message, ObjectId changeId) { return insertId; }
int MethodName(AnyObjectId objectId, int typeHint) { int sz = db.GetObjectSize(); if (sz < 0) { if (typeHint == OBJ_ANY) throw new MissingObjectException(objectId.Copy(), JGitText.Get().unknownObjectType2); throw new MissingObjectException(objectId.Copy(), typeHint); } return sz; }
public ImportInstallationMediaResult ImportInstallationMediaResult() { this.request = this.beforeClientExecution(this.request); return this.executeImportInstallationMedia(this.request); }
public PutLifecycleEventHookExecutionStatusResult PutLifecycleEventHookExecutionStatusResult() { request = beforeClientExecution(request); return executePutLifecycleEventHookExecutionStatus(request); }
public NumberPtg() { @in.readDouble(); }
public GetFieldLevelEncryptionConfigResult GetFieldLevelEncryptionConfigResult(GetFieldLevelEncryptionConfigRequest request) { request = beforeClientExecution; return executeGetFieldLevelEncryptionConfig(request); }
public DescribeDetectorResult MethodName(DescribeDetectorRequest request) { request = BeforeClientExecution(request); return ExecuteDescribeDetector; }
public ReportInstanceStatusResult ReportInstanceStatusResult(ReportInstanceStatusRequest request){request=beforeClientExecution;return executeReportInstanceStatus(request);}
public DeleteAlarmResult DeleteAlarmResult(DeleteAlarmRequest request) { request = BeforeClientExecution(request); return ExecuteDeleteAlarm; }
public TokenStream TokenStream() { return new PortugueseStemFilter(input); }
reserved = new FtCblsSubRecord();
public bool Remove(object @object) { lock (mutex) { return c.Remove(@object); } }
public GetDedicatedIpResult GetDedicatedIpResult() { request = beforeClientExecution(request); return executeGetDedicatedIp(request); }
public string String() { return null; }
public ListStreamProcessorsResult ListStreamProcessorsResult(ListStreamProcessorsRequest request) { request = BeforeClientExecution(); return ExecuteListStreamProcessors(request); }
public DeleteLoadBalancerPolicyRequest(string loadBalancerName, string policyName) { SetLoadBalancerName(loadBalancerName); SetPolicyName(policyName); }
public WindowProtectRecord(object options) { ; }
public UnbufferedCharStream(int bufferSize) { data = new char[bufferSize]; }
public GetOperationsResult GetOperationsResult(GetOperationsRequest request) { request = BeforeClientExecution(request); return executeGetOperations; }
void MethodName(byte[] b, int o) { NB.EncodeInt32(b, o, w1); NB.EncodeInt32(b, o + 4, w2); NB.EncodeInt32(b, o + 8, w3); NB.EncodeInt32(b, o + 12, w4); NB.EncodeInt32(b, o + 16, w5); }
public WindowOneRecord(RecordInputStream @in) { field_1_h_hold = @in.ReadShort(); field_2_v_hold = @in.ReadShort(); field_3_width = @in.ReadShort(); field_4_height = @in.ReadShort(); field_5_options = @in.ReadShort(); field_6_active_sheet = @in.ReadShort(); field_7_first_visible_tab = @in.ReadShort(); field_8_num_selected_tabs = @in.ReadShort(); field_9_tab_width_ratio = @in.ReadShort(); }
StopWorkspacesResult StopWorkspacesResult(StopWorkspacesRequest request) { request = BeforeClientExecution(); return ExecuteStopWorkspaces(request); }
void Method() { if (isOpen) { isOpen = false; try { dump(); } finally { try { channel.SetLength(fileLength); } finally { try { channel.Close(); } finally { fos.Close(); } } } } }
public DescribeMatchmakingRuleSetsResult DescribeMatchmakingRuleSetsResult() { request = beforeClientExecution(request); return executeDescribeMatchmakingRuleSets(request); }
public class String { public String(int wordId, string[] surface, int off, int len) {} }
public String() { }
public static double SomeMethod(double[] v) { double r = double.NaN; if (v != null && v.Length >= 1) { double m = 0, s = 0; int n = v.Length; for (int i = 0; i < n; i++) { s += v[i]; } m = s / n; s = 0; for (int i = 0; i < n; i++) { s += (v[i] - m) * (v[i] - m); } r = (n == 1) ? 0 : s; } return r; }
public DescribeResizeResult DescribeResizeResult() { request = beforeClientExecution(request); return executeDescribeResize(request); }
public bool PassedThroughNonGreedyDecision() => passedThroughNonGreedyDecision;
() => end
void ProcessCells(CellHandler handler) { int firstRow = range.GetFirstRow(); int lastRow = range.GetLastRow(); int firstColumn = range.GetFirstColumn(); int lastColumn = range.GetLastColumn(); int width = lastColumn - firstColumn + 1; SimpleCellWalkContext ctx = new SimpleCellWalkContext(); Row currentRow = null; Cell currentCell = null; for (ctx.RowNumber = firstRow; ctx.RowNumber <= lastRow; ++ctx.RowNumber) { currentRow = sheet.GetRow(ctx.RowNumber); if (currentRow == null) { continue; } for (ctx.ColNumber = firstColumn; ctx.ColNumber <= lastColumn; ++ctx.ColNumber) { currentCell = currentRow.GetCell(ctx.ColNumber); if (currentCell == null) { continue; } if (currentCell.IsEmpty() && !traverseEmptyCells) { continue; } int rowSize = ArithmeticUtils.MulAndCheck(ArithmeticUtils.SubAndCheck(ctx.RowNumber - firstRow), width); ctx.OrdinalNumber = ArithmeticUtils.AddAndCheck(rowSize, (ctx.ColNumber - firstColumn + 1)); handler.OnCell(currentCell, ctx); } } }
() => { }
( ScoreTerm other ) => { if ( this.boost == other.boost ) return other.bytes.get().CompareTo( this.bytes.get() ); else return this.boost.CompareTo( other.boost ); }
public static int ProcessArray(char[] s, int len) { for (int i = 0; i < len; i++) { switch (s[i]) { case FARSI_YEH: case YEH_BARREE: s[i] = YEH; goto case KEHEH; case KEHEH: s[i] = KAF; break; case HEH_YEH: case HEH_GOAL: s[i] = HEH; break; case HAMZA_ABOVE: len = delete(s, i, len); i--; break; default: break; } } return len; }
void WriteShort(LittleEndianOutput @out) { @out.WriteShort(); }
public DiagnosticErrorListener() { this.exactOnly = exactOnly; }
public KeySchemaElement(string attributeName, KeyType keyType) { SetAttributeName(attributeName); SetKeyType(keyType.ToString()); }
public GetAssignmentResult() { request = beforeClientExecution(request); return executeGetAssignment(request); }
bool SomeMethod(AnyObjectId id) { return findOffset(id) != ; }
public GroupingSearch GroupingSearch(bool allGroups) { this.allGroups = allGroups; return this; }
public void SetMultiValued(string dimName, bool v) { lock (this) { DimConfig ft; if (!fieldTypes.TryGetValue(dimName, out ft)) { ft = new DimConfig(); fieldTypes[dimName] = ft; } ft.MultiValued = v; } }
int size = 0; foreach (char c in cells.Keys) { Cell e = cells[c]; if (e.cmd >= 0) size++; } return size;
public DeleteVoiceConnectorResult DeleteVoiceConnector(DeleteVoiceConnectorRequest request) { request = beforeClientExecution; return executeDeleteVoiceConnector(request); }
public DeleteLifecyclePolicyResult DeleteLifecyclePolicyResult(DeleteLifecyclePolicyRequest request) { request = beforeClientExecution; return executeDeleteLifecyclePolicy(request); }
