Path:

````python
from pathlib import Path

Path('my/path/name').exists()


from os.path import basename

basename('path/to/my/file.txt')
# file.txt

import os
os.path.splitext('file.txt')
# ('file', 'ext')
````

Os:

```python
from os import listdir    
    
    for f in listdir(mypath):
        pass  #might contain a mixture of dirs and files

import glob

glob.glob('/path/to/file/*.txt')

```


Read text file:

````python
with open('myfilename') as f:
    read_data = f.read()
    f.close()
    
    # by line
    for line in f:
        process(line)
        
    # Go to nth byte and read k bytes
    f.seek(n)
    f.read(k)
````

DataFrame

````python
  pd.concat([obj1, obj2], axis={0,1,...}, join="inner"/"outer"
````