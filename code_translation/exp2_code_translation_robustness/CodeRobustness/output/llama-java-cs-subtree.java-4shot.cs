public void WriteLittleEndianOutput(LittleEndianOutput out) { out.WriteShort(); }
void Process(BlockList<T> src) { if (src.size == 0) srcDirIdx = 0; for (; srcDirIdx < src.tailDirIdx; srcDirIdx++) addAll(src.directory[srcDirIdx], 0, BLOCK_SIZE); if (src.tailBlkIdx != 0) addAll(src.tailBlock, 0, src.tailBlkIdx); }
public void Add(byte b) { if (upto == blockSize) { if (currentBlock != null) { addBlock(currentBlock); } currentBlock = new byte[blockSize]; upto = 0; } currentBlock[upto++] = b; }
public ObjectId() { }
public virtual DeleteDomainEntryResponse DeleteDomainEntry(DeleteDomainEntryRequest request) { request = beforeClientExecution(request); return executeDeleteDomainEntry(request); }
public long RamBytesUsed() { return (termOffsets != null ? termOffsets.RamBytesUsed() : 0) + (termsDictOffsets != null ? termsDictOffsets.RamBytesUsed() : 0); }
public string GetMessage() { byte[] raw = buffer; int msgB = RawParseUtils.tagMessage; if (msgB < 0) return ""; return RawParseUtils.Decode(GuessEncoding(), raw, msgB, raw.Length); }
public POIFSFileSystem(){_header.setBATCount(1); _header.setBATArray(new int[]{1}); BATBlock bb = BATBlock.createEmptyBATBlock(bigBlockSize, false); bb.setOurBlockIndex(1); _bat_blocks.Add(bb); setNextBlock(0, POIFSConstants.END_OF_CHAIN); setNextBlock(1, POIFSConstants.FAT_SECTOR_BLOCK); _property_table.setStartBlock(0);}
void set(int address) { slice = pool.buffers[address >> ByteBlockPool.BYTE_BLOCK_SHIFT]; Debug.Assert(slice != null); upto = address & ByteBlockPool.BYTE_BLOCK_MASK; offset0 = address; Debug.Assert(upto < slice.Length); }
public SubmoduleAddCommand(string path) { _path = path; }
public ListIngestionsResult() { request = BeforeClientExecution(request); return ExecuteListIngestions(request); }
public QueryParserTokenManager(CharStream stream, int lexState) { ; SwitchTo(lexState); }
public virtual GetShardIteratorResponse GetShardIterator(GetShardIteratorRequest request) { request = BeforeClientExecution(request); return ExecuteGetShardIterator(request); }
public ModifyStrategyRequest() : base("aegis", "2016-11-11", "ModifyStrategy", "vipaegis") { }
public virtual bool HasRemaining() throws IOException { lock (lock) { if (in == null) { throw new IOException("InputStreamReader is closed"); } try { return bytes.HasRemaining || in.Available() > 0; } catch (Exception e) { return false; } }
public EscherOptRecord() { }
public synchronized (char[] buffer, int offset, int length) Read(char[] buffer, int offset, int length) { if (buffer == null) throw new ArgumentNullException(nameof(buffer), "buffer == null"); if (offset < 0 || length < 0 || offset + length > buffer.Length) throw new ArgumentException("Invalid offset or length"); if (length == 0) return (0, 0, 0); int copylen = Math.Min(count - pos, length); for (int i = 0; i < copylen; i++) buffer[offset + i] = this.buffer[pos + i]; pos += copylen; return (buffer, offset, copylen); }
public OpenNLPSentenceBreakIterator() { this.sentenceOp = sentenceOp; }
public void Write(string str) => Write(str ?? string.Empty);
public NotImplementedFunctionException(string functionName, NotImplementedException cause) : base(functionName, cause) { }
public virtual V Next() { return ((IEnumerator<KeyValuePair<K, V>>)base).Current.Value; }
public void Read(byte[] b, int offset, int len, bool useBuffer) {      int available = bufferLength - bufferPosition;      if (len <= available) {          if (len > 0)              Array.Copy(buffer, bufferPosition, b, offset, len);          bufferPosition += len;      }      else {          if (available > 0) {              Array.Copy(buffer, bufferPosition, b, offset, available);              offset += available;              len -= available;              bufferPosition += available;          }          if (useBuffer && len < bufferSize) {              Refill();              if (bufferLength < len) {                  Array.Copy(buffer, 0, b, offset, bufferLength);                  throw new EndOfStreamException("read past EOF: " + this);              }              else {                  Array.Copy(buffer, 0, b, offset, len);              }          }          else {              long after = bufferStart + bufferPosition + len;              if (after > Length())                  throw new EndOfStreamException("read past EOF: " + this);              ReadInternal(b, offset, len);              bufferStart = after;              bufferPosition = 0;              bufferLength = 0;          }      }  }
public virtual TagQueueResponse TagQueue(TagQueueRequest request) { request = beforeClientExecution(request); return executeTagQueue(request); }
void () { throw new NotSupportedException(); }
public virtual ModifyCacheSubnetGroupResponse CacheSubnetGroup(ModifyCacheSubnetGroupRequest request) { request = beforeClientExecution(request); return executeModifyCacheSubnetGroup(request); }
public void SetParams(string @params) { base.SetParams(@params); language = country = variant = ""; string[] parts = @params.Split(','); language = parts.Length > 0 ? parts[0] : ""; country = parts.Length > 1 ? parts[1] : ""; variant = parts.Length > 2 ? parts[2] : ""; }
public virtual DeleteDocumentationVersionResponse DeleteDocumentationVersion(DeleteDocumentationVersionRequest request) { request = beforeClientExecution(request); return executeDeleteDocumentationVersion(request); }
public override bool Equals(object obj) { if (!(obj is FacetLabel)) return false; FacetLabel other = (FacetLabel)obj; if (length != other.length) return false; for (int i = length - 1; i >= 0; i--) { if (!components[i].Equals(other.components[i])) return false; } return true; }
public virtual GetInstanceAccessDetailsResponse GetInstanceAccessDetails(GetInstanceAccessDetailsRequest request) { var options = new InvokeOptions(); options.RequestMarshaller = GetInstanceAccessDetailsRequestMarshaller.Instance; options.ResponseUnmarshaller = GetInstanceAccessDetailsResponseUnmarshaller.Instance; return Invoke<GetInstanceAccessDetailsResponse>(request, options); }
public HSSFPolygon(HSSFChildAnchor anchor) { HSSFPolygon shape = new HSSFPolygon(this, anchor); shape.setParent(); shape.setAnchor(anchor); shapes.Add(shape); onCreate(shape); return shape; }
public virtual string GetString(int sheetIndex) { return GetBoundSheetRec(sheetIndex).GetSheetName(); }
public virtual GetDashboardResult GetDashboard(GetDashboardRequest request) => ExecuteGetDashboard(BeforeClientExecution(request));
public virtual AssociateSigninDelegateGroupsWithAccountResponse AssociateSigninDelegateGroupsWithAccount(AssociateSigninDelegateGroupsWithAccountRequest request) { request = beforeClientExecution(request); return executeAssociateSigninDelegateGroupsWithAccount(request); }
void MulBlankRecord(MulBlankRecord mbr) { for (int j = 0; j < mbr.getNumColumns(); j++) { BlankRecord br = new BlankRecord(); br.setColumn((short)(j + mbr.getFirstColumn())); br.setRow(mbr.getRow()); br.setXFIndex(mbr.getXFAt()); insertCell(br); } }
public static string Escape(string str) { var string_ = str; var sb = new StringBuilder(); sb.Append("\\Q"); var apos = 0; int k; while ((k = string_.IndexOf("\\E", apos)) >= 0) { sb.Append(string_.Substring(apos, k + 2)).Append("\\\\E\\Q"); apos = k + 2; } return sb.Append(string_.Substring(apos)).Append("\\E").ToString(); }
public ByteBuffer(int value) { throw new ReadOnlyBufferException(); }
public ArrayPtg(object[,] values2d) { nColumns = values2d.GetLength(1); nRows = values2d.GetLength(0); _nColumns = nColumns; _nRows = nRows; object[] vv = new object[_nColumns * _nRows]; for (int r = 0; r < nRows; r++) { for (int c = 0; c < nColumns; c++) { vv[getValueIndex(c, r)] = values2d[r, c]; } } _arrayValues = vv; _reserved0Int = 0; _reserved1Short = 0; _reserved2Byte = 0; }
public GetIceServerConfigResult GetIceServerConfig(GetIceServerConfigRequest request) { request = beforeClientExecution; return executeGetIceServerConfig(request); }
public override string ToString() { return GetType().Name + " [" + GetValueAsString() + "]"; }
public override string ToString() { return $"ToChildBlockJoinQuery ({parentQuery.ToString()})"; }
void Increment() => Interlocked.Increment(ref refCount);
public virtual UpdateConfigurationSetSendingEnabledResponse UpdateConfigurationSetSendingEnabled(UpdateConfigurationSetSendingEnabledRequest request) { var options = new InvokeOptions(); options.RequestMarshaller = UpdateConfigurationSetSendingEnabledRequestMarshaller.Instance; options.ResponseUnmarshaller = UpdateConfigurationSetSendingEnabledResponseUnmarshaller.Instance; return Invoke<UpdateConfigurationSetSendingEnabledResponse>(request, options); }
public int getXBATEntriesPerBlock() { return XBATEntriesPerBlock; }
void MulShift(int pow10) { TenPower tp = TenPower.Instance(Math.Abs(pow10)); if (pow10 < 0) MulShift(1, tp._divisorShift); else MulShift(tp._multiplicand, tp._multiplierShift); }
public override string ToString() { StringBuilder b = new StringBuilder(); int l = length; b.Append(File.separatorChar); for (int i = 0; i < l; i++) { b.Append(getComponent(i)); if (i < l - 1) { b.Append(File.separatorChar); } } return b.ToString(); }
public InstanceProfileCredentialsProvider(ECSMetadataServiceCredentialsFetcher fetcher) { this.fetcher = fetcher; }
public virtual void Init(ProgressMonitor pm) { }
public void Reset(){if (!first){ptr = 0;if (!Eof()) ParseEntry();}}
public object E() { if (iterator.PreviousIndex() >= start) { return iterator.Previous(); } throw new InvalidOperationException(); }
public string ToString() { return ""; }
for (int i = 0; i < mSize; i++) if (mValues[i] == value) return -1;
List<CharsRef> List<CharsRef>(char[] word, int length) { List<CharsRef> stems = Stem(word, length); if (stems.Count < 2) return stems; CharArraySet terms = new CharArraySet(8, dictionary.IgnoreCase); List<CharsRef> deduped = new List<CharsRef>(); foreach (CharsRef s in stems) { if (!terms.Contains(s)) { deduped.Add(s); terms.Add(s); } } return deduped; }
public virtual GetGatewayResponsesResponse GetGatewayResponses(GetGatewayResponsesRequest request) => ExecuteGetGatewayResponses(BeforeClientExecution(request));
void Seek(int pos) { currentBlockIndex = (int)(pos >> blockBits); currentBlock = null; currentBlockUpto = (int)(pos & blockMask); }
(n) => { s = Math.Min(Available(), Math.Max(0, n)); ptr += s; }
public BootstrapActionDetail() { _bootstrapActionConfig = bootstrapActionConfig; }
public virtual void Write(LittleEndianOutput @out) { @out.WriteShort(field_1_row); @out.WriteShort(field_2_col); @out.WriteShort(field_3_flags); @out.WriteShort(field_4_shapeid); @out.WriteShort((short)field_6_author.Length); @out.WriteByte((byte)(field_5_hasMultibyte ? 0x01 : 0x00)); if (field_5_hasMultibyte) StringUtil.PutUnicodeLE(field_6_author, @out); else StringUtil.PutCompressedUnicode(field_6_author, @out); if (field_7_padding.HasValue) @out.WriteByte((byte)field_7_padding.Value); }
public int LastIndexOf(string s) { return LastIndexOf(s); }
bool (E object) => AddLastImpl;
void Unset(string section, string subsection) { ConfigSnapshot src; ConfigSnapshot res; do { src = state.Get(); res = UnsetSection(src, section, subsection); } while (!state.CompareAndSet(src, res)); }
public string ToString() { return tagName; }
public void Add(int index, SubRecord element) => subrecords.Add(element);
public bool Remove(object o) { lock (mutex) { return delegate.Remove(o); } }
public DoubleMetaphoneFilter(TokenStream input) { return new DoubleMetaphoneFilter(input); }
public int getInCoreLength() { return inCoreLength; }
public void SetValue(bool newValue) { }
public Pair(ContentSource oldSource, ContentSource newSource) { this.oldSource = oldSource; this.newSource = newSource; }
public T this[int i] { get { if (i < 0 || i >= entries.Length) throw new IndexOutOfRangeException(); return entries[i]; } }
public CreateRepoRequest() : base("cr", "2016-06-07", "CreateRepo", "cr") { Path = "/repos"; Method = HttpMethod.Post; }
bool() { }
public virtual void Remove(){if (expectedModCount == list.modCount){if (lastLink != null){Link next = lastLink.next;Link<ET> previous = lastLink.previous;next.previous = previous;previous.next = next;if (lastLink == link){pos--; }link = previous;lastLink = null;expectedModCount++;list.size--;list.modCount++;}else{throw new InvalidOperationException();}}else{throw new InvalidOperationException("Collection was modified");}}
public MergeShardsResult() { request = BeforeClientExecution(request); return ExecuteMergeShards(request); }
AllocateHostedConnectionResult(AllocateHostedConnectionRequest request) { request = BeforeClientExecution(request); return ExecuteAllocateHostedConnection(); }
public void MethodName() { }
public static WeightedTerm GetTerms(Query query) => GetTerms(query, false);
public ByteBuffer() { throw new ReadOnlyBufferException(); }
void Decode([ ] blocks, int blocksOffset, [ ] values, int valuesOffset, int iterations) { for (int i = 0; i < iterations; ++i) { int byte0 = blocks[blocksOffset++] & 0xFF; values[valuesOffset++] = byte0 >>> 2; int byte1 = blocks[blocksOffset++] & 0xFF; values[valuesOffset++] = ((byte0 & 3) << 4) | (byte1 >>> 4); int byte2 = blocks[blocksOffset++] & 0xFF; values[valuesOffset++] = ((byte1 & 0x0F) << 2) | (byte2 >>> 6); values[valuesOffset++] = byte2 & 0x3F; } }
public virtual string GetName() { string s = GetPath(); if ("/".Equals(s) || "".Equals(s)) s = GetHost(); if (s == null) throw new System.ArgumentException(); string[] elements; if ("file".Equals(scheme) || LOCAL_FILE.matcher(s).matches()) elements = s.Split(new[] { "\\" + System.IO.Path.DirectorySeparatorChar, "/" }, StringSplitOptions.RemoveEmptyEntries); else elements = s.Split("/+".ToCharArray(), StringSplitOptions.RemoveEmptyEntries); if (elements.Length == 0) throw new System.ArgumentException(); string result = elements[elements.Length - 1]; if (Constants.DOT_GIT.Equals(result)) result = elements[elements.Length - 2]; else if (result.EndsWith(Constants.DOT_GIT_EXT)) result = result.Substring(0, result.Length - Constants.DOT_GIT_EXT.Length); return result; }
public virtual DescribeNotebookInstanceLifecycleConfigResponse DescribeNotebookInstanceLifecycleConfig(DescribeNotebookInstanceLifecycleConfigRequest request) { request = BeforeClientExecution(request); return ExecuteDescribeNotebookInstanceLifecycleConfig(request); }
public override string ToString() { return ""; }
public CreateVpnConnectionResult() { request = BeforeClientExecution(request); return ExecuteCreateVpnConnection(request); }
public DescribeVoicesResult() { request = BeforeClientExecution(request); return ExecuteDescribeVoices(request); }
public ListMonitoringExecutionsResult ListMonitoringExecutions(ListMonitoringExecutionsRequest request) { request = BeforeClientExecution(request); return ExecuteListMonitoringExecutions(); }
public DescribeJobRequest(string vaultName, string jobId) { _vaultName = vaultName; _jobId = jobId; }
public EscherRecord GetEscherRecord(int index) { return escherRecords[index]; }
public virtual GetApisResponse GetApis(GetApisRequest request) { var options = new InvokeOptions(); options.RequestMarshaller = GetApisRequestMarshaller.Instance; options.ResponseUnmarshaller = GetApisResponseUnmarshaller.Instance; return Invoke<GetApisResponse>(request, options); }
public virtual DeleteSmsChannelResponse DeleteSmsChannel(DeleteSmsChannelRequest request) { request = beforeClientExecution(request); return executeDeleteSmsChannel(request); }
public TrackingRefUpdate() { }
void Print(string.Format("{0}", b));
public QueryNode() { return (QueryNode)getChildren[0]; }
public NotIgnoredFilter(TreeIndex workdirTreeIndex) { _workdirTreeIndex = workdirTreeIndex; }
public AreaRecord(RecordInputStream in) { field_1_formatFlags = in.ReadShort(); }
GetThumbnailRequest(ProtocolType.HTTPS);
public DescribeTransitGatewayVpcAttachmentsResult() { request = BeforeClientExecution(request); return ExecuteDescribeTransitGatewayVpcAttachments(request); }
public virtual PutVoiceConnectorStreamingConfigurationResponse PutVoiceConnectorStreamingConfiguration(PutVoiceConnectorStreamingConfigurationRequest request) { request = BeforeClientExecution(request); return ExecutePutVoiceConnectorStreamingConfiguration(request); }
public OrdRange OrdRange() { return prefixToOrdRange[dim]; }
public override string ToString() => string.Format(CultureInfo.CurrentCulture, "{0}('{1}')", nameof(LexerNoViableAltException), Utils.EscapeWhitespace(GetInputStream().GetText(new Interval(startIndex, startIndex)), false));
public T PeekFirst() => peekFirstImpl;
public virtual CreateWorkspacesResponse CreateWorkspaces(CreateWorkspacesRequest request) { var options = new InvokeOptions(); options.RequestMarshaller = CreateWorkspacesRequestMarshaller.Instance; options.ResponseUnmarshaller = CreateWorkspacesResponseUnmarshaller.Instance; return Invoke<CreateWorkspacesResponse>(request, options); }
public virtual NumberFormatIndexRecord() { return copy; }
public DescribeRepositoriesResponse DescribeRepositories(DescribeRepositoriesRequest request) => ExecuteDescribeRepositories(BeforeClientExecution(request));
public SparseIntArray(int initialCapacity) { initialCapacity = ArrayUtils.IdealIntArraySize(initialCapacity); mKeys = new int[initialCapacity]; mValues = new int[initialCapacity]; mSize = 0; }
public HyphenatedWordsFilter() { return new HyphenatedWordsFilter(input); }
public CreateDistributionWithTagsResult() { request = BeforeClientExecution(request); return ExecuteCreateDistributionWithTags(request); }
public RandomAccessFile(string fileName, string mode) : this(new FileInfo(fileName)) { }
public virtual DeleteWorkspaceImageResponse DeleteWorkspaceImage(DeleteWorkspaceImageRequest request) { request = beforeClientExecution(request); return executeDeleteWorkspaceImage(request); }
public static string ToHexString(long value) { var sb = new StringBuilder(16); WriteHex(sb, value, 16, string.Empty); return sb.ToString(); }
public UpdateDistributionResult UpdateDistribution(UpdateDistributionRequest request) { request = beforeClientExecution(request); return executeUpdateDistribution; }
public HSSFColor(int index) { return index == HSSFColorPredefined.Automatic.Index ? HSSFColorPredefined.Automatic.Color : _palette.GetColor(index); }
public override ValueEval Evaluate(ValueEval[] operands, int srcRow, int srcCol) { throw new NotImplementedFunctionException(_functionName); }
public void Write(BinaryWriter out) { out.Write((short)field_1_number_crn_records); out.Write((short)field_2_sheet_table_index); }
public virtual DescribeDBEngineVersionsResponse DescribeDBEngineVersions() => DescribeDBEngineVersions(new DescribeDBEngineVersionsRequest());
public FormatRun(char character, int fontIndex) { this._character = character; this._fontIndex = fontIndex; }
public static ushort[] ToUtf16(char[] chars, int offset, int length)  {     ushort[] result = new ushort[length * 2];      int end = offset + length;      int resultIndex = 0;      for (int i = offset; i < end; ++i)      {          char ch = chars[i];          result[resultIndex++] = (ushort)(ch >> 8);          result[resultIndex++] = (ushort)ch;      }      return result;  }
public UploadArchiveResult(UploadArchiveRequest request) { request = beforeClientExecution; return executeUploadArchive(request); }
public IList GetHiddenTokensToLeft(int tokenIndex) { return GetHiddenTokensToLeft(tokenIndex, -1); }
public override bool Equals(object obj) { if (this == obj) return true; if (!base.Equals(obj)) return false; if (GetType() != obj.GetType()) return false; AutomatonQuery other = (AutomatonQuery)obj; if (!compiled.Equals(other.compiled)) return false; if (term == null) { if (other.term != null) return false; } else if (!term.Equals(other.term)) return false; return true; }
public SpanQuery(){SpanQuery[] spanQueries = new SpanQuery[size()];int i = 0;foreach(var sq in weightBySpanQuery.Keys){float boost = weightBySpanQuery[sq];if(boost != 1f)sq = new SpanBoostQuery(sq, boost);spanQueries[i++] = sq;}return spanQueries.Length == 1 ? spanQueries[0] : new SpanOrQuery(spanQueries);}
public StashCreateCommand() { return new StashCreateCommand(); }
public FieldInfo Get() { return byName.GetValue(fieldName); }
DescribeEventSourceResult(DescribeEventSourceRequest request) { request = beforeClientExecution; return ExecuteDescribeEventSource(request); }
public virtual GetDocumentAnalysisResponse GetDocumentAnalysis(GetDocumentAnalysisRequest request) => ExecuteGetDocumentAnalysis(BeforeClientExecution(request));
public CancelUpdateStackResult() { request = BeforeClientExecution(request); return ExecuteCancelUpdateStack(request); }
public virtual ModifyLoadBalancerAttributesResponse ModifyLoadBalancerAttributes(ModifyLoadBalancerAttributesRequest request) => ExecuteModifyLoadBalancerAttributes(BeforeClientExecution(request));
public SetInstanceProtectionResult() { request = BeforeClientExecution(request); return ExecuteSetInstanceProtection(request); }
public ModifyDBProxyResult ModifyDBProxy(ModifyDBProxyRequest request) { request = beforeClientExecution(request); return executeModifyDBProxy; }
void Add([].output, int offset, int len, int endOffset, int posLength) { if (count == outputs.Length) { outputs = ArrayUtil.Grow(outputs, count + 1); } if (count == endOffsets.Length) { int[] next = new int[ArrayUtil.Oversize(1 + count, sizeof(int))]; Array.Copy(endOffsets, 0, next, 0, count); endOffsets = next; } if (count == posLengths.Length) { int[] next = new int[ArrayUtil.Oversize(1 + count, sizeof(int))]; Array.Copy(posLengths, 0, next, 0, count); posLengths = next; } if (outputs[count] == null) { outputs[count] = new CharsRefBuilder(); } ((CharsRefBuilder)outputs[count]).CopyChars(output, offset, len); endOffsets[count] = endOffset; posLengths[count] = posLength; count++; }
public FetchLibrariesRequest() : base("CloudPhoto", "2017-07-11", "FetchLibraries", "cloudphoto") { }
public virtual bool Exists() { return fs.Exists; }
public FilterOutputStream(Stream out) { this.out = out; }
ScaleClusterRequest("/clusters/{ClusterId}"); SetMethod(MethodType.PUT);
public static DataValidationConstraint Create(DataValidationOperator operatorType, string formula2) => DVConstraint.CreateTimeConstraint(operatorType, formula1, formula2);
public virtual ListObjectParentPathsResponse ListObjectParentPaths(ListObjectParentPathsRequest request) { var options = new InvokeOptions(); options.RequestMarshaller = ListObjectParentPathsRequestMarshaller.Instance; options.ResponseUnmarshaller = ListObjectParentPathsResponseUnmarshaller.Instance; return Invoke<ListObjectParentPathsResponse>(request, options); }
public virtual DescribeCacheSubnetGroupsResponse DescribeCacheSubnetGroups(DescribeCacheSubnetGroupsRequest request) { var options = new InvokeOptions(); options.RequestMarshaller = DescribeCacheSubnetGroupsRequestMarshaller.Instance; options.ResponseUnmarshaller = DescribeCacheSubnetGroupsResponseUnmarshaller.Instance; return Invoke<DescribeCacheSubnetGroupsResponse>(request, options); }
field_5_options = sharedFormula.SetShortBoolean(field_5_options, flag);
bool() { }
public ErrorNode(Token badToken) { ErrorNodeImpl t = new ErrorNodeImpl(badToken); AddAnyChild(t); t.SetParent(this); return t; }
public LatvianStemFilterFactory(IDictionary<string, string> args) : base(args) { if (args.Count > 0) { throw new System.ArgumentException("Unknown parameters: " + args); } }
public virtual EventSubscriptionResponse EventSubscription(EventSubscriptionRequest request) => ExecuteRemoveSourceIdentifierFromSubscription(BeforeClientExecution(request));
public static TokenFilterFactory TokenFilterFactory(string name, Map<string, string> args) { return loader.newInstance(name, args); }
public AddAlbumPhotosRequest() : base("CloudPhoto", "2017-07-11", "AddAlbumPhotos", "cloudphoto") { }
public virtual GetThreatIntelSetResponse GetThreatIntelSet(GetThreatIntelSetRequest request) { var options = new InvokeOptions(); options.RequestMarshaller = GetThreatIntelSetRequestMarshaller.Instance; options.ResponseUnmarshaller = GetThreatIntelSetResponseUnmarshaller.Instance; return Invoke<GetThreatIntelSetResponse>(request, options); }
public RevFilter() { return new Binary(a.Clone() as int[], b.Clone() as int[]); }
bool Equals(object o) { return false; }
public bool HasArray() { return ProtectedHasArray(); }
public UpdateContributorInsightsResult UpdateContributorInsights(UpdateContributorInsightsRequest request) { request = BeforeClientExecution(request); return ExecuteUpdateContributorInsights(request); }
void () { records.Remove(fileShare); records.Remove(writeProtect); writeProtect = null; }
public SolrSynonymParser(bool expand, Analyzer analyzer) : base(analyzer) { this.expand = expand; }
public RequestSpotInstancesResult() { request = BeforeClientExecution(request); return ExecuteRequestSpotInstances(request); }
public object[] GetObjectData() { return findObjectRecord.GetObjectData(); }
public virtual GetContactAttributesResponse GetContactAttributes(GetContactAttributesRequest request) { var options = new InvokeOptions(); options.RequestMarshaller = GetContactAttributesRequestMarshaller.Instance; options.ResponseUnmarshaller = GetContactAttributesResponseUnmarshaller.Instance; return Invoke<GetContactAttributesResponse>(request, options); }
public override string ToString() { return GetKey() + ": " + GetValue(); }
public virtual ListTextTranslationJobsResponse ListTextTranslationJobs(ListTextTranslationJobsRequest request) { request = beforeClientExecution(request); return executeListTextTranslationJobs(request); }
public virtual GetContactMethodsResult GetContactMethods() { var request = beforeClientExecution(request); return executeGetContactMethods(request); }
public static int GetFunctionIndex(string name) => GetInstance().GetFunctionByNameInternal(name)?.GetIndex() ?? GetInstanceCetab().GetFunctionByNameInternal(name)?.GetIndex() ?? -1;
public virtual DescribeAnomalyDetectorsResponse DescribeAnomalyDetectors(DescribeAnomalyDetectorsRequest request) { var options = new InvokeOptions(); options.RequestMarshaller = DescribeAnomalyDetectorsRequestMarshaller.Instance; options.ResponseUnmarshaller = DescribeAnomalyDetectorsResponseUnmarshaller.Instance; return Invoke<DescribeAnomalyDetectorsResponse>(request, options); }
public static string InsertId(string message, ObjectId changeId) { return insertId; }
public int GetObjectSize(AnyObjectId objectId, int typeHint) throws MissingObjectException, IncorrectObjectTypeException, IOException { sz = db.GetObjectSize(objectId); if (sz < 0) { if (typeHint == OBJ_ANY) throw new MissingObjectException(objectId.Copy(), JGitText.Get().unknownObjectType2); throw new MissingObjectException(objectId.Copy(), typeHint); } return sz; }
public ImportInstallationMediaResult(){ request = BeforeClientExecution(request); return ExecuteImportInstallationMedia(request); }
public PutLifecycleEventHookExecutionStatusResult() { request = beforeClientExecution(request); return executePutLifecycleEventHookExecutionStatus(request); }
public NumberPtg(){this.value = in.ReadDouble();}
public GetFieldLevelEncryptionConfigResult GetFieldLevelEncryptionConfig(GetFieldLevelEncryptionConfigRequest request) { request = BeforeClientExecution; return ExecuteGetFieldLevelEncryptionConfig(request); }
public virtual DescribeDetectorResponse DescribeDetector(DescribeDetectorRequest request) { request = BeforeClientExecution(request); return ExecuteDescribeDetector(request); }
public ReportInstanceStatusResult ReportInstanceStatus(ReportInstanceStatusRequest request) { request = BeforeClientExecution(request); return ExecuteReportInstanceStatus(request); }
public virtual DeleteAlarmResponse DeleteAlarm(DeleteAlarmRequest request) { var options = new InvokeOptions(); options.RequestMarshaller = DeleteAlarmRequestMarshaller.Instance; options.ResponseUnmarshaller = DeleteAlarmResponseUnmarshaller.Instance; return Invoke<DeleteAlarmResponse>(request, options); }
public TokenStream PortugueseStemFilter() { return new PortugueseStemFilter(input); }
public FtCblsSubRecord() { reserved = new(); }
public virtual bool Remove(Object object) { lock (mutex) { return c.Remove(object); } }
public GetDedicatedIpResult GetDedicatedIpResult() { request = BeforeClientExecution(request); return ExecuteGetDedicatedIp(request); }
string() { return; }
public virtual ListStreamProcessorsResponse ListStreamProcessors(ListStreamProcessorsRequest request) { var options = new InvokeOptions(); options.RequestMarshaller = ListStreamProcessorsRequestMarshaller.Instance; options.ResponseUnmarshaller = ListStreamProcessorsResponseUnmarshaller.Instance; return Invoke<ListStreamProcessorsResponse>(request, options); }
public DeleteLoadBalancerPolicyRequest(string loadBalancerName, string policyName) { _loadBalancerName = loadBalancerName; _policyName = policyName; }
public WindowProtectRecord() { }
public UnbufferedCharStream(int bufferSize) { data = new char[bufferSize]; }
public GetOperationsResult GetOperations(GetOperationsRequest request) { request = BeforeClientExecution(request); return ExecuteGetOperations(request); }
void Encode([In] byte[] b, int o) { NB.EncodeInt32(b, o, w1); NB.EncodeInt32(b, o + 4, w2); NB.EncodeInt32(b, o + 8, w3); NB.EncodeInt32(b, o + 12, w4); NB.EncodeInt32(b, o + 16, w5); }
public WindowOneRecord(RecordInputStream in) { field_1_h_hold = in.ReadShort(); field_2_v_hold = in.ReadShort(); field_3_width = in.ReadShort(); field_4_height = in.ReadShort(); field_5_options = in.ReadShort(); field_6_active_sheet = in.ReadShort(); field_7_first_visible_tab = in.ReadShort(); field_8_num_selected_tabs = in.ReadShort(); field_9_tab_width_ratio = in.ReadShort(); }
public virtual StopWorkspacesResponse StopWorkspaces(StopWorkspacesRequest request) => ExecuteStopWorkspaces(BeforeClientExecution(request));
public void Dispose() { if (isOpen) { isOpen = false; try { Dump(); } finally { try { channel.Truncate(fileLength); } finally { try { channel.Close(); } finally { fos.Close(); } } } }
public virtual DescribeMatchmakingRuleSetsResponse DescribeMatchmakingRuleSets(DescribeMatchmakingRuleSetsRequest request) { var options = new InvokeOptions(); options.RequestMarshaller = DescribeMatchmakingRuleSetsRequestMarshaller.Instance; options.ResponseUnmarshaller = DescribeMatchmakingRuleSetsResponseUnmarshaller.Instance; return Invoke<DescribeMatchmakingRuleSetsResponse>(request, options); }
string (string wordId, string[] surface, int off, int len) { }
public virtual string ToString() { }
public static double CalculateVariance(double[] v) { double r = double.NaN; if (v != null && v.Length >= 1) { double m, s = 0; int n = v.Length; for (int i = 0; i < n; i++) s += v[i]; m = s / n; s = 0; for (int i = 0; i < n; i++) s += Math.Pow(v[i] - m, 2); r = (n == 1) ? 0 : s; } return r; }
public DescribeResizeResult() { request = BeforeClientExecution(request); return ExecuteDescribeResize(request); }
public bool () { return passedThroughNonGreedyDecision; }
public virtual object End() { return end; }
void Walk(CellHandler handler) { firstRow = range.GetFirstRow(); lastRow = range.GetLastRow(); firstColumn = range.GetFirstColumn(); lastColumn = range.GetLastColumn(); width = lastColumn - firstColumn + 1; SimpleCellWalkContext ctx = new SimpleCellWalkContext(); Row currentRow = null; Cell currentCell = null; for (ctx.rowNumber = firstRow; ctx.rowNumber <= lastRow; ++ctx.rowNumber) { currentRow = sheet.GetRow(ctx.rowNumber); if (currentRow == null) { continue; } for (ctx.colNumber = firstColumn; ctx.colNumber <= lastColumn; ++ctx.colNumber) { currentCell = currentRow.GetCell(ctx.colNumber); if (currentCell == null) { continue; } if (IsEmpty(currentCell) && !traverseEmptyCells) { continue; } rowSize = ArithmeticUtils.MulAndCheck((long)ArithmeticUtils.SubAndCheck(ctx.rowNumber, firstRow), (long)width); ctx.ordinalNumber = ArithmeticUtils.AddAndCheck(rowSize, (ctx.colNumber - firstColumn + 1)); handler.OnCell(currentCell, ctx); } } }
{ }
public int CompareTo(ScoreTerm other) { if (this.boost == other.boost) return this.bytes.CompareTo(other.bytes); else return this.boost.CompareTo(other.boost); }
public int ProcessArray(char[] s, int len) { for (int i = 0; i < len; i++) { switch (s[i]) { case FARSI_YEH: case YEH_BARREE: s[i] = YEH; break; case KEHEH: s[i] = KAF; break; case HEH_YEH: case HEH_GOAL: s[i] = HEH; break; case HAMZA_ABOVE: len = Delete(s, i, len); i--; break; } } return len; }
void Write(LittleEndianOutput @out) { @out.WriteShort(); }
public DiagnosticErrorListener(bool exactOnly = false) { this.exactOnly = exactOnly; }
public KeySchemaElement(string attributeName, KeyType keyType) { this.AttributeName = attributeName; this.KeyType = keyType.ToString(); }
public virtual GetAssignmentResult GetAssignmentResult() { request = BeforeClientExecution(request); return ExecuteGetAssignment(request); }
public bool Contains(AnyObjectId id) { return FindOffset(id) != null; }
public GroupingSearch WithAllGroups(bool allGroups) { AllGroups = allGroups; return this; }
public void SetMultiValued(string dimName, bool v) { DimConfig ft = fieldTypes.Get(dimName); if (ft == null) { ft = new DimConfig(); fieldTypes[dimName] = ft; } ft.multiValued = v; }
public int Size() { int size = 0; foreach (char c in cells.Keys) { Cell e = at(c); if (e.cmd >= 0) size++; } return size; }
public DeleteVoiceConnectorResult(DeleteVoiceConnectorRequest request) { request = beforeClientExecution; return executeDeleteVoiceConnector(request); }
public virtual DeleteLifecyclePolicyResponse DeleteLifecyclePolicy(DeleteLifecyclePolicyRequest request) { var options = new InvokeOptions(); options.RequestMarshaller = DeleteLifecyclePolicyRequestMarshaller.Instance; options.ResponseUnmarshaller = DeleteLifecyclePolicyResponseUnmarshaller.Instance; return Invoke<DeleteLifecyclePolicyResponse>(request, options); }
