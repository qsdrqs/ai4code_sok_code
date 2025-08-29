void (LittleEndianOutput out) { out.WriteShort; }
void Method(BlockList<T> src) { if (src.size == 0) srcDirIdx = 0; for (; srcDirIdx < src.tailDirIdx; srcDirIdx++) addAll(src.directory[srcDirIdx], 0, BLOCK_SIZE); if (src.tailBlkIdx != 0) addAll(src.tailBlock, 0, src.tailBlkIdx); }
void Add(char b) { if (upto == blockSize) { if (currentBlock != null) { AddBlock(currentBlock); } currentBlock = new char[blockSize]; upto = 0; } currentBlock[upto++] = b; }
public ObjectId() { }
DeleteDomainEntryResult DeleteDomainEntry(DeleteDomainEntryRequest request) { request = BeforeClientExecution(request); return ExecuteDeleteDomainEntry(request); }
() { return ((termOffsets != null) ? termOffsets.ramBytesUsed() : 0) + ((termsDictOffsets != null) ? termsDictOffsets.ramBytesUsed() : 0); }
public string GetMessage() { byte[] raw = buffer; int msgB = RawParseUtils.TagMessage(raw); if (msgB < 0) { return ""; } return RawParseUtils.Decode(GuessEncoding(), raw, msgB, raw.Length); }
public POIFSFileSystem() : this(true) { _header.SetBATCount(1); _header.SetBATArray(new[] { 1 }); BATBlock bb = BATBlock.CreateEmptyBATBlock(bigBlockSize, false); bb.SetOurBlockIndex(1); _bat_blocks.Add(bb); SetNextBlock(0, POIFSConstants.END_OF_CHAIN); SetNextBlock(1, POIFSConstants.FAT_SECTOR_BLOCK); _property_table.SetStartBlock(0); }
void _(int address) { slice = pool.buffers[address >> ByteBlockPool.BYTE_BLOCK_SHIFT]; System.Diagnostics.Debug.Assert(slice != null); upto = address & ByteBlockPool.BYTE_BLOCK_MASK; offset0 = address; System.Diagnostics.Debug.Assert(upto < ); }
SubmoduleAddCommand Path(string path) { this.path = path; return this; }
ListIngestionsResult() { request = beforeClientExecution(request); return executeListIngestions(request); }
public QueryParserTokenManager(CharStream stream, int lexState) { SwitchTo(lexState); }
GetShardIteratorResult GetShardIterator(GetShardIteratorRequest request) { request = BeforeClientExecution(request); return ExecuteGetShardIterator; }
public ModifyStrategyRequest() : base("aegis", "2016-11-11", "ModifyStrategy", "vipaegis") { }
bool HasData() { lock(@lock) { if (@in == null) { throw new IOException("InputStreamReader is closed"); } try { return bytes.HasRemaining || @in.Available > 0; } catch (Exception) { return false; } } }
public EscherOptRecord() { }
public int Read(char[] buffer, int offset, int length){lock(this){if(buffer==null)throw new ArgumentNullException(nameof(buffer));if(offset<0||length<0||offset>buffer.Length-length)throw new ArgumentOutOfRangeException();if(length==0)return 0;int copylen=Math.Min(count-pos,length);for(int i=0;i<copylen;i++){buffer[offset+i]=this.buffer[pos+i];}return copylen;}}
OpenNLPSentenceBreakIterator() { this.sentenceOp = sentenceOp; }
void Foo(string str){write(str!=null?str:"null");}
public NotImplementedFunctionException(string functionName, NotImplementedException cause) : base(functionName, cause) { this.functionName = functionName; }
V() { return base.nextEntry.GetValue(); }
public void ReadBytes(byte[] b, int offset, int len, bool useBuffer){int available=bufferLength-bufferPosition;if(len<=available){if(len>0)Array.Copy(buffer,bufferPosition,b,offset,len);bufferPosition+=len;}else{if(available>0){Array.Copy(buffer,bufferPosition,b,offset,available);offset+=available;len-=available;bufferPosition+=available;}if(useBuffer&&len<bufferSize){Refill();if(bufferLength<len){Array.Copy(buffer,0,b,offset,bufferLength);throw new EndOfStreamException("read past EOF: "+this);}else{Array.Copy(buffer,0,b,offset,len);}}else{long after=bufferStart+bufferPosition+len;if(after>Length())throw new EndOfStreamException("read past EOF: "+this);ReadInternal(b,offset,len);bufferStart=after;bufferPosition=0;bufferLength=0;}}}
private TagQueueResult TagQueue(TagQueueRequest request) { request = BeforeClientExecution(request); return ExecuteTagQueue(request); }
void () { throw new NotSupportedException(); }
CacheSubnetGroup() { request = BeforeClientExecution(request); return ExecuteModifyCacheSubnetGroup(request); }
void SetParams(string parameters){base.SetParams(parameters);language=country=variant="";var tokens=parameters.Split(',');if(tokens.Length>0)language=tokens[0];if(tokens.Length>1)country=tokens[1];if(tokens.Length>2)variant=tokens[2];}
DeleteDocumentationVersionResult DeleteDocumentationVersion(DeleteDocumentationVersionRequest request) { request = beforeClientExecution; return executeDeleteDocumentationVersion(request); }
public override bool Equals(object obj){if(!(obj is FacetLabel)){return false;}FacetLabel other=(FacetLabel)obj;if(length!=other.length){return false;}for(int i=length-1;i>=0;i--){if(!components[i].Equals(other.components[i])){return false;}}return true;}
GetInstanceAccessDetailsResult GetInstanceAccessDetails(GetInstanceAccessDetailsRequest request) { request = BeforeClientExecution(request); return executeGetInstanceAccessDetails; }
HSSFPolygon HSSFPolygon(HSSFChildAnchor anchor){HSSFPolygon shape=new HSSFPolygon(this,anchor);shape.SetParent();shape.SetAnchor(anchor);shapes.Add(shape);OnCreate(shape);return shape;}
string GetSheetName(int sheetIndex) { return GetBoundSheetRec(sheetIndex).SheetName; }
GetDashboardResult GetDashboard(GetDashboardRequest request) { request = beforeClientExecution(request); return executeGetDashboard; }
AssociateSigninDelegateGroupsWithAccountResult AssociateSigninDelegateGroupsWithAccount(AssociateSigninDelegateGroupsWithAccountRequest request) { request = beforeClientExecution; return executeAssociateSigninDelegateGroupsWithAccount(request); }
void Method(MulBlankRecord mbr){for(int j=0;j<mbr.getNumColumns();j++){BlankRecord br=new BlankRecord();br.setColumn((short)(j+mbr.getFirstColumn()));br.setRow(mbr.getRow());br.setXFIndex(mbr.getXFAt());insertCell(br);}}
public static string QuoteMeta(string str){var sb=new System.Text.StringBuilder();sb.Append("\\Q");int apos=0,k;while((k=str.IndexOf("\\E",apos))>=0){sb.Append(str.Substring(apos,k+2-apos)).Append("\\\\E\\Q");apos=k+2;}return sb.Append(str.Substring(apos)).Append("\\E").ToString();}
public ByteBuffer(object value) { throw new ReadOnlyBufferException(); }
public ArrayPtg(object[][] values2d){nColumns=values2d[0].Length;nRows=values2d.Length;_nColumns=(short)nColumns;_nRows=(short)nRows;object[] vv=new object[_nColumns*_nRows];for(int r=0;r<nRows;r++){object[] rowData=values2d[r];for(int c=0;c<nColumns;c++){vv[getValueIndex(c,r)]=rowData[c];}}_arrayValues=vv;_reserved0Int=0;_reserved1Short=0;_reserved2Byte=0;}
public GetIceServerConfigResult GetIceServerConfig(GetIceServerConfigRequest request) { request = beforeClientExecution(request); return executeGetIceServerConfig(request); }
public override string ToString() { return GetType().FullName + " [" + GetValueAsString() + "]"; }
public override string ToString() { return "ToChildBlockJoinQuery (" + parentQuery.ToString() + ")"; }
void () { System.Threading.Interlocked.Increment(ref refCount); }
UpdateConfigurationSetSendingEnabledResult UpdateConfigurationSetSendingEnabled(UpdateConfigurationSetSendingEnabledRequest request) { request = beforeClientExecution; return executeUpdateConfigurationSetSendingEnabled(request); }
(  ) { return getXBATEntriesPerBlock ( ) * ; }
void Pow10(int pow10){ TenPower tp = TenPower.GetInstance(Math.Abs(pow10)); if(pow10 < 0){ mulShift( , tp._divisorShift); } else { mulShift(tp._multiplicand, tp._multiplierShift); } }
string ToString() { StringBuilder b = new StringBuilder(); l = length; b.Append(Path.DirectorySeparatorChar); for (i = 0; i < l; i++) { b.Append(getComponent(i)); if (i < l - 1) { b.Append(Path.DirectorySeparatorChar); } } return b.ToString(); }
public InstanceProfileCredentialsProvider(ECSMetadataServiceCredentialsFetcher fetcher){ this.fetcher = fetcher; SetRoleName(roleName); }
void Method(ProgressMonitor pm) { ; }
void Method() { if (!first) { ptr = 0; if (!eof()) parseEntry(); } }
E() { if (iterator.PreviousIndex() >= start) { return iterator.Previous(); } throw new InvalidOperationException(); }
string () { return ; }
(value) { for (i = 0; i < mSize; i++) if (mValues[i] == value) return -1; }
List<CharsRef> GetStems(char[] word, int length) { var stems = Stem(word, length); if (stems.Count < 2) return stems; var terms = new CharArraySet(8, dictionary.ignoreCase); var deduped = new List<CharsRef>(); foreach (CharsRef s in stems) { if (!terms.Contains(s)) { deduped.Add(s); terms.Add(s); } } return deduped; }
GetGatewayResponsesResult GetGatewayResponses(GetGatewayResponsesRequest request) { request = beforeClientExecution(request); return executeGetGatewayResponses; }
void _(long pos) { currentBlockIndex = (int)(pos >> blockBits); currentBlock = default; currentBlockUpto = (int)(pos & blockMask); }
( n ) { s = (int)Math.Min(available(), Math.Max(0, n)); ptr += s; }
BootstrapActionDetail() { setBootstrapActionConfig(bootstrapActionConfig); }
public void Serialize(LittleEndianOutput out){out.WriteShort(field_1_row);out.WriteShort(field_2_col);out.WriteShort(field_3_flags);out.WriteShort(field_4_shapeid);out.WriteShort(field_6_author.Length);out.WriteByte(field_5_hasMultibyte?(byte)0x01:(byte)0x00);if(field_5_hasMultibyte){StringUtil.PutUnicodeLE(field_6_author,out);}else{StringUtil.PutCompressedUnicode(field_6_author,out);}if(field_7_padding!=null){out.WriteByte((byte)field_7_padding.Value);} }
(string @string) => lastIndexOf;
bool (E obj) { return addLastImpl; }
void Method(string section, string subsection) { ConfigSnapshot src, res; do { src = state.Get(); res = unsetSection(src, section, subsection); } while (!state.CompareAndSet(src, res)); }
string () { return tagName; }
void Add(int index, SubRecord element) { subrecords.Add(index, element); }
bool () { lock (mutex) { return delegate().Remove(o); } }
DoubleMetaphoneFilter DoubleMetaphoneFilter(TokenStream input) { return new DoubleMetaphoneFilter(); }
() { return inCoreLength; }
void (bool newValue) { ; }
Pair(ContentSource oldSource, ContentSource newSource) { this.oldSource = oldSource; this.newSource = newSource; }
( i ) { if ( ) throw new IndexOutOfRangeException( i ); return entries[ i ]; }
public CreateRepoRequest() : base("cr", "2016-06-07", "CreateRepo", "cr") { SetUriPattern("/repos"); SetMethod(); }
bool () { }
void Method() { if (expectedModCount == list.modCount) { if (lastLink != null) { var next = lastLink.next; Link<ET> previous = lastLink.previous; next.previous = previous; previous.next = next; if (lastLink == link) { pos--; } link = previous; lastLink = null; expectedModCount++; list.size--; list.modCount++; } else { throw new InvalidOperationException(); } } else { throw new InvalidOperationException(); } }
MergeShardsResult() { request = BeforeClientExecution(request); return ExecuteMergeShards(request); }
AllocateHostedConnectionResult AllocateHostedConnection(AllocateHostedConnectionRequest request){ request = beforeClientExecution(request); return executeAllocateHostedConnection; }
() { }
public static WeightedTerm[] GetTerms(Query query) { return GetTerms(query, false); }
ByteBuffer() { throw new ReadOnlyBufferException(); }
static void Process(byte[] blocks, int blocksOffset, int[] values, int valuesOffset, int iterations) { for (int i = 0; i < iterations; ++i) { int byte0 = blocks[blocksOffset++] & 0xFF; values[valuesOffset++] = byte0 >> 2; int byte1 = blocks[blocksOffset++] & 0xFF; values[valuesOffset++] = ((byte0 & 3) << 4) | (byte1 >> 4); int byte2 = blocks[blocksOffset++] & 0xFF; values[valuesOffset++] = ((byte1 & 15) << 2) | (byte2 >> 6); values[valuesOffset++] = byte2 & 63; } }
string GetRepositoryName() { string s = GetPath(); if ("/".Equals(s) || "".Equals(s)) s = GetHost(); if (s == null) throw new ArgumentException(); string[] elements; if ("file".Equals(scheme) || LOCAL_FILE.Match(s).Success) elements = s.Split(new[] { Path.DirectorySeparatorChar, '/', '\\' }, StringSplitOptions.None); else elements = Regex.Split(s, "/+"); if (elements.Length == 0) throw new ArgumentException(); string result = elements[elements.Length - 1]; if (Constants.DOT_GIT.Equals(result)) result = elements[elements.Length - 2]; else if (result.EndsWith(Constants.DOT_GIT_EXT)) result = result.Substring(0, result.Length - Constants.DOT_GIT_EXT.Length); return result; }
DescribeNotebookInstanceLifecycleConfigResult DescribeNotebookInstanceLifecycleConfig(DescribeNotebookInstanceLifecycleConfigRequest request) { request = beforeClientExecution(request); return executeDescribeNotebookInstanceLifecycleConfig(request); }
string () { return ; }
CreateVpnConnectionResult() { request = BeforeClientExecution(request); return ExecuteCreateVpnConnection(request); }
public DescribeVoicesResult DescribeVoices() { request = BeforeClientExecution(request); return ExecuteDescribeVoices(request); }
ListMonitoringExecutionsResult ListMonitoringExecutions(ListMonitoringExecutionsRequest request) { request = BeforeClientExecution(request); return ExecuteListMonitoringExecutions; }
DescribeJobRequest(string vaultName, string jobId) { setVaultName(vaultName); setJobId(jobId); }
public EscherRecord Get(int index) { return escherRecords[index]; }
GetApisResult GetApis(GetApisRequest request) { request = BeforeClientExecution(request); return ExecuteGetApis(request); }
DeleteSmsChannelResult DeleteSmsChannel(DeleteSmsChannelRequest request) { request = BeforeClientExecution(request); return ExecuteDeleteSmsChannel; }
internal TrackingRefUpdate() { }
void Method() { Console.Write(b.ToString()); }
QueryNode() { return getChildren()[0]; }
public NotIgnoredFilter(int workdirTreeIndex) { this.workdirTreeIndex = workdirTreeIndex; }
public AreaRecord(RecordInputStream inStream) { field_1_formatFlags = inStream.ReadShort(); }
GetThumbnailRequest(ProtocolType.HTTPS);
DescribeTransitGatewayVpcAttachmentsResult DescribeTransitGatewayVpcAttachments(DescribeTransitGatewayVpcAttachmentsRequest request){ request = BeforeClientExecution(request); return ExecuteDescribeTransitGatewayVpcAttachments(request); }
public PutVoiceConnectorStreamingConfigurationResult PutVoiceConnectorStreamingConfiguration(PutVoiceConnectorStreamingConfigurationRequest request) { request = BeforeClientExecution(request); return ExecutePutVoiceConnectorStreamingConfiguration(request); }
OrdRange GetOrdRange() => prefixToOrdRange.TryGetValue(dim, out var value) ? value : null;
public override string ToString() { string symbol = ""; if (startIndex >= 0 && startIndex < GetInputStream().Size) { symbol = GetInputStream().GetText(Interval.Of(startIndex, startIndex)); symbol = Utils.EscapeWhitespace(symbol, false); } return string.Format(System.Globalization.CultureInfo.CurrentCulture, "{0}('{1}')", typeof(LexerNoViableAltException).Name, symbol); }
E PeekFirst() { return peekFirstImpl; }
CreateWorkspacesResult CreateWorkspaces() { request = beforeClientExecution(request); return executeCreateWorkspaces(request); }
NumberFormatIndexRecord NumberFormatIndexRecord() { return copy; }
private DescribeRepositoriesResult DescribeRepositories(DescribeRepositoriesRequest request){request=BeforeClientExecution(request);return ExecuteDescribeRepositories(request);}
public SparseIntArray(int initialCapacity) { initialCapacity = ArrayUtils.IdealIntArraySize(initialCapacity); mKeys = new int[initialCapacity]; mValues = new int[initialCapacity]; mSize = 0; }
HyphenatedWordsFilter() { return new HyphenatedWordsFilter(input); }
public CreateDistributionWithTagsResult CreateDistributionWithTags() { request = beforeClientExecution(request); return executeCreateDistributionWithTags(request); }
public RandomAccessFile(string fileName, string mode){ new FileInfo(fileName); }
DeleteWorkspaceImageResult DeleteWorkspaceImage(DeleteWorkspaceImageRequest request) { request = BeforeClientExecution(request); return executeDeleteWorkspaceImage; }
public static string ToHex(long value) { var sb = new System.Text.StringBuilder(16); WriteHex(sb, value, 16, ""); return sb.ToString(); }
UpdateDistributionResult UpdateDistribution(UpdateDistributionRequest request) { request = beforeClientExecution(request); return executeUpdateDistribution; }
HSSFColor GetColor(short index){if(index==HSSFColorPredefined.AUTOMATIC.getIndex()){return HSSFColorPredefined.AUTOMATIC.getColor();}byte[] b=_palette.getColor(index);return b==null?null:new CustomColor();}
ValueEval Evaluate(ValueEval[] operands, int srcRow, int srcCol) { throw new NotImplementedFunctionException(_functionName); }
void Serialize() { out.Write((short)field_1_number_crn_records); out.Write((short)field_2_sheet_table_index); }
DescribeDBEngineVersionsResult DescribeDBEngineVersions() { return DescribeDBEngineVersions(new DescribeDBEngineVersionsRequest()); }
public FormatRun(char character, int fontIndex) { this._character = character; this._fontIndex = fontIndex; }
public static byte[] ConvertCharsToBytes(char[] chars, int offset, int length){byte[] result=new byte[length*2];int end=offset+length;int resultIndex=0;for(int i=offset;i<end;++i){char ch=chars[i];result[resultIndex++]=(byte)(ch>>8);result[resultIndex++]=(byte)ch;}return result;}
UploadArchiveResult UploadArchive(UploadArchiveRequest request) { request = BeforeClientExecution(request); return ExecuteUploadArchive(request); }
System.Collections.IList GetHiddenTokensToLeft(int tokenIndex) { return GetHiddenTokensToLeft(tokenIndex, -1); }
public override bool Equals(object obj) { if (ReferenceEquals(this, obj)) return true; if (!base.Equals(obj)) return false; if (GetType() != obj.GetType()) return false; AutomatonQuery other = (AutomatonQuery)obj; if (!compiled.Equals(other.compiled)) return false; if (term == null) { if (other.term != null) return false; } else if (!term.Equals(other.term)) return false; return true; }
SpanQuery SpanQuery(){SpanQuery[] spanQueries=new SpanQuery[size()];var sqi=weightBySpanQuery.Keys.GetEnumerator();int i=0;while(sqi.MoveNext()){SpanQuery sq=sqi.Current;float boost=weightBySpanQuery[sq];if(boost!=1f){sq=new SpanBoostQuery(sq,boost);}spanQueries[i++]=sq;}return spanQueries.Length==1?spanQueries[0]:SpanOrQuery(spanQueries);}
StashCreateCommand StashCreateCommand() { return new StashCreateCommand(); }
FieldInfo FieldInfo() { return byName[fieldName]; }
DescribeEventSourceResult DescribeEventSource(DescribeEventSourceRequest request) { request = BeforeClientExecution(request); return ExecuteDescribeEventSource(request); }
GetDocumentAnalysisResult() { request = BeforeClientExecution(request); return ExecuteGetDocumentAnalysis(request); }
CancelUpdateStackResult CancelUpdateStack(CancelUpdateStackRequest request) { request = BeforeClientExecution(request); return ExecuteCancelUpdateStack(request); }
private ModifyLoadBalancerAttributesResult ModifyLoadBalancerAttributes(ModifyLoadBalancerAttributesRequest request) { request = BeforeClientExecution(request); return ExecuteModifyLoadBalancerAttributes(request); }
SetInstanceProtectionResult SetInstanceProtection(SetInstanceProtectionRequest request){request=BeforeClientExecution(request);return ExecuteSetInstanceProtection(request);}
ModifyDBProxyResult ModifyDBProxy(ModifyDBProxyRequest request) { request = BeforeClientExecution(request); return ExecuteModifyDBProxy; }
void Add(char[] output,int offset,int len,int endOffset,int posLength){if(count==outputs.Length){Array.Resize(ref outputs,count+1);}if(count==endOffsets.Length){Array.Resize(ref endOffsets,1+count);}if(count==posLengths.Length){Array.Resize(ref posLengths,1+count);}if(outputs[count]==null){outputs[count]=new CharsRefBuilder();}outputs[count].CopyChars(output,offset,len);endOffsets[count]=endOffset;posLengths[count]=posLength;count++;}
public FetchLibrariesRequest() : base("CloudPhoto", "2017-07-11", "FetchLibraries", "cloudphoto") { }
bool () { return fs.exists; }
FilterOutputStream() { this.@out = @out; }
ScaleClusterRequest("/clusters/[ClusterId]"); SetMethod(MethodType.PUT);
DataValidationConstraint GetTimeConstraint(int operatorType, string formula1, string formula2) { return DVConstraint.CreateTimeConstraint(operatorType, formula1, formula2); }
ListObjectParentPathsResult ListObjectParentPaths() { request = BeforeClientExecution(request); return ExecuteListObjectParentPaths(request); }
public DescribeCacheSubnetGroupsResult DescribeCacheSubnetGroups(DescribeCacheSubnetGroupsRequest request) { request = beforeClientExecution(request); return executeDescribeCacheSubnetGroups; }
void Method() { field_5_options = sharedFormula.setShortBoolean(field_5_options, flag); }
bool () { }
ErrorNodeImpl ErrorNode(Token badToken) { ErrorNodeImpl t = new ErrorNodeImpl(badToken); addAnyChild(t); t.setParent; return t; }
public LatvianStemFilterFactory(IDictionary<string,string> args) : base(args) { if (args.Count != 0) { throw new ArgumentException("Unknown parameters: " + args); } }
EventSubscription() { request = BeforeClientExecution(request); return ExecuteRemoveSourceIdentifierFromSubscription(request); }
public static TokenFilterFactory Create(string name, IDictionary<string, string> args) { return loader.NewInstance(name, args); }
public AddAlbumPhotosRequest() : base("CloudPhoto", "2017-07-11", "AddAlbumPhotos", "cloudphoto") { }
GetThreatIntelSetResult GetThreatIntelSet(GetThreatIntelSetRequest request){request = beforeClientExecution(request);return executeGetThreatIntelSet;}
RevFilter() { return new Binary(a.Clone(), b.Clone()); }
bool (  object o ) { return ; }
bool ( ) { return protectedHasArray ( ) ; }
UpdateContributorInsightsResult UpdateContributorInsights(UpdateContributorInsightsRequest request) { request = beforeClientExecution; return executeUpdateContributorInsights(request); }
void () { records.Remove(fileShare); records.Remove(writeProtect); ; writeProtect = null; }
public SolrSynonymParser(bool dedup, bool expand, Analyzer analyzer) : base(dedup, analyzer) { this.expand = expand; }
public RequestSpotInstancesResult RequestSpotInstances(RequestSpotInstancesRequest request){ request = BeforeClientExecution(request); return ExecuteRequestSpotInstances(request); }
() => findObjectRecord.GetObjectData();
GetContactAttributesResult GetContactAttributes(GetContactAttributesRequest request) { request = beforeClientExecution; return executeGetContactAttributes(request); }
string ToString() { return getKey + ": " + getValue(); }
ListTextTranslationJobsResult ListTextTranslationJobs() { request = BeforeClientExecution(request); return ExecuteListTextTranslationJobs(request); }
GetContactMethodsResult() { request = BeforeClientExecution(request); return ExecuteGetContactMethods(request); }
public static int GetIndexByName(string name){FunctionMetadata fd=getInstance().getFunctionByNameInternal(name);if(fd==null){fd=getInstanceCetab().getFunctionByNameInternal(name);if(fd==null){return-1;}}return(int)fd.getIndex();}
public DescribeAnomalyDetectorsResult DescribeAnomalyDetectors(DescribeAnomalyDetectorsRequest request) { request = BeforeClientExecution(request); return ExecuteDescribeAnomalyDetectors(request); }
public static string (string message, ObjectId changeId) { return insertId; }
long GetObjectSize(AnyObjectId objectId, int typeHint){var sz=db.GetObjectSize();if(sz<0){if(typeHint==OBJ_ANY)throw new MissingObjectException(objectId.Copy(),JGitText.Get().UnknownObjectType2);throw new MissingObjectException(objectId.Copy(),typeHint);}return sz;}
ImportInstallationMediaResult ImportInstallationMedia(ImportInstallationMediaRequest request) { request = BeforeClientExecution(request); return ExecuteImportInstallationMedia(request); }
PutLifecycleEventHookExecutionStatusResult() { request = BeforeClientExecution(request); return ExecutePutLifecycleEventHookExecutionStatus(request); }
NumberPtg() { @in.ReadDouble(); }
GetFieldLevelEncryptionConfigResult GetFieldLevelEncryptionConfig(GetFieldLevelEncryptionConfigRequest request) { request = beforeClientExecution; return executeGetFieldLevelEncryptionConfig(request); }
DescribeDetectorResult DescribeDetector ( DescribeDetectorRequest request ) { request = beforeClientExecution ( request ) ; return executeDescribeDetector ; }
ReportInstanceStatusResult ReportInstanceStatus(ReportInstanceStatusRequest request) { request = beforeClientExecution(request); return executeReportInstanceStatus(request); }
DeleteAlarmResult DeleteAlarm(DeleteAlarmRequest request){request = beforeClientExecution(request);return executeDeleteAlarm;}
TokenStream TokenStream() { return new PortugueseStemFilter(input); }
public FtCblsSubRecord() { reserved = new ; }
public bool Remove(object obj){lock(mutex){return c.Remove(obj);}}
GetDedicatedIpResult GetDedicatedIp() { request = beforeClientExecution(request); return executeGetDedicatedIp(request); }
string () { return ; }
public ListStreamProcessorsResult ListStreamProcessors(ListStreamProcessorsRequest request) { request = BeforeClientExecution(request); return ExecuteListStreamProcessors(request); }
DeleteLoadBalancerPolicyRequest(string loadBalancerName, string policyName){ SetLoadBalancerName(loadBalancerName); SetPolicyName(policyName); }
WindowProtectRecord(object options) { ; }
UnbufferedCharStream(int bufferSize) { data = new char[bufferSize]; }
GetOperationsResult GetOperations(GetOperationsRequest request) { request = beforeClientExecution(request); return executeGetOperations; }
void Write(byte[] b, int o) { NB.EncodeInt32(b, o, w1); NB.EncodeInt32(b, o + 4, w2); NB.EncodeInt32(b, o + 8, w3); NB.EncodeInt32(b, o + 12, w4); NB.EncodeInt32(b, o + 16, w5); }
WindowOneRecord(RecordInputStream inStream){field_1_h_hold=inStream.ReadInt16();field_2_v_hold=inStream.ReadInt16();field_3_width=inStream.ReadInt16();field_4_height=inStream.ReadInt16();field_5_options=inStream.ReadInt16();field_6_active_sheet=inStream.ReadInt16();field_7_first_visible_tab=inStream.ReadInt16();field_8_num_selected_tabs=inStream.ReadInt16();field_9_tab_width_ratio=inStream.ReadInt16();}
StopWorkspacesResult StopWorkspaces(StopWorkspacesRequest request){ request = beforeClientExecution; return executeStopWorkspaces(request); }
void Close() { if (isOpen) { isOpen = false; try { dump(); } finally { try { channel.SetLength(fileLength); } finally { try { channel.Close(); } finally { fos.Close(); } } } } }
DescribeMatchmakingRuleSetsResult DescribeMatchmakingRuleSets() { request = BeforeClientExecution(request); return ExecuteDescribeMatchmakingRuleSets(request); }
public String(int wordId, char[] surface, int off, int len) { }
string () { }
public static double Variance(double[] v){double r=double.NaN;if(v!=null&&v.Length>=1){double m=0,s=0;int n=v.Length;for(int i=0;i<n;i++){s+=v[i];}m=s/n;s=0;for(int i=0;i<n;i++){s+=(v[i]-m)*(v[i]-m);}r=(n==1)?0:s;}return r;}
DescribeResizeResult() { request = BeforeClientExecution(request); return ExecuteDescribeResize(request); }
bool () { return passedThroughNonGreedyDecision; }
() => end;
void Method(CellHandler handler){firstRow=range.GetFirstRow();lastRow=range.GetLastRow();firstColumn=range.GetFirstColumn();lastColumn=range.GetLastColumn();width=lastColumn-firstColumn+1;SimpleCellWalkContext ctx=new SimpleCellWalkContext();Row currentRow=null;Cell currentCell=null;for(ctx.rowNumber=firstRow;ctx.rowNumber<=lastRow;++ctx.rowNumber){currentRow=sheet.GetRow(ctx.rowNumber);if(currentRow==null){continue;}for(ctx.colNumber=firstColumn;ctx.colNumber<=lastColumn;++ctx.colNumber){currentCell=currentRow.GetCell(ctx.colNumber);if(currentCell==null){continue;}if(IsEmpty(currentCell)&&!traverseEmptyCells){continue;}rowSize=ArithmeticUtils.MulAndCheck(ArithmeticUtils.SubAndCheck(ctx.rowNumber,firstRow),width);ctx.ordinalNumber=ArithmeticUtils.AddAndCheck(rowSize,(ctx.colNumber-firstColumn+1));handler.OnCell(currentCell,ctx);}}}
() => { }
public int CompareTo(ScoreTerm other){ if(this.boost==other.boost) return other.bytes.Get().CompareTo(bytes.Get()); else return this.boost.CompareTo(other.boost); }
static int Normalize(char[] s,int len){for(int i=0;i<len;i++){switch(s[i]){case FARSI_YEH:case YEH_BARREE:s[i]=YEH;goto case KEHEH;case KEHEH:s[i]=KAF;break;case HEH_YEH:case HEH_GOAL:s[i]=HEH;break;case HAMZA_ABOVE:len=delete(s,i,len);i--;break;default:break;}}return len;}
void Method(LittleEndianOutput out) { out.WriteShort(); }
DiagnosticErrorListener() { this.exactOnly = exactOnly; }
KeySchemaElement(string attributeName, KeyType keyType) { setAttributeName(attributeName); setKeyType(keyType.ToString()); }
GetAssignmentResult() { request = beforeClientExecution(request); return executeGetAssignment(request); }
bool (  AnyObjectId id ) { return findOffset ( id ) != ; }
public GroupingSearch GroupingSearch(bool allGroups){ this.allGroups = allGroups; return this; }
public void SetMultiValued(string dimName, bool v) { lock(this) { DimConfig ft; if(!fieldTypes.TryGetValue(dimName, out ft)) { ft = new DimConfig(); fieldTypes[dimName] = ft; } ft.multiValued = v; } }
{ var enumerator = cells.Keys.GetEnumerator(); size = 0; while (enumerator.MoveNext()) { char c = enumerator.Current; Cell e = at(c); if (e.cmd >= 0) { size++; } } return size; }
DeleteVoiceConnectorResult DeleteVoiceConnector(DeleteVoiceConnectorRequest request) { request = beforeClientExecution; return executeDeleteVoiceConnector(request); }
DeleteLifecyclePolicyResult DeleteLifecyclePolicy(DeleteLifecyclePolicyRequest request) { request = beforeClientExecution; return executeDeleteLifecyclePolicy(request); }
