@out.WriteShort(field_1_vcenter);
public void AddAll<T>(BlockList<T> src) { if (src.Size == 0) return; for (int srcDirIdx = 0; srcDirIdx < src.TailDirIdx; srcDirIdx++) AddAll(src.Directory[srcDirIdx], 0, BLOCK_SIZE); if (src.TailBlkIdx != 0) AddAll(src.TailBlock, 0, src.TailBlkIdx); }
} ; b = ]++upto[currentBlock } ; 0 = upto ; ]blockSize[ new = currentBlock } ; )currentBlock(AddBlock { )null != currentBlock( if { )blockSize == upto( if { )b( void
public ObjectId ObjectId => objectId;
} ; ) request ( ExecuteDeleteDomainEntry return ; ) request ( beforeClientExecution = request { ) request DeleteDomainEntryRequest (  DeleteDomainEntryResult
{ return (termOffsets?.RamBytesUsed() ?? 0) + (termsDictOffsets?.RamBytesUsed() ?? 0); }
public string Method() { byte[] raw = buffer; int msgB = RawParseUtils.TagMessage(raw, 0); if (msgB < 0) { return ""; } return RawParseUtils.Decode(GuessEncoding(raw, msgB, raw.Length)); }
; ) 0 ( SetStartBlock . _property_table ; ) FAT_SECTOR_BLOCK . POIFSConstants , 1 ( SetNextBlock ; ) END_OF_CHAIN . POIFSConstants , 0 ( SetNextBlock ; ) bb ( Add . _bat_blocks ; ) 1 ( SetOurBlockIndex . bb ; ) false , bigBlockSize ( CreateEmptyBATBlock . BATBlock = bb BATBlock ; ) } 1 { ] [ new ( SetBATArray . _header ; ) 1 ( SetBATCount . _header ; ) true (  { ) ( POIFSFileSystem
slice = pool.buffers[address >> ByteBlockPool.BYTE_BLOCK_SHIFT]; System.Diagnostics.Debug.Assert(slice != null); upto = address & ByteBlockPool.BYTE_BLOCK_MASK; offset0 = address; System.Diagnostics.Debug.Assert(upto < slice.Length);
} ; this return ; path = path . this { ) path string (  SubmoduleAddCommand
} ; ) request ( executeListIngestions return ; ) request ( beforeClientExecution = request { ) request ListIngestionsRequest (  ListIngestionsResult
} ; ) lexState ( SwitchTo ; ) stream ( { ) lexState , stream CharStream ( QueryParserTokenManager
return ExecuteGetShardIterator(new GetShardIteratorRequest { BeforeClientExecution = (request) => { } });
POST.MethodType("vipaegis", "ModifyStrategy", "2016-11-11", "aegis", (ModifyStrategyRequest)base);
{ lock (_lock) { if (_in == null) { throw new IOException("InputStreamReader is closed"); } try { return _bytes.HasRemaining() || _in.Available() > 0; } catch (IOException) { return false; } } }
EscherOptRecord OptRecord => _optRecord;
}} ; copylen return ; copylen += pos ; } ) i + pos [ buffer . this ] = ] i + offset [ buffer ; ++ i ) ; copylen < i ; 0 = i ( for { ; length : pos - count ? length < pos - count = copylen ; { 0 return ; } ) 0 == length ( if ; ) ; ( ArgumentOutOfRangeException new throw ) offset - Length . buffer > length || 0 > length || 0 > offset ( if ; { ) "buffer" ( ArgumentNullException new throw ; } ) null == buffer ( if { ) this ( lock { ) length , offset , buffer ] [ char ( int public
sentenceOp = new NLPSentenceDetectorOp(new OpenNLPSentenceBreakIterator());
write(str?.ToString());
} ; functionName = functionName . this ; ) cause , functionName ( base { ) cause NotImplementedException , functionName string ( NotImplementedFunctionException
} ; ) ( getValue . ) ( nextEntry . base return { ) (  V
public sealed void Read(byte[] b, int offset, int len, bool useBuffer) { int available = bufferLength - bufferPosition; if (len <= available) { if (len > 0) { System.Array.Copy(buffer, bufferPosition, b, offset, len); bufferPosition += len; } } else { if (available > 0) { System.Array.Copy(buffer, bufferPosition, b, offset, available); offset += available; len -= available; bufferPosition += available; } if (useBuffer && len < bufferSize) { Refill(); if (bufferLength < len) { System.Array.Copy(buffer, 0, b, offset, bufferLength); throw new System.IO.EndOfStreamException("read past EOF: " + this); } else { System.Array.Copy(buffer, 0, b, offset, len); bufferPosition = len; } } else { int after = bufferStart + bufferPosition + len; if (after > length) { throw new System.IO.EndOfStreamException("read past EOF: " + this); } ReadInternal(b, offset, len); bufferStart = after; bufferPosition = 0; bufferLength = 0; } } }
} ; ) request ( executeTagQueue return ; ) request ( beforeClientExecution = request { ) request TagQueueRequest (  TagQueueResult
} ; ) ( NotSupportedException new throw { ) (  void
} ; ) request ( ExecuteModifyCacheSubnetGroup return ; ) request ( BeforeClientExecution = request { ) request ModifyCacheSubnetGroupRequest (  CacheSubnetGroup
public void SetParams(string @params) { base.SetParams(@params); language = country = variant = ""; string[] tokens = @params.Split(','); if (tokens.Length > 0) language = tokens[0]; if (tokens.Length > 1) country = tokens[1]; if (tokens.Length > 2) variant = tokens[2]; }
DeleteDocumentationVersionResult ExecuteDeleteDocumentationVersion(DeleteDocumentationVersionRequest request);
public override bool Equals(object obj){var other=obj as FacetLabel;if(other==null)return false;if(length!=other.length)return false;for(int i=length-1;i>=0;i--){if(!components[i].Equals(other.components[i]))return false;}return true;}
}; ) request ( ExecuteGetInstanceAccessDetails return ; ) request ( BeforeClientExecution = request => { } ) request GetInstanceAccessDetailsRequest ( GetInstanceAccessDetailsResult
var shape = new HSSFPolygon(this, anchor); shape.Anchor = anchor; shapes.Add(shape); OnCreate(shape); return shape;
string GetSheetName(int sheetIndex) { return GetBoundSheetRec(sheetIndex).GetSheetName(); }
return await client.GetDashboardAsync(new GetDashboardRequest());
} ; ) request ( ExecuteAssociateSigninDelegateGroupsWithAccount return ; ) request ( BeforeClientExecution = request => { ) request AssociateSigninDelegateGroupsWithAccountRequest (  AssociateSigninDelegateGroupsWithAccountResult
void MulBlankRecord(MulBlankRecord mbr) { for (int j = 0; j < mbr.NumColumns; j++) { BlankRecord br = new BlankRecord { Column = j + mbr.FirstColumn, Row = mbr.Row, XFIndex = mbr.GetXFAt(j) }; InsertCell(br); } }
public static string String(string @string) { var sb = new System.Text.StringBuilder(); sb.Append("\\Q"); int apos = 0; int k; while ((k = @string.IndexOf("\\E", apos)) >= 0) { sb.Append(@string.Substring(apos, k - apos + 2)).Append("\\\\E\\Q"); apos = k + 2; } sb.Append(@string.Substring(apos)).Append("\\E"); return sb.ToString(); }
throw new NotSupportedException();
public ArrayPtg(object[][] values2d) { int nColumns = values2d[0].Length; int nRows = values2d.Length; _nColumns = nColumns; _nRows = nRows; object[] vv = new object[_nColumns * _nRows]; for (int r = 0; r < nRows; r++) { object[] rowData = values2d[r]; for (int c = 0; c < nColumns; c++) { vv[GetValueIndex(c, r)] = rowData[c]; } } _arrayValues = vv; _reserved0Int = 0; _reserved1Short = 0; _reserved2Byte = 0; }
} ; ) request ( ExecuteGetIceServerConfig return ; ) request ( beforeClientExecution = request { ) request GetIceServerConfigRequest ( GetIceServerConfigResult
public override string ToString() { return GetType().Name + " [" + GetValueAsString() + "]"; }
public override string ToString(string field) => "ToChildBlockJoinQuery (" + parentQuery.ToString() + ")";
public sealed void IncrementAndGet() { System.Threading.Interlocked.Increment(ref refCount); }
} ; ) request ( ExecuteUpdateConfigurationSetSendingEnabled return ; ) request ( BeforeClientExecution = request { ) request UpdateConfigurationSetSendingEnabledRequest ( UpdateConfigurationSetSendingEnabledResult
} ; INT_SIZE . LittleEndianConsts * ) ( getXBATEntriesPerBlock return { ) (
void pow10(int pow10) { TenPower tp = TenPower.getInstance(Math.Abs(pow10)); if (pow10 < 0) { mulShift(_divisor.tp, _divisorShift.tp); } else { mulShift(_multiplicand.tp, _multiplierShift.tp); } }
} ; ) ( ToString . b return } } ; ) DirectorySeparatorChar . Path ( Append . b { ) 1 - l < i ( if ; ) ) i ( GetComponent ( Append . b { ) ++ i ; l < i ; 0 = i ( for ; ) DirectorySeparatorChar . Path ( Append . b ; ) ( Length = l ; ) ( StringBuilder new = b StringBuilder { ) ( string
} ; this return ; ) roleName ( SetRoleName . fetcher . this ; fetcher = fetcher . this { ) fetcher EcsMetadataServiceCredentialsFetcher (  InstanceProfileCredentialsProvider
} ; pm = progressMonitor { ) pm ProgressMonitor (  void
} } ; ) ( parseEntry ) ) ( eof ! ( if ; 0 = ptr { ) ) ( first ! ( if { ) (  void
public E Previous() { if (iterator.PreviousIndex() >= start) { return iterator.Previous(); } throw new InvalidOperationException(); }
public string NewPrefix => this.newPrefix;
for (int i = 0; i < mSize; i++) { if (mValues[i] == value) return i; } return -1;
var stems = Stem(word, length); if (stems.Count < 2) { return stems; } var terms = new CharArraySet(8, dictionary.IgnoreCase); var deduped = new List<CharsRef>(); foreach (var s in stems) { if (!terms.Contains(s)) { deduped.Add(s); terms.Add(s); } } return deduped;
return ExecuteGetGatewayResponses(request);
void method(int pos) {         currentBlockIndex = pos >> blockBits;         currentBlock = blocks[currentBlockIndex];         currentBlockUpto = pos & blockMask;     }
s = Math.Min(Available(), Math.Max(0, n)); ptr += s; return s;
} ; ) bootstrapActionConfig ( SetBootstrapActionConfig { ) bootstrapActiongConfig BootstrapActionConfig ( BootstrapActionDetail
public void Serialize(LittleEndianOutput out) { out.WriteShort(field_1_row); out.WriteShort(field_2_col); out.WriteShort(field_3_flags); out.WriteShort(field_4_shapeid); out.WriteShort((short)field_6_author.Length); out.WriteByte((byte)(field_5_hasMultibyte ? 0x01 : 0x00)); if (field_5_hasMultibyte) { StringUtil.PutUnicodeLE(field_6_author, out); } else { StringUtil.PutCompressedUnicode(field_6_author, out); } if (field_7_padding != null) { out.WriteByte((byte)field_7_padding.Value); } }
return LastIndexOf(s, count);
} ; ) object ( addLastImpl return { ) object E (  bool
void UnsetSection(string section, string subsection) { ConfigSnapshot src, res; do { src = _state; res = src.WithSectionRemoved(section, subsection); } while (System.Threading.Interlocked.CompareExchange(ref _state, res, src) != src); }
public string TagName => tagName;
} ; ) element , index ( Add . Subrecords { ) element SubRecord , index (  void
} } ; ) o ( remove . ) ( delegate return { ) mutex ( lock { ) o object ( bool
return new DoubleMetaphoneFilter(input, maxCodeLength, inject);
} ; ) ( inCoreLength return { ) (
public bool MyValue {         set { this.value = value; } // 'value' is an implicit keyword for the incoming value     }
public Pair(ContentSource oldSource, ContentSource newSource) { this.oldSource = oldSource; this.newSource = newSource; }
if (count <= i) { throw new IndexOutOfRangeException(); } return entries[i];
new RouteBuilder().SetMethod(HttpMethod.Put).SetPath("/repos").WithDetails("cr", "CreateRepo", "2016-06-07").UseBaseHandler<CreateRepoRequest>();
public bool DeltaBaseAsOffset() { return false; }
} } ; ) ".etucexe ton yam noitarepo noitaremune ;deifidom saw noitcelloC" ( InvalidOperationException new throw { else } } ; ) ( InvalidOperationException new throw { else } ; ++ modCount . list ; -- size . list ; ++ expectedModCount ; null = lastLink ; previous = link } ; -- pos { ) link == lastLink ( if ; next = next . previous ; previous = previous . next ; previous . lastLink = previous > E < Link ; next . lastLink = next > E < Link { ) null != lastLink ( if { ) modCount . list == expectedModCount ( if { ) ( Remove void
var result = client.ExecuteMergeShards(new MergeShardsRequest { BeforeClientExecution = request => { return; } });
return ExecuteAllocateHostedConnection(new AllocateHostedConnectionRequest { BeforeClientExecution = request => {} });
} ; start return { ) (
public static WeightedTerm[] GetTerms(Query query) { }
() => throw new NotSupportedException();
} } ; 63 & byte2 = ] ++ valuesOffset [ values ; ) 6 >> byte2 ( | ) 2 << ) 15 & byte1 ( ( = ] ++ valuesOffset [ values ; ] ++ blocksOffset [ blocks = byte2 ; ) 4 >> byte1 ( | ) 4 << ) 3 & byte0 ( ( = ] ++ valuesOffset [ values ; ] ++ blocksOffset [ blocks = byte1 ; 2 >> byte0 = ] ++ valuesOffset [ values ; ] ++ blocksOffset [ blocks = byte0 { ) i ++ ; iterations < i ; 0 = i ( rof
string s = GetPath(); if (s == "/" || s == "") s = GetHost(); if (s == null) throw new ArgumentException(); string[] elements; if (scheme == "file" || LOCAL_FILE.IsMatch(s)) elements = s.Split(new[] { System.IO.Path.DirectorySeparatorChar, '/' }, StringSplitOptions.RemoveEmptyEntries); else elements = s.Split(new[] { '/' }, StringSplitOptions.RemoveEmptyEntries); if (elements.Length == 0) throw new ArgumentException(); string result = elements[elements.Length - 1]; if (result == Constants.DOT_GIT) result = elements[elements.Length - 2]; else if (result.EndsWith(Constants.DOT_GIT_EXT)) result = result.Substring(0, result.Length - Constants.DOT_GIT_EXT.Length); return result;
var response = await client.DescribeNotebookInstanceLifecycleConfigAsync(new DescribeNotebookInstanceLifecycleConfigRequest());
public string AccessKeySecret => this.accessKeySecret;
CreateVpnConnectionResponse response = client.CreateVpnConnection(request);
DescribeVoicesResponse response = client.DescribeVoices(new DescribeVoicesRequest());
public ListMonitoringExecutionsResponse ListMonitoringExecutions(ListMonitoringExecutionsRequest request) => client.ListMonitoringExecutions(request);
public DescribeJobRequest(string vaultName, string jobId) { VaultName = vaultName; JobId = jobId; }
return escherRecords[index];
} ; ) request ( ExecuteGetApis return ; ) request ( BeforeClientExecution = request { ) request GetApisRequest ( GetApisResult
} ; ) request ( ExecuteDeleteSmsChannel return ; ) request ( BeforeClientExecution = request { ) request DeleteSmsChannelRequest (  DeleteSmsChannelResult
() => { return trackingRefUpdate; }
} ; ) ) g n i r t S o T . b ( e t i r W . e l o s n o C . m e t s y S   { ) b   l o o b ( t n i r P   d i o v
QueryNode QueryNode() => GetChildren()[0];
workdirTreeIndex = new NotIgnoredFilter(index);
} ; ) ( ReadShort . @in = field_1_formatFlags { ) @in RecordInputStream ( AreaRecord
public GetThumbnailRequest() : base("cloudphoto", "GetThumbnail", "2017-07-11", "CloudPhoto") { this.Protocol = ProtocolType.Https; }
DescribeTransitGatewayVpcAttachmentsResponse response = await client.DescribeTransitGatewayVpcAttachmentsAsync(new DescribeTransitGatewayVpcAttachmentsRequest());
PutVoiceConnectorStreamingConfigurationResponse response = await client.PutVoiceConnectorStreamingConfigurationAsync(new PutVoiceConnectorStreamingConfigurationRequest());
} ; ) dim ( get . PrefixToOrdRange return { ) dim string (  OrdRange
return string.Format(System.Globalization.CultureInfo.CurrentCulture, "{0}('{1}')", typeof(Antlr4.Runtime.LexerNoViableAltException).Name, (startIndex >= 0 && startIndex < GetInputStream().Size ? Utils.EscapeWhitespace(GetInputStream().GetText(new Antlr4.Runtime.Misc.Interval(startIndex, startIndex)), false) : ""));
} ; ) ( PeekFirstImpl return { ) ( E
var result = ExecuteCreateWorkspaces(new CreateWorkspacesRequest(new CreateWorkspacesResponse()), request => { var beforeClientExecution = request; return; });
} ; ) ( copy return { ) (  NumberFormatIndexRecord
DescribeRepositoriesResponse response = await client.DescribeRepositoriesAsync(new DescribeRepositoriesRequest());
public SparseIntArray(int initialCapacity) { initialCapacity = ArrayUtils.IdealIntArraySize(initialCapacity); mKeys = new int[initialCapacity]; mValues = new int[initialCapacity]; mSize = 0; }
} ; ) input ( HyphenatedWordsFilter new return { ) input TokenStream (  HyphenatedWordsFilter
} ; ) request ( ExecuteCreateDistributionWithTags return ; ) request ( BeforeClientExecution = request { ) request CreateDistributionWithTagsRequest ( CreateDistributionWithTagsResult
public RandomAccessFile(string fileName, string mode)
} ; ) request ( executeDeleteWorkspaceImage return ; ) request ( beforeClientExecution = request => { } ) request DeleteWorkspaceImageRequest ( DeleteWorkspaceImageResult
public static string ToHexString(long value) { System.Text.StringBuilder sb = new System.Text.StringBuilder(16); WriteHex(sb, value, 16, ""); return sb.ToString(); }
new UpdateDistributionRequest().WithBeforeClientExecution(request => {}).ExecuteUpdateDistribution();
if (index == HSSFColor.Automatic.Index) { return HSSFColor.Automatic; } byte[] b = _palette.GetColor(index); return b == null ? null : new CustomColor(index, b);
public ValueEval evaluate(ValueEval[] operands, int srcRow, int srcCol) {             throw new NotImplementedFunctionException(_functionName);         }
void Write(LittleEndianOutput out) { out.WriteShort(field_1_number_crn_records); out.WriteShort(field_2_sheet_table_index); }
return DescribeDBEngineVersions(new DescribeDBEngineVersionsRequest());
FormatRun ( character , fontIndex ) { this . _character = character ; this . _fontIndex = fontIndex ; }
} ; result return } ; ch ) ( byte ) = ] ++ resultIndex [ result ; ) 8 >> ch ( ( byte ) = ] ++ resultIndex [ result ; ] i [ chars = ch { ) i ++ ; end < i ; offset = i ( for ; 0 = resultIndex ; length + offset = end ; ] 2 * length [ byte new = result ] [ { ) length , offset , chars ] [ char (  ] [ byte static public
return await client.ExecuteUploadArchiveAsync(new UploadArchiveRequest { BeforeClientExecution = request });
List<Token> Method(int tokenIndex) => GetHiddenTokensToLeft(tokenIndex - 1);
public override bool Equals(object obj) { if (ReferenceEquals(this, obj)) return true; if (!base.Equals(obj)) return false; if (obj is null || GetType() != obj.GetType()) return false; var other = (AutomatonQuery)obj; if (!compiled.Equals(other.compiled)) return false; if (!object.Equals(term, other.term)) return false; return true; }
{     SpanQuery[] spanQueries = new SpanQuery[size()]; // Assumes size() is available, likely weightBySpanQuery.size()     Iterator<SpanQuery> sqi = weightBySpanQuery.keySet().iterator();     int i = 0;     while (sqi.hasNext()) {         SpanQuery sq = sqi.next();         float boost = weightBySpanQuery.get(sq); // 'boost' must be declared elsewhere         if (boost != 1f) {             sq = new SpanBoostQuery(sq, boost);         }         spanQueries[i++] = sq;     }     if (spanQueries.length == 1) {         return spanQueries[0];     } else {         return new SpanOrQuery(spanQueries);     } }
() => new StashCreateCommand(repo);
public Field getFieldByName(String fieldName) {         // In Java, the reflection class is java.lang.reflect.Field         return this.getClass().getField(fieldName);     }
var response = await client.DescribeEventSourceAsync(new DescribeEventSourceRequest());
GetDocumentAnalysisResult ExecuteGetDocumentAnalysis(GetDocumentAnalysisRequest request);
CancelUpdateStackResponse CancelUpdateStack(CancelUpdateStackRequest request);
return client.ModifyLoadBalancerAttributes(request);
return await client.SetInstanceProtectionAsync(request);
} ; ) request ( ExecuteModifyDBProxy return ; ) request ( BeforeClientExecution = request { ) request ModifyDBProxyRequest ( ModifyDBProxyResult
if (count == outputs.Length) { outputs = ArrayUtil.Grow(outputs, count + 1); } if (count == endOffsets.Length) { int[] next = new int[ArrayUtil.Oversize(count + 1, sizeof(int))]; Array.Copy(endOffsets, 0, next, 0, count); endOffsets = next; } if (count == posLengths.Length) { int[] next = new int[ArrayUtil.Oversize(count + 1, sizeof(int))]; Array.Copy(posLengths, 0, next, 0, count); posLengths = next; } if (outputs[count] == null) { outputs[count] = new CharsRefBuilder(); } outputs[count].CopyChars(output, offset, len); endOffsets[count] = endOffset; posLengths[count] = posLength; count++;
public FetchLibrariesRequest() : base("cloudphoto", "FetchLibraries", "2017-07-11", "CloudPhoto") { this.Protocol = ProtocolType.Https; }
bool Exists() => fs.Exists(objects);
public FilterOutputStream(Stream @out) { this.@out = @out; }
new RpcRequest<ScaleClusterRequest>(MethodType.PUT, "/clusters/{ClusterId}", "csk", "ScaleCluster", "2015-12-15", "CS");
public DataValidationConstraint CreateTimeConstraint(int operatorType, string formula1, string formula2) { return new DVConstraint(operatorType, formula1, formula2); }
ListObjectParentPathsResult result = ExecuteListObjectParentPaths(new ListObjectParentPathsRequest { BeforeClientExecution = request => { return; } });
DescribeCacheSubnetGroupsResponse DescribeCacheSubnetGroups(DescribeCacheSubnetGroupsRequest request) { request = BeforeClientExecution(request); return ExecuteDescribeCacheSubnetGroups(request); }
} ; ) flag , field_5_options ( setShortBoolean . sharedFormula = field_5_options { ) flag bool (  void
bool ReuseObjects() { return true; }
} ; t return ; ) this ( setParent . t ; ) t ( addAnyChild ; ) badToken ( ErrorNodeImpl new = t ErrorNodeImpl { ) badToken Token (  ErrorNode
public LatvianStemFilterFactory(IDictionary<string, string> args) { if (args.Count != 0) { throw new ArgumentException($"Unknown parameters: {args}"); } }
return client.RemoveSourceIdentifierFromSubscription(new RemoveSourceIdentifierFromSubscriptionRequest { SubscriptionName = eventSubscription.Name });
public static TokenFilterFactory NewInstance(string name, IDictionary<string, string> args) { return loader.NewInstance(name, args); }
public AddAlbumPhotosRequest() : base("cloudphoto", "AddAlbumPhotos", "2017-07-11", "CloudPhoto") { Protocol = "https"; }
} ; ) request ( ExecuteGetThreatIntelSet return ; ) request ( BeforeClientExecution = request => { ) request GetThreatIntelSetRequest (  GetThreatIntelSetResult
} ; ) ) ( Clone . b , ) ( Clone . a ( Binary new return { ) (  RevFilter
public override bool Equals(object o) { return o is ArmenianStemmer; }
public bool protectedHasArray() { return false; }
} ; ) request ( ExecuteUpdateContributorInsights return ; ) request ( beforeClientExecution = request { ) request UpdateContributorInsightsRequest (  UpdateContributorInsightsResult
void Cleanup() { records.Remove(fileShare); records.Remove(writeProtect); fileShare = null; writeProtect = null; }
public SolrSynonymParser(bool dedup, bool expand, Analyzer analyzer) : base(analyzer) { this.expand = expand; this.dedup = dedup; }
} ; ) request ( ExecuteRequestSpotInstances return ; ) request ( beforeClientExecution = request { ) request RequestSpotInstancesRequest ( RequestSpotInstancesResult
} ; ) ( getObjectData . ) ( findObjectRecord return { ) (  ] [
}; ) request ( ExecuteGetContactAttributes return ; ) request ( BeforeClientExecution = request { ) request GetContactAttributesRequest ( GetContactAttributesResult
public override string ToString() { return $"{GetKey()}: {GetValue()}"; }
return await client.ListTextTranslationJobsAsync(request);
} ; ) request ( ExecuteGetContactMethods return ; ) request ( BeforeClientExecution = request => { ) request GetContactMethodsRequest ( GetContactMethodsResult
public static int GetFunctionByName(string name) { FunctionMetadata fd = GetInstance().GetFunctionByNameInternal(name); if (fd == null) { fd = GetInstanceCetab().GetFunctionByNameInternal(name); } if (fd == null) { return -1; } return fd.GetIndex(); }
request = BeforeClientExecution(request); return await ExecuteDescribeAnomalyDetectorsAsync(request);
public static string message(ObjectId insertId, string changeId) { return changeId; }
{ long sz = db.getObjectSize(this, objectId); if (sz < 0) { if (typeHint == OBJ_ANY) throw new MissingObjectException(objectId.copy(), JGitText.get().unknownObjectType2); else throw new MissingObjectException(objectId.copy(), typeHint); } return sz; }
} ; ) request ( ExecuteImportInstallationMedia return ; ) request ( BeforeClientExecution = request { ) request ImportInstallationMediaRequest ( ImportInstallationMediaResult
public PutLifecycleEventHookExecutionStatusResponse PutLifecycleEventHookExecutionStatus(PutLifecycleEventHookExecutionStatusRequest request) => client.PutLifecycleEventHookExecutionStatus(request);
} ; ) ) ( ReadDouble . in ( { ) in LittleEndianInput ( NumberPtg
} ; ) request ( executeGetFieldLevelEncryptionConfig return ; ) request ( beforeClientExecution = request { ) request GetFieldLevelEncryptionConfigRequest ( GetFieldLevelEncryptionConfigResult
return client.ExecuteDescribeDetector(new DescribeDetectorRequest { BeforeRequestEvent = (request) => { /* logic */ } });
return client.ExecuteReportInstanceStatus(new ReportInstanceStatusRequest());
DeleteAlarmsResponse ExecuteDeleteAlarm(DeleteAlarmsRequest request);
TokenStream ApplyFilter(TokenStream input) => new PortugueseStemFilter(input);
reserved[ENCODED_SIZE] = new FtCblsSubRecord();
public override bool Remove(object @object) { lock (mutex) { return c.Remove(@object); } }
GetDedicatedIpResult result = ExecuteGetDedicatedIp(new GetDedicatedIpRequest { BeforeClientExecution = request => { return; } });
} ; " >= _p" + precedence return { ) (  string
ListStreamProcessorsResult ExecuteListStreamProcessors(ListStreamProcessorsRequest request) { return default; }
public class DeleteLoadBalancerPolicyRequest { public string PolicyName { get; set; } public string LoadBalancerName { get; set; } }
public WindowProtectRecord(options) { _options = options; }
public UnbufferedCharStream(int bufferSize) { n = 0; data = new char[bufferSize]; }
} ; ) request ( executeGetOperations return ; ) request ( beforeClientExecution = request => { ) request GetOperationsRequest (  GetOperationsResult
} ; ) w5 , 16 + o , b ( EncodeInt32 . NB ; ) w4 , 12 + o , b ( EncodeInt32 . NB ; ) w3 , 8 + o , b ( EncodeInt32 . NB ; ) w2 , 4 + o , b ( EncodeInt32 . NB ; ) w1 , o , b ( EncodeInt32 . NB { ) o , b ] [ (  void
public WindowOneRecord(RecordInputStream @in) { field_1_h_hold = @in.ReadShort(); field_2_v_hold = @in.ReadShort(); field_3_width = @in.ReadShort(); field_4_height = @in.ReadShort(); field_5_options = @in.ReadShort(); field_6_active_sheet = @in.ReadShort(); field_7_first_visible_tab = @in.ReadShort(); field_8_num_selected_tabs = @in.ReadShort(); field_9_tab_width_ratio = @in.ReadShort(); }
} ; ) request ( executeStopWorkspaces return ; ) request ( beforeClientExecution = request { ) request StopWorkspacesRequest ( StopWorkspacesResult
} } } } ; ) ( Close . fos { finally } ; ) ( Close . channel { try { finally } ; ) fileLength ( SetLength . channel { try { finally } ; ) ( dump { try ; false = isOpen { ) isOpen ( if { ) ( void
DescribeMatchmakingRuleSetsResponse response = await client.DescribeMatchmakingRuleSetsAsync(new DescribeMatchmakingRuleSetsRequest());
public string Method(int wordId, char[] surface, int off, int len) { return null; }
string GetPath(string pathStr) => pathStr;
} ; r return } ; s : 0 ? ) 1 == n ( = r } ; ) m - ] i [ v ( * ) m - ] i [ v ( += s { ) ++ i ; n < i ; 0 = i ( for ; 0 = s ; n / s = m } ; ] i [ v += s { ) ++ i ; n < i ; 0 = i ( for ; Length . v = n ; 0 = s ; 0 = m { ) 1 >= Length . v && null != v ( if ; NaN . double = r { ) v ] [ (  static public
} ; ) request ( executeDescribeResize return ; ) request ( beforeClientExecution = request { ) request DescribeResizeRequest (  DescribeResizeResult
public bool PassedThroughNonGreedyDecision() { return false; }
() { return end(0); }
} } } ; ) ctx , currentCell ( OnCell . handler ; ) ) 1 + firstColumn - ColNumber . ctx ( , width ( AddAndCheck . ArithmeticUtils = OrdinalNumber . ctx ; ) width ) ( , ) firstRow , RowNumber . ctx ( SubAndCheck . ArithmeticUtils ) ( ( MulAndCheck . ArithmeticUtils = width } ; continue { ) traverseEmptyCells ! && ) currentCell ( IsEmpty ( if } ; continue { ) null == currentCell ( if ; ) ColNumber . ctx ( GetCell . currentRow = currentCell { ) ColNumber . ctx ++ ; lastColumn <= ColNumber . ctx ; firstColumn = ColNumber . ctx ( for } ; continue { ) null == currentRow ( if ; ) RowNumber . ctx ( GetRow . sheet = currentRow { ) RowNumber . ctx ++ ; lastRow <= RowNumber . ctx ; firstRow = RowNumber . ctx ( for ; null = currentCell ICell ; null = currentRow IRow ; ) ( SimpleCellWalkContext new = ctx var ; 1 + firstColumn - lastColumn = width ; ) ( LastColumn . range = lastColumn ; ) ( FirstColumn . range = firstColumn ; ) ( LastRow . range = lastRow ; ) ( FirstRow . range = firstRow { ) handler ICellHandler (  void Traverse
} ; pos return { ) (
if (this.boost == other.boost) {         return this.bytes.compareTo(other.bytes);     } else {         return Float.compare(this.boost, other.boost);     }
} ; len return } } ; break : ; break ; -- i ; ) len , i , s ( delete = len : HAMZA_ABOVE case ; break ; HEH = ] i [ s : HEH_GOAL case : HEH_YEH case ; break ; KAF = ] i [ s : KEHEH case ; break ; YEH = ] i [ s : YEH_BARREE case : FARSI_YEH case { ) ] i [ s ( switch { ) ++ i ; len < i ; 0 = i ( for { ) len , ] [ s (
} ; ) _options ( WriteShort . out { ) out LittleEndianOutput (  void
} ; exactOnly = exactOnly . this { ) exactOnly bool ( DiagnosticErrorListener
public class KeySchemaElement { public string AttributeName { get; set; } public KeyType KeyType { get; set; } }
GetAssignmentResult result = ExecuteGetAssignment(new GetAssignmentRequest { BeforeClientExecution = request => { return; } });
bool SomeMethod(AnyObjectId id) { return FindOffset(id) != -1; }
public GroupingSearch GroupingSearch(bool allGroups) { this.allGroups = allGroups; return this; }
public void SetMultiValued(string dimName, bool v) { lock (this) { if (!fieldTypes.TryGetValue(dimName, out DimConfig ft)) { ft = new DimConfig(); fieldTypes.Add(dimName, ft); } ft.multiValued = v; } }
{ int size = 0; foreach (char c in cells.Keys) { Cell e = at(c); if (e.cmd >= 0) { size++; } } return size; }
} ; ) request ( ExecuteDeleteVoiceConnector return ; ) request ( beforeClientExecution = request { ) request DeleteVoiceConnectorRequest (  DeleteVoiceConnectorResponse
}; ) request ( ExecuteDeleteLifecyclePolicy return ; ) request ( beforeClientExecution = request { ) request DeleteLifecyclePolicyRequest ( DeleteLifecyclePolicyResult
