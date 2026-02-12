### Web application for minecraft developers, which automatically replace mapped methods and fields names to original names.

Deployed at [https://mcremapper.onrender.com](https://mcremapper.onrender.com)

Local run:

1. Install java 17 or higher.
2. Download project source.
3. Open console in root project directory.
4. Run one of the following commands:
    ```
    # Windows CMD
    set PORT=8080 && gradlew clean bootRun
    
    # Linux/macOS
    PORT=8080 ./gradlew clean bootRun
    ```
5. Open in your browser: [http://localhost:8080](http://localhost:8080)

How it works:

1. Select minecraft version you are working with.
2. Paste code with mapped names in editor.
3. It will automatically replace mapped names to original names.

Example:

Input clipboard text (minecraft 1.7.10 decompiled source):
```
public void func_149726_b(World world, int x, int y, int z) {
  super.func_149726_b(world, x, y, z);
  int meta = world.func_72805_g(x, y, z);
  if (meta == 1 || meta % 2 == 1) {
    if (!world.field_72995_K && 
      !world.func_72864_z(x, y, z))
      world.func_147465_d(x, y, z, this, meta - 1, 2); 
  } else if (!world.field_72995_K && 
    world.func_72864_z(x, y, z)) {
    world.func_147465_d(x, y, z, this, meta + 1, 2);
  }
}
```

Result after remapping:
```
public void onBlockAdded(World world, int x, int y, int z) {
  super.onBlockAdded(world, x, y, z);
  int meta = world.getBlockMetadata(x, y, z);
  if (meta == 1 || meta % 2 == 1) {
    if (!world.isRemote && 
      !world.isBlockIndirectlyGettingPowered(x, y, z))
      world.setBlock(x, y, z, this, meta - 1, 2); 
  } else if (!world.isRemote && 
    world.isBlockIndirectlyGettingPowered(x, y, z)) {
    world.setBlock(x, y, z, this, meta + 1, 2);
  }
}
```