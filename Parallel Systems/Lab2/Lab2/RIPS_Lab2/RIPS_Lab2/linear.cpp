#include<iostream>
#include<cstdio>
#include<cstdlib>
#include<ctime>
#include <windows.h>

void RandomDataInitialization(double*& pAMatrix, double *& pBVector, int size) {
	srand(time(NULL));
	for (int i = 0; i < size; i++) {
		for (int j = 0; j < size; j++) {
			pAMatrix[i * size + j] = rand() % 23;
		}
		pBVector[i] = rand() % 10;
	}
}

// Function for memory allocation and data initialization
void ProcessInitialization(double*& pAMatrix, double*& pBVector, double*& pCVector, int& Size) {
	int i, j; // Loop variables
	do {
		printf("\nEnter size of the initial matricies: ");
		scanf_s("%d", &Size);
		if (Size <= 0) {
			printf("Size of the matricies must be greater than 0! \n ");
		}
	} while (Size <= 0);
	pAMatrix = new double[Size * Size];
	pBVector = new double[Size];
	pCVector = new double[Size];
	for (i = 0; i < Size; i++)
		pCVector[i] = 0;
	RandomDataInitialization(pAMatrix, pBVector, Size);
}


// Function for calculating matrix multiplication
void SerialResultCalculation(double* pAMatrix, double* pBVector, double* pCVector, int Size) {
	int i, j; // Loop variables
	for (i = 0; i < Size; i++)
		for (j = 0; j < Size; j++)
			pCVector[i] += pAMatrix[i * Size + j] * pBVector[j];
}

void ProcessTermination(double*& pAMatrix, double*& pBVector, double*& pCVector) {
	delete pAMatrix;
	delete pBVector;
	delete pCVector;
}

void main(int argc, char* argv[]) {
	double* pAMatrix; // The first argument of matrix multiplication
	double* pBVector; // The second argument of matrix multiplication
	double* pCVector; // The result of matrix multiplication
	int size; // Sizes of matricies

	// Data initialization
	ProcessInitialization(pAMatrix, pBVector, pCVector, size);
	// Matrix multiplication
	LARGE_INTEGER liFrequency, liStartTime, liFinishTime;
	QueryPerformanceFrequency(&liFrequency);
	QueryPerformanceCounter(&liStartTime);
	SerialResultCalculation(pAMatrix, pBVector, pCVector, size);
	QueryPerformanceCounter(&liFinishTime);
	double dElapsedTime = 1000. * (liFinishTime.QuadPart - liStartTime.QuadPart) / liFrequency.QuadPart;
	printf("%lf\n", dElapsedTime);
	// Program termination
	ProcessTermination(pAMatrix, pBVector, pCVector);
}

/*void PrintVector(double*& pVector, int size) {
	for (int i = 0; i < size; i++) {
		std::cout << pVector[i] << "\t";
	}
	std::cout << std::endl;
}

void PrintMatrix(double*& pMatrix, int size) {
	for (int i = 0; i < size; i++) {
		for (int j = 0; j < size; j++) {
			std::cout << pMatrix[i * size + j] << "\t";
		}
		std::cout << std::endl;
	}
}*/