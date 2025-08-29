public void Serialize(LittleEndianOutput @out) { @out.WriteShort(field_1_vcenter); }
public void AddAll(BlockList<T> src) { if (src.size == 0) return; int srcDirIdx = 0; for (; srcDirIdx < src.tailDirIdx; srcDirIdx++) AddAll(src.directory[srcDirIdx], 0, BLOCK_SIZE); if (src.tailBlkIdx != 0) AddAll(src.tailBlock, 0, src.tailBlkIdx); }
public virtual void writeByte(byte b) { if (upto == blockSize) { if (currentBlock != null) { addBlock(currentBlock); } currentBlock = new byte[blockSize]; upto = 0; } currentBlock[upto++] = b; }
public ObjectId GetObjectId() { return objectId; }
public DeleteDomainEntryResult DeleteDomainEntry(DeleteDomainEntryRequest request) { request = BeforeClientExecution(request); return ExecuteDeleteDomainEntry(request); }
public long RamBytesUsed() { return (termOffsets != null ? termOffsets.RamBytesUsed() : 0) + (termsDictOffsets != null ? termsDictOffsets.RamBytesUsed() : 0); }
public virtual string GetFullMessage(){byte[] raw = buffer;int msgB = RawParseUtils.TagMessage(raw, 0);return msgB < 0 ? "" : RawParseUtils.Decode(GuessEncoding(), raw, msgB, raw.Length);}
public POIFSFileSystem() : this(true) { _header.setBATCount(1); _header.setBATArray(new int[] { 1 }); BATBlock bb = BATBlock.createEmptyBATBlock(bigBlockSize, false); bb.setOurBlockIndex(1); _bat_blocks.Add(bb); setNextBlock(0, POIFSConstants.END_OF_CHAIN); setNextBlock(1, POIFSConstants.FAT_SECTOR_BLOCK); _property_table.setStartBlock(0); }
public void Init(int address) { slice = pool.Buffers[(address >> ByteBlockPool.ByteBlockShift)]; Debug.Assert(slice != null); upto = address & ByteBlockPool.ByteBlockMask; offset0 = address; Debug.Assert(upto < slice.Length); }
public SubmoduleAddCommand SetPath(String path) { this.path = path; return this; }
public virtual ListIngestionsResponse ListIngestions(ListIngestionsRequest request){var options = new InvokeOptions();options.RequestMarshaller = ListIngestionsRequestMarshaller.Instance;options.ResponseUnmarshaller = ListIngestionsResponseUnmarshaller.Instance;return Invoke<ListIngestionsResponse>(request, options);}
public QueryParserTokenManager(CharStream stream, int lexState) { this(stream); SwitchTo(lexState); }
public virtual GetShardIteratorResponse GetShardIterator(GetShardIteratorRequest request) { request = BeforeClientExecution(request); return ExecuteGetShardIterator(request); }
public ModifyStrategyRequest() : base("aegis", "2016-11-11", "ModifyStrategy", "vipaegis") { Method = MethodType.POST; }
public bool Ready() { lock (lockObj) { if (inStream == null) throw new IOException("InputStreamReader is closed"); try { return bytes.HasRemaining || inStream.Available() > 0; } catch (IOException) { return false; } } }
public EscherOptRecord GetOptRecord() { return _optRecord; }
public virtual int Read(byte[] buffer, int offset, int length) { if (buffer == null) throw new ArgumentNullException(nameof(buffer)); if (offset < 0 || length < 0 || offset + length > buffer.Length) throw new ArgumentException("Invalid offset or length"); if (length == 0) return 0; int copylen = Math.Min(count - pos, length); for (int i = 0; i < copylen; i++) buffer[offset + i] = (byte)this.buffer[pos + i]; pos += copylen; return copylen; }
public OpenNLPSentenceBreakIterator(NLPSentenceDetectorOp sentenceOp) { this.sentenceOp = sentenceOp; }
public void Print(string str) => Write(str != null ? str : ((object)null).ToString());
public NotImplementedFunctionException(string functionName, NotImplementedException cause) : base(functionName, cause) { this.functionName = functionName; }
public V Next() => base.NextEntry().GetValue();
public void ReadBytes(byte[] b, int offset, int len, bool useBuffer) {      int available = bufferLength - bufferPosition;      if (len <= available) {          if (len > 0) Array.Copy(buffer, bufferPosition, b, offset, len);          bufferPosition += len;      } else {          if (available > 0) {              Array.Copy(buffer, bufferPosition, b, offset, available);              offset += available;              len -= available;              bufferPosition += available;          }          if (useBuffer && len < bufferSize) {              Refill();              if (bufferLength < len) {                  Array.Copy(buffer, 0, b, offset, bufferLength);                  throw new EndOfStreamException("read past EOF: " + this);              } else {                  Array.Copy(buffer, 0, b, offset, len);                  bufferPosition = len;              }          } else {              long after = bufferStart + bufferPosition + len;              if (after > Length())                  throw new EndOfStreamException("read past EOF: " + this);              ReadInternal(b, offset, len);              bufferStart = after;              bufferPosition = 0;              bufferLength = 0;          }      }  }
public virtual TagQueueResponse TagQueue(TagQueueRequest request){var options = new InvokeOptions();options.RequestMarshaller = TagQueueRequestMarshaller.Instance;options.ResponseUnmarshaller = TagQueueResponseUnmarshaller.Instance;return Invoke<TagQueueResponse>(request, options);}
public void Remove() { throw new NotSupportedException(); }
public virtual CacheSubnetGroup ModifyCacheSubnetGroup(ModifyCacheSubnetGroupRequest request) { var modifiedRequest = beforeClientExecution(request); return executeModifyCacheSubnetGroup(modifiedRequest); }
public void SetParams(string @params) { base.SetParams(@params); language = country = variant = ""; var st = new StringSplitOptions(',', @params); var tokens = st.Split(new[] { ',' }, StringSplitOptions.RemoveEmptyEntries); if (tokens.Length > 0) language = tokens[0]; if (tokens.Length > 1) country = tokens[1]; if (tokens.Length > 2) variant = tokens[2]; }
public DeleteDocumentationVersionResult DeleteDocumentationVersion(DeleteDocumentationVersionRequest request) { request = BeforeClientExecution(request); return ExecuteDeleteDocumentationVersion(request); }
public override bool Equals(object obj) { if (!(obj is FacetLabel other)) return false; if (length != other.length) return false; for (int i = length - 1; i >= 0; i--) { if (!components[i].Equals(other.components[i])) return false; } return true; }
public virtual GetInstanceAccessDetailsResponse GetInstanceAccessDetails(GetInstanceAccessDetailsRequest request) { request = BeforeClientExecution(request); return ExecuteGetInstanceAccessDetails(request); }
public HSSFPolygon CreatePolygon(HSSFChildAnchor anchor) { var shape = new HSSFPolygon(this, anchor); shape.SetParent(this); shape.SetAnchor(anchor); shapes.Add(shape); OnCreate(shape); return shape; }
public string GetSheetName(int sheetIndex) => GetBoundSheetRec(sheetIndex).GetSheetName();
public virtual GetDashboardResponse GetDashboard(GetDashboardRequest request) { var options = new InvokeOptions(); options.RequestMarshaller = GetDashboardRequestMarshaller.Instance; options.ResponseUnmarshaller = GetDashboardResponseUnmarshaller.Instance; return Invoke<GetDashboardResponse>(request, options); }
public virtual AssociateSigninDelegateGroupsWithAccountResponse AssociateSigninDelegateGroupsWithAccount(AssociateSigninDelegateGroupsWithAccountRequest request) => ExecuteAssociateSigninDelegateGroupsWithAccount(BeforeClientExecution(request));
public void AddMultipleBlanks(MulBlankRecord mbr) { for (int j = 0; j < mbr.GetNumColumns(); j++) { BlankRecord br = new BlankRecord(); br.Column = (short)(j + mbr.GetFirstColumn()); br.Row = mbr.GetRow(); br.XFIndex = mbr.GetXFAt(j); InsertCell(br); } }
public static string Quote(string @string) { var sb = new StringBuilder(); sb.Append("\\Q"); int apos = 0; int k; while ((k = @string.IndexOf("\\E", apos)) >= 0) { sb.Append(@string.Substring(apos, k + 2 - apos)).Append("\\\\E\\Q"); apos = k + 2; } return sb.Append(@string.Substring(apos)).Append("\\E").ToString(); }
public ByteBuffer PutInt(int value) { throw new ReadOnlyBufferException(); }
public ArrayPtg(Object[][] values2d) { int nColumns = values2d[0].Length; int nRows = values2d.Length; _nColumns = (short)nColumns; _nRows = (short)nRows; Object[] vv = new Object[_nColumns * _nRows]; for (int r = 0; r < nRows; r++) { Object[] rowData = values2d[r]; for (int c = 0; c < nColumns; c++) { vv[getValueIndex(c, r)] = rowData[c]; } } _arrayValues = vv; _reserved0Int = 0; _reserved1Short = 0; _reserved2Byte = 0; }
public GetIceServerConfigResult GetIceServerConfig(GetIceServerConfigRequest request) { request = BeforeClientExecution(request); return ExecuteGetIceServerConfig(request); }
public override string ToString() => GetType().Name + " [" + GetValueAsString() + "]";
public string ToString(string field) { return $"ToChildBlockJoinQuery ({parentQuery.ToString()})"; }
public void IncRef() => Interlocked.Increment(ref refCount);
public UpdateConfigurationSetSendingEnabledResponse UpdateConfigurationSetSendingEnabled(UpdateConfigurationSetSendingEnabledRequest request) { request = BeforeClientExecution(request); return ExecuteUpdateConfigurationSetSendingEnabled(request); }
public virtual int GetNextXBATChainOffset() { return GetXBATEntriesPerBlock() * LittleEndianConsts.IntSize; }
public void MultiplyByPowerOfTen(int pow10) { TenPower tp = TenPower.GetInstance(Math.Abs(pow10)); if (pow10 < 0) { MulShift(tp._divisor, tp._divisorShift); } else { MulShift(tp._multiplicand, tp._multiplierShift); } }
public override string ToString() { var b = new StringBuilder(); var l = Length; b.Append(Path.DirectorySeparatorChar); for (int i = 0; i < l; i++) { b.Append(GetComponent(i)); if (i < l - 1) b.Append(Path.DirectorySeparatorChar); } return b.ToString(); }
public InstanceProfileCredentialsProvider WithFetcher(ECSMetadataServiceCredentialsFetcher fetcher) { this.fetcher = fetcher; this.fetcher.RoleName = roleName; return this; }
public virtual void SetProgressMonitor(ProgressMonitor pm) { progressMonitor = pm; }
public void Reset() { if (!First()) { ptr = 0; if (!Eof()) ParseEntry(); } }
public virtual E Previous(){if (iterator.PreviousIndex() >= start){return iterator.Previous();}throw new InvalidOperationException("NoSuchElementException");}
public string GetNewPrefix() { return this.newPrefix; }
public int IndexOfValue(int value) { for (int i = 0; i < mSize; i++) if (mValues[i] == value) return i; return -1; }
public List<CharsRef> UniqueStems(char[] word, int length) {      List<CharsRef> stems = Stem(word, length);      if (stems.Count < 2) {          return stems;      }      CharArraySet terms = new CharArraySet(8, dictionary.IgnoreCase);      List<CharsRef> deduped = new List<CharsRef>();      foreach (CharsRef s in stems) {          if (!terms.Contains(s)) {              deduped.Add(s);              terms.Add(s);          }      }      return deduped;  }
public GetGatewayResponsesResult GetGatewayResponses(GetGatewayResponsesRequest request) { request = BeforeClientExecution(request); return ExecuteGetGatewayResponses(request); }
public virtual void SetPosition(long pos) { currentBlockIndex = (int)(pos >> blockBits); currentBlock = blocks[currentBlockIndex]; currentBlockUpto = (int)(pos & blockMask); }
public long Skip(long n) { int s = (int)Math.Min(Available(), Math.Max(0, n)); ptr += s; return s; }
public BootstrapActionDetail(BootstrapActionConfig bootstrapActionConfig) { this.BootstrapActionConfig = bootstrapActionConfig; }
public void Serialize(ILittleEndianOutput out1) { out1.WriteShort(field_1_row); out1.WriteShort(field_2_col); out1.WriteShort(field_3_flags); out1.WriteShort(field_4_shapeid); out1.WriteShort((short)field_6_author.Length); out1.WriteByte(field_5_hasMultibyte ? (byte)0x01 : (byte)0x00); if (field_5_hasMultibyte) StringUtil.PutUnicodeLE(field_6_author, out1); else StringUtil.PutCompressedUnicode(field_6_author, out1); if (field_7_padding.HasValue) out1.WriteByte((byte)field_7_padding); }
public int LastIndexOf(string str) { return LastIndexOf(str, count); }
public virtual bool Add(E object) => AddLastImpl(object);
public void UnsetSection(string section, string subsection) { ConfigSnapshot src, res; do { src = state.Get(); res = UnsetSection(src, section, subsection); } while (!state.CompareAndSet(src, res)); }
public string TagName => tagName;
public virtual void AddSubRecord(int index, SubRecord element) { subrecords.Insert(index, element); }
public virtual bool Remove(object o) { lock (mutex) { return delegate().Remove(o); } }
public DoubleMetaphoneFilter Create(TokenStream input) => new DoubleMetaphoneFilter(input, maxCodeLength, inject);
public virtual long Length() { return InCoreLength(); }
public void SetValue(bool newValue) { value = newValue; }
public Pair(ContentSource oldSource, ContentSource newSource) { this.oldSource = oldSource; this.newSource = newSource; }
public int Get(int i) { if (count <= i) throw new IndexOutOfRangeException(); return entries[i]; }
public CreateRepoRequest() : base("cr", "2016-06-07", "CreateRepo", "cr") { UriPattern = "/repos"; Method = MethodType.PUT; }
public virtual bool IsDeltaBaseAsOffset() { return deltaBaseAsOffset; }
public void Remove(){if (expectedModCount == list.modCount){if (lastLink != null){Link<ET> next = lastLink.next;Link<ET> previous = lastLink.previous;next.previous = previous;previous.next = next;if (lastLink == link){pos--;};link = previous;lastLink = null;expectedModCount++;list.size--;list.modCount++;}else{throw new InvalidOperationException();}}else{throw new InvalidOperationException();}}
public virtual MergeShardsResponse MergeShards(MergeShardsRequest request) { return ExecuteMergeShards(BeforeClientExecution(request)); }
public virtual AllocateHostedConnectionResponse AllocateHostedConnection(AllocateHostedConnectionRequest request) { var options = new InvokeOptions(); options.RequestMarshaller = AllocateHostedConnectionRequestMarshaller.Instance; options.ResponseUnmarshaller = AllocateHostedConnectionResponseUnmarshaller.Instance; return Invoke<AllocateHostedConnectionResponse>(request, options); }
public int GetBeginIndex() { return start; }
public static WeightedTerm[] GetTerms(Query query) => GetTerms(query, false);
public ByteBuffer Compact() => throw new ReadOnlyBufferException();
public void Decode(byte[] blocks, int blocksOffset, long[] values, int valuesOffset, int iterations) { for (int i = 0; i < iterations; ++i) { long byte0 = blocks[blocksOffset++] & 0xFF; values[valuesOffset++] = byte0 >>> 2; long byte1 = blocks[blocksOffset++] & 0xFF; values[valuesOffset++] = ((byte0 & 3) << 4) | (byte1 >>> 4); long byte2 = blocks[blocksOffset++] & 0xFF; values[valuesOffset++] = ((byte1 & 0xF) << 2) | (byte2 >>> 6); values[valuesOffset++] = byte2 & 0x3F; } }
public string GetHumanishName(){string s = GetPath();if("/".Equals(s) || "".Equals(s))s = GetHost();if(s == null)throw new ArgumentException();string[] elements;if("file".Equals(scheme) || LOCAL_FILE.IsMatch(s))elements = s.Split(new[]{'\\', Path.DirectorySeparatorChar, '/'}, StringSplitOptions.RemoveEmptyEntries);else elements = s.Split(new[]{'/'}, StringSplitOptions.RemoveEmptyEntries);if(elements.Length == 0)throw new ArgumentException();string result = elements[elements.Length - 1];if(Constants.DOT_GIT.Equals(result))result = elements[elements.Length - 2];else if(result.EndsWith(Constants.DOT_GIT_EXT))result = result.Substring(0, result.Length - Constants.DOT_GIT_EXT.Length);return result;}
public DescribeNotebookInstanceLifecycleConfigResult DescribeNotebookInstanceLifecycleConfig(DescribeNotebookInstanceLifecycleConfigRequest request) { request = BeforeClientExecution(request); return ExecuteDescribeNotebookInstanceLifecycleConfig(request); }
public virtual string GetAccessKeySecret() { return this.accessKeySecret; }
public CreateVpnConnectionResult CreateVpnConnection(CreateVpnConnectionRequest request) { request = BeforeClientExecution(request); return ExecuteCreateVpnConnection(request); }
public virtual DescribeVoicesResponse DescribeVoices(DescribeVoicesRequest request) { request = BeforeClientExecution(request); return ExecuteDescribeVoices(request); }
public virtual ListMonitoringExecutionsResponse ListMonitoringExecutions(ListMonitoringExecutionsRequest request) { request = BeforeClientExecution(request); return ExecuteListMonitoringExecutions(request); }
public DescribeJobRequest(string vaultName, string jobId) { VaultName = vaultName; JobId = jobId; }
public EscherRecord GetEscherRecord(int index) => escherRecords[index];
public virtual GetApisResponse GetApis(GetApisRequest request) { var options = new InvokeOptions(); options.RequestMarshaller = GetApisRequestMarshaller.Instance; options.ResponseUnmarshaller = GetApisResponseUnmarshaller.Instance; return Invoke<GetApisResponse>(request, options); }
public virtual DeleteSmsChannelResult DeleteSmsChannel(DeleteSmsChannelRequest request) { request = BeforeClientExecution(request); return ExecuteDeleteSmsChannel(request); }
public TrackingRefUpdate getTrackingRefUpdate(){return _trackingRefUpdate;}
public void Print(bool b) { Print(b.ToString()); }
public QueryNode GetChild() { return GetChildren()[0]; }
public NotIgnoredFilter(int workdirTreeIndex) { this.index = workdirTreeIndex; }
public AreaRecord(RecordInputStream in) { field_1_formatFlags = in.ReadShort(); }
public GetThumbnailRequest() : base("CloudPhoto", "2017-07-11", "GetThumbnail", "cloudphoto") { setProtocol(ProtocolType.HTTPS); }
public virtual DescribeTransitGatewayVpcAttachmentsResponse DescribeTransitGatewayVpcAttachments(DescribeTransitGatewayVpcAttachmentsRequest request) { request = BeforeClientExecution(request); return ExecuteDescribeTransitGatewayVpcAttachments(request); }
public virtual PutVoiceConnectorStreamingConfigurationResponse PutVoiceConnectorStreamingConfiguration(PutVoiceConnectorStreamingConfigurationRequest request) => ExecutePutVoiceConnectorStreamingConfiguration(BeforeClientExecution(request));
public virtual OrdRange GetOrdRange(string dim) => prefixToOrdRange.GetValue(dim);
public override string ToString() => string.Format(CultureInfo.CurrentCulture, "{0}('{1}')", typeof(LexerNoViableAltException).Name, Utils.EscapeWhitespace(getInputStream().GetText(new Interval(startIndex, startIndex)), false));
public E Peek() { return PeekFirstImpl(); }
public virtual CreateWorkspacesResponse CreateWorkspaces(CreateWorkspacesRequest request) { var options = new InvokeOptions(); options.RequestMarshaller = CreateWorkspacesRequestMarshaller.Instance; options.ResponseUnmarshaller = CreateWorkspacesResponseUnmarshaller.Instance; return Invoke<CreateWorkspacesResponse>(request, options); }
public virtual NumberFormatIndexRecord clone() { return copy(); }
public virtual DescribeRepositoriesResponse DescribeRepositories(DescribeRepositoriesRequest request) { var options = new InvokeOptions(); options.RequestMarshaller = DescribeRepositoriesRequestMarshaller.Instance; options.ResponseUnmarshaller = DescribeRepositoriesResponseUnmarshaller.Instance; return Invoke<DescribeRepositoriesResponse>(request, options); }
public SparseIntArray(int initialCapacity) { initialCapacity = ArrayUtils.IdealIntArraySize(initialCapacity); mKeys = new int[initialCapacity]; mValues = new int[initialCapacity]; mSize = 0; }
public HyphenatedWordsFilter Create(TokenStream input) => new HyphenatedWordsFilter(input);
public virtual CreateDistributionWithTagsResponse CreateDistributionWithTags(CreateDistributionWithTagsRequest request) { return ExecuteCreateDistributionWithTags(BeforeClientExecution(request)); }
public RandomAccessFile(string fileName, string mode) : this(new FileInfo(fileName), mode) { }
public virtual DeleteWorkspaceImageResponse DeleteWorkspaceImage(DeleteWorkspaceImageRequest request){request = BeforeClientExecution(request);return ExecuteDeleteWorkspaceImage(request);}
public static string ToHex(long value) { var sb = new StringBuilder(16); WriteHex(sb, value, 16, ""); return sb.ToString(); }
public UpdateDistributionResult UpdateDistribution(UpdateDistributionRequest request) => ExecuteUpdateDistribution(BeforeClientExecution(request));
public virtual HSSFColor GetColor(short index) => index == HSSFColorPredefined.Automatic.Index ? HSSFColorPredefined.Automatic.Color : _palette.GetColor(index)?.Let(b => new CustomColor(index, b));
public ValueEval Evaluate(ValueEval[] operands, int srcRow, int srcCol) => throw new NotImplementedFunctionException(_functionName);
public virtual void Serialize(ILittleEndianOutput @out) { @out.WriteShort((short)field_1_number_crn_records); @out.WriteShort((short)field_2_sheet_table_index); }
public virtual DescribeDBEngineVersionsResponse DescribeDBEngineVersions(DescribeDBEngineVersionsRequest request = null) => DescribeDBEngineVersions(request ?? new DescribeDBEngineVersionsRequest());
public FormatRun(short character, short fontIndex) { this._character = character; this._fontIndex = fontIndex; }
public static byte[] ToBigEndianUtf16Bytes(char[] chars, int offset, int length) { byte[] result = new byte[length * 2]; int end = offset + length; int resultIndex = 0; for (int i = offset; i < end; ++i) { char ch = chars[i]; result[resultIndex++] = (byte)(ch >> 8); result[resultIndex++] = (byte)ch; } return result; }
public virtual UploadArchiveResponse UploadArchive(UploadArchiveRequest request){var options = new InvokeOptions();options.RequestMarshaller = UploadArchiveRequestMarshaller.Instance;options.ResponseUnmarshaller = UploadArchiveResponseUnmarshaller.Instance;return Invoke<UploadArchiveResponse>(request, options);}
public virtual List<Token> GetHiddenTokensToLeft(int tokenIndex) => GetHiddenTokensToLeft(tokenIndex, -1);
public override bool Equals(object obj) { if (this == obj) return true; if (!base.Equals(obj)) return false; if (GetType() != obj.GetType()) return false; AutomatonQuery other = (AutomatonQuery)obj; if (!compiled.Equals(other.compiled)) return false; if (term == null) { if (other.term != null) return false; } else if (!term.Equals(other.term)) return false; return true; }
public SpanQuery MakeSpanClause()  {     SpanQuery[] spanQueries = new SpanQuery[size()];     int i = 0;     foreach (var sq in weightBySpanQuery.Keys)     {         float boost = weightBySpanQuery[sq];         SpanQuery query = boost != 1f ? new SpanBoostQuery(sq, boost) : sq;         spanQueries[i++] = query;     }     return spanQueries.Length == 1 ? spanQueries[0] : new SpanOrQuery(spanQueries); }
public StashCreateCommand StashCreate() { return new StashCreateCommand(repo); }
public FieldInfo FieldInfo(string fieldName) { return byName[fieldName]; }
public DescribeEventSourceResult DescribeEventSource(DescribeEventSourceRequest request) => ExecuteDescribeEventSource(BeforeClientExecution(request));
public GetDocumentAnalysisResult GetDocumentAnalysis(GetDocumentAnalysisRequest request) { request = BeforeClientExecution(request); return ExecuteGetDocumentAnalysis(request); }
public CancelUpdateStackResult CancelUpdateStack(CancelUpdateStackRequest request) { request = BeforeClientExecution(request); return ExecuteCancelUpdateStack(request); }
public virtual ModifyLoadBalancerAttributesResponse ModifyLoadBalancerAttributes(ModifyLoadBalancerAttributesRequest request){var options = new InvokeOptions();options.RequestMarshaller = ModifyLoadBalancerAttributesRequestMarshaller.Instance;options.ResponseUnmarshaller = ModifyLoadBalancerAttributesResponseUnmarshaller.Instance;return Invoke<ModifyLoadBalancerAttributesResponse>(request, options);}
public virtual SetInstanceProtectionResponse SetInstanceProtection(SetInstanceProtectionRequest request) { var options = new InvokeOptions(); options.RequestMarshaller = SetInstanceProtectionRequestMarshaller.Instance; options.ResponseUnmarshaller = SetInstanceProtectionResponseUnmarshaller.Instance; return Invoke<SetInstanceProtectionResponse>(request, options); }
public ModifyDBProxyResult ModifyDBProxy(ModifyDBProxyRequest request) { request = BeforeClientExecution(request); return ExecuteModifyDBProxy(request); }
public void Add(char[] output, int offset, int len, int endOffset, int posLength) { if (count == outputs.Length) { outputs = ArrayUtil.Grow(outputs, count + 1); } if (count == endOffsets.Length) { int[] next = new int[ArrayUtil.Oversize(1 + count, sizeof(int))]; Array.Copy(endOffsets, 0, next, 0, count); endOffsets = next; } if (count == posLengths.Length) { int[] next = new int[ArrayUtil.Oversize(1 + count, sizeof(int))]; Array.Copy(posLengths, 0, next, 0, count); posLengths = next; } if (outputs[count] == null) { outputs[count] = new CharsRefBuilder(); } outputs[count].CopyChars(output, offset, len); endOffsets[count] = endOffset; posLengths[count] = posLength; count++; }
public FetchLibrariesRequest() : base("CloudPhoto", "2017-07-11", "FetchLibraries", "cloudphoto") { Protocol = ProtocolType.HTTPS; }
public bool Exists() { return fs.Exists(objects); }
public FilterOutputStream(System.IO.Stream outStream) { this.out = outStream; }
public ScaleClusterRequest() : base("CS", "2015-12-15", "ScaleCluster", "csk") { UriPattern = "/clusters/[ClusterId]"; Method = MethodType.PUT; }
public DataValidationConstraint CreateTimeConstraint(int operatorType, string formula1, string formula2) => DVConstraint.CreateTimeConstraint(operatorType, formula1, formula2);
public virtual ListObjectParentPathsResponse ListObjectParentPaths(ListObjectParentPathsRequest request){var options = new InvokeOptions();options.RequestMarshaller = ListObjectParentPathsRequestMarshaller.Instance;options.ResponseUnmarshaller = ListObjectParentPathsResponseUnmarshaller.Instance;return Invoke<ListObjectParentPathsResponse>(request, options);}
public DescribeCacheSubnetGroupsResult DescribeCacheSubnetGroups(DescribeCacheSubnetGroupsRequest request) { request = this.BeforeClientExecution(request); return this.ExecuteDescribeCacheSubnetGroups(request); }
public void SetSharedFormula(bool flag) { Field_5_Options = SharedFormula.SetShortBoolean(Field_5_Options, flag); }
public bool IsReuseObjects() { return reuseObjects; }
public ErrorNode AddErrorNode(Token badToken) { ErrorNodeImpl t = new ErrorNodeImpl(badToken); AddAnyChild(t); t.Parent = this; return t; }
public LatvianStemFilterFactory(Dictionary<string, string> args) : base(args) { if (args.Count > 0) { throw new ArgumentException("Unknown parameters: " + string.Join(", ", args)); } }
public virtual EventSubscription RemoveSourceIdentifierFromSubscription(RemoveSourceIdentifierFromSubscriptionRequest request){var options = new InvokeOptions();options.RequestMarshaller = RemoveSourceIdentifierFromSubscriptionRequestMarshaller.Instance;options.ResponseUnmarshaller = EventSubscriptionUnmarshaller.Instance;return Invoke<EventSubscription>(request, options);}
public static TokenFilterFactory ForName(string name, Dictionary<string, string> args) => loader.NewInstance(name, args);
public AddAlbumPhotosRequest() : base("CloudPhoto", "2017-07-11", "AddAlbumPhotos", "cloudphoto") { Protocol = ProtocolType.HTTPS; }
public virtual GetThreatIntelSetResponse GetThreatIntelSet(GetThreatIntelSetRequest request) { var options = new InvokeOptions(); options.RequestMarshaller = GetThreatIntelSetRequestMarshaller.Instance; options.ResponseUnmarshaller = GetThreatIntelSetResponseUnmarshaller.Instance; return Invoke<GetThreatIntelSetResponse>(request, options); }
public RevFilter Clone() => new Binary(a.Clone() as byte[], b.Clone() as byte[]);
public override bool Equals(object o) { return o is ArmenianStemmer; }
public virtual bool HasArray() { return ProtectedHasArray(); }
public UpdateContributorInsightsResult UpdateContributorInsights(UpdateContributorInsightsRequest request) { request = BeforeClientExecution(request); return ExecuteUpdateContributorInsights(request); }
public void UnwriteProtectWorkbook() { records.Remove(fileShare); records.Remove(writeProtect); fileShare = null; writeProtect = null; }
public SolrSynonymParser(bool dedup, bool expand, Analyzer analyzer) : base(dedup, analyzer) { this.expand = expand; }
public virtual RequestSpotInstancesResponse RequestSpotInstances(RequestSpotInstancesRequest request) => Invoke<RequestSpotInstancesResponse>(request, new InvokeOptions { RequestMarshaller = RequestSpotInstancesRequestMarshaller.Instance, ResponseUnmarshaller = RequestSpotInstancesResponseUnmarshaller.Instance });
public byte[] GetObjectData() => FindObjectRecord().GetObjectData();
public virtual GetContactAttributesResponse GetContactAttributes(GetContactAttributesRequest request) { var options = new InvokeOptions(); options.RequestMarshaller = GetContactAttributesRequestMarshaller.Instance; options.ResponseUnmarshaller = GetContactAttributesResponseUnmarshaller.Instance; return Invoke<GetContactAttributesResponse>(request, options); }
public override string ToString() { return GetKey() + ": " + GetValue(); }
public virtual ListTextTranslationJobsResponse ListTextTranslationJobs(ListTextTranslationJobsRequest request) { request = BeforeClientExecution(request); return ExecuteListTextTranslationJobs(request); }
public virtual GetContactMethodsResponse GetContactMethods(GetContactMethodsRequest request){var options = new InvokeOptions();options.RequestMarshaller = GetContactMethodsRequestMarshaller.Instance;options.ResponseUnmarshaller = GetContactMethodsResponseUnmarshaller.Instance;return Invoke<GetContactMethodsResponse>(request, options);}
public static short LookupIndexByName(string name) { FunctionMetadata fd = GetInstance().GetFunctionByNameInternal(name); if (fd == null) { fd = GetInstanceCetab().GetFunctionByNameInternal(name); if (fd == null) { return -1; } } return (short)fd.GetIndex(); }
public virtual DescribeAnomalyDetectorsResponse DescribeAnomalyDetectors(DescribeAnomalyDetectorsRequest request) { var options = new InvokeOptions(); options.RequestMarshaller = DescribeAnomalyDetectorsRequestMarshaller.Instance; options.ResponseUnmarshaller = DescribeAnomalyDetectorsResponseUnmarshaller.Instance; return Invoke<DescribeAnomalyDetectorsResponse>(request, options); }
public static string insertId(string message, ObjectId changeId) { return insertId(message, changeId, false); }
public long GetObjectSize(AnyObjectId objectId, int typeHint)  {      long sz = db.GetObjectSize(this, objectId);      if (sz < 0)      {          if (typeHint == OBJ_ANY)              throw new MissingObjectException(objectId.Copy(), JGitText.Get().unknownObjectType2);          throw new MissingObjectException(objectId.Copy(), typeHint);      }      return sz;  }
public virtual ImportInstallationMediaResponse ImportInstallationMedia(ImportInstallationMediaRequest request){var options = new InvokeOptions();options.RequestMarshaller = ImportInstallationMediaRequestMarshaller.Instance;options.ResponseUnmarshaller = ImportInstallationMediaResponseUnmarshaller.Instance;return Invoke<ImportInstallationMediaResponse>(request, options);}
public virtual PutLifecycleEventHookExecutionStatusResponse PutLifecycleEventHookExecutionStatus(PutLifecycleEventHookExecutionStatusRequest request){request = BeforeClientExecution(request);return ExecutePutLifecycleEventHookExecutionStatus(request);}
public NumberPtg(LittleEndianInput @in) : this(@in.ReadDouble()) { }
public virtual GetFieldLevelEncryptionConfigResponse GetFieldLevelEncryptionConfig(GetFieldLevelEncryptionConfigRequest request){var options = new InvokeOptions();options.RequestMarshaller = GetFieldLevelEncryptionConfigRequestMarshaller.Instance;options.ResponseUnmarshaller = GetFieldLevelEncryptionConfigResponseUnmarshaller.Instance;return Invoke<GetFieldLevelEncryptionConfigResponse>(request, options);}
public virtual DescribeDetectorResponse DescribeDetector(DescribeDetectorRequest request) { var options = new InvokeOptions(); options.RequestMarshaller = DescribeDetectorRequestMarshaller.Instance; options.ResponseUnmarshaller = DescribeDetectorResponseUnmarshaller.Instance; return Invoke<DescribeDetectorResponse>(request, options); }
public virtual ReportInstanceStatusResponse ReportInstanceStatus(ReportInstanceStatusRequest request) { var options = new InvokeOptions(); options.RequestMarshaller = ReportInstanceStatusRequestMarshaller.Instance; options.ResponseUnmarshaller = ReportInstanceStatusResponseUnmarshaller.Instance; return Invoke<ReportInstanceStatusResponse>(request, options); }
public DeleteAlarmResult DeleteAlarm(DeleteAlarmRequest request) { request = BeforeClientExecution(request); return ExecuteDeleteAlarm(request); }
public override TokenStream Create(TokenStream input) { return new PortugueseStemFilter(input); }
public FtCblsSubRecord() { reserved = new byte[ENCODED_SIZE]; }
public override bool Remove(object obj) { lock (mutex) { return c.Remove(obj); } }
public virtual GetDedicatedIpResponse GetDedicatedIp(GetDedicatedIpRequest request) { request = BeforeClientExecution(request); return ExecuteGetDedicatedIp(request); }
public override string ToString() { return precedence + " >= _p"; }
public virtual ListStreamProcessorsResponse ListStreamProcessors(ListStreamProcessorsRequest request){var options = new InvokeOptions();options.RequestMarshaller = ListStreamProcessorsRequestMarshaller.Instance;options.ResponseUnmarshaller = ListStreamProcessorsResponseUnmarshaller.Instance;return Invoke<ListStreamProcessorsResponse>(request, options);}
public DeleteLoadBalancerPolicyRequest(string loadBalancerName, string policyName) { LoadBalancerName = loadBalancerName; PolicyName = policyName; }
public WindowProtectRecord(int options) { _options = options; }
public UnbufferedCharStream(int bufferSize) { n = 0; data = new int[bufferSize]; }
public virtual GetOperationsResponse GetOperations(GetOperationsRequest request) { var options = new InvokeOptions(); options.RequestMarshaller = GetOperationsRequestMarshaller.Instance; options.ResponseUnmarshaller = GetOperationsResponseUnmarshaller.Instance; return Invoke<GetOperationsResponse>(request, options); }
public void CopyRawTo(byte[] b, int o) { NB.EncodeInt32(b, o, w1); NB.EncodeInt32(b, o + 4, w2); NB.EncodeInt32(b, o + 8, w3); NB.EncodeInt32(b, o + 12, w4); NB.EncodeInt32(b, o + 16, w5); }
public WindowOneRecord(RecordInputStream in) { _field_1HHold = in.ReadShort(); _field_2VHold = in.ReadShort(); _field_3Width = in.ReadShort(); _field_4Height = in.ReadShort(); _field_5Options = in.ReadShort(); _field_6ActiveSheet = in.ReadShort(); _field_7FirstVisibleTab = in.ReadShort(); _field_8NumSelectedTabs = in.ReadShort(); _field_9TabWidthRatio = in.ReadShort(); }
public virtual StopWorkspacesResponse StopWorkspaces(StopWorkspacesRequest request) { var options = new InvokeOptions(); options.RequestMarshaller = StopWorkspacesRequestMarshaller.Instance; options.ResponseUnmarshaller = StopWorkspacesResponseUnmarshaller.Instance; return Invoke<StopWorkspacesResponse>(request, options); }
public override void Close(){if (isOpen){isOpen = false;try{dump();}finally{try{channel.Truncate(fileLength);}finally{try{channel.Close();}finally{fos.Close();}}}}
public virtual DescribeMatchmakingRuleSetsResponse DescribeMatchmakingRuleSets(DescribeMatchmakingRuleSetsRequest request) { var options = new InvokeOptions(); options.RequestMarshaller = DescribeMatchmakingRuleSetsRequestMarshaller.Instance; options.ResponseUnmarshaller = DescribeMatchmakingRuleSetsResponseUnmarshaller.Instance; return Invoke<DescribeMatchmakingRuleSetsResponse>(request, options); }
public string GetPronunciation(int wordId, char[] surface, int off, int len) { return null; }
public string GetPath() { return pathStr; }
public static double Devsq(double[] v) => v == null || v.Length < 1 ? double.NaN : v.Length == 1 ? 0 : v.Select(x => x - v.Average()).Sum(x => x * x);
public virtual DescribeResizeResponse DescribeResize(DescribeResizeRequest request){var options = new InvokeOptions();options.RequestMarshaller = DescribeResizeRequestMarshaller.Instance;options.ResponseUnmarshaller = DescribeResizeResponseUnmarshaller.Instance;return Invoke<DescribeResizeResponse>(request, options);}
public bool HasPassedThroughNonGreedyDecision() => passedThroughNonGreedyDecision;
public override int End() { return End(0); }
public void Traverse(CellHandler handler)  {     int firstRow = range.GetFirstRow();      int lastRow = range.GetLastRow();      int firstColumn = range.GetFirstColumn();      int lastColumn = range.GetLastColumn();      int width = lastColumn - firstColumn + 1;      SimpleCellWalkContext ctx = new SimpleCellWalkContext();      Row currentRow = null;      Cell currentCell = null;      for (ctx.rowNumber = firstRow; ctx.rowNumber <= lastRow; ++ctx.rowNumber)      {          currentRow = sheet.GetRow(ctx.rowNumber);          if (currentRow == null)          {              continue;          }          for (ctx.colNumber = firstColumn; ctx.colNumber <= lastColumn; ++ctx.colNumber)          {              currentCell = currentRow.GetCell(ctx.colNumber);              if (currentCell == null)              {                  continue;              }              if (IsEmpty(currentCell) && !traverseEmptyCells)              {                  continue;              }              long rowSize = ArithmeticUtils.MulAndCheck((long)ArithmeticUtils.SubAndCheck(ctx.rowNumber, firstRow), (long)width);              ctx.ordinalNumber = ArithmeticUtils.AddAndCheck(rowSize, (ctx.colNumber - firstColumn + 1));              handler.OnCell(currentCell, ctx);          }      } }
public int GetReadIndex() { return pos; }
public int CompareTo(ScoreTerm other) { return this.boost == other.boost ? other.bytes.CompareTo(this.bytes) : this.boost.CompareTo(other.boost); }
public virtual int Normalize(char[] s, int len) { for (int i = 0; i < len; i++) { switch (s[i]) { case FARSI_YEH: case YEH_BARREE: s[i] = YEH; break; case KEHEH: s[i] = KAF; break; case HEH_YEH: case HEH_GOAL: s[i] = HEH; break; case HAMZA_ABOVE: len = Delete(s, i, len); i--; break; default: break; } } return len; }
public void Serialize(BinaryWriter @out) { @out.Write((short)_options); }
public DiagnosticErrorListener(bool exactOnly) { this.exactOnly = exactOnly; }
public KeySchemaElement(string attributeName, KeyType keyType) { _attributeName = attributeName; _keyType = keyType.ToString(); }
public virtual GetAssignmentResponse GetAssignment(GetAssignmentRequest request) { var options = new InvokeOptions(); options.RequestMarshaller = GetAssignmentRequestMarshaller.Instance; options.ResponseUnmarshaller = GetAssignmentResponseUnmarshaller.Instance; return Invoke<GetAssignmentResponse>(request, options); }
public bool HasObject(AnyObjectId id) => FindOffset(id) != -1;
public GroupingSearch SetAllGroups(bool allGroups) { this.allGroups = allGroups; return this; }
public virtual void SetMultiValued(String dimName, bool v){lock (this){DimConfig ft = fieldTypes[dimName];if (ft == null){ft = new DimConfig();fieldTypes[dimName] = ft;}ft.MultiValued = v;}}
public virtual int GetCellsVal() {var i = cells.Keys.GetEnumerator(); int size = 0; while (i.MoveNext()) { Character c = i.Current; Cell e = At(c); if (e.Cmd >= 0) { size++; }} return size;}
public virtual DeleteVoiceConnectorResponse DeleteVoiceConnector(DeleteVoiceConnectorRequest request){var options = new InvokeOptions();options.RequestMarshaller = DeleteVoiceConnectorRequestMarshaller.Instance;options.ResponseUnmarshaller = DeleteVoiceConnectorResponseUnmarshaller.Instance;return Invoke<DeleteVoiceConnectorResponse>(request, options);}
public virtual DeleteLifecyclePolicyResponse DeleteLifecyclePolicy(DeleteLifecyclePolicyRequest request) => ExecuteDeleteLifecyclePolicy(BeforeClientExecution(request));
