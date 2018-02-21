#!/bin/bash -e
# The MIT License
#
# Copyright (c) 2004-2009, Sun Microsystems, Inc., Kohsuke Kawaguchi
# Copyright (c) 2014, Oliver Vinn
#
# Permission is hereby granted, free of charge, to any person obtaining a copy
# of this software and associated documentation files (the "Software"), to deal
# in the Software without restriction, including without limitation the rights
# to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
# copies of the Software, and to permit persons to whom the Software is
# furnished to do so, subject to the following conditions:
#
# The above copyright notice and this permission notice shall be included in
# all copies or substantial portions of the Software.
#
# THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
# IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
# FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
# AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
# LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
# OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
# THE SOFTWARE.
#

sizes="16x16 24x24 32x32 48x48"
gif_origins=$(ls origin | grep '.gif')
png_origins=$(ls origin | grep '.png')

for size in $sizes; do
    mkdir -p $size
    for file in $gif_origins; do
        convert origin/$file -resize $size $size/$file
        convert origin/$file -resize $size $size/${file%.gif}_anime.gif
    done
    for file in $png_origins; do
        convert origin/$file -resize $size $size/$file
    done
done
