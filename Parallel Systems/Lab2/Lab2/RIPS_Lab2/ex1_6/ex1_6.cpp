#include <windows.h>
#include <process.h>
#include <stdio.h>

#define p 4 // количество дочерних потоков

int n = 1000000;
double pi = 0.; // требуется взаимоисключающий доступ
CRITICAL_SECTION cs;

unsigned __stdcall ThreadFunction(void* pvParam)
{
	int nParam = (int)pvParam;
	int i, start;
	double h, sum, x;
	h = 1. / n;
	sum = 0.;
	start = nParam;
	for (i = start; i < n; i += p)
	{
		x = h * i;
		sum += 4. / (1. + x * x);
	}
	// монопольный доступ
	EnterCriticalSection(&cs);
	pi += h * sum;
	LeaveCriticalSection(&cs);
	return 0;
}

int main()
{
	HANDLE hThreads[p];
	int k;
	InitializeCriticalSection(&cs);
	LARGE_INTEGER liFrequency, liStartTime, liFinishTime;
	QueryPerformanceFrequency(&liFrequency);
	QueryPerformanceCounter(&liStartTime);
	// создание дочерних потоков
	for (k = 0; k < p; ++k)
	{
		hThreads[k] = (HANDLE)_beginthreadex(NULL, 0,
			ThreadFunction, (LPVOID)k, 0, NULL);
		if (hThreads[k] == NULL) // обработка ошибки
		{
			printf("Create Thread %d Error=%d\n", k, GetLastError());
			return -1;
		}
	}
	// ожидание завершения дочерних потоков
	WaitForMultipleObjects(p, hThreads, TRUE, INFINITE);
	QueryPerformanceCounter(&liFinishTime);
	double dElapsedTime = 1000. * (liFinishTime.QuadPart - liStartTime.QuadPart) / liFrequency.QuadPart;
	printf("Time=%lf\n", dElapsedTime);
	for (k = 0; k < p; ++k)
		CloseHandle(hThreads[k]);
	// освобождение ресурсов, занятых критической секцией
	DeleteCriticalSection(&cs);
	printf("PI = %.16f\n", pi);
	return 0;
}
