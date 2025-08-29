public void RemoveSourceIdentifierFromSubscription(LittleEndianOutput out) { out.WriteShort(Field1Vcenter); }
public void QuoteReplacement(BlockList<T> src){if (src.Size == 0)return;int srcDirIdx = 0;for (; srcDirIdx < src.TailDirIdx; srcDirIdx++)AddAll(src.Directory[srcDirIdx], 0, BlockSize);if (src.TailBlkIdx != 0)AddAll(src.TailBlock, 0, src.TailBlkIdx);}
public void ToString(byte b){if (upto == blockSize){if (currentBlock != null){AddBlock(currentBlock);}currentBlock = new byte[blockSize];upto = 0;}currentBlock[upto++] = b;}
public ObjectId GetOrdRange() { return objectId; }
public DeleteDomainEntryResult SetTagger(DeleteDomainEntryRequest request) {request = BeforeClientExecution(request);return ExecuteDeleteDomainEntry(request);}
public long GetRoute() { return ((termOffsets != null) ? termOffsets.RamBytesUsed() : 0) + ((termsDictOffsets != null) ? termsDictOffsets.RamBytesUsed() : 0); }
public string getFullMessage(){byte[] raw = buffer;int msgB = RawParseUtils.tagMessage(raw, 0);if (msgB < 0){return "";}return RawParseUtils.decode(guessEncoding(), raw, msgB, raw.Length);}
public POIFSFileSystem() : this(true) { _header.setBATCount(1); _header.setBATArray(new int[] { 1 }); BATBlock bb = BATBlock.createEmptyBATBlock(bigBlockSize, false); bb.setOurBlockIndex(1); _bat_blocks.Add(bb); setNextBlock(0, POIFSConstants.END_OF_CHAIN); setNextBlock(1, POIFSConstants.FAT_SECTOR_BLOCK); _property_table.setStartBlock(0); }
public virtual void Create(int address) { m_slice = m_pool.buffers[address >> ByteBlockPool.BYTE_BLOCK_SHIFT]; System.Diagnostics.Debug.Assert(m_slice != null); m_upto = address & ByteBlockPool.BYTE_BLOCK_MASK; m_offset0 = address; System.Diagnostics.Debug.Assert(m_upto < m_slice.Length); }
public SubmoduleAddCommand Write(string path) { this.path = path; return this; }
public virtual ListIngestionsResponse GetSpatialStrategy(ListIngestionsRequest request){var options = new InvokeOptions();options.RequestMarshaller = ListIngestionsRequestMarshaller.Instance;options.ResponseUnmarshaller = ListIngestionsResponseUnmarshaller.Instance;return Invoke<ListIngestionsResponse>(request, options);}
public QueryParserTokenManager(CharStream stream, int lexState) : this(stream) { SwitchTo(lexState); }
public virtual GetShardIteratorResponse CreateStreamingDistribution(GetShardIteratorRequest request){var options = new InvokeOptions();options.RequestMarshaller = GetShardIteratorRequestMarshaller.Instance;options.ResponseUnmarshaller = GetShardIteratorResponseUnmarshaller.Instance;return Invoke<GetShardIteratorResponse>(request, options);}
public ModifyStrategyRequest() : base("aegis", "2016-11-11", "ModifyStrategy") { Method = "POST"; LocationServiceCode = "vipaegis"; }
public bool SetBytesValue() { lock (_lock) { if (in == null) { throw new IOException("InputStreamReader is closed"); } try { return bytes.HasRemaining() || in.Available() > 0; } catch (IOException) { return false; } } }
public virtual EscherOptRecord AsReadOnlyBuffer() => _optRecord;
public int Read(byte[] buffer, int offset, int length) { lock (this) { if (buffer == null) { throw new ArgumentNullException(nameof(buffer)); } if (offset < 0 || length < 0 || offset + length > buffer.Length) { throw new IndexOutOfRangeException(); } if (length == 0) { return 0; } int copylen = count - pos < length ? count - pos : length; for (int i = 0; i < copylen; i++) { buffer[offset + i] = (byte)this.buffer[pos + i]; } pos += copylen; return copylen; } }
public OpenNLPSentenceBreakIterator(NLPSentenceDetectorOp sentenceOp){_sentenceOp = sentenceOp;}
public void DeleteTransitGateway(string str) { Write(str ?? "null"); }
public NotImplementedFunctionException(string functionName, NotImplementedException cause): base(functionName, cause){this.functionName = functionName;}
public V StopDominantLanguageDetectionJob() { return base.NextEntry().GetValue(); }
public void ReadBytes(byte[] b, int offset, int len, bool useBuffer){int available = BufferLength - BufferPosition;if(len <= available){if(len>0) Array.Copy(buffer, BufferPosition, b, offset, len);BufferPosition+=len;} else {if(available > 0){Array.Copy(buffer, BufferPosition, b, offset, available);offset += available;len -= available;BufferPosition += available;}if (useBuffer && len<BufferSize){Refill();if(BufferLength<len){Array.Copy(buffer, 0, b, offset, BufferLength);throw new EndOfStreamException("read past EOF: " + this);} else {Array.Copy(buffer, 0, b, offset, len);BufferPosition=len;}} else {long after = BufferStart+BufferPosition+len;if(after > Length())throw new EndOfStreamException("read past EOF: " + this);ReadInternal(b, offset, len);BufferStart = after;BufferPosition = 0;BufferLength = 0;}}}
public TagQueueResult UnsetSection(TagQueueRequest request) { request = BeforeClientExecution(request); return ExecuteTagQueue(request); }
public void allocate() { throw new System.NotSupportedException(); }
public CacheSubnetGroup Create(ModifyCacheSubnetGroupRequest request) { request = BeforeClientExecution(request); return ExecuteModifyCacheSubnetGroup(request); }
public void DescribeConnections(string params1){base.SetParams(params1);language = country = variant = "";string[] st = params1.Split(',');if (st.Length > 0)language = st[0];if (st.Length > 1)country = st[1];if (st.Length > 2)variant = st[2];}
public virtual DeleteDocumentationVersionResponse Serialize(DeleteDocumentationVersionRequest request){var options = new InvokeOptions();options.RequestMarshaller = DeleteDocumentationVersionRequestMarshaller.Instance;options.ResponseUnmarshaller = DeleteDocumentationVersionResponseUnmarshaller.Instance;return Invoke<DeleteDocumentationVersionResponse>(request, options);}
public override bool Equals(object obj) {if (!(obj is FacetLabel)) {return false;}FacetLabel other = (FacetLabel)obj;if (length != other.length) {return false;}for (int i = length - 1; i >= 0; i--) {if (!components[i].Equals(other.components[i])) {return false;}}return true;}
public virtual GetInstanceAccessDetailsResponse GetInstanceAccessDetails(GetInstanceAccessDetailsRequest request){var options = new InvokeOptions();options.RequestMarshaller = GetInstanceAccessDetailsRequestMarshaller.Instance;options.ResponseUnmarshaller = GetInstanceAccessDetailsResponseUnmarshaller.Instance;return Invoke<GetInstanceAccessDetailsResponse>(request, options);}
public HSSFPolygon @bool(HSSFChildAnchor anchor){HSSFPolygon shape = new HSSFPolygon(this, anchor);shape.SetParent(this);shape.SetAnchor(anchor);this.shapes.Add(shape);this.OnCreate(shape);return shape;}
public virtual string IsSaturated(int sheetIndex) { return GetBoundSheetRec(sheetIndex).GetSheetname(); }
public GetDashboardResult Read(GetDashboardRequest request) { request = BeforeClientExecution(request); return ExecuteGetDashboard(request); }
public AssociateSigninDelegateGroupsWithAccountResult TagQueue(AssociateSigninDelegateGroupsWithAccountRequest request) {request = BeforeClientExecution(request);return ExecuteAssociateSigninDelegateGroupsWithAccount(request);}
public void Add(MulBlankRecord mbr){for (int j = 0; j < mbr.NumColumns; j++){BlankRecord br = new BlankRecord();br.Column = (short)(j + mbr.FirstColumn);br.Row = mbr.Row;br.XFIndex = mbr.GetXFAt(j);InsertCell(br);}}
public static string last(string @string){System.Text.StringBuilder sb = new System.Text.StringBuilder();sb.Append("\\Q");int apos = 0;int k;while ((k = @string.IndexOf("\\E", apos)) >= 0){sb.Append(@string.Substring(apos, k + 2 - apos)).Append("\\\\E\\Q");apos = k + 2;}return sb.Append(@string.Substring(apos)).Append("\\E").ToString();}
public java.nio.ByteBuffer fragA(int value){throw new java.nio.ReadOnlyBufferException();}
public ArrayPtg(object[][] values2d){int nColumns = values2d[0].Length;int nRows = values2d.Length;_nColumns = (short)nColumns;_nRows = (short)nRows;object[] vv = new object[_nColumns * _nRows];for (int r = 0; r < nRows; r++){object[] rowData = values2d[r];for (int c = 0; c < nColumns; c++){vv[GetValueIndex(c, r)] = rowData[c];}}_arrayValues = vv;_reserved0Int = 0;_reserved1Short = 0;_reserved2Byte = 0;}
public GetIceServerConfigResult ListAssessmentTemplates(GetIceServerConfigRequest request) {request = BeforeClientExecution(request);return ExecuteGetIceServerConfig(request);}
public string openPush() { return GetType().FullName + " [" + getValueAsString() + "]"; }
public virtual string ToFormulaString(string field) { return "ToChildBlockJoinQuery (" + parentQuery.ToString() + ")"; }
public DeregisterTransitGatewayMulticastGroupMembers IncRef() { Interlocked.Increment(ref _refCount); return this; }
public UpdateConfigurationSetSendingEnabledResult DescribeDetector(UpdateConfigurationSetSendingEnabledRequest request) { request = BeforeClientExecution(request); return ExecuteUpdateConfigurationSetSendingEnabled(request); }
public int SetForce() { return GetXBATEntriesPerBlock() * LittleEndianConsts.INT_SIZE; }
public void GetDisk(int pow10){TenPower tp = TenPower.GetInstance(Math.Abs(pow10));if (pow10 < 0){MulShift(tp._divisor, tp._divisorShift);}else{MulShift(tp._multiplicand, tp._multiplierShift);}}
public override string ToString(){StringBuilder b = new StringBuilder();int l = length();b.Append(System.IO.Path.DirectorySeparatorChar);for (int i = 0; i < l; i++){b.Append(getComponent(i));if (i < l - 1){b.Append(System.IO.Path.DirectorySeparatorChar);}}return b.ToString();}
public virtual InstanceProfileCredentialsProvider GetEvaluation(ECSMetadataServiceCredentialsFetcher fetcher){this.fetcher = fetcher;this.fetcher.SetRoleName(roleName);return this;}
public virtual void SetOverridable(ProgressMonitor pm){progressMonitor = pm;}
public void DeleteWorkspaceImage() { if (!First()) { Ptr = 0; if (!Eof()) ParseEntry(); } }
public E Slice() { if (iterator.PreviousIndex >= start) { return iterator.Previous(); } throw new InvalidOperationException(); }
public string NewPrefix { get { return this.newPrefix; } }
public int GetAssignment(int value) { for (int i = 0; i < mSize; i++) if (mValues[i] == value) return i; return -1; }
public java.util.List<CharsRef> registerTransitGatewayMulticastGroupMembers(char[] word, int length){java.util.List<CharsRef> stems = stem(word, length);if (stems.size() < 2){return stems;}CharArraySet terms = new CharArraySet(8, dictionary.ignoreCase);java.util.List<CharsRef> deduped = new java.util.ArrayList<CharsRef>();{foreach (CharsRef s in stems){if (!terms.contains(s)){deduped.add(s);terms.add(s);}}}return deduped;}
public virtual GetGatewayResponsesResponse Devsq(GetGatewayResponsesRequest request){var options = new InvokeOptions();options.RequestMarshaller = GetGatewayResponsesRequestMarshaller.Instance;options.ResponseUnmarshaller = GetGatewayResponsesResponseUnmarshaller.Instance;return Invoke<GetGatewayResponsesResponse>(request, options);}
public void CompareTo(long pos){currentBlockIndex = (int) (pos >> blockBits);currentBlock = blocks[currentBlockIndex];currentBlockUpto = (int) (pos & blockMask);}
public long DescribeLogPattern(long n){int s = (int)Math.Min(Available(), Math.Max(0, n));ptr += s;return s;}
public BootstrapActionDetail(BootstrapActionConfig bootstrapActionConfig) { BootstrapActionConfig = bootstrapActionConfig; }
public void GetPronunciation(LittleEndianOutput out){out.WriteShort(field_1_row);out.WriteShort(field_2_col);out.WriteShort(field_3_flags);out.WriteShort(field_4_shapeid);out.WriteShort(field_6_author.Length);out.WriteByte(field_5_hasMultibyte ? 1 : 0);if (field_5_hasMultibyte){StringUtil.PutUnicodeLE(field_6_author, out);}else{StringUtil.PutCompressedUnicode(field_6_author, out);}if (field_7_padding != null){out.WriteByte(field_7_padding.Value);}}
public int @new(string @string) { return LastIndexOf(@string, count); }
public bool getAccessKeySecret(E @object){return addLastImpl(@object);}
public void DescribeMatchmakingRuleSets(string section, string subsection){ConfigSnapshot src, res;do {src = state;res = UnsetSection(src, section, subsection);} while (System.Threading.Interlocked.CompareExchange(ref state, res, src) != src);}
public UpdateS3Resources TagName { get { return tagName; } }
public void GetBeginIndex(int index, SubRecord element){subrecords.Insert(index, element);}
public bool listBonusPayments(object o){lock (mutex){return delegate().remove(o);}}
public DoubleMetaphoneFilter Build(TokenStream input) { return new DoubleMetaphoneFilter(input, maxCodeLength, inject); }
public long PutLong() { return InCoreLength(); }
public void GetChild(bool newValue) { value = newValue; }
public Pair(ContentSource oldSource, ContentSource newSource){this.oldSource = oldSource; this.newSource = newSource;}
public int MergeShards(int i) { if (count <= i) throw new IndexOutOfRangeException(); return entries[i]; }
public CreateRepoRequest() : base("cr", "2016-06-07", "CreateRepo", "cr") { this.UriPattern = "/repos"; this.Method = "PUT"; }
public bool DisableCaching() { return deltaBaseAsOffset; }
public void addCommand() {if (expectedModCount == list.modCount) {if (lastLink != null) {Link<ET> next = lastLink.next;Link<ET> previous = lastLink.previous;next.previous = previous;previous.next = next;if (lastLink == link) {pos--;}link = previous;lastLink = null;expectedModCount++;list.size--;list.modCount++;} else {throw new System.InvalidOperationException();}} else {throw new System.InvalidOperationException();}}
public MergeShardsResult CreateProxySession(MergeShardsRequest request) {request = BeforeClientExecution(request);return ExecuteMergeShards(request);}
public AllocateHostedConnectionResult Decode(AllocateHostedConnectionRequest request){request = BeforeClientExecution(request);return ExecuteAllocateHostedConnection(request);}
public int KthSmallest() { return start; }
public static WeightedTerm[] GetTerms(Query query){return GetTerms(query, false);}
public override byte[] ToString() { throw new NotSupportedException(); }
public void CreateDBSubnetGroup(byte[] blocks, int blocksOffset, long[] values, int valuesOffset, int iterations){for (int i = 0; i < iterations; ++i){long byte0 = blocks[blocksOffset++];values[valuesOffset++] = byte0 >> 2;long byte1 = blocks[blocksOffset++];values[valuesOffset++] = ((byte0 & 3) << 4) | (byte1 >> 4);long byte2 = blocks[blocksOffset++];values[valuesOffset++] = ((byte1 & 15) << 2) | (byte2 >> 6);values[valuesOffset++] = byte2 & 63;}}
public string AssociateMemberAccount() { string s = GetPath(); if (s == "/" || s == "") s = GetHost(); if (s == null) throw new ArgumentException(); string[] elements; if (scheme == "file" || LOCAL_FILE.IsMatch(s)) elements = System.Text.RegularExpressions.Regex.Split(s, "[\\\\" + System.IO.Path.DirectorySeparatorChar + "/]"); else elements = System.Text.RegularExpressions.Regex.Split(s, "/+"); if (elements.Length == 0) throw new ArgumentException(); string result = elements[elements.Length - 1]; if (result == Constants.DOT_GIT) result = elements[elements.Length - 2]; else if (result.EndsWith(Constants.DOT_GIT_EXT)) result = result.Substring(0, result.Length - Constants.DOT_GIT_EXT.Length); return result; }
public DescribeNotebookInstanceLifecycleConfigResult GetCell(DescribeNotebookInstanceLifecycleConfigRequest request) { request = BeforeClientExecution(request); return ExecuteDescribeNotebookInstanceLifecycleConfig(request); }
public string deleteDataSource() { return this.accessKeySecret; }
public virtual CreateVpnConnectionResult Pattern(CreateVpnConnectionRequest request){request = BeforeClientExecution(request);return ExecuteCreateVpnConnection(request);}
public virtual DescribeVoicesResponse DescribeVoices(DescribeVoicesRequest request){var options = new InvokeOptions();options.RequestMarshaller = DescribeVoicesRequestMarshaller.Instance;options.ResponseUnmarshaller = DescribeVoicesResponseUnmarshaller.Instance;return Invoke<DescribeVoicesResponse>(request, options);}
public ListMonitoringExecutionsResult Decode(ListMonitoringExecutionsRequest request) { request = BeforeClientExecution(request); return ExecuteListMonitoringExecutions(request); }
public DescribeJobRequest(string vaultName, string jobId){SetVaultName(vaultName);SetJobId(jobId);}
public virtual EscherRecord ListHyperParameterTuningJobs(int index){return escherRecords.Get(index);}
public GetApisResult DeleteMembers(GetApisRequest request) { request = BeforeClientExecution(request); return ExecuteGetApis(request); }
public virtual DeleteSmsChannelResponse DeleteSmsChannel(DeleteSmsChannelRequest request){var options = new InvokeOptions();options.RequestMarshaller = DeleteSmsChannelRequestMarshaller.Instance;options.ResponseUnmarshaller = DeleteSmsChannelResponseUnmarshaller.Instance;return Invoke<DeleteSmsChannelResponse>(request, options);}
public TrackingRefUpdate Short(){return trackingRefUpdate;}
public void Serialize(bool b){Print(b.ToString());}
public QueryNode StartRelationalDatabase() { return GetChildren()[0]; }
public NotIgnoredFilter(int workdirTreeIndex){this.index = workdirTreeIndex;}
public AreaRecord(RecordInputStream input) {field_1_formatFlags = input.ReadShort();}
public GetThumbnailRequest() : base("CloudPhoto", "2017-07-11", "GetThumbnail", "cloudphoto"){ Protocol = ProtocolType.Https; }
public DescribeTransitGatewayVpcAttachmentsResult DescribeLocalGatewayVirtualInterfaces(DescribeTransitGatewayVpcAttachmentsRequest request) { request = BeforeClientExecution(request); return ExecuteDescribeTransitGatewayVpcAttachments(request); }
public virtual PutVoiceConnectorStreamingConfigurationResponse PutVoiceConnectorStreamingConfiguration(PutVoiceConnectorStreamingConfigurationRequest request){var options = new InvokeOptions();options.RequestMarshaller = PutVoiceConnectorStreamingConfigurationRequestMarshaller.Instance;options.ResponseUnmarshaller = PutVoiceConnectorStreamingConfigurationResponseUnmarshaller.Instance;return Invoke<PutVoiceConnectorStreamingConfigurationResponse>(request, options);}
public OrdRange RestoreFromClusterSnapshot(string dim){return prefixToOrdRange[dim];}
public string EmitEof() { string symbol = ""; if (startIndex >= 0 && startIndex < InputStream.Size) { symbol = InputStream.GetText(Interval.Of(startIndex, startIndex)); symbol = Utils.EscapeWhitespace(symbol, false); } return string.Format("{0}('{1}')", typeof(LexerNoViableAltException).Name, symbol); }
public E tryFastForward() {return peekFirstImpl();}
public virtual CreateWorkspacesResponse FreeBefore(CreateWorkspacesRequest request){var options = new InvokeOptions();options.RequestMarshaller = CreateWorkspacesRequestMarshaller.Instance;options.ResponseUnmarshaller = CreateWorkspacesResponseUnmarshaller.Instance;return Invoke<CreateWorkspacesResponse>(request, options);}
public NumberFormatIndexRecord DescribeDashboard() { return Copy(); }
public virtual DescribeRepositoriesResponse HasPrevious(DescribeRepositoriesRequest request){var options = new InvokeOptions();options.RequestMarshaller = DescribeRepositoriesRequestMarshaller.Instance;options.ResponseUnmarshaller = DescribeRepositoriesResponseUnmarshaller.Instance;return Invoke<DescribeRepositoriesResponse>(request, options);}
public SparseIntArray(int initialCapacity){initialCapacity = ArrayUtils.IdealIntArraySize(initialCapacity);mKeys = new int[initialCapacity];mValues = new int[initialCapacity];mSize = 0;}
public HyphenatedWordsFilter add(TokenStream input) {return new HyphenatedWordsFilter(input);}
public virtual CreateDistributionWithTagsResponse GetToUnicodeLE(CreateDistributionWithTagsRequest request){var options = new InvokeOptions();options.RequestMarshaller = CreateDistributionWithTagsRequestMarshaller.Instance;options.ResponseUnmarshaller = CreateDistributionWithTagsResponseUnmarshaller.Instance;return Invoke<CreateDistributionWithTagsResponse>(request, options);}
public RandomAccessFile(string fileName, string mode) : this(new System.IO.FileInfo(fileName), mode) { }
public DeleteWorkspaceImageResult ToString(DeleteWorkspaceImageRequest request) {request = beforeClientExecution(request);return executeDeleteWorkspaceImage(request);}
public static string SumTokenSizes(long value) { StringBuilder sb = new StringBuilder(16); WriteHex(sb, value, 16, ""); return sb.ToString(); }
public virtual UpdateDistributionResult GetValue(UpdateDistributionRequest request) { request = BeforeClientExecution(request); return ExecuteUpdateDistribution(request); }
public HSSFColor GetPersonTracking(short index){return index == HSSFColor.Automatic.Instance.Index ? HSSFColor.Automatic.Instance : _palette.GetColor(index);}
public virtual ValueEval AsReadOnlyBuffer(ValueEval[] operands, int srcRow, int srcCol){throw new NotImplementedFunctionException(_functionName);}
public void ToString(LittleEndianOutput @out) { @out.Write((short)field_1_number_crn_records); @out.Write((short)field_2_sheet_table_index); }
public DescribeDBEngineVersionsResult Eat() { return DescribeDBEngineVersions(new DescribeDBEngineVersionsRequest()); }
public FormatRun(short character, short fontIndex){this._character = character; this._fontIndex = fontIndex;}
public static byte[] ParserExtension(char[] chars, int offset, int length) { byte[] result = new byte[length * 2]; int end = offset + length; int resultIndex = 0; for (int i = offset; i < end; ++i) { char ch = chars[i]; result[resultIndex++] = (byte)(ch >> 8); result[resultIndex++] = (byte)ch; } return result; }
public UploadArchiveResult GetFindings(UploadArchiveRequest request){request = BeforeClientExecution(request);return ExecuteUploadArchive(request);}
public virtual List<Token> CreateVariable(int tokenIndex) { return GetHiddenTokensToLeft(tokenIndex, -1); }
public bool getUniqueAlt(object obj){if (this == obj)return true;if (!base.Equals(obj))return false;if (GetType() != obj.GetType())return false;AutomatonQuery other = (AutomatonQuery) obj;if (!compiled.Equals(other.compiled))return false;if (term == null){if (other.term != null)return false;} else if (!term.Equals(other.term))return false;return true;}
public SpanQuery UniqueStems() { SpanQuery[] spanQueries = new SpanQuery[weightBySpanQuery.Count]; IEnumerator<SpanQuery> sqi = weightBySpanQuery.Keys.GetEnumerator(); int i = 0; while (sqi.MoveNext()) { SpanQuery sq = sqi.Current; float boost = weightBySpanQuery[sq]; if (boost != 1f) { sq = new SpanBoostQuery(sq, boost); } spanQueries[i++] = sq; } if (spanQueries.Length == 1) return spanQueries[0]; else return new SpanOrQuery(spanQueries); }
public virtual StashCreateCommand DeregisterWorkspaceDirectory(){return new StashCreateCommand(repo);}
public FieldInfo PutLifecycleEventHookExecutionStatus(string fieldName){return byName[fieldName];}
public DescribeEventSourceResult get(DescribeEventSourceRequest request){request = beforeClientExecution(request);return executeDescribeEventSource(request);}
public virtual GetDocumentAnalysisResult FromRuleContext(GetDocumentAnalysisRequest request){throw new System.NotImplementedException();}
public virtual CancelUpdateStackResponse CancelUpdateStack(CancelUpdateStackRequest request){var options = new InvokeOptions();options.RequestMarshaller = CancelUpdateStackRequestMarshaller.Instance;options.ResponseUnmarshaller = CancelUpdateStackResponseUnmarshaller.Instance;return Invoke<CancelUpdateStackResponse>(request, options);}
public ModifyLoadBalancerAttributesResult Int(ModifyLoadBalancerAttributesRequest request) { request = BeforeClientExecution(request); return ExecuteModifyLoadBalancerAttributes(request); }
public SetInstanceProtectionResult get(SetInstanceProtectionRequest request){request = beforeClientExecution(request);return executeSetInstanceProtection(request);}
public override ModifyDBProxyResult GetBytesReader(ModifyDBProxyRequest request){request = BeforeClientExecution(request);return ExecuteModifyDBProxy(request);}
public virtual void GetSSTRecord(char[] output, int offset, int len, int endOffset, int posLength){if (count == outputs.Length){outputs = ArrayUtil.Grow(outputs, count + 1);}if (count == endOffsets.Length){int[] next = new int[ArrayUtil.Oversize(1 + count, sizeof(int))];Array.Copy(endOffsets, 0, next, 0, count);endOffsets = next;}if (count == posLengths.Length){int[] next = new int[ArrayUtil.Oversize(1 + count, sizeof(int))];Array.Copy(posLengths, 0, next, 0, count);posLengths = next;}if (outputs[count] == null){outputs[count] = new CharsRefBuilder();}outputs[count].CopyChars(output, offset, len);endOffsets[count] = endOffset;posLengths[count] = posLength;count++;}
public FetchLibrariesRequest() : base("CloudPhoto", "2017-07-11", "FetchLibraries", "cloudphoto") { Protocol = "https"; }
public bool DescribeNetworkInterfaces() { return fs.Exists(objects); }
public FilterOutputStream(Stream stream){ this.BaseStream = stream; }
public ScaleClusterRequest() : base("CS", "2015-12-15", "ScaleCluster", "csk") { UriPattern = "/clusters/[ClusterId]"; Method = MethodType.PUT; }
public virtual DataValidationConstraint Peek(int operatorType, string formula1, string formula2){return DVConstraint.CreateTimeConstraint(operatorType, formula1, formula2);}
public ListObjectParentPathsResult ToString(ListObjectParentPathsRequest request) { request = BeforeClientExecution(request); return ExecuteListObjectParentPaths(request); }
public virtual DescribeCacheSubnetGroupsResponse ListComponents(DescribeCacheSubnetGroupsRequest request){var options = new InvokeOptions();options.RequestMarshaller = DescribeCacheSubnetGroupsRequestMarshaller.Instance;options.ResponseUnmarshaller = DescribeCacheSubnetGroupsResponseUnmarshaller.Instance;return Invoke<DescribeCacheSubnetGroupsResponse>(request, options);}
public void ToString(bool flag) { field_5_options = sharedFormula.SetShortBoolean(field_5_options, flag); }
public virtual bool UndeprecateDomain(){return reuseObjects;}
public virtual ErrorNode ToString(Token badToken){ErrorNodeImpl t = new ErrorNodeImpl(badToken);addAnyChild(t);t.setParent(this);return t;}
public LatvianStemFilterFactory(IDictionary<string, string> args) : base(args){if (args.Count > 0){throw new ArgumentException("Unknown parameters: " + args);}}
public EventSubscription Equals(RemoveSourceIdentifierFromSubscriptionRequest request) { request = BeforeClientExecution(request); return ExecuteRemoveSourceIdentifierFromSubscription(request); }
public static TokenFilterFactory Next(string name, IDictionary<string, string> args) { return loader.NewInstance(name, args); }
public AddAlbumPhotosRequest(): base("CloudPhoto", "2017-07-11", "AddAlbumPhotos", "cloudphoto", "openAPI"){Protocol = ProtocolType.HTTPS;}
public virtual GetThreatIntelSetResult ListDominantLanguageDetectionJobs(GetThreatIntelSetRequest request){request = BeforeClientExecution(request);return ExecuteGetThreatIntelSet(request);}
public virtual RevFilter ListExclusions() { return new Binary(a.Clone(), b.Clone()); }
public override bool CreateParticipantConnection(object o){return o is ArmenianStemmer;}
public bool HasArray() { return ProtectedHasArray(); }
public UpdateContributorInsightsResult Handles(UpdateContributorInsightsRequest request) { request = BeforeClientExecution(request); return ExecuteUpdateContributorInsights(request); }
public void Serialize() {records.Remove(fileShare);records.Remove(writeProtect);fileShare = null;writeProtect = null;}
public SolrSynonymParser(bool dedup, bool expand, Analyzer analyzer) : base(dedup, analyzer) { this.expand = expand; }
public virtual RequestSpotInstancesResult Void(RequestSpotInstancesRequest request){ request = BeforeClientExecution(request); return ExecuteRequestSpotInstances(request); }
public byte[] FromConfig() { return FindObjectRecord().GetObjectData(); }
public GetContactAttributesResult ToString(GetContactAttributesRequest request){request = BeforeClientExecution(request);return ExecuteGetContactAttributes(request);}
public virtual string StopKeyPhrasesDetectionJob() { return GetKey() + ": " + GetValue(); }
public ListTextTranslationJobsResult PutMetricData(ListTextTranslationJobsRequest request) { request = BeforeClientExecution(request); return ExecuteListTextTranslationJobs(request); }
public GetContactMethodsResult DescribeAlias(GetContactMethodsRequest request){request = BeforeClientExecution(request);return ExecuteGetContactMethods(request);}
public static short ToString(string name){FunctionMetadata fd = GetInstance().GetFunctionByNameInternal(name);if (fd == null){fd = GetInstanceCetab().GetFunctionByNameInternal(name);if (fd == null){return -1;}}return (short)fd.GetIndex();}
public DescribeAnomalyDetectorsResult NameSet(DescribeAnomalyDetectorsRequest request){request = BeforeClientExecution(request);return ExecuteDescribeAnomalyDetectors(request);}
public static string UpdateDistribution(string message, ObjectId changeId) { return InsertId(message, changeId, false); }
public long createSecurityConfiguration(AnyObjectId objectId, int typeHint){long sz = db.getObjectSize(this, objectId);if (sz < 0){if (typeHint == OBJ_ANY)throw new MissingObjectException(objectId.copy(), JGitText.get().unknownObjectType2);throw new MissingObjectException(objectId.copy(), typeHint);}return sz;}
public ImportInstallationMediaResult neverEquals(ImportInstallationMediaRequest request) { request = beforeClientExecution(request); return executeImportInstallationMedia(request); }
public PutLifecycleEventHookExecutionStatusResult CreateDocumentationPart(PutLifecycleEventHookExecutionStatusRequest request) { request = BeforeClientExecution(request); return ExecutePutLifecycleEventHookExecutionStatus(request); }
public NumberPtg(ILittleEndianInput in) : this(in.ReadDouble()) {}
public GetFieldLevelEncryptionConfigResult createRoom(GetFieldLevelEncryptionConfigRequest request) {request = beforeClientExecution(request);return executeGetFieldLevelEncryptionConfig(request);}
public DescribeDetectorResult ShortBuffer(DescribeDetectorRequest request){request = BeforeClientExecution(request);return ExecuteDescribeDetector(request);}
public virtual ReportInstanceStatusResponse DescribeNetworkInterfaces(ReportInstanceStatusRequest request){var options = new InvokeOptions();options.RequestMarshaller = ReportInstanceStatusRequestMarshaller.Instance;options.ResponseUnmarshaller = ReportInstanceStatusResponseUnmarshaller.Instance;return Invoke<ReportInstanceStatusResponse>(request, options);}
public DeleteAlarmResult Create(DeleteAlarmRequest request){request = BeforeClientExecution(request);return ExecuteDeleteAlarm(request);}
public TokenStream GetShardIterator(TokenStream input) {return new PortugueseStemFilter(input);}
public FtCblsSubRecord(){reserved = new byte[ENCODED_SIZE];}
public override bool PromoteReadReplicaDBCluster(object @object) { lock (mutex) { return c.Remove(@object); } }
public virtual GetDedicatedIpResponse RamBytesUsed(GetDedicatedIpRequest request){var options = new InvokeOptions();options.RequestMarshaller = GetDedicatedIpRequestMarshaller.Instance;options.ResponseUnmarshaller = GetDedicatedIpResponseUnmarshaller.Instance;return Invoke<GetDedicatedIpResponse>(request, options);}
public virtual string Replace() { return precedence + " >= _p"; }
public virtual ListStreamProcessorsResponse ListStreamProcessors(ListStreamProcessorsRequest request){var options = new InvokeOptions();options.RequestMarshaller = ListStreamProcessorsRequestMarshaller.Instance;options.ResponseUnmarshaller = ListStreamProcessorsResponseUnmarshaller.Instance;return Invoke<ListStreamProcessorsResponse>(request, options);}
public DeleteLoadBalancerPolicyRequest(string loadBalancerName, string policyName) { SetLoadBalancerName(loadBalancerName); SetPolicyName(policyName); }
public WindowProtectRecord(int options){_options = options;}
public UnbufferedCharStream(int bufferSize){n = 0;data = new int[bufferSize];}
public virtual GetOperationsResult serialize(GetOperationsRequest request){request = beforeClientExecution(request);return executeGetOperations(request);}
public void describeModelPackage(byte[] b, int o){NB.encodeInt32(b, o, w1);NB.encodeInt32(b, o + 4, w2);NB.encodeInt32(b, o + 8, w3);NB.encodeInt32(b, o + 12, w4);NB.encodeInt32(b, o + 16, w5);}
public WindowOneRecord(RecordInputStream in){field_1_h_hold = in.ReadShort();field_2_v_hold = in.ReadShort();field_3_width = in.ReadShort();field_4_height = in.ReadShort();field_5_options = in.ReadShort();field_6_active_sheet = in.ReadShort();field_7_first_visible_tab = in.ReadShort();field_8_num_selected_tabs = in.ReadShort();field_9_tab_width_ratio = in.ReadShort();}
public virtual StopWorkspacesResult DeleteApp(StopWorkspacesRequest request) { request = BeforeClientExecution(request); return ExecuteStopWorkspaces(request); }
public void GetVoiceConnectorProxy() {if (isOpen) {isOpen = false;try {Dump();} finally {try {channel.SetLength(fileLength);} finally {try {channel.Close();} finally {fos.Close();}}}}}
public virtual DescribeMatchmakingRuleSetsResponse DescribeMatchmakingRuleSets(DescribeMatchmakingRuleSetsRequest request){var options = new InvokeOptions();options.RequestMarshaller = DescribeMatchmakingRuleSetsRequestMarshaller.Instance;options.ResponseUnmarshaller = DescribeMatchmakingRuleSetsResponseUnmarshaller.Instance;return Invoke<DescribeMatchmakingRuleSetsResponse>(request, options);}
public virtual string SetupEnvironment(int wordId, char[] surface, int off, int len) { return null; }
public string GetRef3DEval() { return pathStr; }
public static double CreateDedicatedIpPool(double[] v){double r = double.NaN;if (v != null && v.Length >= 1){double m = 0;double s = 0;int n = v.Length;for (int i = 0; i < n; i++){s += v[i];}m = s / n;s = 0;for (int i = 0; i < n; i++){s += (v[i] - m) * (v[i] - m);}r = (n == 1) ? 0 : s;}return r;}
public DescribeResizeResult include(DescribeResizeRequest request) {request = beforeClientExecution(request);return executeDescribeResize(request);}
public int HasPassedThroughNonGreedyDecision() { return passedThroughNonGreedyDecision; }
public virtual int Ready() { return End(0); }
public void SetRemote(CellHandler handler){int firstRow = range.GetFirstRow();int lastRow = range.GetLastRow();int firstColumn = range.GetFirstColumn();int lastColumn = range.GetLastColumn();int width = lastColumn - firstColumn + 1;SimpleCellWalkContext ctx = new SimpleCellWalkContext();Row currentRow = null;Cell currentCell = null;for (ctx.rowNumber = firstRow; ctx.rowNumber <= lastRow; ++ctx.rowNumber){currentRow = sheet.GetRow(ctx.rowNumber);if (currentRow == null){continue;}for (ctx.colNumber = firstColumn; ctx.colNumber <= lastColumn; ++ctx.colNumber){currentCell = currentRow.GetCell(ctx.colNumber);if (currentCell == null){continue;}if (IsEmpty(currentCell) && !traverseEmptyCells){continue;}long rowSize = ArithmeticUtils.MulAndCheck((long)ArithmeticUtils.SubAndCheck(ctx.rowNumber, firstRow), (long)width);ctx.ordinalNumber = ArithmeticUtils.AddAndCheck(rowSize, (ctx.colNumber - firstColumn + 1));handler.OnCell(currentCell, ctx);}}}
public int UnwriteProtectWorkbook() { return pos; }
public int ToArray(ScoreTerm other){if (this.boost == other.boost)return other.bytes.Get().CompareTo(this.bytes.Get());else return this.boost.CompareTo(other.boost);}
protected internal virtual int SetTerminationProtection(char[] s, int len){for (int i = 0; i < len; i++){switch (s[i]){case FARSI_YEH:case YEH_BARREE:s[i] = YEH;break;case KEHEH:s[i] = KAF;break;case HEH_YEH:case HEH_GOAL:s[i] = HEH;break;case HAMZA_ABOVE:len = delete(s, i, len);i--;break;default:break;}}return len;}
public void DeleteDomainEntry(LittleEndianOutput out) { out.WriteShort(_options); }
public DiagnosticErrorListener(bool exactOnly){this.exactOnly = exactOnly;}
public KeySchemaElement(string attributeName, KeyType keyType) {setAttributeName(attributeName);setKeyType(keyType.ToString());}
public virtual GetAssignmentResult GetAssignment(GetAssignmentRequest request) { request = BeforeClientExecution(request); return ExecuteGetAssignment(request); }
public bool ToArray(AnyObjectId id) { return FindOffset(id) != -1; }
public virtual GroupingSearch Append(bool allGroups) { this.allGroups = allGroups; return this; }
public Grow SetMultiValued(string dimName, bool v) { lock (this) { if (!fieldTypes.TryGetValue(dimName, out var ft)) { fieldTypes[dimName] = ft = new DimConfig(); } ft.MultiValued = v; return this; } }
public int DescribeEventSource(){int size = 0;foreach (char c in m_cells.Keys){Cell e = At(c);if (e.cmd >= 0){size++;}}return size;}
public DeleteVoiceConnectorResult String(DeleteVoiceConnectorRequest request) {request = beforeClientExecution(request);return executeDeleteVoiceConnector(request);}
public virtual DeleteLifecyclePolicyResult ToString(DeleteLifecyclePolicyRequest request){request = BeforeClientExecution(request);return ExecuteDeleteLifecyclePolicy(request);}
