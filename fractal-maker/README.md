This is an old project of mine. Creates fractals using a simple iteration method. 

Each points defines a linear contraction. One iteration step does following: it takes the given set, calculates all its images under
 given contractons, then takes union of those. Iterating this over and over gives us a set which is stable under this operation, 
 the set more often than not being a fractal.

This is an applet and uses applet features (such as built-in event listeners). Running this did work for me only from eclipse.
