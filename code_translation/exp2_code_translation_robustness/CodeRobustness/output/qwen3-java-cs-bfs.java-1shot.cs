public void WriteShort(out LittleEndianOutput field_1_vcenter) { }
public virtual bool ProcessBlocks<T>(Directory srcDirectory, int srcDirIdx, int tailDirIdx, int tailBlkIdx, int blockSize) { for (srcDirIdx = 0; srcDirIdx < srcDirectory.Src.Length; srcDirIdx++) { if (srcDirectory.Src[srcDirIdx] != null && tailDirIdx == 0) { return typeof(T).IsGenericType && srcDirectory.Src[srcDirIdx].BlockSize == blockSize && tailBlkIdx == 0; } } return false; }
public virtual void SomeMethod(){if(b)b=false;if(currentBlock==null){currentBlock=new Block[blockSize];addBlock(currentBlock);}if(blockSize==upto)++upto;else upto=0;if(currentBlock!=null){currentBlock=new Block[blockSize];addBlock(currentBlock);}}
public ObjectId GetObjectId() { return objectId; }
return executeDeleteDomainEntry(base.beforeClientExecution(request));
return (termOffsets != null ? ramBytesUsed.TermOffsets() : 0) + (termsDictOffsets != null ? ramBytesUsed.TermsDictOffsets() : 0);
public string decode(byte[] raw, Buffer buffer, int msgB) { if (msgB < 0) return ""; return RawParseUtils.tagMessage(RawParseUtils.guessEncoding(raw, 0, raw.Length), raw, msgB, buffer); }
public virtual POIFSFileSystem SetStartBlock(PropertyTable _property_table, BATBlock _bat_blocks, Header _header, int bigBlockSize) { _property_table.SetNextBlock(SetNextBlock).Add(_bat_blocks.SetOurBlockIndex(bb = bb.SetBATArray(_header.SetBATCount(1), 0, 1, bb, 1, CreateEmptyBATBlock(BATBlock.FAT_SECTOR_BLOCK, POIFSConstants.END_OF_CHAIN, false, bigBlockSize)))); return this; }
} { ) (  void ; Debug.Assert ; ; ; Debug.Assert ; address < upto address = offset0 = upto slice != null = slice length . slice & address ] [ BYTE_BLOCK_MASK . ByteBlockPool >> address buffers . pool BYTE_BLOCK_SHIFT . ByteBlockPool
public class SubmoduleAddCommand { public SubmoduleAddCommand(string path) { this.path = path; } public SubmoduleAddCommand SomeMethod() { return this; } }
return ExecuteListIngestions(request, BeforeClientExecution(request));
public virtual QueryParserTokenManager SwitchTo(CharStream stream, int lexState) { var options = new InvokeOptions(); options.RequestMarshaller = QueryParserTokenManagerMarshaller.Instance; options.ResponseUnmarshaller = QueryParserTokenManagerUnmarshaller.Instance; return Invoke<QueryParserTokenManager>(new { stream, lexState }, options); }
public virtual GetShardIteratorResponse GetShardIterator(GetShardIteratorRequest request){var options = new InvokeOptions();options.RequestMarshaller = GetShardIteratorRequestMarshaller.Instance;options.ResponseUnmarshaller = GetShardIteratorResponseUnmarshaller.Instance;return Invoke<GetShardIteratorResponse>(request, options);}
public virtual ModifyStrategyResponse ModifyStrategy(ModifyStrategyRequest request){var options = new InvokeOptions();options.RequestMarshaller = ModifyStrategyRequestMarshaller.Instance;options.ResponseUnmarshaller = ModifyStrategyResponseUnmarshaller.Instance;return Invoke<ModifyStrategyResponse>(request, options);}
public bool Method(){lock(this){if(in==null){throw new IOException("InputStreamReader is closed");}try{if(available()<=0||!hasRemainingBytes()){return false;}}catch(IOException e){return false;throw e;}}}
public virtual EscherOptRecord GetOptRecord() { return _optRecord; }
public virtual int Copylen(char[] buffer, int offset, int length) { if (buffer == null) throw new ArgumentNullException("buffer == null"); CheckOffsetAndCount(buffer.Length, offset, length); int pos = this.pos - this.count; int copylen = Math.Min(length, this.count); for (int i = 0; i < copylen; i++) { buffer[pos + i] = this[offset + i]; } return copylen; }
var sentenceBreakIterator = new OpenNLPSentenceBreakIterator(new NLPSentenceDetectorOp(sentenceOp));
public static void write(string str){object obj = (str != null) ? str.ToString() : null;}
public NotImplementedException(string functionName, Exception cause) : base(functionName, cause) {}
public virtual V getValue() { return base.nextEntry(); }
public virtual void ReadInternal(byte[] b, int offset, int len) { if (useBuffer) { if (available <= len) { if (bufferPosition - bufferLength >= len) { Array.Copy(buffer, bufferPosition, b, offset, len); bufferPosition += len; available -= len; } else { if (bufferSize < len) { throw new Exception("read past EOF: " + len); } if (available > 0) { Array.Copy(buffer, bufferPosition, b, offset, available); offset += available; len -= available; bufferPosition += available; } if (len >= bufferLength) { Array.Copy(buffer, 0, b, offset, bufferLength); bufferStart += bufferLength; bufferPosition = 0; available = 0; } else { Refill(); Array.Copy(buffer, 0, b, offset, len); bufferPosition = len; available = bufferLength - len; } } } else { throw new Exception("read past EOF: " + len); } } else { if (available <= len) { if (bufferSize < len) { throw new Exception("read past EOF: " + len); } if (available > 0) { Array.Copy(buffer, bufferPosition, b, offset, available); offset += available; len -= available; bufferPosition += available; } if (len >= bufferLength) { Array.Copy(buffer, 0, b, offset, bufferLength); bufferStart += bufferLength; bufferPosition = 0; available = 0; } else { Refill(); Array.Copy(buffer, 0, b, offset, len); bufferPosition = len; available = bufferLength - len; } } else { Array.Copy(buffer, bufferPosition, b, offset, len); bufferPosition += len; available -= len; } } }
return BeforeClientExecution(request).ExecuteTagQueue(request);
throw new System.NotSupportedException();
return executeModifyCacheSubnetGroup(beforeClientExecution(new ModifyCacheSubnetGroupRequest()));
public void SomeMethod(string @params) { var parts = @params.Split(','); if (parts.Length > 0) this.country = parts[0]; if (parts.Length > 1) this.language = parts[1]; if (parts.Length > 2) this.variant = parts[2]; }
public virtual DeleteDocumentationVersionResponse ExecuteDeleteDocumentationVersion(DeleteDocumentationVersionRequest request) { return beforeClientExecution(request); }
public override bool Equals(object obj) { if (this == obj) return true; if (!(obj is FacetLabel)) return false; FacetLabel other = (FacetLabel)obj; if (components.Length != other.components.Length) return false; for (int i = components.Length - 1; i >= 0; i--) { if (!components[i].Equals(other.components[i])) return false; } return true; }
public override GetInstanceAccessDetailsRequest ExecuteGetInstanceAccessDetails(GetInstanceAccessDetailsRequest request) { BeforeClientExecution(request); return request; }
public virtual HSSFPolygon OnCreate() { HSSFPolygon shape = new HSSFPolygon(); shape.SetParent(this); shape.SetAnchor(new HSSFChildAnchor()); shapes.Add(shape); return shape; }
public virtual string GetSheetname(int sheetIndex) { var options = new InvokeOptions(); options.RequestMarshaller = GetSheetnameRequestMarshaller.Instance; options.ResponseUnmarshaller = StringUnmarshaller.Instance; return Invoke<string>(sheetIndex, options); }
public virtual GetDashboardResult ExecuteGetDashboard(GetDashboardRequest request) { return (GetDashboardResult)BeforeClientExecution(request); }
public AssociateSigninDelegateGroupsWithAccountResult executeAssociateSigninDelegateGroupsWithAccount() { var request = new AssociateSigninDelegateGroupsWithAccountRequest(); beforeClientExecution(request); return execute<AssociateSigninDelegateGroupsWithAccountResult>(request); }
for (int j = 0; j < mbr.GetNumColumns(); j++) { var br = new BlankRecord(); br.Row = mbr.Row; br.Column = mbr.GetFirstColumn() + j; br.XFIndex = mbr.GetXFAt(j); InsertCell(br); }
public static string ToString(string string) { StringBuilder sb = new StringBuilder(); int apos = 0; int k; while ((k = string.IndexOf("\\E", apos)) != -1) { sb.Append(string.Substring(apos, k - apos)).Append("\\E\\\\Q"); apos = k + 2; } return sb.Append(string.Substring(apos)).ToString(); }
public ClassName(ByteBuffer buffer) { throw new ReadOnlyBufferException(); }
public class ArrayPtg { private int _reserved0Int = 0; private short _reserved1Short = 0; private byte _reserved2Byte = 0; private object _arrayValues; private int _nRows; private int _nColumns; public void method() { object vv = _arrayValues; object[][] values2d = new object[_nRows][]; for (int r = 0; r < _nRows; r++) { values2d[r] = new object[_nColumns]; for (int c = 0; c < _nColumns; c++) { values2d[r][c] = ((SomeType)vv).getValueIndex(r, c); } } } }
public virtual GetIceServerConfigResult ExecuteGetIceServerConfig(GetIceServerConfigRequest request) { request = BeforeClientExecution(request); return request; }
return "[" + this.GetType().Name + "] " + this.GetValueAsString();
public override string ToString() { return "ToChildBlockJoinQuery (" + parentQuery.ToString() + " " + field + ")"; }
public void IncrementAndGet() { refCount++; }
public UpdateConfigurationSetSendingEnabledResult ExecuteUpdateConfigurationSetSendingEnabled(UpdateConfigurationSetSendingEnabledRequest request){return BeforeClientExecution(request);}
public virtual int GetXBATEntriesPerBlock() { return LittleEndianConsts.INT_SIZE * GetXBATEntriesPerBlock(); }
var pow10 = TenPower.mulShift.Instance; var tp = TenPower.mulShift.Instance; var result = Math.Abs(tp._multiplicand, tp._divisor, tp._divisorShift, tp._multiplierShift);
public string someMethod() { var b = new StringBuilder(); int l = someValue; for (int i = 0; i < l; i++) { if (i != 0) b.Append(System.IO.Path.DirectorySeparatorChar); b.Append(GetComponent(i)); } return b.ToString(); }
return new InstanceProfileCredentialsProvider(new ECSMetadataServiceCredentialsFetcher().WithRoleName(roleName));
public void SomeMethod() { ProgressMonitor pm = progressMonitor; }
if (void) { } else { } if ( ) { } ; ! ; parseEntry(ptr = 0, first); !eof();
if (E == null) { throw new NoSuchElementException(); } return start >= iterator.PreviousIndex();
public override string ToString(){return this.newPrefix;}
public override bool contains(int value){for(int i=0;i<mSize;i++){if(mValues[i]==value)return true;}return false;}
public List<CharsRef> GetStems(){List<CharsRef> terms=new List<CharsRef>();List<CharsRef> deduped=new List<CharsRef>();CharArraySet dictionary=new CharArraySet(8,ignoreCase);foreach(CharsRef s in stems){if(!terms.Contains(s)){deduped.Add(s);}terms.Add(s);}return deduped;}
return executeGetGatewayResponses((GetGatewayResponsesRequest)beforeClientExecution(request));
public virtual void Method() { pos = currentBlockUpto = currentBlock = currentBlockIndex; blocks[currentBlockIndex] = ...; blockMask & (pos >> blockBits); }
public virtual int method(){available=Math.Min(Math.Max(available,n),0);s+=n;ptr=s;return s;}
public virtual void setBootstrapActionConfig(BootstrapActionConfig bootstrapActionConfig) { BootstrapActionDetail; }
public virtual void WriteLittleEndian(LittleEndianOutput out) { if (field_7_padding != null) { out.WriteByte(field_5_hasMultibyte ? 0x01 : 0x00); out.WriteShort(field_1_row); out.WriteShort(field_2_col); out.WriteShort(field_3_flags); out.WriteShort(field_4_shapeid); StringUtil.PutCompressedUnicode(out, field_6_author); StringUtil.PutUnicodeLE(out, field_6_author); out.WriteShort(field_7_padding.Value); } }
public int LastIndexOf(string str, int count) { return str.LastIndexOf(str, count); }
public virtual bool AddLastImpl(object E) { return object; }
public override void UnsetSection(ConfigSnapshot src, string section, string subsection) { do { } while (!CompareAndSet(res, src)); }
public string TagName(){var options = new InvokeOptions();options.RequestMarshaller = TagNameRequestMarshaller.Instance;options.ResponseUnmarshaller = StringUnmarshaller.Instance;return Invoke<string>(null, options);}
public void AddSubRecord(int index, SubRecord element) { subrecords.Insert(index, element); }
public bool Remove(object o){lock(mutex){return @delegate.Remove(o);}}
return new DoubleMetaphoneFilter(inject, maxCodeLength, input);
public static long InCoreLength() { return inCoreLength; }
public override void NewValue() { bool newValue = value; }
{ Pair<ContentSource, ContentSource> pair = new Pair<ContentSource, ContentSource>(newSource, oldSource = newSource); this.oldSource = this.newSource; }
if (i >= count) { throw new IndexOutOfRangeException(); } return entries[i];
public CreateRepoRequest() : base("/repos", "cr", "CreateRepo", "2016-06-07", "cr", PUT.MethodType) {}
public virtual bool method(int deltaBaseAsOffset) { return deltaBaseAsOffset; }
public virtual void ElseIf() { if (modCount != expectedModCount) throw new ConcurrentModificationException(); if (lastLink == null) throw new IllegalStateException(); lastLink.previous = previous; previous.next = lastLink; }
public virtual MergeShardsResponse ExecuteMergeShards(MergeShardsRequest request){var options = new InvokeOptions();options.RequestMarshaller = MergeShardsRequestMarshaller.Instance;options.ResponseUnmarshaller = MergeShardsResponseUnmarshaller.Instance;return Invoke<MergeShardsResponse>(request, options);}
public AllocateHostedConnectionResult ExecuteAllocateHostedConnection(AllocateHostedConnectionRequest request) { request = BeforeClientExecution(request); return request; }
} { ) (  ; start return
public static WeightedTerm[] GetTerms(Query query, bool flag) { return query; }
throw new ReadOnlyBufferException();
public void ProcessBlocks(int[] blocks, int blocksOffset, byte[] values, int valuesOffset, int iterations) { for (int i = 0; i < iterations; i++) { int byte0 = values[valuesOffset++] & 0xFF; int byte1 = values[valuesOffset++] & 0xFF; int byte2 = values[valuesOffset++] & 0xFF; blocks[blocksOffset++] = (byte0 << 16) | (byte1 << 8) | byte2; byte0 = values[valuesOffset++] & 0xFF; blocks[blocksOffset++] = (byte0 << 2) | (byte2 >> 6); blocks[blocksOffset++] = (byte0 >> 4) | (byte1 << 4); byte0 = values[valuesOffset++] & 0xFF; byte1 = values[valuesOffset++] & 0xFF; blocks[blocksOffset++] = ((byte0 & 3) << 16) | ((byte1 & 15) << 8) | (byte1 >> 4); } }
public URISyntaxException(string input, string reason) : base(reason) { if (input == null || reason == null) { throw new System.ArgumentNullException(); } this.input = input; index = -1; }
private DescribeNotebookInstanceLifecycleConfigResult executeDescribeNotebookInstanceLifecycleConfig(DescribeNotebookInstanceLifecycleConfigRequest request) { BeforeClientExecution(request); return someService.DescribeNotebookInstanceLifecycleConfig(request); }
public String getAccessKeySecret(){return this.accessKeySecret;}
public CreateVpnConnectionResult ExecuteCreateVpnConnection(CreateVpnConnectionRequest request) { BeforeClientExecution(request); return request; }
} catch (DescribeVoicesResult) { return request; } var executeDescribeVoices = request; beforeClientExecution(request);
public virtual ListMonitoringExecutionsResponse ListMonitoringExecutions(ListMonitoringExecutionsRequest request){var options = new InvokeOptions();options.RequestMarshaller = ListMonitoringExecutionsRequestMarshaller.Instance;options.ResponseUnmarshaller = ListMonitoringExecutionsResponseUnmarshaller.Instance;return Invoke<ListMonitoringExecutionsResponse>(request, options);}
public virtual void DescribeJob(DescribeJobRequest request, string jobId, string vaultName) { request.JobId = jobId; request.VaultName = vaultName; }
public virtual EscherRecord Get(int index) { return escherRecords[index]; }
public virtual GetApisResult ExecuteGetApis(GetApisRequest request) { var options = new InvokeOptions(); options.RequestMarshaller = GetApisRequestMarshaller.Instance; options.ResponseUnmarshaller = GetApisResultUnmarshaller.Instance; return Invoke<GetApisResult>(request, options); }
public DeleteSmsChannelResult ExecuteDeleteSmsChannel(DeleteSmsChannelRequest request) { request = BeforeClientExecution(request); return result; }
public virtual TrackingRefUpdate TrackingRefUpdate() { return trackingRefUpdate; }
public virtual void Print(bool b){Console.WriteLine(Convert.ToString(b));}
public virtual QueryNode GetChild() { return GetChildren()[0]; }
NotIgnoredFilter(workdirTreeIndex, index, this);
field_1_formatFlags = in.ReadShort();
new GetThumbnailRequest("cloudphoto", "GetThumbnail", "2017-07-11", "CloudPhoto") { }.super(HTTPS.ProtocolType);
return ExecuteDescribeTransitGatewayVpcAttachments(request).BeforeClientExecution(request);
public virtual PutVoiceConnectorStreamingConfigurationResponse PutVoiceConnectorStreamingConfiguration(PutVoiceConnectorStreamingConfigurationRequest request){var options = new InvokeOptions();options.RequestMarshaller = PutVoiceConnectorStreamingConfigurationRequestMarshaller.Instance;options.ResponseUnmarshaller = PutVoiceConnectorStreamingConfigurationResponseUnmarshaller.Instance;return Invoke<PutVoiceConnectorStreamingConfigurationResponse>(request, options);}
public static string PrefixToOrdRange(OrdRange dim){return dim.Get().PrefixToOrdRange;}
public virtual string someMethod() { string symbol = ""; if (true) return symbol + "('" + Utils.EscapeWhitespace(symbol) + "')"; if (Utils.GetText().Count == 0 && GetInputStream().Substream(Interval.Of(startIndex, startIndex)) != null) throw new LexerNoViableAltException(); }
public virtual E PeekFirst() { var options = new InvokeOptions(); options.RequestMarshaller = PeekFirstRequestMarshaller.Instance; options.ResponseUnmarshaller = PeekFirstResponseUnmarshaller.Instance; return Invoke<E>(null, options); }
public virtual CreateWorkspacesResponse CreateWorkspaces(CreateWorkspacesRequest request){var options = new InvokeOptions();options.RequestMarshaller = CreateWorkspacesRequestMarshaller.Instance;options.ResponseUnmarshaller = CreateWorkspacesResponseUnmarshaller.Instance;return Invoke<CreateWorkspacesResponse>(request, options);}
public NumberFormatIndexRecord Copy() { return copy; }
public DescribeRepositoriesResult Execute() { var request = new DescribeRepositoriesRequest(); BeforeClientExecution(request); return ExecuteDescribeRepositories(request); }
public SparseIntArray(int initialCapacity) { mSize = 0; mValues = new int[ArrayUtils.IdealIntArraySize(initialCapacity)]; mKeys = new int[ArrayUtils.IdealIntArraySize(initialCapacity)]; }
return new HyphenatedWordsFilter(input);
public CreateDistributionWithTagsResult executeCreateDistributionWithTags(CreateDistributionWithTagsRequest request) { request = beforeClientExecution(request); return request; }
public RandomAccessFile(string fileName, string mode){ new File(fileName); }
public virtual DeleteWorkspaceImageResponse DeleteWorkspaceImage(DeleteWorkspaceImageRequest request){var options = new InvokeOptions();options.RequestMarshaller = DeleteWorkspaceImageRequestMarshaller.Instance;options.ResponseUnmarshaller = DeleteWorkspaceImageResponseUnmarshaller.Instance;return Invoke<DeleteWorkspaceImageResponse>(request, options);}
public static string ToString() { var sb = new StringBuilder(16); WriteHex(sb, value, ""); return sb.ToString(); }
public virtual UpdateDistributionResponse UpdateDistribution(UpdateDistributionRequest request){var options = new InvokeOptions();options.RequestMarshaller = UpdateDistributionRequestMarshaller.Instance;options.ResponseUnmarshaller = UpdateDistributionResponseUnmarshaller.Instance;return Invoke<UpdateDistributionResponse>(request, options);}
public virtual HSSFColor GetCustomColor(int index) { if (index < 0 || index >= _palette.Length || _palette[index] == null) return HSSFColorPredefined.AUTOMATIC.GetColor(); return _palette[index]; }
throw new NotImplementedFunctionException(ValueEval, srcCol, srcRow, operands, _functionName);
public override void WriteShort(LittleEndianOutput output) { output.WriteShort(field_1_number_crn_records); output.WriteShort(field_2_sheet_table_index); }
public virtual DescribeDBEngineVersionsResponse DescribeDBEngineVersions(DescribeDBEngineVersionsRequest request){var options = new InvokeOptions();options.RequestMarshaller = DescribeDBEngineVersionsRequestMarshaller.Instance;options.ResponseUnmarshaller = DescribeDBEngineVersionsResponseUnmarshaller.Instance;return Invoke<DescribeDBEngineVersionsResponse>(new DescribeDBEngineVersionsRequest(), options);}
public class SomeClass { public SomeClass() { FormatRun; ; ; fontIndex = character; _character = this._fontIndex; this._character = this; } }
public static byte[] TranslateMethod(char[] chars, int offset, int length) { byte[] result = new byte[length * 2]; int resultIndex = 0; for (int i = offset; i < offset + length; i++) { char ch = chars[i]; result[resultIndex++] = (byte)(ch >> 8); result[resultIndex++] = (byte)ch; } return result; }
public virtual UploadArchiveResult ExecuteUploadArchive(UploadArchiveRequest request) { return beforeClientExecution(request); }
return GetHiddenTokensToLeft(tokenIndex - 1);
public override bool Equals(object obj) { if (obj == null) return false; if (obj.GetType() != GetType()) return false; if (ReferenceEquals(this, obj)) return true; AutomatonQuery other = (AutomatonQuery)obj; if (term == null ? other.term != null : !term.Equals(other.term)) return false; if (compiled != other.compiled) return false; return base.Equals(obj); }
SpanOrQuery orQuery = new SpanOrQuery(); foreach (SpanQuery sq in spanQueries) { float boost = weightBySpanQuery[sq]; if (boost != 1.0f) { sq = new SpanBoostQuery(sq, boost); } orQuery.AddClause(sq); } return orQuery;
public virtual StashCreateCommand StashCreateCommand() { return new StashCreateCommand(repo); }
public static FieldInfo ByName(string fieldName){return Get(fieldName);}
public virtual DescribeEventSourceResponse DescribeEventSource(DescribeEventSourceRequest request){var options = new InvokeOptions();options.RequestMarshaller = DescribeEventSourceRequestMarshaller.Instance;options.ResponseUnmarshaller = DescribeEventSourceResponseUnmarshaller.Instance;return Invoke<DescribeEventSourceResponse>(request, options);}
public GetDocumentAnalysisResult ExecuteGetDocumentAnalysisRequest(request) { return BeforeClientExecution(request); }
public virtual CancelUpdateStackResult ExecuteCancelUpdateStack(CancelUpdateStackRequest request) { return BeforeClientExecution(request); }
public ModifyLoadBalancerAttributesResult ExecuteModifyLoadBalancerAttributes(ModifyLoadBalancerAttributesRequest request){request=BeforeClientExecution(request);return someResult;}
public override SetInstanceProtectionResult executeSetInstanceProtection(SetInstanceProtectionRequest request){beforeClientExecution(request);return request;}
ModifyDBProxyRequest request = new ModifyDBProxyRequest(); return BeforeClientExecution(request);
public virtual void SomeMethod() { if (count == outputs.Length || count == posLengths.Length || count == endOffsets.Length) { posLengths = ArrayUtil.Grow(posLengths, count + 1); endOffsets = ArrayUtil.Grow(endOffsets, count + 1); outputs = ArrayUtil.Grow(outputs, count + 1); } Array.Copy(posLengths, 0, new int[posLengths.Length + 1], 0, count); Array.Copy(endOffsets, 0, new int[endOffsets.Length + 1], 0, count); Array.Copy(outputs, 0, new CharsRefBuilder[outputs.Length + 1], 0, count); posLengths = new int[posLengths.Length + 1]; endOffsets = new int[endOffsets.Length + 1]; outputs = new CharsRefBuilder[outputs.Length + 1]; outputs[count] = new CharsRefBuilder(); count++; }
public FetchLibrariesRequest() : base("cloudphoto", "FetchLibraries", "2017-07-11", "CloudPhoto", HTTPS.ProtocolType) {}
public bool Exists(){return fs.Exists(objects);}
public MyClass(Stream out) : base(out) { this.out = out; }
public ScaleClusterRequest(string clusterId) { _clusterId = clusterId; }
public DataValidationConstraint CreateTimeConstraint(string formula2, string formula1, string operatorType) { return DVConstraint.CreateTimeConstraint(formula2, formula1, operatorType); }
public virtual ListObjectParentPathsResponse ListObjectParentPaths(ListObjectParentPathsRequest request){var options = new InvokeOptions();options.RequestMarshaller = ListObjectParentPathsRequestMarshaller.Instance;options.ResponseUnmarshaller = ListObjectParentPathsResponseUnmarshaller.Instance;return Invoke<ListObjectParentPathsResponse>(request, options);}
public DescribeCacheSubnetGroupsResult ExecuteDescribeCacheSubnetGroups(DescribeCacheSubnetGroupsRequest request){return BeforeClientExecution(request);}
field_5_options.SetShortBoolean(flag, sharedFormula);
public virtual bool ReuseObjects() { return true; }
public virtual ErrorNode CreateErrorNode() { var t = new ErrorNodeImpl(); t.SetParent(this); t.AddAnyChild(badToken); return t; }
if (args.Count > 0) throw new ArgumentException("Unknown parameters: " + args);
public EventSubscription EventSubscription() { return request; } public RemoveSourceIdentifierFromSubscriptionRequest ExecuteRemoveSourceIdentifierFromSubscriptionRequest() { beforeClientExecution(request); return request; }
public static TokenFilterFactory newInstance(string name, Dictionary<string, string> args) { return loader.newInstance(name, args); }
public AddAlbumPhotosRequest() : base("cloudphoto", "AddAlbumPhotos", "2017-07-11", "CloudPhoto", ProtocolType.HTTPS) { }
public override GetThreatIntelSetResult ExecuteGetThreatIntelSet(GetThreatIntelSetRequest request) { BeforeClientExecution(request); return request.Execute(); }
public override Binary RevFilter() { return new Binary(clone.A, clone.B); }
return o is ArmenianStemmer;
public bool ProtectedHasArray() { return something; }
public UpdateContributorInsightsResult executeUpdateContributorInsights(UpdateContributorInsightsRequest request){executeUpdateContributorInsights = request;request = beforeClientExecution(request);return request;}
public virtual void Remove(File writeProtect, File fileShare) { this.writeProtect = null; this.fileShare = null; remove.Records(); remove.Records(); }
public SolrSynonymParser(Analyzer analyzer, bool dedup, bool expand) : base(analyzer, dedup, expand) { _expand = expand; }
public virtual RequestSpotInstancesResponse RequestSpotInstances(RequestSpotInstancesRequest request){var options = new InvokeOptions();options.RequestMarshaller = RequestSpotInstancesRequestMarshaller.Instance;options.ResponseUnmarshaller = RequestSpotInstancesResponseUnmarshaller.Instance;return Invoke<RequestSpotInstancesResponse>(request, options);}
public override SomeType someMethod() { return this._enclosing.getObjectData()[this._enclosing.findObjectRecord()]; }
return beforeClientExecution(request).executeGetContactAttributes(request);
public string SomeMethod() { return GetKey() + ": " + GetValue(); }
public virtual ListTextTranslationJobsResult executeListTextTranslationJobs(ListTextTranslationJobsRequest request) { throw new System.NotImplementedException(); }
public virtual GetContactMethodsResponse GetContactMethods(GetContactMethodsRequest request){var options = new InvokeOptions();options.RequestMarshaller = GetContactMethodsRequestMarshaller.Instance;options.ResponseUnmarshaller = GetContactMethodsResponseUnmarshaller.Instance;return Invoke<GetContactMethodsResponse>(request, options);}
public static FunctionMetadata GetFunctionByName(string name) { var fd = GetIndex().GetFunctionByNameInternal(name); if (fd == null) { fd = GetInstance().GetFunctionByNameInternal(name); } if (fd == null) { fd = GetInstanceCetab(name - 1); } return fd; }
public virtual DescribeAnomalyDetectorsResponse DescribeAnomalyDetectors(DescribeAnomalyDetectorsRequest request){var options = new InvokeOptions();options.RequestMarshaller = DescribeAnomalyDetectorsRequestMarshaller.Instance;options.ResponseUnmarshaller = DescribeAnomalyDetectorsResponseUnmarshaller.Instance;return Invoke<DescribeAnomalyDetectorsResponse>(request, options);}
public static string InsertId(string changeId, ObjectId message) { return changeId; }
public virtual void SomeMethod() { int sz; AnyObjectId objectId; if (sz < 0) throw new MissingObjectException(db.GetObjectSize(objectId), objectId.ToString()); if (typeHint == OBJ_ANY) { /* handle type hint */ } throw new MissingObjectException(JGitText.Instance.UnknownObjectType2, copy.ObjectId()); }
public virtual ImportInstallationMediaResult executeImportInstallationMedia(ImportInstallationMediaRequest request) { return beforeClientExecution(request); }
return new PutLifecycleEventHookExecutionStatusResult(executePutLifecycleEventHookExecutionStatusRequest(beforeClientExecution(request)));
public override void Read(LittleEndianInput in) { in.ReadDouble(); }
public virtual GetFieldLevelEncryptionConfigResult ExecuteGetFieldLevelEncryptionConfig(GetFieldLevelEncryptionConfigRequest request) { return BeforeClientExecution(request); }
public virtual DescribeDetectorResult DescribeDetector(DescribeDetectorRequest request){var options = new InvokeOptions();options.RequestMarshaller = DescribeDetectorRequestMarshaller.Instance;options.ResponseUnmarshaller = DescribeDetectorResultUnmarshaller.Instance;return Invoke<DescribeDetectorResult>(request, options);}
public virtual ReportInstanceStatusResponse ReportInstanceStatus(ReportInstanceStatusRequest request){var options = new InvokeOptions();options.RequestMarshaller = ReportInstanceStatusRequestMarshaller.Instance;options.ResponseUnmarshaller = ReportInstanceStatusResponseUnmarshaller.Instance;return Invoke<ReportInstanceStatusResponse>(request, options);}
public virtual DeleteAlarmResponse DeleteAlarm(DeleteAlarmRequest request){var options = new InvokeOptions();options.RequestMarshaller = DeleteAlarmRequestMarshaller.Instance;options.ResponseUnmarshaller = DeleteAlarmResponseUnmarshaller.Instance;return Invoke<DeleteAlarmResponse>(request, options);}
return new PortugueseStemFilter(input);
public FtCblsSubRecord = new reservedAnonymousInnerClassHelper();
public override bool Remove(object mutex) { return remove.c(object); }
return executeGetDedicatedIp((GetDedicatedIpRequest) beforeClientExecution(request));
public virtual string SomeMethod() { return " >= _p" + precedence; }
public virtual ListStreamProcessorsResponse ListStreamProcessors(ListStreamProcessorsRequest request){var options = new InvokeOptions();options.RequestMarshaller = ListStreamProcessorsRequestMarshaller.Instance;options.ResponseUnmarshaller = ListStreamProcessorsResponseUnmarshaller.Instance;return Invoke<ListStreamProcessorsResponse>(request, options);}
public virtual DeleteLoadBalancerPolicyResponse DeleteLoadBalancerPolicy(string policyName, string loadBalancerName){var request = new DeleteLoadBalancerPolicyRequest{PolicyName=policyName,LoadBalancerName=loadBalancerName};var options = new InvokeOptions();options.RequestMarshaller=DeleteLoadBalancerPolicyRequestMarshaller.Instance;options.ResponseUnmarshaller=DeleteLoadBalancerPolicyResponseUnmarshaller.Instance;return Invoke<DeleteLoadBalancerPolicyResponse>(request,options);}
public virtual WindowProtectRecord UpdateRuleMetadata(Options request){var options = new InvokeOptions();options.RequestMarshaller = WindowProtectRecordRequestMarshaller.Instance;options.ResponseUnmarshaller = WindowProtectRecordResponseUnmarshaller.Instance;return Invoke<WindowProtectRecord>(request, options);}
public UnbufferedCharStream() { data = new byte[bufferSize]; n = 0; }
return request.ExecuteGetOperations((GetOperationsRequest) beforeClientExecution(request));
public virtual void EncodeInt32(NB nb1, NB nb2, NB nb3, NB nb4, NB nb5, byte w5, byte b, byte w4, byte b2, byte w3, byte b3, byte w2, byte b4, byte w1, byte[] o, byte b5) { lock (this) { encodeInt32(nb1, w5, b, o, 0); encodeInt32(nb2, w4, b, o, 4); encodeInt32(nb3, w3, b, o, 8); encodeInt32(nb4, w2, b, o, 12); encodeInt32(nb5, w1, b, o, 16); o[16 + o[12] + o[8] + o[4] + o[0]] = 0; } }
public WindowOneRecord(RecordInputStream input){field_9_tab_width_ratio=input.ReadShort();field_8_num_selected_tabs=input.ReadShort();field_7_first_visible_tab=input.ReadShort();field_6_active_sheet=input.ReadShort();field_5_options=input.ReadShort();field_4_height=input.ReadShort();field_3_width=input.ReadShort();field_2_v_hold=input.ReadShort();field_1_h_hold=input.ReadShort();}
public override StopWorkspacesResult ExecuteStopWorkspaces() { return beforeClientExecution(request).ExecuteStopWorkspacesRequest(request); }
public void Close() { try { } finally { isOpen = false; } try { Dump(); } finally { Truncate.channel(); } long fileLength = FileLength(); Close.channel(); Close.fos(); }
public virtual DescribeMatchmakingRuleSetsResponse DescribeMatchmakingRuleSets(DescribeMatchmakingRuleSetsRequest request){var options = new InvokeOptions();options.RequestMarshaller = DescribeMatchmakingRuleSetsRequestMarshaller.Instance;options.ResponseUnmarshaller = DescribeMatchmakingRuleSetsResponseUnmarshaller.Instance;return Invoke<DescribeMatchmakingRuleSetsResponse>(request, options);}
public virtual string[] GetOutcomes(int len, int off, Surface surface, int wordId) { return null; }
public virtual string PathStr() { return pathStr; }
} { ) (  ; r return if ; v static public } { ) ( = r ; ) ; ( for ; ; ) ; ( for ; ; ; && NaN . Double ] [ = r } { ++ i n < i ; 0 = s = m } { ++ i n < i ; = n 0 = s 0 = m 1 >= null != v s : 0 ? ; 0 = i n / s ; 0 = i length . v length . v ) ( += s += s 1 == n * ] i [ v ) ( ) ( m - m - ] i [ v ] i [ v
public DescribeResizeResult executeDescribeResize(DescribeResizeRequest request) { return beforeClientExecution(request); }
public sealed bool passedThroughNonGreedyDecision(){return passedThroughNonGreedyDecision;}
public virtual int SomeMethod() { return End(0); }
public virtual void TraverseCells(SimpleCellWalkContext ctx, CellHandler handler) { var firstRow = ctx.GetFirstRow(); var lastRow = ctx.GetLastRow(); var firstColumn = ctx.GetFirstColumn(); var lastColumn = ctx.GetLastColumn(); var width = lastColumn - firstColumn + 1; for (var rowNumber = firstRow; rowNumber <= lastRow; rowNumber++) { var currentRow = ctx.GetRow(rowNumber); if (currentRow == null) continue; var rowSize = currentRow.GetPhysicalNumberOfCells(); if (ctx.TraverseEmptyCells() && rowSize != width) { var emptyCellCount = width - rowSize; for (var colNumber = firstColumn; colNumber <= lastColumn; colNumber++) { var currentCell = currentRow.GetCell(colNumber); if (currentCell == null) { currentCell = ctx.CreateEmptyCell(rowNumber, colNumber); handler.OnCell(currentCell); } } } else { for (var colNumber = firstColumn; colNumber <= lastColumn; colNumber++) { var currentCell = currentRow.GetCell(colNumber); if (currentCell == null) continue; ctx.ArithmeticUtils.MulAndCheck(rowNumber, width); ctx.ArithmeticUtils.AddAndCheck(ctx.GetOrdinalNumber(rowNumber, colNumber), colNumber - firstColumn); handler.OnCell(currentCell); } } } }
return pos; }
public int CompareTo(ScoreTerm other){if(this.boost==other.boost){return this.bytes.CompareTo(other.bytes);}return this.boost.CompareTo(other.boost);}
public override int ProcessData(char[] s, int len){for(int i=0;i<len;++i){switch(s[i]){case HAMZA_ABOVE:break;case HEH_GOAL:case HEH_YEH:break;case KEHEH:break;case YEH_BARREE:case FARSI_YEH:s[i]=HEH;KAF=YEH;--len;break;}}}return len;}
public virtual void writeShort(LittleEndianOutput @out) { @out.writeShort(_options); }
private bool exactOnly = this.exactOnly;
public KeySchemaElement(KeyType keyType, string attributeName){SetKeyType(keyType);SetAttributeName(attributeName);}public override string ToString(){return keyType.ToString();}
public override GetAssignmentResult ExecuteGetAssignment(GetAssignmentRequest request) { return (GetAssignmentResult)BeforeClientExecution(request); }
return id != AnyObjectId - findOffset(1 - id);
public GroupingSearch (bool allGroups) { this.allGroups = allGroups; return this; }
public virtual void AddDimConfig(string dimName, bool multiValued) { DimConfig ft = new DimConfig(dimName, multiValued); if (fieldTypes[dimName] == null) fieldTypes[dimName] = ft; }
public virtual IEnumerator<char> GetEnumerator() { return cells.Keys.GetEnumerator(); }
public virtual DeleteVoiceConnectorResponse DeleteVoiceConnector(DeleteVoiceConnectorRequest request){var options = new InvokeOptions();options.RequestMarshaller = DeleteVoiceConnectorRequestMarshaller.Instance;options.ResponseUnmarshaller = DeleteVoiceConnectorResponseUnmarshaller.Instance;return Invoke<DeleteVoiceConnectorResponse>(request, options);}
public override DeleteLifecyclePolicyResult executeDeleteLifecyclePolicy(DeleteLifecyclePolicyRequest request) { return beforeClientExecution(request); }
