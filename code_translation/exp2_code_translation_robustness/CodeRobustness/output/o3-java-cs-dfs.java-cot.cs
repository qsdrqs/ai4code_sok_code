void Write(LittleEndianOutput output) { output.WriteShort(field_1_vcenter); }
public void Src(BlockList<T> src){if(src.Size()==0)return;int srcDirIdx=0;for(;srcDirIdx<src.TailDirIdx;++srcDirIdx)directory[srcDirIdx].AddRange(src,0,BLOCK_SIZE);if(src.TailBlkIdx!=0)tailBlock.AddRange(src,0,src.TailBlkIdx);}
void b(){if(upto==blockSize){if(currentBlock!=null){addBlock(currentBlock);}currentBlock=new byte[blockSize];upto=0;}b=currentBlock[upto++];}
ObjectId objectId() { return objectId; }
public DeleteDomainEntryResult DeleteDomainEntry(DeleteDomainEntryRequest request) { request = BeforeClientExecution(request); return ExecuteDeleteDomainEntry(request); }
return (termOffsets != null ? ramBytesUsed.termOffsets() : 0) + (termsDictOffsets != null ? ramBytesUsed.termsDictOffsets() : 0);
public string DecodeTagMessage(byte[] raw){int msgB=RawParseUtils.tagMessage(raw,0);if(msgB<0){return "";}return RawParseUtils.decode(RawParseUtils.guessEncoding(raw,msgB,raw.Length));}
_header.setBATCount(1); _header.setBATArray(new int[] { 1 }); BATBlock bb = BATBlock.createEmptyBATBlock(bigBlockSize, false); bb.setOurBlockIndex(1); _bat_blocks.Add(bb); bb.setNextBlock(0, POIFSConstants.END_OF_CHAIN); bb.setNextBlock(1, POIFSConstants.FAT_SECTOR_BLOCK); _property_table.setStartBlock(0);
Debug.Assert(length.slice < upto); address = offset0; address = upto & ByteBlockPool.BYTE_BLOCK_MASK; Debug.Assert(slice != null); slice = buffers.pool[address >> ByteBlockPool.BYTE_BLOCK_SHIFT]; _ = address;
public SubmoduleAddCommand Path(string path) { this.path = path; return this; }
private ListIngestionsResult ListIngestions(ListIngestionsRequest request){request=BeforeClientExecution(request);return ExecuteListIngestions(request);}
public QueryParserTokenManager(CharStream stream, int lexState) { SwitchTo(lexState); }
public GetShardIteratorResult GetShardIterator(GetShardIteratorRequest request) { request = BeforeClientExecution(request); return ExecuteGetShardIterator(request); }
base("aegis", "2016-11-11", "ModifyStrategy", "vipaegis"); SetMethod(MethodType.POST);
bool CheckAvailable() { lock (lockObj) { if (input == null) { throw new IOException("InputStreamReader is closed"); } try { return bytes.HasRemaining() || input.Available > 0; } catch (Exception) { return false; } } }
EscherOptRecord() { return _optRecord; }
public override int Read(char[] buffer, int offset, int length){if(buffer==null)throw new NullReferenceException("buffer == null");if(offset<0||length<0||offset+length>buffer.Length)throw new ArgumentOutOfRangeException();if(length==0)return 0;int copylen=Math.Min(length,count-pos);for(int i=0;i<copylen;++i){buffer[offset+i]=this.buffer[pos+i];}pos+=copylen;return copylen;}
}; sentenceOp = new NLPSentenceDetectorOp(new OpenNLPSentenceBreakIterator(this));
void Write(string str) { Write(str != null ? str : Convert.ToString((object)null)); }
public NotImplementedFunctionException(string functionName, NotImplementedException cause) : base(functionName, cause) { this.functionName = functionName; }
public V Next() { return base.NextEntry().GetValue(); }
public void ReadBytes(byte[] b, int offset, int len, bool useBuffer) { if (len < 0) throw new ArgumentException("len must be >= 0 (got " + len + ")"); int available = bufferLength - bufferPosition; if (len <= available) { if (len > 0) { Array.Copy(buffer, bufferPosition, b, offset, len); bufferPosition += len; } } else { if (available > 0) { Array.Copy(buffer, bufferPosition, b, offset, available); offset += available; len -= available; bufferPosition += available; } if (useBuffer && len < bufferSize) { Refill(); if (bufferLength < len) { Array.Copy(buffer, 0, b, offset, bufferLength); throw new EndOfStreamException("read past EOF: " + this); } else { Array.Copy(buffer, 0, b, offset, len); bufferPosition = len; } } else { long after = bufferStart + bufferPosition + len; if (after > Length()) { throw new EndOfStreamException("read past EOF: " + this); } ReadInternal(b, offset, len); bufferStart = after; bufferPosition = 0; bufferLength = 0; } } }
public TagQueueResult TagQueue(TagQueueRequest request) { request = BeforeClientExecution(request); return ExecuteTagQueue(request); }
void () { throw new NotSupportedException(); }
return ExecuteModifyCacheSubnetGroup(BeforeClientExecution(request));
void SetParams(string parameters){base.SetParams(parameters);string language="";string country="";string variant="";string[] tokens=parameters.Split(',');if(tokens.Length>0)language=tokens[0];if(tokens.Length>1)country=tokens[1];if(tokens.Length>2)variant=tokens[2];}
return ExecuteDeleteDocumentationVersion(BeforeClientExecution(request));
public override bool Equals(object obj){if(!(obj is FacetLabel other)){return false;}if(length!=other.length){return false;}for(int i=length-1;i>=0;--i){if(!components[i].Equals(other.components[i])){return false;}}return true;}
public GetInstanceAccessDetailsResult GetInstanceAccessDetails(GetInstanceAccessDetailsRequest request){request=BeforeClientExecution(request);return ExecuteGetInstanceAccessDetails(request);}
HSSFChildAnchor anchor = new HSSFChildAnchor(); HSSFPolygon shape = new HSSFPolygon(anchor, this); shape.SetParent(this); shape.SetAnchor(anchor); shapes.Add(shape); OnCreate(shape); return shape;
string getSheetname(int sheetIndex){return getBoundSheetRec(sheetIndex).getSheetname();}
request = BeforeClientExecution(request); return ExecuteGetDashboard(request);
AssociateSigninDelegateGroupsWithAccountRequest request = BeforeClientExecution(request); return ExecuteAssociateSigninDelegateGroupsWithAccount(request);
void MulBlankRecord(MultipleBlankRecord mbr){for(int j=0;j<mbr.getNumColumns();j++){BlankRecord br=new BlankRecord();br.setColumn(mbr.getFirstColumn()+j);br.setRow(mbr.getRow());br.setXFIndex(mbr.getXFAt(j));mbr.insertCell(br);}}
public static string QuoteMeta(string s){var sb=new System.Text.StringBuilder();sb.Append("\\Q");int apos=0,k;while((k=s.IndexOf("\\E",apos))>=0){sb.Append(s.Substring(apos,k-apos)).Append("\\\\E\\Q");apos=k+2;}return sb.Append(s.Substring(apos)).Append("\\E").ToString();}
ByteBuffer Value(object value) { throw new ReadOnlyBufferException(); }
int nColumns = values2d[0].Length; int nRows = values2d.Length; object[] vv = new object[nRows * nColumns]; for (int r = 0; r < nRows; ++r) { object[] rowData = values2d[r]; for (int c = 0; c < nColumns; ++c) { vv[getValueIndex(r, c)] = rowData[c]; } } _arrayValues = vv; _reserved0Int = 0; _reserved1Short = 0; _reserved2Byte = 0;
public GetIceServerConfigResult GetIceServerConfig(GetIceServerConfigRequest request) { request = BeforeClientExecution(request); return ExecuteGetIceServerConfig(request); }
return GetType().Name + " [" + GetValueAsString() + "]";
return "ToChildBlockJoinQuery (" + parentQuery.ToString(field) + ")";
public void IncrementRefCount() { refCount.IncrementAndGet(); }
public UpdateConfigurationSetSendingEnabledResult UpdateConfigurationSetSendingEnabled(UpdateConfigurationSetSendingEnabledRequest request){request=BeforeClientExecution(request);return ExecuteUpdateConfigurationSetSendingEnabled(request);}
return getXBATEntriesPerBlock() * LittleEndianConsts.INT_SIZE;
void Pow10() { TenPower tp = TenPower.GetInstance(Math.Abs(pow10)); if (pow10 < 0) { mulShift(_divisor.tp, _divisorShift); } else { mulShift(_multiplicand.tp, _multiplierShift); } }
public override string ToString() { var b = new StringBuilder(); int l = Length(); for (int i = 0; i < l; ++i) { b.Append(GetComponent(i)); if (i < l - 1) { b.Append(Path.DirectorySeparatorChar); } } return b.ToString(); }
public InstanceProfileCredentialsProvider SetRoleName(string roleName) { this.fetcher.SetRoleName(roleName); return this; }
void SomeMethod(ProgressMonitor pm) { pm = progressMonitor; }
void ParseEntry() { if (!first) { ptr = 0; } if (!eof()) { ParseEntry(); } }
if (iterator.PreviousIndex() >= start) { return iterator.Previous(); } else { throw new InvalidOperationException(); }
string() { return this.newPrefix; }
for (int i = 0; i < mSize; i++) { if (value == mValues[i]) return i; } return -1;
List<CharsRef> deduped = new List<CharsRef>(); CharArraySet terms = new CharArraySet(dictionary, 8, ignoreCase); if (stems.Count < 2) return stems; foreach (CharsRef s in stems) { if (!terms.Contains(s)) { deduped.Add(s); } } return deduped;
GetGatewayResponsesRequest request = BeforeClientExecution(new GetGatewayResponsesRequest()); return ExecuteGetGatewayResponses(request);
void Set(int pos) { currentBlockIndex = pos >> blockBits; currentBlock = blocks[currentBlockIndex]; currentBlockUpto = pos & blockMask; }
{ s = Math.Min(Available(), Math.Max(0, n)); ptr += s; return s; }
public BootstrapActionDetail(BootstrapActionConfig bootstrapActionConfig){SetBootstrapActionConfig(bootstrapActionConfig);}
void Serialize(LittleEndianOutput out) { out.WriteShort(field_1_row); out.WriteShort(field_2_col); out.WriteShort(field_3_flags); out.WriteShort(field_4_shapeid); out.WriteShort((short)field_6_author.Length); out.WriteByte(field_5_hasMultibyte ? (byte)0x01 : (byte)0x00); if (field_5_hasMultibyte) { StringUtil.PutUnicodeLE(field_6_author, out); } else { StringUtil.PutCompressedUnicode(field_6_author, out); } if (field_7_padding != null) { out.WriteByte((byte)field_7_padding.Value); } }
(string str){return str.LastIndexOf(count);}
} ; ) object ( addLastImpl return { ) object E (  bool
void Subsection(string section,string subsection){ConfigSnapshot src,res;do{src=state.Get();res=UnsetSection(src,section,subsection);}while(!state.CompareAndSet(src,res));}
public string GetTagName() { return tagName; }
void add(int index, SubRecord element) { subrecords.Insert(index, element); }
bool Remove(object o) { lock (mutex) { return delegate().Remove(o); } }
return new DoubleMetaphoneFilter(input, maxCodeLength);
return inCoreLength();
void newValue(bool value) { newValue = value; }
public Pair(ContentSource oldSource, ContentSource newSource){this.oldSource=oldSource;this.newSource=newSource;}
if (i <= count) throw new IndexOutOfRangeException(i.ToString()); return entries[i];
SetMethod(MethodType.PUT); Put("/repos", "cr", "CreateRepo", "2016-06-07", "cr", base.CreateRepoRequest);
bool deltaBaseAsOffset() { return deltaBaseAsOffset; }
void Remove() { if (list.modCount == expectedModCount) { if (lastLink != null) { Link<ET> next = lastLink.next; Link<ET> previous = lastLink.previous; previous.next = next; next.previous = previous; if (link == lastLink) { link = next; } else { pos--; } lastLink = null; expectedModCount++; list.size--; list.modCount++; } else { throw new InvalidOperationException(); } } else { throw new InvalidOperationException(); } }
[BeforeClientExecution] private MergeShardsResult ExecuteMergeShards(MergeShardsRequest request) => ExecuteMergeShards(request);
public AllocateHostedConnectionResult AllocateHostedConnection(AllocateHostedConnectionRequest request){request=BeforeClientExecution(request);return ExecuteAllocateHostedConnection(request);}
}; start return { ) (
public static WeightedTerm[] GetTerms(Query query) { return GetTerms(query, false); }
ByteBuffer() { throw new ReadOnlyBufferException(); }
void Decode(byte[] blocks, int blocksOffset, int[] values, int valuesOffset, int iterations) { for (int i = 0; i < iterations; i++) { int byte0 = blocks[blocksOffset++] & 0xFF; int byte1 = blocks[blocksOffset++] & 0xFF; int byte2 = blocks[blocksOffset++] & 0xFF; values[valuesOffset++] = byte0 >> 2; values[valuesOffset++] = ((byte0 & 3) << 4) | (byte1 >> 4); values[valuesOffset++] = ((byte1 & 15) << 2) | (byte2 >> 6); values[valuesOffset++] = byte2 & 63; } }
public string GetHumanishName(){string s=GetPath();if(s==null)throw new ArgumentException();if(s.Equals("")||s.Equals("/"))return host;if("file".Equals(scheme)||LOCAL_FILE.IsMatch(s)){string[] elements=Regex.Split(s,Path.DirectorySeparatorChar=='\\'?@"[\\/]+":@"/+");if(elements.Length==0)throw new ArgumentException();string result=elements[elements.Length-1];if(Constants.DOT_GIT.Equals(result))result=elements[elements.Length-2];else if(result.EndsWith(Constants.DOT_GIT_EXT))result=result.Substring(0,result.Length-Constants.DOT_GIT_EXT.Length);return result;}return host;}
return ExecuteDescribeNotebookInstanceLifecycleConfig(BeforeClientExecution(request));
string GetAccessKeySecret() { return this.accessKeySecret; }
return ExecuteCreateVpnConnection(BeforeClientExecution(request));
DescribeVoicesRequest request = BeforeClientExecution(describeVoicesRequest); return ExecuteDescribeVoices(request);
public ListMonitoringExecutionsResult ListMonitoringExecutions(ListMonitoringExecutionsRequest request){ request = BeforeClientExecution(request); return ExecuteListMonitoringExecutions(request); }
public DescribeJobRequest(string jobId, string vaultName) { SetJobId(jobId); SetVaultName(vaultName); }
return (EscherRecord)escherRecords[index];
public GetApisResult GetApis(GetApisRequest request) { request = beforeClientExecution(request); return executeGetApis(request); }
DeleteSmsChannelResult DeleteSmsChannel(DeleteSmsChannelRequest request){request=BeforeClientExecution(request);return ExecuteDeleteSmsChannel(request);}
TrackingRefUpdate() { return trackingRefUpdate; }
void Print(bool b){Console.Write(b.ToString());}
return (QueryNode)GetChildren()[0];
workdirTreeIndex = Index(this, NotIgnoredFilter);
public AreaRecord(RecordInputStream inStream){field_1_formatFlags=inStream.ReadInt16();}
public GetThumbnailRequest() : base("CloudPhoto", "2017-07-11", "GetThumbnail", "cloudphoto") { SetProtocol(ProtocolType.HTTPS); }
public DescribeTransitGatewayVpcAttachmentsResult DescribeTransitGatewayVpcAttachments(DescribeTransitGatewayVpcAttachmentsRequest request){request=BeforeClientExecution(request);return ExecuteDescribeTransitGatewayVpcAttachments(request);}
}; return ExecutePutVoiceConnectorStreamingConfiguration(request); request = BeforeClientExecution(request); PutVoiceConnectorStreamingConfigurationRequest requestObj = new PutVoiceConnectorStreamingConfigurationRequest(); PutVoiceConnectorStreamingConfigurationResult result;
OrdRange PrefixToOrdRange(string dim) { return get.prefixToOrdRange(dim); }
string symbol="";if(startIndex>=0&&startIndex<GetInputStream().Size()){symbol=GetText(Interval.Of(startIndex,startIndex));}symbol=Utils.EscapeWhitespace(symbol,false);return string.Format(System.Globalization.CultureInfo.CurrentCulture,"{0}('{1}')",typeof(LexerNoViableAltException).Name,symbol);
E () { return peekFirstImpl(); }
CreateWorkspacesResult CreateWorkspaces(CreateWorkspacesRequest request){request = BeforeClientExecution(request); return ExecuteCreateWorkspaces(request);}
NumberFormatIndexRecord NumberFormatIndexRecord() { return copy(); }
public override DescribeRepositoriesResult DescribeRepositories(DescribeRepositoriesRequest request) { request = BeforeClientExecution(request); return ExecuteDescribeRepositories(request); }
public SparseIntArray(int initialCapacity) { initialCapacity = ArrayUtils.IdealIntArraySize(initialCapacity); mKeys = new int[initialCapacity]; mValues = new int[initialCapacity]; mSize = 0; }
return new HyphenatedWordsFilter(input);
public CreateDistributionWithTagsResult CreateDistributionWithTags(CreateDistributionWithTagsRequest request){request=BeforeClientExecution(request);return ExecuteCreateDistributionWithTags(request);}
public RandomAccessFile(string fileName, string mode) { new FileInfo(fileName); }
return ExecuteDeleteWorkspaceImage(BeforeClientExecution(request));
public static string ToHex(string value) { StringBuilder sb = new StringBuilder(16); writeHex(sb, value, 16, ""); return sb.ToString(); }
public UpdateDistributionResult UpdateDistribution(UpdateDistributionRequest request){request = BeforeClientExecution(request); return ExecuteUpdateDistribution(request);}
HSSFColor GetColor(short index){if(index==HSSFColorPredefined.AUTOMATIC.GetIndex()){return HSSFColorPredefined.AUTOMATIC.GetColor();}HSSFColor b=_palette.GetColor(index);return b==null?null:new CustomColor(index,b);}
ValueEval Evaluate(ValueEval[] operands, int srcRow, int srcCol) { throw new NotImplementedFunctionException(_functionName); }
public void Serialize(LittleEndianOutput output){output.WriteShort(field_1_number_crn_records);output.WriteShort(field_2_sheet_table_index);}
return DescribeDBEngineVersions(new DescribeDBEngineVersionsRequest());
this.fontIndex = _fontIndex; this.character = _character; FormatRun(character, fontIndex);
public static byte[] ToBytes(char[] chars,int offset,int length){byte[] result=new byte[length*2];int end=offset+length;int resultIndex=0;for(int i=offset;i<end;i++){char ch=chars[i];result[resultIndex++]=(byte)(ch>>8);result[resultIndex++]=(byte)ch;}return result;}
UploadArchiveRequest request = BeforeClientExecution(request); return ExecuteUploadArchive(request);
List<Token> GetHiddenTokensToLeft(int tokenIndex){ return GetHiddenTokensToLeft(tokenIndex - 1); }
public override bool Equals(object obj) { if (this == obj) return true; if (!base.Equals(obj)) return false; if (GetType() != obj.GetType()) return false; AutomatonQuery other = (AutomatonQuery)obj; if (compiled != other.compiled) return false; if (term == null) { if (other.term != null) return false; } else if (!term.Equals(other.term)) { return false; } return true; }
SpanQuery[] spanQueries = weightBySpanQuery.Keys.ToArray(); int i = 0; foreach (var sqKey in weightBySpanQuery.Keys) { SpanQuery sq = sqKey; float boost = weightBySpanQuery[sq]; if (boost != 1f) { sq = new SpanBoostQuery(sq, boost); } spanQueries[i++] = sq; } return spanQueries.Length == 1 ? spanQueries[0] : new SpanOrQuery(spanQueries);
return new StashCreateCommand(repo);
FieldInfo GetFieldInfo(string fieldName){ return FieldInfo.GetByName(fieldName); }
public DescribeEventSourceResult DescribeEventSource(DescribeEventSourceRequest request){request=beforeClientExecution(request);return executeDescribeEventSource(request);}
request = BeforeClientExecution(request); return ExecuteGetDocumentAnalysis(request);
CancelUpdateStackResult CancelUpdateStack(CancelUpdateStackRequest request) { request = BeforeClientExecution(request); return ExecuteCancelUpdateStack(request); }
public ModifyLoadBalancerAttributesResult ModifyLoadBalancerAttributes(ModifyLoadBalancerAttributesRequest request){request=BeforeClientExecution(request);return ExecuteModifyLoadBalancerAttributes(request);}
public SetInstanceProtectionResult SetInstanceProtection(SetInstanceProtectionRequest request) { request = BeforeClientExecution(request); return ExecuteSetInstanceProtection(request); }
request = BeforeClientExecution(request); return ExecuteModifyDBProxy(request);
if (outputs.Length == count) outputs = ArrayUtil.Grow(outputs, count + 1); if (endOffsets.Length == count) { var next = new int[ArrayUtil.Oversize(count + 1, sizeof(int))]; Array.Copy(endOffsets, 0, next, 0, count); endOffsets = next; } if (posLengths.Length == count) { var next = new int[ArrayUtil.Oversize(count + 1, sizeof(int))]; Array.Copy(posLengths, 0, next, 0, count); posLengths = next; } if (outputs[count] == null) outputs[count] = new CharsRefBuilder(); outputs[count].CopyChars(output, offset, len); endOffsets[count] = endOffset; posLengths[count] = posLength; ++count;
public FetchLibrariesRequest() : base("CloudPhoto", "2017-07-11", "FetchLibraries", "cloudphoto") { Protocol = ProtocolType.HTTPS; }
bool Check(){return fs.Exists(objects);}
public FilterOutputStream(System.IO.Stream @out){this.out=@out;}
public ScaleClusterRequest() : base("CS", "2015-12-15", "ScaleCluster", "csk") { UriPattern = "/clusters/[ClusterId]"; Method = MethodType.PUT; }
public DataValidationConstraint CreateTimeConstraint(string operatorType, string formula1, string formula2) { return DVConstraint.CreateTimeConstraint(operatorType, formula1, formula2); }
public ListObjectParentPathsResult ListObjectParentPaths(ListObjectParentPathsRequest request){request=BeforeClientExecution(request);return ExecuteListObjectParentPaths(request);}
public DescribeCacheSubnetGroupsResult DescribeCacheSubnetGroups(DescribeCacheSubnetGroupsRequest request) { request = BeforeClientExecution(request); return ExecuteDescribeCacheSubnetGroups(request); }
void SetSharedFormula(bool flag) { field_5_options = SetShortBoolean(field_5_options, flag); }
bool () { return reuseObjects; }
ErrorNode t = new ErrorNodeImpl(badToken); addAnyChild(t); t.setParent(this); return t;
public LatvianStemFilterFactory(IDictionary<string, string> args) : base(args) { if (args.Count != 0) { throw new ArgumentException("Unknown parameters: " + args); } }
public EventSubscription RemoveSourceIdentifierFromSubscription(RemoveSourceIdentifierFromSubscriptionRequest request){request=BeforeClientExecution(request);return ExecuteRemoveSourceIdentifierFromSubscription(request);}
public static TokenFilterFactory name(string name, Dictionary<string, string> args) { return loader.newInstance(name, args); }
public AddAlbumPhotosRequest() : base("CloudPhoto", "2017-07-11", "AddAlbumPhotos", "cloudphoto") { Protocol = ProtocolType.HTTPS; }
public GetThreatIntelSetResult GetThreatIntelSet(GetThreatIntelSetRequest request){request=BeforeClientExecution(request);return ExecuteGetThreatIntelSet(request);}
RevFilter() { return new Binary(a.Clone(), b.Clone()); }
bool (object o) { return o is ArmenianStemmer; };
public bool protectedHasArray() { return false; }
public UpdateContributorInsightsResult UpdateContributorInsights(UpdateContributorInsightsRequest request) { request = BeforeClientExecution(request); return ExecuteUpdateContributorInsights(request); }
void Method() { records.Remove(fileShare); records.Remove(writeProtect); fileShare = null; writeProtect = null; }
public SolrSynonymParser(bool dedup, bool expand, Analyzer analyzer) : base(dedup, analyzer) { this.expand = expand; }
public RequestSpotInstancesResult RequestSpotInstances(RequestSpotInstancesRequest request) { request = BeforeClientExecution(request); return ExecuteRequestSpotInstances(request); }
return findObjectRecord().getObjectData();
public override GetContactAttributesResult GetContactAttributes(GetContactAttributesRequest request){request=BeforeClientExecution(request);return ExecuteGetContactAttributes(request);}
string ToString() { return GetKey() + ": " + GetValue(); }
public ListTextTranslationJobsResult ListTextTranslationJobs(ListTextTranslationJobsRequest request){request=BeforeClientExecution(request);return ExecuteListTextTranslationJobs(request);}
request = BeforeClientExecution(request); return ExecuteGetContactMethods(request);
public static int GetFunctionIndex(string name){FunctionMetadata fd=GetInstance(GetFunctionByNameInternal(name));if(fd==null){fd=GetInstanceCetab(GetFunctionByNameInternal(name));if(fd==null){return -1;}}return fd.GetIndex();}
public DescribeAnomalyDetectorsResult DescribeAnomalyDetectors(DescribeAnomalyDetectorsRequest request) { request = BeforeClientExecution(request); return ExecuteDescribeAnomalyDetectors(request); }
public static string insertId(string message, ObjectId changeId) { return insertId(message, changeId, false); }
public long GetObjectSize(AnyObjectId objectId,int typeHint){long sz=db.GetObjectSize(this,objectId,typeHint);if(sz<0){if(typeHint==OBJ_ANY)throw new MissingObjectException(objectId.Copy(),JGitText.Get().UnknownObjectType2);throw new MissingObjectException(objectId.Copy(),OBJ_ANY);}return sz;}
return ExecuteImportInstallationMedia(BeforeClientExecution(request));
public PutLifecycleEventHookExecutionStatusResult ExecutePutLifecycleEventHookExecutionStatus(PutLifecycleEventHookExecutionStatusRequest request) { request = BeforeClientExecution(request); return request; }
NumberPtg.ReadDouble(input);
return ExecuteGetFieldLevelEncryptionConfig(BeforeClientExecution(request));
public DescribeDetectorResult DescribeDetector(DescribeDetectorRequest request) { request = BeforeClientExecution(request); return ExecuteDescribeDetector(request); }
public override ReportInstanceStatusResult ReportInstanceStatus(ReportInstanceStatusRequest request){request=BeforeClientExecution(request);return ExecuteReportInstanceStatus(request);}
return ExecuteDeleteAlarm(BeforeClientExecution(request));
TokenStream TokenStream(TokenStream input){ return new PortugueseStemFilter(input); }
reserved = new byte[ENCODED_SIZE];
public override bool Remove(object obj){lock(mutex){return c.Remove(obj);}}
private GetDedicatedIpResult GetDedicatedIp(GetDedicatedIpRequest request){request=BeforeClientExecution(request);return ExecuteGetDedicatedIp(request);}
string ToString() { return _p + " >= " + precedence; }
public ListStreamProcessorsResult ExecuteListStreamProcessors(ListStreamProcessorsRequest request){request=BeforeClientExecution(request);return ExecuteListStreamProcessors(request);}
public DeleteLoadBalancerPolicyRequest(string loadBalancerName, string policyName){ SetLoadBalancerName(loadBalancerName); SetPolicyName(policyName); }
WindowProtectRecord(options){_options=options;}
public UnbufferedCharStream(int bufferSize){n=0;data=new char[bufferSize];}
GetOperationsRequest request = BeforeClientExecution(request); return ExecuteGetOperations(request);
void Method(byte[] b, int o, int w1, int w2, int w3, int w4, int w5) { NB.EncodeInt32(b, o, w1); NB.EncodeInt32(b, o + 4, w2); NB.EncodeInt32(b, o + 8, w3); NB.EncodeInt32(b, o + 12, w4); NB.EncodeInt32(b, o + 16, w5); }
field_1_h_hold = in.ReadInt16(); field_2_v_hold = in.ReadInt16(); field_3_width = in.ReadInt16(); field_4_height = in.ReadInt16(); field_5_options = in.ReadInt16(); field_6_active_sheet = in.ReadInt16(); field_7_first_visible_tab = in.ReadInt16(); field_8_num_selected_tabs = in.ReadInt16(); field_9_tab_width_ratio = in.ReadInt16();
StopWorkspacesResult ExecuteStopWorkspaces(StopWorkspacesRequest request){request=BeforeClientExecution(request);return ExecuteStopWorkspaces(request);}
void SomeMethod(){if(isOpen){try{Dump();try{channel.Truncate(fileLength);}finally{channel.Close();}}finally{fos.Close();}}else{isOpen=false;}}
return ExecuteDescribeMatchmakingRuleSets(BeforeClientExecution(request));
string Surface(int wordId,char[] surface,int off,int len){return null;}
string() { return pathStr; }
public static double Func(double[] v){double r=double.NaN;if(v!=null&&v.Length>=1){double m=0,s=0;int n=v.Length;for(int i=0;i<n;++i){s+=v[i];}m=s/n;s=0;for(int i=0;i<n;++i){s+=(v[i]-m)*(v[i]-m);}r=n==1?0:s;}return r;}
return ExecuteDescribeResize(BeforeClientExecution(request));
public bool passedThroughNonGreedyDecision() { return passedThroughNonGreedyDecision; }
() => end(0);
Row currentRow = null; Cell currentCell = null; var ctx = new SimpleCellWalkContext(); int width = lastColumn - firstColumn + 1; for (int rowNumber = firstRow; rowNumber <= lastRow; rowNumber++) { currentRow = sheet.GetRow(rowNumber); if (currentRow == null) continue; for (int colNumber = firstColumn; colNumber <= lastColumn; colNumber++) { currentCell = currentRow.GetCell(colNumber); if (currentCell == null) continue; if (currentCell.IsEmpty() && !traverseEmptyCells) continue; int rowSize = ArithmeticUtils.MulAndCheck(ArithmeticUtils.SubAndCheck(rowNumber, firstRow), width); ctx.ordinalNumber = ArithmeticUtils.AddAndCheck(rowSize, colNumber - firstColumn + 1); handler.OnCell(ctx, currentCell); } }
} ; pos return { ) (
public int CompareTo(ScoreTerm other){if(this.boost==other.boost)return this.bytes.CompareTo(other.bytes);else return other.boost.CompareTo(this.boost);}
for (int i = 0; i < len; ++i) { switch (s[i]) { case FARSI_YEH: case YEH_BARREE: yeh = s[i]; break; case KEHEH: kaf = s[i]; break; case HEH_YEH: case HEH_GOAL: heh = s[i]; break; case HAMZA_ABOVE: delete = len; break; default: break; } } return len;
void Write(ILittleEndianOutput output){ output.WriteShort(_options); }
DiagnosticErrorListener(bool exactOnly) { this.exactOnly = exactOnly; }
} ; ) ) ( ToString . keyType ( SetKeyType ; ) attributeName ( SetAttributeName { ) keyType KeyType , attributeName string ( KeySchemaElement
GetAssignmentResult GetAssignment(GetAssignmentRequest request) { request = BeforeClientExecution(request); return ExecuteGetAssignment(request); }
bool (AnyObjectId id) { return findOffset(id) != -1; }
public GroupingSearch AllGroups(bool allGroups) { this.allGroups = allGroups; return this; }
public void SetMultiValued(string dimName,bool v){lock(this){DimConfig ft;fieldTypes.TryGetValue(dimName,out ft);if(ft==null){ft=new DimConfig();fieldTypes[dimName]=ft;}ft.multiValued=v;}}
IEnumerator<char> i = cells.Keys.GetEnumerator(); size = 0; while (i.MoveNext()) { char c = i.Current; Cell e = at(c); if (e.cmd >= 0) { ++size; } } return size;
public DeleteVoiceConnectorResult DeleteVoiceConnector(DeleteVoiceConnectorRequest request) { request = BeforeClientExecution(request); return ExecuteDeleteVoiceConnector(request); }
DeleteLifecyclePolicyRequest request = BeforeClientExecution(request); return ExecuteDeleteLifecyclePolicy(request);
