//
//  RecoverBinarySearchTree.swift
//  20190427
//
//  Created by donghongping on 2019/4/27.
//  Copyright © 2019年 com.GuangZhouXuanWu.iphoneEtion. All rights reserved.
//

import Foundation

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     public var val: Int
 *     public var left: TreeNode?
 *     public var right: TreeNode?
 *     public init(_ val: Int) {
 *         self.val = val
 *         self.left = nil
 *         self.right = nil
 *     }
 * }
 */
class Solution {
    func recoverTree(_ root: TreeNode?) {
        var cur = root
        
        //前序节点
        var preNode: TreeNode?
        
        var first: TreeNode?
        var second: TreeNode?
        //中序遍历结果中，当前节点的前一个节点
        var preCur: TreeNode?
        
        while cur != nil {
            
            if cur?.left != nil {
                preNode = getPreNode(cur)
                
                if preNode?.right === cur {
                    if preCur!.val > cur!.val {
                        if first == nil {
                            first = preNode
                            second = cur
                        }
                        else {
                            second = cur
                        }
                    }
                    preCur = cur
                    preNode?.right = nil
                    cur = cur?.right
                }
                else {
                    preNode?.right = cur
                    cur = cur?.left
                }
            }
            else {
                if let pre = preCur, pre.val > cur!.val {
                    if first == nil {
                        first = preCur
                        second = cur
                    }
                    else {
                        second = cur
                    }
                }
                preCur = cur
                cur = cur?.right
            }
        }
        
        if first != nil && second != nil {
            swap(&first!.val, &second!.val)
        }
        
    }
    
    func getPreNode(_ node: TreeNode?) -> TreeNode? {
        var preNode = node
        if node?.left != nil {
            preNode = node?.left
            while preNode?.right != nil && preNode?.right !== node {
                preNode = preNode?.right
            }
        }
        
        return preNode
    }
}
