public void writeShort(int field_1_vcenter, LittleEndianOutput out) { }
if (srcDirIdx < 0) return; for (; srcDirIdx >= 0; ++srcDirIdx) if (src[srcDirIdx] != 0) { tailDirIdx = srcDirIdx; BlockList.AddAll(0, src[tailDirIdx].tailBlkIdx.src, BLOCK_SIZE, 0, src[tailDirIdx].src.tailBlock.src); }
if (b) {      if (blockSize == upto) {          upto = 0;          currentBlock = new int[blockSize];      }      if (currentBlock != null) {          currentBlock[upto++] = addBlock;      }  }
return ObjectId;
return ExecuteDeleteDomainEntry(request).BeforeClientExecution(request);
return (null != termsDictOffsets) ? ramBytesUsed.termsDictOffsets : 0 + (null != termOffsets) ? ramBytesUsed.termOffsets : 0;
public static string Decode(RawParseUtils utils, byte[] raw, int msgB) { if (msgB > 0) return utils.GuessEncoding(0, raw, msgB); else return ""; }
var bb = new BATBlock(bigBlockSize, POIFSConstants.END_OF_CHAIN, POIFSConstants.FAT_SECTOR_BLOCK, false);  POIFSFileSystem.setOurBlockIndex(bb);  POIFSFileSystem._bat_blocks.Add(bb);  POIFSFileSystem._property_table.setNextBlock(0);  POIFSFileSystem._property_table.setNextBlock(1);  POIFSFileSystem._header.setBATCount(1);  POIFSFileSystem._header.setBATArray(new[] { 0 });  POIFSFileSystem.setStartBlock(bb);
var address = 0; var offset0 = 0; var upto = 0; var slice = null; assert slice != null; assert length > 0; upto = slice.Length; address = offset0; assert address < upto; address = (address & ByteBlockPool.BYTE_BLOCK_MASK) >> ByteBlockPool.BYTE_BLOCK_SHIFT; var buffers = ByteBlockPool.pool;
return this as SubmoduleAddCommand { string path = this.path; }
return client.Execute<ListIngestionsRequest, ListIngestionsResult>(request, beforeClientExecution);
public class QueryParserTokenManager
return client.ExecuteGetShardIterator(request).BeforeClientExecution(request) as GetShardIteratorResult;
var request = new ModifyStrategyRequest();  var client = new Client("vipaegis", "ModifyStrategy", "2016-11-11", "aegis"); client.MethodType = MethodType.POST;
lock (this) { try { if (in == null) throw new IOException("InputStreamReader is closed"); return in.Available() > 0; } catch (IOException e) { return false; } }
return EscherOptRecord _optRecord;
public synchronized int copyTo(char[] buffer, int offset, int count) {      if (buffer == null) throw new NullPointerException("buffer == null");      if (count < 0) throw new ArgumentException("count < 0");      if (offset < 0 || offset + count > buffer.Length) throw new ArgumentException("offset < 0 || offset + count > buffer.Length");      int pos = 0;      int copylen = 0;      for (int i = 0; i < count; i++) {          if (copylen >= length) return copylen;          buffer[pos + offset + i] = charAt(copylen);          copylen++;      }      return copylen;  }
OpenNLPSentenceBreakIterator sentenceOp = new NLPSentenceDetectorOp(sentenceOp);
public static void Write(string str) { if (str != null) { object value = str; } }
throw new NotImplementedException(functionName) : base(functionName) { }
return ((V)nextEntry).getValue();
public void ReadInternal(byte[] b, int offset, int len) throws IOException  {     if (useBuffer)      {         if (available <= len)          {             if (bufferPosition + len > bufferLength)              {                 if (len > bufferSize)                  {                     System.arraycopy(buffer, bufferPosition, b, offset, available);                     len -= available;                     offset += available;                     available = 0;                     bufferPosition = bufferLength;                     System.arraycopy(b, offset, buffer, 0, len);                     bufferPosition = len;                     bufferLength = len;                     available = 0;                 }                  else                  {                     System.arraycopy(buffer, bufferPosition, b, offset, available);                     offset += available;                     len -= available;                     available = 0;                     bufferPosition = bufferLength;                     refill(len);                     if (len > available)                      {                         System.arraycopy(buffer, 0, b, offset, available);                         len -= available;                         offset += available;                         available = 0;                         throw new EOFException("read past EOF: " + this);                     }                      else                      {                         System.arraycopy(buffer, 0, b, offset, len);                         available -= len;                         offset += len;                     }                 }             }              else              {                 System.arraycopy(buffer, bufferPosition, b, offset, len);                 bufferPosition += len;                 available -= len;             }         }          else          {             System.arraycopy(b, 0, buffer, bufferPosition, len);             bufferPosition += len;         }     }      else      {         if (len > available)          {             throw new EOFException("read past EOF: " + this);         }         System.arraycopy(b, offset, buffer, 0, len);         available -= len;     } }
return ExecuteTagQueue((TagQueueRequest)request).BeforeClientExecution(request);
throw new UnsupportedOperationException();
return client.BeforeClientExecution(request).Execute(ModifyCacheSubnetGroupRequest, request);
string[] parts = parameters.Split(',');  string language = parts[0];  string country = parts.Length > 1 ? parts[1] : "";  string variant = parts.Length > 2 ? parts[2] : "";
return client.Execute(request, (request) => { }, (request) => new DeleteDocumentationVersionRequest(), new DeleteDocumentationVersionResult());
public bool Equals(object obj) {      if (obj is FacetLabel other) {          if (other.components.Length != components.Length) return false;          for (int i = components.Length - 1; i >= 0; i--) {              if (components[i] != other.components[i]) return false;          }          return true;      }      return false;  }
return client.ExecuteGetInstanceAccessDetails(request).BeforeClientExecution(request).GetInstanceAccessDetailsResult;
return new HSSFPolygon(anchor, this).setParent(shape).setAnchor(anchor).setShapes(shape).add(shape);
return getBoundSheetRec().getSheetName(sheetIndex);
return ExecuteGetDashboard(request, beforeClientExecution: request);
return ExecuteAssociateSigninDelegateGroupsWithAccount(request).BeforeClientExecution(request) as AssociateSigninDelegateGroupsWithAccountResult;
for (int j = 0; j < mbr.getNumColumns(); j++) {      BlankRecord br = new BlankRecord();      br.setRow(mbr.getRow());      br.setColumn(j + mbr.getFirstColumn());      br.setXFIndex(mbr.getXFAt(j));      insertCell(br);  }
public static string toString(String string) {      StringBuilder sb = new StringBuilder();      int apos = 0;      while (true) {          int k = string.indexOf("\\E", apos);          if (k < 0) {              sb.append(string.substring(apos));              break;          }          sb.append(string.substring(apos, k));          sb.append("\\\\E\\Q");          apos = k + 2;      }      sb.append("\\E");      return sb.toString();  }
throw new ReadOnlyBufferException();
for (int r = 0; r < _nRows; r++) { Object[] rowData = new Object[_nColumns]; for (int c = 0; c < _nColumns; c++) { rowData[c] = ((ArrayPtg)vv).getValueIndex(r, c); } values2d[r] = rowData; }
return ExecuteRequest<GetIceServerConfigRequest, GetIceServerConfigResult>(request, beforeClientExecution);
return "[" + GetType().Name + "] " + GetValueAsString();
return $"ToChildBlockJoinQuery({parentQuery.ToString()})".ToString();
public void incrementAndGet(refCount) { }
return client.Execute(request).BeforeClientExecution(request).ExecuteUpdateConfigurationSetSendingEnabled(request) as UpdateConfigurationSetSendingEnabledResult;
return (int)(LittleEndianConsts.XBATEntriesPerBlock * Constants.INT_SIZE);
var tp = TenPower.getInstance()     .mulShift(_divisor, _divisorShift)     .mulShift(_multiplicand, _multiplierShift)     .abs(); var pow10 = tp < 0 ? 0 : tp; if (pow10 > 0)      tp = pow10;
string result = ""; for (int i = 0; i < length; i++) { result += getComponent(i).toString() + File.separatorChar; } return result.Substring(0, result.Length - 1);
this.fetcher = new ECSMetadataServiceCredentialsFetcher(roleName).setRoleName(roleName).returnInstanceProfileCredentialsProvider(this);
ProgressMonitor pm = progressMonitor;
if (ptr == 0) { if (!parseEntry()) { ; } } else { ; }
throw new InvalidOperationException(); return start >= previousIndex;
return new string(this.newPrefix);
for (int i = 0; i < mSize; i++) { if (mValues[i] == value) { return i; } } return -1;
var deduped = new List<CharsRef>(); var stems = new List<CharsRef>(); var terms = new CharArraySet(ignoreCase.dictionary, 8); for (int i = 0; i < stems.Count; i++) { var s = stems[i]; if (!terms.Contains(s)) { terms.Add(s); deduped.Add(s); } } return deduped;
return client.Execute<GetGatewayResponsesRequest, GetGatewayResponsesResult>(request, beforeClientExecution);
pos = currentBlockUpto = currentBlock = currentBlockIndex = (blockMask & (pos >> blockBits)) & 0xFFFFFFFF;
return s += (int)Math.Min(Math.Max(n, 0), available) - (ptr = s);
public BootstrapActionDetail(BootstrapActionConfig bootstrapActionConfig) { this.BootstrapActionConfig = bootstrapActionConfig; }
using System; using LittleEndianOutput;  public class Translation  {     public void Translate(         int field_1_row,          int field_2_col,          int field_3_flags,          int field_4_shapeid,          bool field_5_hasMultibyte,          string field_6_author,          int field_7_padding,          LittleEndianOutput out     )      {         out.writeByte(field_1_row);         out.writeShort(field_2_col);         out.writeShort(field_3_flags);         out.writeShort(field_4_shapeid);         out.writeShort(field_5_hasMultibyte ? 0x01 : 0x00);         out.writeShort(field_6_author.Length);         if (field_6_author != null)          {             StringUtil.putCompressedUnicode(field_6_author, out);         }         if (field_7_padding != null)          {             out.writeByte(field_7_padding.intValue());         }     } }
return ((string)count).LastIndexOf((string)string);
public bool AddLastImpl(object E) { return false; }
public void UnsetSection(string section, string subsection, ConfigSnapshot src) { while (true) { ConfigSnapshot res; if ((res = src).State.CompareAndSet(res.State, src.UnsetSection(section, subsection))) break; } }
public string TagName { get; }
public void AddSubRecord(int index, SubRecord element) { }
public synchronized bool Remove(object o) { return Remove((object)delegate { }); }
return new DoubleMetaphoneFilter(input, inject, maxCodeLength);
return inCoreLength;
bool newValue = value;
newSource = oldSource = new ContentSource(newSource, oldSource, this);
if (i <= count) throw new IndexOutOfRangeException(); return entries[i];
var request = new CreateRepoRequest(MethodType.PUT, "/repos", "cr", "CreateRepo", "2016-06-07", "cr");
bool deltaBaseAsOffset() { }
// Presumed Java code (for context, not directly translated) if (modCount != expectedModCount) {     throw new ConcurrentModificationException(); } if (lastLink == null) {     throw new IllegalStateException(); } Link previous = lastLink.previous; Link next = lastLink.next; if (previous != null) {     previous.next = next; } if (next != null) {     next.previous = previous; } --pos; ++expectedModCount; lastLink = null;
return ExecuteMergeShards(request);
return ExecuteAllocateHostedConnection(request).BeforeClientExecution(request);
}
public static readonly Query getTerms = new WeightedTerm(false);
throw new ReadOnlyBufferException();
for (int i = 0; i < iterations; i++) {      byte byte0 = (byte)(values[valuesOffset++] & 0xFF);      byte byte1 = (byte)(values[valuesOffset++] & 0xFF);      byte byte2 = (byte)(values[valuesOffset++] & 0xFF);      blocks[blocksOffset++] = (byte)((byte2 >> 6) & 0x3F);      blocks[blocksOffset++] = (byte)(((byte2 & 0x0F) << 4) | ((byte1 >> 4) & 0x0F));      blocks[blocksOffset++] = (byte)(((byte1 & 0x0F) << 2) | ((byte0 >> 6) & 0x03));      blocks[blocksOffset++] = (byte)(byte0 & 0x3F);  }
throw new String();  if (result == null) throw new IllegalArgumentException("result");  else if (s == null) throw new IllegalArgumentException("s");  if (s.equals("")) return result;  if (result.endsWith(DOT_GIT)) return result;  String[] elements = s.split("/+");  if (elements.length == 0 || elements[0].equals("")) throw new IllegalArgumentException("elements");  if (elements[elements.length - 1].matches(".*\\"+File.separatorChar+".*")) return result;  if (elements[elements.length - 1].equals("file")) return result.substring(0, result.length() - 1);  if (getHost().equals("") && getPath().equals("/")) return result;  if (scheme == LOCAL_FILE) return s;  return s.replaceFirst("\\[" + DOT_GIT_EXT + "\\]" + separatorChar, "");
return client.Execute(request, DescribeNotebookInstanceLifecycleConfigRequest, DescribeNotebookInstanceLifecycleConfigResult, beforeClientExecution);
return this.accessKeySecret;
return ExecuteCreateVpnConnection(request).BeforeClientExecution(request);
return client.BeforeClientExecution(new DescribeVoicesRequest(), new DescribeVoicesResult());
return client.Execute<ListMonitoringExecutionsRequest, ListMonitoringExecutionsResult>(request, beforeClientExecution);
public class DescribeJobRequest  {     public string JobId { get; set; }     public string VaultName { get; set; }     public DescribeJobRequest() { }     public DescribeJobRequest(string jobId, string vaultName)      {         JobId = jobId;         VaultName = vaultName;     } }
return ((EscherRecord)escherRecords[index]).getIndex();
return Execute<GetApisRequest, GetApisResult>(request, beforeClientExecution);
return client.Execute(request, (request) => request.BeforeClientExecution(), typeof(DeleteSmsChannelRequest), typeof(DeleteSmsChannelResult));
return new TrackingRefUpdate();
Console.WriteLine(bool.Parse("true"));
return getChildren().get(0);
var workdirTreeIndex = this.workdirTreeIndex.Index.NotIgnoredFilter();
ushort field_1_formatFlags = (ushort)recordInputStream.ReadShort();
using Aliyun.Acs.Core;   public class GetThumbnailRequest : RpcAcsRequest<GetThumbnailResponse> {     public GetThumbnailRequest() : base("CloudPhoto", "2017-07-11", "GetThumbnail", "cloudphoto")     {         Protocol = ProtocolType.HTTPS;     } }
return client.Execute(request).BeforeClientExecution(request).DescribeTransitGatewayVpcAttachments();
return ExecutePutVoiceConnectorStreamingConfiguration(request).BeforeClientExecution(request);
public OrdRange GetPrefixToOrdRange(string dim) { return dim; }
return string.Format("%s('%s')", symbol == "" ? "" : symbol, symbol.GetType().Name, Locale.Default);
return ((ICollection<T>)this).PeekFirstImpl();
return ExecuteCreateWorkspaces(request, (request) => request.BeforeClientExecution(), (request) => new CreateWorkspacesResult());
return (NumberFormatIndexRecord)copy;
return ExecuteDescribeRepositories(request, (request) => this.BeforeClientExecution(request));
var mSize = 0; var mKeys = new int[initialCapacity]; var mValues = new int[initialCapacity];
return new HyphenatedWordsFilter(input);
return ExecuteCreateDistributionWithTags(request, (request) => { });
using System.IO;  public class Program {      public static void Main() {          FileStream file = new FileStream(fileName, mode);          // OR          using (FileStream fileStream = new FileStream(fileName, mode)) { }          // OR          File.Open(fileName, mode);      }  }
return ExecuteDeleteWorkspaceImage(request).BeforeClientExecution(request);
public static string ToString(int value) => new StringBuilder().Append(value.ToString("X16")).ToString();
return ExecuteUpdateDistribution(request, beforeClientExecution);
return index == null ? null : _palette.GetColor(index) ?? new CustomColor((HSSFColor)index);
throw new NotImplementedFunctionException(_functionName, srcRow, srcCol, operands) : ValueEval;
public void WriteLittleEndianOutput(BinaryWriter out)  {     out.WriteShort(field_1_number_crn_records);     out.WriteShort(field_2_sheet_table_index); }
public DescribeDBEngineVersionsResult DescribeDBEngineVersions(DescribeDBEngineVersionsRequest request)
{ FormatRun ; ; ) , ( fontIndex = character = fontIndex character _fontIndex . this _character . this
public static char[] decode(char[] chars, int offset, int length) {      int resultIndex = 0, end = (offset + length) - 1;      int i = offset;      int resultLen = 0;      for (; i <= end; i++) {          int ch = chars[i];          resultLen += (ch & 0xFF) >> 8;          resultLen++;      }      char[] result = new char[resultLen];      i = offset;      resultIndex = 0;      for (; i <= end; i++) {          int ch = chars[i];          result[resultIndex++] = (char) (ch & 0xFF);          if ((ch & 0xFF) >> 8 != 0)              result[resultIndex++] = (char) ((ch & 0xFF) >> 8);      }      return result;  }
return Execute<UploadArchiveRequest, UploadArchiveResult>(request, beforeClientExecution: request);
public List<Token> GetHiddenTokensToLeft(int tokenIndex) { return tokenIndex - 1; }
public override bool Equals(object obj) => obj is AutomatonQuery other && other.compiled == compiled && (other.term == null ? term == null : other.term.Equals(term));
if (spanQueries.size() == 1) return spanQueries.get(0); else { int i = 0; Iterator<SpanQuery> sqi = spanQueries.iterator(); SpanQuery sq; while (sqi.hasNext()) { sq = sqi.next(); float boost = weightBySpanQuery.get(sq); if (boost != 1f) { i++; sq = new SpanBoostQuery(sq, boost); } } return i == 0 ? spanQueries.get(0) : new SpanOrQuery(spanQueries.toArray(new SpanQuery[0])); }
return new StashCreateCommand(repo);
return typeof(T).GetField(fieldName);
return (DescribeEventSourceResult)request.ExecuteDescribeEventSource(beforeClientExecution: request);
return ExecuteGetDocumentAnalysis(request).BeforeClientExecution(request).GetDocumentAnalysisResult;
return ExecuteCancelUpdateStack(request, beforeClientExecution: true);
return Execute<ModifyLoadBalancerAttributesRequest, ModifyLoadBalancerAttributesResult>(request, (request) => request.BeforeClientExecution());
return client.Execute(request).SetInstanceProtection(request).BeforeClientExecution();
return ExecuteModifyDBProxy(request, beforeClientExecution: (request) => ModifyDBProxyRequest.ModifyDBProxyResult);
if (posLength != endOffset)  {     int len = endOffset - offset;     if (len > 0)      {         if (count == outputs.Length)          {             outputs = ArrayUtil.Grow(outputs, ArrayUtil.Oversize(count + 1, Integer.BYTES));             posLengths = ArrayUtil.Grow(posLengths, ArrayUtil.Oversize(count + 1, Integer.BYTES));             endOffsets = ArrayUtil.Grow(endOffsets, ArrayUtil.Oversize(count + 1, Integer.BYTES));         }         outputs[count] = new CharsRefBuilder();         posLengths[count] = posLength;         endOffsets[count] = endOffset;         outputs[count].CopyChars(0, offset, len);         count++;         posLength = endOffset = 0;     } }
;  FetchLibrariesRequest ) ( ; ) "cloudphoto" , "FetchLibraries" , "2017-07-11" , "CloudPhoto" ( super { ) ( HTTPS . ProtocolType
public bool Exists { get { return (bool)fs.Exists; } }
public class FilterOutputStream : Stream { public FilterOutputStream(Stream out) { this.out = @out; } }
var request = new ScaleClusterRequest { Method = "PUT", Path = "/clusters/{ClusterId}", HttpMethod = HttpMethod.PUT, ProductCode = "csk", ApiName = "ScaleCluster", ApiVersion = "2015-12-15", ServiceCode = "CS" };
return CreateTimeConstraint(DVConstraint.CreateDataValidationConstraint(formula1, formula2, operatorType));
return client.Execute(request).BeforeClientExecution(request).ListObjectParentPaths(new ListObjectParentPathsRequest()).ListObjectParentPathsResult;
return client.ExecuteDescribeCacheSubnetGroups(request).BeforeClientExecution(request).DescribeCacheSubnetGroupsResult;
bool flag = (field_5_options & SharedFormula.setShortBoolean) != 0;
bool ReuseObjects { get; }
return new ErrorNodeImpl(badToken).setParent(t).addAnyChild(t);
throw new ArgumentException("Unknown parameters: " + string.Join(", ", args));
return request.ExecuteRemoveSourceIdentifierFromSubscription(RemoveSourceIdentifierFromSubscriptionRequest, beforeClientExecution);
public static new TokenFilterFactory NewInstance(Map<String, String> args, String name, ClassLoader loader) { return new TokenFilterFactory(args, name); }
: AddAlbumPhotosRequest ) ( ; ) "cloudphoto" , "AddAlbumPhotos" , "2017-07-11" , "CloudPhoto" ( base { ) ( ProtocolType.HTTPS
return ExecuteGetThreatIntelSet(request).BeforeClientExecution(request) as GetThreatIntelSetResult;
return new Binary((clone.a), (clone.b));
return o is ArmenianStemmer;
public protected bool HasArray { get; }
return ExecuteUpdateContributorInsights(request).BeforeClientExecution(request);
fileShare.Remove(records); writeProtect = null; fileShare = null;
public class SolrSynonymParser : Analyzer : this(expand, analyzer, dedup) { }
return ExecuteRequest<RequestSpotInstancesRequest, RequestSpotInstancesResult>(request, (request) => request, (request) => request.BeforeClientExecution);
return GetObjectData()[findObjectRecord()];
return ExecuteGetContactAttributes(request).BeforeClientExecution(request);
return getKey() + ": " + getValue();
return client.ExecuteListTextTranslationJobs(request).BeforeClientExecution(request) as ListTextTranslationJobsResult;
return Execute<GetContactMethodsRequest, GetContactMethodsResult>(request, (request) => BeforeClientExecution(request));
public static string FunctionMetadata(string name) {      var fd = FunctionMetadata.getInstance().getFunctionByNameInternal(name);      return fd == null ? null : fd.getIndex() - 1;  }
return client.Execute(request, (request) => DescribeAnomalyDetectors(request));
public static string InsertId(bool changeId, string message, ObjectId objectId) { return changeId ? objectId : message; }
throw new MissingObjectException(objectId, typeHint) if (0 < sz && (sz = GetObjectSize(objectId, typeHint)) == 0);
return ExecuteImportInstallationMedia(request, (request) => request.BeforeClientExecution(), ImportInstallationMediaRequest.class);
return new PutLifecycleEventHookExecutionStatusResult().ExecutePutLifecycleEventHookExecutionStatus((PutLifecycleEventHookExecutionStatusRequest)request).BeforeClientExecution(request);
using LittleEndianInput;  double readDouble = ((NumberPtg)(in LittleEndianInput)).readDouble();
return ExecuteGetFieldLevelEncryptionConfig(request).BeforeClientExecution(request).GetFieldLevelEncryptionConfigResult;
return client.ExecuteDescribeDetector(request, (request) => beforeClientExecution(request));
return ExecuteReportInstanceStatus(request, beforeClientExecution: true);
return client.BeforeClientExecution(new DeleteAlarmRequest(), new DeleteAlarmResult());
return new PortugueseStemFilter(input);
public class Test  {     public static void Main(string[] args)      {     } }
public override bool Synchronized(object mutex) { return Remove(mutex); }
return client.Execute(request, (request) => GetDedicatedIpRequest, () => new GetDedicatedIpResult());
return ">=" + _p.ToString() + precedence;
return client.Execute(request).BeforeClientExecution(request).ListStreamProcessors(new ListStreamProcessorsRequest()).ListStreamProcessorsResult;
public class DeleteLoadBalancerPolicyRequest  {     public string LoadBalancerName { get; set; }     public string PolicyName { get; set; }     public DeleteLoadBalancerPolicyRequest() { }     public DeleteLoadBalancerPolicyRequest(string loadBalancerName, string policyName)      {         LoadBalancerName = loadBalancerName;         PolicyName = policyName;     } }
public WindowProtectRecord() { } public WindowProtectRecord(WindowProtectRecord options) { this.options = options; }
new UnbufferedCharStream(data, 0, bufferSize) { buffer = new char[bufferSize] };
return ExecuteGetOperations(request, (request) => BeforeClientExecution(request)).GetOperationsResult;
void EncodeInt32(byte[] b, int o, int w1, int w2, int w3, int w4, int w5)      => b[o + 4] = (byte)w1      && b[o + 5] = (byte)(w1 >> 8)      && b[o + 6] = (byte)(w1 >> 16)      && b[o + 7] = (byte)(w1 >> 24)     && b[o] = (byte)w2      && b[o + 1] = (byte)(w2 >> 8)      && b[o + 2] = (byte)(w2 >> 16)      && b[o + 3] = (byte)(w2 >> 24)     && b[o + 8] = (byte)w3      && b[o + 9] = (byte)(w3 >> 8)      && b[o + 10] = (byte)(w3 >> 16)      && b[o + 11] = (byte)(w3 >> 24)     && b[o + 12] = (byte)w4      && b[o + 13] = (byte)(w4 >> 8)      && b[o + 14] = (byte)(w4 >> 16)      && b[o + 15] = (byte)(w4 >> 24)     && b[o + 16] = (byte)w5      && b[o + 17] = (byte)(w5 >> 8)      && b[o + 18] = (byte)(w5 >> 16)      && b[o + 19] = (byte)(w5 >> 24);
field_1_h_hold = input.ReadShort(); field_2_v_hold = input.ReadShort(); field_3_width = input.ReadShort(); field_4_height = input.ReadShort(); field_5_options = input.ReadShort(); field_6_active_sheet = input.ReadShort(); field_7_first_visible_tab = input.ReadShort(); field_8_num_selected_tabs = input.ReadShort(); field_9_tab_width_ratio = input.ReadShort();
return ExecuteStopWorkspaces((StopWorkspacesRequest)request).BeforeClientExecution(request);
try { } catch (IOException) { } finally { isOpen = false; try { } finally { dump(); } try { truncate(channel); } finally { channel.close(); fos.close(); } }
return ExecuteDescribeMatchmakingRuleSets(request).BeforeClientExecution(request);
return string.Format("{0},{1},{2},{3}", wordId, surface, off, len);
return (string)pathStr;
public static double[] func(double[] v) {      if (v == null) return new double[0];      int n = v.Length;      double s = 0, m = 0;      for (int i = 0; i < n; ++i)          s += v[i];      double r = s / n;      s = 0;      for (int i = 0; i < n; ++i)          s += Math.Pow(v[i] - r, 2);      r = Math.Sqrt(s / n);      return new double[] { r, Double.NaN };  }
return client.BeforeClientExecution(request).ExecuteDescribeResize(request).DescribeResizeResult;
public final bool ReturnPassedThroughNonGreedyDecision()
return 0;
using System;  public class SimpleCellWalkContext  {     public int FirstRow { get; set; }     public int LastRow { get; set; }     public int FirstColumn { get; set; }     public int LastColumn { get; set; }     public int Width { get; set; } }  public class Cell  { }  public class CellHandler  {     public void OnCell(Cell cell, SimpleCellWalkContext ctx)      {     } }  public class ArithmeticUtils  {     public static int AddAndCheck(int a, int b)      {         return a + b;     }      public static int MulAndCheck(int a, int b)      {         return a * b;     }      public static int SubAndCheck(int a, int b)      {         return a - b;     } }  public class Program  {     public static void TraverseCells(SimpleCellWalkContext ctx, CellHandler handler)      {         int firstRow = ctx.FirstRow;         int lastRow = ctx.LastRow;         int firstColumn = ctx.FirstColumn;         int lastColumn = ctx.LastColumn;         int width = ctx.Width;         Row currentRow = null;         Cell currentCell = null;          for (; firstRow <= lastRow; ++firstRow)          {             ctx.RowNumber = firstRow;             currentRow = GetRow(ctx);             if (currentRow == null)              {                 continue;             }             for (int colNumber = firstColumn; colNumber <= lastColumn; ++colNumber)              {                 ctx.ColNumber = colNumber;                 currentCell = GetCell(currentRow, ctx);                 if (currentCell == null && !TraverseEmptyCells)                  {                     continue;                 }                 handler.OnCell(currentCell, ctx);             }         }     }      public static Row GetRow(SimpleCellWalkContext ctx)      {         // implement this method         return null;     }      public static Cell GetCell(Row row, SimpleCellWalkContext ctx)      {         // implement this method         return null;     }      public static bool TraverseEmptyCells      {         get;          // implement this property     }      public static int RowSize      {         get;          // implement this property     }      public static void Main(string[] args)      {     } }
return pos;
return other is ScoreTerm ? (this.boost == other.boost ? 0 : this.boost > other.boost ? 1 : -1) : (this.bytes.CompareTo(other.bytes));
switch (s[i]) { case 'ح': case 'ه': case 'ي': case 'ى': case 'ك': case 'ﻫ': s[i] = s[--i]; break; case 'ﺡ': break; default: break; } return s.Length;
using System.IO;  public class LittleEndianWriter  {     private BinaryWriter out;      public void writeShort(int _options)      {         out.Write((short)_options);     } }
public class DiagnosticErrorListener
public KeySchemaElement(string attributeName, KeyType keyType) { this.AttributeName = attributeName; this.KeyType = keyType; }
return ExecuteGetAssignment(request).BeforeClientExecution(request).GetAssignmentResult();
return (bool)(findOffset(id) != AnyObjectId - 1);
return this.allGroups = allGroups;
public synchronized void DimConfig(String dimName, boolean v) { if (v) { FieldType ft = fieldTypes.get(dimName); if (ft == null) { ft = new DimConfig(dimName, true); fieldTypes.put(dimName, ft); } ft.multiValued = true; } }

return ExecuteDeleteVoiceConnector(request).BeforeClientExecution(request);
return client.ExecuteDeleteLifecyclePolicy(request, (request) => { });
