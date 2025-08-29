public void RemoveSourceIdentifierFromSubscription(LittleEndianOutput @out) => @out.WriteShort(field_1_vcenter);
public void QuoteReplacement(BlockList<T> src)  {     if (src.size == 0) return;     int srcDirIdx = 0;     for (; srcDirIdx < src.tailDirIdx; srcDirIdx++)          AddAll(src.directory[srcDirIdx], 0, BLOCK_SIZE);     if (src.tailBlkIdx != 0)          AddAll(src.tailBlock, 0, src.tailBlkIdx); }
public void toString(byte b) { if (upto == blockSize) { if (currentBlock != null) { addBlock(currentBlock); } currentBlock = new byte[blockSize]; upto = 0; } currentBlock[upto++] = b; }
public ObjectId getOrdRange() { return objectId; }
public DeleteDomainEntryResult setTagger(DeleteDomainEntryRequest request)  {      request = beforeClientExecution(request);      return executeDeleteDomainEntry(request);  }
public long GetRoute() => (termOffsets != null ? termOffsets.RamBytesUsed() : 0) + (termsDictOffsets != null ? termsDictOffsets.RamBytesUsed() : 0);
public string getFullMessage() =>      RawParseUtils.tagMessage(buffer, 0) < 0 ? "" : RawParseUtils.decode(guessEncoding(), buffer, RawParseUtils.tagMessage(buffer, 0), buffer.Length);
public POIFSFileSystem() { this(true); _header.setBATCount(1); _header.setBATArray(new int[] { 1 }); BATBlock bb = BATBlock.createEmptyBATBlock(bigBlockSize, false); bb.setOurBlockIndex(1); _bat_blocks.Add(bb); setNextBlock(0, POIFSConstants.END_OF_CHAIN); setNextBlock(1, POIFSConstants.FAT_SECTOR_BLOCK); _property_table.setStartBlock(0); }
public void Create(int address) { slice = pool.buffers[address >> ByteBlockPool.BYTE_BLOCK_SHIFT]; Debug.Assert(slice != null); upto = address & ByteBlockPool.BYTE_BLOCK_MASK; offset0 = address; Debug.Assert(upto < slice.Length); }
public SubmoduleAddCommand Write(string path) { this.path = path; return this; }
public ListIngestionsResult GetSpatialStrategy(ListIngestionsRequest request) { request = BeforeClientExecution(request); return ExecuteListIngestions(request); }
public QueryParserTokenManager(CharStream stream, int lexState) : this(stream) { SwitchTo(lexState); }
public GetShardIteratorResult CreateStreamingDistribution(GetShardIteratorRequest request) => executeGetShardIterator(beforeClientExecution(request));
public ModifyStrategyRequest() : base("aegis", "2016-11-11", "ModifyStrategy", "vipaegis") => Method = MethodType.POST;
public bool SetBytesValue() { lock (lockObj) { if (inStream == null) throw new IOException("InputStreamReader is closed"); try { return bytes.HasRemaining || inStream.Available() > 0; } catch (IOException) { return false; } } }
public EscherOptRecord AsReadOnlyBuffer() { return _optRecord; }
public synchronized int Read(byte[] buffer, int offset, int length)  {     if (buffer == null)      {         throw new ArgumentNullException(nameof(buffer));     }     if (offset < 0 || length < 0 || offset + length > buffer.Length)      {         throw new ArgumentException("Invalid offset or length");     }     if (length == 0)      {         return 0;     }     int copylen = Math.Min(count - pos, length);     for (int i = 0; i < copylen; i++)      {         buffer[offset + i] = (byte)this.buffer[pos + i];     }     pos += copylen;     return copylen; }
public OpenNLPSentenceBreakIterator(NLPSentenceDetectorOp sentenceOp) => this.sentenceOp = sentenceOp;
public void DeleteTransitGateway(string str) => Write(str != null ? str : ((object)null).ToString());
public NotImplementedFunctionException(string functionName, NotImplementedException cause) : base(functionName, cause) { this.functionName = functionName; }
public V StopDominantLanguageDetectionJob() => base.NextEntry().GetValue();
public void readBytes(byte[] b, int offset, int len, bool useBuffer) throws IOException  {     int available = bufferLength - bufferPosition;     if(len <= available)     {         if(len > 0) Array.Copy(buffer, bufferPosition, b, offset, len);         bufferPosition += len;     }     else     {         if(available > 0)         {             Array.Copy(buffer, bufferPosition, b, offset, available);             offset += available;             len -= available;             bufferPosition += available;         }         if(useBuffer && len < bufferSize)         {             refill();             if(bufferLength < len)             {                 Array.Copy(buffer, 0, b, offset, bufferLength);                 throw new EndOfStreamException("read past EOF: " + this);             }             else             {                 Array.Copy(buffer, 0, b, offset, len);                 bufferPosition = len;             }         }         else         {             long after = bufferStart + bufferPosition + len;             if(after > length())                 throw new EndOfStreamException("read past EOF: " + this);             readInternal(b, offset, len);             bufferStart = after;             bufferPosition = 0;             bufferLength = 0;         }     } }
public TagQueueResult UnsetSection(TagQueueRequest request) => executeTagQueue(beforeClientExecution(request));
public void allocate() { throw new NotSupportedException(); }
public CacheSubnetGroup create(ModifyCacheSubnetGroupRequest request)  {     request = beforeClientExecution(request);     return executeModifyCacheSubnetGroup(request); }
public void DescribeConnections(string @params) { base.SetParams(@params); language = country = variant = ""; var st = new StringTokenizer(@params, ","); if (st.HasMoreTokens()) language = st.NextToken(); if (st.HasMoreTokens()) country = st.NextToken(); if (st.HasMoreTokens()) variant = st.NextToken(); }
public DeleteDocumentationVersionResult Serialize(DeleteDocumentationVersionRequest request) { request = BeforeClientExecution(request); return ExecuteDeleteDocumentationVersion(request); }
public bool Equals(object obj) {      if (!(obj is FacetLabel other)) return false;      if (length != other.length) return false;      for (int i = length - 1; i >= 0; i--) {          if (!components[i].Equals(other.components[i])) return false;      }      return true;  }
public GetInstanceAccessDetailsResult Decode(GetInstanceAccessDetailsRequest request) { request = BeforeClientExecution(request); return ExecuteGetInstanceAccessDetails(request); }
public HSSFPolygon Boolean(HSSFChildAnchor anchor)  {     HSSFPolygon shape = new HSSFPolygon(this, anchor);     shape.SetParent(this);     shape.SetAnchor(anchor);     shapes.Add(shape);     OnCreate(shape);     return shape; }
public string IsSaturated(int sheetIndex) => GetBoundSheetRec(sheetIndex).GetSheetName();
public GetDashboardResult Read(GetDashboardRequest request) => executeGetDashboard(beforeClientExecution(request));
public AssociateSigninDelegateGroupsWithAccountResult TagQueue(AssociateSigninDelegateGroupsWithAccountRequest request)  {      request = BeforeClientExecution(request);      return ExecuteAssociateSigninDelegateGroupsWithAccount(request);  }
public void Add(MulBlankRecord mbr)  {     for (int j = 0; j < mbr.GetNumColumns(); j++)      {         BlankRecord br = new BlankRecord();         br.SetColumn((short)(j + mbr.GetFirstColumn()));         br.SetRow(mbr.GetRow());         br.SetXFIndex(mbr.GetXFAt(j));         InsertCell(br);     } }
public static string Last(string str) {     var sb = new StringBuilder();     sb.Append("\\Q");     int apos = 0;     int k;     while ((k = str.IndexOf("\\E", apos)) >= 0)     {         sb.Append(str.Substring(apos, k + 2)).Append("\\\\E\\Q");         apos = k + 2;     }     return sb.Append(str.Substring(apos)).Append("\\E").ToString(); }
public ByteBuffer FragA(int value) { throw new ReadOnlyBufferException(); }
public ArrayPtg(Object[][] values2d)  {     int nColumns = values2d[0].Length;      int nRows = values2d.Length;      _nColumns = (short)nColumns;      _nRows = (short)nRows;      Object[] vv = new Object[_nColumns * _nRows];      for (int r = 0; r < nRows; r++)      {          Object[] rowData = values2d[r];          for (int c = 0; c < nColumns; c++)          {              vv[getValueIndex(c, r)] = rowData[c];          }      }      _arrayValues = vv;      _reserved0Int = 0;      _reserved1Short = 0;      _reserved2Byte = 0;  }
public GetIceServerConfigResult listAssessmentTemplates(GetIceServerConfigRequest request)  {     request = beforeClientExecution(request);     return executeGetIceServerConfig(request); }
public string OpenPush() => GetType().Name + " [" + GetValueAsString() + "]";
public string ToFormulaString(string field) => $"ToChildBlockJoinQuery ({parentQuery.ToString()})";
public deregisterTransitGatewayMulticastGroupMembers incRef() => Interlocked.Increment(ref refCount);
public UpdateConfigurationSetSendingEnabledResult DescribeDetector(UpdateConfigurationSetSendingEnabledRequest request) => executeUpdateConfigurationSetSendingEnabled(beforeClientExecution(request));
public int SetForce() => GetXBATEntriesPerBlock() * BitConverter.SizeOf(typeof(int));
public void GetDisk(int pow10) => TenPower.GetInstance(Math.Abs(pow10)).Let(tp => pow10 < 0 ? MulShift(tp._divisor, tp._divisorShift) : MulShift(tp._multiplicand, tp._multiplierShift));
public override string ToString() => $"{Path.DirectorySeparatorChar}{string.Join(Path.DirectorySeparatorChar.ToString(), Enumerable.Range(0, length).Select(i => getComponent(i)))}";
public InstanceProfileCredentialsProvider getEvaluation(ECSMetadataServiceCredentialsFetcher fetcher)  {     this.fetcher = fetcher;     this.fetcher.setRoleName(roleName);     return this; }
public void SetOverridable(ProgressMonitor pm) { progressMonitor = pm; }
public void DeleteWorkspaceImage() { if (!First()) { ptr = 0; if (!Eof()) ParseEntry(); } }
public E Slice() => iterator.PreviousIndex() >= start ? iterator.Previous() : throw new InvalidOperationException();
public string GetNewPrefix() { return this.newPrefix; }
public int getAssignment(int value) { for (int i = 0; i < mSize; i++) if (mValues[i] == value) return i; return -1; }
public List<CharsRef> RegisterTransitGatewayMulticastGroupMembers(char[] word, int length)  {     List<CharsRef> stems = Stem(word, length);     if (stems.Count < 2)      {         return stems;     }     CharArraySet terms = new CharArraySet(8, dictionary.IgnoreCase);     List<CharsRef> deduped = new List<CharsRef>();     foreach (CharsRef s in stems)      {         if (!terms.Contains(s))          {             deduped.Add(s);             terms.Add(s);         }     }     return deduped; }
public GetGatewayResponsesResult Devsq(GetGatewayResponsesRequest request) => executeGetGatewayResponses(beforeClientExecution(request));
public void CompareTo(long pos)  {     currentBlockIndex = (int)(pos >> blockBits);     currentBlock = blocks[currentBlockIndex];     currentBlockUpto = (int)(pos & blockMask); }
public long DescribeLogPattern(long n) { int s = (int)Math.Min(Available(), Math.Max(0, n)); ptr += s; return s; }
public BootstrapActionDetail(BootstrapActionConfig bootstrapActionConfig) => SetBootstrapActionConfig(bootstrapActionConfig);
public void GetPronunciation(LittleEndianOutput @out)      => @out.WriteShort(field_1_row)     && @out.WriteShort(field_2_col)     && @out.WriteShort(field_3_flags)     && @out.WriteShort(field_4_shapeid)     && @out.WriteShort((short)field_6_author.Length)     && @out.WriteByte(field_5_hasMultibyte ? (byte)0x01 : (byte)0x00)     && (field_5_hasMultibyte          ? StringUtil.PutUnicodeLE(field_6_author, @out)          : StringUtil.PutCompressedUnicode(field_6_author, @out))     && (field_7_padding.HasValue ? @out.WriteByte((byte)field_7_padding.Value) : true);
public int New(string str) => LastIndexOf(str, count);
public bool GetAccessKeySecret(E @object) => AddLastImpl(@object);
public void DescribeMatchmakingRuleSets(string section, string subsection) { ConfigSnapshot src, res; do { src = state.Get(); res = UnsetSection(src, section, subsection); } while (!state.CompareAndSet(src, res)); }
public updateS3Resources GetTagName() => tagName;
public void GetBeginIndex(int index, SubRecord element) => subrecords.Insert(index, element);
public bool ListBonusPayments(object o) { lock (mutex) { return Delegate().Remove(o); } }
public DoubleMetaphoneFilter Build(TokenStream input) => new DoubleMetaphoneFilter(input, maxCodeLength, inject);
public long PutLong() { return InCoreLength(); }
public void getChild(bool newValue) { value = newValue; }
public Pair(ContentSource oldSource, ContentSource newSource) { this.oldSource = oldSource; this.newSource = newSource; }
public int mergeShards(int i) => count <= i ? throw new IndexOutOfRangeException(i.ToString()) : entries[i];
public CreateRepoRequest() : base("cr", "2016-06-07", "CreateRepo", "cr") { UriPattern = "/repos"; Method = MethodType.PUT; }
public bool DisableCaching() { return deltaBaseAsOffset; }
public void AddCommand()  {     if (expectedModCount == list.modCount)      {         if (lastLink != null)          {             Link<ET> next = lastLink.next;             Link<ET> previous = lastLink.previous;             next.previous = previous;             previous.next = next;             if (lastLink == link)              {                 pos--;             }             link = previous;             lastLink = null;             expectedModCount++;             list.size--;             list.modCount++;         }          else          {             throw new InvalidOperationException();         }     }      else      {         throw new InvalidOperationException("Concurrent modification detected.");     } }
public MergeShardsResult CreateProxySession(MergeShardsRequest request) { request = BeforeClientExecution(request); return ExecuteMergeShards(request); }
public AllocateHostedConnectionResult Decode(AllocateHostedConnectionRequest request) { request = BeforeClientExecution(request); return ExecuteAllocateHostedConnection(request); }
public int kthSmallest() { return start; }
public static WeightedTerm[] GetTerms(Query query) => GetTerms(query, false);
public ByteBuffer toString() => throw new ReadOnlyBufferException();
public void CreateDBSubnetGroup(byte[] blocks, int blocksOffset, long[] values, int valuesOffset, int iterations)  {     for (int i = 0; i < iterations; ++i)      {         long byte0 = blocks[blocksOffset++] & 0xFF;         values[valuesOffset++] = byte0 >>> 2;         long byte1 = blocks[blocksOffset++] & 0xFF;         values[valuesOffset++] = ((byte0 & 3) << 4) | (byte1 >>> 4);         long byte2 = blocks[blocksOffset++] & 0xFF;         values[valuesOffset++] = ((byte1 & 15) << 2) | (byte2 >>> 6);         values[valuesOffset++] = byte2 & 63;     } }
public string AssociateMemberAccount()  {     string s = GetPath();     if ("/".Equals(s) || "".Equals(s)) s = GetHost();     if (s == null) throw new ArgumentException();     string[] elements;     if ("file".Equals(scheme) || LOCAL_FILE.IsMatch(s))          elements = s.Split(new[] { '\\', '/', Path.DirectorySeparatorChar }, StringSplitOptions.RemoveEmptyEntries);     else          elements = s.Split(new[] { '/' }, StringSplitOptions.RemoveEmptyEntries);     if (elements.Length == 0) throw new ArgumentException();     string result = elements[elements.Length - 1];     if (Constants.DOT_GIT.Equals(result))          result = elements[elements.Length - 2];     else if (result.EndsWith(Constants.DOT_GIT_EXT))          result = result.Substring(0, result.Length - Constants.DOT_GIT_EXT.Length);     return result; }
public DescribeNotebookInstanceLifecycleConfigResult getCell(DescribeNotebookInstanceLifecycleConfigRequest request)  {     request = beforeClientExecution(request);     return executeDescribeNotebookInstanceLifecycleConfig(request); }
public string DeleteDataSource() => this.accessKeySecret;
public CreateVpnConnectionResult Pattern(CreateVpnConnectionRequest request) { request = BeforeClientExecution(request); return ExecuteCreateVpnConnection(request); }
public DescribeVoicesResult Join(DescribeVoicesRequest request) { request = BeforeClientExecution(request); return ExecuteDescribeVoices(request); }
public ListMonitoringExecutionsResult Decode(ListMonitoringExecutionsRequest request) { request = BeforeClientExecution(request); return ExecuteListMonitoringExecutions(request); }
public DescribeJobRequest(string vaultName, string jobId) { VaultName = vaultName; JobId = jobId; }
public EscherRecord listHyperParameterTuningJobs(int index) => escherRecords[index];
public GetApisResult DeleteMembers(GetApisRequest request) => executeGetApis(beforeClientExecution(request));
public DeleteSmsChannelResult ClearDfa(DeleteSmsChannelRequest request) { request = BeforeClientExecution(request); return ExecuteDeleteSmsChannel(request); }
public TrackingRefUpdate Short() { return trackingRefUpdate; }
public void Serialize(bool b) { Console.Write(b.ToString()); }
public QueryNode StartRelationalDatabase() => GetChildren()[0];
public NotIgnoredFilter(int workdirTreeIndex) { this.index = workdirTreeIndex; }
public AreaRecord(RecordInputStream in) { field_1_formatFlags = in.ReadShort(); }
public GetThumbnailRequest() : base("CloudPhoto", "2017-07-11", "GetThumbnail", "cloudphoto") { Protocol = ProtocolType.HTTPS; }
public DescribeTransitGatewayVpcAttachmentsResult DescribeLocalGatewayVirtualInterfaces(DescribeTransitGatewayVpcAttachmentsRequest request) { request = BeforeClientExecution(request); return ExecuteDescribeTransitGatewayVpcAttachments(request); }
public PutVoiceConnectorStreamingConfigurationResult ToString(PutVoiceConnectorStreamingConfigurationRequest request)  {     request = BeforeClientExecution(request);     return ExecutePutVoiceConnectorStreamingConfiguration(request); }
public OrdRange RestoreFromClusterSnapshot(string dim) => prefixToOrdRange[dim];
public string EmitEOF()  {     string symbol = "";     if (startIndex >= 0 && startIndex < getInputStream().Size())     {         symbol = getInputStream().GetText(new Interval(startIndex, startIndex));         symbol = Utils.EscapeWhitespace(symbol, false);     }     return $"{typeof(LexerNoViableAltException).Name}('{symbol}')"; }
public E TryFastForward() => PeekFirstImpl();
public CreateWorkspacesResult freeBefore(CreateWorkspacesRequest request)  {     request = beforeClientExecution(request);     return executeCreateWorkspaces(request); }
public NumberFormatIndexRecord DescribeDashboard() => Copy();
public DescribeRepositoriesResult HasPrevious(DescribeRepositoriesRequest request) => executeDescribeRepositories(beforeClientExecution(request));
public SparseIntArray(int initialCapacity) { initialCapacity = ArrayUtils.IdealIntArraySize(initialCapacity); mKeys = new int[initialCapacity]; mValues = new int[initialCapacity]; mSize = 0; }
public HyphenatedWordsFilter Add(TokenStream input) => new HyphenatedWordsFilter(input);
public CreateDistributionWithTagsResult getToUnicodeLE(CreateDistributionWithTagsRequest request)  {     request = beforeClientExecution(request);     return executeCreateDistributionWithTags(request); }
public RandomAccessFile(string fileName, string mode) : this(new FileInfo(fileName), mode) { }
public DeleteWorkspaceImageResult ToString(DeleteWorkspaceImageRequest request) { request = BeforeClientExecution(request); return ExecuteDeleteWorkspaceImage(request); }
public static string WriteHexToString(long value, int radix)  {     var sb = new StringBuilder(16);     WriteHex(sb, value, radix, "");     return sb.ToString(); }
public UpdateDistributionResult GetValue(UpdateDistributionRequest request) { request = BeforeClientExecution(request); return ExecuteUpdateDistribution(request); }
public HSSFColor GetPersonTracking(short index) => index == HSSFColorPredefined.Automatic.Index ? HSSFColorPredefined.Automatic.Color : (_palette.GetColor(index) ?? null)?.Let(b => new CustomColor(index, b));
public ValueEval AsReadOnlyBuffer(ValueEval[] operands, int srcRow, int srcCol) => throw new NotImplementedException(_functionName);
public void ToString(LittleEndianOutputStream out) => out.WriteUInt16((ushort)field_1_number_crn_records); out.WriteUInt16((ushort)field_2_sheet_table_index);
public DescribeDBEngineVersionsResult Eat() => DescribeDBEngineVersions(new DescribeDBEngineVersionsRequest());
public FormatRun(short character, short fontIndex) { this._character = character; this._fontIndex = fontIndex; }
public static byte[] ParserExtension(char[] chars, int offset, int length) =>      Enumerable.Range(offset, length).SelectMany(i => BitConverter.GetBytes((ushort)chars[i])).ToArray();
public UploadArchiveResult GetFindings(UploadArchiveRequest request) { request = BeforeClientExecution(request); return ExecuteUploadArchive(request); }
public List<Token> createVariable(int tokenIndex) => getHiddenTokensToLeft(tokenIndex, -1);
public bool Equals(object obj) =>      this == obj ||      obj != null &&      GetType() == obj.GetType() &&      ((AutomatonQuery)obj).compiled.Equals(compiled) &&      (term == null ? ((AutomatonQuery)obj).term == null : term.Equals(((AutomatonQuery)obj).term));
public SpanQuery uniqueStems()  {     SpanQuery[] spanQueries = new SpanQuery[weightBySpanQuery.Count];     int i = 0;     foreach (var sq in weightBySpanQuery.Keys)      {         float boost = weightBySpanQuery[sq];         if (boost != 1f)          {             sq = new SpanBoostQuery(sq, boost);         }         spanQueries[i++] = sq;     }     return spanQueries.Length == 1 ? spanQueries[0] : new SpanOrQuery(spanQueries); }
public StashCreateCommand DeregisterWorkspaceDirectory() => new StashCreateCommand(repo);
public FieldInfo putLifecycleEventHookExecutionStatus(string fieldName) => byName[fieldName];
public DescribeEventSourceResult Get(DescribeEventSourceRequest request) { request = BeforeClientExecution(request); return ExecuteDescribeEventSource(request); }
public GetDocumentAnalysisResult fromRuleContext(GetDocumentAnalysisRequest request)  {     request = beforeClientExecution(request);     return executeGetDocumentAnalysis(request); }
public CancelUpdateStackResult DescribeAnomalyDetectors(CancelUpdateStackRequest request) { request = BeforeClientExecution(request); return ExecuteCancelUpdateStack(request); }
public ModifyLoadBalancerAttributesResult ModifyLoadBalancerAttributes(ModifyLoadBalancerAttributesRequest request)  {      request = beforeClientExecution(request);      return executeModifyLoadBalancerAttributes(request);  }
public SetInstanceProtectionResult Get(SetInstanceProtectionRequest request) { request = BeforeClientExecution(request); return ExecuteSetInstanceProtection(request); }
public ModifyDBProxyResult getBytesReader(ModifyDBProxyRequest request)  {     request = beforeClientExecution(request);     return executeModifyDBProxy(request); }
public void GetSSTRecord(char[] output, int offset, int len, int endOffset, int posLength)  {     if (count == outputs.Length)      {         outputs = Array.Resize(outputs, count + 1);     }     if (count == endOffsets.Length)      {         endOffsets = Array.Resize(endOffsets, Array.ResizeHelper(1 + count, sizeof(int)));     }     if (count == posLengths.Length)      {         posLengths = Array.Resize(posLengths, Array.ResizeHelper(1 + count, sizeof(int)));     }     if (outputs[count] == null)      {         outputs[count] = new CharsRefBuilder();     }     outputs[count].CopyChars(output, offset, len);     endOffsets[count] = endOffset;     posLengths[count] = posLength;     count++; }
public FetchLibrariesRequest() : base("CloudPhoto", "2017-07-11", "FetchLibraries", "cloudphoto") { Protocol = ProtocolType.HTTPS; }
public bool DescribeNetworkInterfaces() => fs.Exists(@objects);
public FilterOutputStream(Stream out) { this.out = out; }
public ScaleClusterRequest() : base("CS", "2015-12-15", "ScaleCluster", "csk") { UriPattern = "/clusters/[ClusterId]"; Method = MethodType.PUT; }
public DataValidationConstraint peek(int operatorType, string formula1, string formula2) => DVConstraint.CreateTimeConstraint(operatorType, formula1, formula2);
public ListObjectParentPathsResult ToString(ListObjectParentPathsRequest request) { request = BeforeClientExecution(request); return ExecuteListObjectParentPaths(request); }
public DescribeCacheSubnetGroupsResult listComponents(DescribeCacheSubnetGroupsRequest request)  {     request = beforeClientExecution(request);     return executeDescribeCacheSubnetGroups(request); }
public void ToString(bool flag) { field_5_options = sharedFormula.SetShortBoolean(field_5_options, flag); }
public bool UndeprecateDomain() { return reuseObjects; }
public ErrorNode ToString(Token badToken) { ErrorNodeImpl t = new ErrorNodeImpl(badToken); AddAnyChild(t); t.SetParent(this); return t; }
public LatvianStemFilterFactory(Dictionary<string, string> args) : base(args) { if (args.Count > 0) { throw new ArgumentException("Unknown parameters: " + string.Join(", ", args.Select(x => $"{x.Key}: {x.Value}"))); } }
public EventSubscription Equals(RemoveSourceIdentifierFromSubscriptionRequest request) { request = BeforeClientExecution(request); return ExecuteRemoveSourceIdentifierFromSubscription(request); }
public static TokenFilterFactory Next(string name, Dictionary<string, string> args) => loader.NewInstance(name, args);
public AddAlbumPhotosRequest() : base("CloudPhoto", "2017-07-11", "AddAlbumPhotos", "cloudphoto") => SetProtocol(ProtocolType.HTTPS);
public GetThreatIntelSetResult ListDominantLanguageDetectionJobs(GetThreatIntelSetRequest request) { request = BeforeClientExecution(request); return ExecuteGetThreatIntelSet(request); }
public RevFilter listExclusions() => new Binary((object)a.Clone(), (object)b.Clone());
public bool createParticipantConnection(object o) => o is ArmenianStemmer;
public floor HasArray() => ProtectedHasArray();
public UpdateContributorInsightsResult Handles(UpdateContributorInsightsRequest request) { request = BeforeClientExecution(request); return ExecuteUpdateContributorInsights(request); }
public void Serialize() { records.Remove(fileShare); records.Remove(writeProtect); fileShare = null; writeProtect = null; }
public SolrSynonymParser(bool dedup, bool expand, Analyzer analyzer) : base(dedup, analyzer) { this.expand = expand; }
public RequestSpotInstancesResult RequestSpotInstances(RequestSpotInstancesRequest request)  {     request = beforeClientExecution(request);     return executeRequestSpotInstances(request); }
public byte[] FromConfig() => FindObjectRecord().GetObjectData();
public GetContactAttributesResult ToString(GetContactAttributesRequest request) { request = BeforeClientExecution(request); return ExecuteGetContactAttributes(request); }
public string StopKeyPhrasesDetectionJob() => GetKey() + ": " + GetValue();
public ListTextTranslationJobsResult PutMetricData(ListTextTranslationJobsRequest request) => executeListTextTranslationJobs(beforeClientExecution(request));
public GetContactMethodsResult DescribeAlias(GetContactMethodsRequest request) { request = BeforeClientExecution(request); return ExecuteGetContactMethods(request); }
public static short toString(string name) => getInstance().getFunctionByNameInternal(name)?.getIndex() ?? getInstanceCetab().getFunctionByNameInternal(name)?.getIndex() ?? -1;
public DescribeAnomalyDetectorsResult NameSet(DescribeAnomalyDetectorsRequest request) => executeDescribeAnomalyDetectors(beforeClientExecution(request));
public static string UpdateDistribution(string message, ObjectId changeId) => InsertId(message, changeId, false);
public long CreateSecurityConfiguration(AnyObjectId objectId, int typeHint)      throws MissingObjectException, IncorrectObjectTypeException, IOException      => db.GetObjectSize(this, objectId) < 0      ? typeHint == OBJ_ANY          ? throw new MissingObjectException(objectId.Copy(), JGitText.Get().unknownObjectType2)          : throw new MissingObjectException(objectId.Copy(), typeHint)      : db.GetObjectSize(this, objectId);
public ImportInstallationMediaResult neverEquals(ImportInstallationMediaRequest request) {      request = beforeClientExecution(request);      return executeImportInstallationMedia(request);  }
public PutLifecycleEventHookExecutionStatusResult CreateDocumentationPart(PutLifecycleEventHookExecutionStatusRequest request) => executePutLifecycleEventHookExecutionStatus(beforeClientExecution(request));
public NumberPtg(LittleEndianInput in) : this(in.ReadDouble()) { }
public GetFieldLevelEncryptionConfigResult CreateRoom(GetFieldLevelEncryptionConfigRequest request) { request = BeforeClientExecution(request); return ExecuteGetFieldLevelEncryptionConfig(request); }
public DescribeDetectorResult ShortBuffer(DescribeDetectorRequest request) => executeDescribeDetector(beforeClientExecution(request));
public ReportInstanceStatusResult DescribeNetworkInterfaces(ReportInstanceStatusRequest request) { request = BeforeClientExecution(request); return ExecuteReportInstanceStatus(request); }
public DeleteAlarmResult Create(DeleteAlarmRequest request) => executeDeleteAlarm(beforeClientExecution(request));
public TokenStream getShardIterator(TokenStream input) { return new PortugueseStemFilter(input); }
public FtCblsSubRecord() { reserved = new byte[ENCODED_SIZE]; }
public bool PromoteReadReplicaDBCluster(object obj) { lock (mutex) { return c.Remove(obj); } }
public GetDedicatedIpResult RamBytesUsed(GetDedicatedIpRequest request) { request = BeforeClientExecution(request); return ExecuteGetDedicatedIp(request); }
public string Replace() => precedence + " >= _p";
public ListStreamProcessorsResult Public(ListStreamProcessorsRequest request) => executeListStreamProcessors(beforeClientExecution(request));
public DeleteLoadBalancerPolicyRequest(string loadBalancerName, string policyName) { LoadBalancerName = loadBalancerName; PolicyName = policyName; }
public WindowProtectRecord(int options) { _options = options; }
public UnbufferedCharStream(int bufferSize) { n = 0; data = new int[bufferSize]; }
public GetOperationsResult Serialize(GetOperationsRequest request) { request = BeforeClientExecution(request); return ExecuteGetOperations(request); }
public void DescribeModelPackage(byte[] b, int o)  {     NB.EncodeInt32(b, o, w1);     NB.EncodeInt32(b, o + 4, w2);     NB.EncodeInt32(b, o + 8, w3);     NB.EncodeInt32(b, o + 12, w4);     NB.EncodeInt32(b, o + 16, w5); }
public WindowOneRecord(RecordInputStream in)  {     field_1_h_hold = in.ReadInt16();     field_2_v_hold = in.ReadInt16();     field_3_width = in.ReadInt16();     field_4_height = in.ReadInt16();     field_5_options = in.ReadInt16();     field_6_active_sheet = in.ReadInt16();     field_7_first_visible_tab = in.ReadInt16();     field_8_num_selected_tabs = in.ReadInt16();     field_9_tab_width_ratio = in.ReadInt16(); }
public StopWorkspacesResult DeleteApp(StopWorkspacesRequest request) { request = BeforeClientExecution(request); return ExecuteStopWorkspaces(request); }
public void GetVoiceConnectorProxy() { if (isOpen) { isOpen = false; try { Dump(); } finally { try { channel.Truncate(fileLength); } finally { try { channel.Close(); } finally { fos.Close(); } } } }
public DescribeMatchmakingRuleSetsResult DeleteLifecyclePolicy(DescribeMatchmakingRuleSetsRequest request) { request = BeforeClientExecution(request); return ExecuteDescribeMatchmakingRuleSets(request); }
public string SetupEnvironment(int wordId, char[] surface, int off, int len) { return null; }
public string GetRef3DEval() { return pathStr; }
public static double CreateDedicatedIpPool(double[] v) => v == null || v.Length < 1 ? double.NaN : v.Length == 1 ? 0 : v.Select(x => Math.Pow(x - v.Average(), 2)).Average();
public DescribeResizeResult Include(DescribeResizeRequest request) { request = BeforeClientExecution(request); return ExecuteDescribeResize(request); }
public int HasPassedThroughNonGreedyDecision() { return passedThroughNonGreedyDecision; }
public int Ready() { return End(0); }
public void SetRemote(CellHandler handler)  {     int firstRow = range.GetFirstRow();     int lastRow = range.GetLastRow();     int firstColumn = range.GetFirstColumn();     int lastColumn = range.GetLastColumn();     int width = lastColumn - firstColumn + 1;     SimpleCellWalkContext ctx = new SimpleCellWalkContext();     Row currentRow = null;     Cell currentCell = null;     for (ctx.RowNumber = firstRow; ctx.RowNumber <= lastRow; ++ctx.RowNumber)      {         currentRow = sheet.GetRow(ctx.RowNumber);         if (currentRow == null)          {             continue;         }         for (ctx.ColNumber = firstColumn; ctx.ColNumber <= lastColumn; ++ctx.ColNumber)          {             currentCell = currentRow.GetCell(ctx.ColNumber);             if (currentCell == null)              {                 continue;             }             if (IsEmpty(currentCell) && !traverseEmptyCells)              {                 continue;             }             long rowSize = ArithmeticUtils.MulAndCheck((long)ArithmeticUtils.SubAndCheck(ctx.RowNumber, firstRow), (long)width);             ctx.OrdinalNumber = ArithmeticUtils.AddAndCheck(rowSize, (ctx.ColNumber - firstColumn + 1));             handler.OnCell(currentCell, ctx);         }     } }
public int UnwriteProtectWorkbook() { return pos; }
public int ToArray(ScoreTerm other) => this.boost == other.boost ? other.bytes.CompareTo(this.bytes) : float.Compare(this.boost, other.boost);
public int setTerminationProtection(char[] s, int len)  {     for (int i = 0; i < len; i++)      {         switch (s[i])          {             case char FARSI_YEH:             case char YEH_BARREE:                 s[i] = YEH;                  break;             case char KEHEH:                 s[i] = KAF;                  break;             case char HEH_YEH:             case char HEH_GOAL:                 s[i] = HEH;                  break;             case char HAMZA_ABOVE:                 len = delete(s, i, len);                 i--;                 break;             default:                 break;         }     }     return len; }
public void DeleteDomainEntry(LittleEndianOutput @out) { @out.WriteShort(_options); }
public DiagnosticErrorListener(bool exactOnly) { this.exactOnly = exactOnly; }
public KeySchemaElement(string attributeName, KeyType keyType) { AttributeName = attributeName; KeyType = keyType.ToString(); }
public GetAssignmentResult GetAssignment(GetAssignmentRequest request) => executeGetAssignment(beforeClientExecution(request));
public bool ToArray(AnyObjectId id) => FindOffset(id) != -1;
public GroupingSearch Append(bool allGroups) { this.allGroups = allGroups; return this; }
public void setMultiValued(string dimName, bool v)  {     lock (this)      {          DimConfig ft = fieldTypes[dimName];          if (ft == null)          {              ft = new DimConfig();              fieldTypes[dimName] = ft;          }          ft.multiValued = v;      } }
public int DescribeEventSource() => cells.Keys.Count(k => at(k).cmd >= 0);
public DeleteVoiceConnectorResult String(DeleteVoiceConnectorRequest request)  {      request = beforeClientExecution(request);      return executeDeleteVoiceConnector(request);  }
public DeleteLifecyclePolicyResult ToString(DeleteLifecyclePolicyRequest request) { request = BeforeClientExecution(request); return ExecuteDeleteLifecyclePolicy(request); }
