#ifndef CONFIGURATION_H_INCLUIDED
#define CONFIGURATION_H_INCLUIDED

// set to 1 if you want run repository specifications otherwise, 0
#define REPOSITORY_SPECIFICATION 1

// number of executions of the same algorithim
// its a specification of repository
#define TIMES 100

// its a repository specification. 
// Its a number of iterations of each k-means execution
#define NUMBER_OF_ITERATIONS 15

// debug logs
#define DEBUG_LOGS 1

// number of centroids
extern int NUMBER_OF_CENTROIDS;

// number of points
extern int NUMBER_OF_POINTS;

#endif // CONFIGURATION_H_INCLUIDED
