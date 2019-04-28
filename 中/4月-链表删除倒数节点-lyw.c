struct ListNode* removeNthFromEnd(struct ListNode* head, int n){
    struct ListNode* original = head;
    int totalCount = 1;
    while (head->next != NULL) {
        totalCount++;
        head = head->next;
    }
    
    head = original;
    int targetCount = totalCount - n;
    if (targetCount == 0) {
        return head->next;
    }
    
    int currentCount = 0;
    while (head->next != NULL) {
        if (targetCount - 1 == currentCount) {
            head->next = head->next->next;
            break;
        }
        currentCount++;
        head = head->next;
    }
    
    return original;
}
