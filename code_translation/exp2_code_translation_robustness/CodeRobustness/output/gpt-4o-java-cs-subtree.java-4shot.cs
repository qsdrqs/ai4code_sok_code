void LittleEndianOutput(out short value) { out.WriteShort(value); }
void BlockList<T>(BlockList<T> src){if (src.size == 0) srcDirIdx = 0; for (; srcDirIdx < src.tailDirIdx; srcDirIdx++) addAll(src.directory[srcDirIdx], 0, BLOCK_SIZE); if (src.tailBlkIdx != 0) addAll(src.tailBlock, 0, src.tailBlkIdx);}
void AddByte(byte b){if (currentBlock == null){if (currentBlock != null){AddBlock(currentBlock);}currentBlock = new byte[blockSize];upto = 0;}currentBlock[upto++] = b;}
public ObjectId() { }
public virtual DeleteDomainEntryResponse DeleteDomainEntry(DeleteDomainEntryRequest request){var options = new InvokeOptions();options.RequestMarshaller = DeleteDomainEntryRequestMarshaller.Instance;options.ResponseUnmarshaller = DeleteDomainEntryResponseUnmarshaller.Instance;return Invoke<DeleteDomainEntryResponse>(request, options);}
public long RamBytesUsed() { return ((termOffsets != null) ? termOffsets.RamBytesUsed() : 0) + ((termsDictOffsets != null) ? termsDictOffsets.RamBytesUsed() : 0); }
public override string ToString() { byte[] raw = buffer; int msgB = RawParseUtils.TagMessage; if (msgB < 0) { return ""; } return RawParseUtils.Decode(GuessEncoding(), raw, msgB, raw.Length); }
POIFSFileSystem() { (true); _header.SetBATCount(); _header.SetBATArray(new[] { 1 }); BATBlock bb = BATBlock.CreateEmptyBATBlock(bigBlockSize, false); bb.SetOurBlockIndex(1); _bat_blocks.Add(bb); SetNextBlock(0, POIFSConstants.END_OF_CHAIN); SetNextBlock(1, POIFSConstants.FAT_SECTOR_BLOCK); _property_table.SetStartBlock(0); }
void Address(int address) { slice = pool.Buffers[address >> ByteBlockPool.BYTE_BLOCK_SHIFT]; Debug.Assert(slice != null); upto = address & ByteBlockPool.BYTE_BLOCK_MASK; offset0 = address; Debug.Assert(upto < ByteBlockPool.BYTE_BLOCK_SIZE); }
public SubmoduleAddCommand(string path) { this.Path = path; return this; }
public ListIngestionsResult() { request = BeforeClientExecution(request); return ExecuteListIngestions(request); }
public QueryParserTokenManager(CharStream stream, int lexState) : base() { SwitchTo(lexState); }
public virtual GetShardIteratorResponse GetShardIterator(GetShardIteratorRequest request){var options = new InvokeOptions();options.RequestMarshaller = GetShardIteratorRequestMarshaller.Instance;options.ResponseUnmarshaller = GetShardIteratorResponseUnmarshaller.Instance;return Invoke<GetShardIteratorResponse>(request, options);}
public ModifyStrategyRequest() : base("aegis", "2016-11-11", "ModifyStrategy", "vipaegis") { }
public bool HasData() { lock (lockObj) { if (inputStream == null) { throw new IOException("InputStreamReader is closed"); } try { return bytes.HasRemaining || inputStream.Available() > 0; } catch { return false; } } }
public EscherOptRecord() { }
public virtual int Read(char[] buffer, int offset, int length) { if (buffer == null) { throw new NullReferenceException("buffer == null"); } java.util.Arrays.checkOffsetAndCount(buffer.Length, offset, length); if (length == 0) { return 0; } int copylen = count - pos < length ? count - pos : length; for (int i = 0; i < copylen; i++) { buffer[offset + i] = this.buffer[pos + i]; } return copylen; }
OpenNLPSentenceBreakIterator() { this.sentenceOp = sentenceOp; }
void Write(string str) { Write(!string.IsNullOrEmpty(str) ? str : Convert.ToString((object)null)); }
public NotImplementedFunctionException(string functionName, NotImplementedException cause) : base(functionName, cause) { this.functionName = functionName; }
public virtual V GetValue() { return base.NextEntry.GetValue(); }
public void Read(byte[] b, int offset, int len, bool useBuffer){int available = bufferLength - bufferPosition;if (len <= available){if (len > 0)Array.Copy(buffer, bufferPosition, b, offset, len);bufferPosition += len;}else{if (available > 0){Array.Copy(buffer, bufferPosition, b, offset, available);offset += available;len -= available;bufferPosition += available;}if (useBuffer && len < bufferSize){Refill();if (bufferLength < len){Array.Copy(buffer, 0, b, offset, bufferLength);throw new EndOfStreamException("read past EOF: " + this);}else{Array.Copy(buffer, 0, b, offset, len);}}else{long after = bufferStart + bufferPosition + len;if (after > Length())throw new EndOfStreamException("read past EOF: " + this);ReadInternal(b, offset, len);bufferStart = after;bufferPosition = 0;bufferLength = 0;}}}
public virtual TagQueueResponse TagQueue(TagQueueRequest request){var options = new InvokeOptions();options.RequestMarshaller = TagQueueRequestMarshaller.Instance;options.ResponseUnmarshaller = TagQueueResponseUnmarshaller.Instance;return Invoke<TagQueueResponse>(request, options);}
void Method() { throw new NotSupportedException(); }
CacheSubnetGroup() { request = beforeClientExecution(request); return executeModifyCacheSubnetGroup(request); }
void SetParams(string parameters) { base.SetParams(parameters); language = country = variant = ""; var st = new StringTokenizer(parameters, ","); if (st.HasMoreTokens()) language = st.NextToken(); if (st.HasMoreTokens()) country = st.NextToken(); if (st.HasMoreTokens()) variant = st.NextToken(); }
public virtual DeleteDocumentationVersionResponse DeleteDocumentationVersion(DeleteDocumentationVersionRequest request){var options = new InvokeOptions();options.RequestMarshaller = DeleteDocumentationVersionRequestMarshaller.Instance;options.ResponseUnmarshaller = DeleteDocumentationVersionResponseUnmarshaller.Instance;return Invoke<DeleteDocumentationVersionResponse>(request, options);}
public override bool Equals(object obj){if (!(obj is FacetLabel)){return false;}FacetLabel other = (FacetLabel)obj;if (length != other.length){return false;}for (int i = length - 1; i >= 0; i--){if (!components[i].Equals(other.components[i])){return false;}}return true;}
public virtual GetInstanceAccessDetailsResponse GetInstanceAccessDetails(GetInstanceAccessDetailsRequest request){var options = new InvokeOptions();options.RequestMarshaller = GetInstanceAccessDetailsRequestMarshaller.Instance;options.ResponseUnmarshaller = GetInstanceAccessDetailsResponseUnmarshaller.Instance;return Invoke<GetInstanceAccessDetailsResponse>(request, options);}
HSSFPolygon(HSSFChildAnchor anchor){HSSFPolygon shape = new HSSFPolygon(this, anchor);shape.SetParent();shape.SetAnchor(anchor);shapes.Add(shape);OnCreate(shape);return shape;}
public string SheetName(int sheetIndex) => GetBoundSheetRec(sheetIndex).SheetName;
public virtual GetDashboardResponse GetDashboard(GetDashboardRequest request){var options = new InvokeOptions();options.RequestMarshaller = GetDashboardRequestMarshaller.Instance;options.ResponseUnmarshaller = GetDashboardResponseUnmarshaller.Instance;return Invoke<GetDashboardResponse>(request, options);}
public virtual AssociateSigninDelegateGroupsWithAccountResponse AssociateSigninDelegateGroupsWithAccount(AssociateSigninDelegateGroupsWithAccountRequest request){var options = new InvokeOptions();options.RequestMarshaller = AssociateSigninDelegateGroupsWithAccountRequestMarshaller.Instance;options.ResponseUnmarshaller = AssociateSigninDelegateGroupsWithAccountResponseUnmarshaller.Instance;return Invoke<AssociateSigninDelegateGroupsWithAccountResponse>(request, options);}
void MulBlankRecord(MulBlankRecord mbr){for (int j = 0; j < mbr.GetNumColumns(); j++){BlankRecord br = new BlankRecord();br.SetColumn((short)(j + mbr.GetFirstColumn()));br.SetRow(mbr.GetRow());br.SetXFIndex(mbr.GetXFAt());InsertCell(br);}}
public static string Method() { StringBuilder sb = new StringBuilder(); sb.Append("\\Q"); int apos = 0; int k; while ((k = string.IndexOf("\\E", apos)) >= 0) { sb.Append(string.Substring(apos, k - apos + 2)).Append("\\\\E\\Q"); apos = k + 2; } return sb.Append(string.Substring(apos)).Append("\\E").ToString(); }
ByteBuffer(value) => throw new ReadOnlyBufferException();
ArrayPtg(object values2d) { nColumns = ((object[])values2d)[0].Length; nRows = ((object[])values2d).Length; _nColumns = nColumns; _nRows = nRows; object[] vv = new object[_nColumns * _nRows]; for (int r = 0; r < nRows; r++) { object[] rowData = (object[])((object[])values2d)[r]; for (int c = 0; c < nColumns; c++) { vv[GetValueIndex(c, r)] = rowData[c]; } } _arrayValues = vv; _reserved0Int = 0; _reserved1Short = 0; _reserved2Byte = 0; }
public virtual GetIceServerConfigResponse GetIceServerConfig(GetIceServerConfigRequest request){var options = new InvokeOptions();options.RequestMarshaller = GetIceServerConfigRequestMarshaller.Instance;options.ResponseUnmarshaller = GetIceServerConfigResponseUnmarshaller.Instance;return Invoke<GetIceServerConfigResponse>(request, options);}
public override string ToString() => GetType().Name + " [" + GetValueAsString() + "]";
public override string ToString() { return "ToChildBlockJoinQuery (" + _parentQuery.ToString() + ")"; }
void () { refCount.IncrementAndGet(); }
public virtual UpdateConfigurationSetSendingEnabledResponse UpdateConfigurationSetSendingEnabled(UpdateConfigurationSetSendingEnabledRequest request){var options = new InvokeOptions();options.RequestMarshaller = UpdateConfigurationSetSendingEnabledRequestMarshaller.Instance;options.ResponseUnmarshaller = UpdateConfigurationSetSendingEnabledResponseUnmarshaller.Instance;return Invoke<UpdateConfigurationSetSendingEnabledResponse>(request, options);}
() { return GetXBATEntriesPerBlock() * ; }
void Pow10(int pow10){var tp = TenPower.GetInstance(Math.Abs(pow10));if (pow10 < 0){MulShift(tp._divisor, tp._divisorShift);}else{MulShift(tp._multiplicand, tp._multiplierShift);}}
public override string ToString(){StringBuilder b = new StringBuilder();int l = length;b.Append(Path.DirectorySeparatorChar);for (int i = 0; i < l; i++){b.Append(GetComponent(i));if (i < l - 1){b.Append(Path.DirectorySeparatorChar);}}return b.ToString();}
public InstanceProfileCredentialsProvider(ECSMetadataServiceCredentialsFetcher fetcher){this.fetcher = fetcher; SetRoleName(roleName); return this;}
public virtual void ProgressMonitor(ProgressMonitor pm) { }
void Method() { if (!first) { ptr = 0; if (!Eof()) ParseEntry(); } }
public E E(){if(iterator.PreviousIndex() >= start){return iterator.Previous();}throw new NoSuchElementException();}
public string String() { return ""; }
(value) { for (int i = 0; i < mSize; i++) if (mValues[i] == value) return -1; }
public List<CharsRef> List(char[] word, int length){List<CharsRef> stems = Stem(word, length);if (stems.Count < 2){return stems;}CharArraySet terms = new CharArraySet(8, dictionary.ignoreCase);List<CharsRef> deduped = new List<CharsRef>();foreach (CharsRef s in stems){if (!terms.Contains(s)){deduped.Add(s);terms.Add(s);}}return deduped;}
public virtual GetGatewayResponsesResponse GetGatewayResponses(GetGatewayResponsesRequest request){var options = new InvokeOptions();options.RequestMarshaller = GetGatewayResponsesRequestMarshaller.Instance;options.ResponseUnmarshaller = GetGatewayResponsesResponseUnmarshaller.Instance;return Invoke<GetGatewayResponsesResponse>(request, options);}
void SetPosition(long pos) { currentBlockIndex = (int)(pos >> blockBits); currentBlock = blocks[currentBlockIndex]; currentBlockUpto = (int)(pos & blockMask); }
(n) => { s = Math.Min(Available(), Math.Max(0, n)); ptr += s; };
public BootstrapActionDetail() { SetBootstrapActionConfig(bootstrapActionConfig); }
void Write(LittleEndianOutput out) { out.WriteShort(field_1_row); out.WriteShort(field_2_col); out.WriteShort(field_3_flags); out.WriteShort(field_4_shapeid); out.WriteShort(field_6_author.Length); out.WriteByte(field_5_hasMultibyte ? (byte)0x01 : (byte)0x00); if (field_5_hasMultibyte) { StringUtil.PutUnicodeLE(field_6_author, out); } else { StringUtil.PutCompressedUnicode(field_6_author, out); } if (field_7_padding != null) { out.WriteByte((byte)field_7_padding.Value); } }
(string str) => lastIndexOf;
bool Add(E obj) { return AddLastImpl; }
void UpdateConfig(string section, string subsection) { ConfigSnapshot src, res; do { src = state.Get(); res = UnsetSection(src, section, subsection); } while (!state.CompareAndSet(src, res)); }
public override string ToString() { return tagName; }
void (int index, SubRecord element) { subrecords.Add(element); }
public bool Remove(object o) { lock (mutex) { return delegate.Remove(o); } }
DoubleMetaphoneFilter(TokenStream input) : base(input) { return new DoubleMetaphoneFilter(); }
public int InCoreLength() { return inCoreLength; }
void Method(bool newValue) { }
public Pair(ContentSource oldSource, ContentSource newSource){this.oldSource = oldSource; this.newSource = newSource;}
public virtual object this[int i] { get { if (i < 0 || i >= entries.Length) throw new IndexOutOfRangeException(i.ToString()); return entries[i]; } }
public CreateRepoRequest(): base("cr", "2016-06-07", "CreateRepo", "cr", "openAPI"){ResourcePath = "/repos";Method = MethodType.POST;}
bool () { }
void Remove() { if (expectedModCount == list.modCount) { if (lastLink != null) { var next = lastLink.next; var previous = lastLink.previous; next.previous = previous; previous.next = next; if (lastLink == link) { pos--; } link = previous; lastLink = null; expectedModCount++; list.size--; list.modCount++; } else { throw new InvalidOperationException(); } } else { throw new InvalidOperationException(); } }
public virtual MergeShardsResponse MergeShards(MergeShardsRequest request){var options = new InvokeOptions();options.RequestMarshaller = MergeShardsRequestMarshaller.Instance;options.ResponseUnmarshaller = MergeShardsResponseUnmarshaller.Instance;return Invoke<MergeShardsResponse>(request, options);}
AllocateHostedConnectionResult AllocateHostedConnection(AllocateHostedConnectionRequest request) { request = BeforeClientExecution(request); return ExecuteAllocateHostedConnection; }
() { }
public static WeightedTerm WeightedTerm(Query query) => GetTerms(query, false);
ByteBuffer() { throw new ReadOnlyBufferException(); }
void Decode(byte[] blocks, int blocksOffset, int[] values, int valuesOffset, int iterations) { for (int i = 0; i < iterations; ++i) { int byte0 = blocks[blocksOffset++] & 0xFF; values[valuesOffset++] = byte0 >> 2; int byte1 = blocks[blocksOffset++] & 0xFF; values[valuesOffset++] = ((byte0 & 3) << 4) | (byte1 >> 4); int byte2 = blocks[blocksOffset++] & 0xFF; values[valuesOffset++] = ((byte1 & 15) << 2) | (byte2 >> 6); values[valuesOffset++] = byte2 & 63; } }
public string GetString() { string s = GetPath(); if ("/".Equals(s) || "".Equals(s)) s = GetHost(); if (s == null) throw new ArgumentException(); string[] elements; if ("file".Equals(scheme) || LOCAL_FILE.IsMatch(s)) elements = s.Split(new[] { Path.DirectorySeparatorChar, '/' }, StringSplitOptions.RemoveEmptyEntries); else elements = s.Split(new[] { '/' }, StringSplitOptions.RemoveEmptyEntries); if (elements.Length == 0) throw new ArgumentException(); string result = elements[elements.Length - 1]; if (Constants.DOT_GIT.Equals(result)) result = elements[elements.Length - 2]; else if (result.EndsWith(Constants.DOT_GIT_EXT)) result = result.Substring(0, result.Length - Constants.DOT_GIT_EXT.Length); return result; }
public virtual DescribeNotebookInstanceLifecycleConfigResponse DescribeNotebookInstanceLifecycleConfig(DescribeNotebookInstanceLifecycleConfigRequest request){var options = new InvokeOptions();options.RequestMarshaller = DescribeNotebookInstanceLifecycleConfigRequestMarshaller.Instance;options.ResponseUnmarshaller = DescribeNotebookInstanceLifecycleConfigResponseUnmarshaller.Instance;return Invoke<DescribeNotebookInstanceLifecycleConfigResponse>(request, options);}
public string String() { return ""; }
public virtual CreateVpnConnectionResponse CreateVpnConnection(CreateVpnConnectionRequest request){var options = new InvokeOptions();options.RequestMarshaller = CreateVpnConnectionRequestMarshaller.Instance;options.ResponseUnmarshaller = CreateVpnConnectionResponseUnmarshaller.Instance;return Invoke<CreateVpnConnectionResponse>(request, options);}
public DescribeVoicesResult() { request = BeforeClientExecution(request); return ExecuteDescribeVoices(request); }
ListMonitoringExecutionsResult(ListMonitoringExecutionsRequest request) { request = BeforeClientExecution(request); return ExecuteListMonitoringExecutions; }
public DescribeJobRequest(string vaultName, string jobId) { SetVaultName(vaultName); SetJobId(jobId); }
public EscherRecord EscherRecord(int index) { return escherRecords[index]; }
public GetApisResult GetApis(GetApisRequest request) { request = BeforeClientExecution(request); return ExecuteGetApis(request); }
public virtual DeleteSmsChannelResponse DeleteSmsChannel(DeleteSmsChannelRequest request){var options = new InvokeOptions();options.RequestMarshaller = DeleteSmsChannelRequestMarshaller.Instance;options.ResponseUnmarshaller = DeleteSmsChannelResponseUnmarshaller.Instance;return Invoke<DeleteSmsChannelResponse>(request, options);}
public TrackingRefUpdate() { }
void Print(){Console.WriteLine(Convert.ToString(b));}
public QueryNode() { return GetChildren[0]; }
public NotIgnoredFilter(WorkdirTreeIndex workdirTreeIndex){this.workdirTreeIndex = workdirTreeIndex;}
public AreaRecord(RecordInputStream in1) { field_1_formatFlags = in1.ReadShort(); }
GetThumbnailRequest(ProtocolType.HTTPS);
public virtual DescribeTransitGatewayVpcAttachmentsResponse DescribeTransitGatewayVpcAttachments(DescribeTransitGatewayVpcAttachmentsRequest request){var options = new InvokeOptions();options.RequestMarshaller = DescribeTransitGatewayVpcAttachmentsRequestMarshaller.Instance;options.ResponseUnmarshaller = DescribeTransitGatewayVpcAttachmentsResponseUnmarshaller.Instance;return Invoke<DescribeTransitGatewayVpcAttachmentsResponse>(request, options);}
public virtual PutVoiceConnectorStreamingConfigurationResponse PutVoiceConnectorStreamingConfiguration(PutVoiceConnectorStreamingConfigurationRequest request){var options = new InvokeOptions();options.RequestMarshaller = PutVoiceConnectorStreamingConfigurationRequestMarshaller.Instance;options.ResponseUnmarshaller = PutVoiceConnectorStreamingConfigurationResponseUnmarshaller.Instance;return Invoke<PutVoiceConnectorStreamingConfigurationResponse>(request, options);}
public OrdRange() { return prefixToOrdRange.Get(dim); }
public override string ToString() { string symbol = ""; if (startIndex >= 0 && startIndex < GetInputStream().Size) { symbol = GetInputStream().GetText(Interval.Of(startIndex, startIndex)); symbol = Utils.EscapeWhitespace(symbol, false); } return string.Format(CultureInfo.CurrentCulture, "{0}('{1}')", typeof(LexerNoViableAltException).Name, symbol); }
public object E() { return peekFirstImpl; }
public virtual CreateWorkspacesResponse CreateWorkspaces(CreateWorkspacesRequest request){var options = new InvokeOptions();options.RequestMarshaller = CreateWorkspacesRequestMarshaller.Instance;options.ResponseUnmarshaller = CreateWorkspacesResponseUnmarshaller.Instance;return Invoke<CreateWorkspacesResponse>(request, options);}
public NumberFormatIndexRecord() { return Copy; }
public virtual DescribeRepositoriesResponse DescribeRepositories(DescribeRepositoriesRequest request){var options = new InvokeOptions();options.RequestMarshaller = DescribeRepositoriesRequestMarshaller.Instance;options.ResponseUnmarshaller = DescribeRepositoriesResponseUnmarshaller.Instance;return Invoke<DescribeRepositoriesResponse>(request, options);}
SparseIntArray(int initialCapacity){initialCapacity = ArrayUtils.IdealIntArraySize(initialCapacity);mKeys = new int[initialCapacity];mValues = new int[initialCapacity];mSize = 0;}
public HyphenatedWordsFilter() { return new HyphenatedWordsFilter(input); }
CreateDistributionWithTagsResult(){request = BeforeClientExecution(request); return ExecuteCreateDistributionWithTags(request);}
public RandomAccessFile(string fileName, string mode) { new File(fileName); }
public virtual DeleteWorkspaceImageResponse DeleteWorkspaceImage(DeleteWorkspaceImageRequest request){var options = new InvokeOptions();options.RequestMarshaller = DeleteWorkspaceImageRequestMarshaller.Instance;options.ResponseUnmarshaller = DeleteWorkspaceImageResponseUnmarshaller.Instance;return Invoke<DeleteWorkspaceImageResponse>(request, options);}
public static string ToString(long value){StringBuilder sb = new StringBuilder(16);WriteHex(sb, value, 16, "");return sb.ToString();}
UpdateDistributionResult(UpdateDistributionRequest request) { request = BeforeClientExecution(request); return ExecuteUpdateDistribution; }
public HSSFColor(short index){if(index==HSSFColorPredefined.AUTOMATIC.GetIndex()){return HSSFColorPredefined.AUTOMATIC.GetColor();}byte[] b=_palette.GetColor(index);return (b==null)?null:new CustomColor();}
public ValueEval(ValueEval operands, int srcRow, int srcCol) { throw new NotImplementedFunctionException(_functionName); }
void Write() { out.WriteShort(field_1_number_crn_records); out.WriteShort(field_2_sheet_table_index); }
public virtual DescribeDBEngineVersionsResponse DescribeDBEngineVersions() { return DescribeDBEngineVersions(new DescribeDBEngineVersionsRequest()); }
public FormatRun(char character, int fontIndex) { this._character = character; this._fontIndex = fontIndex; }
public static byte[] ConvertCharsToBytes(char[] chars, int offset, int length){byte[] result = new byte[length * 2];int end = offset + length;int resultIndex = 0;for (int i = offset; i < end; ++i){char ch = chars[i];result[resultIndex++] = (byte)(ch >> 8);result[resultIndex++] = (byte)ch;}return result;}
public virtual UploadArchiveResponse UploadArchive(UploadArchiveRequest request){var options = new InvokeOptions();options.RequestMarshaller = UploadArchiveRequestMarshaller.Instance;options.ResponseUnmarshaller = UploadArchiveResponseUnmarshaller.Instance;return Invoke<UploadArchiveResponse>(request, options);}
public List HiddenTokensToLeft(int tokenIndex) { return GetHiddenTokensToLeft(tokenIndex, -1); }
public override bool Equals(object obj){if (this == obj) return true;if (!base.Equals(obj)) return false;if (GetType() != obj.GetType()) return false;AutomatonQuery other = (AutomatonQuery)obj;if (!compiled.Equals(other.compiled)) return false;if (term == null){if (other.term != null) return false;}else if (!term.Equals(other.term)) return false;return true;}
public SpanQuery() { SpanQuery[] spanQueries = new SpanQuery[size()]; var sqi = weightBySpanQuery.Keys.GetEnumerator(); int i = 0; while (sqi.MoveNext()) { SpanQuery sq = sqi.Current; float boost = weightBySpanQuery[sq]; if (boost != 1f) { sq = new SpanBoostQuery(sq, boost); } spanQueries[i++] = sq; } if (spanQueries.Length == 1) return spanQueries[0]; else return new SpanOrQuery(spanQueries); }
public StashCreateCommand() { return new StashCreateCommand(); }
public override FieldInfo FieldInfo() { return byName.Get(fieldName); }
public DescribeEventSourceResult DescribeEventSource(DescribeEventSourceRequest request){request = BeforeClientExecution(request); return ExecuteDescribeEventSource(request);}
public virtual GetDocumentAnalysisResponse GetDocumentAnalysis(GetDocumentAnalysisRequest request){var options = new InvokeOptions();options.RequestMarshaller = GetDocumentAnalysisRequestMarshaller.Instance;options.ResponseUnmarshaller = GetDocumentAnalysisResponseUnmarshaller.Instance;return Invoke<GetDocumentAnalysisResponse>(request, options);}
CancelUpdateStackResult() {request = BeforeClientExecution(request); return ExecuteCancelUpdateStack(request);}
public virtual ModifyLoadBalancerAttributesResponse ModifyLoadBalancerAttributes(ModifyLoadBalancerAttributesRequest request){var options = new InvokeOptions();options.RequestMarshaller = ModifyLoadBalancerAttributesRequestMarshaller.Instance;options.ResponseUnmarshaller = ModifyLoadBalancerAttributesResponseUnmarshaller.Instance;return Invoke<ModifyLoadBalancerAttributesResponse>(request, options);}
public virtual SetInstanceProtectionResponse SetInstanceProtection(SetInstanceProtectionRequest request){request = BeforeClientExecution(request);return ExecuteSetInstanceProtection(request);}
public virtual ModifyDBProxyResponse ModifyDBProxy(ModifyDBProxyRequest request){var options = new InvokeOptions();options.RequestMarshaller = ModifyDBProxyRequestMarshaller.Instance;options.ResponseUnmarshaller = ModifyDBProxyResponseUnmarshaller.Instance;return Invoke<ModifyDBProxyResponse>(request, options);}
void Add(char[] output, int offset, int len, int endOffset, int posLength){if (count == outputs.Length){outputs = ArrayUtil.Grow(outputs, count + 1);}if (count == endOffsets.Length){int[] next = new int[ArrayUtil.Oversize(1 + count, sizeof(int))];Array.Copy(endOffsets, 0, next, 0, count);endOffsets = next;}if (count == posLengths.Length){int[] next = new int[ArrayUtil.Oversize(1 + count, sizeof(int))];Array.Copy(posLengths, 0, next, 0, count);posLengths = next;}if (outputs[count] == null){outputs[count] = new CharsRefBuilder();}outputs[count].CopyChars(output, offset, len);endOffsets[count] = endOffset;posLengths[count] = posLength;count++;}
FetchLibrariesRequest() : base("CloudPhoto", "2017-07-11", "FetchLibraries", "cloudphoto") { }
public virtual bool Exists() { return fs.Exists; }
FilterOutputStream() { this.out = @out; }
ScaleClusterRequest("/clusters/[ClusterId]") { Method = MethodType.PUT; }
public DataValidationConstraint(int operatorType, string formula2) { return DVConstraint.CreateTimeConstraint(operatorType, formula1, formula2); }
ListObjectParentPathsResult() { request = BeforeClientExecution(request); return ExecuteListObjectParentPaths(request); }
public virtual DescribeCacheSubnetGroupsResponse DescribeCacheSubnetGroups(DescribeCacheSubnetGroupsRequest request){var options = new InvokeOptions();options.RequestMarshaller = DescribeCacheSubnetGroupsRequestMarshaller.Instance;options.ResponseUnmarshaller = DescribeCacheSubnetGroupsResponseUnmarshaller.Instance;return Invoke<DescribeCacheSubnetGroupsResponse>(request, options);}
void Method() { field_5_options = SharedFormula.SetShortBoolean(field_5_options, flag); }
bool () { }
public ErrorNode ErrorNode(Token badToken){var t = new ErrorNodeImpl(badToken);AddAnyChild(t);t.SetParent();return t;}
LatvianStemFilterFactory(Dictionary<string, string> args) { ProcessArgs(args); if (args.Count > 0) { throw new ArgumentException("Unknown parameters: " + string.Join(", ", args.Keys)); } }
public virtual EventSubscription RemoveSourceIdentifierFromSubscription(RemoveSourceIdentifierFromSubscriptionRequest request){var options = new InvokeOptions();options.RequestMarshaller = RemoveSourceIdentifierFromSubscriptionRequestMarshaller.Instance;options.ResponseUnmarshaller = RemoveSourceIdentifierFromSubscriptionResponseUnmarshaller.Instance;return Invoke<EventSubscription>(request, options);}
public static TokenFilterFactory Create(string name, IDictionary<string, string> args) { return loader.NewInstance(name, args); }
AddAlbumPhotosRequest() : base("CloudPhoto", "2017-07-11", "AddAlbumPhotos", "cloudphoto") { }
public virtual GetThreatIntelSetResponse GetThreatIntelSet(GetThreatIntelSetRequest request){var options = new InvokeOptions();options.RequestMarshaller = GetThreatIntelSetRequestMarshaller.Instance;options.ResponseUnmarshaller = GetThreatIntelSetResponseUnmarshaller.Instance;return Invoke<GetThreatIntelSetResponse>(request, options);}
RevFilter() { return new Binary(a.Clone(), b.Clone()); }
public bool MethodName(object o) { return false; }
protected bool HasArray() => ProtectedHasArray();
public UpdateContributorInsightsResult UpdateContributorInsights(UpdateContributorInsightsRequest request){request = BeforeClientExecution(request);return ExecuteUpdateContributorInsights(request);}
void Method(){records.Remove(fileShare);records.Remove(writeProtect);writeProtect = null;}
public SolrSynonymParser(bool expand, Analyzer analyzer) : base(dedup, analyzer) { this.expand = expand; }
public RequestSpotInstancesResponse RequestSpotInstances(RequestSpotInstancesRequest request){request = BeforeClientExecution(request);return ExecuteRequestSpotInstances(request);}
public object GetObjectData() { return findObjectRecord.GetObjectData(); }
public virtual GetContactAttributesResponse GetContactAttributes(GetContactAttributesRequest request){var options = new InvokeOptions();options.RequestMarshaller = GetContactAttributesRequestMarshaller.Instance;options.ResponseUnmarshaller = GetContactAttributesResponseUnmarshaller.Instance;return Invoke<GetContactAttributesResponse>(request, options);}
public override string ToString() => GetKey + ": " + GetValue();
public ListTextTranslationJobsResult ListTextTranslationJobs(ListTextTranslationJobsRequest request){request = BeforeClientExecution(request); return ExecuteListTextTranslationJobs(request);}
public virtual GetContactMethodsResponse GetContactMethods(GetContactMethodsRequest request){var options = new InvokeOptions();options.RequestMarshaller = GetContactMethodsRequestMarshaller.Instance;options.ResponseUnmarshaller = GetContactMethodsResponseUnmarshaller.Instance;return Invoke<GetContactMethodsResponse>(request, options);}
public static int GetFunctionIndex(string name) { FunctionMetadata fd = GetInstance().GetFunctionByNameInternal(name); if (fd == null) { fd = GetInstanceCetab().GetFunctionByNameInternal(name); if (fd == null) { return -1; } } return fd.GetIndex(); }
public DescribeAnomalyDetectorsResult DescribeAnomalyDetectors(DescribeAnomalyDetectorsRequest request) { request = BeforeClientExecution(request); return ExecuteDescribeAnomalyDetectors(request); }
public static string Method(string message, ObjectId changeId) => insertId;
public long GetObjectSize(AnyObjectId objectId, int typeHint){long sz = db.GetObjectSize(objectId);if (sz < 0){if (typeHint == OBJ_ANY)throw new MissingObjectException(objectId.Copy(), JGitText.Get().UnknownObjectType2);throw new MissingObjectException(objectId.Copy(), typeHint);}return sz;}
public virtual ImportInstallationMediaResponse ImportInstallationMedia(ImportInstallationMediaRequest request){var options = new InvokeOptions();options.RequestMarshaller = ImportInstallationMediaRequestMarshaller.Instance;options.ResponseUnmarshaller = ImportInstallationMediaResponseUnmarshaller.Instance;return Invoke<ImportInstallationMediaResponse>(request, options);}
public virtual PutLifecycleEventHookExecutionStatusResponse PutLifecycleEventHookExecutionStatus(PutLifecycleEventHookExecutionStatusRequest request){var options = new InvokeOptions();options.RequestMarshaller = PutLifecycleEventHookExecutionStatusRequestMarshaller.Instance;options.ResponseUnmarshaller = PutLifecycleEventHookExecutionStatusResponseUnmarshaller.Instance;return Invoke<PutLifecycleEventHookExecutionStatusResponse>(request, options);}
NumberPtg() { ((in.ReadDouble())); }
public GetFieldLevelEncryptionConfigResponse GetFieldLevelEncryptionConfig(GetFieldLevelEncryptionConfigRequest request){var options = new InvokeOptions();options.RequestMarshaller = GetFieldLevelEncryptionConfigRequestMarshaller.Instance;options.ResponseUnmarshaller = GetFieldLevelEncryptionConfigResponseUnmarshaller.Instance;return Invoke<GetFieldLevelEncryptionConfigResponse>(request, options);}
public virtual DescribeDetectorResponse DescribeDetector(DescribeDetectorRequest request){request = BeforeClientExecution(request);return ExecuteDescribeDetector(request);}
public virtual ReportInstanceStatusResponse ReportInstanceStatus(ReportInstanceStatusRequest request){request = BeforeClientExecution(request);return ExecuteReportInstanceStatus(request);}
public virtual DeleteAlarmResponse DeleteAlarm(DeleteAlarmRequest request){var options = new InvokeOptions();options.RequestMarshaller = DeleteAlarmRequestMarshaller.Instance;options.ResponseUnmarshaller = DeleteAlarmResponseUnmarshaller.Instance;return Invoke<DeleteAlarmResponse>(request, options);}
public virtual TokenStream TokenStream() { return new PortugueseStemFilter(input); }
FtCblsSubRecord() { reserved = new(); }
public bool Remove(object obj) { lock (mutex) { return c.Remove(obj); } }
public GetDedicatedIpResult(){request = BeforeClientExecution(request); return ExecuteGetDedicatedIp(request);}
public string String() { return ""; }
public virtual ListStreamProcessorsResponse ListStreamProcessors(ListStreamProcessorsRequest request){request = BeforeClientExecution(request);return ExecuteListStreamProcessors(request);}
public DeleteLoadBalancerPolicyRequest(string loadBalancerName, string policyName) { SetLoadBalancerName(loadBalancerName); SetPolicyName(policyName); }
public WindowProtectRecord(object options) { }
UnbufferedCharStream(int bufferSize) { data = new char[bufferSize]; }
public virtual GetOperationsResponse GetOperations(GetOperationsRequest request){var options = new InvokeOptions();options.RequestMarshaller = GetOperationsRequestMarshaller.Instance;options.ResponseUnmarshaller = GetOperationsResponseUnmarshaller.Instance;return Invoke<GetOperationsResponse>(request, options);}
void Encode(byte[] b, int o) { NB.EncodeInt32(b, o, w1); NB.EncodeInt32(b, o + 4, w2); NB.EncodeInt32(b, o + 8, w3); NB.EncodeInt32(b, o + 12, w4); NB.EncodeInt32(b, o + 16, w5); }
public WindowOneRecord(RecordInputStream in){field_1_h_hold = in.ReadShort();field_2_v_hold = in.ReadShort();field_3_width = in.ReadShort();field_4_height = in.ReadShort();field_5_options = in.ReadShort();field_6_active_sheet = in.ReadShort();field_7_first_visible_tab = in.ReadShort();field_8_num_selected_tabs = in.ReadShort();field_9_tab_width_ratio = in.ReadShort();}
public virtual StopWorkspacesResponse StopWorkspaces(StopWorkspacesRequest request){var options = new InvokeOptions();options.RequestMarshaller = StopWorkspacesRequestMarshaller.Instance;options.ResponseUnmarshaller = StopWorkspacesResponseUnmarshaller.Instance;return Invoke<StopWorkspacesResponse>(request, options);}
void Method() { if (isOpen) { isOpen = false; try { Dump(); } finally { try { channel.Truncate(fileLength); } finally { try { channel.Close(); } finally { fos.Close(); } } } } }
public virtual DescribeMatchmakingRuleSetsResponse DescribeMatchmakingRuleSets(DescribeMatchmakingRuleSetsRequest request){var options = new InvokeOptions();options.RequestMarshaller = DescribeMatchmakingRuleSetsRequestMarshaller.Instance;options.ResponseUnmarshaller = DescribeMatchmakingRuleSetsResponseUnmarshaller.Instance;return Invoke<DescribeMatchmakingRuleSetsResponse>(request, options);}
string (wordId, string[] surface, int off, int len) { }
public string String() { }
public static double CalculateVariance(double[] v){double r = double.NaN;if (v != null && v.Length >= 1){double m = 0, s = 0;int n = v.Length;for (int i = 0; i < n; i++){s += v[i];}m = s / n;s = 0;for (int i = 0; i < n; i++){s += (v[i] - m) * (v[i] - m);}r = (n == 1) ? 0 : s;}return r;}
public virtual DescribeResizeResponse DescribeResize(DescribeResizeRequest request){var options = new InvokeOptions();options.RequestMarshaller = DescribeResizeRequestMarshaller.Instance;options.ResponseUnmarshaller = DescribeResizeResponseUnmarshaller.Instance;return Invoke<DescribeResizeResponse>(request, options);}
public virtual bool PassedThroughNonGreedyDecision() { return passedThroughNonGreedyDecision; }
() { return end; }
void ProcessCells(CellHandler handler) { firstRow = range.GetFirstRow(); lastRow = range.GetLastRow(); firstColumn = range.GetFirstColumn(); lastColumn = range.GetLastColumn(); width = lastColumn - firstColumn + 1; SimpleCellWalkContext ctx = new SimpleCellWalkContext(); IRow currentRow = null; ICell currentCell = null; for (ctx.RowNumber = firstRow; ctx.RowNumber <= lastRow; ++ctx.RowNumber) { currentRow = sheet.GetRow(ctx.RowNumber); if (currentRow == null) { continue; } for (ctx.ColNumber = firstColumn; ctx.ColNumber <= lastColumn; ++ctx.ColNumber) { currentCell = currentRow.GetCell(ctx.ColNumber); if (currentCell == null) { continue; } if (IsEmpty(currentCell) && !traverseEmptyCells) { continue; } rowSize = ArithmeticUtils.MulAndCheck(ArithmeticUtils.SubAndCheck(ctx.RowNumber, firstRow), width); ctx.OrdinalNumber = ArithmeticUtils.AddAndCheck(rowSize, (ctx.ColNumber - firstColumn + 1)); handler.OnCell(currentCell, ctx); } } }
() { }
public int CompareTo(ScoreTerm other) => this.boost == other.boost ? other.bytes.Get().CompareTo(this.bytes.Get()) : this.boost.CompareTo(other.boost);
public int ProcessCharacters(char[] s, int len){for (int i = 0; i < len; i++){switch (s[i]){case FARSI_YEH:case YEH_BARREE:s[i] = YEH;break;case KEHEH:s[i] = KAF;break;case HEH_YEH:case HEH_GOAL:s[i] = HEH;break;case HAMZA_ABOVE:len = Delete(s, i, len);i--;break;default:break;}}return len;}
void LittleEndianOutput(out short value) { out.WriteShort(value); }
DiagnosticErrorListener() { this.exactOnly = exactOnly; }
public KeySchemaElement(string attributeName){SetAttributeName(attributeName);SetKeyType(keyType.ToString());}
public virtual GetAssignmentResult GetAssignment(GetAssignmentRequest request){request = BeforeClientExecution(request);return ExecuteGetAssignment(request);}
public bool AnyObjectId(AnyObjectId id) { return FindOffset(id) != 0; }
public GroupingSearch(bool allGroups) { this.AllGroups = allGroups; return this; }
public override void Set(string dimName, bool v){DimConfig ft = fieldTypes.TryGetValue(dimName, out var value) ? value : new DimConfig();if (!fieldTypes.ContainsKey(dimName)) fieldTypes[dimName] = ft;ft.MultiValued = v;}
{ IEnumerator<char> i = cells.Keys.GetEnumerator(); size = 0; while (i.MoveNext()) { char c = i.Current; Cell e = at(c); if (e.cmd >= 0) { size++; } } return size; }
public virtual DeleteVoiceConnectorResponse DeleteVoiceConnector(DeleteVoiceConnectorRequest request){var options = new InvokeOptions();options.RequestMarshaller = DeleteVoiceConnectorRequestMarshaller.Instance;options.ResponseUnmarshaller = DeleteVoiceConnectorResponseUnmarshaller.Instance;return Invoke<DeleteVoiceConnectorResponse>(request, options);}
public virtual DeleteLifecyclePolicyResponse DeleteLifecyclePolicy(DeleteLifecyclePolicyRequest request){var options = new InvokeOptions();options.RequestMarshaller = DeleteLifecyclePolicyRequestMarshaller.Instance;options.ResponseUnmarshaller = DeleteLifecyclePolicyResponseUnmarshaller.Instance;return Invoke<DeleteLifecyclePolicyResponse>(request, options);}
