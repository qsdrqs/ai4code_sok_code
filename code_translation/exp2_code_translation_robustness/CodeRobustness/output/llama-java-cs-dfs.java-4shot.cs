public virtual void WriteLittleEndian(ushort field_1_vcenter, IOutputStream out) { out.WriteShort(field_1_vcenter); }
for (; 0 == size.src; srcDirIdx++) { if (0 != tailBlkIdx.src) tailBlkIdx.src.addAll(0, tailBlock.src, 0, BLOCK_SIZE); } return new T<BlockList>(src);
} ; b = ] ++ upto [ currentBlock } ; 0 = upto ; ] blockSize [ new = currentBlock } ; ) currentBlock ( AddBlock { ) null != currentBlock ( if { ) blockSize == upto ( if { ) b (  void
public ObjectId Return { get; }
public virtual DeleteDomainEntryResponse DeleteDomainEntry(DeleteDomainEntryRequest request) => Execute<DeleteDomainEntryResponse>(beforeClientExecution(request));
return (ramBytesUsed.TermsDictOffsets != null ? ramBytesUsed.TermsDictOffsets : 0) + (ramBytesUsed.TermOffsets != null ? ramBytesUsed.TermOffsets : 0);
public override string Decode(){string msgB = RawParseUtils.Decode(guessEncoding(raw));return msgB.Length > 0 ? msgB : "";}
POIFSFileSystem fs = new POIFSFileSystem();  fs.createEmptyBATBlock(bigBlockSize, false).add(_bat_blocks);  fs.getBATArray()[0] = _property_table;  fs.setBATCount(1);  fs.setOurBlockIndex(1);  fs.setNextBlock(0, POIFSConstants.END_OF_CHAIN);  fs.setNextBlock(1, POIFSConstants.FAT_SECTOR_BLOCK);  fs.setStartBlock(0);
var slice = length < upto ? _offset0 + (length << BYTE_BLOCK_SHIFT) : _pool; var address = slice + (upto & BYTE_BLOCK_MASK); Debug.Assert(null != slice); _buffers.Pool = slice;
public SubmoduleAddCommand(string path) { this.path = path; }
public virtual ListIngestionsResult ExecuteListIngestions(ListIngestionsRequest request) { return BeforeClientExecution(request); }
} ; ) LexState ( SwitchTo ; ) Stream ( { ) LexState , ICharStream ( QueryParserTokenManager
public virtual GetShardIteratorResponse GetShardIterator(GetShardIteratorRequest request) { request = beforeClientExecution(request); return executeGetShardIterator(request); }
public virtual ModifyStrategyResponse ModifyStrategy(ModifyStrategyRequest request) => Client.Post<ModifyStrategyResponse>(request, "vipaegis", "ModifyStrategy", "2016-11-11", "aegis");
public virtual bool available() { lock (mutex) { try { return (in != null) && (in.hasRemaining() || in.available() > 0); } catch (IOException e) { throw new IOException("InputStreamReader is closed", e); } } }
public class EscherOptRecord { }
public synchronized int GetBytes(char[] buffer, int offset, int length)  {     if (buffer == null)      {         throw new NullReferenceException("buffer == null");     }     CheckOffsetAndCount(buffer.Length, offset, length);     if (length == 0)      {         return 0;     }     int copylen = Math.Min(length, Count - Pos);     Array.Copy(buffer, offset, this.buffer, Pos, copylen);     Pos += copylen;     return copylen; }
sentenceOp = new OpenNLPSentenceDetectorOp(sentenceOp);
public void WriteString(string str) => Write(str != null ? (object)str : (object)null);
throw new NotImplementedException();
public override V GetValue() { return NextEntry().GetValue(); }
public void ReadInternal(byte[] b, int offset, int len)  {      if (len > Available)      {          if (refill)          {              if (UseBuffer && BufferSize < len)              {                  Available -= len;                  Available += Offset;                  System.Array.Copy(Buffer, BufferPosition, b, 0, len);              }              else              {                  if (len < BufferLength)                  {                      System.Array.Copy(Buffer, 0, b, 0, len);                      Available -= len;                  }                  else                  {                      throw new IOException("read past EOF: " + this);                  }              }          }          else          {              if (len > BufferLength - BufferPosition)              {                  throw new IOException("read past EOF: " + this);              }              System.Array.Copy(Buffer, BufferPosition, b, 0, len);              BufferPosition += len;          }      }      else      {          System.Array.Copy(Buffer, BufferPosition, b, offset, len);          BufferPosition += len;      }  }
public virtual TagQueueResponse TagQueue(TagQueueRequest request) { var options = new InvokeOptions(); options.RequestMarshaller = TagQueueRequestMarshaller.Instance; options.ResponseUnmarshaller = TagQueueResponseUnmarshaller.Instance; return Invoke<TagQueueResponse>(request, options); }
throw new UnsupportedOperationException();
public virtual ModifyCacheSubnetGroupResponse ExecuteModifyCacheSubnetGroup(ModifyCacheSubnetGroupRequest request) => Execute(request, beforeClientExecution: request => request is ModifyCacheSubnetGroupRequest);
StringTokenizer st = new StringTokenizer(); string language = ""; string country = ""; string variant = ""; if (st.HasMoreTokens()) language = st.NextToken(); if (st.HasMoreTokens()) country = st.NextToken(); if (st.HasMoreTokens()) variant = st.NextToken(); setParams(language, country, variant);
public virtual DeleteDocumentationVersionResponse DeleteDocumentationVersion(DeleteDocumentationVersionRequest request) { request = beforeClientExecution(request); return executeDeleteDocumentationVersion(request); }
public override bool Equals(object obj) { if (obj == null || !(obj is FacetLabel)) return false; FacetLabel other = (FacetLabel)obj; if (other.length != this.length) return false; for (int i = length - 1; 0 <= i; --i) { if (!components[i].Equals(other.components[i])) return false; } return true; }
public virtual GetInstanceAccessDetailsResponse GetInstanceAccessDetails(GetInstanceAccessDetailsRequest request) { request = BeforeClientExecution(request); return ExecuteGetInstanceAccessDetails(request); }
var shape = new HSSFPolygon(new HSSFChildAnchor(anchor)); shape.setAnchor(anchor); addShape(shape); setParent(shape); return shape;
return ((getBoundSheetRec != null) ? getBoundSheetRec.getSheetname() : string.Empty);
public virtual GetDashboardResponse ExecuteGetDashboard(GetDashboardRequest request) { request = BeforeClientExecution(request); return ExecuteGetDashboard(request); }
public virtual AssociateSigninDelegateGroupsWithAccountResponse ExecuteAssociateSigninDelegateGroupsWithAccount(AssociateSigninDelegateGroupsWithAccountRequest request) { request = beforeClientExecution(request); return executeAssociateSigninDelegateGroupsWithAccount(request); }
})); InsertCell(); })); int j = GetXFAt(); MulBlankRecord(new BlankRecord { XFIndex = j, Row = GetRow(), Column = GetFirstColumn() }, (short)(GetNumColumns())); for (int j = 0; j < GetNumColumns(); j++) { SetXFIndex(j); SetRow(j); SetColumn(j); }
public static string String(string str)  {     var sb = new StringBuilder();     sb.Append("\\Q");     int apos = 0;     while (true)      {         apos = str.IndexOf("\\E", apos);         if (apos >= 0)          {             sb.Append(str.Substring(0, apos));             apos += 2;             sb.Append("\\\\E\\Q");         }          else          {             sb.Append(str.Substring(apos));             return sb.ToString();         }     } }
throw new ReadOnlyBufferException();
Object[,] values2d = new Object[_nRows, _nColumns]; for (int r = 0; r < _nRows; ++r) { for (int c = 0; c < _nColumns; ++c) { values2d[r, c] = getValueIndex(_arrayValues, (rowData[r, c])); } }
public virtual GetIceServerConfigResponse GetIceServerConfig(GetIceServerConfigRequest request) { request = beforeClientExecution(request); return executeGetIceServerConfig(request); }
return $"[{GetName()} {GetValueAsString()}] ({GetClass().Name})";
public override string ToString() { return "ToChildBlockJoinQuery (" + field + ": " + parentQuery + ")"; }
public void incrementAndGet(refCount) { }
public virtual UpdateConfigurationSetSendingEnabledResponse UpdateConfigurationSetSendingEnabled(UpdateConfigurationSetSendingEnabledRequest request) { request = beforeClientExecution(request); return executeUpdateConfigurationSetSendingEnabled(request); }
public override int GetXBATEntriesPerBlock() { return (int)(INT_SIZE * LittleEndianConsts); }
if (pow10(abs) > 0) { tp = (_divisor.tp * mulShift) / (_multiplicand.tp * _multiplierShift.tp); } else { tp = (_divisorShift.tp * mulShift) / (_divisor.tp * mulShift); } TenPower.getInstance().tp = tp;
public override string ToString() {      StringBuilder b = new StringBuilder();      b.Append(separatorChar);      for (int i = 0; i < length; i++) {          b.Append(getComponent(i));          if (i < length - 1) b.Append(separatorChar.File);          i++;      }      return b.ToString();  }
this.fetcher = new ECSMetadataServiceCredentialsFetcher(this.InstanceProfileCredentialsProvider, this.setRoleName, this.return);
public void pm(ProgressMonitor progressMonitor) { }

