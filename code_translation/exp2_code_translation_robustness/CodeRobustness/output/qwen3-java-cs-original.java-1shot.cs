public void Serialize(LittleEndianOutput @out) { @out.WriteShort(this.field_1_vcenter); }
public void addAll(BlockList<T> src) {if (src.size == 0)return;int srcDirIdx = 0;for (; srcDirIdx < src.tailDirIdx; srcDirIdx++)addAll(src.directory[srcDirIdx], 0, BLOCK_SIZE);if (src.tailBlkIdx != 0)addAll(src.tailBlock, 0, src.tailBlkIdx);}
public void WriteByte(byte b) { if (upto == blockSize) { if (currentBlock != null) { AddBlock(currentBlock); } currentBlock = new byte[blockSize]; upto = 0; } currentBlock[upto++] = b; }
public virtual ObjectId GetObjectId() { return objectId; }
public DeleteDomainEntryResult DeleteDomainEntry(DeleteDomainEntryRequest request) { request = BeforeClientExecution(request); return ExecuteDeleteDomainEntry(request); }
public long ramBytesUsed() { return (termOffsets != null ? termOffsets.ramBytesUsed() : 0) + (termsDictOffsets != null ? termsDictOffsets.ramBytesUsed() : 0); }
public virtual string GetFullMessage() { byte[] raw = buffer; int msgB = RawParseUtils.TagMessage(raw, 0); if (msgB < 0) { return ""; } return RawParseUtils.Decode(GuessEncoding(), raw, msgB, raw.Length); }
public POIFSFileSystem() : this(true) {_header.SetBATCount(1);_header.SetBATArray(new int[]{1});BATBlock bb = BATBlock.CreateEmptyBATBlock(bigBlockSize, false);bb.SetOurBlockIndex(1);_bat_blocks.Add(bb);SetNextBlock(0, POIFSConstants.END_OF_CHAIN);SetNextBlock(1, POIFSConstants.FAT_SECTOR_BLOCK);_property_table.SetStartBlock(0);}
public virtual void Init(int address){slice = pool.buffers[address >> ByteBlockPool.BYTE_BLOCK_SHIFT];Debug.Assert(slice != null);upto = address & ByteBlockPool.BYTE_BLOCK_MASK;offset0 = address;Debug.Assert(upto < slice.Length);}
public SubmoduleAddCommand SetPath(string path) { _path = path; return this; }
public virtual ListIngestionsResponse ListIngestions(ListIngestionsRequest request){var options = new InvokeOptions();options.RequestMarshaller = ListIngestionsRequestMarshaller.Instance;options.ResponseUnmarshaller = ListIngestionsResponseUnmarshaller.Instance;return Invoke<ListIngestionsResponse>(request, options);}
public QueryParserTokenManager(CharStream stream, int lexState) : this(stream) { SwitchTo(lexState); }
public GetShardIteratorResult GetShardIterator(GetShardIteratorRequest request){request = BeforeClientExecution(request);return ExecuteGetShardIterator(request);}
public ModifyStrategyRequest() : base("aegis", "2016-11-11", "ModifyStrategy", "vipaegis") { Method = MethodType.POST; }
public virtual bool Ready() { lock (@lock) { if (in == null) { throw new IOException("InputStreamReader is closed"); } try { return bytes.HasRemaining() || in.Available() > 0; } catch (IOException e) { return false; } } }
public virtual EscherOptRecord GetOptRecord(){return _optRecord;}
public override int Read(byte[] buffer, int offset, int length) { if (buffer == null) { throw new java.lang.NullPointerException("buffer == null"); } Arrays.checkOffsetAndCount(buffer.length, offset, length); if (length == 0) { return 0; } int copylen = _count - _pos < length ? _count - _pos : length; for (int i = 0; i < copylen; i++) { buffer[offset + i] = (byte)this.buffer[_pos + i]; } _pos += copylen; return copylen; }
public OpenNLPSentenceBreakIterator(NLPSentenceDetectorOp sentenceOp) { this.sentenceOp = sentenceOp; }
public virtual void Print(string str) { Write(str ?? "null"); }
public NotImplementedFunctionException(string functionName, NotImplementedException cause) : base(functionName, cause) { this.functionName = functionName; }
public V Next(){return base.NextEntry().GetValue();}
public void ReadBytes(byte[] b, int offset, int len, bool useBuffer) { int available = bufferLength - bufferPosition; if (len <= available) { if (len > 0) Buffer.BlockCopy(buffer, bufferPosition, b, offset, len); bufferPosition += len; } else { if (available > 0) { Buffer.BlockCopy(buffer, bufferPosition, b, offset, available); offset += available; len -= available; bufferPosition += available; } if (useBuffer && len < bufferSize) { refill(); if (bufferLength < len) { Buffer.BlockCopy(buffer, 0, b, offset, bufferLength); throw new EOFException("read past EOF: " + this); } else { Buffer.BlockCopy(buffer, 0, b, offset, len); bufferPosition = len; } } else { long after = bufferStart + bufferPosition + len; if (after > length()) throw new EOFException("read past EOF: " + this); readInternal(b, offset, len); bufferStart = after; bufferPosition = 0; bufferLength = 0; } } }
public override TagQueueResult tagQueue(TagQueueRequest request) { request = beforeClientExecution(request); return executeTagQueue(request); }
public virtual void Remove() { throw new NotSupportedException(); }
public CacheSubnetGroup ModifyCacheSubnetGroup(ModifyCacheSubnetGroupRequest request) { request = BeforeClientExecution(request); return ExecuteModifyCacheSubnetGroup(request); }
public void setParams(String @params) {base.setParams(@params);language = country = variant = "";String[] tokens = @params.Split(new char[]{','}, System.StringSplitOptions.RemoveEmptyEntries);if(tokens.Length>0)language=tokens[0];if(tokens.Length>1)country=tokens[1];if(tokens.Length>2)variant=tokens[2];}
public DeleteDocumentationVersionResult deleteDocumentationVersion(DeleteDocumentationVersionRequest request) { request = beforeClientExecution(request); return executeDeleteDocumentationVersion(request); }
public override bool Equals(object obj) {if (!(obj is FacetLabel)) {return false;}FacetLabel other = (FacetLabel)obj;if (length != other.length) {return false;}for (int i = length - 1; i >= 0; i--) {if (!components[i].Equals(other.components[i])) {return false;}}return true;}
public IGetInstanceAccessDetailsResult GetInstanceAccessDetails(IGetInstanceAccessDetailsRequest request){request = BeforeClientExecution(request);return ExecuteGetInstanceAccessDetails(request);}
public virtual HSSFPolygon CreatePolygon(HSSFChildAnchor anchor) { HSSFPolygon shape = new HSSFPolygon(this, anchor); shape.SetParent(this); shape.SetAnchor(anchor); shapes.Add(shape); OnCreate(shape); return shape; }
public virtual string GetSheetName(int sheetIndex) { return GetBoundSheetRec(sheetIndex).GetSheetname(); }
public GetDashboardResult GetDashboard(GetDashboardRequest request){request = BeforeClientExecution(request);return ExecuteGetDashboard(request);}
public override AssociateSigninDelegateGroupsWithAccountResult associateSigninDelegateGroupsWithAccount(AssociateSigninDelegateGroupsWithAccountRequest request) { request = beforeClientExecution(request); return executeAssociateSigninDelegateGroupsWithAccount(request); }
public virtual void AddMultipleBlanks(MulBlankRecord mbr) { for (int j = 0; j < mbr.GetNumColumns(); j++) { BlankRecord br = new BlankRecord(); br.SetColumn((short)(j + mbr.GetFirstColumn())); br.SetRow(mbr.GetRow()); br.SetXFIndex(mbr.GetXFAt(j)); InsertCell(br); } }
public static string Quote(string string) { StringBuilder sb = new StringBuilder(); sb.Append(@"\Q"); int apos = 0; int k; while ((k = string.IndexOf(@"\E", apos, StringComparison.Ordinal)) >=0 ) { sb.Append(string.Substring(apos, k + 2 - apos)).Append(@"\E\Q"); apos = k + 2; } return sb.Append(string.Substring(apos)).Append(@"\E").ToString(); }
public ByteBuffer PutInt(int value) { throw new Sharpen.ReadOnlyBufferException(); }
public ArrayPtg(Object[][] values2d) {int nColumns = values2d[0].Length;int nRows = values2d.Length;_nColumns = (short)nColumns;_nRows = (short)nRows;Object[] vv = new Object[_nColumns * _nRows];for (int r = 0; r < nRows; r++) {Object[] rowData = values2d[r];for (int c = 0; c < nColumns; c++) {vv[GetValueIndex(c, r)] = rowData[c];}}_arrayValues = vv;_reserved0Int = 0;_reserved1Short = 0;_reserved2Byte = 0;}
public GetIceServerConfigResult GetIceServerConfig(GetIceServerConfigRequest request) { request = beforeClientExecution(request); return executeGetIceServerConfig(request); }
public virtual string ToString() { return GetType().Name + " [" + getValueAsString() +"]";}
public string ToString(string field) { return "ToChildBlockJoinQuery (" + parentQuery.ToString() + ")"; }
public sealed void IncRef() { Interlocked.Increment(ref refCount); }
public UpdateConfigurationSetSendingEnabledResult UpdateConfigurationSetSendingEnabled(UpdateConfigurationSetSendingEnabledRequest request){request = BeforeClientExecution(request);return ExecuteUpdateConfigurationSetSendingEnabled(request);}
public virtual int GetNextXBATChainOffset() { return GetXBATEntriesPerBlock() * LittleEndianConsts.INT_SIZE; }
public virtual void MultiplyByPowerOfTen(int pow10) { TenPower tp = TenPower.GetInstance(Math.Abs(pow10)); if (pow10 < 0) { mulShift(tp._divisor, tp._divisorShift); } else { mulShift(tp._multiplicand, tp._multiplierShift); } }
public string ToString(){StringBuilder b = new StringBuilder();int l = Length();b.Append(Path.DirectorySeparatorChar);for(int i=0;i<l;i++){b.Append(GetComponent(i));if(i < l-1){b.Append(Path.DirectorySeparatorChar);}}return b.ToString();}
public override InstanceProfileCredentialsProvider withFetcher(ECSMetadataServiceCredentialsFetcher fetcher) { this.fetcher = fetcher; this.fetcher.setRoleName(_roleName); return this; }
public virtual void SetProgressMonitor(ProgressMonitor pm) { progressMonitor = pm; }
public override void Reset() { if (!First()) { ptr = 0; if (!Eof()) ParseEntry(); } }
public virtual E Previous() { if (iterator.PreviousIndex() >= start) return iterator.Previous(); throw new InvalidOperationException(); }
public virtual string GetNewPrefix() { return this.newPrefix; }
public int indexOfValue(int value) { for (int i = 0; i < mSize; i++) if (mValues[i] == value) return i; return -1; }
public List<CharsRef> UniqueStems(char[] word, int length) {List<CharsRef> stems = Stem(word, length);if (stems.Count < 2) {return stems;}CharArraySet terms = new CharArraySet(8, dictionary.IgnoreCase);List<CharsRef> deduped = new List<CharsRef>();foreach (CharsRef s in stems) {if (!terms.Contains(s)) {deduped.Add(s);terms.Add(s);}}return deduped;}
public GetGatewayResponsesResult GetGatewayResponses(GetGatewayResponsesRequest request) { request = beforeClientExecution(request); return executeGetGatewayResponses(request); }
public void setPosition(long pos) { currentBlockIndex = (int)(pos >> blockBits); currentBlock = blocks[currentBlockIndex]; currentBlockUpto = (int)(pos & blockMask); }
public long Skip(long n) { int s = (int)Math.Min(Available(), Math.Max(0, n)); ptr += s; return s; }
public BootstrapActionDetail(BootstrapActionConfig bootstrapActionConfig) { SetBootstrapActionConfig(bootstrapActionConfig); }
public void Serialize(LittleEndianOutput @out) { @out.WriteShort(field_1_row); @out.WriteShort(field_2_col); @out.WriteShort(field_3_flags); @out.WriteShort(field_4_shapeid); @out.WriteShort((ushort)field_6_author.Length); @out.WriteByte(field_5_hasMultibyte ? (byte)0x01 : (byte)0x00); if (field_5_hasMultibyte) { StringUtil.PutUnicodeLE(field_6_author, @out); } else { StringUtil.PutCompressedUnicode(field_6_author, @out); } if (field_7_padding != null) { @out.WriteByte((byte)field_7_padding.Value); } }
public int LastIndexOf(string str) { return LastIndexOf(str, count); }
public virtual bool Add(E obj){return AddLastImpl(obj);}
public virtual void UnsetSection(string section, string subsection) { ConfigSnapshot src, res; do { src = state.Value; res = UnsetSection(src, section, subsection); } while (!state.CompareAndSet(src, res)); }
public virtual string GetTagName(){return tagName;}
public virtual void AddSubRecord(int index, SubRecord element) { subrecords.Insert(index, element); }
public override bool Remove(object o) { lock (mutex) { return delegate().Remove(o); } }
public virtual DoubleMetaphoneFilter Create(TokenStream input){return new DoubleMetaphoneFilter(input, m_maxCodeLength, m_inject);}
public long Length() { return inCoreLength(); }
public void SetValue(bool newValue) { value = newValue; }
public Pair(ContentSource oldSource, ContentSource newSource) { this.oldSource = oldSource; this.newSource = newSource; }
public virtual int Get(int i) { if (count <= i) throw new IndexOutOfRangeException(i.ToString()); return entries[i]; }
public CreateRepoRequest() : base("cr", "2016-06-07", "CreateRepo", "cr") { SetUriPattern("/repos"); SetMethod(MethodType.PUT); }
public override bool IsDeltaBaseAsOffset() { return deltaBaseAsOffset; }
public virtual void Remove() { if (expectedModCount == list.modCount) { if (lastLink != null) { Link<ET> next = lastLink.next; Link<ET> previous = lastLink.previous; next.previous = previous; previous.next = next; if (lastLink == link) { pos--; } link = previous; lastLink = null; expectedModCount++; list.size--; list.modCount++; } else { throw new InvalidOperationException(); } } else { throw new InvalidOperationException(); } }
public MergeShardsResult MergeShards(MergeShardsRequest request) { request = BeforeClientExecution(request); return ExecuteMergeShards(request); }
public override AllocateHostedConnectionResult AllocateHostedConnection(AllocateHostedConnectionRequest request){request = BeforeClientExecution(request);return ExecuteAllocateHostedConnection(request);}
public virtual int GetBeginIndex() { return start; }
public static WeightedTerm[] GetTerms(Query query) { return GetTerms(query, false); }
public ByteBuffer Compact() { throw new ReadOnlyBufferException(); }
public void Decode(byte[] blocks, int blocksOffset, long[] values, int valuesOffset, int iterations) { for (int i = 0; i < iterations; ++i) { long byte0 = blocks[blocksOffset++]; values[valuesOffset++] = byte0 >> 2; long byte1 = blocks[blocksOffset++]; values[valuesOffset++] = ((byte0 & 3) << 4) | (byte1 >> 4); long byte2 = blocks[blocksOffset++]; values[valuesOffset++] = ((byte1 & 15) << 2) | (byte2 >> 6); values[valuesOffset++] = byte2 & 63; } }
public string GetHumanishName() { string s = GetPath(); if (s == "/" || s == string.Empty) s = GetHost(); if (s == null) throw new ArgumentException(); string[] elements; if ("file" == scheme || LOCAL_FILE.IsMatch(s)) elements = s.Split(new[] { Path.DirectorySeparatorChar, '/' }, StringSplitOptions.RemoveEmptyEntries); else elements = s.Split(new[] { '/' }, StringSplitOptions.RemoveEmptyEntries); if (elements.Length == 0) throw new ArgumentException(); string result = elements[elements.Length - 1]; if (Constants.DOT_GIT == result) result = elements[elements.Length - 2]; else if (result.EndsWith(Constants.DOT_GIT_EXT)) result = result.Substring(0, result.Length - Constants.DOT_GIT_EXT.Length); return result; }
public DescribeNotebookInstanceLifecycleConfigResult DescribeNotebookInstanceLifecycleConfig(DescribeNotebookInstanceLifecycleConfigRequest request){request = beforeClientExecution(request);return executeDescribeNotebookInstanceLifecycleConfig(request);}
public virtual string GetAccessKeySecret() { return this.accessKeySecret; }
public override CreateVpnConnectionResult CreateVpnConnection(CreateVpnConnectionRequest request){request = beforeClientExecution(request);return executeCreateVpnConnection(request);}
public DescribeVoicesResult DescribeVoices(DescribeVoicesRequest request) { request = BeforeClientExecution(request); return ExecuteDescribeVoices(request); }
public override ListMonitoringExecutionsResult listMonitoringExecutions(ListMonitoringExecutionsRequest request) { request = beforeClientExecution(request); return executeListMonitoringExecutions(request); }
public DescribeJobRequest(string vaultName, string jobId) { SetVaultName(vaultName); SetJobId(jobId); }
public EscherRecord GetEscherRecord(int index){return escherRecords[index];}
public new GetApisResult GetApis(GetApisRequest request) { request = BeforeClientExecution(request); return ExecuteGetApis(request); }
public DeleteSmsChannelResult DeleteSmsChannel(DeleteSmsChannelRequest request) { request = BeforeClientExecution(request); return ExecuteDeleteSmsChannel(request); }
public virtual TrackingRefUpdate GetTrackingRefUpdate() { return trackingRefUpdate; }
public void print(bool b) { print(Convert.ToString(b)); }
public QueryNode GetChild() { return GetChildren()[0]; }
public NotIgnoredFilter(int workdirTreeIndex) { this.index = workdirTreeIndex; }
public AreaRecord(RecordInputStream @in) { field_1_formatFlags = @in.ReadShort(); }
public GetThumbnailRequest() : base("CloudPhoto", "2017-07-11", "GetThumbnail", "cloudphoto") { Protocol = ProtocolType.HTTPS; }
public virtual DescribeTransitGatewayVpcAttachmentsResult DescribeTransitGatewayVpcAttachments(DescribeTransitGatewayVpcAttachmentsRequest request) { request = beforeClientExecution(request); return executeDescribeTransitGatewayVpcAttachments(request); }
public PutVoiceConnectorStreamingConfigurationResult PutVoiceConnectorStreamingConfiguration(PutVoiceConnectorStreamingConfigurationRequest request){request = beforeClientExecution(request);return executePutVoiceConnectorStreamingConfiguration(request);}
public virtual OrdRange GetOrdRange(string dim){return prefixToOrdRange[dim];}
public override string ToString() { string symbol = ""; if (startIndex >= 0 && startIndex < GetInputStream().Size()) { symbol = GetInputStream().GetText(new Interval(startIndex, startIndex)); symbol = Utils.EscapeWhitespace(symbol, false); } return string.Format(CultureInfo.CurrentCulture, "{0}('{1}')", typeof(LexerNoViableAltException).Name, symbol); }
public T Peek() { return PeekFirstImpl(); }
public virtual CreateWorkspacesResult CreateWorkspaces(CreateWorkspacesRequest request){request = beforeClientExecution(request);return executeCreateWorkspaces(request);}
public override NumberFormatIndexRecord Clone() { return copy(); }
public virtual DescribeRepositoriesResponse DescribeRepositories(DescribeRepositoriesRequest request){var options = new InvokeOptions();options.RequestMarshaller = DescribeRepositoriesRequestMarshaller.Instance;options.ResponseUnmarshaller = DescribeRepositoriesResponseUnmarshaller.Instance;return Invoke<DescribeRepositoriesResponse>(request, options);}
public SparseIntArray(int initialCapacity) { initialCapacity = ArrayUtils.idealIntArraySize(initialCapacity); mKeys = new int[initialCapacity]; mValues = new int[initialCapacity]; mSize = 0; }
public virtual HyphenatedWordsFilter Create(TokenStream input) { return new HyphenatedWordsFilter(input); }
public virtual CreateDistributionWithTagsResponse CreateDistributionWithTags(CreateDistributionWithTagsRequest request) { request = beforeClientExecution(request); return executeCreateDistributionWithTags(request); }
public RandomAccessFile(string fileName, string mode) : this(new File(fileName), mode) {}
public DeleteWorkspaceImageResult deleteWorkspaceImage(DeleteWorkspaceImageRequest request) { request = beforeClientExecution(request); return executeDeleteWorkspaceImage(request); }
public static string ToHex(long value) {StringBuilder sb = new StringBuilder(16);writeHex(sb, value, 16, "");return sb.ToString();}
public virtual UpdateDistributionResponse UpdateDistribution(UpdateDistributionRequest request){var options = new InvokeOptions();options.RequestMarshaller = UpdateDistributionRequestMarshaller.Instance;options.ResponseUnmarshaller = UpdateDistributionResponseUnmarshaller.Instance;return Invoke<UpdateDistributionResponse>(request, options);}
public virtual HSSFColor GetColor(short index) { if (index == HSSFColorPredefined.AUTOMATIC.Index) { return HSSFColorPredefined.AUTOMATIC.Color; } byte[] b = _palette.GetColor(index); return b == null ? null : new CustomColor(index, b); }
public virtual ValueEval Evaluate(ValueEval[] operands, int srcRow, int srcCol) { throw new NotImplementedFunctionException(_functionName); }
public virtual void Serialize(LittleEndianOutput output) { output.writeShort((short)field_1_number_crn_records); output.writeShort((short)field_2_sheet_table_index); }
public virtual DescribeDBEngineVersionsResult DescribeDBEngineVersions(){return describeDBEngineVersions(new DescribeDBEngineVersionsRequest());}
public FormatRun(short character, short fontIndex) { this._character = character; this._fontIndex = fontIndex; }
public static byte[] toBigEndianUtf16Bytes(char[] chars, int offset, int length) {byte[] result = new byte[length * 2];int end = offset + length;int resultIndex = 0;for (int i = offset; i < end; ++i) {char ch = chars[i];result[resultIndex++] = (byte)(ch >> 8);result[resultIndex++] = (byte)ch;}return result;}
public UploadArchiveResult UploadArchive(UploadArchiveRequest request) { request = BeforeClientExecution(request); return ExecuteUploadArchive(request); }
public virtual List<Token> GetHiddenTokensToLeft(int tokenIndex) { return GetHiddenTokensToLeft(tokenIndex, -1); }
public override bool Equals(object obj) { if (ReferenceEquals(this, obj)) return true; if (!base.Equals(obj)) return false; if (GetType() != obj.GetType()) return false; AutomatonQuery other = (AutomatonQuery)obj; if (!compiled.Equals(other.compiled)) return false; if (term == null) { if (other.term != null) return false; } else if (!term.Equals(other.term)) return false; return true; }
public virtual SpanQuery MakeSpanClause(){SpanQuery[] spanQueries = new SpanQuery[weightBySpanQuery.Keys.Count];int i = 0;foreach(var sq in weightBySpanQuery.Keys){float boost = weightBySpanQuery[sq];if(boost != 1f){sq = new SpanBoostQuery(sq, boost);}spanQueries[i++] = sq;}if(spanQueries.Length == 1)return spanQueries[0];else return new SpanOrQuery(spanQueries);}
public virtual StashCreateCommand stashCreate(){return new StashCreateCommand(repo);}
public virtual FieldInfo FieldInfo(string fieldName) { return byName[fieldName]; }
public virtual DescribeEventSourceResponse DescribeEventSource(DescribeEventSourceRequest request){var options = new InvokeOptions();options.RequestMarshaller = DescribeEventSourceRequestMarshaller.Instance;options.ResponseUnmarshaller = DescribeEventSourceResponseUnmarshaller.Instance;return Invoke<DescribeEventSourceResponse>(request, options);}
public GetDocumentAnalysisResult GetDocumentAnalysis(GetDocumentAnalysisRequest request) { request = beforeClientExecution(request); return executeGetDocumentAnalysis(request); }
public virtual CancelUpdateStackResponse CancelUpdateStack(CancelUpdateStackRequest request) { var options = new InvokeOptions(); options.RequestMarshaller = CancelUpdateStackRequestMarshaller.Instance; options.ResponseUnmarshaller = CancelUpdateStackResponseUnmarshaller.Instance; return Invoke<CancelUpdateStackResponse>(request, options); }
public virtual ModifyLoadBalancerAttributesResult ModifyLoadBalancerAttributes(ModifyLoadBalancerAttributesRequest request) { request = BeforeClientExecution(request); return ExecuteModifyLoadBalancerAttributes(request); }
public virtual SetInstanceProtectionResponse SetInstanceProtection(SetInstanceProtectionRequest request) {     var options = new InvokeOptions();     options.RequestMarshaller = SetInstanceProtectionRequestMarshaller.Instance;     options.ResponseUnmarshaller = SetInstanceProtectionResponseUnmarshaller.Instance;     return Invoke<SetInstanceProtectionResponse>(request, options); }
public virtual ModifyDBProxyResponse ModifyDBProxy(ModifyDBProxyRequest request){var options = new InvokeOptions();options.RequestMarshaller = ModifyDBProxyRequestMarshaller.Instance;options.ResponseUnmarshaller = ModifyDBProxyResponseUnmarshaller.Instance;return Invoke<ModifyDBProxyResponse>(request, options);}
public void Add(char[] output, int offset, int len, int endOffset, int posLength) { if (count == outputs.Length) { outputs = ArrayUtil.Grow(outputs, count + 1); } if (count == endOffsets.Length) { int[] next = new int[ArrayUtil.Oversize(1 + count, 4)]; Array.Copy(endOffsets, 0, next, 0, count); endOffsets = next; } if (count == posLengths.Length) { int[] next = new int[ArrayUtil.Oversize(1 + count, 4)]; Array.Copy(posLengths, 0, next, 0, count); posLengths = next; } if (outputs[count] == null) { outputs[count] = new CharsRefBuilder(); } outputs[count].CopyChars(output, offset, len); endOffsets[count] = endOffset; posLengths[count] = posLength; count++; }
public FetchLibrariesRequest() : base("CloudPhoto", "2017-07-11", "FetchLibraries", "cloudphoto") { SetProtocol(ProtocolType.HTTPS); }
public virtual bool Exists() { return fs.Exists(objects); }
public FilterOutputStream(OutputStream @out) { this.out = @out; }
public ScaleClusterRequest() : base("CS", "2015-12-15", "ScaleCluster", "csk") { UriPattern = "/clusters/[ClusterId]"; Method = MethodType.PUT; }
public DataValidationConstraint CreateTimeConstraint(int operatorType, string formula1, string formula2) { return DVConstraint.CreateTimeConstraint(operatorType, formula1, formula2); }
public virtual ListObjectParentPathsResult ListObjectParentPaths(ListObjectParentPathsRequest request) { request = BeforeClientExecution(request); return ExecuteListObjectParentPaths(request); }
public virtual DescribeCacheSubnetGroupsResponse DescribeCacheSubnetGroups(DescribeCacheSubnetGroupsRequest request){var options = new InvokeOptions();options.RequestMarshaller = DescribeCacheSubnetGroupsRequestMarshaller.Instance;options.ResponseUnmarshaller = DescribeCacheSubnetGroupsResponseUnmarshaller.Instance;return Invoke<DescribeCacheSubnetGroupsResponse>(request, options);}
public virtual void SetSharedFormula(bool flag) { field_5_options = SharedFormula.SetShortBoolean(field_5_options, flag); }
public bool IsReuseObjects() { return reuseObjects; }
public virtual ErrorNode AddErrorNode(Token badToken) { var t = new ErrorNodeImpl(badToken); AddAnyChild(t); t.Parent = this; return t; }
public LatvianStemFilterFactory(Dictionary<string,string> args) : base(args) { if (args.Count > 0) { throw new ArgumentException("Unknown parameters: " + args); } }
public EventSubscription RemoveSourceIdentifierFromSubscription(RemoveSourceIdentifierFromSubscriptionRequest request) { request = BeforeClientExecution(request); return ExecuteRemoveSourceIdentifierFromSubscription(request); }
public static TokenFilterFactory forName(string name, Dictionary<string, string> args) { return loader.newInstance(name, args); }
public AddAlbumPhotosRequest() : base("CloudPhoto", "2017-07-11", "AddAlbumPhotos", "cloudphoto"){setProtocol(ProtocolType.HTTPS);throw new System.NotImplementedException();}
public GetThreatIntelSetResult GetThreatIntelSet(GetThreatIntelSetRequest request){request = beforeClientExecution(request);return executeGetThreatIntelSet(request);}
public virtual RevFilter Clone() { return new Binary(a.Clone(), b.Clone()); }
public override bool Equals(object o) { return o is ArmenianStemmer; }
public bool HasArray() { return ProtectedHasArray(); }
public UpdateContributorInsightsResult updateContributorInsights(UpdateContributorInsightsRequest request) { request = beforeClientExecution(request); return executeUpdateContributorInsights(request); }
public void UnwriteProtectWorkbook() { records.Remove(m_fileShare); records.Remove(m_writeProtect); m_fileShare = null; m_writeProtect = null; }
public SolrSynonymParser(bool dedup, bool expand, Analyzer analyzer) : base(dedup, analyzer) { this.expand = expand; }
public override RequestSpotInstancesResult RequestSpotInstances(RequestSpotInstancesRequest request) { request = BeforeClientExecution(request); return ExecuteRequestSpotInstances(request); }
public byte[] GetObjectData() { return FindObjectRecord().GetObjectData(); }
public virtual GetContactAttributesResponse GetContactAttributes(GetContactAttributesRequest request){var options = new InvokeOptions();options.RequestMarshaller = GetContactAttributesRequestMarshaller.Instance;options.ResponseUnmarshaller = GetContactAttributesResponseUnmarshaller.Instance;return Invoke<GetContactAttributesResponse>(request, options);}
public string ToString() { return getKey() + ": " + getValue(); }
public virtual ListTextTranslationJobsResult listTextTranslationJobs(ListTextTranslationJobsRequest request){request = beforeClientExecution(request);return executeListTextTranslationJobs(request);}
public virtual GetContactMethodsResponse GetContactMethods(GetContactMethodsRequest request){var options = new InvokeOptions();options.RequestMarshaller = GetContactMethodsRequestMarshaller.Instance;options.ResponseUnmarshaller = GetContactMethodsResponseUnmarshaller.Instance;return Invoke<GetContactMethodsResponse>(request, options);}
public static short LookupIndexByName(string name) { var fd = Instance.GetFunctionByNameInternal(name); if (fd == null) { fd = Cetab.Instance.GetFunctionByNameInternal(name); if (fd == null) return -1; } return (short)fd.GetIndex(); }
public virtual DescribeAnomalyDetectorsResult DescribeAnomalyDetectors(DescribeAnomalyDetectorsRequest request) { request = BeforeClientExecution(request); return ExecuteDescribeAnomalyDetectors(request); }
public static string InsertId(string message, ObjectId changeId) { return InsertId(message, changeId, false); }
public virtual long GetObjectSize(AnyObjectId objectId, int typeHint) { long sz = db.GetObjectSize(this, objectId); if (sz < 0) { if (typeHint == Constants.OBJ_ANY) throw new MissingObjectException(objectId.Copy(), JGitText.Instance.UnknownObjectType2); throw new MissingObjectException(objectId.Copy(), typeHint); } return sz; }
public virtual ImportInstallationMediaResponse ImportInstallationMedia(ImportInstallationMediaRequest request){var options = new InvokeOptions();options.RequestMarshaller = ImportInstallationMediaRequestMarshaller.Instance;options.ResponseUnmarshaller = ImportInstallationMediaResponseUnmarshaller.Instance;return Invoke<ImportInstallationMediaResponse>(request, options);}
public virtual PutLifecycleEventHookExecutionStatusResult PutLifecycleEventHookExecutionStatus(PutLifecycleEventHookExecutionStatusRequest request){var options = new InvokeOptions();options.RequestMarshaller = PutLifecycleEventHookExecutionStatusRequestMarshaller.Instance;options.ResponseUnmarshaller = PutLifecycleEventHookExecutionStatusResultUnmarshaller.Instance;return Invoke<PutLifecycleEventHookExecutionStatusResult>(request, options);}
public NumberPtg(LittleEndianInput in) : this(in.ReadDouble()) {}
public GetFieldLevelEncryptionConfigResult GetFieldLevelEncryptionConfig(GetFieldLevelEncryptionConfigRequest request) { request = BeforeClientExecution(request); return ExecuteGetFieldLevelEncryptionConfig(request); }
public virtual DescribeDetectorResponse DescribeDetector(DescribeDetectorRequest request){var options = new InvokeOptions();options.RequestMarshaller = DescribeDetectorRequestMarshaller.Instance;options.ResponseUnmarshaller = DescribeDetectorResponseUnmarshaller.Instance;return Invoke<DescribeDetectorResponse>(request, options);}
public virtual ReportInstanceStatusResult ReportInstanceStatus(ReportInstanceStatusRequest request) { request = BeforeClientExecution(request); return ExecuteReportInstanceStatus(request); }
public virtual DeleteAlarmResponse DeleteAlarm(DeleteAlarmRequest request){var options = new InvokeOptions();options.RequestMarshaller = DeleteAlarmRequestMarshaller.Instance;options.ResponseUnmarshaller = DeleteAlarmResponseUnmarshaller.Instance;return Invoke<DeleteAlarmResponse>(request, options);}
public virtual TokenStream Create(TokenStream input) { return new PortugueseStemFilter(input); }
public FtCblsSubRecord() { reserved = new byte[ENCODED_SIZE]; }
public override bool Remove(object obj) { lock (mutex) { return c.Remove(obj); } }
public GetDedicatedIpResult getDedicatedIp(GetDedicatedIpRequest request) { request = beforeClientExecution(request); return executeGetDedicatedIp(request); }
public override string ToString() { return precedence + " >= _p"; }
public virtual ListStreamProcessorsResponse ListStreamProcessors(ListStreamProcessorsRequest request){var options = new InvokeOptions();options.RequestMarshaller = ListStreamProcessorsRequestMarshaller.Instance;options.ResponseUnmarshaller = ListStreamProcessorsResponseUnmarshaller.Instance;return Invoke<ListStreamProcessorsResponse>(request, options);}
public DeleteLoadBalancerPolicyRequest(string loadBalancerName, string policyName) { SetLoadBalancerName(loadBalancerName); SetPolicyName(policyName); }
public WindowProtectRecord(int options) {_options = options;}
public UnbufferedCharStream(int bufferSize){n = 0; data = new int[bufferSize];}
public override GetOperationsResult GetOperations(GetOperationsRequest request){request = beforeClientExecution(request);return executeGetOperations(request);}
public void CopyRawTo(byte[] b, int o) {NB.EncodeInt32(b, o, this.w1);NB.EncodeInt32(b, o + 4, this.w2);NB.EncodeInt32(b, o + 8, this.w3);NB.EncodeInt32(b, o + 12, this.w4);NB.EncodeInt32(b, o + 16, this.w5);}
public WindowOneRecord(RecordInputStream @in) { field_1_h_hold = @in.ReadShort(); field_2_v_hold = @in.ReadShort(); field_3_width = @in.ReadShort(); field_4_height = @in.ReadShort(); field_5_options = @in.ReadShort(); field_6_active_sheet = @in.ReadShort(); field_7_first_visible_tab = @in.ReadShort(); field_8_num_selected_tabs = @in.ReadShort(); field_9_tab_width_ratio = @in.ReadShort(); }
public virtual StopWorkspacesResponse StopWorkspaces(StopWorkspacesRequest request){var options = new InvokeOptions();options.RequestMarshaller = StopWorkspacesRequestMarshaller.Instance;options.ResponseUnmarshaller = StopWorkspacesResponseUnmarshaller.Instance;return Invoke<StopWorkspacesResponse>(request, options);}
public void close(){if(isOpen){isOpen=false;try{dump();}finally{try{channel.truncate(fileLength);}finally{try{channel.close();}finally{fos.close();}}}}}
public virtual DescribeMatchmakingRuleSetsResponse DescribeMatchmakingRuleSets(DescribeMatchmakingRuleSetsRequest request){var options = new InvokeOptions();options.RequestMarshaller = DescribeMatchmakingRuleSetsRequestMarshaller.Instance;options.ResponseUnmarshaller = DescribeMatchmakingRuleSetsResponseUnmarshaller.Instance;return Invoke<DescribeMatchmakingRuleSetsResponse>(request, options);}
public string GetPronunciation(int wordId, char[] surface, int off, int len) { return null; }
public virtual string GetPath() { return pathStr; }
public static double Devsq(double[] v) { double r = double.NaN; if (v != null && v.Length >= 1) { double m = 0; double s = 0; int n = v.Length; for (int i = 0; i < n; i++) { s += v[i]; } m = s / n; s = 0; for (int i = 0; i < n; i++) { s += (v[i] - m) * (v[i] - m); } r = (n == 1) ? 0 : s; } return r; }
public virtual DescribeResizeResponse DescribeResize(DescribeResizeRequest request){var options = new InvokeOptions();options.RequestMarshaller = DescribeResizeRequestMarshaller.Instance;options.ResponseUnmarshaller = DescribeResizeResponseUnmarshaller.Instance;return Invoke<DescribeResizeResponse>(request, options);}
public bool hasPassedThroughNonGreedyDecision() { return passedThroughNonGreedyDecision; }
public int end() { throw new System.NotImplementedException(); }
public virtual void traverse(CellHandler handler) {int firstRow = range.getFirstRow();int lastRow = range.getLastRow();int firstColumn = range.getFirstColumn();int lastColumn = range.getLastColumn();int width = lastColumn - firstColumn + 1;SimpleCellWalkContext ctx = new SimpleCellWalkContext();Row currentRow = null;Cell currentCell = null;for (ctx.rowNumber = firstRow; ctx.rowNumber <= lastRow; ++ctx.rowNumber) {currentRow = sheet.getRow(ctx.rowNumber);if (currentRow == null) {continue;}for (ctx.colNumber = firstColumn; ctx.colNumber <= lastColumn; ++ctx.colNumber) {currentCell = currentRow.getCell(ctx.colNumber);if (currentCell == null) {continue;}if (isEmpty(currentCell) && !traverseEmptyCells) {continue;}long rowSize = ArithmeticUtils.mulAndCheck((long)ArithmeticUtils.subAndCheck(ctx.rowNumber, firstRow), (long)width);ctx.ordinalNumber = ArithmeticUtils.addAndCheck(rowSize, (ctx.colNumber - firstColumn + 1));handler.onCell(currentCell, ctx);}}}
public virtual int GetReadIndex() { return pos; }
public virtual int CompareTo(ScoreTerm other) { if (this.boost == other.boost) return other.bytes.CompareTo(this.bytes); else return this.boost.CompareTo(other.boost); }
public int Normalize(char[] s, int len) { for (int i = 0; i < len; i++) { switch (s[i]) { case FARSI_YEH: case YEH_BARREE: s[i] = YEH; break; case KEHEH: s[i] = KAF; break; case HEH_YEH: case HEH_GOAL: s[i] = HEH; break; case HAMZA_ABOVE: len = delete(s, i, len); i--; break; default: break; } } return len; }
public virtual void serialize(LittleEndianOutput @out) { @out.writeShort(_options); }
public DiagnosticErrorListener(bool exactOnly) { this.exactOnly = exactOnly; }
public KeySchemaElement(string attributeName, KeyType keyType) { SetAttributeName(attributeName); SetKeyType(keyType.ToString()); }
public virtual GetAssignmentResponse GetAssignment(GetAssignmentRequest request){var options = new InvokeOptions();options.RequestMarshaller = GetAssignmentRequestMarshaller.Instance;options.ResponseUnmarshaller = GetAssignmentResponseUnmarshaller.Instance;return Invoke<GetAssignmentResponse>(request, options);}
public bool HasObject(AnyObjectId id) { return FindOffset(id) != -1; }
public virtual GroupingSearch SetAllGroups(bool allGroups) { this.allGroups = allGroups; return this; }
public void SetMultiValued(string dimName, bool v) { lock (this) { DimConfig ft; if (!fieldTypes.TryGetValue(dimName, out ft)) { ft = new DimConfig(); fieldTypes[dimName] = ft; } ft.multiValued = v; } }
public int GetCellsVal() {int size = 0;foreach (char c in cells.Keys) {Cell e = At(c);if (e.cmd >= 0) {size++;}}return size;}
public DeleteVoiceConnectorResult DeleteVoiceConnector(DeleteVoiceConnectorRequest request) { request = BeforeClientExecution(request); return ExecuteDeleteVoiceConnector(request); }
public DeleteLifecyclePolicyResult DeleteLifecyclePolicy(DeleteLifecyclePolicyRequest request) { request = BeforeClientExecution(request); return ExecuteDeleteLifecyclePolicy(request); }
