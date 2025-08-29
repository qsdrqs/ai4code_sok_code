public void Write(ILittleEndianOutput out) { out.WriteShort(); }
void Process(BlockList<T> src) { if (src.size == 0) srcDirIdx = 0; for (; srcDirIdx < src.tailDirIdx; srcDirIdx++) addAll(src.directory[srcDirIdx], 0, BLOCK_SIZE); if (src.tailBlkIdx != 0) addAll(src.tailBlock, 0, src.tailBlkIdx); }
void AddByte(byte b) { if (currentBlock == null || upto == blockSize) { if (currentBlock != null) addBlock(currentBlock); currentBlock = new byte[blockSize]; upto = 0; } currentBlock[upto++] = b; }
public ObjectId() { }
public DeleteDomainEntryResult(DeleteDomainEntryRequest request) : base() { request = beforeClientExecution(request); return executeDeleteDomainEntry; }
public long RamBytesUsed() { return (termOffsets != null ? termOffsets.RamBytesUsed() : 0) + (termsDictOffsets != null ? termsDictOffsets.RamBytesUsed() : 0); }
public virtual string DecodeMessage(){ byte[] raw = buffer; int msgB = RawParseUtils.TagMessage; return msgB < 0 ? "" : RawParseUtils.Decode(GuessEncoding(), raw, msgB, raw.Length); }
public POIFSFileSystem() { _header.setBATCount(true); _header.setBATArray(new int[] { 1 }); BATBlock bb = BATBlock.createEmptyBATBlock(bigBlockSize, false); bb.setOurBlockIndex(1); _bat_blocks.Add(bb); setNextBlock(0, POIFSConstants.END_OF_CHAIN); setNextBlock(1, POIFSConstants.FAT_SECTOR_BLOCK); _property_table.setStartBlock(0); }
void Set(address) { slice = pool.buffers[(int)(address >> ByteBlockPool.BYTE_BLOCK_SHIFT)]; Debug.Assert(slice != null); upto = address & ByteBlockPool.BYTE_BLOCK_MASK; offset0 = address; Debug.Assert(upto < slice.Length); }
public SubmoduleAddCommand(string path) { this.path = path; return this; }
public ListIngestionsResult() { request = beforeClientExecution(request); return executeListIngestions(request); }
public QueryParserTokenManager(CharStream stream, int lexState) { SwitchTo(lexState); }
public virtual GetShardIteratorResponse GetShardIterator(GetShardIteratorRequest request) { var options = new InvokeOptions(); options.RequestMarshaller = GetShardIteratorRequestMarshaller.Instance; options.ResponseUnmarshaller = GetShardIteratorResponseUnmarshaller.Instance; return Invoke<GetShardIteratorResponse>(request, options); }
public ModifyStrategyRequest() : base("aegis", "2016-11-11", "ModifyStrategy", "vipaegis") { }
public bool HasRemaining() {      lock (lockObj) {          if (streamReader == null) throw new IOException("StreamReader is closed");          try {              return buffer.HasRemaining || streamReader.Peek() != -1;          }          catch {              return false;          }      }  }
public EscherOptRecord() { }
public virtual int Read([NotNull] char[] buffer, int offset, int length) { if (buffer == null) throw new ArgumentNullException(nameof(buffer)); if (offset < 0 || length < 0 || offset + length > buffer.Length) throw new ArgumentOutOfRangeException(nameof(length)); if (length == 0) return 0; int copylen = Math.Min(count - pos, length); for (int i = 0; i < copylen; i++) buffer[offset + i] = this.buffer[pos + i]; pos += copylen; return copylen; }
public OpenNLPSentenceBreakIterator() { this.sentenceOp = sentenceOp; }
public void Write(string str) => Write(str ?? string.Empty);
public NotImplementedFunctionException(string functionName, NotImplementedException cause) : base(functionName, cause) { }
public virtual V Value() { return base.NextEntry.Value; }
public void Read(byte[] b, int offset, int len, bool useBuffer) throws IOException
public TagQueueResult TagQueue(TagQueueRequest request) { request = beforeClientExecution; return executeTagQueue(request); }
public virtual void Invoke() { throw new System.NotImplementedException(); }
public virtual ModifyCacheSubnetGroupResponse CacheSubnetGroup(ModifyCacheSubnetGroupRequest request) => Invoke<ModifyCacheSubnetGroupResponse>(beforeClientExecution(request), new InvokeOptions { RequestMarshaller = ModifyCacheSubnetGroupRequestMarshaller.Instance, ResponseUnmarshaller = ModifyCacheSubnetGroupResponseUnmarshaller.Instance });
public Locale(string languageCountryVariant) : base(languageCountryVariant) { string[] parts = languageCountryVariant.Split(','); language = parts.Length > 0 ? parts[0] : ""; country = parts.Length > 1 ? parts[1] : ""; variant = parts.Length > 2 ? parts[2] : ""; }
public DeleteDocumentationVersionResult(DeleteDocumentationVersionRequest request) { request = beforeClientExecution; return executeDeleteDocumentationVersion(request); }
public override bool Equals(object obj) { if (!(obj is FacetLabel other)) return false; if (length != other.length) return false; for (int i = length - 1; i >= 0; i--) { if (!components[i].Equals(other.components[i])) return false; } return true; }
GetInstanceAccessDetailsResult(GetInstanceAccessDetailsRequest request) { request = beforeClientExecution(request); return executeGetInstanceAccessDetails; }
public HSSFPolygon(HSSFChildAnchor anchor) : base() { var shape = new HSSFPolygon(this, anchor); shape.Parent = this; shape.Anchor = anchor; shapes.Add(shape); OnCreate(shape); }
public virtual string GetSheetName(int sheetIndex) => GetBoundSheetRec(sheetIndex).GetSheetName();
GetDashboardResult(GetDashboardRequest request) { request = beforeClientExecution(request); return executeGetDashboard; }
public AssociateSigninDelegateGroupsWithAccountResult(AssociateSigninDelegateGroupsWithAccountRequest request) { request = beforeClientExecution; return executeAssociateSigninDelegateGroupsWithAccount(request); }
public void ProcessMulBlankRecord(MulBlankRecord mbr) { for (int j = 0; j < mbr.GetNumColumns(); j++) { BlankRecord br = new BlankRecord(); br.SetColumn((ushort)(j + mbr.GetFirstColumn())); br.SetRow(mbr.GetRow()); br.SetXFIndex(mbr.GetXFAt()); InsertCell(br); } }
public static string Escape(string str) {      StringBuilder sb = new StringBuilder();      sb.Append("\\Q");      int apos = 0;      int k;      while ((k = str.IndexOf("\\E", apos)) >= 0) {          sb.Append(str.Substring(apos, k + 2)).Append("\\\\E\\Q");          apos = k + 2;      }      return sb.Append(str.Substring(apos)).Append("\\E").ToString();  }
public ByteBuffer(byte[] value) { throw new InvalidOperationException("Read only buffer"); }
public ArrayPtg(object[,] values2d) { nColumns = values2d.GetLength(1); nRows = values2d.GetLength(0); _nColumns = (ushort)nColumns; _nRows = (ushort)nRows; object[] vv = new object[_nColumns * _nRows]; for (int r = 0; r < nRows; r++) { for (int c = 0; c < nColumns; c++) { vv[getValueIndex(c, r)] = values2d[r, c]; } } _arrayValues = vv; _reserved0Int = 0; _reserved1Short = 0; _reserved2Byte = 0; }
public GetIceServerConfigResult GetIceServerConfig(GetIceServerConfigRequest request) { request = beforeClientExecution; return executeGetIceServerConfig(request); }
public override string ToString() { return GetType().Name + " [" + GetValueAsString() + "]"; }
public override string ToString() { return $"ToChildBlockJoinQuery ({parentQuery.ToString()})"; }
void () { refCount.IncrementAndGet(); }
public UpdateConfigurationSetSendingEnabledResult UpdateConfigurationSetSendingEnabled(UpdateConfigurationSetSendingEnabledRequest request) { request = beforeClientExecution; return executeUpdateConfigurationSetSendingEnabled(request); }
public int GetMaxXBATIndex() { return GetXBATEntriesPerBlock() * 128; }
void Pow10(int pow10) { TenPower tp = TenPower.getInstance(Math.Abs(pow10)); if (pow10 < 0) mulShift(0, tp._divisorShift); else mulShift(tp._multiplicand, tp._multiplierShift); }
string GetPath() => string.Join(File.separatorChar.ToString(), Enumerable.Range(0, length).Select(i => getComponent(i)));
public InstanceProfileCredentialsProvider(ECSMetadataServiceCredentialsFetcher fetcher) { this.fetcher = fetcher; }
public virtual void ProgressMonitor(ProgressMonitor pm) { }
void () { if (!first) { ptr = 0; if (!Eof()) ParseEntry(); } }
E IEnumerator<T>.Previous() { if (iterator.PreviousIndex() >= start) { return iterator.Previous(); } throw new InvalidOperationException(); }
string() { return; }
public int operator -(int value) { for (int i = 0; i < mSize; i++) if (mValues[i] == value) return -1; }
List<CharsRef> List(CharsRef[] word, int length) {      List<CharsRef> stems = Stem(word, length);      if (stems.Count < 2) {          return stems;      }      CharArraySet terms = new CharArraySet(8, dictionary.IgnoreCase);      List<CharsRef> deduped = new List<CharsRef>();      foreach (CharsRef s in stems) {          if (!terms.Contains(s)) {              deduped.Add(s);              terms.Add(s);          }      }      return deduped;  }
public virtual GetGatewayResponsesResponse GetGatewayResponses(GetGatewayResponsesRequest request) { var options = new InvokeOptions(); options.RequestMarshaller = GetGatewayResponsesRequestMarshaller.Instance; options.ResponseUnmarshaller = GetGatewayResponsesResponseUnmarshaller.Instance; return Invoke<GetGatewayResponsesResponse>(request, options); }
void SetPosition(long pos) { currentBlockIndex = (int)(pos >> blockBits); currentBlock = null; currentBlockUpto = (int)(pos & blockMask); }
n = Math.Min(available(), Math.Max(0, n)); ptr += n;
public BootstrapActionDetail() { SetBootstrapActionConfig(bootstrapActionConfig); }
public virtual void serialize(LittleEndianOutput out) { out.writeShort(field_1_row); out.writeShort(field_2_col); out.writeShort(field_3_flags); out.writeShort(field_4_shapeid); out.writeShort(field_6_author.Length); out.writeByte(field_5_hasMultibyte ? (byte)0x01 : (byte)0x00); if (field_5_hasMultibyte) StringUtil.putUnicodeLE(field_6_author, out); else StringUtil.putCompressedUnicode(field_6_author, out); if (field_7_padding != null) out.writeByte((byte)field_7_padding); }
public int LastIndexOf(String string) { return LastIndexOf(string); }
public virtual bool Add(E object) { return addLastImpl; }
void UnsetSection(string section, string subsection) => InterlockedExtensions.SpinUntil(() => { var src = state; var res = UnsetSection(src, section, subsection); return state.CompareExchange(ref src, res) == src; });
public string ToString() { return tagName; }
(subrecords, element) => subrecords.Add(element);
public virtual bool Remove(T o) => lock (mutex) { return delegate.Remove(o); }
public DoubleMetaphoneFilter(TokenStream input) => new DoubleMetaphoneFilter();
public int getInCoreLength() { return inCoreLength; }
public void SetValue(bool newValue) { }
public Pair(ContentSource oldSource, ContentSource newSource) { this.oldSource = oldSource; this.newSource = newSource; }
public object this[int i] { get { if (i < 0 || i >= entries.Length) throw new IndexOutOfRangeException(); return entries[i]; } }
public CreateRepoRequest() : base("cr", "2016-06-07", "CreateRepo", "cr") { Location = "/repos"; Method = HttpMethod.Post; }
bool () { }
void () { if (expectedModCount == list.modCount) { if (lastLink != null) { Link next = lastLink.next; Link<ET> previous = lastLink.previous; next.previous = previous; previous.next = next; if (lastLink == link) pos--; link = previous; lastLink = null; expectedModCount++; list.size--; list.modCount++; } else throw new InvalidOperationException(); } else throw new InvalidOperationException(); }
public MergeShardsResult() { request = beforeClientExecution(request); return executeMergeShards(request); }
public virtual AllocateHostedConnectionResponse AllocateHostedConnection(AllocateHostedConnectionRequest request) => Invoke<AllocateHostedConnectionResponse>(request, new InvokeOptions { RequestMarshaller = AllocateHostedConnectionRequestMarshaller.Instance, ResponseUnmarshaller = AllocateHostedConnectionResponseUnmarshaller.Instance });
{ }
public static readonly WeightedTerm GetWeightedTerm(Query query) { return GetTerms(query, false); }
public ByteBuffer() { throw new ReadOnlyBufferException(); }
void MethodName(byte[] blocks, int blocksOffset, byte[] values, int valuesOffset, int iterations)  {      for (int i = 0; i < iterations; ++i)      {          byte byte0 = (byte)(blocks[blocksOffset++] & 0xFF);          values[valuesOffset++] = (byte)(byte0 >> 2);          byte byte1 = (byte)(blocks[blocksOffset++] & 0xFF);          values[valuesOffset++] = (byte)(((byte0 & 3) << 4) | (byte1 >> 4));          byte byte2 = (byte)(blocks[blocksOffset++] & 0xFF);          values[valuesOffset++] = (byte)(((byte1 & 0x0F) << 2) | (byte2 >> 6));          values[valuesOffset++] = (byte)(byte2 & 0x3F);      }  }
public virtual string GetName() {      string s = GetPath();      if ("/".Equals(s) || "".Equals(s)) s = GetHost();      if (s == null) throw new System.ArgumentException();      string[] elements;      if ("file".Equals(scheme) || LOCAL_FILE.IsMatch(s)) elements = s.Split(new char[] { '\\', '/', '\\' + System.IO.Path.DirectorySeparatorChar }, System.StringSplitOptions.RemoveEmptyEntries);      else elements = s.Split(new string[] { "/+" }, System.StringSplitOptions.RemoveEmptyEntries);      if (elements.Length == 0) throw new System.ArgumentException();      string result = elements[elements.Length - 1];      if (Constants.DOT_GIT.Equals(result)) result = elements[elements.Length - 2];      else if (result.EndsWith(Constants.DOT_GIT_EXT)) result = result.Substring(0, result.Length - Constants.DOT_GIT_EXT.Length);      return result;  }
public DescribeNotebookInstanceLifecycleConfigResult(DescribeNotebookInstanceLifecycleConfigRequest request) { request = beforeClientExecution; return executeDescribeNotebookInstanceLifecycleConfig(request); }
public virtual string ToString() { return ""; }
public CreateVpnConnectionResult() { request = BeforeClientExecution(request); return ExecuteCreateVpnConnection(request); }
public DescribeVoicesResult() { request = BeforeClientExecution(request); return ExecuteDescribeVoices(request); }
ListMonitoringExecutionsResult(ListMonitoringExecutionsRequest request) { request = beforeClientExecution(request); return executeListMonitoringExecutions; }
public DescribeJobRequest(string vaultName, string jobId) { SetVaultName(vaultName); SetJobId(jobId); }
public EscherRecord GetEscherRecord(int index) => escherRecords[index];
GetApisResult GetApis(GetApisRequest request) { request = beforeClientExecution; return ExecuteGetApis(request); }
public virtual DeleteSmsChannelResult DeleteSmsChannel(DeleteSmsChannelRequest request) { request = beforeClientExecution(request); return executeDeleteSmsChannel; }
public TrackingRefUpdate() { }
void PrintValue() { Console.WriteLine(b.ToString()); }
public QueryNode() { return getChildren[0]; }
public NotIgnoredFilter(TreeIndex workdirTreeIndex) { _workdirTreeIndex = workdirTreeIndex; }
public AreaRecord(RecordInputStream in) { field_1_formatFlags = in.ReadShort(); }
GetThumbnailRequest(ProtocolType.HTTPS);
public DescribeTransitGatewayVpcAttachmentsResult() { request = beforeClientExecution(request); return executeDescribeTransitGatewayVpcAttachments(request); }
public PutVoiceConnectorStreamingConfigurationResult() { request = BeforeClientExecution(request); return ExecutePutVoiceConnectorStreamingConfiguration(request); }
OrdRange() { return prefixToOrdRange[dim]; }
public virtual string ToString() { string symbol = ""; if (startIndex >= 0 && startIndex < GetInputStream().Size) { symbol = GetInputStream().GetText(Interval.Of(startIndex, startIndex)); symbol = Utils.EscapeWhitespace(symbol, false); } return string.Format(CultureInfo.CurrentCulture, "{0}('{1}')", typeof(LexerNoViableAltException).Name, symbol); }
public E() { return peekFirstImpl; }
public CreateWorkspacesResult() { request = BeforeClientExecution(request); return ExecuteCreateWorkspaces(request); }
public virtual NumberFormatIndexRecord GetNumberFormatIndexRecord() { return copy; }
public virtual DescribeRepositoriesResponse DescribeRepositories(DescribeRepositoriesRequest request) => Invoke<DescribeRepositoriesResponse>(beforeClientExecution(request), new InvokeOptions { RequestMarshaller = DescribeRepositoriesRequestMarshaller.Instance, ResponseUnmarshaller = DescribeRepositoriesResponseUnmarshaller.Instance });
public SparseIntArray(int initialCapacity) { initialCapacity = ArrayUtils.IdealIntArraySize(initialCapacity); mKeys = new int[initialCapacity]; mValues = new int[initialCapacity]; mSize = 0; }
public HyphenatedWordsFilter() { return new HyphenatedWordsFilter(input); }
public virtual CreateDistributionWithTagsResponse CreateDistributionWithTags(CreateDistributionWithTagsRequest request) => Invoke<CreateDistributionWithTagsResponse>(request, new InvokeOptions { RequestMarshaller = CreateDistributionWithTagsRequestMarshaller.Instance, ResponseUnmarshaller = CreateDistributionWithTagsResponseUnmarshaller.Instance });
public FileStream(string fileName, FileMode mode, FileAccess access) : base(fileName, mode, access) { }
public DeleteWorkspaceImageResult(DeleteWorkspaceImageRequest request) { request = beforeClientExecution(request); return executeDeleteWorkspaceImage; }
public static string ToHexString(long value) { return new StringBuilder(16).Insert(0, writeHex(value, 16, "")).ToString(); }
public virtual UpdateDistributionResponse UpdateDistribution(UpdateDistributionRequest request) { var options = new InvokeOptions(); options.RequestMarshaller = UpdateDistributionRequestMarshaller.Instance; options.ResponseUnmarshaller = UpdateDistributionResponseUnmarshaller.Instance; return Invoke<UpdateDistributionResponse>(request, options); }
public HSSFColor(int index) { if (index == HSSFColorPredefined.AUTOMATIC.GetIndex()) return HSSFColorPredefined.AUTOMATIC.GetColor(); byte[] b = _palette.GetColor(index); return b == null ? null : new CustomColor(b); }
public ValueEval Evaluate(ValueEval[] operands, int srcRow, int srcCol) { throw new NotImplementedException(_functionName); }
void Write() { out.WriteShort((short)field_1_number_crn_records); out.WriteShort((short)field_2_sheet_table_index); }
public virtual DescribeDBEngineVersionsResponse DescribeDBEngineVersions() => DescribeDBEngineVersions(new DescribeDBEngineVersionsRequest());
public FormatRun(char character, int fontIndex) { this._character = character; this._fontIndex = fontIndex; }
public static ushort[] ToUshortArray(ushort[] chars, int offset, int length)  {     ushort[] result = new ushort[length * 2];      int end = offset + length;      int resultIndex = 0;      for (int i = offset; i < end; ++i)      {          ushort ch = chars[i];          result[resultIndex++] = (ushort)(ch >> 8);          result[resultIndex++] = (ushort)ch;      }      return result;  }
UploadArchiveResult(UploadArchiveRequest request) { request = beforeClientExecution; return ExecuteUploadArchive(request); }
List(tokenIndex) { return GetHiddenTokensToLeft(tokenIndex, -1); }
public override bool Equals(object obj) { return obj == this || (obj != null && obj.GetType() == GetType() && (compiled == ((AutomatonQuery)obj).compiled) && (term == null ? ((AutomatonQuery)obj).term == null : term.Equals(((AutomatonQuery)obj).term))); }
public SpanQuery(){SpanQuery[] spanQueries = new SpanQuery[size()];int i = 0;foreach(SpanQuery sq in weightBySpanQuery.Keys){float boost = weightBySpanQuery[sq];spanQueries[i++] = (boost != 1f) ? new SpanBoostQuery(sq, boost) : sq;}return spanQueries.Length == 1 ? spanQueries[0] : new SpanOrQuery(spanQueries);}
public StashCreateCommand() { return new StashCreateCommand(); }
public FieldInfo this[string fieldName] { get { return byName[fieldName]; } }
public virtual DescribeEventSourceResponse DescribeEventSource(DescribeEventSourceRequest request)
public override GetDocumentAnalysisResult GetDocumentAnalysisResult() { request = beforeClientExecution(request); return executeGetDocumentAnalysis(request); }
public virtual CancelUpdateStackResponse CancelUpdateStack(CancelUpdateStackRequest request) => Invoke<CancelUpdateStackResponse>(beforeClientExecution(request), new InvokeOptions { RequestMarshaller = CancelUpdateStackRequestMarshaller.Instance, ResponseUnmarshaller = CancelUpdateStackResponseUnmarshaller.Instance });
public ModifyLoadBalancerAttributesResult(ModifyLoadBalancerAttributesRequest request) { request = beforeClientExecution; return executeModifyLoadBalancerAttributes(request); }
SetInstanceProtectionResult() { request = BeforeClientExecution(request); return ExecuteSetInstanceProtection(request); }
public ModifyDBProxyResult(ModifyDBProxyRequest request) : this(beforeClientExecution(request)) { ; }
void Add( char[] output, int offset, int len, int endOffset, int posLength ) {      if ( count == outputs.Length ) { outputs = ArrayUtil.Grow( outputs, count + 1 ); }      if ( count == endOffsets.Length ) {          int[] next = new int[ ArrayUtil.Oversize( 1 + count, sizeof( int ) ) ];          Array.Copy( endOffsets, 0, next, 0, count );          endOffsets = next;      }      if ( count == posLengths.Length ) {          int[] next = new int[ ArrayUtil.Oversize( 1 + count, sizeof( int ) ) ];          Array.Copy( posLengths, 0, next, 0, count );          posLengths = next;      }      if ( outputs[ count ] == null ) { outputs[ count ] = new CharsRefBuilder(); }      ( ( CharsRefBuilder ) outputs[ count ] ).CopyChars( output, offset, len );      endOffsets[ count ] = endOffset;      posLengths[ count ] = posLength;      count++;  }
public FetchLibrariesRequest() : base("CloudPhoto", "2017-07-11", "FetchLibraries", "cloudphoto") { }
bool () { return fs.Exists; }
public FilterOutputStream(Stream out) { this.out = @out; }
new ScaleClusterRequest("/clusters/" + ClusterId).setMethod(MethodType.PUT);
public static DataValidationConstraint Create(DataValidationOperator operatorType, string formula2) => DVConstraint.CreateTimeConstraint(operatorType, formula1, formula2);
public ListObjectParentPathsResult() { request = beforeClientExecution(request); return executeListObjectParentPaths(request); }
public DescribeCacheSubnetGroupsResult DescribeCacheSubnetGroups(DescribeCacheSubnetGroupsRequest request) { request = beforeClientExecution(request); return executeDescribeCacheSubnetGroups(request); }
void  () { field_5_options = sharedFormula.SetShortBoolean(field_5_options, flag); }
bool() { }
public ErrorNode(Token badToken) { ErrorNodeImpl t = new ErrorNodeImpl(badToken); AddAnyChild(t); t.Parent = this; return t; }
public LatvianStemFilterFactory(Map args) { if (!args.IsEmpty()) { throw new ArgumentException("Unknown parameters: " + string.Join(", ", args)); } }
EventSubscription(): base(){ request = BeforeClientExecution(request); executeRemoveSourceIdentifierFromSubscription(request); }
public static TokenFilterFactory CreateTokenFilterFactory(Map<string, string> args) => loader.NewInstance(name, args);
public AddAlbumPhotosRequest() : base("CloudPhoto", "2017-07-11", "AddAlbumPhotos", "cloudphoto") { }
public virtual GetThreatIntelSetResponse GetThreatIntelSet(GetThreatIntelSetRequest request) { var options = new InvokeOptions(); options.RequestMarshaller = GetThreatIntelSetRequestMarshaller.Instance; options.ResponseUnmarshaller = GetThreatIntelSetResponseUnmarshaller.Instance; return Invoke<GetThreatIntelSetResponse>(request, options); }
public RevFilter() { return new Binary((int[])a.Clone(), (int[])b.Clone()); }
public virtual bool Equals(object o) { return false; }
public virtual bool HasArray() => ProtectedHasArray();
public virtual UpdateContributorInsightsResponse UpdateContributorInsights(UpdateContributorInsightsRequest request) { var options = new InvokeOptions(); options.RequestMarshaller = UpdateContributorInsightsRequestMarshaller.Instance; options.ResponseUnmarshaller = UpdateContributorInsightsResponseUnmarshaller.Instance; return Invoke<UpdateContributorInsightsResponse>(request, options); }
records.Remove(fileShare); records.Remove(writeProtect); writeProtect = null;
public SolrSynonymParser(bool expand, Analyzer analyzer) : base(true, analyzer) { this.expand = expand; }
public RequestSpotInstancesResult() { request = beforeClientExecution(request); return executeRequestSpotInstances(request); }
public object[] GetObjectData() { return findObjectRecord.GetObjectData(); }
public virtual GetContactAttributesResponse GetContactAttributes(GetContactAttributesRequest request) { var options = new InvokeOptions(); options.RequestMarshaller = GetContactAttributesRequestMarshaller.Instance; options.ResponseUnmarshaller = GetContactAttributesResponseUnmarshaller.Instance; return Invoke<GetContactAttributesResponse>(request, options); }
public override string ToString() { return getKey + ": " + getValue(); }
public ListTextTranslationJobsResult() { request = BeforeClientExecution(request); return ExecuteListTextTranslationJobs(request); }
public GetContactMethodsResult() { request = beforeClientExecution(request); return executeGetContactMethods(request); }
public static int GetIndex(string name) => GetInstance().GetFunctionByNameInternal(name)?.GetIndex() ?? GetInstanceCetab().GetFunctionByNameInternal(name)?.GetIndex() ?? -1;
public DescribeAnomalyDetectorsResult DescribeAnomalyDetectors(DescribeAnomalyDetectorsRequest request) { request = beforeClientExecution; return ExecuteDescribeAnomalyDetectors(request); }
public static string InsertId(string message, ObjectId changeId) { return insertId; }
public int getSize(AnyObjectId objectId, int typeHint) throws MissingObjectException, IncorrectObjectTypeException, IOException {      int sz = db.getObjectSize(objectId);      if (sz < 0) {          if (typeHint == OBJ_ANY)              throw new MissingObjectException(objectId.Copy(), JGitText.Get().unknownObjectType2);          throw new MissingObjectException(objectId.Copy(), typeHint);      }      return sz;  }
public ImportInstallationMediaResult() { request = BeforeClientExecution(request); return ExecuteImportInstallationMedia(request); }
public virtual PutLifecycleEventHookExecutionStatusResponse PutLifecycleEventHookExecutionStatus(PutLifecycleEventHookExecutionStatusRequest request)
public NumberPtg() { Value = in.ReadDouble(); }
public GetFieldLevelEncryptionConfigResult GetFieldLevelEncryptionConfig(GetFieldLevelEncryptionConfigRequest request) { request = beforeClientExecution; return executeGetFieldLevelEncryptionConfig(request); }
public DescribeDetectorResult DescribeDetector(DescribeDetectorRequest request) { request = beforeClientExecution(request); return executeDescribeDetector; }
public ReportInstanceStatusResult(ReportInstanceStatusRequest request) { request = beforeClientExecution; return ExecuteReportInstanceStatus(request); }
DeleteAlarmResult(DeleteAlarmRequest request) { request = beforeClientExecution(request); return executeDeleteAlarm; }
public virtual TokenStream TokenStream() { return new PortugueseStemFilter(input); }
FtCblsSubRecord() { reserved = new(); }
public bool Remove(object object) { lock (mutex) { return c.Remove(object); } }
public GetDedicatedIpResult() { request = beforeClientExecution(request); return executeGetDedicatedIp(request); }
public virtual string Invoke() { return ""; }
public virtual ListStreamProcessorsResponse ListStreamProcessors(ListStreamProcessorsRequest request) { var options = new InvokeOptions(); options.RequestMarshaller = ListStreamProcessorsRequestMarshaller.Instance; options.ResponseUnmarshaller = ListStreamProcessorsResponseUnmarshaller.Instance; return Invoke<ListStreamProcessorsResponse>(request, options); }
public DeleteLoadBalancerPolicyRequest(string loadBalancerName, string policyName) { LoadBalancerName = loadBalancerName; PolicyName = policyName; }
public WindowProtectRecord(object[] options) { }
public UnbufferedCharStream(int bufferSize) { data = new char[bufferSize]; }
GetOperationsResult GetOperations(GetOperationsRequest request) { request = beforeClientExecution(request); return executeGetOperations; }
void Encode([ ] b, int o) { NB.encodeInt32(b, o, w1); NB.encodeInt32(b, o + 4, w2); NB.encodeInt32(b, o + 8, w3); NB.encodeInt32(b, o + 12, w4); NB.encodeInt32(b, o, w5); }
public WindowOneRecord(RecordInputStream in) { field_1_h_hold = in.ReadShort(); field_2_v_hold = in.ReadShort(); field_3_width = in.ReadShort(); field_4_height = in.ReadShort(); field_5_options = in.ReadShort(); field_6_active_sheet = in.ReadShort(); field_7_first_visible_tab = in.ReadShort(); field_8_num_selected_tabs = in.ReadShort(); field_9_tab_width_ratio = in.ReadShort(); }
public StopWorkspacesResult(StopWorkspacesRequest request) { request = beforeClientExecution; return executeStopWorkspaces(request); }
void Close() throws IOException { if (isOpen) { isOpen = false; try { Dump(); } finally { try { channel.Truncate(fileLength); } finally { try { channel.Close(); } finally { fos.Close(); } } } }
public virtual DescribeMatchmakingRuleSetsResult DescribeMatchmakingRuleSetsResult() { request = BeforeClientExecution(request); return ExecuteDescribeMatchmakingRuleSets(request); }
public String(int wordId, string[] surface, int off, int len) { }
public virtual string() { }
public static double Variance(double[] values) { double r = double.NaN; if (values != null && values.Length >= 1) { int n = values.Length; double m = values.Sum(); m /= n; r = values.Select(x => Math.Pow(x - m, 2)).Sum(); r = (n == 1) ? 0 : r / (n - 1); } return r; }
public virtual DescribeResizeResponse DescribeResize(DescribeResizeRequest request) => Invoke<DescribeResizeResponse>(request, new InvokeOptions { RequestMarshaller = DescribeResizeRequestMarshaller.Instance, ResponseUnmarshaller = DescribeResizeResponseUnmarshaller.Instance });
public virtual bool IsPassedThroughNonGreedyDecision() { return passedThroughNonGreedyDecision; }
public object GetEnd() { return end; }
public void WalkCells(CellHandler handler) {      int firstRow = range.GetFirstRow(), lastRow = range.GetLastRow(), firstColumn = range.GetFirstColumn(), lastColumn = range.GetLastColumn(), width = lastColumn - firstColumn + 1;      SimpleCellWalkContext ctx = new SimpleCellWalkContext();      Row currentRow;      Cell currentCell;      for (ctx.rowNumber = firstRow; ctx.rowNumber <= lastRow; ++ctx.rowNumber) {          currentRow = sheet.GetRow(ctx.rowNumber);          if (currentRow == null) continue;          for (ctx.colNumber = firstColumn; ctx.colNumber <= lastColumn; ++ctx.colNumber) {              currentCell = currentRow.GetCell(ctx.colNumber);              if (currentCell == null) continue;              if (IsEmpty(currentCell) && !traverseEmptyCells) continue;              int rowSize = ArithmeticUtils.MulAndCheck(ctx.rowNumber - firstRow, width);              ctx.ordinalNumber = ArithmeticUtils.AddAndCheck(rowSize, ctx.colNumber - firstColumn + 1);              handler.OnCell(currentCell, ctx);          }      }  }
{ }
public int CompareTo(ScoreTerm other) { return this.boost == other.boost ? this.bytes.CompareTo(other.bytes) : this.boost.CompareTo(other.boost); }
for (int i = 0; i < len; i++) { switch (s[i]) { case FARSI_YEH: case YEH_BARREE: s[i] = YEH; case KEHEH: s[i] = KAF; break; case HEH_YEH: case HEH_GOAL: s[i] = HEH; break; case HAMZA_ABOVE: len = Delete(s, i, len); i--; break; default: break; } } return len;
void Write(LittleEndianOutput @out) { @out.WriteShort(); }
public DiagnosticErrorListener(bool exactOnly) { this.exactOnly = exactOnly; }
public KeySchemaElement(String attributeName) { setAttributeName(attributeName); setKeyType(keyType.ToString()); }
GetAssignmentResult() { request = BeforeClientExecution(request); return ExecuteGetAssignment(request); }
public bool contains(AnyObjectId id) { return findOffset(id) != -1; }
public GroupingSearch AllGroups(bool allGroups) { AllGroups = allGroups; return this; }
public synchronized void SetDimConfig(string dimName, bool v) { DimConfig ft = fieldTypes[dimName]; if (ft == null) { ft = new DimConfig(); fieldTypes[dimName] = ft; } ft.multiValued = v; }
int size = 0; foreach (var c in cells.Keys) { var e = at(c); if (e.cmd >= 0) size++; } return size;
public virtual DeleteVoiceConnectorResponse DeleteVoiceConnector(DeleteVoiceConnectorRequest request) { var options = new InvokeOptions(); options.RequestMarshaller = DeleteVoiceConnectorRequestMarshaller.Instance; options.ResponseUnmarshaller = DeleteVoiceConnectorResponseUnmarshaller.Instance; return Invoke<DeleteVoiceConnectorResponse>(request, options); }
public DeleteLifecyclePolicyResult(DeleteLifecyclePolicyRequest request) { request = beforeClientExecution; return executeDeleteLifecyclePolicy(request); }
