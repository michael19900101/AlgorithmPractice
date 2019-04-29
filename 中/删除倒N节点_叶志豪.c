struct ListNode {
    int val;
    struct ListNode *next;
};

//用两个指针，第二个与第一个距离为n，同时向后移动，第二个数到尾，第一个相当于倒数
struct ListNode* removeNthFromEnd(struct ListNode* head, int n) {
    struct ListNode* front = (struct ListNode*)malloc(sizeof(struct ListNode));
    front->next = head;
    struct ListNode *t1 = front, *t2 = front;
    int i = 0;
    for(i = 0; i < n; i++)
        t2 = t2->next;
    while(t2->next) {
        t1 = t1->next;
        t2 = t2->next;
    }
    struct ListNode* delete = t1->next;
    t1->next = delete->next;
    free(delete);
    t1 = front->next;
    free(front);
    return t1;
}