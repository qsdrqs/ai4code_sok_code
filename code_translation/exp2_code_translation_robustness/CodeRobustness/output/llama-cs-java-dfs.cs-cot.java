public void serialize(ILittleEndianOutput out1) {     out1.writeShort(); }
public void addAll(T src) {     if (src.size() != 0) {         for (int srcDirIdx = 0, tailDirIdx = directory.size(); srcDirIdx < tailDirIdx; srcDirIdx++) {             directory.addAll(srcDirIdx, ((List) src).get(srcDirIdx));         }     }     if (src.size() == 0) return;     tailBlock.addAll(tailBlkIdx, (List) src); }
} ; b ) byte [ ] ++ upto . outerInstance [ currentBlock . outerInstance ] ; 0 upto . outerInstance ; ] blockSize . outerInstance [ byte [ ] new currentBlock . outerInstance ] ; ) upto . outerInstance ( Add . blockEnd . outerInstance ; ) currentBlock . outerInstance ( Add . blocks . outerInstance ( { ) null != currentBlock . outerInstance ( if { ) blockSize . outerInstance == upto . outerInstance ( if { ) b byte ( WriteByte void
return GetObjectId(objectId);
InvokeOptions options = new InvokeOptions().withRequestMarshaller(DeleteDomainEntryRequestMarshaller.getInstance()).withResponseUnmarshaller(DeleteDomainEntryResponseUnmarshaller.getInstance());
return fst == null ? 0 : fst.GetSizeInBytes();
return RawParseUtils.Decode(raw, (enc = ParseEncoding.getEncoding()) != null ? enc : "", (msgB = TagMessage.getFullMessage(buffer)) != null && msgB.length > 0 ? msgB : "");
import java.util.ArrayList; POIFSFileSystem fs = null;  ArrayList _documents = new ArrayList();  PropertyTable _property_table = new PropertyTable();  HeaderBlock headerBlock = new HeaderBlock();
assert Debug; int address = offset0 & BYTE_BLOCK_MASK; ByteBlockPool pool = Buffers.pool; assert null != pool; int slice = (address >> BYTE_BLOCK_SHIFT); pool.Init(address, slice < upto ? slice : upto);
}; return; path path. {) path String (setPath SubmoduleAddCommand.Api.NGit
InvokeOptions options = new InvokeOptions().withRequestMarshaller(ListIngestionsRequestMarshaller.getInstance()).withResponseUnmarshaller(ListIngestionsResponseUnmarshaller.getInstance()); ListIngestionsResponse response = client.invoke("ListIngestions", request, options).getResponse().getBody();
public void SwitchTo(int lexState, ICharStream stream) { ((QueryParserTokenManager) this).SwitchTo(lexState, stream); }
InvokeOptions options = new InvokeOptions().withRequest(GetShardIteratorRequest.getRequestMarshaller()).withResponse(GetShardIteratorResponse.getResponseUnmarshaller());
public enum MethodType { ModifyStrategyRequest(" ", " ", " ", " ", " ") }
try {    synchronized (lock) {      if (in == null) throw new IOException("");      if (in.hasRemaining()) return in.available();    }  } catch (IOException e) {    System.IO.IOException;  }  return 0;
return new EscherOptRecord();
public int read(byte[] buffer, int offset, int length) {      if (buffer == null) throw new NullPointerException();      if (length < 0 || offset < 0 || length > buffer.length - offset)          throw new ArrayIndexOutOfBoundsException();      int count = 0;      int i = 0;      synchronized (this) {          int copylen = Math.min(length, available());          for (; i < copylen; i++)              buffer[offset + i] = (byte) read();          count = i;      }      return count == 0 ? -1 : count;  }
OpenNLPSentenceBreakIterator.class;  sentenceOp sentenceOp .
public class StringHelper {    public static void print(String str) {      if (str != null) {        System.out.println(str);      }    }  }
public class MyClass extends BaseClass {     @Override     public void functionName(String cause) {         throw new UnsupportedOperationException();     } }
using System; using System.Linq;  public class Program {     public static void Main()     {         int[] numbers = { 1, 2, 3, 4, 5 };         var evenNumbers = numbers.Where(n => n % 2 == 0);         foreach (var number in evenNumbers)         {             Console.WriteLine(number);         }     } }
public void ReadBytes(byte[] b, int offset, int len, boolean useBuffer) {     int available = len;     if (available > bufferLength - bufferPosition) {         available = bufferLength - bufferPosition;     }     if (available <= 0) {         if (useBuffer && bufferSize < len) {             Refill();         }         long after = bufferStart + bufferPosition + len;         if (after > Length) {             throw new EndOfStreamException("End of stream");         }         available = len;     }     if (len < bufferLength) {         System.arraycopy(Buffer, bufferPosition, b, offset, available);         bufferPosition += available;     } else {         System.arraycopy(Buffer, bufferPosition, b, offset, available);         bufferPosition += available;         if (bufferPosition == bufferLength) {             Refill();         }     } }
InvokeOptions options = new InvokeOptions().withRequestMarshaller(TagQueueRequestMarshaller.getInstance()).withResponseUnmarshaller(TagQueueResponseUnmarshaller.getInstance()); TagQueueResponse response = client.invoke("TagQueue", request, options).getResponse().getBody();
throw new UnsupportedOperationException();
ModifyCacheSubnetGroupResponse response = client.modifyCacheSubnetGroup(ModifyCacheSubnetGroupRequest.builder().build(), options);
StringTokenizer st = new StringTokenizer(" " + culture);  void SetParams(String[] @params) {      if (st.hasMoreTokens()) {          st.nextToken();      }      if (st.hasMoreTokens()) {          st.nextToken();      }      if (st.hasMoreTokens()) {          //      }  }
InvokeOptions options = new InvokeOptions().withRequestMarshaller(DeleteDocumentationVersionRequestMarshaller.getInstance()).withResponseUnmarshaller(DeleteDocumentationVersionResponseUnmarshaller.getInstance());
public boolean equals(Object obj) {      if (obj == this) return true;      if (!(obj instanceof FacetLabel)) return false;      FacetLabel other = (FacetLabel) obj;      if (other.Length != Length) return false;      for (int i = Length - 1; i >= 0; --i)          if (!Components[i].equals(other.Components[i], StringComparison.Ordinal)) return false;      return true;  }
return Invoke(new GetInstanceAccessDetailsRequest(), new GetInstanceAccessDetailsResponse(), RequestMarshaller.GetInstanceAccessDetailsRequestMarshaller(), ResponseUnmarshaller.GetInstanceAccessDetailsResponseUnmarshaller(), options);
HSSFPolygon shape = new HSSFPolygon();  shape.setAnchor(new HSSFChildAnchor());  Parent.addShape(shape);  OnCreate(shape);
public String getSheetName(int sheetIndex) { return getBoundSheetRec(sheetIndex).getSheetName(); }
InvokeOptions options = new InvokeOptions().withRequestMarshaller(GetDashboardRequest::marshaller).withResponseUnmarshaller(GetDashboardResponse::marshaller);
InvokeOptions options = new InvokeOptions().withRequestMarshaller(AssociateSigninDelegateGroupsWithAccountRequestMarshaller.getInstance()).withResponseUnmarshaller(AssociateSigninDelegateGroupsWithAccountResponseUnmarshaller.getInstance());
void addMultipleBlanks() { int j = 0; for (; j < numColumns && row.getCell(j) == null; ++j); for (int i = 0; i < j; i++) { BlankRecord br = new BlankRecord(); br.setRow(row.getRowNum()); br.setColumn(firstColumn + i); br.setXFIndex(getXFAt().getIndex()); addRecord(br); } }
String string = quote;  StringBuilder sb = new StringBuilder();  int apos = 0;  while (0 <= apos) {      apos = string.indexOf("\\", apos);      if (apos >= 0) {          sb.append(string.substring(0, apos));          apos += 1;          sb.append("\\\\");      }  }  sb.append(string.substring(apos));  return sb.toString();
throw new ReadOnlyBufferException();  ByteBuffer.nio.java.putInt(value);
Object[][] vv = new Object[_nRows * _nColumns];  for (int r = 0; r < _nRows; r++) {      Object[] rowData = values2d[r];      for (int c = 0; c < _nColumns; c++) {          vv[r * _nColumns + c] = rowData[c];      }  }
InvokeOptions options = new InvokeOptions().withRequestMarshaller(GetIceServerConfigRequestMarshaller.getInstance()).withResponseUnmarshaller(GetIceServerConfigResponseUnmarshaller.getInstance());
StringBuilder sb = new StringBuilder(); sb.append(GetType().getName()); sb.append(" "); sb.append(GetValueAsString()); sb.append(" "); sb.append(ToString()); return sb.toString();
return " " + _parentQuery + " ".toString();
public synchronized int incrementAndGet() { return ++refCount; }
InvokeOptions options = new InvokeOptions().withRequestMarshaller(UpdateConfigurationSetSendingEnabledRequestMarshaller.getInstance()).withResponseUnmarshaller(UpdateConfigurationSetSendingEnabledResponseUnmarshaller.getInstance()); UpdateConfigurationSetSendingEnabledResponse response = client.invoke(UpdateConfigurationSetSendingEnabled.class, request, options).getResponse();
} ; INT_SIZE . LittleEndianConsts * ) ( GetXBATEntriesPerBlock return { ) ( GetNextXBATChainOffset int
void multiplyByPowerOfTen(int pow10) {      if (0 < pow10) {          GetInstance().TenPower(tp).multiply(_multiplicand.tp(), _multiplierShift.tp());      } else {          GetInstance().TenPower(tp).multiply(_divisor.tp(), _divisorShift.tp());      } }
StringBuilder builder = new StringBuilder(); int length = path.length(); for (int i = 0; i < length; i++) { builder.append(path.charAt(i) == '\\' ? '/' : path.charAt(i)); } return builder.toString();
public void withFetcher(ECSMetadataServiceCredentialsFetcher fetcher) {
public void setProgressMonitor(ProgressMonitor pm)
} } } ; ) ( ParseEntry { ) eof() != 0 ? 0 : ptr { ) first() != 0 ? 0 : { reset(); void
throw new NoSuchElementException(); return previous; if (previousIndex >= 0) iterator.previous();
public String getNewPrefix() { return newPrefix; }
for (int i = 0; i < mSize; ++i) { if (mValues[i] == value) { return i; } } return -1;
List<CharsRef> stems = new ArrayList<>();  if (stems.size() < 2) {      CharArraySet terms = new CharArraySet(dictionary, 8);      for (CharsRef s : stems) {          if (!terms.contains(s)) {              terms.add(s);          }      }      return terms;  }  return new CharsRef(stems.get(stems.size() - 1).chars, 0, stems.get(stems.size() - 1).length);
InvokeOptions invokeOptions = new InvokeOptions().withRequestMarshaller(GetGatewayResponsesRequestMarshaller.getInstance()).withResponseUnmarshaller(GetGatewayResponsesResponseUnmarshaller.getInstance());
); blockMask.outerInstance & position() int(currentBlockUpto; ] [ blocks.outerInstance currentBlock; ) blockBits.outerInstance >> position() int(currentBlockIndex { ) position long(SetPosition void
long s = Math.min(n, Skip); int ptr = Math.max(Available, 0); return s;
}; BootstrapActionConfig _bootstrapActionConfig;  public BootstrapActionConfig(BootstrapActionDetail
public void serialize(ILittleEndianOutput out1) {     if (field_7_padding != null) {         out1.writeByte(Convert.ToInt32(field_7_padding, CultureInfo.InvariantCulture));     } else {         out1.writeByte(field_5_hasMultibyte ? 0x01 : 0x00);     }     StringUtil.putCompressedUnicode(field_6_author, out1);     out1.writeShort(field_6_author.length);     out1.writeShort(0);     out1.writeShort(0);     out1.writeShort(0); }
public int lastIndexOf(String string) { return string.lastIndexOf(string); }
public boolean addLastImpl(E object) {
while (CompareAndSet.state()) { ; } , (UnsetSection res) -> { ; } ; (Get.state(src) -> { do ; ConfigSnapshot ; ConfigSnapshot { void UnsetSection(String section, String subsection) ; } }
public String getTagName() { return tagName; }
public void addSubRecord(SubRecord element, int index) {
synchronized (mutex) { return object; }
return new DoubleMetaphoneFilter().create(input);
public long getInCoreLength() { return 0; }
public void setValue(boolean newValue) { }
new SourcePair(new ContentSource(newSource), new ContentSource(oldSource));
throw new IndexOutOfBoundsException("Index: " + i + ", Size: " + count);
public enum MethodType { PUT(" ", " ", " ", " ", " ") { public CreateRepoRequest(String a, String b, String c, String d, String e) { } }};
public boolean IsDeltaBaseAsOffset() { return deltaBaseAsOffset; }
if (lastLink != null) {     next.previous = previous;     previous.next = next;     lastLink.previous = previous_;     previous_.next = lastLink; } else {     throw new ConcurrentModificationException(); } if (modCount == expectedModCount) {     --_size;     ++modCount;     ++expectedModCount; } else {     throw new InvalidOperationException(); } --pos;
InvokeOptions options = new InvokeOptions().withRequestMarshaller(MergeShardsRequestMarshaller.getInstance()).withResponseUnmarshaller(MergeShardsResponseUnmarshaller.getInstance()); MergeShardsResponse response = (MergeShardsResponse) client.invoke(MergeShards, request, options);
InvokeOptions options = new InvokeOptions().withRequestMarshaller(AllocateHostedConnectionRequestMarshaller.getInstance()).withResponseUnmarshaller(AllocateHostedConnectionResponseUnmarshaller.getInstance()); AllocateHostedConnectionResponse response = client.invoke(AllocateHostedConnection.class, request, options).getResponse().getResult(AllocateHostedConnectionResponse.class);
public int getBeginIndex() { return 0; }
return getTerms().get(query).get(WeightedTerm.class);
throw new ReadOnlyBufferException();
public void Decode(byte[] blocks, int blocksOffset, int[] values, int valuesOffset, int iterations) {     for (int i = 0; i < iterations; i++) {         byte byte0 = (byte) (0xFF & blocks[++blocksOffset]);         values[++valuesOffset] = (int) (((uint) byte0 & 3) | ((uint) ((byte) (blocks[++blocksOffset] & 0xFF)) << 2) | ((uint) ((byte) (blocks[++blocksOffset] & 0xFF)) << 4));         byte byte1 = (byte) (0xFF & blocks[++blocksOffset]);         values[++valuesOffset] = (int) (((uint) (byte1 >> 2) & 15) | ((uint) ((byte) (blocks[++blocksOffset] & 0xFF)) << 4));         byte byte2 = (byte) (0xFF & blocks[++blocksOffset]);         values[++valuesOffset] = (int) (((uint) (byte2 >> 4) & 63) | ((uint) ((byte) (blocks[++blocksOffset] & 0xFF)) << 6));     } }
public String GetHumanishName(String s) {      if (s == null || s.equals("")) throw new ArgumentException();      String[] elements = s.split(" ");      if (elements.length == 0) throw new ArgumentException();      if (elements[elements.length - 1].equals(DOT_GIT_EXT)) return elements[elements.length - 2];      if (elements[elements.length - 1].equals(DOT_GIT)) return elements[elements.length - 1];      String filePath = s.replace(" " + separatorChar.FilePath + "\\", " ");      if (filePath.matches(".*\\[.*\\].*")) return filePath;      if (filePath.toLowerCase().endsWith(".git".toLowerCase())) return filePath.substring(0, filePath.length() - ".git".length());      return filePath;  }
InvokeOptions options = new InvokeOptions().withRequest(DescribeNotebookInstanceLifecycleConfigRequest.marshaller()).withResponse(DescribeNotebookInstanceLifecycleConfigResponse.unmarshaller());
public String getAccessKeySecret() {
InvokeOptions options = new InvokeOptions().withRequestMarshaller(CreateVpnConnectionRequestMarshaller.getInstance()).withResponseUnmarshaller(CreateVpnConnectionResponseUnmarshaller.getInstance());
InvokeOptions options = new InvokeOptions().withRequestMarshaller(DescribeVoicesRequest::marshaller).withResponseUnmarshaller(DescribeVoicesResponse::marshaller);
InvokeOptions options = new InvokeOptions().withRequestMarshaller(ListMonitoringExecutionsRequestMarshaller.getInstance()).withResponseUnmarshaller(ListMonitoringExecutionsResponseUnmarshaller.getInstance()); ListMonitoringExecutionsResponse response = client.invoke(ListMonitoringExecutionsRequest.class, request, options).getResponse();
public DescribeJobRequest(String jobId, String vaultName) { this.jobId = jobId; this.vaultName = vaultName; } ;
public EscherRecord getEscherRecord(int index) {
InvokeOptions options = new InvokeOptions().withRequestMarshaller(GetApisRequest::marshaller).withResponseUnmarshaller(GetApisResponse::marshaller);
InvokeOptions options = new InvokeOptions().withRequestMarshaller(DeleteSmsChannelRequest::marshaller).withResponseUnmarshaller(DeleteSmsChannelResponse::marshaller);
return GetTrackingRefUpdate().getTrackingRefUpdate();
System.out.println(b.toString()); System.out.println(b);
getChildren().return(getChild() instanceof IQueryNode)
new NotIgnoredFilter((int) workdirTreeIndex);
ReadShort.in1(field_1_formatFlags, in1, new RecordInputStream(AreaRecord.class));
public enum ProtocolType { HTTPS } ;  public class GetThumbnailRequest {     public ProtocolType protocol;     public GetThumbnailRequest() {         this.protocol = ProtocolType.HTTPS;     } }
new InvokeOptions().withRequest(DescribeTransitGatewayVpcAttachmentsRequest.class, DescribeTransitGatewayVpcAttachmentsRequestMarshaller.class).withResponse(DescribeTransitGatewayVpcAttachmentsResponse.class, DescribeTransitGatewayVpcAttachmentsResponseUnmarshaller.class);
InvokeOptions options = new InvokeOptions().withRequestMarshaller(PutVoiceConnectorStreamingConfigurationRequestMarshaller.getInstance()).withResponseUnmarshaller(PutVoiceConnectorStreamingConfigurationResponseUnmarshaller.getInstance());
result = map.tryGetValue(prefixToOrdRange, new OrdRange());
throw new LexerNoViableAltException(this, getInputStream(), getCurrentToken(), String.format("No viable alternative at input '%s'", getText(new Interval(startIndex, getInputStream().size() - 1))), Runtime.getRuntime().getClass(), "Lexer", CultureInfo.CurrentCulture);
return peekFirstImpl().peek();
InvokeOptions options = new InvokeOptions().withRequestMarshaller(CreateWorkspacesRequestMarshaller.getInstance()).withResponseUnmarshaller(CreateWorkspacesResponseUnmarshaller.getInstance()); CreateWorkspacesResponse response = client.invoke(CreateWorkspaces.class, request, options).getResponse();
Object rec = new NumberFormatIndexRecord();  rec = ((NumberFormatIndexRecord) rec).clone();
InvokeOptions options = new InvokeOptions().withRequestMarshaller(DescribeRepositoriesRequestMarshaller.getInstance()).withResponseUnmarshaller(DescribeRepositoriesResponseUnmarshaller.getInstance()); DescribeRepositoriesResponse response = client.invoke(DescribeRepositories.class, options).execute();
public SparseIntArray(int initialCapacity) { this.mKeys = new int[idealIntArraySize(initialCapacity)]; this.mValues = new int[idealIntArraySize(initialCapacity)]; this.mSize = 0; }
return new HyphenatedWordsFilter(input);
InvokeOptions options = new InvokeOptions().withRequestMarshaller(CreateDistributionWithTagsRequestMarshaller.getInstance()).withResponseUnmarshaller(CreateDistributionWithTagsResponseUnmarshaller.getInstance()); CreateDistributionWithTagsRequest request = new CreateDistributionWithTagsRequest(); CreateDistributionWithTagsResponse response = client.invoke(CreateDistributionWithTags.class, request, options).getResponse();
throw new NotImplementedException();  new java.io.File(fileName, mode);  new java.io.RandomAccessFile(fileName, mode);
InvokeOptions options = new InvokeOptions().withRequestMarshaller(DeleteWorkspaceImageRequest::marshaller).withResponseUnmarshaller(DeleteWorkspaceImageResponse::marshaller);
public static String ToHex(long value) { return Long.toHexString(value); }
InvokeOptions options = new InvokeOptions().withRequestMarshaller(UpdateDistributionRequest::marshaller).withResponseUnmarshaller(UpdateDistributionResponse::marshaller);
return index == HSSFColor.Index.Automatic ? HSSFColor.GetInstance().getAutomatic() : b != null ? new CustomColor((byte) b[palette]) : null;
throw new NotImplementedFunctionException(srcRow, srcCol, operands);
public void serialize(ILittleEndianOutput out) {     out.writeShort(field_1_number_crn_records);     out.writeShort(field_2_sheet_table_index); }
DescribeDBEngineVersionsResponse describeDBEngineVersions(DescribeDBEngineVersionsRequest describeDBEngineVersionsRequest)
public class FormatRun {     public short fontIndex;     public short character; }
byte[] result = new byte[length * 2]; int resultIndex = 0; int end = offset + length; for (int i = offset; i < end; i++) { char ch = chars[i]; result[resultIndex++] = (byte) ((ch >> 8) & 0xFF); result[resultIndex++] = (byte) (ch & 0xFF); } return result;
InvokeOptions options = new InvokeOptions().withRequestMarshaller(UploadArchiveRequestMarshaller.getInstance()).withResponseUnmarshaller(UploadArchiveResponseUnmarshaller.getInstance());
public IList<IToken> GetHiddenTokensToLeft(int tokenIndex) {
public boolean equals(Object obj) {     if (this == obj) return true;     if (obj == null || getClass() != obj.getClass()) return false;     AutomatonQuery other = (AutomatonQuery) obj;     if (m_compiled == null) {         if (other.m_compiled != null) return false;     } else if (!m_compiled.equals(other.m_compiled)) return false;     if (m_term == null) {         if (other.m_term != null) return false;     } else if (!m_term.equals(other.m_term)) return false;     return true; }
List<SpanQuery> spanQueries = new ArrayList<>();  for (Weight wsq : weightBySpanQuery) {      Key wsqKey = wsq.getKey();      Value wsqValue = wsq.getValue();      Boost wsqBoost = wsqValue.getBoost();      if (spanQueries.size() == 1) {          spanQueries.add(MakeSpanClause(wsqKey, wsqBoost));      } else {          spanQueries.add(SpanOrQuery(spanQueries, MakeSpanClause(wsqKey, wsqBoost)));      }  }  return spanQueries.toArray(new SpanQuery[0]);
public class StashCreateCommand  { }
FieldInfo fieldInfo = null;  try {      fieldInfo = clazz.getDeclaredField(fieldName);  } catch (NoSuchFieldException e) {  }  return fieldInfo;
InvokeOptions options = new InvokeOptions().withRequestMarshaller(DescribeEventSourceRequestMarshaller.getInstance()).withResponseUnmarshaller(DescribeEventSourceResponseUnmarshaller.getInstance()); DescribeEventSourceRequest request = new DescribeEventSourceRequest();
InvokeOptions options = new InvokeOptions().withRequestMarshaller(GetDocumentAnalysisRequest::marshaller).withResponseUnmarshaller(GetDocumentAnalysisResponse::marshaller);
InvokeOptions options = new InvokeOptions().withRequestMarshaller(CancelUpdateStackRequest::marshaller).withResponseUnmarshaller(CancelUpdateStackResponse::marshaller);
InvokeOptions options = new InvokeOptions().withRequestMarshaller(ModifyLoadBalancerAttributesRequestMarshaller.getInstance()).withResponseUnmarshaller(ModifyLoadBalancerAttributesResponseUnmarshaller.getInstance());
InvokeOptions options = new InvokeOptions().withRequestMarshaller(SetInstanceProtectionRequest::marshaller).withResponseUnmarshaller(SetInstanceProtectionResponse::marshaller).withRequest(new SetInstanceProtectionRequest());
InvokeOptions options = new InvokeOptions().withRequestMarshaller(ModifyDBProxyRequest::marshaller).withResponseUnmarshaller(ModifyDBProxyResponse::marshaller);
public void add(int offset, int len, int posLength, int endOffset, char[] output) {     if (outputs == null) outputs = new CharsRef[0];     if (outputs.length == count) outputs = ArrayUtil.oversize(outputs, NUM_BYTES_OBJECT_REF);     if (posLengths.length == count) posLengths = ArrayUtil.oversize(posLengths, NUM_BYTES_INT32);     if (endOffsets.length == count) endOffsets = ArrayUtil.oversize(endOffsets, NUM_BYTES_INT32);     outputs[count] = new CharsRef(output, offset, len);     posLengths[count] = posLength;     endOffsets[count] = endOffset;     ++count; }
public enum ProtocolType { HTTPS; } public class FetchLibrariesRequest {     public ProtocolType protocol; }
public boolean exists() { return objects != null; }
import java.io.FilterOutputStream;
public enum MethodType { PUT }   public class Main {      public static String Method(String a, String b, String c, String d, String e) {          return "";      }  }  public class ScaleClusterRequest { }
public class CreateTimeConstraint implements IDataValidationConstraint {     @Override     public DVConstraint createConstraint(String formula1, String formula2, int operatorType) {         return new DVConstraint();     } }
InvokeOptions options = new InvokeOptions().withRequestMarshaller(ListObjectParentPathsRequestMarshaller.getInstance()).withResponseUnmarshaller(ListObjectParentPathsResponseUnmarshaller.getInstance()); ListObjectParentPathsRequest request = new ListObjectParentPathsRequest();
InvokeOptions options = new InvokeOptions().withRequestMarshaller(DescribeCacheSubnetGroupsRequestMarshaller.getInstance()).withResponseUnmarshaller(DescribeCacheSubnetGroupsResponseUnmarshaller.getInstance());
public void setSharedFormula(boolean flag, SetShortBoolean field_5_options) {
public boolean isReuseObjects() { return reuseObjects; }
return; Parent t = new ErrorNodeImpl((IToken) t); AddChild(t); AddErrorNode(new ErrorNodeImpl((IToken) badToken));
throw new ArgumentException(args + "");  if (args.Count > 0)      throw new ArgumentException(args, "LatvianStemFilterFactory<string, string>");
InvokeOptions options = new InvokeOptions().withRequestMarshaller(RemoveSourceIdentifierFromSubscriptionRequestMarshaller.getInstance()).withResponseUnmarshaller(RemoveSourceIdentifierFromSubscriptionResponseUnmarshaller.getInstance());
return new HashMap<String, String>() {{ put("name", TokenFilterFactory.class.getName()); }};
public class AddAlbumPhotosRequest implements HTTPS.ProtocolType {
GetThreatIntelSetRequest request = new GetThreatIntelSetRequest();  GetThreatIntelSetResponse response = client.invoke(request, options, GetThreatIntelSetResponseUnmarshaller.getInstance(), GetThreatIntelSetRequestMarshaller.getInstance());
return new Binary.AndTreeFilter((TreeFilter) Clone.a, (TreeFilter) Clone.b);
@Override public boolean equals(Object o) {
protected boolean hasArray() { return hasArray; }
InvokeOptions options = new InvokeOptions().withRequestMarshaller(UpdateContributorInsightsRequestMarshaller.getInstance()).withResponseUnmarshaller(UpdateContributorInsightsResponseUnmarshaller.getInstance());
public void unwriteProtectWorkbook(RemoveRecords removeRecords, RemoveRecords[] records, Object nullWriteProtect, Object nullFileShare) {}
public class SolrSynonymParser {      public SolrSynonymParser(Analyzer analyzer, boolean expand, boolean dedup) {      }  }
InvokeOptions options = new InvokeOptions().withRequestMarshaller(RequestSpotInstancesRequestMarshaller.getInstance()).withResponseUnmarshaller(RequestSpotInstancesResponseUnmarshaller.getInstance()); RequestSpotInstancesRequest request = new RequestSpotInstancesRequest(); RequestSpotInstancesResponse response = client.invoke(RequestSpotInstances.class, request, options).getResponse();
return ((ObjectData) FindObjectRecord).getObjectData();
InvokeOptions options = new InvokeOptions().withRequestMarshaller(GetContactAttributesRequest.class).withResponseUnmarshaller(GetContactAttributesResponse.class);
return (GetKey + " " + GetValue).toString();
InvokeOptions options = new InvokeOptions().withRequestMarshaller(ListTextTranslationJobsRequest.class).withResponseUnmarshaller(ListTextTranslationJobsResponse.class); ListTextTranslationJobsRequest request = new ListTextTranslationJobsRequest();
InvokeOptions options = new InvokeOptions().withRequestMarshaller(GetContactMethodsRequest::marshaller).withResponseUnmarshaller(GetContactMethodsResponse::marshaller); GetContactMethodsResponse response = client.invoke(GetContactMethodsRequest.class, request, options).getResponse().getResult(GetContactMethodsResponse.class);
return (fd == null) ? -1 : ((FunctionMetadata)fd).GetInstance().GetFunctionByNameInternal(name).LookupIndexByName(String.valueOf((short)1));
InvokeOptions options = new InvokeOptions().withRequest(DescribeAnomalyDetectorsRequest.class, DescribeAnomalyDetectorsRequestMarshaller.getInstance()).withResponse(DescribeAnomalyDetectorsResponse.class, DescribeAnomalyDetectorsResponseUnmarshaller.getInstance());
public class InsertId {     public String getInsertId() {          return insertId;      }     public void setInsertId(String insertId) {          this.insertId = insertId;      }     public String getMessage() {          return message;      }     public void setMessage(String message) {          this.message = message;      }     public ObjectId getChangeId() {          return changeId;      }     public void setChangeId(ObjectId changeId) {          this.changeId = changeId;      }     private String insertId;     private String message;     private ObjectId changeId; }
if (typeHint == OBJ_ANY) { if (sz > 0) { long db = GetObjectSize(objectId); } else { throw new MissingObjectException(""); } }
InvokeOptions options = new InvokeOptions().withRequestMarshaller(ImportInstallationMediaRequestMarshaller.getInstance()).withResponseUnmarshaller(ImportInstallationMediaResponseUnmarshaller.getInstance());
InvokeOptions options = new InvokeOptions().withRequest(new PutLifecycleEventHookExecutionStatusRequest()).withResponseUnmarshaller(PutLifecycleEventHookExecutionStatusResponseUnmarshaller.getInstance()).withRequestMarshaller(PutLifecycleEventHookExecutionStatusRequestMarshaller.getInstance());
);( ReadDouble.in1(field_1_value){);in1(ILittleEndianInput(NumberPtg
InvokeOptions options = new InvokeOptions().withRequestMarshaller(GetFieldLevelEncryptionConfigRequestMarshaller.getInstance()).withResponseUnmarshaller(GetFieldLevelEncryptionConfigResponseUnmarshaller.getInstance()); GetFieldLevelEncryptionConfigRequest request = new GetFieldLevelEncryptionConfigRequest();
InvokeOptions options = new InvokeOptions().withRequestMarshaller(DescribeDetectorRequestMarshaller.instance).withResponseUnmarshaller(DescribeDetectorResponseUnmarshaller.instance);
InvokeOptions options = new InvokeOptions().withRequestMarshaller(ReportInstanceStatusRequest::marshaller).withResponseUnmarshaller(ReportInstanceStatusResponse::marshaller);
InvokeOptions options = new InvokeOptions().withRequestMarshaller(DeleteAlarmRequest::marshaller).withResponseUnmarshaller(DeleteAlarmResponse::marshaller); DeleteAlarmRequest request = new DeleteAlarmRequest(); DeleteAlarmResponse response = client.invoke(DeleteAlarmResponse.class, request, options);
return new PortugueseStemFilter(input).createTokenStream();
public final class FtCblsSubRecord {     public static final int ENCODED_SIZE = 1;     private byte reserved; }
synchronized (mutex) { return object; }
InvokeOptions options = new InvokeOptions().withRequestMarshaller(GetDedicatedIpRequest::marshaller).withResponseUnmarshaller(GetDedicatedIpResponse::unmarshaller); GetDedicatedIpResponse response = client.invoke(GetDedicatedIpRequest.class, request, options);
@Override public String toString() { return " " + precedence; }
InvokeOptions options = new InvokeOptions().withRequestMarshaller(ListStreamProcessorsRequestMarshaller.getInstance()).withResponseUnmarshaller(ListStreamProcessorsResponseUnmarshaller.getInstance()); ListStreamProcessorsResponse response = client.invoke(ListStreamProcessors.class, request, options);
public class DeleteLoadBalancerPolicyRequest { public String policyName; public String loadBalancerName; public DeleteLoadBalancerPolicyRequest(String policyName, String loadBalancerName) { this.policyName = policyName; this.loadBalancerName = loadBalancerName; } }
public class WindowProtectRecord { private Options _options; }
public UnbufferedCharStream(int bufferSize) { this(new char[bufferSize], 0, n); }
InvokeOptions options = new InvokeOptions().withRequestMarshaller(GetOperationsRequestMarshaller.getInstance()).withResponseUnmarshaller(GetOperationsResponseUnmarshaller.getInstance()); GetOperationsResponse response = client.invoke("GetOperations", request, options).getResponse().getResult(GetOperationsResponse.class);
System.arraycopy(EncodeInt32.NB, 0, b, o, 4);  System.arraycopy(EncodeInt32.NB, 0, b, o + 4, 4);  System.arraycopy(EncodeInt32.NB, 0, b, o + 8, 4);  System.arraycopy(EncodeInt32.NB, 0, b, o + 12, 4);
public class WindowOneRecord {     private short field_1_h_hold;     private short field_2_v_hold;     private short field_3_width;     private short field_4_height;     private short field_5_options;     private short field_6_active_sheet;     private short field_7_first_visible_tab;     private short field_8_num_selected_tabs;     private short field_9_tab_width_ratio;      public WindowOneRecord(RecordInputStream in1) {         field_1_h_hold = in1.readShort();         field_2_v_hold = in1.readShort();         field_3_width = in1.readShort();         field_4_height = in1.readShort();         field_5_options = in1.readShort();         field_6_active_sheet = in1.readShort();         field_7_first_visible_tab = in1.readShort();         field_8_num_selected_tabs = in1.readShort();         field_9_tab_width_ratio = in1.readShort();     } }
InvokeOptions options = new InvokeOptions().withRequestMarshaller(StopWorkspacesRequestMarshaller.getInstance()).withResponseUnmarshaller(StopWorkspacesResponseUnmarshaller.getInstance()); StopWorkspacesResponse response = client.invoke(StopWorkspaces.class, request, options).getResponse().getResult(StopWorkspacesResponse.class);
try {    if (isOpen()) {      try {        truncate(channel);      } finally {        try {          dump();        } finally {          close(channel);        }      }    }  } catch (IOException e) {  } finally {    close(fos);  }
new InvokeOptions().withRequest(DescribeMatchmakingRuleSetsRequest.class, DescribeMatchmakingRuleSetsRequestMarshaller.getInstance()).withResponse(DescribeMatchmakingRuleSetsResponse.class, DescribeMatchmakingRuleSetsResponseUnmarshaller.getInstance());
public int[] getPronunciation(String wordId, char[] surface, int off, int len) { return null; }
public String getPath() { return pathStr; }
public class Main {     public static double devsq(double[] v) {         int n = v.length;         if (n == 0 || v == null) return Double.NaN;         double m = 0;         double s = 0;         for (int i = 0; i < n; i++) {             m += v[i];         }         m /= n;         for (int i = 0; i < n; i++) {             s += Math.pow(v[i] - m, 2);         }         return s;     } }
InvokeOptions options = new InvokeOptions().withRequestMarshaller(DescribeResizeRequestMarshaller.getInstance()).withResponseUnmarshaller(DescribeResizeResponseUnmarshaller.getInstance()); DescribeResizeResponse response = client.invoke(DescribeResize.class, request, options);
public boolean hasPassedThroughNonGreedyDecision() { return passedThroughNonGreedyDecision; }
}
void traverse(SimpleCellWalkContext ctx, ICellHandler handler) {     int width = 1 + firstColumn - lastColumn;     ctx.setRange(FirstRow, firstRow, LastRow, lastRow, FirstColumn, firstColumn, LastColumn, lastColumn);     IRow currentRow = null;     ICell currentCell = null;     for (int rowNumber = firstRow; rowNumber <= lastRow; rowNumber++) {         currentRow = ctx.getSheet().getRow(rowNumber);         if (currentRow == null) continue;         for (int colNumber = firstColumn; colNumber <= lastColumn; colNumber++) {             currentCell = currentRow.getCell(colNumber);             if (currentCell == null) continue;             if (ctx.traverseEmptyCells() && currentCell.isEmpty()) continue;             handler.onCell(currentCell, ctx);         }     } }
public int getReadIndex() { return _readIndex; }
public int compareTo(ScoreTerm other) {      if (BytesEquals(Term, other.Term)) {          if (Boost == other.Boost) return 0;          else return Boost < other.Boost ? -1 : 1;      }      return Term.compareTo(other.Term);  }
for (int i = 0; i < len; i++) { switch (s[i]) { case 'ھ': case 'ﻫ': case 'ﻪ': case 'ﻫ': case 'ﻩ': case 'ﯿ': case 'ﻳ': case 'ﯾ': case 'ﻲ': case 'ﻳ': case 'ﮏ': case 'ﻙ': case 'ﯽ': case 'ﻱ': break; default: break; } }
public void serialize(ILittleEndianOutput out1) {     out1.writeShort(); }
public class DiagnosticErrorListener implements ErrorListener {      @Override public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol, int line, int charPositionInLine, String msg, RecognitionException e) {}     @Override public void reportAmbiguity(Parser parser, DFA.LabeledDecisionState dfaState, int startIndex, int stopIndex, String[] conflictingAlts, String message) {}     @Override public void reportContextSensitivity(Parser parser, DFA.LabeledDecisionState dfaState, int startIndex, int stopIndex, String[] conflictingAlts, String message) {}     @Override public void reportAttemptingFullContext(Parser parser, DFA.LabeledDecisionState dfaState, int startIndex, int stopIndex, String[] conflictingAlts, String message) {} }
public class KeySchemaElement {      private KeyType keyType;      private String attributeName;      public KeyType getKeyType(){ return keyType; }      public void setKeyType(KeyType keyType){ this.keyType = keyType; }      public String getAttributeName(){ return attributeName; }      public void setAttributeName(String attributeName){ this.attributeName = attributeName; }  }
InvokeOptions options = new InvokeOptions().withRequestMarshaller(GetAssignmentRequestMarshaller.getInstance()).withResponseUnmarshaller(GetAssignmentResponseUnmarshaller.getInstance()); GetAssignmentRequest request = new GetAssignmentRequest(); GetAssignmentResponse response = client.invoke(GetAssignment.class, request, options);
public boolean hasObject(AnyObjectId id) { return findOffset(id) != -1; }
public boolean setAllGroups(GroupingSearch allGroups) { return allGroups; }
public void setMultiValued(String dimName, boolean v) {      lock      {          if (fieldTypes.TryGetValue(dimName, out DimConfig fieldType))          {              if (v != fieldType.IsMultiValued)              {                  fieldType.IsMultiValued = v;                  fieldTypes[dimName] = fieldType;              }          }          else          {              fieldTypes[dimName] = new DimConfig { IsMultiValued = v };          }      }  }
int size = 0; for (char c : Keys.cells) { size++; } for (int e = 0; e < cmd.e; e++) { Cell At = GetCellsVal(e); if (At.size >= 0) { return size; } }
InvokeOptions options = new InvokeOptions().withRequestMarshaller(DeleteVoiceConnectorRequestMarshaller.getInstance()).withResponseUnmarshaller(DeleteVoiceConnectorResponseUnmarshaller.getInstance());
InvokeOptions options = new InvokeOptions().withRequestMarshaller(DeleteLifecyclePolicyRequestMarshaller.getInstance()).withResponseUnmarshaller(DeleteLifecyclePolicyResponseUnmarshaller.getInstance());
