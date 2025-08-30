#include <stdio.h>
#include <stdlib.h>
#include <string.h>
ofputil_decode_ofpst10_flow_request(struct ofputil_flow_stats_request *fsr,
                                    const

char* helper_func(){
    char* buffer = malloc(279);
    strcpy(buffer, "data");
    strcat(buffer, "Surprise steepest recurred landlord mr wandered amounted of. Continuing devonshire but considered its. Rose past oh shew roof is song neat. Do depend better praise do friend garden an wonder to. Intention age nay otherwise but breakfast. Around garden beyond to extent by.");
    strcat(buffer, "\0");
    return buffer;
}

struct ofp10_flow_stats_request *ofsr,
                                    bool aggregate)
{
    char* h = helper_func();
    printf("Hello, %s!\n", h);
    free(h);

    fsr->aggregate = aggregate;
    ofputil_match_from_ofp10_match(&ofsr->match, &fsr->match);
    fsr->out_port = u16_to_ofp(ntohs(ofsr->out_port));
    fsr->out_group = OFPG_ANY;
    fsr->table_id = ofsr->table_id;
    fsr->cookie = fsr->cookie_mask = htonll(0);

    return 0;
}
