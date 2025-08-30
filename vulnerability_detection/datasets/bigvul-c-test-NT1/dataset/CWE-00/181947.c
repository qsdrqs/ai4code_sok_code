static void calc_coeff(double buffer1[4], const int buffer2[4], int prefilter, double r2, double mul)
{
    double mul2 = mul * mul, mul3 = mul2 * mul;
    double buffer3[] = {
        (5204 + 2520 * mul + 1092 * mul2 + 3280 * mul3) / 12096,
        (2943 -  210 * mul -  273 * mul2 - 2460 * mul3) / 12096,
        ( 486 -  924 * mul -  546 * mul2 +  984 * mul3) / 12096,
         (  17 -  126 * mul +  273 * mul2 -  164 * mul3) / 12096,
     };
 
    double buffer4[13];
     memcpy(buffer4, buffer3, sizeof(buffer3));
     memset(buffer4 + 4, 0, sizeof(buffer4) - sizeof(buffer3));
     int n = 6;
    coeff_filter(buffer4, n, buffer3);
    for (int k = 0; k < 2 * prefilter; ++k)
        coeff_blur121(buffer4, ++n);

    double buffer5[13];
    n = buffer2[3] + prefilter + 3;
    calc_gauss(buffer5, n, r2);
    memset(buffer5 + n + 1, 0, sizeof(buffer5) - (n + 1) * sizeof(buffer5[0]));
    n -= 3;
    coeff_filter(buffer5, n, buffer3);
    for (int k = 0; k < prefilter; ++k)
        coeff_blur121(buffer5, --n);

    double buffer6[4][4];
    calc_matrix(buffer6, buffer4, buffer2);

    double buffer7[4];
    for (int i = 0; i < 4; ++i)
        buffer7[i] = buffer4[0] - buffer4[buffer2[i]] - buffer5[0] + buffer5[buffer2[i]];

    for (int i = 0; i < 4; ++i) {
        double res = 0;
        for (int j = 0; j < 4; ++j)
            res += buffer6[i][j] * buffer7[j];
        buffer1[i] = FFMAX(0, res);
    }
}
