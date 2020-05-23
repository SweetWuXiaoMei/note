# PooledByteBuf的原理

在内存中分配一个大的内存，将它组成多个chunk，一个chunk包含多个page，page的结构是一个二叉树，在
分配时，如果当前有适合的page，则直接分配，该节点一下的节点都不可用了，如果需要分配的空间小于当前
page的空间，则当前page会等值分裂
![结构图](./page.png)