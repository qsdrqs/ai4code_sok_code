public void Serialize(LittleEndianOutput output) { output.WriteShort(field_1_vcenter); }
public void AddAll(BlockList<T> src){if(src.size==0)return;int srcDirIdx=0;for(;srcDirIdx<src.tailDirIdx;srcDirIdx++)AddAll(src.directory[srcDirIdx],0,BLOCK_SIZE);if(src.tailBlkIdx!=0)AddAll(src.tailBlock,0,src.tailBlkIdx);}
public void WriteByte(byte b){if(upto==blockSize){if(currentBlock!=null){addBlock(currentBlock);}currentBlock=new byte[blockSize];upto=0;}currentBlock[upto++]=b;}
public ObjectId GetObjectId() { return objectId; }
public DeleteDomainEntryResult DeleteDomainEntry(DeleteDomainEntryRequest request) { request = BeforeClientExecution(request); return ExecuteDeleteDomainEntry(request); }
public long RamBytesUsed(){return (termOffsets!=null?termOffsets.RamBytesUsed():0)+(termsDictOffsets!=null?termsDictOffsets.RamBytesUsed():0);}
public string GetFullMessage(){byte[] raw=buffer;int msgB=RawParseUtils.TagMessage(raw,0);if(msgB<0){return string.Empty;}return RawParseUtils.Decode(GuessEncoding(),raw,msgB,raw.Length);}
public POIFSFileSystem() { this(true); _header.SetBATCount(1); _header.SetBATArray(new int[] { 1 }); BATBlock bb = BATBlock.CreateEmptyBATBlock(bigBlockSize, false); bb.SetOurBlockIndex(1); _bat_blocks.Add(bb); SetNextBlock(0, POIFSConstants.END_OF_CHAIN); SetNextBlock(1, POIFSConstants.FAT_SECTOR_BLOCK); _property_table.SetStartBlock(0); }
public void Init(int address){slice=pool.buffers[address>>ByteBlockPool.BYTE_BLOCK_SHIFT];System.Diagnostics.Debug.Assert(slice!=null);upto=address&ByteBlockPool.BYTE_BLOCK_MASK;offset0=address;System.Diagnostics.Debug.Assert(upto<slice.Length);}
public SubmoduleAddCommand SetPath(string path){ this.path = path; return this; }
public ListIngestionsResult ListIngestions(ListIngestionsRequest request) { request = BeforeClientExecution(request); return ExecuteListIngestions(request); }
public QueryParserTokenManager(CharStream stream, int lexState) : this(stream) { SwitchTo(lexState); }
public GetShardIteratorResult GetShardIterator(GetShardIteratorRequest request) { request = BeforeClientExecution(request); return ExecuteGetShardIterator(request); }
public ModifyStrategyRequest() : base("aegis", "2016-11-11", "ModifyStrategy", "vipaegis") { SetMethod(MethodType.POST); }
public bool Ready(){lock(@lock){if(@in==null){throw new IOException("InputStreamReader is closed");}try{return bytes.HasRemaining()||@in.Available>0;}catch(IOException){return false;}}}
public EscherOptRecord GetOptRecord() { return _optRecord; }
public int Read(byte[] buffer,int offset,int length){lock(this){if(buffer==null)throw new NullReferenceException("buffer == null");if(offset<0||length<0||offset+length>buffer.Length)throw new ArgumentOutOfRangeException();if(length==0)return 0;int copylen=count-pos<length?count-pos:length;for(int i=0;i<copylen;i++)buffer[offset+i]=(byte)this.buffer[pos+i];pos+=copylen;return copylen;}}
public OpenNLPSentenceBreakIterator(NLPSentenceDetectorOp sentenceOp) { this.sentenceOp = sentenceOp; }
public void Print(string str) { Write(str ?? "null"); }
public NotImplementedFunctionException(string functionName, NotImplementedException cause) : base(functionName, cause) { this.functionName = functionName; }
public V Next() { return base.NextEntry().Value; }
public void ReadBytes(byte[] b, int offset, int len, bool useBuffer){int available=bufferLength-bufferPosition;if(len<=available){if(len>0)Array.Copy(buffer,bufferPosition,b,offset,len);bufferPosition+=len;}else{if(available>0){Array.Copy(buffer,bufferPosition,b,offset,available);offset+=available;len-=available;bufferPosition+=available;}if(useBuffer&&len<bufferSize){Refill();if(bufferLength<len){Array.Copy(buffer,0,b,offset,bufferLength);throw new EndOfStreamException("read past EOF: "+this);}else{Array.Copy(buffer,0,b,offset,len);bufferPosition=len;}}else{long after=bufferStart+bufferPosition+len;if(after>Length())throw new EndOfStreamException("read past EOF: "+this);ReadInternal(b,offset,len);bufferStart=after;bufferPosition=0;bufferLength=0;}}}
public TagQueueResult TagQueue(TagQueueRequest request) { request = BeforeClientExecution(request); return ExecuteTagQueue(request); }
public void Remove() { throw new NotSupportedException(); }
public CacheSubnetGroup ModifyCacheSubnetGroup(ModifyCacheSubnetGroupRequest request) { request = BeforeClientExecution(request); return ExecuteModifyCacheSubnetGroup(request); }
public override void SetParams(string parameters){base.SetParams(parameters);language=country=variant="";var parts=parameters.Split(',');if(parts.Length>0)language=parts[0];if(parts.Length>1)country=parts[1];if(parts.Length>2)variant=parts[2];}
public DeleteDocumentationVersionResult DeleteDocumentationVersion(DeleteDocumentationVersionRequest request) { request = BeforeClientExecution(request); return ExecuteDeleteDocumentationVersion(request); }
public override bool Equals(object obj){if(!(obj is FacetLabel))return false;FacetLabel other=(FacetLabel)obj;if(length!=other.length)return false;for(int i=length-1;i>=0;i--){if(!components[i].Equals(other.components[i]))return false;}return true;}
public GetInstanceAccessDetailsResult GetInstanceAccessDetails(GetInstanceAccessDetailsRequest request) { request = BeforeClientExecution(request); return ExecuteGetInstanceAccessDetails(request); }
public HSSFPolygon CreatePolygon(HSSFChildAnchor anchor) { HSSFPolygon shape = new HSSFPolygon(this, anchor); shape.SetParent(this); shape.SetAnchor(anchor); shapes.Add(shape); OnCreate(shape); return shape; }
public string GetSheetName(int sheetIndex) { return GetBoundSheetRec(sheetIndex).GetSheetname(); }
public GetDashboardResult GetDashboard(GetDashboardRequest request) { request = BeforeClientExecution(request); return ExecuteGetDashboard(request); }
public AssociateSigninDelegateGroupsWithAccountResult AssociateSigninDelegateGroupsWithAccount(AssociateSigninDelegateGroupsWithAccountRequest request) { request = BeforeClientExecution(request); return ExecuteAssociateSigninDelegateGroupsWithAccount(request); }
public void AddMultipleBlanks(MulBlankRecord mbr) { for (int j = 0; j < mbr.GetNumColumns(); j++) { BlankRecord br = new BlankRecord(); br.SetColumn((short)(j + mbr.GetFirstColumn())); br.SetRow(mbr.GetRow()); br.SetXFIndex(mbr.GetXFAt(j)); InsertCell(br); } }
public static string Quote(string input){var sb=new System.Text.StringBuilder();sb.Append("\\Q");int apos=0,k;while((k=input.IndexOf("\\E",apos,StringComparison.Ordinal))>=0){sb.Append(input.Substring(apos,k+2-apos)).Append("\\\\E\\Q");apos=k+2;}return sb.Append(input.Substring(apos)).Append("\\E").ToString();}
public ByteBuffer PutInt(int value) { throw new ReadOnlyBufferException(); }
public ArrayPtg(object[][] values2d){int nColumns=values2d[0].Length;int nRows=values2d.Length;_nColumns=(short)nColumns;_nRows=(short)nRows;object[] vv=new object[_nColumns*_nRows];for(int r=0;r<nRows;r++){object[] rowData=values2d[r];for(int c=0;c<nColumns;c++){vv[GetValueIndex(c,r)]=rowData[c];}}_arrayValues=vv;_reserved0Int=0;_reserved1Short=0;_reserved2Byte=0;}
public GetIceServerConfigResult GetIceServerConfig(GetIceServerConfigRequest request) { request = BeforeClientExecution(request); return ExecuteGetIceServerConfig(request); }
public override string ToString(){return GetType().Name+" ["+GetValueAsString()+"]";}
public string ToString(string field){return "ToChildBlockJoinQuery ("+parentQuery.ToString()+")";}
public void IncRef() { System.Threading.Interlocked.Increment(ref refCount); }
public UpdateConfigurationSetSendingEnabledResult UpdateConfigurationSetSendingEnabled(UpdateConfigurationSetSendingEnabledRequest request){request = BeforeClientExecution(request); return ExecuteUpdateConfigurationSetSendingEnabled(request);}
public int GetNextXBATChainOffset() { return GetXBATEntriesPerBlock() * LittleEndianConsts.INT_SIZE; }
public void MultiplyByPowerOfTen(int pow10){TenPower tp=TenPower.GetInstance(Math.Abs(pow10));if(pow10<0){MulShift(tp._divisor,tp._divisorShift);}else{MulShift(tp._multiplicand,tp._multiplierShift);}}
public override string ToString(){var b=new System.Text.StringBuilder();int l=Length();b.Append(System.IO.Path.DirectorySeparatorChar);for(int i=0;i<l;i++){b.Append(GetComponent(i));if(i<l-1){b.Append(System.IO.Path.DirectorySeparatorChar);}}return b.ToString();}
public InstanceProfileCredentialsProvider WithFetcher(ECSMetadataServiceCredentialsFetcher fetcher) { this.fetcher = fetcher; this.fetcher.SetRoleName(roleName); return this; }
public void SetProgressMonitor(ProgressMonitor pm){progressMonitor = pm;}
public void Reset() { if (!First()) { ptr = 0; if (!Eof()) ParseEntry(); } }
public E Previous() { if (iterator.PreviousIndex() >= start) { return iterator.Previous(); } throw new InvalidOperationException(); }
public string GetNewPrefix() { return this.newPrefix; }
public int IndexOfValue(int value){for(int i=0;i<mSize;i++)if(mValues[i]==value)return i;return -1;}
public List<CharsRef> UniqueStems(char[] word,int length){List<CharsRef> stems=Stem(word,length);if(stems.Count<2){return stems;}var terms=new CharArraySet(8,dictionary.IgnoreCase);List<CharsRef> deduped=new List<CharsRef>();foreach(CharsRef s in stems){if(!terms.Contains(s)){deduped.Add(s);terms.Add(s);}}return deduped;}
public GetGatewayResponsesResult GetGatewayResponses(GetGatewayResponsesRequest request) { request = BeforeClientExecution(request); return ExecuteGetGatewayResponses(request); }
public void SetPosition(long pos) { currentBlockIndex = (int)(pos >> blockBits); currentBlock = blocks[currentBlockIndex]; currentBlockUpto = (int)(pos & blockMask); }
public long Skip(long n) { int s = (int)Math.Min(Available(), Math.Max(0, n)); ptr += s; return s; }
public BootstrapActionDetail(BootstrapActionConfig bootstrapActionConfig) { setBootstrapActionConfig(bootstrapActionConfig); }
public void Serialize(ILittleEndianOutput out1){out1.WriteShort(field_1_row);out1.WriteShort(field_2_col);out1.WriteShort(field_3_flags);out1.WriteShort(field_4_shapeid);out1.WriteShort((short)field_6_author.Length);out1.WriteByte(field_5_hasMultibyte?(byte)0x01:(byte)0x00);if(field_5_hasMultibyte){StringUtil.PutUnicodeLE(field_6_author,out1);}else{StringUtil.PutCompressedUnicode(field_6_author,out1);}if(field_7_padding!=null){out1.WriteByte((byte)field_7_padding.Value);}}
public int LastIndexOf(string str) { return LastIndexOf(str, count); }
public bool Add(E obj) { return AddLastImpl(obj); }
public void UnsetSection(string section,string subsection){ConfigSnapshot src,res;do{src=state.Get();res=UnsetSection(src,section,subsection);}while(!state.CompareAndSet(src,res));}
public string getTagName() { return tagName; }
public void AddSubRecord(int index, SubRecord element) { subrecords.Insert(index, element); }
public bool Remove(object o){lock(mutex){return @delegate().Remove(o);}}
public DoubleMetaphoneFilter Create(TokenStream input) => new DoubleMetaphoneFilter(input, maxCodeLength, inject);
public long Length() { return InCoreLength(); }
public void SetValue(bool newValue) { value = newValue; }
public Pair(ContentSource oldSource, ContentSource newSource) { this.oldSource = oldSource; this.newSource = newSource; }
public int Get(int i) { if (count <= i) throw new IndexOutOfRangeException(i.ToString()); return entries[i]; }
public CreateRepoRequest() : base("cr", "2016-06-07", "CreateRepo", "cr") { SetUriPattern("/repos"); SetMethod(MethodType.PUT); }
public bool IsDeltaBaseAsOffset() { return deltaBaseAsOffset; }
public void Remove(){if(expectedModCount==list.modCount){if(lastLink!=null){Link<ET> next=lastLink.next;Link<ET> previous=lastLink.previous;next.previous=previous;previous.next=next;if(lastLink==link){pos--;}link=previous;lastLink=null;expectedModCount++;list.size--;list.modCount++;}else{throw new InvalidOperationException();}}else{throw new InvalidOperationException();}}
public MergeShardsResult MergeShards(MergeShardsRequest request) { request = BeforeClientExecution(request); return ExecuteMergeShards(request); }
public AllocateHostedConnectionResult AllocateHostedConnection(AllocateHostedConnectionRequest request) { request = BeforeClientExecution(request); return ExecuteAllocateHostedConnection(request); }
public int GetBeginIndex() { return start; }
public static WeightedTerm[] GetTerms(Query query){ return GetTerms(query, false); }
public ByteBuffer Compact() => throw new ReadOnlyBufferException();
public void Decode(byte[] blocks,int blocksOffset,long[] values,int valuesOffset,int iterations){for(int i=0;i<iterations;++i){long byte0=blocks[blocksOffset++]&0xFF;values[valuesOffset++]=byte0>>2;long byte1=blocks[blocksOffset++]&0xFF;values[valuesOffset++]=((byte0&3)<<4)|(byte1>>4);long byte2=blocks[blocksOffset++]&0xFF;values[valuesOffset++]=((byte1&15)<<2)|(byte2>>6);values[valuesOffset++]=byte2&63;}}
public string GetHumanishName(){string s=GetPath();if("/".Equals(s)||"".Equals(s))s=GetHost();if(s==null)throw new ArgumentException();string[] elements;if("file".Equals(scheme)||LOCAL_FILE.IsMatch(s))elements=s.Split(new[]{'/','\\'},StringSplitOptions.RemoveEmptyEntries);else elements=Regex.Split(s,"/+");if(elements.Length==0)throw new ArgumentException();string result=elements[elements.Length-1];if(Constants.DOT_GIT.Equals(result))result=elements[elements.Length-2];else if(result.EndsWith(Constants.DOT_GIT_EXT))result=result.Substring(0,result.Length-Constants.DOT_GIT_EXT.Length);return result;}
public DescribeNotebookInstanceLifecycleConfigResult DescribeNotebookInstanceLifecycleConfig(DescribeNotebookInstanceLifecycleConfigRequest request) { request = BeforeClientExecution(request); return ExecuteDescribeNotebookInstanceLifecycleConfig(request); }
public string GetAccessKeySecret() { return this.accessKeySecret; }
public CreateVpnConnectionResult CreateVpnConnection(CreateVpnConnectionRequest request) { request = BeforeClientExecution(request); return ExecuteCreateVpnConnection(request); }
public DescribeVoicesResult DescribeVoices(DescribeVoicesRequest request) { request = BeforeClientExecution(request); return ExecuteDescribeVoices(request); }
public ListMonitoringExecutionsResult ListMonitoringExecutions(ListMonitoringExecutionsRequest request) { request = BeforeClientExecution(request); return ExecuteListMonitoringExecutions(request); }
public DescribeJobRequest(string vaultName, string jobId) { SetVaultName(vaultName); SetJobId(jobId); }
public EscherRecord GetEscherRecord(int index){return escherRecords[index];}
public GetApisResult GetApis(GetApisRequest request){request=BeforeClientExecution(request);return ExecuteGetApis(request);}
public DeleteSmsChannelResult DeleteSmsChannel(DeleteSmsChannelRequest request) { request = BeforeClientExecution(request); return ExecuteDeleteSmsChannel(request); }
public TrackingRefUpdate GetTrackingRefUpdate() { return trackingRefUpdate; }
public void Print(bool b){Print(b.ToString());}
public QueryNode GetChild() { return GetChildren()[0]; }
public NotIgnoredFilter(int workdirTreeIndex) { this.index = workdirTreeIndex; }
public AreaRecord(RecordInputStream in) { field_1_formatFlags = in.ReadInt16(); }
public GetThumbnailRequest() : base("CloudPhoto", "2017-07-11", "GetThumbnail", "cloudphoto") { Protocol = ProtocolType.HTTPS; }
public DescribeTransitGatewayVpcAttachmentsResult DescribeTransitGatewayVpcAttachments(DescribeTransitGatewayVpcAttachmentsRequest request) { request = BeforeClientExecution(request); return ExecuteDescribeTransitGatewayVpcAttachments(request); }
public PutVoiceConnectorStreamingConfigurationResult PutVoiceConnectorStreamingConfiguration(PutVoiceConnectorStreamingConfigurationRequest request) { request = BeforeClientExecution(request); return ExecutePutVoiceConnectorStreamingConfiguration(request); }
public OrdRange GetOrdRange(string dim) { return prefixToOrdRange[dim]; }
public override string ToString() { var symbol = ""; if (startIndex >= 0 && startIndex < GetInputStream().Size) { symbol = GetInputStream().GetText(Antlr4.Runtime.Misc.Interval.Of(startIndex, startIndex)); symbol = Utils.EscapeWhitespace(symbol, false); } return string.Format(System.Globalization.CultureInfo.CurrentCulture, "{0}('{1}')", typeof(LexerNoViableAltException).Name, symbol); }
public E Peek() { return PeekFirstImpl(); }
public CreateWorkspacesResult CreateWorkspaces(CreateWorkspacesRequest request) { request = BeforeClientExecution(request); return ExecuteCreateWorkspaces(request); }
public NumberFormatIndexRecord Clone() { return Copy(); }
public DescribeRepositoriesResult DescribeRepositories(DescribeRepositoriesRequest request) { request = BeforeClientExecution(request); return ExecuteDescribeRepositories(request); }
public SparseIntArray(int initialCapacity) { initialCapacity = ArrayUtils.IdealIntArraySize(initialCapacity); mKeys = new int[initialCapacity]; mValues = new int[initialCapacity]; mSize = 0; }
public HyphenatedWordsFilter Create(TokenStream input) { return new HyphenatedWordsFilter(input); }
public CreateDistributionWithTagsResult CreateDistributionWithTags(CreateDistributionWithTagsRequest request){request = BeforeClientExecution(request); return ExecuteCreateDistributionWithTags(request);}
public RandomAccessFile(string fileName, string mode) : this(new FileInfo(fileName), mode) { }
public DeleteWorkspaceImageResult DeleteWorkspaceImage(DeleteWorkspaceImageRequest request){request = BeforeClientExecution(request); return ExecuteDeleteWorkspaceImage(request);}
public static string ToHex(long value){var sb=new System.Text.StringBuilder(16);WriteHex(sb,value,16,"");return sb.ToString();}
public UpdateDistributionResult UpdateDistribution(UpdateDistributionRequest request) { request = BeforeClientExecution(request); return ExecuteUpdateDistribution(request); }
public HSSFColor GetColor(short index){ if(index == HSSFColorPredefined.AUTOMATIC.Index){ return HSSFColorPredefined.AUTOMATIC.Color; } byte[] b = _palette.GetColor(index); return (b == null) ? null : new CustomColor(index, b); }
public ValueEval Evaluate(ValueEval[] operands, int srcRow, int srcCol) { throw new NotImplementedFunctionException(_functionName); }
public void Serialize(LittleEndianOutput out) { out.WriteShort((short)field_1_number_crn_records); out.WriteShort((short)field_2_sheet_table_index); }
public DescribeDBEngineVersionsResult DescribeDBEngineVersions() { return DescribeDBEngineVersions(new DescribeDBEngineVersionsRequest()); }
public FormatRun(short character, short fontIndex){this._character=character;this._fontIndex=fontIndex;}
public static byte[] ToBigEndianUtf16Bytes(char[] chars,int offset,int length){byte[] result=new byte[length*2];int end=offset+length;int resultIndex=0;for(int i=offset;i<end;++i){char ch=chars[i];result[resultIndex++]=(byte)(ch>>8);result[resultIndex++]=(byte)ch;}return result;}
public UploadArchiveResult UploadArchive(UploadArchiveRequest request){request=BeforeClientExecution(request);return ExecuteUploadArchive(request);}
public List<Token> GetHiddenTokensToLeft(int tokenIndex) => GetHiddenTokensToLeft(tokenIndex, -1);
public override bool Equals(object obj){if(object.ReferenceEquals(this,obj))return true;if(!base.Equals(obj))return false;if(GetType()!=obj.GetType())return false;AutomatonQuery other=(AutomatonQuery)obj;if(!compiled.Equals(other.compiled))return false;if(term==null){if(other.term!=null)return false;}else if(!term.Equals(other.term))return false;return true;}
public SpanQuery MakeSpanClause(){SpanQuery[] spanQueries=new SpanQuery[Size()];int i=0;foreach(KeyValuePair<SpanQuery,float> kv in weightBySpanQuery){SpanQuery sq=kv.Key;float boost=kv.Value;if(boost!=1f){sq=new SpanBoostQuery(sq,boost);}spanQueries[i++]=sq;}return spanQueries.Length==1?spanQueries[0]:new SpanOrQuery(spanQueries);}
public StashCreateCommand StashCreate() { return new StashCreateCommand(repo); }
public FieldInfo fieldInfo(string fieldName) { return byName[fieldName]; }
public DescribeEventSourceResult DescribeEventSource(DescribeEventSourceRequest request){request = BeforeClientExecution(request); return ExecuteDescribeEventSource(request);}
public GetDocumentAnalysisResult GetDocumentAnalysis(GetDocumentAnalysisRequest request){request = BeforeClientExecution(request);return ExecuteGetDocumentAnalysis(request);}
public CancelUpdateStackResult CancelUpdateStack(CancelUpdateStackRequest request){request = BeforeClientExecution(request); return ExecuteCancelUpdateStack(request);}
public ModifyLoadBalancerAttributesResult ModifyLoadBalancerAttributes(ModifyLoadBalancerAttributesRequest request) { request = BeforeClientExecution(request); return ExecuteModifyLoadBalancerAttributes(request); }
public SetInstanceProtectionResult SetInstanceProtection(SetInstanceProtectionRequest request) { request = BeforeClientExecution(request); return ExecuteSetInstanceProtection(request); }
public ModifyDBProxyResult ModifyDBProxy(ModifyDBProxyRequest request) { request = BeforeClientExecution(request); return ExecuteModifyDBProxy(request); }
public void Add(char[] output, int offset, int len, int endOffset, int posLength){if(count==outputs.Length){outputs=ArrayUtil.Grow(outputs,count+1);}if(count==endOffsets.Length){int[] next=new int[ArrayUtil.Oversize(1+count,sizeof(int))];Array.Copy(endOffsets,0,next,0,count);endOffsets=next;}if(count==posLengths.Length){int[] next=new int[ArrayUtil.Oversize(1+count,sizeof(int))];Array.Copy(posLengths,0,next,0,count);posLengths=next;}if(outputs[count]==null){outputs[count]=new CharsRefBuilder();}outputs[count].CopyChars(output,offset,len);endOffsets[count]=endOffset;posLengths[count]=posLength;count++;}
public FetchLibrariesRequest() : base("CloudPhoto", "2017-07-11", "FetchLibraries", "cloudphoto") { SetProtocol(ProtocolType.HTTPS); }
public bool Exists() { return fs.Exists(objects); }
public FilterOutputStream(Stream @out){this.@out=@out;}
public ScaleClusterRequest() : base("CS", "2015-12-15", "ScaleCluster", "csk") { SetUriPattern("/clusters/[ClusterId]"); SetMethod(MethodType.PUT); }
public DataValidationConstraint CreateTimeConstraint(int operatorType, string formula1, string formula2) { return DVConstraint.CreateTimeConstraint(operatorType, formula1, formula2); }
public ListObjectParentPathsResult ListObjectParentPaths(ListObjectParentPathsRequest request) { request = BeforeClientExecution(request); return ExecuteListObjectParentPaths(request); }
public DescribeCacheSubnetGroupsResult DescribeCacheSubnetGroups(DescribeCacheSubnetGroupsRequest request) { request = BeforeClientExecution(request); return ExecuteDescribeCacheSubnetGroups(request); }
public void SetSharedFormula(bool flag) { field_5_options = sharedFormula.SetShortBoolean(field_5_options, flag); }
public bool IsReuseObjects() { return reuseObjects; }
public ErrorNode AddErrorNode(Token badToken) { ErrorNodeImpl t = new ErrorNodeImpl(badToken); AddAnyChild(t); t.SetParent(this); return t; }
public LatvianStemFilterFactory(IDictionary<string,string> args) : base(args) { if (args.Count > 0) throw new ArgumentException("Unknown parameters: " + args); }
public EventSubscription RemoveSourceIdentifierFromSubscription(RemoveSourceIdentifierFromSubscriptionRequest request) { request = BeforeClientExecution(request); return ExecuteRemoveSourceIdentifierFromSubscription(request); }
public static TokenFilterFactory ForName(string name, IDictionary<string,string> args) { return loader.NewInstance(name, args); }
public AddAlbumPhotosRequest() : base("CloudPhoto", "2017-07-11", "AddAlbumPhotos", "cloudphoto") { Protocol = ProtocolType.HTTPS; }
public GetThreatIntelSetResult GetThreatIntelSet(GetThreatIntelSetRequest request) { request = BeforeClientExecution(request); return ExecuteGetThreatIntelSet(request); }
public RevFilter Clone() => new Binary(a.Clone(), b.Clone());
public override bool Equals(object o) { return o is ArmenianStemmer; }
public bool HasArray() { return ProtectedHasArray(); }
public UpdateContributorInsightsResult UpdateContributorInsights(UpdateContributorInsightsRequest request){request = BeforeClientExecution(request);return ExecuteUpdateContributorInsights(request);}
public void UnwriteProtectWorkbook() { records.Remove(fileShare); records.Remove(writeProtect); fileShare = null; writeProtect = null; }
public SolrSynonymParser(bool dedup, bool expand, Analyzer analyzer) : base(dedup, analyzer) { this.expand = expand; }
public RequestSpotInstancesResult RequestSpotInstances(RequestSpotInstancesRequest request){request = BeforeClientExecution(request);return ExecuteRequestSpotInstances(request);}
public byte[] GetObjectData() { return FindObjectRecord().GetObjectData(); }
public GetContactAttributesResult GetContactAttributes(GetContactAttributesRequest request) { request = BeforeClientExecution(request); return ExecuteGetContactAttributes(request); }
public override string ToString() { return GetKey() + ": " + GetValue(); }
public ListTextTranslationJobsResult ListTextTranslationJobs(ListTextTranslationJobsRequest request) { request = BeforeClientExecution(request); return ExecuteListTextTranslationJobs(request); }
public GetContactMethodsResult GetContactMethods(GetContactMethodsRequest request) { request = BeforeClientExecution(request); return ExecuteGetContactMethods(request); }
public static short LookupIndexByName(string name) { FunctionMetadata fd = GetInstance().GetFunctionByNameInternal(name); if (fd == null) { fd = GetInstanceCetab().GetFunctionByNameInternal(name); if (fd == null) { return -1; } } return (short)fd.GetIndex(); }
public DescribeAnomalyDetectorsResult DescribeAnomalyDetectors(DescribeAnomalyDetectorsRequest request){request=BeforeClientExecution(request);return ExecuteDescribeAnomalyDetectors(request);}
public static string InsertId(string message, ObjectId changeId) { return InsertId(message, changeId, false); }
public long GetObjectSize(AnyObjectId objectId, int typeHint){ long sz = db.GetObjectSize(this, objectId); if (sz < 0){ if (typeHint == OBJ_ANY) throw new MissingObjectException(objectId.Copy(), JGitText.Get().UnknownObjectType2); throw new MissingObjectException(objectId.Copy(), typeHint);} return sz;}
public ImportInstallationMediaResult ImportInstallationMedia(ImportInstallationMediaRequest request) { request = BeforeClientExecution(request); return ExecuteImportInstallationMedia(request); }
public PutLifecycleEventHookExecutionStatusResult PutLifecycleEventHookExecutionStatus(PutLifecycleEventHookExecutionStatusRequest request) { request = BeforeClientExecution(request); return ExecutePutLifecycleEventHookExecutionStatus(request); }
public NumberPtg(LittleEndianInput in) : this(in.ReadDouble()) {}
public GetFieldLevelEncryptionConfigResult GetFieldLevelEncryptionConfig(GetFieldLevelEncryptionConfigRequest request) { request = BeforeClientExecution(request); return ExecuteGetFieldLevelEncryptionConfig(request); }
public DescribeDetectorResult DescribeDetector(DescribeDetectorRequest request) { request = BeforeClientExecution(request); return ExecuteDescribeDetector(request); }
public ReportInstanceStatusResult ReportInstanceStatus(ReportInstanceStatusRequest request) { request = BeforeClientExecution(request); return ExecuteReportInstanceStatus(request); }
public DeleteAlarmResult DeleteAlarm(DeleteAlarmRequest request) { request = BeforeClientExecution(request); return ExecuteDeleteAlarm(request); }
public TokenStream Create(TokenStream input) => new PortugueseStemFilter(input);
public FtCblsSubRecord() { reserved = new byte[ENCODED_SIZE]; }
public override bool Remove(object obj) { lock (mutex) { return c.Remove(obj); } }
public GetDedicatedIpResult GetDedicatedIp(GetDedicatedIpRequest request) { request = BeforeClientExecution(request); return ExecuteGetDedicatedIp(request); }
public override string ToString() { return precedence + " >= _p"; }
public ListStreamProcessorsResult ListStreamProcessors(ListStreamProcessorsRequest request) { request = BeforeClientExecution(request); return ExecuteListStreamProcessors(request); }
public DeleteLoadBalancerPolicyRequest(string loadBalancerName, string policyName){SetLoadBalancerName(loadBalancerName);SetPolicyName(policyName);}
public WindowProtectRecord(int options) { _options = options; }
public UnbufferedCharStream(int bufferSize) { n = 0; data = new int[bufferSize]; }
public GetOperationsResult GetOperations(GetOperationsRequest request) { request = BeforeClientExecution(request); return ExecuteGetOperations(request); }
public void CopyRawTo(byte[] b,int o){NB.EncodeInt32(b,o,w1);NB.EncodeInt32(b,o+4,w2);NB.EncodeInt32(b,o+8,w3);NB.EncodeInt32(b,o+12,w4);NB.EncodeInt32(b,o+16,w5);}
public WindowOneRecord(RecordInputStream inStream){field_1_h_hold=inStream.ReadInt16();field_2_v_hold=inStream.ReadInt16();field_3_width=inStream.ReadInt16();field_4_height=inStream.ReadInt16();field_5_options=inStream.ReadInt16();field_6_active_sheet=inStream.ReadInt16();field_7_first_visible_tab=inStream.ReadInt16();field_8_num_selected_tabs=inStream.ReadInt16();field_9_tab_width_ratio=inStream.ReadInt16();}
public StopWorkspacesResult StopWorkspaces(StopWorkspacesRequest request) { request = BeforeClientExecution(request); return ExecuteStopWorkspaces(request); }
public void Close() { if (isOpen) { isOpen = false; try { Dump(); } finally { try { channel.Truncate(fileLength); } finally { try { channel.Close(); } finally { fos.Close(); } } } } }
public DescribeMatchmakingRuleSetsResult DescribeMatchmakingRuleSets(DescribeMatchmakingRuleSetsRequest request){request = BeforeClientExecution(request);return ExecuteDescribeMatchmakingRuleSets(request);}
public string GetPronunciation(int wordId, char[] surface, int off, int len) { return null; }
public string GetPath() { return pathStr; }
public static double Devsq(double[] v){double r=double.NaN;if(v!=null&&v.Length>=1){double m=0;double s=0;int n=v.Length;for(int i=0;i<n;i++){s+=v[i];}m=s/n;s=0;for(int i=0;i<n;i++){s+=(v[i]-m)*(v[i]-m);}r=(n==1)?0:s;}return r;}
public DescribeResizeResult DescribeResize(DescribeResizeRequest request) { request = BeforeClientExecution(request); return ExecuteDescribeResize(request); }
public bool HasPassedThroughNonGreedyDecision() { return passedThroughNonGreedyDecision; }
public int End() { return End(0); }
public void Traverse(CellHandler handler){int firstRow=range.FirstRow;int lastRow=range.LastRow;int firstColumn=range.FirstColumn;int lastColumn=range.LastColumn;int width=lastColumn-firstColumn+1;var ctx=new SimpleCellWalkContext();IRow currentRow=null;ICell currentCell=null;for(ctx.rowNumber=firstRow;ctx.rowNumber<=lastRow;++ctx.rowNumber){currentRow=sheet.GetRow(ctx.rowNumber);if(currentRow==null){continue;}for(ctx.colNumber=firstColumn;ctx.colNumber<=lastColumn;++ctx.colNumber){currentCell=currentRow.GetCell(ctx.colNumber);if(currentCell==null){continue;}if(IsEmpty(currentCell)&&!traverseEmptyCells){continue;}long rowSize=checked((long)(ctx.rowNumber-firstRow)*width);ctx.ordinalNumber=checked(rowSize+(ctx.colNumber-firstColumn+1));handler.OnCell(currentCell,ctx);}}}
public int GetReadIndex() { return pos; }
public int CompareTo(ScoreTerm other){return this.boost==other.boost?other.bytes.Get().CompareTo(this.bytes.Get()):this.boost.CompareTo(other.boost);}
public int Normalize(char[] s, int len){for(int i=0;i<len;i++){switch(s[i]){case FARSI_YEH:case YEH_BARREE:s[i]=YEH;break;case KEHEH:s[i]=KAF;break;case HEH_YEH:case HEH_GOAL:s[i]=HEH;break;case HAMZA_ABOVE:len=Delete(s,i,len);i--;break;default:break;}}return len;}
public void Serialize(LittleEndianOutput output) { output.WriteShort(_options); }
public DiagnosticErrorListener(bool exactOnly) { this.exactOnly = exactOnly; }
public KeySchemaElement(string attributeName, KeyType keyType) { SetAttributeName(attributeName); SetKeyType(keyType.ToString()); }
public GetAssignmentResult GetAssignment(GetAssignmentRequest request) { request = BeforeClientExecution(request); return ExecuteGetAssignment(request); }
public bool HasObject(AnyObjectId id){return FindOffset(id)!=-1;}
public GroupingSearch SetAllGroups(bool allGroups) { this.allGroups = allGroups; return this; }
// C# translation in a single line (thread-safety preserved with lock) public void SetMultiValued(string dimName, bool v) { lock(this) { DimConfig ft; if(!fieldTypes.TryGetValue(dimName, out ft)) { ft = new DimConfig(); fieldTypes[dimName] = ft; } ft.multiValued = v; } }
public int GetCellsVal(){int size=0;foreach(var c in cells.Keys){Cell e=At(c);if(e.cmd>=0){size++;}}return size;}
public DeleteVoiceConnectorResult DeleteVoiceConnector(DeleteVoiceConnectorRequest request){request = BeforeClientExecution(request);return ExecuteDeleteVoiceConnector(request);}
public DeleteLifecyclePolicyResult DeleteLifecyclePolicy(DeleteLifecyclePolicyRequest request){ request = BeforeClientExecution(request); return ExecuteDeleteLifecyclePolicy(request); }
