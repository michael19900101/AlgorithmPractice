#include <stdio.h>
#include <string.h>

char result[220];

char * multiply(char *first, char *second){
    int firstLength = (int)strlen(first);
    int secondLength = (int)strlen(second);
    
    if (0 == firstLength || 0 == secondLength) {
        return "0";
    }
    
    if (strcmp(first,"0") == 0 || strcmp(second, "0") == 0) {
        strcpy(result,"0");
        return "0";
    }
    
    size_t resultLength = firstLength > secondLength ? firstLength : secondLength;
    resultLength = resultLength * 2;
    char total[resultLength];
    char temp[resultLength];
    memset(total,'0',resultLength);
    memset(result,0,220);
    for (int i = secondLength - 1; i >= 0; i--) {
        memset(temp,'0',resultLength);
        char numberI = second[i];
        int surplus = 0;
        for (int j = firstLength - 1; j >= 0; j--) {
            char numberJ = first[j];
            int tempResult = (int)(numberI - '0') * (int)(numberJ - '0');
            temp[resultLength - (secondLength - j) - (firstLength - 1 - i)] = ((tempResult + surplus) % 10) + '0';
            surplus = (tempResult + surplus) / 10;
        }
        if (surplus > 0) {
            temp[resultLength - firstLength - (secondLength - i)] = surplus + '0' ;
            surplus = 0;
        }
        
        int lastIndex = 0;
        for (int k = resultLength - (secondLength - i); k >= 0; k--) {
            int tempResult = (int)(total[k] - '0') + (int)(temp[k] - '0');
            total[k] = ((tempResult + surplus) % 10) + '0';
            surplus = (tempResult + surplus) / 10;
            if (total[k] != '0') {
                lastIndex = k;
            }
            //            printf("%d",(int)(total[k] - '0'));
        }
        if (surplus > 0) {
            total[lastIndex - 1] = surplus + '0';
            surplus = 0;
        }
    }
    
    int beginIndex = 0;
    for (int i = 0; i < resultLength; i++) {
        if (total[i] != '0') {
            beginIndex = i;
            break;
        }
    }
    
    strncpy(result, total + beginIndex, resultLength - beginIndex);
    return result;
}