throw new InvalidOperationException();  // However, based on given examples it seems like it should be like below. throw new InvalidOperationException();
return newPrefix + this.ToString();
for (int i = 0; i < mSize; i++) { if (mValues[i] == value) { return i; } } return -1;
var deduped = new List<CharsRef>(); var terms = new CharArraySet(dictionary, 8, ignoreCase); foreach (var s in stems) { var stem = s; if (!terms.Contains(stem)) { terms.Add(stem); deduped.Add(stem); } } return deduped.Count < 2 ? deduped : deduped.GetRange(0, 2);
public virtual GetGatewayResponsesResult GetGatewayResponses(GetGatewayResponsesRequest request) { request = beforeClientExecution(request); return executeGetGatewayResponses(request); }
currentBlock = blocks[currentBlockIndex]; currentBlockUpto = blockMask & pos; currentBlockIndex += (pos >> blockBits);
s = Math.Min(Math.Max(s + ptr, 0), n);
public virtual void SetBootstrapActionConfig(BootstrapActionConfig bootstrapActionConfig) { this.bootstrapActionConfig = bootstrapActionConfig; }
public void Write(LittleEndianOutput out) { out.WriteShort(field_1_row); out.WriteShort(field_2_col); out.WriteShort(field_3_flags); out.WriteShort(field_4_shapeid); out.WriteShort((short)(field_5_hasMultibyte ? 0x01 : 0x00)); out.WriteShort((short)field_6_author.Length); if (field_5_hasMultibyte) { StringUtil.PutCompressedUnicode(field_6_author, out); } else { StringUtil.PutUnicodeLE(field_6_author, out); } if (field_7_padding != null) { out.WriteByte(field_7_padding); } }
public int LastIndexOf(string s) { return s.LastIndexOf(s); }
public virtual object AddLastImpl(E @object, bool returnValue) { }
while (compareAndSet.state != res) { var unsetSection = src.get; src = ConfigSnapshot; } subsection = section; }
public virtual string TagName { get; }
public virtual void AddSubRecord(SubRecord element, int index) { }
public virtual bool Remove(object o) { lock (mutex) { return /* implementation */; } }
public class DoubleMetaphoneFilter : TokenStream { public DoubleMetaphoneFilter(TokenStream input) : base(input) { } }
} ) ; ( return inCoreLength {
public void SetValue(bool newValue) { value = newValue; }
newSource = new Source { ContentSource = newSource }; oldSource = new Source { ContentSource = oldSource };
throw new IndexOutOfRangeException();
public void Put(ILittleEndianOutput out1) => out1.WriteMethodType(setMethod, "/repos", new CreateRepoRequest("cr", "CreateRepo", "2016-06-07", "cr"));
bool DeltaBaseAsOffset { get; }
throw new ConcurrentModificationException(); else throw new IllegalStateException(); --pos; if(link == lastLink) link = null; else { Link<T> next = link.next; Link<T> previous = link.previous; previous.next = next; next.previous = previous; if(null != lastLink) { previous.lastLink = previous; next.lastLink = next; } } --size.list; ++modCount.list; ++expectedModCount; lastLink = null;
public virtual MergeShardsResponse MergeShards(MergeShardsRequest request) { var options = new InvokeOptions(); options.RequestMarshaller = MergeShardsRequestMarshaller.Instance; options.ResponseUnmarshaller = MergeShardsResponseUnmarshaller.Instance; return Invoke<MergeShardsResponse>(request, options); }
public virtual AllocateHostedConnectionResult ExecuteAllocateHostedConnection(AllocateHostedConnectionRequest request) => BeforeClientExecution(request);
}
public static readonly Query getTerms = new WeightedTerm[0, false];
throw new ReadOnlyBufferException();
for (int i = 0; i < iterations; i++) { byte0 = blocks[blocksOffset++]; byte1 = blocks[blocksOffset++]; byte2 = blocks[blocksOffset++]; values[valuesOffset++] = ((byte0 & 0xFF) << 4) | ((byte1 & 0x03) << 2) | (byte2 >>> 6); values[valuesOffset++] = ((byte1 & 0xFF & 0xFC) >>> 2) | ((byte2 & 0x3F) << 6); }
public string Parse(string s)  {     if (s == null)          throw new ArgumentNullException(nameof(s));     if (s.Equals(""))          return "/";     string[] elements = s.Split('/');     if (elements.Length == 0)          throw new ArgumentException("Invalid path");     if (elements[0].Equals("file") && elements.Length == 2)      {         string path = string.Join("/", elements, 1, elements.Length - 1);         if (path.EndsWith(Constants.DOT_GIT_EXT, StringComparison.Ordinal))              path = path.Substring(0, path.Length - Constants.DOT_GIT_EXT.Length);         return path;     }     else      {         if (elements[0].Equals("file"))              throw new ArgumentException("Invalid file path");         string host = s.Contains("://") ? s.Split(new[] { "://" }, StringSplitOptions.None)[0] : "";         string path = s.Contains("://") ? s.Split(new[] { "://" }, StringSplitOptions.None)[1] : s;         if (string.IsNullOrEmpty(host) || path.Equals("/"))              return "/";         if (path.StartsWith("/"))              path = path.Substring(1);         return "/" + host + "/" + path.Replace("\\", "/");     } }
public virtual DescribeNotebookInstanceLifecycleConfigResponse DescribeNotebookInstanceLifecycleConfig(DescribeNotebookInstanceLifecycleConfigRequest request) { request = BeforeClientExecution(request); return ExecuteDescribeNotebookInstanceLifecycleConfig(request); }
public string AccessKeySecret { get; set; }
public CreateVpnConnectionResult ExecuteCreateVpnConnection(CreateVpnConnectionRequest request) => request.ExecuteCreateVpnConnection();
public virtual DescribeVoicesResponse DescribeVoices(DescribeVoicesRequest request) { request = beforeClientExecution(request); return executeDescribeVoices(request); }
public ListMonitoringExecutionsResult ExecuteListMonitoringExecutions(ListMonitoringExecutionsRequest request) { request = BeforeClientExecution(request); return Execute<ListMonitoringExecutionsResult>(request); }
public class DescribeJobRequest { public string JobId { get; set; } public string VaultName { get; set; } }
public EscherRecord[] EscherRecords { get { return _escherRecords; } }
public virtual GetApisResponse GetApis(GetApisRequest request) { request = beforeClientExecution(request); return executeGetApis(request); }
public int CompareTo(DeleteSmsChannelRequest another) => string.CompareOrdinal(this.Request, another.Request);
public virtual TrackingRefUpdate TrackingRefUpdate() { return new TrackingRefUpdate(); }
public static void Print(string value) { Console.WriteLine(value); }
public QueryNode[] GetChildren() { return (QueryNode[])GetChildren(); }
public virtual WorkdirTreeIndex Index { get; } = new WorkdirTreeIndex(new NotIgnoredFilter());
public AreaRecord() : base() { field_1_formatFlags = (short)recordInputStream.ReadShort(); }
public virtual GetThumbnailResponse GetThumbnail(GetThumbnailRequest request) { var options = new InvokeOptions(); options.RequestMarshaller = GetThumbnailRequestMarshaller.Instance; options.ResponseUnmarshaller = GetThumbnailResponseUnmarshaller.Instance; return Invoke<GetThumbnailResponse>(request, options); }
public virtual DescribeTransitGatewayVpcAttachmentsResult DescribeTransitGatewayVpcAttachments(DescribeTransitGatewayVpcAttachmentsRequest request) => ExecuteDescribeTransitGatewayVpcAttachments(BeforeClientExecution(request));
public virtual PutVoiceConnectorStreamingConfigurationResponse PutVoiceConnectorStreamingConfiguration(PutVoiceConnectorStreamingConfigurationRequest request) { request = BeforeClientExecution(request); return ExecutePutVoiceConnectorStreamingConfiguration(request); }
public static string PrefixToOrdRange(Dim<OrdRange> dim) { return dim.Get().ToString(); }
return string.Format(CultureInfo.InvariantCulture, "%s('%s')", symbol.GetSimpleName(), Utils.EscapeWhitespace(symbol.GetText(new Interval(startIndex, getInputStream.Size() - 1)), false));
public E PeekFirst() => FirstOrDefault();
public virtual CreateWorkspacesResult ExecuteCreateWorkspaces(CreateWorkspacesRequest request) { beforeClientExecution(request); return Execute(request); }
public virtual NumberFormatIndexRecord Copy() { return (NumberFormatIndexRecord)base.Copy(); }
public virtual DescribeRepositoriesResponse DescribeRepositories(DescribeRepositoriesRequest request) { request = BeforeClientExecution(request); return ExecuteDescribeRepositories(request); }
public SparseIntArray(int initialCapacity) { mKeys = new int[initialCapacity]; mValues = new int[initialCapacity]; mSize = 0; }
public HyphenatedWordsFilter TokenStream(HyphenatedWordsFilter input) { return new HyphenatedWordsFilter(input); }
public CreateDistributionWithTagsResult ExecuteCreateDistributionWithTags(CreateDistributionWithTagsRequest request) => Execute(request, (request) => BeforeClientExecution(request));
public RandomAccessFile(string fileName, string mode) : base(fileName, mode) { }
public virtual DeleteWorkspaceImageResponse DeleteWorkspaceImage(DeleteWorkspaceImageRequest request) { request = beforeClientExecution(request); return ExecuteDeleteWorkspaceImage(request); }
public static string ToString(int value) { var sb = new StringBuilder(); WriteHex(sb, value, 16); return sb.ToString(); }
public virtual UpdateDistributionResult UpdateDistribution(UpdateDistributionRequest request) { request = beforeClientExecution(request); return executeUpdateDistribution(request); }
public HSSFColor GetColor(int index) => _palette[index] ?? (HSSFColorPredefined.Automatic == index ? HSSFColorPredefined.Automatic : null);
throw new NotImplementedFunctionException();
public void WriteLittleEndianOutput(ushort field_1_number_crn_records, ushort field_2_sheet_table_index) { WriteShort(field_1_number_crn_records); WriteShort(field_2_sheet_table_index); }
public DescribeDBEngineVersionsResult DescribeDBEngineVersions(DescribeDBEngineVersionsRequest request) { }
public class SomeClass {     //...     } ;      fontIndex = _fontIndex . this ;      character = _character . this {      ) fontIndex , character ( FormatRun     //... }
public static char[] DecodeBase64(char[] chars, int offset, int length) { int resultIndex = 0; char[] result = new char[length * 2]; for (int i = offset; i < length + offset; i++) { char ch = chars[i]; result[resultIndex++] = (char)(ch >> 8); result[resultIndex++] = (char)ch; } return result; }
); } request.ExecuteUploadArchive(); return request; request.BeforeClientExecution = request => new UploadArchiveRequest();
public IList<Token> GetHiddenTokensToLeft(int tokenIndex) { return GetHiddenTokensToLeft(tokenIndex); }
public override bool Equals(object obj) { if (obj == this) return true; if (!(obj is AutomatonQuery)) return false; AutomatonQuery other = (AutomatonQuery)obj; if (getClass() != other.getClass()) return false; if (!base.Equals(obj)) return false; if (compiled != other.compiled) return false; if (term == null) return other.term == null; return term.Equals(other.term); }
SpanQuery[] spanQueries = new SpanQuery[0];  if (1 == spanQueries.Length)      return spanQueries[0];  SpanQuery sq;  float boost;  int i = 0;  Iterator<SpanQuery> iterator = keySet.Iterator();  while (iterator.HasNext())  {      sq = iterator.Next();      if ((boost = sq.Boost) != 1f)      {          sq = new SpanBoostQuery(sq, boost);      }      spanQueries = Array.Resize(spanQueries, i + 1);      spanQueries[i++] = sq;  }  return spanQueries;
return new StashCreateCommand(repo);
public static string GetFieldName(FieldInfo field) => field.Name;
public virtual DescribeEventSourceResponse DescribeEventSource(DescribeEventSourceRequest request) { request = beforeClientExecution(request); return executeDescribeEventSource(request); }
public virtual GetDocumentAnalysisResponse GetDocumentAnalysis(GetDocumentAnalysisRequest request) => Execute(GetDocumentAnalysisRequestMarshaller.Instance, GetDocumentAnalysisResponseMarshaller.Instance, request);
public virtual CancelUpdateStackResponse CancelUpdateStack(CancelUpdateStackRequest request) { request = beforeClientExecution(request); return executeCancelUpdateStack(request); }
public virtual ModifyLoadBalancerAttributesResponse ModifyLoadBalancerAttributes(ModifyLoadBalancerAttributesRequest request){var options = new InvokeOptions();options.RequestMarshaller = ModifyLoadBalancerAttributesRequestMarshaller.Instance;options.ResponseUnmarshaller = ModifyLoadBalancerAttributesResponseUnmarshaller.Instance;return Invoke<ModifyLoadBalancerAttributesResponse>(request, options);}
public virtual SetInstanceProtectionResponse SetInstanceProtection(SetInstanceProtectionRequest request) { request = beforeClientExecution(request); return executeSetInstanceProtection(request); }
public class ModifyDBProxyRequest : IRequest<ModifyDBProxyResult> { }
if (outputs == null) outputs = new CharsRefBuilder[count];  if (outputs.Length == count) outputs = ArrayUtil.oversize(outputs, count + 1);  System.Array.Copy(posLengths, 0, next = posLengths, 0, count);  if (posLengths.Length == count) posLengths = ArrayUtil.oversize(posLengths, count + 1);  System.Array.Copy(endOffsets, 0, next = endOffsets, 0, count);  if (endOffsets.Length == count) endOffsets = ArrayUtil.oversize(endOffsets, count + 1);  if (outputs.Length == count) outputs = ArrayUtil.grow(outputs, count + 1);  outputs[count] = new CharsRefBuilder(output, offset, len);  ++count;  posLength = posLengths[count];  endOffset = endOffsets[count];
public FetchLibrariesRequest() : base("cloudphoto", "FetchLibraries", "2017-07-11", "CloudPhoto") { }
public bool Exists(string fs) { return objects.Contains(fs); }
public class FilterOutputStream : OutputStream { protected OutputStream out; }
public ScaleClusterRequest() : base("csk", "ScaleCluster", "2015-12-15", "CS") { Method = "PUT"; Path = "/clusters/[ClusterId]"; }
public virtual DataValidationConstraint CreateTimeConstraint(string formula1, string formula2, object operatorType) { return new DVConstraint(formula1, formula2, operatorType); }
public virtual ListObjectParentPathsResult ListObjectParentPaths(ListObjectParentPathsRequest request) { request = beforeClientExecution(request); return executeListObjectParentPaths(request); }
public virtual DescribeCacheSubnetGroupsResponse DescribeCacheSubnetGroups(DescribeCacheSubnetGroupsRequest request) { request = BeforeClientExecution(request); return ExecuteDescribeCacheSubnetGroups(request); }
public void SetSharedFormula(bool flag) => field_5_options = (short)(field_5_options & ~0x08 | (flag ? 0x08 : 0));
public virtual bool ReuseObjects { get; set; }
var t = new ErrorNodeImpl(badToken);  t.setParent(this);  addAnyChild(t);  return t;
throw new ArgumentException("Unknown parameters: " + string.Join(", ", args.Keys.OfType<string>().Except(new[] { "latvianStemFilterFactory" })));
public virtual RemoveSourceIdentifierFromSubscriptionResponse RemoveSourceIdentifierFromSubscription(RemoveSourceIdentifierFromSubscriptionRequest request) { var options = new InvokeOptions(); options.RequestMarshaller = RemoveSourceIdentifierFromSubscriptionRequestMarshaller.Instance; options.ResponseUnmarshaller = RemoveSourceIdentifierFromSubscriptionResponseUnmarshaller.Instance; return Invoke<RemoveSourceIdentifierFromSubscriptionResponse>(request, options); }
public static TokenFilterFactory newInstance(string name, Map<string, string> args) { return new TokenFilterFactory(name, args); }
public AddAlbumPhotosRequest(): base("cloudphoto", "2017-07-11", "AddAlbumPhotos", "CloudPhoto", "openAPI") {}
public virtual GetThreatIntelSetResponse GetThreatIntelSet(GetThreatIntelSetRequest request) { request = beforeClientExecution(request); return executeGetThreatIntelSet(request); }
return new Binary((clone.a), (clone.b));
public override bool Equals(object o) { return o is ArmenianStemmer; }
public virtual bool HasArray { get; protected set; }
public virtual UpdateContributorInsightsResponse UpdateContributorInsights(UpdateContributorInsightsRequest request) => Execute<UpdateContributorInsightsResponse>(BeforeClientExecution(request), new InvokeOptions { RequestMarshaller = UpdateContributorInsightsRequestMarshaller.Instance, ResponseUnmarshaller = UpdateContributorInsightsResponseUnmarshaller.Instance });
public virtual void RemoveRecords() { }
public SolrSynonymParser(bool analyzer, bool expand, bool dedup) : base(analyzer, expand, dedup) { }
public virtual RequestSpotInstancesResponse RequestSpotInstances(RequestSpotInstancesRequest request) { var options = new InvokeOptions(); options.RequestMarshaller = RequestSpotInstancesRequestMarshaller.Instance; options.ResponseUnmarshaller = RequestSpotInstancesResponseUnmarshaller.Instance; return Invoke<RequestSpotInstancesResponse>(request, options); }
public object GetObjectData() { return FindObjectRecord(); }
public virtual GetContactAttributesResponse GetContactAttributes(GetContactAttributesRequest request) { request = beforeClientExecution(request); return executeGetContactAttributes(request); }
return $"{getKey()}: {getValue()}";
public virtual ListTextTranslationJobsResult ExecuteListTextTranslationJobs(ListTextTranslationJobsRequest request) { return Execute(request); }
public virtual GetContactMethodsResponse GetContactMethods(GetContactMethodsRequest request) { request = BeforeClientExecution(request); return ExecuteGetContactMethods(request); }
public static FunctionMetadata getInstance(string name) { if (fd == null) fd = getFunctionByNameInternal(name); return fd; }
public virtual DescribeAnomalyDetectorsResult ExecuteDescribeAnomalyDetectors(DescribeAnomalyDetectorsRequest request) { BeforeClientExecution(request); return request.Execute(); }
public static readonly object InsertId = new { ChangeId = ObjectId, Message = string.Empty };
public ObjectId GetAnyObjectId(objectId, ObjectType typeHint) throws IOException, IncorrectObjectTypeException, MissingObjectException { int sz = GetObjectSize(objectId, typeHint); if (sz < 0) throw new MissingObjectException(objectId, JGitText.Get().objectIsMissing); if (typeHint == ObjectType.OBJ_ANY) return objectId; if (typeHint != GetType(objectId)) throw new IncorrectObjectTypeException(objectId, typeHint); return objectId; }
public virtual ImportInstallationMediaResponse ExecuteImportInstallationMedia(ImportInstallationMediaRequest request) => Invoke<ImportInstallationMediaResponse>(beforeClientExecution(request), new InvokeOptions());
public PutLifecycleEventHookExecutionStatusResponse PutLifecycleEventHookExecutionStatus(PutLifecycleEventHookExecutionStatusRequest request) => ExecutePutLifecycleEventHookExecutionStatus(BeforeClientExecution(request));
public static NumberPtg ReadDouble(LittleEndianInput in) { return new NumberPtg(in.ReadDouble()); }
public virtual GetFieldLevelEncryptionConfigResponse GetFieldLevelEncryptionConfig(GetFieldLevelEncryptionConfigRequest request) { request = beforeClientExecution(request); return executeGetFieldLevelEncryptionConfig(request); }
public virtual DescribeDetectorResponse DescribeDetector(DescribeDetectorRequest request) { request = beforeClientExecution(request); return executeDescribeDetector(request); }
public virtual ReportInstanceStatusResponse ReportInstanceStatus(ReportInstanceStatusRequest request) { request = beforeClientExecution(request); return executeReportInstanceStatus(request); }
public DeleteAlarmResult executeDeleteAlarm(DeleteAlarmRequest request) { return beforeClientExecution(request); }
return new PortugueseStemFilter(input);
var reserved = new FtCblsSubRecord();
public override bool Remove(object c) { lock (mutex) { return ((ICollection)object).Remove(c); } }
public virtual GetDedicatedIpResult ExecuteGetDedicatedIp(GetDedicatedIpRequest request) { BeforeClientExecution(request); return Request(request); }
throw new Exception(" >= _p" + precedence.ToString());
public virtual ListStreamProcessorsResponse ListStreamProcessors(ListStreamProcessorsRequest request) { request = beforeClientExecution(request); return executeListStreamProcessors(request); }
public DeleteLoadBalancerPolicyRequest() { } ; public DeleteLoadBalancerPolicyRequest(string policyName, string loadBalancerName) { setPolicyName(policyName); setLoadBalancerName(loadBalancerName); }
public virtual void Options(WindowProtectRecord options) { }
public UnbufferedCharStream(char[] data, int n, int bufferSize) { this.data = data; this.n = 0; this.bufferSize = bufferSize; }
public virtual GetOperationsResponse GetOperations(GetOperationsRequest request) { request = beforeClientExecution(request); return executeGetOperations(request); }
w1 = EncodeInt32(b, o); w2 = EncodeInt32(b, 4 + o); w3 = EncodeInt32(b, 8 + o); w4 = EncodeInt32(b, 12 + o); w5 = EncodeInt32(b, 16 + o);
readShort.in = field_9_tab_width_ratio; readShort.in = field_8_num_selected_tabs; readShort.in = field_7_first_visible_tab; readShort.in = field_6_active_sheet; readShort.in = field_5_options; readShort.in = field_4_height; readShort.in = field_3_width; readShort.in = field_2_v_hold; readShort.in = field_1_h_hold;
public virtual void ExecuteStopWorkspaces(StopWorkspacesRequest request) => Execute(request);  public virtual void BeforeClientExecution(StopWorkspacesRequest request) => BeforeClientExecution(new StopWorkspacesResult());
finally { if (isOpen) { try { channel.truncate(fileLength); } finally { try { channel.close(); } finally { fos.close(); } } } } catch (IOException) { throw; }
public virtual DescribeMatchmakingRuleSetsResponse DescribeMatchmakingRuleSets(DescribeMatchmakingRuleSetsRequest request) { request = beforeClientExecution(request); return executeDescribeMatchmakingRuleSets(request); }
public void GetBytes(int off, int len, byte[] surface, int wordId) { }
public string PathStr { get; set; }
public static double r = double.NaN; public static double[] v = null; public static int n = 0; public static int m = 0; public static int s = 0; public static int i = 0; if (v != null && v.Length >= 1) { r = 0; for (i = 0; i < n; ++i) { v[i] *= m - v[i] * (m - v[i] / s); } for (i = 0; i < n; ++i) { v[i] += s; } m = 0; s = 0; for (i = 0; i < n; i++) { m += v[i]; } s = m / n; m = 0; for (i = 0; i < n; ++i) { m += (v[i] - s) * (v[i] - s); } r = m / (n - 1); } return r;
public virtual DescribeResizeResponse DescribeResize(DescribeResizeRequest request) { var options = new InvokeOptions(); options.RequestMarshaller = DescribeResizeRequestMarshaller.Instance; options.ResponseUnmarshaller = DescribeResizeResponseUnmarshaller.Instance; return Invoke<DescribeResizeResponse>(request, options); }
public virtual bool PassedThroughNonGreedyDecision { get; return; }
}
var ctx = new SimpleCellWalkContext();  int width = 1 + firstColumn - lastColumn;  var range = new CellRange(firstRow, lastRow, firstColumn, lastColumn);  ctx.handler = onCell.handler;  for (int rowNumber = firstRow; rowNumber <= lastRow; rowNumber++)  {      var currentRow = sheet.GetRow(rowNumber);      if (currentRow == null)      {          continue;      }      for (int colNumber = firstColumn; colNumber <= lastColumn; colNumber++)      {          var currentCell = currentRow.GetCell(colNumber);          if (currentCell == null || currentCell.IsEmpty())          {              continue;          }          ctx.CurrentCell = currentCell;          ctx.RowSize = ArithmeticUtils.AddAndCheck(ctx.RowSize, width);          ctx.OrdinalNumber = ArithmeticUtils.MulAndCheck(ctx.OrdinalNumber, rowSize);          ctx.Handler(currentCell);      }  }
};
return other.ScoreTerm.CompareTo(this.ScoreTerm) == 0 ? this.Bytes.CompareTo(other.Bytes) : other.ScoreTerm.CompareTo(this.ScoreTerm);
for (int i = 0; i < len; i++) { switch (s[i]) { case HAMZA_ABOVE: case HEH: case HEH_GOAL: case HEH_YEH: case KAF: case KEHEH: case YEH: case YEH_BARREE: case FARSI_YEH: delete = len; break; } } --i; } }
public void WriteShort(LittleEndianOutput out, short _options) { out.WriteShort(_options); }
public bool ExactOnly { get; } = exactOnly;
public class KeySchemaElement { public KeyType KeyType { get; set; } public string AttributeName { get; set; } public override string ToString() { return $"KeyType: {KeyType}, AttributeName: {AttributeName}"; } }
public GetAssignmentResult ExecuteGetAssignment(GetAssignmentRequest request) { return BeforeClientExecution(request); }
public int FindOffset(AnyObjectId id) { return id != null ? 1 : -1; }
public virtual bool AllGroups { get; } = new GroupingSearch().AllGroups(this);
public synchronized void PutFieldTypes(string dimName, bool v, FieldTypes ft) { if (ft == null) ft = new DimConfig(); fieldTypes.Put(dimName, v, ft); }
for (var i = 0; i < ((ICollection<Character>)keySet).Count; i++) { var c = (Character)iterator.next(); var e = (Cell)c; if (e.cmd >= 0) { size++; } }
public DeleteVoiceConnectorResult DeleteVoiceConnector(DeleteVoiceConnectorRequest request) => ExecuteDeleteVoiceConnector(BeforeClientExecution(request));
public DeleteLifecyclePolicyResult ExecuteDeleteLifecyclePolicy(DeleteLifecyclePolicyRequest request) => BeforeClientExecution(request);
