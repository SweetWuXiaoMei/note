# AbstractReferenceCountedByteBuf的属性与功能

主要就是一个属性refCnt，用于计数，当被引用时+1，被释放时-1，用于垃圾回收
```
private static final long REFCNT_FIELD_OFFSET;
    private static final AtomicIntegerFieldUpdater<AbstractReferenceCountedByteBuf> refCntUpdater =
            AtomicIntegerFieldUpdater.newUpdater(AbstractReferenceCountedByteBuf.class, "refCnt");

    // even => "real" refcount is (refCnt >>> 1); odd => "real" refcount is 0
    @SuppressWarnings("unused")
    private volatile int refCnt = 2;

    static {
        long refCntFieldOffset = -1;
        try {
            if (PlatformDependent.hasUnsafe()) {
                refCntFieldOffset = PlatformDependent.objectFieldOffset(
                        AbstractReferenceCountedByteBuf.class.getDeclaredField("refCnt"));
            }
        } catch (Throwable ignore) {
            refCntFieldOffset = -1;
        }

        REFCNT_FIELD_OFFSET = refCntFieldOffset;
    }
```

引用时会调用retain方法，将计数+1

```
private ByteBuf retain0(final int increment) {
    // all changes to the raw count are 2x the "real" change
    int adjustedIncrement = increment << 1; // overflow OK here
    int oldRef = refCntUpdater.getAndAdd(this, adjustedIncrement);
    if ((oldRef & 1) != 0) {
        throw new IllegalReferenceCountException(0, increment);
    }
    // don't pass 0!
    if ((oldRef <= 0 && oldRef + adjustedIncrement >= 0)
            || (oldRef >= 0 && oldRef + adjustedIncrement < oldRef)) {
        // overflow case
        refCntUpdater.getAndAdd(this, -adjustedIncrement);
        throw new IllegalReferenceCountException(realRefCnt(oldRef), increment);
    }
    return this;
}
```

引用时会调用release，将计数-1

```
private boolean release0(int decrement) {
    int rawCnt = nonVolatileRawCnt(), realCnt = toLiveRealCnt(rawCnt, decrement);
    if (decrement == realCnt) {
        if (refCntUpdater.compareAndSet(this, rawCnt, 1)) {
            deallocate();
            return true;
        }
        return retryRelease0(decrement);
    }
    return releaseNonFinal0(decrement, rawCnt, realCnt);
}
```