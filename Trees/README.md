## kd-Tree and PR-QuadTree data structures

CSC201 - Data Structures and Algorithms, 
Technical University of Crete.

April, 2023

The current project contains java implementations of the kd-Tree and the PR-QuadTree. The purpose of this exercise is to
develop the insertion and searching algorithms of the above mentioned and determine their search multiplicities by performing
random searches on them. 

The aforementioned data structures were designed to be generic and are capable of functioning on a variety of data types. The kd-Tree has the advantage in this regard over the PR-QuadTree, since it can work with any data type that extends the Comparable interface. The PR-QuadTree is limited to coordinates of type double and cannot be altered without changing the implementation. However, both have generic fields that can be associated with the coordinates.

## Folder Structure

The workspace contains two folders by default, where:

- `src`: the folder to maintain sources
- `src/model` : the folder that contains the trees
- `src/modelTesting` : the folder that contains the testing utilities.
- `lib`: the folder to maintain dependencies

Meanwhile, the compiled output files will be generated in the `bin` folder by default.

## Installation Instructions
# Prerequisites
- java-14\\
```sudo apt install openjdk-14-jdk```
