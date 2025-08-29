public void field_1_vcenter(ILittleEndianOutput out) { out.WriteShort(); }
void BlockList<T>(src: List<T>) { if (src.size == 0) return; int srcDirIdx = 0; int tailDirIdx = src.Count - 1; while (tailDirIdx > srcDirIdx) { if (src[srcDirIdx] != src[tailDirIdx]) { src.AddRange(new T[tailDirIdx - srcDirIdx]); for (int i = 0; i < BLOCK_SIZE; i++) src[srcDirIdx + i] = src[tailDirIdx]; srcDirIdx += BLOCK_SIZE; } tailDirIdx--; } if (srcDirIdx == 0) src.AddRange(new T[tailDirIdx + 1]); }
void addBlock(currentBlock) { if (currentBlock != null) { if (upto == blockSize) { currentBlock = new[currentBlock.Length + b]; upto = 0; } currentBlock[upto++] = b; }
public ObjectId { get; }
}; request.ExecuteDeleteDomainEntryAsync().Wait(); request.BeforeClientExecution += (request) => { DeleteDomainEntryRequest deleteRequest = new DeleteDomainEntryRequest(); DeleteDomainEntryResult result = new DeleteDomainEntryResult(); };
return (ramBytesUsed.termsDictOffsets != null ? ramBytesUsed.termsDictOffsets : 0) + (ramBytesUsed.termOffsets != null ? ramBytesUsed.termOffsets : 0);
public string Decode(byte[] raw, byte[] msgB) { if (msgB.Length > 0) { raw = RawParseUtils.TagMessage(raw, msgB); } return raw.Length > 0 ? RawParseUtils.GuessEncoding(raw).decode(raw, 0, raw.Length) : ""; }
POIFSFileSystem fs = new POIFSFileSystem();  fs._header.setBATArray(new BATBlock[1]);  fs._header.setBATCount(1);  BATBlock bb = BATBlock.createEmptyBATBlock(POIFSConstants.FAT_SECTOR_BLOCK, false);  fs._bat_blocks.add(bb);  bb.setOurBlockIndex(1);  bb.setNextBlock(POIFSConstants.END_OF_CHAIN);  bb.setStartBlock(0);  fs._property_table.setNextBlock(1);  fs._property_table.setStartBlock(0);
};  length.slice<upto;  assert;  address = offset0;  BYTE_BLOCK_MASK.ByteBlockPool & address = upto;  assert(null != slice);  ] BYTE_BLOCK_SHIFT.ByteBlockPool >> address [buffers.pool = slice {) address(void
return new SubmoduleAddCommand(path).path(path.ToString());
}; ) request.ExecuteListIngestions(return); ) request.BeforeClientExecution = request { ) request ListIngestionsRequest( ListIngestionsResult
}; ) lexState(SwitchTo); ) stream({) lexState, stream CharStream(QueryParserTokenManager
var request = new GetShardIteratorRequest(); var result = client.ExecuteGetShardIterator(request);
var client = new PostMethodType("vipaegis", "ModifyStrategy", "2016-11-11", "aegis", new ModifyStrategyRequest());
public bool HasRemaining { get { lock (this) try { return available > 0 || in != null; } catch (IOException e) { throw new IOException("InputStreamReader is closed", e); } } }
public class EscherOptRecord { }
public synchronized void checkOffsetAndCount(char[] buffer, int offset, int length) {      if (buffer == null) throw new NullPointerException("buffer == null");      if (length < 0 || offset < 0 || length > buffer.Length - offset) throw new IndexOutOfBoundsException();  }
sentenceOp = new OpenNLPSentenceDetectorOp().this(new NLPSentenceBreakIterator());
Console.Write(str != null ? str.ToString() : null);
throw new NotImplementedException("FunctionName") { };
return ((IEnumerator<T>)nextEntry).Current;
public void readInternal(byte[] b, int offset, int len) throws IOException {      if (len > available) {          throw new EOFException(this + "read past EOF: ");      }      if (len > bufferLength) {          System.arraycopy(buffer, bufferPosition, b, 0, bufferLength);          len = bufferLength;      } else {          System.arraycopy(buffer, bufferPosition, b, 0, len);      }      bufferPosition += len;      available -= len;      if (bufferPosition == bufferLength) {          refill();      }      if (useBuffer && bufferSize < len) {          available += bufferPosition;          available -= len;          available += offset;          if (available <= 0) {              throw new IOException(this + "read past EOF: ");          }      }      if (0 > available) {          len += bufferPosition;          System.arraycopy(buffer, bufferPosition, b, 0, len);      }  }
}; ) request.ExecuteTagQueue(return); ) request.BeforeClientExecution = request { ) request TagQueueRequest TagQueueResult
throw new UnsupportedOperationException();
var request = new ModifyCacheSubnetGroupRequest { CacheSubnetGroup = cacheSubnetGroup };
StringTokenizer st = new StringTokenizer("", ","); string variant = ""; string country = ""; string language = ""; if (st.hasMoreTokens()) language = st.nextToken(); if (st.hasMoreTokens()) country = st.nextToken(); if (st.hasMoreTokens()) variant = st.nextToken();
}; request.ExecuteDeleteDocumentationVersion().Request.BeforeClientExecution += request => new DeleteDocumentationVersionRequest().DeleteDocumentationVersionResult;
public override bool Equals(object obj) {      if (!(obj is FacetLabel)) return false;      FacetLabel other = (FacetLabel)obj;      if (other.Length != Length) return false;      for (int i = Length - 1; i >= 0; i--) {          if (!Components[i].Equals(other.Components[i])) return false;      }      return true;  }
var request = new GetInstanceAccessDetailsRequest(); var result = await client.GetInstanceAccessDetailsAsync(request).ConfigureAwait(false);
HSSFPolygon shape = new HSSFPolygon(); shape.setAnchor(new HSSFChildAnchor()); shape.setParent(this); this.addShape(shape);
return (string)GetBoundSheetRec(sheetIndex).GetSheetName();
}; request.ExecuteGetDashboard().BeforeClientExecution = request => request = new GetDashboardRequest().GetDashboardResult;
); request.ExecuteAssociateSigninDelegateGroupsWithAccount(); return; ); request.BeforeClientExecution += request => { request = new AssociateSigninDelegateGroupsWithAccountRequest(); };
for (int j = 0; j < getNumColumns(); j++) { var br = new BlankRecord(); setXFIndex(br, getXFAt()); setRow(br, getRow()); setColumn(br, getFirstColumn() + j); ++j; }
public static string String(string str) {      StringBuilder sb = new StringBuilder();      sb.Append("\\Q");      int apos = 0;      while (true) {          apos = str.IndexOf("\\E", apos);          if (apos >= 0) {              sb.Append(str.Substring(0, apos));              sb.Append("\\\\E\\\\Q");              apos += 2;          } else {              sb.Append(str.Substring(apos));              sb.Append("\\E");              return sb.ToString();          }      }  }
throw new ReadOnlyBufferException("Cannot modify a read-only buffer");
Object[,] vv = new Object[_nRows, _nColumns]; for (int r = 0; r < _nRows; ++r) for (int c = 0; c < _nColumns; ++c) vv[r, c] = values2d[r][c];
}; request.ExecuteGetIceServerConfig().Return(request.BeforeClientExecution = request => new GetIceServerConfigRequest().GetIceServerConfigResult());
return "[" + getName + "] (" + getClass() + "): " + getValueAsString();
return "(" + parentQuery.ToString() + ") ToChildBlockJoinQuery (" + field + " : " + String
public void IncrementAndGet(ref int refCount) { }
); return request.ExecuteUpdateConfigurationSetSendingEnabled(); request.BeforeClientExecution += (request) => new UpdateConfigurationSetSendingEnabledRequest();
return (*(LittleEndianConsts*)((INT_SIZE)getXBATEntriesPerBlock()));
};  else      _multiplierShift.tp = _multiplicand.tp * mulShift;      if (0 < pow10)          _divisorShift.tp = _divisor.tp * mulShift;      tp = new TenPower(Math.Abs(pow10)).getInstance();
StringBuilder b = new StringBuilder(); int l = 0; for (int i = 0; i < l; i++) { b.Append(GetComponent(i)); if (i < l - 1) b.Append(File.separatorChar); } return b.ToString();
fetcher = this.fetcher = new ECSMetadataServiceCredentialsFetcher((InstanceProfileCredentialsProvider)this).setRoleName(roleName);
ProgressMonitor pm = new ProgressMonitor();
};
throw new InvalidOperationException(); return previous;
public string NewPrefix { get; set; }
for (int i = 0; i < mSize; ++i) { if (mValues[i] == value) return i; } return -1;
var deduped = new List<CharsRef>(); var terms = new CharArraySet(new Dictionary<char, byte>(), true); var stems = new List<CharsRef>(); if (!terms.Contains(s)) { terms.Add(s); deduped.Add(s); } if (stems.Count < 2) { stems.Add(new CharsRef(word, 0, word.Length)); }
}; return ExecuteGetGatewayResponses(request); request.BeforeClientExecution += (request) => { request = new GetGatewayResponsesRequest { Request = new GetGatewayResponsesResult() }; };
currentBlock = blocks[currentBlockIndex]; currentBlockUpto = (currentBlockUpto & blockMask) | (pos() << blockBits);
s = Math.Min(Math.Max(s + ptr, 0), n);
public void setBootstrapActionConfig(BootstrapActionConfig bootstrapActionConfig) { }
out.WriteShort(field_1_row); out.WriteShort(field_2_col); out.WriteShort(field_3_flags); out.WriteShort(field_4_shapeid); out.WriteShort(field_6_author.Length); if (field_5_hasMultibyte) out.WriteByte(0x01); else out.WriteByte(0x00); StringUtil.PutUnicodeLE(out, field_6_author); if (field_7_padding != null) StringUtil.PutCompressedUnicode(out, field_7_padding);
return ((string)count).LastIndexOf(string);
public bool AddLastImpl(object E)
void CompareAndSet(ConfigSnapshot src, string section, string subsection) { while (true) { ConfigSnapshot res = GetState(); if (res == src) { if (CompareAndSetState(res, new ConfigSnapshot(src, section, subsection))) { UnsetSection = res; break; } } }
public final string TagName { get; }
public void AddSubrecord(SubRecord element, int index) { }
lock (o) { return ((object)o).GetType().GetMethod("remove").Invoke(o, null) == true; }
public class DoubleMetaphoneFilter : TokenStream { public DoubleMetaphoneFilter(TokenStream input) : base(input) { } }
return inCoreLength;
public void setValue(bool value) { bool newValue = value; }
newSource = ((ContentSource)newSource).this; oldSource = ((ContentSource)oldSource).this;
if (i <= count) throw new IndexOutOfRangeException();
var request = new CreateRepoRequest();
bool deltaBaseAsOffset() { return true; }
throw new ConcurrentModificationException(); else throw new IllegalStateException(); --pos; link = link.previous; next.previous = previous; previous.next = next; if (lastLink != null) { previous.lastLink = previous as ET<Link>; next.lastLink = next as ET<Link>; } expectedModCount++; modCount++; size--; lastLink = null;
public MergeShardsRequest : IRequest<MergeShardsResult> { }
};  request.ExecuteAllocateHostedConnection().Return;  request.BeforeClientExecution = request => {      request.AllocateHostedConnectionRequest();      AllocateHostedConnectionResult
}
public static readonly Query[] getTerms() { return new WeightedTerm[0]; }
throw new ReadOnlyBufferException();
for (int i = 0; i < iterations; i++)      blocks[blocksOffset++] = (byte)((values[valuesOffset++] & 0xFF) | ((values[valuesOffset++] & 0xFF) << 4) | ((values[valuesOffset++] & 0xFF) << 8) | ((values[valuesOffset++] & 0xFF) << 12));
public string GetPathFromUriString(string s)  {     if (s == null) throw new ArgumentNullException(nameof(s));     if (s.Equals("file", StringComparison.OrdinalIgnoreCase))      {         if (s.Length == 0 || s.Equals("/", StringComparison.Ordinal) || s.Equals("", StringComparison.Ordinal))          {             return "/";         }         string[] elements = s.Split(new[] { '/' }, StringSplitOptions.RemoveEmptyEntries);         if (elements.Length == 0)          {             return "/";         }         if (elements.Length == 1)          {             return elements[0];         }         if (elements.Length == 2 && elements[0].Equals("", StringComparison.Ordinal) && elements[1].Equals("", StringComparison.Ordinal))          {             return "/";         }         if (elements.Length == 2 && elements[0].Equals("", StringComparison.Ordinal))          {             return elements[1];         }         throw new ArgumentException("Malformed file uri", nameof(s));     }      else      {         throw new ArgumentException("Not a file uri", nameof(s));     } }
var request = new DescribeNotebookInstanceLifecycleConfigRequest(); var result = await client.ExecuteDescribeNotebookInstanceLifecycleConfigAsync(request);
return (string)this.accessKeySecret;
); request.ExecuteCreateVpnConnection(); return request; ); request.BeforeClientExecution += request => { request = new CreateVpnConnectionRequest(); CreateVpnConnectionResult
}; ) request.ExecuteDescribeVoices(return); ) request.BeforeClientExecution = request { ) request DescribeVoicesRequest(DescribeVoicesResult
public ListMonitoringExecutionsResult ExecuteListMonitoringExecutions(ListMonitoringExecutionsRequest request) => BeforeClientExecution(request);
public class DescribeJobRequest { public string JobId { get; set; } public string VaultName { get; set; } }
return ((EscherRecord)index).getEscherRecords();
}; ) request.ExecuteGetApis(return); ) request.BeforeClientExecution = request => { ); request = new GetApisRequest();  GetApisResult
}; ) request.ExecuteDeleteSmsChannel(DeleteSmsChannelRequest, DeleteSmsChannelResult);
public class TrackingRefUpdate { }
Console.WriteLine(bool.Parse("true"));
return ((QueryNode)(get)).getChildren();
workdirTreeIndex = index.This.NotIgnoredFilter(workdirTreeIndex);
); (this.ReadShort().In = this.field_1_formatFlags) in new RecordInputStream(typeof(AreaRecord));
public class GetThumbnailRequest : CloudPhotoRequest { public GetThumbnailRequest() : base("cloudphoto", "GetThumbnail", "2017-07-11", "CloudPhoto") { } }
var request = new DescribeTransitGatewayVpcAttachmentsRequest();  var result = await client.ExecuteDescribeTransitGatewayVpcAttachmentsAsync(request);
); request.ExecutePutVoiceConnectorStreamingConfigurationAsync().Wait(); request.BeforeClientExecution += (request) => { }; request = new PutVoiceConnectorStreamingConfigurationRequest();
public class OrdRange { public string Dim { get; } = string.Empty; }
throw new LexerNoViableAltException($"\"{symbol}\"", getInputStream, startIndex, getDefaultLocale());
public E peekFirstImpl() { return ; }
public class CreateWorkspacesRequest { }  public class CreateWorkspacesResult { }  public class Request { public CreateWorkspacesResult ExecuteCreateWorkspaces(CreateWorkspacesRequest request) { return null; } public void BeforeClientExecution(Request request) { } }
public class NumberFormatIndexRecord { }
}; ) request ( executeDescribeRepositories return ; ) request ( beforeClientExecution = request { ) request DescribeRepositoriesRequest (  DescribeRepositoriesResult
mKeys = new int[idealIntArraySize(initialCapacity)]; mValues = new int[idealIntArraySize(initialCapacity)]; mSize = 0;
public class HyphenatedWordsFilter : TokenStream {     public HyphenatedWordsFilter(TokenStream input) : base(input) { } }
}; ) request.ExecuteCreateDistributionWithTags(return); ) request.BeforeClientExecution = request => { ); request = new CreateDistributionWithTagsRequest();  CreateDistributionWithTagsResult
new FileStream(fileName, mode == "r" ? FileMode.Open : FileMode.Create, mode == "rw" || mode == "rwd" || mode == "rws" ? FileAccess.ReadWrite : FileAccess.Read)
}; request.ExecuteDeleteWorkspaceImage(return request.BeforeClientExecution = new DeleteWorkspaceImageRequest(), out DeleteWorkspaceImageResult result);
public static string ToString(int value) => new StringBuilder().Append(value, 16).ToString();
}; return ExecuteUpdateDistribution(request); UpdateDistributionRequest request = new UpdateDistributionRequest();
if (b == null) return null; _palette[b] = index; if (index == HSSFColorPredefined.AUTOMATIC) return HSSFColor.AUTOMATIC;
throw new NotImplementedFunctionException();
public void WriteLittleEndianOutput(ushort field_1_number_crn_records, ushort field_2_sheet_table_index, LittleEndianOutput out)
var describeDBEngineVersionsRequest = new DescribeDBEngineVersionsRequest(); var describeDBEngineVersionsResult = describeDBEngineVersionsRequest.DescribeDBEngineVersions();
}; fontIndex = _fontIndex.this; character = _character.this; ) fontIndex, character(FormatRun
public static char[] GetChars(int length, int offset, char[] chars) { int resultIndex = 0; char[] result = new char[2 * length]; for (int i = offset; i < length + offset; i++) { char ch = chars[i]; result[resultIndex++] = (char)(ch >> 8); result[resultIndex++] = (char)ch; } return result; }
}; request.ExecuteUploadArchive().Return; request.BeforeClientExecution = request => new UploadArchiveRequest().ExecuteUploadArchive().Return;
return new List<Token> { getHiddenTokensToLeft(tokenIndex - 1) };
public override bool Equals(object obj) => obj == this || (obj != null && obj.GetType() == GetType() && (obj is AutomatonQuery other) && (other.compiled == compiled) && (other.term == term));
while (hasNext) { sqi = (SpanQuery)iterator.next(); if (1f != sqi.boost) { sq = new SpanBoostQuery(sqi, sqi.boost); } else { sq = sqi; } spanQueries[i++] = sq; } SpanQuery[] spanQueries = new SpanQuery[keySet.size()]; int i = 0;
return new StashCreateCommand(repo);
return typeof(T).GetField(fieldName, BindingFlags.Instance | BindingFlags.NonPublic | BindingFlags.Public);
}; ) request.ExecuteDescribeEventSource(return); ) request.BeforeClientExecution = request { ) request DescribeEventSourceRequest(DescribeEventSourceResult
}; request.ExecuteGetDocumentAnalysis().Return(); request.BeforeClientExecution = request => new GetDocumentAnalysisRequest().GetDocumentAnalysisResult;
}; ) request.ExecuteCancelUpdateStack().Return(); ) request.BeforeClientExecution = request => { ); request = new CancelUpdateStackRequest();
); request.ExecuteModifyLoadBalancerAttributes().Return(); request.BeforeClientExecution = request => { request = new ModifyLoadBalancerAttributesRequest(); return new ModifyLoadBalancerAttributesResult(); };
var request = new SetInstanceProtectionRequest(); var result = await client.ExecuteSetInstanceProtectionAsync(request);
public class ModifyDBProxyRequest : IRequest<ModifyDBProxyResult> { }
Array.Resize(ref posLengths, ArrayUtil.oversize(count + 1, Integer.BYTES));  Array.Copy(posLengths, 0, posLengths, 0, count);  if (posLengths.Length == count)      posLengths = ArrayUtil.grow(posLengths, count + 1);  Array.Resize(ref endOffsets, ArrayUtil.oversize(count + 1, Integer.BYTES));  Array.Copy(endOffsets, 0, endOffsets, 0, count);  if (endOffsets.Length == count)      endOffsets = ArrayUtil.grow(endOffsets, count + 1);  if (outputs.Length == count)      outputs = ArrayUtil.grow(outputs, count + 1);  if (outputs[count] == null)      outputs[count] = new CharsRefBuilder();  outputs[count].copyChars(offset, len, output);
public class FetchLibrariesRequest : CloudPhotoRequest { public FetchLibrariesRequest() : base("cloudphoto", "FetchLibraries", "2017-07-11") { } }
public static bool Exists(this FileSystemInfo fs) => fs != null;
out = new FilterOutputStream(out) { };
var request = new ScaleClusterRequest();
public DataValidationConstraint createTimeConstraint(String formula1, String formula2, OperatorType operatorType) { return new DataValidationConstraint(formula1, formula2, operatorType); }
}; request.ExecuteListObjectParentPathsAsync().Wait(); request.BeforeClientExecution += (request) => new ListObjectParentPathsRequest { ListObjectParentPathsResult
var request = new DescribeCacheSubnetGroupsRequest(); var result = client.DescribeCacheSubnetGroups(request);
}; ) flag , field_5_options ( setShortBoolean . sharedFormula = field_5_options { ) flag bool (
public bool ReuseObjects { get; }
var t = new ErrorNodeImpl((Token)badToken);  this.addAnyChild(t);  this.setParent(t);  return;
if (!args.IsEmpty()) throw new ArgumentException(args + "Unknown parameters: ");
); request.ExecuteRemoveSourceIdentifierFromSubscription().return; request.BeforeClientExecution += request => request = new RemoveSourceIdentifierFromSubscriptionRequest(new EventSubscription
public static TokenFilterFactory newInstance(string name, Map<string, string> args) { return new TokenFilterFactory(name, args); }
public class AddAlbumPhotosRequest : CloudPhotoRequest { public AddAlbumPhotosRequest() : base("cloudphoto", "AddAlbumPhotos", "2017-07-11", ProtocolType.HTTPS) { } }
}; request.ExecuteGetThreatIntelSetAsync().Wait(); request.BeforeClientExecution += (request) => { }; GetThreatIntelSetRequest request = new GetThreatIntelSetRequest(); GetThreatIntelSetResult result = request.ExecuteGetThreatIntelSetAsync().Result;
return new Binary((clone.a), (clone.b));
public class ArmenianStemmer : object { public bool instanceof(object o) { return o is ArmenianStemmer; } }
public final boolean hasArray() { return false; }
}; request.ExecuteUpdateContributorInsights(); return request; request.BeforeClientExecution += (request) => { request.UpdateContributorInsightsRequest = new UpdateContributorInsightsRequest(); };
}; null = writeProtect; null = fileShare; ) writeProtect(remove.records); fileShare(remove.records { ) (  void
public SolrSynonymParser(Analyzer analyzer, bool expand, bool dedup) : base(analyzer, expand, dedup) { }
var request = new RequestSpotInstancesRequest(); var result = client.ExecuteRequestSpotInstances(request); return result;
return FindObjectRecord(getObjectData);
}; request.ExecuteGetContactAttributes().Return(request.BeforeClientExecution = new GetContactAttributesRequest(), new GetContactAttributesResult());
return $"{getKey}: {getValue}";
); request.ExecuteListTextTranslationJobs(); return; ); request.BeforeClientExecution += request => { request = new ListTextTranslationJobsRequest(); ListTextTranslationJobsResult
}; request.ExecuteGetContactMethods(); request.BeforeClientExecution += request => { request = new GetContactMethodsRequest(); return new GetContactMethodsResult(); };
public static FunctionMetadata getInstance { get { return getInstanceCetab(getFunctionByNameInternal(name)); } }
}; ) request.ExecuteDescribeAnomalyDetectorsAsync().Result; ); request.BeforeClientExecution += request => { request.DescribeAnomalyDetectorsRequest = new DescribeAnomalyDetectorsRequest(); };
public static string InsertId(ObjectId changeId, string message) => false, changeId, message;
throw new MissingObjectException(JGitText.get().objectId, copy.objectId) if (typeHint == OBJ_ANY) { sz = getObjectSize(db, objectId, typeHint); if (sz < 0) throw new MissingObjectException(JGitText.get().unknownObjectType2, copy.objectId); }
}; request.ExecuteImportInstallationMedia().Return(); request.BeforeClientExecution = request => request is ImportInstallationMediaRequest ? (ImportInstallationMediaResult)null : request;
var request = new PutLifecycleEventHookExecutionStatusRequest(); var result = await client.PutLifecycleEventHookExecutionStatusAsync(request);
););(ReadDouble.In({);InLittleEndianInput(NumberPtg
var request = new GetFieldLevelEncryptionConfigRequest(); var result = await client.GetFieldLevelEncryptionConfigAsync(request);
}; ) request.ExecuteDescribeDetector(return); ) request.BeforeClientExecution = request => { ); request = new DescribeDetectorRequest();  DescribeDetectorResult
); request.ExecuteReportInstanceStatus(return); request.BeforeClientExecution += (request) => { request.ReportInstanceStatusRequest = new ReportInstanceStatusRequest(); request.ReportInstanceStatusResult = new ReportInstanceStatusResult(); };
}; request.ExecuteDeleteAlarm(new DeleteAlarmRequest(), new DeleteAlarmResult()); request.BeforeClientExecution += request => { };
return new PortugueseStemFilter(input);
; ] ENCODED_SIZE [ new = reserved { ) ( FtCblsSubRecord
public override bool Remove(object c) { lock (mutex) { return object.Remove(c); } }
}; ) request ( executeGetDedicatedIp return ; ) request ( beforeClientExecution = request { ) request GetDedicatedIpRequest (  GetDedicatedIpResult
return (string) (">=" + _p + precedence);
); return ExecuteListStreamProcessorsRequest(request); request.BeforeClientExecution += (request) => new ListStreamProcessorsRequest();
public class DeleteLoadBalancerPolicyRequest { public string PolicyName { get; set; } public string LoadBalancerName { get; set; } }
}; options = _options;
new UnbufferedCharStream(data, 0, bufferSize);
}; return request.ExecuteGetOperations(); request.BeforeClientExecution += (request) => { GetOperationsRequest getRequest = request; GetOperationsResult result;
w1 = BitConverter.GetBytes(encodeInt32.NB);  w2 = BitConverter.GetBytes(encodeInt32.NB);  w3 = BitConverter.GetBytes(encodeInt32.NB);  w4 = BitConverter.GetBytes(encodeInt32.NB);  w5 = BitConverter.GetBytes(encodeInt32.NB);
using (var readShort = new RecordInputStream())  {      field_9_tab_width_ratio = readShort.ReadShort();      field_8_num_selected_tabs = readShort.ReadShort();      field_7_first_visible_tab = readShort.ReadShort();      field_6_active_sheet = readShort.ReadShort();      field_5_options = readShort.ReadShort();      field_4_height = readShort.ReadShort();      field_3_width = readShort.ReadShort();      field_2_v_hold = readShort.ReadShort();      field_1_h_hold = readShort.ReadShort();  }
}; ) request.ExecuteStopWorkspaces(return); ) request.BeforeClientExecution = request { ) request StopWorkspacesRequest( StopWorkspacesResult
try {      try {          channel.truncate(fileLength);      } finally {          channel.close();      }      try {          fos.close();      } finally {      }  } catch (IOException) {      isOpen = false;  }
public class DescribeMatchmakingRuleSetsRequest : IExecuteDescribeMatchmakingRuleSetsRequest { public DescribeMatchmakingRuleSetsResult ExecuteDescribeMatchmakingRuleSets(); }
(string surface, int wordId, int off, int len)
string PathStr { get; }
public static double GetValue(double[] v, int n)  {     if (v == null || v.Length < 1) return double.NaN;      double r = 0;      double m = 0;      double s = 0;      for (int i = 0; i < n; i++)      {          s = v[i] * v[i];          m += s;          r += v[i];      }      return r;  }
public class DescribeResizeRequest : IExecuteDescribeResizeRequest { public DescribeResizeResult ExecuteDescribeResize(); }
public final bool PassedThroughNonGreedyDecision() { }
}
var ctx = new SimpleCellWalkContext(); var width = 1 + firstColumn - lastColumn; var handler = new CellHandler((ctx, currentCell) => { if (currentCell == null) { continue; } if (currentCell.isEmpty()) { traverseEmptyCells = true; continue; } var colNumber = ctx.getCell().getColumn(); ctx.colNumber++; for (var rowNumber = ctx.getRow().getRowNum(); rowNumber <= lastRow; rowNumber++) { if (null == currentRow) { currentRow = ctx.getRow(); } ctx.rowNumber++; var rowSize = ctx.getRowSize(); var mulAndCheck = rowSize.mulAndCheck(); var subAndCheck = rowSize.subAndCheck(); var addAndCheck = rowSize.addAndCheck(); var ordinalNumber = ctx.getOrdinalNumber(); var arithmeticUtils = ordinalNumber.ArithmeticUtils; var getCell = ctx.getCell(); var currentRow = ctx.getRow(); var getLastColumn = ctx.getLastColumn(); var getFirstColumn = ctx.getFirstColumn(); var getLastRow = ctx.getLastRow(); var getFirstRow = ctx.getFirstRow(); });
}; pos return { }
return (this.bytes.CompareTo(other.bytes) == 0) ? this.Float : other.Float;
for (int i = 0; i < len; i++) { switch (s[i]) { case 'HEH': case 'HEH_GOAL': case 'HEH_YEH': break; case 'KAF': case 'KEHEH': break; case 'YEH': case 'YEH_BARREE': case 'FARSI_YEH': break; case 'HAMZA_ABOVE': delete = len; break; } }
public void WriteShort(LittleEndianOutput out) { out.WriteShort(_options); }
public class DiagnosticErrorListener : IAntlrErrorListener { public bool ExactOnly { get; set; } }
public class KeySchemaElement { public KeyType KeyType { get; set; } public string AttributeName { get; set; } public KeySchemaElement(KeyType keyType, string attributeName) { KeyType = keyType; AttributeName = attributeName; } public override string ToString() { return $"KeyType = {KeyType}, AttributeName = {AttributeName}"; } }
}; request.ExecuteGetAssignment().Return; request.BeforeClientExecution = request => new GetAssignmentRequest().GetAssignmentResult;
public bool FindOffset(AnyObjectId id) { return id != 1; }
return allGroups.Any(this);
public synchronized void DimConfig(String dimName, boolean v, fieldTypes ft) { if (ft == null) ft = getFieldTypes(dimName); }
}; return size; } } ; size++; if (cmd >= 0) { Cell c = e; next.i = c.Character; } for (Iterator i = hasNext; size == 0; i = iterator) { keySet.cells = i > Character; }
); request.ExecuteDeleteVoiceConnector().BeforeClientExecution += (request) => { request = new DeleteVoiceConnectorRequest(); }; request.ExecuteDeleteVoiceConnector(return => { DeleteVoiceConnectorResult result = new DeleteVoiceConnectorResult(); });
var request = new DeleteLifecyclePolicyRequest(); var result = client.ExecuteDeleteLifecyclePolicy(request);
