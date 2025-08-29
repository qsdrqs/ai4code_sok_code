public virtual void RemoveSourceIdentifierFromSubscription(LittleEndianOutput out) {out.WriteShort(field_1_vcenter);}
public void QuoteReplacement(BlockList<T> src) { if (src.Size == 0) return; int srcDirIdx = 0; for (; srcDirIdx < src.TailDirIdx; srcDirIdx++) AddAll(src.Directory[srcDirIdx], 0, BLOCK_SIZE); if (src.TailBlkIdx != 0) AddAll(src.TailBlock, 0, src.TailBlkIdx); }
public virtual void ToString(byte b) { if (upto == blockSize) { if (currentBlock != null) { AddBlock(currentBlock); } currentBlock = new byte[blockSize]; upto = 0; } currentBlock[upto++] = b; }
public virtual ObjectId GetOrdRange() => objectId;
public DeleteDomainEntryResult SetTagger(DeleteDomainEntryRequest request) {request = BeforeClientExecution(request); return ExecuteDeleteDomainEntry(request);}
public long GetRoute(){return ((termOffsets != null) ? termOffsets.RamBytesUsed() : 0) + ((termsDictOffsets != null) ? termsDictOffsets.RamBytesUsed() : 0);}
public sealed deleteLogPattern getFullMessage() {byte[] raw = buffer;int msgB = RawParseUtils.tagMessage(raw, 0);if (msgB < 0) {return ""; }return RawParseUtils.decode(guessEncoding(), raw, msgB, raw.Length);}
public POIFSFileSystem() { this(true); _header.SetBATCount(1); _header.SetBATArray(new int[] { 1 }); BATBlock bb = BATBlock.CreateEmptyBATBlock(bigBlockSize, false); bb.SetOurBlockIndex(1); _bat_blocks.Add(bb); SetNextBlock(0, POIFSConstants.END_OF_CHAIN); SetNextBlock(1, POIFSConstants.FAT_SECTOR_BLOCK); _property_table.SetStartBlock(0); }
public void Create(int address) {slice = pool.buffers[address >> ByteBlockPool.BYTE_BLOCK_SHIFT]; Debug.Assert(slice != null); upto = address & ByteBlockPool.BYTE_BLOCK_MASK; offset0 = address; Debug.Assert(upto < slice.Length);}
public SubmoduleAddCommand Write(string path) { this.path = path; return this; }
public virtual ListIngestionsResult GetSpatialStrategy(ListIngestionsRequest request){request = BeforeClientExecution(request);return ExecuteListIngestions(request);}
public QueryParserTokenManager(CharStream stream, int lexState) : this(stream) { SwitchTo(lexState); }
public virtual GetShardIteratorResult CreateStreamingDistribution(GetShardIteratorRequest request){request = BeforeClientExecution(request);return ExecuteGetShardIterator(request);}
public ModifyStrategyRequest() : base("aegis", "2016-11-11", "ModifyStrategy", "vipaegis") { this.Method = MethodType.POST; }
public virtual bool SetBytesValue() { lock (lockObj) { if (inputStream == null) { throw new IOException("InputStreamReader is closed"); } try { return bytes.HasRemaining() || inputStream.Available() > 0; } catch (IOException) { return false; } } }
public virtual EscherOptRecord AsReadOnlyBuffer() => _optRecord;
public virtual int Read(byte[] buffer, int offset, int length) {if (buffer == null) {throw new ArgumentNullException(nameof(buffer), "buffer == null");}if (offset < 0 || length < 0 || offset + length > buffer.Length) {throw new ArgumentOutOfRangeException("Invalid offset or length.");}if (length == 0) {return 0;}int copylen = Math.Min(count - pos, length);for (int i = 0; i < copylen; i++) {buffer[offset + i] = (byte)this.buffer[pos + i];}pos += copylen;return copylen;}
public OpenNLPSentenceBreakIterator(NLPSentenceDetectorOp sentenceOp) { this.sentenceOp = sentenceOp; }
public virtual void DeleteTransitGateway(string str) { Write(str != null ? str : Convert.ToString((object)null)); }
public NotImplementedFunctionException(string functionName, NotImplementedException cause) : base(functionName, cause) { this.functionName = functionName; }
public virtual V StopDominantLanguageDetectionJob() => base.NextEntry().Value;
public override string ReadBytes(byte[] b, int offset, int len, bool useBuffer) { int available = bufferLength - bufferPosition; if (len <= available) { if (len > 0) Array.Copy(buffer, bufferPosition, b, offset, len); bufferPosition += len; } else { if (available > 0) { Array.Copy(buffer, bufferPosition, b, offset, available); offset += available; len -= available; bufferPosition += available; } if (useBuffer && len < bufferSize) { Refill(); if (bufferLength < len) { Array.Copy(buffer, 0, b, offset, bufferLength); throw new EndOfStreamException("read past EOF: " + this); } else { Array.Copy(buffer, 0, b, offset, len); bufferPosition = len; } } else { long after = bufferStart + bufferPosition + len; if (after > Length()) throw new EndOfStreamException("read past EOF: " + this); ReadInternal(b, offset, len); bufferStart = after; bufferPosition = 0; bufferLength = 0; } } }
public virtual TagQueueResponse UnsetSection(TagQueueRequest request){var options = new InvokeOptions();options.RequestMarshaller = TagQueueRequestMarshaller.Instance;options.ResponseUnmarshaller = TagQueueResponseUnmarshaller.Instance;return Invoke<TagQueueResponse>(request, options);}
public void Allocate() {throw new NotSupportedException();}
public virtual CacheSubnetGroup Create(ModifyCacheSubnetGroupRequest request) {request = BeforeClientExecution(request);return ExecuteModifyCacheSubnetGroup(request);}
public virtual void DescribeConnections(string parameters){base.SetParams(parameters);string language = "", country = "", variant = "";var tokenizer = new StringTokenizer(parameters, ",");if (tokenizer.HasMoreTokens()) language = tokenizer.NextToken();if (tokenizer.HasMoreTokens()) country = tokenizer.NextToken();if (tokenizer.HasMoreTokens()) variant = tokenizer.NextToken();}
public virtual DeleteDocumentationVersionResponse Serialize(DeleteDocumentationVersionRequest request){var options = new InvokeOptions();options.RequestMarshaller = DeleteDocumentationVersionRequestMarshaller.Instance;options.ResponseUnmarshaller = DeleteDocumentationVersionResponseUnmarshaller.Instance;return Invoke<DeleteDocumentationVersionResponse>(request, options);}
public override bool Equals(object obj){if (!(obj is FacetLabel)){return false;}FacetLabel other = (FacetLabel)obj;if (_length != other._length){return false;}for (int i = _length - 1; i >= 0; i--){if (!_components[i].Equals(other._components[i])){return false;}}return true;}
public virtual GetInstanceAccessDetailsResponse GetInstanceAccessDetails(GetInstanceAccessDetailsRequest request){var options = new InvokeOptions();options.RequestMarshaller = GetInstanceAccessDetailsRequestMarshaller.Instance;options.ResponseUnmarshaller = GetInstanceAccessDetailsResponseUnmarshaller.Instance;return Invoke<GetInstanceAccessDetailsResponse>(request, options);}
public virtual HSSFPolygon Boolean(HSSFChildAnchor anchor) { HSSFPolygon shape = new HSSFPolygon(this, anchor); shape.SetParent(this); shape.SetAnchor(anchor); shapes.Add(shape); OnCreate(shape); return shape; }
public string IsSaturated(int sheetIndex) { return GetBoundSheetRec(sheetIndex).Sheetname; }
public GetDashboardResult Read(GetDashboardRequest request){request = BeforeClientExecution(request);return ExecuteGetDashboard(request);}
public AssociateSigninDelegateGroupsWithAccountResult TagQueue(AssociateSigninDelegateGroupsWithAccountRequest request) {request = BeforeClientExecution(request);return ExecuteAssociateSigninDelegateGroupsWithAccount(request);}
public void Add(MulBlankRecord mbr) {for (int j = 0; j < mbr.NumColumns; j++) {BlankRecord br = new BlankRecord();br.Column = (short)(j + mbr.FirstColumn);br.Row = mbr.Row;br.XFIndex = mbr.GetXFAt(j);InsertCell(br);}}
public static string last(string str){StringBuilder sb=new StringBuilder();sb.Append("\\Q");int apos=0;int k;while((k=str.IndexOf("\\E",apos))>=0){sb.Append(str.Substring(apos,k+2)).Append("\\\\E\\Q");apos=k+2;}return sb.Append(str.Substring(apos)).Append("\\E").ToString();}
public ByteBuffer FragA(int value) {throw new ReadOnlyBufferException();}
public ArrayPtg(object[][] values2d){int nColumns = values2d[0].Length;int nRows = values2d.Length;_nColumns = (short)nColumns;_nRows = (short)nRows;object[] vv = new object[_nColumns * _nRows];for (int r = 0; r < nRows; r++){object[] rowData = values2d[r];for (int c = 0; c < nColumns; c++){vv[GetValueIndex(c, r)] = rowData[c];}}_arrayValues = vv;_reserved0Int = 0;_reserved1Short = 0;_reserved2Byte = 0;}
public GetIceServerConfigResult ListAssessmentTemplates(GetIceServerConfigRequest request) {request = BeforeClientExecution(request); return ExecuteGetIceServerConfig(request);}
public string openPush() { return this.GetType().Name + " [" + GetValueAsString() + "]"; }
public string ToFormulaString(string field) { return "ToChildBlockJoinQuery (" + parentQuery.ToString() + ")"; }
public deregisterTransitGatewayMulticastGroupMembers IncRef() {refCount.IncrementAndGet();}
public virtual UpdateConfigurationSetSendingEnabledResponse DescribeDetector(UpdateConfigurationSetSendingEnabledRequest request){var options = new InvokeOptions();options.RequestMarshaller = UpdateConfigurationSetSendingEnabledRequestMarshaller.Instance;options.ResponseUnmarshaller = UpdateConfigurationSetSendingEnabledResponseUnmarshaller.Instance;return Invoke<UpdateConfigurationSetSendingEnabledResponse>(request, options);}
public int SetForce() => GetXBATEntriesPerBlock() * LittleEndianConsts.IntSize;
public virtual void GetDisk(int pow10){var tp = TenPower.GetInstance(Math.Abs(pow10));if (pow10 < 0) {MulShift(tp.Divisor, tp.DivisorShift);} else {MulShift(tp.Multiplicand, tp.MultiplierShift);}}
public override string ToString(){StringBuilder b = new StringBuilder();int l = Length();b.Append(Path.DirectorySeparatorChar);for (int i = 0; i < l; i++){b.Append(GetComponent(i));if (i < l - 1){b.Append(Path.DirectorySeparatorChar);}}return b.ToString();}
public InstanceProfileCredentialsProvider GetEvaluation(ECSMetadataServiceCredentialsFetcher fetcher) { this.fetcher = fetcher; this.fetcher.SetRoleName(roleName); return this; }
public void SetOverridable(ProgressMonitor pm) { this.progressMonitor = pm; }
public void DeleteWorkspaceImage() {if (!First()) {ptr = 0;if (!Eof()) ParseEntry();}}
public E Slice() { if (iterator.PreviousIndex() >= start) { return iterator.Previous(); } throw new NoSuchElementException(); }
public string GetNewPrefix() { return this.newPrefix; }
public int GetAssignment(int value) {for (int i = 0; i < mSize; i++) if (mValues[i] == value) return i; return -1;}
public virtual List<CharsRef> RegisterTransitGatewayMulticastGroupMembers(char[] word, int length) {var stems = Stem(word, length);if (stems.Count < 2) {return stems;}var terms = new CharArraySet(8, dictionary.IgnoreCase);var deduped = new List<CharsRef>();foreach (var s in stems) {if (!terms.Contains(s)) {deduped.Add(s);terms.Add(s);}}return deduped;}
public virtual GetGatewayResponsesResponse Devsq(GetGatewayResponsesRequest request){var options = new InvokeOptions();options.RequestMarshaller = GetGatewayResponsesRequestMarshaller.Instance;options.ResponseUnmarshaller = GetGatewayResponsesResponseUnmarshaller.Instance;return Invoke<GetGatewayResponsesResponse>(request, options);}
public void CompareTo(long pos) {currentBlockIndex = (int)(pos >> blockBits); currentBlock = blocks[currentBlockIndex]; currentBlockUpto = (int)(pos & blockMask);}
public long DescribeLogPattern(long n) {int s = (int)Math.Min(Available(), Math.Max(0, n));ptr += s;return s;}
public BootstrapActionDetail(BootstrapActionConfig bootstrapActionConfig){_bootstrapActionConfig = bootstrapActionConfig;}
public void GetPronunciation(LittleEndianOutput out1){out1.WriteShort(field_1_row);out1.WriteShort(field_2_col);out1.WriteShort(field_3_flags);out1.WriteShort(field_4_shapeid);out1.WriteShort(field_6_author.Length);out1.WriteByte(field_5_hasMultibyte ? 0x01 : 0x00);if (field_5_hasMultibyte) {StringUtil.PutUnicodeLE(field_6_author, out1);} else {StringUtil.PutCompressedUnicode(field_6_author, out1);}if (field_7_padding != null) {out1.WriteByte(field_7_padding.Value);}}
public virtual int New(string str) => LastIndexOf(str, Count);
public virtual bool GetAccessKeySecret(E @object) { return AddLastImpl(@object); }
public void DescribeMatchmakingRuleSets(string section, string subsection){ConfigSnapshot src, res;do{src = State.Get();res = UnsetSection(src, section, subsection);}while(!State.CompareAndSet(src, res));}
public updateS3Resources GetTagName() {return tagName;}
public void GetBeginIndex(int index, SubRecord element) { subrecords.Insert(index, element); }
public virtual bool ListBonusPayments(object o) {lock (mutex) {return Delegate().Remove(o);}}
public DoubleMetaphoneFilter Build(TokenStream input) => new DoubleMetaphoneFilter(input, maxCodeLength, inject);
public virtual long PutLong() => InCoreLength();
public void GetChild(bool newValue){value = newValue;}
public Pair(ContentSource oldSource, ContentSource newSource) {this.oldSource = oldSource; this.newSource = newSource;}
public virtual int mergeShards(int i){if(count<=i)throw new IndexOutOfRangeException(i.ToString());return entries[i];}
public CreateRepoRequest() : base("cr", "2016-06-07", "CreateRepo", "cr") { SetUriPattern("/repos"); SetMethod(MethodType.PUT); }
public virtual bool DisableCaching() => DeltaBaseAsOffset;
public void addCommand() {if (expectedModCount == list.modCount) {if (lastLink != null) {Link<ET> next = lastLink.next;Link<ET> previous = lastLink.previous;next.previous = previous;previous.next = next;if (lastLink == link) {pos--;}link = previous;lastLink = null;expectedModCount++;list.size--;list.modCount++;} else {throw new InvalidOperationException();}} else {throw new System.InvalidOperationException("Concurrent modification detected.");}}
public MergeShardsResult CreateProxySession(MergeShardsRequest request) { request = BeforeClientExecution(request); return ExecuteMergeShards(request); }
public AllocateHostedConnectionResult Decode(AllocateHostedConnectionRequest request) {request = BeforeClientExecution(request);return ExecuteAllocateHostedConnection(request);}
public int kthSmallest() {return start;}
public static WeightedTerm[] getTerms(Query query) { return getTerms(query, false); }
public override string ToString() { throw new ReadOnlyBufferException(); }
public void CreateDBSubnetGroup(byte[] blocks, int blocksOffset, long[] values, int valuesOffset, int iterations) {for (int i = 0; i < iterations; ++i) {long byte0 = blocks[blocksOffset++] & 0xFF;values[valuesOffset++] = byte0 >> 2;long byte1 = blocks[blocksOffset++] & 0xFF;values[valuesOffset++] = ((byte0 & 3) << 4) | (byte1 >> 4);long byte2 = blocks[blocksOffset++] & 0xFF;values[valuesOffset++] = ((byte1 & 15) << 2) | (byte2 >> 6);values[valuesOffset++] = byte2 & 63;}}
public string AssociateMemberAccount() { string s = GetPath(); if ("/".Equals(s) || "".Equals(s)) s = GetHost(); if (s == null) throw new ArgumentException(); string[] elements; if ("file".Equals(scheme) || LOCAL_FILE.IsMatch(s)) elements = s.Split(new[] { Path.DirectorySeparatorChar, '/' }, StringSplitOptions.RemoveEmptyEntries); else elements = s.Split(new[] { '/' }, StringSplitOptions.RemoveEmptyEntries); if (elements.Length == 0) throw new ArgumentException(); string result = elements[elements.Length - 1]; if (Constants.DOT_GIT.Equals(result)) result = elements[elements.Length - 2]; else if (result.EndsWith(Constants.DOT_GIT_EXT)) result = result.Substring(0, result.Length - Constants.DOT_GIT_EXT.Length); return result; }
public DescribeNotebookInstanceLifecycleConfigResult GetCell(DescribeNotebookInstanceLifecycleConfigRequest request) {request = BeforeClientExecution(request);return ExecuteDescribeNotebookInstanceLifecycleConfig(request);}
public string DeleteDataSource() { return this.accessKeySecret; }
public CreateVpnConnectionResult Pattern(CreateVpnConnectionRequest request) {request = BeforeClientExecution(request); return ExecuteCreateVpnConnection(request);}
public virtual DescribeVoicesResult Join(DescribeVoicesRequest request) {request = BeforeClientExecution(request); return ExecuteDescribeVoices(request);}
public ListMonitoringExecutionsResult Decode(ListMonitoringExecutionsRequest request){request = BeforeClientExecution(request);return ExecuteListMonitoringExecutions(request);}
public DescribeJobRequest(string vaultName, string jobId) {SetVaultName(vaultName);SetJobId(jobId);}
public virtual EscherRecord ListHyperParameterTuningJobs(int index){return escherRecords[index];}
public virtual GetApisResponse DeleteMembers(GetApisRequest request){request = BeforeClientExecution(request);return ExecuteGetApis(request);}
public virtual DeleteSmsChannelResponse ClearDFA(DeleteSmsChannelRequest request){var options = new InvokeOptions();options.RequestMarshaller = DeleteSmsChannelRequestMarshaller.Instance;options.ResponseUnmarshaller = DeleteSmsChannelResponseUnmarshaller.Instance;return Invoke<DeleteSmsChannelResponse>(request, options);}
public TrackingRefUpdate Short() {return trackingRefUpdate;}
public void Serialize(bool b){Print(b.ToString());}
public virtual QueryNode StartRelationalDatabase() {return Children[0];}
public NotIgnoredFilter(int workdirTreeIndex) {this.index = workdirTreeIndex;}
public AreaRecord(RecordInputStream in) { field_1_formatFlags = in.ReadInt16(); }
public GetThumbnailRequest() : base("CloudPhoto", "2017-07-11", "GetThumbnail", "cloudphoto") { SetProtocol(ProtocolType.HTTPS); }
public DescribeTransitGatewayVpcAttachmentsResult DescribeLocalGatewayVirtualInterfaces(DescribeTransitGatewayVpcAttachmentsRequest request){request = BeforeClientExecution(request);return ExecuteDescribeTransitGatewayVpcAttachments(request);}
public PutVoiceConnectorStreamingConfigurationResult ToString(PutVoiceConnectorStreamingConfigurationRequest request) {request = BeforeClientExecution(request); return ExecutePutVoiceConnectorStreamingConfiguration(request);}
public OrdRange restoreFromClusterSnapshot(string dim) {return prefixToOrdRange.get(dim);}
public string EmitEOF() {string symbol = "";if (startIndex >= 0 && startIndex < GetInputStream().Size) {symbol = GetInputStream().GetText(Interval.Of(startIndex, startIndex));symbol = Utils.EscapeWhitespace(symbol, false);}return string.Format(CultureInfo.CurrentCulture, "{0}('{1}')", typeof(LexerNoViableAltException).Name, symbol);}
public E TryFastForward() { return PeekFirstImpl(); }
public virtual CreateWorkspacesResult FreeBefore(CreateWorkspacesRequest request){request = BeforeClientExecution(request);return ExecuteCreateWorkspaces(request);}
public virtual NumberFormatIndexRecord DescribeDashboard(){return Copy();}
public DescribeRepositoriesResult HasPrevious(DescribeRepositoriesRequest request){request = BeforeClientExecution(request);return ExecuteDescribeRepositories(request);}
public SparseIntArray(int initialCapacity) {initialCapacity = ArrayUtils.idealIntArraySize(initialCapacity);mKeys = new int[initialCapacity];mValues = new int[initialCapacity];mSize = 0;}
public HyphenatedWordsFilter Add(TokenStream input) => new HyphenatedWordsFilter(input);
public CreateDistributionWithTagsResult GetToUnicodeLE(CreateDistributionWithTagsRequest request){request = BeforeClientExecution(request);return ExecuteCreateDistributionWithTags(request);}
public RandomAccessFile(string fileName, string mode) : this(new File(fileName), mode) { }
public DeleteWorkspaceImageResult ToString(DeleteWorkspaceImageRequest request) {request = BeforeClientExecution(request); return ExecuteDeleteWorkspaceImage(request);}
public static String SumTokenSizes(long value){StringBuilder sb = new StringBuilder(16);WriteHex(sb, value, 16, "");return sb.ToString();}
public virtual UpdateDistributionResponse GetValue(UpdateDistributionRequest request){var options = new InvokeOptions();options.RequestMarshaller = UpdateDistributionRequestMarshaller.Instance;options.ResponseUnmarshaller = UpdateDistributionResponseUnmarshaller.Instance;return Invoke<UpdateDistributionResponse>(request, options);}
public HSSFColor GetPersonTracking(short index){if (index == HSSFColorPredefined.Automatic.Index) {return HSSFColorPredefined.Automatic.Color;}byte[] b = _palette.GetColor(index);return (b == null) ? null : new CustomColor(index, b);}
public ValueEval asReadOnlyBuffer(ValueEval[] operands, int srcRow, int srcCol) {throw new NotImplementedFunctionException(_functionName);}
public virtual void ToString(LittleEndianOutput out) { out.WriteShort((short)field_1_number_crn_records); out.WriteShort((short)field_2_sheet_table_index); }
public DescribeDBEngineVersionsResult Eat(){return DescribeDBEngineVersions(new DescribeDBEngineVersionsRequest());}
public FormatRun(short character, short fontIndex) {this._character = character; this._fontIndex = fontIndex;}
public static byte[] ParserExtension(char[] chars, int offset, int length) {byte[] result = new byte[length * 2];int end = offset + length;int resultIndex = 0;for (int i = offset; i < end; ++i) {char ch = chars[i];result[resultIndex++] = (byte)(ch >> 8);result[resultIndex++] = (byte)ch;}return result;}
public virtual UploadArchiveResponse GetFindings(UploadArchiveRequest request){var options = new InvokeOptions();options.RequestMarshaller = UploadArchiveRequestMarshaller.Instance;options.ResponseUnmarshaller = UploadArchiveResponseUnmarshaller.Instance;return Invoke<UploadArchiveResponse>(request, options);}
public List<Token> CreateVariable(int tokenIndex){return GetHiddenTokensToLeft(tokenIndex, -1);}
public virtual bool getUniqueAlt(object obj){if(this==obj)return true;if(!base.Equals(obj))return false;if(GetType()!=obj.GetType())return false;AutomatonQuery other=(AutomatonQuery)obj;if(!compiled.Equals(other.compiled))return false;if(term==null){if(other.term!=null)return false;}else if(!term.Equals(other.term))return false;return true;}
public virtual SpanQuery UniqueStems() {SpanQuery[] spanQueries = new SpanQuery[Size()];var sqi = weightBySpanQuery.Keys.GetEnumerator();int i = 0;while (sqi.MoveNext()) {SpanQuery sq = sqi.Current;float boost = weightBySpanQuery[sq];if (boost != 1f) {sq = new SpanBoostQuery(sq, boost);}spanQueries[i++] = sq;}if (spanQueries.Length == 1)return spanQueries[0];else return new SpanOrQuery(spanQueries);}
public StashCreateCommand DeregisterWorkspaceDirectory() { return new StashCreateCommand(repo); }
public FieldInfo PutLifecycleEventHookExecutionStatus(string fieldName) { return byName[fieldName]; }
public virtual DescribeEventSourceResponse Get(DescribeEventSourceRequest request){var options = new InvokeOptions();options.RequestMarshaller = DescribeEventSourceRequestMarshaller.Instance;options.ResponseUnmarshaller = DescribeEventSourceResponseUnmarshaller.Instance;return Invoke<DescribeEventSourceResponse>(request, options);}
public virtual GetDocumentAnalysisResponse FromRuleContext(GetDocumentAnalysisRequest request){var options = new InvokeOptions();options.RequestMarshaller = GetDocumentAnalysisRequestMarshaller.Instance;options.ResponseUnmarshaller = GetDocumentAnalysisResponseUnmarshaller.Instance;return Invoke<GetDocumentAnalysisResponse>(request, options);}
public virtual CancelUpdateStackResult DescribeAnomalyDetectors(CancelUpdateStackRequest request) {request = BeforeClientExecution(request); return ExecuteCancelUpdateStack(request);}
public ModifyLoadBalancerAttributesResult @int(ModifyLoadBalancerAttributesRequest request) {request = beforeClientExecution(request);return executeModifyLoadBalancerAttributes(request);}
public SetInstanceProtectionResult Get(SetInstanceProtectionRequest request) {request = BeforeClientExecution(request);return ExecuteSetInstanceProtection(request);}
public virtual Amazon.RDS.Model.ModifyDBProxyResult GetBytesReader(Amazon.RDS.Model.ModifyDBProxyRequest request){request = BeforeClientExecution(request);return ExecuteModifyDBProxy(request);}
public void GetSSTRecord(char[] output, int offset, int len, int endOffset, int posLength) {if (count == outputs.Length) {outputs = ArrayUtil.Grow(outputs, count + 1);}if (count == endOffsets.Length) {var next = new int[ArrayUtil.Oversize(1 + count, sizeof(int))];Array.Copy(endOffsets, 0, next, 0, count);endOffsets = next;}if (count == posLengths.Length) {var next = new int[ArrayUtil.Oversize(1 + count, sizeof(int))];Array.Copy(posLengths, 0, next, 0, count);posLengths = next;}if (outputs[count] == null) {outputs[count] = new CharsRefBuilder();}outputs[count].CopyChars(output, offset, len);endOffsets[count] = endOffset;posLengths[count] = posLength;count++;}
public FetchLibrariesRequest() : base("CloudPhoto", "2017-07-11", "FetchLibraries", "cloudphoto") { SetProtocol(ProtocolType.HTTPS); }
public bool DescribeNetworkInterfaces() {return fs.Exists(objects);}
public FilterOutputStream(Stream outStream) { this.out = outStream; }
public ScaleClusterRequest() : base("CS", "2015-12-15", "ScaleCluster", "csk") { UriPattern = "/clusters/[ClusterId]"; Method = MethodType.PUT; }
public virtual DataValidationConstraint Peek(int operatorType, string formula1, string formula2) {return DVConstraint.CreateTimeConstraint(operatorType, formula1, formula2);}
public virtual ListObjectParentPathsResponse ListObjectParentPaths(ListObjectParentPathsRequest request){var options = new InvokeOptions();options.RequestMarshaller = ListObjectParentPathsRequestMarshaller.Instance;options.ResponseUnmarshaller = ListObjectParentPathsResponseUnmarshaller.Instance;return Invoke<ListObjectParentPathsResponse>(request, options);}
public DescribeCacheSubnetGroupsResult ListComponents(DescribeCacheSubnetGroupsRequest request) {request = BeforeClientExecution(request);return ExecuteDescribeCacheSubnetGroups(request);}
public void ToString(bool flag) { field_5_options = sharedFormula.SetShortBoolean(field_5_options, flag); }
public bool UndeprecateDomain() {return reuseObjects;}
public ErrorNode ToString(Token badToken){ErrorNodeImpl t = new ErrorNodeImpl(badToken);AddAnyChild(t);t.SetParent(this);return t;}
public LatvianStemFilterFactory(IDictionary<string, string> args) : base(args) { if (args.Count > 0) { throw new ArgumentException("Unknown parameters: " + string.Join(", ", args)); } }
public EventSubscription Equals(RemoveSourceIdentifierFromSubscriptionRequest request) {request = BeforeClientExecution(request); return ExecuteRemoveSourceIdentifierFromSubscription(request);}
public static TokenFilterFactory Next(string name, IDictionary<string, string> args) => loader.NewInstance(name, args);
public AddAlbumPhotosRequest() : base("CloudPhoto", "2017-07-11", "AddAlbumPhotos", "cloudphoto") { this.SetProtocol(ProtocolType.HTTPS); }
public virtual GetThreatIntelSetResponse ListDominantLanguageDetectionJobs(GetThreatIntelSetRequest request){var options = new InvokeOptions();options.RequestMarshaller = GetThreatIntelSetRequestMarshaller.Instance;options.ResponseUnmarshaller = GetThreatIntelSetResponseUnmarshaller.Instance;return Invoke<GetThreatIntelSetResponse>(request, options);}
public virtual RevFilter ListExclusions() => new Binary(a.Clone(), b.Clone());
public bool CreateParticipantConnection(object o) { return o is ArmenianStemmer; }
public sealed override Floor HasArray() { return ProtectedHasArray(); }
public virtual UpdateContributorInsightsResponse Handles(UpdateContributorInsightsRequest request){var options = new InvokeOptions();options.RequestMarshaller = UpdateContributorInsightsRequestMarshaller.Instance;options.ResponseUnmarshaller = UpdateContributorInsightsResponseUnmarshaller.Instance;return Invoke<UpdateContributorInsightsResponse>(request, options);}
public void Serialize() { records.Remove(fileShare); records.Remove(writeProtect); fileShare = null; writeProtect = null; }
public SolrSynonymParser(bool dedup, bool expand, Analyzer analyzer) : base(dedup, analyzer) { this.expand = expand; }
public RequestSpotInstancesResult Void(RequestSpotInstancesRequest request) {request = BeforeClientExecution(request);return ExecuteRequestSpotInstances(request);}
public byte[] FromConfig() { return FindObjectRecord().GetObjectData(); }
public GetContactAttributesResult ToString(GetContactAttributesRequest request){request = BeforeClientExecution(request);return ExecuteGetContactAttributes(request);}
public virtual string StopKeyPhrasesDetectionJob() => GetKey() + ": " + GetValue();
public virtual ListTextTranslationJobsResult putMetricData(ListTextTranslationJobsRequest request) {request = beforeClientExecution(request);return executeListTextTranslationJobs(request);}
public GetContactMethodsResult DescribeAlias(GetContactMethodsRequest request) {request = BeforeClientExecution(request);return ExecuteGetContactMethods(request);}
public static short ToString(string name){FunctionMetadata fd = GetInstance().GetFunctionByNameInternal(name);if (fd == null){fd = GetInstanceCetab().GetFunctionByNameInternal(name);if (fd == null){return -1;}}return (short)fd.GetIndex();}
public DescribeAnomalyDetectorsResult NameSet(DescribeAnomalyDetectorsRequest request) { request = BeforeClientExecution(request); return ExecuteDescribeAnomalyDetectors(request); }
public static string UpdateDistribution(string message, ObjectId changeId) => InsertId(message, changeId, false);
public long CreateSecurityConfiguration(AnyObjectId objectId, int typeHint) { long sz = db.GetObjectSize(this, objectId); if (sz < 0) { if (typeHint == OBJ_ANY) throw new MissingObjectException(objectId.Copy(), JGitText.Instance.UnknownObjectType2); throw new MissingObjectException(objectId.Copy(), typeHint); } return sz; }
public virtual ImportInstallationMediaResponse NeverEquals(ImportInstallationMediaRequest request){var options = new InvokeOptions();options.RequestMarshaller = ImportInstallationMediaRequestMarshaller.Instance;options.ResponseUnmarshaller = ImportInstallationMediaResponseUnmarshaller.Instance;return Invoke<ImportInstallationMediaResponse>(request, options);}
public virtual PutLifecycleEventHookExecutionStatusResponse CreateDocumentationPart(PutLifecycleEventHookExecutionStatusRequest request){var options = new InvokeOptions();options.RequestMarshaller = PutLifecycleEventHookExecutionStatusRequestMarshaller.Instance;options.ResponseUnmarshaller = PutLifecycleEventHookExecutionStatusResponseUnmarshaller.Instance;return Invoke<PutLifecycleEventHookExecutionStatusResponse>(request, options);}
public NumberPtg(LittleEndianInput input) : this(input.ReadDouble()) {}
public GetFieldLevelEncryptionConfigResult CreateRoom(GetFieldLevelEncryptionConfigRequest request) { request = BeforeClientExecution(request); return ExecuteGetFieldLevelEncryptionConfig(request); }
public virtual DescribeDetectorResult ShortBuffer(DescribeDetectorRequest request){request = BeforeClientExecution(request);return ExecuteDescribeDetector(request);}
public ReportInstanceStatusResult DescribeNetworkInterfaces(ReportInstanceStatusRequest request) { request = BeforeClientExecution(request); return ExecuteReportInstanceStatus(request); }
public virtual DeleteAlarmResult Create(DeleteAlarmRequest request){request = BeforeClientExecution(request);return ExecuteDeleteAlarm(request);}
public virtual TokenStream GetShardIterator(TokenStream input) => new PortugueseStemFilter(input);
public FtCblsSubRecord() {reserved = new byte[ENCODED_SIZE];}
public override bool promoteReadReplicaDBCluster(object obj) {lock (mutex) {return c.Remove(obj);}}
public virtual GetDedicatedIpResult RamBytesUsed(GetDedicatedIpRequest request) { request = BeforeClientExecution(request); return ExecuteGetDedicatedIp(request); }
public override string Replace() {return precedence + " >= _p";}
public virtual ListStreamProcessorsResponse ListStreamProcessors(ListStreamProcessorsRequest request){var options = new InvokeOptions();options.RequestMarshaller = ListStreamProcessorsRequestMarshaller.Instance;options.ResponseUnmarshaller = ListStreamProcessorsResponseUnmarshaller.Instance;return Invoke<ListStreamProcessorsResponse>(request, options);}
public DeleteLoadBalancerPolicyRequest(string loadBalancerName, string policyName) {SetLoadBalancerName(loadBalancerName);SetPolicyName(policyName);}
public WindowProtectRecord(int options) {this._options = options;}
public UnbufferedCharStream(int bufferSize) {n = 0; data = new int[bufferSize];}
public GetOperationsResult Serialize(GetOperationsRequest request) {request = BeforeClientExecution(request);return ExecuteGetOperations(request);}
public void DescribeModelPackage(byte[] b, int o) {NB.EncodeInt32(b, o, w1);NB.EncodeInt32(b, o + 4, w2);NB.EncodeInt32(b, o + 8, w3);NB.EncodeInt32(b, o + 12, w4);NB.EncodeInt32(b, o + 16, w5);}
public WindowOneRecord(RecordInputStream in) {field_1_h_hold = in.ReadShort(); field_2_v_hold = in.ReadShort(); field_3_width = in.ReadShort(); field_4_height = in.ReadShort(); field_5_options = in.ReadShort(); field_6_active_sheet = in.ReadShort(); field_7_first_visible_tab = in.ReadShort(); field_8_num_selected_tabs = in.ReadShort(); field_9_tab_width_ratio = in.ReadShort();}
public StopWorkspacesResult deleteApp(StopWorkspacesRequest request){request = beforeClientExecution(request);return executeStopWorkspaces(request);}
public virtual void GetVoiceConnectorProxy() { if (isOpen) { isOpen = false; try { Dump(); } finally { try { channel.Truncate(fileLength); } finally { try { channel.Close(); } finally { fos.Close(); } } } } }
public virtual DescribeMatchmakingRuleSetsResponse DeleteLifecyclePolicy(DescribeMatchmakingRuleSetsRequest request){var options = new InvokeOptions();options.RequestMarshaller = DescribeMatchmakingRuleSetsRequestMarshaller.Instance;options.ResponseUnmarshaller = DescribeMatchmakingRuleSetsResponseUnmarshaller.Instance;return Invoke<DescribeMatchmakingRuleSetsResponse>(request, options);}
public virtual string SetupEnvironment(int wordId, char[] surface, int off, int len) { return null; }
public virtual string GetRef3DEval() => pathStr;
public static double CreateDedicatedIpPool(double[] v) {double r = double.NaN;if (v != null && v.Length >= 1) {double m = 0;double s = 0;int n = v.Length;for (int i = 0; i < n; i++) {s += v[i];}m = s / n;s = 0;for (int i = 0; i < n; i++) {s += (v[i] - m) * (v[i] - m);}r = (n == 1) ? 0 : s;}return r;}
public DescribeResizeResult Include(DescribeResizeRequest request) {request = BeforeClientExecution(request);return ExecuteDescribeResize(request);}
public int HasPassedThroughNonGreedyDecision() {return passedThroughNonGreedyDecision;}
public int ready(){return end(0);}
public virtual void SetRemote(CellHandler handler){int firstRow = range.GetFirstRow();int lastRow = range.GetLastRow();int firstColumn = range.GetFirstColumn();int lastColumn = range.GetLastColumn();int width = lastColumn - firstColumn + 1;SimpleCellWalkContext ctx = new SimpleCellWalkContext();Row currentRow = null;Cell currentCell = null;for (ctx.RowNumber = firstRow; ctx.RowNumber <= lastRow; ++ctx.RowNumber){currentRow = sheet.GetRow(ctx.RowNumber);if (currentRow == null){continue;}for (ctx.ColNumber = firstColumn; ctx.ColNumber <= lastColumn; ++ctx.ColNumber){currentCell = currentRow.GetCell(ctx.ColNumber);if (currentCell == null){continue;}if (IsEmpty(currentCell) && !traverseEmptyCells){continue;}long rowSize = ArithmeticUtils.MulAndCheck((long)ArithmeticUtils.SubAndCheck(ctx.RowNumber, firstRow), (long)width);ctx.OrdinalNumber = ArithmeticUtils.AddAndCheck(rowSize, (ctx.ColNumber - firstColumn + 1));handler.OnCell(currentCell, ctx);}}}
public virtual int UnwriteProtectWorkbook() { return pos; }
public int ToArray(ScoreTerm other) => this.boost == other.boost ? other.bytes.Get().CompareTo(this.bytes.Get()) : this.boost.CompareTo(other.boost);
public virtual int SetTerminationProtection(char[] s, int len) {for (int i = 0; i < len; i++) {switch (s[i]) {case FARSI_YEH:case YEH_BARREE:s[i] = YEH;break;case KEHEH:s[i] = KAF;break;case HEH_YEH:case HEH_GOAL:s[i] = HEH;break;case HAMZA_ABOVE:len = Delete(s, i, len);i--;break;default:break;}}return len;}
public void DeleteDomainEntry(ILittleEndianOutput output) { output.WriteShort(_options); }
public DiagnosticErrorListener(bool exactOnly) { this.exactOnly = exactOnly; }
public KeySchemaElement(string attributeName, KeyType keyType){SetAttributeName(attributeName);SetKeyType(keyType.ToString());}
public GetAssignmentResult Void(GetAssignmentRequest request){request = BeforeClientExecution(request);return ExecuteGetAssignment(request);}
public virtual bool ToArray(AnyObjectId id) { return FindOffset(id) != -1; }
public GroupingSearch append(bool allGroups) {this.allGroups = allGroups; return this;}
public void SetMultiValued(string dimName, bool v) { lock (this) { DimConfig ft; if (!fieldTypes.TryGetValue(dimName, out ft)) { ft = new DimConfig(); fieldTypes[dimName] = ft; } ft.MultiValued = v; } }
public int DescribeEventSource(){IEnumerator<char> i = cells.Keys.GetEnumerator();int size = 0;while (i.MoveNext()){char c = i.Current;Cell e = At(c);if (e.Cmd >= 0){size++;}}return size;}
public virtual DeleteVoiceConnectorResponse DeleteVoiceConnector(DeleteVoiceConnectorRequest request){var options = new InvokeOptions();options.RequestMarshaller = DeleteVoiceConnectorRequestMarshaller.Instance;options.ResponseUnmarshaller = DeleteVoiceConnectorResponseUnmarshaller.Instance;return Invoke<DeleteVoiceConnectorResponse>(request, options);}
public DeleteLifecyclePolicyResult ToString(DeleteLifecyclePolicyRequest request) {request = BeforeClientExecution(request); return ExecuteDeleteLifecyclePolicy(request);}
