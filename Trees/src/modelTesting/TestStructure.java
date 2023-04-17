package modelTesting;

/**
 * A record class that contains the necessary performance metrics 
 * for the kd-Tree and PR-QuadTree.
 */
public record TestStructure(int dataSize, float successKD, float failKD, float successPR, float failPR) {}
