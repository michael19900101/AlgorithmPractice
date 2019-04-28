//
//  ShortestPalindrome.swift
//  20190427
//
//  Created by donghongping on 2019/4/27.
//  Copyright © 2019年 com.GuangZhouXuanWu.iphoneEtion. All rights reserved.
//

import Foundation

class Solution {
    func shortestPalindrome(_ s: String) -> String {
        let rev = String(s.reversed())
        let temp = s + "#" + rev
        let next = getKMPNext(str: temp)
        
        let r = rev.prefix((rev.count - next[temp.count - 1] - 1))
        return String(r) + s
    }
    
    func getKMPNext(str: String) -> [Int] {
        var next = [Int](repeating: -1, count: str.count)
        
        let p = str.map { String($0) }
        
        var j: Int = 0
        var k: Int = -1
        
        while j < p.count - 1 {
            if k == -1 || p[j] == p[k] {
                j += 1
                k += 1
                next[j] = k
            }
            else {
                k = next[k]
            }
        }
        
        return next
    }
}
