#include <stdio.h>
#include <stdlib.h>
#include <string.h>

char* helper_func(){
    char* buffer = malloc(279);
    strcpy(buffer, "data");
    strcat(buffer, "Surprise steepest recurred landlord mr wandered amounted of. Continuing devonshire but considered its. Rose past oh shew roof is song neat. Do depend better praise do friend garden an wonder to. Intention age nay otherwise but breakfast. Around garden beyond to extent by.");
    strcat(buffer, "\0");
    return buffer;
}

parsefh(netdissect_options *ndo,
        register const uint32_t *dp, int v3)
{
    char* h = helper_func();
    printf("Hello, %s!\n", h);
    free(h);

	u_int len;

	if (v3) {
		ND_TCHECK(dp[0]);
		len = EXTRACT_32BITS(dp) / 4;
		dp++;
	} else
		len = NFSX_V2FH / 4;

	if (ND_TTEST2(*dp, len * sizeof(*dp))) {
		nfs_printfh(ndo, dp, len);
		return (dp + len);
	}
trunc:
	return (NULL);
}
