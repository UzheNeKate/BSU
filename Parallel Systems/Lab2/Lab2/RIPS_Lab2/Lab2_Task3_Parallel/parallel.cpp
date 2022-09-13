#include<cstdio>
#include<cstdlib>
#include<ctime>
#include<windows.h>

#define CYCLIC false

struct ParallelMatrixData {
	int firstRow;
	int lastRow;
	int step;
	int size;
	double* pAMatrix;
	double* pBVector;
	double* pCVector;
};

void RandomDataInitialization(double*& pAMatrix, double*& pBVector, int size) {
	srand(time(NULL));
	for (int i = 0; i < size; i++) {
		for (int j = 0; j < size; j++) {
			pAMatrix[i * size + j] = rand() % 15;
		}
		pBVector[i] = rand() % 15;
	}
}

// Function for memory allocation and data initialization
void ProcessInitialization(double*& pAMatrix, double*& pBVector, double*& pCVector, int& Size, int& threadsCount) {
	int i; // Loop variables
	do {
		printf("\nEnter size of the initial matricies: ");
		scanf_s("%d", &Size);
		if (Size <= 0) {
			printf("Size of the matricies must be greater than 0! \n ");
		}
	} while (Size <= 0);
	do {
		printf("\nEnter count of the threads: ");
		scanf_s("%d", &threadsCount);
		if (threadsCount <= 0) {
			printf("Count of the threads must be greater than 0! \n ");
		}
	} while (threadsCount <= 0);
	pAMatrix = new double[Size * Size];
	pBVector = new double[Size];
	pCVector = new double[Size];
	for (i = 0; i < Size; i++)
		pCVector[i] = 0;
	RandomDataInitialization(pAMatrix, pBVector, Size);
}

DWORD WINAPI ParallelResultCalculation(LPVOID data) {
	auto* md = (ParallelMatrixData*)data;
	for (int i = md->firstRow; i < md->lastRow; i += md->step)
		for (int j = 0; j < md->size; j++)
			md->pCVector[i] += md->pAMatrix[i * md->size + j] * md->pBVector[j];
	return 0;
}


void InitializeThreadsData(double* pAMatrix, double* pBVector, double* pCVector, 
		ParallelMatrixData*& threadsData, int size, int threadsCount, bool cyclic) {
	int portion = ceil(double(size) / threadsCount);
	for (int i = 0; i < threadsCount; i++) {
		if (cyclic) {
			threadsData[i] = ParallelMatrixData{
				i,
				size,
				threadsCount,
				size,
				pAMatrix,
				pBVector,
				pCVector
			};
		} else {
			threadsData[i] = ParallelMatrixData{
				i * portion,
				min(size, (i + 1) * portion),
				1,
				size,
				pAMatrix,
				pBVector,
				pCVector
			};
		}
	}
}

void ProcessTermination(double*& pAMatrix, double*& pBVector, double*& pCVector,
		HANDLE*& hThreads, ParallelMatrixData*& threadsData, int threadsCount) {
	delete pAMatrix;
	delete pBVector;
	delete pCVector;
	for (int i = 0; i < threadsCount; i++)
		CloseHandle(hThreads[i]);
	delete hThreads;
	delete threadsData;
}

void main(int argc, char* argv[]) {
	double* pAMatrix; // The first argument of matrix multiplication
	double* pBVector; // The second argument of matrix multiplication
	double* pCVector; // The result of matrix multiplication
	int size = 2500; // Sizes of matricies
	int threadsCount = 3;

	// Data initialization
	ProcessInitialization(pAMatrix, pBVector, pCVector, size, threadsCount);
	HANDLE* hThreads = new HANDLE[threadsCount];
	ParallelMatrixData* threadsData = new ParallelMatrixData[threadsCount];

	InitializeThreadsData(pAMatrix, pBVector, pCVector, threadsData, size, threadsCount, CYCLIC);
	// Matrix multiplication
	LARGE_INTEGER liFrequency, liStartTime, liFinishTime;
	QueryPerformanceFrequency(&liFrequency);
	QueryPerformanceCounter(&liStartTime);
	//SerialResultCalculation(pAMatrix, pBVector, pCVector, hThreads, threadsData, size, threadsCount);
	for (int i = 0; i < threadsCount; i++) {
		hThreads[i] = CreateThread(NULL, 0, ParallelResultCalculation, (LPVOID)&threadsData[i], 0, NULL);
		if (hThreads[i] == NULL) // обработка ошибки
		{
			printf("Create Thread %d Error=%d\n", i, GetLastError());
			return;
		}
	}
	WaitForMultipleObjects(threadsCount, hThreads, TRUE, INFINITE);
	QueryPerformanceCounter(&liFinishTime);
	double dElapsedTime = 1000. * (liFinishTime.QuadPart - liStartTime.QuadPart) / liFrequency.QuadPart;
	printf("Time=%lf\n", dElapsedTime);
	// Program termination
	ProcessTermination(pAMatrix, pBVector, pCVector, hThreads, threadsData, threadsCount);
}