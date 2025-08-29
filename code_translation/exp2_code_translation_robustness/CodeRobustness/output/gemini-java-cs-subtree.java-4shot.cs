public virtual void WriteShort(LittleEndianOutput @out){@out.WriteShort();}
public void AddAll(BlockList<T> src){if (src.Count == 0) srcDirIdx = 0; for (; srcDirIdx < src.TailDirIdx; srcDirIdx++) AddAll(src.Directory[srcDirIdx], 0, BLOCK_SIZE); if (src.TailBlkIdx != 0) AddAll(src.TailBlock, 0, src.TailBlkIdx);}
void Process(byte b) { if (upto == blockSize) { if (currentBlock != null) { AddBlock(currentBlock); } currentBlock = new byte[blockSize]; upto = 0; } currentBlock[upto++] = b; }
public ObjectId() { }
DeleteDomainEntryResult(DeleteDomainEntryRequest request){ request = BeforeClientExecution(request); return ExecuteDeleteDomainEntry; }
public override long RamBytesUsed() { return (termOffsets != null ? termOffsets.RamBytesUsed() : 0) + (termsDictOffsets != null ? termsDictOffsets.RamBytesUsed() : 0); }
public override string ToString() { byte[] raw = buffer; int msgB = RawParseUtils.TagMessage; if (msgB < 0) { return ""; } return RawParseUtils.Decode(GuessEncoding(), raw, msgB, raw.Length); }
public POIFSFileSystem(){ (true); _header.SetBATCount(1); _header.SetBATArray(new int[] { 1 }); BATBlock bb = BATBlock.CreateEmptyBATBlock(bigBlockSize, false); bb.SetOurBlockIndex(1); _bat_blocks.Add(bb); SetNextBlock(0, POIFSConstants.END_OF_CHAIN); SetNextBlock(1, POIFSConstants.FAT_SECTOR_BLOCK); _property_table.SetStartBlock(0); }
public void SetAddress(int address){slice = pool.buffers[address >> ByteBlockPool.BYTE_BLOCK_SHIFT];System.Diagnostics.Debug.Assert(slice != null);upto = address & ByteBlockPool.BYTE_BLOCK_MASK;offset0 = address;System.Diagnostics.Debug.Assert(upto < ByteBlockPool.BYTE_BLOCK_SIZE);}
public SubmoduleAddCommand SetPath(string path){this.path = path;return this;}
public virtual ListIngestionsResponse ListIngestions(ListIngestionsRequest request){var options = new InvokeOptions();options.RequestMarshaller = ListIngestionsRequestMarshaller.Instance;options.ResponseUnmarshaller = ListIngestionsResponseUnmarshaller.Instance;return Invoke<ListIngestionsResponse>(request, options);}
public QueryParserTokenManager(CharStream stream, int lexState) { SwitchTo(lexState); }
public virtual GetShardIteratorResponse GetShardIterator(GetShardIteratorRequest request){var options = new InvokeOptions();options.RequestMarshaller = GetShardIteratorRequestMarshaller.Instance;options.ResponseUnmarshaller = GetShardIteratorResponseUnmarshaller.Instance;return Invoke<GetShardIteratorResponse>(request, options);}
public ModifyStrategyRequest() : base("aegis", "2016-11-11", "ModifyStrategy", "vipaegis") { }
public bool Ready() { lock (lock) { if (in == null) { throw new IOException("InputStreamReader is closed"); } try { return bytes.HasRemaining() || in.Available() > 0; } catch { return false; } } }
public EscherOptRecord() { }
[MethodImpl(MethodImplOptions.Synchronized)] public int Read(char[] buffer, int offset, int length) { if (buffer == null) { throw new ArgumentNullException("buffer", "buffer == null"); } if (offset < 0 || length < 0 || offset > buffer.Length - length) { throw new IndexOutOfRangeException(); } if (length == 0) { return 0; } int copylen = count - pos < length ? count - pos : length; for (int i = 0; i < copylen; i++) { buffer[offset + i] = this._buffer[pos + i]; } return copylen; }
public OpenNLPSentenceBreakIterator(SentenceDetectorME sentenceOp){this.sentenceOp = sentenceOp;}
void Write(string str) { Write(str ?? "null"); }
public NotImplementedFunctionException(string functionName, NotImplementedException cause) : base(functionName, cause) { this.FunctionName = functionName; }
public override V V(){ return base.nextEntry.Value; }
public void ReadBytes(byte[] b, int offset, int len, bool useBuffer){int available = bufferLength - bufferPosition;if (len <= available){if (len > 0) Array.Copy(buffer, bufferPosition, b, offset, len);bufferPosition += len;}else{if (available > 0){Array.Copy(buffer, bufferPosition, b, offset, available);offset += available;len -= available;bufferPosition += available;}if (useBuffer && len < bufferSize){Refill();if (bufferLength < len){Array.Copy(buffer, 0, b, offset, bufferLength);throw new EndOfStreamException("read past EOF: " + this);}else{Array.Copy(buffer, 0, b, offset, len);}}else{var after = bufferStart + bufferPosition + len;if (after > Length()) throw new EndOfStreamException("read past EOF: " + this);ReadInternal(b, offset, len);bufferStart = after;bufferPosition = 0;bufferLength = 0;}}}
public virtual TagQueueResponse TagQueue(TagQueueRequest request){var options = new InvokeOptions();options.RequestMarshaller = TagQueueRequestMarshaller.Instance;options.ResponseUnmarshaller = TagQueueResponseUnmarshaller.Instance;return Invoke<TagQueueResponse>(request, options);}
void Invoke() { throw new NotSupportedException(); }
public virtual ModifyCacheSubnetGroupResponse ModifyCacheSubnetGroup(ModifyCacheSubnetGroupRequest request){var options = new InvokeOptions();options.RequestMarshaller = ModifyCacheSubnetGroupRequestMarshaller.Instance;options.ResponseUnmarshaller = ModifyCacheSubnetGroupResponseUnmarshaller.Instance;return Invoke<ModifyCacheSubnetGroupResponse>(request, options);}
public override void SetParams(string @params){base.SetParams(@params);language = country = variant = "";string[] st = @params.Split(new char[] { ',' }, System.StringSplitOptions.RemoveEmptyEntries);if (st.Length > 0) language = st[0];if (st.Length > 1) country = st[1];if (st.Length > 2) variant = st[2];}
public virtual DeleteDocumentationVersionResponse DeleteDocumentationVersion(DeleteDocumentationVersionRequest request){var options = new InvokeOptions();options.RequestMarshaller = DeleteDocumentationVersionRequestMarshaller.Instance;options.ResponseUnmarshaller = DeleteDocumentationVersionResponseUnmarshaller.Instance;return Invoke<DeleteDocumentationVersionResponse>(request, options);}
public override bool Equals(object obj){if (obj is not FacetLabel other) return false;if (length != other.length) return false;for (int i = length - 1; i >= 0; i--) if (!components[i].Equals(other.components[i])) return false;return true;}
public virtual GetInstanceAccessDetailsResponse GetInstanceAccessDetails(GetInstanceAccessDetailsRequest request){var options = new InvokeOptions();options.RequestMarshaller = GetInstanceAccessDetailsRequestMarshaller.Instance;options.ResponseUnmarshaller = GetInstanceAccessDetailsResponseUnmarshaller.Instance;return Invoke<GetInstanceAccessDetailsResponse>(request, options);}
public HSSFPolygon CreatePolygon(HSSFChildAnchor anchor){HSSFPolygon shape = new HSSFPolygon(this, anchor); shape.Parent = this; shape.Anchor = anchor; shapes.Add(shape); OnCreate(shape); return shape;}
public string GetSheetname(int sheetIndex){return GetBoundSheetRec(sheetIndex).Sheetname;}
public virtual GetDashboardResponse GetDashboard(GetDashboardRequest request){var options = new InvokeOptions();options.RequestMarshaller = GetDashboardRequestMarshaller.Instance;options.ResponseUnmarshaller = GetDashboardResponseUnmarshaller.Instance;return Invoke<GetDashboardResponse>(request, options);}
public AssociateSigninDelegateGroupsWithAccountResult AssociateSigninDelegateGroupsWithAccount(AssociateSigninDelegateGroupsWithAccountRequest request) { request = BeforeClientExecution; return ExecuteAssociateSigninDelegateGroupsWithAccount(request); }
virtual void method(MulBlankRecord mbr){for (j = 0; j < mbr.GetNumColumns(); j++){BlankRecord br = new BlankRecord(); br.SetColumn(j + mbr.GetFirstColumn()); br.SetRow(mbr.GetRow()); br.SetXFIndex(mbr.getXFAt); insertCell(br);}}
public static string Quote(string str){System.Text.StringBuilder sb = new System.Text.StringBuilder();sb.Append("\\Q");int apos = 0;int k;while ((k = str.IndexOf("\\E", apos)) >= 0){sb.Append(str.Substring(apos, k + 2 - apos)).Append("\\\\E\\Q");apos = k + 2;}return sb.Append(str.Substring(apos)).Append("\\E").ToString();}
public ByteBuffer(ByteBuffer value){throw new NotSupportedException();}
public ArrayPtg(object[][] values2d){nColumns = values2d[0].Length;nRows = values2d.Length;_nColumns = nColumns;_nRows = nRows;object[] vv = new object[_nColumns * _nRows];for (int r = 0; r < nRows; r++){object[] rowData = values2d[r];for (int c = 0; c < nColumns; c++){vv[getValueIndex(c, r)] = rowData[c];}}_arrayValues = vv;_reserved0Int = 0;_reserved1Short = 0;_reserved2Byte = 0;}
public virtual GetIceServerConfigResponse GetIceServerConfig(GetIceServerConfigRequest request){var options = new InvokeOptions();options.RequestMarshaller = GetIceServerConfigRequestMarshaller.Instance;options.ResponseUnmarshaller = GetIceServerConfigResponseUnmarshaller.Instance;return Invoke<GetIceServerConfigResponse>(request, options);}
public override string ToString() { return $"{GetType().FullName} [{GetValueAsString()}]"; }
public override string ToString() { return "ToChildBlockJoinQuery (" + parentQuery.ToString() + ")"; }
Interlocked.Increment(ref refCount);
UpdateConfigurationSetSendingEnabledResult UpdateConfigurationSetSendingEnabled(UpdateConfigurationSetSendingEnabledRequest request){ request = beforeClientExecution; return ExecuteUpdateConfigurationSetSendingEnabled(request); }
() { return XBATEntriesPerBlock * ; }
void Pow10(int pow10) { TenPower tp = TenPower.GetInstance(Math.Abs(pow10)); if (pow10 < 0) { MulShift(tp._divisor, tp._divisorShift); } else { MulShift(tp._multiplicand, tp._multiplierShift); } }
public override string ToString(){StringBuilder b = new StringBuilder();int l = length;b.Append(System.IO.Path.DirectorySeparatorChar);for (int i = 0; i < l; i++){b.Append(GetComponent(i));if (i < l - 1){b.Append(System.IO.Path.DirectorySeparatorChar);}}return b.ToString();}
public InstanceProfileCredentialsProvider InstanceProfileCredentialsProvider(ECSMetadataServiceCredentialsFetcher fetcher){this.fetcher = fetcher;this.SetRoleName(roleName);return this;}
void Progress(ProgressMonitor pm) { }
void Next() { if (!first) { ptr = 0; if (!Eof()) ParseEntry(); } }
public virtual E E() { if (iterator.PreviousIndex() >= start) { return iterator.Previous(); } throw new InvalidOperationException(); }
public virtual void String() { return; }
{ for (i = 0; i < _size; i++) if (_values[i] == value) return -1; }
public IList<CharsRef> List(char[] word, int length) { var stems = Stem(word, length); if (stems.Count < 2) { return stems; } var terms = new CharArraySet(8, dictionary.IgnoreCase); var deduped = new List<CharsRef>(); foreach (var s in stems) { if (!terms.Contains(s)) { deduped.Add(s); terms.Add(s); } } return deduped; }
public virtual GetGatewayResponsesResponse GetGatewayResponses(GetGatewayResponsesRequest request){var options = new InvokeOptions();options.RequestMarshaller = GetGatewayResponsesRequestMarshaller.Instance;options.ResponseUnmarshaller = GetGatewayResponsesResponseUnmarshaller.Instance;return Invoke<GetGatewayResponsesResponse>(request, options);}
void Position(long pos){currentBlockIndex = (int)(pos >> blockBits);currentBlock = blocks[currentBlockIndex];currentBlockUpto = (int)(pos & blockMask);}
(n){s = Math.Min(Available(), Math.Max(0, n)); ptr += s;}
public BootstrapActionDetail() { BootstrapActionConfig = new BootstrapActionConfig(); }
public void Serialize(LittleEndianOutput out) { out.WriteShort(field_1_row); out.WriteShort(field_2_col); out.WriteShort(field_3_flags); out.WriteShort(field_4_shapeid); out.WriteShort((short)field_6_author.Length); out.WriteByte(field_5_hasMultibyte ? (byte)0x01 : (byte)0x00); if (field_5_hasMultibyte) { StringUtil.PutUnicodeLE(field_6_author, out); } else { StringUtil.PutCompressedUnicode(field_6_author, out); } if (field_7_padding != null) { out.WriteByte((byte)field_7_padding.Value); } }
(string @string) => lastIndexOf;
public bool AddLast(E obj) { return addLastImpl; }
public virtual void Unset(string section, string subsection){ConfigSnapshot src, res; do { src = state.Get(); res = UnsetSection(src, section, subsection); } while (!state.CompareAndSet(src, res));}
public override string ToString() { return tagName; }
public void Add(int index, SubRecord element){subrecords.Insert(index, element);}
bool Boolean() { lock (mutex) { return Delegate().Remove(o); } }
DoubleMetaphoneFilter(TokenStream input) : base(input){}
public int InCoreLength { get { return inCoreLength; } }
public void Method(bool newValue) {}
public Pair(ContentSource oldSource, ContentSource newSource) { this.oldSource = oldSource; this.newSource = newSource; }
(int i) { if (i < 0 || i >= entries.Length) throw new IndexOutOfRangeException(); return entries[i]; }
public CreateRepoRequest(): base("cr", "2016-06-07", "CreateRepo", "cr", "openAPI"){UriPattern = "/repos"; Method = MethodType.POST;}
void Boolean() { }
public void Remove() { if (expectedModCount == list.ModCount) { if (lastLink != null) { Link<ET> next = lastLink.next; Link<ET> previous = lastLink.previous; next.previous = previous; previous.next = next; if (lastLink == link) { pos--; } link = previous; lastLink = null; expectedModCount++; list.Size--; list.ModCount++; } else { throw new InvalidOperationException(); } } else { throw new InvalidOperationException(); } }
MergeShardsResult(){ request = BeforeClientExecution(request); return ExecuteMergeShards(request); }
public virtual AllocateHostedConnectionResponse AllocateHostedConnection(AllocateHostedConnectionRequest request){var options = new InvokeOptions();options.RequestMarshaller = AllocateHostedConnectionRequestMarshaller.Instance;options.ResponseUnmarshaller = AllocateHostedConnectionResponseUnmarshaller.Instance;return Invoke<AllocateHostedConnectionResponse>(request, options);}
() { }
public static WeightedTerm WeightedTerm(Query query){return GetTerms(query, false);}
public ByteBuffer() { throw new ReadOnlyBufferException(); }
for (int i = 0; i < iterations; ++i) { int byte0 = blocks[blocksOffset++]; values[valuesOffset++] = byte0 >> 2; int byte1 = blocks[blocksOffset++]; values[valuesOffset++] = ((byte0 & 3) << 4) | (byte1 >> 4); int byte2 = blocks[blocksOffset++]; values[valuesOffset++] = ((byte1 & 15) << 2) | (byte2 >> 6); values[valuesOffset++] = byte2 & 63; }
string s = GetPath(); if (s == "/" || s == "") s = GetHost(); if (s == null) throw new System.ArgumentException(); string[] elements; if (scheme == "file" || LOCAL_FILE.IsMatch(s)) elements = System.Text.RegularExpressions.Regex.Split(s, "[\\\\" + System.IO.Path.DirectorySeparatorChar + "/]"); else elements = System.Text.RegularExpressions.Regex.Split(s, "/+"); if (elements.Length == 0) throw new System.ArgumentException(); string result = elements[elements.Length - 1]; if (Constants.DOT_GIT == result) result = elements[elements.Length - 2]; else if (result.EndsWith(Constants.DOT_GIT_EXT)) result = result.Substring(0, result.Length - Constants.DOT_GIT_EXT.Length); return result;
public virtual DescribeNotebookInstanceLifecycleConfigResponse DescribeNotebookInstanceLifecycleConfig(DescribeNotebookInstanceLifecycleConfigRequest request){var options = new InvokeOptions();options.RequestMarshaller = DescribeNotebookInstanceLifecycleConfigRequestMarshaller.Instance;options.ResponseUnmarshaller = DescribeNotebookInstanceLifecycleConfigResponseUnmarshaller.Instance;return Invoke<DescribeNotebookInstanceLifecycleConfigResponse>(request, options);}
string String() { return null; }
public virtual CreateVpnConnectionResponse CreateVpnConnection(CreateVpnConnectionRequest request){var options = new InvokeOptions();options.RequestMarshaller = CreateVpnConnectionRequestMarshaller.Instance;options.ResponseUnmarshaller = CreateVpnConnectionResponseUnmarshaller.Instance;return Invoke<CreateVpnConnectionResponse>(request, options);}
public virtual DescribeVoicesResponse DescribeVoices(DescribeVoicesRequest request){var options = new InvokeOptions();options.RequestMarshaller = DescribeVoicesRequestMarshaller.Instance;options.ResponseUnmarshaller = DescribeVoicesResponseUnmarshaller.Instance;return Invoke<DescribeVoicesResponse>(request, options);}
public virtual ListMonitoringExecutionsResponse ListMonitoringExecutions(ListMonitoringExecutionsRequest request){var options = new InvokeOptions();options.RequestMarshaller = ListMonitoringExecutionsRequestMarshaller.Instance;options.ResponseUnmarshaller = ListMonitoringExecutionsResponseUnmarshaller.Instance;return Invoke<ListMonitoringExecutionsResponse>(request, options);}
public DescribeJobRequest(string vaultName, string jobId){SetVaultName(vaultName);SetJobId(jobId);}
public EscherRecord GetEscherRecord(int index) { return escherRecords[index]; }
public virtual GetApisResponse GetApis(GetApisRequest request){var options = new InvokeOptions();options.RequestMarshaller = GetApisRequestMarshaller.Instance;options.ResponseUnmarshaller = GetApisResponseUnmarshaller.Instance;return Invoke<GetApisResponse>(request, options);}
public virtual DeleteSmsChannelResponse DeleteSmsChannel(DeleteSmsChannelRequest request){var options = new InvokeOptions();options.RequestMarshaller = DeleteSmsChannelRequestMarshaller.Instance;options.ResponseUnmarshaller = DeleteSmsChannelResponseUnmarshaller.Instance;return Invoke<DeleteSmsChannelResponse>(request, options);}
public TrackingRefUpdate() { }
() => Console.Write(Convert.ToString(b));
public QueryNode QueryNode => Children[0];
NotIgnoredFilter(WorkdirTreeIndex workdirTreeIndex){ this.workdirTreeIndex = workdirTreeIndex; }
public AreaRecord(RecordInputStream in1){field_1_formatFlags = in1.ReadShort();}
new GetThumbnailRequest(ProtocolType.HTTPS);
public virtual DescribeTransitGatewayVpcAttachmentsResponse DescribeTransitGatewayVpcAttachments(DescribeTransitGatewayVpcAttachmentsRequest request){var options = new InvokeOptions();options.RequestMarshaller = DescribeTransitGatewayVpcAttachmentsRequestMarshaller.Instance;options.ResponseUnmarshaller = DescribeTransitGatewayVpcAttachmentsResponseUnmarshaller.Instance;return Invoke<DescribeTransitGatewayVpcAttachmentsResponse>(request, options);}
public virtual PutVoiceConnectorStreamingConfigurationResponse PutVoiceConnectorStreamingConfiguration(PutVoiceConnectorStreamingConfigurationRequest request){var options = new InvokeOptions();options.RequestMarshaller = PutVoiceConnectorStreamingConfigurationRequestMarshaller.Instance;options.ResponseUnmarshaller = PutVoiceConnectorStreamingConfigurationResponseUnmarshaller.Instance;return Invoke<PutVoiceConnectorStreamingConfigurationResponse>(request, options);}
public OrdRange OrdRange(){return prefixToOrdRange.Get(dim);}
public override string ToString(){string symbol = ""; if (startIndex >= 0 && startIndex < InputStream.Size){symbol = InputStream.GetText(Interval.Of(startIndex, startIndex)); symbol = Utils.EscapeWhitespace(symbol, false);} return string.Format("{0}('{1}')", typeof(LexerNoViableAltException).Name, symbol);}
public E PeekFirst() { return peekFirstImpl; }
CreateWorkspacesResult CreateWorkspacesResult(){request = BeforeClientExecution(request);return ExecuteCreateWorkspaces(request);}
public virtual NumberFormatIndexRecord Copy() { return copy; }
public virtual DescribeRepositoriesResponse DescribeRepositories(DescribeRepositoriesRequest request){var options = new InvokeOptions();options.RequestMarshaller = DescribeRepositoriesRequestMarshaller.Instance;options.ResponseUnmarshaller = DescribeRepositoriesResponseUnmarshaller.Instance;return Invoke<DescribeRepositoriesResponse>(request, options);}
public SparseIntArray(int initialCapacity){initialCapacity = ArrayUtils.IdealIntArraySize(initialCapacity);_keys = new int[initialCapacity];_values = new int[initialCapacity];_size = 0;}
public virtual HyphenatedWordsFilter HyphenatedWordsFilter() { return new HyphenatedWordsFilter(input); }
CreateDistributionWithTagsResult() { request = BeforeClientExecution(request); return ExecuteCreateDistributionWithTags(request); }
public RandomAccessFile(string fileName, string mode) { new File(fileName); }
public virtual DeleteWorkspaceImageResponse DeleteWorkspaceImage(DeleteWorkspaceImageRequest request){var options = new InvokeOptions();options.RequestMarshaller = DeleteWorkspaceImageRequestMarshaller.Instance;options.ResponseUnmarshaller = DeleteWorkspaceImageResponseUnmarshaller.Instance;return Invoke<DeleteWorkspaceImageResponse>(request, options);}
public static string toHexString(long value){return value.ToString("X16");}
public virtual UpdateDistributionResponse UpdateDistribution(UpdateDistributionRequest request){var options = new InvokeOptions();options.RequestMarshaller = UpdateDistributionRequestMarshaller.Instance;options.ResponseUnmarshaller = UpdateDistributionResponseUnmarshaller.Instance;return Invoke<UpdateDistributionResponse>(request, options);}
public HSSFColor HSSFColor(int index){if (index == HSSFColor.HSSFColorPredefined.AUTOMATIC.Index){return HSSFColor.HSSFColorPredefined.AUTOMATIC.Color;}byte[] b = _palette.GetColor(index);return (b == null) ? null : new CustomColor();}
public ValueEval Evaluate(ValueEval operands, int srcRow, int srcCol){throw new NotImplementedFunctionException(_functionName);}
out.Write((short)field_1_number_crn_records); out.Write((short)field_2_sheet_table_index);
public virtual DescribeDBEngineVersionsResponse DescribeDBEngineVersions() { return DescribeDBEngineVersions(new DescribeDBEngineVersionsRequest()); }
public FormatRun(char character, int fontIndex) { this._character = character; this._fontIndex = fontIndex; }
public static byte[] ToBytes(char[] chars, int offset, int length){byte[] result = new byte[length * 2];int end = offset + length;int resultIndex = 0;for (int i = offset; i < end; ++i){char ch = chars[i];result[resultIndex++] = (byte)(ch >> 8);result[resultIndex++] = (byte)ch;}return result;}
public virtual UploadArchiveResponse UploadArchive(UploadArchiveRequest request){var options = new InvokeOptions();options.RequestMarshaller = UploadArchiveRequestMarshaller.Instance;options.ResponseUnmarshaller = UploadArchiveResponseUnmarshaller.Instance;return Invoke<UploadArchiveResponse>(request, options);}
public IList<IToken> List(int tokenIndex) { return GetHiddenTokensToLeft(tokenIndex, -1); }
public override bool Equals(object obj){if (this == obj) return true;if (!base.Equals(obj)) return false;if (GetType() != obj.GetType()) return false;AutomatonQuery other = (AutomatonQuery)obj;if (!compiled.Equals(other.compiled)) return false;if (!object.Equals(term, other.term)) return false;return true;}
public SpanQuery GetQuery(){SpanQuery[] spanQueries = new SpanQuery[weightBySpanQuery.Count];int i = 0;var sqi = weightBySpanQuery.GetEnumerator();while (sqi.MoveNext()){SpanQuery sq = sqi.Current.Key;float boost = sqi.Current.Value;if (boost != 1f){sq = new SpanBoostQuery(sq, boost);}spanQueries[i++] = sq;}if (spanQueries.Length == 1)return spanQueries[0];else return new SpanOrQuery(spanQueries);}
StashCreateCommand StashCreateCommand() { return new StashCreateCommand(); }
FieldInfo() { return byName[fieldName]; }
public virtual DescribeEventSourceResult DescribeEventSourceResult(DescribeEventSourceRequest request) { request = beforeClientExecution; return executeDescribeEventSource(request); }
public virtual GetDocumentAnalysisResponse GetDocumentAnalysis(GetDocumentAnalysisRequest request){var options = new InvokeOptions();options.RequestMarshaller = GetDocumentAnalysisRequestMarshaller.Instance;options.ResponseUnmarshaller = GetDocumentAnalysisResponseUnmarshaller.Instance;return Invoke<GetDocumentAnalysisResponse>(request, options);}
CancelUpdateStackResult CancelUpdateStackResult() { request = BeforeClientExecution(request); return ExecuteCancelUpdateStack(request); }
public virtual ModifyLoadBalancerAttributesResult ModifyLoadBalancerAttributes(ModifyLoadBalancerAttributesRequest request) { request = beforeClientExecution; return ExecuteModifyLoadBalancerAttributes(request); }
public virtual SetInstanceProtectionResponse SetInstanceProtection(SetInstanceProtectionRequest request){var options = new InvokeOptions();options.RequestMarshaller = SetInstanceProtectionRequestMarshaller.Instance;options.ResponseUnmarshaller = SetInstanceProtectionResponseUnmarshaller.Instance;return Invoke<SetInstanceProtectionResponse>(request, options);}
public virtual ModifyDBProxyResponse ModifyDBProxy(ModifyDBProxyRequest request){var options = new InvokeOptions();options.RequestMarshaller = ModifyDBProxyRequestMarshaller.Instance;options.ResponseUnmarshaller = ModifyDBProxyResponseUnmarshaller.Instance;return Invoke<ModifyDBProxyResponse>(request, options);}
public virtual void Add(char[] output, int offset, int len, int endOffset, int posLength){if (count == outputs.Length){outputs = ArrayUtil.Grow(outputs, count + 1);} if (count == endOffsets.Length){int[] next = new int[ArrayUtil.Oversize(1 + count, sizeof(int))]; Array.Copy(endOffsets, 0, next, 0, count); endOffsets = next;} if (count == posLengths.Length){int[] next = new int[ArrayUtil.Oversize(1 + count, sizeof(int))]; Array.Copy(posLengths, 0, next, 0, count); posLengths = next;} if (outputs[count] == null){outputs[count] = new CharsRefBuilder();} outputs[count].CopyChars(output, offset, len); endOffsets[count] = endOffset; posLengths[count] = posLength; count++;}
public FetchLibrariesRequest() : base("CloudPhoto", "2017-07-11", "FetchLibraries", "cloudphoto") { }
bool Exists() { return fs.Exists; }
protected FilterOutputStream() : base() { }
ScaleClusterRequest("/clusters/[ClusterId]"); SetMethod(MethodType.PUT);
public DataValidationConstraint DataValidationConstraint(int operatorType, string formula1, string formula2) { return DVConstraint.CreateTimeConstraint(operatorType, formula1, formula2); }
ListObjectParentPathsResult() { request = BeforeClientExecution(request); return ExecuteListObjectParentPaths(request); }
public DescribeCacheSubnetGroupsResult DescribeCacheSubnetGroups(DescribeCacheSubnetGroupsRequest request){ request = BeforeClientExecution(request); return ExecuteDescribeCacheSubnetGroups; }
field_5_options = sharedFormula.SetShortBoolean(field_5_options, flag);
bool () {}
public ErrorNode ErrorNode(Token badToken) { ErrorNodeImpl t = new ErrorNodeImpl(badToken); AddAnyChild(t); t.Parent = this; return t; }
public LatvianStemFilterFactory(IDictionary<string, string> args) : base(args) { if (args.Count > 0) { throw new ArgumentException("Unknown parameters: " + args); } }
public virtual RemoveSourceIdentifierFromSubscriptionResponse RemoveSourceIdentifierFromSubscription(RemoveSourceIdentifierFromSubscriptionRequest request){var options = new InvokeOptions();options.RequestMarshaller = RemoveSourceIdentifierFromSubscriptionRequestMarshaller.Instance;options.ResponseUnmarshaller = RemoveSourceIdentifierFromSubscriptionResponseUnmarshaller.Instance;return Invoke<RemoveSourceIdentifierFromSubscriptionResponse>(request, options);}
public static TokenFilterFactory ForName(string name, IDictionary<string, string> args){return loader.NewInstance(name, args);}
public AddAlbumPhotosRequest() : base("CloudPhoto", "2017-07-11", "AddAlbumPhotos", "cloudphoto") { }
public virtual GetThreatIntelSetResponse GetThreatIntelSet(GetThreatIntelSetRequest request){var options = new InvokeOptions();options.RequestMarshaller = GetThreatIntelSetRequestMarshaller.Instance;options.ResponseUnmarshaller = GetThreatIntelSetResponseUnmarshaller.Instance;return Invoke<GetThreatIntelSetResponse>(request, options);}
public virtual RevFilter Rev() { return new RevFilter(b.Clone(), a.Clone()); }
public virtual bool Boolean(object o){return false;}
public virtual bool HasArray() { return ProtectedHasArray(); }
UpdateContributorInsightsResult(UpdateContributorInsightsRequest request) { request = BeforeClientExecution; return ExecuteUpdateContributorInsights(request); }
records.Remove(fileShare); records.Remove(writeProtect); writeProtect = null;
public SolrSynonymParser(bool dedup, bool expand, Analyzer analyzer) : base(dedup, analyzer) { this.expand = expand; }
public virtual RequestSpotInstancesResponse RequestSpotInstances(RequestSpotInstancesRequest request){var options = new InvokeOptions();options.RequestMarshaller = RequestSpotInstancesRequestMarshaller.Instance;options.ResponseUnmarshaller = RequestSpotInstancesResponseUnmarshaller.Instance;return Invoke<RequestSpotInstancesResponse>(request, options);}
() => findObjectRecord.GetObjectData()
public virtual GetContactAttributesResponse GetContactAttributes(GetContactAttributesRequest request){var options = new InvokeOptions();options.RequestMarshaller = GetContactAttributesRequestMarshaller.Instance;options.ResponseUnmarshaller = GetContactAttributesResponseUnmarshaller.Instance;return Invoke<GetContactAttributesResponse>(request, options);}
public override string ToString() { return GetKey + ": " + GetValue(); }
public virtual ListTextTranslationJobsResponse ListTextTranslationJobs(ListTextTranslationJobsRequest request){var options = new InvokeOptions();options.RequestMarshaller = ListTextTranslationJobsRequestMarshaller.Instance;options.ResponseUnmarshaller = ListTextTranslationJobsResponseUnmarshaller.Instance;return Invoke<ListTextTranslationJobsResponse>(request, options);}
public virtual GetContactMethodsResponse GetContactMethods(GetContactMethodsRequest request){var options = new InvokeOptions();options.RequestMarshaller = GetContactMethodsRequestMarshaller.Instance;options.ResponseUnmarshaller = GetContactMethodsResponseUnmarshaller.Instance;return Invoke<GetContactMethodsResponse>(request, options);}
public static int GetFunctionIndex(string name){FunctionMetadata fd = FunctionMetadata.GetInstance().GetFunctionByNameInternal(name);if (fd == null){fd = FunctionMetadata.GetInstanceCetab().GetFunctionByNameInternal(name);if (fd == null){return -1;}}return fd.GetIndex();}
DescribeAnomalyDetectorsResult DescribeAnomalyDetectors(DescribeAnomalyDetectorsRequest request){request = beforeClientExecution;return executeDescribeAnomalyDetectors(request);}
public static string InsertId(string message, ObjectId changeId){return insertId;}
sz = db.GetObjectSize(objectId, typeHint); if (sz < 0) { if (typeHint == OBJ_ANY) throw new MissingObjectException(objectId.Copy(), JGitText.Get().UnknownObjectType2); throw new MissingObjectException(objectId.Copy(), typeHint); } return sz;
public virtual ImportInstallationMediaResponse ImportInstallationMedia(ImportInstallationMediaRequest request){var options = new InvokeOptions();options.RequestMarshaller = ImportInstallationMediaRequestMarshaller.Instance;options.ResponseUnmarshaller = ImportInstallationMediaResponseUnmarshaller.Instance;return Invoke<ImportInstallationMediaResponse>(request, options);}
public virtual PutLifecycleEventHookExecutionStatusResponse PutLifecycleEventHookExecutionStatus(PutLifecycleEventHookExecutionStatusRequest request){var options = new InvokeOptions();options.RequestMarshaller = PutLifecycleEventHookExecutionStatusRequestMarshaller.Instance;options.ResponseUnmarshaller = PutLifecycleEventHookExecutionStatusResponseUnmarshaller.Instance;return Invoke<PutLifecycleEventHookExecutionStatusResponse>(request, options);}
public NumberPtg() { m_in.ReadDouble(); }
public GetFieldLevelEncryptionConfigResult GetFieldLevelEncryptionConfig(GetFieldLevelEncryptionConfigRequest request){ request = BeforeClientExecution(request); return ExecuteGetFieldLevelEncryptionConfig(request);}
public virtual DescribeDetectorResponse DescribeDetector(DescribeDetectorRequest request){var options = new InvokeOptions();options.RequestMarshaller = DescribeDetectorRequestMarshaller.Instance;options.ResponseUnmarshaller = DescribeDetectorResponseUnmarshaller.Instance;return Invoke<DescribeDetectorResponse>(request, options);}
public virtual ReportInstanceStatusResponse ReportInstanceStatus(ReportInstanceStatusRequest request){var options = new InvokeOptions();options.RequestMarshaller = ReportInstanceStatusRequestMarshaller.Instance;options.ResponseUnmarshaller = ReportInstanceStatusResponseUnmarshaller.Instance;return Invoke<ReportInstanceStatusResponse>(request, options);}
public virtual DeleteAlarmResponse DeleteAlarm(DeleteAlarmRequest request){var options = new InvokeOptions();options.RequestMarshaller = DeleteAlarmRequestMarshaller.Instance;options.ResponseUnmarshaller = DeleteAlarmResponseUnmarshaller.Instance;return Invoke<DeleteAlarmResponse>(request, options);}
TokenStream TokenStream() { return new PortugueseStemFilter(input); }
public FtCblsSubRecord(){reserved = new byte[0];}
public bool Remove(object @object){lock (mutex){return c.Remove(@object);}}
public virtual GetDedicatedIpResponse GetDedicatedIp(GetDedicatedIpRequest request){var options = new InvokeOptions();options.RequestMarshaller = GetDedicatedIpRequestMarshaller.Instance;options.ResponseUnmarshaller = GetDedicatedIpResponseUnmarshaller.Instance;return Invoke<GetDedicatedIpResponse>(request, options);}
string String() { return null; }
public virtual ListStreamProcessorsResponse ListStreamProcessors(ListStreamProcessorsRequest request){var options = new InvokeOptions();options.RequestMarshaller = ListStreamProcessorsRequestMarshaller.Instance;options.ResponseUnmarshaller = ListStreamProcessorsResponseUnmarshaller.Instance;return Invoke<ListStreamProcessorsResponse>(request, options);}
public DeleteLoadBalancerPolicyRequest(string loadBalancerName, string policyName){LoadBalancerName = loadBalancerName;PolicyName = policyName;}
public WindowProtectRecord(object options) : base(options) {}
public UnbufferedCharStream(int bufferSize){data = new char[bufferSize];}
public virtual GetOperationsResponse GetOperations(GetOperationsRequest request){var options = new InvokeOptions();options.RequestMarshaller = GetOperationsRequestMarshaller.Instance;options.ResponseUnmarshaller = GetOperationsResponseUnmarshaller.Instance;return Invoke<GetOperationsResponse>(request, options);}
public virtual void Encode(byte[] b, int o){NB.EncodeInt32(b, o, w1); NB.EncodeInt32(b, o + 4, w2); NB.EncodeInt32(b, o + 8, w3); NB.EncodeInt32(b, o + 12, w4); NB.EncodeInt32(b, o + 16, w5);}
public WindowOneRecord(RecordInputStream in){field_1_h_hold = in.ReadShort();field_2_v_hold = in.ReadShort();field_3_width = in.ReadShort();field_4_height = in.ReadShort();field_5_options = in.ReadShort();field_6_active_sheet = in.ReadShort();field_7_first_visible_tab = in.ReadShort();field_8_num_selected_tabs = in.ReadShort();field_9_tab_width_ratio = in.ReadShort();}
public StopWorkspacesResult StopWorkspaces(StopWorkspacesRequest request){ request = beforeClientExecution; return ExecuteStopWorkspaces(request); }
public void Close() { if (isOpen) { isOpen = false; try { Dump(); } finally { try { channel.SetLength(fileLength); } finally { try { channel.Close(); } finally { fos.Close(); } } } } }
public virtual DescribeMatchmakingRuleSetsResponse DescribeMatchmakingRuleSets(DescribeMatchmakingRuleSetsRequest request){var options = new InvokeOptions();options.RequestMarshaller = DescribeMatchmakingRuleSetsRequestMarshaller.Instance;options.ResponseUnmarshaller = DescribeMatchmakingRuleSetsResponseUnmarshaller.Instance;return Invoke<DescribeMatchmakingRuleSetsResponse>(request, options);}
public String(int wordId, char[] surface, int off, int len) {}
public String() { }
public static void Method(double[] v){r = double.NaN; if (v != null && v.Length >= 1){double m = 0; double s = 0; int n = v.Length; for (int i = 0; i < n; i++){s += v[i];} m = s / n; s = 0; for (int i = 0; i < n; i++){s += (v[i] - m) * (v[i] - m);} r = (n == 1) ? 0 : s;}}
public virtual DescribeResizeResponse DescribeResize(DescribeResizeRequest request){var options = new InvokeOptions();options.RequestMarshaller = DescribeResizeRequestMarshaller.Instance;options.ResponseUnmarshaller = DescribeResizeResponseUnmarshaller.Instance;return Invoke<DescribeResizeResponse>(request, options);}
public bool IsPassedThroughNonGreedyDecision(){return passedThroughNonGreedyDecision;}
get { return end; }
public void Process(CellHandler handler){firstRow = range.GetFirstRow();lastRow = range.GetLastRow();firstColumn = range.GetFirstColumn();lastColumn = range.GetLastColumn();width = lastColumn - firstColumn + 1;SimpleCellWalkContext ctx = new SimpleCellWalkContext();IRow currentRow = null;ICell currentCell = null;for (ctx.rowNumber = firstRow; ctx.rowNumber <= lastRow; ++ctx.rowNumber){currentRow = sheet.GetRow(ctx.rowNumber);if (currentRow == null){continue;}for (ctx.colNumber = firstColumn; ctx.colNumber <= lastColumn; ++ctx.colNumber){currentCell = currentRow.GetCell(ctx.colNumber);if (currentCell == null){continue;}if (IsEmpty(currentCell) && !traverseEmptyCells){continue;}rowSize = ArithmeticUtils.MulAndCheck(ArithmeticUtils.SubAndCheck(ctx.rowNumber, firstRow), width);ctx.ordinalNumber = ArithmeticUtils.AddAndCheck(rowSize, (ctx.colNumber - firstColumn + 1));handler.OnCell(currentCell, ctx);}}}
() : base() { }
public int CompareTo(ScoreTerm other) { if (this.boost == other.boost) return other.bytes.CompareTo(this.bytes); else return this.boost.CompareTo(other.boost); }
public int Normalize(char[] s, int len){for (int i = 0; i < len; i++){switch (s[i]){case FARSI_YEH:case YEH_BARREE:case KEHEH:s[i] = KAF;break;case HEH_YEH:case HEH_GOAL:s[i] = HEH;break;case HAMZA_ABOVE:len = Delete(s, i, len);i--;break;default:break;}}return len;}
out.WriteShort();
public DiagnosticErrorListener(bool exactOnly) { this.exactOnly = exactOnly; }
public KeySchemaElement(string attributeName, KeyType keyType){AttributeName = attributeName;KeyType = keyType.ToString();}
public virtual GetAssignmentResponse GetAssignment(GetAssignmentRequest request){var options = new InvokeOptions();options.RequestMarshaller = GetAssignmentRequestMarshaller.Instance;options.ResponseUnmarshaller = GetAssignmentResponseUnmarshaller.Instance;return Invoke<GetAssignmentResponse>(request, options);}
public virtual bool Contains(AnyObjectId id){return FindOffset(id) != -1;}
public GroupingSearch GroupingSearch(bool allGroups) { this.allGroups = allGroups; return this; }
public void SetMultiValued(string dimName, bool v) { lock (this) { if (!fieldTypes.TryGetValue(dimName, out DimConfig ft)) { ft = new DimConfig(); fieldTypes[dimName] = ft; } ft.MultiValued = v; } }
int size = 0; foreach (char c in cells.Keys) { if (At(c).Cmd >= 0) { size++; } } return size;
public virtual DeleteVoiceConnectorResponse DeleteVoiceConnector(DeleteVoiceConnectorRequest request){var options = new InvokeOptions();options.RequestMarshaller = DeleteVoiceConnectorRequestMarshaller.Instance;options.ResponseUnmarshaller = DeleteVoiceConnectorResponseUnmarshaller.Instance;return Invoke<DeleteVoiceConnectorResponse>(request, options);}
public virtual DeleteLifecyclePolicyResponse DeleteLifecyclePolicy(DeleteLifecyclePolicyRequest request){var options = new InvokeOptions();options.RequestMarshaller = DeleteLifecyclePolicyRequestMarshaller.Instance;options.ResponseUnmarshaller = DeleteLifecyclePolicyResponseUnmarshaller.Instance;return Invoke<DeleteLifecyclePolicyResponse>(request, options);}
