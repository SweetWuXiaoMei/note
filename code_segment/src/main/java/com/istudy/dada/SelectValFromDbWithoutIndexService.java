package com.istudy.dada;

import java.util.Comparator;
import java.util.function.LongFunction;
import java.util.function.LongSupplier;
import java.util.function.Supplier;

/**
 *
 * 在数据库中，要求有主键自增id，用于获取非索引的某一列中大于
 * /小于某个值的首条数据，例如，从数据库中获取创建时间是2019
 * 年1月1日的第一行，且创建时间没有索引。常用于job类操作首条
 * 语句定位，和分页首条语句定位
 *
 *
 * @author 陈志达
 *
 */
public class SelectValFromDbWithoutIndexService {

    /**
     *
     * 二分查找>=一个数的第一个数
     * T 比较的类型
     * lowSupplier 二分查找低位的提供函数
     * highSupplier 二分查找高位提供函数
     * searchTarget 搜索的目标函数
     * midValSearchFunction 搜索区间中间的数的函数
     * comparator 比较函数
     * @author 陈志达
     *
     */
    public <T> Long getMinId(LongSupplier lowSupplier,
                          LongSupplier highSupplier,
                          Supplier<T> searchTarget,
                          LongFunction<T> midValSearchFunction,
                          Comparator<T> comparator) {

        long result = -1;

        if (lowSupplier == null || highSupplier == null || searchTarget == null || midValSearchFunction == null || comparator == null) {
            return result;
        }

        long low = lowSupplier.getAsLong();
        long high = highSupplier.getAsLong();
        T targetVal = searchTarget.get();

        while (low <= high) {
            long mid = (low + high) >>> 1;

            T midVal = midValSearchFunction.apply(mid);
            int cmp = comparator.compare(midVal, targetVal);

            if (cmp < 0) {
                low = mid + 1;
            } else {
                high = mid - 1;
                result = mid;
            }
        }
        return result;
    }

    /**
     *
     * TODO 查找<=一个值的最后一个值
     *
     * @author zhida.chen@ttpai.cn
     *
     */

}
