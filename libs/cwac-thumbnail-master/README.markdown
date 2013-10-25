CWAC Thumbnail: Images in Lists, Without the Wait
=================================================

**THIS PROJECT IS DISCONTINUED &mdash; USE AT YOUR OWN RISK**

So you want to have a `ListView` that, among other things,
displays thumbnail images off the Internet. Doing that in
a performant fashion is a pain, since you have to do the
HTTP requests in the background so as not to tie up the UI
thread. Matching those requests up to their corresponding
`ImageView`s -- and only for those rows presently visible --
is quite annoying.

Unless you use `ThumbnailAdapter`.

`ThumbnailAdapter` handles all of that mess for you. All you
need to do is wrap your regular `ListAdapter` in a `ThumbnailAdapter`,
supplying a `SimpleWebImageCache` (from the [CWAC Cache module][cache])
and the IDs of the `ImageView`s in each row. Then, when you bind
your rows, tuck the URL for the image into the `ImageView` itself
via `setTag()`. `ThumbnailAdapter` handles the rest.

Usage
-----
Full instructions for using this module are forthcoming. Stay
tuned!

Dependencies
------------
This project requires the [CWAC Task module][task], the
[CWAC Bus module][bus], [CWAC AdapterWrapper][adapter],
and the [CWAC Cache module][cache].
A copy of compatible JARs can be found in the `libs/` directory
of the project, though you are welcome to try newer ones, or
ones that you have patched yourself.

Version
-------
This is version 0.1 of this module, meaning it is pretty darn
new.

Demo
----
In the `com.commonsware.cwac.thumbnail.demo` package you will find
a sample activity that demonstrates the use of `ThumbnailAdapter`.

Note that when you build the JAR via `ant jar`, the sample
activity is not included, nor any resources -- only the
compiled classes for the actual library are put into the JAR.

License
-------
The code in this project is licensed under the Apache
Software License 2.0, per the terms of the included LICENSE
file.

Questions
---------
**THIS PROJECT IS UNSUPPORTED**

[task]: http://github.com/commonsguy/cwac-task/tree/master
[bus]: http://github.com/commonsguy/cwac-bus/tree/master
[cache]: http://github.com/commonsguy/cwac-cache/tree/master
[adapter]: http://github.com/commonsguy/cwac-adapter/tree/master
