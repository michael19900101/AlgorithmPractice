char* multiply2(char* num1, char* num2) {
    //容错判断
    if(num1 == NULL || num2 == NULL || num1[0] == '0' || num2[0] == '0') {
        char* result = (char*)calloc(2, sizeof(char));
        result[0] = '0';
        return result;
    }
    int len1 = strlen(num1);
    int len2 = strlen(num2);
    int i = 0, j = 0, t = 0, index = 0;
    int* sum = (int*)calloc(220, sizeof(int));
    //对应的乘数放在对应的位置相加
    for(i = 0; i < len1; i++) {
        for(j = 0; j < len2; j++) {
            sum[i + j] += (num1[i] - '0') * (num2[j] - '0');
        }
    }
    //进位计算
    for(i = len1 + len2 - 2; i >= 0; i--) {
        sum[i] += t;
        if(sum[i] >= 10) {
            t = sum[i] / 10;
            sum[i] = sum[i] % 10;
        }
        else
            t = 0;
    }
    //计算字符串长度
    int len = 2 + (t ? len1 + len2 - 1 : len1 + len2 - 2);
    char* result = (char*)malloc(len * sizeof(char));
    if(t)
        result[index++] = '0' + t;
    //数字转字符串
    for(i = 0; i <= len1 + len2 - 2; i++)
        result[index++] = sum[i] + '0';
    result[index] = 0;
    free(sum);
    return result;
}