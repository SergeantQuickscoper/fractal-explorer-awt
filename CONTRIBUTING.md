# Contributing Guide

This project is also intended as an intro to the AWT, hence the code is quite decently documented for a small project. However, I understand that things may still be unclear to people new to AWT at the beginning, so refer this document for a guide to my initial design and if it still can't clear all your questions please never hesitate to contact me via my mobile number or email, I'll be happy to help.

## Project Structure
The project contains two files as of now:
 - `ExplorerMain.java`
    This file has two main functions, setup the frame and canvas within the main function, host the class `FractalCanvas` which is an override of the `Canvas` AWT class. I will get into more detail about `FractalCanvas`. `FractalCanvas` expects some `FractalLogic` instance (which is defined in the next file) which should pass give it a `BufferredImage` object to draw and also call `regenerateFractal` when re-rendering (example: when zooming in and out).
<br>
 - `FractalLogics.java`
    This file is supposed to be container of all the different fractals that will be a part of the project. The `FractalLogics` class as of now contains the `FractalLogic` interface, which only contains two methods which I think will be required in every `FractalLogic` implementation class in the future, and an implementation of the this interface which of course is the `MandelbrotFractal`. 

### Writing your own Fractal

With the current direction of the project, the expected flow is that you will create your own fractal implementation from `FractalLogic` and keep it as a public, static class within `FractalLogics`, your fractal should also be able to generate a `BuferredImage` of your fractal to be rendered. However, I am not completely sure if this approach could be extended to ALL kinds of fractals so feel free to [raise an issue](https://github.com/SergeantQuickscoper/fractal-explorer-awt/issues/new) if you have  suggestions, found a bug, or if you have project related queries. 

Have fun!

Authored by: Don Chacko <donisepic30@gmail.com>


