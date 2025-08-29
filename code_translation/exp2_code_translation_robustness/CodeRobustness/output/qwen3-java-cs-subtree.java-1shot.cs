public virtual void WriteShort(LittleEndianOutput output) { output.WriteShort(); }
public BlockList(BlockList<T> src){if(src.size==0)srcDirIdx=0;for(;srcDirIdx<src.tailDirIdx;srcDirIdx++)AddAll(src.directory[srcDirIdx],0,BLOCK_SIZE);if(src.tailBlkIdx!=0)AddAll(src.tailBlock,0,src.tailBlkIdx);}
public virtual void Write(byte b) { if (/* condition */true) { if (currentBlock != null) { AddBlock(currentBlock); } currentBlock = new byte[blockSize]; upto = 0; } currentBlock[upto++] = b; }
public ObjectId() { }
public DeleteDomainEntryResult(DeleteDomainEntryRequest request) { request = beforeClientExecution(request); return executeDeleteDomainEntry; }
{ return ((termOffsets != null) ? termOffsets.RamBytesUsed() : 0) + ((termsDictOffsets != null) ? termsDictOffsets.RamBytesUsed() : 0); }
public override string Decode() { byte[] raw = buffer; int msgB = RawParseUtils.tagMessage; if (msgB < 0) { return ""; } return RawParseUtils.decode(guessEncoding(), raw, msgB, raw.Length); }
public POIFSFileSystem() { (true); _header.BATCount = true; _header.BATArray = new int[] { 1 }; BATBlock bb = BATBlock.CreateEmptyBATBlock(bigBlockSize, false); bb.OurBlockIndex = 1; _bat_blocks.Add(bb); SetNextBlock(0, POIFSConstants.END_OF_CHAIN); SetNextBlock(1, POIFSConstants.FAT_SECTOR_BLOCK); _property_table.StartBlock = 0; }
public virtual void Method(int address) { slice = pool.buffers[address >> ByteBlockPool.BYTE_BLOCK_SHIFT]; assert slice != null; upto = address & ByteBlockPool.BYTE_BLOCK_MASK; offset0 = address; assert upto < ; }
public SubmoduleAddCommand SubmoduleAddCommand(string path) { m_path = path; return this; }
public virtual ListIngestionsResponse ListIngestions(ListIngestionsRequest request){var options = new InvokeOptions();options.RequestMarshaller = ListIngestionsRequestMarshaller.Instance;options.ResponseUnmarshaller = ListIngestionsResponseUnmarshaller.Instance;return Invoke<ListIngestionsResponse>(request, options);}
public QueryParserTokenManager(CharStream stream, int lexState) { SwitchTo(lexState); }
public override GetShardIteratorResult GetShardIteratorResult(GetShardIteratorRequest request){request = BeforeClientExecution(request);return executeGetShardIterator;}
public ModifyStrategyRequest() : base("aegis", "2016-11-11", "ModifyStrategy", "vipaegis") { }
public virtual bool HasAvailable() { lock (lock) { if (in == null) { throw new IOException("InputStreamReader is closed"); } try { return bytes.HasRemaining || in.Available() > 0; } catch { return false; } } }
public EscherOptRecord() { }
public virtual int Read(char[] buffer, int offset, int length) { if (buffer == null) throw new ArgumentNullException("buffer"); Arrays.CheckOffsetAndCount(buffer.Length, offset, length); if (length == 0) return 0; int copylen = count - pos < length ? count - pos : length; for (int i = 0; i < copylen; i++) buffer[offset + i] = this.buffer[pos + i]; return copylen; }
public OpenNLPSentenceBreakIterator() { this.sentenceOp = sentenceOp; }
public void Write(string str) { Write(str == null ? str : String.ValueOf((object)null)); }
public NotImplementedFunctionException(string functionName, NotImplementedException cause) : base(functionName, cause) { this.functionName = functionName; }
public override V V() { return base.nextEntry.GetValue(); }
public override void Read(byte[] b, int offset, int len, bool useBuffer) { available = bufferLength - bufferPosition; if (len <= available) { if (len > 0) Array.Copy(buffer, bufferPosition, b, offset, len); bufferPosition += len; } else { if (available > 0) { Array.Copy(buffer, bufferPosition, b, offset, available); offset += available; len -= available; bufferPosition += available; } if (useBuffer && len < bufferSize) { refill(); if (bufferLength < len) { Array.Copy(buffer, 0, b, offset, bufferLength); throw new EOFException("read past EOF: " + this); } else { Array.Copy(buffer, 0, b, offset, len); } } else { after = bufferStart + bufferPosition + len; if (after > length()) throw new EOFException("read past EOF: " + this); readInternal(b, offset, len); bufferStart = after; bufferPosition = 0; bufferLength = 0; } } }
public TagQueueResult TagQueueResult(TagQueueRequest request_1) { request_1 = beforeClientExecution; return executeTagQueue(request_1); }
public virtual void Method() { throw new NotSupportedException(); }
public virtual CacheSubnetGroup CacheSubnetGroup() { request = BeforeClientExecution(request); return ExecuteModifyCacheSubnetGroup(request); }
public MyClass(string @params): base(){base.SetParams(@params);language=country=variant="";StringTokenizer st=new StringTokenizer(@params,",");if(st.hasMoreTokens())language=st.nextToken();if(st.hasMoreTokens())country=st.nextToken();if(st.hasMoreTokens())variant=st.nextToken();}
public DeleteDocumentationVersionResult DeleteDocumentationVersionResult(DeleteDocumentationVersionRequest request){request = beforeClientExecution;return executeDeleteDocumentationVersion(request);}
public override bool Equals(object obj) { if (!(obj is FacetLabel)) return false; var other = (FacetLabel)obj; if (length != other.length) return false; for (int i = length - 1; ; i--) { if (!components[i].Equals(other.components[i])) return false; } return true; }
GetInstanceAccessDetailsResult GetInstanceAccessDetailsResult(GetInstanceAccessDetailsRequest request) { request = beforeClientExecution(request); return executeGetInstanceAccessDetails; }
public HSSFPolygon HSSFPolygon(HSSFChildAnchor anchor) { HSSFPolygon shape = new HSSFPolygon(this, anchor); shape.setParent(); shape.setAnchor(anchor); shapes.Add(shape); onCreate(shape); return shape; }
public override string String(int sheetIndex) { return getBoundSheetRec(sheetIndex).getSheetname; }
public override GetDashboardResult GetDashboard(GetDashboardRequest request) { request = BeforeClientExecution(request); return executeGetDashboard; }
public AssociateSigninDelegateGroupsWithAccountResult SomeMethod(AssociateSigninDelegateGroupsWithAccountRequest request) { request = beforeClientExecution; return executeAssociateSigninDelegateGroupsWithAccount(request); }
public virtual void MethodName(MulBlankRecord mbr){for(int j=0;j<mbr.GetNumColumns();j++){BlankRecord br=new BlankRecord();br.SetColumn((short)(j+mbr.GetFirstColumn()));br.SetRow(mbr.GetRow());br.SetXFIndex(mbr.GetXFAt());InsertCell(br);}}
public static string Escape(string @string){StringBuilder sb=new StringBuilder();sb.Append("\\Q");int apos=0;int k;while((k=@string.IndexOf("\\E",apos))>=0){sb.Append(@string.Substring(apos,k+2 -apos)).Append("\\\\E\\Q");apos=k+2;}return sb.Append(@string.Substring(apos)).Append("\\E").ToString();}
public ByteBuffer(ByteBuffer value) : base() { throw new ReadOnlyBufferException(); }
public ArrayPtg(object[][] values2d) { nColumns = values2d[0].Length; nRows = values2d.Length; _nColumns = (int)nColumns; _nRows = (int)nRows; object[] vv = new object[_nColumns * _nRows]; for (int r = 0; r < nRows; r++) { object[] rowData = values2d[r]; for (int c = 0; c < nColumns; c++) { vv[GetValueIndex(c, r)] = rowData[c]; } } _arrayValues = vv; _reserved0Int = 0; _reserved1Short = 0; _reserved2Byte = 0; }
public virtual GetIceServerConfigResponse GetIceServerConfig(GetIceServerConfigRequest request) { var options = new InvokeOptions(); options.RequestMarshaller = GetIceServerConfigRequestMarshaller.Instance; options.ResponseUnmarshaller = GetIceServerConfigResponseUnmarshaller.Instance; return Invoke<GetIceServerConfigResponse>(request, options); }
public string String(){return GetType().Name+" ["+GetValueAsString()+"]";}
public override string ToString() { return "ToChildBlockJoinQuery (" + parentQuery.ToString() + ")"; }
public void Method(){refCount.IncrementAndGet();}
UpdateConfigurationSetSendingEnabledResult UpdateConfigurationSetSendingEnabledResult(UpdateConfigurationSetSendingEnabledRequest request) { request = beforeClientExecution; return executeUpdateConfigurationSetSendingEnabled(request); }
{ return getXBATEntriesPerBlock() * ; }
public void MulShift(int pow10){TenPower tp=TenPower.GetInstance(Math.Abs(pow10));if(pow10<0){MulShift(tp._divisor,tp._divisorShift);}else{MulShift(tp._multiplicand,tp._multiplierShift);}}
public virtual string String() { StringBuilder b = new StringBuilder(); int l = length; b.Append(Path.DirectorySeparatorChar); for (int i = 0; i < l; i++) { b.Append(GetComponent(i)); if (i < l - 1) { b.Append(Path.DirectorySeparatorChar); } } return b.ToString(); }
public InstanceProfileCredentialsProvider InstanceProfileCredentialsProvider(ECSMetadataServiceCredentialsFetcher fetcher) { this.fetcher = fetcher; this.SetRoleName(roleName); return this; }
void (ProgressMonitor pm){ ; }
public virtual void Method(){if(!first){ptr=0;if(!Eof())ParseEntry();}}
public E E() { if (iterator.previousIndex() >= start) { return iterator.previous(); } throw new System.NotImplementedException(); }
String (  ) { return ; }
for (int i = 0; i < mSize; i++) if (mValues[i] == value) return -1;
public virtual List<CharsRef> ProcessStems(char[] word, int length) { List<CharsRef> stems = Stem(word, length); if (stems.Count < 2) { return stems; } CharArraySet terms = new CharArraySet(8, dictionary.IgnoreCase); List<CharsRef> deduped = new List<CharsRef>(); foreach (var s in stems) { if (!terms.Contains(s)) { deduped.Add(s); terms.Add(s); } } return deduped; }
public virtual GetGatewayResponsesResponse GetGatewayResponses(GetGatewayResponsesRequest request){var options = new InvokeOptions();options.RequestMarshaller = GetGatewayResponsesRequestMarshaller.Instance;options.ResponseUnmarshaller = GetGatewayResponsesResponseUnmarshaller.Instance;return Invoke<GetGatewayResponsesResponse>(request, options);}
public void Method(long pos) { currentBlockIndex = (int)(pos >> blockBits); currentBlock =; currentBlockUpto = (int)(pos & blockMask); }
s = Math.Min(available(), Math.Max(0, n)); ptr += s;
public BootstrapActionDetail() { SetBootstrapActionConfig(bootstrapActionConfig); }
public void Write(LittleEndianOutput @out) { @out.WriteShort(field_1_row); @out.WriteShort(field_2_col); @out.WriteShort(field_3_flags); @out.WriteShort(field_4_shapeid); @out.WriteShort(field_6_author.Length); @out.WriteByte(field_5_hasMultibyte ? 0x01 : 0x00); if (field_5_hasMultibyte) { StringUtil.PutUnicodeLE(field_6_author, @out); } else { StringUtil.PutCompressedUnicode(field_6_author, @out); } if (field_7_padding != null) { @out.WriteByte(field_7_padding.Value); } }
(String string) => lastIndexOf;
public virtual bool Add(E object) { return addLastImpl; }
public virtual void Method(StateType state, string subsection) { ConfigSnapshot src, res; do { src = state.Get(); res = UnsetSection(src, section, subsection); } while (!state.CompareAndSet(src, res)); }
public virtual string String(){return tagName;}
public void Add(int index, SubRecord element) { subrecords.Add(element); }
public bool Remove() { lock(mutex) { return Delegate().Remove(o); } }
public DoubleMetaphoneFilter DoubleMetaphoneFilter(TokenStream input) { return new DoubleMetaphoneFilter(); }
public virtual int InCoreLength() { return inCoreLength; }
internal virtual void Method(bool newValue) { }
public Pair(ContentSource oldSource, NewSourceType newSource) { this.oldSource = oldSource; this.newSource = newSource; }
public Entry Get(int i){if(){throw new ArrayIndexOutOfBoundsException(i);}return entries[i];}
public CreateRepoRequest() : base("cr", "2016-06-07", "CreateRepo", "cr") { ("/repos"); setMethod(); }
bool () { }
public virtual void Remove() { if (expectedModCount == list.modCount) { if (lastLink != null) { var next = lastLink.next; var previous = lastLink.previous; next.previous = previous; previous.next = next; if (lastLink == link) { pos--; } link = previous; lastLink = null; expectedModCount++; list.size--; list.modCount++; } else { throw new IllegalStateException(); } } else { throw new ConcurrentModificationException(); } }
public MergeShardsResult MergeShardsResult() { request = beforeClientExecution(request); return executeMergeShards(request); }
public override AllocateHostedConnectionResult AllocateHostedConnection(AllocateHostedConnectionRequest request) { request = BeforeClientExecution(request); return executeAllocateHostedConnection; }
public void MyMethod(){}
public static WeightedTerm WeightedTerm(Query query) { return getTerms(query, false); }
public ByteBuffer() { throw new ReadOnlyBufferException(); }
public void ProcessData(byte[] blocks, int blocksOffset, int[] values, int valuesOffset, int iterations) { for (int i = 0; i < iterations; ++i) { int byte0 = blocks[blocksOffset++] & 0xFF; values[valuesOffset++] = byte0 >> 2; int byte1 = blocks[blocksOffset++] & 0xFF; values[valuesOffset++] = ((byte0 & 3) << 4) | (byte1 >> 4); int byte2 = blocks[blocksOffset++] & 0xFF; values[valuesOffset++] = ((byte1 & 15) << 2) | (byte2 >> 6); values[valuesOffset++] = byte2 & 63; } }
public virtual string MethodName() { string s = GetPath(); if (s == "/" || s == "") s = GetHost(); if (s == null) throw new ArgumentException(); string[] elements; if (scheme == "file" || LOCAL_FILE.IsMatch(s)) elements = Regex.Split(s, @"[\\/]"); else elements = Regex.Split(s, "/+"); if (elements.Length == 0) throw new ArgumentException(); string result = elements[elements.Length - 1]; if (Constants.DOT_GIT.Equals(result)) result = elements[elements.Length - 2]; else if (result.EndsWith(Constants.DOT_GIT_EXT)) result = result.Substring(0, result.Length - Constants.DOT_GIT_EXT.Length); return result; }
public virtual DescribeNotebookInstanceLifecycleConfigResponse DescribeNotebookInstanceLifecycleConfig(DescribeNotebookInstanceLifecycleConfigRequest request){var options = new InvokeOptions();options.RequestMarshaller = DescribeNotebookInstanceLifecycleConfigRequestMarshaller.Instance;options.ResponseUnmarshaller = DescribeNotebookInstanceLifecycleConfigResponseUnmarshaller.Instance;return Invoke<DescribeNotebookInstanceLifecycleConfigResponse>(request, options);}
public String() { return; }
public CreateVpnConnectionResult CreateVpnConnectionResult(){request=BeforeClientExecution(request);return ExecuteCreateVpnConnection(request);}
public DescribeVoicesResult DescribeVoicesResult() { request = BeforeClientExecution(request); return ExecuteDescribeVoices(request); }
public virtual ListMonitoringExecutionsResult ListMonitoringExecutions(ListMonitoringExecutionsRequest request){var options = new InvokeOptions();options.RequestMarshaller = ListMonitoringExecutionsRequestMarshaller.Instance;options.ResponseUnmarshaller = ListMonitoringExecutionsResponseUnmarshaller.Instance;return Invoke<ListMonitoringExecutionsResult>(request, options);}
public DescribeJobRequest(string vaultName, string jobId) { VaultName = vaultName; JobId = jobId; }
public virtual EscherRecord GetEscherRecord(int index) { return escherRecords[index]; }
public virtual GetApisResult GetApisResult(GetApisRequest request) { request = beforeClientExecution; return executeGetApis(request); }
public virtual DeleteSmsChannelResponse DeleteSmsChannel(DeleteSmsChannelRequest request){var options = new InvokeOptions();options.RequestMarshaller = DeleteSmsChannelRequestMarshaller.Instance;options.ResponseUnmarshaller = DeleteSmsChannelResponseUnmarshaller.Instance;return Invoke<DeleteSmsChannelResponse>(request, options);}
public TrackingRefUpdate() { }
public virtual void MyMethod(){Print(String.ValueOf(b));}
public virtual QueryNode QueryNode() { return GetChildren()[0]; }
public NotIgnoredFilter(WorkdirTreeIndex workdirTreeIndex) { throw new System.NotImplementedException(); }
AreaRecord(RecordInputStream in1) { field_1_formatFlags = in1.ReadShort(); }
new GetThumbnailRequest(ProtocolType.HTTPS);
public virtual DescribeTransitGatewayVpcAttachmentsResult DescribeTransitGatewayVpcAttachmentsResult() { m_request = BeforeClientExecution(m_request); return ExecuteDescribeTransitGatewayVpcAttachments(m_request); }
public override PutVoiceConnectorStreamingConfigurationResult PutVoiceConnectorStreamingConfigurationResult() { request = BeforeClientExecution(request); return ExecutePutVoiceConnectorStreamingConfiguration(request); }
public override java.nio.OrdRange OrdRange(){return prefixToOrdRange.get(dim);}
public override string ToString(){string symbol = "";if(startIndex >=0 && startIndex < InputStream.size){symbol = InputStream.GetText(Interval.Of(startIndex, startIndex));symbol = Utils.EscapeWhitespace(symbol, false);}return string.Format(CultureInfo.CurrentCulture, "{0}('{1}')", typeof(LexerNoViableAltException).Name, symbol);}
public E E(){return peekFirstImpl;}
public virtual CreateWorkspacesResponse CreateWorkspaces(CreateWorkspacesRequest request){var options = new InvokeOptions();options.RequestMarshaller = CreateWorkspacesRequestMarshaller.Instance;options.ResponseUnmarshaller = CreateWorkspacesResponseUnmarshaller.Instance;return Invoke<CreateWorkspacesResponse>(request, options);}
public NumberFormatIndexRecord Copy() { return copy; }
public DescribeRepositoriesResult DescribeRepositoriesResult(){request = beforeClientExecution(request);return executeDescribeRepositories(request);}
public SparseIntArray(int initialCapacity) { initialCapacity = ArrayUtils.IdealIntArraySize(initialCapacity); mKeys = new int[initialCapacity]; mValues = new int[initialCapacity]; mSize = 0; }
public HyphenatedWordsFilter HyphenatedWordsFilter() { return new HyphenatedWordsFilter(input); }
public virtual CreateDistributionWithTagsResult CreateDistributionWithTagsResult() { request = BeforeClientExecution(request); return ExecuteCreateDistributionWithTags(request); }
public RandomAccessFile(string fileName, string mode){new File(fileName);}
public DeleteWorkspaceImageResult DeleteWorkspaceImageResult(DeleteWorkspaceImageRequest request){request = BeforeClientExecution(request);return executeDeleteWorkspaceImage;}
public static string Method(int value) { StringBuilder sb = new StringBuilder(16); writeHex(sb, value, 16, ""); return sb.ToString(); }
public static UpdateDistributionResult UpdateDistributionResult(UpdateDistributionRequest request) { request = beforeClientExecution(request); return executeUpdateDistribution; }
public HSSFColor GetColor(int index) { if (index == HSSFColorPredefined.AUTOMATIC.GetIndex()) { return HSSFColorPredefined.AUTOMATIC.GetColor(); } byte[] b = _palette.GetColor(index); return b == null ? null : new CustomColor(); }
public ValueEval(ValueEval operands, int srcRow, int srcCol) { throw new NotImplementedFunctionException(_functionName); }
public override void Method() { out.WriteShort((short)field_1_number_crn_records); out.WriteShort((short)field_2_sheet_table_index); }
public DescribeDBEngineVersionsResult DescribeDBEngineVersionsResult() { return DescribeDBEngineVersions(new DescribeDBEngineVersionsRequest()); }
public FormatRun(char character, int fontIndex) { this._character = character; this._fontIndex = fontIndex; }
public static byte[] ConvertChars(char[] chars, int offset, int length) { byte[] result = new byte[length * 2]; int end = offset + length; int resultIndex = 0; for (int i = offset; i < end; i++) { char ch = chars[i]; result[resultIndex++] = (byte)(ch >> 8); result[resultIndex++] = (byte)ch; } return result; }
public UploadArchiveResult UploadArchiveResult(UploadArchiveRequest request) { request = beforeClientExecution; return ExecuteUploadArchive(request); }
public java.util.List List(int tokenIndex){return getHiddenTokensToLeft(tokenIndex,-1);}
public override bool Equals(object obj){if(ReferenceEquals(this,obj))return true;if(!base.Equals(obj))return false;if(GetType()!=obj.GetType())return false;var other=(AutomatonQuery)obj;if(!compiled.Equals(other.compiled))return false;if(term==null){if(other.term!=null)return false;}else if(!term.Equals(other.term))return false;return true;}
public SpanQuery SpanQuery(){SpanQuery[] spanQueries=new SpanQuery[weightBySpanQuery.Count];int i=0;foreach(var sq in weightBySpanQuery.Keys){float boost=weightBySpanQuery[sq];spanQueries[i++]=(boost!=1f)?new SpanBoostQuery(sq,boost):sq;}return spanQueries.Length==1?spanQueries[0]:new SpanOrQuery(spanQueries);}
public StashCreateCommand StashCreateCommand() { return new StashCreateCommand(); }
public FieldInfo FieldInfo { get { return byName[fieldName]; } internal set { byName[fieldName] = value; } }
public virtual DescribeEventSourceResult DescribeEventSourceResult(DescribeEventSourceRequest request){request = beforeClientExecution;return ExecuteDescribeEventSource(request);}
public override GetDocumentAnalysisResult GetDocumentAnalysisResult() { request = beforeClientExecution(request); return executeGetDocumentAnalysis(request); }
public virtual CancelUpdateStackResult CancelUpdateStackResult(){request=beforeClientExecution(request);return executeCancelUpdateStack(request);}
public virtual ModifyLoadBalancerAttributesResponse ModifyLoadBalancerAttributes(ModifyLoadBalancerAttributesRequest request) { request = beforeClientExecution; return executeModifyLoadBalancerAttributes(request); }
public virtual SetInstanceProtectionResponse SetInstanceProtection(SetInstanceProtectionRequest request){var options = new InvokeOptions();options.RequestMarshaller = SetInstanceProtectionRequestMarshaller.Instance;options.ResponseUnmarshaller = SetInstanceProtectionResponseUnmarshaller.Instance;return Invoke<SetInstanceProtectionResponse>(request, options);}
public virtual ModifyDBProxyResult ModifyDBProxyResult(ModifyDBProxyRequest request){request = beforeClientExecution(request);return c.executeModifyDBProxy;}
public override void add(char[] output, int offset, int len, int endOffset, int posLength) { if (count == outputs.Length) { outputs = ArrayUtil.Grow(outputs, count + 1); } if (count == endOffsets.Length) { int[] next = new int[ArrayUtil.Oversize(1 + count, sizeof(int))]; Array.Copy(endOffsets, 0, next, 0, count); endOffsets = next; } if (count == posLengths.Length) { int[] next = new int[ArrayUtil.Oversize(1 + count, sizeof(int))]; Array.Copy(posLengths, 0, next, 0, count); posLengths = next; } if (outputs[count] == null) { outputs[count] = new CharsRefBuilder(); } outputs[count].CopyChars(output, offset, len); endOffsets[count] = endOffset; posLengths[count] = posLength; count++; }
public FetchLibrariesRequest() : base("CloudPhoto", "2017-07-11", "FetchLibraries", "cloudphoto") { ; }
public virtual bool Exists() { return fs.exists; }
FilterOutputStream() { this.out = out; }
new ScaleClusterRequest("/clusters/[ClusterId]").setMethod(MethodType.Put);
public virtual DataValidationConstraint createTimeConstraint(int operatorType, string formula1, string formula2) { return DVConstraint.createTimeConstraint(operatorType, formula1, formula2); }
public ListObjectParentPathsResult ListObjectParentPathsResult(){request = BeforeClientExecution(request);return ExecuteListObjectParentPaths(request);}
public virtual DescribeCacheSubnetGroupsResponse DescribeCacheSubnetGroups(DescribeCacheSubnetGroupsRequest request) { request = beforeClientExecution(request); return executeDescribeCacheSubnetGroups; }
field_5_options = sharedFormula.SetShortBoolean(field_5_options, flag);
public virtual bool Method(){var options = new InvokeOptions();options.RequestMarshaller = MethodRequestMarshaller.Instance;options.ResponseUnmarshaller = BooleanResponseUnmarshaller.Instance;return Invoke<bool>(null, options);}
public virtual ErrorNode ErrorNode(Token badToken){lock(mutex){ErrorNodeImpl t = new ErrorNodeImpl(badToken); addAnyChild(t); t.setParent; return t;}}
public LatvianStemFilterFactory(Dictionary<object, object> args) : base(args) { if (args.Count != 0) { throw new ArgumentException("Unknown parameters: " + args); } }
public override EventSubscription EventSubscription() { request = beforeClientExecution(request); return executeRemoveSourceIdentifierFromSubscription(request); }
public static TokenFilterFactory NewInstance(string name, Dictionary<string, string> args) { return loader.NewInstance(name, args); }
public AddAlbumPhotosRequest() : base("CloudPhoto", "2017-07-11", "AddAlbumPhotos", "cloudphoto") {}
public GetThreatIntelSetResult GetThreatIntelSetResult(GetThreatIntelSetRequest request) { request = beforeClientExecution(request); return executeGetThreatIntelSet; }
public Binary RevFilter() { return new Binary(a.Clone(), b.Clone()); }
public bool Method(object o) { return; }
public virtual bool HasArray() { return ProtectedHasArray(); }
public UpdateContributorInsightsResult UpdateContributorInsightsResult(UpdateContributorInsightsRequest request) { request = m_beforeClientExecution; return executeUpdateContributorInsights(request); }
private virtual void Method(){records.Remove(fileShare);records.Remove(writeProtect);writeProtect = null;}
public SolrSynonymParser(bool dedup, bool expand, Analyzer analyzer) : base(dedup, analyzer) { this.expand = expand; }
public virtual RequestSpotInstancesResult RequestSpotInstancesResult(){ request = beforeClientExecution(request); return executeRequestSpotInstances(request); }
() => { return findObjectRecord.getObjectData(); }
public virtual GetContactAttributesResult GetContactAttributesResult(GetContactAttributesRequest request){request = beforeClientExecution;return executeGetContactAttributes(request);}
public override string ToString(){return getKey + ": " + getValue();}
public ListTextTranslationJobsResult ListTextTranslationJobsResult(){request=BeforeClientExecution(request);return ExecuteListTextTranslationJobs(request);}
public virtual GetContactMethodsResponse GetContactMethods(GetContactMethodsRequest request){var options = new InvokeOptions();options.RequestMarshaller = GetContactMethodsRequestMarshaller.Instance;options.ResponseUnmarshaller = GetContactMethodsResponseUnmarshaller.Instance;return Invoke<GetContactMethodsResponse>(request, options);}
public static int GetFunctionIndex(){FunctionMetadata fd = GetInstance().GetFunctionByNameInternal(name);if(fd == null){fd = GetInstanceCetab().GetFunctionByNameInternal(name);if(fd == null){return -1;}}return fd.GetIndex();}
private DescribeAnomalyDetectorsResult DescribeAnomalyDetectorsResult(DescribeAnomalyDetectorsRequest request) { request = beforeClientExecution; return ExecuteDescribeAnomalyDetectors(request); }
public static string InsertId(string message, ObjectId changeId) { return insertId; }
public int SomeMethod(AnyObjectId objectId, ObjectType typeHint){int sz=db.GetObjectSize();if(sz<0){if(typeHint==ObjectType.OBJ_ANY)throw new MissingObjectException(objectId.Copy(),JGitText.Instance.unknownObjectType2);throw new MissingObjectException(objectId.Copy(),typeHint);}return sz;}
public virtual ImportInstallationMediaResult ImportInstallationMediaResult(){request = beforeClientExecution(request);return executeImportInstallationMedia(request);}
public override PutLifecycleEventHookExecutionStatusResult PutLifecycleEventHookExecutionStatusResult(){request = beforeClientExecution(request); return executePutLifecycleEventHookExecutionStatus(request);}
public NumberPtg() { @in.ReadDouble(); }
public GetFieldLevelEncryptionConfigResult GetFieldLevelEncryptionConfigResult(GetFieldLevelEncryptionConfigRequest request) { request = beforeClientExecution; return executeGetFieldLevelEncryptionConfig(request); }
public virtual DescribeDetectorResponse DescribeDetector(DescribeDetectorRequest request){var options = new InvokeOptions();options.RequestMarshaller = DescribeDetectorRequestMarshaller.Instance;options.ResponseUnmarshaller = DescribeDetectorResponseUnmarshaller.Instance;return Invoke<DescribeDetectorResponse>(request, options);}
public ReportInstanceStatusResult ReportInstanceStatusResult(ReportInstanceStatusRequest request) { request = beforeClientExecution; return executeReportInstanceStatus(request); }
public virtual DeleteAlarmResponse DeleteAlarm(DeleteAlarmRequest request){var options = new InvokeOptions();options.RequestMarshaller = DeleteAlarmRequestMarshaller.Instance;options.ResponseUnmarshaller = DeleteAlarmResponseUnmarshaller.Instance;return Invoke<DeleteAlarmResponse>(request, options);}
public virtual TokenStream TokenStream() { return new PortugueseStemFilter(input); }
public FtCblsSubRecord() { reserved = new ; }
public override bool Remove(object obj) { lock (mutex) { return c.Remove(obj); } }
public GetDedicatedIpResult GetDedicatedIpResult() { request = beforeClientExecution(request); return executeGetDedicatedIp(request); }
public static string String() { return; }
public override ListStreamProcessorsResult ListStreamProcessorsResult(ListStreamProcessorsRequest request) { request = this._enclosing._beforeClientExecution; return this._enclosing.executeListStreamProcessors(request); }
public DeleteLoadBalancerPolicyRequest(string loadBalancerName, string policyName) { LoadBalancerName = loadBalancerName; PolicyName = policyName; }
public WindowProtectRecord(Options options) { }
public UnbufferedCharStream(int bufferSize) { this.Data = new char[bufferSize]; }
public GetOperationsResult GetOperationsResult(GetOperationsRequest request) { request = beforeClientExecution(request); return executeGetOperations; }
public void Write(byte[] b, int o) { NB.EncodeInt32(b, o, w1); NB.EncodeInt32(b, o + 4, w2); NB.EncodeInt32(b, o + 8, w3); NB.EncodeInt32(b, o + 12, w4); NB.EncodeInt32(b, o + 16, w5); }
public WindowOneRecord(RecordInputStream input){field_1_h_hold=input.ReadShort();field_2_v_hold=input.ReadShort();field_3_width=input.ReadShort();field_4_height=input.ReadShort();field_5_options=input.ReadShort();field_6_active_sheet=input.ReadShort();field_7_first_visible_tab=input.ReadShort();field_8_num_selected_tabs=input.ReadShort();field_9_tab_width_ratio=input.ReadShort();}
public StopWorkspacesResult StopWorkspacesResult(StopWorkspacesRequest request) { request = this.beforeClientExecution; return this.ExecuteStopWorkspaces(request); }
public void Close(){if(isOpen){isOpen=false;try{Dump();}finally{try{channel.SetLength(fileLength);}finally{try{channel.Close();}finally{fos.Close();}}}}}
public DescribeMatchmakingRuleSetsResult DescribeMatchmakingRuleSetsResult() { request = BeforeClientExecution(request); return ExecuteDescribeMatchmakingRuleSets(request); }
public string String(int wordId, string[] surface, int off, int len) { }
public String() { }
public static double CalculateVariance(double[] v) { double r = double.NaN; if (v != null && v.Length >= 1) { double m = 0, s = 0; int n = v.Length, i; for (i = 0; i < n; i++) s += v[i]; m = s / n; s = 0; for (i = 0; i < n; i++) s += (v[i] - m) * (v[i] - m); r = n == 1 ? 0 : s; } return r; }
public virtual DescribeResizeResponse DescribeResize(DescribeResizeRequest request){var options = new InvokeOptions();options.RequestMarshaller = DescribeResizeRequestMarshaller.Instance;options.ResponseUnmarshaller = DescribeResizeResponseUnmarshaller.Instance;return Invoke<DescribeResizeResponse>(request, options);}
public virtual bool PassedThroughNonGreedyDecision() { return passedThroughNonGreedyDecision; }
public virtual ResponseType Method(){return end;}
public void Process(CellHandler handler) { firstRow = range.FirstRow; lastRow = range.LastRow; firstColumn = range.FirstColumn; lastColumn = range.LastColumn; width = lastColumn - firstColumn + 1; SimpleCellWalkContext ctx = new SimpleCellWalkContext(); Row currentRow = null; Cell currentCell = null; for (ctx.rowNumber = firstRow; ctx.rowNumber <= lastRow; ++ctx.rowNumber) { currentRow = sheet.GetRow(ctx.rowNumber); if (currentRow == null) { continue; } for (ctx.colNumber = firstColumn; ctx.colNumber <= lastColumn; ++ctx.colNumber) { currentCell = currentRow.GetCell(ctx.colNumber); if (currentCell == null) { continue; } if (IsEmpty(currentCell) && !traverseEmptyCells) { continue; } rowSize = ArithmeticUtils.MulAndCheck(() => ArithmeticUtils.SubAndCheck(ctx.rowNumber - firstRow), () => width); ctx.ordinalNumber = ArithmeticUtils.AddAndCheck(rowSize, (ctx.colNumber - firstColumn + 1)); handler.OnCell(currentCell, ctx); } } }
public void Method() { }
public virtual int compare(ScoreTerm other){if(this.boost == other.boost)return other.bytes.get().CompareTo(this.bytes.get());elsereturn this.boost.CompareTo(other.boost);}
public int method(char[] s, int len){for(int i=0;i<len;i++){switch(s[i]){case FARSI_YEH:case YEH_BARREE:s[i]=YEH;goto case KEHEH;case KEHEH:s[i]=KAF;break;case HEH_YEH:case HEH_GOAL:s[i]=HEH;break;case HAMZA_ABOVE:len=delete(s,i,len);i--;break;default:break;}}}return len;}
public void WriteShort(LittleEndianOutput @out) { @out.writeShort(); }
public DiagnosticErrorListener() { this.exactOnly = exactOnly; }
public KeySchemaElement(string attributeName) { SetAttributeName(attributeName); SetKeyType(keyType.ToString()); }
public virtual GetAssignmentResult GetAssignmentResult(){this.request=BeforeClientExecution(this.request);return ExecuteGetAssignment(this.request);}
public bool Matches(AnyObjectId id){return FindOffset(id)!=-1;}
public GroupingSearch GroupingSearch(bool allGroups) { this.allGroups = allGroups; return this; }
public virtual void someMethod(string dimName, bool v) { lock (this) { DimConfig ft = fieldTypes.get(dimName); if (ft == null) { ft = new DimConfig(); fieldTypes.put(dimName, ft); } ft.multiValued = v; } }
public virtual int GetSize() { int size = 0; foreach (char c in cells.Keys) { Cell e = at(c); if (e.cmd >= 0) { size++; } } return size; }
public virtual DeleteVoiceConnectorResult DeleteVoiceConnectorResult(DeleteVoiceConnectorRequest request) { request = beforeClientExecution; return executeDeleteVoiceConnector(request); }
public DeleteLifecyclePolicyResult DeleteLifecyclePolicyResult(DeleteLifecyclePolicyRequest request){request = beforeClientExecution;return executeDeleteLifecyclePolicy(request);}
