 static inline ogg_uint32_t decode_packed_entry_number(codebook *buffer1,
						      oggpack_buffer *buffer2){
   ogg_uint32_t chase=0;
   int  read=book->dec_maxlength;
   long lok = oggpack_look(b,read),i;

 while(lok<0 && read>1)
    lok = oggpack_look(buffer2, --read);

 if(lok<0){
    oggpack_adv(buffer2,1); /* force eop */
 return -1;
 }

 /* chase the tree with the bits we got */
 switch (buffer1->dec_method)
 {
 case 0:
 {
 /* book->dec_nodeb==1, book->dec_leafw==1 */
 /* 8/8 - Used */

       unsigned char *buffer3=(unsigned char *)book->dec_table;
 
       for(i=0;i<read;i++){
	chase=buffer3[chase*2+((lok>>i)&1)];
	if(chase&0x80UL)break;
       }
       chase&=0x7fUL;
       break;
 }
 case 1:
 {
 /* book->dec_nodeb==1, book->dec_leafw!=1 */

       /* 8/16 - Used by infile2 */
       unsigned char *buffer4=(unsigned char *)book->dec_table;
       for(i=0;i<read;i++){
	int bit=(lok>>i)&1;
	int next=t[chase+bit];
	if(next&0x80){
	  chase= (next<<8) | buffer4[chase+bit+1+(!bit || buffer4[chase]&0x80)];
	  break;
	}
	chase=next;
       }
       chase&=~0x8000UL;
 break;
 }
 case 2:
 {

       /* book->dec_nodeb==2, book->dec_leafw==1 */
       /* 16/16 - Used */
       for(i=0;i<read;i++){
	chase=((ogg_uint16_t *)(buffer1->dec_table))[chase*2+((lok>>i)&1)];
	if(chase&0x8000UL)break;
       }
       chase&=~0x8000UL;
 break;
 }
 case 3:
 {
 /* book->dec_nodeb==2, book->dec_leafw!=1 */

       /* 16/32 - Used by infile2 */
       ogg_uint16_t *buffer5=(ogg_uint16_t *)book->dec_table;
       for(i=0;i<read;i++){
	int bit=(lok>>i)&1;
	int next=t[chase+bit];
	if(next&0x8000){
	  chase= (next<<16) | buffer5[chase+bit+1+(!bit || buffer5[chase]&0x8000)];
	  break;
	}
	chase=next;
       }
       chase&=~0x80000000UL;
 break;
 }
 case 4:

     {
       for(i=0;i<read;i++){
	chase=((ogg_uint32_t *)(buffer1->dec_table))[chase*2+((lok>>i)&1)];
	if(chase&0x80000000UL)break;
       }
       chase&=~0x80000000UL;
 break;
 }
 }

 if(i<read){
    oggpack_adv(buffer2,i+1);
 return chase;
 }
  oggpack_adv(buffer2,read+1);
 return(-1);
}
