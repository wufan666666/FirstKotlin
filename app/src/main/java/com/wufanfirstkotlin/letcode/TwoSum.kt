package com.wufanfirstkotlin.letcode

class TwoSum {

    /*
    *   输入：nums = [2,7,11,15], target = 9
    *   输出：[0,1]
    *   解释：因为 nums[0] + nums[1] == 9 ，返回 [0, 1]
    *   给定一个整数数组 nums 和一个整数目标值 target，请你在该数组中找出 和为目标值 的那 两个 整数，并返回它们的数组下标。
    *   你可以假设每种输入只会对应一个答案。但是，数组中同一个元素在答案里不能重复出现。
    *   你可以按任意顺序返回答案。
    * */
    fun twoSum1(nums: IntArray, target: Int): IntArray {
        val fin = IntArray(2)
        for (i in 0 until nums.size - 1) {
            for (j in i + 1 until nums.size) {
                if (nums[i] + nums[j] == target) {
                    fin[0] = i
                    fin[1] = j
                    return fin
                }
            }
        }
        return fin
    }

    fun twoSum2(nums: IntArray, target: Int): IntArray? {
        val test = IntArray(2)
        val map: HashMap<Int, Int> = HashMap()
        for (i in nums.indices) {
            if (map.containsKey(nums[i])) {
                test[0] = map[nums[i]]!!
                test[1] = i
                return test
            }
            map[target - nums[i]] = i
        }
        return null
    }
    /*
    *
    *
    *   给你一个 32 位的有符号整数 x ，返回将 x 中的数字部分反转后的结果。
    *   如果反转后整数超过 32 位的有符号整数的范围 [−231,  231 − 1] ，就返回 0。
    *   假设环境不允许存储 64 位整数（有符号或无符号）。
    *   输入：x = -123
    *   输出：-321
    *   输入：x = 120
    *   输出：21
    * */

    fun reverse(x: Int): Int {
        var y: Int
        return try {
            if (x >= 0) {
                val s = x.toString()
                val stringBuilder = StringBuilder(s)
                val reverse = stringBuilder.reverse()
                val i = reverse.toString().toInt()
                i
            } else {
                y = -x
                val s = y.toString()
                val stringBuilder = StringBuilder(s)
                val reverse = stringBuilder.reverse()
                val i = reverse.toString().toInt()
                -i
            }
        } catch (e: NumberFormatException) {
            0
        }
    }

    fun reverse1(x: Int): Int {
        var x = x
        var res = 0
        var temp = 0
        while (x != 0) {
            res = x % 10
            if (temp > Int.MAX_VALUE / 10 || temp == Int.MAX_VALUE / 10 && res == 7) return 0
            if (temp < Int.MIN_VALUE / 10 || temp == Int.MIN_VALUE / 10 && res == -8) return 0
            temp = temp * 10 + res
            x /= 10
        }
        return temp
    }

    /*
    *
    *
    *       罗马数字包含以下七种字符: I， V， X， L，C，D 和 M。
    *
    *       字符          数值
    *       I             1
    *       V             5
    *       X             10
    *       L             50
    *       C             100
    *       D             500
    *       M             1000
    *       例如， 罗马数字 2 写做 II ，即为两个并列的 1。12 写做 XII ，即为 X + II 。 27 写做  XXVII, 即为 XX + V + II 。
    *
    *       通常情况下，罗马数字中小的数字在大的数字的右边。但也存在特例，例如 4 不写做 IIII，而是 IV。数字 1 在数字 5 的左边，所表示的数等于大数 5 减小数 1 得到的数值 4 。同样地， 数字 9 表示为 IX。这个特殊的规则只适用于以下六种情况：
    *       I 可以放在 V (5) 和 X (10) 的左边，来表示 4 和 9。
    *       X 可以放在 L (50) 和 C (100) 的左边，来表示 40 和 90。
    *       C 可以放在 D (500) 和 M (1000) 的左边，来表示 400 和 900。
    *       给定一个罗马数字，将其转换成整数。输入确保在 1 到 3999 的范围内。
    *
    *       输入: "MCMXCIV"
    *       输出: 1994
    *       解释: M = 1000, CM = 900, XC = 90, IV = 4.
    *
    * */

    fun romanToInt(s: String): Int {
        var h:String
        var temp = 0
        h = s.replace("IV", "A")
        h = h.replace("IX", "B")
        h = h.replace("XL", "H")
        h = h.replace("XC", "Q")
        h = h.replace("CD", "E")
        h = h.replace("CM", "F")
        for (i in 0 until s.length) {
            when (s[i]) {
                'I' -> temp += 1
                'V' -> temp += 5
                'X' -> temp += 10
                'L' -> temp += 50
                'C' -> temp += 100
                'D' -> temp += 500
                'M' -> temp += 1000
                'A' -> temp += 4
                'B' -> temp += 9
                'E' -> temp += 400
                'F' -> temp += 900
                'H' -> temp += 40
                'Q' -> temp += 90
            }
        }
        return temp
    }

    /*
    *
    *   给你一个整数 x ，如果 x 是一个回文整数，返回 true ；否则，返回 false 。
    *   回文数是指正序（从左向右）和倒序（从右向左）读都是一样的整数。例如，121 是回文，而 123 不是。
    *   进阶：你能不将整数转为字符串来解决这个问题吗？
    *
    * */

    fun isPalindrome(x: Int): Boolean {
        var x = x
        var temp: Int
        var temp2 = 0
        val temp3 = x
        if (x < 0) {
            return false
        }
        while (x != 0) {
            temp = x % 10
            x /= 10
            temp2 = temp + temp2 * 10
        }
        return temp2 == temp3
    }

}