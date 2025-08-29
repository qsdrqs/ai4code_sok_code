void (  LittleEndianOutput out ) { out . writeShort ; }
void (  BlockList < T > src ) { if ( src . size == 0 ) srcDirIdx = 0 ; for ( ; srcDirIdx < src . tailDirIdx ; srcDirIdx ++ ) addAll ( src . directory [ srcDirIdx ] , 0 , BLOCK_SIZE ) ; if ( src . tailBlkIdx != 0 ) addAll ( src . tailBlock , 0 , src . tailBlkIdx ) ; }
void (  b ) { if ( ) { if ( currentBlock != null ) { addBlock ( currentBlock ) ; } currentBlock = new [ blockSize ] ; upto = 0 ; } currentBlock [ upto ++ ] = b ; }
ObjectId (  ) { }
DeleteDomainEntryResult (  DeleteDomainEntryRequest request ) { request = beforeClientExecution ( request ) ; return executeDeleteDomainEntry ; }
(  ) { return ( ( termOffsets != null ) ? termOffsets . ramBytesUsed ( ) : 0 ) + ( ( ) ? termsDictOffsets . ramBytesUsed ( ) : 0 ) ; }
public final String (  ) { [ ] raw = buffer ; msgB = RawParseUtils . tagMessage ; if ( msgB < 0 ) { return "" ; } return RawParseUtils . decode ( guessEncoding ( ) , raw , msgB , raw . length ) ; }
POIFSFileSystem ( ) { (  true ) ; _header . setBATCount ; _header . setBATArray ( new [ ] { 1 } ) ; BATBlock bb = BATBlock . createEmptyBATBlock ( bigBlockSize , false ) ; bb . setOurBlockIndex ( 1 ) ; _bat_blocks . add ( bb ) ; setNextBlock ( 0 , POIFSConstants . END_OF_CHAIN ) ; setNextBlock ( 1 , POIFSConstants . FAT_SECTOR_BLOCK ) ; _property_table . setStartBlock ( 0 ) ;
void (  address ) { slice = pool . buffers [ address >> ByteBlockPool . BYTE_BLOCK_SHIFT ] ; assert slice != null ; upto = address & ByteBlockPool . BYTE_BLOCK_MASK ; offset0 = address ; assert upto < ; }
SubmoduleAddCommand (  String path ) { = path ; return this ; }
ListIngestionsResult (  ) { request = beforeClientExecution ( request ) ; return executeListIngestions ( request ) ; }
QueryParserTokenManager ( CharStream stream , lexState ) { ; SwitchTo ( lexState ) ; }
GetShardIteratorResult (  GetShardIteratorRequest request ) { request = beforeClientExecution ( request ) ; return executeGetShardIterator ; }
ModifyStrategyRequest ( ) { super ( "aegis" , "2016-11-11" , "ModifyStrategy" , "vipaegis" ) ; (  ) ;
boolean (  ) throws IOException { synchronized ( lock ) { if ( in == null ) { throw new IOException ( "InputStreamReader is closed" ) ; } try { return bytes . hasRemaining || in . available ( ) > 0 ; } catch ( e ) { return false ; } }
EscherOptRecord (  ) { }
public synchronized (  [ ] buffer , offset , length ) { if ( buffer == null ) { throw new NullPointerException ( "buffer == null" ) ; } Arrays . checkOffsetAndCount ( buffer . length , offset , length ) ; if ( length == 0 ) { return 0 ; } copylen = count - pos < length ? count - pos : length ; for ( i = 0 ; i < copylen ; i ++ ) { buffer [ offset + i ] = ( ) this . buffer . charAt ( pos + i ) ; } ; return copylen ; }
OpenNLPSentenceBreakIterator ( ) { this . sentenceOp = sentenceOp ; }
void (  String str ) { write ( ? str : String . valueOf ( ( Object ) null ) ) ; }
NotImplementedFunctionException ( String functionName , NotImplementedException cause ) { super ( functionName , cause ) ; = functionName ; }
V (  ) { return super . nextEntry . getValue ( ) ; }
public final void (  [ ] b , offset , len , boolean useBuffer ) throws IOException { available = bufferLength - bufferPosition ; if ( len <= available ) { if ( len > 0 ) System . arraycopy ( buffer , bufferPosition , b , offset , len ) ; bufferPosition += len ; } else { if ( available > 0 ) { System . arraycopy ( buffer , bufferPosition , b , offset , available ) ; offset += available ; len -= available ; bufferPosition += available ; } if ( useBuffer && len < bufferSize ) { refill ( ) ; if ( bufferLength < len ) { System . arraycopy ( buffer , 0 , b , offset , bufferLength ) ; throw new EOFException ( "read past EOF: " + this ) ; } else { System . arraycopy ( buffer , 0 , b , offset , len ) ; ; } } else { after = bufferStart + bufferPosition + len ; if ( after > length ( ) ) throw new EOFException ( "read past EOF: " + this ) ; readInternal ( b , offset , len ) ; bufferStart = after ; bufferPosition = 0 ; bufferLength = 0 ; } } }
TagQueueResult (  TagQueueRequest request ) { request = beforeClientExecution ; return executeTagQueue ( request ) ; }
void (  ) { throw new UnsupportedOperationException ; }
CacheSubnetGroup (  ) { request = beforeClientExecution ( request ) ; return executeModifyCacheSubnetGroup ( request ) ; }
void (  String params ) { super . setParams ( params ) ; language = country = variant = "" ; StringTokenizer st = new StringTokenizer ( params , "," ) ; if ( st . hasMoreTokens ( ) ) language = st . nextToken ( ) ; if ( st . hasMoreTokens ( ) ) country = st . nextToken ( ) ; if ( st . hasMoreTokens ( ) ) variant = st . nextToken ; }
DeleteDocumentationVersionResult (  DeleteDocumentationVersionRequest request ) { request = beforeClientExecution ; return executeDeleteDocumentationVersion ( request ) ; }
boolean (  Object obj ) { if ( ! ( obj instanceof FacetLabel ) ) { return false ; } FacetLabel other = ( FacetLabel ) obj ; if ( length != other . length ) { return false ; } for ( i = length - 1 ; ; i -- ) { if ( ! components [ i ] . equals ( other . components [ i ] ) ) { return false ; } } return true ; }
GetInstanceAccessDetailsResult (  GetInstanceAccessDetailsRequest request ) { request = beforeClientExecution ( request ) ; return executeGetInstanceAccessDetails ; }
HSSFPolygon (  HSSFChildAnchor anchor ) { HSSFPolygon shape = new HSSFPolygon ( this , anchor ) ; shape . setParent ; shape . setAnchor ( anchor ) ; shapes . add ( shape ) ; onCreate ( shape ) ; return shape ; }
String (  sheetIndex ) { return getBoundSheetRec ( sheetIndex ) . getSheetname ; }
GetDashboardResult (  GetDashboardRequest request ) { request = beforeClientExecution ( request ) ; return executeGetDashboard ; }
AssociateSigninDelegateGroupsWithAccountResult (  AssociateSigninDelegateGroupsWithAccountRequest request ) { request = beforeClientExecution ; return executeAssociateSigninDelegateGroupsWithAccount ( request ) ; }
void (  MulBlankRecord mbr ) { for ( j = 0 ; j < mbr . getNumColumns ( ) ; j ++ ) { BlankRecord br = new BlankRecord ( ) ; br . setColumn ( ( ) ( j + mbr . getFirstColumn ( ) ) ) ; br . setRow ( mbr . getRow ( ) ) ; br . setXFIndex ( mbr . getXFAt ) ; insertCell ( br ) ; } }
public static String (  ) { StringBuilder sb = new StringBuilder ( ) ; sb . append ( "\\Q" ) ; apos = 0 ; ; while ( ( k = string . indexOf ( "\\E" , apos ) ) >= 0 ) { sb . append ( string . substring ( apos , k + 2 ) ) . append ( "\\\\E\\Q" ) ; apos = k + 2 ; } return sb . append ( string . substring ( apos ) ) . append ( "\\E" ) . toString ( ) ; }
ByteBuffer (  value ) { throw new ReadOnlyBufferException ; }
ArrayPtg ( Object values2d ) { nColumns = values2d [ 0 ] . length ; nRows = values2d . length ; _nColumns = ( ) nColumns ; _nRows = ( ) nRows ; Object [ ] vv = new Object [ _nColumns * _nRows ] ; for ( r = 0 ; r < nRows ; r ++ ) { Object [ ] rowData = values2d [ r ] ; for ( c = 0 ; c < nColumns ; c ++ ) { vv [ getValueIndex ( c , r ) ] = rowData [ c ] ; } } _arrayValues = vv ; _reserved0Int = 0 ; _reserved1Short = 0 ; _reserved2Byte = 0 ; }
GetIceServerConfigResult (  GetIceServerConfigRequest request ) { request = beforeClientExecution ; return executeGetIceServerConfig ( request ) ; }
String (  ) { return getClass ( ) . getName + " [" + getValueAsString ( ) + "]" ; }
String (  ) { return "ToChildBlockJoinQuery (" + parentQuery . toString ( ) + ")" ; }
void (  ) { refCount . incrementAndGet ( ) ; }
UpdateConfigurationSetSendingEnabledResult (  UpdateConfigurationSetSendingEnabledRequest request ) { request = beforeClientExecution ; return executeUpdateConfigurationSetSendingEnabled ( request ) ; }
(  ) { return getXBATEntriesPerBlock ( ) * ; }
void (  pow10 ) { TenPower tp = TenPower . getInstance ( Math . abs ( pow10 ) ) ; if ( pow10 < 0 ) { mulShift ( , tp . _divisorShift ) ; } else { mulShift ( tp . _multiplicand , tp . _multiplierShift ) ; } }
String (  ) { StringBuilder b = new StringBuilder ( ) ; l = length ; b . append ( File . separatorChar ) ; for ( i = 0 ; i < l ; i ++ ) { b . append ( getComponent ( i ) ) ; if ( i < l - 1 ) { b . append ( File . separatorChar ) ; } } return b . toString ( ) ; }
InstanceProfileCredentialsProvider (  ECSMetadataServiceCredentialsFetcher fetcher ) { this . fetcher = fetcher ; . setRoleName ( roleName ) ; return this ; }
void (  ProgressMonitor pm ) { ; }
void (  ) { if ( ! first ) { ptr = 0 ; if ( ! eof ( ) ) parseEntry ( ) ; } }
E (  ) { if ( iterator . previousIndex ( ) >= start ) { return iterator . previous ( ) ; } throw new NoSuchElementException ; }
String (  ) { return ; }
(  value ) { for ( i = 0 ; i < mSize ; i ++ ) if ( mValues [ i ] == value ) return - 1 ; }
List < CharsRef > (  word [ ] , length ) { List < CharsRef > stems = stem ( word , length ) ; if ( stems . size ( ) < 2 ) { return stems ; } CharArraySet terms = new CharArraySet ( 8 , dictionary . ignoreCase ) ; List < CharsRef > deduped = new ArrayList < > ( ) ; for ( CharsRef s : stems ) { if ( ! terms . contains ( s ) ) { deduped . add ; terms . add ( s ) ; } } return deduped ; }
GetGatewayResponsesResult (  GetGatewayResponsesRequest request ) { request = beforeClientExecution ( request ) ; return executeGetGatewayResponses ; }
void (  pos ) { currentBlockIndex = ( ) ( pos >> blockBits ) ; currentBlock = ; currentBlockUpto = ( ) ( pos & blockMask ) ; }
(  n ) { s = ( ) Math . min ( available ( ) , Math . max ( 0 , n ) ) ; ptr += s ; }
BootstrapActionDetail ( ) { setBootstrapActionConfig ( bootstrapActionConfig ) ; }
void (  LittleEndianOutput out ) { out . writeShort ( field_1_row ) ; out . writeShort ( field_2_col ) ; out . writeShort ( field_3_flags ) ; out . writeShort ( field_4_shapeid ) ; out . writeShort ( field_6_author . length ( ) ) ; out . writeByte ( field_5_hasMultibyte ? 0x01 : 0x00 ) ; if ( field_5_hasMultibyte ) { StringUtil . putUnicodeLE ; } else { StringUtil . putCompressedUnicode ( field_6_author , out ) ; } if ( field_7_padding != null ) { out . writeByte ( field_7_padding . intValue ( ) ) ; } }
(  String string ) { return lastIndexOf ; }
boolean (  E object ) { return addLastImpl ; }
void (  , String subsection ) { ConfigSnapshot , ; do { src = state . get ( ) ; res = unsetSection ( src , section , subsection ) ; } while ( ! state . compareAndSet ( src , res ) ) ; }
String (  ) { return tagName ; }
void (  index , SubRecord element ) { subrecords . add ; }
boolean (  ) { synchronized ( mutex ) { return delegate ( ) . remove ( o ) ; } }
DoubleMetaphoneFilter (  TokenStream input ) { return new DoubleMetaphoneFilter ; }
(  ) { return inCoreLength ; }
void (  boolean newValue ) { ; }
Pair ( ContentSource oldSource , ) { this . oldSource = oldSource ; this . newSource = newSource ; }
(  i ) { if ( ) throw new ArrayIndexOutOfBoundsException ( i ) ; return entries [ i ] ; }
CreateRepoRequest ( ) { super ( "cr" , "2016-06-07" , "CreateRepo" , "cr" ) ; (  "/repos" ) ; setMethod ( ) ;
boolean (  ) { }
void (  ) { if ( expectedModCount == list . modCount ) { if ( lastLink != null ) { Link next = lastLink . next ; Link < ET > previous = lastLink . previous ; next . previous = previous ; previous . next = next ; if ( lastLink == link ) { pos -- ; } link = previous ; lastLink = null ; expectedModCount ++ ; list . size -- ; list . modCount ++ ; } else { throw new IllegalStateException ( ) ; } } else { throw new ConcurrentModificationException ( ) ; } }
MergeShardsResult (  ) { request = beforeClientExecution ( request ) ; return executeMergeShards ( request ) ; }
AllocateHostedConnectionResult (  AllocateHostedConnectionRequest request ) { request = beforeClientExecution ( request ) ; return executeAllocateHostedConnection ; }
(  ) { }
public static final WeightedTerm (  Query query ) { return getTerms ( query , false ) ; }
ByteBuffer (  ) { throw new ReadOnlyBufferException ; }
void (  [ ] blocks , blocksOffset , [ ] values , valuesOffset , iterations ) { for ( i = 0 ; i < iterations ; ++ i ) { byte0 = blocks [ blocksOffset ++ ] & 0xFF ; values [ valuesOffset ++ ] = byte0 >>> 2 ; byte1 = blocks [ blocksOffset ++ ] & 0xFF ; values [ valuesOffset ++ ] = ( ( byte0 & 3 ) << 4 ) | ( ) ; byte2 = blocks [ blocksOffset ++ ] & 0xFF ; values [ valuesOffset ++ ] = ( ( byte1 & 15 ) << 2 ) | ( byte2 >>> 6 ) ; values [ valuesOffset ++ ] = byte2 & 63 ; } }
String (  ) throws  { String s = getPath ( ) ; if ( "/" . equals || "" . equals ( s ) ) s = getHost ( ) ; if ( s == null ) throw new IllegalArgumentException ( ) ; String [ ] ; if ( "file" . equals ( scheme ) || LOCAL_FILE . matcher ( s ) . matches ( ) ) elements = s . split ( "[\\" + File . separatorChar + "/]" ) ; elseelements = s . split ( "/+" ) ; if ( elements . length == 0 ) throw new IllegalArgumentException ( ) ; String result = elements [ elements . length - 1 ] ; if ( Constants . DOT_GIT . equals ( result ) ) result = elements [ elements . length - 2 ] ; else if ( result . endsWith ( Constants . DOT_GIT_EXT ) ) result = result . substring ( 0 , result . length ( ) - Constants . DOT_GIT_EXT . length ( ) ) ; return result ; }
DescribeNotebookInstanceLifecycleConfigResult (  DescribeNotebookInstanceLifecycleConfigRequest request ) { request = beforeClientExecution ; return executeDescribeNotebookInstanceLifecycleConfig ( request ) ; }
String (  ) { return ; }
CreateVpnConnectionResult (  ) { request = beforeClientExecution ( request ) ; return executeCreateVpnConnection ( request ) ; }
DescribeVoicesResult (  ) { request = beforeClientExecution ( request ) ; return executeDescribeVoices ( request ) ; }
ListMonitoringExecutionsResult (  ListMonitoringExecutionsRequest request ) { request = beforeClientExecution ( request ) ; return executeListMonitoringExecutions ; }
DescribeJobRequest ( , String jobId ) { setVaultName ( vaultName ) ; setJobId ( jobId ) ; }
EscherRecord (  index ) { return escherRecords . get ; }
GetApisResult (  GetApisRequest request ) { request = beforeClientExecution ; return executeGetApis ( request ) ; }
DeleteSmsChannelResult (  DeleteSmsChannelRequest request ) { request = beforeClientExecution ( request ) ; return executeDeleteSmsChannel ; }
TrackingRefUpdate (  ) { }
void (  ) { print ( String . valueOf ( b ) ) ; }
QueryNode (  ) { return getChildren . get ( 0 ) ; }
NotIgnoredFilter ( workdirTreeIndex ) { = workdirTreeIndex ; }
AreaRecord ( RecordInputStream in ) { field_1_formatFlags = in . readShort ; }
GetThumbnailRequest (  ProtocolType . HTTPS ) ;
DescribeTransitGatewayVpcAttachmentsResult (  ) { request = beforeClientExecution ( request ) ; return executeDescribeTransitGatewayVpcAttachments ( request ) ; }
PutVoiceConnectorStreamingConfigurationResult (  ) { request = beforeClientExecution ( request ) ; return executePutVoiceConnectorStreamingConfiguration ( request ) ; }
OrdRange (  ) { return prefixToOrdRange . get ( dim ) ; }
String (  ) { String symbol = "" ; if ( startIndex >= 0 && startIndex < getInputStream ( ) . size ) { symbol = getInputStream ( ) . getText ( Interval . of ( startIndex , startIndex ) ) ; symbol = Utils . escapeWhitespace ( symbol , false ) ; } return String . format ( Locale . getDefault ( ) , "%s('%s')" , LexerNoViableAltException . class . getSimpleName ( ) , symbol ) ; }
E (  ) { return peekFirstImpl ; }
CreateWorkspacesResult (  ) { request = beforeClientExecution ( request ) ; return executeCreateWorkspaces ( request ) ; }
NumberFormatIndexRecord (  ) { return copy ; }
DescribeRepositoriesResult (  ) { request = beforeClientExecution ( request ) ; return executeDescribeRepositories ( request ) ; }
SparseIntArray ( initialCapacity ) { initialCapacity = ArrayUtils . idealIntArraySize ( initialCapacity ) ; mKeys = new [ initialCapacity ] ; mValues = new ; mSize = 0 ; }
HyphenatedWordsFilter (  ) { return new HyphenatedWordsFilter ( input ) ; }
CreateDistributionWithTagsResult (  ) { request = beforeClientExecution ( request ) ; return executeCreateDistributionWithTags ( request ) ; }
RandomAccessFile ( String fileName , String mode ) throws FileNotFoundException { ( new File ( fileName ) ) ; }
DeleteWorkspaceImageResult (  DeleteWorkspaceImageRequest request ) { request = beforeClientExecution ( request ) ; return executeDeleteWorkspaceImage ; }
public static String (  value ) { StringBuilder sb = new StringBuilder ( 16 ) ; writeHex ( sb , value , 16 , "" ) ; return sb . toString ; }
UpdateDistributionResult (  UpdateDistributionRequest request ) { request = beforeClientExecution ( request ) ; return executeUpdateDistribution ; }
HSSFColor (  index ) { if ( index == HSSFColorPredefined . AUTOMATIC . getIndex ( ) ) { return HSSFColorPredefined . AUTOMATIC . getColor ( ) ; } [ ] b = _palette . getColor ( index ) ; return ( b == null ) ? null : new CustomColor ; }
ValueEval (  ValueEval operands , srcRow , srcCol ) { throw new NotImplementedFunctionException ( _functionName ) ; }
void (  ) { out . writeShort ( ( ) field_1_number_crn_records ) ; out . writeShort ( ( ) field_2_sheet_table_index ) ; }
DescribeDBEngineVersionsResult (  ) { return describeDBEngineVersions ( new DescribeDBEngineVersionsRequest ) ; }
FormatRun ( character , fontIndex ) { this . _character = character ; = fontIndex ; }
public static [ ] (  [ ] chars , offset , length ) { [ ] result = new [ length * 2 ] ; end = offset + length ; resultIndex = 0 ; for ( i = offset ; i < end ; ++ i ) { ch = chars [ i ] ; result [ resultIndex ++ ] = ( ) ( ch >> 8 ) ; result [ ] = ( ) ch ; } return result ; }
UploadArchiveResult (  UploadArchiveRequest request ) { request = beforeClientExecution ; return executeUploadArchive ( request ) ; }
List (  tokenIndex ) { return getHiddenTokensToLeft ( tokenIndex , - 1 ) ; }
boolean (  Object obj ) { if ( this == obj ) return true ; if ( ! super . equals ( obj ) ) return false ; if ( getClass != obj . getClass ( ) ) return false ; AutomatonQuery other = ( AutomatonQuery ) obj ; if ( ! compiled . equals ( other . compiled ) ) return false ; if ( term == null ) { if ( other . term != null ) return false ; } else if ( ! term . equals ( other . term ) ) return false ; return true ; }
SpanQuery (  ) { SpanQuery [ ] spanQueries = new SpanQuery [ size ( ) ] ; Iterator sqi = weightBySpanQuery . keySet ( ) . iterator ( ) ; i = 0 ; while ( sqi . hasNext ( ) ) { SpanQuery sq = sqi . next ( ) ; boost = weightBySpanQuery . get ( sq ) ; if ( boost != 1f ) { sq = new SpanBoostQuery ( sq , boost ) ; } spanQueries [ i ++ ] = sq ; } if ( spanQueries . length == 1 ) return spanQueries [ 0 ] ; elsereturn  SpanOrQuery ( spanQueries ) ; }
StashCreateCommand (  ) { return new StashCreateCommand ; }
FieldInfo (  ) { return byName . get ( fieldName ) ; }
DescribeEventSourceResult (  DescribeEventSourceRequest request ) { request = beforeClientExecution ; return executeDescribeEventSource ( request ) ; }
GetDocumentAnalysisResult (  ) { request = beforeClientExecution ( request ) ; return executeGetDocumentAnalysis ( request ) ; }
CancelUpdateStackResult (  ) { request = beforeClientExecution ( request ) ; return executeCancelUpdateStack ( request ) ; }
ModifyLoadBalancerAttributesResult (  ModifyLoadBalancerAttributesRequest request ) { request = beforeClientExecution ; return executeModifyLoadBalancerAttributes ( request ) ; }
SetInstanceProtectionResult (  ) { request = beforeClientExecution ( request ) ; return executeSetInstanceProtection ( request ) ; }
ModifyDBProxyResult (  ModifyDBProxyRequest request ) { request = beforeClientExecution ( request ) ; return executeModifyDBProxy ; }
void (  [ ] output , offset , len , endOffset , posLength ) { if ( count == outputs . length ) { outputs = ArrayUtil . grow ( outputs , count + 1 ) ; } if ( count == endOffsets . length ) { [ ] next = new [ ArrayUtil . oversize ( 1 + count , Integer . BYTES ) ] ; System . arraycopy ( endOffsets , 0 , next , 0 , count ) ; endOffsets = next ; } if ( count == posLengths . length ) { [ ] next = new [ ArrayUtil . oversize ( 1 + count , Integer . BYTES ) ] ; System . arraycopy ( posLengths , 0 , next , 0 , count ) ; posLengths = next ; } if ( outputs [ count ] == null ) { outputs [ count ] = new CharsRefBuilder ( ) ; } . copyChars ( output , offset , len ) ; endOffsets [ count ] = endOffset ; posLengths [ count ] = posLength ; count ++ ; }
FetchLibrariesRequest ( ) { super ( "CloudPhoto" , "2017-07-11" , "FetchLibraries" , "cloudphoto" ) ; (  ) ;
boolean (  ) { return fs . exists ; }
FilterOutputStream ( ) { this . out = out ; }
ScaleClusterRequest (  "/clusters/[ClusterId]" ) ; setMethod ( MethodType . PUT ) ;
DataValidationConstraint (  operatorType , , String formula2 ) { return DVConstraint . createTimeConstraint ( operatorType , formula1 , formula2 ) ; }
ListObjectParentPathsResult (  ) { request = beforeClientExecution ( request ) ; return executeListObjectParentPaths ( request ) ; }
DescribeCacheSubnetGroupsResult (  DescribeCacheSubnetGroupsRequest request ) { request = beforeClientExecution ( request ) ; return executeDescribeCacheSubnetGroups ; }
void (  ) { field_5_options = sharedFormula . setShortBoolean ( field_5_options , flag ) ; }
boolean (  ) { }
ErrorNode (  Token badToken ) { ErrorNodeImpl t = new ErrorNodeImpl ( badToken ) ; addAnyChild ( t ) ; t . setParent ; return t ; }
LatvianStemFilterFactory ( Map args ) { ( args ) ; if ( ! args . isEmpty ( ) ) { throw new IllegalArgumentException ( "Unknown parameters: " + args ) ; } }
EventSubscription (  ) { request = beforeClientExecution ( request ) ; return executeRemoveSourceIdentifierFromSubscription ( request ) ; }
public static TokenFilterFactory (  , Map < String , String > args ) { return loader . newInstance ( name , args ) ; }
AddAlbumPhotosRequest ( ) { super ( "CloudPhoto" , "2017-07-11" , "AddAlbumPhotos" , "cloudphoto" ) ; (  ) ;
GetThreatIntelSetResult (  GetThreatIntelSetRequest request ) { request = beforeClientExecution ( request ) ; return executeGetThreatIntelSet ; }
RevFilter (  ) { return new Binary ( a . clone ( ) , b . clone ) ; }
boolean (  Object o ) { return ; }
boolean (  ) { return protectedHasArray ( ) ; }
UpdateContributorInsightsResult (  UpdateContributorInsightsRequest request ) { request = beforeClientExecution ; return executeUpdateContributorInsights ( request ) ; }
void (  ) { records . remove ( fileShare ) ; records . remove ( writeProtect ) ; ; writeProtect = null ; }
SolrSynonymParser ( , boolean expand , Analyzer analyzer ) { super ( dedup , analyzer ) ; this . expand = expand ; }
RequestSpotInstancesResult (  ) { request = beforeClientExecution ( request ) ; return executeRequestSpotInstances ( request ) ; }
[ ] (  ) { return findObjectRecord . getObjectData ( ) ; }
GetContactAttributesResult (  GetContactAttributesRequest request ) { request = beforeClientExecution ; return executeGetContactAttributes ( request ) ; }
String (  ) { return getKey + ": " + getValue ( ) ; }
ListTextTranslationJobsResult (  ) { request = beforeClientExecution ( request ) ; return executeListTextTranslationJobs ( request ) ; }
GetContactMethodsResult (  ) { request = beforeClientExecution ( request ) ; return executeGetContactMethods ( request ) ; }
public static (  ) { FunctionMetadata fd = getInstance ( ) . getFunctionByNameInternal ( name ) ; if ( fd == null ) { fd = getInstanceCetab ( ) . getFunctionByNameInternal ( name ) ; if ( fd == null ) { return - 1 ; } } return ( ) fd . getIndex ( ) ; }
DescribeAnomalyDetectorsResult (  DescribeAnomalyDetectorsRequest request ) { request = beforeClientExecution ; return executeDescribeAnomalyDetectors ( request ) ; }
public static String (  String message , ObjectId changeId ) { return insertId ; }
(  AnyObjectId objectId , typeHint ) throws MissingObjectException , IncorrectObjectTypeException , IOException { sz = db . getObjectSize ; if ( sz < 0 ) { if ( typeHint == OBJ_ANY ) throw new MissingObjectException ( objectId . copy ( ) , JGitText . get ( ) . unknownObjectType2 ) ; throw new MissingObjectException ( objectId . copy ( ) , typeHint ) ; } return sz ; }
ImportInstallationMediaResult (  ) { request = beforeClientExecution ( request ) ; return executeImportInstallationMedia ( request ) ; }
PutLifecycleEventHookExecutionStatusResult (  ) { request = beforeClientExecution ( request ) ; return executePutLifecycleEventHookExecutionStatus ( request ) ; }
NumberPtg ( ) { ( in . readDouble ( ) ) ; }
GetFieldLevelEncryptionConfigResult (  GetFieldLevelEncryptionConfigRequest request ) { request = beforeClientExecution ; return executeGetFieldLevelEncryptionConfig ( request ) ; }
DescribeDetectorResult (  DescribeDetectorRequest request ) { request = beforeClientExecution ( request ) ; return executeDescribeDetector ; }
ReportInstanceStatusResult (  ReportInstanceStatusRequest request ) { request = beforeClientExecution ; return executeReportInstanceStatus ( request ) ; }
DeleteAlarmResult (  DeleteAlarmRequest request ) { request = beforeClientExecution ( request ) ; return executeDeleteAlarm ; }
TokenStream (  ) { return new PortugueseStemFilter ( input ) ; }
FtCblsSubRecord ( ) { reserved = new ;
public boolean (  Object object ) { synchronized ( mutex ) { return c . remove ( object ) ; } }
GetDedicatedIpResult (  ) { request = beforeClientExecution ( request ) ; return executeGetDedicatedIp ( request ) ; }
String (  ) { return ; }
ListStreamProcessorsResult (  ListStreamProcessorsRequest request ) { request = beforeClientExecution ; return executeListStreamProcessors ( request ) ; }
DeleteLoadBalancerPolicyRequest ( String loadBalancerName , ) { setLoadBalancerName ( loadBalancerName ) ; setPolicyName ( policyName ) ; }
WindowProtectRecord ( options ) { ; }
UnbufferedCharStream ( bufferSize ) { ; data = new [ bufferSize ] ; }
GetOperationsResult (  GetOperationsRequest request ) { request = beforeClientExecution ( request ) ; return executeGetOperations ; }
void (  [ ] b , o ) { NB . encodeInt32 ( b , o , w1 ) ; NB . encodeInt32 ( b , o + 4 , w2 ) ; NB . encodeInt32 ( b , o + 8 , w3 ) ; NB . encodeInt32 ( b , o + 12 , w4 ) ; NB . encodeInt32 ( b , , w5 ) ; }
WindowOneRecord ( RecordInputStream in ) { field_1_h_hold = in . readShort ( ) ; field_2_v_hold = in . readShort ( ) ; field_3_width = in . readShort ( ) ; field_4_height = in . readShort ; field_5_options = in . readShort ( ) ; field_6_active_sheet = in . readShort ( ) ; field_7_first_visible_tab = in . readShort ( ) ; field_8_num_selected_tabs = in . readShort ( ) ; field_9_tab_width_ratio = in . readShort ( ) ; }
StopWorkspacesResult (  StopWorkspacesRequest request ) { request = beforeClientExecution ; return executeStopWorkspaces ( request ) ; }
void (  ) throws IOException { if ( isOpen ) { isOpen = false ; try { dump ( ) ; } finally { try { channel . truncate ( fileLength ) ; } finally { try { channel . close ( ) ; } finally { fos . close ; } } } }
DescribeMatchmakingRuleSetsResult (  ) { request = beforeClientExecution ( request ) ; return executeDescribeMatchmakingRuleSets ( request ) ; }
String (  wordId , surface [ ] , off , len ) { }
String (  ) { }
public static (  [ ] v ) { r = Double . NaN ; if ( v != null && v . length >= 1 ) { m = 0 ; s = 0 ; n = v . length ; for ( i = 0 ; i < n ; i ++ ) { s += v [ i ] ; } m = s / n ; s = 0 ; for ( i = 0 ; i < n ; i ++ ) { s += ( v [ i ] - m ) * ( v [ i ] - m ) ; } r = ( n == 1 ) ? 0 : s ; } }
DescribeResizeResult (  ) { request = beforeClientExecution ( request ) ; return executeDescribeResize ( request ) ; }
boolean (  ) { return passedThroughNonGreedyDecision ; }
(  ) { return end ; }
void (  CellHandler handler ) { firstRow = range . getFirstRow ( ) ; lastRow = range . getLastRow ( ) ; firstColumn = range . getFirstColumn ( ) ; lastColumn = range . getLastColumn ( ) ; width = lastColumn - firstColumn + 1 ; SimpleCellWalkContext ctx = new SimpleCellWalkContext ( ) ; Row currentRow = null ; Cell currentCell = null ; for ( ctx . rowNumber = firstRow ; ctx . rowNumber <= lastRow ; ++ ctx . rowNumber ) { currentRow = sheet . getRow ( ctx . rowNumber ) ; if ( currentRow == null ) { continue ; } for ( ctx . colNumber = firstColumn ; <= lastColumn ; ++ ctx . colNumber ) { currentCell = currentRow . getCell ( ctx . colNumber ) ; if ( currentCell == null ) { continue ; } if ( isEmpty ( currentCell ) && ! traverseEmptyCells ) { continue ; } rowSize = ArithmeticUtils . mulAndCheck ( ( ) ArithmeticUtils . subAndCheck ( ctx . rowNumber , firstRow ) , ( ) width ) ; ctx . ordinalNumber = ArithmeticUtils . addAndCheck ( rowSize , ( ctx . colNumber - firstColumn + 1 ) ) ; handler . onCell ( currentCell , ctx ) ; } } }
(  ) { }
(  ScoreTerm other ) { if ( this . boost == other . boost ) return other . bytes . get ( ) . compareTo ( . get ( ) ) ; elsereturn Float . (  this . boost , other . boost ) ; }
(  s [ ] , len ) { for ( i = 0 ; i < len ; i ++ ) { switch ( s [ i ] ) { case FARSI_YEH : case YEH_BARREE : s [ i ] = YEH ; case KEHEH : s [ i ] = KAF ; break ; case HEH_YEH : case HEH_GOAL : s [ i ] = HEH ; break ; case HAMZA_ABOVE : len = delete ( s , i , len ) ; i -- ; break ; : break ; } } return len ; }
void (  LittleEndianOutput out ) { out . writeShort ; }
DiagnosticErrorListener ( ) { this . exactOnly = exactOnly ; }
KeySchemaElement ( String attributeName , ) { setAttributeName ( attributeName ) ; setKeyType ( keyType . toString ( ) ) ; }
GetAssignmentResult (  ) { request = beforeClientExecution ( request ) ; return executeGetAssignment ( request ) ; }
boolean (  AnyObjectId id ) { return findOffset ( id ) != ; }
GroupingSearch (  boolean allGroups ) { = allGroups ; return this ; }
public synchronized void (  , boolean v ) { DimConfig ft = fieldTypes . get ( dimName ) ; if ( ft == null ) { ft = new DimConfig ( ) ; fieldTypes . put ( dimName , ft ) ; } ft . multiValued = v ; }
(  ) { Iterator < Character > i = cells . keySet ( ) . iterator ; size = 0 ; for ( ; i . hasNext ( ) ; ) { Character c = i . next ( ) ; Cell e = at ( c ) ; if ( e . cmd >= 0 ) { size ++ ; } } return size ; }
DeleteVoiceConnectorResult (  DeleteVoiceConnectorRequest request ) { request = beforeClientExecution ; return executeDeleteVoiceConnector ( request ) ; }
DeleteLifecyclePolicyResult (  DeleteLifecyclePolicyRequest request ) { request = beforeClientExecution ; return executeDeleteLifecyclePolicy ( request ) ; }
void (  b ) { len = b . length ; checkPosition ( len ) ; System . arraycopy ( b , 0 , _buf , _writeIndex , len ) ; _writeIndex += len ; }
RebaseResult (  ) { return ; }
public static (  maxNumberOfValuesExpected , desiredSaturation ) { for ( ; i < usableBitSetSizes . length ; i ++ ) { numSetBitsAtDesiredSaturation = ( ) ( usableBitSetSizes [ i ] * desiredSaturation ) ; estimatedNumUniqueValues = getEstimatedNumberUniqueValuesAllowingForCollisions ( usableBitSetSizes [ i ] , numSetBitsAtDesiredSaturation ) ; if ( estimatedNumUniqueValues > maxNumberOfValuesExpected ) { return usableBitSetSizes [ i ] ; } } return - 1 ; }
DescribeDashboardResult (  ) { request = beforeClientExecution ( request ) ; return executeDescribeDashboard ( request ) ; }
CreateSegmentResult (  CreateSegmentRequest request ) { request = beforeClientExecution ; return executeCreateSegment ( request ) ; }
String (  ) { StringBuilder buffer = new StringBuilder ( ) ; buffer . append ; buffer . append ( "    .rowoffset = " ) . append ( HexDump . intToHex ( field_1_row_offset ) ) . append ( "\n" ) ; for ( k = 0 ; k < field_2_cell_offsets . length ; k ++ ) { buffer . append ( "    .cell_" ) . append ( k ) . append ( " = " ) . append ( HexDump . shortToHex ( field_2_cell_offsets [ k ] ) ) . append ( "\n" ) ; } buffer . append ( "[/DBCELL]\n" ) ; return buffer . toString ( ) ; }
List (  ) { return undeletedList ; }
String (  ) { }
MergeScheduler (  ) { }
PlainTextDictionary ( Reader reader ) { in = new BufferedReader ; }
StringBuilder (  CharSequence csq ) { if ( ) { appendNull ( ) ; } else { append0 ( csq , 0 , csq . length ( ) ) ; } return this ; }
ListAssociatedStacksResult (  ListAssociatedStacksRequest request ) { request = beforeClientExecution ; return executeListAssociatedStacks ( request ) ; }
public static (  [ ] v ) { r = 0 ; m = 0 ; s = 0 ; for ( , iSize = v . length ; i < iSize ; i ++ ) { s += v [ i ] ; } m = s / v . length ; s = 0 ; for ( i = 0 , iSize = v . length ; i < iSize ; i ++ ) { s += Math . abs ( v [ i ] - m ) ; } r = s / v . length ; return r ; }
DescribeByoipCidrsResult (  DescribeByoipCidrsRequest request ) { request = beforeClientExecution ( request ) ; return executeDescribeByoipCidrs ; }
GetDiskResult (  GetDiskRequest request ) { request = beforeClientExecution ( request ) ; return executeGetDisk ; }
DBClusterParameterGroup (  CreateDBClusterParameterGroupRequest request ) { request = beforeClientExecution ; return executeCreateDBClusterParameterGroup ( request ) ; }
public static CharBuffer (  [ ] array , start , charCount ) { Arrays . checkOffsetAndCount ( , start , charCount ) ; CharBuffer buf = new ReadWriteCharArrayBuffer ( array ) ; buf . position = start ; buf . limit = start + charCount ; return buf ; }
SubmoduleStatusType (  ) { }
DescribeGameServerGroupResult (  DescribeGameServerGroupRequest request ) { request = beforeClientExecution ; return executeDescribeGameServerGroup ( request ) ; }
Pattern (  ) { }
V (  ) { throw new UnsupportedOperationException ( ) ; }
StringBuilder (  ) { CharSequence cmd = stemmer . getLastOnPath ( word ) ; if ( cmd == null ) return null ; buffer . setLength ( 0 ) ; buffer . append ( word ) ; Diff . apply ( buffer , cmd ) ; if ( buffer . length ( ) > 0 ) return buffer ; elsereturn ; }
RenameFaceRequest (  ProtocolType . HTTPS ) ;
(  Map args , String name ) { return require ( args , name ) . charAt ( 0 ) ; }
public static String (  Tree t ) { return toStringTree ( t , ( List ) null ) ; }
String (  ) { }
GetRepoWebhookLogListRequest (  "/repos/[RepoNamespace]/[RepoName]/webhooks/[WebhookId]/logs" ) ; setMethod ( MethodType . GET ) ;
GetJobUnlockCodeResult (  ) { request = beforeClientExecution ( request ) ; return executeGetJobUnlockCode ( request ) ; }
RemoveTagsRequest ( String resourceId ) { setResourceId ; }
(  ch ) { try { [ ] buffer = Character . toString ( ch ) . getBytes ( "GB2312" ) ; if ( buffer . length != 2 ) { return - 1 ; } b0 = ( buffer [ 0 ] & 0x0FF ) - 161 ; b1 = ( buffer [ 1 ] & 0x0FF ) - 161 ; return ( ) ( + b1 ) ; } catch ( e ) { throw new RuntimeException ( e ) ; } }
BatchRefUpdate (  Collection < ReceiveCommand > cmd ) { commands . addAll ( cmd ) ; }
(  sheetNumber ) { return ( ) getOrCreateLinkTable ( ) . checkExternSheet ; }
@ Override public boolean (  ) { return c . equals ( object ) ; }
BooleanQuery (  QueryNode queryNode ) throws QueryNodeException { AnyQueryNode andNode = ( AnyQueryNode ) queryNode ; BooleanQuery . Builder bQuery = new BooleanQuery . Builder ( ) ; List < QueryNode > children = andNode . getChildren ( ) ; if ( ) { for ( QueryNode child : children ) { Object obj = child . getTag ( QueryTreeBuilder . QUERY_TREE_BUILDER_TAGID ) ; if ( obj != null ) { Query query = ( Query ) obj ; try { bQuery . add ( query , BooleanClause . Occur . SHOULD ) ; } catch ( ex ) { throw new QueryNodeException ( new MessageImpl ( QueryParserMessages . EMPTY_MESSAGE ) , ex ) ; } } } } bQuery . setMinimumNumberShouldMatch ( andNode . getMinimumMatchingElements ( ) ) ; return bQuery . build ( ) ; }
DescribeStreamProcessorResult (  DescribeStreamProcessorRequest request ) { request = beforeClientExecution ( request ) ; return executeDescribeStreamProcessor ; }
DescribeDashboardPermissionsResult (  ) { request = beforeClientExecution ( request ) ; return executeDescribeDashboardPermissions ( request ) ; }
Ref (  Ref ref ) { try { return getRefDatabase ( ) . peel ( ref ) ; } catch ( e ) { } }
(  ) { return RamUsageEstimator . alignObjectSize ( RamUsageEstimator . NUM_BYTES_OBJECT_HEADER + 2 * Integer . BYTES + ) + RamUsageEstimator . sizeOf ( blocks ) ; }
GetDomainSuggestionsResult (  GetDomainSuggestionsRequest request ) { request = beforeClientExecution ( request ) ; return executeGetDomainSuggestions ; }
DescribeStackEventsResult (  DescribeStackEventsRequest request ) { request = beforeClientExecution ( request ) ; return executeDescribeStackEvents ; }
void (  idx , ) { setRule ( idx , ( HSSFConditionalFormattingRule ) cfRule ) ; }
CreateResolverRuleResult (  CreateResolverRuleRequest request ) { request = beforeClientExecution ( request ) ; return executeCreateResolverRule ; }
SeriesIndexRecord ( ) { field_1_index = in . readShort ( ) ; }
GetStylesRequest ( ) { super ( "lubancloud" , "2018-05-09" , "GetStyles" , "luban" ) ; (  ) ;
void (  ) { out . writeShort ( field_1_gridset_flag ) ; }
boolean (  Object obj ) { if ( this == obj ) { return true ; } if ( obj == null ) { return false ; } if ( getClass ( ) != obj . getClass ( ) ) { return false ; } Toffs other = ( Toffs ) obj ; if ( getStartOffset ( ) != other . getStartOffset ( ) ) { } if ( getEndOffset ( ) != other . getEndOffset ( ) ) { return false ; } return true ; }
CreateGatewayGroupResult (  CreateGatewayGroupRequest request ) { request = beforeClientExecution ( request ) ; return executeCreateGatewayGroup ; }
CreateParticipantConnectionResult (  CreateParticipantConnectionRequest request ) { request = beforeClientExecution ( request ) ; return executeCreateParticipantConnection ; }
public static (  income ) { return irr ( income , 0.1d ) ; }
RegisterWorkspaceDirectoryResult (  RegisterWorkspaceDirectoryRequest request ) { request = beforeClientExecution ; return executeRegisterWorkspaceDirectory ( request ) ; }
RevertCommand (  ) { return include ( commit . getName ( ) , commit ) ; }
ValueEval (  srcRowIndex , srcColumnIndex , ValueEval inumberVE ) { ValueEval ; try { veText1 = OperandResolver . getSingleValue ( inumberVE , srcRowIndex , srcColumnIndex ) ; } catch ( e ) { return e . getErrorEval ( ) ; } String iNumber = OperandResolver . coerceValueToString ( veText1 ) ; Matcher m = COMPLEX_NUMBER_PATTERN . matcher ( iNumber ) ; boolean result = m . matches ( ) ; String imaginary = "" ; if ( result ) { String imaginaryGroup = m . group ( 5 ) ; boolean hasImaginaryPart = imaginaryGroup . equals ( "i" ) || imaginaryGroup . equals ( "j" ) ; if ( imaginaryGroup . length ( ) == 0 ) { return new StringEval ( String . valueOf ( 0 ) ) ; } if ( hasImaginaryPart ) { String sign = "" ; String imaginarySign = m . group ( GROUP3_IMAGINARY_SIGN ) ; if ( imaginarySign . length ( ) != 0 && ! ( imaginarySign . equals ( "+" ) ) ) { sign = imaginarySign ; } String groupImaginaryNumber = m . group ( GROUP4_IMAGINARY_INTEGER_OR_DOUBLE ) ; if ( groupImaginaryNumber . length ( ) != 0 ) { imaginary = sign + groupImaginaryNumber ; } else { imaginary = sign + "1" ; } } } else { return ErrorEval . NUM_ERROR ; } return new StringEval ; }
E (  ) { Map . Entry < E , Object > entry = backingMap . pollLastEntry ; return ( entry == null ) ? null : entry . getKey ( ) ; }
(  ) { ch1 = readUByte ( ) ; ch2 = readUByte ; return ( ch2 << 8 ) + ( ch1 << 0 ) ; }
ModifySnapshotAttributeRequest ( String snapshotId , SnapshotAttributeName attribute , OperationType operationType ) { setSnapshotId ( snapshotId ) ; setAttribute ( attribute . toString ) ; setOperationType ( operationType . toString ( ) ) ; }
ListBonusPaymentsResult (  ListBonusPaymentsRequest request ) { request = beforeClientExecution ; return executeListBonusPayments ( request ) ; }
V (  CharSequence cs ) { if ( cs == null ) throw new NullPointerException ( ) ; }
TokenFilter (  TokenStream input ) { CommonGramsFilter commonGrams = ( CommonGramsFilter ) super . create ; return new CommonGramsQueryFilter ( commonGrams ) ; }
String (  ) { }
InitiateMultipartUploadResult (  InitiateMultipartUploadRequest request ) { request = beforeClientExecution ( request ) ; return executeInitiateMultipartUpload ; }
StringBuilder (  offset , i ) { insert0 ( offset , Integer . toString ( i ) ) ; }
void (  [ ] blocks , blocksOffset , [ ] values , valuesOffset , iterations ) { for ( i = 0 ; i < iterations ; ++ i ) { block = blocks [ blocksOffset ++ ] ; for ( shift = 62 ; shift >= 0 ; shift -= 2 ) { values [ valuesOffset ++ ] = ( ) ( ( ) & 3 ) ; } } }
TokenStream (  TokenStream input ) { return new ElisionFilter ; }
boolean (  Row in , remap [ ] ) { sum = 0 ; for ( Iterator < Cell > i = in . cells . values ( ) . iterator ( ) ; i . hasNext ( ) ; ) { Cell c = i . next ( ) ; sum += ; if ( c . ref >= 0 ) { if ( remap [ c . ref ] == 0 ) { c . ref = - 1 ; } } } frame = sum / 10 ; boolean live = false ; for ( Iterator < Cell > i = in . cells . values ( ) . iterator ( ) ; i . hasNext ( ) ; ) { Cell c = i . next ( ) ; if ( c . cnt < frame && c . cmd >= 0 ) { c . cnt = 0 ; c . cmd = - 1 ; } if ( c . cmd >= 0 || c . ref >= 0 ) { live |= true ; } } return ! live ; }
final public Token (  index ) { Token t = jj_lookingAhead ? jj_scanpos : token ; for ( i = 0 ; i < index ; i ++ ) { if ( t . next != null ) t = t . next ; else t = = token_source . getNextToken ( ) ; } return t ; }
String (  ) { StringBuilder sb = new StringBuilder ( ) ; sb . append ( getClass ( ) . getName ( ) ) . append ( " [ARRAY]\n" ) ; sb . append ( " range=" ) . append ( getRange ( ) ) . append ( "\n" ) ; sb . append ( " options=" ) . append ( HexDump . shortToHex ( _options ) ) . append ( "\n" ) ; sb . append ( " notUsed=" ) . append ( HexDump . intToHex ( _field3notUsed ) ) . append ( "\n" ) ; sb . append ( " formula:" ) . append ( "\n" ) ; Ptg [ ] ptgs = _formula . getTokens ( ) ; for ( i = 0 ; i < ; i ++ ) { Ptg ptg = ptgs [ i ] ; sb . append ( ptg ) . append ( ptg . getRVAType ( ) ) . append ( "\n" ) ; } sb . append ( "]" ) ; return sb . toString ( ) ; }
GetFolderResult (  GetFolderRequest request ) { request = beforeClientExecution ; return executeGetFolder ( request ) ; }
@ Override public void (  location , ) { throw new UnsupportedOperationException ( ) ; }
PositiveScoresOnlyCollector ( Collector in ) { ; }
CreateRepoBuildRuleRequest (  "/repos/[RepoNamespace]/[RepoName]/rules" ) ; setMethod ( MethodType . PUT ) ;
BaseRef ( AreaEval ae ) { ; _areaEval = ae ; _firstRowIndex = ae . getFirstRow ( ) ; _firstColumnIndex = ae . getFirstColumn ( ) ; _height = ae . getLastRow ( ) - ae . getFirstRow ( ) + 1 ; _width = ae . getLastColumn ( ) - ae . getFirstColumn ( ) + 1 ; }
DrawingManager2 ( ) { this . dgg = dgg ; }
void (  ) { if ( ! first ( ) ) reset ; }
CharsetDecoder (  ) { status = INIT ; implReset ( ) ; return this ; }
BufferedReader ( Reader in , size ) { ( in ) ; if ( ) { throw new IllegalArgumentException ( "size <= 0" ) ; } this . in = in ; buf = new [ size ] ; }
DescribeCodeRepositoryResult (  DescribeCodeRepositoryRequest request ) { request = beforeClientExecution ; return executeDescribeCodeRepository ( request ) ; }
DBSubnetGroup (  CreateDBSubnetGroupRequest request ) { request = beforeClientExecution ; return executeCreateDBSubnetGroup ( request ) ; }
RenameBranchCommand (  String oldName ) { checkCallable ( ) ; = oldName ; return this ; }
DeleteBranchCommand (  boolean force ) { checkCallable ( ) ; = force ; return this ; }
StopCompilationJobResult (  StopCompilationJobRequest request ) { request = beforeClientExecution ; return executeStopCompilationJob ( request ) ; }
public synchronized final void (  diff ) { setSecondaryProgress ( ) ; }
[ ] (  ) { return ; }
String (  ) { }
GetUserSourceAccountRequest ( ) { super ( "cr" , "2016-06-07" , "GetUserSourceAccount" , "cr" ) ; (  "/users/sourceAccount" ) ; setMethod ( ) ;
CreateExportJobResult (  CreateExportJobRequest request ) { request = beforeClientExecution ( request ) ; return executeCreateExportJob ; }
CreateDedicatedIpPoolResult (  CreateDedicatedIpPoolRequest request ) { request = beforeClientExecution ; return executeCreateDedicatedIpPool ( request ) ; }
boolean (  Object obj ) { if ( this == obj ) { return true ; } if ( ) { return false ; } if ( obj instanceof HSSFCellStyle ) { HSSFCellStyle other = ( HSSFCellStyle ) obj ; if ( _format == null ) { if ( other . _format != null ) { return false ; } } else if ( ! _format . equals ( other . _format ) ) { return false ; } if ( _index != other . _index ) { return false ; } return true ; } return false ; }
ReleaseHostsResult (  ) { request = beforeClientExecution ( request ) ; return executeReleaseHosts ( request ) ; }
boolean (  Object object ) { if ( this == object ) { return true ; } if ( object instanceof Set ) { Set < > s = ( Set < > ) object ; try { return size ( ) == s . size ( ) && containsAll ( s ) ; } catch ( ignored ) { return false ; } catch ( ignored ) { return false ; } } }
void (  String msg , boolean appendStatus ) { customRefLog = true ; if ( msg == null && ! appendStatus ) { disableRefLog ( ) ; } else if ( msg == null && appendStatus ) { refLogMessage = "" ; refLogIncludeResult = true ; } else { refLogMessage = msg ; ; } }
StreamIDRecord ( RecordInputStream in ) { idstm = in . readShort ; }
RecognizeCarRequest (  MethodType . POST ) ;
ByteOrder (  ) { return ByteOrder . nativeOrder ( ) ; }
(  ) { }
boolean (  ) { }
GetCloudFrontOriginAccessIdentityConfigResult (  ) { request = beforeClientExecution ( request ) ; return executeGetCloudFrontOriginAccessIdentityConfig ( request ) ; }
boolean (  symbol , minVocabSymbol , maxVocabSymbol ) { return ; }
DeleteTransitGatewayResult (  ) { request = beforeClientExecution ( request ) ; return executeDeleteTransitGateway ( request ) ; }
public static (  [ ] array , minSize ) { assert minSize >= 0 : "size must be positive (got " + minSize + "): likely integer overflow?" ; if ( array . length < minSize ) { return growExact ( array , oversize ( minSize , Byte . BYTES ) ) ; } elsereturn ; }
CreateTransactionRequest ( ) { super ( "CloudPhoto" , "2017-07-11" , "CreateTransaction" , "cloudphoto" ) ; (  ) ;
BatchRefUpdate (  PersonIdent pi ) { refLogIdent = pi ; }
GetLaunchTemplateDataResult (  ) { request = beforeClientExecution ( request ) ; return executeGetLaunchTemplateData ( request ) ; }
ParseInfo ( ProfilingATNSimulator atnSimulator ) { = atnSimulator ; }
SimpleQQParser ( String qqNames [ ] , String indexField ) { = qqNames ; this . indexField = indexField ; }
DBCluster (  PromoteReadReplicaDBClusterRequest request ) { request = beforeClientExecution ; return executePromoteReadReplicaDBCluster ( request ) ; }
DescribeCapacityReservationsResult (  ) { request = beforeClientExecution ( request ) ; return executeDescribeCapacityReservations ( request ) ; }
String (  ) { return + "; executor=" + executor + "; sliceExecutionControlPlane " + sliceExecutor + ")" ; }
boolean (  ) { return false ; }
void (  LittleEndianOutput out ) { out . writeShort ( ) ; out . writeShort ( subFrom ) ; out . writeShort ( subTo ) ; }
void (  [ ] blocks , blocksOffset , [ ] values , valuesOffset , iterations ) { if ( ) { throw new UnsupportedOperationException ( "Cannot decode " + bitsPerValue + "-bits values into an int[]" ) ; } for ( i = 0 ; i < iterations ; ++ i ) { block = readLong ( blocks , blocksOffset ) ; blocksOffset += 8 ; valuesOffset = decode ( block , values , valuesOffset ) ; } }
boolean (  symbol ) { ATN atn = getInterpreter ( ) . atn ; ParserRuleContext ctx = _ctx ; ATNState s = atn . states . get ( getState ( ) ) ; IntervalSet following = atn . nextTokens ( s ) ; if ( following . contains ( symbol ) ) { return true ; } if ( ! following . contains ( Token . EPSILON ) ) return false ; while ( ctx != null && ctx . invokingState >= 0 && following . contains ( Token . EPSILON ) ) { ATNState invokingState = atn . states . get ( ctx . invokingState ) ; RuleTransition rt = ( RuleTransition ) invokingState . transition ; following = atn . nextTokens ( rt . followState ) ; if ( following . contains ( symbol ) ) { return true ; } ctx = ( ParserRuleContext ) ctx . parent ; } if ( following . contains ( Token . EPSILON ) && symbol == Token . EOF ) { return true ; } return false ; }
UpdateStreamResult (  ) { request = beforeClientExecution ( request ) ; return executeUpdateStream ( request ) ; }
ValueEval (  srcRowIndex , srcColumnIndex , ) { try { OperandResolver . getSingleValue ( arg0 , srcRowIndex , srcColumnIndex ) ; return ErrorEval . NA ; } catch ( e ) { result = translateErrorCodeToErrorTypeValue ( e . getErrorEval ( ) . getErrorCode ( ) ) ; return new NumberEval ( result ) ; } }
String (  ) { return getClass . getName ( ) + " [" + _index + " " + _name + "]" ; }
ListAssignmentsForHITResult (  ) { request = beforeClientExecution ( request ) ; return executeListAssignmentsForHIT ( request ) ; }
DeleteAccessControlRuleResult (  DeleteAccessControlRuleRequest request ) { request = beforeClientExecution ( request ) ; return executeDeleteAccessControlRule ; }
Arc < Long > (  FST . Arc < Long > arc )  { return fst . getFirstArc ; }
void (  [ ] blocks , blocksOffset , [ ] values , valuesOffset , iterations ) { for ( i = 0 ; i < iterations ; ++ i ) { block = blocks [ blocksOffset ++ ] ; for ( shift = 48 ; shift >= 0 ; shift -= 16 ) { values [ valuesOffset ++ ] = ( ) ( ( ) & 65535 ) ; } } }
(  charCount ) throws IOException { if ( charCount < 0 ) { throw new IllegalArgumentException ( "charCount < 0: " + charCount ) ; } synchronized ( lock ) { checkNotClosed ( ) ; if ( charCount == 0 ) { return 0 ; } ; availableFromBuffer = buf . length - pos ; if ( availableFromBuffer > 0 ) { requiredFromIn = charCount - availableFromBuffer ; if ( requiredFromIn <= 0 ) { pos += charCount ; return charCount ; } pos += availableFromBuffer ; inSkipped = in . skip ; } else { inSkipped = in . skip ( charCount ) ; } return inSkipped + availableFromBuffer ; } }
Map (  ) { return advertisedRefs ; }
UpdateApiKeyResult (  UpdateApiKeyRequest request ) { request = beforeClientExecution ; return executeUpdateApiKey ( request ) ; }
ObjectStream (  ) throws MissingObjectException  { PackInputStream ; @ SuppressWarnings ( "resource" ) DfsReader ctx = db . newReader ( ) ; try { try { packIn = new PackInputStream ( pack , objectOffset + headerLength , ctx ) ; ctx = null ; } catch ( packGone ) { ObjectId obj = pack . getReverseIdx ( ctx ) . findObject ( objectOffset ) ; return ctx . open ( obj , type ) . openStream ; } } finally { if ( ctx != null ) { ctx . close ( ) ; } } bufsz = 8192 ; InputStream in = new BufferedInputStream ( new InflaterInputStream ( packIn , packIn . ctx . inflater ( ) , bufsz ) , bufsz ) ; return new ObjectStream . Filter ( type , size , in ) ; }
ArrayList ( ) { array = ;
UpdateDetectorVersionResult (  UpdateDetectorVersionRequest request ) { request = beforeClientExecution ; return executeUpdateDetectorVersion ( request ) ; }
void (  ) { resize ( ) ; }
RevFlagSet ( Collection < RevFlag > s ) { this ( ) ; addAll ; }
(  ) { }
public final (  ) { newPosition = position + SizeOf . LONG ; if ( newPosition > limit ) { throw new BufferUnderflowException ( ) ; } result = Memory . peekLong ( backingArray , offset + position , order ) ; position = newPosition ; }
StringBuilder (  offset , l ) { insert0 ( offset , Long . toString ( l ) ) ; }
TurkishLowerCaseFilter ( TokenStream in ) { ; }
ParseTreeMatch (  ParseTree tree , ParseTreePattern pattern ) { MultiMap < String , ParseTree > labels = new MultiMap ( ) ; ParseTree mismatchedNode = matchImpl ( tree , pattern . getPatternTree ( ) , labels ) ; return new ParseTreeMatch ( tree , pattern , labels , mismatchedNode ) ; }
void (  WeightedPhraseInfo wpi ) { for ( WeightedPhraseInfo existWpi : getPhraseList ( ) ) { if ( existWpi . isOffsetOverlap ( wpi ) ) { existWpi . getTermsInfos ( ) . addAll ( wpi . getTermsInfos ( ) ) ; return ; } } getPhraseList . add ( wpi ) ; }
ThreeWayMerger (  Repository db ) { return new InCoreMerger ; }
(  docId , String field , numPayloadsSeen , payloadScore ) { return numPayloadsSeen > 0 ? ( ) : 1 ; }
Collection (  ParseTree t ) { return Trees . findAllRuleNodes ( t , ruleIndex ) ; }
String (  ) { StringBuilder buffer = new StringBuilder ( ) ; buffer . append ( "[CFRULE]\n" ) ; buffer . append ( "    .condition_type   =" ) . append ( getConditionType ( ) ) . append ( "\n" ) ; buffer . append . append ( Integer . toHexString ( getOptions ( ) ) ) . append ( "\n" ) ; if ( containsFontFormattingBlock ( ) ) { buffer . append ( _fontFormatting ) . append ( "\n" ) ; } if ( containsBorderFormattingBlock ( ) ) { buffer . append ( _borderFormatting ) . append ( "\n" ) ; } if ( containsPatternFormattingBlock ( ) ) { buffer . append ( _patternFormatting ) . append ( "\n" ) ; } buffer . append ( "    Formula 1 =" ) . append ( Arrays . toString ( getFormula1 ( ) . getTokens ( ) ) ) . append ( "\n" ) ; buffer . append ( "    Formula 2 =" ) . append ( Arrays . toString ( getFormula2 ( ) . getTokens ( ) ) ) . append ( "\n" ) ; buffer . append ( "[/CFRULE]\n" ) ; return buffer . toString ( ) ; }
DescribeServiceUpdatesResult (  DescribeServiceUpdatesRequest request ) { request = beforeClientExecution ; return executeDescribeServiceUpdates ( request ) ; }
String (  index ) { return getNameAt ( index ) . getNameName ; }
DescribeLocationsResult (  ) { return describeLocations ( new DescribeLocationsRequest ) ; }
String (  ) { return "<phraseslop value='" + getValueString ( ) + "'>" + "\n" + getChild ( ) . toString + "\n</phraseslop>" ; }
DirCacheEntry (  ) { return ? currentEntry : null ; }
IntBuffer (  [ ] src , srcOffset , intCount ) { Arrays . checkOffsetAndCount ( src . length , srcOffset , intCount ) ; if ( intCount > remaining ( ) ) { throw new BufferOverflowException ( ) ; } for ( ; i < srcOffset + intCount ; ++ i ) { put ( src [ i ] ) ; } return this ; }
void (  ) { s = size ; if ( s == array . length ) { return ; } if ( s == 0 ) { array = EmptyArray . OBJECT ; } else { Object [ ] newArray = new Object ; System . arraycopy ( array , 0 , newArray , 0 , s ) ; array = newArray ; } modCount ++ ; }
DescribeLocalGatewayVirtualInterfacesResult (  ) { request = beforeClientExecution ( request ) ; return executeDescribeLocalGatewayVirtualInterfaces ( request ) ; }
TokenStream (  TokenStream input ) { return new RussianLightStemFilter ; }
[ ] (  [ ] a ) { [ ] ; if ( == _limit ) { System . arraycopy ( _array , 0 , a , 0 , _limit ) ; rval = a ; } else { rval = toArray ( ) ; } return rval ; }
BasicSessionCredentials ( String accessKeyId , String accessKeySecret , String sessionToken , roleSessionDurationSeconds ) { if ( accessKeyId == null ) { throw new IllegalArgumentException ; } if ( accessKeySecret == null ) { throw new IllegalArgumentException ( "Access key secret cannot be null." ) ; } this . accessKeyId = accessKeyId ; this . accessKeySecret = accessKeySecret ; this . sessionToken = sessionToken ; this . roleSessionDurationSeconds = roleSessionDurationSeconds ; this . sessionStartedTimeInMilliSeconds = System . currentTimeMillis ( ) ; }
public final ShortBuffer (  [ ] dst , dstOffset , shortCount ) { if ( shortCount > remaining ( ) ) { throw new BufferUnderflowException ( ) ; } System . arraycopy ( backingArray , offset + position , dst , dstOffset , shortCount ) ; position += shortCount ; }
ActivateEventSourceResult (  ActivateEventSourceRequest request ) { request = beforeClientExecution ( request ) ; return executeActivateEventSource ; }
DescribeReceiptRuleSetResult (  ) { request = beforeClientExecution ( request ) ; return executeDescribeReceiptRuleSet ( request ) ; }
Filter ( ) { setName ( name ) ; }
DoubleBuffer (  c ) { throw new ReadOnlyBufferException ; }
CreateTrafficPolicyInstanceResult (  ) { request = beforeClientExecution ( request ) ; return executeCreateTrafficPolicyInstance ( request ) ; }
JapaneseIterationMarkCharFilter ( Reader input , , boolean normalizeKana ) { ( input ) ; this . normalizeKanji = normalizeKanji ; this . normalizeKana = normalizeKana ; buffer . reset ( input ) ; }
void (  v ) { writeInt ( ( ) ( ) ) ; writeInt ( ( ) ( v >> 32 ) ) ; }
FileResolver ( ) { exports = new ConcurrentHashMap ( ) ; exportBase = new CopyOnWriteArrayList < > ( ) ;
ValueEval (  Ref3DPxg rptg ) { SheetRangeEvaluator sre = createExternSheetRefEvaluator ( rptg . getSheetName ( ) , rptg . getLastSheetName ( ) , rptg . getExternalWorkbookNumber ( ) ) ; return new LazyRefEval ( rptg . getRow , rptg . getColumn ( ) , sre ) ; }
DeleteDatasetResult (  DeleteDatasetRequest request ) { request = beforeClientExecution ( request ) ; return executeDeleteDataset ; }
StartRelationalDatabaseResult (  StartRelationalDatabaseRequest request ) { request = beforeClientExecution ( request ) ; return executeStartRelationalDatabase ; }
DescribeReservedCacheNodesOfferingsResult (  ) { return describeReservedCacheNodesOfferings ( new DescribeReservedCacheNodesOfferingsRequest ) ; }
(  r , nper , pv , fv , type ) { return - r * ( pv * Math . pow ( 1 + r , nper ) + fv ) / ( ( 1 + r * type ) * ( Math . pow ( 1 + r , nper ) - 1 ) ) ; }
DescribeDocumentVersionsResult (  ) { request = beforeClientExecution ( request ) ; return executeDescribeDocumentVersions ( request ) ; }
ListPublishingDestinationsResult (  ListPublishingDestinationsRequest request ) { request = beforeClientExecution ( request ) ; return executeListPublishingDestinations ; }
DeleteAccountAliasRequest ( ) { setAccountAlias ( accountAlias ) ; }
public static [ ] (  array ) { return grow ( array , 1 + array . length ) ; }
String (  ) { if ( ! ( output instanceof List ) ) { return outputs . outputToString ( ( T ) output ) ; } else { List < T > outputList = ( List < T > ) output ; StringBuilder b = new StringBuilder ( ) ; b . append ( '[' ) ; for ( i = 0 ; i < outputList . size ( ) ; i ++ ) { if ( i > 0 ) { b . append ( ", " ) ; } b . append ( outputs . outputToString ( outputList . get ( i ) ) ) ; } b . append ( ']' ) ; return b . toString ( ) ; } }
void (  ) { _bookEvaluator . notifyDeleteCell ( new HSSFEvaluationCell ( ( HSSFCell ) cell ) ) ; }
StringBuilder (  start , end , ) { replace0 ( start , end , str ) ; return this ; }
SetIdentityPoolConfigurationResult (  ) { request = beforeClientExecution ( request ) ; return executeSetIdentityPoolConfiguration ( request ) ; }
public static (  [ ] v , k ) { r = Double . NaN ; index = k - 1 ; if ( v != null && v . length > index && index >= 0 ) { Arrays . sort ( v ) ; r = ; } return r ; }
void (  index , value ) { o = index >>> 5 ; b = ; shift = b << 1 ; blocks [ o ] = ( blocks [ o ] & ~ ( 3L << shift ) ) | ( value << shift ) ; }
String (  ) { if ( getChildren ( ) == null || getChildren ( ) . size ( ) == 0 ) StringBuilder sb = new StringBuilder ( ) ; sb . append ( "<boolean operation='and'>" ) ; for ( QueryNode child : getChildren ( ) ) { sb . append ( "\n" ) ; sb . append ( child . toString ( ) ) ; } sb . append ( "\n</boolean>" ) ; return sb . toString ( ) ; }
(  fromIx , toIx ) { result = 0 ; for ( i = fromIx ; i < toIx ; i ++ ) { result += . getSize ( ) ; } return result ; }
void (  boolean readonly ) { if ( this . readonly && ) throw new IllegalStateException ( "can't alter readonly IntervalSet" ) ; this . readonly = readonly ; }
public final void (  FormulaCellCacheEntry cce ) { if ( ! _consumingCells . remove ( cce ) ) { throw new IllegalStateException ; } }
@ Override public List < E > (  start , end ) { synchronized { return new SynchronizedRandomAccessList < E > ( list . subList ( start , end ) , mutex ) ; } }
FileHeader (  ) { }
AttachLoadBalancersResult (  AttachLoadBalancersRequest request ) { request = beforeClientExecution ; return executeAttachLoadBalancers ( request ) ; }
InitiateJobRequest ( String accountId , String vaultName , JobParameters jobParameters ) { setAccountId ( accountId ) ; setVaultName ; setJobParameters ( jobParameters ) ; }
String (  ) { }
ReplaceableAttribute ( String name , String value , Boolean replace ) { setName ( name ) ; setValue ( value ) ; setReplace ; }
public final void (  ) { fields . add ( field ) ; }
DeleteStackSetResult (  ) { request = beforeClientExecution ( request ) ; return executeDeleteStackSet ( request ) ; }
GetRepoBuildRuleListRequest ( ) { super ( "cr" , "2016-06-07" , "GetRepoBuildRuleList" , "cr" ) ; (  "/repos/[RepoNamespace]/[RepoName]/rules" ) ; setMethod ( ) ;
SparseArray ( initialCapacity ) { initialCapacity = ArrayUtils . idealIntArraySize ; mKeys = new [ initialCapacity ] ; mValues = new Object [ initialCapacity ] ; mSize = 0 ; }
InvokeServiceRequest ( ) { super ( "industry-brain" , "2018-07-12" , "InvokeService" ) ; (  ) ;
ListAlbumPhotosRequest (  ProtocolType . HTTPS ) ;
boolean (  ) { return link != ; }
DeleteHsmConfigurationResult (  ) { request = beforeClientExecution ( request ) ; return executeDeleteHsmConfiguration ( request ) ; }
CreateLoadBalancerRequest ( ) { setLoadBalancerName ( loadBalancerName ) ; }
String (  ) { return decode ; }
TagAttendeeResult (  ) { request = beforeClientExecution ( request ) ; return executeTagAttendee ( request ) ; }
String (  ) { }
SpanNearQuery (  ) { return new SpanNearQuery ( clauses . toArray ( new SpanQuery [ clauses . size ] ) , slop , ordered ) ; }
boolean (  rowIndex , columnIndex ) { }
DescribeDBProxiesResult (  DescribeDBProxiesRequest request ) { request = beforeClientExecution ; return executeDescribeDBProxies ( request ) ; }
GetVoiceConnectorProxyResult (  GetVoiceConnectorProxyRequest request ) { request = beforeClientExecution ( request ) ; return executeGetVoiceConnectorProxy ; }
WindowCacheConfig (  Config rc ) { setPackedGitUseStrongRefs ( rc . getBoolean ( CONFIG_CORE_SECTION , CONFIG_KEY_PACKED_GIT_USE_STRONGREFS , isPackedGitUseStrongRefs ( ) ) ) ; setPackedGitOpenFiles ( rc . getInt ( CONFIG_CORE_SECTION , null , CONFIG_KEY_PACKED_GIT_OPENFILES , getPackedGitOpenFiles ( ) ) ) ; setPackedGitLimit ( rc . getLong ( CONFIG_CORE_SECTION , null , CONFIG_KEY_PACKED_GIT_LIMIT , getPackedGitLimit ( ) ) ) ; setPackedGitWindowSize ( rc . getInt ( CONFIG_CORE_SECTION , null , CONFIG_KEY_PACKED_GIT_WINDOWSIZE , getPackedGitWindowSize ) ) ; setPackedGitMMAP ( rc . getBoolean ( CONFIG_CORE_SECTION , null , CONFIG_KEY_PACKED_GIT_MMAP , isPackedGitMMAP ( ) ) ) ; setDeltaBaseCacheLimit ( rc . getInt ( CONFIG_CORE_SECTION , null , CONFIG_KEY_DELTA_BASE_CACHE_LIMIT , getDeltaBaseCacheLimit ( ) ) ) ; maxMem = Runtime . getRuntime ( ) . maxMemory ( ) ; sft = rc . getLong ( CONFIG_CORE_SECTION , null , CONFIG_KEY_STREAM_FILE_TRESHOLD , getStreamFileThreshold ( ) ) ; sft = Math . min ( sft , maxMem / 4 ) ; sft = Math . min ( sft , Integer . MAX_VALUE ) ; setStreamFileThreshold ( ( ) sft ) ; return this ; }
public static Date (  date ) { return getJavaDate ; }
StartPersonTrackingResult (  StartPersonTrackingRequest request ) { request = beforeClientExecution ; return executeStartPersonTracking ( request ) ; }
@ Override public (  ) { }
GetRouteResult (  ) { request = beforeClientExecution ( request ) ; return executeGetRoute ( request ) ; }
DeleteClusterResult (  DeleteClusterRequest request ) { request = beforeClientExecution ; return executeDeleteCluster ( request ) ; }
String (  ) { StringBuilder buffer = new StringBuilder ( ) ; buffer . append ; buffer . append ( "    .addMenu        = " ) . append ( Integer . toHexString ( getAddMenuCount ( ) ) ) . append ( "\n" ) ; buffer . append ( "    .delMenu        = " ) . append ( Integer . toHexString ( getDelMenuCount ( ) ) ) . append ( "\n" ) ; buffer . append ( "[/MMS]\n" ) ; return buffer . toString ( ) ; }
FileBasedConfig ( , File cfgLocation , FS fs ) { ( base ) ; configFile = cfgLocation ; this . fs = fs ; this . snapshot = FileSnapshot . DIRTY ; this . hash = ObjectId . zeroId ( ) ; }
(  pos ) { if ( pos < text . getBeginIndex || pos > text . getEndIndex ( ) ) { throw new IllegalArgumentException ( "offset out of bounds" ) ; } else if ( 0 == sentenceStarts . length ) { text . setIndex ( text . getBeginIndex ( ) ) ; return DONE ; } else if ( pos >= sentenceStarts [ sentenceStarts . length - 1 ] ) { text . setIndex ( text . getEndIndex ( ) ) ; currentSentence = sentenceStarts . length - 1 ; return DONE ; } else { currentSentence = ( sentenceStarts . length - 1 ) / 2 ; moveToSentenceAt ( pos , 0 , sentenceStarts . length - 2 ) ; text . setIndex ( sentenceStarts [ ++ currentSentence ] ) ; return current ( ) ; } }
UpdateParameterGroupResult (  ) { request = beforeClientExecution ( request ) ; return executeUpdateParameterGroup ( request ) ; }
SeriesChartGroupIndexRecord (  ) { return copy ; }
public static (  Shape shape , distErrPct , ) { if ( distErrPct < 0 || distErrPct > 0.5 ) { throw new IllegalArgumentException ( "distErrPct " + distErrPct + " must be between [0 to 0.5]" ) ; } if ( distErrPct == 0 || shape instanceof Point ) { return 0 ; } Rectangle bbox = shape . getBoundingBox ( ) ; Point ctr = bbox . getCenter ( ) ; y = ( ctr . getY ( ) >= 0 ? bbox . getMaxY ( ) : bbox . getMinY ( ) ) ; diagonalDist = ctx . getDistCalc ( ) . distance ( ctr , bbox . getMaxX ( ) , y ) ; return diagonalDist * distErrPct ; }
(  index ) { if ( || index >= count ) { throw indexAndLength ( index ) ; } return Character . codePointAt ( value , index , count ) ; }
void (  passwordVerifier ) { = passwordVerifier ; }
ListVaultsRequest ( String accountId ) { setAccountId ; }
SquashMessageFormatter dateFormatter = new GitDateFormatter ( Format . DEFAULT ) ;
GetVideoCoverRequest ( ) { super ( "CloudPhoto" , "2017-07-11" , "GetVideoCover" , "cloudphoto" ) ; (  ) ;
(  Object object ) { pos = size ; Link < E > link = voidLink . previous ; if ( object != null ) { while ( link != voidLink ) { pos -- ; if ( object . equals ( link . data ) ) { return pos ; } link = link . previous ; } } else { while ( ) { pos -- ; if ( link . data == null ) { return pos ; } link = link . previous ; } } return - 1 ; }
DescribeSpotFleetRequestsResult (  ) { request = beforeClientExecution ( request ) ; return executeDescribeSpotFleetRequests ( request ) ; }
IndexFacesResult (  IndexFacesRequest request ) { request = beforeClientExecution ( request ) ; return executeIndexFaces ; }
RuleBasedBreakIterator (  script ) { switch ( script ) { case UScript . JAPANESE : return ( RuleBasedBreakIterator ) cjkBreakIterator . clone ; case UScript . MYANMAR : if ( myanmarAsWords ) { return ( RuleBasedBreakIterator ) defaultBreakIterator . clone ( ) ; } else { return ( RuleBasedBreakIterator ) myanmarSyllableIterator . clone ( ) ; } : return ( RuleBasedBreakIterator ) defaultBreakIterator . clone ( ) ; } }
String (  ) { StringBuilder b = new StringBuilder ( ) ; b . append ( "[DCONREF]\n" ) ; b . append ( "    .ref\n" ) ; b . append ( "        .firstrow   = " ) . append ( firstRow ) . append ( "\n" ) ; b . append ( "        .lastrow    = " ) . append ( lastRow ) . append ( "\n" ) ; b . append ( "        .firstcol   = " ) . append ( firstCol ) . append ( "\n" ) ; b . append ( "        .lastcol    = " ) . append ( lastCol ) . append ( "\n" ) ; b . append ( "    .cch            = " ) . append ( charCount ) . append ( "\n" ) ; b . append ( "    .stFile\n" ) ; b . append ( "        .h          = " ) . append ( charType ) . append ( "\n" ) ; b . append ( "        .rgb        = " ) . append ( getReadablePath ) . append ( "\n" ) ; b . append ( "[/DCONREF]\n" ) ; return b . toString ( ) ; }
(  ) { }
String (  ) { StringBuilder buffer = new StringBuilder ( ) ; buffer . append ( "[FEATURE HEADER]\n" ) ; buffer . append ; return buffer . toString ( ) ; }
public static [ ] (  String string ) { return string . getBytes ; }
List < String > (  String keyName ) { return getFooterLines ( new FooterKey ( keyName ) ) ; }
void (  ) { super . refresh ; clearReferences ( ) ; }
(  index ) { checkIndex ( index ) ; return byteBuffer . getFloat ( index * ) ; }
DeleteDetectorResult (  DeleteDetectorRequest request ) { request = beforeClientExecution ; return executeDeleteDetector ( request ) ; }
(  ) { assert bytesStart != null ; return bytesStart = ArrayUtil . grow ( bytesStart , bytesStart . length + 1 ) ; }
ListExclusionsResult (  ListExclusionsRequest request ) { request = beforeClientExecution ( request ) ; return executeListExclusions ; }
public static SpatialStrategy (  roundNumber ) { SpatialStrategy result = spatialStrategyCache . get ( roundNumber ) ; if ( result == null ) { throw new IllegalStateException ; } return result ; }
DBCluster (  RestoreDBClusterToPointInTimeRequest request ) { request = beforeClientExecution ; return executeRestoreDBClusterToPointInTime ( request ) ; }
void (  ) { out . writeShort ( field_1_categoryDataType ) ; out . writeShort ( field_2_valuesDataType ) ; out . writeShort ( field_3_numCategories ) ; out . writeShort ( field_4_numValues ) ; out . writeShort ( field_5_bubbleSeriesType ) ; out . writeShort ( field_6_numBubbleValues ) ; }
PostAgentProfileResult (  ) { request = beforeClientExecution ( request ) ; return executePostAgentProfile ( request ) ; }
ParseTreePattern (  String pattern , patternRuleIndex ) { if ( getTokenStream ( ) != null ) { TokenSource tokenSource = getTokenStream ( ) . getTokenSource ( ) ; if ( tokenSource instanceof Lexer ) { Lexer lexer = ( Lexer ) tokenSource ; return compileParseTreePattern ; } } throw new UnsupportedOperationException ( "Parser can't discover a lexer to use" ) ; }
BacktrackDBClusterResult (  ) { request = beforeClientExecution ( request ) ; return executeBacktrackDBCluster ( request ) ; }
String (  ) { }
void (  [ ] b , o ) { formatHexByte ( b , , w1 ) ; formatHexByte ( b , o + 8 , w2 ) ; formatHexByte ( b , o + 16 , w3 ) ; formatHexByte ( b , o + 24 , w4 ) ; formatHexByte ( b , o + 32 , w5 ) ; }
public static final IntList (  buf , ptr , end ) { IntList map = new IntList ( ( end - ptr ) / 36 ) ; map . fillTo ( 1 , Integer . MIN_VALUE ) ; for ( ; ptr < end ; ptr = nextLF ( buf , ptr ) ) { map . add ( ptr ) ; } map . add ( end ) ; return map ; }
Set (  ) { return Collections . emptySet ( ) ; }
public synchronized (  ) { sizeInBytes = BASE_RAM_BYTES_USED + fields . size ( ) * 2 * RamUsageEstimator . NUM_BYTES_OBJECT_REF ; for ( SimpleTextTerms simpleTextTerms : termsCache . values ( ) ) { sizeInBytes += ( simpleTextTerms != null ) ? simpleTextTerms . ramBytesUsed ( ) : 0 ; } }
String (  String tab ) { StringBuilder builder = new StringBuilder ( ) ; builder . append ( tab ) . append ( "<" ) . append ( getRecordName ( ) ) . append ( ">\n" ) ; for ( EscherRecord escherRecord : getEscherRecords ( ) ) { builder . append ( escherRecord . toXml ( tab + "\t" ) ) ; } builder . append . append ( "</" ) . append ( getRecordName ( ) ) . append ( ">\n" ) ; return builder . toString ( ) ; }
TokenStream (  TokenStream input ) { return new GalicianMinimalStemFilter ; }
String (  ) { StringBuilder r = new StringBuilder ( ) ; r . append ( "Commit" ) ; r . append ( "={\n" ) ; r . append ( "tree " ) ; r . append ( treeId != null ? treeId . name ( ) : "NOT_SET" ) ; r . append ( "\n" ) ; for ( ObjectId p : parentIds ) { r . append ( "parent " ) ; r . append ( p . name ( ) ) ; r . append ( "\n" ) ; } r . append ( "author " ) ; r . append ( author != null ? author . toString ( ) : "NOT_SET" ) ; r . append ( "\n" ) ; r . append ; r . append ( committer != null ? committer . toString ( ) : "NOT_SET" ) ; r . append ( "\n" ) ; r . append ( "gpgSignature " ) ; r . append ( gpgSignature != null ? gpgSignature . toString ( ) : "NOT_SET" ) ; r . append ( "\n" ) ; if ( encoding != null && ! References . isSameObject ( encoding , UTF_8 ) ) { r . append ( "encoding " ) ; r . append ( encoding . name ( ) ) ; r . append ( "\n" ) ; } r . append ( "\n" ) ; r . append ( message != null ? message : "" ) ; r . append ( "}" ) ; return r . toString ( ) ; }
IndicNormalizationFilterFactory ( Map < String , String > args ) { ; if ( ! args . isEmpty ( ) ) { throw new IllegalArgumentException ( "Unknown parameters: " + args ) ; } }
OptionGroup (  CreateOptionGroupRequest request ) { request = beforeClientExecution ( request ) ; return executeCreateOptionGroup ; }
AssociateMemberAccountResult (  ) { request = beforeClientExecution ( request ) ; return executeAssociateMemberAccount ( request ) ; }
void (  ) { doRefreshProgress ; mRefreshProgressRunnable = this ; }
SetTerminationProtectionResult (  SetTerminationProtectionRequest request ) { request = beforeClientExecution ( request ) ; return executeSetTerminationProtection ; }
String (  RecognitionException e ) { line = e . getOffendingToken ( ) . getLine ( ) ; charPositionInLine = e . getOffendingToken ( ) . getCharPositionInLine ; return "line " + line + ":" + charPositionInLine ; }
CharBuffer (  ) { CharToByteBufferAdapter buf = new CharToByteBufferAdapter ( byteBuffer . asReadOnlyBuffer ( ) ) ; buf . limit = limit ; buf . position = position ; buf . mark = mark ; . order = byteBuffer . order ; return buf ; }
StopSentimentDetectionJobResult (  StopSentimentDetectionJobRequest request ) { request = beforeClientExecution ( request ) ; return executeStopSentimentDetectionJob ; }
ObjectIdSubclassMap < ObjectId > (  ) { if ( ) return newObjectIds ; return new ObjectIdSubclassMap < > ( ) ; }
void (  ) { hash = hash ( new ) ; super . clear ( ) ; }
void (  ) throws IOException { synchronized ( lock ) { checkNotClosed ( ) ; if ( mark == ) { throw new IOException ( "Invalid mark" ) ; } pos = mark ; }
RefErrorPtg ( LittleEndianInput in ) { field_1_reserved = in . readInt ; }
SuspendGameServerGroupResult (  ) { request = beforeClientExecution ( request ) ; return executeSuspendGameServerGroup ( request ) ; }
public final ValueEval (  ValueEval [ ] args , srcRowIndex , srcColumnIndex ) { if ( args . length != 3 ) { return ErrorEval . VALUE_INVALID ; } return evaluate ( srcRowIndex , srcColumnIndex , args [ 0 ] , args [ 1 ] , ) ; }
GetRepoRequest ( ) { super ( "cr" , "2016-06-07" , "GetRepo" , "cr" ) ;  ; setMethod ( MethodType . GET ) ;
void (  ) { if ( date != null ) { setDate ( DateTools . dateToString ( date , DateTools . Resolution . SECOND ) ) ; } else { this . date = null ; } }
TokenStream (  TokenStream input ) { return new GermanMinimalStemFilter ; }
Object (  ) { return a . clone ( ) ; }
void (  [ ] buffer , offset , len ) { Arrays . checkOffsetAndCount ( buffer . length , offset , len ) ; synchronized ( lock ) { expand ( len ) ; System . arraycopy ( buffer , offset , this . buf , this . count , len ) ; += len ; } }
RevFilter (  Date ts ) { return after ( ts . getTime ( ) ) ; }
DeleteGroupPolicyRequest ( , String policyName ) { setGroupName ( groupName ) ; setPolicyName ( policyName ) ; }
DeregisterTransitGatewayMulticastGroupMembersResult (  ) { request = beforeClientExecution ( request ) ; return executeDeregisterTransitGatewayMulticastGroupMembers ( request ) ; }
BatchDeleteScheduledActionResult (  BatchDeleteScheduledActionRequest request ) { request = beforeClientExecution ; return executeBatchDeleteScheduledAction ( request ) ; }
CreateAlgorithmResult (  CreateAlgorithmRequest request ) { request = beforeClientExecution ( request ) ; return executeCreateAlgorithm ; }
(  ) { return readByte & 0x00FF ; }
void (  sz ) { NB . encodeInt32 ( info , , sz ) ; }
DescribeScalingProcessTypesResult (  ) { return describeScalingProcessTypes ( new DescribeScalingProcessTypesRequest ) ; }
ListResourceRecordSetsResult (  ListResourceRecordSetsRequest request ) { request = beforeClientExecution ( request ) ; return executeListResourceRecordSets ; }
Token (  Parser recognizer ) throws {  InputMismatchException e = new InputMismatchException ; for ( ParserRuleContext context = recognizer . getContext ( ) ; context != null ; context = context . getParent ( ) ) { context . exception = e ; } throw new ParseCancellationException ( e ) ; }
SetTagsForResourceResult (  SetTagsForResourceRequest request ) { request = beforeClientExecution ; return executeSetTagsForResource ( request ) ; }
ModifyStrategyRequest ) ; }
DescribeVpcEndpointServicesResult (  DescribeVpcEndpointServicesRequest request ) { request = beforeClientExecution ; return executeDescribeVpcEndpointServices ( request ) ; }
EnableLoggingResult (  ) { request = beforeClientExecution ( request ) ; return executeEnableLogging ( request ) ; }
boolean (  Object o ) { return ConcurrentHashMap . this . containsValue ; }
SheetRangeIdentifier ( String bookName , NameIdentifier firstSheetIdentifier , NameIdentifier lastSheetIdentifier ) { super ( bookName , firstSheetIdentifier ) ; ; }
DomainMetadataRequest ( String domainName ) { setDomainName ; }
ParseException ( Token currentTokenVal , [ ] [ ] expectedTokenSequencesVal , String [ ] tokenImageVal ) { ( new MessageImpl ( QueryParserMessages . INVALID_SYNTAX , initialise ( currentTokenVal , expectedTokenSequencesVal , tokenImageVal ) ) ) ; this . currentToken = currentTokenVal ; = expectedTokenSequencesVal ; this . tokenImage = tokenImageVal ; }
FetchPhotosRequest (  ProtocolType . HTTPS ) ;
PrintWriter (  ) { }
NGramTokenizerFactory ( Map args ) { ( args ) ; minGramSize = getInt ( args , "minGramSize" , NGramTokenizer . DEFAULT_MIN_NGRAM_SIZE ) ; maxGramSize = getInt ( args , "maxGramSize" , NGramTokenizer . DEFAULT_MAX_NGRAM_SIZE ) ; if ( ! args . isEmpty ( ) ) { throw new IllegalArgumentException ( "Unknown parameters: " + args ) ; } }
boolean (  ) { return ; }
IndonesianStemFilter ( , boolean stemDerivational ) { ( input ) ; this . stemDerivational = stemDerivational ; }
CreateTrafficPolicyResult (  CreateTrafficPolicyRequest request ) { request = beforeClientExecution ; return executeCreateTrafficPolicy ( request ) ; }
void (  ) { out . writeInt ( fSD ) ; out . writeInt ( passwordVerifier ) ; StringUtil . writeUnicodeString ( out , title ) ; out . write ( securityDescriptor ) ; }
public static (  n , s ) { if ( s == 0 && n != 0 ) { return Double . NaN ; } else { return ( || s == 0 ) ? 0 : Math . floor ( n / s ) * s ; } }
ByteArrayDataOutput ( bytes , offset , len ) { reset ( bytes , offset , len ) ; }
public static List < Tree > (  Tree t ) { List < Tree > kids = new ArrayList < Tree > ( ) ; for ( i = 0 ; i < t . getChildCount ( ) ; ) { kids . add ( t . getChild ( i ) ) ; } return kids ; }
void (  ) { . clear ( ) ; }
RefreshAllRecord ( boolean refreshAll ) { ( 0 ) ; setRefreshAll ; }
DeleteNamedQueryResult (  DeleteNamedQueryRequest request ) { request = beforeClientExecution ; return executeDeleteNamedQuery ( request ) ; }
GraphvizFormatter ( ConnectionCosts costs ) { = costs ; this . bestPathMap = new HashMap < > ( ) ; sb . append ( formatHeader ( ) ) ; sb . append ( "  init [style=invis]\n" ) ; sb . append ( "  init -> 0.0 [label=\"" + BOS_LABEL + "\"]\n" ) ; }
CheckMultiagentRequest ( ) { super ( "visionai-poc" , "2020-04-08" , "CheckMultiagent" ) ; (  ) ;
ListUserProfilesResult (  ListUserProfilesRequest request ) { request = beforeClientExecution ; return executeListUserProfiles ( request ) ; }
CreateRelationalDatabaseFromSnapshotResult (  CreateRelationalDatabaseFromSnapshotRequest request ) { request = beforeClientExecution ; return executeCreateRelationalDatabaseFromSnapshot ( request ) ; }
StartTaskResult (  StartTaskRequest request ) { request = beforeClientExecution ( request ) ; return executeStartTask ; }
Set (  ) { return ignoredPaths ; }
FeatSmartTag ( ) { data = in . readRemainder ( ) ; }
Change ( , ResourceRecordSet resourceRecordSet ) { setAction ( action . toString ( ) ) ; setResourceRecordSet ( resourceRecordSet ) ; }
DeleteImageResult (  ) { request = beforeClientExecution ( request ) ; return executeDeleteImage ( request ) ; }
CreateConfigurationSetResult (  CreateConfigurationSetRequest request ) { request = beforeClientExecution ( request ) ; return executeCreateConfigurationSet ; }
Iterator < E > (  ) { Object [ ] snapshot = elements ; return new CowIterator ( snapshot , 0 , snapshot . length ) ; }
void (  RecordVisitor rv ) { if ( _recs . isEmpty ) { return ; } rv . visitRecord ( _bofRec ) ; for ( i = 0 ; i < _recs . size ( ) ; i ++ ) { RecordBase rb = _recs . get ( i ) ; if ( rb instanceof RecordAggregate ) { ( ( RecordAggregate ) rb ) . visitContainedRecords ( rv ) ; } else { rv . visitRecord ( ( org . apache . poi . hssf . record . Record ) rb ) ; } } rv . visitRecord ( EOFRecord . instance ) ; }
String (  ) { StringBuilder buffer = new StringBuilder ( ) ; buffer . append ( "[FtCbls ]" ) . append ( "\n" ) ; buffer . append ( "  size     = " ) . append ( getDataSize ( ) ) . append ( "\n" ) ; buffer . append ( "  reserved = " ) . append ( HexDump . toHex ( reserved ) ) . append ( "\n" ) ; buffer . append ( "[/FtCbls ]" ) . append ; return buffer . toString ( ) ; }
public static BATBlock (  POIFSBigBlockSize bigBlockSize , boolean isXBAT ) { BATBlock block = new BATBlock ( bigBlockSize ) ; if ( isXBAT ) { _entries_per_xbat_block = bigBlockSize . getXBATEntriesPerBlock ; block . _values [ _entries_per_xbat_block ] = POIFSConstants . END_OF_CHAIN ; } return block ; }
TagResourceResult (  TagResourceRequest request ) { request = beforeClientExecution ; return executeTagResource ( request ) ; }
DeleteMailboxPermissionsResult (  DeleteMailboxPermissionsRequest request ) { request = beforeClientExecution ; return executeDeleteMailboxPermissions ( request ) ; }
ListDatasetGroupsResult (  ListDatasetGroupsRequest request ) { request = beforeClientExecution ; return executeListDatasetGroups ( request ) ; }
ResumeProcessesResult (  ResumeProcessesRequest request ) { request = beforeClientExecution ( request ) ; return executeResumeProcesses ; }
GetPersonTrackingResult (  GetPersonTrackingRequest request ) { request = beforeClientExecution ( request ) ; return executeGetPersonTracking ; }
String (  String [ ] operands ) { if ( space . isSet ) { return operands [ 0 ] ; } else if ( optiIf . isSet ( _options ) ) { return toFormulaString ( ) + "(" + operands [ 0 ] + ")" ; } else if ( optiSkip . isSet ( _options ) ) { return toFormulaString ( ) + operands [ 0 ] ; } else { return toFormulaString ( ) + "(" + operands [ 0 ] + ")" ; } }
T (  T first , T second ) { throw new UnsupportedOperationException ; }
String (  ) { return . getKey ( ) + ": " + getLocalizedMessage ( ) ; }
XPath ( Parser parser , ) { this . parser = parser ; this . path = path ; elements = split ( path ) ; }
CreateAccountAliasRequest ( String accountAlias ) { setAccountAlias ; }
void (  [ ] blocks , blocksOffset , [ ] values , valuesOffset , iterations ) { for ( j = 0 ; j < iterations ; ++ j ) { block = blocks [ blocksOffset ++ ] ; values [ valuesOffset ++ ] = ( block >>> 7 ) & 1 ; values [ valuesOffset ++ ] = ( ) & 1 ; values [ valuesOffset ++ ] = ( block >>> 5 ) & 1 ; values [ valuesOffset ++ ] = ( block >>> 4 ) & 1 ; values [ valuesOffset ++ ] = ( block >>> 3 ) & 1 ; values [ valuesOffset ++ ] = ( block >>> 2 ) & 1 ; values [ valuesOffset ++ ] = ( block >>> 1 ) & 1 ; values [ valuesOffset ++ ] = block & 1 ; } }
PushConnection  throws TransportException { return new TcpPushConnection ( ) ;
void (  [ ] dst , di , [ ] src , si ) { while ( src [ si ] != 0 ) { dst [ di ++ ] = src [ si ++ ] ; } dst [ di ] = 0 ; }
public K (  ) { return mapEntry . getKey ( ) ; }
public static (  Object [ ] data ) { n = 0 ; if ( data == null ) for ( Object o : data ) { if ( o != null ) n ++ ; } return n ; }
void (  location , E object ) { if ( location >= 0 && location <= size ) { Link < E > link = voidLink ; if ( location < ( size / 2 ) ) { for ( i = 0 ; i <= location ; i ++ ) { link = link . next ; } } else { for ( i = size ; i > location ; i -- ) { link = link . previous ; } } Link < E > previous = link . previous ; Link < E > newLink = new Link < E > ( object , previous , link ) ; previous . next = newLink ; link . previous = newLink ; size ++ ; modCount ++ ; } else { throw new IndexOutOfBoundsException ; } }
DescribeDomainResult (  ) { request = beforeClientExecution ( request ) ; return executeDescribeDomain ( request ) ; }
void  throws IOException { super . flush ( ) ;
PersianCharFilterFactory ( Map < String , String > args ) { ( args ) ; if ( ! args . isEmpty ( ) ) { throw new IllegalArgumentException ( ) ; } }
boolean (  ) { if ( used ) { return false ; } clearAttributes ( ) ; termAttribute . append ( value ) ; offsetAttribute . setOffset ( 0 , length ) ; used = true ; }
public static FloatBuffer (  capacity ) { if ( capacity < 0 ) { throw new IllegalArgumentException ; } return new ReadWriteFloatArrayBuffer ( capacity ) ; }
public final Edit (  Edit cut ) { return new Edit ( cut . endA , endA , , endB ) ; }
UpdateRuleVersionResult (  ) { request = beforeClientExecution ( request ) ; return executeUpdateRuleVersion ( request ) ; }
ListVoiceConnectorTerminationCredentialsResult (  ListVoiceConnectorTerminationCredentialsRequest request ) { request = beforeClientExecution ; return executeListVoiceConnectorTerminationCredentials ( request ) ; }
GetDeploymentTargetResult (  ) { request = beforeClientExecution ( request ) ; return executeGetDeploymentTarget ( request ) ; }
void (  ) { ; for ( PerfTask task : tasks ) { if ( task instanceof TaskSequence ) { ( ( TaskSequence ) task ) . setNoChildReport ( ) ; } } }
E (  location ) { try { return ; } catch ( e ) { throw java . util . ArrayList . throwIndexOutOfBoundsException ( location , a . length ) ; } }
DescribeDataSetResult (  ) { request = beforeClientExecution ( request ) ; return executeDescribeDataSet ( request ) ; }
SkipWorkTreeFilter ( treeIdx ) { = treeIdx ; }
DescribeNetworkInterfacesResult (  ) { return describeNetworkInterfaces ( new DescribeNetworkInterfacesRequest ) ; }
public final boolean (  row , col ) { return && _lastRow >= row && _firstColumn <= col && _lastColumn >= col ; }
String (  ) { return new String ( ) ; }
PatchType (  ) { }
Iterator (  ) { return new KeyIterator ( ) ; }
CreateScriptResult (  ) { request = beforeClientExecution ( request ) ; return executeCreateScript ( request ) ; }
BytesRef (  ) { termUpto ++ ; if ( termUpto >= info . terms . size ( ) ) { return null ; } else { info . terms . get ( info . sortedTerms [ termUpto ] , br ) ; } }
String (  CharsRef output ) { return output . toString ; }
AssociateWebsiteAuthorizationProviderResult (  AssociateWebsiteAuthorizationProviderRequest request ) { request = beforeClientExecution ; return executeAssociateWebsiteAuthorizationProvider ( request ) ; }
void (  RevCommit c ) { Block b = head ; if ( b == null ) { b = free . newBlock ( ) ; b . resetToMiddle ( ) ; b . add ( c ) ; head = b ; tail = b ; } else if ( b . canUnpop ( ) ) { b . unpop ( c ) ; return ; } b = free . newBlock ( ) ; b . resetToEnd ( ) ; b . unpop ( c ) ; b . next = head ; head = b ; }
EdgeNGramTokenizerFactory ( Map < String , String > args ) { ( args ) ; minGramSize = getInt ( args , "minGramSize" , ) ; maxGramSize = getInt ( args , "maxGramSize" , EdgeNGramTokenizer . DEFAULT_MAX_GRAM_SIZE ) ; if ( ! args . isEmpty ( ) ) { throw new IllegalArgumentException ( "Unknown parameters: " + args ) ; } }
ModifyDBParameterGroupRequest ( String dBParameterGroupName , java . util . List < Parameter > parameters ) { setDBParameterGroupName ( dBParameterGroupName ) ; setParameters ; }
GetHostedZoneLimitResult (  GetHostedZoneLimitRequest request ) { request = beforeClientExecution ; return executeGetHostedZoneLimit ( request ) ; }
void (  index , value ) { o = index >>> 6 ; b = index & 63 ; shift = b << 0 ; = ( blocks [ o ] & ~ ( 1L << shift ) ) | ( value << shift ) ; }
RevFilter (  ) { return new PatternSearch ( pattern ) ; }
String (  ) { return "spans(" + term . toString ( ) + ")@" + ( doc == ? "START" : ( doc == NO_MORE_DOCS ) ? "ENDDOC" : doc + " - " + ( position == NO_MORE_POSITIONS ? "ENDPOS" : position ) ) ; }
boolean (  ) { for ( Head head : heads ) { if ( head != LastHead . INSTANCE ) { } } return false ; }
(  String subString , start ) { return super . lastIndexOf ( subString , start ) ; }
DeleteNetworkAclEntryResult (  ) { request = beforeClientExecution ( request ) ; return executeDeleteNetworkAclEntry ( request ) ; }
AssociateMemberToGroupResult (  AssociateMemberToGroupRequest request ) { request = beforeClientExecution ; return executeAssociateMemberToGroup ( request ) ; }
public static final (  [ ] b , ptr ) { sz = b . length ; if ( ptr == 0 ) ptr += 46 ; while ( ptr < sz && b [ ptr ] == 'p' ) ; if ( ptr < sz && b [ ptr ] == 'a' ) ptr = nextLF ( b , ptr ) ; return match ( b , ptr , committer ) ; }
(  ) { }
SubmoduleUpdateCommand (  String path ) { paths . add ( path ) ; }
GetPushTemplateResult (  ) { request = beforeClientExecution ( request ) ; return executeGetPushTemplate ( request ) ; }
DescribeVaultResult (  DescribeVaultRequest request ) { request = beforeClientExecution ( request ) ; return executeDescribeVault ; }
DescribeVpcPeeringConnectionsResult (  ) { return describeVpcPeeringConnections ( new DescribeVpcPeeringConnectionsRequest ) ; }
ByteBuffer (  index , value ) { throw new ReadOnlyBufferException ; }
RegisterDeviceResult (  RegisterDeviceRequest request ) { request = beforeClientExecution ; return executeRegisterDevice ( request ) ; }
public static Format (  id ) { for ( Format format : Format . values ( ) ) { if ( format . getId == id ) { return format ; } } throw new IllegalArgumentException ( "Unknown format id: " + id ) ; }
DeleteAppResult (  DeleteAppRequest request ) { request = beforeClientExecution ( request ) ; return executeDeleteApp ; }
GetBaiduChannelResult (  GetBaiduChannelRequest request ) { request = beforeClientExecution ; return executeGetBaiduChannel ( request ) ; }
FST . BytesReader (  ) { return fst . getBytesReader ; }
public static boolean (  index , c ) { if ( ( c >= 'a' && c <= 'z' ) || ( c >= 'A' && c <= 'Z' ) ) { return true ; } if ( index > 0 && ( ( c >= '0' && c <= '9' ) || || c == '-' || c == '.' ) ) { return true ; } return false ; }
ListAppliedSchemaArnsResult (  ListAppliedSchemaArnsRequest request ) { request = beforeClientExecution ; return executeListAppliedSchemaArns ( request ) ; }
String (  ) { return ; }
ValueEval (  ValueEval [ ] args , OperationEvaluationContext ec ) { if ( args . length < 1 ) { return ErrorEval . VALUE_INVALID ; } boolean ; String ; try { ValueEval ve = OperandResolver . getSingleValue ( args [ 0 ] , ec . getRowIndex ( ) , ec . getColumnIndex ( ) ) ; text = OperandResolver . coerceValueToString ( ve ) ; switch ( args . length ) { case 1 : isA1style = true ; break ; case 2 : isA1style = evaluateBooleanArg ( , ec ) ; break ; : return ErrorEval . VALUE_INVALID ; } } catch ( e ) { return e . getErrorEval ( ) ; } return evaluateIndirect ( ec , text , isA1style ) ; }
public final (  [ ] bs , p ) { ; cmp = NB . compareUInt32 ( w1 , bs [ p ] ) ; if ( cmp != 0 ) return cmp ; cmp = NB . compareUInt32 ( w2 , bs [ p + 1 ] ) ; if ( cmp != 0 ) return cmp ; cmp = NB . compareUInt32 ( w3 , bs [ p + 2 ] ) ; if ( cmp != 0 ) cmp = NB . compareUInt32 ( w4 , bs [ p + 3 ] ) ; if ( cmp != 0 ) return cmp ; return NB . compareUInt32 ( w5 , bs [ p + 4 ] ) ; }
void (  index ) { names . remove ( index ) ; workbook . removeName ; }
GetQueueAttributesRequest ( String queueUrl , java . util . List attributeNames ) { setQueueUrl ( queueUrl ) ; setAttributeNames ( attributeNames ) ; }
public static boolean [ ] (  boolean original , newLength ) { if ( newLength < 0 ) { throw new NegativeArraySizeException ( ) ; } return copyOfRange ( original , 0 , newLength ) ; }
public static void (  boolean enabled ) { ; }
DeleteLogPatternResult (  DeleteLogPatternRequest request ) { request = beforeClientExecution ( request ) ; return executeDeleteLogPattern ; }
boolean (  [ ] text , off , len ) { return map . containsKey ; }
(  externSheetNumber ) { return linkTable . getFirstInternalSheetIndexForExtIndex ; }
boolean (  String commandLine ) { return command . length ( ) + 1 < commandLine . length ( ) && commandLine . charAt ( command . length ) == ' ' && commandLine . startsWith ( command ) ; }
void (  MergeStrategy imp ) { register ( imp . getName ( ) , imp ) ; }
(  ) { return BASE_RAM_BYTES_USED + ( ( ) ? index . ramBytesUsed ( ) : 0 ) ; }
HostedZone ( , String name , String callerReference ) { setId ( id ) ; setName ( name ) ; setCallerReference ( callerReference ) ; }
GetFindingsResult (  ) { request = beforeClientExecution ( request ) ; return executeGetFindings ( request ) ; }
DescribeTopicsDetectionJobResult (  DescribeTopicsDetectionJobRequest request ) { request = beforeClientExecution ( request ) ; return executeDescribeTopicsDetectionJob ; }
boolean (  ValueEval eval ) { if ( eval instanceof NumericValueEval ) { if ( minimumValue == null ) { minimumValue = eval ; } else { currentValue = ( ( NumericValueEval ) eval ) . getNumberValue ( ) ; oldValue = ( ( NumericValueEval ) minimumValue ) . getNumberValue ( ) ; if ( currentValue < oldValue ) { ; } } } return true ; }
void (  LittleEndianOutput out ) { out . writeByte ( sid + getPtgClass ) ; out . writeShort ( field_1_len_ref_subexpression ) ; }
public static void (  String [ ] args ) throws IOException { boolean printTree = false ; String path = null ; for ( i = 0 ; i < args . length ; i ++ ) { if ( args [ i ] . equals ( "-printTree" ) ) { printTree = true ; } else { path = ; } } if ( args . length != ( printTree ? 2 : 1 ) ) { System . out . println ( "\nUsage: java -classpath ... org.apache.lucene.facet.util.PrintTaxonomyStats [-printTree] /path/to/taxononmy/index\n" ) ; System . exit ( 1 ) ; } Directory dir = FSDirectory . open ( Paths . get ( path ) ) ; TaxonomyReader r = new DirectoryTaxonomyReader ( dir ) ; printStats ( r , System . out , printTree ) ; r . close ( ) ; dir . close ( ) ; }
void (  value ) { if ( ! ( fieldsData instanceof Byte ) ) { throw new IllegalArgumentException ( "cannot change value type from " + fieldsData . getClass ( ) . getSimpleName + " to Byte" ) ; } fieldsData = Byte . valueOf ( value ) ; }
public static (  ) { return initialize ; }
CachingDoubleValueSource ( DoubleValuesSource source ) { this . source = source ; cache = new HashMap ( ) ; }
AttributeDefinition ( String attributeName , ScalarAttributeType attributeType ) { setAttributeName ; setAttributeType ( attributeType . toString ( ) ) ; }
public static String (  Collection < String > parts , ) { return StringUtils . join ( parts , separator , separator ) ; }
ListTaskDefinitionFamiliesResult (  ListTaskDefinitionFamiliesRequest request ) { request = beforeClientExecution ( request ) ; return executeListTaskDefinitionFamilies ; }
ListComponentsResult (  ListComponentsRequest request ) { request = beforeClientExecution ; return executeListComponents ( request ) ; }
ActivatePhotosRequest (  ProtocolType . HTTPS ) ;
CreateMatchmakingRuleSetResult (  CreateMatchmakingRuleSetRequest request ) { request = beforeClientExecution ; return executeCreateMatchmakingRuleSet ( request ) ; }
ListAvailableManagementCidrRangesResult (  ListAvailableManagementCidrRangesRequest request ) { request = beforeClientExecution ; return executeListAvailableManagementCidrRanges ( request ) ; }
ObjectIdSubclassMap < ObjectId > (  ) { if ( ) return baseObjectIds ; return new ObjectIdSubclassMap < > ( ) ; }
DeletePushTemplateResult (  ) { request = beforeClientExecution ( request ) ; return executeDeletePushTemplate ( request ) ; }
CreateDomainEntryResult (  CreateDomainEntryRequest request ) { request = beforeClientExecution ( request ) ; return executeCreateDomainEntry ; }
public static (  Object [ ] values ) { result = * 1 ; for ( Object value : values ) { result += getEncodedSize ( value ) ; } return result ; }
OpenNLPTokenizerFactory ( Map < String , String > args ) { ( args ) ; sentenceModelFile = require ; tokenizerModelFile = require ( args , TOKENIZER_MODEL ) ; if ( ! args . isEmpty ( ) ) { throw new IllegalArgumentException ( "Unknown parameters: " + args ) ; } }
(  index ) { checkIndex ( index , SizeOf . INT ) ; return Memory . peekInt ( backingArray , offset + index , order ) ; }
List < Head > (  c ) { if ( matches ) { return newHeads ; } return FileNameMatcher . EMPTY_HEAD_LIST ; }
ByteBuffer (  value ) { throw new ReadOnlyBufferException ; }
void (  ) throws IOException { writeObject ( object , true ) ; }
(  index , codePointOffset ) { return Character . offsetByCodePoints ; }
public static (  Collection < BitSet > altsets ) { BitSet all = getAlts ; if ( all . cardinality ( ) == 1 ) return all . nextSetBit ( 0 ) ; return ATN . INVALID_ALT_NUMBER ; }
Date (  ) { return new Date ; }
RuleTagToken ( , bypassTokenType , String label ) { if ( ruleName == null || ruleName . isEmpty ( ) ) { throw new IllegalArgumentException ( "ruleName cannot be null or empty." ) ; } this . ruleName = ruleName ; this . bypassTokenType = bypassTokenType ; this . label = label ; }
DisableOrganizationAdminAccountResult (  DisableOrganizationAdminAccountRequest request ) { request = beforeClientExecution ; return executeDisableOrganizationAdminAccount ( request ) ; }
CreateRoomResult (  CreateRoomRequest request ) { request = beforeClientExecution ( request ) ; return executeCreateRoom ; }
ReplicationGroup (  DeleteReplicationGroupRequest request ) { request = beforeClientExecution ( request ) ; return executeDeleteReplicationGroup ; }
CharBuffer (  ByteBuffer buffer ) { try { return newDecoder ( ) . onMalformedInput ( CodingErrorAction . REPLACE ) . onUnmappableCharacter ( CodingErrorAction . REPLACE ) . decode ( buffer ) ; } catch ( ex ) { throw new Error ( ex . getMessage ( ) , ex ) ; } }
Distribution ( , String status , String domainName ) { setId ( id ) ; setStatus ( status ) ; setDomainName ( domainName ) ; }
[ ] (  ) { return protectedArray ( ) ; }
DateWindow1904Record ( ) { field_1_window = in . readShort ( ) ; }
DeleteDBSnapshotRequest ( String dBSnapshotIdentifier ) { setDBSnapshotIdentifier ; }
public final ParserExtension (  String key ) { return this . extensions . get ; }
void (  ResourceLoader loader ) { try { if ( ) { OpenNLPOpsFactory . getChunkerModel ( chunkerModelFile , loader ) ; } } catch ( e ) { throw new IllegalArgumentException ( e ) ; } }
CompleteVaultLockResult (  CompleteVaultLockRequest request ) { request = beforeClientExecution ; return executeCompleteVaultLock ( request ) ; }
public final [ ] (  ) { return points . clone ; }
(  ) { return values . ramBytesUsed ( ) + super . ramBytesUsed + Long . BYTES + RamUsageEstimator . NUM_BYTES_OBJECT_REF ; }
RegisterInstancesWithLoadBalancerResult (  ) { request = beforeClientExecution ( request ) ; return executeRegisterInstancesWithLoadBalancer ( request ) ; }
DescribeClusterUserKubeconfigRequest ( ) { super ( "CS" , "2015-12-15" , "DescribeClusterUserKubeconfig" , "csk" ) ;  ; setMethod ( MethodType . GET ) ;
PrecisionRecord ( RecordInputStream in ) { field_1_precision = in . readShort ; }
void (  LittleEndianOutput out ) { out . writeShort ( getLeftRowGutter ( ) ) ; out . writeShort ( getTopColGutter ( ) ) ; out . writeShort ( getRowLevelMax ) ; out . writeShort ( getColLevelMax ( ) ) ; }
DeleteVirtualInterfaceResult (  ) { request = beforeClientExecution ( request ) ; return executeDeleteVirtualInterface ( request ) ; }
Entry (  String name ) throws FileNotFoundException { if ( excludes . contains ) { throw new FileNotFoundException ( name ) ; } Entry entry = directory . getEntry ( name ) ; return wrapEntry ( entry ) ;
String (  ) { StringBuilder buffer = new StringBuilder ( ) ; buffer . append ( "[BACKUP]\n" ) ; buffer . append ( "    .backup          = " ) . append ( Integer . toHexString ( getBackup ( ) ) ) . append ( "\n" ) ; buffer . append ; return buffer . toString ( ) ; }
DeleteVoiceConnectorOriginationResult (  DeleteVoiceConnectorOriginationRequest request ) { request = beforeClientExecution ; return executeDeleteVoiceConnectorOrigination ( request ) ; }
Appendable (  c ) { write ; return this ; }
public static (  String fileName ) { if ( fileName . equals ( OLD_SEGMENTS_GEN ) ) { throw new IllegalArgumentException ( "\"" + OLD_SEGMENTS_GEN + "\" is not a valid segment file name since 4.0" ) ; } else if ( fileName . equals ( IndexFileNames . SEGMENTS ) ) { } else if ( fileName . startsWith ( IndexFileNames . SEGMENTS ) ) { return Long . parseLong ( fileName . substring ( 1 + IndexFileNames . SEGMENTS . length ( ) ) , Character . MAX_RADIX ) ; } else { throw new IllegalArgumentException ( "fileName \"" + fileName + "\" is not a segments file" ) ; } }
public static TagOpt (  String o ) { if ( || o . length ( ) == 0 ) return AUTO_FOLLOW ; for ( TagOpt tagopt : values ( ) ) { if ( tagopt . option ( ) . equals ( o ) ) return tagopt ; } throw new IllegalArgumentException ( MessageFormat . format ( JGitText . get ( ) . invalidTagOption , o ) ) ; }
StartContentModerationResult (  StartContentModerationRequest request ) { request = beforeClientExecution ( request ) ; return executeStartContentModeration ; }
public static String (  String s ) { StringBuilder result = new StringBuilder ( s . length ( ) ) ; for ( ; i < s . length ( ) ; i ++ ) { c = s . charAt ( i ) ; if ( c == '\\' || c == '$' ) { result . append ( '\\' ) ; } result . append ( c ) ; } return result . toString ( ) ; }
public final void (  V newValue ) { ; }
QueryParserTokenManager ( CharStream stream ) { ; }
(  elapsed ) { ; if ( modBy == 0 ) val = elapsed / factor ; elseval = % modBy ; if ( type == '0' ) return Math . round ( val ) ; elsereturn ( ) val ; }
LongBuffer (  [ ] dst , dstOffset , longCount ) { byteBuffer . limit ( limit * ) ; byteBuffer . position ( position * SizeOf . LONG ) ; if ( byteBuffer instanceof DirectByteBuffer ) { ( ( DirectByteBuffer ) byteBuffer ) . get ( dst , dstOffset , longCount ) ; } else { ( ( HeapByteBuffer ) byteBuffer ) . get ( dst , dstOffset , longCount ) ; } this . position += longCount ; return this ; }
void (  ) { _listeners . clear ; }
CommonTokenStream ( , channel ) { ( tokenSource ) ; this . channel = channel ; }
ListObjectPoliciesResult (  ListObjectPoliciesRequest request ) { request = beforeClientExecution ( request ) ; return executeListObjectPolicies ; }
ObjectToPack ( , type ) { ( src ) ; flags = type << TYPE_SHIFT ; }
(  s , len ) { numVowels = numVowels ( s , len ) ; for ( i = 0 ; i < affixes . length ; i ++ ) { Affix affix = affixes [ i ] ; if ( numVowels > affix . vc && len >= affix . affix . length + 3 && endsWith ( s , len , affix . affix ) ) { len -= affix . affix . length ; return affix . palatalizes ? unpalatalize ( s , len ) : len ; } } return len ; }
void (  Parser recognizer , RecognitionException e ) { if ( lastErrorIndex == recognizer . getInputStream ( ) . index ( ) && lastErrorStates != null && lastErrorStates . contains ( recognizer . getState ( ) ) ) { recognizer . consume ( ) ; } lastErrorIndex = recognizer . getInputStream ( ) . index ; if ( lastErrorStates == null ) lastErrorStates = new IntervalSet ( ) ; lastErrorStates . add ( recognizer . getState ( ) ) ; IntervalSet followSet = getErrorRecoverySet ( recognizer ) ; consumeUntil ( recognizer , followSet ) ; }
String (  ) { String value = field_3_string ; len = value . length ( ) ; StringBuilder sb = new StringBuilder ( len + 4 ) ; sb . append ( FORMULA_DELIMITER ) ; for ( i = 0 ; ; i ++ ) { c = value . charAt ( i ) ; if ( c == FORMULA_DELIMITER ) { sb . append ( FORMULA_DELIMITER ) ; } sb . append ( c ) ; } sb . append ( FORMULA_DELIMITER ) ; return sb . toString ( ) ; }
UnlinkFaceRequest (  ProtocolType . HTTPS ) ; setMethod ( MethodType . POST ) ;
ConfigurationOptionSetting ( String namespace , String optionName , String value ) { setNamespace ; setOptionName ( optionName ) ; setValue ( value ) ; }
CharSequence (  CharSequence key ) { StringBuilder result = new StringBuilder ( tries . size ( ) * 2 ) ; for ( i = 0 ; i < tries . size ; i ++ ) { CharSequence r = tries . get ( i ) . getFully ( key ) ; if ( r == null || ( r . length ( ) == 1 && r . charAt ( 0 ) == EOM ) ) { return result ; } result . append ( r ) ; } return result ; }
DescribeMountTargetSecurityGroupsResult (  ) { request = beforeClientExecution ( request ) ; return executeDescribeMountTargetSecurityGroups ( request ) ; }
GetApiMappingResult (  GetApiMappingRequest request ) { request = beforeClientExecution ; return executeGetApiMapping ( request ) ; }
HttpRequest ( String strUrl ) { ; }
MemFuncPtg ( subExprLen ) { ; }
public static TermStats [ ] (  IndexReader reader , numTerms , String field , Comparator < TermStats > comparator ) throws Exception { TermStatsQueue ; if ( field != null ) { Terms terms = MultiTerms . getTerms ( reader , field ) ; if ( terms == null ) { throw new RuntimeException ( "field " + field + " not found" ) ; } TermsEnum termsEnum = terms . iterator ( ) ; tiq = new TermStatsQueue ( numTerms , comparator ) ; tiq . fill ( field , termsEnum ) ; } else { Collection < String > fields = FieldInfos . getIndexedFields ( reader ) ; if ( fields . size ( ) == 0 ) { throw new RuntimeException ( "no fields found for this index" ) ; } tiq = new TermStatsQueue ( numTerms , comparator ) ; for ( String fieldName : fields ) { Terms terms = MultiTerms . getTerms ( reader , fieldName ) ; if ( terms != null ) { tiq . fill ( fieldName , terms . iterator ( ) ) ; } } } TermStats [ ] result = new TermStats [ tiq . size ( ) ] ; count = tiq . size ( ) - 1 ; while ( tiq . size ( ) != 0 ) { result [ count ] = tiq . pop ( ) ; count -- ; } return result ; }
DeleteApnsVoipChannelResult (  DeleteApnsVoipChannelRequest request ) { request = beforeClientExecution ( request ) ; return executeDeleteApnsVoipChannel ; }
ListFacesResult (  ) { request = beforeClientExecution ( request ) ; return executeListFaces ( request ) ; }
ShapeFieldCacheDistanceValueSource ( SpatialContext ctx , ShapeFieldCacheProvider < Point > provider , Point from , multiplier ) { this . ctx = ctx ; this . from = from ; = provider ; this . multiplier = multiplier ; }
(  index ) { checkIndex ( index ) ; return sequence . charAt ; }
UpdateConfigurationProfileResult (  UpdateConfigurationProfileRequest request ) { request = beforeClientExecution ( request ) ; return executeUpdateConfigurationProfile ; }
DescribeLifecycleHooksResult (  DescribeLifecycleHooksRequest request ) { request = beforeClientExecution ( request ) ; return executeDescribeLifecycleHooks ; }
DescribeHostReservationsResult (  DescribeHostReservationsRequest request ) { request = beforeClientExecution ; return executeDescribeHostReservations ( request ) ; }
public static PredictionContext (  ATN atn , RuleContext outerContext ) { if ( outerContext == null ) outerContext = RuleContext . EMPTY ; if ( outerContext . parent == null || outerContext == RuleContext . EMPTY ) { return PredictionContext . EMPTY ; } PredictionContext parent = EMPTY ; parent = PredictionContext . fromRuleContext ( atn , outerContext . parent ) ; ATNState state = atn . states . get ( outerContext . invokingState ) ; RuleTransition transition = ( RuleTransition ) state . transition ; return SingletonPredictionContext . create ( parent , transition . followState . stateNumber ) ; }
String (  ) { StringBuilder buffer = new StringBuilder ( ) ; buffer . append ( "[SXVDEX]\n" ) ; buffer . append ( "    .grbit1 =" ) . append ( HexDump . intToHex ( _grbit1 ) ) . append ( "\n" ) ; buffer . append ( "    .grbit2 =" ) . append ( HexDump . byteToHex ( _grbit2 ) ) . append ( "\n" ) ; buffer . append ( "    .citmShow =" ) . append ( HexDump . byteToHex ( _citmShow ) ) . append ; buffer . append ( "    .isxdiSort =" ) . append ( HexDump . shortToHex ( _isxdiSort ) ) . append ( "\n" ) ; buffer . append ( "    .isxdiShow =" ) . append ( HexDump . shortToHex ( _isxdiShow ) ) . append ( "\n" ) ; buffer . append ( "    .subtotalName =" ) . append ( _subtotalName ) . append ( "\n" ) ; buffer . append ( "[/SXVDEX]\n" ) ; return buffer . toString ( ) ; }
String (  ) { StringBuilder r = new StringBuilder ( ) ; r . append ; r . append ( getResultPath ( ) ) ; return r . toString ( ) ; }
ListChangeSetsResult (  ListChangeSetsRequest request ) { request = beforeClientExecution ( request ) ; return executeListChangeSets ; }
boolean (  ) { }
FeatRecord ( ) { futureHeader = new FtrHeader ( ) ; futureHeader . setRecordType ;
ShortBuffer (  c ) { throw new ReadOnlyBufferException ; }
void (  ) { this . query = query ; this . message = new MessageImpl ( QueryParserMessages . INVALID_SYNTAX_CANNOT_PARSE , query , "" ) ; }
StashApplyCommand (  ) { return new StashApplyCommand ; }
Set < String > (  ) { return Collections . unmodifiableSet ( dictionary . values ) ; }
public static (  String scheme , specifiedPort ) { if ( specifiedPort != - 1 ) { return specifiedPort ; } if ( "http" . equalsIgnoreCase ( scheme ) ) { return 80 ; } else if ( "https" . equalsIgnoreCase ) { return 443 ; } else { return - 1 ; } }
ListAssessmentTemplatesResult (  ) { request = beforeClientExecution ( request ) ; return executeListAssessmentTemplates ( request ) ; }
Cluster (  RestoreFromClusterSnapshotRequest request ) { request = beforeClientExecution ( request ) ; return executeRestoreFromClusterSnapshot ; }
void (  ) { shape . setPatriarch ( this . getPatriarch ( ) ) ; shape . setParent ( this ) ; shapes . add ( shape ) ; }
boolean (  Object o ) { if ( this == o ) return true ; if ( o == null || getClass ( ) != o . getClass ) return false ; FacetEntry that = ( FacetEntry ) o ; if ( count != that . count ) return false ; if ( ! value . equals ( that . value ) ) return false ; return true ; }
(  [ ] b , ptr , chrA ) { if ( ptr == b . length ) -- ptr ; while ( ptr >= 0 ) { if ( b [ ptr -- ] == chrA ) return ptr ; } return ptr ; }
boolean (  ) { return deltaBase != null ; }
Token (  ) { cpos = getCharPositionInLine ; line = getLine ( ) ; Token eof = _factory . create ( _tokenFactorySourcePair , Token . EOF , null , Token . DEFAULT_CHANNEL , _input . index ( ) , _input . index ( ) - 1 , line , cpos ) ; emit ( eof ) ; return eof ; }
UpdateUserRequest ( String userName ) { setUserName ; }
RevFilter (  ) { return NotRevFilter . create ; }
void (  ) { tagger = taggerIdent ; }
public static BufferSize (  ) { Runtime rt = Runtime . getRuntime ( ) ; max = rt . maxMemory ( ) ; total = rt . totalMemory ( ) ; free = rt . freeMemory ( ) ; totalAvailableBytes = max - total + free ; sortBufferByteSize = free / 2 ; minBufferSizeBytes = MIN_BUFFER_SIZE_MB * MB ; if ( || totalAvailableBytes > 10 * minBufferSizeBytes ) { if ( totalAvailableBytes / 2 > minBufferSizeBytes ) { sortBufferByteSize = totalAvailableBytes / 2 ; } else { sortBufferByteSize = Math . max ( ABSOLUTE_MIN_SORT_BUFFER_SIZE , sortBufferByteSize ) ; } } return new BufferSize ( Math . min ( ( ) Integer . MAX_VALUE , sortBufferByteSize ) ) ; }
public static (  raw , start , end ) { ptr = end - 1 ; while ( start <= ptr && isWhitespace ( raw [ ptr ] ) ) ptr -- ; return ptr + 1 ; }
TopMarginRecord ( ) { field_1_margin = in . readDouble ( ) ; }
RetrieveEnvironmentInfoRequest ( ) { setInfoType ( infoType . toString ( ) ) ; }
CreatePlayerSessionsResult (  CreatePlayerSessionsRequest request ) { request = beforeClientExecution ; return executeCreatePlayerSessions ( request ) ; }
CreateProxySessionResult (  CreateProxySessionRequest request ) { request = beforeClientExecution ; return executeCreateProxySession ( request ) ; }
(  ) { }
String (  ) { }
void (  [ ] ch , start , length ) { contents . append ; }
FetchAlbumTagPhotosRequest ( ) { super ( "CloudPhoto" , "2017-07-11" , "FetchAlbumTagPhotos" , "cloudphoto" ) ; (  ) ;
DeleteMembersResult (  DeleteMembersRequest request ) { request = beforeClientExecution ; return executeDeleteMembers ( request ) ; }
GetContactReachabilityStatusResult (  ) { request = beforeClientExecution ( request ) ; return executeGetContactReachabilityStatus ( request ) ; }
public boolean (  Object o ) { return Impl . this . remove ( o ) != null ; }
E (  ) { return backingMap . lastKey ; }
CreateStreamingDistributionResult (  CreateStreamingDistributionRequest request ) { request = beforeClientExecution ; return executeCreateStreamingDistribution ( request ) ; }
boolean (  ) { }
DisableAddOnResult (  DisableAddOnRequest request ) { request = beforeClientExecution ( request ) ; return executeDisableAddOn ; }
DescribeAliasResult (  DescribeAliasRequest request ) { request = beforeClientExecution ; return executeDescribeAlias ( request ) ; }
void (  delta ) { while ( -- delta >= 0 ) { if ( ) ptr += currentSubtree . getEntrySpan ( ) ; elseptr ++ ; if ( eof ( ) ) break ; parseEntry ( ) ; } }
RevFilter (  ) { return new Binary ( a . clone , b . clone ( ) ) ; }
Reader (  ) { return new PersianCharFilter ( input ) ; }
String (  ) { }
String (  ) { StringBuilder sb = new StringBuilder ( "[" ) ; for ( Object item : this ) { if ( sb . length > 1 ) sb . append ( ", " ) ; if ( item instanceof [ ] ) { sb . append ( ( [ ] ) item ) ; } else { sb . append ( item ) ; } } return sb . append ( ']' ) . toString ( ) ; }
DescribeSignalingChannelResult (  ) { request = beforeClientExecution ( request ) ; return executeDescribeSignalingChannel ( request ) ; }
AttachStaticIpResult (  AttachStaticIpRequest request ) { request = beforeClientExecution ( request ) ; return executeAttachStaticIp ; }
String (  ) { StringBuilder sb = new StringBuilder ( 64 ) ; CellReference crA = new CellReference ( _firstRowIndex , _firstColumnIndex , false , false ) ; CellReference crB = new CellReference ; sb . append ( getClass ( ) . getName ( ) ) ; sb . append ( " [" ) . append ( crA . formatAsString ( ) ) . append ( ':' ) . append ( crB . formatAsString ( ) ) . append ( "]" ) ; return sb . toString ( ) ; }
BloomFilteringPostingsFormat ( PostingsFormat delegatePostingsFormat , BloomFilterFactory bloomFilterFactory ) { ( BLOOM_CODEC_NAME ) ; = delegatePostingsFormat ; this . bloomFilterFactory = bloomFilterFactory ; }
ListTemplatesResult (  ListTemplatesRequest request ) { request = beforeClientExecution ; return executeListTemplates ( request ) ; }
TimerThread ( resolution , ) { ( THREAD_NAME ) ; this . resolution = resolution ; this . counter = counter ; this . setDaemon ( true ) ; }
DrawingRecord ( ) { ;
ListDirectoriesResult (  ListDirectoriesRequest request ) { request = beforeClientExecution ; return executeListDirectories ( request ) ; }
void (  [ ] blocks , blocksOffset , [ ] values , valuesOffset , iterations ) { for ( j = 0 ; ; ++ j ) { block = blocks [ blocksOffset ++ ] ; values [ valuesOffset ++ ] = ( block >>> 7 ) & 1 ; values [ valuesOffset ++ ] = ( block >>> 6 ) & 1 ; values [ valuesOffset ++ ] = ( block >>> 5 ) & 1 ; values [ valuesOffset ++ ] = ( block >>> 4 ) & 1 ; values [ valuesOffset ++ ] = ( block >>> 3 ) & 1 ; values [ valuesOffset ++ ] = ( block >>> 2 ) & 1 ; values [ valuesOffset ++ ] = ( block >>> 1 ) & 1 ; values [ valuesOffset ++ ] = block & 1 ; } }
GroupingSearch (  ) { this . maxCacheRAMMB = null ; = null ; return this ; }
public static (  need ) { for ( i = 4 ; i < 32 ; i ++ ) if ( need <= ( 1 << i ) - 12 ) return ( 1 << i ) - 12 ; }
UpdateAssessmentTargetResult (  ) { request = beforeClientExecution ( request ) ; return executeUpdateAssessmentTarget ( request ) ; }
ModifyVolumeResult (  ModifyVolumeRequest request ) { request = beforeClientExecution ( request ) ; return executeModifyVolume ; }
Cell (  Cell m , Cell e ) { if ( m . cmd == e . cmd && m . ref == e . ref && == e . skip ) { Cell c = new Cell ( m ) ; c . cnt += e . cnt ; return c ; } else { return null ; } }
ByteBuffer (  length , position ) throws IOException { if ( position >= size ( ) ) { throw new IndexOutOfBoundsException ( + " past the end of the file" ) ; } ByteBuffer ; if ( writable ) { dst = channel . map ( FileChannel . MapMode . READ_WRITE , position , length ) ; buffersToClean . add ( dst ) ; } else { channel . position ( position ) ; dst = ByteBuffer . allocate ( length ) ; worked = IOUtils . readFully ( channel , dst ) ; if ( worked == - 1 ) { throw new IndexOutOfBoundsException ( "Position " + position + " past the end of the file" ) ; } } dst . position ( 0 ) ; return dst ; }
void (  RespondActivityTaskCompletedRequest request ) { request = beforeClientExecution ( request ) ; executeRespondActivityTaskCompleted ; }
public synchronized final void (  diff ) { setProgress ( ) ; }
MetadataDiff (  DirCacheEntry entry ) { if ( entry . isAssumeValid ( ) ) return MetadataDiff . EQUAL ; if ( entry . isUpdateNeeded ( ) ) return MetadataDiff . DIFFER_BY_METADATA ; if ( isModeDifferent ( entry . getRawMode ( ) ) ) return ; type = mode & FileMode . TYPE_MASK ; if ( type == FileMode . TYPE_TREE || type == FileMode . TYPE_GITLINK ) return MetadataDiff . EQUAL ; if ( ! entry . isSmudged ( ) && entry . getLength ( ) != ( ) getEntryLength ( ) ) return MetadataDiff . DIFFER_BY_METADATA ; Instant cacheLastModified = entry . getLastModifiedInstant ( ) ; Instant fileLastModified = getEntryLastModifiedInstant ( ) ; if ( timestampComparator . compare ( cacheLastModified , fileLastModified , getOptions ( ) . getCheckStat ( ) == CheckStat . MINIMAL ) != 0 ) { return MetadataDiff . DIFFER_BY_TIMESTAMP ; } if ( entry . isSmudged ( ) ) { return MetadataDiff . SMUDGED ; } return MetadataDiff . EQUAL ; }
public static NumberRecord (  RKRecord rk ) { NumberRecord num = new NumberRecord ( ) ; num . setColumn ( rk . getColumn ( ) ) ; num . setRow ( rk . getRow ( ) ) ; num . setXFIndex ( rk . getXFIndex ( ) ) ; num . setValue ( rk . getRKNumber ( ) ) ; }
CharBuffer (  src , srcOffset , charCount ) { byteBuffer . limit ( limit * SizeOf . CHAR ) ; byteBuffer . position ( position * SizeOf . CHAR ) ; if ( byteBuffer instanceof ReadWriteDirectByteBuffer ) { ( ( ReadWriteDirectByteBuffer ) byteBuffer ) . put ( src , srcOffset , charCount ) ; } else { ( ( ReadWriteHeapByteBuffer ) byteBuffer ) . put ( src , srcOffset , charCount ) ; } this . position += charCount ; return this ; }
(  ) { Iterator < Character > i = cells . keySet . iterator ( ) ; size = 0 ; for ( ; i . hasNext ( ) ; ) { Character c = i . next ( ) ; Cell e = at ( c ) ; if ( e . cmd >= 0 || e . ref >= 0 ) { size ++ ; } } return size ; }
BeiderMorseFilterFactory ( Map < String , String > args ) { ( args ) ; NameType nameType = NameType . valueOf ( get ( args , "nameType" , NameType . GENERIC . toString ( ) ) ) ; RuleType ruleType = RuleType . valueOf ( get ( args , "ruleType" , RuleType . APPROX . toString ( ) ) ) ; boolean concat = getBoolean ( args , "concat" , true ) ; engine = new PhoneticEngine ( nameType , ruleType , concat ) ; Set < String > langs = getSet ( args , "languageSet" ) ; languageSet = ( null == langs || ( 1 == langs . size ( ) && langs . contains ( "auto" ) ) ) ? null : LanguageSet . from ( langs ) ; if ( ! args . isEmpty ( ) ) { throw new IllegalArgumentException ( ) ; } }
public static (  v ) { r = Double . NaN ; if ( v != null && v . length > 1 ) { r = devsq ( v ) / v . length ; } return r ; }
PersianNormalizationFilterFactory ( Map args ) { ( args ) ; if ( ! args . isEmpty ( ) ) { throw new IllegalArgumentException ( "Unknown parameters: " + args ) ; } }
public static WeightedTerm [ ] (  Query query , boolean prohibited , ) { HashSet < WeightedTerm > terms = new HashSet < > ( ) ; Predicate < String > fieldSelector = fieldName == null ? f -> true : fieldName :: equals ; query . visit ( new BoostedTermExtractor ( 1 , terms , prohibited , fieldSelector ) ) ; return terms . toArray ( new WeightedTerm [ 0 ] ) ; }
DeleteDocumentationPartResult (  ) { request = beforeClientExecution ( request ) ; return executeDeleteDocumentationPart ( request ) ; }
String (  ) { StringBuilder sb = new StringBuilder ( ) ; sb . append ( "[CHART]\n" ) ; sb . append ( "    .x     = " ) . append ( getX ( ) ) . append ( '\n' ) ; sb . append ( "    .y     = " ) . append ( getY ( ) ) . append ( '\n' ) ; sb . append ( "    .width = " ) . append ( getWidth ( ) ) . append ; sb . append ( "    .height= " ) . append ( getHeight ( ) ) . append ( '\n' ) ; sb . append ( "[/CHART]\n" ) ; return sb . toString ( ) ; }
public final (  index ) { checkIndex ( index ) ; return backingArray [ ] ; }
String (  ) { }
ValueEval (  srcRowIndex , srcColumnIndex , ValueEval arg0 , ValueEval arg1 ) { try { AreaEval reA = evaluateRef ( arg0 ) ; AreaEval reB = evaluateRef ( arg1 ) ; AreaEval result = resolveRange ( reA , reB ) ; if ( result == null ) { return ErrorEval . NULL_INTERSECTION ; } } catch ( e ) { return e . getErrorEval ( ) ; } }
void (  ) { weightBySpanQuery . clear ; }
(  StringBuilder buffer , start ) { if ( start > buffer . length ( ) || start < 0 ) bi . setText ( buffer . substring ( start ) ) ; return bi . next ( ) + start ; }
SrndQuery (  ) throws  { SrndQuery ; switch ( ( jj_ntk == - 1 ) ? jj_ntk ( ) : jj_ntk ) { case LPAREN : jj_consume_token ( LPAREN ) ; q = FieldsQuery ( ) ; jj_consume_token ( RPAREN ) ; break ; case OR : case AND : case W : case N : q = PrefixOperatorQuery ( ) ; break ; case TRUNCQUOTED : case QUOTED : case SUFFIXTERM : case TRUNCTERM : case TERM : q = SimpleTerm ( ) ; break ; : jj_la1 [ 5 ] = jj_gen ; jj_consume_token ( - 1 ) ; throw new ParseException ( ) ; } OptionalWeights ( q ) ; { if ( true ) return q ; } throw new Error ( "Missing return statement in function" ) ; }
DeleteApiKeyResult (  ) { request = beforeClientExecution ( request ) ; return executeDeleteApiKey ( request ) ; }
InsertTagsRequest (  MethodType . POST ) ;
DeleteUserByPrincipalIdResult (  ) { request = beforeClientExecution ( request ) ; return executeDeleteUserByPrincipalId ( request ) ; }
DescribeNetworkInterfacesResult (  ) { request = beforeClientExecution ( request ) ; return executeDescribeNetworkInterfaces ( request ) ; }
(  offset , [ ] data , EscherSerializationListener listener ) { listener . beforeRecordSerialize ( offset , getRecordId ( ) , this ) ; LittleEndian . putShort ( data , offset , getOptions ( ) ) ; LittleEndian . putShort ( data , offset + 2 , getRecordId ( ) ) ; LittleEndian . putInt ( data , offset + 4 , 8 ) ; LittleEndian . putInt ( data , offset + 8 , field_1_numShapes ) ; LittleEndian . putInt ( data , offset + 12 , field_2_lastMSOSPID ) ; listener . afterRecordSerialize ( , getRecordId ( ) , getRecordSize ( ) , this ) ; return getRecordSize ( ) ; }
CreateSecurityConfigurationResult (  CreateSecurityConfigurationRequest request ) { request = beforeClientExecution ( request ) ; return executeCreateSecurityConfiguration ; }
DescribeClientVpnConnectionsResult (  DescribeClientVpnConnectionsRequest request ) { request = beforeClientExecution ; return executeDescribeClientVpnConnections ( request ) ; }
void (  [ ] array , value ) { for ( i = 0 ; i < array . length ; i ++ ) { array [ i ] = value ; } }
boolean (  ) { return nextId < ; }
PostingsEnum (  postings ) { this . postings = postings ; upto = - 2 ; freq = 0 ; return this ; }
public final boolean (  RevFlagSet set ) { return ( flags & ) == set . mask ; }
ModifyAccountResult (  ModifyAccountRequest request ) { request = beforeClientExecution ( request ) ; return executeModifyAccount ; }
Token (  k ) { lazyInit ( ) ; if ( ) return null ; if ( k < 0 ) return LB ( - k ) ; i = p + k - 1 ; sync ( i ) ; if ( i >= tokens . size ( ) ) { return tokens . get ( tokens . size ( ) - 1 ) ; } return tokens . get ( i ) ; }
void (  sheetIndex ) { if ( boundsheets . size ( ) > sheetIndex ) { records . remove ( records . getBspos ( ) - ( boundsheets . size ( ) - 1 ) + sheetIndex ) ; boundsheets . remove ( sheetIndex ) ; fixTabIdRecord ( ) ; } sheetNum1Based = ; for ( i = 0 ; i < getNumNames ( ) ; i ++ ) { NameRecord nr = getNameRecord ( i ) ; if ( nr . getSheetNumber ( ) == sheetNum1Based ) { nr . setSheetNumber ( 0 ) ; } else if ( nr . getSheetNumber ( ) > sheetNum1Based ) { nr . setSheetNumber ( nr . getSheetNumber ( ) - 1 ) ; } } if ( linkTable != null ) { linkTable . removeSheet ( sheetIndex ) ; } }
void (  ) { index = getNameIndex ( name ) ; removeName ( index ) ; }
boolean (  Object o ) { if ( ! ( o instanceof Property ) ) { return false ; } Property p = ( Property ) o ; Object pValue = p . getValue ( ) ; pId = p . getID ( ) ; if ( id != pId || ( id != 0 && ! typesAreEqual ( type , p . getType ( ) ) ) ) { return false ; } if ( value == null && pValue == null ) { return true ; } if ( value == null || pValue == null ) { return false ; } Class < > valueClass = value . getClass ( ) ; Class < > pValueClass = pValue . getClass ( ) ; if ( ! ( valueClass . isAssignableFrom ( pValueClass ) ) && ! ( pValueClass . isAssignableFrom ( valueClass ) ) ) { return false ; } if ( value instanceof [ ] ) { [ ] thisVal = ( [ ] ) value , otherVal = ( [ ] ) pValue ; len = unpaddedLength ( thisVal ) ; if ( len != unpaddedLength ( otherVal ) ) { } for ( i = 0 ; i < len ; i ++ ) { if ( thisVal [ i ] != otherVal [ i ] ) { return false ; } } return true ; } return value . equals ( pValue ) ; }
GetRepoBuildListRequest (  "/repos/[RepoNamespace]/[RepoName]/build" ) ; setMethod ( MethodType . GET ) ;
MessageWriter ( ) { buf = new ByteArrayOutputStream ; enc = new OutputStreamWriter ( getRawStream ( ) , UTF_8 ) ;
void (  RecordBase r ) { _recs . add ; }
void (  ) throws IOException { if ( read ( skipBuffer ) != - 1 || actualSize != expectedSize ) { throw new CorruptObjectException ( MessageFormat . format ( JGitText . get ( ) . packfileCorruptionDetected , JGitText . get ( ) . wrongDecompressedLength ) ) ; } used = bAvail - inf . getRemaining ( ) ; if ( ) { onObjectData ( src , buf , p , used ) ; use ( used ) ; } inf . reset ( ) ;
DescribeModelPackageResult (  DescribeModelPackageRequest request ) { request = beforeClientExecution ; return executeDescribeModelPackage ( request ) ; }
void (  CellValueRecordInterface rec , RecordStream rs , ) { if ( rec instanceof FormulaRecord ) { FormulaRecord formulaRec = ( FormulaRecord ) rec ; StringRecord ; Class < ? extends Record > nextClass = rs . peekNextClass ( ) ; if ( nextClass == StringRecord . class ) { cachedText = ( StringRecord ) rs . getNext ( ) ; } else { cachedText = null ; } insertCell ( new FormulaRecordAggregate ( formulaRec , cachedText , sfh ) ) ; } else { insertCell ( rec ) ; } }
Decompressor (  ) { return new DeflateDecompressor ; }
UpdateS3ResourcesResult (  UpdateS3ResourcesRequest request ) { request = beforeClientExecution ; return executeUpdateS3Resources ( request ) ; }
GroupQueryNode ( QueryNode query ) { if ( ) { throw new QueryNodeError ( new MessageImpl ( QueryParserMessages . PARAMETER_VALUE_NOT_SUPPORTED , "query" , "null" ) ) ; } allocate ( ) ; setLeaf ( false ) ; add ( query ) ; }
CharSequence (  EscapeQuerySyntax escaper ) { StringBuilder path = new StringBuilder ( ) ; path . append ( "/" ) . append ( getFirstPathElement ( ) ) ; for ( QueryText pathelement : getPathElements ( 1 ) ) { CharSequence value = escaper . escape ( pathelement . value , Locale . getDefault ( ) , Type . STRING ) ; path . append . append ( value ) . append ( "\"" ) ; } return path . toString ( ) ; }
void (  ) { HSSFComment comment = _sheet . findCellComment ( _record . getRow , _record . getColumn ( ) ) ; _comment = null ; if ( null == comment ) { return ; } _sheet . getDrawingPatriarch ( ) . removeShape ( comment ) ; }
void (  ) { arriving = ; leaving = - 1 ; }
ActivateUserResult (  ActivateUserRequest request ) { request = beforeClientExecution ; return executeActivateUser ( request ) ; }
boolean (  ) { throw new UnsupportedOperationException ; }
Cluster (  ModifySnapshotCopyRetentionPeriodRequest request ) { request = beforeClientExecution ( request ) ; return executeModifySnapshotCopyRetentionPeriod ; }
DeleteClusterSubnetGroupResult (  ) { request = beforeClientExecution ( request ) ; return executeDeleteClusterSubnetGroup ( request ) ; }
public static String (  buffer ) { return decode ( buffer , 0 , buffer . length ) ; }
(  ) { return ; }
StopTaskResult (  StopTaskRequest request ) { request = beforeClientExecution ( request ) ; return executeStopTask ; }
void (  BytesRef target , TermState otherState ) { assert otherState != null && otherState instanceof BlockTermState ; assert ! doOrd || ( ( BlockTermState ) otherState ) . ord < numTerms ; state . copyFrom ( otherState ) ; seekPending = true ; ; term . copyBytes ( target ) ; }
SeriesToChartGroupRecord ( RecordInputStream in ) { field_1_chartGroupIndex = in . readShort ; }
public static void (  LittleEndianOutput out , String value ) { boolean is16Bit = hasMultibyte ( value ) ; out . writeByte ( is16Bit ? 0x01 : 0x00 ) ; if ( is16Bit ) { putUnicodeLE ; } else { putCompressedUnicode ( value , out ) ; } }
AuthorizeSecurityGroupIngressResult (  ) { request = beforeClientExecution ( request ) ; return executeAuthorizeSecurityGroupIngress ( request ) ; }
void (  String file ) { checkFileNames ( Collections . singleton ( file ) ) ; setFiles . add ( namedForThisSegment ) ; }
void (  width , height ) { ; mHeight = height ; }
public final void (  boolean value ) { if { this . reachesIntoOuterContext |= 0x40000000 ; } else { this . reachesIntoOuterContext &= ~ SUPPRESS_PRECEDENCE_FILTER ; } }
IntervalSet (  ATNState s , ) { return LOOK ( s , null , ctx ) ; }
void (  LittleEndianOutput out ) { out . writeShort ( getOptionFlags ) ; out . writeShort ( getRowHeight ( ) ) ; }
Builder ( boolean dedup ) { = dedup ; }
Hashtable ( capacity , loadFactor ) { ( capacity ) ; if ( || Float . isNaN ( loadFactor ) ) { throw new IllegalArgumentException ( "Load factor: " + loadFactor ) ; } }
Object (  CharSequence key ) { bucket = normalCompletion . getBucket ( key ) ; return bucket == - 1 ? null : Long . valueOf ; }
ListHyperParameterTuningJobsResult (  ListHyperParameterTuningJobsRequest request ) { request = beforeClientExecution ; return executeListHyperParameterTuningJobs ( request ) ; }
DeleteTableResult (  String tableName ) { return deleteTable ( new DeleteTableRequest ( ) . withTableName ) ; }
public final boolean (  TextFragment fragA , TextFragment fragB ) { if ( fragA . getScore == fragB . getScore ( ) ) return fragA . fragNum > fragB . fragNum ; elsereturn fragA . (  ) < fragB . getScore ( ) ; }
void (  pos ) { assert pos >= 0 ; assert pos <= nextPos ; newCount = ; assert newCount <= count : "newCount=" + newCount + " count=" + count ; assert newCount <= buffer . length : "newCount=" + newCount + " buf.length=" + buffer . length ; count = newCount ; }
UpdateHITTypeOfHITResult (  UpdateHITTypeOfHITRequest request ) { request = beforeClientExecution ( request ) ; return executeUpdateHITTypeOfHIT ; }
UpdateRecommenderConfigurationResult (  ) { request = beforeClientExecution ( request ) ; return executeUpdateRecommenderConfiguration ( request ) ; }
(  BytesRef other ) { return Arrays . compareUnsigned ( this . bytes , this . offset , this . offset + this . length , , other . offset , other . offset + other . length ) ; }
(  s [ ] , len ) { if ( && s [ len - 1 ] == 's' ) len -- ; if ( len > 5 && ( endsWith ( s , len , "ene" ) || ( endsWith ( s , len , "ane" ) && useNynorsk ) ) ) return len - 3 ; if ( len > 4 && ( endsWith ( s , len , "er" ) || endsWith ( s , len , "en" ) || endsWith ( s , len , "et" ) || ( endsWith ( s , len , "ar" ) && useNynorsk ) ) ) return len - 2 ; if ( len > 3 ) switch ( s [ len - 1 ] ) { case 'a' : case 'e' : return len - 1 ; } return len ; }
DescribeDBSnapshotsResult (  DescribeDBSnapshotsRequest request ) { request = beforeClientExecution ; return executeDescribeDBSnapshots ( request ) ; }
SortedSetDocValuesFacetField ( String dim , String label ) { ( "dummy" , TYPE ) ; FacetField . verifyLabel ( label ) ; FacetField . verifyLabel ; this . dim = dim ; this . label = label ; }
CreateDocumentationPartResult (  CreateDocumentationPartRequest request ) { request = beforeClientExecution ; return executeCreateDocumentationPart ( request ) ; }
String (  ) { }
ShortBuffer (  ) { return duplicate ; }
UpdateDataSourcePermissionsResult (  UpdateDataSourcePermissionsRequest request ) { request = beforeClientExecution ( request ) ; return executeUpdateDataSourcePermissions ; }
public static org . apache . poi . hssf . record . Record (  ) { I_RecordCreator constructor = _recordCreatorsById . get ( Integer . valueOf ( in . getSid ( ) ) ) ; if ( constructor == null ) { return new UnknownRecord ( in ) ; } return constructor . create ( in ) ; }
(  ) { return mTabs . size ; }
DeleteApplicationReferenceDataSourceResult (  DeleteApplicationReferenceDataSourceRequest request ) { request = beforeClientExecution ; return executeDeleteApplicationReferenceDataSource ( request ) ; }
CreateProjectVersionResult (  CreateProjectVersionRequest request ) { request = beforeClientExecution ( request ) ; return executeCreateProjectVersion ; }
IntBuffer (  ) { return new ReadOnlyIntArrayBuffer ( remaining ( ) , backingArray , ) ; }
public final (  ) { if ( position == limit ) { throw new BufferUnderflowException ( ) ; } return this . block . peekByte ( offset + ) ; }
LongBuffer (  index , c ) { checkIndex ; backingArray [ offset + index ] = c ; return this ; }
StoredField ( String name , value ) { super ( name , TYPE ) ; ; }
IntervalSet (  ) { ATN atn = getInterpreter ( ) . atn ; ATNState s = . get ( getState ( ) ) ; return atn . nextTokens ( s ) ; }
String (  ) { StringBuilder buffer = new StringBuilder ( ) ; buffer . append ( "[FILESHARING]\n" ) ; buffer . append ( "    .readonly       = " ) . append ( getReadOnly ( ) == 1 ? "true" : "false" ) . append ( "\n" ) ; buffer . append ( "    .password       = " ) . append ( Integer . toHexString ( getPassword ( ) ) ) . append ( "\n" ) ; buffer . append . append ( getUsername ( ) ) . append ( "\n" ) ; buffer . append ( "[/FILESHARING]\n" ) ; return buffer . toString ( ) ; }
SubmoduleInitCommand ( Repository repo ) { ( repo ) ; paths = new ArrayList ( ) ; }
void (  String name , AnyObjectId id ) { boolean validRefName = Repository . isValidRefName ( name ) || Constants . HEAD . equals ( name ) ; if ( ! validRefName ) throw new IllegalArgumentException ( MessageFormat . format ( JGitText . get ( ) . invalidRefName , name ) ) ; if ( include . containsKey ) throw new IllegalStateException ( JGitText . get ( ) . duplicateRef + name ) ; include . put ( name , id . toObjectId ( ) ) ; }
Cluster (  ) { request = beforeClientExecution ( request ) ; return executeEnableSnapshotCopy ( request ) ; }
ValueFiller (  ) { return new ValueFiller ( ) { private final MutableValueFloat mval = new MutableValueFloat ; @ Override
void (  LittleEndianOutput out ) { out . writeByte ( getPane ( ) ) ; out . writeShort ( getActiveCellRow ( ) ) ; out . writeShort ( getActiveCellCol ( ) ) ; out . writeShort ( getActiveCellRef ( ) ) ; nRefs = ; out . writeShort ( nRefs ) ; for ( CellRangeAddress8Bit field_6_ref : field_6_refs ) { field_6_ref . serialize ( out ) ; } }
public static Counter (  ) { return newCounter ; }
boolean (  String name , boolean dflt ) { boolean vals [ ] = ( boolean [ ] ) valByRound . get ( name ) ; if ( vals != null ) { return vals [ roundNumber % vals . length ] ; } String sval = props . getProperty ( name , "" + dflt ) ; if ( sval . indexOf ( ":" ) < 0 ) { return Boolean . valueOf ( sval ) . booleanValue ( ) ; } k = sval . indexOf ( ":" ) ; String colName = sval . substring ( 0 , k ) ; sval = sval . substring ( k + 1 ) ; colForValByRound . put ( name , colName ) ; vals = propToBooleanArray ( sval ) ; valByRound . put ; return vals [ roundNumber % vals . length ] ; }
void (  ) { if ( records . getTabpos ( ) > 0 ) { TabIdRecord tir = ( TabIdRecord ) records . get ( records . getTabpos ( ) ) ; if ( tir . _tabids . length < boundsheets . size ) { fixTabIdRecord ( ) ; } } }
LimitTokenCountAnalyzer ( Analyzer delegate , maxTokenCount , boolean consumeAllTokens ) { ( delegate . getReuseStrategy ( ) ) ; this . delegate = delegate ; this . maxTokenCount = maxTokenCount ; = consumeAllTokens ; }
ExternalBookBlock ( numberOfSheets ) { _externalBookRecord = SupBookRecord . createInternalReferences ( ( ) numberOfSheets ) ; _externalNameRecords = new ExternalNameRecord [ 0 ] ; _crnBlocks = new CRNBlock ; }
String (  ) { StringBuilder buffer = new StringBuilder ( ) ; buffer . append ( "[SCENARIOPROTECT]\n" ) ; buffer . append ( "    .protect         = " ) . append ( getProtect ) . append ( "\n" ) ; buffer . append ( "[/SCENARIOPROTECT]\n" ) ; return buffer . toString ( ) ; }
PushCommand (  ) { checkCallable ( ) ; this . thin = thin ; return this ; }
(  ) { return Double . compare ( other . recordTimeSec , recordTimeSec ) ; }
ReverseStringFilter (  TokenStream in ) { return new ReverseStringFilter ; }
BlockList directory = BlockList . < T > newDirectory ( 256 ) ; directory [ 0 ] = BlockList . < T > newBlock ( ) ; tailBlock = directory [ 0 ] ;
QueryScorer ( WeightedSpanTerm [ ] weightedTerms ) { this . fieldWeightedSpanTerms = new HashMap < > ( weightedTerms . length ) ; for ( i = 0 ; i < weightedTerms . length ; i ++ ) { WeightedSpanTerm existingTerm = fieldWeightedSpanTerms . get ( weightedTerms [ i ] . term ) ; if ( ( existingTerm == null ) || ( < weightedTerms [ i ] . weight ) ) { fieldWeightedSpanTerms . put ( weightedTerms [ i ] . term , weightedTerms [ i ] ) ; maxTermWeight = Math . max ( maxTermWeight , weightedTerms [ i ] . getWeight ( ) ) ; } } skipInitExtractor = true ; }
boolean (  Object _other ) { assert neverEquals ( _other ) ; if ( _other instanceof MergedGroup ) { MergedGroup < > other = ( MergedGroup < > ) _other ; if ( ) { return other == null ; } else { return groupValue . equals ( other ) ; } } else { return false ; } }
Charset (  ) { return cs ; }
DescribeExperimentResult (  ) { request = beforeClientExecution ( request ) ; return executeDescribeExperiment ( request ) ; }
EscherGraphics ( HSSFShapeGroup escherGroup , , Color forecolor , verticalPointsPerPixel ) { this . escherGroup = escherGroup ; this . workbook = workbook ; this . verticalPointsPerPixel = verticalPointsPerPixel ; this . verticalPixelsPerPoint = 1 / verticalPointsPerPixel ; this . font = new Font ( "Arial" , 0 , 10 ) ; this . foreground = forecolor ; }
String (  ) { }
DeleteRouteTableResult (  DeleteRouteTableRequest request ) { request = beforeClientExecution ( request ) ; return executeDeleteRouteTable ; }
AssociateVPCWithHostedZoneResult (  ) { request = beforeClientExecution ( request ) ; return executeAssociateVPCWithHostedZone ( request ) ; }
PutIntegrationResult (  ) { request = beforeClientExecution ( request ) ; return executePutIntegration ( request ) ; }
SimpleEntry ( K theKey , V theValue ) { ; value = theValue ; }
void (  [ ] blocks , blocksOffset , [ ] values , valuesOffset , iterations ) { for ( ; i < iterations ; ++ i ) { byte0 = blocks [ blocksOffset ++ ] & 0xFF ; byte1 = blocks [ blocksOffset ++ ] & 0xFF ; values [ valuesOffset ++ ] = ( byte0 << 4 ) | ( byte1 >>> 4 ) ; byte2 = blocks [ blocksOffset ++ ] & 0xFF ; values [ valuesOffset ++ ] = ( ( byte1 & 15 ) << 8 ) | byte2 ; } }
DisassociateConnectionFromLagResult (  ) { request = beforeClientExecution ( request ) ; return executeDisassociateConnectionFromLag ( request ) ; }
FileMode (  ) { }
public String (  ) { return m . toString ( ) ; }
StopKeyPhrasesDetectionJobResult (  StopKeyPhrasesDetectionJobRequest request ) { request = beforeClientExecution ( request ) ; return executeStopKeyPhrasesDetectionJob ; }
String (  ) { return "[Array Formula or Shared Formula]\n" + "row = " + getRow + "\n" + "col = " + getColumn ( ) + "\n" ; }
ListDominantLanguageDetectionJobsResult (  ) { request = beforeClientExecution ( request ) ; return executeListDominantLanguageDetectionJobs ( request ) ; }
String (  ) { return + " length=" + length + " readerIndex=" + readerIndex ; }
public static final (  digit ) { r = digits16 [ digit ] ; if ( ) throw new ArrayIndexOutOfBoundsException ( ) ; return r ; }
Attribute ( String name , ) { setName ( name ) ; setValue ( value ) ; }
DescribeStackSetOperationResult (  DescribeStackSetOperationRequest request ) { request = beforeClientExecution ; return executeDescribeStackSetOperation ( request ) ; }
HSSFCell (  cellnum ) { return getCell ( cellnum , book . getMissingCellPolicy ) ; }
void (  [ ] b ) { writeContinueIfRequired ( b . length ) ; _ulrOutput . write ; }
ResetImageAttributeRequest ( String imageId , ResetImageAttributeName attribute ) { setImageId ; setAttribute ( attribute . toString ( ) ) ; }
void (  ) { ; }
ObjectId (  ) { return getLeaf ( ) . getPeeledObjectId ; }
void (  UndeprecateDomainRequest request ) { request = beforeClientExecution ( request ) ; executeUndeprecateDomain ; }
void (  LittleEndianOutput out ) { out . writeByte ( sid + getPtgClass ( ) ) ; out . writeByte ( field_3_string . length ( ) ) ; out . writeByte ( _is16bitUnicode ? 0x01 : 0x00 ) ; if ( _is16bitUnicode ) { StringUtil . putUnicodeLE ; } else { StringUtil . putCompressedUnicode ( field_3_string , out ) ; } }
DeleteQueueResult (  String queueUrl ) { return deleteQueue ( new DeleteQueueRequest ( ) . withQueueUrl ) ; }
void (  ) { checkEofAfterPackFooter = b ; }
void (  ) { ; sEnd = endA ; beginA = beginB ; endA = endB ; beginB = sBegin ; endB = sEnd ; }
(  ) { }
PutMetricDataResult (  PutMetricDataRequest request ) { request = beforeClientExecution ; return executePutMetricData ( request ) ; }
GetCelebrityRecognitionResult (  ) { request = beforeClientExecution ( request ) ; return executeGetCelebrityRecognition ( request ) ; }
CreateQueueRequest ( String queueName ) { setQueueName ; }
Area3DPxg ( externalWorkbookNumber , SheetIdentifier sheetName , AreaReference arearef ) { ( arearef ) ; this . externalWorkbookNumber = externalWorkbookNumber ; this . firstSheetName = sheetName . getSheetIdentifier ( ) . getName ( ) ; if ( sheetName instanceof SheetRangeIdentifier ) { = ( ( SheetRangeIdentifier ) sheetName ) . getLastSheetIdentifier ( ) . getName ( ) ; } else { this . lastSheetName = null ; } }
void (  clockTime ) { t0 = clockTime ; timeout = ; }
MoveAddressToVpcResult (  MoveAddressToVpcRequest request ) { request = beforeClientExecution ( request ) ; return executeMoveAddressToVpc ; }
String (  ) { String coll = collectionModel . getName ( ) ; if ( coll != null ) { return String . format ( Locale . ROOT , "LM %s - %s" , getName ( ) , coll ) ; } else { return String . format ( , "LM %s" , getName ( ) ) ; } }
DescribeLagsResult (  DescribeLagsRequest request ) { request = beforeClientExecution ( request ) ; return executeDescribeLags ; }
AreaEval (  relFirstRowIx , relLastRowIx , relFirstColIx , relLastColIx ) { if ( ) { return _areaEval . offset ( relFirstRowIx , relLastRowIx , relFirstColIx , relLastColIx ) ; } return _refEval . offset ( relFirstRowIx , relLastRowIx , relFirstColIx , relLastColIx ) ; }
ShortBuffer (  [ ] src , srcOffset , shortCount ) { byteBuffer . limit ( limit * SizeOf . SHORT ) ; byteBuffer . position ( position * SizeOf . SHORT ) ; if ( byteBuffer instanceof ReadWriteDirectByteBuffer ) { ( ) . put ( src , srcOffset , shortCount ) ; } else { ( ( ReadWriteHeapByteBuffer ) byteBuffer ) . put ( src , srcOffset , shortCount ) ; } this . position += shortCount ; return this ; }
void (  String cat ) { = cat ; }
void (  oneByte ) throws IOException { out . write ; written ++ ; }
DescribeImportImageTasksResult (  DescribeImportImageTasksRequest request ) { request = beforeClientExecution ; return executeDescribeImportImageTasks ( request ) ; }
ColumnInfoRecord ( RecordInputStream in ) { _firstCol = in . readUShort ( ) ; _lastCol = in . readUShort ( ) ; _colWidth = in . readUShort ( ) ; _xfIndex = in . readUShort ( ) ; _options = in . readUShort ( ) ; switch ( in . remaining ( ) ) { case 2 : field_6_reserved = in . readUShort ( ) ; break ; case 1 : field_6_reserved = in . readByte ; break ; case 0 : field_6_reserved = 0 ; break ; : throw new RuntimeException ( "Unusual record size remaining=(" + in . remaining ( ) + ")" ) ; } }
Status ( ) { super ( ) ; this . diff = diff ; hasUncommittedChanges = ! diff . getAdded ( ) . isEmpty ( ) || ! diff . getChanged ( ) . isEmpty ( ) || ! diff . getRemoved ( ) . isEmpty ( ) || ! diff . getMissing ( ) . isEmpty ( ) || ! diff . getModified ( ) . isEmpty ( ) || ! diff . getConflicting ( ) . isEmpty ( ) ; clean = ! hasUncommittedChanges && diff . getUntracked ( ) . isEmpty ( ) ; }
CreateExperimentResult (  CreateExperimentRequest request ) { request = beforeClientExecution ( request ) ; return executeCreateExperiment ; }
UnknownRecord (  ) { return copy ; }
FloatBuffer (  ) { byteBuffer . limit ( limit * SizeOf . FLOAT ) ; byteBuffer . position ( position * SizeOf . FLOAT ) ; ByteBuffer bb = byteBuffer . slice ( ) . order ( byteBuffer . order ( ) ) ; FloatBuffer result = new FloatToByteBufferAdapter ; byteBuffer . clear ( ) ; return result ; }
DescribeSnapshotSchedulesResult (  ) { request = beforeClientExecution ( request ) ; return executeDescribeSnapshotSchedules ( request ) ; }
ListImagesResult (  ) { request = beforeClientExecution ( request ) ; return executeListImages ( request ) ; }
Diff ( ins , del , rep , noop ) { ; DELETE = del ; REPLACE = rep ; NOOP = noop ; }
String (  String operands ) { StringBuilder buffer = new StringBuilder ( ) ; buffer . append ( operands [ 0 ] ) ; buffer . append ( "," ) ; buffer . append ( operands [ 1 ] ) ; return buffer . toString ( ) ; }
void (  String [ ] workbookNames , ForkedEvaluator [ ] evaluators ) { WorkbookEvaluator [ ] wbEvals = new WorkbookEvaluator [ evaluators . length ] ; for ( i = 0 ; i < wbEvals . length ; i ++ ) { wbEvals [ i ] = evaluators [ i ] . _evaluator ; } CollaboratingWorkbooksEnvironment . setup ( workbookNames , wbEvals ) ; }
ListPhotoTagsRequest (  ProtocolType . HTTPS ) ;
RandomSamplingFacetsCollector ( sampleSize , seed ) { ( false ) ; this . sampleSize = sampleSize ; this . random = new XORShift64Random ( seed ) ; = null ; }
AllocateStaticIpResult (  AllocateStaticIpRequest request ) { request = beforeClientExecution ; return executeAllocateStaticIp ( request ) ; }
FeatRecord ( RecordInputStream in ) { futureHeader = new FtrHeader ( in ) ; isf_sharedFeatureType = in . readShort ( ) ; reserved1 = in . readByte ( ) ; reserved2 = in . readInt ( ) ; cref = in . readUShort ( ) ; cbFeatData = in . readInt ( ) ; reserved3 = in . readShort ( ) ; cellRefs = new CellRangeAddress [ cref ] ; for ( i = 0 ; i < cellRefs . length ; i ++ ) { cellRefs [ i ] = new CellRangeAddress ( in ) ; } switch ( isf_sharedFeatureType ) { case FeatHdrRecord . SHAREDFEATURES_ISFPROTECTION : sharedFeature = new FeatProtection ( in ) ; case FeatHdrRecord . SHAREDFEATURES_ISFFEC2 : sharedFeature = new FeatFormulaErr2 ( in ) ; break ; case FeatHdrRecord . SHAREDFEATURES_ISFFACTOID : sharedFeature = new FeatSmartTag ( in ) ; break ; : logger . log ( POILogger . ERROR , "Unknown Shared Feature " + isf_sharedFeatureType + " found!" ) ; } }
RevCommit (  RevCommit newCommit ) throws IOException , GitAPIException { Ref head = getHead ( ) ; ObjectId headId = head . getObjectId ; if ( headId == null ) throw new RefNotFoundException ( MessageFormat . format ( JGitText . get ( ) . refNotResolved , Constants . HEAD ) ) ; RevCommit headCommit = walk . lookupCommit ( headId ) ; if ( walk . isMergedInto ( newCommit , headCommit ) ) return newCommit ; String headName = getHeadName ( head ) ; return tryFastForward ( headName , headCommit , newCommit ) ; }
CreateSnapshotScheduleResult (  CreateSnapshotScheduleRequest request ) { request = beforeClientExecution ; return executeCreateSnapshotSchedule ( request ) ; }
Record (  ) { if ( ! hasNext ( ) ) { throw new RuntimeException ( "Attempt to read past end of record stream" ) ; } _countRead ++ ; return _list . get ( ) ; }
String (  ) { return RawParseUtils . decode ( buf . toByteArray ) ; }
ListTablesRequest ( String exclusiveStartTableName ) { setExclusiveStartTableName ; }
EnableAlarmActionsResult (  ) { request = beforeClientExecution ( request ) ; return executeEnableAlarmActions ( request ) ; }
Builder ( ) {  ;
boolean (  Object obj ) { State other = ( State ) obj ; return is_final == other . is_final && Arrays . equals ( this . labels , ) && referenceEquals ( this . states , other . states ) ; }
TokenStream (  TokenStream input ) { return new EnglishPossessiveFilter ; }
void (  ) { _string = cloneStringIfRequired ( ) ; _string . clearFormatting ; addToSSTIfRequired ( ) ; }
(  index , [ ] arr , off , len ) { assert len > 0 : "len must be > 0 (got " + len + ")" ; assert index >= 0 && ; len = Math . min ( len , valueCount - index ) ; Arrays . fill ( arr , off , off + len , 0 ) ; return len ; }
DeleteRouteResponseResult (  ) { request = beforeClientExecution ( request ) ; return executeDeleteRouteResponse ( request ) ; }
String (  ) { return format ; }
CreatePresignedDomainUrlResult (  CreatePresignedDomainUrlRequest request ) { request = beforeClientExecution ( request ) ; return executeCreatePresignedDomainUrl ; }
void (  oneChar ) { doWrite ( new { ( ) oneChar } , 0 , 1 ) ; }
SSTRecord (  ) { }
String (  ) { return + ",field=" + field + ",value=" + valueToString ( ) + ",docIDUpto=" + docIDUpto ; }
boolean (  , FieldInfo fieldInfo ) { return bloomFilter . getSaturation ( ) > 0.9f ; }
Builder ( ) { this . ignoreCase = ignoreCase ; }
String (  ) { return getClass . getName ( ) + "(maxBasicQueries: " + maxBasicQueries + ", queriesMade: " + queriesMade + ")" ; }
DeleteDataSourceResult (  DeleteDataSourceRequest request ) { request = beforeClientExecution ; return executeDeleteDataSource ( request ) ; }
RebootNodeResult (  RebootNodeRequest request ) { request = beforeClientExecution ; return executeRebootNode ( request ) ; }
void (  ) { convertRawBytesToEscherRecords ; }
CreateOrUpdateTagsResult (  CreateOrUpdateTagsRequest request ) { request = beforeClientExecution ( request ) ; return executeCreateOrUpdateTags ; }
FileSnapshot (  ) { }
InputStream (  String resource ) throws IOException { InputStream stream = ( clazz != null ) ? clazz . getResourceAsStream ( resource ) : loader . getResourceAsStream ( resource ) ; if ( stream == null ) throw new IOException ( "Resource not found: " + resource ) ; }
String (  ) { StringBuilder sb = new StringBuilder ( 64 ) ; sb . append ( getClass ( ) . getName ( ) ) . append ; sb . append ( "sid=" ) . append ( HexDump . shortToHex ( _sid ) ) ; sb . append ( " size=" ) . append ( _data . length ) ; sb . append ( " : " ) . append ( HexDump . toHex ( _data ) ) ; sb . append ( "]\n" ) ; return sb . toString ( ) ; }
(  ) { }
CharSequence (  EscapeQuerySyntax escaper ) { if ( isDefaultField ( ) ) { return "\"" + getTermEscapeQuoted ( escaper ) + "\"" ; } else { return this . field + ":" + "\"" + getTermEscapeQuoted ( escaper ) + "\"" ; } }
CalcModeRecord (  ) { return copy ; }
boolean (  ) { }
CreateNetworkInterfaceResult (  CreateNetworkInterfaceRequest request ) { request = beforeClientExecution ; return executeCreateNetworkInterface ( request ) ; }
void (  ) { out . writeShort ( field_1_password ) ; }
StopDominantLanguageDetectionJobResult (  ) { request = beforeClientExecution ( request ) ; return executeStopDominantLanguageDetectionJob ( request ) ; }
ECSMetadataServiceCredentialsFetcher (  milliseconds ) { this . connectionTimeoutInMilliseconds = milliseconds ; }
GetGatewayGroupResult (  ) { request = beforeClientExecution ( request ) ; return executeGetGatewayGroup ( request ) ; }
FloatBuffer (  ) { return new ReadOnlyFloatArrayBuffer ( remaining ( ) , backingArray , ) ; }
public static String (  Collection < String > parts , String separator , ) { StringBuilder sb = new StringBuilder ( ) ; i = 0 ; lastIndex = parts . size ( ) - 1 ; for ( String part : parts ) { sb . append ( part ) ; if ( i == lastIndex - 1 ) { sb . append ( lastSeparator ) ; } else if ( i != lastIndex ) { sb . append ( separator ) ; } i ++ ; } return sb . toString ( ) ; }
String (  ) { return "(" + a . toString + " AND " + b . toString ( ) + ")" ; }
ListSubscriptionsByTopicRequest ( String topicArn , ) { setTopicArn ( topicArn ) ; setNextToken ( nextToken ) ; }
(  ) { return bytes [ ] ; }
TerminateClientVpnConnectionsResult (  ) { request = beforeClientExecution ( request ) ; return executeTerminateClientVpnConnections ( request ) ; }
ReceiveMessageRequest ( ) { setQueueUrl ( queueUrl ) ; }
void (  ) { out . writeShort ( field_1_barSpace ) ; out . writeShort ( field_2_categorySpace ) ; out . writeShort ( field_3_formatFlags ) ; }
Object (  Object output1 , Object output2 ) { return outputs . common ( , ( T ) output2 ) ; }
CreateVariableResult (  CreateVariableRequest request ) { request = beforeClientExecution ( request ) ; return executeCreateVariable ; }
(  [ ] b , ptr , [ ] src ) { if ( ptr + src . length > b . length ) return - 1 ; for ( i = 0 ; i < src . length ; i ++ , ptr ++ ) if ( b [ ptr ] != src [ i ] ) return - 1 ; return ptr ; }
(  data , offset , EscherRecordFactory recordFactory ) { bytesRemaining = readHeader ( data , offset ) ; pos = offset + 8 ; size = 0 ; field_1_rectX1 = LittleEndian . getInt ( data , pos + size ) ; size += 4 ; field_2_rectY1 = LittleEndian . getInt ( data , pos + size ) ; size += 4 ; field_3_rectX2 = LittleEndian . getInt ( data , pos + size ) ; size += 4 ; field_4_rectY2 = LittleEndian . getInt ( data , pos + size ) ; size += 4 ; bytesRemaining -= size ; if ( bytesRemaining != 0 ) { throw new RecordFormatException ( "Expected no remaining bytes but got " + bytesRemaining ) ; } return 8 + size + bytesRemaining ; }
CreateCloudFrontOriginAccessIdentityResult (  CreateCloudFrontOriginAccessIdentityRequest request ) { request = beforeClientExecution ; return executeCreateCloudFrontOriginAccessIdentity ( request ) ; }
boolean (  ) { return getFeature ( ) ; }
void (  ) { overridable = on ; }
String (  ) { }
DirectoryReader (  ) { if ( indexReader != null ) { indexReader . incRef ( ) ; } return indexReader ; }
(  key ) { return binarySearch ; }
BlankRecord ( ) { field_1_row = in . readUShort ( ) ; field_2_col = in . readShort ( ) ; field_3_xf = in . readShort ( ) ; }
(  ) { }
PasswordRecord ( ) { field_1_password = in . readShort ( ) ; }
HashMap ( capacity , loadFactor ) { ; if ( loadFactor <= 0 || Float . isNaN ( loadFactor ) ) { throw new IllegalArgumentException ( "Load factor: " + loadFactor ) ; } }
void (  ) { lastReopenStartNS = System . nanoTime ( ) ; while ( ! finish ) { while ( ! finish ) { reopenLock . lock ( ) ; try { boolean hasWaiting = ; nextReopenStartNS = lastReopenStartNS + ( hasWaiting ? targetMinStaleNS : targetMaxStaleNS ) ; sleepNS = nextReopenStartNS - System . nanoTime ( ) ; if ( sleepNS > 0 ) { reopenCond . awaitNanos ( sleepNS ) ; } else { break ; } } catch ( ie ) { Thread . currentThread ( ) . interrupt ( ) ; return ; } finally { reopenLock . unlock ( ) ; } } if ( finish ) { break ; } lastReopenStartNS = System . nanoTime ( ) ; refreshStartGen = writer . getMaxCompletedSequenceNumber ( ) ; try { manager . maybeRefreshBlocking ( ) ; } catch ( ioe ) { throw new RuntimeException ( ioe ) ; } } }
DeleteLoginProfileRequest ( String userName ) { setUserName ; }
E (  ) { return ( size == 0 ) ? null : removeFirstImpl ; }
CreatePhotoRequest ( ) { super ( "CloudPhoto" , "2017-07-11" , "CreatePhoto" , "cloudphoto" ) ; (  ) ;
String (  ) { }
(  StringBuilder buffer , start ) { if ( start > buffer . length ( ) || start < 0 ) return start ; , count = maxScan ; for ( offset = start ; offset < buffer . length ( ) && ; count -- ) { if ( boundaryChars . contains ( buffer . charAt ( offset ) ) ) return offset ; offset ++ ; } return start ; }
void (  ) { objCheck = oc ; }
BaseRef ( AreaEval ae ) { _refEval = null ; _areaEval = ae ; _firstRowIndex = ae . getFirstRow ( ) ; _firstColumnIndex = ae . getFirstColumn ; _height = ae . getLastRow ( ) - ae . getFirstRow ( ) + 1 ; _width = ae . getLastColumn ( ) - ae . getFirstColumn ( ) + 1 ; }
CreateVpcEndpointResult (  CreateVpcEndpointRequest request ) { request = beforeClientExecution ; return executeCreateVpcEndpoint ( request ) ; }
DeregisterWorkspaceDirectoryResult (  DeregisterWorkspaceDirectoryRequest request ) { request = beforeClientExecution ( request ) ; return executeDeregisterWorkspaceDirectory ; }
ChartFRTInfoRecord ( RecordInputStream in ) { rt = in . readShort ( ) ; grbitFrt = in . readShort ( ) ; verOriginator = in . readByte ( ) ; verWriter = in . readByte ( ) ; cCFRTID = in . readShort ( ) ; rgCFRTID = new CFRTID [ cCFRTID ] ; for ( ; i < cCFRTID ; i ++ ) { rgCFRTID [ i ] = new CFRTID ( in ) ; } }
Merger (  Repository db ) { return new OneSide ; }
CreateDataSourceFromRedshiftResult (  CreateDataSourceFromRedshiftRequest request ) { request = beforeClientExecution ; return executeCreateDataSourceFromRedshift ( request ) ; }
void (  ) { for ( d = 0 ; d < decisionToDFA . length ; ) { decisionToDFA [ d ] = new DFA ( atn . getDecisionState ( d ) , d ) ; } }
void (  String name ) { index = getNameIndex ( name ) ; removeName ; }
String (  ) { StringBuilder buffer = new StringBuilder ( ) ; buffer . append ( "[RightMargin]\n" ) ; buffer . append ( "    .margin               = " ) . append . append ( getMargin ( ) ) . append ( " )\n" ) ; buffer . append ( "[/RightMargin]\n" ) ; return buffer . toString ( ) ; }
RefreshAllRecord (  ) { return copy ; }
StandardQueryNodeProcessorPipeline ( QueryConfigHandler queryConfig ) { ( queryConfig ) ; add ( new WildcardQueryNodeProcessor ( ) ) ; add ( new MultiFieldQueryNodeProcessor ( ) ) ; add ( new FuzzyQueryNodeProcessor ( ) ) ; add ( new RegexpQueryNodeProcessor ( ) ) ; add ( new MatchAllDocsQueryNodeProcessor ( ) ) ; add ( new OpenRangeQueryNodeProcessor ( ) ) ; add ( new PointQueryNodeProcessor ( ) ) ; add ( new PointRangeQueryNodeProcessor ( ) ) ; add ( new TermRangeQueryNodeProcessor ( ) ) ; add ( new AllowLeadingWildcardProcessor ( ) ) ; add ( new AnalyzerQueryNodeProcessor ( ) ) ; add ( new PhraseSlopQueryNodeProcessor ( ) ) ; add ( new BooleanQuery2ModifierNodeProcessor ( ) ) ; add ( new NoChildOptimizationQueryNodeProcessor ( ) ) ; add ( new RemoveDeletedQueryNodesProcessor ( ) ) ; add ( new RemoveEmptyNonLeafQueryNodeProcessor ( ) ) ; add ( new BooleanSingleChildOptimizationQueryNodeProcessor ( ) ) ; add ( new DefaultPhraseSlopQueryNodeProcessor ( ) ) ; add ( new BoostQueryNodeProcessor ) ; add ( new MultiTermRewriteMethodProcessor ( ) ) ; }
String (  String sheetName , boolean useAbsoluteAddress ) { StringBuilder sb = new StringBuilder ( ) ; if ( sheetName != null ) { sb . append ( SheetNameFormatter . format ( sheetName ) ) ; sb . append ( "!" ) ; } CellReference cellRefFrom = new CellReference ( getFirstRow , getFirstColumn ( ) , useAbsoluteAddress , useAbsoluteAddress ) ; CellReference cellRefTo = new CellReference ( getLastRow ( ) , getLastColumn ( ) , useAbsoluteAddress , useAbsoluteAddress ) ; sb . append ( cellRefFrom . formatAsString ( ) ) ; if ( ! cellRefFrom . equals ( cellRefTo ) || isFullColumnRange ( ) || isFullRowRange ( ) ) { sb . append ( ':' ) ; sb . append ( cellRefTo . formatAsString ( ) ) ; } return sb . toString ( ) ; }
ByteBuffer (  index , value ) { throw new ReadOnlyBufferException ; }
void (  m ) { ; }
ShortBuffer (  ) { return new ReadWriteShortArrayBuffer ( remaining , backingArray , offset + position ) ; }
void (  index , n ) { if ( count < index ) throw new ArrayIndexOutOfBoundsException ( index ) ; else if ( ) add ( n ) ; elseentries [ index ] = n ; }
ByteBuffer (  value ) { throw new ReadOnlyBufferException ; }
(  [ ] values ) { max = Double . NEGATIVE_INFINITY ; for ( value : values ) { max = Math . max ( max , value ) ; } return max ; }
UpdateRepoWebhookRequest ( ) { super ( "cr" , "2016-06-07" , "UpdateRepoWebhook" , "cr" ) ; (  "/repos/[RepoNamespace]/[RepoName]/webhooks/[WebhookId]" ) ; setMethod ( ) ;
DeleteAttributesRequest ( String domainName , String itemName , java . util . List < Attribute > attributes , UpdateCondition expected ) { setDomainName ( domainName ) ; setItemName ( itemName ) ; setAttributes ; setExpected ( expected ) ; }
String (  ) { StringBuilder sb = new StringBuilder ( ) ; sb . append ( "[SXPI]\n" ) ; for ( i = 0 ; i < ; i ++ ) { sb . append ( "    item[" ) . append ( i ) . append ( "]=" ) ; _fieldInfos [ i ] . appendDebugInfo ( sb ) ; sb . append ( '\n' ) ; } sb . append ( "[/SXPI]\n" ) ; return sb . toString ( ) ; }
boolean (  ) { if ( mergeResult != null ) return mergeResult . getMergeStatus ( ) . isSuccessful ( ) ; else if ( rebaseResult != null ) return rebaseResult . getStatus . isSuccessful ( ) ; return true ; }
void (  [ ] value ) { setBytesValue ( new BytesRef ) ; }
DescribeConnectionsResult (  DescribeConnectionsRequest request ) { request = beforeClientExecution ( request ) ; return executeDescribeConnections ; }
DeletePhotosRequest ( ) { super ( "CloudPhoto" , "2017-07-11" , "DeletePhotos" , "cloudphoto" ) ; (  ) ;
void (  E object ) { iterator . add ( object ) ; subList . sizeChanged ( true ) ; ; }
public static ByteBuffer (  capacity ) { if ( capacity < 0 ) { throw new IllegalArgumentException ( ) ; } return new ReadWriteHeapByteBuffer ; }
SrndQuery (  qn ) { return queries . get ; }
(  docId , String field , start , end , numPayloadsSeen , currentScore , currentPayloadScore ) { if ( numPayloadsSeen == 0 ) { return currentPayloadScore ; } else { return Math . min ; } }
String (  ) { StringBuilder sb = new StringBuilder ( ) ; sb . append ( "[BLANK]\n" ) ; sb . append ( "    row= " ) . append ( HexDump . shortToHex ( getRow ( ) ) ) . append ( "\n" ) ; sb . append ( "    col= " ) . append ( HexDump . shortToHex ( getColumn ) ) . append ( "\n" ) ; sb . append ( "    xf = " ) . append ( HexDump . shortToHex ( getXFIndex ( ) ) ) . append ( "\n" ) ; sb . append ( "[/BLANK]\n" ) ; return sb . toString ( ) ; }
DescribeLogPatternResult (  ) { request = beforeClientExecution ( request ) ; return executeDescribeLogPattern ( request ) ; }
RegisterTransitGatewayMulticastGroupMembersResult (  ) { request = beforeClientExecution ( request ) ; return executeRegisterTransitGatewayMulticastGroupMembers ( request ) ; }
GetPhoneNumberSettingsResult (  GetPhoneNumberSettingsRequest request ) { request = beforeClientExecution ( request ) ; return executeGetPhoneNumberSettings ; }
ObjectId (  ) { }
boolean (  ) { }
DeleteServerCertificateRequest ( ) { setServerCertificateName ( serverCertificateName ) ; }
StringBuffer (  d ) { RealToString . getInstance ( ) . appendDouble ; return this ; }
GetEvaluationResult (  GetEvaluationRequest request ) { request = beforeClientExecution ( request ) ; return executeGetEvaluation ; }
LinkedDataRecord (  ) { }
boolean (  start ) { findPos = start ; if ( findPos < regionStart ) { findPos = regionStart ; } else if ( findPos >= regionEnd ) { matchFound = false ; return false ; } matchFound = findImpl ; if ( matchFound ) { findPos = matchOffsets [ 1 ] ; } return matchFound ; }
GetLifecyclePolicyPreviewResult (  GetLifecyclePolicyPreviewRequest request ) { request = beforeClientExecution ( request ) ; return executeGetLifecyclePolicyPreview ; }
SinglePositionTokenStream ( String word ) { termAtt = addAttribute ( CharTermAttribute . class ) ; posIncrAtt = addAttribute ( PositionIncrementAttribute . class ) ; this . word = word ; ; }
void (  LittleEndianOutput out ) { out . writeShort ; }
String (  ) { StringBuilder s = new StringBuilder ( ) ; s . append ( Constants . typeString ( getType ) ) ; s . append ( ' ' ) ; s . append ( name ( ) ) ; s . append ( ' ' ) ; s . append ( commitTime ) ; s . append ( ' ' ) ; appendCoreFlags ( s ) ; return s . toString ( ) ; }
LsRemoteCommand (  String remote ) { checkCallable ( ) ; this . remote = remote ; }
void (  rowNumber ) { startRow = findStartOfRowOutlineGroup ( rowNumber ) ; RowRecord rowRecord = getRow ( startRow ) ; nextRowIx = writeHidden ( rowRecord , startRow ) ; RowRecord row = getRow ( nextRowIx ) ; if ( row == null ) { row = createRow ( nextRowIx ) ; insertRow ( row ) ; } row . setColapsed ; }
AssociateSkillGroupWithRoomResult (  AssociateSkillGroupWithRoomRequest request ) { request = beforeClientExecution ( request ) ; return executeAssociateSkillGroupWithRoom ; }
String (  ) { StringBuilder buffer = new StringBuilder ( ) ; buffer . append ( "[SERIESLIST]\n" ) ; buffer . append ( "    .seriesNumbers= " ) . append ( " (" ) . append ( Arrays . toString ( getSeriesNumbers ( ) ) ) . append ( " )" ) ; buffer . append ( "\n" ) ; buffer . append ; return buffer . toString ( ) ; }
QueryConfigHandler (  ) { return ; }
String (  ) { if ( null != originalArgs ) { String className = originalArgs . get ( CLASS_NAME ) ; if ( null != className ) { } } return getClass ( ) . getName ( ) ; }
